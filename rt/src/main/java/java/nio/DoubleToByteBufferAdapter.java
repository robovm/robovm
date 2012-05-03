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

import libcore.io.SizeOf;

/**
 * This class wraps a byte buffer to be a double buffer.
 * <p>
 * Implementation notice:
 * <ul>
 * <li>After a byte buffer instance is wrapped, it becomes privately owned by
 * the adapter. It must NOT be accessed outside the adapter any more.</li>
 * <li>The byte buffer's position and limit are NOT linked with the adapter.
 * The adapter extends Buffer, thus has its own position and limit.</li>
 * </ul>
 * </p>
 *
 */
final class DoubleToByteBufferAdapter extends DoubleBuffer {

    private final ByteBuffer byteBuffer;

    static DoubleBuffer asDoubleBuffer(ByteBuffer byteBuffer) {
        ByteBuffer slice = byteBuffer.slice();
        slice.order(byteBuffer.order());
        return new DoubleToByteBufferAdapter(slice);
    }

    private DoubleToByteBufferAdapter(ByteBuffer byteBuffer) {
        super(byteBuffer.capacity() / SizeOf.DOUBLE);
        this.byteBuffer = byteBuffer;
        this.byteBuffer.clear();
        this.effectiveDirectAddress = byteBuffer.effectiveDirectAddress;
    }

    @Override
    public DoubleBuffer asReadOnlyBuffer() {
        DoubleToByteBufferAdapter buf = new DoubleToByteBufferAdapter(byteBuffer.asReadOnlyBuffer());
        buf.limit = limit;
        buf.position = position;
        buf.mark = mark;
        buf.byteBuffer.order = byteBuffer.order;
        return buf;
    }

    @Override
    public DoubleBuffer compact() {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        byteBuffer.limit(limit * SizeOf.DOUBLE);
        byteBuffer.position(position * SizeOf.DOUBLE);
        byteBuffer.compact();
        byteBuffer.clear();
        position = limit - position;
        limit = capacity;
        mark = UNSET_MARK;
        return this;
    }

    @Override
    public DoubleBuffer duplicate() {
        ByteBuffer bb = byteBuffer.duplicate().order(byteBuffer.order());
        DoubleToByteBufferAdapter buf = new DoubleToByteBufferAdapter(bb);
        buf.limit = limit;
        buf.position = position;
        buf.mark = mark;
        return buf;
    }

    @Override
    public double get() {
        if (position == limit) {
            throw new BufferUnderflowException();
        }
        return byteBuffer.getDouble(position++ * SizeOf.DOUBLE);
    }

    @Override
    public double get(int index) {
        checkIndex(index);
        return byteBuffer.getDouble(index * SizeOf.DOUBLE);
    }

    @Override
    public DoubleBuffer get(double[] dst, int dstOffset, int doubleCount) {
        byteBuffer.limit(limit * SizeOf.DOUBLE);
        byteBuffer.position(position * SizeOf.DOUBLE);
        if (byteBuffer instanceof DirectByteBuffer) {
            ((DirectByteBuffer) byteBuffer).get(dst, dstOffset, doubleCount);
        } else {
            ((HeapByteBuffer) byteBuffer).get(dst, dstOffset, doubleCount);
        }
        this.position += doubleCount;
        return this;
    }

    @Override
    public boolean isDirect() {
        return byteBuffer.isDirect();
    }

    @Override
    public boolean isReadOnly() {
        return byteBuffer.isReadOnly();
    }

    @Override
    public ByteOrder order() {
        return byteBuffer.order();
    }

    @Override
    protected double[] protectedArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected int protectedArrayOffset() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected boolean protectedHasArray() {
        return false;
    }

    @Override
    public DoubleBuffer put(double c) {
        if (position == limit) {
            throw new BufferOverflowException();
        }
        byteBuffer.putDouble(position++ * SizeOf.DOUBLE, c);
        return this;
    }

    @Override
    public DoubleBuffer put(int index, double c) {
        checkIndex(index);
        byteBuffer.putDouble(index * SizeOf.DOUBLE, c);
        return this;
    }

    @Override
    public DoubleBuffer put(double[] src, int srcOffset, int doubleCount) {
        byteBuffer.limit(limit * SizeOf.DOUBLE);
        byteBuffer.position(position * SizeOf.DOUBLE);
        if (byteBuffer instanceof ReadWriteDirectByteBuffer) {
            ((ReadWriteDirectByteBuffer) byteBuffer).put(src, srcOffset, doubleCount);
        } else {
            ((ReadWriteHeapByteBuffer) byteBuffer).put(src, srcOffset, doubleCount);
        }
        this.position += doubleCount;
        return this;
    }

    @Override
    public DoubleBuffer slice() {
        byteBuffer.limit(limit * SizeOf.DOUBLE);
        byteBuffer.position(position * SizeOf.DOUBLE);
        ByteBuffer bb = byteBuffer.slice().order(byteBuffer.order());
        DoubleBuffer result = new DoubleToByteBufferAdapter(bb);
        byteBuffer.clear();
        return result;
    }

}
