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
import static org.robovm.compiler.llvm.Type.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.robovm.compiler.llvm.Alloca;
import org.robovm.compiler.llvm.BasicBlockRef;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.Inttoptr;
import org.robovm.compiler.llvm.Label;
import org.robovm.compiler.llvm.Load;
import org.robovm.compiler.llvm.NullConstant;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.Ptrtoint;
import org.robovm.compiler.llvm.Ret;
import org.robovm.compiler.llvm.Store;
import org.robovm.compiler.llvm.Unreachable;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;
import org.robovm.compiler.trampoline.Invokestatic;
import org.robovm.compiler.trampoline.LdcClass;
import org.robovm.compiler.trampoline.Trampoline;

import soot.SootClass;
import soot.SootMethod;

/**
 *
 * @version $Id$
 */
public abstract class AbstractMethodCompiler {
    protected String className;
    protected SootMethod sootMethod;
    protected Set<Trampoline> trampolines;
    protected Set<String> catches;
    
    public AbstractMethodCompiler(Config config) {
    }
    
    public Set<Trampoline> getTrampolines() {
        return trampolines;
    }
    
    public Set<String> getCatches() {
        return catches;
    }
    
    public void compile(ModuleBuilder moduleBuilder, SootMethod method) {
        className = getInternalName(method.getDeclaringClass());
        sootMethod = method;
        trampolines = new HashSet<Trampoline>();
        catches = new HashSet<String>();
        doCompile(moduleBuilder, method);
        if (method.isSynchronized()) {
            compileSynchronizedWrapper(moduleBuilder, method);
        }
        if (isCallback(method)) {
            compileCallback(moduleBuilder, method);
        }
    }
        
    protected abstract void doCompile(ModuleBuilder moduleBuilder, SootMethod method);

    private void compileSynchronizedWrapper(ModuleBuilder moduleBuilder, SootMethod method) {
        String targetName = mangleMethod(method);
        Function syncFn = FunctionBuilder.synchronizedWrapper(method);
        moduleBuilder.addFunction(syncFn);
        FunctionType functionType = syncFn.getType();
        FunctionRef target = new FunctionRef(targetName, functionType);
        
        Value monitor = null;
        if (method.isStatic()) {
            FunctionRef fn = FunctionBuilder.ldcInternal(sootMethod.getDeclaringClass()).ref();
            monitor = call(syncFn, fn, syncFn.getParameterRef(0));
        } else {
            monitor = syncFn.getParameterRef(1);
        }
        
        call(syncFn, BC_MONITOR_ENTER, syncFn.getParameterRef(0), monitor);
        BasicBlockRef bbSuccess = syncFn.newBasicBlockRef(new Label("success"));
        BasicBlockRef bbFailure = syncFn.newBasicBlockRef(new Label("failure"));
        trycatchAllEnter(syncFn, bbSuccess, bbFailure);

        syncFn.newBasicBlock(bbSuccess.getLabel());
        Value result = call(syncFn, target, syncFn.getParameterRefs());
        trycatchLeave(syncFn);
        call(syncFn, BC_MONITOR_EXIT, syncFn.getParameterRef(0), monitor);
        syncFn.add(new Ret(result));

        syncFn.newBasicBlock(bbFailure.getLabel());
        trycatchLeave(syncFn);
        call(syncFn, BC_MONITOR_EXIT, syncFn.getParameterRef(0), monitor);
        call(syncFn, BC_THROW_IF_EXCEPTION_OCCURRED, syncFn.getParameterRef(0));
        syncFn.add(new Unreachable());
    }
    
    private void validateCallbackMethod(SootMethod method) {
        if (!method.isStatic()) {
            throw new IllegalArgumentException("@Callback annotated method " 
                    + method.getName() + " must be static");
        }
        if (!canMarshal(method)) {
            throw new IllegalArgumentException("No @Marshaler for return type of" 
                    + " @Callback annotated method " + method.getName() + " found");
        }
        for (int i = 0; i < method.getParameterCount(); i++) {
            if (!canMarshal(method, i)) {
                throw new IllegalArgumentException("No @Marshaler for parameter " + (i + 1) 
                        + " of @Callback annotated method " + method.getName() + " found");
            }            
        }        
    }
    
