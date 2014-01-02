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

import java.util.ArrayList;

import soot.PrimType;
import soot.RefType;
import soot.SootMethod;
import soot.VoidType;
import soot.tagkit.AnnotationArrayElem;
import soot.tagkit.AnnotationElem;
import soot.tagkit.AnnotationIntElem;
import soot.tagkit.AnnotationTag;

/**
 * 
 */
public abstract class Bro {

    public static class MarshalerFlags {
        // The constant here MUST correspond to those in org.robovm.rt.bro.MarshalerFlags in rt.
        
        // Bit 1-3: The type of call marshaled for
        public static final long CALL_TYPE_BRIDGE               = 0 << 0;
        public static final long CALL_TYPE_CALLBACK             = 1 << 0;
        public static final long CALL_TYPE_STRUCT_MEMBER        = 2 << 0;
        public static final long CALL_TYPE_GLOBAL_VALUE         = 3 << 0;
        public static final long CALL_TYPE_PTR                  = 4 << 0;
    }
    
    public static boolean needsMarshaler(soot.Type t) {
        if (t.equals(VoidType.v()) || t instanceof PrimType) {
            // void and any of the primitive types can be marshaled directly
            return false;
        }
        return true;
    }

    public static int getStructMemberOffset(SootMethod method) {
        AnnotationTag annotation = getStructMemberAnnotation(method);
        if (annotation == null) {
            return -1;
        }
        return ((AnnotationIntElem) annotation.getElemAt(0)).getValue();
    }

    public static int[] getArrayDimensions(SootMethod method) {
        return getArrayDimensions(method, -1);
    }

    public static int[] getArrayDimensions(SootMethod method, int paramIndex) {
        AnnotationTag annotation = paramIndex == -1 ? getArrayAnnotation(method) 
                : getArrayAnnotation(method, paramIndex);
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
        return paramIndex == 0 && isStruct(sootType) 
                && (hasStructRetAnnotation(method, paramIndex));
    }
    

}
