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

import java.nio.channels.FileChannel.MapMode;
import libcore.io.SizeOf;

/**
 * Rather than duplicate all the code from ReadOnlyDirectByteBuffer and
 * ReadWriteDirectByteBuffer (and their superclasses), we delegate to one or the other.
 * The tricky part is that we need to keep our fields in sync with our delegate's fields.
 * There are lots of methods that access the fields directly.
 *
 * The main consequences of this implementation are:
 *
 * 1. we need to explicitly call wrapped.position(int) before any operation on our delegate
 * that makes use of the implicit position.
 *
 * 2. we need to explicitly update position after any operation on our delegate that makes
 * use of the implicit position.
 *
 * This means that, even more than usual, the implicit iteration
 * operations are more expensive than the indexed operations.
 *
 * But we save a ton of code, for classes that no-one really uses because the API's broken
 * by design (disallowing munmap(2) calls). Internally, we can use libcore.io.MemoryMappedFile
 * as a high-performance and more usable replacement for MappedByteBuffer.
 *
 * FIXME: harmony changed their implementation after we diverged, switching to a scheme
 * where DirectByteBuffer extends MappedByteBuffer and this class doesn't exist. That's
 * much better than their original implementation, fossilized here.
 */
final class MappedByteBufferAdapter extends MappedByteBuffer {
    private MappedByteBufferAdapter(ByteBuffer buffer) {
        super(buffer);
        effectiveDirectAddress = wrapped.effectiveDirectAddress;
    }

    public MappedByteBufferAdapter(MemoryBlock block, int capacity, int offset, MapMode mode) {
        super(block, capacity, offset, mode);
        effectiveDirectAddress = wrapped.effectiveDirectAddress;
    }

    @Override void limitImpl(int newLimit) {
        super.limitImpl(newLimit);
        wrapped.limit(newLimit);
    }

    @Override void positionImpl(int newPosition) {
        super.positionImpl(newPosition);
        wrapped.position(newPosition);
    }

    @Override
    public CharBuffer asCharBuffer() {
        return wrapped.asCharBuffer();
    }

    @Override
    public DoubleBuffer asDoubleBuffer() {
        return wrapped.asDoubleBuffer();
    }

    @Override
    public FloatBuffer asFloatBuffer() {
        return wrapped.asFloatBuffer();
    }

    @Override
    public IntBuffer asIntBuffer() {
        return wrapped.asIntBuffer();
    }

    @Override
    public LongBuffer asLongBuffer() {
        return wrapped.asLongBuffer();
    }

    @Override
    public ByteBuffer asReadOnlyBuffer() {
        MappedByteBufferAdapter result = new MappedByteBufferAdapter(wrapped.asReadOnlyBuffer());
        result.limit(limit);
        result.position(position);
        result.mark = mark;
        return result;
    }

    @Override
    public ShortBuffer asShortBuffer() {
        return wrapped.asShortBuffer();
    }

