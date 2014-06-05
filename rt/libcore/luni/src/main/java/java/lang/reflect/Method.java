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

package java.lang.reflect;

import java.lang.annotation.Annotation;
import java.util.Comparator;
import libcore.util.EmptyArray;
import org.apache.harmony.luni.lang.reflect.GenericSignatureParser;
import org.apache.harmony.luni.lang.reflect.ListOfTypes;
import org.apache.harmony.luni.lang.reflect.Types;
import org.robovm.rt.VM;

/**
 * This class represents a method. Information about the method can be accessed,
 * and the method can be invoked dynamically.
 */
public final class Method extends AccessibleObject implements GenericDeclaration, Member {

    /**
     * Orders methods by their name, parameters and return type.
     *
     * @hide
     */
    public static final Comparator<Method> ORDER_BY_SIGNATURE = new Comparator<Method>() {
        public int compare(Method a, Method b) {
            int comparison = a.name.compareTo(b.name);
            if (comparison != 0) {
                return comparison;
            }
    
            Class<?>[] aParameters = a.parameterTypes;
            Class<?>[] bParameters = b.parameterTypes;
            int length = Math.min(aParameters.length, bParameters.length);
            for (int i = 0; i < length; i++) {
                comparison = aParameters[i].getName().compareTo(bParameters[i].getName());
                if (comparison != 0) {
                    return comparison;
                }
            }
    
            if (aParameters.length != bParameters.length) {
                return aParameters.length - bParameters.length;
            }

            // this is necessary for methods that have covariant return types.
            return a.getReturnType().getName().compareTo(b.getReturnType().getName());
        }
    };

    /*
     * The RoboVM Method* object
     */
    private final long method;

    /*
     * Cached fields
     */
    private int modifiers = -1;
    private Class<?> declaringClass;
    private String name;
    private Class<?>[] parameterTypes;
    private Class<?>[] exceptionTypes;
    private Class<?> returnType;
    private Object defaultValue;
    private Annotation[] declaredAnnotations;
    private Annotation[][] parameterAnnotations;

    private ListOfTypes genericExceptionTypes;
    private ListOfTypes genericParameterTypes;
    private Type genericReturnType;
    private TypeVariable<Method>[] formalTypeParameters;
    private volatile boolean genericTypesAreInitialized = false;
    
    @SuppressWarnings("unchecked")
    private synchronized void initGenericTypes() {
        if (!genericTypesAreInitialized) {
            String signatureAttribute = getSignatureAttribute();
            GenericSignatureParser parser = new GenericSignatureParser(
                    getDeclaringClass().getClassLoader());
            parser.parseForMethod(this, signatureAttribute, getExceptionTypes());
            formalTypeParameters = parser.formalTypeParameters;
            genericParameterTypes = parser.parameterTypes;
            genericExceptionTypes = parser.exceptionTypes;
            genericReturnType = parser.returnType;
            genericTypesAreInitialized = true;
        }
    }
    
    Method(long method) {
        this.method = method;
    }
    
    /**
     * Construct a clone of the given instance.
     *
     * @param orig non-null; the original instance to clone
     */
    /*package*/ Method(Method orig) {

        this.method = orig.method;
        this.modifiers = orig.modifiers;
        this.declaringClass = orig.declaringClass;
        this.name = orig.name;
        this.parameterTypes = orig.parameterTypes;
        this.exceptionTypes = orig.exceptionTypes;
        this.returnType = orig.returnType;
        this.defaultValue = orig.defaultValue;
        this.genericExceptionTypes = orig.genericExceptionTypes;
        this.genericParameterTypes = orig.genericParameterTypes;
        this.genericReturnType = orig.genericReturnType;
        this.formalTypeParameters = orig.formalTypeParameters;
        this.genericTypesAreInitialized = orig.genericTypesAreInitialized;
        this.declaredAnnotations = orig.declaredAnnotations;
        this.parameterAnnotations = orig.parameterAnnotations;
        
        // Copy the accessible flag.
        if (orig.flag) {
            this.flag = true;
        }
    }
    
