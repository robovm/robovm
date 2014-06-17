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
 * Copyright (C) 2006-2007 The Android Open Source Project
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
 * Copyright (C) 2012 Trillian Mobile AB
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

import java.io.InputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.ref.SoftReference;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.net.URL;
import java.security.ProtectionDomain;
import java.util.Collection;
import java.util.HashMap;

import libcore.reflect.GenericSignatureParser;
import libcore.reflect.Types;
import dalvik.system.VMStack;

/**
 * The in-memory representation of a Java class. This representation serves as
 * the starting point for querying class-related information, a process usually
 * called "reflection". There are basically three types of {@code Class}
 * instances: those representing real classes and interfaces, those representing
 * primitive types, and those representing array classes.
 *
 * <h4>Class instances representing object types (classes or interfaces)</h4>
 * <p>
 * These represent an ordinary class or interface as found in the class
 * hierarchy. The name associated with these {@code Class} instances is simply
 * the fully qualified class name of the class or interface that it represents.
 * In addition to this human-readable name, each class is also associated by a
 * so-called <em>signature</em>, which is the letter "L", followed by the
 * class name and a semicolon (";"). The signature is what the runtime system
 * uses internally for identifying the class (for example in a DEX file).
 * </p>
 * <h4>Classes representing primitive types</h4>
 * <p>
 * These represent the standard Java primitive types and hence share their
 * names (for example "int" for the {@code int} primitive type). Although it is
 * not possible to create new instances based on these {@code Class} instances,
 * they are still useful for providing reflection information, and as the
 * component type of array classes. There is one {@code Class} instance for each
 * primitive type, and their signatures are:
 * </p>
 * <ul>
 * <li>{@code B} representing the {@code byte} primitive type</li>
 * <li>{@code S} representing the {@code short} primitive type</li>
 * <li>{@code I} representing the {@code int} primitive type</li>
 * <li>{@code J} representing the {@code long} primitive type</li>
 * <li>{@code F} representing the {@code float} primitive type</li>
 * <li>{@code D} representing the {@code double} primitive type</li>
 * <li>{@code C} representing the {@code char} primitive type</li>
 * <li>{@code Z} representing the {@code boolean} primitive type</li>
 * <li>{@code V} representing void function return values</li>
 * </ul>
 * <p>
 * <h4>Classes representing array classes</h4>
 * <p>
 * These represent the classes of Java arrays. There is one such {@code Class}
 * instance per combination of array leaf component type and arity (number of
 * dimensions). In this case, the name associated with the {@code Class}
 * consists of one or more left square brackets (one per dimension in the array)
 * followed by the signature of the class representing the leaf component type,
 * which can be either an object type or a primitive type. The signature of a
 * {@code Class} representing an array type is the same as its name. Examples
 * of array class signatures are:
 * </p>
 * <ul>
 * <li>{@code [I} representing the {@code int[]} type</li>
 * <li>{@code [Ljava/lang/String;} representing the {@code String[]} type</li>
 * <li>{@code [[[C} representing the {@code char[][][]} type (three dimensions!)</li>
 * </ul>
 */
public final class Class<T> implements Serializable, AnnotatedElement, GenericDeclaration, Type {

    private static final long serialVersionUID = 3206093459760846163L;

    private SoftReference<ClassCache<T>> cacheRef;

    private Class() {
        // Prevent this class to be instantiated, instance
        // should be created by JVM only
    }

    ClassCache<T> getClassCache() {
        ClassCache<T> cache = cacheRef != null ? cacheRef.get() : null;
        if (cache == null) {
            cache = new ClassCache<T>(this);
            cacheRef = new SoftReference<ClassCache<T>>(cache);
        }
        return cache;
    }
    
    /**
     * Get the Signature attribute for this class.  Returns null if not found.
     */
    private native String getSignatureAttribute();    

