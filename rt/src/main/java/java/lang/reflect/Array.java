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
     * <p>TODO Document this method. Is it actually used?</p>
     * @param componentType
     * @param dimensions
     * @param dimensionsArray
     * @return
     */
    @SuppressWarnings("unused")
    private static native Object multiNewArrayImpl(Class<?> componentType,
            int dimensions, int[] dimensionsArray);

    /**
     * <p>TODO Document this method. Is it actually used?</p>
     * @param componentType
     * @param dimension
     * @return
     */
    @SuppressWarnings("unused")
    private static native Object newArrayImpl(Class<?> componentType, int dimension);

    /**
     * Returns the element of the array at the specified index. This reproduces
     * the effect of {@code array[index]}. If the array component is a primitive
     * type, the result is automatically wrapped.
     *
     * @param array
     *            the array
     * @param index
     *            the index
     * @return the requested element, possibly wrapped
     * @throws NullPointerException
     *             if the array is null
     * @throws IllegalArgumentException
     *             if {@code array} is not an array
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code  index < 0 || index >= array.length}
     */
	public static native Object get(Object array, int index)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    /**
     * Returns the element of the array at the specified index, converted to a
     * {@code boolean}, if possible. This reproduces the effect of {@code
     * array[index]}
     *
     * @param array
     *            the array
     * @param index
     *            the index
     * @return the requested element
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array} is not an array or the element at the
     *             index position can not be converted to the return type
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code index < 0 || index >= array.length}
     */
	public static native boolean getBoolean(Object array, int index)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    /**
     * Returns the element of the array at the specified index, converted to a
     * {@code byte}, if possible. This reproduces the effect of {@code
     * array[index]}
     *
     * @param array
     *            the array
     * @param index
     *            the index
     * @return the requested element
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array} is not an array or the element at the
     *             index position can not be converted to the return type
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code index < 0 || index >= array.length}
     */
	public static native byte getByte(Object array, int index)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    /**
     * Returns the element of the array at the specified index, converted to a
     * {@code char}, if possible. This reproduces the effect of {@code
     * array[index]}
     *
     * @param array
     *            the array
     * @param index
     *            the index
     * @return the requested element
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array} is not an array or the element at the
     *             index position can not be converted to the return type
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code index < 0 || index >= array.length}
     */
	public static native char getChar(Object array, int index)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    /**
     * Returns the element of the array at the specified index, converted to a
     * {@code double}, if possible. This reproduces the effect of {@code
     * array[index]}
     *
     * @param array
     *            the array
     * @param index
     *            the index
     * @return the requested element
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array} is not an array or the element at the
     *             index position can not be converted to the return type
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code index < 0 || index >= array.length}
     */
	public static native double getDouble(Object array, int index)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    /**
     * Returns the element of the array at the specified index, converted to a
     * {@code float}, if possible. This reproduces the effect of {@code
     * array[index]}
     *
     * @param array
     *            the array
     * @param index
     *            the index
     * @return the requested element
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array} is not an array or the element at the
     *             index position can not be converted to the return type
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code index < 0 || index >= array.length}
     */
	public static native float getFloat(Object array, int index)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    /**
     * Returns the element of the array at the specified index, converted to an
     * {@code int}, if possible. This reproduces the effect of {@code
     * array[index]}
     *
     * @param array
     *            the array
     * @param index
     *            the index
     * @return the requested element
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array} is not an array or the element at the
     *             index position can not be converted to the return type
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code index < 0 || index >= array.length}
     */
	public static native int getInt(Object array, int index)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    /**
     * Returns the length of the array. This reproduces the effect of {@code
     * array.length}
     *
     * @param array
     *            the array
     * @return the length of the array
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array} is not an array
     */
	public static native int getLength(Object array)
			throws IllegalArgumentException;

    /**
     * Returns the element of the array at the specified index, converted to a
     * {@code long}, if possible. This reproduces the effect of {@code
     * array[index]}
     *
     * @param array
     *            the array
     * @param index
     *            the index
     * @return the requested element
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array} is not an array or the element at the
     *             index position can not be converted to the return type
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code index < 0 || index >= array.length}
     */
	public static native long getLong(Object array, int index)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

    /**
     * Returns the element of the array at the specified index, converted to a
     * {@code short}, if possible. This reproduces the effect of {@code
     * array[index]}
     *
     * @param array
     *            the array
     * @param index
     *            the index
     * @return the requested element
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if {@code array} is not an array or the element at the
     *             index position can not be converted to the return type
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code index < 0 || index >= array.length}
     */
	public static native short getShort(Object array, int index)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

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
     * @return the new array
     * @throws NullPointerException
     *             if the component type is {@code null}
     * @throws NegativeArraySizeException
     *             if any of the dimensions are negative
     * @throws IllegalArgumentException
     *             if the array of dimensions is of size zero, or exceeds the
     *             limit of the number of dimension for an array (currently 255)
     */
	public static Object newInstance(Class<?> componentType, int[] dimensions)
			throws NegativeArraySizeException, IllegalArgumentException {
		return null;
	}

    /**
     * Returns a new array of the specified component type and length. This
     * reproduces the effect of {@code new componentType[size]}.
     *
     * @param componentType
     *            the component type of the new array
     * @param size
     *            the length of the new array
     * @return the new array
     * @throws NullPointerException
     *             if the component type is null
     * @throws NegativeArraySizeException
     *             if {@code size < 0}
     */
	public static Object newInstance(Class<?> componentType, int size)
			throws NegativeArraySizeException {
		return null;
	}

    /**
     * Sets the element of the array at the specified index to the value. This
     * reproduces the effect of {@code array[index] = value}. If the array
     * component is a primitive type, the value is automatically unwrapped.
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
	public static native void set(Object array, int index, Object value)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

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
	public static native void setBoolean(Object array, int index, boolean value)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

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
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if the {@code array} is not an array or the value cannot be
     *             converted to the array type by a widening conversion
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code  index < 0 || index >= array.length}
     */
	public static native void setByte(Object array, int index, byte value)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

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
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if the {@code array} is not an array or the value cannot be
     *             converted to the array type by a widening conversion
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code  index < 0 || index >= array.length}
     */
	public static native void setChar(Object array, int index, char value)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

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
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if the {@code array} is not an array or the value cannot be
     *             converted to the array type by a widening conversion
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code  index < 0 || index >= array.length}
     */
	public static native void setDouble(Object array, int index, double value)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

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
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if the {@code array} is not an array or the value cannot be
     *             converted to the array type by a widening conversion
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code  index < 0 || index >= array.length}
     */
	public static native void setFloat(Object array, int index, float value)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

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
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if the {@code array} is not an array or the value cannot be
     *             converted to the array type by a widening conversion
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code  index < 0 || index >= array.length}
     */
	public static native void setInt(Object array, int index, int value)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

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
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if the {@code array} is not an array or the value cannot be
     *             converted to the array type by a widening conversion
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code  index < 0 || index >= array.length}
     */
	public static native void setLong(Object array, int index, long value)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

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
     * @throws NullPointerException
     *             if the {@code array} is {@code null}
     * @throws IllegalArgumentException
     *             if the {@code array} is not an array or the value cannot be
     *             converted to the array type by a widening conversion
     * @throws ArrayIndexOutOfBoundsException
     *             if {@code  index < 0 || index >= array.length}
     */
	public static native void setShort(Object array, int index, short value)
			throws IllegalArgumentException, ArrayIndexOutOfBoundsException;

}
