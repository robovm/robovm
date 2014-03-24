/*
 * Copyright (C) 2014 Trillian Mobile AB
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
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.util.Arrays;

import org.robovm.rt.VM;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.MachineSizedSInt;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * Points to a 32-bit or 64-bit signed value depending on the machine word
 * size (<code>long *</code> in C).
 */
public final class MachineSizedSIntPtr extends Struct<MachineSizedSIntPtr> {

    /**
     * Pointer to {@link MachineSizedSIntPtr} (<code>long **</code> in C)
     */
    public static class MachineSizedSIntPtrPtr extends org.robovm.rt.bro.ptr.Ptr<MachineSizedSIntPtr, MachineSizedSIntPtrPtr> {}
    
    /**
     * Creates a new {@link MachineSizedSIntPtr} with a value of 0.
     */
    public MachineSizedSIntPtr() {
    }
    
    /**
     * Returns the current value.
     * 
     * @return the value.
     */
    @StructMember(0)
    public native @MachineSizedSInt long get();
    
    /**
     * Sets the value.
     * 
     * @param value the new value.
     */
    @StructMember(0)
    public native void set(@MachineSizedSInt long value);

    /**
     * Returns a {@link IntBuffer} which reads and writes to the same memory
     * location pointed to by this {@link MachineSizedSIntPtr}. Must only be
     * called on 32-bit platforms.
     * 
     * @param n the maximum number of ints the {@link IntBuffer} can 
     *        read/write. This will be the {@link IntBuffer}'s 
     *        <code>capacity</code>.
     * @return the {@link IntBuffer}.
     * @throws IllegalStateException if this isn't a 32-bit platform.
     */
    public IntBuffer asIntBuffer(int n) {
        if (_sizeOf() != 4) {
            throw new IllegalStateException("Not a 32-bit platform");
        }
        return VM.newDirectByteBuffer(getHandle(), n << 2)
                .order(ByteOrder.nativeOrder()).asIntBuffer();
    }
    
    /**
     * Returns a {@link LongBuffer} which reads and writes to the same memory
     * location pointed to by this {@link MachineSizedSIntPtr}. Must only be
     * called on 64-bit platforms.
     * 
     * @param n the maximum number of longs the {@link LongBuffer} can 
     *        read/write. This will be the {@link LongBuffer}'s 
     *        <code>capacity</code>.
     * @return the {@link LongBuffer}.
     * @throws IllegalStateException if this isn't a 64-bit platform.
     */
    public LongBuffer asLongBuffer(int n) {
        if (_sizeOf() != 8) {
            throw new IllegalStateException("Not a 64-bit platform");
        }
        return VM.newDirectByteBuffer(getHandle(), n << 3)
                .order(ByteOrder.nativeOrder()).asLongBuffer();
    }
    
    /**
     * Copies {@code n} ints from the memory pointed to by this 
     * {@link MachineSizedSIntPtr} to a new {@code int[]} instance. Does the
     * proper narrowing {@code long} to {@code int} conversion if running on a 64-bit
     * platform.
     * 
     * @param n the number of ints to copy.
     * @return the {@code int[]}.
     */
    public int[] toIntArray(int n) {
        int[] result = new int[n];
        get(result);
        return result;
    }

    /**
     * Copies {@code dst.length} ints from the memory pointed to by this 
     * {@link MachineSizedSIntPtr} to {@code dst}. Does the proper 
     * narrowing {@code long} to {@code int} conversion if running on a 64-bit
     * platform.
     * 
     * @param dst the destination.
     */
    public void get(int[] dst) {
        get(dst, 0, dst.length);
    }

