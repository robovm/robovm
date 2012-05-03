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
 * This class wraps a byte buffer to be a float buffer.
 * <p>
 * Implementation notice:
 * <ul>
 * <li>After a byte buffer instance is wrapped, it becomes privately owned by
 * the adapter. It must NOT be accessed outside the adapter any more.</li>
 * <li>The byte buffer's position and limit are NOT linked with the adapter.
 * The adapter extends Buffer, thus has its own position and limit.</li>
 * </ul>
 * </p>
 */
final class FloatToByteBufferAdapter extends FloatBuffer {

    private final ByteBuffer byteBuffer;

    static FloatBuffer asFloatBuffer(ByteBuffer byteBuffer) {
        ByteBuffer slice = byteBuffer.slice();
        slice.order(byteBuffer.order());
        return new FloatToByteBufferAdapter(slice);
    }

    FloatToByteBufferAdapter(ByteBuffer byteBuffer) {
        super(byteBuffer.capacity() / SizeOf.FLOAT);
        this.byteBuffer = byteBuffer;
        this.byteBuffer.clear();
        this.effectiveDirectAddress = byteBuffer.effectiveDirectAddress;
    }

    @Override
    public FloatBuffer asReadOnlyBuffer() {
        FloatToByteBufferAdapter buf = new FloatToByteBufferAdapter(byteBuffer.asReadOnlyBuffer());
        buf.limit = limit;
        buf.position = position;
        buf.mark = mark;
        buf.byteBuffer.order = byteBuffer.order;
        return buf;
    }

    @Override
    public FloatBuffer compact() {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        byteBuffer.limit(limit * SizeOf.FLOAT);
        byteBuffer.position(position * SizeOf.FLOAT);
        byteBuffer.compact();
        byteBuffer.clear();
        position = limit - position;
        limit = capacity;
        mark = UNSET_MARK;
        return this;
    }

    @Override
    public FloatBuffer duplicate() {
        ByteBuffer bb = byteBuffer.duplicate().order(byteBuffer.order());
        FloatToByteBufferAdapter buf = new FloatToByteBufferAdapter(bb);
        buf.limit = limit;
        buf.position = position;
        buf.mark = mark;
        return buf;
    }

    @Override
    public float get() {
        if (position == limit) {
            throw new BufferUnderflowException();
        }
        return byteBuffer.getFloat(position++ * SizeOf.FLOAT);
    }

    @Override
    public float get(int index) {
        checkIndex(index);
        return byteBuffer.getFloat(index * SizeOf.FLOAT);
    }

    @Override
    public FloatBuffer get(float[] dst, int dstOffset, int floatCount) {
        byteBuffer.limit(limit * SizeOf.FLOAT);
        byteBuffer.position(position * SizeOf.FLOAT);
        if (byteBuffer instanceof DirectByteBuffer) {
            ((DirectByteBuffer) byteBuffer).get(dst, dstOffset, floatCount);
        } else {
            ((HeapByteBuffer) byteBuffer).get(dst, dstOffset, floatCount);
        }
        this.position += floatCount;
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
    protected float[] protectedArray() {
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
    public FloatBuffer put(float c) {
        if (position == limit) {
            throw new BufferOverflowException();
        }
        byteBuffer.putFloat(position++ * SizeOf.FLOAT, c);
        return this;
    }

    @Override
    public FloatBuffer put(int index, float c) {
        checkIndex(index);
        byteBuffer.putFloat(index * SizeOf.FLOAT, c);
        return this;
    }

    @Override
    public FloatBuffer put(float[] src, int srcOffset, int floatCount) {
        byteBuffer.limit(limit * SizeOf.FLOAT);
        byteBuffer.position(position * SizeOf.FLOAT);
        if (byteBuffer instanceof ReadWriteDirectByteBuffer) {
            ((ReadWriteDirectByteBuffer) byteBuffer).put(src, srcOffset, floatCount);
        } else {
            ((ReadWriteHeapByteBuffer) byteBuffer).put(src, srcOffset, floatCount);
        }
        this.position += floatCount;
        return this;
    }

    @Override
    public FloatBuffer slice() {
        byteBuffer.limit(limit * SizeOf.FLOAT);
        byteBuffer.position(position * SizeOf.FLOAT);
        ByteBuffer bb = byteBuffer.slice().order(byteBuffer.order());
        FloatBuffer result = new FloatToByteBufferAdapter(bb);
        byteBuffer.clear();
        return result;
    }

}
