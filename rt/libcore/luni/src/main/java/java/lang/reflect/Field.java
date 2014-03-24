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
import org.apache.harmony.luni.lang.reflect.GenericSignatureParser;
import org.apache.harmony.luni.lang.reflect.Types;
import org.robovm.rt.VM;

/**
 * This class represents a field. Information about the field can be accessed,
 * and the field's value can be accessed dynamically.
 */
public final class Field extends AccessibleObject implements Member {
    
    /**
     * Orders fields by their name and declaring class.
     *
     * @hide
     */
    public static final Comparator<Field> ORDER_BY_NAME_AND_DECLARING_CLASS
            = new Comparator<Field>() {
        @Override public int compare(Field a, Field b) {
            int comparison = a.name.compareTo(b.name);
            if (comparison != 0) {
                return comparison;
            }

            return a.getDeclaringClass().getName().compareTo(b.getDeclaringClass().getName());
        }
    };
    
    /*
     * The RoboVM Field* object
     */
    private final long field;
    
    /*
     * Cached fields
     */
    private int modifiers = -1;
    private Class<?> declaringClass;
    private String name;
    private Class<?> type;

    private Type genericType;
    private volatile boolean genericTypesAreInitialized = false;
    
    Field(long field){
        this.field = field;
    }
    
    /**
     * Construct a clone of the given instance.
     *
     * @param orig non-null; the original instance to clone
     */
    /*package*/ Field(Field orig) {
        this.field = orig.field;
        this.modifiers = orig.modifiers;
        this.declaringClass = orig.declaringClass;
        this.name = orig.name;
        this.type = orig.type;
        this.genericType = orig.genericType;
        this.genericTypesAreInitialized = orig.genericTypesAreInitialized;

        // Copy the accessible flag.
        if (orig.flag) {
            this.flag = true;
        }
    }
    
    private synchronized void initGenericType() {
        if (!genericTypesAreInitialized) {
            String signatureAttribute = getSignatureAttribute();
            GenericSignatureParser parser = new GenericSignatureParser(
                    getDeclaringClass().getClassLoader());
            parser.parseForField(this.getDeclaringClass(), signatureAttribute);
            genericType = parser.fieldType;
            if (genericType == null) {
                genericType = getType();
            }
            genericTypesAreInitialized = true;
        }
    }
    
    @Override
    protected String getSignatureAttribute() {
        return getSignatureAttribute(field);
    }
    
    private final native static String getSignatureAttribute(long field);


    /**
     * Indicates whether or not this field is synthetic.
     *
     * @return {@code true} if this field is synthetic, {@code false} otherwise
     */
    public boolean isSynthetic() {
        return (getModifiers() & Modifier.SYNTHETIC) != 0;
    }

    /**
     * Returns the string representation of this field, including the field's
     * generic type.
     *
     * @return the string representation of this field
     */
    public String toGenericString() {
        StringBuilder sb = new StringBuilder(80);
        // append modifiers if any
        int modifier = getModifiers();
        if (modifier != 0) {
            sb.append(Modifier.toString(modifier)).append(' ');
        }
        // append generic type
        appendGenericType(sb, getGenericType());
        sb.append(' ');
        // append full field name
        sb.append(getDeclaringClass().getName()).append('.').append(getName());
        return sb.toString();
    }

    /**
     * Indicates whether or not this field is an enumeration constant.
     *
     * @return {@code true} if this field is an enumeration constant, {@code
     *         false} otherwise
     */
    public boolean isEnumConstant() {
        return (getModifiers() & Modifier.ENUM) != 0;
    }

    /**
     * Returns the generic type of this field.
     *
     * @return the generic type
     * @throws GenericSignatureFormatError
     *             if the generic field signature is invalid
     * @throws TypeNotPresentException
     *             if the generic type points to a missing type
     * @throws MalformedParameterizedTypeException
     *             if the generic type points to a type that cannot be
     *             instantiated for some reason
     */
    public Type getGenericType() {
        initGenericType();
        return Types.getType(genericType);
    }

    @Override
    public Annotation[] getDeclaredAnnotations() {
        return getDeclaredAnnotations(field);
    }
    private static final native Annotation[] getDeclaredAnnotations(long field);
    
    /**
     * Indicates whether or not the specified {@code object} is equal to this
     * field. To be equal, the specified object must be an instance of
     * {@code Field} with the same declaring class, type and name as this field.
     *
     * @param object
     *            the object to compare
     * @return {@code true} if the specified object is equal to this method,
     *         {@code false} otherwise
     * @see #hashCode
     */
    @Override
    public boolean equals(Object object) {
        return object instanceof Field && toString().equals(object.toString());
    }

