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
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Type.*;

import java.util.ArrayList;
import java.util.List;

import org.robovm.compiler.MarshalerLookup.ArrayMarshalerMethod;
import org.robovm.compiler.MarshalerLookup.MarshalSite;
import org.robovm.compiler.MarshalerLookup.MarshalerMethod;
import org.robovm.compiler.MarshalerLookup.ValueMarshalerMethod;
import org.robovm.compiler.config.Config;
import org.robovm.compiler.llvm.Alloca;
import org.robovm.compiler.llvm.ArrayType;
import org.robovm.compiler.llvm.ConstantBitcast;
import org.robovm.compiler.llvm.DataLayout;
import org.robovm.compiler.llvm.Function;
import org.robovm.compiler.llvm.FunctionRef;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.GlobalRef;
import org.robovm.compiler.llvm.IntegerConstant;
import org.robovm.compiler.llvm.Inttoptr;
import org.robovm.compiler.llvm.Load;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.Ptrtoint;
import org.robovm.compiler.llvm.Store;
import org.robovm.compiler.llvm.StructureType;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.llvm.Value;
import org.robovm.compiler.llvm.Variable;
import org.robovm.compiler.trampoline.Invokestatic;
import org.robovm.compiler.trampoline.LdcClass;
import org.robovm.compiler.trampoline.Trampoline;

import soot.LongType;
import soot.PrimType;
import soot.RefType;
import soot.SootClass;
import soot.SootMethod;
import soot.VoidType;
import soot.tagkit.AnnotationTag;
import soot.tagkit.VisibilityAnnotationTag;
import soot.tagkit.VisibilityParameterAnnotationTag;


/**
 * 
 */
public abstract class BroMethodCompiler extends AbstractMethodCompiler {

