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
import java.util.Collections;
import java.util.List;

import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.StructureType;
import org.robovm.compiler.llvm.Type;
import org.robovm.compiler.util.GenericSignatureParser;

import soot.LongType;
import soot.PrimType;
import soot.RefType;
import soot.SootClass;
import soot.SootMethod;
import soot.SootResolver;
import soot.VoidType;
import soot.tagkit.AnnotationClassElem;
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
        // Use @Marshaler annotation on the method if there is one 
        AnnotationTag annotation = getMarshalerAnnotation(method);
        if (annotation == null) {
            // The method has no @Marshaler annotation
            SootClass type = ((RefType) method.getReturnType()).getSootClass();
            if (unwrapPtr && isPtr(type)) {
                // Search for a @Marshaler annotation for the target type pointed to
                type = getPtrTargetClass(method);
            }
            // Search for a @Marshaler annotation in the class declaring the method and its superclasses
            annotation = getMarshalerAnnotation(method.getDeclaringClass(), RefType.v(type));
            if (annotation == null) {
                // Search for a @Marshaler annotation in the outer class of the class declaring the method
                SootClass outerClass = getOuterClass(method.getDeclaringClass());
                if (outerClass != null) {
                    annotation = getMarshalerAnnotation(outerClass, RefType.v(type));                    
                }
            }
            if (annotation == null) {
                // Search the return type and its superclasses/superinterfaces
                annotation = getMarshalerAnnotation(type);                
                if (annotation == null && isEnum(type)) {
                    // For enums we have a fallback
                    return "org/robovm/rt/bro/EnumMarshalers$AsIntMarshaler";
                }
            }
        }
        if (annotation == null) {
            return null;
        }
        String desc = ((AnnotationClassElem) getElemByName(annotation, "value")).getDesc();
        return getInternalNameFromDescriptor(desc);
    }
    
    public static String getMarshalerClassName(SootMethod method, int paramIndex, boolean unwrapPtr) {
        // Use @Marshaler annotation on parameter at paramIndex if there is one 
        AnnotationTag annotation = getMarshalerAnnotation(method, paramIndex);
        if (annotation == null) {
            // The parameter has no @Marshaler annotation
            SootClass paramType = ((RefType) method.getParameterType(paramIndex)).getSootClass();
            if (unwrapPtr && isPtr(paramType)) {
                // Search for a @Marshaler annotation for the target type pointed to
                paramType = getPtrTargetClass(method, paramIndex);
            }
            // Search for a @Marshaler annotation in the class declaring the method and its superclasses
            annotation = getMarshalerAnnotation(method.getDeclaringClass(), RefType.v(paramType));
            if (annotation == null) {
                // Search for a @Marshaler annotation in the outer class of the class declaring the method
                SootClass outerClass = getOuterClass(method.getDeclaringClass());
                if (outerClass != null) {
                    annotation = getMarshalerAnnotation(outerClass, RefType.v(paramType));                    
                }
            }
            if (annotation == null) {
                // Search the parameter type and its superclasses/superinterfaces
                annotation = getMarshalerAnnotation(paramType);
                if (annotation == null && isEnum(paramType)) {
                    // For enums we have a fallback
                    return "org/robovm/rt/bro/EnumMarshalers$AsIntMarshaler";
                }
            }
        }
        if (annotation == null) {
            return null;
        }
        String desc = ((AnnotationClassElem) getElemByName(annotation, "value")).getDesc();
        return getInternalNameFromDescriptor(desc);
    }

    public static Type getMarshalType(String marshalerClassName, soot.Type type) {
        SootClass marshalerClass = SootResolver.v().resolveClass(marshalerClassName.replace('/', '.'), SootClass.SIGNATURES);
        boolean isenum = isEnum(type);
        Type defType = isenum ? I32 : I8_PTR;
        if (marshalerClass.isPhantom()) {
            return defType;
        }
        try {
            List<?> paramTypes = Collections.singletonList(RefType.v(isenum ? "java.lang.Enum" : "java.lang.Object"));
            SootMethod toNative = marshalerClass.getMethod("toNative", paramTypes);
            soot.Type returnType = toNative.getReturnType();
            if (returnType instanceof PrimType) {
                if (!isenum && hasPointerAnnotation(toNative) && returnType == LongType.v()) {
                    return I8_PTR;
                }
                return getType(returnType);
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
    
    private static Type getReturnType(String anno, SootMethod method) {
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

        String marshalerClassName = getMarshalerClassName(method, false);
        return getMarshalType(marshalerClassName, sootType);
    }
    
    private static Type getParameterType(String anno, SootMethod method, int i) {
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
        
        String marshalerClassName = getMarshalerClassName(method, i, false);
        return getMarshalType(marshalerClassName, sootType);
    }

    public static FunctionType getBridgeFunctionType(SootMethod method) {
        return getBridgeOrCallbackFunctionType("@Bridge", method);
    }
    
    public static FunctionType getCallbackFunctionType(SootMethod method) {
        return getBridgeOrCallbackFunctionType("@Callback", method);
    }
    
    private static FunctionType getBridgeOrCallbackFunctionType(String anno, SootMethod method) {
        Type returnType = getReturnType(anno, method);
        
        Type[] paramTypes = new Type[method.getParameterTypes().size()];
        for (int i = 0; i < paramTypes.length; i++) {
            paramTypes[i] = getParameterType(anno, method, i);
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
    
    public static StructureType getStructType(soot.Type t) {
        return getStructType(((RefType) t).getSootClass());                
    }
    
    private static void validateStructMemberPair(SootMethod getter, SootMethod setter, SootClass clazz, int offset) {
        if (getter != null && setter != null) {
            if (!setter.getParameterType(0).equals(getter.getReturnType())) {
                throw new IllegalArgumentException("Different type used " 
                        + "for getter and setter of " 
                        + "@StructMember(" + offset + ") in class " + clazz);                        
            }
            soot.Type type = getter.getReturnType();
            if (hasPointerAnnotation(getter) || hasPointerAnnotation(setter, 0)) {
                if (hasPointerAnnotation(getter) != hasPointerAnnotation(setter, 0)) {
                    throw new IllegalArgumentException("Getter and setter of " 
                            + "@StructMember(" + offset + ") in class " + clazz 
                            + " must both be annotated with @Pointer");                    
                }
            } else if (isStruct(type)) {
                // Both has to be either pass by value or pass by reference
                if (isPassByValue(getter) != isPassByValue(setter, 0)) {
                    throw new IllegalArgumentException("Getter and setter of " 
                            + "@StructMember(" + offset + ") in class " + clazz 
                            + " must either both use @ByVal or @ByRef");                    
                }
            }
        }
    }
    
    public static StructMemberPair getStructMemberPair(SootClass clazz, int offset) {
        return getStructMemberPairs(clazz)[offset];
    }
    
    private static StructMemberPair[] getStructMemberPairs(SootClass clazz) {
        int n = 0;
        for (SootMethod method : clazz.getMethods()) {
            n = Math.max(getStructMemberOffset(method) + 1, n);
        }
        if (n == 0) {
            return new StructMemberPair[0];
        }
        StructMemberPair[] result = new StructMemberPair[n];
        for (SootMethod method : clazz.getMethods()) {
            int offset = getStructMemberOffset(method);
            if (offset != -1) {
                StructMemberPair pair = result[offset];
                if (pair == null) {
                    pair = new StructMemberPair();
                    result[offset] = pair;
                }
                if (pair.getter != null && pair.setter != null) {
                    throw new IllegalArgumentException("Too many @StructMember " 
                            + "annotated methods with offset " + offset + " in class " + clazz);
                }
                if (!method.isNative() && !method.isStatic()) {
                    throw new IllegalArgumentException("@StructMember annotated method " 
                            + method.getName() + " in class " + clazz 
                            + " must be native and not static");
                }
                if (method.getParameterCount() == 0) {
                    // Possibly a getter
                    if (pair.getter != null) {
                        throw new IllegalArgumentException("Two getters for " 
                                + "@StructMember(" + offset + ") found in class " + clazz);
                    }
                    if (hasPointerAnnotation(method) && !method.getReturnType().equals(LongType.v())) {
                        throw new IllegalArgumentException("@StructMember(" + offset + ") in class " 
                                + clazz + " must be of type long when annotated with @Pointer");
                    }
                    if (!canMarshal(method)) {
                        throw new IllegalArgumentException("No @Marshaler found for " 
                                + "@StructMember(" + offset + ") in class " + clazz);
                    }
                    validateStructMemberPair(method, pair.setter, clazz, offset);
                    pair.getter = method;
                } else if (method.getParameterCount() == 1) {
                    if (pair.setter != null) {
                        throw new IllegalArgumentException("Two setters for " 
                                + "@StructMember(" + offset + ") found in class " + clazz);
                    }
                    if (hasPointerAnnotation(method, 0) && !method.getParameterType(0).equals(LongType.v())) {
                        throw new IllegalArgumentException("@StructMember(" + offset + ") in class " 
                                + clazz + " must be of type long when annotated with @Pointer");
                    }
                    if (!canMarshal(method, 0)) {
                        throw new IllegalArgumentException("No @Marshaler found for " 
                                + "@StructMember(" + offset + ") in class " + clazz);
                    }
                    validateStructMemberPair(pair.getter, method, clazz, offset);
                    soot.Type retType = method.getReturnType();
                    // The return type of the setter must be void or this
                    if (!retType.equals(VoidType.v()) && !(retType instanceof RefType && ((RefType) retType).getSootClass().equals(clazz))) {
                        throw new IllegalArgumentException("Setter " + method.getName() +" for " 
                                + "@StructMember(" + offset + ") in class " + clazz 
                                + " must either return nothing or return " + clazz);
                    }
                    pair.setter = method;
                } else {
                    throw new IllegalArgumentException("@StructMember annotated method " 
                            + method.getName() + " in class " + clazz 
                            + " has too many parameters");
                }
            }
        }
        
        for (int i = 0; i < result.length; i++) {
            if (result[i] == null) {
                throw new IllegalArgumentException("No @StructMember(" + i 
                        + ") defined in class " + clazz);
            }
        }
        
        return result;
    }
    
    public static StructureType getStructType(SootClass clazz) {
        List<Type> types = new ArrayList<Type>();
        
        for (StructMemberPair pair : getStructMemberPairs(clazz)) {
            SootMethod getter = pair.getter;
            SootMethod setter = pair.setter;
            soot.Type type = getter != null 
                    ? getter.getReturnType() : setter.getParameterType(0);
            if (isStruct(type)) {
                boolean byVal = getter != null ? isPassByValue(getter) : isPassByValue(setter, 0);
                if (!byVal) {
                    // NOTE: We use i8* instead of <StructType>* to support pointers to recursive structs
                    types.add(I8_PTR);
                } else {
                    try {
                        types.add(getStructType(type));
                    } catch (StackOverflowError e) {
                        throw new IllegalArgumentException("Struct type " + type + " refers to itself");
                    }
                }
            } else if (isNativeObject(type)) {
                types.add(I8_PTR);
            } else if (getter != null && hasPointerAnnotation(getter) || hasPointerAnnotation(setter)) {
                types.add(I8_PTR);
            } else if (type instanceof PrimType) {
                types.add(getType(type));
            } else {
                String marshalerClassName = getter != null ? getMarshalerClassName(getter, false) : getMarshalerClassName(setter, 0, false);
                types.add(getMarshalType(marshalerClassName, type));
            }
        }
        
        if (types.isEmpty()) {
            throw new IllegalArgumentException("Struct class " + clazz + " has no @StructMember annotated methods");
        }
        return new StructureType(types.toArray(new Type[types.size()]));
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
