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
import static org.robovm.compiler.llvm.FunctionAttribute.*;
import static org.robovm.compiler.llvm.Linkage.*;
import static org.robovm.compiler.llvm.Type.*;

import java.util.ArrayList;
import java.util.List;

import org.robovm.compiler.Bro.MarshalerFlags;
import org.robovm.compiler.MarshalerLookup.MarshalSite;
import org.robovm.compiler.MarshalerLookup.MarshalerMethod;
import org.robovm.compiler.MarshalerLookup.PointerMarshalerMethod;
import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.OS;
import org.robovm.compiler.llvm.Alias;
import org.robovm.compiler.llvm.BasicBlockRef;
import org.robovm.compiler.llvm.Bitcast;
import org.robovm.compiler.llvm.BooleanConstant;
import org.robovm.compiler.llvm.ConstantBitcast;
import org.robovm.compiler.llvm.DataLayout;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.Label;
import org.robovm.compiler.llvm.Linkage;
import org.robovm.compiler.llvm.ParameterAttribute;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.PrimitiveType;
import org.robovm.compiler.llvm.Ret;
import org.robovm.compiler.llvm.StructureType;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.llvm.Unreachable;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;
import org.robovm.compiler.trampoline.Invokestatic;

import soot.SootMethod;


/**
 * 
 */
public class CallbackMethodCompiler extends BroMethodCompiler {

    public CallbackMethodCompiler(Config config) {
        super(config);
    }

    @Override
    protected void doCompile(ModuleBuilder moduleBuilder, SootMethod method) {
        compileCallback(moduleBuilder, method);
    }

    private void validateCallbackMethod(SootMethod method) {
        if (!method.isStatic()) {
            throw new IllegalArgumentException("@Callback annotated method " 
                    + method.getName() + " must be static");
        }
    }
    
    private Function callback(SootMethod method) {
        return new FunctionBuilder(method)
            .type(getCallbackFunctionType(method)).suffix("_callback")
            .linkage(external).attribs(noinline, optsize).build();
    }

    private Function callback(SootMethod method, Type returnType) {
        FunctionType ft = getCallbackFunctionType(method);
        return new FunctionBuilder(method)
            .type(new FunctionType(returnType, ft.isVarargs(), ft.getParameterTypes())).suffix("_callback")
            .linkage(external).attribs(noinline, optsize).build();
    }
    
