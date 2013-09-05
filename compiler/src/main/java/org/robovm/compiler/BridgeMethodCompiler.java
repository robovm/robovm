/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.compiler;

import static org.robovm.compiler.Annotations.*;
import static org.robovm.compiler.Bro.*;
import static org.robovm.compiler.Functions.*;
import static org.robovm.compiler.Mangler.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Linkage.*;
import static org.robovm.compiler.llvm.ParameterAttribute.*;
import static org.robovm.compiler.llvm.Type.*;

import java.util.ArrayList;
import java.util.List;

import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.OS;
import org.robovm.compiler.llvm.Argument;
import org.robovm.compiler.llvm.BasicBlockRef;
import org.robovm.compiler.llvm.Br;
import org.robovm.compiler.llvm.ConstantBitcast;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.Global;
import org.robovm.compiler.llvm.Icmp;
import org.robovm.compiler.llvm.Icmp.Condition;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.Label;
import org.robovm.compiler.llvm.Load;
import org.robovm.compiler.llvm.NullConstant;
import org.robovm.compiler.llvm.ParameterAttribute;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.PrimitiveType;
import org.robovm.compiler.llvm.Ret;
import org.robovm.compiler.llvm.StructureType;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.llvm.Unreachable;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;
import org.robovm.compiler.llvm.VariableRef;
import org.robovm.compiler.trampoline.Invokestatic;
import org.robovm.compiler.trampoline.LdcClass;

import soot.SootClass;
import soot.SootMethod;

/**
 * @author niklas
 *
 */
public class BridgeMethodCompiler extends BroMethodCompiler {

    public BridgeMethodCompiler(Config config) {
        super(config);
    }

    private void validateBridgeMethod(SootMethod method) {
        if (!canMarshal(method)) {
            throw new IllegalArgumentException("No @Marshaler for return type of" 
                    + " @Bridge annotated method '" + method.getDeclaration() + "' in class " 
                    + method.getDeclaringClass() + " found");
        }
        for (int i = 0; i < method.getParameterCount(); i++) {
            if (!canMarshal(method, i)) {
                throw new IllegalArgumentException("No @Marshaler for parameter " + (i + 1) 
                        + " of @Bridge annotated method '" + method.getDeclaration() + "' in class " 
                        + method.getDeclaringClass() + " found");
            }            
        }
    }
    
