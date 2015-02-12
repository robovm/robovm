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

import static org.robovm.compiler.Annotations.*;
import static org.robovm.compiler.Bro.*;
import static org.robovm.compiler.Functions.*;
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Type.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.robovm.compiler.Annotations.Visibility;
import org.robovm.compiler.MarshalerLookup.ArrayMarshalerMethod;
import org.robovm.compiler.MarshalerLookup.MarshalSite;
import org.robovm.compiler.MarshalerLookup.MarshalerMethod;
import org.robovm.compiler.MarshalerLookup.ValueMarshalerMethod;
import org.robovm.compiler.clazz.Clazz;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.llvm.AggregateType;
import org.robovm.compiler.llvm.Alloca;
import org.robovm.compiler.llvm.ArrayType;
import org.robovm.compiler.llvm.Bitcast;
import org.robovm.compiler.llvm.ConstantBitcast;
import org.robovm.compiler.llvm.DataLayout;
import org.robovm.compiler.llvm.Fpext;
import org.robovm.compiler.llvm.Fptrunc;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.GlobalRef;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.IntegerType;
import org.robovm.compiler.llvm.Inttoptr;
import org.robovm.compiler.llvm.Load;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.PrimitiveType;
import org.robovm.compiler.llvm.Ptrtoint;
import org.robovm.compiler.llvm.Sext;
import org.robovm.compiler.llvm.Store;
import org.robovm.compiler.llvm.StructureType;
import org.robovm.compiler.llvm.Trunc;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;
import org.robovm.compiler.llvm.Zext;
import org.robovm.compiler.trampoline.Invokestatic;
import org.robovm.compiler.trampoline.LdcClass;
import org.robovm.compiler.trampoline.Trampoline;

import soot.DoubleType;
import soot.FloatType;
import soot.LongType;
import soot.PrimType;
import soot.RefType;
import soot.SootClass;
import soot.SootMethod;
import soot.VoidType;


/**
 * 
 */
public abstract class BroMethodCompiler extends AbstractMethodCompiler {
    private final List<String> cWrapperFunctions = new ArrayList<>();

    public BroMethodCompiler(Config config) {
        super(config);
    }

    @Override
    public void reset(Clazz clazz) {
        cWrapperFunctions.clear();
        super.reset(clazz);
    }
    
    public List<String> getCWrapperFunctions() {
        return cWrapperFunctions;
    }
    
    protected boolean requiresCWrapper(SootMethod method) {
        if (isPassByValue(method)) {
            return true;
        }
        if (hasVariadicAnnotation(method)) {
            return true;
        }
        for (int i = 0; i < method.getParameterCount(); i++) {
            if (isPassByValue(method, i)) {
                return true;
            }
        }
        return false;
    }
    
