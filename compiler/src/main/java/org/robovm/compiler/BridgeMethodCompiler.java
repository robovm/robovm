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

import static org.robovm.compiler.Bro.*;
import static org.robovm.compiler.Functions.*;
import static org.robovm.compiler.Mangler.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Linkage.*;
import static org.robovm.compiler.llvm.ParameterAttribute.*;
import static org.robovm.compiler.llvm.Type.*;
import static org.robovm.compiler.Annotations.*;

import java.util.ArrayList;
import java.util.List;

import org.robovm.compiler.Bro.MarshalerFlags;
import org.robovm.compiler.MarshalerLookup.MarshalSite;
import org.robovm.compiler.MarshalerLookup.MarshalerMethod;
import org.robovm.compiler.MarshalerLookup.PointerMarshalerMethod;
import org.robovm.compiler.config.Arch;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.config.OS;
import org.robovm.compiler.llvm.Argument;
import org.robovm.compiler.llvm.BasicBlockRef;
import org.robovm.compiler.llvm.Bitcast;
import org.robovm.compiler.llvm.Br;
import org.robovm.compiler.llvm.ConstantBitcast;
import org.robovm.compiler.llvm.DataLayout;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.Global;
import org.robovm.compiler.llvm.Icmp;
import org.robovm.compiler.llvm.Icmp.Condition;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.Inttoptr;
import org.robovm.compiler.llvm.Label;
import org.robovm.compiler.llvm.Load;
import org.robovm.compiler.llvm.NullConstant;
import org.robovm.compiler.llvm.ParameterAttribute;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.PrimitiveType;
import org.robovm.compiler.llvm.Ret;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.llvm.Unreachable;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;
import org.robovm.compiler.llvm.VariableRef;
import org.robovm.compiler.trampoline.Invokestatic;
import org.robovm.compiler.trampoline.LdcClass;

import soot.LongType;
import soot.SootMethod;
import soot.tagkit.AnnotationTag;

/**
 * @author niklas
 *
 */
public class BridgeMethodCompiler extends BroMethodCompiler {

    public BridgeMethodCompiler(Config config) {
        super(config);
    }

    private void validateBridgeMethod(SootMethod method) {
        if (!method.isNative()) {
            throw new IllegalArgumentException("@Bridge annotated method " 
                    + method + " must be native");
        }
        AnnotationTag bridgeAnnotation = getAnnotation(method, BRIDGE);
        if (readBooleanElem(bridgeAnnotation, "dynamic", false)) {
            if (!method.isStatic() || method.getParameterCount() == 0
                    || method.getParameterType(0) != LongType.v()
                    || !hasParameterAnnotation(method, 0, POINTER)) {
                throw new IllegalArgumentException("Dynamic @Bridge annotated method " 
                        + method + " must be static and take a @Pointer long as first parameter");
            }
        }
    }

