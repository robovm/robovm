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

package java.lang.reflect;

import java.lang.annotation.Annotation;

import org.apache.harmony.luni.lang.reflect.GenericSignatureParser;
import org.apache.harmony.luni.lang.reflect.ListOfTypes;
import org.apache.harmony.luni.lang.reflect.Types;

/**
 * This class represents a method. Information about the method can be accessed,
 * and the method can be invoked dynamically.
 */
public final class Method extends AccessibleObject implements GenericDeclaration, Member {
    /*
     * The NullVM Method* object
     */
    private final long method;
    
    private static final Annotation[] NO_ANNOTATIONS = new Annotation[0];
    
    /*
     * Cached fields
     */
    private int modifiers = -1;
    private Class<?> declaringClass;
    private String name;
    private Class<?> returnType;
    private Class<?>[] parameterTypes;
    private Class<?>[] exceptionTypes;
    private Object defaultValue;

    private ListOfTypes genericExceptionTypes;
    private ListOfTypes genericParameterTypes;
    private Type genericReturnType;
    private TypeVariable<Method>[] formalTypeParameters;
    private volatile boolean genericTypesAreInitialized = false;
    
    @SuppressWarnings("unchecked")
    private synchronized void initGenericTypes() {
        // Start (C) Android
        if (!genericTypesAreInitialized) {
            String signatureAttribute = getSignatureAttribute();
            GenericSignatureParser parser = new GenericSignatureParser(
                    getDeclaringClass().getClassLoader());
            parser.parseForMethod(this, signatureAttribute, getExceptionTypes(false));
            formalTypeParameters = parser.formalTypeParameters;
            genericParameterTypes = parser.parameterTypes;
            genericExceptionTypes = parser.exceptionTypes;
            genericReturnType = parser.returnType;
            genericTypesAreInitialized = true;
        }
        // End (C) Android
    }
    
    Method(long method) {
        this.method = method;
    }
    
    public TypeVariable<Method>[] getTypeParameters() {
        initGenericTypes();
        return formalTypeParameters.clone();        
    }

