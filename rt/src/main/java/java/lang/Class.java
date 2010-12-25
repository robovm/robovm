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

package java.lang;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.net.URL;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.HashSet;

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
 *
 * @since 1.0
 */
public final class Class<T> implements Serializable, AnnotatedElement,
        GenericDeclaration, Type {

    /*
     * This class must be implemented by the VM vendor. The documented natives must
     * be implemented to support other provided class implementations in this
     * package.
     */

    private static final long serialVersionUID = 3206093459760846163L;
    
    private String name = null;
    
    private Class() {
        // prevent this class to be instantiated, instance should be created by
        // JVM only
    }

    /**
     * This must be provided by the VM vendor, as it is used by other provided
     * class implementations in this package. This method is used by
     * SecurityManager.classDepth(), and getClassContext() which use the
     * parameters (-1, false) and SecurityManager.classLoaderDepth(),
     * currentClassLoader(), and currentLoadedClass() which use the parameters
     * (-1, true). Walk the stack and answer an array containing the maxDepth
     * most recent classes on the stack of the calling thread. Starting with the
     * caller of the caller of getStackClasses(), return an array of not more
     * than maxDepth Classes representing the classes of running methods on the
     * stack (including native methods). Frames representing the VM
     * implementation of java.lang.reflect are not included in the list. If
     * stopAtPrivileged is true, the walk will terminate at any frame running
     * one of the following methods: <code><ul>
     * <li>java/security/AccessController.doPrivileged(Ljava/security/PrivilegedAction;)Ljava/lang/Object;</li>
     * <li>java/security/AccessController.doPrivileged(Ljava/security/PrivilegedExceptionAction;)Ljava/lang/Object;</li>
     * <li>java/security/AccessController.doPrivileged(Ljava/security/PrivilegedAction;Ljava/security/AccessControlContext;)Ljava/lang/Object;</li>
     * <li>java/security/AccessController.doPrivileged(Ljava/security/PrivilegedExceptionAction;Ljava/security/AccessControlContext;)Ljava/lang/Object;</li>
     * </ul></code> If one of the doPrivileged methods is found, the walk terminate
     * and that frame is NOT included in the returned array. Notes:
     * <ul>
     * <li>This method operates on the defining classes of methods on stack.
     * NOT the classes of receivers.</li>
     * <li>The item at index zero in the result array describes the caller of
     * the caller of this method.</li>
     * </ul>
     * 
     * @param maxDepth
     *            maximum depth to walk the stack, -1 for the entire stack
     * @param stopAtPrivileged
     *            stop at privileged classes
     * @return the array of the most recent classes on the stack
     */
    static native final Class[] getStackClasses(int maxDepth, boolean stopAtPrivileged);

    /**
     * Returns a {@code Class} object which represents the class with the
     * specified name. The name should be the name of a class as described in
     * the {@link Class class definition}; however, {@code Class}es representing
     * primitive types can not be found using this method.
     * <p>
     * If the class has not been loaded so far, it is being loaded and linked
     * first. This is done through either the class loader of the calling class
     * or one of its parent class loaders. The class is also being initialized,
     * which means that a possible static initializer block is executed.
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
    public static Class<?> forName(String className)
            throws ClassNotFoundException {
        return forName(className, true, getStackClasses(1, false)[0].getClassLoader());
    }

    /**
     * Returns a {@code Class} object which represents the class with the
     * specified name. The name should be the name of a class as described in
     * the {@link Class class definition}, however {@code Class}es representing
     * primitive types can not be found using this method. Security rules will
     * be obeyed.
     * <p>
     * If the class has not been loaded so far, it is being loaded and linked
     * first. This is done through either the specified class loader or one of
     * its parent class loaders. The caller can also request the class to be
     * initialized, which means that a possible static initializer block is
     * executed.
     *
     * @param className
     *            the name of the non-primitive-type class to find.
     * @param initializeBoolean
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
    public native static Class<?> forName(String className, boolean initializeBoolean,
            ClassLoader classLoader) throws ClassNotFoundException;

    /**
     * Returns an array containing {@code Class} objects for all public classes
     * and interfaces that are members of this class. This includes public
     * members inherited from super classes and interfaces. If there are no such
     * class members or if this object represents a primitive type then an array
     * of length 0 is returned.
     *
     * @return the public class members of the class represented by this object.
     * @throws SecurityException
     *             if a security manager exists and it does not allow member
     *             access.
     */
    @SuppressWarnings("unchecked") // According to spec
    public native Class<?>[] getClasses();

    /**
     * Verify the specified Class using the VM byte code verifier.
     * 
     * @throws VerifyError
     *             if the Class cannot be verified
     */
    void verify() {
        return;
    }

    /**
     * Returns the annotation of the given type. If there is no such annotation
     * then the method returns {@code null}.
     *
     * @param annotationClass
     *            the annotation type.
     * @return the annotation of the given type, or {@code null} if there is no
     *         such annotation.
     * @since 1.5
     */
    public native <A extends Annotation> A getAnnotation(Class<A> annotationClass);

    /**
     * Returns all the annotations of this class. If there are no annotations
     * then an empty array is returned.
     *
     * @return a copy of the array containing this class' annotations.
     * @see #getDeclaredAnnotations()
     */
    public native Annotation[] getAnnotations();

    /**
     * Returns the canonical name of this class. If this class does not have a
     * canonical name as defined in the Java Language Specification, then the
     * method returns {@code null}.
     *
     * @return this class' canonical name, or {@code null} if it does not have a
     *         canonical name.
     */
    public String getCanonicalName() {
        // Start (C) Android
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
        // End (C) Android
    }

    /**
     * Returns the class loader which was used to load the class represented by
     * this {@code Class}. Implementations are free to return {@code null} for
     * classes that were loaded by the bootstrap class loader.
     *
     * @return the class loader for the represented class.
     * @throws SecurityException
     *             if a security manager exists and it does not allow accessing
     *             the class loader.
     * @see ClassLoader
     */
    public native ClassLoader getClassLoader();

    /**
     * This must be provided by the VM vendor, as it is used by other provided
     * class implementations in this package. Outside of this class, it is used
     * by SecurityManager.checkMemberAccess(), classLoaderDepth(),
     * currentClassLoader() and currentLoadedClass(). Return the ClassLoader for
     * this Class without doing any security checks. The bootstrap ClassLoader
     * is returned, unlike getClassLoader() which returns null in place of the
     * bootstrap ClassLoader.
     * 
     * @return the ClassLoader
     * @see ClassLoader#isSystemClassLoader()
     */
    native ClassLoader getClassLoaderImpl();

    /**
     * Returns a {@code Class} object which represents the component type if
     * this class represents an array type. Returns {@code null} if this class
     * does not represent an array type. The component type of an array type is
     * the type of the elements of the array.
     *
     * @return the component type of this class.
     */
    public native Class<?> getComponentType();

    /**
     * Returns a {@code Constructor} object which represents the public
     * constructor matching the specified parameter types.
     *
     * @param parameterTypes
     *            the parameter types of the requested constructor.
     * @return the constructor described by {@code parameterTypes}.
     * @throws NoSuchMethodException
     *             if the constructor can not be found.
     * @throws SecurityException
     *             if a security manager exists and it does not allow member
     *             access.
     * @see #getDeclaredConstructor(Class[])
     */
    public Constructor<T> getConstructor(Class<?>... parameterTypes)
            throws NoSuchMethodException, SecurityException {
        
        return findConstructor(getDeclaredConstructors(true), parameterTypes);
    }
    
    @SuppressWarnings("unchecked")
    private Constructor<T> findConstructor(Constructor<?>[] candidates, Class<?>... parameterTypes)
            throws NoSuchMethodException, SecurityException {
        
        next: for (Constructor<?> c : candidates) {
            Class<?>[] pTypes = c.getParameterTypes();
            if (pTypes.length == parameterTypes.length) {
                for (int i = 0; i < pTypes.length; i++) {
                    if (pTypes[i] != parameterTypes[i]) {
                        break next;
                    }
                }
                return (Constructor<T>) c;
            }
        }
    
        throw new NoSuchMethodException(getSimpleName() + '(' + parameterTypesToString(parameterTypes) + ')');
    }

    private String parameterTypesToString(Class<?>[] parameterTypes) {
        if (parameterTypes.length > 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(parameterTypes[0]);
            for (int i = 1; i < parameterTypes.length; i++) {
                sb.append(',');
                sb.append(parameterTypes[i] == null ? "null" 
                        : parameterTypes[i].getCanonicalName());
            }
            return sb.toString();
        }
        return "";
    }
    
    /**
     * Returns an array containing {@code Constructor} objects for all public
     * constructors for the class represented by this {@code Class}. If there
     * are no public constructors or if this {@code Class} represents an array
     * class, a primitive type or void then an empty array is returned.
     *
     * @return an array with the public constructors of the class represented by
     *         this {@code Class}.
     * @throws SecurityException
     *             if a security manager exists and it does not allow member
     *             access.
     * @see #getDeclaredConstructors()
     */
    public Constructor<?>[] getConstructors() throws SecurityException {
        return getDeclaredConstructors(true);
    }
    
    /**
     * Returns the annotations that are directly defined on the class
     * represented by this {@code Class}. Annotations that are inherited are not
     * included in the result. If there are no annotations at all, an empty
     * array is returned.
     *
     * @return a copy of the array containing the annotations defined for the
     *         class that this {@code Class} represents.
     * @see #getAnnotations()
     */
    public native Annotation[] getDeclaredAnnotations();

    /**
     * Returns an array containing {@code Class} objects for all classes and
     * interfaces that are declared as members of the class which this {@code
     * Class} represents. If there are no classes or interfaces declared or if
     * this class represents an array class, a primitive type or void, then an
     * empty array is returned.
     *
     * @return an array with {@code Class} objects for all the classes and
     *         interfaces that are used in member declarations.
     * @throws SecurityException
     *             if a security manager exists and it does not allow member
     *             access.
     */
    public native Class<?>[] getDeclaredClasses() throws SecurityException;

    /**
     * Returns a {@code Constructor} object which represents the constructor
     * matching the specified parameter types that is declared by the class
     * represented by this {@code Class}.
     *
     * @param parameterTypes
     *            the parameter types of the requested constructor.
     * @return the constructor described by {@code parameterTypes}.
     * @throws NoSuchMethodException
     *             if the requested constructor can not be found.
     * @throws SecurityException
     *             if a security manager exists and it does not allow member
     *             access.
     * @see #getConstructor(Class[])
     */
    public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes)
            throws NoSuchMethodException, SecurityException {
        
        return findConstructor(getDeclaredConstructors(false), parameterTypes);
    }

    /**
     * Returns an array containing {@code Constructor} objects for all
     * constructors declared in the class represented by this {@code Class}. If
     * there are no constructors or if this {@code Class} represents an array
     * class, a primitive type or void then an empty array is returned.
     *
     * @return an array with the constructors declared in the class represented
     *         by this {@code Class}.
     *
     * @throws SecurityException
     *             if a security manager exists and it does not allow member
     *             access.
     * @see #getConstructors()
     */
    public Constructor<?>[] getDeclaredConstructors() throws SecurityException {
        return getDeclaredConstructors(false);
    }

    private Constructor<?>[] getDeclaredConstructors(boolean publicOnly) {
        if (isArray() || isPrimitive()) {
            return new Constructor<?>[0];
        }
        return getDeclaredConstructors0(publicOnly);
    }
    
    private native Constructor<?>[] getDeclaredConstructors0(boolean publicOnly);

    /**
     * Returns a {@code Field} object for the field with the specified name
     * which is declared in the class represented by this {@code Class}.
     *
     * @param name
     *            the name of the requested field.
     * @return the requested field in the class represented by this class.
     * @throws NoSuchFieldException
     *             if the requested field can not be found.
     * @throws SecurityException
     *             if a security manager exists and it does not allow member
     *             access.
     * @see #getField(String)
     */
    public native Field getDeclaredField(String name) throws NoSuchFieldException,
            SecurityException;

    /**
     * Returns an array containing {@code Field} objects for all fields declared
     * in the class represented by this {@code Class}. If there are no fields or
     * if this {@code Class} represents an array class, a primitive type or void
     * then an empty array is returned.
     *
     * @return an array with the fields declared in the class represented by
     *         this class.
     * @throws SecurityException
     *             if a security manager exists and it does not allow member
     *             access.
     * @see #getFields()
     */
    public native Field[] getDeclaredFields() throws SecurityException;

    /**
     * Returns a {@code Method} object which represents the method matching the
     * specified name and parameter types that is declared by the class
     * represented by this {@code Class}.
     *
     * @param name
     *            the requested method's name.
     * @param parameterTypes
     *            the parameter types of the requested method.
     * @return the method described by {@code name} and {@code parameterTypes}.
     * @throws NoSuchMethodException
     *             if the requested constructor can not be found.
     * @throws NullPointerException
     *             if {@code name} is {@code null}.
     * @throws SecurityException
     *             if a security manager exists and it does not allow member
     *             access.
     * @see #getMethod(String, Class[])
     */
    public Method getDeclaredMethod(String name, Class<?>... parameterTypes)
            throws NoSuchMethodException, SecurityException {
        
        return findMethod(getDeclaredMethods(false), name, parameterTypes);
    }

    /**
     * Returns an array containing {@code Method} objects for all methods
     * declared in the class represented by this {@code Class}. If there are no
     * methods or if this {@code Class} represents an array class, a primitive
     * type or void then an empty array is returned.
     *
     * @return an array with the methods declared in the class represented by
     *         this {@code Class}.
     * @throws SecurityException
     *             if a security manager exists and it does not allow member
     *             access.
     * @see #getMethods()
     */
    public Method[] getDeclaredMethods() throws SecurityException {
        return getDeclaredMethods(false);
    }

    private Method[] getDeclaredMethods(boolean publicOnly) {
        if (isArray() || isPrimitive()) {
            return new Method[0];
        }
        return getDeclaredMethods0(publicOnly);
    }
    
    private native Method[] getDeclaredMethods0(boolean publicOnly);

    /**
     * Returns the declaring {@code Class} of this {@code Class}. Returns
     * {@code null} if the class is not a member of another class or if this
     * {@code Class} represents an array class, a primitive type or void.
     *
     * @return the declaring {@code Class} or {@code null}.
     */
    public native Class<?> getDeclaringClass();

    /**
     * Returns the enclosing {@code Class} of this {@code Class}. If there is no
     * enclosing class the method returns {@code null}.
     *
     * @return the enclosing {@code Class} or {@code null}.
     */
    public native Class<?> getEnclosingClass();

    /**
     * Gets the enclosing {@code Constructor} of this {@code Class}, if it is an
     * anonymous or local/automatic class; otherwise {@code null}.
     *
     * @return the enclosing {@code Constructor} instance or {@code null}.
     */
    public native Constructor<?> getEnclosingConstructor();

    /**
     * Gets the enclosing {@code Method} of this {@code Class}, if it is an
     * anonymous or local/automatic class; otherwise {@code null}.
     *
     * @return the enclosing {@code Method} instance or {@code null}.
     */
    public native Method getEnclosingMethod();

    /**
     * Gets the {@code enum} constants associated with this {@code Class}.
     * Returns {@code null} if this {@code Class} does not represent an {@code
     * enum} type.
     *
     * @return an array with the {@code enum} constants or {@code null}.
     * @since 1.5
     */
    public native T[] getEnumConstants();

    /**
     * Returns a {@code Field} object which represents the public field with the
     * specified name. This method first searches the class C represented by
     * this {@code Class}, then the interfaces implemented by C and finally the
     * superclasses of C.
     *
     * @param name
     *            the name of the requested field.
     * @return the public field specified by {@code name}.
     * @throws NoSuchFieldException
     *             if the field can not be found.
     * @throws SecurityException
     *             if a security manager exists and it does not allow member
     *             access.
     * @see #getDeclaredField(String)
     */
    public native Field getField(String name) throws NoSuchFieldException,
            SecurityException;

    /**
     * Returns an array containing {@code Field} objects for all public fields
     * for the class C represented by this {@code Class}. Fields may be declared
     * in C, the interfaces it implements or in the superclasses of C. The
     * elements in the returned array are in no particular order.
     * <p>
     * If there are no public fields or if this class represents an array class,
     * a primitive type or {@code void} then an empty array is returned.
     * </p>
     *
     * @return an array with the public fields of the class represented by this
     *         {@code Class}.
     * @throws SecurityException
     *             if a security manager exists and it does not allow member
     *             access.
     * @see #getDeclaredFields()
     */
    public native Field[] getFields() throws SecurityException;

    /**
     * Gets the {@link Type}s of the interfaces that this {@code Class} directly
     * implements. If the {@code Class} represents a primitive type or {@code
     * void} then an empty array is returned.
     *
     * @return an array of {@link Type} instances directly implemented by the
     *         class represented by this {@code class}.
     */
    public native Type[] getGenericInterfaces();

    /**
     * Gets the {@code Type} that represents the superclass of this {@code
     * class}.
     *
     * @return an instance of {@code Type} representing the superclass.
     * @since 1.5
     */
    public native Type getGenericSuperclass();

    /**
     * Returns an array of {@code Class} objects that match the interfaces
     * specified in the {@code implements} declaration of the class represented
     * by this {@code Class}. The order of the elements in the array is
     * identical to the order in the original class declaration. If the class
     * does not implement any interfaces, an empty array is returned.
     *
     * @return an array with the interfaces of the class represented by this
     *         class.
     */
    public native Class<?>[] getInterfaces();

    /**
     * Returns a {@code Method} object which represents the public method with
     * the specified name and parameter types. This method first searches the
     * class C represented by this {@code Class}, then the superclasses of C and
     * finally the interfaces implemented by C and finally the superclasses of C
     * for a method with matching name.
     *
     * @param name
     *            the requested method's name.
     * @param parameterTypes
     *            the parameter types of the requested method.
     * @return the public field specified by {@code name}.
     * @throws NoSuchMethodException
     *             if the method can not be found.
     * @throws SecurityException
     *             if a security manager exists and it does not allow member
     *             access.
     * @see #getDeclaredMethod(String, Class[])
     */
    public Method getMethod(String name, Class<?>... parameterTypes)
            throws NoSuchMethodException, SecurityException {
        
        return findMethod(getMethods(), name, parameterTypes);
    }

    @SuppressWarnings("unchecked")
    private Method findMethod(Method[] candidates, String name, Class<?>... parameterTypes)
            throws NoSuchMethodException, SecurityException {
        
        next: for (Method m : candidates) {
            Class<?>[] pTypes = m.getParameterTypes();
            if (pTypes.length == parameterTypes.length && m.getName().equals(name)) {
                for (int i = 0; i < pTypes.length; i++) {
                    if (pTypes[i] != parameterTypes[i]) {
                        break next;
                    }
                }
                return m;
            }
        }
    
        throw new NoSuchMethodException(name + '(' + parameterTypesToString(parameterTypes) + ')');
    }
    
    /**
     * Returns an array containing {@code Method} objects for all public methods
     * for the class C represented by this {@code Class}. Methods may be
     * declared in C, the interfaces it implements or in the superclasses of C.
     * The elements in the returned array are in no particular order.
     * <p>
     * If there are no public methods or if this {@code Class} represents a
     * primitive type or {@code void} then an empty array is returned.
     * </p>
     *
     * @return an array with the methods of the class represented by this
     *         {@code Class}.
     * @throws SecurityException
     *             if a security manager exists and it does not allow member
     *             access.
     * @see #getDeclaredMethods()
     */
    public Method[] getMethods() throws SecurityException {
        ArrayList<Method> result = getMethods(new ArrayList<Method>(), new HashSet<String>());
        return result.toArray(new Method[result.size()]);
    }

    private ArrayList<Method> getMethods(ArrayList<Method> result, HashSet<String> seen) {
        Class<?> c = this;
        while (c != null) {
            for (Method m : c.getDeclaredMethods(true)) {
                String s = m.getName() + '(' + parameterTypesToString(m.getParameterTypes()) + ')';
                if (!seen.contains(s)) {
                    result.add(m);
                    seen.add(s);
                }
            }
            c = c.getSuperclass();
        }
        
        for (Class<?> intf : this.getInterfaces()) {
            intf.getMethods(result, seen);
        }
        
        return result;
    }
    
    /**
     * Returns an integer that represents the modifiers of the class represented
     * by this {@code Class}. The returned value is a combination of bits
     * defined by constants in the {@link Modifier} class.
     *
     * @return the modifiers of the class represented by this {@code Class}.
     */
    public native int getModifiers();

    /**
     * Returns the name of the class represented by this {@code Class}. For a
     * description of the format which is used, see the class definition of
     * {@link Class}.
     *
     * @return the name of the class represented by this {@code Class}.
     */
    public String getName() {
        if (name == null) {
            String n = getName0();
            if (isPrimitive()) {
                switch (n.charAt(0)) {
                case 'Z':
                    name = "boolean";
                    break;
                case 'B':
                    name = "boolean";
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
    private native String getName0();

    /**
     * Returns the simple name of the class represented by this {@code Class} as
     * defined in the source code. If there is no name (that is, the class is
     * anonymous) then an empty string is returned. If the receiver is an array
     * then the name of the underlying type with square braces appended (for
     * example {@code &quot;Integer[]&quot;}) is returned.
     *
     * @return the simple name of the class represented by this {@code Class}.
     */
    public String getSimpleName() {
        // Start (C) Android
        if (isArray()) {
            return getComponentType().getSimpleName() + "[]";
        }
                
        if (isAnonymousClass()) {
            return "";
        }

        if (isMemberClass() || isLocalClass()) {
            return getInnerClassName();
        }
        
        String name = getName();
        int dot = name.lastIndexOf('.');
        if (dot != -1) {
            return name.substring(dot + 1);
        }
        
        return name;
        // End (C) Android
    }

    // Start (C) Android
    /*
     * Returns the simple name of a member or local class, or null otherwise. 
     * 
     * @return The name.
     */
    private native String getInnerClassName();
    // End (C) Android
    
    /**
     * Returns the {@code ProtectionDomain} of the class represented by this
     * class.
     * <p>
     * Note: In order to conserve space in an embedded target like Android, we
     * allow this method to return {@code null} for classes in the system
     * protection domain (that is, for system classes). System classes are
     * always given full permissions (that is, AllPermission). This can not be
     * changed through the {@link java.security.Policy} class.
     * </p>
     *
     * @return the {@code ProtectionDomain} of the class represented by this
     *         class.
     * @throws SecurityException
     *             if a security manager exists and it does not allow member
     *             access.
     */
    public native ProtectionDomain getProtectionDomain();

    /**
     * Answers the ProtectionDomain of the receiver.
     * 
     * This method is for internal use only.
     * 
     * @return ProtectionDomain the receiver's ProtectionDomain.
     */
    native ProtectionDomain getPDImpl();

    /**
     * Returns the URL of the resource specified by {@code resName}. The mapping
     * between the resource name and the URL is managed by the class' class
     * loader.
     *
     * @param resName
     *            the name of the resource.
     * @return the requested resource's {@code URL} object or {@code null} if
     *         the resource can not be found.
     * @see ClassLoader
     */
    public native URL getResource(String resName);

    /**
     * Returns a read-only stream for the contents of the resource specified by
     * {@code resName}. The mapping between the resource name and the stream is
     * managed by the class' class loader.
     *
     * @param resName
     *            the name of the resource.
     * @return a stream for the requested resource or {@code null} if no
     *         resource with the specified name can be found.
     * @see ClassLoader
     */
    public native InputStream getResourceAsStream(String resName);

    /**
     * Returns the signers for the class represented by this {@code Class} or
     * {@code null} if either there are no signers or this {@code Class}
     * represents a primitive type or void.
     *
     * @return the signers of the class represented by this {@code Class}.
     */
    public native Object[] getSigners();

    /**
     * Returns the {@code Class} object which represents the superclass of the
     * class represented by this {@code Class}. If this {@code Class} represents
     * the {@code Object} class, a primitive type, an interface or void then the
     * method returns {@code null}. If this {@code Class} represents an array
     * class then the {@code Object} class is returned.
     *
     * @return the superclass of the class represented by this {@code Class}.
     */
    public native Class<? super T> getSuperclass();

    /**
     * Returns an array containing {@code TypeVariable} objects for type
     * variables declared by the generic class represented by this {@code
     * Class}. Returns an empty array if the class is not generic.
     *
     * @return an array with the type variables of the class represented by this
     *         class.
     * @since 1.5
     */
    @SuppressWarnings("unchecked")
    public native TypeVariable<Class<T>>[] getTypeParameters();

    /**
     * Indicates whether this {@code Class} represents an annotation class.
     *
     * @return {@code true} if this {@code Class} represents an annotation
     *         class; {@code false} otherwise.
     */
    public native boolean isAnnotation();

    /**
     * Indicates whether the specified annotation is present for the class
     * represented by this {@code Class}.
     *
     * @param annotationClass
     *            the annotation to look for.
     * @return {@code true} if the class represented by this {@code Class} is
     *         annotated with {@code annotationClass}; {@code false} otherwise.
     * @since 1.5
     */
    public native boolean isAnnotationPresent(
            Class<? extends Annotation> annotationClass);

    /**
     * Indicates whether the class represented by this {@code Class} is
     * anonymously declared.
     *
     * @return {@code true} if the class represented by this {@code Class} is
     *         anonymous; {@code false} otherwise.
     * @since 1.5
     */
    public native boolean isAnonymousClass();

    /**
     * Indicates whether the class represented by this {@code Class} is an array
     * class.
     *
     * @return {@code true} if the class represented by this {@code Class} is an
     *         array class; {@code false} otherwise.
     */
    public boolean isArray() {
        return getComponentType() != null;
    }

    /**
     * Indicates whether the specified class type can be converted to the class
     * represented by this {@code Class}. Conversion may be done via an identity
     * conversion or a widening reference conversion (if either the receiver or
     * the argument represent primitive types, only the identity conversion
     * applies).
     *
     * @param cls
     *            the class to check.
     * @return {@code true} if {@code cls} can be converted to the class
     *         represented by this {@code Class}; {@code false} otherwise.
     * @throws NullPointerException
     *             if {@code cls} is {@code null}.
     */
    public native boolean isAssignableFrom(Class<?> cls);

    /**
     * Indicates whether the class represented by this {@code Class} is an
     * {@code enum}.
     *
     * @return {@code true} if the class represented by this {@code Class} is an
     *         {@code enum}; {@code false} otherwise.
     */
    public native boolean isEnum();

    /**
     * Indicates whether the specified object can be cast to the class
     * represented by this {@code Class}. This is the runtime version of the
     * {@code instanceof} operator.
     *
     * @param object
     *            the object to check.
     * @return {@code true} if {@code object} can be cast to the type
     *         represented by this {@code Class}; {@code false} if {@code
     *         object} is {@code null} or cannot be cast.
     */
    public native boolean isInstance(Object object);

    /**
     * Indicates whether this {@code Class} represents an interface.
     *
     * @return {@code true} if this {@code Class} represents an interface;
     *         {@code false} otherwise.
     */
    public native boolean isInterface();

    /**
     * Indicates whether the class represented by this {@code Class} is defined
     * locally.
     *
     * @return {@code true} if the class represented by this {@code Class} is
     *         defined locally; {@code false} otherwise.
     */
    public native boolean isLocalClass();

    /**
     * Indicates whether the class represented by this {@code Class} is a member
     * class.
     *
     * @return {@code true} if the class represented by this {@code Class} is a
     *         member class; {@code false} otherwise.
     */
    public native boolean isMemberClass();

    /**
     * Indicates whether this {@code Class} represents a primitive type.
     *
     * @return {@code true} if this {@code Class} represents a primitive type;
     *         {@code false} otherwise.
     */
    public native boolean isPrimitive();

    /**
     * Indicates whether this {@code Class} represents a synthetic type.
     *
     * @return {@code true} if this {@code Class} represents a synthetic type;
     *         {@code false} otherwise.
     */
    public native boolean isSynthetic();

    /**
     * Returns a new instance of the class represented by this {@code Class},
     * created by invoking the default (that is, zero-argument) constructor. If
     * there is no such constructor, or if the creation fails (either because of
     * a lack of available memory or because an exception is thrown by the
     * constructor), an {@code InstantiationException} is thrown. If the default
     * constructor exists but is not accessible from the context where this
     * method is invoked, an {@code IllegalAccessException} is thrown.
     *
     * @return a new instance of the class represented by this {@code Class}.
     * @throws IllegalAccessException
     *             if the default constructor is not visible.
     * @throws InstantiationException
     *             if the instance can not be created.
     * @throws SecurityException
     *             if a security manager exists and it does not allow creating
     *             new instances.
     */
    public native T newInstance() throws IllegalAccessException,
            InstantiationException;

    @Override
    public String toString() {
        // Start (C) Android
        if (isPrimitive()) {
            return getSimpleName().toLowerCase();
        } else {
            return (isInterface() ? "interface " : "class ") + getName();
        }
        // End (C) Android
    }

    /**
     * Returns the {@code Package} of which the class represented by this
     * {@code Class} is a member. Returns {@code null} if no {@code Package}
     * object was created by the class loader of the class.
     *
     * @return Package the {@code Package} of which this {@code Class} is a
     *         member or {@code null}.
     */
    public native Package getPackage();

    /**
     * Returns the assertion status for the class represented by this {@code
     * Class}. Assertion is enabled / disabled based on the class loader,
     * package or class default at runtime.
     *
     * @return the assertion status for the class represented by this {@code
     *         Class}.
     */
    public native boolean desiredAssertionStatus();

    /**
     * Casts this {@code Class} to represent a subclass of the specified class.
     * If successful, this {@code Class} is returned; otherwise a {@code
     * ClassCastException} is thrown.
     *
     * @param clazz
     *            the required type.
     * @return this {@code Class} cast as a subclass of the given type.
     * @throws ClassCastException
     *             if this {@code Class} cannot be cast to the specified type.
     */
    @SuppressWarnings("unchecked")    
    public <U> Class<? extends U> asSubclass(Class<U> clazz) {
        // Start (C) Android
        if (clazz.isAssignableFrom(this)) {
            return (Class<? extends U>)this;
        }
        throw new ClassCastException();
        // End (C) Android
    }

    /**
     * Casts the specified object to the type represented by this {@code Class}.
     * If the object is {@code null} then the result is also {@code null}.
     *
     * @param obj
     *            the object to cast.
     * @return the object that has been cast.
     * @throws ClassCastException
     *             if the object cannot be cast to the specified type.
     */
    @SuppressWarnings("unchecked")    
    public T cast(Object obj) {
        // Start (C) Android
        if (obj == null) {
            return null;
        } else if (this.isInstance(obj)) {
            return (T)obj;            
        }
        throw new ClassCastException();
        // End (C) Android
    }
}
