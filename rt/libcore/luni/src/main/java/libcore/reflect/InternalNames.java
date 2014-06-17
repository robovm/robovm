/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.reflect;

import java.lang.reflect.Array;

/**
 * Work with a type's internal name like "V" or "Ljava/lang/String;".
 */
public final class InternalNames {
    private InternalNames() {
    }

    public static Class<?> getClass(ClassLoader classLoader, String internalName) {
        if (internalName.startsWith("[")) {
            Class<?> componentClass = getClass(classLoader, internalName.substring(1));
            return Array.newInstance(componentClass, 0).getClass();
        } else if (internalName.equals("Z")) {
            return boolean.class;
        } else if (internalName.equals("B")) {
            return byte.class;
        } else if (internalName.equals("S")) {
            return short.class;
        } else if (internalName.equals("I")) {
            return int.class;
        } else if (internalName.equals("J")) {
            return long.class;
        } else if (internalName.equals("F")) {
            return float.class;
        } else if (internalName.equals("D")) {
            return double.class;
        } else if (internalName.equals("C")) {
            return char.class;
        } else if (internalName.equals("V")) {
            return void.class;
        } else {
            String name = internalName.substring(1, internalName.length() - 1).replace('/', '.');
            try {
                return classLoader.loadClass(name);
            } catch (ClassNotFoundException e) {
                NoClassDefFoundError error = new NoClassDefFoundError(name);
                error.initCause(e);
                throw error;
            }
        }
    }

    public static String getInternalName(Class<?> c) {
        if (c.isArray()) {
            return '[' + getInternalName(c.getComponentType());
        } else if (c == boolean.class) {
            return "Z";
        } else if (c == byte.class) {
            return "B";
        } else if (c == short.class) {
            return "S";
        } else if (c == int.class) {
            return "I";
        } else if (c == long.class) {
            return "J";
        } else if (c == float.class) {
            return "F";
        } else if (c == double.class) {
            return "D";
        } else if (c == char.class) {
            return "C";
        } else if (c == void.class) {
            return "V";
        } else {
            return 'L' + c.getName().replace('.', '/') + ';';
        }
    }
}
