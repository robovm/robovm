/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.rt.bro.ptr;

import java.nio.ByteBuffer;

import org.robovm.rt.VM;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * Points to an 8-bit signed value (<code>char *</code> in C).
 */
public final class BytePtr extends Struct<BytePtr> {
    /**
     * Creates a new {@link BytePtr} with a value of 0.
     */
    public BytePtr() {
    }
    
    /**
     * Creates a new {@link BytePtr} and initializes it with the specified value.
     * 
     * @param value the value.
     */
    public BytePtr(byte value) {
        set(value);
    }

    /**
     * Returns the current value.
     * 
     * @return the value.
     */
    @StructMember(0)
    public native byte get();
    
    /**
     * Sets the value.
     * 
     * @param value the new value.
     */
    @StructMember(0)
    public native void set(byte value);
    
    /**
     * Returns a {@link String} created from the NUL-terminated C string pointed to by this 
     * {@link BytePtr}. Non ASCII characters will be replaced with '?' in the result.
     * 
     * @return a {@link String} containing the same characters as the C string pointed to.
     */
    public String toStringAsciiZ() {
        int length = 0;
        int b = 0;
        long address = getHandle();
        while ((b = VM.getByte(address++)) != 0) {
            length++;
        }
        StringBuilder sb = new StringBuilder(length);
        address = getHandle();
        while ((b = VM.getByte(address++)) != 0) {
            if (b < 0x80) {
                sb.append((char) b);
            } else {
                sb.append('?');
            }
        }
        return sb.toString();
    }
    
    /**
     * Returns a {@link ByteBuffer} which reads and writes to the same memory
     * location pointed to by this {@link BytePtr}.
     * 
     * @param n the maximum number of bytes the {@link ByteBuffer} can 
     *        read/write. This will be the {@link ByteBuffer}'s 
     *        <code>capacity</code>.
     * @return the {@link ByteBuffer}.
     */
    public ByteBuffer asByteBuffer(int n) {
        return VM.newDirectByteBuffer(getHandle(), n);
    }
    
    /**
     * Converts the specified {@link String} to a NUL-terminated C string of ASCII characters. Non
     * ASCII characters will be replaced with '?' in the result.
     * 
     * @param s the {@link String} to convert.
     * @return a {@link BytePtr} which points to the first character in the result.
     */
    public static BytePtr toBytePtrAsciiZ(String s) {
        int length = s.length();
        long handle = VM.allocateMemoryAtomic(length + 1);
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c < 0x80) {
                bytes[i] = (byte) c;
            } else {
                bytes[i] = '?';
            }
        }
        VM.memcpy(handle, VM.getArrayValuesAddress(bytes), length);
        VM.setByte(handle + length, (byte) 0);
        return Struct.toStruct(BytePtr.class, handle);
    }
}
