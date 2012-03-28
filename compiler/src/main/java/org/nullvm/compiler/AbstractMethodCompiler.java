/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler;

import static org.nullvm.compiler.Functions.*;
import static org.nullvm.compiler.Mangler.*;
import static org.nullvm.compiler.Types.*;
import static org.nullvm.compiler.llvm.Type.*;

import java.util.HashSet;
import java.util.Set;

import org.nullvm.compiler.llvm.BasicBlockRef;
import org.nullvm.compiler.llvm.Function;
import org.nullvm.compiler.llvm.FunctionAttribute;
import org.nullvm.compiler.llvm.FunctionRef;
import org.nullvm.compiler.llvm.FunctionType;
import org.nullvm.compiler.llvm.Label;
import org.nullvm.compiler.llvm.Landingpad;
import org.nullvm.compiler.llvm.Landingpad.Catch;
import org.nullvm.compiler.llvm.Linkage;
import org.nullvm.compiler.llvm.NullConstant;
import org.nullvm.compiler.llvm.Ret;
import org.nullvm.compiler.llvm.StructureType;
import org.nullvm.compiler.llvm.Type;
import org.nullvm.compiler.llvm.Unreachable;
import org.nullvm.compiler.llvm.Value;
import org.nullvm.compiler.llvm.Variable;
import org.nullvm.compiler.llvm.VariableRef;
import org.nullvm.compiler.trampoline.Trampoline;

import soot.SootMethod;

/**
 *
 * @version $Id$
 */
public abstract class AbstractMethodCompiler {

    protected static final VariableRef ENV = new VariableRef("env", ENV_PTR);
    
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
    }
        
    protected abstract void doCompile(ModuleBuilder moduleBuilder, SootMethod method);

    private void compileSynchronizedWrapper(ModuleBuilder moduleBuilder, SootMethod method) {
        String targetName = mangleMethod(method);
        Function function = createFunction(targetName + "_synchronized", method, 
                Linkage.external, FunctionAttribute.noinline);
        moduleBuilder.addFunction(function);
        FunctionType functionType = function.getType();
        FunctionRef target = new FunctionRef(targetName, functionType);
        
        Value monitor = null;
        if (method.isStatic()) {
            FunctionRef fn = new FunctionRef(mangleClass(sootMethod.getDeclaringClass()) + "_ldc", 
                    new FunctionType(OBJECT_PTR, ENV_PTR));
            monitor = call(function, fn, ENV);
        } else {
            monitor = new VariableRef("this", OBJECT_PTR);
        }
        
        String[] parameterNames = function.getParameterNames();
        Type[] parameterTypes = function.getType().getParameterTypes();
        Value[] args = new Value[parameterNames.length];
        for (int i = 0; i < args.length; i++) {
            args[i] = new VariableRef(parameterNames[i], parameterTypes[i]);
        }
        
        call(function, NVM_BC_MONITOR_ENTER, ENV, monitor);
        BasicBlockRef bbSuccess = function.newBasicBlockRef(new Label("success"));
        BasicBlockRef bbFailure = function.newBasicBlockRef(new Label("failure"));
        Value result = invoke(function, target, bbSuccess, bbFailure, args);
        
        function.newBasicBlock(new Label("success"));
        call(function, NVM_BC_MONITOR_EXIT, ENV, monitor);
        function.add(new Ret(result));

        function.newBasicBlock(new Label("failure"));
        Variable lpResult = function.newVariable(new StructureType(I8_PTR, I32));
        function.add(new Landingpad(lpResult, NVM_BC_PERSONALITY, new Catch(new NullConstant(I8_PTR))));
        call(function, NVM_BC_MONITOR_EXIT, ENV, monitor);
        call(function, NVM_BC_RETHROW, ENV);
        function.add(new Unreachable());
    }
}
