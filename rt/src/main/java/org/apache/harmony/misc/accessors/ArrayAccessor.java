/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.harmony.misc.accessors;

import java.util.HashMap;

import org.apache.harmony.misc.internal.nls.Messages;

/**
 * This class is a performance optimization aid which provides the low-level
 * access to arrays. It contains the following groups of methods:
 * <ul>
 * <li><b>lockArray/lockArrayLong methods </b>- used to lock the memory
 * location for the primitive type arrays for either short or long amounts of
 * time such that their contents can be directly accessed in the memory.
 * Typically, this is used to pass java arrays as arguments to native functions.
 * One array can not be locked for the short and long period of time
 * simultaneously.
 * 
 * <li><b>set/getElement methods </b>- used to read and write individual array
 * elements bypassing the bounds checks.
 * <li><b>getArrayBaseOffset/ElementSize methods </b>- used to obtain
 * information about arrays layout in the memory.
 * </ul>
 * The typical implementations of the {@link #lockArrayShort(Object)}and
 * {@link #lockArrayLong(Object)}methods would instruct a virtual machine that
 * the given array should stay unmovable for a certain period of time (i.e.
 * until it is released by {@link LockedArray#release()}call). On virtual
 * machines which do not support the unmovable arrays for some reason,
 * functionality of the <code>lockArray/release</code> pair still can be
 * emulated by copying the array content into the native heap and back. However,
 * whatever implementation exists, the {@link LockedArray#getAddress()}method
 * must return the memory location which is applicable for direct memory access
 * operations, such as the ones provided by {@link MemoryAccessor}class.
 * <p>
 * A typical usage example for the locked arrays would look like that:
 * 
 * <pre>
 * ArrayAccessor aa = AccessorFactory.getArrayAccessor();
 * byte[] bytearr = (byte[])aa.createArray(Byte.TYPE, 1024);
 * ...fill the bytearr ...
 * LockedArray la = aa.lockArrayShort(bytearr);
 * int pixmap = x11.XCreateBitmapFromData(display, wnd, la.getAddress(), width,
 *         height);
 * la.release();
 * </pre>
 */
public class ArrayAccessor {

    private static ArrayAccessor instance;

    static ArrayAccessor getInstance() {
        if (instance == null) {
            System.loadLibrary("accessors"); //$NON-NLS-1$
            instance = new ArrayAccessor();
        }
        return instance;
    }

    private ArrayAccessor() {
    }

    /**
     * Allocates a primitive type array that will be locked. The purpose of this
     * method is to give a hint to object allocator that an array will be locked.
     * This may help to increase the performance on certain virtual machine
     * implementations.
     *
     * @param type
     *            the primitive type class. For example, use the
     *            {@link Integer#TYPE}to specify the array of integers.
     * @param size
     *            number of elements in the array
     * @return allocated array
     */
    public Object createArray(Class type, int size) {
        if (type == Boolean.TYPE) {
            return new boolean[size];
        }
        if (type == Byte.TYPE) {
            return new byte[size];
        }
        if (type == Character.TYPE) {
            return new char[size];
        }
        if (type == Short.TYPE) {
            return new short[size];
        }
        if (type == Integer.TYPE) {
            return new int[size];
        }
        if (type == Long.TYPE) {
            return new long[size];
        }
        if (type == Float.TYPE) {
            return new float[size];
        }
        if (type == Double.TYPE) {
            return new double[size];
        }
        // misc.1=Non primitive type {0}
        throw new IllegalArgumentException(Messages.getString("misc.1", type)); //$NON-NLS-1$
    }

    /**
     * Internal hash which keeps the record for all locked arrays.
     */
    static final HashMap objectLockMap = new HashMap();

    /**
     * Allocates and immediately locks a primitive type array for supposedly a
     * long period of time. The purpose of this method is to give a hint to
     * object allocator that an array needs to be locked initially. This may
     * help to increase the performance on certain virtual machine
     * implementations.
     * 
     * @param type
     *            the primitive type class. For example, use the
     *            {@link Integer#TYPE}to specify the array of integers.
     * @param size
     *            number of elements in the array
     * @return allocated array
     * @see #lockArrayLong(Object)
     * @see LockedArray#getAddress()
     */
    public LockedArray createLockedArrayLong(Class type, int size) {
        Object array = createArray(type, size);
        return lockArrayLong(array);
    }