    public TypeVariable<Method>[] getTypeParameters() {
        initGenericTypes();
        return formalTypeParameters.clone();        
    }

    @Override
    protected String getSignatureAttribute() {
        return getSignatureAttribute(method);
    }
    
    final native static String getSignatureAttribute(long method);
    
    /**
     * Returns the string representation of the method's declaration, including
     * the type parameters.
     *
     * @return the string representation of this method
     */
    public String toGenericString() {
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
                    sb.append(",");
                }
            }
            sb.append("> ");
        }
        // append return type
        appendGenericType(sb, Types.getType(genericReturnType));
        sb.append(' ');
        // append method name
        appendTypeName(sb, getDeclaringClass());
        sb.append(".").append(getName());
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
     */
    public Type getGenericReturnType() {
        initGenericTypes();
        return Types.getType(genericReturnType);
    }
    
    @Override
    protected Annotation[] getDeclaredAnnotations(boolean copy) {
        if (declaredAnnotations == null) {
            declaredAnnotations = getDeclaredAnnotations(method);
        }
        return copy ? declaredAnnotations.clone() : declaredAnnotations;
    }
    static final native Annotation[] getDeclaredAnnotations(long method);

    private static final Annotation[] NO_ANNOTATIONS = new Annotation[0];

    /**
     * Creates an array of empty Annotation arrays.
     */
    /*package*/ static Annotation[][] noAnnotations(int size) {
        Annotation[][] annotations = new Annotation[size][];
        for (int i = 0; i < size; i++) {
            annotations[i] = NO_ANNOTATIONS;
        }
        return annotations;
    }
    
    /**
     * Returns an array of arrays that represent the annotations of the formal
     * parameters of this method. If there are no parameters on this method,
     * then an empty array is returned. If there are no annotations set, then
     * and array of empty arrays is returned.
     *
     * @return an array of arrays of {@code Annotation} instances
     */
    public Annotation[][] getParameterAnnotations() {
        if (parameterAnnotations == null) {
            Annotation[][] pa = getParameterAnnotations(method);
            if (pa.length == 0) {
                pa = noAnnotations(getParameterTypes(false).length);
            }
            parameterAnnotations = pa;
        }
        return parameterAnnotations.clone();
    }
    final static native Annotation[][] getParameterAnnotations(long method);
    
    /**
     * Indicates whether or not this method takes a variable number argument.
     *
     * @return {@code true} if a vararg is declared, {@code false} otherwise
     */
    public boolean isVarArgs() {
        return (getModifiers() & Modifier.VARARGS) != 0;
    }

    /**
     * Indicates whether or not this method is a bridge.
     *
     * @return {@code true} if this method is a bridge, {@code false} otherwise
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
     *
     * @return {@code true} if the specified object is equal to this
     *         method, {@code false} otherwise
     *
     * @see #hashCode
     */
    @Override
    public boolean equals(Object object) {
        return object instanceof Method && toString().equals(object.toString());
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
    final Class<?>[] getParameterTypes(boolean copy) {
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
     * Returns the result of dynamically invoking this method. Equivalent to
     * {@code receiver.methodName(arg1, arg2, ... , argN)}.
     *
     * <p>If the method is static, the receiver argument is ignored (and may be null).
     *
     * <p>If the method takes no arguments, you can pass {@code (Object[]) null} instead of
     * allocating an empty array.
     *
     * <p>If you're calling a varargs method, you need to pass an {@code Object[]} for the
     * varargs parameter: that conversion is usually done in {@code javac}, not the VM, and
     * the reflection machinery does not do this for you. (It couldn't, because it would be
     * ambiguous.)
     *
     * <p>Reflective method invocation follows the usual process for method lookup.
     *
     * <p>If an exception is thrown during the invocation it is caught and
     * wrapped in an InvocationTargetException. This exception is then thrown.
     *
     * <p>If the invocation completes normally, the return value itself is
     * returned. If the method is declared to return a primitive type, the
     * return value is boxed. If the return type is void, null is returned.
     *
     * @param receiver
     *            the object on which to call this method (or null for static methods)
     * @param args
     *            the arguments to the method
     * @return the result
     *
     * @throws NullPointerException
     *             if {@code receiver == null} for a non-static method
     * @throws IllegalAccessException
     *             if this method is not accessible (see {@link AccessibleObject})
     * @throws IllegalArgumentException
     *             if the number of arguments doesn't match the number of parameters, the receiver
     *             is incompatible with the declaring class, or an argument could not be unboxed
     *             or converted by a widening conversion to the corresponding parameter type
     * @throws InvocationTargetException
     *             if an exception was thrown by the invoked method
     */
    public Object invoke(Object receiver, Object... args)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        if (!Modifier.isStatic(getModifiers())) {
            Class<?> expectedClass = getDeclaringClass();
            if (receiver == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("expected receiver of type ");
                appendTypeName(sb, expectedClass);
                sb.append(", but got null");
                throw new NullPointerException(sb.toString());
            }
            if (!getDeclaringClass().isInstance(receiver)) {
                StringBuilder sb = new StringBuilder();
                sb.append("expected receiver of type ");
                appendTypeName(sb, expectedClass);
                sb.append(", but got ");
                appendTypeName(sb, receiver.getClass());
                throw new IllegalArgumentException(sb.toString());
            }
        }
        
        if (args == null) {
            args = EmptyArray.OBJECT;
        }
        
        Class<?>[] pTypes = getParameterTypes(false);
        if (args.length != pTypes.length) {
            throw new IllegalArgumentException("wrong number of arguments; " 
                        + "expected " + pTypes.length + ", got " + args.length);
        }
        
        if (!flag) {
            // Check access
            if (!checkAccessibleFast(this)) {
                Class<?> caller = VM.getStackClasses(0, 1)[0];
                if (!checkAccessible(caller, this)) {
                    throw new IllegalAccessException(String.format("Attempt to access method %s.%s(%s) from class %s", 
                            getDeclaringClass().getName(), getName(), toString(parameterTypes), caller.getName()));
                }
            }
        }
        
        return internalInvoke(method, pTypes, receiver, args);
    }
    
    private static native Object internalInvoke(long method, Class<?>[] pTypes, Object receiver, Object[] args)
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
        Class<?>[] parameterTypes = getParameterTypes(false);
        Class<?>[] exceptionTypes = getExceptionTypes(false);
        
        StringBuilder result = new StringBuilder(Modifier.toString(getModifiers()));

        if (result.length() != 0)
            result.append(' ');
        appendTypeName(result, returnType);
        result.append(' ');
        result.append(declaringClass.getName());
        result.append('.');
        result.append(name);
        result.append("(");
        result.append(toString(parameterTypes));
        result.append(")");
        if (exceptionTypes != null && exceptionTypes.length != 0) {
            result.append(" throws ");
            result.append(toString(exceptionTypes));
            }

        return result.toString();
        }
        
    /**
     * Returns the constructor's signature in non-printable form. This is called
     * (only) from IO native code and needed for deriving the serialVersionUID
     * of the class
     *
     * @return The constructor's signature.
     */
    @SuppressWarnings("unused")
    private String getSignature() {
        Class<?> returnType = getReturnType();
        Class<?>[] parameterTypes = getParameterTypes(false);
        StringBuilder result = new StringBuilder();

        result.append('(');
        for (int i = 0; i < parameterTypes.length; i++) {
            result.append(getSignature(parameterTypes[i]));
        }
        result.append(')');
        result.append(getSignature(returnType));

        return result.toString();
    }

}