    private void checkAccess(Object receiver, boolean setter) throws IllegalAccessException {
        if (!flag) {
            int mod = getModifiers();
            if (setter && (mod & Modifier.FINAL) > 0) {
                throw new IllegalAccessException("Cannot set final field");
            }
            // Check access
            if (!checkAccessibleFast(this)) {
                Class<?> caller = VM.getStackClasses(1, 1)[0];
                if (!checkAccessible(caller, this)) {
                    throw new IllegalAccessException(String.format(
                            "Attempt to access field %s.%s from class %s", 
                            getDeclaringClass().getName(), getName(), caller.getName()));
                }
                
                if ((getModifiers() & Modifier.PROTECTED) > 0) {
                    boolean isInstance = caller.isInstance(receiver);
                    boolean samePackage = isSamePackage(getDeclaringClass(), caller);
                    if (!isInstance && !samePackage) {
                        throw new IllegalAccessException(String.format(
                                "Attempt to access protected field %s.%s from class %s", 
                                getDeclaringClass().getName(), getName(), caller.getName()));
                    }
                }
            }
        }
    }
    
    private void checkReceiver(Object object) {
        int mod = getModifiers();
        if ((mod & Modifier.STATIC) == 0) { 
            if (object == null) {
                throw new NullPointerException();
            }
            if (!getDeclaringClass().isInstance(object)) {
                throw new IllegalArgumentException("Receiver is not compatible " 
                        + "with declaring class");
            }
        }
    }

    private void throwGetConversionException(Class<?> fieldType, Class<?> expectedType) {
        throw new IllegalArgumentException("Cannot convert " + fieldType + " field " 
                + getDeclaringClass().getName() + "." + getName() + " to " 
                + expectedType);
    }
    
    private void throwSetConversionException(Class<?> fieldType, Class<?> valueType) {
        throw new IllegalArgumentException("Cannot set " + fieldType + " field " 
                + getDeclaringClass().getName() + "." + getName() + " to " 
                + valueType);
    }
    
    private double getDouble(Object object, Class<?> fieldType, Class<?> expectedType) {
        if (fieldType == Double.TYPE) {
            return getD(object, field);
        }
        return getFloat(object, fieldType, expectedType); 
    }
    
    private float getFloat(Object object, Class<?> fieldType, Class<?> expectedType) {
        if (fieldType == Float.TYPE) {
            return getF(object, field);
        }
        return getLong(object, fieldType, expectedType); 
    }
    
    private long getLong(Object object, Class<?> fieldType, Class<?> expectedType) {
        if (fieldType == Long.TYPE) {
            return getJ(object, field);
        }
        return getInt(object, fieldType, expectedType);
    }
    
    private int getInt(Object object, Class<?> fieldType, Class<?> expectedType) {
        if (fieldType == Integer.TYPE) {
            return getI(object, field);
        }
        if (fieldType == Character.TYPE) {
            return getC(object, field);
        }
        return getShort(object, fieldType, expectedType);
    }
    
    private char getChar(Object object, Class<?> fieldType, Class<?> expectedType) {
        if (fieldType == Character.TYPE) {
            return getC(object, field);
        }
        throwGetConversionException(fieldType, expectedType);
        return 0;
    }

    private short getShort(Object object, Class<?> fieldType, Class<?> expectedType) {
        if (fieldType == Short.TYPE) {
            return getS(object, field);
        }
        return getByte(object, fieldType, expectedType);
    }

    private byte getByte(Object object, Class<?> fieldType, Class<?> expectedType) {
        if (fieldType == Byte.TYPE) {
            return getB(object, field);
        }
        throwGetConversionException(fieldType, expectedType);
        return 0;
    }
    
    private boolean getBoolean(Object object, Class<?> fieldType, Class<?> expectedType) {
        if (fieldType == Boolean.TYPE) {
            return getZ(object, field);
        }
        throwGetConversionException(fieldType, expectedType);
        return false;
    }
    
    private void setDouble(Object object, double value, Class<?> fieldType, Class<?> valueType) {
        if (fieldType == Double.TYPE) {
            setD(object, field, value);
        } else {
            throwSetConversionException(fieldType, valueType);
        }
    }

    private void setFloat(Object object, float value, Class<?> fieldType, Class<?> valueType) {
        if (fieldType == Float.TYPE) {
            setF(object, field, value);
        } else {
            setDouble(object, value, fieldType, valueType);
        }
    }

