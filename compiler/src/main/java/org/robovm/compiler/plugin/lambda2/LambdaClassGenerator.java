/*
 * Copyright (C) 2014 RoboVM AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */
package org.robovm.compiler.plugin.lambda2;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.robovm.compiler.CompilerException;
import org.robovm.compiler.Types;
import soot.*;

import java.util.ArrayList;
import java.util.List;

import static org.objectweb.asm.Opcodes.*;

public class LambdaClassGenerator {
	private static int CLASS_VERSION = 51;
	private int counter = 1;

	public LambdaClass generate(SootClass caller, String invokedName, SootMethodRef invokedType,
			SootMethodType samMethodType, SootMethodHandle implMethod, SootMethodType instantiatedMethodType,
			List<Type> markerInterfaces, List<SootMethodType> bridgeMethods) {
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

		String lambdaClassName = caller.getName().replace('.', '/') + "$$Lambda$" + (counter++);
		String functionalInterface = invokedType.returnType().toString().replace('.', '/');

		cw.visit(CLASS_VERSION, ACC_FINAL + ACC_SUPER + ACC_SYNTHETIC, lambdaClassName, null, "java/lang/Object",
				new String[] { functionalInterface });

		String targetMethod = "<init>";
		createFieldsAndConstructor(lambdaClassName, cw, invokedType, samMethodType, implMethod, instantiatedMethodType);

		// if we perform capturing, we can't cache the
		// lambda instance. We need to create a factory method
		// that returns a new instance of the lambda
		// every time the lambda is invoked. That method
		// will be invoked instead of the <init> method
		// of the lambda by LambdaPlugin.
		if (!invokedType.parameterTypes().isEmpty()) {
			targetMethod = createFactory(lambdaClassName, cw, invokedType, samMethodType, implMethod,
					instantiatedMethodType);
		}
		
		// forward the lambda method
		createForwardingMethod(lambdaClassName, cw, invokedName, samMethodType.getParameterTypes(), samMethodType.getReturnType(), invokedType.parameterTypes(), samMethodType, implMethod, instantiatedMethodType, false);
		
		// create any bridge methods necessary
		for(SootMethodType bridgeMethod: bridgeMethods) {			
			createForwardingMethod(lambdaClassName, cw, invokedName, bridgeMethod.getParameterTypes(), bridgeMethod.getReturnType(), invokedType.parameterTypes(), samMethodType, implMethod, instantiatedMethodType, true);
		}
		cw.visitEnd();

		return new LambdaClass(lambdaClassName, cw.toByteArray(), targetMethod, invokedType.parameterTypes(),
				invokedType.returnType());
	}

	private void createForwardingMethod(String lambdaClassName, ClassWriter cw, String name, List<Type> parameters,
			Type returnType, List<Type> invokedParameters, SootMethodType samMethodType, SootMethodHandle implMethod, SootMethodType instantiatedMethodType, boolean isBridgeMethod) {
		String descriptor = Types.getDescriptor(parameters, returnType);
		String implClassName = implMethod.getMethodRef().declaringClass().getName().replace('.', '/');
		MethodVisitor mv = cw.visitMethod(ACC_PUBLIC | (isBridgeMethod?ACC_BRIDGE:0), name, descriptor, null, null);
		mv.visitCode();

		pushArguments(lambdaClassName, mv, parameters, invokedParameters, implMethod, instantiatedMethodType);
		int invokeOpCode = INVOKESTATIC;
		switch (implMethod.getReferenceKind()) {
		case SootMethodHandle.REF_invokeInterface:
			invokeOpCode = INVOKEINTERFACE;
			break;
		case SootMethodHandle.REF_invokeSpecial:
		case SootMethodHandle.REF_newInvokeSpecial:
			invokeOpCode = INVOKESPECIAL;
			break;
		case SootMethodHandle.REF_invokeStatic:
			invokeOpCode = INVOKESTATIC;
			break;
		case SootMethodHandle.REF_invokeVirtual:
			invokeOpCode = INVOKEVIRTUAL;
			break;
		default:
			throw new CompilerException("Unknown invoke type: " + implMethod.getReferenceKind());
		}
		String implDescriptor = null;
		List<Type> paramTypes = new ArrayList<Type>(implMethod.getMethodType().getParameterTypes());
		// need to remove the first parameter (this) in case this
		// is an instance method
		if (invokeOpCode != INVOKESTATIC && !paramTypes.isEmpty()) {
			paramTypes.remove(0);
		}
		implDescriptor = Types.getDescriptor(paramTypes, implMethod.getMethodType().getReturnType());
		mv.visitMethodInsn(invokeOpCode, implClassName, implMethod.getMethodRef().name(), implDescriptor, false);
		createForwardingMethodReturn(mv, returnType, samMethodType, implMethod, instantiatedMethodType);

		mv.visitMaxs(-1, -1);
		mv.visitEnd();
	}