    private void compileCallback(ModuleBuilder moduleBuilder, SootMethod method) {
        validateCallbackMethod(method);

        Function callbackFn = FunctionBuilder.callback(method);
        moduleBuilder.addFunction(callbackFn);

        String targetName = mangleMethod(method);
        if (method.isSynchronized()) {
            targetName += "_synchronized";
        }
        FunctionRef targetFn = new FunctionRef(targetName, getFunctionType(method));
        
        // Increase the attach count for the current thread (attaches the thread
        // if not attached)
        Value env = call(callbackFn, BC_ATTACH_THREAD_FROM_CALLBACK);

        BasicBlockRef bbSuccess = callbackFn.newBasicBlockRef(new Label("success"));
        BasicBlockRef bbFailure = callbackFn.newBasicBlockRef(new Label("failure"));
        pushCallbackFrame(callbackFn, env);
        trycatchAllEnter(callbackFn, env, bbSuccess, bbFailure);
        callbackFn.newBasicBlock(bbSuccess.getLabel());

        class MarshaledValue {
            private Value object;
            private Value handle;
            private int paramIndex;
        }
        List<MarshaledValue> marshaledValues = new ArrayList<MarshaledValue>();
        
        ArrayList<Value> args = new ArrayList<Value>();
        args.add(env);
        
        for (int i = 0; i < sootMethod.getParameterCount(); i++) {
            Value arg = callbackFn.getParameterRef(i);
            soot.Type type = sootMethod.getParameterType(i);
            if (needsMarshaler(type)) {
                String marshalerClassName = getMarshalerClassName(method, i, true);

                if (!isEnum(type)) {
                    if (isPassByValue(method, i)) {
                        // Copy the arg to the stack
                        Variable stackCopy = callbackFn.newVariable(new PointerType(arg.getType()));
                        callbackFn.add(new Alloca(stackCopy, arg.getType()));
                        callbackFn.add(new Store(arg, stackCopy.ref()));
                        arg = stackCopy.ref();
                    }
    
                    Variable tmp = callbackFn.newVariable(I64);
                    callbackFn.add(new Ptrtoint(tmp, arg, I64));
                    Value handle = tmp.ref();
                    
                    if (isPtr(type)) {
                        // Call the Marshaler's toPtr() method
                        SootClass sootPtrTargetClass = getPtrTargetClass(method, i);
                        Value ptrTargetClass = ldcClass(callbackFn, getInternalName(sootPtrTargetClass), env);
                        int ptrWrapCount = getPtrWrapCount(method, i);
                        Invokestatic invokestatic = new Invokestatic(
                                getInternalName(method.getDeclaringClass()), marshalerClassName, 
                                "toPtr", "(Ljava/lang/Class;JI)Lorg/robovm/rt/bro/ptr/Ptr;");
                        trampolines.add(invokestatic);
                        arg = call(callbackFn, invokestatic.getFunctionRef(), 
                                env, ptrTargetClass, 
                                handle, new IntegerConstant(ptrWrapCount));
                    } else {
                        // Load the declared Class of the parameter
                        String targetClassName = getInternalName(type);
                        Value targetClass = ldcClass(callbackFn, targetClassName, env);
                        
                        // Call the Marshaler's toObject() method
                        Invokestatic invokestatic = new Invokestatic(
                                getInternalName(method.getDeclaringClass()), marshalerClassName, 
                                "toObject", "(Ljava/lang/Class;JZ)Ljava/lang/Object;");
                        trampolines.add(invokestatic);
                        arg = call(callbackFn, invokestatic.getFunctionRef(), 
                                env, targetClass, handle, new IntegerConstant((byte) 0));
                    }
                    
                    MarshaledValue marshaledValue = new MarshaledValue();
                    marshaledValues.add(marshaledValue);
                    marshaledValue.handle = handle;
                    marshaledValue.paramIndex = i;
                    marshaledValue.object = arg;
                } else {
                    String enumClassName = getInternalName(type);
                    Invokestatic invokeValues = new Invokestatic(
                            getInternalName(method.getDeclaringClass()), enumClassName, 
                            "values", String.format("()[L%s;", enumClassName));
                    trampolines.add(invokeValues);
                    Value values = call(callbackFn, invokeValues.getFunctionRef(), env);
                    
                    Invokestatic invokeToObject = new Invokestatic(
                            getInternalName(method.getDeclaringClass()), marshalerClassName, 
                            "toObject", "([Ljava/lang/Enum;I)Ljava/lang/Enum;");
                    trampolines.add(invokeToObject);
                    arg = call(callbackFn, invokeToObject.getFunctionRef(), 
                            env, values, arg);
                }
                
            } else if (hasPointerAnnotation(method, i)) {
                Variable resultI64 = callbackFn.newVariable(I64);
                callbackFn.add(new Ptrtoint(resultI64, arg, I64));
                arg = resultI64.ref();
            }
            args.add(arg);
        }
        
        Value result = call(callbackFn, targetFn, args);
        
        // Call Marshaler.updateNative() for each object that was marshaled before
        // the call.
        for (MarshaledValue value : marshaledValues) {
            String marshalerClassName = getMarshalerClassName(method, value.paramIndex, false);
            // Call the Marshaler's updateNative() method
            Invokestatic invokestatic = new Invokestatic(
                    getInternalName(method.getDeclaringClass()), marshalerClassName, 
                    "updateNative", "(Ljava/lang/Object;J)V");
            trampolines.add(invokestatic);
            call(callbackFn, invokestatic.getFunctionRef(), 
                    env, value.object, value.handle);
        }

        
        // Marshal the returned value to a native value before returning
        if (needsMarshaler(method.getReturnType())) {
            String marshalerClassName = getMarshalerClassName(method, false);
            // Call the Marshaler's toNative() method
            if (!isEnum(method.getReturnType())) {
                Invokestatic invokestatic = new Invokestatic(
                        getInternalName(method.getDeclaringClass()), marshalerClassName, 
                        "toNative", "(Ljava/lang/Object;)J");
                trampolines.add(invokestatic);
                Value resultI64 = call(callbackFn, invokestatic.getFunctionRef(), 
                        env, result);
                
                // Convert the returned long to an appropriate value
                Variable resultVal = callbackFn.newVariable(callbackFn.getType().getReturnType());
                if (isPassByValue(method)) {
                    Variable tmp = callbackFn.newVariable(new PointerType(resultVal.getType()));
                    callbackFn.add(new Inttoptr(tmp, resultI64, tmp.getType()));
                    callbackFn.add(new Load(resultVal, tmp.ref()));
                } else {
                    callbackFn.add(new Inttoptr(resultVal, resultI64, resultVal.getType()));
                }
                result = resultVal.ref();
            } else {
                Invokestatic invokestatic = new Invokestatic(
                        getInternalName(method.getDeclaringClass()), marshalerClassName, 
                        "toNative", "(Ljava/lang/Enum;)I");
                trampolines.add(invokestatic);
                result = call(callbackFn, invokestatic.getFunctionRef(), 
                        env, result);
            }
        } else if (hasPointerAnnotation(method)) {
            // @Pointer long. Convert from i64 to i8*
            Variable resultI8Ptr = callbackFn.newVariable(I8_PTR);
            callbackFn.add(new Inttoptr(resultI8Ptr, result, I8_PTR));
            result = resultI8Ptr.ref();
        }
        
        trycatchLeave(callbackFn, env);
        popCallbackFrame(callbackFn, env);
        call(callbackFn, BC_DETACH_THREAD_FROM_CALLBACK, env);
        callbackFn.add(new Ret(result));

        callbackFn.newBasicBlock(bbFailure.getLabel());
        trycatchLeave(callbackFn, env);
        popCallbackFrame(callbackFn, env);
        Value ex = call(callbackFn, BC_EXCEPTION_CLEAR, env);
        call(callbackFn, BC_DETACH_THREAD_FROM_CALLBACK, env);
        call(callbackFn, BC_THROW, env, ex);
        callbackFn.add(new Unreachable());
    }
    
    protected Value ldcClass(Function fn, String name, Value env) {
        FunctionRef ldcClassFn = null;
        if (name.equals(this.className)) {
            ldcClassFn = FunctionBuilder.ldcInternal(this.className).ref();
        } else {
            Trampoline trampoline = new LdcClass(this.className, name);
            trampolines.add(trampoline);
            ldcClassFn = trampoline.getFunctionRef();
        }
        return call(fn, ldcClassFn, env);
    }
}