    protected void doCompile(ModuleBuilder moduleBuilder, SootMethod method) {
        validateBridgeMethod(method);

        AnnotationTag bridgeAnnotation = getAnnotation(method, BRIDGE);
        boolean dynamic = readBooleanElem(bridgeAnnotation, "dynamic", false);
        
        Function fn = FunctionBuilder.method(method);
        moduleBuilder.addFunction(fn);
        
        Type[] parameterTypes = fn.getType().getParameterTypes();
        String[] parameterNames = fn.getParameterNames();
        ArrayList<Argument> args = new ArrayList<Argument>();
        for (int i = 0; i < parameterTypes.length; i++) {
            args.add(new Argument(new VariableRef(parameterNames[i], parameterTypes[i])));
        }
        
        SootMethod originalMethod = method;
        Value structObj = null;
        boolean passByValue = isPassByValue(originalMethod);
        DataLayout dataLayout = config.getDataLayout();
        if (passByValue) {
            // The method returns a struct by value. Determine whether that struct
            // is small enough to be passed in a register or has to be returned
            // using a @StructRet parameter.
            
            Arch arch = config.getArch();
            OS os = config.getOs();
            int size = dataLayout.getAllocSize(getStructType(originalMethod.getReturnType()));
            if (!os.isReturnedInRegisters(arch, size)) {
                method = createFakeStructRetMethod(method);
                
                // Call Struct.allocate(<returnType>) to allocate a struct instance
                // which will be used as return value.
                VariableRef env = fn.getParameterRef(0);
                LdcClass ldcClass = new LdcClass(getInternalName(method.getDeclaringClass()), 
                        getInternalName(originalMethod.getReturnType()));
                trampolines.add(ldcClass);
                Value cls = call(fn, ldcClass.getFunctionRef(), env);
                Invokestatic invokestatic = new Invokestatic(
                        getInternalName(method.getDeclaringClass()), "org/robovm/rt/bro/Struct", 
                        "allocate", "(Ljava/lang/Class;)Lorg/robovm/rt/bro/Struct;");
                trampolines.add(invokestatic);
                structObj = call(fn, invokestatic.getFunctionRef(), env, cls);
    
                // Insert the allocated struct as arg 1 (first arg is always the Env*)
                args.add(1, new Argument(structObj));
            }
        }
        
        FunctionType targetFnType = getBridgeFunctionType(method, dynamic);
        if (method == originalMethod && passByValue) {
            // Returns a small struct. We need to change the return type to
            // i8/i16/i32/i64.
            int size = dataLayout.getAllocSize(targetFnType.getReturnType());
            Type t = size <= 1 ? I8 : (size <= 2 ? I16 : (size <= 4 ? I32 : I64));
            targetFnType = new FunctionType(t, targetFnType.isVarargs(), targetFnType.getParameterTypes());
        }

        VariableRef env = fn.getParameterRef(0);
        
        // Load the address of the resolved @Bridge method
        Variable targetFn = fn.newVariable(targetFnType);
        if (!dynamic) {
            Global targetFnPtr = new Global(getTargetFnPtrName(originalMethod), 
                    _private, new NullConstant(I8_PTR));
            moduleBuilder.addGlobal(targetFnPtr);
            fn.add(new Load(targetFn, new ConstantBitcast(targetFnPtr.ref(), new PointerType(targetFnType))));
    
            Label nullLabel = new Label();
            Label notNullLabel = new Label();
            Variable nullCheck = fn.newVariable(I1);
            fn.add(new Icmp(nullCheck, Condition.eq, targetFn.ref(), new NullConstant(targetFnType)));
            fn.add(new Br(nullCheck.ref(), fn.newBasicBlockRef(nullLabel), fn.newBasicBlockRef(notNullLabel)));
            fn.newBasicBlock(nullLabel);
            call(fn, BC_THROW_UNSATISIFED_LINK_ERROR, env,
                    moduleBuilder.getString(String.format("@Bridge method %s.%s%s not bound", className,
                            originalMethod.getName(), getDescriptor(originalMethod))));
            fn.add(new Unreachable());
            fn.newBasicBlock(notNullLabel);
        } else {
            // Dynamic @Bridge methods pass the target function pointer as a
            // long in the first parameter.
            fn.add(new Inttoptr(targetFn, fn.getParameterRef(1), targetFn.getType()));
            args.remove(1);
        }
        
        // Marshal args
        
        // Remove Env* from args
        args.remove(0);

        // Save the Object->handle mapping for each marshaled object. We need it
        // after the native call to call updateObject() on the marshaler for 
        // each value. Since the LLVM variables that store these values are used 
        // after the native call we get the nice side effect that neither the
        // Java objects nor the handles can be garbage collected while we're in
        // native code.
        List<MarshaledArg> marshaledArgs = new ArrayList<MarshaledArg>();
        
        Type[] targetParameterTypes = targetFnType.getParameterTypes();
        
        int receiverIdx = -1;
        if (!method.isStatic()) {
            MarshalerMethod marshalerMethod = config.getMarshalerLookup().findMarshalerMethod(new MarshalSite(method, MarshalSite.RECEIVER));
            MarshaledArg marshaledArg = new MarshaledArg();
            marshaledArg.paramIndex = MarshalSite.RECEIVER;
            marshaledArgs.add(marshaledArg);
            // The receiver is either at index 0 or 1 in args depending on whether this method returns
            // a large struct by value or not.
            receiverIdx = method == originalMethod ? 0 : 1;
            Type nativeType = targetParameterTypes[receiverIdx];
            Value nativeValue = marshalObjectToNative(fn, marshalerMethod, marshaledArg, nativeType, env, args.get(receiverIdx).getValue(),
                    MarshalerFlags.CALL_TYPE_BRIDGE);
            args.set(receiverIdx, new Argument(nativeValue));
        }
        
        for (int i = 0, argIdx = 0; i < method.getParameterCount(); i++) {
            if (dynamic && (method == originalMethod && i == 0 || method != originalMethod && i == 1)) {
                // Skip the target function pointer for dynamic bridge methods.
                continue;
            }
            if (argIdx == receiverIdx) {
                // Skip the receiver in args. It doesn't correspond to a parameter.
                argIdx++;
            }
            soot.Type type = method.getParameterType(i);
            if (needsMarshaler(type)) {

                MarshalerMethod marshalerMethod = config.getMarshalerLookup().findMarshalerMethod(new MarshalSite(method, i));
                
                // The return type of the marshaler's toNative() method is derived from the target function type.
                Type nativeType = targetParameterTypes[argIdx];

                if (nativeType instanceof PrimitiveType) {
                    Value nativeValue = marshalValueObjectToNative(fn, marshalerMethod, nativeType, env, 
                            args.get(argIdx).getValue(), MarshalerFlags.CALL_TYPE_BRIDGE);
                    args.set(argIdx, new Argument(nativeValue));
                } else {
                    ParameterAttribute[] parameterAttributes = new ParameterAttribute[0];
                    if (isPassByValue(method, i) || isStructRet(method, i)) {
                        // The parameter must not be null. We assume that Structs 
                        // never have a NULL handle so we just check that the Java
                        // Object isn't null.
                        call(fn, CHECK_NULL, env, args.get(argIdx).getValue());
                        parameterAttributes = new ParameterAttribute[1];
                        if (isStructRet(method, i)) {
                            parameterAttributes[0] = sret;
                        } else {
                            parameterAttributes[0] = byval;
                        }
                    }
            
                    MarshaledArg marshaledArg = new MarshaledArg();
                    marshaledArg.paramIndex = i;
                    marshaledArgs.add(marshaledArg);
                    Value nativeValue = marshalObjectToNative(fn, marshalerMethod, marshaledArg, nativeType, env, args.get(argIdx).getValue(),
                            MarshalerFlags.CALL_TYPE_BRIDGE);
                    args.set(argIdx, new Argument(nativeValue, parameterAttributes));
                }
                
            } else {
                args.set(argIdx, new Argument(marshalPrimitiveToNative(fn, method, i, args.get(argIdx).getValue())));                    
            }
            
            argIdx++;
        }        
        
        // Execute the call to native code
        BasicBlockRef bbSuccess = fn.newBasicBlockRef(new Label("success"));
        BasicBlockRef bbFailure = fn.newBasicBlockRef(new Label("failure"));
        pushNativeFrame(fn);
        trycatchAllEnter(fn, env, bbSuccess, bbFailure);
        fn.newBasicBlock(bbSuccess.getLabel());
        Value result = callWithArguments(fn, targetFn.ref(), args);
        trycatchLeave(fn, env);
        popNativeFrame(fn);

        updateObject(method, fn, env, MarshalerFlags.CALL_TYPE_BRIDGE, marshaledArgs);
        
        // Marshal the return value
        if (needsMarshaler(method.getReturnType())) {
            MarshalerMethod marshalerMethod = config.getMarshalerLookup().findMarshalerMethod(new MarshalSite(method));
            String targetClassName = getInternalName(method.getReturnType());
            
            if (passByValue) {
                // Must be a small struct since larger structs are returned in 
                // the first parameter. Copy to the stack and then copy to the heap.
                Value stackCopy = createStackCopy(fn, result);
                Variable src = fn.newVariable(I8_PTR);
                fn.add(new Bitcast(src, stackCopy, I8_PTR));
                Value heapCopy = call(fn, BC_COPY_STRUCT, env, src.ref(), 
                        new IntegerConstant(dataLayout.getAllocSize(result.getType())));
                result = marshalNativeToObject(fn, marshalerMethod, null, env, 
                        targetClassName, heapCopy, MarshalerFlags.CALL_TYPE_BRIDGE);
            } else if (targetFnType.getReturnType() instanceof PrimitiveType) {
                result = marshalNativeToValueObject(fn, marshalerMethod, env, 
                        targetClassName, result, MarshalerFlags.CALL_TYPE_BRIDGE);
            } else {
                result = marshalNativeToObject(fn, marshalerMethod, null, env, 
                        targetClassName, result, MarshalerFlags.CALL_TYPE_BRIDGE);
            }
        } else {
            result = marshalNativeToPrimitive(fn, method, result);
        }
        
        if (method != originalMethod) {
            fn.add(new Ret(structObj));
        } else {
            fn.add(new Ret(result));
        }
        
        fn.newBasicBlock(bbFailure.getLabel());
        trycatchLeave(fn, env);
        popNativeFrame(fn);
        
        Value ex = call(fn, BC_EXCEPTION_CLEAR, env);
        
        // Call Marshaler.updateObject() for each object that was marshaled before
        // the call.
        updateObject(method, fn, env, MarshalerFlags.CALL_TYPE_BRIDGE, marshaledArgs);
        
        call(fn, BC_THROW, env, ex);
        fn.add(new Unreachable());
    }

    private void updateObject(SootMethod method, Function fn, Value env, long flags, List<MarshaledArg> marshaledArgs) {
        for (MarshaledArg value : marshaledArgs) {
            MarshalerMethod marshalerMethod = config.getMarshalerLookup().findMarshalerMethod(new MarshalSite(method, value.paramIndex));
            SootMethod afterMethod = ((PointerMarshalerMethod) marshalerMethod).getAfterBridgeCallMethod();
            if (afterMethod != null) {
                Invokestatic invokestatic = new Invokestatic(
                        getInternalName(method.getDeclaringClass()),
                        getInternalName(afterMethod.getDeclaringClass()), 
                        afterMethod.getName(),
                        getDescriptor(afterMethod));
                trampolines.add(invokestatic);
                call(fn, invokestatic.getFunctionRef(), 
                        env, value.object, value.handle, 
                        new IntegerConstant(flags));
            }
        }
    }
    
    public static String getTargetFnPtrName(SootMethod method) {
        return "bridge_" + mangleMethod(method) + "_ptr";
    }
}