	private void pushArguments(String lambdaClassName, MethodVisitor mv,
			List<Type> parameters, List<Type> invokedParameters, SootMethodHandle implMethod, SootMethodType instantiatedMethodType) {
		int localIndex = 1; // we start at slot index 1, because this occupies
							// slot 0

		// push the captured arguments, may include
		// the caller's this if the desugared lambda
		// is a instance method
		int i = 0;
		for (Object obj : invokedParameters) {
			Type captureType = (Type) obj;
			mv.visitVarInsn(ALOAD, 0);
			mv.visitFieldInsn(GETFIELD, lambdaClassName, "field" + i, Types.getDescriptor(captureType));
			i++;
		}

		// push the functional interface parameters
		for (Type arg : parameters) {
			mv.visitVarInsn(loadOpcodeForType(arg), localIndex);
			localIndex += slotsForType(arg);
		}
	}

	private void createForwardingMethodReturn(MethodVisitor mv, Type returnType, SootMethodType samMethodType,
			SootMethodHandle implMethod, SootMethodType instantiatedMethodType) {		
		if (returnType.equals(VoidType.v())) {
			mv.visitInsn(RETURN);
		} else if (returnType instanceof PrimType) {
			if (returnType.equals(LongType.v())) {
				mv.visitInsn(LRETURN);
			} else if (returnType.equals(FloatType.v())) {
				mv.visitInsn(FRETURN);
			} else if (returnType.equals(DoubleType.v())) {
				mv.visitInsn(DRETURN);
			} else {
				mv.visitInsn(IRETURN);
			}
		} else {
			mv.visitInsn(ARETURN);
		}
	}

	private void createFieldsAndConstructor(String lambdaClassName, ClassWriter cw, SootMethodRef invokedType,
			SootMethodType samMethodType, SootMethodHandle implMethod, SootMethodType instantiatedMethodType) {
		StringBuffer constructorDescriptor = new StringBuffer();

		// create the fields on the class
		int i = 0;
		for (Object obj : invokedType.parameterTypes()) {
			Type captureType = (Type) obj;
			String typeDesc = Types.getDescriptor(captureType);
			cw.visitField(ACC_PRIVATE + ACC_FINAL, "field" + i, typeDesc, null, null);
			constructorDescriptor.append(typeDesc);
			i++;
		}

		// create constructor
		MethodVisitor mv = cw.visitMethod(0, "<init>", "(" + constructorDescriptor.toString() + ")V", null, null);
		mv.visitCode();

		// calls super
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);

		// store the captures into the fields
		i = 0;
		int localIndex = 1; // we start at slot 1, because this occupies slot 0
		for (Object obj : invokedType.parameterTypes()) {
			Type captureType = (Type) obj;

			// load this for put field
			mv.visitVarInsn(ALOAD, 0);

			// load capture from argument slot
			mv.visitVarInsn(loadOpcodeForType(captureType), localIndex);
			localIndex += slotsForType(captureType);

			// store the capture into the field
			mv.visitFieldInsn(PUTFIELD, lambdaClassName, "field" + i, Types.getDescriptor(captureType));

			i++;
		}

		mv.visitInsn(RETURN);
		mv.visitMaxs(-1, -1);
		mv.visitEnd();
	}

	private String createFactory(String lambdaClassName, ClassWriter cw, SootMethodRef invokedType,
			SootMethodType samMethodType, SootMethodHandle implMethod, SootMethodType instantiatedMethodType) {
		MethodVisitor mv = cw.visitMethod(ACC_STATIC, "getLambdaInstance",
				Types.getDescriptor(invokedType.parameterTypes(), invokedType.returnType()), null, null);
		mv.visitCode();
		mv.visitTypeInsn(NEW, lambdaClassName);
		mv.visitInsn(DUP);
		int i = 0;
		for (Object obj : invokedType.parameterTypes()) {
			Type captureType = (Type) obj;
			mv.visitVarInsn(loadOpcodeForType(captureType), i);
			i += slotsForType(captureType);
		}
		mv.visitMethodInsn(INVOKESPECIAL, lambdaClassName, "<init>",
				Types.getDescriptor(invokedType.parameterTypes(), VoidType.v()), false);
		mv.visitInsn(ARETURN);
		mv.visitMaxs(-1, -1);
		mv.visitEnd();
		return "getLambdaInstance";
	}

	public int loadOpcodeForType(Type type) {
		if (type instanceof PrimType) {
			if (type.equals(LongType.v())) {
				return LLOAD;
			} else if (type.equals(FloatType.v())) {
				return FLOAD;
			} else if (type.equals(DoubleType.v())) {
				return DLOAD;
			} else {
				return ILOAD;
			}
		} else {
			return ALOAD;
		}
	}

	public int slotsForType(Type type) {
		if (type.equals(LongType.v()) || type.equals(DoubleType.v())) {
			return 2;
		} else {
			return 1;
		}
	}
}
