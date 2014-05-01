/*
 * Copyright (C) 2012 Trillian Mobile AB
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

import java.nio.ByteOrder;
import java.nio.ShortBuffer;

import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * Points to a 16-bit signed value (<code>short *</code> in C).
 */
public final class ShortPtr extends Struct<ShortPtr> {

    /**
     * Pointer to {@link ShortPtr} (<code>short **</code> in C)
     */
    public static class ShortPtrPtr extends org.robovm.rt.bro.ptr.Ptr<ShortPtr, ShortPtrPtr> {}

    /**
     * Creates a new {@link ShortPtr} with a value of 0.
     */
    public ShortPtr() {
    }
    
    /**
     * Creates a new {@link ShortPtr} and initializes it with the specified value.
     * 
     * @param value the value.
     */
    public ShortPtr(short value) {
        set(value);
    }

    /**
     * Returns the current value.
     * 
     * @return the value.
     */
    @StructMember(0)
    public native short get();
    
    /**
     * Sets the value.
     * 
     * @param value the new value.
     */
    @StructMember(0)
    public native void set(short value);
    
    /**
     * Returns a {@link ShortBuffer} which reads and writes to the same memory
     * location pointed to by this {@link ShortPtr}.
     * 
     * @param n the maximum number of shorts the {@link ShortBuffer} can 
     *        read/write. This will be the {@link ShortBuffer}'s 
     *        <code>capacity</code>.
     * @return the {@link ShortBuffer}.
     */
    public ShortBuffer asShortBuffer(int n) {
        return as(BytePtr.class).asByteBuffer(n << 1).order(ByteOrder.nativeOrder()).asShortBuffer();
    }
    
    /**
     * Copies {@code n} shorts from the memory pointed to by this {@link ShortPtr}
     * to a new {@code short[]} instance.
     * 
     * @param n the number of shorts to copy.
     * @return the {@code short[]}.
     */
    public short[] toShortArray(int n) {
        short[] result = new short[n];
        get(result);
        return result;
    }

    /**
     * Copies {@code dst.length} shorts from the memory pointed to by this 
     * {@link ShortPtr} to {@code dst}.
     * 
     * @param dst the destination.
     */
    public void get(short[] dst) {
        get(dst, 0, dst.length);
    }

    /**
     * Copies {@code count} shorts from the memory pointed to by this 
     * {@link ShortPtr} to {@code dst} starting at offset {@code offset}.
     * 
     * @param dst the destination.
     * @param offset the offset within the destination array to start copying to.
     * @param count the number of elements to copy.
     */
    public void get(short[] dst, int offset, int count) {
        asShortBuffer(count).get(dst, offset, count);
    }

    /**
     * Copies {@code src.length} shorts from {@code src} to the memory pointed to by
     * this {@link ShortPtr}.
     * 
     * @param src the source.
     */
    public void set(short[] src) {
        set(src, 0, src.length);
    }
    
    /**
     * Copies {@code count} shorts from {@code src} starting at offset {@code offset}
     * to the memory pointed to by this {@link ShortPtr}.
     * 
     * @param src the source.
     * @param offset the offset within the source array to start copying from.
     * @param count the number of elements to copy.
     */
    public void set(short[] src, int offset, int count) {
        asShortBuffer(count).put(src, offset, count);
    }
}
