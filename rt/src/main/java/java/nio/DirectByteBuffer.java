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

abstract class DirectByteBuffer extends BaseByteBuffer {
    // This is the offset into {@code Buffer.block} at which this buffer logically starts.
    // TODO: rewrite this so we set 'block' to an OffsetMemoryBlock?
    protected final int offset;

    protected DirectByteBuffer(MemoryBlock block, int capacity, int offset) {
        super(capacity, block);

        long baseSize = block.getSize();
        if (baseSize >= 0 && (capacity + offset) > baseSize) {
            throw new IllegalArgumentException("capacity + offset > baseSize");
        }

        this.offset = offset;
        this.effectiveDirectAddress = block.toInt() + offset;
    }

    @Override
    public final ByteBuffer get(byte[] dst, int dstOffset, int byteCount) {
        checkGetBounds(1, dst.length, dstOffset, byteCount);
        this.block.peekByteArray(offset + position, dst, dstOffset, byteCount);
        position += byteCount;
        return this;
    }

    final void get(char[] dst, int dstOffset, int charCount) {
        int byteCount = checkGetBounds(SizeOf.CHAR, dst.length, dstOffset, charCount);
        this.block.peekCharArray(offset + position, dst, dstOffset, charCount, order.needsSwap);
        position += byteCount;
    }

    final void get(double[] dst, int dstOffset, int doubleCount) {
        int byteCount = checkGetBounds(SizeOf.DOUBLE, dst.length, dstOffset, doubleCount);
        this.block.peekDoubleArray(offset + position, dst, dstOffset, doubleCount, order.needsSwap);
        position += byteCount;
    }

    final void get(float[] dst, int dstOffset, int floatCount) {
        int byteCount = checkGetBounds(SizeOf.FLOAT, dst.length, dstOffset, floatCount);
        this.block.peekFloatArray(offset + position, dst, dstOffset, floatCount, order.needsSwap);
        position += byteCount;
    }

    final void get(int[] dst, int dstOffset, int intCount) {
        int byteCount = checkGetBounds(SizeOf.INT, dst.length, dstOffset, intCount);
        this.block.peekIntArray(offset + position, dst, dstOffset, intCount, order.needsSwap);
        position += byteCount;
    }

    final void get(long[] dst, int dstOffset, int longCount) {
        int byteCount = checkGetBounds(SizeOf.LONG, dst.length, dstOffset, longCount);
        this.block.peekLongArray(offset + position, dst, dstOffset, longCount, order.needsSwap);
        position += byteCount;
    }

    final void get(short[] dst, int dstOffset, int shortCount) {
        int byteCount = checkGetBounds(SizeOf.SHORT, dst.length, dstOffset, shortCount);
        this.block.peekShortArray(offset + position, dst, dstOffset, shortCount, order.needsSwap);
        position += byteCount;
    }

    @Override
    public final byte get() {
        if (position == limit) {
            throw new BufferUnderflowException();
        }
        return this.block.peekByte(offset + position++);
    }

    @Override
    public final byte get(int index) {
        checkIndex(index);
        return this.block.peekByte(offset + index);
    }

    @Override
    public final char getChar() {
        int newPosition = position + SizeOf.CHAR;
        if (newPosition > limit) {
            throw new BufferUnderflowException();
        }
        char result = (char) this.block.peekShort(offset + position, order);
        position = newPosition;
        return result;
    }

    @Override
    public final char getChar(int index) {
        checkIndex(index, SizeOf.CHAR);
        return (char) this.block.peekShort(offset + index, order);
    }

    @Override
    public final double getDouble() {
        int newPosition = position + SizeOf.DOUBLE;
        if (newPosition > limit) {
            throw new BufferUnderflowException();
        }
        double result = Double.longBitsToDouble(this.block.peekLong(offset + position, order));
        position = newPosition;
        return result;
    }

    @Override
    public final double getDouble(int index) {
        checkIndex(index, SizeOf.DOUBLE);
        return Double.longBitsToDouble(this.block.peekLong(offset + index, order));
    }

    @Override
    public final float getFloat() {
        int newPosition = position + SizeOf.FLOAT;
        if (newPosition > limit) {
            throw new BufferUnderflowException();
        }
        float result = Float.intBitsToFloat(this.block.peekInt(offset + position, order));
        position = newPosition;
        return result;
    }

    @Override
    public final float getFloat(int index) {
        checkIndex(index, SizeOf.FLOAT);
        return Float.intBitsToFloat(this.block.peekInt(offset + index, order));
    }

    @Override
    public final int getInt() {
        int newPosition = position + SizeOf.INT;
        if (newPosition > limit) {
            throw new BufferUnderflowException();
        }
        int result = this.block.peekInt(offset + position, order);
        position = newPosition;
        return result;
    }

    @Override
    public final int getInt(int index) {
        checkIndex(index, SizeOf.INT);
        return this.block.peekInt(offset + index, order);
    }

    @Override
    public final long getLong() {
        int newPosition = position + SizeOf.LONG;
        if (newPosition > limit) {
            throw new BufferUnderflowException();
        }
        long result = this.block.peekLong(offset + position, order);
        position = newPosition;
        return result;
    }

    @Override
    public final long getLong(int index) {
        checkIndex(index, SizeOf.LONG);
        return this.block.peekLong(offset + index, order);
    }

    @Override
    public final short getShort() {
        int newPosition = position + SizeOf.SHORT;
        if (newPosition > limit) {
            throw new BufferUnderflowException();
        }
        short result = this.block.peekShort(offset + position, order);
        position = newPosition;
        return result;
    }

    @Override
    public final short getShort(int index) {
        checkIndex(index, SizeOf.SHORT);
        return this.block.peekShort(offset + index, order);
    }

    @Override
    public final boolean isDirect() {
        return true;
    }

    public final void free() {
        block.free();
    }

    @Override protected byte[] protectedArray() {
        byte[] array = this.block.array();
        if (array == null) {
            throw new UnsupportedOperationException();
        }
        return array;
    }

    @Override protected int protectedArrayOffset() {
        protectedArray(); // Check we have an array.
        return offset;
    }

    @Override protected boolean protectedHasArray() {
        return protectedArray() != null;
    }
}
