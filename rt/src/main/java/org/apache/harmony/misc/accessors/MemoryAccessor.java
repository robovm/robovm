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

import org.apache.harmony.misc.internal.nls.Messages;

/**
 * The class describes low level memory operation for memory allocation/deallocation
 * and access.
 * <p>Type <code>long</code> is used for memory address representation on all platforms. 
 * On 32 bit platforms only low 32 address bits are used,
 * high bits can have arbitrary value.
 */
public class MemoryAccessor {


    private static MemoryAccessor instance;
    static MemoryAccessor getInstance() {
        if (instance == null) {
            System.loadLibrary("accessors"); //$NON-NLS-1$
            instance = new MemoryAccessor();
        }
        return instance;
    }
    private MemoryAccessor(){}
    
    /**
     * Means high order bytes in larger than byte type memory
     * representation will have higher addresses in memory than low order bytes.
     */
    public static final int BIG_ENDIAN = 0;

    /**
     * Means high order bytes in larger than byte type memory
     * representation will have lower addresses in memory than low order bytes.
     */
    public static final int LITTLE_ENDIAN = 1;

    /**
     * The cached value of the native byte order used by the current platform.
     */
    private final int nativeByteOrder = getNativeByteOrder0();

    /**
     * The cached value of the native pointer size of the current platform.
     */
    private final int pointerSize = Malloc.getPointerSize();

    /**
     * The cached value of the C long size of the current platform.
     */
    private final int cLongSize = Malloc.getCLongSize();

    /**
     * Finds the offset of the first different element in two
     * memory blocks. If no difference the size is returned. If reorder is set
     * changes the byte order of first block elements before comparison.
     * @param addr1 start address of the first block
     * @param addr2 start address of the second block
     * @param elemSize size of the element can be 1, 2, 4, 8
     * @param size number of elements to compare
     * @param reorder blocks are in different byte order
     * @return index of the first different element
     */
    public final long findFirstDiff(long addr1, long addr2, int elemSize, long size, boolean reorder) {
        if (elemSize == 1 || !reorder) {
            return findFirstDiff(addr1, addr2, elemSize * size) / elemSize;
        }
        switch (elemSize) {
        case 2:
            return findFirstDiffReorder16(addr1, addr2, size);
        case 4:
            return findFirstDiffReorder32(addr1, addr2, size);
        case 8:
            return findFirstDiffReorder64(addr1, addr2, size);
        }
        // misc.0=bad elemSize
        throw new IllegalArgumentException(Messages.getString("misc.0")); //$NON-NLS-1$
    }

    private native long findFirstDiff(long addr1, long addr2, long size);

    private native long findFirstDiffReorder16(long addr1, long addr2, long size);

    private native long findFirstDiffReorder32(long addr1, long addr2, long size);

    private native long findFirstDiffReorder64(long addr1, long addr2, long size);

    /**
     * Releases memory block previously allocated with malloc or realloc.
     * @param addr start address of block
     */

    public final void free(long addr) {
        Malloc.free(addr);
    }

    private native void getArray(long addr, Object array, long offset, long length);

    private native void getArrayReorder16(long addr, Object array, long offset, long length);

    private native void getArrayReorder32(long addr, Object array, long offset, long length);

    private native void getArrayReorder64(long addr, Object array, long offset, long length);

    /**
     * Reads a boolean value from memory address.
     * @param addr memory address
     * @return boolean value
     */
    public final native boolean getBoolean(long addr);

    /**
     * Reads data from memory address in platform byte order into boolean array.
     * @param addr data start address in memory
     * @param value array to save data
     * @param offset initial offset in java array
     * @param length number of array elements to read
     */
    public final void getBoolean(long addr, boolean[] value, int offset, int length) {
        getArray(addr, value, offset, length);
    }

    /**
     * Reads a byte value from memory address.
     * @param addr memory address
     * @return byte value
     */
    public final native byte getByte(long addr);

    /**
     * Reads data from memory address in platform byte order into byte array.
     * @param addr data start address in memory
     * @param value array to save data
     * @param offset initial offset in java array
     * @param length number of array elements to read
     */
    public final void getByte(long addr, byte[] value, int offset, int length) {
        getArray(addr, value, offset, length);
    }

    /**
     * Reads a char value from memory address in a platform byte order.
     * @param addr memory address
     * @return memory value
     */
    public final char getChar(long addr) {
        return (char)getShort(addr);
    }