    private void compileCallback(ModuleBuilder moduleBuilder, SootMethod method) {
        validateCallbackMethod(method);

        DataLayout dataLayout = config.getDataLayout();
        SootMethod originalMethod = method;
        boolean passByValue = isPassByValue(originalMethod);
        if (passByValue) {
            // The method returns a struct by value. Determine whether that struct
            // is small enough to be passed in a register or has to be returned
            // using a @StructRet parameter.
            
            Arch arch = config.getArch();
            OS os = config.getOs();
            int size = dataLayout.getAllocSize(getStructType(originalMethod.getReturnType()));
            if (!os.isReturnedInRegisters(arch, size)) {
                method = createFakeStructRetMethod(method);
            }
        }
        
        Function callbackFn = callback(method);
        if (originalMethod != method) {
            callbackFn.setParameterAttributes(0, ParameterAttribute.sret);
        } else if (passByValue) {
            // Returns a small struct. We need to change the return type to
            // i8/i16/i32/i64.
            int size = dataLayout.getAllocSize(callbackFn.getType().getReturnType());
            Type t = size <= 1 ? I8 : (size <= 2 ? I16 : (size <= 4 ? I32 : I64));
            callbackFn = callback(method, t);
        }
        moduleBuilder.addFunction(callbackFn);
        moduleBuilder.addAlias(new Alias(mangleMethod(originalMethod) + "_callback_i8p", 
                Linkage._private, new ConstantBitcast(callbackFn.ref(), I8_PTR_PTR)));

        String targetName = mangleMethod(originalMethod);
        if (originalMethod.isSynchronized()) {
            targetName += "_synchronized";
        }
        FunctionRef targetFn = new FunctionRef(targetName, getFunctionType(originalMethod));
        
        // Increase the attach count for the current thread (attaches the thread
        // if not attached)
        Value env = call(callbackFn, BC_ATTACH_THREAD_FROM_CALLBACK);

        BasicBlockRef bbSuccess = callbackFn.newBasicBlockRef(new Label("success"));
        BasicBlockRef bbFailure = callbackFn.newBasicBlockRef(new Label("failure"));
        pushCallbackFrame(callbackFn, env);
        trycatchAllEnter(callbackFn, env, bbSuccess, bbFailure);
        callbackFn.newBasicBlock(bbSuccess.getLabel());

        List<MarshaledArg> marshaledArgs = new ArrayList<MarshaledArg>();
        
        ArrayList<Value> args = new ArrayList<Value>();
        args.add(env);
        
        // Skip the first parameter if we're returning a large struct by value.
        int start = originalMethod == method ? 0 : 1;
        
        for (int i = start; i < method.getParameterCount(); i++) {
            Value arg = callbackFn.getParameterRef(i);
            soot.Type type = method.getParameterType(i);
            
            if (needsMarshaler(type)) {
                MarshalerMethod marshalerMethod = config.getMarshalerLookup().findMarshalerMethod(new MarshalSite(method, i));
                String targetClassName = getInternalName(type);

                if (arg.getType() instanceof PrimitiveType) {
                    arg = marshalNativeToValueObject(callbackFn, marshalerMethod, env, targetClassName, arg,
                            MarshalerFlags.CALL_TYPE_CALLBACK);
                } else {
                    MarshaledArg marshaledArg = new MarshaledArg();
                    marshaledArg.paramIndex = i;
                    marshaledArgs.add(marshaledArg);
                    arg = marshalNativeToObject(callbackFn, marshalerMethod, marshaledArg, env, targetClassName, arg,
                            MarshalerFlags.CALL_TYPE_CALLBACK);
                }
            } else if (hasPointerAnnotation(method, i)) {
                arg = marshalPointerToLong(callbackFn, arg);
            }
            args.add(arg);
        }
        
        Value result = call(callbackFn, targetFn, args);
        
        // Call Marshaler.updateNative() for each object that was marshaled before
        // the call.
        updateNative(method, callbackFn, env, MarshalerFlags.CALL_TYPE_CALLBACK, marshaledArgs);
        
        // Marshal the returned value to a native value before returning
        if (needsMarshaler(method.getReturnType())) {
            MarshalerMethod marshalerMethod = config.getMarshalerLookup().findMarshalerMethod(new MarshalSite(method));
            Type nativeType = callbackFn.getType().getReturnType();
            
            if (passByValue) {
                // Small struct.
                result = marshalObjectToNative(callbackFn, marshalerMethod, null, nativeType, env, result, 
                        MarshalerFlags.CALL_TYPE_CALLBACK, true);
            } else if (nativeType instanceof PrimitiveType) {
                result = marshalValueObjectToNative(callbackFn, marshalerMethod, nativeType, env, result,
                        MarshalerFlags.CALL_TYPE_CALLBACK);
            } else {
                result = marshalObjectToNative(callbackFn, marshalerMethod, null, nativeType, env, result,
                        MarshalerFlags.CALL_TYPE_CALLBACK);
            }
        } else if (hasPointerAnnotation(method)) {
            result = marshalLongToPointer(callbackFn, result);
        } else if (originalMethod != method) {
            // The original method returns a large struct by value. The callback
            // function takes a struct allocated on the stack by the caller as
            // it's first parameter. We need to copy the struct which the Java
            // method returned to the struct passed in by the caller.
            MarshalerMethod marshalerMethod = config.getMarshalerLookup().findMarshalerMethod(new MarshalSite(originalMethod));
            PointerType nativeType = (PointerType) callbackFn.getType().getParameterTypes()[0];
            Value addr = marshalObjectToNative(callbackFn, marshalerMethod, null, nativeType, env, result,
                    MarshalerFlags.CALL_TYPE_CALLBACK);
            Variable src = callbackFn.newVariable(I8_PTR);
            Variable dest = callbackFn.newVariable(I8_PTR);
            callbackFn.add(new Bitcast(src, addr, I8_PTR));
            callbackFn.add(new Bitcast(dest, callbackFn.getParameterRef(0), I8_PTR));
            call(callbackFn, LLVM_MEMCPY, dest.ref(), src.ref(), 
                    sizeof((StructureType) nativeType.getBase()), new IntegerConstant(0), BooleanConstant.FALSE);
            
            // Make sure the callback returns void.
            result = null;
        }
        
        trycatchLeave(callbackFn, env);
        popCallbackFrame(callbackFn, env);
        call(callbackFn, BC_DETACH_THREAD_FROM_CALLBACK, env);
        callbackFn.add(new Ret(result));

        callbackFn.newBasicBlock(bbFailure.getLabel());
        trycatchLeave(callbackFn, env);
        popCallbackFrame(callbackFn, env);
        Value ex = call(callbackFn, BC_EXCEPTION_CLEAR, env);
        // Call Marshaler.updateNative() for each object that was marshaled before
        // the call.
        updateNative(method, callbackFn, env, MarshalerFlags.CALL_TYPE_CALLBACK, marshaledArgs);
        call(callbackFn, BC_DETACH_THREAD_FROM_CALLBACK, env);
        call(callbackFn, BC_THROW, env, ex);
        callbackFn.add(new Unreachable());
    }

    private void updateNative(SootMethod method, Function fn, Value env, long flags, List<MarshaledArg> marshaledArgs) {
        for (MarshaledArg value : marshaledArgs) {
            MarshalerMethod marshalerMethod = config.getMarshalerLookup().findMarshalerMethod(new MarshalSite(method, value.paramIndex));
            SootMethod afterMethod = ((PointerMarshalerMethod) marshalerMethod).getAfterCallbackCallMethod();
            if (afterMethod != null) {
                Invokestatic invokestatic = new Invokestatic(
                        getInternalName(method.getDeclaringClass()),
                        getInternalName(afterMethod.getDeclaringClass()), 
                        afterMethod.getName(),
                        getDescriptor(afterMethod));
                trampolines.add(invokestatic);
                call(fn, invokestatic.getFunctionRef(), 
                        env, value.handle, value.object, 
                        new IntegerConstant(flags));
            }
        }
    }
}