    private void setLong(Object object, long value, Class<?> fieldType, Class<?> valueType) {
        if (fieldType == Long.TYPE) {
            setJ(object, field, value);
        } else {
            setFloat(object, value, fieldType, valueType);
        }
    }
    
    private void setInt(Object object, int value, Class<?> fieldType, Class<?> valueType) {
        if (fieldType == Integer.TYPE) {
            setI(object, field, value);
        } else {
            setLong(object, value, fieldType, valueType);
        }
    }
    
    private void setChar(Object object, char value, Class<?> fieldType, Class<?> valueType) {
        if (fieldType == Character.TYPE) {
            setC(object, field, value);
        } else {
            setInt(object, value, fieldType, valueType);
        }
    }

    private void setShort(Object object, short value, Class<?> fieldType, Class<?> valueType) {
        if (fieldType == Short.TYPE) {
            setS(object, field, value);
        } else {
            setInt(object, value, fieldType, valueType);
        }
    }
    
    private void setByte(Object object, byte value, Class<?> fieldType, Class<?> valueType) {
        if (fieldType == Byte.TYPE) {
            setB(object, field, value);
        } else {
            setShort(object, value, fieldType, valueType);
        }
    }
    
    private void setBoolean(Object object, boolean value, Class<?> fieldType, Class<?> valueType) {
        if (fieldType == Boolean.TYPE) {
            setZ(object, field, value);
        } else {
            throwSetConversionException(fieldType, valueType);
        }
    }
    
    /**
     * Returns the value of the field in the specified object. This reproduces
     * the effect of {@code object.fieldName}
     *
     * <p>If the type of this field is a primitive type, the field value is
     * automatically boxed.
     *
     * <p>If this field is static, the object argument is ignored.
     * Otherwise, if the object is null, a NullPointerException is thrown. If
     * the object is not an instance of the declaring class of the method, an
     * IllegalArgumentException is thrown.
     *
     * <p>If this Field object is enforcing access control (see AccessibleObject)
     * and this field is not accessible from the current context, an
     * IllegalAccessException is thrown.
     *
     * @param object
     *            the object to access
     * @return the field value, possibly boxed
     * @throws NullPointerException
     *             if the object is {@code null} and the field is non-static
     * @throws IllegalArgumentException
     *             if the object is not compatible with the declaring class
     * @throws IllegalAccessException
     *             if this field is not accessible
     */
    public Object get(Object object) throws IllegalAccessException,
            IllegalArgumentException {

        checkAccess(object, false);
        checkReceiver(object);
        Class<?> type = getType();
        if (type.isPrimitive()) {
            if (type == Boolean.TYPE) {
                return Boolean.valueOf(getZ(object, field));
            }
            if (type == Byte.TYPE) {
                return Byte.valueOf(getB(object, field));
            }
            if (type == Short.TYPE) {
                return Short.valueOf(getS(object, field));
            }
            if (type == Character.TYPE) {
                return Character.valueOf(getC(object, field));
            }
            if (type == Integer.TYPE) {
                return Integer.valueOf(getI(object, field));
            }
            if (type == Long.TYPE) {
                return Long.valueOf(getJ(object, field));
            }
            if (type == Float.TYPE) {
                return Float.valueOf(getF(object, field));
            }
            if (type == Double.TYPE) {
                return Double.valueOf(getD(object, field));
            }
        }
        return getL(object, field);
    }

    /**
     * Returns the value of the field in the specified object as a {@code
     * boolean}. This reproduces the effect of {@code object.fieldName}
     * <p>
     * If this field is static, the object argument is ignored.
     * Otherwise, if the object is {@code null}, a NullPointerException is
     * thrown. If the object is not an instance of the declaring class of the
     * method, an IllegalArgumentException is thrown.
     * <p>
     * If this Field object is enforcing access control (see AccessibleObject)
     * and this field is not accessible from the current context, an
     * IllegalAccessException is thrown.
     *
     * @param object
     *            the object to access
     * @return the field value
     * @throws NullPointerException
     *             if the object is {@code null} and the field is non-static
     * @throws IllegalArgumentException
     *             if the object is not compatible with the declaring class
     * @throws IllegalAccessException
     *             if this field is not accessible
     */
    public boolean getBoolean(Object object)
            throws IllegalAccessException, IllegalArgumentException {
        
        checkAccess(object, false);
        checkReceiver(object);
        return getBoolean(object, getType(), Boolean.TYPE);
    }

