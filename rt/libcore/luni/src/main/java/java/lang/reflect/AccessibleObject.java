/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Copyright (C) 2008 The Android Open Source Project
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
/*
 * Copyright (C) 2012 Trillian AB
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

package java.lang.reflect;

import java.lang.annotation.Annotation;
import java.util.Hashtable;
import org.robovm.rt.ReflectionAccess;

/**
 * {@code AccessibleObject} is the superclass of all member reflection classes
 * (Field, Constructor, Method). AccessibleObject provides the ability to toggle
 * a flag controlling access checks for these objects. By default, accessing a
 * member (for example, setting a field or invoking a method) checks the
 * validity of the access (for example, invoking a private method from outside
 * the defining class is prohibited) and throws IllegalAccessException if the
 * operation is not permitted. If the accessible flag is set to true, these
 * checks are omitted. This allows privileged code, such as Java object
 * serialization, object inspectors, and debuggers to have complete access to
 * objects.
 *
 * @see Field
 * @see Constructor
 * @see Method
 */
public class AccessibleObject implements AnnotatedElement {
    static final ReflectionAccess REFLECTION_ACCESS = new ReflectionAccess() {
        
        public Method[] clone(Method[] m) {
            if (m.length == 0) {
                return m;
            }
            Method[] result = new Method[m.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = new Method(m[i]);
            }
            return result;
        }
        
        @SuppressWarnings({ "unchecked", "rawtypes" })
        public Constructor<?>[] clone(Constructor<?>[] c) {
            if (c.length == 0) {
                return c;
            }
            Constructor[] result = new Constructor[c.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = new Constructor(c[i]);
            }
            return result;
        }
        
        public Field[] clone(Field[] f) {
            if (f.length == 0) {
                return f;
            }
            Field[] result = new Field[f.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = new Field(f[i]);
            }
            return result;
        }
        
        public Method clone(Method m) {
            return new Method(m);
        }
        
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public Constructor<?> clone(Constructor<?> c) {
            return new Constructor(c);
        }
        
        public Field clone(Field f) {
            return new Field(f);
        }
        
        public boolean equals(Method a, Method b) {
            if (a.getReturnType() != b.getReturnType()) {
                return false;
            }
            if (!a.getName().equals(b.getName())) {
                return false;
            }
            return compareClassLists(a.getParameterTypes(false), b.getParameterTypes(false));
        }
        
        public boolean matchParameterTypes(Constructor<?> c, Class<?>[] parameterTypes) {
            return compareClassLists(c.getParameterTypes(false), parameterTypes);
        }

        public boolean matchParameterTypes(Method m, Class<?>[] parameterTypes) {
            return compareClassLists(m.getParameterTypes(false), parameterTypes);
        }
        
        private boolean compareClassLists(Class<?>[] a, Class<?>[] b) {
            // Copied from Android's ClassCache.java
            // Start (C) Android
            if (a == null) {
                return (b == null) || (b.length == 0);
            }

            int length = a.length;

            if (b == null) {
                return (length == 0);
            }

            if (length != b.length) {
                return false;
            }

            for (int i = length - 1; i >= 0; i--) {
                if (a[i] != b[i]) {
                    return false;
                }
            }

            return true;
            // End (C) Android
        }        
    };
    
    // If true, object is accessible, bypassing normal access checks
    boolean flag = false;
        
    // Holds a mapping from Java type names to native type codes.
    static Hashtable<String, String> trans;
        
    static {
        trans = new Hashtable<String, String>(9);
        trans.put("byte", "B");
        trans.put("char", "C");
        trans.put("short", "S");
        trans.put("int", "I");
        trans.put("long", "J");
        trans.put("float", "F");
        trans.put("double", "D");
        trans.put("void", "V");
        trans.put("boolean", "Z");
            }

    /**
     * Attempts to set the value of the accessible flag for all the objects in
     * the array provided. Setting this
     * flag to {@code false} will enable access checks, setting to {@code true}
     * will disable them.
     *
     * @param objects
     *            the accessible objects
     * @param flag
     *            the new value for the accessible flag
     *
     * @see #setAccessible(boolean)
     */
    public static void setAccessible(AccessibleObject[] objects, boolean flag) {
        synchronized(AccessibleObject.class) {
            for (AccessibleObject object : objects) {
                object.flag = flag;
            }
        }
    }