    /**
     * Returns a {@code Class} object which represents the class with the
     * given name. The name should be the name of a non-primitive class, as described in
     * the {@link Class class definition}.
     * Primitive types can not be found using this method; use {@code int.class} or {@code Integer.TYPE} instead.
     *
     * <p>If the class has not yet been loaded, it is loaded and initialized
     * first. This is done through either the class loader of the calling class
     * or one of its parent class loaders. It is possible that a static initializer is run as
     * a result of this call.
     *
     * @param className
     *            the name of the non-primitive-type class to find.
     * @return the named {@code Class} instance.
     * @throws ClassNotFoundException
     *             if the requested class can not be found.
     * @throws LinkageError
     *             if an error occurs during linkage
     * @throws ExceptionInInitializerError
     *             if an exception occurs during static initialization of a
     *             class.
     */
    public static Class<?> forName(String className) throws ClassNotFoundException {
        return forName(className, true, VMStack.getCallingClassLoader());
    }

    /**
     * Returns a {@code Class} object which represents the class with the
     * given name. The name should be the name of a non-primitive class, as described in
     * the {@link Class class definition}.
     * Primitive types can not be found using this method; use {@code int.class} or {@code Integer.TYPE} instead.
     *
     * <p>If the class has not yet been loaded, it is loaded first, using the given class loader.
     * If the class has not yet been initialized and {@code shouldInitialize} is true,
     * the class will be initialized.
     *
     * @param className
     *            the name of the non-primitive-type class to find.
     * @param shouldInitialize
     *            indicates whether the class should be initialized.
     * @param classLoader
     *            the class loader to use to load the class.
     * @return the named {@code Class} instance.
     * @throws ClassNotFoundException
     *             if the requested class can not be found.
     * @throws LinkageError
     *             if an error occurs during linkage
     * @throws ExceptionInInitializerError
     *             if an exception occurs during static initialization of a
     *             class.
     */
    public static Class<?> forName(String className, boolean shouldInitialize,
            ClassLoader classLoader) throws ClassNotFoundException {

        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        // Catch an Exception thrown by the underlying native code. It wraps
        // up everything inside a ClassNotFoundException, even if e.g. an
        // Error occurred during initialization. This as a workaround for
        // an ExceptionInInitializerError that's also wrapped. It is actually
        // expected to be thrown. Maybe the same goes for other errors.
        // Not wrapping up all the errors will break android though.
        Class<?> result;
        try {
            result = classForName(className, shouldInitialize,
                    classLoader);
        } catch (ClassNotFoundException e) {
            Throwable cause = e.getCause();
            if (cause instanceof ExceptionInInitializerError) {
                throw (ExceptionInInitializerError) cause;
            }
            throw e;
        }
        return result;
    }

    static native Class<?> classForName(String className, boolean shouldInitialize,
            ClassLoader classLoader) throws ClassNotFoundException;

    /**
     * Returns an array containing {@code Class} objects for all public classes
     * and interfaces that are members of this class. This includes public
     * members inherited from super classes and interfaces. If there are no such
     * class members or if this object represents a primitive type then an array
     * of length 0 is returned.
     *
     * @return the public class members of the class represented by this object.
     */
    public Class<?>[] getClasses() {
        return getClassCache().getClasses(true);
    }

    @Override public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
        if (annotationType == null) {
            throw new NullPointerException("annotationType == null");
        }

        A annotation = getDeclaredAnnotation(annotationType);
        if (annotation != null) {
            return annotation;
        }

        if (annotationType.isAnnotationPresent(Inherited.class)) {
            for (Class<?> sup = getSuperclass(); sup != null; sup = sup.getSuperclass()) {
                annotation = sup.getDeclaredAnnotation(annotationType);
                if (annotation != null) {
                    return annotation;
                }
            }
        }

