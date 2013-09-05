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
import static org.robovm.compiler.llvm.Type.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.OS;
import org.robovm.compiler.llvm.Alias;
import org.robovm.compiler.llvm.Alloca;
import org.robovm.compiler.llvm.BasicBlockRef;
import org.robovm.compiler.llvm.Bitcast;
import org.robovm.compiler.llvm.BooleanConstant;
import org.robovm.compiler.llvm.ConstantBitcast;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.Inttoptr;
import org.robovm.compiler.llvm.Label;
import org.robovm.compiler.llvm.Linkage;
import org.robovm.compiler.llvm.Load;
import org.robovm.compiler.llvm.ParameterAttribute;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.PrimitiveType;
import org.robovm.compiler.llvm.Ptrtoint;
import org.robovm.compiler.llvm.Ret;
import org.robovm.compiler.llvm.Store;
import org.robovm.compiler.llvm.StructureType;
import org.robovm.compiler.llvm.Type;
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
    protected Config config;
    protected String className;
    protected SootMethod sootMethod;
    protected Set<Trampoline> trampolines;
    protected Set<String> catches;
    
    public AbstractMethodCompiler(Config config) {
        this.config = config;
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
        
        call(syncFn, MONITORENTER, syncFn.getParameterRef(0), monitor);
        BasicBlockRef bbSuccess = syncFn.newBasicBlockRef(new Label("success"));
        BasicBlockRef bbFailure = syncFn.newBasicBlockRef(new Label("failure"));
        trycatchAllEnter(syncFn, bbSuccess, bbFailure);

        syncFn.newBasicBlock(bbSuccess.getLabel());
        Value result = call(syncFn, target, syncFn.getParameterRefs());
        trycatchLeave(syncFn);
        call(syncFn, MONITOREXIT, syncFn.getParameterRef(0), monitor);
        syncFn.add(new Ret(result));

        syncFn.newBasicBlock(bbFailure.getLabel());
        trycatchLeave(syncFn);
        call(syncFn, MONITOREXIT, syncFn.getParameterRef(0), monitor);
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

        SootMethod originalMethod = method;
        boolean passByValue = isPassByValue(originalMethod);
        if (passByValue) {
            // The method returns a struct by value. Determine whether that struct
            // is small enough to be passed in a register or has to be returned
            // using a @StructRet parameter.
            
            Arch arch = config.getArch();
            OS os = config.getOs();
            String triple = config.getTriple();
            int size = getStructType(originalMethod.getReturnType()).getAllocSize(triple);
            if (!os.isReturnedInRegisters(arch, size)) {
                method = createFakeStructRetMethod(method);
            }
        }
        
        Function callbackFn = FunctionBuilder.callback(method);
        if (originalMethod != method) {
            callbackFn.setParameterAttributes(0, ParameterAttribute.sret);
        } else if (passByValue) {
            // Returns a small struct. We need to change the return type to
            // i8/i16/i32/i64.
            int size = ((StructureType) callbackFn.getType().getReturnType()).getAllocSize(config.getTriple());
            Type t = size <= 1 ? I8 : (size <= 2 ? I16 : (size <= 4 ? I32 : I64));
            callbackFn = FunctionBuilder.callback(method, t);
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
                String marshalerClassName = getMarshalerClassName(method, i, true);
                String targetClassName = getInternalName(type);

                if (arg.getType() instanceof PrimitiveType) {
                    if (isEnum(type)) {
                        arg = marshalNativeToEnumObject(callbackFn, marshalerClassName, env, targetClassName, arg);
                    } else {
                        arg = marshalNativeToValueObject(callbackFn, marshalerClassName, env, targetClassName, arg);
                    }
                } else {
                    MarshaledArg marshaledArg = new MarshaledArg();
                    marshaledArg.paramIndex = i;
                    marshaledArgs.add(marshaledArg);
                    if (isPtr(type)) {
                        arg = marshalNativeToPtr(callbackFn, marshalerClassName, marshaledArg, env, getPtrTargetClass(method, i), arg, getPtrWrapCount(method, i));
                    } else {
                        arg = marshalNativeToObject(callbackFn, marshalerClassName, marshaledArg, env, targetClassName, arg, false);
                    }
                }
            } else if (hasPointerAnnotation(method, i)) {
                arg = marshalPointerToLong(callbackFn, arg);
            }
            args.add(arg);
        }
        
        Value result = call(callbackFn, targetFn, args);
        
        // Call Marshaler.updateNative() for each object that was marshaled before
        // the call.
        for (MarshaledArg marshaledArg : marshaledArgs) {
            String marshalerClassName = getMarshalerClassName(method, marshaledArg.paramIndex, false);
            // Call the Marshaler's updateNative() method
            Invokestatic invokestatic = new Invokestatic(
                    getInternalName(method.getDeclaringClass()), marshalerClassName, 
                    "updateNative", "(Ljava/lang/Object;J)V");
            trampolines.add(invokestatic);
            call(callbackFn, invokestatic.getFunctionRef(), 
                    env, marshaledArg.object, marshaledArg.handle);
        }

        
        // Marshal the returned value to a native value before returning
        if (needsMarshaler(method.getReturnType())) {
            String marshalerClassName = getMarshalerClassName(method, false);
            Type nativeType = callbackFn.getType().getReturnType();
            
            if (passByValue) {
                // Small struct.
                result = marshalObjectToNative(callbackFn, marshalerClassName, null, nativeType, env, result, true);
            } else if (nativeType instanceof PrimitiveType) {
                if (isEnum(method.getReturnType())) {
                    result = marshalEnumObjectToNative(callbackFn, marshalerClassName, nativeType, env, result);
                } else {
                    result = marshalValueObjectToNative(callbackFn, marshalerClassName, nativeType, env, result);
                }
            } else {
                result = marshalObjectToNative(callbackFn, marshalerClassName, null, nativeType, env, result);
            }
        } else if (hasPointerAnnotation(method)) {
            result = marshalLongToPointer(callbackFn, result);
        } else if (originalMethod != method) {
            // The original method returns a large struct by value. The callback
            // function takes a struct allocated on the stack by the caller as
            // it's first parameter. We need to copy the struct which the Java
            // method returned to the struct passed in by the caller.
            String marshalerClassName = getMarshalerClassName(originalMethod, false);
            PointerType nativeType = (PointerType) callbackFn.getType().getParameterTypes()[0];
            Value addr = marshalObjectToNative(callbackFn, marshalerClassName, null, nativeType, env, result);
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
    
    public static class MarshaledArg {
        public Value object;
        public Value handle;
        public int paramIndex;
    }
    
    protected Value marshalNativeToPtr(Function fn, String marshalerClassName, MarshaledArg marshaledArg, Value env, 
            SootClass sootPtrTargetClass, Value nativeValue, int ptrWrapCount) {
        
        Value ptrTargetClass = ldcClass(fn, getInternalName(sootPtrTargetClass), env);
        Invokestatic invokeToPtr = new Invokestatic(
                getInternalName(sootMethod.getDeclaringClass()), marshalerClassName, 
                "toPtr", "(Ljava/lang/Class;JI)Lorg/robovm/rt/bro/ptr/Ptr;");
        trampolines.add(invokeToPtr);
        
        Variable handle = fn.newVariable(I64);
        fn.add(new Ptrtoint(handle, nativeValue, I64));
        
        Value object = call(fn, invokeToPtr.getFunctionRef(), 
                env, ptrTargetClass, 
                handle.ref(), new IntegerConstant(ptrWrapCount));
        
        if (marshaledArg != null) {
            marshaledArg.handle = handle.ref();
            marshaledArg.object = object;
        }
        
        return object;
    }
    
    protected Value marshalNativeToObject(Function fn, String marshalerClassName, MarshaledArg marshaledArg, Value env, 
            String valueClassName, Value nativeValue, boolean copy) {
        return marshalNativeToObject(fn, marshalerClassName, marshaledArg, env, valueClassName, nativeValue, copy, false);
    }
    protected Value marshalNativeToObject(Function fn, String marshalerClassName, MarshaledArg marshaledArg, Value env, 
            String valueClassName, Value nativeValue, boolean copy, boolean smallStructRet) {
        
        if (nativeValue.getType() instanceof StructureType || smallStructRet) {
            // Copy the result to the stack
            Variable stackCopy = fn.newVariable(new PointerType(nativeValue.getType()));
            fn.add(new Alloca(stackCopy, nativeValue.getType()));
            fn.add(new Store(nativeValue, stackCopy.ref()));
            nativeValue = stackCopy.ref();
        }
        
        Invokestatic invokestatic = new Invokestatic(
                getInternalName(sootMethod.getDeclaringClass()), marshalerClassName, 
                "toObject", "(Ljava/lang/Class;JZ)Ljava/lang/Object;");
        trampolines.add(invokestatic);
    
        Value valueClass = ldcClass(fn, valueClassName, env);
        
        Variable handle = fn.newVariable(I64);
        fn.add(new Ptrtoint(handle, nativeValue, I64));
        
        Value object = call(fn, invokestatic.getFunctionRef(), 
                env, valueClass, handle.ref(), 
                new IntegerConstant((byte) (copy ? 1 : 0)));
        
        if (marshaledArg != null) {
            marshaledArg.handle = handle.ref();
            marshaledArg.object = object;
        }
        
        return object;
    }
    
    protected Value marshalNativeToValueObject(Function fn, String marshalerClassName, Value env, 
            String valueClassName, Value nativeValue) {
        
        Invokestatic invokeToObject = new Invokestatic(
                getInternalName(sootMethod.getDeclaringClass()), marshalerClassName, 
                "toObject", String.format("(Ljava/lang/Class;%s)Ljava/lang/Object;", 
                        getDescriptor(nativeValue.getType())));
        trampolines.add(invokeToObject);
    
        Value valueClass = ldcClass(fn, valueClassName, env);
        
        return call(fn, invokeToObject.getFunctionRef(), env, valueClass, nativeValue);
    }
    
    protected Value marshalNativeToEnumObject(Function fn, String marshalerClassName, Value env, 
            String enumClassName, Value nativeValue) {
        
        Invokestatic invokeValues = new Invokestatic(
                getInternalName(sootMethod.getDeclaringClass()), enumClassName, 
                "values", String.format("()[L%s;", enumClassName));
        trampolines.add(invokeValues);
        Value values = call(fn, invokeValues.getFunctionRef(), env);
        
        Invokestatic invokeToObject = new Invokestatic(
                getInternalName(sootMethod.getDeclaringClass()), marshalerClassName, 
                "toObject", String.format("([Ljava/lang/Enum;%s)Ljava/lang/Enum;", 
                        getDescriptor(nativeValue.getType())));
        trampolines.add(invokeToObject);
        
        return call(fn, invokeToObject.getFunctionRef(), env, values, nativeValue);
    }
    
    protected Value marshalPointerToLong(Function fn, Value pointer) {
        Variable result = fn.newVariable(I64);
        fn.add(new Ptrtoint(result, pointer, I64));
        return result.ref();
    }
    
    protected Value marshalObjectToNative(Function fn, String marshalerClassName, MarshaledArg marshaledArg, 
            Type nativeType, Value env, Value object) {
        return marshalObjectToNative(fn, marshalerClassName, marshaledArg, nativeType, env, object, false);
    }
    
    protected Value marshalObjectToNative(Function fn, String marshalerClassName, MarshaledArg marshaledArg, 
            Type nativeType, Value env, Value object, boolean smallStructRet) {
        
        Invokestatic invokestatic = new Invokestatic(
                getInternalName(sootMethod.getDeclaringClass()), marshalerClassName, 
                "toNative", "(Ljava/lang/Object;)J");
        trampolines.add(invokestatic);
        Value handle = call(fn, invokestatic.getFunctionRef(), 
                env, object);
    
        Variable nativeValue = fn.newVariable(nativeType);
        if (nativeType instanceof StructureType || smallStructRet) {
            Variable tmp = fn.newVariable(new PointerType(nativeType));
            fn.add(new Inttoptr(tmp, handle, tmp.getType()));
            fn.add(new Load(nativeValue, tmp.ref()));
        } else {
            fn.add(new Inttoptr(nativeValue, handle, nativeType));
        }
        
        if (marshaledArg != null) {
            marshaledArg.handle = handle;
            marshaledArg.object = object;
        }
        
        return nativeValue.ref();
    }
    
    protected Value marshalValueObjectToNative(Function fn, String marshalerClassName, Type nativeType, Value env, Value object) {
        Invokestatic invokestatic = new Invokestatic(
                getInternalName(sootMethod.getDeclaringClass()), marshalerClassName, 
                "toNative", "(Ljava/lang/Object;)" + getDescriptor(nativeType));
        trampolines.add(invokestatic);
        return call(fn, invokestatic.getFunctionRef(), env, object);
    }
    
    protected Value marshalEnumObjectToNative(Function fn, String marshalerClassName, Type nativeType, Value env, Value object) {
        Invokestatic invokestatic = new Invokestatic(
                getInternalName(sootMethod.getDeclaringClass()), marshalerClassName, 
                "toNative", "(Ljava/lang/Enum;)" + getDescriptor(nativeType));
        trampolines.add(invokestatic);
        return call(fn, invokestatic.getFunctionRef(), env, object);
    }
    
    protected Value marshalLongToPointer(Function fn, Value handle) {
        Variable result = fn.newVariable(I8_PTR);
        fn.add(new Inttoptr(result, handle, I8_PTR));
        return result.ref();
    }
}
