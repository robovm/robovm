/*
 * Copyright (C) 2012 RoboVM
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
package java.lang;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.robovm.rt.ReflectionAccess;

/**
 *
 * @version $Id$
 */
class ClassCache<T> {
    static final ReflectionAccess R = loadReflectionAccess();
    
    private static final Class<?>[] EMPTY_CLASSES = new Class<?>[0];
    private static final Constructor<?>[] EMPTY_CONSTRUCTORS = new Constructor<?>[0];
    private static final Method[] EMPTY_METHODS = new Method[0];
    private static final Field[] EMPTY_FIELDS = new Field[0];    
    
    private final Class<T> clazz;
    
    private String name;
    private Class<?>[] declaredClasses;
    private Class<?>[] declaredPublicClasses;
    private Class<?>[] allPublicClasses;
    private Field[] declaredFields;
    private Field[] declaredPublicFields;
    private Field[] allPublicFields;
    private Constructor<?>[] declaredConstructors;
    private Constructor<?>[] declaredPublicConstructors;
    private Method[] declaredMethods;
    private Method[] declaredPublicMethods;
    private Method[] allPublicMethods;
    
    ClassCache(Class<T> clazz) {
        this.clazz = clazz;
    }

    String getName() {
        if (name == null) {
            String n = clazz.getName0();
            if (clazz.isPrimitive()) {
                switch (n.charAt(0)) {
                case 'Z':
                    name = "boolean";
                    break;
                case 'B':
                    name = "byte";
                    break;
                case 'S':
                    name = "short";
                    break;
                case 'C':
                    name = "char";
                    break;
                case 'I':
                    name = "int";
                    break;
                case 'J':
                    name = "long";
                    break;
                case 'F':
                    name = "float";
                    break;
                case 'D':
                    name = "double";
                    break;
                case 'V':
                    name = "void";
                    break;
                }
            } else {
                name = n.replace('/', '.');
            }
        }
        return name;
    }
    
    Class<?>[] getDeclaredClasses(boolean copy) {
        if (declaredClasses == null) {
            declaredClasses = clazz.getDeclaredClasses0(false);
            if (declaredClasses == null) {
                declaredClasses = EMPTY_CLASSES;
            }
        }
        return copy ? declaredClasses.clone() : declaredClasses;
    }
    
    Class<?>[] getDeclaredPublicClasses(boolean copy) {
        if (declaredPublicClasses == null) {
            declaredPublicClasses = clazz.getDeclaredClasses0(true);
            if (declaredPublicClasses == null) {
                declaredPublicClasses = EMPTY_CLASSES;
            }
        }
        return copy ? declaredPublicClasses.clone() : declaredPublicClasses;
    }
    
    Class<?>[] getDeclaredClasses(boolean copy, boolean publicOnly) {
        if (publicOnly) {
            return getDeclaredPublicClasses(copy);
        }
        return getDeclaredClasses(copy);
    }
    
    Field[] getDeclaredFields(boolean copy) {
        if (declaredFields == null) {
            declaredFields = clazz.getDeclaredFields0(false);
            if (declaredFields == null) {
                declaredFields = EMPTY_FIELDS;
            }
        }
        return copy ? R.clone(declaredFields) : declaredFields;
    }

    Field[] getDeclaredPublicFields(boolean copy) {
        if (declaredPublicFields == null) {
            declaredPublicFields = clazz.getDeclaredFields0(true);
            if (declaredPublicFields == null) {
                declaredPublicFields = EMPTY_FIELDS;
            }
        }
        return copy ? R.clone(declaredPublicFields) : declaredPublicFields;
    }

    Field[] getDeclaredFields(boolean copy, boolean publicOnly) {
        if (publicOnly) {
            return getDeclaredPublicFields(copy);
        }
        return getDeclaredFields(copy);
    }
    
