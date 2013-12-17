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
import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Type.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.robovm.compiler.llvm.ArrayType;
import org.robovm.compiler.llvm.DataLayout;
import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.StructureType;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.util.GenericSignatureParser;

import soot.BooleanType;
import soot.ByteType;
import soot.CharType;
import soot.DoubleType;
import soot.FloatType;
import soot.IntType;
import soot.LongType;
import soot.PrimType;
import soot.RefType;
import soot.ShortType;
import soot.SootClass;
import soot.SootMethod;
import soot.SootResolver;
import soot.VoidType;
import soot.tagkit.AnnotationArrayElem;
import soot.tagkit.AnnotationClassElem;
import soot.tagkit.AnnotationElem;
import soot.tagkit.AnnotationIntElem;
import soot.tagkit.AnnotationTag;
import soot.tagkit.InnerClassTag;
import soot.tagkit.SignatureTag;
import soot.tagkit.Tag;
import soot.tagkit.VisibilityAnnotationTag;
import soot.tagkit.VisibilityParameterAnnotationTag;

/**
 * 
 */
public abstract class Bro {

    public static boolean needsMarshaler(soot.Type t) {
        if (t.equals(VoidType.v()) || t instanceof PrimType) {
            // void and any of the primitive types can be marshaled directly
            return false;
        }
        return true;
    }
    
    private static boolean canMarshal(soot.Type t) {
        if (!needsMarshaler(t)) {
            // void and any of the primitive types can be marshaled directly
            return true;
        }
        if (t instanceof RefType) {
            if (isEnum(t)) {
                // Enums can always be marshaled
                return true;
            }
            // Classes which have a @Marshaler set (inherited) can be marshaled
            return getMarshalerAnnotation(((RefType) t).getSootClass()) != null;            
        }
        // Nothing else can be marshaled
        return false;
    }
    
    public static boolean canMarshal(SootMethod method) {
        if (canMarshal(method.getReturnType())) {
            return true;
        }
        // Methods which have a @Marshaler set can be marshaled
        return getMarshalerClassName(method, true) != null;            
    }
    
    public static boolean canMarshal(SootMethod method, int paramIndex) {
        if (canMarshal(method.getParameterType(paramIndex))) {
            return true;
        }
        // Parameters which have a @Marshaler set can be marshaled
        return getMarshalerClassName(method, paramIndex, true) != null;            
    }
    
    public static SootClass getPtrTargetClass(SootMethod method) {
        return getPtrTargetClass(method, -1);
    }
    
    public static SootClass getPtrTargetClass(SootMethod method, int paramIndex) {
        SignatureTag signatureTag = (SignatureTag) method.getTag("SignatureTag");
        if (signatureTag == null) {
            // Assume Ptr<VoidPtr>
            return SootResolver.v().resolveClass("org.robovm.bro.ptr.VoidPtr", SootClass.SIGNATURES);
        } else {
            GenericSignatureParser parser = new GenericSignatureParser(signatureTag.getSignature());
            String sig = paramIndex != -1 ? parser.getParameterSignature(paramIndex) : parser.getReturnTypeSignature();
            String name = sig.replaceAll("(?:Lorg/robovm/rt/bro/ptr/Ptr<)+L([^<;]+).*", "$1");
            name = name.replace('/', '.');
            return SootResolver.v().resolveClass(name, SootClass.SIGNATURES);
        }
    }

    public static int getPtrWrapCount(SootMethod method) {
        return getPtrWrapCount(method, -1);
    }
    
    public static int getPtrWrapCount(SootMethod method, int paramIndex) {
        SignatureTag signatureTag = (SignatureTag) method.getTag("SignatureTag");
        if (signatureTag == null) {
            // Assume Ptr<VoidPtr>
            return 1;
        } else {
            GenericSignatureParser parser = new GenericSignatureParser(signatureTag.getSignature());
            String sig = paramIndex != -1 ? parser.getParameterSignature(paramIndex) : parser.getReturnTypeSignature();
            int count = 0;
            while (sig.startsWith("Lorg/robovm/rt/bro/ptr/Ptr<")) {
                count++;
                sig = sig.substring("Lorg/robovm/rt/bro/ptr/Ptr<".length());
            }
            return count;
        }
    }
    
