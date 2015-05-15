/*
 * Copyright (C) 2015 RoboVM AB
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
package org.robovm.compiler.plugin.objc;

import static org.robovm.compiler.Annotations.*;
import static org.robovm.compiler.Bro.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import org.robovm.compiler.Types;

import soot.PrimType;
import soot.RefType;
import soot.SootClass;
import soot.SootMethod;
import soot.Type;
import soot.VoidType;

/**
 * Encodes Objective-C method signatures as specified by the <a href=
 * "https://developer.apple.com/library/mac/documentation/Cocoa/Conceptual/ObjCRuntimeGuide/Articles/ocrtTypeEncodings.html#//apple_ref/doc/uid/TP40008048-CH100"
 * > Objective-C runtime Type Encodings documentation</a>. For now this just
 * does enough to encode <code>IBOutlet</code>/<code>Property</code> annotated
 * methods that take/return primitives and instances of types deriving from
 * <code></code>.
 */
public class TypeEncoder {
    public static final String SELECTOR = "org.robovm.objc.Selector";
    public static final String OBJC_CLASS = "org.robovm.objc.ObjCClass";

    public String encode(SootMethod method, boolean is64bit) {
        StringBuilder sb = new StringBuilder();
        sb.append(encodeOne(method, method.getReturnType(), -1, is64bit));
        for (int i = 0; i < method.getParameterCount(); i++) {
            sb.append(encodeOne(method, method.getParameterType(i), i, is64bit));
        }
        return sb.toString();
    }

    private boolean hasAnno(SootMethod method, int idx, String annotationType) {
        if (idx == -1) {
            return hasAnnotation(method, annotationType);
        } else {
            return hasParameterAnnotation(method, idx, annotationType);
        }
    }

    private String encodeOne(SootMethod method, Type t, int idx, boolean is64bit) {
        if (t instanceof VoidType) {
            return encodeVoid((VoidType) t);
        }
        if (t instanceof PrimType) {
            return encodePrimitive(method, (PrimType) t, idx, is64bit);
        }
        if (Types.isStruct(method.getDeclaringClass()) && hasAnno(method, idx, ARRAY)) {
            throw new IllegalArgumentException("Cannot not determine type encoding for @Array annotated method "
                    + method + ". @Array is not yet supported. Use an explicit @TypeEncoding annotation instead.");
        }
        if (t instanceof RefType) {
            return encodeRef(method, (RefType) t, idx, is64bit);
        }
        throw new IllegalArgumentException("Unsupported type "
                + t.getClass().getName() + " " + t);
    }

    private String encodeVoid(VoidType t) {
        return "v";
    }

    private String encodeRef(SootMethod method, RefType t, int idx, boolean is64bit) {
        if (SELECTOR.equals(t.getClassName())) {
            return ":";
        }
        if (OBJC_CLASS.equals(t.getClassName())) {
            return "#";
        }
        if (Types.isStruct(t)) {
            if (hasAnno(method, idx, BY_VAL)) {
                // Encode as struct
                return encodeStruct(method, t, idx, is64bit);
            }
            return "^v"; // Encode any type of pointer as void*
        }
        return "@";
    }

    private boolean isUnion(Collection<Member> members) {
        for (Member m : members) {
            if (m.offset > 0) {
                return false;
            }
        }
        return true;
    }

    private String encodeStruct(SootMethod method, RefType t, int idx, boolean is64bit) {
        // We wrap in a TreeSet to filter out duplicate types that could come
        // from getters/setters.
        TreeSet<Member> members = new TreeSet<Member>(getStructMembers(t.getSootClass(), is64bit));
        StringBuilder sb = new StringBuilder();
        boolean union = isUnion(members);
        sb.append(union ? '(' : '{');
        sb.append("?=");
        for (Member m : members) {
            sb.append(m.type);
        }
        sb.append(union ? ')' : '}');
        return sb.toString();
    }

    private String encodePrimitive(SootMethod method, PrimType t, int idx, boolean is64bit) {
        if (t.equals(soot.BooleanType.v())) {
            return "c";
        } else if (t.equals(soot.ByteType.v())) {
            return "c";
        } else if (t.equals(soot.ShortType.v())) {
            return "s";
        } else if (t.equals(soot.CharType.v())) {
            return "S";
        } else if (t.equals(soot.IntType.v())) {
            return "i";
        } else if (t.equals(soot.LongType.v())) {
            if (hasAnno(method, idx, POINTER)) {
                return "^v"; // void*
            }
            if (hasAnno(method, idx, MACHINE_SIZED_S_INT)) {
                return is64bit ? "q" : "i";
            }
            if (hasAnno(method, idx, MACHINE_SIZED_U_INT)) {
                return is64bit ? "Q" : "I";
            }
            return "q";
        } else if (t.equals(soot.FloatType.v())) {
            if (hasAnno(method, idx, MACHINE_SIZED_FLOAT)) {
                return is64bit ? "d" : "f";
            }
            return "f";
        } else if (t.equals(soot.DoubleType.v())) {
            if (hasAnno(method, idx, MACHINE_SIZED_FLOAT)) {
                return is64bit ? "d" : "f";
            }
            return "d";
        } else {
            throw new IllegalArgumentException("Unknown Type: " + t);
        }
    }

    static class Member implements Comparable<Member> {
        int offset;
        String type;

        @Override
        public int compareTo(Member o) {
            int c = Integer.compare(this.offset, o.offset);
            if (c == 0) {
                c = type.compareTo(o.type);
            }
            return c;
        }

        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + offset;
            result = prime * result + ((type == null) ? 0 : type.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Member other = (Member) obj;
            if (offset != other.offset) {
                return false;
            }
            if (type == null) {
                if (other.type != null) {
                    return false;
                }
            } else if (!type.equals(other.type)) {
                return false;
            }
            return true;
        }
    }

    private List<Member> getStructMembers(SootClass clazz, boolean is64bit) {
        List<Member> members = new ArrayList<>();

        if (clazz.hasSuperclass()) {
            SootClass superclass = clazz.getSuperclass();
            if (!superclass.getName().equals("org.robovm.rt.bro.Struct")) {
                members.addAll(getStructMembers(clazz, is64bit));
            }
        }

        for (SootMethod method : clazz.getMethods()) {
            int offset = getStructMemberOffset(method);
            if (offset != -1) {
                if (!method.isNative() && !method.isStatic()) {
                    // Invalid struct member. Just ignore. An exception will be
                    // thrown by the ClassCompiler later on.
                    continue;
                }
                Type type;
                int idx;
                if (method.getParameterCount() == 0) {
                    type = method.getReturnType();
                    idx = -1;
                } else if (method.getParameterCount() == 1) {
                    type = method.getParameterType(0);
                    idx = 0;
                } else {
                    // Invalid struct member. Just ignore. An exception will be
                    // throw by the ClassCompiler later on.
                    continue;
                }

                Member member = new Member();
                member.offset = offset;
                member.type = encodeOne(method, type, idx, is64bit);
                members.add(member);
            }
        }

        return members;
    }
}
