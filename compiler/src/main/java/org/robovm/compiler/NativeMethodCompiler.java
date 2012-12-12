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

import static org.robovm.compiler.Functions.*;
import static org.robovm.compiler.Types.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.Ret;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.trampoline.NativeCall;
import org.robovm.compiler.trampoline.Trampoline;

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
        Function outerFn = FunctionBuilder.method(method);
        moduleBuilder.addFunction(outerFn);
        Function innerFn = FunctionBuilder.nativeInner(method);
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
            FunctionRef ldcFn = FunctionBuilder.ldcInternal(sootMethod.getDeclaringClass()).ref();
            Value clazz = call(innerFn, ldcFn, env);
            args.add(1, clazz);
        }
        
        pushNativeFrame(innerFn);
        Value resultInner = call(innerFn, trampoline.getFunctionRef(), args);
        popNativeFrame(innerFn);
        call(innerFn, BC_THROW_IF_EXCEPTION_OCCURRED, env);
        innerFn.add(new Ret(resultInner));
    }
}