    protected Value ldcClass(Function fn, String name, Value env) {
        if (isArray(name) && isPrimitiveBaseType(name)) {
            String primitiveDesc = name.substring(name.length() - 1);
            Variable result = fn.newVariable(OBJECT_PTR);
            fn.add(new Load(result, new ConstantBitcast(
                    new GlobalRef("array_" + primitiveDesc, CLASS_PTR), new PointerType(OBJECT_PTR))));
            return result.ref();
        } else {
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
    }

    protected Value marshalNativeToPrimitive(Function fn, SootMethod method, int paramIndex, Value value) {
        soot.Type type = method.getParameterType(paramIndex);
        if (hasPointerAnnotation(method, paramIndex)) {
            value = marshalPointerToLong(fn, value);
        } else if (hasMachineSizedFloatAnnotation(method, paramIndex) && type.equals(DoubleType.v())) {
            value = marshalMachineSizedFloatToDouble(fn, value);
        } else if (hasMachineSizedFloatAnnotation(method, paramIndex) && type.equals(FloatType.v())) {
            value = marshalMachineSizedFloatToFloat(fn, value);
        } else if (hasMachineSizedSIntAnnotation(method, paramIndex) && type.equals(LongType.v())) {
            value = marshalMachineSizedSIntToLong(fn, value);
        } else if (hasMachineSizedUIntAnnotation(method, paramIndex) && type.equals(LongType.v())) {
            value = marshalMachineSizedUIntToLong(fn, value);
        }
        return value;
    }

    protected Value marshalPrimitiveToNative(Function fn, SootMethod method, int paramIndex, Value value) {
        soot.Type type = method.getParameterType(paramIndex);
        if (hasPointerAnnotation(method, paramIndex)) {
            value = marshalLongToPointer(fn, value);
        } else if (hasMachineSizedFloatAnnotation(method, paramIndex) && type.equals(DoubleType.v())) {
            value = marshalDoubleToMachineSizedFloat(fn, value);
        } else if (hasMachineSizedFloatAnnotation(method, paramIndex) && type.equals(FloatType.v())) {
            value = marshalFloatToMachineSizedFloat(fn, value);
        } else if (hasMachineSizedSIntAnnotation(method, paramIndex) && type.equals(LongType.v())) {
            value = marshalLongToMachineSizedInt(fn, value);
        } else if (hasMachineSizedUIntAnnotation(method, paramIndex) && type.equals(LongType.v())) {
            value = marshalLongToMachineSizedInt(fn, value);
        }
        return value;
    }
    
    protected Value marshalNativeToPrimitive(Function fn, SootMethod method, Value value) {
        soot.Type type = method.getReturnType();
        if (hasPointerAnnotation(method)) {
            value = marshalPointerToLong(fn, value);
        } else if (hasMachineSizedFloatAnnotation(method) && type.equals(DoubleType.v())) {
            value = marshalMachineSizedFloatToDouble(fn, value);
        } else if (hasMachineSizedFloatAnnotation(method) && type.equals(FloatType.v())) {
            value = marshalMachineSizedFloatToFloat(fn, value);
        } else if (hasMachineSizedSIntAnnotation(method) && type.equals(LongType.v())) {
            value = marshalMachineSizedSIntToLong(fn, value);
        } else if (hasMachineSizedUIntAnnotation(method) && type.equals(LongType.v())) {
            value = marshalMachineSizedUIntToLong(fn, value);
        }
        return value;
    }

    protected Value marshalPrimitiveToNative(Function fn, SootMethod method, Value value) {
        soot.Type type = method.getReturnType();
        if (hasPointerAnnotation(method)) {
            value = marshalLongToPointer(fn, value);
        } else if (hasMachineSizedFloatAnnotation(method) && type.equals(DoubleType.v())) {
            value = marshalDoubleToMachineSizedFloat(fn, value);
        } else if (hasMachineSizedFloatAnnotation(method) && type.equals(FloatType.v())) {
            value = marshalFloatToMachineSizedFloat(fn, value);
        } else if (hasMachineSizedSIntAnnotation(method) && type.equals(LongType.v())) {
            value = marshalLongToMachineSizedInt(fn, value);
        } else if (hasMachineSizedUIntAnnotation(method) && type.equals(LongType.v())) {
            value = marshalLongToMachineSizedInt(fn, value);
        }
        return value;
    }
    
    public static class MarshaledArg {
        public Value object;
        public Value handle;
        public int paramIndex;
    }
    
    protected Value marshalNativeToObject(Function fn, MarshalerMethod marshalerMethod, 
            MarshaledArg marshaledArg, Value env,  String valueClassName, 
            Value nativeValue, long flags) {
        
        if (nativeValue.getType() instanceof StructureType) {
            nativeValue = createStackCopy(fn, nativeValue);
        }
        
        Invokestatic invokestatic = marshalerMethod.getInvokeStatic(
                sootMethod.getDeclaringClass());
        trampolines.add(invokestatic);
    
        Value valueClass = ldcClass(fn, valueClassName, env);
        
        Variable handle = fn.newVariable(I64);
        fn.add(new Ptrtoint(handle, nativeValue, I64));
        
        Value object = call(fn, invokestatic.getFunctionRef(), 
                env, valueClass, handle.ref(), new IntegerConstant(flags));
        
        if (marshaledArg != null) {
            marshaledArg.handle = handle.ref();
            marshaledArg.object = object;
        }
        
        return object;
    }

    protected Value createStackCopy(Function fn, Value value) {
        Variable stackCopy = fn.newVariable(new PointerType(value.getType()));
        fn.add(new Alloca(stackCopy, value.getType()));
        fn.add(new Store(value, stackCopy.ref()));
        return stackCopy.ref();
    }
    
    protected Value marshalNativeToValueObject(Function fn, MarshalerMethod marshalerMethod, Value env, 
            String valueClassName, Value nativeValue, long flags) {
        
        Invokestatic invokeToObject = marshalerMethod.getInvokeStatic(
                sootMethod.getDeclaringClass());
        trampolines.add(invokeToObject);
    
        Value valueClass = ldcClass(fn, valueClassName, env);
        
        nativeValue = marshalNativeToPrimitive(fn, marshalerMethod.getMethod(), 1, nativeValue);
        
        return call(fn, invokeToObject.getFunctionRef(), env, valueClass, nativeValue, new IntegerConstant(flags));
    }

    private List<Value> arrayDimensionsValues(int[] dimensions) {
        List<Value> l = new ArrayList<>();
        for (int i = 0; i < dimensions.length; i++) {
            l.add(new IntegerConstant(dimensions[i]));
        }
        return l;
    }

    protected Value marshalNativeToArray(Function fn, MarshalerMethod marshalerMethod, Value env, 
            String arrayClassName, Value nativeValue, long flags, int[] dimensions) {
                
        Invokestatic invokeToObject = marshalerMethod.getInvokeStatic(sootMethod.getDeclaringClass());
        trampolines.add(invokeToObject);

        Variable handle = fn.newVariable(I64);
        fn.add(new Ptrtoint(handle, nativeValue, I64)); 

        Value valueClass = ldcClass(fn, arrayClassName, env);
        List<Value> args = new ArrayList<>();
        args.add(env);
        args.add(valueClass);
        args.add(handle.ref());
        args.add(new IntegerConstant(flags));
        args.addAll(arrayDimensionsValues(dimensions));
        
        return call(fn, invokeToObject.getFunctionRef(), args);
    }
    
    protected Value marshalPointerToLong(Function fn, Value pointer) {
        Variable result = fn.newVariable(I64);
        fn.add(new Ptrtoint(result, pointer, I64));
        return result.ref();
    }

    protected Value marshalMachineSizedSIntToLong(Function fn, Value value) {
        if (config.getArch().is32Bit()) {
            Variable result = fn.newVariable(I64);
            fn.add(new Sext(result, value, I64));
            return result.ref();
        } else {
            return value;
        }
    }

    protected Value marshalMachineSizedUIntToLong(Function fn, Value value) {
        if (config.getArch().is32Bit()) {
            Variable result = fn.newVariable(I64);
            fn.add(new Zext(result, value, I64));
            return result.ref();
        } else {
            return value;
        }
    }

    protected Value marshalMachineSizedFloatToDouble(Function fn, Value value) {
        if (config.getArch().is32Bit()) {
            Variable result = fn.newVariable(DOUBLE);
            fn.add(new Fpext(result, value, DOUBLE));
            return result.ref();
        } else {
            return value;
        }
    }

    protected Value marshalMachineSizedFloatToFloat(Function fn, Value value) {
        if (!config.getArch().is32Bit()) {
            Variable result = fn.newVariable(FLOAT);
            fn.add(new Fptrunc(result, value, FLOAT));
            return result.ref();
        } else {
            return value;
        }
    }

    protected Value marshalObjectToNative(Function fn, MarshalerMethod marshalerMethod, MarshaledArg marshaledArg, 
            Type nativeType, Value env, Value object, long flags) {
        
        Invokestatic invokestatic = marshalerMethod.getInvokeStatic(sootMethod.getDeclaringClass());
        trampolines.add(invokestatic);
        Value handle = call(fn, invokestatic.getFunctionRef(), 
                env, object, new IntegerConstant(flags));
    
        Variable nativeValue = fn.newVariable(nativeType);
        if (nativeType instanceof StructureType || nativeType instanceof ArrayType) {
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
    
    protected Value marshalValueObjectToNative(Function fn, MarshalerMethod marshalerMethod, 
            Type nativeType, Value env, Value object, long flags) {
        
        Invokestatic invokestatic = marshalerMethod.getInvokeStatic(
                sootMethod.getDeclaringClass());
        trampolines.add(invokestatic);
        Value result = call(fn, invokestatic.getFunctionRef(), env, object, new IntegerConstant(flags));
        return marshalPrimitiveToNative(fn, marshalerMethod.getMethod(), result);
    }
    
    protected void marshalArrayToNative(Function fn, MarshalerMethod marshalerMethod, 
            Value env, Value object, Value destPtr, long flags, int[] dimensions) {
        
        Invokestatic invokestatic = marshalerMethod.getInvokeStatic(sootMethod.getDeclaringClass());
        trampolines.add(invokestatic);

        Variable handle = fn.newVariable(I64);
        fn.add(new Ptrtoint(handle, destPtr, I64)); 

        List<Value> args = new ArrayList<>();
        args.add(env);
        args.add(object);
        args.add(handle.ref());
        args.add(new IntegerConstant(flags));
        args.addAll(arrayDimensionsValues(dimensions));

        call(fn, invokestatic.getFunctionRef(), args);
    }
    
    protected Value marshalLongToPointer(Function fn, Value handle) {
        Variable result = fn.newVariable(I8_PTR);
        fn.add(new Inttoptr(result, handle, I8_PTR));
        return result.ref();
    }

    protected Value marshalLongToMachineSizedInt(Function fn, Value value) {
        if (config.getArch().is32Bit()) {
            Variable result = fn.newVariable(I32);
            fn.add(new Trunc(result, value, I32));
            return result.ref();
        } else {
            return value;
        }
    }

    protected Value marshalDoubleToMachineSizedFloat(Function fn, Value value) {
        if (config.getArch().is32Bit()) {
            Variable result = fn.newVariable(FLOAT);
            fn.add(new Fptrunc(result, value, FLOAT));
            return result.ref();
        } else {
            return value;
        }
    }

    protected Value marshalFloatToMachineSizedFloat(Function fn, Value value) {
        if (!config.getArch().is32Bit()) {
            Variable result = fn.newVariable(DOUBLE);
            fn.add(new Fpext(result, value, DOUBLE));
            return result.ref();
        } else {
            return value;
        }
    }

    private Type getReturnType(String anno, SootMethod method) {
        soot.Type sootType = method.getReturnType();
        if (hasPointerAnnotation(method)) {
            if (!sootType.equals(LongType.v())) {
                throw new IllegalArgumentException(anno + " annotated method " 
                        + method + " must return long when annotated with @Pointer");
            }
            return I8_PTR;
        }        
        if (hasMachineSizedFloatAnnotation(method)) {
            if (!sootType.equals(DoubleType.v()) && !sootType.equals(FloatType.v())) {
                throw new IllegalArgumentException(anno + " annotated method " 
                        + method + " must return float or double when annotated with @MachineSizedFloat");
            }
            return config.getArch().is32Bit() ? FLOAT : DOUBLE;
        }        
        if (hasMachineSizedSIntAnnotation(method) || hasMachineSizedUIntAnnotation(method)) {
            if (!sootType.equals(LongType.v())) {
                throw new IllegalArgumentException(anno + " annotated method " 
                        + method + " must return long when annotated with @MachineSizedSInt or @MachineSizedUInt");
            }
            return config.getArch().is32Bit() ? I32 : I64;
        }        
        if (isStruct(sootType)) {
            if (!isPassByValue(method)) {
                // Structs are returned by reference by default
                return new PointerType(getStructType(sootType));
            }
            return getStructType(sootType);
        } else if (isNativeObject(sootType)) {
            // NativeObjects are always returned by reference.
            return I8_PTR;
        } else if (sootType instanceof PrimType || sootType == VoidType.v()) {
            return getType(sootType);
        }

        MarshalerMethod marshalerMethod = config.getMarshalerLookup().findMarshalerMethod(new MarshalSite(method));
        if (marshalerMethod instanceof ValueMarshalerMethod) {
            return ((ValueMarshalerMethod) marshalerMethod).getNativeType(config.getArch());
        } else {
            return I8_PTR;
        }
    }
    
    private Type getParameterType(String anno, SootMethod method, int i) {
        soot.Type sootType = method.getParameterType(i);
        if (hasPointerAnnotation(method, i)) {
            if (!sootType.equals(LongType.v())) {
                throw new IllegalArgumentException("Parameter " + (i + 1) 
                        + " of " + anno + " annotated method " + method 
                        + " must be of type long when annotated with @Pointer.");
            }
            return I8_PTR;
        }
        if (hasMachineSizedFloatAnnotation(method, i)) {
            if (!sootType.equals(DoubleType.v()) && !sootType.equals(FloatType.v())) {
                throw new IllegalArgumentException("Parameter " + (i + 1) 
                        + " of " + anno + " annotated method " + method 
                        + " must be of type float or double when annotated with @MachineSizedFloat.");
            }
            return config.getArch().is32Bit() ? FLOAT : DOUBLE;
        }
        if (hasMachineSizedSIntAnnotation(method, i) || hasMachineSizedUIntAnnotation(method, i)) {
            if (!sootType.equals(LongType.v())) {
                throw new IllegalArgumentException("Parameter " + (i + 1) 
                        + " of " + anno + " annotated method " + method 
                        + " must be of type long when annotated with " 
                        + "@MachineSizedSInt or @MachineSizedUInt");
            }
            return config.getArch().is32Bit() ? I32 : I64;
        }        
        if (hasStructRetAnnotation(method, i)) {
            if (i > 0) {
                throw new IllegalArgumentException("Parameter " + (i + 1) 
                        + " of " + anno + " annotated method " + method 
                        + " cannot be annotated with @StructRet. Only the first" 
                        + " parameter may have this annotation.");
            }
            if (!isStruct(sootType)) {
                throw new IllegalArgumentException("Parameter " + (i + 1) 
                        + " of " + anno + " annotated method " + method
                        + " must be a sub class of Struct when annotated with @StructRet.");
            }
            // @StructRet implies pass by reference
            return new PointerType(getStructType(sootType));
        }        
        if (isStruct(sootType)) {
            StructureType structType = getStructType(sootType);
            if (hasByValAnnotation(method, i)) {
                return getStructType(sootType);
            }
            return new PointerType(structType);
        } else if (isNativeObject(sootType)) {
            // NativeObjects are always passed by reference.
            return I8_PTR;
        } else if (sootType instanceof PrimType) {
            return getType(sootType);
        }
        
        MarshalerMethod marshalerMethod = config.getMarshalerLookup().findMarshalerMethod(new MarshalSite(method, i));
        if (marshalerMethod instanceof ValueMarshalerMethod) {
            return ((ValueMarshalerMethod) marshalerMethod).getNativeType(config.getArch());
        } else {
            return I8_PTR;
        }
    }

    public FunctionType getBridgeFunctionType(SootMethod method, boolean dynamic, boolean considerVariadic) {
        return getBridgeOrCallbackFunctionType("@Bridge", method, dynamic, considerVariadic);
    }
    
    public FunctionType getCallbackFunctionType(SootMethod method, boolean considerVariadic) {
        return getBridgeOrCallbackFunctionType("@Callback", method, false, considerVariadic);
    }
    
    private FunctionType getBridgeOrCallbackFunctionType(String anno, SootMethod method, boolean dynamic, boolean considerVariadic) {
        Type returnType = getReturnType(anno, method);
        
        boolean varargs = considerVariadic && hasVariadicAnnotation(method);
        int variadicIndex = varargs ? getVariadicParameterIndex(method) : Integer.MAX_VALUE;
        List<Type> paramTypes = new ArrayList<>();
        for (int i = dynamic ? 1 : 0; i < method.getParameterCount(); i++) {
            if (i == variadicIndex) {
                break;
            }
            paramTypes.add(getParameterType(anno, method, i));
        }
        if (!method.isStatic()) {
            int idx = hasStructRetAnnotation(method, 0) ? 1 : 0;
            soot.Type sootType = method.getDeclaringClass().getType();
            if (isStruct(sootType)) {
                paramTypes.add(idx, new PointerType(getStructType(sootType)));
            } else if (isNativeObject(sootType)) {
                // NativeObjects are always passed by reference.
                paramTypes.add(idx, I8_PTR);
            } else {
                throw new IllegalArgumentException("Receiver of non static " 
                        + anno + " method " + method 
                        + " must either be a Struct or a NativeObject");
            }
        }

        return new FunctionType(returnType, varargs, paramTypes.toArray(new Type[paramTypes.size()]));
    }
    
    public StructureType getStructType(soot.Type t) {
        return getStructType(((RefType) t).getSootClass());                
    }
    
    public StructureType getStructType(SootClass clazz) {
        return getStructType(clazz, true);
    }
    
    private StructureType getStructType(SootClass clazz, boolean checkEmpty) {
        int n = 0;
        for (SootMethod method : clazz.getMethods()) {
            n = Math.max(getStructMemberOffset(method) + 1, n);
        }
        
        Type[] result = new Type[n + 1];
        
        StructureType superType = null;
        if (clazz.hasSuperclass()) {
            SootClass superclass = clazz.getSuperclass();
            if (!superclass.getName().equals("org.robovm.rt.bro.Struct")) {
                superType = getStructType(superclass, false);
            }
        }
        result[0] = superType != null ? superType : new StructureType();
        
        for (SootMethod method : clazz.getMethods()) {
            int offset = getStructMemberOffset(method);
            if (offset != -1) {
                if (!method.isNative() && !method.isStatic()) {
                    throw new IllegalArgumentException("@StructMember annotated method " 
                            + method + " must be native and not static");
                }
                Type type = null;
                if (method.getParameterCount() == 0) {
                    soot.Type sootType = method.getReturnType();
                    // Possibly a getter
                    if (hasPointerAnnotation(method) && !sootType.equals(LongType.v())) {
                        throw new IllegalArgumentException("@StructMember(" + offset + ") annotated getter " + method 
                                + " must be of type long when annotated with @Pointer");
                    }
                    if (hasMachineSizedFloatAnnotation(method) && !sootType.equals(DoubleType.v()) && !sootType.equals(FloatType.v())) {
                        throw new IllegalArgumentException("@StructMember(" + offset + ") annotated getter " + method
                                + " must be of type float or double when annotated with @MachineSizedFloat");
                    }
                    if ((hasMachineSizedSIntAnnotation(method) || hasMachineSizedUIntAnnotation(method)) && !sootType.equals(LongType.v())) {
                        throw new IllegalArgumentException("@StructMember(" + offset + ") annotated getter " + method
                                + " must be of type long when annotated with @MachineSizedSInt or @MachineSizedUInt");
                    }
                    if (sootType instanceof soot.ArrayType && !hasArrayAnnotation(method)) {
                        throw new IllegalArgumentException("@Array annotation expected on struct member getter " + method);
                    }
                } else if (method.getParameterCount() == 1) {
                    soot.Type sootType = method.getParameterType(0);
                    if (hasPointerAnnotation(method, 0) && !sootType.equals(LongType.v())) {
                        throw new IllegalArgumentException("@StructMember(" + offset + ") annotated setter " + method 
                                + " must be of type long when annotated with @Pointer");
                    }
                    if (hasMachineSizedFloatAnnotation(method, 0) && !sootType.equals(DoubleType.v()) && !sootType.equals(FloatType.v())) {
                        throw new IllegalArgumentException("@StructMember(" + offset + ") annotated setter " + method
                                + " must be of type float or double when annotated with @MachineSizedFloat");
                    }
                    if ((hasMachineSizedSIntAnnotation(method, 0) || hasMachineSizedUIntAnnotation(method)) && !sootType.equals(LongType.v())) {
                        throw new IllegalArgumentException("@StructMember(" + offset + ") annotated setter " + method
                                + " must be of type long when annotated with @MachineSizedSInt or @MachineSizedUInt");
                    }
                    if (sootType instanceof soot.ArrayType && !hasArrayAnnotation(method, 0)) {
                        throw new IllegalArgumentException("@Array annotation expected on first parameter of struct member setter " + method);
                    }
                    soot.Type retType = method.getReturnType();
                    // The return type of the setter must be void or this
                    if (!retType.equals(VoidType.v()) && !(retType instanceof RefType && ((RefType) retType).getSootClass().equals(clazz))) {
                        throw new IllegalArgumentException("Setter " + method +" for " 
                                + "@StructMember(" + offset + ") "
                                + " must either return nothing or return a " + clazz);
                    }
                } else {
                    throw new IllegalArgumentException("@StructMember annotated method " 
                            + method + " has too many parameters");
                }
                
                type = getStructMemberType(method);
                int index = offset + 1;
                if (result[index] == null) {
                    result[index] = type;
                } else if (type != result[index]) {
                    // Two members mapped to the same offset (union). Pick
                    // the type with the largest alignment and pad with bytes
                    // up to the largest size.
                    result[index] = mergeStructMemberTypes(config.getDataLayout(), type, result[index]);
                }
            }
        }
        
        for (int i = 1; i < result.length; i++) {
            if (result[i] == null) {
                throw new IllegalArgumentException("No @StructMember(" + i 
                        + ") defined in class " + clazz);
            }
        }

        if (!clazz.isAbstract() && checkEmpty && n == 0 && superType == null) {
            throw new IllegalArgumentException("Struct class " + clazz + " has no @StructMember annotated methods");
        }
        
        return new StructureType(result);
    }
    
    static Type mergeStructMemberTypes(DataLayout dataLayout, Type t1, Type t2) {
        int align1 = dataLayout.getAlignment(t1);
        int align2 = dataLayout.getAlignment(t2);
        int size1 = dataLayout.getStoreSize(t1);
        int size2 = dataLayout.getStoreSize(t2);
        
        Type result = align1 < align2 ? t2 : t1;
        int size = align1 < align2 ? size2 : size1;
        int pad = Math.max(size1, size2) - size;
        if (pad > 0) {
            return new StructureType(result, new ArrayType(pad, I8));
        } else {
            return result;
        }
    }
    
    protected static String getHiType(Type type) {
        if (type == Type.VOID) {
            return "void";
        }
        if (type instanceof PointerType || type instanceof AggregateType) {
            return "void*";
        } else if (type instanceof IntegerType) {
            switch (((IntegerType) type).getBits()) {
            case 8:
                return "char";
            case 16:
                return "short";
            case 32:
                return "int";
            case 64:
                return "long long";
            }
        } else if (type == Type.FLOAT) {
            return "float";
        } else if (type == Type.DOUBLE) {
            return "double";
        }
        throw new IllegalArgumentException("Cannot convert type " + type + " to C type");
    }

    protected static String getLoType(final Type type, String base, int index, Map<String, String> structs) {
        if (type instanceof StructureType) {
            StringBuilder sb = new StringBuilder();
            StructureType st = (StructureType) type;
            sb.append("{");
            String name = String.format("%s_%04d", base, index);
            for (int i = 0; i < st.getTypeCount(); i++) {
                Type t = st.getTypeAt(i);
                if (i == 0 && t instanceof StructureType) {
                    if (((StructureType) t).getTypeCount() == 0) {
                        // Skip empty structs as first member
                        continue;
                    }
                }
                // Only support arrays embedded in structs
                StringBuilder dims = new StringBuilder();
                while (t instanceof ArrayType) {
                    ArrayType at = (ArrayType) t;
                    dims.append('[').append(at.getSize()).append(']');
                    t = ((ArrayType) t).getElementType();
                }
                sb.append(getLoType(t, name, i, structs)).append(" m" + i).append(dims).append(";");
            }
            sb.append("}");
            structs.put(name, sb.toString());
            return "struct " + name;
        } else {
            return getHiType(type);
        }
    }

    public Type getStructMemberType(SootMethod method) {
        String methodType = hasStructMemberAnnotation(method) ? "@StructMember" : "@GlobalValue";
        SootMethod getter = method.getParameterCount() == 0 ? method : null;
        SootMethod setter = getter == null ? method: null;
        soot.Type type = getter != null 
                ? getter.getReturnType() : setter.getParameterType(0);
        Type memberType = null;
        if (getter != null && hasPointerAnnotation(getter) || setter != null && hasPointerAnnotation(setter, 0)) {
            memberType = I8_PTR;
        } else if (getter != null && hasMachineSizedFloatAnnotation(getter) || setter != null && hasMachineSizedFloatAnnotation(setter, 0)) {
            memberType = config.getArch().is32Bit() ? FLOAT : DOUBLE;
        } else if (getter != null && (hasMachineSizedSIntAnnotation(getter) || hasMachineSizedUIntAnnotation(getter)) 
                || setter != null && (hasMachineSizedSIntAnnotation(setter, 0) || hasMachineSizedUIntAnnotation(setter, 0))) {
            memberType = config.getArch().is32Bit() ? I32 : I64;
        } else if (type instanceof PrimType) {
            memberType = getType(type);
        } else if (getter != null && hasArrayAnnotation(getter) || setter != null && hasArrayAnnotation(setter, 0)) {
            int[] dimensions = getter != null ? getArrayDimensions(getter) : getArrayDimensions(setter, 0);
            if (dimensions == null || dimensions.length == 0) {
                throw new IllegalArgumentException("No dimensions specified for @Array annotation on " + methodType + " " 
                        + (getter != null ? "getter" : "setter") + " " + method);
            }
            if (type instanceof soot.ArrayType && ((soot.ArrayType) type).numDimensions != dimensions.length) {
                throw new IllegalArgumentException("Mismatch in number of dimennsions for @Array annotation " 
                        + "and type on " + methodType + " " 
                        + (getter != null ? "getter" : "setter") + " " + method);
            }

            Type baseType = null;
            if (type instanceof soot.ArrayType) {
                soot.ArrayType arrayType = (soot.ArrayType) type;
                if (isStruct(arrayType.baseType)) {
                    // ByVal is implied for arrays of structs
                    try {
                        baseType = getStructType(arrayType.baseType);
                    } catch (StackOverflowError e) {
                        throw new IllegalArgumentException("Struct type " + type + " refers to itself");
                    }
                } else {
                    baseType = getType(arrayType.baseType);
                }
            } else if (isStruct(type)) {
                // ByVal is implied
                try {
                    baseType = getStructType(type);
                } catch (StackOverflowError e) {
                    throw new IllegalArgumentException("Struct type " + type + " refers to itself");
                }
            } else if (type instanceof RefType) {
                MarshalerMethod marshalerMethod = config.getMarshalerLookup()
                        .findMarshalerMethod(getter != null ? new MarshalSite(getter) : new MarshalSite(setter, 0));
                baseType = getType(((ArrayMarshalerMethod) marshalerMethod).getBaseType());
            }
            
            if (baseType == null) {
                throw new IllegalArgumentException("Arrays of " + type + " is not supported");
            }

            long total = dimensions[0];
            for (int i = 1; i < dimensions.length; i++) {
                total *= dimensions[i];
            }
            memberType = new ArrayType(total, baseType);
        } else if (isStruct(type)) {
            boolean byVal = getter != null ? isPassByValue(getter) : isPassByValue(setter, 0);
            if (!byVal) {
                // NOTE: We use i8* instead of <StructType>* to support pointers to recursive structs
                memberType = I8_PTR;
            } else {
                try {
                    memberType = getStructType(type);
                } catch (StackOverflowError e) {
                    throw new IllegalArgumentException("Struct type " + type + " refers to itself");
                }
            }
        } else if (isNativeObject(type)) {
            memberType = I8_PTR;
        } else {
            MarshalerMethod marshalerMethod = config.getMarshalerLookup()
                    .findMarshalerMethod(getter != null ? new MarshalSite(getter) : new MarshalSite(setter, 0));
            if (marshalerMethod instanceof ValueMarshalerMethod) {
                memberType = ((ValueMarshalerMethod) marshalerMethod).getNativeType(config.getArch());
            } else {
                memberType = I8_PTR;
            }
        }
        
        return memberType;
    }
    
    protected SootMethod createFakeStructRetMethod(SootMethod originalMethod) {
        // Create a new method with the same parameters but a @StructRet parameter inserted first
        @SuppressWarnings("unchecked")
        ArrayList<soot.Type> newParameterTypes = new ArrayList<soot.Type>(originalMethod.getParameterTypes());
        newParameterTypes.add(0, originalMethod.getReturnType());
        SootMethod method = new SootMethod(originalMethod.getName(), newParameterTypes, VoidType.v(), originalMethod.getModifiers());
        method.setDeclaringClass(originalMethod.getDeclaringClass());
        method.setDeclared(true);
        // Copy all annotations from the original method
        copyAnnotations(originalMethod, method, Visibility.Any);
        // Copy all parameter annotations from the original method to the copy shifting by 1
        copyParameterAnnotations(originalMethod, method, 0, originalMethod.getParameterCount(), 1, Visibility.Any);
        // Add @StructRet to parameter 0
        addRuntimeVisibleParameterAnnotation(method, 0, STRUCT_RET);
        return method;
    }

    protected Value loadValueForGetter(SootMethod method, Function fn, Type memberType,
            Value memberPtr, Value env, boolean dereference, long flags) {
            
        soot.Type type = method.getReturnType();
        
        Value result = null;
        if (memberType instanceof StructureType) {
            // The member is a child struct contained in the current struct
            result = memberPtr;
        } else if (memberType instanceof ArrayType) {
            // The member is an array contained in the current struct
            result = memberPtr;
        } else if (dereference) {
            Variable tmp = fn.newVariable(memberType);
            fn.add(new Load(tmp, memberPtr));
            result = tmp.ref();
        } else {
            // Do not dereference the pointer but use it as is. This is needed for
            // global values such as _dispatch_main_q which is a struct and not a
            // pointer which we should load. We want the address of the struct.
            Variable tmp = fn.newVariable(memberType);
            fn.add(new Bitcast(tmp, memberPtr, tmp.getType()));
            result = tmp.ref();
        }
        
        if (needsMarshaler(type)) {
            MarshalerMethod marshalerMethod = config.getMarshalerLookup().findMarshalerMethod(new MarshalSite(method));
            String targetClassName = getInternalName(type);
            
            if (memberType instanceof PrimitiveType) {
                // Value type wrapping a primitive value (e.g. Enum, Integer and Bits)
                result = marshalNativeToValueObject(fn, marshalerMethod, env, 
                        targetClassName, result, flags);
            } else {
                if (memberType instanceof ArrayType) {
                    // Array
                    result = marshalNativeToArray(fn, marshalerMethod, env, 
                            targetClassName, result, flags,
                            getArrayDimensions(method));
                } else {
                    result = marshalNativeToObject(fn, marshalerMethod, null, env, 
                            targetClassName, result, flags);
                }
            }
        } else {
            result = marshalNativeToPrimitive(fn, method, result);
        }
        return result;
    }

    protected void storeValueForSetter(SootMethod method, Function function,
            Type memberType, Value memberPtr, Value env, Value value, long flags) {
        
        soot.Type type = method.getParameterType(0);
        if (needsMarshaler(type)) {
            MarshalerMethod marshalerMethod = config.getMarshalerLookup().findMarshalerMethod(new MarshalSite(method, 0));
            
            if (memberType instanceof PrimitiveType) {
                value = marshalValueObjectToNative(function, marshalerMethod, memberType, env, 
                        value, flags);
            } else {
                if (memberType instanceof StructureType || memberType instanceof ArrayType) {
                    // The parameter must not be null. We assume that Structs 
                    // never have a NULL handle so we just check that the Java
                    // Object isn't null.
                    call(function, CHECK_NULL, env, value);
                }
                
                if (memberType instanceof ArrayType) {
                    // Array
                    marshalArrayToNative(function, marshalerMethod, env, value, memberPtr, 
                            flags, getArrayDimensions(method, 0));
                    value = null;
                } else {
                    value = marshalObjectToNative(function, marshalerMethod, null, memberType, env, value,
                            flags);
                }
            }
        } else {
            value = marshalPrimitiveToNative(function, method, 0, value);
        }
        if (value != null) {
            function.add(new Store(value, memberPtr));
        }
    }
}
