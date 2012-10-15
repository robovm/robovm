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

import static org.robovm.compiler.Types.*;

import java.util.*;

import soot.*;
import soot.tagkit.*;

/**
 * @author niklas
 *
 */
public class Annotations {
    public static final String BRIDGE = "Lorg/robovm/rt/bro/annotation/Bridge;";
    public static final String CALLBACK = "Lorg/robovm/rt/bro/annotation/Callback;";
    public static final String STRUCT_MEMBER = "Lorg/robovm/rt/bro/annotation/StructMember;";
    public static final String STRUCT_RET = "Lorg/robovm/rt/bro/annotation/StructRet;";
    public static final String POINTER = "Lorg/robovm/rt/bro/annotation/Pointer;";
    public static final String MARSHALER = "Lorg/robovm/rt/bro/annotation/Marshaler;";
    public static final String MARSHALERS = "Lorg/robovm/rt/bro/annotation/Marshalers;";
    public static final String BY_VAL = "Lorg/robovm/rt/bro/annotation/ByVal;";
    public static final String BY_REF = "Lorg/robovm/rt/bro/annotation/ByRef;";

    public static boolean hasAnnotation(Host host, String annotationType) {
        return getAnnotation(host, annotationType) != null;
    }
    
    public static boolean hasParameterAnnotation(SootMethod method, int paramIndex, String annotationType) {
        return getParameterAnnotation(method, paramIndex, annotationType) != null;
    }

    public static List<AnnotationTag> getAnnotations(Host host) {
        VisibilityAnnotationTag vatag = (VisibilityAnnotationTag) host.getTag("VisibilityAnnotationTag");
        if (vatag != null) {
            return vatag.getAnnotations();
        }
        return Collections.emptyList();
    }
    
    public static AnnotationTag getAnnotation(Host host, String annotationType) {
        for (AnnotationTag tag : getAnnotations(host)) {
            if (annotationType.equals(tag.getType())) {
                return tag;
            }                    
        }
        return null;
    }
    
    public static List<AnnotationTag> getParameterAnnotations(SootMethod method, int paramIndex) {
        VisibilityParameterAnnotationTag vpatag = (VisibilityParameterAnnotationTag) method.getTag("VisibilityParameterAnnotationTag");
        if (vpatag != null) {
            List<VisibilityAnnotationTag> tags = vpatag.getVisibilityAnnotations();
            List<AnnotationTag> annotations = tags.get(paramIndex).getAnnotations();
            if (annotations != null) {
                return annotations;
            }
        }
        return Collections.emptyList();
    }

    public static AnnotationTag getParameterAnnotation(SootMethod method, int paramIndex, String annotationType) {
        for (AnnotationTag tag : getParameterAnnotations(method, paramIndex)) {
            if (annotationType.equals(tag.getType())) {
                return tag;
            }                    
        }
        return null;
    }
    
    public static boolean hasBridgeAnnotation(SootMethod method) {
        return hasAnnotation(method, BRIDGE);
    }
    
    public static boolean hasCallbackAnnotation(SootMethod method) {
        return hasAnnotation(method, CALLBACK);
    }
    
    public static boolean hasStructMemberAnnotation(SootMethod method) {
        return hasAnnotation(method, STRUCT_MEMBER);
    }
    
    public static boolean hasPointerAnnotation(SootMethod method) {
        return hasAnnotation(method, POINTER);
    }

    public static boolean hasByValAnnotation(SootMethod method) {
        return hasAnnotation(method, BY_VAL);
    }
    
    public static boolean hasByRefAnnotation(SootMethod method) {
        return hasAnnotation(method, BY_REF);
    }
    
    public static boolean hasStructRetAnnotation(SootMethod method, int paramIndex) {
        return hasParameterAnnotation(method, paramIndex, STRUCT_RET);
    }
    
    public static boolean hasPointerAnnotation(SootMethod method, int paramIndex) {
        return hasParameterAnnotation(method, paramIndex, POINTER);
    }
    
    public static boolean hasByValAnnotation(SootMethod method, int paramIndex) {
        return hasParameterAnnotation(method, paramIndex, BY_VAL);
    }
    
    public static boolean hasByRefAnnotation(SootMethod method, int paramIndex) {
        return hasParameterAnnotation(method, paramIndex, BY_REF);
    }
    
    public static AnnotationTag getMarshalerAnnotation(SootMethod method, int paramIndex) {
        return getParameterAnnotation(method, paramIndex, MARSHALER);
    }
    
    public static AnnotationTag getMarshalerAnnotation(SootMethod method) {
        return getAnnotation(method, MARSHALER);
    }
    
    public static AnnotationTag getMarshalerAnnotation(SootClass clazz) {
        return getMarshalerAnnotation(clazz, null);
    }
    
    public static AnnotationTag getMarshalerAnnotation(SootClass clazz, soot.Type type) {
        while (!clazz.isPhantom() && clazz.hasSuperclass()) {
            List<AnnotationTag> tags = new ArrayList<AnnotationTag>();
            AnnotationTag marshaler = getAnnotation(clazz, MARSHALER);
            if (marshaler != null) {
                tags.add(marshaler);
            } else {
                AnnotationTag marshalers = getAnnotation(clazz, MARSHALERS);
                if (marshalers != null) {
                    for (AnnotationElem e : ((AnnotationArrayElem) marshalers.getElemAt(0)).getValues()) {
                        AnnotationAnnotationElem elem = (AnnotationAnnotationElem) e;
                        tags.add(elem.getValue());
                    }
                }
            }
            
            for (AnnotationTag tag : tags) {
                AnnotationClassElem elem = (AnnotationClassElem) getElemByName(tag, "type");
                if (elem == null && type == null) {
                    return tag;
                }
                if (elem != null && type != null) {
                    String desc = elem.getDesc();
                    if (getInternalName(type).equals(getInternalNameFromDescriptor(desc))) {
                        return tag;                    
                    }
                }
            }
            
            for (SootClass interfaze : clazz.getInterfaces()) {
                AnnotationTag tag = getMarshalerAnnotation(interfaze, type);
                if (tag != null) {
                    return tag;
                }
            }
            
            clazz = clazz.getSuperclass();
        }
        return null;
    }

    public static AnnotationTag getStructMemberAnnotation(SootMethod method) {
        return getAnnotation(method, STRUCT_MEMBER);
    }
    
    public static boolean hasByValAnnotation(SootClass clazz) {
        while (!clazz.isPhantom() && clazz.hasSuperclass()) {
            if (hasAnnotation(clazz, BY_VAL)) {
                return true;
            }
            if (hasAnnotation(clazz, BY_REF)) {
                return false;
            }
            clazz = clazz.getSuperclass();
        }
        return false;
    }
    
    public static boolean hasByRefAnnotation(SootClass clazz) {
        while (!clazz.isPhantom() && clazz.hasSuperclass()) {
            if (hasAnnotation(clazz, BY_REF)) {
                return true;
            }
            if (hasAnnotation(clazz, BY_VAL)) {
                return false;
            }
            clazz = clazz.getSuperclass();
        }
        return false;
    }
    
    public static AnnotationElem getElemByName(AnnotationTag annotation, String name) {
        for (int i = 0; i < annotation.getNumElems(); i++) {
            AnnotationElem elem = annotation.getElemAt(i);
            if (name.equals(elem.getName())) {
                return elem;
            }
        }
        return null;
    }
}