    /**
     * Returns the value of the field in the specified object as a {@code byte}.
     * This reproduces the effect of {@code object.fieldName}
     * <p>
     * If this field is static, the object argument is ignored.
     * Otherwise, if the object is {@code null}, a NullPointerException is
     * thrown. If the object is not an instance of the declaring class of the
     * method, an IllegalArgumentException is thrown.
     * <p>
     * If this Field object is enforcing access control (see AccessibleObject)
     * and this field is not accessible from the current context, an
     * IllegalAccessException is thrown.
     *
     * @param object
     *            the object to access
     * @return the field value
     * @throws NullPointerException
     *             if the object is {@code null} and the field is non-static
     * @throws IllegalArgumentException
     *             if the object is not compatible with the declaring class
     * @throws IllegalAccessException
     *             if this field is not accessible
     */
    public byte getByte(Object object) throws IllegalAccessException,
            IllegalArgumentException {
                
        checkAccess(object, false);
        checkReceiver(object);
        return getByte(object, getType(), Byte.TYPE);
    }

    /**
     * Returns the value of the field in the specified object as a {@code char}.
     * This reproduces the effect of {@code object.fieldName}
     * <p>
     * If this field is static, the object argument is ignored.
     * Otherwise, if the object is {@code null}, a NullPointerException is
     * thrown. If the object is not an instance of the declaring class of the
     * method, an IllegalArgumentException is thrown.
     * <p>
     * If this Field object is enforcing access control (see AccessibleObject)
     * and this field is not accessible from the current context, an
     * IllegalAccessException is thrown.
     *
     * @param object
     *            the object to access
     * @return the field value
     * @throws NullPointerException
     *             if the object is {@code null} and the field is non-static
     * @throws IllegalArgumentException
     *             if the object is not compatible with the declaring class
     * @throws IllegalAccessException
     *             if this field is not accessible
     */
    public char getChar(Object object) throws IllegalAccessException,
            IllegalArgumentException {
                
        checkAccess(object, false);
        checkReceiver(object);
        return getChar(object, getType(), Character.TYPE);
    }

    /**
     * Returns the class that declares this field.
     *
     * @return the declaring class
     */
    public Class<?> getDeclaringClass() {
        if (declaringClass == null) {
            declaringClass = getDeclaringClass(field);
        }
        return declaringClass;
    }
    native static <T> Class<T> getDeclaringClass(long field);

    /**
     * Returns the value of the field in the specified object as a {@code
     * double}. This reproduces the effect of {@code object.fieldName}
     * <p>
     * If this field is static, the object argument is ignored.
     * Otherwise, if the object is {@code null}, a NullPointerException is
     * thrown. If the object is not an instance of the declaring class of the
     * method, an IllegalArgumentException is thrown.
     * <p>
     * If this Field object is enforcing access control (see AccessibleObject)
     * and this field is not accessible from the current context, an
     * IllegalAccessException is thrown.
     *
     * @param object
     *            the object to access
     * @return the field value
     * @throws NullPointerException
     *             if the object is {@code null} and the field is non-static
     * @throws IllegalArgumentException
     *             if the object is not compatible with the declaring class
     * @throws IllegalAccessException
     *             if this field is not accessible
     */
    public double getDouble(Object object)
            throws IllegalAccessException, IllegalArgumentException {
                
        checkAccess(object, false);
        checkReceiver(object);
        return getDouble(object, getType(), Double.TYPE);
    }

    /**
     * Returns the value of the field in the specified object as a {@code float}.
     * This reproduces the effect of {@code object.fieldName}
     * <p>
     * If this field is static, the object argument is ignored.
     * Otherwise, if the object is {@code null}, a NullPointerException is
     * thrown. If the object is not an instance of the declaring class of the
     * method, an IllegalArgumentException is thrown.
     * <p>
     * If this Field object is enforcing access control (see AccessibleObject)
     * and this field is not accessible from the current context, an
     * IllegalAccessException is thrown.
     *
     * @param object
     *            the object to access
     * @return the field value
     * @throws NullPointerException
     *             if the object is {@code null} and the field is non-static
     * @throws IllegalArgumentException
     *             if the object is not compatible with the declaring class
     * @throws IllegalAccessException
     *             if this field is not accessible
     */
    public float getFloat(Object object) throws IllegalAccessException,
            IllegalArgumentException {
                
        checkAccess(object, false);
        checkReceiver(object);
        return getFloat(object, getType(), Float.TYPE);
    }