    private static SootClass getOuterClass(SootClass clazz) {
        for (Tag tag : clazz.getTags()) {
            if (tag instanceof InnerClassTag) {
                InnerClassTag innerClassTag = (InnerClassTag) tag;
                String inner = innerClassTag.getInnerClass();
                String outer = innerClassTag.getOuterClass();
                if (inner != null && inner.equals(getInternalName(clazz))) {
                    if (outer != null) {
                        return SootResolver.v().resolveClass(outer.replace('/', '.'), SootClass.SIGNATURES);
                    }
                }
            }
        }
        return null;
    }
    
    public static String getMarshalerClassName(SootMethod method, boolean unwrapPtr) {
        return getMarshalerClassName(method, -1, unwrapPtr);
    }
    
    public static String getMarshalerClassName(SootMethod method, int paramIndex, boolean unwrapPtr) {
        // Use @Marshaler annotation on method or parameter at paramIndex if there is one 
        AnnotationTag annotation = paramIndex == -1 ? getMarshalerAnnotation(method) : getMarshalerAnnotation(method, paramIndex);
        if (annotation == null) {
            // No @Marshaler annotation
            soot.Type type = paramIndex == -1 ? method.getReturnType() : method.getParameterType(paramIndex);
            SootClass clazz = null;
            if (type instanceof RefType) {
                clazz = ((RefType) type).getSootClass();
                if (unwrapPtr && isPtr(clazz)) {
                    // Search for a @Marshaler annotation for the target type pointed to
                    clazz = paramIndex == -1 ? getPtrTargetClass(method) : getPtrTargetClass(method, paramIndex);
                }
            }
            // Search for a @Marshaler annotation in the class declaring the method and its superclasses
            annotation = getMarshalerAnnotation(method.getDeclaringClass(), type);
            if (annotation == null) {
                // Search for a @Marshaler annotation in the outer class of the class declaring the method
                SootClass outerClass = getOuterClass(method.getDeclaringClass());
                if (outerClass != null) {
                    annotation = getMarshalerAnnotation(outerClass, type);                    
                }
            }
            if (clazz != null && annotation == null) {
                // Search the type and its superclasses/superinterfaces
                annotation = getMarshalerAnnotation(clazz);
            }
            if (annotation == null) {
                // Search builtin marshalers
                SootClass builtins = SootResolver.v().resolveClass("org.robovm.rt.bro.BuiltinMarshalers", SootClass.SIGNATURES);
                annotation = getMarshalerAnnotation(builtins, type);                    
            }
        }
        if (annotation == null) {
            return null;
        }
        String desc = ((AnnotationClassElem) getElemByName(annotation, "value")).getDesc();
        return getInternalNameFromDescriptor(desc);
    }

    public static Type getMarshalType(String marshalerClassName, soot.Type type, boolean isarray) {
        SootClass marshalerClass = SootResolver.v().resolveClass(marshalerClassName.replace('/', '.'), SootClass.SIGNATURES);
        boolean isenum = isEnum(type);
        Type defType = isenum ? I32 : isarray ? I8 : I8_PTR;
        if (marshalerClass.isPhantom()) {
            return defType;
        }
        try {
            List<?> paramTypes = isarray ? Arrays.asList(RefType.v("java.lang.Object"), LongType.v(), IntType.v()) 
                    : Collections.singletonList(RefType.v(isenum ? "java.lang.Enum" : "java.lang.Object"));
            SootMethod toNative = marshalerClass.getMethod("toNative", paramTypes);
            if (isarray) {
                soot.Type baseType = getArrayBaseType(toNative);
                if (baseType != null) {
                    if (hasPointerAnnotation(toNative) && baseType == LongType.v()) {
                        return I8_PTR;
                    }
                    return getType(baseType);
                }
            } else {
                soot.Type returnType = toNative.getReturnType();
                if (returnType instanceof PrimType) {
                    if (!isenum && hasPointerAnnotation(toNative) && returnType == LongType.v()) {
                        return I8_PTR;
                    }
                    return getType(returnType);
                }
            }
        } catch (RuntimeException e) {
            // Either not found or ambiguous
        }
        return defType;
    }
    
