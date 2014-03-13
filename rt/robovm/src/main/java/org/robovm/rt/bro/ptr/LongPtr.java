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

import java.nio.ByteOrder;
import java.nio.LongBuffer;

import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * Points to a 64-bit signed value (<code>int64_t *</code> in C).
 */
public final class LongPtr extends Struct<LongPtr> {

    /**
     * Pointer to {@link LongPtr} (<code>int64_t **</code> in C)
     */
    public static class LongPtrPtr extends org.robovm.rt.bro.ptr.Ptr<LongPtr, LongPtrPtr> {}
    
    /**
     * Creates a new {@link LongPtr} with a value of 0.
     */
    public LongPtr() {
    }
    
    /**
     * Creates a new {@link LongPtr} and initializes it with the specified value.
     * 
     * @param value the value.
     */
    public LongPtr(long value) {
        set(value);
    }

    /**
     * Returns the current value.
     * 
     * @return the value.
     */
    @StructMember(0)
    public native long get();
    
    /**
     * Sets the value.
     * 
     * @param value the new value.
     */
    @StructMember(0)
    public native void set(long value);
    
    /**
     * Returns a {@link LongBuffer} which reads and writes to the same memory
     * location pointed to by this {@link LongPtr}.
     * 
     * @param n the maximum number of longs the {@link LongBuffer} can 
     *        read/write. This will be the {@link LongBuffer}'s 
     *        <code>capacity</code>.
     * @return the {@link LongBuffer}.
     */
    public LongBuffer asLongBuffer(int n) {
        return as(BytePtr.class).asByteBuffer(n << 3).order(ByteOrder.nativeOrder()).asLongBuffer();
    }    
    
    /**
     * Copies {@code n} longs from the memory pointed to by this {@link LongPtr}
     * to a new {@code long[]} instance.
     * 
     * @param n the number of longs to copy.
     * @return the {@code long[]}.
     */
    public long[] toLongArray(int n) {
        long[] result = new long[n];
        get(result);
        return result;
    }

    /**
     * Copies {@code dst.length} longs from the memory pointed to by this 
     * {@link LongPtr} to {@code dst}.
     * 
     * @param dst the destination.
     */
    public void get(long[] dst) {
        get(dst, 0, dst.length);
    }

    /**
     * Copies {@code count} longs from the memory pointed to by this 
     * {@link LongPtr} to {@code dst} starting at offset {@code offset}.
     * 
     * @param dst the destination.
     * @param offset the offset within the destination array to start copying to.
     * @param count the number of elements to copy.
     */
    public void get(long[] dst, int offset, int count) {
        asLongBuffer(count).get(dst, offset, count);
    }

    /**
     * Copies {@code src.length} longs from {@code src} to the memory pointed to by
     * this {@link LongPtr}.
     * 
     * @param src the source.
     */
    public void set(long[] src) {
        set(src, 0, src.length);
    }
    
    /**
     * Copies {@code count} longs from {@code src} starting at offset {@code offset}
     * to the memory pointed to by this {@link LongPtr}.
     * 
     * @param src the source.
     * @param offset the offset within the source array to start copying from.
     * @param count the number of elements to copy.
     */
    public void set(long[] src, int offset, int count) {
        asLongBuffer(count).put(src, offset, count);
    }
}
