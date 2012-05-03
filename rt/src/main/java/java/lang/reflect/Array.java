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

package java.lang.reflect;

/**
 * This class provides static methods to create and access arrays dynamically.
 */
public final class Array {

    /**
     * Prevent this class from being instantiated.
     */
    private Array(){
        //do nothing
    }

    /**
     * Returns the element of the array at the specified index. This reproduces
     * the effect of {@code array[index]}. If the array component is a primitive
     * type, the result is automatically boxed.
     *
     * @param array
     *            the array
     * @param index
     *            the index
     *
     * @return the requested element, possibly boxed
     *
     * @throws NullPointerException
     *             if the array is null
     * @throws IllegalArgumentException
     *             if {@code array} is not an array
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code  index < 0 || index >= array.length}
     */
    public static Object get(Object array, int index)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array instanceof Object[])
            return ((Object[]) array)[index];

        if (array instanceof boolean[])
            return ((boolean[]) array)[index] ? Boolean.TRUE : Boolean.FALSE;

        if (array instanceof byte[])
            return Byte.valueOf(((byte[]) array)[index]);

        if (array instanceof char[])
            return Character.valueOf(((char[]) array)[index]);

        if (array instanceof short[])
            return Short.valueOf(((short[]) array)[index]);

        if (array instanceof int[])
            return Integer.valueOf(((int[]) array)[index]);

        if (array instanceof long[])
            return Long.valueOf(((long[]) array)[index]);

        if (array instanceof float[])
            return new Float(((float[]) array)[index]);

        if (array instanceof double[])
            return new Double(((double[]) array)[index]);

        if (array == null)
            throw new NullPointerException();

        throw new IllegalArgumentException("Not an array");
    }

    /**
     * Returns the element of the array at the specified index, converted to a
     * {@code boolean}, if possible. This reproduces the effect of {@code
     * array[index]}
     *
     * @param array
     *            the array
     * @param index
     *            the index
     *
     * @return the requested element
     *
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array} is not an array or the element at the
     *             index position can not be converted to the return type
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code index < 0 || index >= array.length}
     */
    public static boolean getBoolean(Object array, int index)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array instanceof boolean[]) {
            return ((boolean[]) array)[index];
        } else if (array == null) {
            throw new NullPointerException();
        } else if (array.getClass().isArray()) {
            throw new IllegalArgumentException("Wrong array type");
        } else {
            throw new IllegalArgumentException("Not an array");
        }
    }

    /**
     * Returns the element of the array at the specified index, converted to a
     * {@code byte}, if possible. This reproduces the effect of {@code
     * array[index]}
     *
     * @param array
     *            the array
     * @param index
     *            the index
     *
     * @return the requested element
     *
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array} is not an array or the element at the
     *             index position can not be converted to the return type
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code index < 0 || index >= array.length}
     */
    public static byte getByte(Object array, int index)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array instanceof byte[]) {
            return ((byte[]) array)[index];
        } else {
            return getBoolean(array, index) ? (byte)1 : (byte)0;
        }
    }

    /**
     * Returns the element of the array at the specified index, converted to a
     * {@code char}, if possible. This reproduces the effect of {@code
     * array[index]}
     *
     * @param array
     *            the array
     * @param index
     *            the index
     *
     * @return the requested element
     *
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array} is not an array or the element at the
     *             index position can not be converted to the return type
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code index < 0 || index >= array.length}
     */
    public static char getChar(Object array, int index)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array instanceof char[]) {
            return ((char[]) array)[index];
        } else if (array == null) {
            throw new NullPointerException();
        } else if (array.getClass().isArray()) {
            throw new IllegalArgumentException("Wrong array type");
        } else {
            throw new IllegalArgumentException("Not an array");
        }
    }

    /**
     * Returns the element of the array at the specified index, converted to a
     * {@code double}, if possible. This reproduces the effect of {@code
     * array[index]}
     *
     * @param array
     *            the array
     * @param index
     *            the index
     *
     * @return the requested element
     *
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array} is not an array or the element at the
     *             index position can not be converted to the return type
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code index < 0 || index >= array.length}
     */
    public static double getDouble(Object array, int index)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array instanceof double[]) {
            return ((double[]) array)[index];
        } else {
            return getFloat(array, index);
        }
    }

    /**
     * Returns the element of the array at the specified index, converted to a
     * {@code float}, if possible. This reproduces the effect of {@code
     * array[index]}
     *
     * @param array
     *            the array
     * @param index
     *            the index
     *
     * @return the requested element
     *
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array} is not an array or the element at the
     *             index position can not be converted to the return type
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code index < 0 || index >= array.length}
     */
    public static float getFloat(Object array, int index)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array instanceof float[]) {
            return ((float[]) array)[index];
        } else {
            return getLong(array, index);
        }
    }

    /**
     * Returns the element of the array at the specified index, converted to an
     * {@code int}, if possible. This reproduces the effect of {@code
     * array[index]}
     *
     * @param array
     *            the array
     * @param index
     *            the index
     *
     * @return the requested element
     *
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array} is not an array or the element at the
     *             index position can not be converted to the return type
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code index < 0 || index >= array.length}
     */
    public static int getInt(Object array, int index)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array instanceof int[]) {
            return ((int[]) array)[index];
        } else {
            return getShort(array, index);
        }
    }

    /**
     * Returns the length of the array. This reproduces the effect of {@code
     * array.length}
     *
     * @param array
     *            the array
     *
     * @return the length of the array
     *
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array} is not an array
     */
    public static int getLength(Object array) {
        if (array instanceof Object[])
            return ((Object[]) array).length;

        if (array instanceof boolean[])
            return ((boolean[]) array).length;

        if (array instanceof byte[])
            return ((byte[]) array).length;

        if (array instanceof char[])
            return ((char[]) array).length;

        if (array instanceof short[])
            return ((short[]) array).length;

        if (array instanceof int[])
            return ((int[]) array).length;

        if (array instanceof long[])
            return ((long[]) array).length;

        if (array instanceof float[])
            return ((float[]) array).length;

        if (array instanceof double[])
            return ((double[]) array).length;

        if (array == null)
            throw new NullPointerException();

        throw new IllegalArgumentException("Not an array");
    }

    /**
     * Returns the element of the array at the specified index, converted to a
     * {@code long}, if possible. This reproduces the effect of {@code
     * array[index]}
     *
     * @param array
     *            the array
     * @param index
     *            the index
     *
     * @return the requested element
     *
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array} is not an array or the element at the
     *             index position can not be converted to the return type
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code index < 0 || index >= array.length}
     */
    public static long getLong(Object array, int index)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array instanceof long[]) {
            return ((long[]) array)[index];
        } else {
            return getInt(array, index);
        }
    }

    /**
     * Returns the element of the array at the specified index, converted to a
     * {@code short}, if possible. This reproduces the effect of {@code
     * array[index]}
     *
     * @param array
     *            the array
     * @param index
     *            the index
     *
     * @return the requested element
     *
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array} is not an array or the element at the
     *             index position can not be converted to the return type
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code index < 0 || index >= array.length}
     */
    public static short getShort(Object array, int index)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array instanceof short[])
            return ((short[]) array)[index];

        return getByte(array, index);
    }

    /**
     * Returns a new multidimensional array of the specified component type and
     * dimensions. This reproduces the effect of {@code new
     * componentType[d0][d1]...[dn]} for a dimensions array of { d0, d1, ... ,
     * dn }.
     *
     * @param componentType
     *            the component type of the new array
     * @param dimensions
     *            the dimensions of the new array
     *
     * @return the new array
     *
     * @throws NullPointerException
     *             if the component type is {@code null}
     * @throws NegativeArraySizeException
     *             if any of the dimensions are negative
     * @throws IllegalArgumentException
     *             if the array of dimensions is of size zero, or exceeds the
     *             limit of the number of dimension for an array (currently 255)
     */
    public static Object newInstance(Class<?> componentType, int... dimensions)
            throws NegativeArraySizeException, IllegalArgumentException {
        if (dimensions.length <= 0 || dimensions.length > 255)
            throw new IllegalArgumentException("Bad number of dimensions");

        if (componentType == void.class)
            throw new IllegalArgumentException();

        if (componentType == null)
            throw new NullPointerException();

        return createMultiArray(componentType, dimensions);
    }

    /*
     * Create a multi-dimensional array of objects with the specified type.
     */
    native private static Object createMultiArray(Class<?> componentType,
        int[] dimensions) throws NegativeArraySizeException;

    /**
     * Returns a new array of the specified component type and length. This
     * reproduces the effect of {@code new componentType[size]}.
     *
     * @param componentType
     *            the component type of the new array
     * @param size
     *            the length of the new array
     *
     * @return the new array
     *
     * @throws NullPointerException
     *             if the component type is null
     * @throws NegativeArraySizeException
     *             if {@code size < 0}
     */
    public static Object newInstance(Class<?> componentType, int size)
            throws NegativeArraySizeException {
        if (!componentType.isPrimitive()) {
            return createObjectArray(componentType, size);
        }
        if (componentType == boolean.class) {
            return new boolean[size];
        }
        if (componentType == byte.class) {
            return new byte[size];
        }
        if (componentType == char.class) {
            return new char[size];
        }
        if (componentType == short.class) {
            return new short[size];
        }
        if (componentType == int.class) {
            return new int[size];
        }
        if (componentType == long.class) {
            return new long[size];
        }
        if (componentType == float.class) {
            return new float[size];
        }
        if (componentType == double.class) {
            return new double[size];
        }
        if (componentType == void.class) {
            throw new IllegalArgumentException();
        }
        throw new AssertionError();
    }

    /*
     * Create a one-dimensional array of objects with the specified type.
     */
    native private static Object createObjectArray(Class<?> componentType,
        int length) throws NegativeArraySizeException;

    /**
     * Sets the element of the array at the specified index to the value. This
     * reproduces the effect of {@code array[index] = value}. If the array
     * component is a primitive type, the value is automatically unboxed.
     *
     * @param array
     *            the array
     * @param index
     *            the index
     * @param value
     *            the new value
     *
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array} is not an array or the value cannot be
     *             converted to the array type by a widening conversion
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code  index < 0 || index >= array.length}
     */
    public static void set(Object array, int index, Object value)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Not an array type");
        }

        if (array instanceof Object[]) {
            if (value != null &&
                !array.getClass().getComponentType().isInstance(value)) {
                // incompatible object type for this array
                throw new IllegalArgumentException("Wrong array type");
            }

            ((Object[]) array)[index] = value;
        } else {
            if (value == null) {
                throw new IllegalArgumentException("Primitive array can't take null values.");
            }

            if (value instanceof Boolean)
                setBoolean(array, index, ((Boolean) value).booleanValue());
            else if (value instanceof Byte)
                setByte(array, index, ((Byte) value).byteValue());
            else if (value instanceof Character)
                setChar(array, index, ((Character) value).charValue());
            else if (value instanceof Short)
                setShort(array, index, ((Short) value).shortValue());
            else if (value instanceof Integer)
                setInt(array, index, ((Integer) value).intValue());
            else if (value instanceof Long)
                setLong(array, index, ((Long) value).longValue());
            else if (value instanceof Float)
                setFloat(array, index, ((Float) value).floatValue());
            else if (value instanceof Double)
                setDouble(array, index, ((Double) value).doubleValue());
        }
    }

    /**
     * Sets the element of the array at the specified index to the {@code
     * boolean} value. This reproduces the effect of {@code array[index] =
     * value}.
     *
     * @param array
     *            the array
     * @param index
     *            the index
     * @param value
     *            the new value
     *
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if the {@code array} is not an array or the value cannot be
     *             converted to the array type by a widening conversion
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code  index < 0 || index >= array.length}
     */
    public static void setBoolean(Object array, int index, boolean value) {
        if (array instanceof boolean[]) {
            ((boolean[]) array)[index] = value;
        } else {
            setByte(array, index, value ? (byte)1 : (byte)0);
        }
    }

    /**
     * Sets the element of the array at the specified index to the {@code byte}
     * value. This reproduces the effect of {@code array[index] = value}.
     *
     * @param array
     *            the array
     * @param index
     *            the index
     * @param value
     *            the new value
     *
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if the {@code array} is not an array or the value cannot be
     *             converted to the array type by a widening conversion
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code  index < 0 || index >= array.length}
     */
    public static void setByte(Object array, int index, byte value)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array instanceof byte[]) {
            ((byte[]) array)[index] = value;
        } else {
            setShort(array, index, value);
        }
    }

    /**
     * Set the element of the array at the specified index to the {@code char}
     * value. This reproduces the effect of {@code array[index] = value}.
     *
     * @param array
     *            the array
     * @param index
     *            the index
     * @param value
     *            the new value
     *
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if the {@code array} is not an array or the value cannot be
     *             converted to the array type by a widening conversion
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code  index < 0 || index >= array.length}
     */
    public static void setChar(Object array, int index, char value)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array instanceof char[]) {
            ((char[]) array)[index] = value;
        } else if (array == null) {
            throw new NullPointerException();
        } else if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Not an array");
        } else {
            throw new IllegalArgumentException("Wrong array type");
        }
    }

    /**
     * Set the element of the array at the specified index to the {@code double}
     * value. This reproduces the effect of {@code array[index] = value}.
     *
     * @param array
     *            the array
     * @param index
     *            the index
     * @param value
     *            the new value
     *
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if the {@code array} is not an array or the value cannot be
     *             converted to the array type by a widening conversion
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code  index < 0 || index >= array.length}
     */
    public static void setDouble(Object array, int index, double value)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array instanceof double[]) {
            ((double[]) array)[index] = value;
        } else if (array == null) {
            throw new NullPointerException();
        } else if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Not an array");
        } else {
            throw new IllegalArgumentException("Wrong array type");
        }
    }

    /**
     * Set the element of the array at the specified index to the {@code float}
     * value. This reproduces the effect of {@code array[index] = value}.
     *
     * @param array
     *            the array
     * @param index
     *            the index
     * @param value
     *            the new value
     *
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if the {@code array} is not an array or the value cannot be
     *             converted to the array type by a widening conversion
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code  index < 0 || index >= array.length}
     */
    public static void setFloat(Object array, int index, float value)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array instanceof float[]) {
            ((float[]) array)[index] = value;
        } else {
            setDouble(array, index, value);
        }
    }

    /**
     * Set the element of the array at the specified index to the {@code int}
     * value. This reproduces the effect of {@code array[index] = value}.
     *
     * @param array
     *            the array
     * @param index
     *            the index
     * @param value
     *            the new value
     *
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if the {@code array} is not an array or the value cannot be
     *             converted to the array type by a widening conversion
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code  index < 0 || index >= array.length}
     */
    public static void setInt(Object array, int index, int value)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array instanceof int[]) {
            ((int[]) array)[index] = value;
        } else {
            setLong(array, index, value);
        }
    }

    /**
     * Set the element of the array at the specified index to the {@code long}
     * value. This reproduces the effect of {@code array[index] = value}.
     *
     * @param array
     *            the array
     * @param index
     *            the index
     * @param value
     *            the new value
     *
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if the {@code array} is not an array or the value cannot be
     *             converted to the array type by a widening conversion
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code  index < 0 || index >= array.length}
     */
    public static void setLong(Object array, int index, long value)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array instanceof long[]) {
            ((long[]) array)[index] = value;
        } else {
            setFloat(array, index, value);
        }
    }

    /**
     * Set the element of the array at the specified index to the {@code short}
     * value. This reproduces the effect of {@code array[index] = value}.
     *
     * @param array
     *            the array
     * @param index
     *            the index
     * @param value
     *            the new value
     *
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if the {@code array} is not an array or the value cannot be
     *             converted to the array type by a widening conversion
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code  index < 0 || index >= array.length}
     */
    public static void setShort(Object array, int index, short value)
            throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if (array instanceof short[]) {
            ((short[]) array)[index] = value;
        } else {
            setInt(array, index, value);
        }
    }

}