    public static int getStructMemberOffset(SootMethod method) {
        AnnotationTag annotation = getStructMemberAnnotation(method);
        if (annotation == null) {
            return -1;
        }
        return ((AnnotationIntElem) annotation.getElemAt(0)).getValue();
    }

    public static int[] getArrayDimensions(SootMethod method) {
        AnnotationTag annotation = getArrayAnnotation(method);
        if (annotation == null) {
            return null;
        }
        ArrayList<AnnotationElem> values = ((AnnotationArrayElem) annotation.getElemAt(0)).getValues();
        int[] dims = new int[values.size()];
        for (int i = 0; i < dims.length; i++) {
            dims[i] = ((AnnotationIntElem) values.get(i)).getValue();
        }
        return dims;
    }

    public static int[] getArrayDimensions(SootMethod method, int paramIndex) {
        AnnotationTag annotation = getArrayAnnotation(method, paramIndex);
        if (annotation == null) {
            return null;
        }
        ArrayList<AnnotationElem> values = ((AnnotationArrayElem) annotation.getElemAt(0)).getValues();
        int[] dims = new int[values.size()];
        for (int i = 0; i < dims.length; i++) {
            dims[i] = ((AnnotationIntElem) values.get(i)).getValue();
        }
        return dims;
    }

    public static soot.Type getArrayBaseType(SootMethod method) {
        AnnotationTag annotation = getBaseTypeAnnotation(method);
        if (annotation == null) {
            return null;
        }
        AnnotationClassElem elem = (AnnotationClassElem) annotation.getElemAt(0);
        switch (elem.getDesc().charAt(0)) {
        case 'Z': return BooleanType.v();
        case 'B': return ByteType.v();
        case 'S': return ShortType.v();
        case 'C': return CharType.v();
        case 'I': return IntType.v();
        case 'J': return LongType.v();
        case 'F': return FloatType.v();
        case 'D': return DoubleType.v();
        }
        throw new IllegalArgumentException("Unsupported type " + elem.getDesc() 
                + " in @BaseType annotation on method " + method + ". Only primitive types are supported.");
    }

    public static boolean isPassByValue(SootMethod method) {
        soot.Type sootType = method.getReturnType();
        return isStruct(sootType) && (hasByValAnnotation(method) 
                || hasByValAnnotation(((RefType) sootType).getSootClass()));
    }
    
    public static boolean isPassByValue(SootMethod method, int paramIndex) {
        soot.Type sootType = method.getParameterType(paramIndex);
        return isStruct(sootType) && !isPtr(sootType) && (hasByValAnnotation(method, paramIndex) 
                || hasByValAnnotation(((RefType) sootType).getSootClass()));
    }
    
    public static boolean isStructRet(SootMethod method, int paramIndex) {
        soot.Type sootType = method.getParameterType(paramIndex);
        return paramIndex == 0 && isStruct(sootType) && !isPtr(sootType) 
                && (hasStructRetAnnotation(method, paramIndex));
    }
    
