/*
 * Copyright (C) 2012 Trillian AB
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
import java.nio.charset.Charset;

import org.robovm.rt.VM;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * Points to an 8-bit signed value (<code>char *</code> in C).
 */
public final class BytePtr extends Struct<BytePtr> {
    
    /**
     * Pointer to {@link BytePtr} (<code>char **</code> in C)
     */
    public static class BytePtrPtr extends org.robovm.rt.bro.ptr.Ptr<BytePtr, BytePtrPtr> {}
    
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
     * Returns a {@link String} created from the NUL-terminated C string pointed 
     * to by this {@link BytePtr}. Non ASCII characters will be replaced with 
     * '?' in the result. This method is more efficient than using 
     * {@link #toStringZ(Charset)} with ASCII as {@link Charset}.
     * 
     * @return a {@link String} containing the same characters as the C string 
     *         pointed to.
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
     * Returns a {@link String} created from the NUL-terminated C string pointed 
     * to by this {@link BytePtr} using the default {@link Charset}. Illegal 
     * characters will be replaced with '?' in the result. This assumes that
     * the default {@link Charset} is an 8-bit encoding or a variable length
     * encoding with 8-bits as smallest bit length such as UTF-8.
     * 
     * @return a {@link String} converted from the C string bytes.
     */
    public String toStringZ() {
        return toStringZ(Charset.defaultCharset());
    }
    
    /**
     * Returns a {@link String} created from the NUL-terminated C string pointed 
     * to by this {@link BytePtr} using the specified {@link Charset}. Illegal 
     * characters will be replaced with '?' in the result.
     * 
     * @param charset the {@link Charset} to use. Must be an 8-bit or variable
     *        length character encoding with 8-bits as smallest value and that 
     *        can be NUL-terminated (e.g. UTF-8).
     * @return a {@link String} converted from the C string bytes.
     */
    public String toStringZ(Charset charset) {
        int length = 0;
        long address = getHandle();
        while (VM.getByte(address++) != 0) {
            length++;
        }
        return charset.decode(asByteBuffer(length)).toString();
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
     * Converts the specified {@link String} to a NUL-terminated C string of 
     * ASCII characters. Non ASCII characters will be replaced with '?' in the 
     * result. The memory will be allocated on the GCed heaped. This method is 
     * more efficient than using {@link #toStringZ(Charset)} with ASCII as 
     * {@link Charset}.
     * 
     * @param s the {@link String} to convert.
     * @return a {@link BytePtr} which points to the first character in the result.
     */
    public static BytePtr toBytePtrAsciiZ(String s) {
        return toBytePtrAsciiZ(s, false);
    }
    
    /**
     * Converts the specified {@link String} to a NUL-terminated C string of 
     * ASCII characters. Non ASCII characters will be replaced with '?' in the 
     * result. This method is more efficient than using 
     * {@link #toStringZ(Charset)} with ASCII as {@link Charset}.
     * 
     * @param s the {@link String} to convert.
     * @param useNativeHeap whether the memory should be allocated on the native 
     *        heap using {@code malloc()} or on the GCed heap.
     * @return a {@link BytePtr} which points to the first character in the result.
     */
    public static BytePtr toBytePtrAsciiZ(String s, boolean useNativeHeap) {
        int length = s.length();
        long handle = useNativeHeap ? VM.malloc(length + 1) : VM.allocateMemoryAtomic(length + 1);
        long address = handle;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c < 0x80) {
                VM.setByte(address, (byte) c);
            } else {
                VM.setByte(address, (byte) '?');
            }
            address++;
        }
        // VM.malloc() and VM.allocateMemoryAtomic() initialize the memory with 
        // zeroes so there's no need to set the NUL-terminator.
        return Struct.toStruct(BytePtr.class, handle);
    }
    
    /**
     * Converts the specified {@link String} to a NUL-terminated C string using
     * the default {@link Charset}. Illegal characters will be replaced with 
     * '?' in the result. The memory will be allocated on the GCed heaped.
     * This assumes that the default {@link Charset} is an 8-bit encoding or a
     * variable length encoding with 8-bits as smallest bit length such as 
     * UTF-8.
     * 
     * @param s the {@link String} to convert.
     * @return a {@link BytePtr} which points to the first character in the result.
     */
    public static BytePtr toBytePtrZ(String s) {
        return toBytePtrZ(s, Charset.defaultCharset(), false);
    }
    
    /**
     * Converts the specified {@link String} to a NUL-terminated C string using
     * the specified {@link Charset}. Illegal characters will be replaced with 
     * '?' in the result. The memory will be allocated on the GCed heaped.
     * 
     * @param s the {@link String} to convert.
     * @param charset the {@link Charset} to use. Must be an 8-bit or variable
     *        length character encoding with 8-bits as smallest value and that 
     *        can be NUL-terminated (e.g. UTF-8).
     * @return a {@link BytePtr} which points to the first character in the result.
     */
    public static BytePtr toBytePtrZ(String s, Charset charset) {
        return toBytePtrZ(s, charset, false);
    }
    
    /**
     * Converts the specified {@link String} to a NUL-terminated C string using
     * the specified {@link Charset}. Illegal characters will be replaced with 
     * '?' in the result.
     * 
     * @param s the {@link String} to convert.
     * @param charset the {@link Charset} to use. Must be an 8-bit or variable
     *        length character encoding with 8-bits as smallest value and that 
     *        can be NUL-terminated (e.g. UTF-8).
     * @param useNativeHeap whether the memory should be allocated on the native 
     *        heap using {@code malloc()} or on the GCed heap.
     * @return a {@link BytePtr} which points to the first character in the result.
     */
    public static BytePtr toBytePtrZ(String s, Charset charset, boolean useNativeHeap) {
        byte[] bytes = s.getBytes(charset);
        int length = bytes.length;
        long handle = useNativeHeap ? VM.malloc(length + 1) : VM.allocateMemoryAtomic(length + 1);
        VM.memcpy(handle, VM.getArrayValuesAddress(bytes), length);
        // VM.malloc() and VM.allocateMemoryAtomic() initialize the memory with 
        // zeroes so there's no need to set the NUL-terminator.
        return Struct.toStruct(BytePtr.class, handle);
    }
}