    /**
     * Allocates and immediately locks a primitive type array for supposedly a
     * short period of time. The purpose of this method is to give a hint to
     * object allocator that an array needs to be locked initially. This may
     * help to increase the performance on certain virtual machine
     * implementations. Use the {@link LockedArray#release()}method to unlock
     * the array after use.
     * 
     * @param type
     *            the primitive type class. For example, use the
     *            {@link Integer#TYPE}to specify the array of integers.
     * @param size
     *            number of elements in the array
     * @return allocated array
     * @see #lockArrayShort(Object)
     * @see LockedArray#getAddress()
     */
    public LockedArray createLockedArrayShort(Class type, int size) {
        Object array = createArray(type, size);
        return lockArrayShort(array);
    }

    /**
     * Locks an existing array for supposedly a short period of time.
     * Typically, this method would instruct a virtual machine that the given
     * array should stay unmovable for a short period of time, such as one
     * native library call. This method returns an instance of
     * {@link LockedArray}object which can be queried for the array memory
     * location. Use the {@link LockedArray#release()}method to unlock the
     * array after use.
     * <p>
     * Default implementation of this method delegates to the
     * <code>GetPrimitiveArrayCritical</code> JNI call.
     * <p>
     * Please note that synchronization and waiting (including native) must be
     * avoided between {@link #lockArrayShort(Object)}and
     * {@link LockedArray#release()}calls. If the array needs to be locked for
     * a long period of time, it is recommended to use the
     * {@link #lockArrayLong(Object)}method.
     * 
     * @see #lockArrayLong(Object)
     * @see LockedArray#release()
     * @see LockedArray#getAddress()
     * @throws RuntimeException
     *             if <code>array</code> is already locked.
     * @throws NullPointerException
     *             if <code>array</code> is <code>null</code>.
     * @param array
     *            an array of primitive type to lock
     * @return locked array object
     */
    public LockedArray lockArrayShort(Object array) {
        return lockArray(array, false);
    }

    /**
     * Locks an existing array for  supposedly a long period of time and returns
     * its location in memory. Typically, this method would instruct a virtual
     * machine that the given array should stay unmovable for a long period of
     * time. As a consequence, this method call may be expensive potentially may
     * reduce GC efficiency. Use the {@link #lockArrayShort(Object)}method in
     * case the array needs to be locked only for a short period of time. This
     * method returns an instance of {@link LockedArray}object which can then
     * be queried for the array memory location. To unlock the array, use the
     * {@link LockedArray#release()}method.
     * <p>
     * Default implementation of this method delegates to the
     * <code>GetXXXArrayElements</code> JNI call.
     * <p>
     * 
     * @see #lockArrayShort(Object)
     * @see LockedArray#release()
     * @see LockedArray#getAddress()
     * @throws RuntimeException
     *             if <code>array</code> is already locked.
     * @throws NullPointerException
     *             if <code>array</code> is <code>null</code>.
     * @param array
     *            an array of primitive type to lock
     * @return locked array object
     */
    public LockedArray lockArrayLong(Object array) {
        return lockArray(array, true);
    }

    private static LockedArray lockArray(Object array, boolean longLock) {
        synchronized (objectLockMap) {
            if (objectLockMap.get(array) != null) {
                // misc.2=array is already locked/pinned
                throw new RuntimeException(Messages.getString("misc.2")); //$NON-NLS-1$
            }
            long addr = 0;
            if (longLock) {
                if (array instanceof byte[]) {
                    addr = staticPinByteArray((byte[]) array);
                } else if (array instanceof char[]) {
                    addr = staticPinCharArray((char[]) array);
                } else if (array instanceof int[]) {
                    addr = staticPinIntArray((int[]) array);
                } else if (array instanceof short[]) {
                    addr = staticPinShortArray((short[]) array);
                } else if (array instanceof long[]) {
                    addr = staticPinLongArray((long[]) array);
                } else if (array instanceof float[]) {
                    addr = staticPinFloatArray((float[]) array);
                } else if (array instanceof double[]) {
                    addr = staticPinDoubleArray((double[]) array);
                }
            } else {
                addr = staticLockArray(array);
            }
            if (addr == 0) {
                // misc.3=lock failed
                throw new RuntimeException(Messages.getString("misc.3")); //$NON-NLS-1$
            }
            LockedArray la = new LockedArray(array, addr, longLock);
            objectLockMap.put(array, la);
            return la;
        }
    }

    static void releaseArray(Object array, long addr, boolean longLock) {
        synchronized (objectLockMap) {
            if (longLock) {
                if (array instanceof byte[]) {
                    staticUnpinByteArray((byte[]) array, addr);
                } else if (array instanceof char[]) {
                    staticUnpinCharArray((char[]) array, addr);
                } else if (array instanceof int[]) {
                    staticUnpinIntArray((int[]) array, addr);
                } else if (array instanceof short[]) {
                    staticUnpinShortArray((short[]) array, addr);
                } else if (array instanceof long[]) {
                    staticUnpinLongArray((long[]) array, addr);
                } else if (array instanceof float[]) {
                    staticUnpinFloatArray((float[]) array, addr);
                } else if (array instanceof double[]) {
                    staticUnpinDoubleArray((double[]) array, addr);
                }
            } else {
                staticUnlockArray(array, addr);
            }
            objectLockMap.remove(array);
        }
    }