    /**
     * Copies {@code count} ints from the memory pointed to by this 
     * {@link MachineSizedSIntPtr} to {@code dst} starting at offset {@code offset}.
     * Does the proper narrowing {@code long} to {@code int} conversion if running on 
     * a 64-bit platform.
     * 
     * @param dst the destination.
     * @param offset the offset within the destination array to start copying to.
     * @param count the number of elements to copy.
     */
    public void get(int[] dst, int offset, int count) {
        if (_sizeOf() == 4) {
            asIntBuffer(count).get(dst, offset, count);
        } else {
            Arrays.checkOffsetAndCount(dst.length, offset, count);
            LongBuffer buf = asLongBuffer(count);
            for (int i = 0; i < count; i++) {
                dst[i + offset] = (int) buf.get();
            }
        }
    }

    /**
     * Copies {@code src.length} ints from {@code src} to the memory pointed 
     * to by this {@link MachineSizedSIntPtr}. Does signed
     * {@code int} to {@code long} conversion if running on a 64-bit platform.
     * 
     * @param src the source.
     */
    public void set(int[] src) {
        set(src, 0, src.length);
    }
    
    /**
     * Copies {@code count} ints from {@code src} starting at offset {@code offset}
     * to the memory pointed to by this {@link MachineSizedSIntPtr}. Does 
     * signed {@code int} to {@code long} conversion if running on a 64-bit 
     * platform.
     * 
     * @param src the source.
     * @param offset the offset within the source array to start copying from.
     * @param count the number of elements to copy.
     */
    public void set(int[] src, int offset, int count) {
        if (_sizeOf() == 4) {
            asIntBuffer(count).put(src, offset, count);
        } else {
            Arrays.checkOffsetAndCount(src.length, offset, count);
            LongBuffer buf = asLongBuffer(count);
            for (int i = 0; i < count; i++) {
                buf.put(src[i + offset]);
            }
        }
    }
    
    /**
     * Copies {@code n} longs from the memory pointed to by this 
     * {@link MachineSizedSIntPtr} to a new {@code long[]} instance. Does 
     * signed {@code int} to {@code long} conversion if running on a 32-bit
     * platform.
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
     * {@link MachineSizedSIntPtr} to {@code dst}. Does signed {@code int} to 
     * {@code long} conversion if running on a 32-bit platform.
     * 
     * @param dst the destination.
     */
    public void get(long[] dst) {
        get(dst, 0, dst.length);
    }

    /**
     * Copies {@code count} longs from the memory pointed to by this 
     * {@link MachineSizedSIntPtr} to {@code dst} starting at offset {@code offset}.
     * Does signed {@code int} to {@code long} conversion if running on 
     * a 32-bit platform.
     * 
     * @param dst the destination.
     * @param offset the offset within the destination array to start copying to.
     * @param count the number of elements to copy.
     */
    public void get(long[] dst, int offset, int count) {
        if (_sizeOf() == 8) {
            asLongBuffer(count).get(dst, offset, count);
        } else {
            Arrays.checkOffsetAndCount(dst.length, offset, count);
            IntBuffer buf = asIntBuffer(count);
            for (int i = 0; i < count; i++) {
                dst[i + offset] = buf.get();
            }
        }
    }

    /**
     * Copies {@code src.length} longs from {@code src} to the memory pointed 
     * to by this {@link MachineSizedSIntPtr}. 
     * Does the proper narrowing {@code long} to {@code int} conversion if running on 
     * a 32-bit platform.
     * 
     * @param src the source.
     */
    public void set(long[] src) {
        set(src, 0, src.length);
    }
    
    /**
     * Copies {@code count} longs from {@code src} starting at offset {@code offset}
     * to the memory pointed to by this {@link MachineSizedSIntPtr}. 
     * Does the proper narrowing {@code long} to {@code int} conversion if running on 
     * a 32-bit platform.
     * 
     * @param src the source.
     * @param offset the offset within the source array to start copying from.
     * @param count the number of elements to copy.
     */
    public void set(long[] src, int offset, int count) {
        if (_sizeOf() == 8) {
            asLongBuffer(count).put(src, offset, count);
        } else {
            Arrays.checkOffsetAndCount(src.length, offset, count);
            IntBuffer buf = asIntBuffer(count);
            for (int i = 0; i < count; i++) {
                buf.put((int) src[i + offset]);
            }
        }
    }
}