    Constructor<?>[] getDeclaredConstructors(boolean copy) {
        if (declaredConstructors == null) {
            declaredConstructors = clazz.getDeclaredConstructors0(false);
            if (declaredConstructors == null) {
                declaredConstructors = EMPTY_CONSTRUCTORS;
            }
        }
        return copy ? R.clone(declaredConstructors) : declaredConstructors;
    }

    Constructor<?>[] getDeclaredPublicConstructors(boolean copy) {
        if (declaredPublicConstructors == null) {
            declaredPublicConstructors = clazz.getDeclaredConstructors0(true);
            if (declaredPublicConstructors == null) {
                declaredPublicConstructors = EMPTY_CONSTRUCTORS;
            }
        }
        return copy ? R.clone(declaredPublicConstructors) : declaredPublicConstructors;
    }

    Method[] getDeclaredMethods(boolean copy) {
        if (declaredMethods == null) {
            declaredMethods = clazz.getDeclaredMethods0(false);
            if (declaredMethods == null) {
                declaredMethods = EMPTY_METHODS;
            }
        }
        return copy ? R.clone(declaredMethods) : declaredMethods;
    }

    Method[] getDeclaredPublicMethods(boolean copy) {
        if (declaredPublicMethods == null) {
            declaredPublicMethods = clazz.getDeclaredMethods0(true);
            if (declaredPublicMethods == null) {
                declaredPublicMethods = EMPTY_METHODS;
            }
        }
        return copy ? R.clone(declaredPublicMethods) : declaredPublicMethods;
    }

    Method[] getDeclaredMethods(boolean copy, boolean publicOnly) {
        if (publicOnly) {
            return getDeclaredPublicMethods(copy);
        }
        return getDeclaredMethods(copy);
    }
    
    Class<?>[] getClasses(boolean copy) {
        if (this.allPublicClasses == null) {
            List<Class<?>> l = buildClassesList(new ArrayList<Class<?>>(), new HashSet<String>(), true);
            this.allPublicClasses = l.toArray(new Class<?>[l.size()]);
        }
        return copy ? this.allPublicClasses.clone() : this.allPublicClasses;
    }
    
    Field[] getFields(boolean copy) {
        if (this.allPublicFields == null) {
            List<Field> l = buildFieldsList(new ArrayList<Field>(), new HashSet<String>(), true);
            this.allPublicFields = l.toArray(new Field[l.size()]);
        }
        return copy ? R.clone(this.allPublicFields) : this.allPublicFields;
    }
    
    Method[] getMethods(boolean copy) {
        if (this.allPublicMethods == null) {
            List<Method> l = buildMethodsList(new ArrayList<Method>(), new HashSet<MethodHashKey>(), true);
            this.allPublicMethods = l.toArray(new Method[l.size()]);
        }
        return copy ? R.clone(this.allPublicMethods) : this.allPublicMethods;
    }
    
    @SuppressWarnings("unchecked")
    Constructor<T> getDeclaredConstructor(boolean copy, Class<?>... parameterTypes) throws java.lang.NoSuchMethodException {
        Constructor<T> c = findConstructor(getDeclaredConstructors(false), parameterTypes);
        return copy ? (Constructor<T>) R.clone(c) : c;
    }

    @SuppressWarnings("unchecked")
    Constructor<T> getConstructor(boolean copy, Class<?>... parameterTypes) throws java.lang.NoSuchMethodException {
        Constructor<T> c = findConstructor(getDeclaredPublicConstructors(false), parameterTypes);
        return copy ? (Constructor<T>) R.clone(c) : c;
    }

    Field getDeclaredField(boolean copy, String name) throws NoSuchFieldException {
        Field f = findField(getDeclaredFields(false), name);
        return copy ? R.clone(f) : f;
    }

    Field getField(boolean copy, String name) throws NoSuchFieldException {
        Field f = findField(getFields(false), name);
        return copy ? R.clone(f) : f;
    }
    
    Method getDeclaredMethod(boolean copy, String name, Class<?>... parameterTypes) throws NoSuchMethodException {
        Method m = findMethod(getDeclaredMethods(false), name, parameterTypes);
        return copy ? R.clone(m) : m;
    }
    
