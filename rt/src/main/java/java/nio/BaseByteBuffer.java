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

/**
 * Serves as the root of other byte buffer impl classes, implements common
 * methods that can be shared by child classes.
 */
abstract class BaseByteBuffer extends ByteBuffer {

    protected BaseByteBuffer(int capacity, MemoryBlock block) {
        super(capacity, block);
    }

    @Override
    public final CharBuffer asCharBuffer() {
        return CharToByteBufferAdapter.asCharBuffer(this);
    }

    @Override
    public final DoubleBuffer asDoubleBuffer() {
        return DoubleToByteBufferAdapter.asDoubleBuffer(this);
    }

    @Override
    public final FloatBuffer asFloatBuffer() {
        return FloatToByteBufferAdapter.asFloatBuffer(this);
    }

    @Override
    public final IntBuffer asIntBuffer() {
        return IntToByteBufferAdapter.asIntBuffer(this);
    }

    @Override
    public final LongBuffer asLongBuffer() {
        return LongToByteBufferAdapter.asLongBuffer(this);
    }

    @Override
    public final ShortBuffer asShortBuffer() {
        return ShortToByteBufferAdapter.asShortBuffer(this);
    }

    @Override
    public char getChar() {
        return (char) getShort();
    }

    @Override
    public char getChar(int index) {
        return (char) getShort(index);
    }

    @Override
    public ByteBuffer putChar(char value) {
        return putShort((short) value);
    }

    @Override
    public ByteBuffer putChar(int index, char value) {
        return putShort(index, (short) value);
    }
}