    static void releaseArrayNoCopy(Object array, long addr, boolean longLock) {
        synchronized (objectLockMap) {
            if (longLock) {
                if (array instanceof byte[]) {
                    staticUnpinByteArrayNoCopy((byte[]) array, addr);
                } else if (array instanceof char[]) {
                    staticUnpinCharArrayNoCopy((char[]) array, addr);
                } else if (array instanceof int[]) {
                    staticUnpinIntArrayNoCopy((int[]) array, addr);
                } else if (array instanceof short[]) {
                    staticUnpinShortArrayNoCopy((short[]) array, addr);
                } else if (array instanceof long[]) {
                    staticUnpinLongArrayNoCopy((long[]) array, addr);
                } else if (array instanceof float[]) {
                    staticUnpinFloatArrayNoCopy((float[]) array, addr);
                } else if (array instanceof double[]) {
                    staticUnpinDoubleArrayNoCopy((double[]) array, addr);
                }
            } else {
                staticUnlockArrayNoCopy(array, addr);
            }
            objectLockMap.remove(array);
        }
    }

    /**
     * Reports the offset of the first element in the storage allocation of a
     * given array class.
     * 
     * @param arrayClass
     *            class of array (ex.: byte[].class)
     * @return the first element location
     * @see #getArrayElementSize(Class)
     */
    public final long getArrayBaseOffset(Class arrayClass) {
        return 0;
    }

    /**
     * Reports the element size for addressing elements in the storage
     * allocation of a given array class.
     * 
     * @param arrayClass
     *            class of array (ex.: byte[].class)
     * @return element size
     */
    public final int getArrayElementSize(Class arrayClass) {
        if (!arrayClass.isArray()) {
            // misc.4=not an array Class
            throw new RuntimeException(Messages.getString("misc.4")); //$NON-NLS-1$
        }
        if (arrayClass == byte[].class || arrayClass == boolean[].class) {
            return 1;
        }
        if (arrayClass == char[].class || arrayClass == short[].class) {
            return 2;
        }
        if (arrayClass == int[].class || arrayClass == float[].class) {
            return 4;
        }
        if (arrayClass == long[].class || arrayClass == double[].class) {
            return 8;
        }
        return 4;
    }

    /**
     * Reads a byte element at the given index without bounds check.
     * 
     * @see #setElement(byte[], int, byte)
     * @param arr
     *            array those element needs to be read
     * @param index
     *            element index
     * @return byte value of the element
     */
    public final native byte getElement(byte[] arr, int index);

    /**
     * Writes a byte element at the given index without bounds check.
     * 
     * @see #getElement(byte[], int)
     * @param arr
     *            array those element needs to be set
     * @param index
     *            element index
     * @param value
     *            a byte value to be set
     */
    public final native void setElement(byte[] arr, int index, byte value);

    /**
     * Reads a boolean element at the given index without bounds check.
     * 
     * @see #setElement(boolean[], int, boolean)
     * @param arr
     *            array those element needs to be read
     * @param index
     *            element index
     * @return boolean value of the element
     */
    public final native boolean getElement(boolean[] arr, int index);

    /**
     * Writes a boolean element at the given index without bounds check.
     * 
     * @see #getElement(boolean[], int)
     * @param arr
     *            array those element needs to be set
     * @param index
     *            element index
     * @param value
     *            a boolean value to be set
     */
    public final native void setElement(boolean[] arr, int index, boolean value);

    /**
     * Reads a char element at the given index without bounds check.
     * 
     * @see #setElement(char[], int, char)
     * @param arr
     *            array those element needs to be read
     * @param index
     *            element index
     * @return char value of the element
     */
    public final native char getElement(char[] arr, int index);

    /**
     * Writes a char element at the given index without bounds check.
     * 
     * @see #getElement(char[], int)
     * @param arr
     *            array those element needs to be set
     * @param index
     *            element index
     * @param value
     *            a char value to be set
     */
    public final native void setElement(char[] arr, int index, char value);

    /**
     * Reads a short element at the given index without bounds check.
     * 
     * @see #setElement(short[], int, short)
     * @param arr
     *            array those element needs to be read
     * @param index
     *            element index
     * @return short value of the element
     */
    public final native short getElement(short[] arr, int index);

