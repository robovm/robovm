package org.robovm.compiler.plugin.lambda2;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.robovm.compiler.Types;
import soot.*;

import java.util.ArrayList;

import static org.objectweb.asm.Opcodes.*;


/**
 * Created by badlogic on 09/07/15.
 */
public class LambdaClassGenerator {
    private static int CLASS_VERSION = 51;
    private int counter = 1;

    public LambdaClass generate(SootClass caller, String invokedName, SootMethodRef invokedType, SootMethodType samMethodType, SootMethodHandle implMethod, SootMethodType instantiatedMethodType) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

        String packageName = caller.getPackageName().replace('.', '/');
        if(!packageName.isEmpty()) {
            packageName += "/";
        }
        String lambdaClassName = packageName + caller.getShortName() + "$$Lambda$" + (counter++);
        String functionalInterface = invokedType.returnType().toString().replace('.', '/');

        cw.visit(CLASS_VERSION,
                ACC_FINAL + ACC_SUPER + ACC_SYNTHETIC,
                lambdaClassName,
                null, "java/lang/Object",
                new String[]{ functionalInterface });

        createConstructor(cw);
        createForwardingMethod(cw, invokedType, samMethodType, implMethod, instantiatedMethodType);
        cw.visitEnd();

        return new LambdaClass(lambdaClassName, cw.toByteArray(), "<init>", new ArrayList<Type>(), invokedType.returnType());
    }

    private void createForwardingMethod(ClassWriter cw, SootMethodRef invokedType, SootMethodType samMethodType, SootMethodHandle implMethod, SootMethodType instantiatedMethodType) {
        String descriptor = Types.getDescriptor(samMethodType.getParameterTypes(), samMethodType.getReturnType());
        String implClassName = implMethod.getMethodRef().declaringClass().getName().replace('.', '/');
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, invokedType.name(), descriptor, null, null);
        mv.visitCode();
        mv.visitMethodInsn(INVOKESTATIC, implClassName, implMethod.getMethodRef().name(), descriptor, false);

        createForwardingMethodReturn(mv, samMethodType, implMethod, instantiatedMethodType);
        mv.visitMaxs(-1, -1);
        mv.visitEnd();
    }

    private void createForwardingMethodReturn(MethodVisitor mv, SootMethodType samMethodType, SootMethodHandle implMethod, SootMethodType instantiatedMethodType) {
        if(samMethodType.getReturnType().toString().equals("void")) {
            mv.visitInsn(RETURN);
        } else {

        }
    }

    private void createConstructor(ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(0, "<init>", "()V", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        mv.visitInsn(RETURN);
        mv.visitMaxs(-1, -1);
        mv.visitEnd();
    }
}