    public BroMethodCompiler(Config config) {
        super(config);
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
    
    protected Value marshalObjectToNative(Function fn, MarshalerMethod marshalerMethod, MarshaledArg marshaledArg, 
            Type nativeType, Value env, Value object, long flags) {
        return marshalObjectToNative(fn, marshalerMethod, marshaledArg, nativeType, env, object, flags, false);
    }
    
    protected Value marshalObjectToNative(Function fn, MarshalerMethod marshalerMethod, MarshaledArg marshaledArg, 
            Type nativeType, Value env, Value object, long flags, boolean smallStructRet) {
        
        Invokestatic invokestatic = marshalerMethod.getInvokeStatic(sootMethod.getDeclaringClass());
        trampolines.add(invokestatic);
        Value handle = call(fn, invokestatic.getFunctionRef(), 
                env, object, new IntegerConstant(flags));
    
        Variable nativeValue = fn.newVariable(nativeType);
        if (nativeType instanceof StructureType || nativeType instanceof ArrayType || smallStructRet) {
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
        return call(fn, invokestatic.getFunctionRef(), env, object, new IntegerConstant(flags));
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
    
    private Type getReturnType(String anno, SootMethod method) {
        soot.Type sootType = method.getReturnType();
        if (hasPointerAnnotation(method)) {
            if (!sootType.equals(LongType.v())) {
                throw new IllegalArgumentException(anno + " annotated method " 
                        + method.getName() + " must return long when annotated with @Pointer");
            }
            return I8_PTR;
        }        
        if (isStruct(sootType)) {
            if (!isPassByValue(method)) {
                // Structs are returned by reference by default
                return new PointerType(getStructType(sootType));
            }
            // Only small Structs can be returned by value. How small is defined by the target ABI.
            // Larger Structs should be passed as parameters with the @StructRet annotation.
            return getStructType(sootType);
        } else if (isNativeObject(sootType)) {
            // NativeObjects are always returned by reference.
            return I8_PTR;
        } else if (sootType instanceof PrimType || sootType == VoidType.v()) {
            return getType(sootType);
        }

        MarshalerMethod marshalerMethod = config.getMarshalerLookup().findMarshalerMethod(new MarshalSite(method));
        if (marshalerMethod instanceof ValueMarshalerMethod) {
            return ((ValueMarshalerMethod) marshalerMethod).getNativeType();
        } else {
            return I8_PTR;
        }
    }
    
    private Type getParameterType(String anno, SootMethod method, int i) {
        soot.Type sootType = method.getParameterType(i);
        if (hasPointerAnnotation(method, i)) {
            if (!sootType.equals(LongType.v())) {
                throw new IllegalArgumentException("Parameter " + (i + 1) 
                        + " of " + anno + " annotated method " + method.getName() 
                        + " must be of type long when annotated with @Pointer.");
            }
            return I8_PTR;
        }
        if (hasStructRetAnnotation(method, i)) {
            if (i > 0) {
                throw new IllegalArgumentException("Parameter " + (i + 1) 
                        + " of " + anno + " annotated method " + method.getName() 
                        + " cannot be annotated with @StructRet. Only the first" 
                        + " parameter may have this annotation.");
            }
            if (!isStruct(sootType)) {
                throw new IllegalArgumentException("Parameter " + (i + 1) 
                        + " of " + anno + " annotated method " + method.getName() 
                        + " must be a sub class of Struct when annotated with @StructRet.");
            }
            // @StructRet implies pass by reference
            return new PointerType(getStructType(sootType));
        }        
        if (isStruct(sootType)) {
            if (isPassByValue(method, i) && "@Callback".equals(anno)) {
                return getStructType(sootType);
            }
            // For @Bridge methods Structs are always passed as pointers. The LLVM 
            // byval attribute  will be added to the parameter when making the 
            // call if @ByVal has been specified to get the desired pass by 
            // value semantics.
            return new PointerType(getStructType(sootType));
        } else if (isNativeObject(sootType)) {
            // NativeObjects are always passed by reference.
            return I8_PTR;
        } else if (sootType instanceof PrimType) {
            return getType(sootType);
        }
        
        MarshalerMethod marshalerMethod = config.getMarshalerLookup().findMarshalerMethod(new MarshalSite(method, i));
        if (marshalerMethod instanceof ValueMarshalerMethod) {
            return ((ValueMarshalerMethod) marshalerMethod).getNativeType();
        } else {
            return I8_PTR;
        }
    }

    public FunctionType getBridgeFunctionType(SootMethod method) {
        return getBridgeOrCallbackFunctionType("@Bridge", method);
    }
    
    public FunctionType getCallbackFunctionType(SootMethod method) {
        return getBridgeOrCallbackFunctionType("@Callback", method);
    }
    
    private FunctionType getBridgeOrCallbackFunctionType(String anno, SootMethod method) {
        Type returnType = getReturnType(anno, method);
        
        Type[] paramTypes = new Type[method.getParameterTypes().size()];
        for (int i = 0; i < paramTypes.length; i++) {
            paramTypes[i] = getParameterType(anno, method, i);
        }

        return new FunctionType(returnType, paramTypes);
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
                            + method.getName() + " in class " + clazz 
                            + " must be native and not static");
                }
                Type type = null;
                if (method.getParameterCount() == 0) {
                    // Possibly a getter
                    if (hasPointerAnnotation(method) && !method.getReturnType().equals(LongType.v())) {
                        throw new IllegalArgumentException("@StructMember(" + offset + ") annotated getter " + method.getName() 
                                + " in class " + clazz + " must be of type long when annotated with @Pointer");
                    }
                    if (method.getReturnType() instanceof soot.ArrayType && !hasArrayAnnotation(method)) {
                        throw new IllegalArgumentException("@Array annotation expected on struct member getter " + method);
                    }
                } else if (method.getParameterCount() == 1) {
                    if (hasPointerAnnotation(method, 0) && !method.getParameterType(0).equals(LongType.v())) {
                        throw new IllegalArgumentException("@StructMember(" + offset + ") annotated setter " + method.getName() 
                                + " in class " + clazz + " must be of type long when annotated with @Pointer");
                    }
                    if (method.getParameterType(0) instanceof soot.ArrayType && !hasArrayAnnotation(method, 0)) {
                        throw new IllegalArgumentException("@Array annotation expected on first parameter of struct member setter " + method);
                    }
                    soot.Type retType = method.getReturnType();
                    // The return type of the setter must be void or this
                    if (!retType.equals(VoidType.v()) && !(retType instanceof RefType && ((RefType) retType).getSootClass().equals(clazz))) {
                        throw new IllegalArgumentException("Setter " + method.getName() +" for " 
                                + "@StructMember(" + offset + ") in class " + clazz 
                                + " must either return nothing or return a " + clazz);
                    }
                } else {
                    throw new IllegalArgumentException("@StructMember annotated method " 
                            + method.getName() + " in class " + clazz 
                            + " has too many parameters");
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
    
    public Type getStructMemberType(SootMethod method) {
        SootMethod getter = method.getParameterCount() == 0 ? method : null;
        SootMethod setter = getter == null ? method: null;
        soot.Type type = getter != null 
                ? getter.getReturnType() : setter.getParameterType(0);
        Type memberType = null;
        if (getter != null && hasPointerAnnotation(getter) || setter != null && hasPointerAnnotation(setter, 0)) {
            memberType = I8_PTR;
        } else if (type instanceof PrimType) {
            memberType = getType(type);
        } else if (getter != null && hasArrayAnnotation(getter) || setter != null && hasArrayAnnotation(setter, 0)) {
            int[] dimensions = getter != null ? getArrayDimensions(getter) : getArrayDimensions(setter, 0);
            if (dimensions == null || dimensions.length == 0) {
                throw new IllegalArgumentException("No dimensions specified for @Array annotation on struct member " 
                        + (getter != null ? "getter" : "setter") + " " + method);
            }
            if (type instanceof soot.ArrayType && ((soot.ArrayType) type).numDimensions != dimensions.length) {
                throw new IllegalArgumentException("Mismatch in number of dimennsions for @Array annotation " 
                        + "and struct member type on struct member " 
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
                memberType = ((ValueMarshalerMethod) marshalerMethod).getNativeType();
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
        // Copy all annotations from the original method except for the parameter annotations
        method.addAllTagsOf(originalMethod);
        method.removeTag("VisibilityParameterAnnotationTag");
        // Copy all parameter annotations from the original method to the copy
        VisibilityParameterAnnotationTag vpaTag = new VisibilityParameterAnnotationTag(newParameterTypes.size(), 0);
        VisibilityAnnotationTag vaTag = new VisibilityAnnotationTag(0);
        vaTag.addAnnotation(new AnnotationTag(STRUCT_RET, 0));
        vpaTag.addVisibilityAnnotation(vaTag);
        for (int i = 0; i < originalMethod.getParameterCount(); i++) {
            vaTag = new VisibilityAnnotationTag(0);
            for (AnnotationTag tag : getParameterAnnotations(originalMethod, i)) {
                vaTag.addAnnotation(tag);
            }
            vpaTag.addVisibilityAnnotation(vaTag);
        }
        method.addTag(vpaTag);
        return method;
    }
}