        return null;
    }

    /**
     * Returns an array containing all the annotations of this class. If there are no annotations
     * then an empty array is returned.
     *
     * @see #getDeclaredAnnotations()
     */
    public Annotation[] getAnnotations() {
        return getClassCache().getAnnotations(true);
    }

    final Annotation[] getAnnotations0() {
        /*
         * We need to get the annotations declared on this class, plus the
         * annotations from superclasses that have the "@Inherited" annotation
         * set.  We create a temporary map to use while we accumulate the
         * annotations and convert it to an array at the end.
         *
         * It's possible to have duplicates when annotations are inherited.
         * We use a Map to filter those out.
         *
         * HashMap might be overkill here.
         */
        HashMap<Class, Annotation> map = new HashMap<Class, Annotation>();
        Annotation[] declaredAnnotations = getClassCache().getDeclaredAnnotations(false);

        for (int i = declaredAnnotations.length-1; i >= 0; --i) {
            map.put(declaredAnnotations[i].annotationType(), declaredAnnotations[i]);
        }
        for (Class<?> sup = getSuperclass(); sup != null; sup = sup.getSuperclass()) {
            declaredAnnotations = sup.getClassCache().getDeclaredAnnotations(false);
            for (int i = declaredAnnotations.length-1; i >= 0; --i) {
                Class<?> clazz = declaredAnnotations[i].annotationType();
                if (!map.containsKey(clazz) && clazz.isAnnotationPresent(Inherited.class)) {
                    map.put(clazz, declaredAnnotations[i]);
                }
            }
        }

        /* convert annotation values from HashMap to array */
        Collection<Annotation> coll = map.values();
        return coll.toArray(new Annotation[coll.size()]);
    }

    /**
     * Returns the canonical name of this class. If this class does not have a
     * canonical name as defined in the Java Language Specification, then the
     * method returns {@code null}.
     */
    public String getCanonicalName() {
        if (isLocalClass() || isAnonymousClass())
            return null;

        if (isArray()) {
            /*
             * The canonical name of an array type depends on the (existence of)
             * the component type's canonical name.
             */
            String name = getComponentType().getCanonicalName();
            if (name != null) {
                return name + "[]";
            }
        } else if (isMemberClass()) {
            /*
             * The canonical name of an inner class depends on the (existence
             * of) the declaring class' canonical name.
             */
            String name = getDeclaringClass().getCanonicalName();
            if (name != null) {
                return name + "." + getSimpleName();
            }
        } else {
            /*
             * The canonical name of a top-level class or primitive type is
             * equal to the fully qualified name.
             */
            return getName();
        }

        /*
         * Other classes don't have a canonical name.
         */
        return null;
    }

    /**
     * Returns the class loader which was used to load the class represented by
     * this {@code Class}. Implementations are free to return {@code null} for
     * classes that were loaded by the bootstrap class loader. The Android
     * reference implementation, though, always returns a reference to an actual
     * class loader.
     */
    public ClassLoader getClassLoader() {
        if (this.isPrimitive()) {
            return null;
        }

        ClassLoader loader = getClassLoaderImpl();
        if (loader == null) {
            loader = BootClassLoader.getInstance();
        }
        return loader;
    }

    /**
     * This must be provided by the VM vendor, as it is used by other provided
     * class implementations in this package. Outside of this class, it is used
     * by SecurityManager.classLoaderDepth(),
     * currentClassLoader() and currentLoadedClass(). Return the ClassLoader for
     * this Class without doing any security checks. The bootstrap ClassLoader
     * is returned, unlike getClassLoader() which returns null in place of the
     * bootstrap ClassLoader.
     */
    ClassLoader getClassLoaderImpl() {
        ClassLoader loader = getClassLoader(this);
        return loader == null ? BootClassLoader.getInstance() : loader;
    }

    /*
     * Returns the defining class loader for the given class.
     */
    private static native ClassLoader getClassLoader(Class<?> c);

    /**
     * Returns a {@code Class} object which represents the component type if
     * this class represents an array type. Returns {@code null} if this class
     * does not represent an array type. The component type of an array type is
     * the type of the elements of the array.
     */
    public native Class<?> getComponentType();

    /**
     * Returns a {@code Constructor} object which represents the public
     * constructor matching the given parameter types.
     * {@code (Class[]) null} is equivalent to the empty array.
     *
     * <p>See {@link #getMethod} for details of the search order.
     * Use {@link #getDeclaredConstructor} if you don't want to search superclasses.
     *
     * @throws NoSuchMethodException
     *             if the constructor can not be found.
     */
    @SuppressWarnings("unchecked")
    public Constructor<T> getConstructor(Class<?>... parameterTypes) throws NoSuchMethodException {
        return getClassCache().getConstructor(true, parameterTypes);
    }

    /**
     * Returns an array containing {@code Constructor} objects for all public
     * constructors for this {@code Class}. If there
     * are no public constructors or if this {@code Class} represents an array
     * class, a primitive type or void then an empty array is returned.
     *
     * @see #getDeclaredConstructors()
     */
    public Constructor<?>[] getConstructors() {
        return getClassCache().getDeclaredPublicConstructors(true);
    }

    /**
     * Returns the annotations that are directly defined on the class
     * represented by this {@code Class}. Annotations that are inherited are not
     * included in the result. If there are no annotations at all, an empty
     * array is returned.
     *
     * @see #getAnnotations()
     */
    public Annotation[] getDeclaredAnnotations() {
        return getClassCache().getDeclaredAnnotations(true);
    }

    final native Annotation[] getDeclaredAnnotations0();

    /**
     * Returns the annotation if it exists.
     */
    private <A extends Annotation> A getDeclaredAnnotation(Class<A> annotationClass) {
        if (annotationClass == null) {
            throw new NullPointerException("annotationClass == null");
        }
        Annotation[] annos = getClassCache().getDeclaredAnnotations(false);
        for (int i = annos.length-1; i >= 0; --i) {
            if (annos[i].annotationType() == annotationClass) {
                return (A) annos[i];
            }
        }
        return null;
    }

    /**
     * Returns true if the annotation exists.
     */
    private boolean isDeclaredAnnotationPresent(Class<? extends Annotation> annotationClass) {
        if (annotationClass == null) {
            throw new NullPointerException("annotationClass == null");
        }
        return getDeclaredAnnotation(annotationClass) != null;
    }

    /**
     * Returns an array containing {@code Class} objects for all classes and
     * interfaces that are declared as members of the class which this {@code
     * Class} represents. If there are no classes or interfaces declared or if
     * this class represents an array class, a primitive type or void, then an
     * empty array is returned.
     */
    public Class<?>[] getDeclaredClasses() {
        return getClassCache().getDeclaredClasses(true);
    }

    final native Class<?>[] getDeclaredClasses0(boolean publicOnly);    

    /**
     * Returns a {@code Constructor} object which represents the constructor
     * matching the given parameter types that is declared by the class
     * represented by this {@code Class}.
     * {@code (Class[]) null} is equivalent to the empty array.
     *
     * <p>Use {@link #getConstructor} if you want to search superclasses.
     *
     * @throws NoSuchMethodException
     *             if the requested constructor can not be found.
     */
    @SuppressWarnings("unchecked")
    public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)
            throws NoSuchMethodException {
        return getClassCache().getDeclaredConstructor(true, parameterTypes);
    }

    /**
     * Returns an array containing {@code Constructor} objects for all
     * constructors declared in the class represented by this {@code Class}. If
     * there are no constructors or if this {@code Class} represents an array
     * class, a primitive type, or void then an empty array is returned.
     *
     * @see #getConstructors()
     */
    public Constructor<?>[] getDeclaredConstructors() {
        return getClassCache().getDeclaredConstructors(true);
    }

    final native Constructor<?>[] getDeclaredConstructors0(boolean publicOnly);

    /**
     * Returns a {@code Field} object for the field with the given name
     * which is declared in the class represented by this {@code Class}.
     *
     * @throws NoSuchFieldException if the requested field can not be found.
     * @see #getField(String)
     */
    public Field getDeclaredField(String name) throws NoSuchFieldException {
        if (name == null) {
            throw new NullPointerException("name == null");
        }
        return getClassCache().getDeclaredField(true, name);        
    }

    /**
     * Returns an array containing {@code Field} objects for all fields declared
     * in the class represented by this {@code Class}. If there are no fields or
     * if this {@code Class} represents an array class, a primitive type or void
     * then an empty array is returned.
     *
     * @see #getFields()
     */
    public Field[] getDeclaredFields() {
        return getClassCache().getDeclaredFields(true);
    }

    final native Field[] getDeclaredFields0(boolean publicOnly);

    /**
     * Returns a {@code Method} object which represents the method matching the
     * given name and parameter types that is declared by the class
     * represented by this {@code Class}.
     * {@code (Class[]) null} is equivalent to the empty array.
     *
     * <p>See {@link #getMethod} if you want to search superclasses.
     *
     * @throws NoSuchMethodException
     *             if the requested method can not be found.
     * @throws NullPointerException
     *             if {@code name} is {@code null}.
     */
    public Method getDeclaredMethod(String name, Class<?>... parameterTypes)
            throws NoSuchMethodException {
        return getClassCache().getDeclaredMethod(true, name, parameterTypes);
    }

    /**
     * Returns an array containing {@code Method} objects for all methods
     * declared in the class represented by this {@code Class}. If there are no
     * methods or if this {@code Class} represents an array class, a primitive
     * type or void then an empty array is returned.
     *
     * @see #getMethods()
     */
    public Method[] getDeclaredMethods() {
        return getClassCache().getDeclaredMethods(true);
    }

    final native Method[] getDeclaredMethods0(boolean publicOnly);

    /**
     * Returns the declaring {@code Class} of this {@code Class}. Returns
     * {@code null} if the class is not a member of another class or if this
     * {@code Class} represents an array class, a primitive type, or void.
     */
    public native Class<?> getDeclaringClass();

    /**
     * Returns the enclosing {@code Class} of this {@code Class}. If there is no
     * enclosing class the method returns {@code null}.
     */
    public native Class<?> getEnclosingClass();

    /**
     * Returns the enclosing {@code Constructor} of this {@code Class}, if it is an
     * anonymous or local/automatic class; otherwise {@code null}.
     */
    public native Constructor<?> getEnclosingConstructor();

    /**
     * Returns the enclosing {@code Method} of this {@code Class}, if it is an
     * anonymous or local/automatic class; otherwise {@code null}.
     */
    public native Method getEnclosingMethod();

    /**
     * Returns the {@code enum} constants associated with this {@code Class}.
     * Returns {@code null} if this {@code Class} does not represent an {@code
     * enum} type.
     */
    @SuppressWarnings("unchecked") // we only cast after confirming that this class is an enum
    public T[] getEnumConstants() {
        if (!isEnum()) {
            return null;
        }
        return (T[]) Enum.getSharedConstants((Class) this).clone();
    }

    /**
     * Returns a {@code Field} object which represents the public field with the
     * given name. This method first searches the class C represented by
     * this {@code Class}, then the interfaces implemented by C and finally the
     * superclasses of C.
     *
     * @throws NoSuchFieldException
     *             if the field can not be found.
     * @see #getDeclaredField(String)
     */
    public Field getField(String name) throws NoSuchFieldException {
        if (name == null) {
            throw new NullPointerException("name == null");
        }
        return getClassCache().getField(true, name);
    }

    /**
     * Returns an array containing {@code Field} objects for all public fields
     * for the class C represented by this {@code Class}. Fields may be declared
     * in C, the interfaces it implements or in the superclasses of C. The
     * elements in the returned array are in no particular order.
     *
     * <p>If there are no public fields or if this class represents an array class,
     * a primitive type or {@code void} then an empty array is returned.
     *
     * @see #getDeclaredFields()
     */
    public Field[] getFields() {
        return getClassCache().getFields(true);
    }

    /**
     * Returns the {@link Type}s of the interfaces that this {@code Class} directly
     * implements. If the {@code Class} represents a primitive type or {@code
     * void} then an empty array is returned.
     */
    public Type[] getGenericInterfaces() {
        GenericSignatureParser parser = new GenericSignatureParser(getClassLoader());
        parser.parseForClass(this, getSignatureAttribute());
        return Types.getTypeArray(parser.interfaceTypes, true);
    }

    /**
     * Returns the {@code Type} that represents the superclass of this {@code
     * class}.
     */
    public Type getGenericSuperclass() {
        GenericSignatureParser parser = new GenericSignatureParser(getClassLoader());
        parser.parseForClass(this, getSignatureAttribute());
        return Types.getType(parser.superclassType);
    }

    /**
     * Returns an array of {@code Class} objects that match the interfaces
     * in the {@code implements} declaration of the class represented
     * by this {@code Class}. The order of the elements in the array is
     * identical to the order in the original class declaration. If the class
     * does not implement any interfaces, an empty array is returned.
     */
    public native Class<?>[] getInterfaces();

    /**
     * Returns a {@code Method} object which represents the public method with
     * the given name and parameter types.
     * {@code (Class[]) null} is equivalent to the empty array.
     *
     * <p>This method first searches the class C represented by this {@code Class},
     * then the superclasses of C,
     * and finally the interfaces implemented by C and its superclasses.
     *
     * <p>Use {@link #getDeclaredMethod} if you don't want to search superclasses.
     *
     * @throws NoSuchMethodException
     *             if the method can not be found.
     */
    public Method getMethod(String name, Class<?>... parameterTypes) throws NoSuchMethodException {
        return getClassCache().getMethod(true, name, parameterTypes);
    }

    /**
     * Returns an array containing {@code Method} objects for all public methods
     * for the class C represented by this {@code Class}. Methods may be
     * declared in C, the interfaces it implements or in the superclasses of C.
     * The elements in the returned array are in no particular order.
     *
     * <p>If there are no public methods or if this {@code Class} represents a
     * primitive type or {@code void} then an empty array is returned.
     *
     * @see #getDeclaredMethods()
     */
    public Method[] getMethods() {
        return getClassCache().getMethods(true);
    }

    /**
     * Returns an integer that represents the modifiers of the class represented
     * by this {@code Class}. The returned value is a combination of bits
     * defined by constants in the {@link Modifier} class.
     */
    public int getModifiers() {
        return getModifiers(this, false);
    }

    /*
     * Returns the modifiers for the given class.
     *
     * {@code ignoreInnerClassesAttrib} determines whether we look for and use the
     *     flags from an "inner class" attribute
     */
    private static native int getModifiers(Class<?> clazz, boolean ignoreInnerClassesAttrib);

    /**
     * Returns the name of the class represented by this {@code Class}. For a
     * description of the format which is used, see the class definition of
     * {@link Class}.
     */
    public String getName() {
        return getClassCache().getName();
    }

    /**
     * Returns the RoboVM class name. E.g. java/lang/String, I, [[I,
     * [Ljava/lang/Object;.
     */
    final native String getName0();
    
    /**
     * Returns the simple name of the class represented by this {@code Class} as
     * defined in the source code. If there is no name (that is, the class is
     * anonymous) then an empty string is returned. If the receiver is an array
     * then the name of the underlying type with square braces appended (for
     * example {@code "Integer[]"}) is returned.
     *
     * @return the simple name of the class represented by this {@code Class}.
     */
    public String getSimpleName() {
        if (isArray()) {
            return getComponentType().getSimpleName() + "[]";
        }

        String name = getName();

        if (isAnonymousClass()) {
            return "";
        }

        if (isMemberClass() || isLocalClass()) {
            return getInnerClassName();
        }

        int dot = name.lastIndexOf('.');
        if (dot != -1) {
            return name.substring(dot + 1);
        }

        return name;
    }

    /*
     * Returns the simple name of a member or local class, or null otherwise.
     */
    private native String getInnerClassName();

    /**
     * Returns null.
     */
    public ProtectionDomain getProtectionDomain() {
        return null;
    }

    /**
     * Returns the URL of the given resource, or null if the resource is not found.
     * The mapping between the resource name and the URL is managed by the class' class loader.
     *
     * @see ClassLoader
     */
    public URL getResource(String resourceName) {
        // Get absolute resource name, but without the leading slash
        if (resourceName.startsWith("/")) {
            resourceName = resourceName.substring(1);
        } else {
            String pkg = getName();
            int dot = pkg.lastIndexOf('.');
            if (dot != -1) {
                pkg = pkg.substring(0, dot).replace('.', '/');
            } else {
                pkg = "";
            }

            resourceName = pkg + "/" + resourceName;
        }

        // Delegate to proper class loader
        ClassLoader loader = getClassLoader();
        if (loader != null) {
            return loader.getResource(resourceName);
        } else {
            return ClassLoader.getSystemResource(resourceName);
        }
    }

    /**
     * Returns a read-only stream for the contents of the given resource, or null if the resource
     * is not found.
     * The mapping between the resource name and the stream is managed by the class' class loader.
     *
     * @see ClassLoader
     */
    public InputStream getResourceAsStream(String resourceName) {
        // Get absolute resource name, but without the leading slash
        if (resourceName.startsWith("/")) {
            resourceName = resourceName.substring(1);
        } else {
            String pkg = getName();
            int dot = pkg.lastIndexOf('.');
            if (dot != -1) {
                pkg = pkg.substring(0, dot).replace('.', '/');
            } else {
                pkg = "";
            }

            resourceName = pkg + "/" + resourceName;
        }

        // Delegate to proper class loader
        ClassLoader loader = getClassLoader();
        if (loader != null) {
            return loader.getResourceAsStream(resourceName);
        } else {
            return ClassLoader.getSystemResourceAsStream(resourceName);
        }
    }

    /**
     * Returns null. (On Android, a {@code ClassLoader} can load classes from multiple dex files.
     * All classes from any given dex file will have the same signers, but different dex
     * files may have different signers. This does not fit well with the original
     * {@code ClassLoader}-based model of {@code getSigners}.)
     */
    public Object[] getSigners() {
        // See http://code.google.com/p/android/issues/detail?id=1766.
        return null;
    }

    /**
     * Returns the {@code Class} object which represents the superclass of the
     * class represented by this {@code Class}. If this {@code Class} represents
     * the {@code Object} class, a primitive type, an interface or void then the
     * method returns {@code null}. If this {@code Class} represents an array
     * class then the {@code Object} class is returned.
     */
    public native Class<? super T> getSuperclass();

    /**
     * Returns an array containing {@code TypeVariable} objects for type
     * variables declared by the generic class represented by this {@code
     * Class}. Returns an empty array if the class is not generic.
     */
    @SuppressWarnings("unchecked")
    public synchronized TypeVariable<Class<T>>[] getTypeParameters() {
        GenericSignatureParser parser = new GenericSignatureParser(getClassLoader());
        parser.parseForClass(this, getSignatureAttribute());
        return parser.formalTypeParameters.clone();
    }

    /**
     * Tests whether this {@code Class} represents an annotation class.
     */
    public boolean isAnnotation() {
        final int ACC_ANNOTATION = 0x2000;  // not public in reflect.Modifiers
        int mod = getModifiers(this, true);
        return (mod & ACC_ANNOTATION) != 0;
    }

    @Override public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
        if (annotationType == null) {
            throw new NullPointerException("annotationType == null");
        }

        if (isDeclaredAnnotationPresent(annotationType)) {
            return true;
        }

        if (annotationType.isDeclaredAnnotationPresent(Inherited.class)) {
            for (Class<?> sup = getSuperclass(); sup != null; sup = sup.getSuperclass()) {
                if (sup.isDeclaredAnnotationPresent(annotationType)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Tests whether the class represented by this {@code Class} is
     * anonymous.
     */
    native public boolean isAnonymousClass();

    /**
     * Tests whether the class represented by this {@code Class} is an array class.
     */
    public boolean isArray() {
        return getComponentType() != null;
    }

    /**
     * Tests whether the given class type can be converted to the class
     * represented by this {@code Class}. Conversion may be done via an identity
     * conversion or a widening reference conversion (if either the receiver or
     * the argument represent primitive types, only the identity conversion
     * applies).
     *
     * @throws NullPointerException
     *             if {@code c} is {@code null}.
     */
    public native boolean isAssignableFrom(Class<?> c);

    /**
     * Tests whether the class represented by this {@code Class} is an
     * {@code enum}.
     */
    public boolean isEnum() {
        if (getSuperclass() != Enum.class) {
            return false;
        }
        int mod = getModifiers(this, true);
        return (mod & 0x4000) != 0;
    }

    /**
     * Tests whether the given object can be cast to the class
     * represented by this {@code Class}. This is the runtime version of the
     * {@code instanceof} operator.
     *
     * @return {@code true} if {@code object} can be cast to the type
     *         represented by this {@code Class}; {@code false} if {@code
     *         object} is {@code null} or cannot be cast.
     */
    public native boolean isInstance(Object object);

    /**
     * Tests whether this {@code Class} represents an interface.
     */
    public native boolean isInterface();

    /**
     * Tests whether the class represented by this {@code Class} is defined
     * locally.
     */
    public boolean isLocalClass() {
        boolean enclosed = (getEnclosingMethod() != null ||
                         getEnclosingConstructor() != null);
        return enclosed && !isAnonymousClass();
    }

    /**
     * Tests whether the class represented by this {@code Class} is a member
     * class.
     */
    public boolean isMemberClass() {
        return getDeclaringClass() != null;
    }

    /**
     * Tests whether this {@code Class} represents a primitive type.
     */
    public native boolean isPrimitive();

    /**
     * Tests whether this {@code Class} represents a synthetic type.
     */
    public boolean isSynthetic() {
        final int ACC_SYNTHETIC = 0x1000;   // not public in reflect.Modifiers
        int mod = getModifiers(this, true);
        return (mod & ACC_SYNTHETIC) != 0;
    }

    /**
     * Returns a new instance of the class represented by this {@code Class},
     * created by invoking the default (that is, zero-argument) constructor. If
     * there is no such constructor, or if the creation fails (either because of
     * a lack of available memory or because an exception is thrown by the
     * constructor), an {@code InstantiationException} is thrown. If the default
     * constructor exists but is not accessible from the context where this
     * method is invoked, an {@code IllegalAccessException} is thrown.
     *
     * @throws IllegalAccessException
     *             if the default constructor is not visible.
     * @throws InstantiationException
     *             if the instance can not be created.
     */
    public T newInstance() throws InstantiationException, IllegalAccessException {
        try {
            Constructor<T> constructor = getDeclaredConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException e) {
            throw new InstantiationException(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new InstantiationException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        if (isPrimitive()) {
            return getSimpleName();
        }
        return (isInterface() ? "interface " : "class ") + getName();
    }

    /**
     * Returns the {@code Package} of which the class represented by this
     * {@code Class} is a member. Returns {@code null} if no {@code Package}
     * object was created by the class loader of the class.
     */
    public Package getPackage() {
        // TODO This might be a hack, but the VM doesn't have the necessary info.
        ClassLoader loader = getClassLoader();
        if (loader != null) {
            String name = getName();
            int dot = name.lastIndexOf('.');
            return (dot != -1 ? loader.getPackage(name.substring(0, dot)) : null);
        }
        return null;
    }

    /**
     * Returns the assertion status for the class represented by this {@code
     * Class}. Assertion is enabled / disabled based on the class loader,
     * package or class default at runtime.
     */
    public native boolean desiredAssertionStatus();

    /**
     * Casts this {@code Class} to represent a subclass of the given class.
     * If successful, this {@code Class} is returned; otherwise a {@code
     * ClassCastException} is thrown.
     *
     * @throws ClassCastException
     *             if this {@code Class} cannot be cast to the given type.
     */
    @SuppressWarnings("unchecked")
    public <U> Class<? extends U> asSubclass(Class<U> c) {
        if (c.isAssignableFrom(this)) {
            return (Class<? extends U>)this;
        }
        String actualClassName = this.getName();
        String desiredClassName = c.getName();
        throw new ClassCastException(actualClassName + " cannot be cast to " + desiredClassName);
    }

    /**
     * Casts the given object to the type represented by this {@code Class}.
     * If the object is {@code null} then the result is also {@code null}.
     *
     * @throws ClassCastException
     *             if the object cannot be cast to the given type.
     */
    @SuppressWarnings("unchecked")
    public T cast(Object obj) {
        if (obj == null) {
            return null;
        } else if (this.isInstance(obj)) {
            return (T)obj;
        }
        String actualClassName = obj.getClass().getName();
        String desiredClassName = this.getName();
        throw new ClassCastException(actualClassName + " cannot be cast to " + desiredClassName);
    }

    /**
     * Copies two arrays into one. Assumes that the destination array is large
     * enough.
     *
     * @param result the destination array
     * @param head the first source array
     * @param tail the second source array
     * @return the destination array, that is, result
     */
    private static <T extends Object> T[] arraycopy(T[] result, T[] head, T[] tail) {
        System.arraycopy(head, 0, result, 0, head.length);
        System.arraycopy(tail, 0, result, head.length, tail.length);
        return result;
    }
}
