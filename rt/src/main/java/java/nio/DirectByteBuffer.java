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

import org.apache.harmony.luni.platform.PlatformAddress;
import org.apache.harmony.luni.platform.PlatformAddressFactory;
import org.apache.harmony.nio.internal.DirectBuffer;
import org.apache.harmony.nio.internal.nls.Messages;

/**
 * DirectByteBuffer, ReadWriteDirectByteBuffer and ReadOnlyDirectByteBuffer
 * compose the implementation of platform memory based byte buffers.
 * <p>
 * DirectByteBuffer implements all the shared readonly methods and is extended
 * by the other two classes.
 * </p>
 * <p>
 * All methods are marked final for runtime performance.
 * </p>
 * 
 */
abstract class DirectByteBuffer extends MappedByteBuffer implements DirectBuffer {

    // This is the offset from the base address at which this buffer logically
    // starts.
    protected final int offset;

    /*
     * Constructs a new direct byte buffer of the given capacity on newly
     * allocated OS memory. The memory will have been zeroed. When the instance
     * is discarded the OS memory will be freed if it has not already been done
     * so by an explicit call to #free(). Callers are encouraged to explicitly
     * free the memory where possible.
     */
    DirectByteBuffer(int capacity) {
        this(PlatformAddressFactory.alloc(capacity, (byte) 0), capacity, 0);
        address.autoFree();
    }

    DirectByteBuffer(PlatformAddress address, int capacity, int offset) {
        super(capacity);
        this.address = address;
        this.offset = offset;
        address.autoFree();
    }

    /*
     * Override ByteBuffer.get(byte[], int, int) to improve performance.
     * 
     * (non-Javadoc)
     * 
     * @see java.nio.ByteBuffer#get(byte[], int, int)
     */
    @Override
    public final ByteBuffer get(byte[] dest, int off, int len) {
        int length = dest.length;
        if ((off < 0) || (len < 0) || (long) off + (long) len > length) {
            throw new IndexOutOfBoundsException();
        }
        if (len > remaining()) {
            throw new BufferUnderflowException();
        }
        getBaseAddress().getByteArray(offset + position, dest, off, len);
        position += len;
        return this;
    }

    @Override
    public final byte get() {
        if (position == limit) {
            throw new BufferUnderflowException();
        }
        return getBaseAddress().getByte(offset + position++);
    }

    @Override
    public final byte get(int index) {
        if (index < 0 || index >= limit) {
            throw new IndexOutOfBoundsException();
        }
        return getBaseAddress().getByte(offset + index);
    }

    @Override
    public final double getDouble() {
        int newPosition = position + 8;
        if (newPosition > limit) {
            throw new BufferUnderflowException();
        }
        double result = getBaseAddress().getDouble(offset + position, order);
        position = newPosition;
        return result;
    }

    @Override
    public final double getDouble(int index) {
        if (index < 0 || (long) index + 8 > limit) {
            throw new IndexOutOfBoundsException();
        }
        return getBaseAddress().getDouble(offset + index, order);
    }

    @Override
    public final float getFloat() {
        int newPosition = position + 4;
        if (newPosition > limit) {
            throw new BufferUnderflowException();
        }
        float result = getBaseAddress().getFloat(offset + position, order);
        position = newPosition;
        return result;
    }

    @Override
    public final float getFloat(int index) {
        if (index < 0 || (long) index + 4 > limit) {
            throw new IndexOutOfBoundsException();
        }
        return getBaseAddress().getFloat(offset + index, order);
    }

    @Override
    public final int getInt() {
        int newPosition = position + 4;
        if (newPosition > limit) {
            throw new BufferUnderflowException();
        }
        int result = getBaseAddress().getInt(offset + position, order);
        position = newPosition;
        return result;
    }

    @Override
    public final int getInt(int index) {
        if (index < 0 || (long) index + 4 > limit) {
            throw new IndexOutOfBoundsException();
        }
        return getBaseAddress().getInt(offset + index, order);
    }

    @Override
    public final long getLong() {
        int newPosition = position + 8;
        if (newPosition > limit) {
            throw new BufferUnderflowException();
        }
        long result = getBaseAddress().getLong(offset + position, order);
        position = newPosition;
        return result;
    }

