/*
 * Copyright (C) 2012 RoboVM
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
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.llvm.Unreachable;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;
import org.robovm.compiler.llvm.VariableRef;
import org.robovm.compiler.trampoline.Invokestatic;

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

        Function outerFn = FunctionBuilder.method(method);
        moduleBuilder.addFunction(outerFn);
        Function innerFn = FunctionBuilder.bridgeInner(method);
        moduleBuilder.addFunction(innerFn);
        
        Type[] parameterTypes = innerFn.getType().getParameterTypes();
        String[] parameterNames = innerFn.getParameterNames();
        ArrayList<Argument> args = new ArrayList<Argument>();
        for (int i = 0; i < parameterTypes.length; i++) {
            args.add(new Argument(new VariableRef(parameterNames[i], parameterTypes[i])));
        }
        
        Value resultOuter = callWithArguments(outerFn, innerFn.ref(), args);
        outerFn.add(new Ret(resultOuter));

        FunctionType targetFnType = getBridgeFunctionType(method);

        // Load the address of the resolved @Bridge method
        Variable targetFn = innerFn.newVariable(targetFnType);
        Global targetFnPtr = new Global(getTargetFnPtrName(method), 
                _private, new NullConstant(I8_PTR));
        moduleBuilder.addGlobal(targetFnPtr);
        innerFn.add(new Load(targetFn, new ConstantBitcast(targetFnPtr.ref(), new PointerType(targetFnType))));

        Label nullLabel = new Label();
        Label notNullLabel = new Label();
        Variable nullCheck = innerFn.newVariable(I1);
        innerFn.add(new Icmp(nullCheck, Condition.eq, targetFn.ref(), new NullConstant(targetFnType)));
        innerFn.add(new Br(nullCheck.ref(), innerFn.newBasicBlockRef(nullLabel), innerFn.newBasicBlockRef(notNullLabel)));
        innerFn.newBasicBlock(nullLabel);
        VariableRef env = innerFn.getParameterRef(0);
        call(innerFn, BC_THROW_UNSATISIFED_LINK_ERROR, env,
                moduleBuilder.getString(String.format("Bridge method %s.%s%s not bound", className,
                        method.getName(), getDescriptor(method))));
        innerFn.add(new Unreachable());
        innerFn.newBasicBlock(notNullLabel);
        
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
        for (int i = 0; i < sootMethod.getParameterCount(); i++) {
            soot.Type type = sootMethod.getParameterType(i);
            if (needsMarshaler(type)) {

                String marshalerClassName = getMarshalerClassName(method, i, false);
                
                // The return type of the marshaler's toNative() method is derived from the target function type.
                Type nativeType = targetParameterTypes[i];

                if (nativeType instanceof PrimitiveType) {
                    if (isEnum(type)) {
                        Value nativeValue = marshalEnumObjectToNative(innerFn, marshalerClassName, nativeType, env, args.get(i).getValue());
                        args.set(i, new Argument(nativeValue));
                    } else {
                        Value nativeValue = marshalValueObjectToNative(innerFn, marshalerClassName, nativeType, env, args.get(i).getValue());
                        args.set(i, new Argument(nativeValue));
                    }
                } else {
                    ParameterAttribute[] parameterAttributes = new ParameterAttribute[0];
                    if (isPassByValue(method, i) || isStructRet(method, i)) {
                        // The parameter must not be null. We assume that Structs 
                        // never have a NULL handle so we just check that the Java
                        // Object isn't null.
                        call(innerFn, CHECK_NULL, env, args.get(i).getValue());
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
                    Value nativeValue = marshalObjectToNative(innerFn, marshalerClassName, marshaledArg, nativeType, env, args.get(i).getValue());
                    args.set(i, new Argument(nativeValue, parameterAttributes));
                }
                
            } else if (hasPointerAnnotation(method, i)) {
                // @Pointer long. Convert from i64 to i8*
                args.set(i, new Argument(marshalLongToPointer(innerFn, args.get(i).getValue())));                    
            }
        }        
        
        // Execute the call to native code
        BasicBlockRef bbSuccess = innerFn.newBasicBlockRef(new Label("success"));
        BasicBlockRef bbFailure = innerFn.newBasicBlockRef(new Label("failure"));
        pushNativeFrame(innerFn);
        trycatchAllEnter(innerFn, env, bbSuccess, bbFailure);
        innerFn.newBasicBlock(bbSuccess.getLabel());
        Value resultInner = callWithArguments(innerFn, targetFn.ref(), args);
        trycatchLeave(innerFn, env);
        popNativeFrame(innerFn);

        // Call Marshaler.updateObject() or Marshaler.updatePtr() for each object that was marshaled before
        // the call.
        for (MarshaledArg value : marshaledArgs) {
            soot.Type type = sootMethod.getParameterType(value.paramIndex);
            String marshalerClassName = getMarshalerClassName(method, value.paramIndex, true);
            if (isPtr(type)) {
                // Call the Marshaler's updatePtr() method
                SootClass sootPtrTargetClass = getPtrTargetClass(method, value.paramIndex);
                Value ptrTargetClass = ldcClass(innerFn, getInternalName(sootPtrTargetClass), env);
                int ptrWrapCount = getPtrWrapCount(method, value.paramIndex);
                Invokestatic invokestatic = new Invokestatic(
                        getInternalName(method.getDeclaringClass()), marshalerClassName, 
                        "updatePtr", "(Lorg/robovm/rt/bro/ptr/Ptr;Ljava/lang/Class;JI)V");
                trampolines.add(invokestatic);
                call(innerFn, invokestatic.getFunctionRef(), 
                        env, value.object, ptrTargetClass, 
                        value.handle, new IntegerConstant(ptrWrapCount));
            } else {
                // Call the Marshaler's updateObject() method
                Invokestatic invokestatic = new Invokestatic(
                        getInternalName(method.getDeclaringClass()), marshalerClassName, 
                        "updateObject", "(Ljava/lang/Object;J)V");
                trampolines.add(invokestatic);
                call(innerFn, invokestatic.getFunctionRef(), 
                        env, value.object, value.handle);
            }
        }
        
        // Marshal the return value
        if (needsMarshaler(method.getReturnType())) {
            String marshalerClassName = getMarshalerClassName(method, true);
            String targetClassName = getInternalName(method.getReturnType());
            
            if (targetFnType.getReturnType() instanceof PrimitiveType) {
                if (isEnum(method.getReturnType())) {
                    resultInner = marshalNativeToEnumObject(innerFn, marshalerClassName, env, targetClassName, resultInner);
                } else {
                    resultInner = marshalNativeToValueObject(innerFn, marshalerClassName, env, targetClassName, resultInner);
                }
            } else {
                if (isPtr(method.getReturnType())) {
                    resultInner = marshalNativeToPtr(innerFn, marshalerClassName, null, env, 
                            getPtrTargetClass(method), resultInner, getPtrWrapCount(method));
                } else {
                    resultInner = marshalNativeToObject(innerFn, marshalerClassName, null, env, 
                            targetClassName, resultInner, isPassByValue(method));
                }
            }
        } else if (hasPointerAnnotation(method)) {
            resultInner = marshalPointerToLong(innerFn, resultInner);
        }
        innerFn.add(new Ret(resultInner));
        
        innerFn.newBasicBlock(bbFailure.getLabel());
        trycatchLeave(innerFn, env);
        popNativeFrame(innerFn);
        call(innerFn, BC_THROW_IF_EXCEPTION_OCCURRED, env);
        innerFn.add(new Unreachable());
    }
    
    public static String getTargetFnPtrName(SootMethod method) {
        return "bridge_" + mangleMethod(method) + "_ptr";
    }
}
