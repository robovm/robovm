/* Licensed to the Apache Software Foundation (ASF) under one or more
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

package java.nio;

import libcore.io.Memory;
import libcore.io.SizeOf;

/**
 * HeapByteBuffer, ReadWriteHeapByteBuffer and ReadOnlyHeapByteBuffer compose
 * the implementation of array based byte buffers.
 * <p>
 * ReadWriteHeapByteBuffer extends HeapByteBuffer with all the write methods.
 * </p>
 * <p>
 * This class is marked final for runtime performance.
 * </p>
 *
 */
final class ReadWriteHeapByteBuffer extends HeapByteBuffer {

    static ReadWriteHeapByteBuffer copy(HeapByteBuffer other, int markOfOther) {
        ReadWriteHeapByteBuffer buf =
                new ReadWriteHeapByteBuffer(other.backingArray, other.capacity(), other.offset);
        buf.limit = other.limit;
        buf.position = other.position();
        buf.mark = markOfOther;
        return buf;
    }

    ReadWriteHeapByteBuffer(byte[] backingArray) {
        super(backingArray);
    }

    ReadWriteHeapByteBuffer(int capacity) {
        super(capacity);
    }

    ReadWriteHeapByteBuffer(byte[] backingArray, int capacity, int arrayOffset) {
        super(backingArray, capacity, arrayOffset);
    }

    @Override
    public ByteBuffer asReadOnlyBuffer() {
        return ReadOnlyHeapByteBuffer.copy(this, mark);
    }

    @Override
    public ByteBuffer compact() {
        System.arraycopy(backingArray, position + offset, backingArray, offset, remaining());
        position = limit - position;
        limit = capacity;
        mark = UNSET_MARK;
        return this;
    }

    @Override
    public ByteBuffer duplicate() {
        return copy(this, mark);
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    @Override
    protected byte[] protectedArray() {
        return backingArray;
    }

    @Override
    protected int protectedArrayOffset() {
        return offset;
    }

    @Override
    protected boolean protectedHasArray() {
        return true;
    }

    @Override
    public ByteBuffer put(byte b) {
        if (position == limit) {
            throw new BufferOverflowException();
        }
        backingArray[offset + position++] = b;
        return this;
    }

    @Override
    public ByteBuffer put(int index, byte b) {
        checkIndex(index);
        backingArray[offset + index] = b;
        return this;
    }

    @Override
    public ByteBuffer put(byte[] src, int srcOffset, int byteCount) {
        checkPutBounds(1, src.length, srcOffset, byteCount);
        System.arraycopy(src, srcOffset, backingArray, offset + position, byteCount);
        position += byteCount;
        return this;
    }

    final void put(char[] src, int srcOffset, int charCount) {
        int byteCount = checkPutBounds(SizeOf.CHAR, src.length, srcOffset, charCount);
        Memory.unsafeBulkPut(backingArray, offset + position, byteCount, src, srcOffset, SizeOf.CHAR, order.needsSwap);
        position += byteCount;
    }

    final void put(double[] src, int srcOffset, int doubleCount) {
        int byteCount = checkPutBounds(SizeOf.DOUBLE, src.length, srcOffset, doubleCount);
        Memory.unsafeBulkPut(backingArray, offset + position, byteCount, src, srcOffset, SizeOf.DOUBLE, order.needsSwap);
        position += byteCount;
    }

    final void put(float[] src, int srcOffset, int floatCount) {
        int byteCount = checkPutBounds(SizeOf.FLOAT, src.length, srcOffset, floatCount);
        Memory.unsafeBulkPut(backingArray, offset + position, byteCount, src, srcOffset, SizeOf.FLOAT, order.needsSwap);
        position += byteCount;
    }

    final void put(int[] src, int srcOffset, int intCount) {
        int byteCount = checkPutBounds(SizeOf.INT, src.length, srcOffset, intCount);
        Memory.unsafeBulkPut(backingArray, offset + position, byteCount, src, srcOffset, SizeOf.INT, order.needsSwap);
        position += byteCount;
    }

    final void put(long[] src, int srcOffset, int longCount) {
        int byteCount = checkPutBounds(SizeOf.LONG, src.length, srcOffset, longCount);
        Memory.unsafeBulkPut(backingArray, offset + position, byteCount, src, srcOffset, SizeOf.LONG, order.needsSwap);
        position += byteCount;
    }

    final void put(short[] src, int srcOffset, int shortCount) {
        int byteCount = checkPutBounds(SizeOf.SHORT, src.length, srcOffset, shortCount);
        Memory.unsafeBulkPut(backingArray, offset + position, byteCount, src, srcOffset, SizeOf.SHORT, order.needsSwap);
        position += byteCount;
    }

    @Override
    public ByteBuffer putChar(int index, char value) {
        checkIndex(index, SizeOf.CHAR);
        Memory.pokeShort(backingArray, offset + index, (short) value, order);
        return this;
    }

    @Override
    public ByteBuffer putChar(char value) {
        int newPosition = position + SizeOf.CHAR;
        if (newPosition > limit) {
            throw new BufferOverflowException();
        }
        Memory.pokeShort(backingArray, offset + position, (short) value, order);
        position = newPosition;
        return this;
    }

    @Override
    public ByteBuffer putDouble(double value) {
        return putLong(Double.doubleToRawLongBits(value));
    }

    @Override
    public ByteBuffer putDouble(int index, double value) {
        return putLong(index, Double.doubleToRawLongBits(value));
    }

    @Override
    public ByteBuffer putFloat(float value) {
        return putInt(Float.floatToRawIntBits(value));
    }

    @Override
    public ByteBuffer putFloat(int index, float value) {
        return putInt(index, Float.floatToRawIntBits(value));
    }

    @Override
    public ByteBuffer putInt(int value) {
        int newPosition = position + SizeOf.INT;
        if (newPosition > limit) {
            throw new BufferOverflowException();
        }
        Memory.pokeInt(backingArray, offset + position, value, order);
        position = newPosition;
        return this;
    }

    @Override
    public ByteBuffer putInt(int index, int value) {
        checkIndex(index, SizeOf.INT);
        Memory.pokeInt(backingArray, offset + index, value, order);
        return this;
    }

    @Override
    public ByteBuffer putLong(int index, long value) {
        checkIndex(index, SizeOf.LONG);
        Memory.pokeLong(backingArray, offset + index, value, order);
        return this;
    }

    @Override
    public ByteBuffer putLong(long value) {
        int newPosition = position + SizeOf.LONG;
        if (newPosition > limit) {
            throw new BufferOverflowException();
        }
        Memory.pokeLong(backingArray, offset + position, value, order);
        position = newPosition;
        return this;
    }

    @Override
    public ByteBuffer putShort(int index, short value) {
        checkIndex(index, SizeOf.SHORT);
        Memory.pokeShort(backingArray, offset + index, value, order);
        return this;
    }

    @Override
    public ByteBuffer putShort(short value) {
        int newPosition = position + SizeOf.SHORT;
        if (newPosition > limit) {
            throw new BufferOverflowException();
        }
        Memory.pokeShort(backingArray, offset + position, value, order);
        position = newPosition;
        return this;
    }

    @Override
    public ByteBuffer slice() {
        return new ReadWriteHeapByteBuffer(backingArray, remaining(), offset + position);
    }
}