    /**
     * Reads data from memory address in a platform byte order into char array.
     * @param addr data start address in memory
     * @param value array to save data
     * @param offset initial offset in java array
     * @param length number of array elements to read
     */
    public final void getChar(long addr, char[] value, int offset, int length) {
        getArray(addr, value, offset << 1, length << 1);
    }

    /**
     * Reads data from memory address in selected order into char array.
     * @param addr data start address in memory
     * @param value array to save data
     * @param offset initial offset in java array
     * @param length number of array elements to read
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     */
    public final void getChar(long addr, char[] value, int offset, int length, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            getArray(addr, value, offset << 1, length << 1);
            return;
        }
        getArrayReorder16(addr, value, offset, length);
    }

    /**
     * Reads a char value from memory address in selected order.     * 
     * @param addr memory address
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     * @return memory value
     */
    public final char getChar(long addr, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            return getChar(addr);
        }
        return getCharReorder(addr);
    }

    private char getCharReorder(long addr) {
        return (char)getShortReorder(addr);
    }

    /**
     * Reads a double value from memory address in a platform byte order.
     * @param addr memory address
     * @return memory value
     */
    public final native double getDouble(long addr);

    /**
     * Reads data from memory address in platform byte order into array of doubles.
     * @param addr data start address in memory
     * @param value array to save data
     * @param offset initial offset in java array
     * @param length number of array elements to read
     */
    public final void getDouble(long addr, double[] value, int offset, int length) {
        getArray(addr, value, offset << 3, length << 3);
    }

    /**
     * Reads data from memory address in selected byte order into array of doubles.
     * @param addr data start address in memory
     * @param value array to save data
     * @param offset initial offset in java array
     * @param length number of array elements to read
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     */
    public final void getDouble(long addr, double[] value, int offset, int length, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            getArray(addr, value, offset << 3, length << 3);
            return;
        }
        getArrayReorder64(addr, value, offset, length);
    }

    /**
     * Reads a double value from memory address in selected byte order.
     * @param addr memory address
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     * @return memory value
     */
    public final double getDouble(long addr, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            return getDouble(addr);
        }
        return getDoubleReorder(addr);
    }

    private final native double getDoubleReorder(long addr);

    /**
     * Reads a float value from memory address in platform byte order.
     * @param addr memory address
     * @return memory value
     */
    public final native float getFloat(long addr);

    /**
     * Reads data from memory address in platform byte order into float array.
     * @param addr data start address in memory
     * @param value array to save data
     * @param offset initial offset in java array
     * @param length number of array elements to read
     */

    public final void getFloat(long addr, float[] value, int offset, int length) {
        getArray(addr, value, offset << 2, length << 2);
    }

    /**
     * Reads data from memory address in selected byte order into float array.
     * @param addr data start address in memory
     * @param value array to save data
     * @param offset initial offset in java array
     * @param length number of array elements to read
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     */
    public final void getFloat(long addr, float[] value, int offset, int length, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            getArray(addr, value, offset << 2, length << 2);
            return;
        }
        getArrayReorder32(addr, value, offset, length);
    }

    /**
     * Reads a float value from memory address in selected byte order.
     * @param addr memory address
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     * @return memory value
     */
    public final float getFloat(long addr, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            return getFloat(addr);
        }
        return getFloatReorder(addr);
    }

    private final native float getFloatReorder(long addr);

    /**
     * Calculates the hash function of the block of bytes
     * @param addr memory address
     * @param size number of bytes to process
     * @return hash function value
     */
    public final native int getHashCode(long addr, long size);

    /**
     * Reads an integer value from memory address in platform byte order.
     * @param addr memory address
     * @return memory value
     */
    public final native int getInt(long addr);

    /**
     * Reads an integer value from memory address in selected byte order.
     * @param addr memory address
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     * @return memory value
     */
    public final int getInt(long addr, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            return getInt(addr);
        }
        return getIntReorder(addr);
    }

    /**
     * Reads data from memory address in platform byte order into array of integers.
     * @param addr data start address in memory
     * @param value array to save data
     * @param offset initial offset in java array
     * @param length number of array elements to read
     */
    public final void getInt(long addr, int[] value, int offset, int length) {
        getArray(addr, value, offset << 2, length << 2);
    }

    /**
     * Reads data from memory address in selected byte order into array of integers.
     * @param addr data start address in memory
     * @param value array to save data
     * @param offset initial offset in java array
     * @param length number of array elements to read
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     */
    public final void getInt(long addr, int[] value, int offset, int length, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            getArray(addr, value, offset << 2, length << 2);
            return;
        }
        getArrayReorder32(addr, value, offset, length);
    }

    private final native int getIntReorder(long addr);

    /**
     * Reads a long value from memory address in platform byte order.
     * @param addr memory address
     * @return memory value
     */
    public final native long getLong(long addr);

    /**
     * Reads a long value from memory address in selected byte order.
     * @param addr memory address
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     * @return memory value
     */
    public final long getLong(long addr, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            return getLong(addr);
        }
        return getLongReorder(addr);
    }

    /**
     * Reads data from memory address in platform byte order into array of longs.
     * @param addr data start address in memory
     * @param value array to save data
     * @param offset initial offset in java array
     * @param length number of array elements to read
     */
    public final void getLong(long addr, long[] value, int offset, int length) {
        getArray(addr, value, offset << 3, length << 3);
    }

    /**
     * Reads data from memory address in selected byte order into array of longs.
     * @param addr data start address in memory
     * @param value array to save data
     * @param offset initial offset in java array
     * @param length number of array elements to read
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     */
    public final void getLong(long addr, long[] value, int offset, int length, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            getArray(addr, value, offset << 3, length << 3);
            return;
        }
        getArrayReorder64(addr, value, offset, length);
    }

    private final native long getLongReorder(long addr);

    private native final int getNativeByteOrder0();

    /**
     * Reads a short value from memory address in platform byte order.
     * @param addr memory address
     * @return memory value
     */
    public final native short getShort(long addr);

    /**
     * Reads a short value from memory address in selected byte order.
     * @param addr memory address
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     * @return memory value
     */
    public final short getShort(long addr, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            return getShort(addr);
        }
        return getShortReorder(addr);
    }

    /**
     * Reads data from memory address in platform byte order into short array.
     * @param addr data start address in memory
     * @param value array to save data
     * @param offset initial offset in java array
     * @param length number of array elements to read
     */
    public final void getShort(long addr, short[] value, int offset, int length) {
        getArray(addr, value, offset << 1, length << 1);
    }

    /**
     * Reads data from memory address in selected byte order into short array.
     * @param addr data start address in memory
     * @param value array to save data
     * @param offset initial offset in java array
     * @param length number of array elements to read
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     */
    public final void getShort(long addr, short[] value, int offset, int length, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            getArray(addr, value, offset << 1, length << 1);
            return;
        }
        getArrayReorder16(addr, value, offset, length);
    }

    private native short getShortReorder(long addr);

    /**
     * Allocates memory block in the native heap.
     * @param size size of block in bytes
     * @return start address of allocated block
     */
    public final long malloc(long size) {
        return Malloc.malloc(size);
    }

    /**
     * Compares two memory blocks.
     * @param addr0 start address of block 0
     * @param addr1  start address of block 1
     * @param size size in bytes
     * @return 0 if equal byte by byte, < 0 if block 0 less than block 1,
     * > 0 if block 0 greater than block 1.
     */
    public final int memcmp(long addr0, long addr1, long size) {
        return Malloc.memcmp(addr0, addr1, size);
    }

    /**
     * Copies size bytes from src to dst, scr and dst can't
     * overlap.
     * @param dst start address of destination block
     * @param src start address of source block
     * @param size number of bytes to copy
     * @return dst
     */
    public final long memcpy(long dst, long src, long size) {
        return Malloc.memcpy(dst, src, size);
    }

    /**
     * Copies size bytes from src to dst, scr and dst can
     * overlap.
     * @param dst start address of destination block
     * @param src start address of source block
     * @param size number of bytes to copy
     * @return dst
     */
    public final long memmove(long dst, long src, long size) {
        return Malloc.memmove(dst, src, size);
    }

    /**
     * Fills the memory block with fill value.
     * @param addr start address of block
     * @param fill fill value
     * @param size number of bytes to fill
     * @return addr
     */
    public final long memset(long addr, byte fill, long size) {
        return Malloc.memset(addr, fill, size);
    }

    /**
     * Resizes memory block previously allocated with malloc or realloc.
     * @param addr start address of block
     * @param size new size in bytes
     * @return new start address of block
     */
    public final long realloc(long addr, long size) {
        return Malloc.realloc(addr, size);
    }

    private native void setArray(long addr, Object array, long offset, long length);

    private native void setArrayReorder16(long addr, Object array, long offset, long length);

    private native void setArrayReorder32(long addr, Object array, long offset, long length);

    private native void setArrayReorder64(long addr, Object array, long offset, long length);

    /**
     * Writes boolean value to memory address.
     * @param addr memory address
     * @param value new memory value
     */
    public final native void setBoolean(long addr, boolean value);

    /**
     * Writes data from boolean array to memory address in platform byte order.
     * @param addr data start address in memory
     * @param value data array
     * @param offset initial offset in java array
     * @param length number of array elements to write
     */
    public final void setBoolean(long addr, boolean[] value, int offset, int length) {
        setArray(addr, value, offset, length);
    }

    /**
     * Writes byte value to memory address.
     * @param addr memory address
     * @param value new memory value
     */
    public final native void setByte(long addr, byte value);

    /**
     * Writes data from byte array to memory address in platform byte order.
     * @param addr data start address in memory
     * @param value data array
     * @param offset initial offset in java array
     * @param length number of array elements to write
     */
    public final void setByte(long addr, byte[] value, int offset, int length) {
        setArray(addr, value, offset, length);
    }

    /**
     * Writes char value to memory address in platform byte order.
     * @param addr memory address
     * @param value new memory value
     */
    public final void setChar(long addr, char value) {
        setShort(addr, (short)value);
    }

    /**
     * Writes char value to memory address in selected order.
     * @param addr memory address
     * @param value new memory value
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     */
    public final void setChar(long addr, char value, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            setChar(addr, value);
            return;
        }
        setCharReorder(addr, value);
    }

    /**
     * Writes data from char array to memory address in platform byte order.
     * @param addr data start address in memory
     * @param value data array
     * @param offset initial offset in java array
     * @param length number of array elements to write
     */
    public final void setChar(long addr, char[] value, int offset, int length) {
        setArray(addr, value, offset << 1, length << 1);
    }

    /**
     * Writes data from char array to memory address in selected byte order.
     * @param addr data start address in memory
     * @param value data array
     * @param offset initial offset in java array
     * @param length number of array elements to write
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     */
    public final void setChar(long addr, char[] value, int offset, int length, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            setArray(addr, value, offset << 1, length << 1);
            return;
        }
        setArrayReorder16(addr, value, offset, length);
    }

    private void setCharReorder(long addr, char value) {
        setShortReorder(addr, (short)value);
    }

    /**
     * Writes a double value to memory address in platform byte order,
     * @param addr memory address
     * @param value new memory value
     */
    public final native void setDouble(long addr, double value);

    /**
     * Writes a double value to memory address in selected byte order.
     * @param addr memory address
     * @param value new memory value
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     */
    public final void setDouble(long addr, double value, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            setDouble(addr, value);
            return;
        }
        setDoubleReorder(addr, value);
    }

    /**
     * Writes data from double array to memory address in platform byte order.
     * @param addr data start address in memory
     * @param value data array
     * @param offset initial offset in java array
     * @param length number of array elements to write
     */
    public final void setDouble(long addr, double[] value, int offset, int length) {
        setArray(addr, value, offset << 3, length << 3);
    }

    /**
     * Writes data from a double array to memory address in selected byte order.
     * @param addr data start address in memory
     * @param value data array
     * @param offset initial offset in java array
     * @param length number of array elements to write
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     */
    public final void setDouble(long addr, double[] value, int offset, int length, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            setArray(addr, value, offset << 3, length << 3);
            return;
        }
        setArrayReorder64(addr, value, offset, length);
    }

    private final native void setDoubleReorder(long addr, double value);

    /**
     * Writes a float value to memory address in platform byte order.
     * @param addr memory address
     * @param value new memory value
     */
    public final native void setFloat(long addr, float value);

    /**
     * Writes data to memory address in selected byte order.
     * @param addr memory address
     * @param value new memory value
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     */
    public final void setFloat(long addr, float value, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            setFloat(addr, value);
            return;
        }
        setFloatReorder(addr, value);
    }

    /**
     * Writes data from float array to memory address in platform byte order.
     * @param addr data start address in memory
     * @param value data array
     * @param offset initial offset in java array
     * @param length number of array elements to write
     */
    public final void setFloat(long addr, float[] value, int offset, int length) {
        setArray(addr, value, offset << 2, length << 2);
    }

    /**
     * Writes data from float array to memory address in selected byte order.
     * @param addr data start address in memory
     * @param value data array
     * @param offset initial offset in java array
     * @param length number of array elements to write
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     */
    public final void setFloat(long addr, float[] value, int offset, int length, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            setArray(addr, value, offset << 2, length << 2);
            return;
        }
        setArrayReorder32(addr, value, offset, length);
    }

    private final native void setFloatReorder(long addr, float value);

    /**
     * Writes an integer value to memory address in  platform byte order.
     * @param addr memory address
     * @param value new memory value
     */
    public final native void setInt(long addr, int value);

    /**
     * Writes an integer value to memory address in selected byte order.
     * @param addr memory address
     * @param value new memory value
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     */
    public final void setInt(long addr, int value, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            setInt(addr, value);
            return;
        }
        setIntReorder(addr, value);
    }

    /**
     * Writes data from integer array to memory address in platform byte order.
     * @param addr data start address in memory
     * @param value data array
     * @param offset initial offset in java array
     * @param length number of array elements to write
     */
    public final void setInt(long addr, int[] value, int offset, int length) {
        setArray(addr, value, offset << 2, length << 2);
    }

    /**
     * Writes data from integer array to memory address in selected byte order.
     * @param addr data start address in memory
     * @param value data array
     * @param offset initial offset in java array
     * @param length number of array elements to write
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     */
    public final void setInt(long addr, int[] value, int offset, int length, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            setArray(addr, value, offset << 2, length << 2);
            return;
        }
        setArrayReorder32(addr, value, offset, length);
    }

    private final native void setIntReorder(long addr, int value);

    /**
     * Writes a long value to memory address in platform byte order.
     * @param addr memory address
     * @param value new memory value
     */
    public final native void setLong(long addr, long value);

    /**
     * Writes a long value to memory address in selected byte order.
     * @param addr memory address
     * @param value new memory value
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     */
    public final void setLong(long addr, long value, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            setLong(addr, value);
            return;
        }
        setLongReorder(addr, value);
    }

    /**
     * Writes data from long array to memory address in platform byte order.
     * @param addr data start address in memory
     * @param value data array
     * @param offset initial offset in java array
     * @param length number of array elements to write
     */
    public final void setLong(long addr, long[] value, int offset, int length) {
        setArray(addr, value, offset << 3, length << 3);
    }

    /**
     * Writes data from long array to memory address in selected byte order.
     * @param addr data start address in memory
     * @param value data array
     * @param offset initial offset in java array
     * @param length number of array elements to write
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     */
    public void setLong(long addr, long[] value, int offset, int length, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            setArray(addr, value, offset << 3, length << 3);
            return;
        }
        setArrayReorder64(addr, value, offset, length);
    }

    private final native void setLongReorder(long addr, long value);


    /**
     * Writes a short value to memory address in platform byte order.
     * @param addr memory address
     * @param value new memory value
     */
    public final native void setShort(long addr, short value);

    /**
     * Writes a short value to memory address in selected byte order.
     * @param addr memory address
     * @param value new memory value
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     */
    public final void setShort(long addr, short value, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            setShort(addr, value);
            return;
        }
        setShortReorder(addr, value);
    }

    /**
     * Writes data from short array to memory address in platform byte order.
     * @param addr data start address in memory
     * @param value data array
     * @param offset initial offset in java array
     * @param length number of array elements to write
     */
    public final void setShort(long addr, short[] value, int offset, int length) {
        setArray(addr, value, offset << 1, length << 1);
    }

    /**
     * Writes data from short array to memory address in selected byte order.
     * @param addr data start address in memory
     * @param value data array
     * @param offset initial offset in java array
     * @param length number of array elements to write
     * @param byteOrder LITTLE_ENDIAN or BIG_ENDIAN
     */
    public final void setShort(long addr, short[] value, int offset, int length, int byteOrder) {
        if (byteOrder == nativeByteOrder) {
            setArray(addr, value, offset << 1, length << 1);
            return;
        }
        setArrayReorder16(addr, value, offset, length);
    }

    private final native void setShortReorder(long addr, short value);

    /**
     * Returns native pointer value from addr.
     * @param addr address in memory
     * @return memory value
     */
    public final native long getPointer(long addr);

    /**
     * Writes a native pointer value to addr.
     * @param addr address in memory
     * @param value new memory value
     */
    public final native void setPointer(long addr, long value);
    
    /**
     * Returns the native pointer size of the current platform in bytes,
     * may be 4 or 8.
     */
    public final int getPointerSize() {
        return pointerSize;
    }

    /**
     * Returns the C long size of the current platform in bytes,
     * may be 4 or 8.
     */
    public final int getCLongSize() {
        return cLongSize;
    }

    /**
     * Returns byte order used by platform
     * @return LITTLE_ENDIAN or BIG_ENDIAN 
     */
    public final int getNativeByteOrder() {
        return nativeByteOrder;
    }

}