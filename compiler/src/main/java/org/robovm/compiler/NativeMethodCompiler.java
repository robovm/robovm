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

import org.robovm.compiler.config.Config;
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
        Function fn = FunctionBuilder.method(method);
        moduleBuilder.addFunction(fn);

        Value env = fn.getParameterRef(0);
        
        String targetClassName = getInternalName(method.getDeclaringClass());
        String methodName = method.getName();
        String methodDesc = getDescriptor(method);
        Trampoline trampoline = new NativeCall(this.className, targetClassName, methodName, methodDesc, method.isStatic());
        trampolines.add(trampoline);
        
        ArrayList<Value> args = new ArrayList<Value>(Arrays.asList(fn.getParameterRefs()));
        if (method.isStatic()) {
            // Add the current class as second parameter
            FunctionRef ldcFn = FunctionBuilder.ldcInternal(sootMethod.getDeclaringClass()).ref();
            Value clazz = call(fn, ldcFn, env);
            args.add(1, clazz);
        }
        
        pushNativeFrame(fn);
        Value result = call(fn, trampoline.getFunctionRef(), args);
        popNativeFrame(fn);
        call(fn, BC_THROW_IF_EXCEPTION_OCCURRED, env);
        fn.add(new Ret(result));
    }
}
