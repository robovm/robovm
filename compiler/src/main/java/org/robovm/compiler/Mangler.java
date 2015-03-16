/*
 * Copyright (C) 2012 RoboVM AB
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


/**
 * Mangles native method signatures into short and long JNI function names.
 * See {@link http://docs.oracle.com/javase/8/docs/technotes/guides/jni/spec/design.html#resolving_native_method_names}
 * for how a VM resolves JNI functions.
 */
public class Mangler {

    /**
     * Returns the short version of the JNI function name for a method.
     * 
     * @param owner the internal name of the class of the method.
     * @param name the name of the method.
     */
    public static String mangleNativeMethod(String owner, String name) {
        return mangleNativeMethod(owner, name, null);
    }
    
    /**
     * Returns the long version of the JNI function name for a method.
     * 
     * @param owner the internal name of the class of the method.
     * @param name the name of the method.
     * @param desc the method descriptor.
     */
    public static String mangleNativeMethod(String owner, String name, String desc) {
        StringBuilder sb = new StringBuilder();
        sb.append("Java_");
        sb.append(mangleNativeString(owner));
        sb.append("_");
        sb.append(mangleNativeString(name));
        if (desc != null) {
            sb.append("__");
            sb.append(mangleNativeString(desc.substring(1, desc.lastIndexOf(')'))));
        }
        return sb.toString();
    }
    
    static String mangleNativeString(String name) {
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
    
}
