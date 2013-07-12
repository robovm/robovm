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

import static org.robovm.compiler.Types.*;

import java.util.List;

import soot.SootClass;
import soot.SootField;
import soot.SootFieldRef;
import soot.SootMethod;
import soot.SootMethodRef;

/**
 * @author niklas
 *
 */
public class Mangler {
    private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();

    public static String mangleClass(SootClass clazz) {
        return mangleString(getInternalName(clazz));
    }
    
    public static String mangleClass(String internalName) {
        return mangleString(internalName);
    }
    
    public static String mangleMethod(SootMethod method) {
        return mangleMethod(method.makeRef());
    }
    
    @SuppressWarnings("unchecked")
    public static String mangleMethod(SootMethodRef methodRef) {
        return mangleMethod(getInternalName(methodRef.declaringClass()), methodRef.name(), 
                methodRef.parameterTypes(), methodRef.returnType());
    }
    
    public static String mangleMethod(String owner, String name, List<soot.Type> parameterTypes, soot.Type returnType) {
        StringBuilder sb = new StringBuilder();
        sb.append(mangleString(owner));
        sb.append("_");
        sb.append(mangleString(name));
        if (!parameterTypes.isEmpty()) {
            sb.append("__");
            for (soot.Type parameterType : parameterTypes) {
                sb.append(mangleString(getDescriptor(parameterType)));
            }
        }
        sb.append("__");
        sb.append(mangleString(getDescriptor(returnType)));
        return sb.toString();
    }
    
    public static String mangleMethod(String owner, String name, String desc) {
        StringBuilder sb = new StringBuilder();
        sb.append(mangleString(owner));
        sb.append("_");
        sb.append(mangleString(name));
        if (!desc.startsWith("()")) {
            sb.append("__");
            sb.append(mangleString(desc.substring(1, desc.indexOf(')'))));
        }
        sb.append("__");
        sb.append(mangleString(desc.substring(desc.indexOf(')') + 1)));
        return sb.toString();
    }
    
    public static String mangleNativeMethod(String owner, String name) {
        return mangleNativeMethod(owner, name, null);
    }
    
    public static String mangleNativeMethod(String owner, String name, String desc) {
        StringBuilder sb = new StringBuilder();
        sb.append("Java_");
        sb.append(mangleNativeString(owner));
        sb.append("_");
        sb.append(mangleNativeString(name));
        if (desc != null && !desc.startsWith("()")) {
            sb.append("__");
            sb.append(mangleNativeString(desc.substring(1, desc.lastIndexOf(')'))));
        }
        return sb.toString();
    }
    
    public static String mangleNativeString(String name) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
                sb.append(c);
            } else if (c == '_') {
                sb.append("_1");
            } else if (c == ';') {
                sb.append("_2");
            } else if (c == '[') {
                sb.append("_3");
            } else if (c == '/') {
                sb.append("_");
            } else {
                sb.append(String.format("_0%04x", (int) c));
            }
        }
        return sb.toString();
    }

    public static String mangleField(SootField field) {
        return mangleField(getInternalName(field.getDeclaringClass()), field.getName(), field.getType());
    }
    
    public static String mangleField(SootFieldRef field) {
        return mangleField(getInternalName(field.declaringClass()), field.name(), field.type());
    }
    
    public static String mangleField(String owner, String name, soot.Type type) {
        return mangleField(owner, name, getDescriptor(type));
    }
    
    public static String mangleField(String owner, String name, String desc) {
        StringBuilder sb = new StringBuilder();
        sb.append(mangleString(owner));
        sb.append("_");
        sb.append(mangleString(name));
        sb.append("__");
        sb.append(mangleString(desc));
        return sb.toString();
    }
    
    public static String mangleString(String name) {
        return mangleModifiedUtf8(Strings.stringToModifiedUtf8(name));
    }
    
    public static String mangleModifiedUtf8(byte[] s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            byte c = s[i];
            if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
                sb.append((char) c);
            } else if (c == '/') {
                sb.append('_');
            } else {
                sb.append('$');
                sb.append(HEX_CHARS[(c >> 4) & 0xf]);
                sb.append(HEX_CHARS[c & 0xf]);
            }
        }
        return sb.toString();
    }
    
}