    /**
     * Writes a short element at the given index without bounds check.
     * 
     * @see #getElement(short[], int)
     * @param arr
     *            array those element needs to be set
     * @param index
     *            element index
     * @param value
     *            a short value to be set
     */
    public final native void setElement(short[] arr, int index, short value);

    /**
     * Reads a int element at the given index without bounds check.
     * 
     * @see #setElement(int[], int, int)
     * @param arr
     *            array those element needs to be read
     * @param index
     *            element index
     * @return int value of the element
     */
    public final native int getElement(int[] arr, int index);

    /**
     * Writes a int element at the given index without bounds check.
     * 
     * @see #getElement(int[], int)
     * @param arr
     *            array those element needs to be set
     * @param index
     *            element index
     * @param value
     *            a int value to be set
     */
    public final native void setElement(int[] arr, int index, int value);

    /**
     * Reads a long element at the given index without bounds check.
     * 
     * @see #setElement(long[], int, long)
     * @param arr
     *            array those element needs to be read
     * @param index
     *            element index
     * @return long value of the element
     */
    public final native long getElement(long[] arr, int index);

    /**
     * Writes a long element at the given index without bounds check.
     * 
     * @see #getElement(long[], int)
     * @param arr
     *            array those element needs to be set
     * @param index
     *            element index
     * @param value
     *            a long value to be set
     */
    public final native void setElement(long[] arr, int index, long value);

    /**
     * Reads a float element at the given index without bounds check.
     * 
     * @see #setElement(float[], int, float)
     * @param arr
     *            array those element needs to be read
     * @param index
     *            element index
     * @return float value of the element
     */
    public final native float getElement(float[] arr, int index);

    /**
     * Writes a float element at the given index without bounds check.
     * 
     * @see #getElement(float[], int)
     * @param arr
     *            array those element needs to be set
     * @param index
     *            element index
     * @param value
     *            a float value to be set
     */
    public final native void setElement(float[] arr, int index, float value);

    /**
     * Reads a double element at the given index without bounds check.
     * 
     * @see #setElement(double[], int, double)
     * @param arr
     *            array those element needs to be read
     * @param index
     *            element index
     * @return double value of the element
     */
    public final native double getElement(double[] arr, int index);

    /**
     * Writes a double element at the given index without bounds check.
     * 
     * @see #getElement(double[], int)
     * @param arr
     *            array those element needs to be set
     * @param index
     *            element index
     * @param value
     *            a double value to be set
     */
    public final native void setElement(double[] arr, int index, double value);

    /**
     * Reads an Object element at the given index without bounds check.
     * 
     * @see #setElement(Object[], int, Object)
     * @param arr
     *            array those element needs to be read
     * @param index
     *            element index
     * @return Object value of the element
     */
    public final native Object getElement(Object[] arr, int index);

    /**
     * Writes an Object element at the given index without bounds check.
     * 
     * @see #getElement(Object[], int)
     * @param arr
     *            array those element needs to be set
     * @param index
     *            element index
     * @param value
     *            a Object value to be set
     */
    public final native void setElement(Object[] arr, int index, Object value);

    private static native long staticLockArray(Object array);

    static native void staticUnlockArray(Object array, long addr);

    static native void staticUnlockArrayNoCopy(Object array, long addr);

    private static native long staticPinByteArray(Object array);

    private static native long staticPinCharArray(Object array);

    private static native long staticPinShortArray(Object array);

    private static native long staticPinIntArray(Object array);

    private static native long staticPinLongArray(Object array);

    private static native long staticPinFloatArray(Object array);

    private static native long staticPinDoubleArray(Object array);

    private static native void staticUnpinByteArray(Object array, long l);

    private static native void staticUnpinCharArray(Object array, long l);

    private static native void staticUnpinShortArray(Object array, long l);

    private static native void staticUnpinIntArray(Object array, long l);

    private static native void staticUnpinLongArray(Object array, long l);

    private static native void staticUnpinFloatArray(Object array, long l);

    private static native void staticUnpinDoubleArray(Object array, long l);

    private static native void staticUnpinByteArrayNoCopy(Object array, long l);

    private static native void staticUnpinCharArrayNoCopy(Object array, long l);

    private static native void staticUnpinShortArrayNoCopy(Object array, long l);

    private static native void staticUnpinIntArrayNoCopy(Object array, long l);

    private static native void staticUnpinLongArrayNoCopy(Object array, long l);

    private static native void staticUnpinFloatArrayNoCopy(Object array, long l);

    private static native void staticUnpinDoubleArrayNoCopy(Object array, long l);

}
