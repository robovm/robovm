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
import org.apache.harmony.luni.lang.reflect.Types;
import org.nullvm.rt.VM;

/**
 * This class represents a field. Information about the field can be accessed,
 * and the field's value can be accessed dynamically.
 */
public final class Field extends AccessibleObject implements Member {
    /*
     * The NullVM Field* object
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
    
    /**
     * Prevent this class from being instantiated
     */
    Field(long field){
        this.field = field;
    }
    
    Field(Field f) {
        this.field = f.field;
    }

    private synchronized void initGenericType() {
        // Start (C) Android
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
        // End (C) Android
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
     * @since 1.5
     */
    public String toGenericString() {
        // Start (C) Android
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
        // End (C) Android
    }

    /**
     * Indicates whether or not this field is an enumeration constant.
     *
     * @return {@code true} if this field is an enumeration constant, {@code
     *         false} otherwise
     * @since 1.5
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
     * @since 1.5
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

    private void checkAccess(boolean setter) throws IllegalAccessException {
        if (!flag) {
            int mod = getModifiers();
            if (setter && (mod & Modifier.FINAL) > 0) {
                throw new IllegalAccessException("Cannot set final field");
            }
            // TODO: Check that the caller class may access the field
            //Class<?> caller = VM.getStackClasses(1, 1)[0];
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

    private long getAddress(Object object) {
        int mod = getModifiers();
        if ((mod & Modifier.STATIC) > 0) {
            return VM.getClassFieldAddress(field);
        }
        return VM.getObjectAddress(object) + VM.getInstanceFieldOffset(field);
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
    
    private double getDouble(long address, Class<?> fieldType, Class<?> expectedType) {
        if (fieldType == Double.TYPE) {
            return VM.getDouble(address);
        }
        return getFloat(address, fieldType, expectedType); 
    }
    
    private float getFloat(long address, Class<?> fieldType, Class<?> expectedType) {
        if (fieldType == Float.TYPE) {
            return VM.getFloat(address);
        }
        return getLong(address, fieldType, expectedType); 
    }
    
    private long getLong(long address, Class<?> fieldType, Class<?> expectedType) {
        if (fieldType == Long.TYPE) {
            return VM.getLong(address);
        }
        return getInt(address, fieldType, expectedType);
    }
    
    private int getInt(long address, Class<?> fieldType, Class<?> expectedType) {
        if (fieldType == Integer.TYPE) {
            return VM.getInt(address);
        }
        if (fieldType == Character.TYPE) {
            return VM.getChar(address);
        }
        return getShort(address, fieldType, expectedType);
    }
    
    private char getChar(long address, Class<?> fieldType, Class<?> expectedType) {
        if (fieldType == Character.TYPE) {
            return VM.getChar(address);
        }
        throwGetConversionException(fieldType, expectedType);
        return 0;
    }

    private short getShort(long address, Class<?> fieldType, Class<?> expectedType) {
        if (fieldType == Short.TYPE) {
            return VM.getShort(address);
        }
        return getByte(address, fieldType, expectedType);
    }

    private byte getByte(long address, Class<?> fieldType, Class<?> expectedType) {
        if (fieldType == Byte.TYPE) {
            return VM.getByte(address);
        }
        throwGetConversionException(fieldType, expectedType);
        return 0;
    }
    
    private boolean getBoolean(long address, Class<?> fieldType, Class<?> expectedType) {
        if (fieldType == Boolean.TYPE) {
            return VM.getBoolean(address);
        }
        throwGetConversionException(fieldType, expectedType);
        return false;
    }
    
    private void setDouble(long address, double value, Class<?> fieldType, Class<?> valueType) {
        if (fieldType == Double.TYPE) {
            VM.setDouble(address, value);
        } else {
            throwSetConversionException(fieldType, valueType);
        }
    }

    private void setFloat(long address, float value, Class<?> fieldType, Class<?> valueType) {
        if (fieldType == Float.TYPE) {
            VM.setFloat(address, value);
        } else {
            setDouble(address, value, fieldType, valueType);
        }
    }

    private void setLong(long address, long value, Class<?> fieldType, Class<?> valueType) {
        if (fieldType == Long.TYPE) {
            VM.setLong(address, value);
        } else {
            setFloat(address, value, fieldType, valueType);
        }
    }
    
    private void setInt(long address, int value, Class<?> fieldType, Class<?> valueType) {
        if (fieldType == Integer.TYPE) {
            VM.setInt(address, value);
        } else {
            setLong(address, value, fieldType, valueType);
        }
    }
    
    private void setChar(long address, char value, Class<?> fieldType, Class<?> valueType) {
        if (fieldType == Character.TYPE) {
            VM.setChar(address, value);
        } else {
            setInt(address, value, fieldType, valueType);
        }
    }

    private void setShort(long address, short value, Class<?> fieldType, Class<?> valueType) {
        if (fieldType == Short.TYPE) {
            VM.setShort(address, value);
        } else {
            setInt(address, value, fieldType, valueType);
        }
    }
    
    private void setByte(long address, byte value, Class<?> fieldType, Class<?> valueType) {
        if (fieldType == Byte.TYPE) {
            VM.setByte(address, value);
        } else {
            setShort(address, value, fieldType, valueType);
        }
    }
    
    private void setBoolean(long address, boolean value, Class<?> fieldType, Class<?> valueType) {
        if (fieldType == Boolean.TYPE) {
            VM.setBoolean(address, value);
        } else {
            throwSetConversionException(fieldType, valueType);
        }
    }
    
    /**
     * Returns the value of the field in the specified object. This reproduces
     * the effect of {@code object.fieldName}
     * <p>
     * If the type of this field is a primitive type, the field value is
     * automatically wrapped.
     * <p>
     * If this field is static, the object argument is ignored.
     * Otherwise, if the object is null, a NullPointerException is thrown. If
     * the object is not an instance of the declaring class of the method, an
     * IllegalArgumentException is thrown.
     * <p>
     * If this Field object is enforcing access control (see AccessibleObject)
     * and this field is not accessible from the current context, an
     * IllegalAccessException is thrown.
     *
     * @param object
     *            the object to access
     * @return the field value, possibly wrapped
     * @throws NullPointerException
     *             if the object is {@code null} and the field is non-static
     * @throws IllegalArgumentException
     *             if the object is not compatible with the declaring class
     * @throws IllegalAccessException
     *             if this field is not accessible
     */
    public Object get(Object object) throws IllegalAccessException,
            IllegalArgumentException {

        checkAccess(false);
        checkReceiver(object);
        Class<?> type = getType();
        long address = getAddress(object);
        if (type.isPrimitive()) {
            if (type == Boolean.TYPE) {
                return Boolean.valueOf(VM.getBoolean(address));
            }
            if (type == Byte.TYPE) {
                return Byte.valueOf(VM.getByte(address));
            }
            if (type == Short.TYPE) {
                return Short.valueOf(VM.getShort(address));
            }
            if (type == Character.TYPE) {
                return Character.valueOf(VM.getChar(address));
            }
            if (type == Integer.TYPE) {
                return Integer.valueOf(VM.getInt(address));
            }
            if (type == Long.TYPE) {
                return Long.valueOf(VM.getLong(address));
            }
            if (type == Float.TYPE) {
                return Float.valueOf(VM.getFloat(address));
            }
            if (type == Double.TYPE) {
                return Double.valueOf(VM.getDouble(address));
            }
        }
        return VM.getObject(address);
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
        
        checkAccess(false);
        checkReceiver(object);
        return getBoolean(getAddress(object), getType(), Boolean.TYPE);
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
                
        checkAccess(false);
        checkReceiver(object);
        return getByte(getAddress(object), getType(), Byte.TYPE);
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
                
        checkAccess(false);
        checkReceiver(object);
        return getChar(getAddress(object), getType(), Character.TYPE);
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
                
        checkAccess(false);
        checkReceiver(object);
        return getDouble(getAddress(object), getType(), Double.TYPE);
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
                
        checkAccess(false);
        checkReceiver(object);
        return getFloat(getAddress(object), getType(), Float.TYPE);
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
                
        checkAccess(false);
        checkReceiver(object);
        return getInt(getAddress(object), getType(), Integer.TYPE);
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
                
        checkAccess(false);
        checkReceiver(object);
        return getLong(getAddress(object), getType(), Long.TYPE);
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
                
        checkAccess(false);
        checkReceiver(object);
        return getShort(getAddress(object), getType(), Short.TYPE);
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
        return getDeclaringClass().getName().hashCode() ^ getName().hashCode();
    }

    /**
     * Sets the value of the field in the specified object to the value. This
     * reproduces the effect of {@code object.fieldName = value}
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
     * If the field type is a primitive type, the value is automatically
     * unwrapped. If the unwrap fails, an IllegalArgumentException is thrown. If
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

        checkAccess(true);
        checkReceiver(object);
        Class<?> type = getType();
        long address = getAddress(object);
        if (type.isPrimitive()) {
            if (value == null) {
                throwSetConversionException(type, null);
            }
            if (value instanceof Boolean) {
                setBoolean(address, ((Boolean) value).booleanValue(), type, Boolean.TYPE);
            } else if (value instanceof Byte) {
                setByte(address, ((Byte) value).byteValue(), type, Byte.TYPE);
            } else if (value instanceof Short) {
                setShort(address, ((Short) value).shortValue(), type, Short.TYPE);
            } else if (value instanceof Character) {
                setChar(address, ((Character) value).charValue(), type, Character.TYPE);
            } else if (value instanceof Integer) {
                setInt(address, ((Integer) value).intValue(), type, Integer.TYPE);
            } else if (value instanceof Long) {
                setLong(address, ((Long) value).longValue(), type, Long.TYPE);
            } else if (value instanceof Float) {
                setFloat(address, ((Float) value).floatValue(), type, Float.TYPE);
            } else if (value instanceof Double) {
                setDouble(address, ((Double) value).doubleValue(), type, Double.TYPE);
            } else {
                throwSetConversionException(type, value.getClass());
            }
        } else {
            VM.setObject(address, value);
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
                
        checkAccess(true);
        checkReceiver(object);
        setBoolean(getAddress(object), value, getType(), Boolean.TYPE);
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
        
        checkAccess(true);
        checkReceiver(object);
        setByte(getAddress(object), value, getType(), Byte.TYPE);
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
        
        checkAccess(true);
        checkReceiver(object);
        setChar(getAddress(object), value, getType(), Character.TYPE);
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
        
        checkAccess(true);
        checkReceiver(object);
        setDouble(getAddress(object), value, getType(), Double.TYPE);
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
        
        checkAccess(true);
        checkReceiver(object);
        setFloat(getAddress(object), value, getType(), Float.TYPE);
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
                
        checkAccess(true);
        checkReceiver(object);
        setInt(getAddress(object), value, getType(), Integer.TYPE);
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
                
        checkAccess(true);
        checkReceiver(object);
        setLong(getAddress(object), value, getType(), Long.TYPE);
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
        
        checkAccess(true);
        checkReceiver(object);
        setShort(getAddress(object), value, getType(), Short.TYPE);
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
        
        StringBuilder sb = new StringBuilder(Modifier.toString(getModifiers()));

        if (sb.length() > 0) {
            sb.append(' ');
        }
        sb.append(type.getName());
        sb.append(' ');
        sb.append(declaringClass.getName());
        sb.append('.');
        sb.append(name);
        
        return sb.toString();
    }
}
