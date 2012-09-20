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

import org.robovm.compiler.llvm.Alloca;
import org.robovm.compiler.llvm.Argument;
import org.robovm.compiler.llvm.Br;
import org.robovm.compiler.llvm.ConstantBitcast;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.Global;
import org.robovm.compiler.llvm.Icmp;
import org.robovm.compiler.llvm.Icmp.Condition;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.Inttoptr;
import org.robovm.compiler.llvm.Label;
import org.robovm.compiler.llvm.Load;
import org.robovm.compiler.llvm.NullConstant;
import org.robovm.compiler.llvm.ParameterAttribute;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.Ptrtoint;
import org.robovm.compiler.llvm.Ret;
import org.robovm.compiler.llvm.Store;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.llvm.Unreachable;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;
import org.robovm.compiler.llvm.VariableRef;
import org.robovm.compiler.trampoline.Invokestatic;
import org.robovm.compiler.trampoline.LdcClass;
import org.robovm.compiler.trampoline.Trampoline;

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
                    + " @Bridge annotated method " + method.getName() + " found");
        }
        for (int i = 0; i < method.getParameterCount(); i++) {
            if (!canMarshal(method, i)) {
                throw new IllegalArgumentException("No @Marshaler for parameter " + (i + 1) 
                        + " of @Bridge annotated method " + method.getName() + " found");
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
        call(innerFn, BC_THROW_UNSATISIFED_LINK_ERROR, innerFn.getParameterRef(0),
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
        class MarshaledValue {
            private Value object;
            private Value handle;
            private String marshalerClassName;
        }
        List<MarshaledValue> marshaledValues = new ArrayList<MarshaledValue>();
        
        Type[] targetParameterTypes = targetFnType.getParameterTypes();
        for (int i = 0; i < sootMethod.getParameterCount(); i++) {
            soot.Type type = sootMethod.getParameterType(i);
            if (needsMarshaler(type)) {

                ParameterAttribute[] parameterAttributes = new ParameterAttribute[0];
                if (isPassByValue(method, i) || isStructRet(method, i)) {
                    // The parameter must not be null. We assume that Structs 
                    // never have a NULL handle so we just check that the Java
                    // Object isn't null.
                    call(innerFn, CHECK_NULL, innerFn.getParameterRef(0), args.get(i).getValue());
                    parameterAttributes = new ParameterAttribute[1];
                    if (isStructRet(method, i)) {
                        parameterAttributes[0] = sret;
                    } else {
                        parameterAttributes[0] = byval;
                    }
                }
                
                MarshaledValue marshaledValue = new MarshaledValue();
                marshaledValues.add(marshaledValue);
                marshaledValue.object = args.get(i).getValue();
                
                // Call the Marshaler's toNative() method
                String marshalerClassName = getMarshalerClassName(method, i);
                Invokestatic invokestatic = new Invokestatic(
                        getInternalName(method.getDeclaringClass()), marshalerClassName, 
                        "toNative", "(Ljava/lang/Object;)J");
                trampolines.add(invokestatic);
                Value argI64 = call(innerFn, invokestatic.getFunctionRef(), 
                        innerFn.getParameterRef(0), args.get(i).getValue());

                marshaledValue.marshalerClassName = marshalerClassName;
                marshaledValue.handle = argI64;
                
                Variable arg = innerFn.newVariable(targetParameterTypes[i]);
                innerFn.add(new Inttoptr(arg, argI64, arg.getType()));
                args.set(i, new Argument(arg.ref(), parameterAttributes));
            } else if (hasPointerAnnotation(method, i)) {
                // @Pointer long. Convert from i64 to i8*
                Variable arg = innerFn.newVariable(I8_PTR);
                innerFn.add(new Inttoptr(arg, args.get(i).getValue(), I8_PTR));
                args.set(i, new Argument(arg.ref()));                    
                
            }
        }        
        
        // Execute the call to native code
        pushNativeFrame(innerFn);
        Value resultInner = callWithArguments(innerFn, targetFn.ref(), args);
        popNativeFrame(innerFn);

        // Call Marshaler.updateObject() for each object that was marshaled before
        // the call.
        for (MarshaledValue value : marshaledValues) {
            // Call the Marshaler's updateObject() method
            Invokestatic invokestatic = new Invokestatic(
                    getInternalName(method.getDeclaringClass()), value.marshalerClassName, 
                    "updateObject", "(Ljava/lang/Object;J)V");
            trampolines.add(invokestatic);
            call(innerFn, invokestatic.getFunctionRef(), 
                    innerFn.getParameterRef(0), value.object, value.handle);
        }
        
        // Marshal the return value
        if (needsMarshaler(method.getReturnType())) {
            Value copy = new IntegerConstant((byte) 0);
            if (isPassByValue(method)) {
                // Copy the result to the stack
                Variable stackCopy = innerFn.newVariable(new PointerType(targetFnType.getReturnType()));
                innerFn.add(new Alloca(stackCopy, targetFnType.getReturnType()));
                innerFn.add(new Store(resultInner, stackCopy.ref()));
                resultInner = stackCopy.ref();
                copy = new IntegerConstant((byte) 1);
            }
            
            // Load the declared Class of the return value
            String targetClassName = getInternalName(method.getReturnType());
            FunctionRef ldcClassFn = null;
            if (targetClassName.equals(this.className)) {
                ldcClassFn = FunctionBuilder.ldcInternal(method.getDeclaringClass()).ref();
            } else {
                Trampoline trampoline = new LdcClass(className, targetClassName);
                trampolines.add(trampoline);
                ldcClassFn = trampoline.getFunctionRef();
            }
            Value returnClass = call(innerFn, ldcClassFn, innerFn.getParameterRef(0));
            
            // Call the Marshaler's toObject() method
            String marshalerClassName = getMarshalerClassName(method);
            Invokestatic invokestatic = new Invokestatic(
                    getInternalName(method.getDeclaringClass()), marshalerClassName, 
                    "toObject", "(Ljava/lang/Class;JZ)Ljava/lang/Object;");
            trampolines.add(invokestatic);
            Variable resultI64 = innerFn.newVariable(I64);
            innerFn.add(new Ptrtoint(resultI64, resultInner, I64));
            resultInner = call(innerFn, invokestatic.getFunctionRef(), 
                    innerFn.getParameterRef(0), returnClass, resultI64.ref(), copy);
        } else if (hasPointerAnnotation(method)) {
            Variable resultI64 = innerFn.newVariable(I64);
            innerFn.add(new Ptrtoint(resultI64, resultInner, I64));
            resultInner = resultI64.ref();
        }
        innerFn.add(new Ret(resultInner));
    }
    
    public static String getTargetFnPtrName(SootMethod method) {
        return "bridge_" + mangleMethod(method) + "_ptr";
    }
}
