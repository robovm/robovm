/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler;

import static org.nullvm.compiler.Functions.*;
import static org.nullvm.compiler.Mangler.*;
import static org.nullvm.compiler.Types.*;
import static org.nullvm.compiler.llvm.FunctionAttribute.*;
import static org.nullvm.compiler.llvm.Linkage.*;
import static org.nullvm.compiler.llvm.Type.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.nullvm.compiler.llvm.BasicBlockRef;
import org.nullvm.compiler.llvm.ConstantBitcast;
import org.nullvm.compiler.llvm.Function;
import org.nullvm.compiler.llvm.FunctionAttribute;
import org.nullvm.compiler.llvm.FunctionRef;
import org.nullvm.compiler.llvm.FunctionType;
import org.nullvm.compiler.llvm.Inttoptr;
import org.nullvm.compiler.llvm.Label;
import org.nullvm.compiler.llvm.Landingpad;
import org.nullvm.compiler.llvm.Landingpad.Catch;
import org.nullvm.compiler.llvm.NullConstant;
import org.nullvm.compiler.llvm.Ptrtoint;
import org.nullvm.compiler.llvm.Ret;
import org.nullvm.compiler.llvm.StructureType;
import org.nullvm.compiler.llvm.Unreachable;
import org.nullvm.compiler.llvm.Value;
import org.nullvm.compiler.llvm.Variable;
import org.nullvm.compiler.llvm.VariableRef;
import org.nullvm.compiler.trampoline.Trampoline;

import soot.PrimType;
import soot.SootMethod;
import soot.VoidType;

/**
 *
 * @version $Id$
 */
public abstract class AbstractMethodCompiler {
    protected String className;
    protected SootMethod sootMethod;
    protected Set<Trampoline> trampolines;
    
    public AbstractMethodCompiler(Config config) {
    }
    
    public Set<Trampoline> getTrampolines() {
        return trampolines;
    }
    
    public void compile(ModuleBuilder moduleBuilder, SootMethod method) {
        className = getInternalName(method.getDeclaringClass());
        sootMethod = method;
        trampolines = new HashSet<Trampoline>();
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
        Function syncFn = createFunction(targetName + "_synchronized", method, 
                external, noinline);
        moduleBuilder.addFunction(syncFn);
        FunctionType functionType = syncFn.getType();
        FunctionRef target = new FunctionRef(targetName, functionType);
        
        Value monitor = null;
        if (method.isStatic()) {
            FunctionRef fn = new FunctionRef(mangleClass(sootMethod.getDeclaringClass()) + "_ldc", 
                    new FunctionType(OBJECT_PTR, ENV_PTR));
            monitor = call(syncFn, fn, syncFn.getParameterRef(0));
        } else {
            monitor = new VariableRef("this", OBJECT_PTR);
        }
        
        call(syncFn, NVM_BC_MONITOR_ENTER, syncFn.getParameterRef(0), monitor);
        BasicBlockRef bbSuccess = syncFn.newBasicBlockRef(new Label("success"));
        BasicBlockRef bbFailure = syncFn.newBasicBlockRef(new Label("failure"));
        Value result = invoke(syncFn, target, bbSuccess, bbFailure, syncFn.getParameterRefs());
        
        syncFn.newBasicBlock(new Label("success"));
        call(syncFn, NVM_BC_MONITOR_EXIT, syncFn.getParameterRef(0), monitor);
        syncFn.add(new Ret(result));

        syncFn.newBasicBlock(new Label("failure"));
        Variable lpResult = syncFn.newVariable(new StructureType(I8_PTR, I32));
        syncFn.add(new Landingpad(lpResult, new ConstantBitcast(NVM_BC_PERSONALITY, I8_PTR), new Catch(new NullConstant(I8_PTR))));
        call(syncFn, NVM_BC_MONITOR_EXIT, syncFn.getParameterRef(0), monitor);
        call(syncFn, NVM_BC_RETHROW, syncFn.getParameterRef(0), lpResult.ref());
        syncFn.add(new Unreachable());
    }
    
    private void validateCallbackMethod(SootMethod method) {
        if (!method.isStatic()) {
            throw new IllegalArgumentException("@Callback annotated method must be static: " + method);
        }
        soot.Type sootRetType = method.getReturnType();
        if (!sootRetType.equals(VoidType.v()) && !(sootRetType instanceof PrimType)) {
            throw new IllegalArgumentException("@Callback annotated method must return void or primitive type");
        }
        for (int i = 0; i < method.getParameterCount(); i++) {
            if (!(method.getParameterType(i) instanceof PrimType)) {
                throw new IllegalArgumentException("@Callback annotated method must take only primitive type arguments");
            }
        }
    }
    
    private void compileCallback(ModuleBuilder moduleBuilder, SootMethod method) {
        validateCallbackMethod(method);

        Function callbackFn = new Function(external,
                new FunctionAttribute[] { noinline, optsize },
                mangleMethod(method) + "_callback",
                getCallbackFunctionType(method));
        moduleBuilder.addFunction(callbackFn);

        // Increase the attach count for the current thread (attaches the thread
        // if not attached)
        Value env = call(callbackFn, NVM_BC_ATTACH_THREAD_FROM_CALLBACK);

        pushCallbackFrame(callbackFn);

        ArrayList<Value> args = new ArrayList<Value>();
        args.add(env);
        for (VariableRef ref : callbackFn.getParameterRefs()) {
            if (ref.getType() == I8_PTR) {
                Variable tmp = callbackFn.newVariable(I64);
                callbackFn.add(new Ptrtoint(tmp, ref, I64));
                ref = tmp.ref();
            }
            args.add(ref);
        }

        String targetName = mangleMethod(method);
        if (method.isSynchronized()) {
            targetName += "_synchronized";
        }
        FunctionRef targetFn = new FunctionRef(targetName, getFunctionType(method));
        
        BasicBlockRef bbSuccess = callbackFn.newBasicBlockRef(new Label("success"));
        BasicBlockRef bbFailure = callbackFn.newBasicBlockRef(new Label("failure"));
        Value result = invoke(callbackFn, targetFn, bbSuccess, bbFailure, args);
        
        callbackFn.newBasicBlock(new Label("success"));
        if (callbackFn.getType().getReturnType() == I8_PTR) {
            Variable resultI8Ptr = callbackFn.newVariable(I8_PTR);
            callbackFn.add(new Inttoptr(resultI8Ptr, result, I8_PTR));
            result = resultI8Ptr.ref();
        }
        popCallbackFrame(callbackFn);
        call(callbackFn, NVM_BC_DETACH_THREAD_FROM_CALLBACK, env);
        callbackFn.add(new Ret(result));

        callbackFn.newBasicBlock(new Label("failure"));
        Variable lpResult = callbackFn.newVariable(new StructureType(I8_PTR, I32));
        callbackFn.add(new Landingpad(lpResult, new ConstantBitcast(NVM_BC_PERSONALITY, I8_PTR), new Catch(new NullConstant(I8_PTR))));
        popCallbackFrame(callbackFn);
        call(callbackFn, NVM_BC_DETACH_THREAD_FROM_CALLBACK, env);
        call(callbackFn, NVM_BC_RETHROW, env, lpResult.ref());
        callbackFn.add(new Unreachable());
    }
}