    @Override
    public final long getLong(int index) {
        if (index < 0 || (long) index + 8 > limit) {
            throw new IndexOutOfBoundsException();
        }
        return getBaseAddress().getLong(offset + index, order);
    }

    @Override
    public final short getShort() {
        int newPosition = position + 2;
        if (newPosition > limit) {
            throw new BufferUnderflowException();
        }
        short result = getBaseAddress().getShort(offset + position, order);
        position = newPosition;
        return result;
    }

    @Override
    public final short getShort(int index) {
        if (index < 0 || (long) index + 2 > limit) {
            throw new IndexOutOfBoundsException();
        }
        return getBaseAddress().getShort(offset + index, order);
    }

    @Override
    public final boolean isDirect() {
        return true;
    }

    public final boolean isAddressValid() {
        return address != PlatformAddress.INVALID;
    }

    public final void addressValidityCheck() {
        if (!isAddressValid()) {
            // nio.08=Cannot use the direct byte buffer after it has been
            // explicitly freed.
            throw new IllegalStateException(Messages.getString("nio.08")); //$NON-NLS-1$
        }
    }

    /*
     * Answers the base address of the buffer (i.e. before offset).
     */
    public final PlatformAddress getBaseAddress() {
        addressValidityCheck();
        return address;
    }

    /**
     * Answers the platform address of the start of this buffer instance.
     * <em>You must not attempt to free the returned address!!</em> It may not
     * be an address that was explicitly malloc'ed (i.e. if this buffer is the
     * result of a split); and it may be memory shared by multiple buffers.
     * <p>
     * If you can guarantee that you want to free the underlying memory call the
     * #free() method on this instance -- generally applications will rely on
     * the garbage collector to autofree this memory.
     * </p>
     * 
     * @return the effective address of the start of the buffer.
     * @throws IllegalStateException
     *             if this buffer address is known to have been freed
     *             previously.
     */
    public final PlatformAddress getEffectiveAddress() {
        return getBaseAddress().offsetBytes(offset);
    }

    /**
     * Explicitly free the memory used by this direct byte buffer. If the memory
     * has already been freed then this is a no-op. Once the memory has been
     * freed then operations requiring access to the memory will throw an
     * <code>IllegalStateException</code>.
     * <p>
     * Note it is possible that the memory is freed by code that reaches
     * into the address and explicitly frees it 'beneath' us -- this is bad
     * form.
     * </p>
     */
    public final void free() {
        if (isAddressValid()) {
            PlatformAddress a = address;
            address = PlatformAddress.INVALID;
            a.free();
        }
    }

    @Override
    final protected byte[] protectedArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    final protected int protectedArrayOffset() {
        throw new UnsupportedOperationException();
    }

    @Override
    final protected boolean protectedHasArray() {
        return false;
    }

    public final int getByteCapacity() {
        return capacity;
    }

    @Override
    public final CharBuffer asCharBuffer() {
        return CharToByteBufferAdapter.wrap(this);
    }

    @Override
    public final DoubleBuffer asDoubleBuffer() {
        return DoubleToByteBufferAdapter.wrap(this);
    }

    @Override
    public final FloatBuffer asFloatBuffer() {
        return FloatToByteBufferAdapter.wrap(this);
    }

    @Override
    public final IntBuffer asIntBuffer() {
        return IntToByteBufferAdapter.wrap(this);
    }

    @Override
    public final LongBuffer asLongBuffer() {
        return LongToByteBufferAdapter.wrap(this);
    }

    @Override
    public final ShortBuffer asShortBuffer() {
        return ShortToByteBufferAdapter.wrap(this);
    }

    @Override
    public final char getChar() {
        return (char) getShort();
    }

    @Override
    public final char getChar(int index) {
        return (char) getShort(index);
    }

    @Override
    public final ByteBuffer putChar(char value) {
        return putShort((short) value);
    }

    @Override
    public final ByteBuffer putChar(int index, char value) {
        return putShort(index, (short) value);
    }
}