    /**
     * Returns the value of the field in the specified object as an {@code int}.
     * This reproduces the effect of {@code object.fieldName}
     * <p>
     * If this field is static, the object argument is ignored.
     * Otherwise, if the object is {@code null}, a NullPointerException is
     * thrown. If the object is not an instance of the declaring class of the
     * method, an IllegalArgumentException is thrown.
     * <p>
     * If this Field object is enforcing access control (see AccessibleObject)
     * and this field is not accessible from the current context, an
     * IllegalAccessException is thrown.
     *
     * @param object
     *            the object to access
     * @return the field value
     * @throws NullPointerException
     *             if the object is {@code null} and the field is non-static
     * @throws IllegalArgumentException
     *             if the object is not compatible with the declaring class
     * @throws IllegalAccessException
     *             if this field is not accessible
     */
    public int getInt(Object object) throws IllegalAccessException,
            IllegalArgumentException {
                
        checkAccess(object, false);
        checkReceiver(object);
        return getInt(object, getType(), Integer.TYPE);
    }

    /**
     * Returns the value of the field in the specified object as a {@code long}.
     * This reproduces the effect of {@code object.fieldName}
     * <p>
     * If this field is static, the object argument is ignored.
     * Otherwise, if the object is {@code null}, a NullPointerException is
     * thrown. If the object is not an instance of the declaring class of the
     * method, an IllegalArgumentException is thrown.
     * <p>
     * If this Field object is enforcing access control (see AccessibleObject)
     * and this field is not accessible from the current context, an
     * IllegalAccessException is thrown.
     *
     * @param object
     *            the object to access
     * @return the field value
     * @throws NullPointerException
     *             if the object is {@code null} and the field is non-static
     * @throws IllegalArgumentException
     *             if the object is not compatible with the declaring class
     * @throws IllegalAccessException
     *             if this field is not accessible
     */
    public long getLong(Object object) throws IllegalAccessException,
            IllegalArgumentException {
                
        checkAccess(object, false);
        checkReceiver(object);
        return getLong(object, getType(), Long.TYPE);
    }

    /**
     * Returns the modifiers for this field. The {@link Modifier} class should
     * be used to decode the result.
     *
     * @return the modifiers for this field
     * @see Modifier
     */
    public int getModifiers() {
        if (modifiers == -1) {
            modifiers = getModifiers(field);
        }
        return modifiers;
    }
    private native static int getModifiers(long field);

    /**
     * Returns the name of this field.
     *
     * @return the name of this field
     */
    public String getName() {
        if (name == null) {
            name = getName(field);
        }
        return name;
    }
    private native static String getName(long field);

    /**
     * Returns the value of the field in the specified object as a {@code short}
     * . This reproduces the effect of {@code object.fieldName}
     * <p>
     * If this field is static, the object argument is ignored.
     * Otherwise, if the object is {@code null}, a NullPointerException is
     * thrown. If the object is not an instance of the declaring class of the
     * method, an IllegalArgumentException is thrown.
     * <p>
     * If this Field object is enforcing access control (see AccessibleObject)
     * and this field is not accessible from the current context, an
     * IllegalAccessException is thrown.
     *
     * @param object
     *            the object to access
     * @return the field value
     * @throws NullPointerException
     *             if the object is {@code null} and the field is non-static
     * @throws IllegalArgumentException
     *             if the object is not compatible with the declaring class
     * @throws IllegalAccessException
     *             if this field is not accessible
     */
    public short getShort(Object object) throws IllegalAccessException,
            IllegalArgumentException {
                
        checkAccess(object, false);
        checkReceiver(object);
        return getShort(object, getType(), Short.TYPE);
    }

    /**
     * Returns the constructor's signature in non-printable form. This is called
     * (only) from IO native code and needed for deriving the serialVersionUID
     * of the class
     *
     * @return the constructor's signature.
     */
    @SuppressWarnings("unused")
    private String getSignature() {
        return getSignature(type);
    }

    /**
     * Return the {@link Class} associated with the type of this field.
     *
     * @return the type of this field
     */
    public Class<?> getType() {
        if (type == null) {
            type = getType(field);
        }
        return type;
    }
    private native static Class<?> getType(long field);

    /**
     * Returns an integer hash code for this field. Objects which are equal
     * return the same value for this method.
     * <p>
     * The hash code for a Field is the exclusive-or combination of the hash
     * code of the field's name and the hash code of the name of its declaring
     * class.
     *
     * @return the hash code for this field
     * @see #equals
     */
    @Override
    public int hashCode() {
        return getName().hashCode() ^ getDeclaringClass().getName().hashCode();
    }

