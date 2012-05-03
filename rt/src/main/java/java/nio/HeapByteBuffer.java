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

package java.nio;

import libcore.io.SizeOf;
import libcore.io.Memory;

/**
 * HeapByteBuffer, ReadWriteHeapByteBuffer and ReadOnlyHeapByteBuffer compose
 * the implementation of array based byte buffers.
 * <p>
 * HeapByteBuffer implements all the shared readonly methods and is extended by
 * the other two classes.
 * </p>
 * <p>
 * All methods are marked final for runtime performance.
 * </p>
 *
 */
abstract class HeapByteBuffer extends BaseByteBuffer {

    /**
     * These fields are non-private for NioUtils.unsafeArray.
     */
    final byte[] backingArray;
    final int offset;

    HeapByteBuffer(byte[] backingArray) {
        this(backingArray, backingArray.length, 0);
    }

    HeapByteBuffer(int capacity) {
        this(new byte[capacity], capacity, 0);
    }

    HeapByteBuffer(byte[] backingArray, int capacity, int offset) {
        super(capacity, null);
        this.backingArray = backingArray;
        this.offset = offset;
        if (offset + capacity > backingArray.length) {
            throw new IndexOutOfBoundsException("backingArray.length=" + backingArray.length +
                    ", capacity=" + capacity + ", offset=" + offset);
        }
    }

    @Override
    public final ByteBuffer get(byte[] dst, int dstOffset, int byteCount) {
        checkGetBounds(1, dst.length, dstOffset, byteCount);
        System.arraycopy(backingArray, offset + position, dst, dstOffset, byteCount);
        position += byteCount;
        return this;
    }

    final void get(char[] dst, int dstOffset, int charCount) {
        int byteCount = checkGetBounds(SizeOf.CHAR, dst.length, dstOffset, charCount);
        Memory.unsafeBulkGet(dst, dstOffset, byteCount, backingArray, offset + position, SizeOf.CHAR, order.needsSwap);
        position += byteCount;
    }

    final void get(double[] dst, int dstOffset, int doubleCount) {
        int byteCount = checkGetBounds(SizeOf.DOUBLE, dst.length, dstOffset, doubleCount);
        Memory.unsafeBulkGet(dst, dstOffset, byteCount, backingArray, offset + position, SizeOf.DOUBLE, order.needsSwap);
        position += byteCount;
    }

    final void get(float[] dst, int dstOffset, int floatCount) {
        int byteCount = checkGetBounds(SizeOf.FLOAT, dst.length, dstOffset, floatCount);
        Memory.unsafeBulkGet(dst, dstOffset, byteCount, backingArray, offset + position, SizeOf.FLOAT, order.needsSwap);
        position += byteCount;
    }

    final void get(int[] dst, int dstOffset, int intCount) {
        int byteCount = checkGetBounds(SizeOf.INT, dst.length, dstOffset, intCount);
        Memory.unsafeBulkGet(dst, dstOffset, byteCount, backingArray, offset + position, SizeOf.INT, order.needsSwap);
        position += byteCount;
    }

    final void get(long[] dst, int dstOffset, int longCount) {
        int byteCount = checkGetBounds(SizeOf.LONG, dst.length, dstOffset, longCount);
        Memory.unsafeBulkGet(dst, dstOffset, byteCount, backingArray, offset + position, SizeOf.LONG, order.needsSwap);
        position += byteCount;
    }

    final void get(short[] dst, int dstOffset, int shortCount) {
        int byteCount = checkGetBounds(SizeOf.SHORT, dst.length, dstOffset, shortCount);
        Memory.unsafeBulkGet(dst, dstOffset, byteCount, backingArray, offset + position, SizeOf.SHORT, order.needsSwap);
        position += byteCount;
    }

    @Override
    public final byte get() {
        if (position == limit) {
            throw new BufferUnderflowException();
        }
        return backingArray[offset + position++];
    }

    @Override
    public final byte get(int index) {
        checkIndex(index);
        return backingArray[offset + index];
    }

    @Override
    public final char getChar() {
        int newPosition = position + SizeOf.CHAR;
        if (newPosition > limit) {
            throw new BufferUnderflowException();
        }
        char result = (char) Memory.peekShort(backingArray, offset + position, order);
        position = newPosition;
        return result;
    }

    @Override
    public final char getChar(int index) {
        checkIndex(index, SizeOf.CHAR);
        return (char) Memory.peekShort(backingArray, offset + index, order);
    }

    @Override
    public final double getDouble() {
        return Double.longBitsToDouble(getLong());
    }

    @Override
    public final double getDouble(int index) {
        return Double.longBitsToDouble(getLong(index));
    }

    @Override
    public final float getFloat() {
        return Float.intBitsToFloat(getInt());
    }

    @Override
    public final float getFloat(int index) {
        return Float.intBitsToFloat(getInt(index));
    }

    @Override
    public final int getInt() {
        int newPosition = position + SizeOf.INT;
        if (newPosition > limit) {
            throw new BufferUnderflowException();
        }
        int result = Memory.peekInt(backingArray, offset + position, order);
        position = newPosition;
        return result;
    }

    @Override
    public final int getInt(int index) {
        checkIndex(index, SizeOf.INT);
        return Memory.peekInt(backingArray, offset + index, order);
    }

    @Override
    public final long getLong() {
        int newPosition = position + SizeOf.LONG;
        if (newPosition > limit) {
            throw new BufferUnderflowException();
        }
        long result = Memory.peekLong(backingArray, offset + position, order);
        position = newPosition;
        return result;
    }

    @Override
    public final long getLong(int index) {
        checkIndex(index, SizeOf.LONG);
        return Memory.peekLong(backingArray, offset + index, order);
    }

    @Override
    public final short getShort() {
        int newPosition = position + SizeOf.SHORT;
        if (newPosition > limit) {
            throw new BufferUnderflowException();
        }
        short result = Memory.peekShort(backingArray, offset + position, order);
        position = newPosition;
        return result;
    }

    @Override
    public final short getShort(int index) {
        checkIndex(index, SizeOf.SHORT);
        return Memory.peekShort(backingArray, offset + index, order);
    }

    @Override
    public final boolean isDirect() {
        return false;
    }
}
