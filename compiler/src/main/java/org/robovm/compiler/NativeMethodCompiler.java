/*
 * Copyright (C) 2012 RoboVM AB
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
import static org.robovm.compiler.Mangler.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Linkage.*;
import static org.robovm.compiler.llvm.Type.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.robovm.compiler.config.Config;
import org.robovm.compiler.llvm.Bitcast;
import org.robovm.compiler.llvm.Br;
import org.robovm.compiler.llvm.FloatingPointConstant;
import org.robovm.compiler.llvm.FloatingPointType;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.Global;
import org.robovm.compiler.llvm.Icmp;
import org.robovm.compiler.llvm.Icmp.Condition;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.IntegerType;
import org.robovm.compiler.llvm.Label;
import org.robovm.compiler.llvm.NullConstant;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.Ret;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;

import soot.SootMethod;

/**
 *
 */
public class NativeMethodCompiler extends AbstractMethodCompiler {

    public NativeMethodCompiler(Config config) {
        super(config);
    }

    protected Function doCompile(ModuleBuilder moduleBuilder, SootMethod method) {
        Function fn = createMethodFunction(method);
        moduleBuilder.addFunction(fn);

        Value env = fn.getParameterRef(0);

        ArrayList<Value> args = new ArrayList<Value>(Arrays.asList(fn.getParameterRefs()));
        if (method.isStatic()) {
            // Add the current class as second parameter
            FunctionRef ldcFn = FunctionBuilder.ldcInternal(sootMethod.getDeclaringClass()).ref();
            Value clazz = call(fn, ldcFn, env);
            args.add(1, clazz);
        }

        pushNativeFrame(fn);
        FunctionRef targetFn = createNative(moduleBuilder, method);
        Value result = call(fn, targetFn, args);
        popNativeFrame(fn);
        call(fn, BC_THROW_IF_EXCEPTION_OCCURRED, env);
        fn.add(new Ret(result));

        return fn;
    }

    private boolean isLongNativeFunctionNameRequired(SootMethod method) {
        int nativeCount = 0;
        for (SootMethod m : this.sootClass.getMethods()) {
            if (m.isNative() && m.getName().equals(method.getName())) {
                nativeCount++;
            }
        }
        return nativeCount > 1;
    }

    private FunctionRef createNative(ModuleBuilder mb, SootMethod method) {
        String targetInternalName = getInternalName(method.getDeclaringClass());
        String methodName = method.getName();
        String methodDesc = getDescriptor(method);
        FunctionType nativeFunctionType = Types.getNativeFunctionType(methodDesc, method.isStatic());

        String shortName = mangleNativeMethod(targetInternalName, methodName);
        String longName = mangleNativeMethod(targetInternalName, methodName, methodDesc);

        /*
         * To support statically linked native method implementation we create
         * weak stub functions with the same names as the expected JNI functions
         * (long and short names). These will be discarded by the linker if
         * proper functions are available at link time.
         * 
         * The weak stub with the short JNI name just calls the weak stub with
         * the long name.
         * 
         * The weak stub with the long name calls _bcResolveNative() which will
         * try to resolve the native method against dynamically loaded JNI libs.
         * If _bcResolveNative() finds a matching symbol in a dynamic lib or an
         * implementation has previously been registered using JNI
         * RegisterNatives() that will be stored in the native method pointer
         * passed to it and returned. The stub will call the implementation
         * returned by _bcResolveNative(). If no implementation can be found
         * _bcResolveNative() throws an UnsatisfiedLinkError and doesn't return
         * to the stub.
         * 
         * The limitation of this approach is that RegisterNatives() only works
         * for dynamically linked native methods and can only be used prior to
         * the first call of such a method. Native methods can never be rewired
         * or unregistered.
         */

        /*
         * The function with the long JNI name. This is the one that calls
         * _bcResolveNative() and then calls the implementation.
         */
        Function fn = new FunctionBuilder(longName, nativeFunctionType).linkage(weak).build();

        Global g = new Global(Symbols.nativeMethodPtrSymbol(targetInternalName, methodName, methodDesc),
                new NullConstant(I8_PTR));
        mb.addGlobal(g);
        FunctionRef ldcFn = FunctionBuilder.ldcInternal(targetInternalName).ref();
        Value theClass = call(fn, ldcFn, fn.getParameterRef(0));
        Value implI8Ptr = call(fn, BC_RESOLVE_NATIVE, fn.getParameterRef(0),
                theClass,
                mb.getString(methodName),
                mb.getString(methodDesc),
                mb.getString(shortName),
                mb.getString(longName),
                g.ref());
        Variable nullTest = fn.newVariable(I1);
        fn.add(new Icmp(nullTest, Condition.ne, implI8Ptr, new NullConstant(I8_PTR)));
        Label trueLabel = new Label();
        Label falseLabel = new Label();
        fn.add(new Br(nullTest.ref(), fn.newBasicBlockRef(trueLabel), fn.newBasicBlockRef(falseLabel)));
        fn.newBasicBlock(falseLabel);
        if (fn.getType().getReturnType() instanceof IntegerType) {
            fn.add(new Ret(new IntegerConstant(0, (IntegerType) fn.getType().getReturnType())));
        } else if (fn.getType().getReturnType() instanceof FloatingPointType) {
            fn.add(new Ret(new FloatingPointConstant(0.0, (FloatingPointType) fn.getType().getReturnType())));
        } else if (fn.getType().getReturnType() instanceof PointerType) {
            fn.add(new Ret(new NullConstant((PointerType) fn.getType().getReturnType())));
        } else {
            fn.add(new Ret());
        }
        fn.newBasicBlock(trueLabel);
        Variable impl = fn.newVariable(nativeFunctionType);
        fn.add(new Bitcast(impl, implI8Ptr, impl.getType()));
        Value result = call(fn, impl.ref(), fn.getParameterRefs());
        fn.add(new Ret(result));
        mb.addFunction(fn);

        FunctionRef targetFn = fn.ref();

        if (!isLongNativeFunctionNameRequired(method)) {
            /*
             * Generate a function with the short JNI name. This just calls the
             * function with the long name.
             */
            Function fnShort = new FunctionBuilder(shortName, nativeFunctionType).linkage(weak).build();
            Value resultInner = call(fnShort, fn.ref(), fnShort.getParameterRefs());
            fnShort.add(new Ret(resultInner));
            mb.addFunction(fnShort);
            targetFn = fnShort.ref();
        }

        return targetFn;
    }
}