    /**
     * Sets the value of the field in the specified object to the value. This
     * reproduces the effect of {@code object.fieldName = value}
     *
     * <p>If this field is static, the object argument is ignored.
     * Otherwise, if the object is {@code null}, a NullPointerException is
     * thrown. If the object is not an instance of the declaring class of the
     * method, an IllegalArgumentException is thrown.
     *
     * <p>If this Field object is enforcing access control (see AccessibleObject)
     * and this field is not accessible from the current context, an
     * IllegalAccessException is thrown.
     *
     * <p>If the field type is a primitive type, the value is automatically
     * unboxed. If the unboxing fails, an IllegalArgumentException is thrown. If
     * the value cannot be converted to the field type via a widening
     * conversion, an IllegalArgumentException is thrown.
     *
     * @param object
     *            the object to access
     * @param value
     *            the new value
     * @throws NullPointerException
     *             if the object is {@code null} and the field is non-static
     * @throws IllegalArgumentException
     *             if the object is not compatible with the declaring class
     * @throws IllegalAccessException
     *             if this field is not accessible
     */
    public void set(Object object, Object value)
            throws IllegalAccessException, IllegalArgumentException {

        checkAccess(object, true);
        checkReceiver(object);
        Class<?> type = getType();
        if (type.isPrimitive()) {
            if (value == null) {
                throwSetConversionException(type, null);
            }
            if (value instanceof Boolean) {
                setBoolean(object, ((Boolean) value).booleanValue(), type, Boolean.TYPE);
            } else if (value instanceof Byte) {
                setByte(object, ((Byte) value).byteValue(), type, Byte.TYPE);
            } else if (value instanceof Short) {
                setShort(object, ((Short) value).shortValue(), type, Short.TYPE);
            } else if (value instanceof Character) {
                setChar(object, ((Character) value).charValue(), type, Character.TYPE);
            } else if (value instanceof Integer) {
                setInt(object, ((Integer) value).intValue(), type, Integer.TYPE);
            } else if (value instanceof Long) {
                setLong(object, ((Long) value).longValue(), type, Long.TYPE);
            } else if (value instanceof Float) {
                setFloat(object, ((Float) value).floatValue(), type, Float.TYPE);
            } else if (value instanceof Double) {
                setDouble(object, ((Double) value).doubleValue(), type, Double.TYPE);
            } else {
                throwSetConversionException(type, value.getClass());
            }
        } else {
            if (value != null && !getType().isInstance(value)) {
                throw new IllegalArgumentException(
                        String.format("Can not set %s field %s.%s to %s", 
                                getType().getName(), getDeclaringClass().getName(), 
                                getName(), value.getClass().getName()));
            }
            setL(object, field, value);
        }
    }

    /**
     * Sets the value of the field in the specified object to the {@code
     * boolean} value. This reproduces the effect of {@code object.fieldName =
     * value}
     * <p>
     * If this field is static, the object argument is ignored.
     * Otherwise, if the object is {@code null}, a NullPointerException is
     * thrown. If the object is not an instance of the declaring class of the
     * method, an IllegalArgumentException is thrown.
     * <p>
     * If this Field object is enforcing access control (see AccessibleObject)
     * and this field is not accessible from the current context, an
     * IllegalAccessException is thrown.
     * <p>
     * If the value cannot be converted to the field type via a widening
     * conversion, an IllegalArgumentException is thrown.
     *
     * @param object
     *            the object to access
     * @param value
     *            the new value
     * @throws NullPointerException
     *             if the object is {@code null} and the field is non-static
     * @throws IllegalArgumentException
     *             if the object is not compatible with the declaring class
     * @throws IllegalAccessException
     *             if this field is not accessible
     */
    public void setBoolean(Object object, boolean value)
            throws IllegalAccessException, IllegalArgumentException {
                
        checkAccess(object, true);
        checkReceiver(object);
        setBoolean(object, value, getType(), Boolean.TYPE);
    }

    /**
     * Sets the value of the field in the specified object to the {@code byte}
     * value. This reproduces the effect of {@code object.fieldName = value}
     * <p>
     * If this field is static, the object argument is ignored.
     * Otherwise, if the object is {@code null}, a NullPointerException is
     * thrown. If the object is not an instance of the declaring class of the
     * method, an IllegalArgumentException is thrown.
     * <p>
     * If this Field object is enforcing access control (see AccessibleObject)
     * and this field is not accessible from the current context, an
     * IllegalAccessException is thrown.
     * <p>
     * If the value cannot be converted to the field type via a widening
     * conversion, an IllegalArgumentException is thrown.
     *
     * @param object
     *            the object to access
     * @param value
     *            the new value
     * @throws NullPointerException
     *             if the object is {@code null} and the field is non-static
     * @throws IllegalArgumentException
     *             if the object is not compatible with the declaring class
     * @throws IllegalAccessException
     *             if this field is not accessible
     */
    public void setByte(Object object, byte value)
            throws IllegalAccessException, IllegalArgumentException {
        
        checkAccess(object, true);
        checkReceiver(object);
        setByte(object, value, getType(), Byte.TYPE);
    }

