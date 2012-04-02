/**
 * 
 */
package org.nullvm.compiler;

import static org.nullvm.compiler.Functions.*;
import static org.nullvm.compiler.Mangler.*;
import static org.nullvm.compiler.Types.*;
import static org.nullvm.compiler.llvm.FunctionAttribute.*;
import static org.nullvm.compiler.llvm.Linkage.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.nullvm.compiler.llvm.Function;
import org.nullvm.compiler.llvm.FunctionRef;
import org.nullvm.compiler.llvm.FunctionType;
import org.nullvm.compiler.llvm.IntegerConstant;
import org.nullvm.compiler.llvm.Ret;
import org.nullvm.compiler.llvm.Value;
import org.nullvm.compiler.trampoline.NativeCall;
import org.nullvm.compiler.trampoline.Trampoline;

import soot.SootMethod;

/**
 * @author niklas
 *
 */
public class NativeMethodCompiler extends AbstractMethodCompiler {

    public NativeMethodCompiler(Config config) {
        super(config);
    }

    protected void doCompile(ModuleBuilder moduleBuilder, SootMethod method) {
        Function outerFn = createFunction(method, external, noinline);
        moduleBuilder.addFunction(outerFn);
        Function innerFn = createFunction(mangleMethod(method) + "_inner", 
                method, internal, noinline);
        moduleBuilder.addFunction(innerFn);

        Value env = innerFn.getParameterRef(0);
        
        String targetClassName = getInternalName(method.getDeclaringClass());
        String methodName = method.getName();
        String methodDesc = getDescriptor(method);
        Trampoline trampoline = new NativeCall(this.className, targetClassName, methodName, methodDesc, method.isStatic());
        trampolines.add(trampoline);
        
        Value resultOuter = call(outerFn, innerFn.ref(), outerFn.getParameterRefs());
        outerFn.add(new Ret(resultOuter));

        ArrayList<Value> args = new ArrayList<Value>(Arrays.asList(outerFn.getParameterRefs()));
        if (method.isStatic()) {
            // Add the current class as second parameter
            FunctionRef ldcFn = new FunctionRef(mangleClass(sootMethod.getDeclaringClass()) + "_ldc", 
                    new FunctionType(OBJECT_PTR, ENV_PTR));
            Value clazz = call(innerFn, ldcFn, env);
            args.add(1, clazz);
        }
        
        Value frameAddress = call(innerFn, LLVM_FRAMEADDRESS, new IntegerConstant(0));
        call(innerFn, NVM_BC_PUSH_NATIVE_FRAME, env, frameAddress);
        Value resultInner = call(innerFn, trampoline.getFunctionRef(), args);
        call(innerFn, NVM_BC_POP_NATIVE_FRAME, env);
        call(innerFn, NVM_BC_THROW_IF_EXCEPTION_OCCURRED, env);
        innerFn.add(new Ret(resultInner));
    }
}