    protected void doCompile(ModuleBuilder moduleBuilder, SootMethod method) {
        validateBridgeMethod(method);

        Function fn = FunctionBuilder.method(method);
        moduleBuilder.addFunction(fn);
        
        Type[] parameterTypes = fn.getType().getParameterTypes();
        String[] parameterNames = fn.getParameterNames();
        ArrayList<Argument> args = new ArrayList<Argument>();
        for (int i = 0; i < parameterTypes.length; i++) {
            args.add(new Argument(new VariableRef(parameterNames[i], parameterTypes[i])));
        }
        
        SootMethod originalMethod = method;
        Value structObj = null;
        boolean passByValue = isPassByValue(originalMethod);
        if (passByValue) {
            // The method returns a struct by value. Determine whether that struct
            // is small enough to be passed in a register or has to be returned
            // using a @StructRet parameter.
            
            Arch arch = config.getArch();
            OS os = config.getOs();
            String triple = config.getTriple();
            int size = getStructType(originalMethod.getReturnType()).getAllocSize(triple);
            if (!os.isReturnedInRegisters(arch, size)) {
                method = createFakeStructRetMethod(method);
                
                // Call Struct.allocate(<returnType>) to allocate a struct instance
                // which will be used as return value.
                VariableRef env = fn.getParameterRef(0);
                LdcClass ldcClass = new LdcClass(getInternalName(method.getDeclaringClass()), 
                        getInternalName(originalMethod.getReturnType()));
                trampolines.add(ldcClass);
                Value cls = call(fn, ldcClass.getFunctionRef(), env);
                Invokestatic invokestatic = new Invokestatic(
                        getInternalName(method.getDeclaringClass()), "org/robovm/rt/bro/Struct", 
                        "allocate", "(Ljava/lang/Class;)Lorg/robovm/rt/bro/Struct;");
                trampolines.add(invokestatic);
                structObj = call(fn, invokestatic.getFunctionRef(), env, cls);
    
                // Insert the allocated struct as arg 1 or 2 (first arg is always the Env*)
                args.add(method.isStatic() ? 1 : 2, new Argument(structObj));
            }
        }
        
        FunctionType targetFnType = getBridgeFunctionType(method);
        if (method == originalMethod && passByValue) {
            // Returns a small struct. We need to change the return type to
            // i8/i16/i32/i64.
            int size = ((StructureType) targetFnType.getReturnType()).getAllocSize(config.getTriple());
            Type t = size <= 1 ? I8 : (size <= 2 ? I16 : (size <= 4 ? I32 : I64));
            targetFnType = new FunctionType(t, targetFnType.isVarargs(), targetFnType.getParameterTypes());
        }

        // Load the address of the resolved @Bridge method
        Variable targetFn = fn.newVariable(targetFnType);
        Global targetFnPtr = new Global(getTargetFnPtrName(originalMethod), 
                _private, new NullConstant(I8_PTR));
        moduleBuilder.addGlobal(targetFnPtr);
        fn.add(new Load(targetFn, new ConstantBitcast(targetFnPtr.ref(), new PointerType(targetFnType))));

        Label nullLabel = new Label();
        Label notNullLabel = new Label();
        Variable nullCheck = fn.newVariable(I1);
        fn.add(new Icmp(nullCheck, Condition.eq, targetFn.ref(), new NullConstant(targetFnType)));
        fn.add(new Br(nullCheck.ref(), fn.newBasicBlockRef(nullLabel), fn.newBasicBlockRef(notNullLabel)));
        fn.newBasicBlock(nullLabel);
        VariableRef env = fn.getParameterRef(0);
        call(fn, BC_THROW_UNSATISIFED_LINK_ERROR, env,
                moduleBuilder.getString(String.format("Bridge method %s.%s%s not bound", className,
                        originalMethod.getName(), getDescriptor(originalMethod))));
        fn.add(new Unreachable());
        fn.newBasicBlock(notNullLabel);
        
        // Marshal args
        
        // Remove Env* from args
        args.remove(0);
        if (!method.isStatic()) {
            // Remove receiver from args
            args.remove(0);
        }

        // Save the Object->handle mapping for each marshaled object. We need it
        // after the native call to call updateObject() on the marshaler for 
        // each value. Since the LLVM variables that store these values are used 
        // after the native call we get the nice side effect that neither the
        // Java objects nor the handles can be garbage collected while we're in
        // native code.
        List<MarshaledArg> marshaledArgs = new ArrayList<MarshaledArg>();
        
        Type[] targetParameterTypes = targetFnType.getParameterTypes();
        for (int i = 0; i < method.getParameterCount(); i++) {
            soot.Type type = method.getParameterType(i);
            if (needsMarshaler(type)) {

                String marshalerClassName = getMarshalerClassName(method, i, false);
                
                // The return type of the marshaler's toNative() method is derived from the target function type.
                Type nativeType = targetParameterTypes[i];

                if (nativeType instanceof PrimitiveType) {
                    if (isEnum(type)) {
                        Value nativeValue = marshalEnumObjectToNative(fn, marshalerClassName, nativeType, env, args.get(i).getValue());
                        args.set(i, new Argument(nativeValue));
                    } else {
                        Value nativeValue = marshalValueObjectToNative(fn, marshalerClassName, nativeType, env, args.get(i).getValue());
                        args.set(i, new Argument(nativeValue));
                    }
                } else {
                    ParameterAttribute[] parameterAttributes = new ParameterAttribute[0];
                    if (isPassByValue(method, i) || isStructRet(method, i)) {
                        // The parameter must not be null. We assume that Structs 
                        // never have a NULL handle so we just check that the Java
                        // Object isn't null.
                        call(fn, CHECK_NULL, env, args.get(i).getValue());
                        parameterAttributes = new ParameterAttribute[1];
                        if (isStructRet(method, i)) {
                            parameterAttributes[0] = sret;
                        } else {
                            parameterAttributes[0] = byval;
                        }
                    }
            
                    MarshaledArg marshaledArg = new MarshaledArg();
                    marshaledArg.paramIndex = i;
                    marshaledArgs.add(marshaledArg);
                    Value nativeValue = marshalObjectToNative(fn, marshalerClassName, marshaledArg, nativeType, env, args.get(i).getValue());
                    args.set(i, new Argument(nativeValue, parameterAttributes));
                }
                
            } else if (hasPointerAnnotation(method, i)) {
                // @Pointer long. Convert from i64 to i8*
                args.set(i, new Argument(marshalLongToPointer(fn, args.get(i).getValue())));                    
            }
        }        
        
        // Execute the call to native code
        BasicBlockRef bbSuccess = fn.newBasicBlockRef(new Label("success"));
        BasicBlockRef bbFailure = fn.newBasicBlockRef(new Label("failure"));
        pushNativeFrame(fn);
        trycatchAllEnter(fn, env, bbSuccess, bbFailure);
        fn.newBasicBlock(bbSuccess.getLabel());
        Value result = callWithArguments(fn, targetFn.ref(), args);
        trycatchLeave(fn, env);
        popNativeFrame(fn);

        // Call Marshaler.updateObject() or Marshaler.updatePtr() for each object that was marshaled before
        // the call.
        for (MarshaledArg value : marshaledArgs) {
            soot.Type type = method.getParameterType(value.paramIndex);
            String marshalerClassName = getMarshalerClassName(method, value.paramIndex, true);
            if (isPtr(type)) {
                // Call the Marshaler's updatePtr() method
                SootClass sootPtrTargetClass = getPtrTargetClass(method, value.paramIndex);
                Value ptrTargetClass = ldcClass(fn, getInternalName(sootPtrTargetClass), env);
                int ptrWrapCount = getPtrWrapCount(method, value.paramIndex);
                Invokestatic invokestatic = new Invokestatic(
                        getInternalName(method.getDeclaringClass()), marshalerClassName, 
                        "updatePtr", "(Lorg/robovm/rt/bro/ptr/Ptr;Ljava/lang/Class;JI)V");
                trampolines.add(invokestatic);
                call(fn, invokestatic.getFunctionRef(), 
                        env, value.object, ptrTargetClass, 
                        value.handle, new IntegerConstant(ptrWrapCount));
            } else {
                // Call the Marshaler's updateObject() method
                Invokestatic invokestatic = new Invokestatic(
                        getInternalName(method.getDeclaringClass()), marshalerClassName, 
                        "updateObject", "(Ljava/lang/Object;J)V");
                trampolines.add(invokestatic);
                call(fn, invokestatic.getFunctionRef(), 
                        env, value.object, value.handle);
            }
        }
        
        // Marshal the return value
        if (needsMarshaler(method.getReturnType())) {
            String marshalerClassName = getMarshalerClassName(method, true);
            String targetClassName = getInternalName(method.getReturnType());
            
            if (passByValue) {
                result = marshalNativeToObject(fn, marshalerClassName, null, env, 
                        targetClassName, result, true, true);
            } else if (targetFnType.getReturnType() instanceof PrimitiveType) {
                if (isEnum(method.getReturnType())) {
                    result = marshalNativeToEnumObject(fn, marshalerClassName, env, targetClassName, result);
                } else {
                    result = marshalNativeToValueObject(fn, marshalerClassName, env, targetClassName, result);
                }
            } else {
                if (isPtr(method.getReturnType())) {
                    result = marshalNativeToPtr(fn, marshalerClassName, null, env, 
                            getPtrTargetClass(method), result, getPtrWrapCount(method));
                } else {
                    result = marshalNativeToObject(fn, marshalerClassName, null, env, 
                            targetClassName, result, passByValue);
                }
            }
        } else if (hasPointerAnnotation(method)) {
            result = marshalPointerToLong(fn, result);
        }
        
        if (method != originalMethod) {
            fn.add(new Ret(structObj));
        } else {
            fn.add(new Ret(result));
        }
        
        fn.newBasicBlock(bbFailure.getLabel());
        trycatchLeave(fn, env);
        popNativeFrame(fn);
        call(fn, BC_THROW_IF_EXCEPTION_OCCURRED, env);
        fn.add(new Unreachable());
    }
    
    public static String getTargetFnPtrName(SootMethod method) {
        return "bridge_" + mangleMethod(method) + "_ptr";
    }
}
