/*
 * Copyright (C) 2012 RoboVM
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

import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.PointerType;
import org.robovm.compiler.llvm.Type;

import soot.LongType;
import soot.PrimType;
import soot.RefType;
import soot.SootMethod;
import soot.VoidType;
import soot.tagkit.AnnotationClassElem;
import soot.tagkit.AnnotationTag;

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
            // Classes which have a @Marshaler set (inherited) can be marshaled
            return getMarshalerAnnotation(((RefType) t).getSootClass()) != null;            
        }
        // Nothing else can be marshaled
        return false;
    }
    
    public static boolean canMarshal(SootMethod method) {
        return canMarshal(method.getReturnType());
    }
    
    public static boolean canMarshal(SootMethod method, int paramIndex) {
        soot.Type t = method.getParameterType(paramIndex);
        if (canMarshal(t)) {
            return true;
        }
        if (t instanceof RefType) {
            // Parameters which have a @Marshaler set can be marshaled
            return getMarshalerAnnotation(method, paramIndex) != null;            
        }
        // Nothing else can be marshaled
        return false;
    }
    
    public static String getMarshalerClassName(SootMethod method) {
        AnnotationTag annotation = getMarshalerAnnotation(method);
        if (annotation == null) {
            annotation = getMarshalerAnnotation(((RefType) method.getReturnType()).getSootClass());
        }
        String desc = ((AnnotationClassElem) annotation.getElemAt(0)).getDesc();
        return desc.substring(1, desc.length() - 1);
    }
    
    public static String getMarshalerClassName(SootMethod method, int paramIndex) {
        AnnotationTag annotation = getMarshalerAnnotation(method, paramIndex);
        if (annotation == null) {
            annotation = getMarshalerAnnotation(((RefType) method.getParameterType(paramIndex)).getSootClass());
        }
        String desc = ((AnnotationClassElem) annotation.getElemAt(0)).getDesc();
        return desc.substring(1, desc.length() - 1);
    }

    public static boolean isPassByValue(SootMethod method) {
        soot.Type sootType = method.getReturnType();
        return isStruct(sootType) && (hasByValAnnotation(method) 
                || hasByValAnnotation(((RefType) sootType).getSootClass()));
    }
    
    public static boolean isPassByValue(SootMethod method, int paramIndex) {
        soot.Type sootType = method.getParameterType(paramIndex);
        return isStruct(sootType) && (hasByValAnnotation(method, paramIndex) 
                || hasByValAnnotation(((RefType) sootType).getSootClass()));
    }
    
    public static boolean isStructRet(SootMethod method, int paramIndex) {
        soot.Type sootType = method.getParameterType(paramIndex);
        return paramIndex == 0 && isStruct(sootType) && (hasStructRetAnnotation(method, paramIndex));
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
        }
        return getType(sootType);
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
            // Structs are always passed as pointers. The LLVM byval attribute 
            // will be added to the parameter when making the call if @ByVal has 
            // been specified to get the desired pass by value semantics.
            return new PointerType(getStructType(sootType));
        } else if (isNativeObject(sootType)) {
            // NativeObjects are always passed by reference.
            return I8_PTR;
        }
        return getType(sootType);
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
    
}
