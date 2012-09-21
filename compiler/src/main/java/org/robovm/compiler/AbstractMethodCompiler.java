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

import static org.robovm.compiler.Bro.*;
import static org.robovm.compiler.Functions.*;
import static org.robovm.compiler.Mangler.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Type.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.robovm.compiler.llvm.BasicBlockRef;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.Inttoptr;
import org.robovm.compiler.llvm.Label;
import org.robovm.compiler.llvm.Ptrtoint;
import org.robovm.compiler.llvm.Ret;
import org.robovm.compiler.llvm.Unreachable;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;
import org.robovm.compiler.llvm.VariableRef;
import org.robovm.compiler.trampoline.Trampoline;

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

        Function callbackFn = FunctionBuilder.callback(method);
        moduleBuilder.addFunction(callbackFn);

        // Increase the attach count for the current thread (attaches the thread
        // if not attached)
        Value env = call(callbackFn, BC_ATTACH_THREAD_FROM_CALLBACK);

        pushCallbackFrame(callbackFn, env);

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
        trycatchAllEnter(callbackFn, env, bbSuccess, bbFailure);
        
        callbackFn.newBasicBlock(bbSuccess.getLabel());
        Value result = call(callbackFn, targetFn, args);
        if (callbackFn.getType().getReturnType() == I8_PTR) {
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
        call(callbackFn, BC_DETACH_THREAD_FROM_CALLBACK, env);
        call(callbackFn, BC_THROW_IF_EXCEPTION_OCCURRED, env);
        callbackFn.add(new Unreachable());
    }
}