    private static Type getReturnType(DataLayout dataLayout, String anno, SootMethod method) {
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
                return new PointerType(getStructType(dataLayout, sootType));
            }
            // Only small Structs can be returned by value. How small is defined by the target ABI.
            // Larger Structs should be passed as parameters with the @StructRet annotation.
            return getStructType(dataLayout, sootType);
        } else if (isNativeObject(sootType)) {
            // NativeObjects are always returned by reference.
            return I8_PTR;
        } else if (sootType instanceof PrimType || sootType == VoidType.v()) {
            return getType(sootType);
        }

        String marshalerClassName = getMarshalerClassName(method, false);
        return getMarshalType(marshalerClassName, sootType, false);
    }
    
    private static Type getParameterType(DataLayout dataLayout, String anno, SootMethod method, int i) {
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
            return new PointerType(getStructType(dataLayout, sootType));
        }        
        if (isStruct(sootType)) {
            if (isPassByValue(method, i) && "@Callback".equals(anno)) {
                return getStructType(dataLayout, sootType);
            }
            // For @Bridge methods Structs are always passed as pointers. The LLVM 
            // byval attribute  will be added to the parameter when making the 
            // call if @ByVal has been specified to get the desired pass by 
            // value semantics.
            return new PointerType(getStructType(dataLayout, sootType));
        } else if (isNativeObject(sootType)) {
            // NativeObjects are always passed by reference.
            return I8_PTR;
        } else if (sootType instanceof PrimType) {
            return getType(sootType);
        }
        
        String marshalerClassName = getMarshalerClassName(method, i, false);
        return getMarshalType(marshalerClassName, sootType, false);
    }

    public static FunctionType getBridgeFunctionType(DataLayout dataLayout, SootMethod method) {
        return getBridgeOrCallbackFunctionType(dataLayout, "@Bridge", method);
    }
    
    public static FunctionType getCallbackFunctionType(DataLayout dataLayout, SootMethod method) {
        return getBridgeOrCallbackFunctionType(dataLayout, "@Callback", method);
    }
    
    private static FunctionType getBridgeOrCallbackFunctionType(DataLayout dataLayout, String anno, SootMethod method) {
        Type returnType = getReturnType(dataLayout, anno, method);
        
        Type[] paramTypes = new Type[method.getParameterTypes().size()];
        for (int i = 0; i < paramTypes.length; i++) {
            paramTypes[i] = getParameterType(dataLayout, anno, method, i);
        }

        return new FunctionType(returnType, paramTypes);
    }
    
    public static boolean isBridge(SootMethod sm) {
        return hasBridgeAnnotation(sm);
    }
    
    public static boolean isCallback(SootMethod sm) {
        return hasCallbackAnnotation(sm);
    }
    
    public static boolean isStructMember(SootMethod sm) {
        return hasStructMemberAnnotation(sm);
    }
    
    public static StructureType getStructType(DataLayout dataLayout, soot.Type t) {
        return getStructType(dataLayout, ((RefType) t).getSootClass());                
    }
    
    public static StructureType getStructType(DataLayout dataLayout, SootClass clazz) {
        int n = 0;
        for (SootMethod method : clazz.getMethods()) {
            n = Math.max(getStructMemberOffset(method) + 1, n);
        }
        if (n == 0) {
            throw new IllegalArgumentException("Struct class " + clazz + " has no @StructMember annotated methods");
        }
        Type[] result = new Type[n];
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
                    if (!canMarshal(method)) {
                        throw new IllegalArgumentException("No @Marshaler found for " 
                                + "@StructMember(" + offset + ") annotated getter " + method.getName() 
                                + " in class " + clazz);
                    }
                } else if (method.getParameterCount() == 1) {
                    if (hasPointerAnnotation(method, 0) && !method.getParameterType(0).equals(LongType.v())) {
                        throw new IllegalArgumentException("@StructMember(" + offset + ") annotated setter " + method.getName() 
                                + " in class " + clazz + " must be of type long when annotated with @Pointer");
                    }
                    if (method.getParameterType(0) instanceof soot.ArrayType && !hasArrayAnnotation(method, 0)) {
                        throw new IllegalArgumentException("@Array annotation expected on first parameter of struct member setter " + method);
                    }
                    if (!canMarshal(method, 0)) {
                        throw new IllegalArgumentException("No @Marshaler found for " 
                                + "@StructMember(" + offset + ") annotated setter " + method.getName() 
                                + " in class " + clazz);
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
                
                type = getStructMemberType(dataLayout, method);
                if (result[offset] == null) {
                    result[offset] = type;
                } else if (type != result[offset]) {
                    // Two members mapped to the same offset (union). Pick
                    // the type with the largest alignment and pad with bytes
                    // up to the largest size.
                    result[offset] = mergeStructMemberTypes(dataLayout, type, result[offset]);
                }
            }
        }
        
        for (int i = 0; i < result.length; i++) {
            if (result[i] == null) {
                throw new IllegalArgumentException("No @StructMember(" + i 
                        + ") defined in class " + clazz);
            }
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
    
    public static Type getStructMemberType(DataLayout dataLayout, SootMethod method) {
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
                        baseType = getStructType(dataLayout, arrayType.baseType);
                    } catch (StackOverflowError e) {
                        throw new IllegalArgumentException("Struct type " + type + " refers to itself");
                    }
                } else {
                    baseType = getType(arrayType.baseType);
                }
            } else if (isStruct(type)) {
                boolean byVal = getter != null ? isPassByValue(getter) : isPassByValue(setter, 0);
                if (!byVal) {
                    // NOTE: We use i8* instead of <StructType>* to support pointers to recursive structs
                    baseType = I8_PTR;
                } else { 
                    try {
                        baseType = getStructType(dataLayout, type);
                    } catch (StackOverflowError e) {
                        throw new IllegalArgumentException("Struct type " + type + " refers to itself");
                    }
                }
            } else if (type instanceof RefType) {
                SootClass c = ((RefType) type).getSootClass();
                if (isInstanceOfClass(c, "java.nio.ByteBuffer")) {
                    baseType = I8;
                } else if (isInstanceOfClass(c, "java.nio.ShortBuffer")) {
                    baseType = I16;
                } else if (isInstanceOfClass(c, "java.nio.CharBuffer")) {
                    baseType = I16;
                } else if (isInstanceOfClass(c, "java.nio.IntBuffer")) {
                    baseType = I32;
                } else if (isInstanceOfClass(c, "java.nio.LongBuffer")) {
                    baseType = I64;
                } else if (isInstanceOfClass(c, "java.nio.FloatBuffer")) {
                    baseType = FLOAT;
                } else if (isInstanceOfClass(c, "java.nio.DoubleBuffer")) {
                    baseType = DOUBLE;
                } else {
                    String marshalerClassName = getter != null ? getMarshalerClassName(getter, false) : getMarshalerClassName(setter, 0, false);
                    baseType = getMarshalType(marshalerClassName, type, true);
                }
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
                    memberType = getStructType(dataLayout, type);
                } catch (StackOverflowError e) {
                    throw new IllegalArgumentException("Struct type " + type + " refers to itself");
                }
            }
        } else if (isNativeObject(type)) {
            memberType = I8_PTR;
        } else {
            String marshalerClassName = getter != null ? getMarshalerClassName(getter, false) : getMarshalerClassName(setter, 0, false);
            memberType = getMarshalType(marshalerClassName, type, false);
        }
        
        return memberType;
    }
    
    public static boolean isNativeObject(soot.Type t) {
        if (t instanceof RefType) {
            return isNativeObject(((RefType) t).getSootClass());
        }
        return false;
    }
    
    public static boolean isNativeObject(SootClass sc) {
        return isSubclass(sc, "org.robovm.rt.bro.NativeObject");
    }
    
    public static boolean isStruct(soot.Type t) {
        if (t instanceof RefType) {
            return isStruct(((RefType) t).getSootClass());
        }
        return false;
    }
    
    public static boolean isStruct(SootClass sc) {
        return !sc.isAbstract() && isSubclass(sc, "org.robovm.rt.bro.Struct");
    }
    
    public static boolean isPtr(soot.Type t) {
        if (t instanceof RefType) {
            return isPtr(((RefType) t).getSootClass());
        }
        return false;
    }
    
    public static boolean isPtr(SootClass sc) {
        return sc.getName().equals("org.robovm.rt.bro.ptr.Ptr");
    }
    
    public static class StructMemberPair {
        private SootMethod getter;
        private SootMethod setter;
        public SootMethod getGetter() {
            return getter;
        }
        public SootMethod getSetter() {
            return setter;
        }
    }
    
    public static SootMethod createFakeStructRetMethod(SootMethod originalMethod) {
        // Create a new method with the same parameters but a @StructRet parameter inserted first
        @SuppressWarnings("unchecked")
        ArrayList<soot.Type> newParameterTypes = new ArrayList<soot.Type>(originalMethod.getParameterTypes());
        newParameterTypes.add(0, originalMethod.getReturnType());
        SootMethod method = new SootMethod(originalMethod.getName(), newParameterTypes, VoidType.v(), originalMethod.getModifiers());
        method.setDeclaringClass(originalMethod.getDeclaringClass());
        method.setDeclared(true);
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