    @Override
    public ByteBuffer compact() {
        if (wrapped.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        wrapped.compact();
        limit(capacity);
        position(wrapped.position());
        this.mark = UNSET_MARK;
        return this;
    }

    @Override
    public ByteBuffer duplicate() {
        MappedByteBufferAdapter result = new MappedByteBufferAdapter(wrapped.duplicate());
        result.limit(limit);
        result.position(position);
        result.mark = mark;
        return result;
    }

    @Override
    public byte get() {
        wrapped.position(position);
        byte result = wrapped.get();
        ++position;
        return result;
    }

    @Override
    public byte get(int index) {
        return wrapped.get(index);
    }

    @Override
    public ByteBuffer get(byte[] dst, int dstOffset, int byteCount) {
        ByteBuffer result = wrapped.get(dst, dstOffset, byteCount);
        position += byteCount;
        return result;
    }

    @Override
    public char getChar() {
        wrapped.position(position);
        char result = wrapped.getChar();
        position += SizeOf.CHAR;
        return result;
    }

    @Override
    public char getChar(int index) {
        return wrapped.getChar(index);
    }

    @Override
    public double getDouble() {
        wrapped.position(position);
        double result = wrapped.getDouble();
        position += SizeOf.DOUBLE;
        return result;
    }

    @Override
    public double getDouble(int index) {
        return wrapped.getDouble(index);
    }

    @Override
    public float getFloat() {
        wrapped.position(position);
        float result = wrapped.getFloat();
        position += SizeOf.FLOAT;
        return result;
    }

    @Override
    public float getFloat(int index) {
        return wrapped.getFloat(index);
    }

    @Override
    public int getInt() {
        wrapped.position(position);
        int result = wrapped.getInt();
        position += SizeOf.INT;
        return result;
    }

    @Override
    public int getInt(int index) {
        return wrapped.getInt(index);
    }

    @Override
    public long getLong() {
        wrapped.position(position);
        long result = wrapped.getLong();
        position += SizeOf.LONG;
        return result;
    }

    @Override
    public long getLong(int index) {
        return wrapped.getLong(index);
    }

    @Override
    public short getShort() {
        wrapped.position(position);
        short result = wrapped.getShort();
        position += SizeOf.SHORT;
        return result;
    }

    @Override
    public short getShort(int index) {
        return wrapped.getShort(index);
    }

    @Override
    public boolean isDirect() {
        return true;
    }

    @Override
    public boolean isReadOnly() {
        return wrapped.isReadOnly();
    }

    @Override void orderImpl(ByteOrder byteOrder) {
        super.orderImpl(byteOrder);
        wrapped.order(byteOrder);
    }

    @Override
    public ByteBuffer put(byte b) {
        wrapped.position(this.position);
        wrapped.put(b);
        this.position++;
        return this;
    }

    @Override
    public ByteBuffer put(byte[] src, int srcOffset, int byteCount) {
        wrapped.position(this.position);
        wrapped.put(src, srcOffset, byteCount);
        this.position += byteCount;
        return this;
    }

    @Override
    public ByteBuffer put(int index, byte b) {
        wrapped.position(this.position);
        wrapped.put(index, b);
        return this;
    }

    @Override
    public ByteBuffer putChar(char value) {
        wrapped.position(this.position);
        wrapped.putChar(value);
        this.position += SizeOf.CHAR;
        return this;
    }

    @Override
    public ByteBuffer putChar(int index, char value) {
        wrapped.position(this.position);
        wrapped.putChar(index, value);
        return this;
    }

    @Override
    public ByteBuffer putDouble(double value) {
        wrapped.position(this.position);
        wrapped.putDouble(value);
        this.position += SizeOf.DOUBLE;
        return this;
    }

    @Override
    public ByteBuffer putDouble(int index, double value) {
        wrapped.position(this.position);
        wrapped.putDouble(index, value);
        return this;
    }

    @Override
    public ByteBuffer putFloat(float value) {
        wrapped.position(this.position);
        wrapped.putFloat(value);
        this.position += SizeOf.FLOAT;
        return this;
    }

    @Override
    public ByteBuffer putFloat(int index, float value) {
        wrapped.position(this.position);
        wrapped.putFloat(index, value);
        return this;
    }

    @Override
    public ByteBuffer putInt(int index, int value) {
        wrapped.position(this.position);
        wrapped.putInt(index, value);
        return this;
    }

    @Override
    public ByteBuffer putInt(int value) {
        wrapped.position(this.position);
        wrapped.putInt(value);
        this.position += SizeOf.INT;
        return this;
    }

    @Override
    public ByteBuffer putLong(int index, long value) {
        wrapped.position(this.position);
        wrapped.putLong(index, value);
        return this;
    }

    @Override
    public ByteBuffer putLong(long value) {
        wrapped.position(this.position);
        wrapped.putLong(value);
        this.position += SizeOf.LONG;
        return this;
    }

    @Override
    public ByteBuffer putShort(int index, short value) {
        wrapped.position(this.position);
        wrapped.putShort(index, value);
        return this;
    }

    @Override
    public ByteBuffer putShort(short value) {
        wrapped.position(this.position);
        wrapped.putShort(value);
        this.position += SizeOf.SHORT;
        return this;
    }

    @Override
    public ByteBuffer slice() {
        wrapped.position(this.position);
        MappedByteBufferAdapter result = new MappedByteBufferAdapter(wrapped.slice());
        wrapped.clear();
        return result;
    }

    @Override
    byte[] protectedArray() {
        return wrapped.protectedArray();
    }

    @Override
    int protectedArrayOffset() {
        return wrapped.protectedArrayOffset();
    }

    @Override
    boolean protectedHasArray() {
        return wrapped.protectedHasArray();
    }

    public final void free() {
        wrapped.free();
    }
}
