/*
 * Copyright (C) 2012 Trillian Mobile AB
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
import static org.robovm.compiler.llvm.FunctionAttribute.*;
import static org.robovm.compiler.llvm.Linkage.*;
import static org.robovm.compiler.llvm.Type.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.OS;
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
import org.robovm.compiler.llvm.Unreachable;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;

import soot.SootMethod;

/**
 * @author niklas
 *
 */
public class NativeMethodCompiler extends AbstractMethodCompiler {
    public static final String UNSATISFIED_LINK_ERROR = "%s.%s%s";

    public NativeMethodCompiler(Config config) {
        super(config);
    }

    protected Function doCompile(ModuleBuilder moduleBuilder, SootMethod method) {
        Function fn = FunctionBuilder.method(method);
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
        
        Clazz target = config.getClazzes().load(targetInternalName);
        String shortName = mangleNativeMethod(targetInternalName, methodName);
        String longName = mangleNativeMethod(targetInternalName, methodName, methodDesc);
        if (target.isInBootClasspath() || !config.isUseDynamicJni() || config.getOs() == OS.ios) {
            /*
             * Static JNI. Create weak stub functions with the same names as the
             * expected JNI functions (long and short names). These will be
             * discarded by the linker if proper functions are available at link
             * time. The weak stubs just throw UnsatisfiedLinkError.
             */

            // The function with the long JNI name. This is the one that throws
            // UnsatisfiedLinkError.
            Function fnLong = new FunctionBuilder(longName, nativeFunctionType).linkage(weak).build();
            // The NativeCall caller pushed a GatewayFrame and will only pop it
            // if the native method exists. So we need to pop it here.
            popNativeFrame(fnLong);
            call(fnLong, BC_THROW_UNSATISIFED_LINK_ERROR, fnLong.getParameterRef(0), 
                    mb.getString(String.format(UNSATISFIED_LINK_ERROR, targetInternalName,
                            methodName, methodDesc)));
            fnLong.add(new Unreachable());
            mb.addFunction(fnLong);
            FunctionRef targetFn = fnLong.ref();

            if (!isLongNativeFunctionNameRequired(method)) {
                // Generate a function with the short JNI name. This just calls
                // the function with the long name.
                Function fnShort = new FunctionBuilder(shortName, nativeFunctionType).linkage(weak).build();
                Value resultInner = call(fnShort, fnLong.ref(), fnShort.getParameterRefs());
                fnShort.add(new Ret(resultInner));
                mb.addFunction(fnShort);
                targetFn = fnShort.ref();
            }

            return targetFn;
        } else {
            /*
             * Dynamic JNI. Generate a function which calls _bcResolveNative()
             * on the first invocation. _bcResolveNative() will resolve the JNI
             * function or throw an exception. The JNI function pointer is
             * cached for subsequent invocations.
             */
            Global g = new Global(Symbols.nativeMethodPtrSymbol(targetInternalName, methodName, methodDesc), 
                    new NullConstant(I8_PTR));
            mb.addGlobal(g);
            String fnName = Symbols.nativeCallMethodSymbol(targetInternalName, methodName, methodDesc);
            Function fn = new FunctionBuilder(fnName, nativeFunctionType)
                .linkage(external)
                .attribs(alwaysinline, optsize).build();
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
            return fn.ref();
        }
    }
}
