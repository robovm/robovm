/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.io;

import libcore.io.Memory;

/**
 * Iterates over big- or little-endian bytes on the native heap.
 * See {@link MemoryMappedFile#bigEndianIterator} and {@link MemoryMappedFile#littleEndianIterator}.
 *
 * @hide don't make this public without adding bounds checking.
 */
public final class NioBufferIterator extends BufferIterator {
    private final int address;
    private final int size;
    private final boolean swap;

    private int position;

    NioBufferIterator(int address, int size, boolean swap) {
        this.address = address;
        this.size = size;
        this.swap = swap;
    }

    public void seek(int offset) {
        position = offset;
    }

    public void skip(int byteCount) {
        position += byteCount;
    }

    public void readByteArray(byte[] dst, int dstOffset, int byteCount) {
        Memory.peekByteArray(address + position, dst, dstOffset, byteCount);
        position += byteCount;
    }

    public byte readByte() {
        byte result = Memory.peekByte(address + position);
        ++position;
        return result;
    }

    public int readInt() {
        int result = Memory.peekInt(address + position, swap);
        position += SizeOf.INT;
        return result;
    }

    public void readIntArray(int[] dst, int dstOffset, int intCount) {
        Memory.peekIntArray(address + position, dst, dstOffset, intCount, swap);
        position += SizeOf.INT * intCount;
    }

    public short readShort() {
        short result = Memory.peekShort(address + position, swap);
        position += SizeOf.SHORT;
        return result;
    }
}