    /**
     * Constructs a new {@code AccessibleObject} instance. {@code
     * AccessibleObject} instances can only be constructed by the virtual
     * machine.
     */
    protected AccessibleObject() {
    }

    /**
     * Indicates whether this object is accessible without access checks being
     * performed. Returns the accessible flag.
     *
     * @return {@code true} if this object is accessible without access
     *         checks, {@code false} otherwise
     */
    public boolean isAccessible() {
        return flag;
    }

    /**
     * Attempts to set the value of the accessible flag. Setting this flag to
     * {@code false} will enable access checks, setting to {@code true} will
     * disable them.
     *
     * @param flag
     *            the new value for the accessible flag
     */
    public void setAccessible(boolean flag) {
        this.flag = flag;
    }

    public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
        if (annotationType == null) {
            throw new NullPointerException("annotationType == null");
        }
        return getAnnotation(annotationType) != null;
    }
    
    public Annotation[] getDeclaredAnnotations() {
        throw new UnsupportedOperationException();
    }

    public Annotation[] getAnnotations() {
        // for all but Class, getAnnotations == getDeclaredAnnotations
        return getDeclaredAnnotations();
    }

    /* slow, but works for all sub-classes */
    public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
        if (annotationType == null) {
            throw new NullPointerException("annotationType == null");
        }
        Annotation[] annos = getAnnotations();
        for (int i = annos.length-1; i >= 0; --i) {
            if (annos[i].annotationType() == annotationType) {
                return (T) annos[i];
            }
        }
        return null;
    }

    /**
     * Returns the signature for a class. This is the kind of signature used
     * internally by the JVM, with one-character codes representing the basic
     * types. It is not suitable for printing.
     *
     * @param clazz
     *            the class for which a signature is required
     *
     * @return The signature as a string
     */
    String getSignature(Class<?> clazz) {
        String result = "";
        String nextType = clazz.getName();

        if(trans.containsKey(nextType)) {
            result = trans.get(nextType);
        } else {
            if(clazz.isArray()) {
                result = "[" + getSignature(clazz.getComponentType());
            } else {
                result = "L" + nextType + ";";
            }
        }
        return result;
    }

    /**
     * Returns a printable String consisting of the canonical names of the
     * classes contained in an array. The form is that used in parameter and
     * exception lists, that is, the class or type names are separated by
     * commas.
     *
     * @param types
     *            the array of classes
     *
     * @return The String of names
     */
    String toString(Class<?>[] types) {
        StringBuilder result = new StringBuilder();

        if (types.length != 0) {
            appendTypeName(result, types[0]);
            for (int i = 1; i < types.length; i++) {
                result.append(',');
                appendTypeName(result, types[i]);
            }
        }

        return result.toString();
    }

    /**
     * Gets the Signature attribute for this instance. Returns {@code null}
     * if not found.
     */
    /*package*/ String getSignatureAttribute() {
        /*
         * Note: This method would have been declared abstract, but the
         * standard API lists this class as concrete.
         */
        throw new UnsupportedOperationException();
    }

    static boolean isSamePackage(Class<?> c1, Class<?> c2) {
        if (c1 == c2) {
            return true;
        }
        if (c1.getClassLoader() != c2.getClassLoader()) {
            return false;
        }
        String n1 = c1.getName();
        String n2 = c2.getName();
        int dot1 = n1.lastIndexOf('.');
        int dot2 = n2.lastIndexOf('.');
        if (dot1 == -1) {
            return dot2 == -1;
        }
        if (dot1 != dot2) {
            return false;
        }
        return n1.substring(0, dot1).equals(n2.substring(0, dot2));
    }

    /**
     * Returns the access flags for the specified {@link Class} without checking the InnerClasses
     * attribute for inner classes. This is described in this bug report:
     * http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4471811
     */
    static native int getAccessFlags(Class<?> cls);

    static boolean checkAccessibleFast(Member member) {
        int access = member.getModifiers();
        return (getAccessFlags(member.getDeclaringClass()) & access & Modifier.PUBLIC) > 0;
    }
    
    static boolean checkAccessible(Class<?> caller, Member member) {
        Class<?> callee = member.getDeclaringClass();
        if (caller == callee) {
            return true;
        }
        
        boolean samePackage = false;
        
        // Check if callee class is accessible
        if ((getAccessFlags(callee) & Modifier.PUBLIC) == 0) {
            samePackage = isSamePackage(caller, callee);
            if (!samePackage) {
                return false;
            }
        }
        
        // Check member accessible
        int access = member.getModifiers();
        if ((access & Modifier.PUBLIC) > 0) {
            return true;
        }
        if ((access & Modifier.PRIVATE) > 0) {
            return false;
        }
        if ((access & Modifier.PROTECTED) > 0) {
            Class<?> sc = caller.getSuperclass();
            while (sc != null) {
                if (sc == callee) {
                    return true;
                }
                sc = sc.getSuperclass();
            }
        }
        return samePackage || isSamePackage(caller, callee);
    }
    
    /**
     * Appends the best {@link #toString} name for {@code c} to {@code out}.
     * This works around the fact that {@link Class#getName} is lousy for
     * primitive arrays (it writes "[C" instead of "char[]") and {@link
     * Class#getCanonicalName()} is lousy for nested classes (it uses a "."
     * separator rather than a "$" separator).
     */
    void appendTypeName(StringBuilder out, Class<?> c) {
        int dimensions = 0;
        while (c.isArray()) {
            c = c.getComponentType();
            dimensions++;
        }
        out.append(c.getName());
        for (int d = 0; d < dimensions; d++) {
            out.append("[]");
        }
    }

    /**
     * Appends names of the specified array classes to the buffer. The array
     * elements may represent a simple type, a reference type or an array type.
     * Output format: java.lang.Object[], java.io.File, void
     *
     * @param types array of classes to print the names
     * @throws NullPointerException if any of the arguments is null
     */
    void appendArrayGenericType(StringBuilder sb, Type[] types) {
        if (types.length > 0) {
            appendGenericType(sb, types[0]);
            for (int i = 1; i < types.length; i++) {
                sb.append(',');
                appendGenericType(sb, types[i]);
            }
        }
    }

    /**
     * Appends the generic type representation to the buffer.
     *
     * @param sb buffer
     * @param obj the generic type which representation should be appended to the buffer
     *
     * @throws NullPointerException if any of the arguments is null
     */
    void appendGenericType(StringBuilder sb, Type obj) {
        if (obj instanceof TypeVariable) {
            sb.append(((TypeVariable)obj).getName());
        } else if (obj instanceof ParameterizedType) {
            sb.append(obj.toString());
        } else if (obj instanceof GenericArrayType) { //XXX: is it a working branch?
            Type simplified = ((GenericArrayType)obj).getGenericComponentType();
            appendGenericType(sb, simplified);
            sb.append("[]");
        } else if (obj instanceof Class) {
            Class c = ((Class<?>)obj);
            if (c.isArray()){
                String as[] = c.getName().split("\\[");
                int len = as.length-1;
                if (as[len].length() > 1){
                    sb.append(as[len].substring(1, as[len].length()-1));
                } else {
                    char ch = as[len].charAt(0);
                    if (ch == 'I')
                        sb.append("int");
                    else if (ch == 'B')
                        sb.append("byte");
                    else if (ch == 'J')
                        sb.append("long");
                    else if (ch == 'F')
                        sb.append("float");
                    else if (ch == 'D')
                        sb.append("double");
                    else if (ch == 'S')
                        sb.append("short");
                    else if (ch == 'C')
                        sb.append("char");
                    else if (ch == 'Z')
                        sb.append("boolean");
                    else if (ch == 'V') //XXX: is it a working branch?
                        sb.append("void");
                }
                for (int i = 0; i < len; i++){
                    sb.append("[]");
                }
            } else {
                sb.append(c.getName());
            }
        }
    }
}