    Method getMethod(boolean copy, String name, Class<?>... parameterTypes) throws NoSuchMethodException {
        Method m = findMethod(getMethods(false), name, parameterTypes);
        return copy ? R.clone(m) : m;
    }

    private List<Class<?>> buildClassesList(List<Class<?>> result, Set<String> seen, boolean publicOnly) {
        for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
            for (Class<?> f : c.getClassCache().getDeclaredClasses(false, publicOnly)) {
                String s = f.toString();
                if (!seen.contains(s)) {
                    result.add(f);
                    seen.add(s);
                }
            }
            for (Class<?> intf : c.getInterfaces()) {
                intf.getClassCache().buildClassesList(result, seen, publicOnly);
            }
        }
        return result;
    }
    
    private List<Field> buildFieldsList(List<Field> result, Set<String> seen, boolean publicOnly) {
        for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
            for (Field f : c.getClassCache().getDeclaredFields(false, publicOnly)) {
                String s = f.toString();
                if (!seen.contains(s)) {
                    result.add(f);
                    seen.add(s);
                }
            }
            for (Class<?> intf : c.getInterfaces()) {
                intf.getClassCache().buildFieldsList(result, seen, publicOnly);
            }
        }
        return result;
    }

    private List<Method> buildMethodsList(List<Method> result, Set<MethodHashKey> seen, boolean publicOnly) {
        for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
            for (Method m : c.getClassCache().getDeclaredMethods(false, publicOnly)) {
                MethodHashKey key = new MethodHashKey(m);
                if (!seen.contains(key)) {
                    result.add(m);
                    seen.add(key);
                }
            }
        }
        
        for (Class<?> c = clazz; c != null; c = c.getSuperclass()) {
            for (Class<?> intf : c.getInterfaces()) {
                intf.getClassCache().buildMethodsList(result, seen, publicOnly);
            }
        }
        return result;
    }
    
    @SuppressWarnings("unchecked")
    private Constructor<T> findConstructor(Constructor<?>[] candidates, Class<?>... parameterTypes)
            throws NoSuchMethodException, SecurityException {
        
        for (Constructor<?> c : candidates) {
            if (R.matchParameterTypes(c, parameterTypes)) {
                return (Constructor<T>) c;
            }
        }
        throw new NoSuchMethodException(clazz.getName() + '(' 
                + parameterTypesToString(parameterTypes) + ')');
    }

    private Method findMethod(Method[] candidates, String name, Class<?>... parameterTypes)
            throws NoSuchMethodException {
        
        for (Method m : candidates) {
            if (name.equals(m.getName()) && R.matchParameterTypes(m, parameterTypes)) {
                return m;
            }
        }
        throw new NoSuchMethodException(clazz.getName() + "." + name + '(' 
                + parameterTypesToString(parameterTypes) + ')');
    }

    private Field findField(Field[] candidates, String name) throws NoSuchFieldException {
        for (Field f : candidates) {
            if (name.equals(f.getName())) {
                return f;
            }
        }
        throw new NoSuchFieldException(name);
    }
    
    private static String parameterTypesToString(Class<?>[] parameterTypes) {
        if (parameterTypes != null && parameterTypes.length > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(parameterTypes[0].getCanonicalName());
            for (int i = 1; i < parameterTypes.length; i++) {
                sb.append(',');
                sb.append(parameterTypes[i] == null ? "null" 
                        : parameterTypes[i].getCanonicalName());
            }
            return sb.toString();
        }
        return "";
    }

    private static final native ReflectionAccess loadReflectionAccess();

    private static final class MethodHashKey {
        private final Method m;
        MethodHashKey(Method m) {
            this.m = m;
        }
        @Override
        public int hashCode() {
            return m.getName().hashCode();
        }
        @Override
        public boolean equals(Object o) {
            Method m1 = m;
            Method m2 = ((MethodHashKey) o).m;
            return R.equals(m1, m2);
        }
    }
}