    /**
     * Returns the string representation of the method's declaration, including
     * the type parameters.
     *
     * @return the string representation of this method
     * @since 1.5
     */
    public String toGenericString() {
        // Start (C) Android
        StringBuilder sb = new StringBuilder(80);

        initGenericTypes();

        // append modifiers if any
        int modifier = getModifiers();
        if (modifier != 0) {
            sb.append(Modifier.toString(modifier & ~(Modifier.BRIDGE +
                    Modifier.VARARGS))).append(' ');
        }
        // append type parameters
        if (formalTypeParameters != null && formalTypeParameters.length > 0) {
            sb.append('<');
            for (int i = 0; i < formalTypeParameters.length; i++) {
                appendGenericType(sb, formalTypeParameters[i]);
                if (i < formalTypeParameters.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("> ");
        }
        // append return type
        appendGenericType(sb, Types.getType(genericReturnType));
        sb.append(' ');
        // append method name
        appendArrayType(sb, getDeclaringClass());
        sb.append("."+getName());
        // append parameters
        sb.append('(');
        appendArrayGenericType(sb,
                Types.getClonedTypeArray(genericParameterTypes));
        sb.append(')');
        // append exceptions if any
        Type[] genericExceptionTypeArray = Types.getClonedTypeArray(
                genericExceptionTypes);
        if (genericExceptionTypeArray.length > 0) {
            sb.append(" throws ");
            appendArrayGenericType(sb, genericExceptionTypeArray);
        }
        return sb.toString();
        // End (C) Android
    }

    /**
     * Returns the parameter types as an array of {@code Type} instances, in
     * declaration order. If this method has no parameters, an empty array is
     * returned.
     *
     * @return the parameter types
     *
     * @throws GenericSignatureFormatError
     *             if the generic method signature is invalid
     * @throws TypeNotPresentException
     *             if any parameter type points to a missing type
     * @throws MalformedParameterizedTypeException
     *             if any parameter type points to a type that cannot be
     *             instantiated for some reason
     * @since 1.5
     */
    public Type[] getGenericParameterTypes() {
        initGenericTypes();
        return Types.getClonedTypeArray(genericParameterTypes);        
    }

    /**
     * Returns the exception types as an array of {@code Type} instances. If
     * this method has no declared exceptions, an empty array will be returned.
     *
     * @return an array of generic exception types
     *
     * @throws GenericSignatureFormatError
     *             if the generic method signature is invalid
     * @throws TypeNotPresentException
     *             if any exception type points to a missing type
     * @throws MalformedParameterizedTypeException
     *             if any exception type points to a type that cannot be
     *             instantiated for some reason
     * @since 1.5
     */
    public Type[] getGenericExceptionTypes() {
        initGenericTypes();
        return Types.getClonedTypeArray(genericExceptionTypes);
    }

    /**
     * Returns the return type of this method as a {@code Type} instance.
     *
     * @return the return type of this method
     *
     * @throws GenericSignatureFormatError
     *             if the generic method signature is invalid
     * @throws TypeNotPresentException
     *             if the return type points to a missing type
     * @throws MalformedParameterizedTypeException
     *             if the return type points to a type that cannot be
     *             instantiated for some reason
     * @since 1.5
     */
    public Type getGenericReturnType() {
        initGenericTypes();
        return Types.getType(genericReturnType);
    }
    /**
     * Returns an array of arrays that represent the annotations of the formal
     * parameters of this method. If there are no parameters on this method,
     * then an empty array is returned. If there are no annotations set, then
     * and array of empty arrays is returned.
     *
     * @return an array of arrays of {@code Annotation} instances
     * @since 1.5
     */
    public Annotation[][] getParameterAnnotations() {
        Annotation[][] parameterAnnotations = getParameterAnnotations(method);
        if (parameterAnnotations.length == 0) {
            return noAnnotations(getParameterTypes(false).length);
        }
        return parameterAnnotations;
    }
    
    /**
     * Creates an array of empty Annotation arrays.
     */
    static Annotation[][] noAnnotations(int size) {
        // Start (C) Android
        Annotation[][] annotations = new Annotation[size][];
        for (int i = 0; i < size; i++) {
            annotations[i] = NO_ANNOTATIONS;
        }
        return annotations;
        // End (C) Android
    }
    
    private static native Annotation[][] getParameterAnnotations(long method);
    
    /**
     * Indicates whether or not this method takes a variable number argument.
     *
     * @return {@code true} if a vararg is declared, {@code false} otherwise
     * @since 1.5
     */
    public boolean isVarArgs() {
        return (getModifiers() & Modifier.VARARGS) != 0;
    }

    /**
     * Indicates whether or not this method is a bridge.
     *
     * @return {@code true} if this method is a bridge, {@code false} otherwise
     * @since 1.5
     */
    public boolean isBridge() {
        return (getModifiers() & Modifier.BRIDGE) != 0;
    }

    /**
     * Indicates whether or not this method is synthetic.
     *
     * @return {@code true} if this method is synthetic, {@code false} otherwise
     */
    public boolean isSynthetic() {
        return (getModifiers() & Modifier.SYNTHETIC) != 0;
    }

    /**
     * Returns the default value for the annotation member represented by this
     * method.
     *
     * @return the default value, or {@code null} if none
     *
     * @throws TypeNotPresentException
     *             if this annotation member is of type {@code Class} and no
     *             definition can be found
     * @since 1.5
     */
    public Object getDefaultValue() {
        if (defaultValue == null) {
            defaultValue = getDefaultValue(method);
        }
        return defaultValue;
    }
    final native static Object getDefaultValue(long method);

    /**
     * Indicates whether or not the specified {@code object} is equal to this
     * method. To be equal, the specified object must be an instance
     * of {@code Method} with the same declaring class and parameter types
     * as this method.
     *
     * @param object
     *            the object to compare
     * @return {@code true} if the specified object is equal to this
     *         method, {@code false} otherwise
     * @see #hashCode
     */
    @Override
    public boolean equals(Object object) {
        return object instanceof Method && toString().equals(object);
    }

    /**
     * Returns the class that declares this method.
     *
     * @return the declaring class
     */
    public Class<?> getDeclaringClass() {
        if (declaringClass == null) {
            declaringClass = getDeclaringClass(method);
        }
        return declaringClass;
    }
    final native static <T> Class<T> getDeclaringClass(long method);

    /**
     * Returns the exception types as an array of {@code Class} instances. If
     * this method has no declared exceptions, an empty array is returned.
     *
     * @return the declared exception classes
     */
    public Class<?>[] getExceptionTypes() {
        return getExceptionTypes(true);
    }
    private Class<?>[] getExceptionTypes(boolean copy) {
        if (exceptionTypes == null) {
            exceptionTypes = getExceptionTypes(method);
        }
        return copy ? exceptionTypes.clone() : exceptionTypes;
    }
    final native static Class<?>[] getExceptionTypes(long method);

    /**
     * Returns the modifiers for this method. The {@link Modifier} class should
     * be used to decode the result.
     *
     * @return the modifiers for this method
     *
     * @see Modifier
     */
    public int getModifiers() {
        if (modifiers == -1) {
            modifiers = getModifiers(method);
        }
        return modifiers;
    }
    final native static int getModifiers(long method);

    /**
     * Returns the name of the method represented by this {@code Method}
     * instance.
     *
     * @return the name of this method
     */
    public String getName() {
        if (name == null) {
            name = getName(method);
        }
        return name;
    }
    private native static String getName(long method);

    /**
     * Returns an array of {@code Class} objects associated with the parameter
     * types of this method. If the method was declared with no parameters, an
     * empty array will be returned.
     *
     * @return the parameter types
     */
    public Class<?>[] getParameterTypes() {
        return getParameterTypes(true);
    }
    private Class<?>[] getParameterTypes(boolean copy) {
        if (parameterTypes == null) {
            parameterTypes = getParameterTypes(method);
        }
        return copy ? parameterTypes.clone() : parameterTypes;
    }
    final native static Class<?>[] getParameterTypes(long method);

    /**
     * Returns the {@code Class} associated with the return type of this
     * method.
     *
     * @return the return type
     */
    public Class<?> getReturnType() {
        if (returnType == null) {
            returnType = getReturnType(method);
        }
        return returnType;
    }
    private native static Class<?> getReturnType(long method);

    /**
     * Returns an integer hash code for this method. Objects which are equal
     * return the same value for this method. The hash code for this Method is
     * the hash code of the name of this method.
     *
     * @return hash code for this method
     *
     * @see #equals
     */
    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    /**
     * Returns the result of dynamically invoking this method. This reproduces
     * the effect of {@code receiver.methodName(arg1, arg2, ... , argN)} This
     * method performs the following:
     * <ul>
     * <li>If this method is static, the receiver argument is ignored.</li>
     * <li>Otherwise, if the receiver is null, a NullPointerException is thrown.
     * </li>
     * <li>If the receiver is not an instance of the declaring class of the
     * method, an IllegalArgumentException is thrown.</li>
     * <li>If this Method object is enforcing access control (see
     * AccessibleObject) and this method is not accessible from the current
     * context, an IllegalAccessException is thrown.</li>
     * <li>If the number of arguments passed and the number of parameters do not
     * match, an IllegalArgumentException is thrown.</li>
     * <li>For each argument passed:
     * <ul>
     * <li>If the corresponding parameter type is a primitive type, the argument
     * is unwrapped. If the unwrapping fails, an IllegalArgumentException is
     * thrown.</li>
     * <li>If the resulting argument cannot be converted to the parameter type
     * via a widening conversion, an IllegalArgumentException is thrown.</li>
     * </ul>
     * <li>If this method is static, it is invoked directly. If it is
     * non-static, this method and the receiver are then used to perform a
     * standard dynamic method lookup. The resulting method is then invoked.</li>
     * <li>If an exception is thrown during the invocation it is caught and
     * wrapped in an InvocationTargetException. This exception is then thrown.</li>
     * <li>If the invocation completes normally, the return value itself is
     * returned. If the method is declared to return a primitive type, the
     * return value is first wrapped. If the return type is void, null is
     * returned.</li>
     * </ul>
     *
     * @param receiver
     *            the object on which to call this method
     * @param args
     *            the arguments to the method
     *
     * @return the new, initialized, object
     *
     * @throws NullPointerException
     *             if the receiver is null for a non-static method
     * @throws IllegalAccessException
     *             if this method is not accessible
     * @throws IllegalArgumentException
     *             if an incorrect number of arguments are passed, the receiver
     *             is incompatible with the declaring class, or an argument
     *             could not be converted by a widening conversion
     * @throws InvocationTargetException
     *             if an exception was thrown by the invoked method
     *
     * @see AccessibleObject
     */
    public Object invoke(Object receiver, Object... args)
            throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        if (args == null) {
            args = new Object[0];
        }
        
        return invoke(method, flag, receiver, args);
    }
    
    private static native Object invoke(long method, boolean flag, Object receiver, Object... args)
            throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException;
    
    /**
     * Returns a string containing a concise, human-readable description of this
     * method. The format of the string is:
     *
     * <ol>
     *   <li>modifiers (if any)
     *   <li>return type or 'void'
     *   <li>declaring class name
     *   <li>'('
     *   <li>parameter types, separated by ',' (if any)
     *   <li>')'
     *   <li>'throws' plus exception types, separated by ',' (if any)
     * </ol>
     *
     * For example: {@code public native Object
     * java.lang.Method.invoke(Object,Object) throws
     * IllegalAccessException,IllegalArgumentException
     * ,InvocationTargetException}
     *
     * @return a printable representation for this method
     */
    @Override
    public String toString() {
        Class<?> returnType = getReturnType();
        Class<?> declaringClass = getDeclaringClass();
        String name = getName();
        Class<?>[] pTypes = getParameterTypes(false);
        Class<?>[] eTypes = getExceptionTypes(false);
        
        StringBuilder sb = new StringBuilder(Modifier.toString(getModifiers()));

        if (sb.length() > 0) {
            sb.append(' ');
        }
        sb.append(returnType.getName());
        sb.append(' ');
        sb.append(declaringClass.getName());
        sb.append('.');
        sb.append(name);
        sb.append('(');
        if (pTypes.length > 0) {
            sb.append(pTypes[0].getCanonicalName());
            for (int i = 1; i < pTypes.length; i++) {
                sb.append(',');
                sb.append(pTypes[i].getCanonicalName());
            }
        }
        sb.append(')');
        if (eTypes.length > 0) {
            sb.append(" throws ");
            sb.append(eTypes[0].getCanonicalName());
            for (int i = 1; i < eTypes.length; i++) {
                sb.append(',');
                sb.append(eTypes[i].getCanonicalName());
            }
        }
        
        return sb.toString();
    }
}