    /**
     * Sets the value of the field in the specified object to the {@code char}
     * value. This reproduces the effect of {@code object.fieldName = value}
     * <p>
     * If this field is static, the object argument is ignored.
     * Otherwise, if the object is {@code null}, a NullPointerException is
     * thrown. If the object is not an instance of the declaring class of the
     * method, an IllegalArgumentException is thrown.
     * <p>
     * If this Field object is enforcing access control (see AccessibleObject)
     * and this field is not accessible from the current context, an
     * IllegalAccessException is thrown.
     * <p>
     * If the value cannot be converted to the field type via a widening
     * conversion, an IllegalArgumentException is thrown.
     *
     * @param object
     *            the object to access
     * @param value
     *            the new value
     * @throws NullPointerException
     *             if the object is {@code null} and the field is non-static
     * @throws IllegalArgumentException
     *             if the object is not compatible with the declaring class
     * @throws IllegalAccessException
     *             if this field is not accessible
     */
    public void setChar(Object object, char value)
            throws IllegalAccessException, IllegalArgumentException {
        
        checkAccess(object, true);
        checkReceiver(object);
        setChar(object, value, getType(), Character.TYPE);
    }

    /**
     * Sets the value of the field in the specified object to the {@code double}
     * value. This reproduces the effect of {@code object.fieldName = value}
     * <p>
     * If this field is static, the object argument is ignored.
     * Otherwise, if the object is {@code null}, a NullPointerException is
     * thrown. If the object is not an instance of the declaring class of the
     * method, an IllegalArgumentException is thrown.
     * <p>
     * If this Field object is enforcing access control (see AccessibleObject)
     * and this field is not accessible from the current context, an
     * IllegalAccessException is thrown.
     * <p>
     * If the value cannot be converted to the field type via a widening
     * conversion, an IllegalArgumentException is thrown.
     *
     * @param object
     *            the object to access
     * @param value
     *            the new value
     * @throws NullPointerException
     *             if the object is {@code null} and the field is non-static
     * @throws IllegalArgumentException
     *             if the object is not compatible with the declaring class
     * @throws IllegalAccessException
     *             if this field is not accessible
     */
    public void setDouble(Object object, double value)
            throws IllegalAccessException, IllegalArgumentException {
        
        checkAccess(object, true);
        checkReceiver(object);
        setDouble(object, value, getType(), Double.TYPE);
    }

    /**
     * Sets the value of the field in the specified object to the {@code float}
     * value. This reproduces the effect of {@code object.fieldName = value}
     * <p>
     * If this field is static, the object argument is ignored.
     * Otherwise, if the object is {@code null}, a NullPointerException is
     * thrown. If the object is not an instance of the declaring class of the
     * method, an IllegalArgumentException is thrown.
     * <p>
     * If this Field object is enforcing access control (see AccessibleObject)
     * and this field is not accessible from the current context, an
     * IllegalAccessException is thrown.
     * <p>
     * If the value cannot be converted to the field type via a widening
     * conversion, an IllegalArgumentException is thrown.
     *
     * @param object
     *            the object to access
     * @param value
     *            the new value
     * @throws NullPointerException
     *             if the object is {@code null} and the field is non-static
     * @throws IllegalArgumentException
     *             if the object is not compatible with the declaring class
     * @throws IllegalAccessException
     *             if this field is not accessible
     */
    public void setFloat(Object object, float value)
            throws IllegalAccessException, IllegalArgumentException {
        
        checkAccess(object, true);
        checkReceiver(object);
        setFloat(object, value, getType(), Float.TYPE);
    }

    /**
     * Set the value of the field in the specified object to the {@code int}
     * value. This reproduces the effect of {@code object.fieldName = value}
     * <p>
     * If this field is static, the object argument is ignored.
     * Otherwise, if the object is {@code null}, a NullPointerException is
     * thrown. If the object is not an instance of the declaring class of the
     * method, an IllegalArgumentException is thrown.
     * <p>
     * If this Field object is enforcing access control (see AccessibleObject)
     * and this field is not accessible from the current context, an
     * IllegalAccessException is thrown.
     * <p>
     * If the value cannot be converted to the field type via a widening
     * conversion, an IllegalArgumentException is thrown.
     *
     * @param object
     *            the object to access
     * @param value
     *            the new value
     * @throws NullPointerException
     *             if the object is {@code null} and the field is non-static
     * @throws IllegalArgumentException
     *             if the object is not compatible with the declaring class
     * @throws IllegalAccessException
     *             if this field is not accessible
     */
    public void setInt(Object object, int value)
            throws IllegalAccessException, IllegalArgumentException {
                
        checkAccess(object, true);
        checkReceiver(object);
        setInt(object, value, getType(), Integer.TYPE);
    }

