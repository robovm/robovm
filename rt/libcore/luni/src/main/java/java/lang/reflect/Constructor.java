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

import libcore.util.EmptyArray;

import org.apache.harmony.luni.lang.reflect.GenericSignatureParser;
import org.apache.harmony.luni.lang.reflect.ListOfTypes;
import org.apache.harmony.luni.lang.reflect.Types;
import org.robovm.rt.VM;

/**
 * This class represents a constructor. Information about the constructor can be
 * accessed, and the constructor can be invoked dynamically.
 *
 * @param <T> the class that declares this constructor
 */
public final class Constructor<T> extends AccessibleObject implements GenericDeclaration,
        Member {

    /*
     * The RoboVM Method* object
     */
    private final long method;
    
    /*
     * Cached fields
     */
    private int modifiers = -1;
    private Class<T> declaringClass;
    private Class<?>[] parameterTypes;
    private Class<?>[] exceptionTypes;    

    ListOfTypes genericExceptionTypes;
    ListOfTypes genericParameterTypes;
    TypeVariable<Constructor<T>>[] formalTypeParameters;
    private volatile boolean genericTypesAreInitialized = false;
    
    Constructor(long method){
        this.method = method;
    }
    
    /**
     * Construct a clone of the given instance.
     *
     * @param orig non-null; the original instance to clone
     */
    /*package*/ Constructor(Constructor<T> orig) {

        this.method = orig.method;
        this.modifiers = orig.modifiers;
        this.declaringClass = orig.declaringClass;
        this.parameterTypes = orig.parameterTypes;
        this.exceptionTypes = orig.exceptionTypes;
        this.genericExceptionTypes = orig.genericExceptionTypes;
        this.genericParameterTypes = orig.genericParameterTypes;
        this.formalTypeParameters = orig.formalTypeParameters;
        this.genericTypesAreInitialized = orig.genericTypesAreInitialized;
        
        // Copy the accessible flag.
        if (orig.flag) {
            this.flag = true;
        }
    }
    
    @SuppressWarnings("unchecked")
    private synchronized void initGenericTypes() {
        if (!genericTypesAreInitialized) {
            String signatureAttribute = getSignatureAttribute();
            GenericSignatureParser parser = new GenericSignatureParser(
                    getDeclaringClass().getClassLoader());
            parser.parseForConstructor(this, signatureAttribute, getExceptionTypes());
            formalTypeParameters = parser.formalTypeParameters;
            genericParameterTypes = parser.parameterTypes;
            genericExceptionTypes = parser.exceptionTypes;
            genericTypesAreInitialized = true;
        }
    }
    
    @Override
    protected String getSignatureAttribute() {
        return Method.getSignatureAttribute(method);
    }
    
    public TypeVariable<Constructor<T>>[] getTypeParameters() {
        initGenericTypes();
        return formalTypeParameters.clone();
    }

    /**
     * Returns the string representation of the constructor's declaration,
     * including the type parameters.
     *
     * @return the string representation of the constructor's declaration
     */
    public String toGenericString() {
        StringBuilder sb = new StringBuilder(80);
        initGenericTypes();
        // append modifiers if any
        int modifier = getModifiers();
        if (modifier != 0) {
            sb.append(Modifier.toString(modifier & ~Modifier.VARARGS)).append(' ');
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
        // append constructor name
        appendTypeName(sb, getDeclaringClass());
        // append parameters
        sb.append('(');
        appendArrayGenericType(sb,
                Types.getClonedTypeArray(genericParameterTypes));
        sb.append(')');
        // append exceptions if any
        Type[] genericExceptionTypeArray =
                Types.getClonedTypeArray(genericExceptionTypes);
        if (genericExceptionTypeArray.length > 0) {
            sb.append(" throws ");
            appendArrayGenericType(sb, genericExceptionTypeArray);
        }
        return sb.toString();
    }

    /**
     * Returns the generic parameter types as an array of {@code Type}
     * instances, in declaration order. If this constructor has no generic
     * parameters, an empty array is returned.
     *
     * @return the parameter types
     *
     * @throws GenericSignatureFormatError
     *             if the generic constructor signature is invalid
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
     * this constructor has no declared exceptions, an empty array will be
     * returned.
     *
     * @return an array of generic exception types
     *
     * @throws GenericSignatureFormatError
     *             if the generic constructor signature is invalid
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

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return Method.getDeclaredAnnotations(method);
    }
    
    /**
     * Returns an array of arrays that represent the annotations of the formal
     * parameters of this constructor. If there are no parameters on this
     * constructor, then an empty array is returned. If there are no annotations
     * set, then an array of empty arrays is returned.
     *
     * @return an array of arrays of {@code Annotation} instances
     */
    public Annotation[][] getParameterAnnotations() {
        Annotation[][] parameterAnnotations = Method.getParameterAnnotations(method);
        if (parameterAnnotations.length == 0) {
            return Method.noAnnotations(getParameterTypes(false).length);
        }
        return parameterAnnotations;
    }

    /**
     * Indicates whether or not this constructor takes a variable number of
     * arguments.
     *
     * @return {@code true} if a vararg is declare, otherwise
     *         {@code false}
     */
    public boolean isVarArgs() {
        return (getModifiers() & Modifier.VARARGS) != 0;
    }

    /**
     * Indicates whether or not this constructor is synthetic (artificially
     * introduced by the compiler).
     *
     * @return {@code true} if this constructor is synthetic, {@code false}
     *         otherwise
     */
    public boolean isSynthetic() {
        return (getModifiers() & Modifier.SYNTHETIC) != 0;
    }

    /**
     * Indicates whether or not the specified {@code object} is equal to this
     * constructor. To be equal, the specified object must be an instance
     * of {@code Constructor} with the same declaring class and parameter types
     * as this constructor.
     *
     * @param object
     *            the object to compare
     *
     * @return {@code true} if the specified object is equal to this
     *         constructor, {@code false} otherwise
     *
     * @see #hashCode
     */
    @Override
    public boolean equals(Object object) {
        return object instanceof Constructor && toString().equals(object.toString());
    }

    /**
     * Returns the class that declares this constructor.
     *
     * @return the declaring class
     */
    public Class<T> getDeclaringClass() {
        if (declaringClass == null) {
            declaringClass = Method.getDeclaringClass(method);
        }
        return declaringClass;
    }

    /**
     * Returns the exception types as an array of {@code Class} instances. If
     * this constructor has no declared exceptions, an empty array will be
     * returned.
     *
     * @return the declared exception classes
     */
    public Class<?>[] getExceptionTypes() {
        return getExceptionTypes(true);
    }
    Class<?>[] getExceptionTypes(boolean copy) {
        if (exceptionTypes == null) {
            exceptionTypes = Method.getExceptionTypes(method);
        }
        return copy ? exceptionTypes.clone() : exceptionTypes;
    }

    /**
     * Returns the modifiers for this constructor. The {@link Modifier} class
     * should be used to decode the result.
     *
     * @return the modifiers for this constructor
     * @see Modifier
     */
    public int getModifiers() {
        if (modifiers == -1) {
            modifiers = Method.getModifiers(method);
        }
        return modifiers;
    }

    /**
     * Returns the name of this constructor.
     *
     * @return the name of this constructor
     */
    public String getName() {
        return getDeclaringClass().getName();
    }

    /**
     * Returns an array of the {@code Class} objects associated with the
     * parameter types of this constructor. If the constructor was declared with
     * no parameters, an empty array will be returned.
     *
     * @return the parameter types
     */
    public Class<?>[] getParameterTypes() {
        return getParameterTypes(true);
    }
    Class<?>[] getParameterTypes(boolean copy) {
        if (parameterTypes == null) {
            parameterTypes = Method.getParameterTypes(method);
        }
        return copy ? parameterTypes.clone() : parameterTypes;
    }

    /**
     * Returns the constructor's signature in non-printable form. This is called
     * (only) from IO native code and needed for deriving the serialVersionUID
     * of the class
     *
     * @return the constructor's signature
     */
    @SuppressWarnings("unused")
    private String getSignature() {
        Class<?>[] parameterTypes = getParameterTypes(false);
        StringBuilder result = new StringBuilder();

        result.append('(');
        for (int i = 0; i < parameterTypes.length; i++) {
            result.append(getSignature(parameterTypes[i]));
        }
        result.append(")V");

        return result.toString();
    }

    /**
     * Returns an integer hash code for this constructor. Constructors which are
     * equal return the same value for this method. The hash code for a
     * Constructor is the hash code of the name of the declaring class.
     *
     * @return the hash code
     *
     * @see #equals
     */
    @Override
    public int hashCode() {
        return getDeclaringClass().getName().hashCode();
    }

    /**
     * Returns a new instance of the declaring class, initialized by dynamically
     * invoking the constructor represented by this {@code Constructor} object.
     * This reproduces the effect of {@code new declaringClass(arg1, arg2, ... ,
     * argN)} This method performs the following:
     * <ul>
     * <li>A new instance of the declaring class is created. If the declaring
     * class cannot be instantiated (i.e. abstract class, an interface, an array
     * type, or a primitive type) then an InstantiationException is thrown.</li>
     * <li>If this Constructor object is enforcing access control (see
     * {@link AccessibleObject}) and this constructor is not accessible from the
     * current context, an IllegalAccessException is thrown.</li>
     * <li>If the number of arguments passed and the number of parameters do not
     * match, an IllegalArgumentException is thrown.</li>
     * <li>For each argument passed:
     * <ul>
     * <li>If the corresponding parameter type is a primitive type, the argument
     * is unboxed. If the unboxing fails, an IllegalArgumentException is
     * thrown.</li>
     * <li>If the resulting argument cannot be converted to the parameter type
     * via a widening conversion, an IllegalArgumentException is thrown.</li>
     * </ul>
     * <li>The constructor represented by this {@code Constructor} object is
     * then invoked. If an exception is thrown during the invocation, it is
     * caught and wrapped in an InvocationTargetException. This exception is
     * then thrown. If the invocation completes normally, the newly initialized
     * object is returned.
     * </ul>
     *
     * @param args
     *            the arguments to the constructor
     *
     * @return the new, initialized, object
     *
     * @exception InstantiationException
     *                if the class cannot be instantiated
     * @exception IllegalAccessException
     *                if this constructor is not accessible
     * @exception IllegalArgumentException
     *                if an incorrect number of arguments are passed, or an
     *                argument could not be converted by a widening conversion
     * @exception InvocationTargetException
     *                if an exception was thrown by the invoked constructor
     *
     * @see AccessibleObject
     */
    @SuppressWarnings("unchecked")
    public T newInstance(Object... args) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        
        Class<T> clazz = getDeclaringClass();
        int clazzModifiers = clazz.getModifiers();
        if (clazz.isArray() || Modifier.isAbstract(clazzModifiers) || Modifier.isInterface(clazzModifiers) || clazz.isPrimitive()) {
            throw new InstantiationException("class " + clazz.getName() + " cannot be instantiated");
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
                Class<?>[] callers = VM.getStackClasses(0, 2);
                // If we've been called from Class.newInstance() we should use the caller's caller
                Class<?> caller = callers[0] == Class.class ? callers[1] : callers[0];
                if (!checkAccessible(caller, this)) {
                    throw new IllegalAccessException(String.format("Attempt to access constructor %s(%s) from class %s", 
                            clazz.getName(), toString(parameterTypes), caller.getName()));
                }
            }
        }
        
        return (T) internalNewInstance(method, pTypes, args);
    }

    private native static Object internalNewInstance(long method, Class<?>[] pTypes, Object[] args) throws InstantiationException, IllegalAccessException,
        IllegalArgumentException, InvocationTargetException;
    
    /**
     * Returns a string containing a concise, human-readable description of this
     * constructor. The format of the string is:
     *
     * <ol>
     *   <li>modifiers (if any)
     *   <li>declaring class name
     *   <li>'('
     *   <li>parameter types, separated by ',' (if any)
     *   <li>')'
     *   <li>'throws' plus exception types, separated by ',' (if any)
     * </ol>
     *
     * For example:
     * {@code public String(byte[],String) throws UnsupportedEncodingException}
     *
     * @return a printable representation for this constructor
     */
    @Override
    public String toString() {
        Class<?> declaringClass = getDeclaringClass();
        Class<?>[] parameterTypes = getParameterTypes(false);
        Class<?>[] exceptionTypes = getExceptionTypes(false);
        
        StringBuilder result = new StringBuilder(Modifier.toString(getModifiers()));

        if (result.length() != 0)
            result.append(' ');
        result.append(declaringClass.getName());
        result.append("(");
        result.append(toString(parameterTypes));
        result.append(")");
        if (exceptionTypes != null && exceptionTypes.length != 0) {
            result.append(" throws ");
            result.append(toString(exceptionTypes));
        }
        
        return result.toString();
    }
}