    /**
     * Sets the value of the field in the specified object to the {@code long}
     * value. This reproduces the effect of {@code object.fieldName = value}
     * <p>
     * If this field is static, the object argument is ignored.
     * Otherwise, if the object is {@code null}, a NullPointerException is
     * thrown. If the object is not an instance of the declaring class of the
     * method, an IllegalArgumentException is thrown.
     * <p>
     * If this Field object is enforcing access control (see AccessibleObject)
     * and this field is not accessible from the current context, an
     * IllegalAccessException is thrown.
     * <p>
     * If the value cannot be converted to the field type via a widening
     * conversion, an IllegalArgumentException is thrown.
     *
     * @param object
     *            the object to access
     * @param value
     *            the new value
     * @throws NullPointerException
     *             if the object is {@code null} and the field is non-static
     * @throws IllegalArgumentException
     *             if the object is not compatible with the declaring class
     * @throws IllegalAccessException
     *             if this field is not accessible
     */
    public void setLong(Object object, long value)
            throws IllegalAccessException, IllegalArgumentException {
                
        checkAccess(object, true);
        checkReceiver(object);
        setLong(object, value, getType(), Long.TYPE);
    }

    /**
     * Sets the value of the field in the specified object to the {@code short}
     * value. This reproduces the effect of {@code object.fieldName = value}
     * <p>
     * If this field is static, the object argument is ignored.
     * Otherwise, if the object is {@code null}, a NullPointerException is
     * thrown. If the object is not an instance of the declaring class of the
     * method, an IllegalArgumentException is thrown.
     * <p>
     * If this Field object is enforcing access control (see AccessibleObject)
     * and this field is not accessible from the current context, an
     * IllegalAccessException is thrown.
     * <p>
     * If the value cannot be converted to the field type via a widening
     * conversion, an IllegalArgumentException is thrown.
     *
     * @param object
     *            the object to access
     * @param value
     *            the new value
     * @throws NullPointerException
     *             if the object is {@code null} and the field is non-static
     * @throws IllegalArgumentException
     *             if the object is not compatible with the declaring class
     * @throws IllegalAccessException
     *             if this field is not accessible
     */
    public void setShort(Object object, short value)
            throws IllegalAccessException, IllegalArgumentException {
        
        checkAccess(object, true);
        checkReceiver(object);
        setShort(object, value, getType(), Short.TYPE);
    }

    /**
     * Returns a string containing a concise, human-readable description of this
     * field.
     * <p>
     * The format of the string is:
     * <ol>
     *   <li>modifiers (if any)
     *   <li>type
     *   <li>declaring class name
     *   <li>'.'
     *   <li>field name
     * </ol>
     * <p>
     * For example: {@code public static java.io.InputStream
     * java.lang.System.in}
     *
     * @return a printable representation for this field
     */
    @Override
    public String toString() {
        Class<?> type = getType();
        Class<?> declaringClass = getDeclaringClass();
        String name = getName();
        
        StringBuilder result = new StringBuilder(Modifier.toString(getModifiers()));
        if (result.length() != 0) {
            result.append(' ');
        }
        appendTypeName(result, type);
        result.append(' ');
        appendTypeName(result, declaringClass);
        result.append('.');
        result.append(name);
        return result.toString();
    }

    private static native boolean getZ(Object o, long field);
    private static native byte getB(Object o, long field);
    private static native char getC(Object o, long field);
    private static native short getS(Object o, long field);
    private static native int getI(Object o, long field);
    private static native long getJ(Object o, long field);
    private static native float getF(Object o, long field);
    private static native double getD(Object o, long field);
    private static native Object getL(Object o, long field);
    private static native void setZ(Object o, long field, boolean value);
    private static native void setB(Object o, long field, byte value);
    private static native void setC(Object o, long field, char value);
    private static native void setS(Object o, long field, short value);
    private static native void setI(Object o, long field, int value);
    private static native void setJ(Object o, long field, long value);
    private static native void setF(Object o, long field, float value);
    private static native void setD(Object o, long field, double value);
    private static native void setL(Object o, long field, Object value);
}
