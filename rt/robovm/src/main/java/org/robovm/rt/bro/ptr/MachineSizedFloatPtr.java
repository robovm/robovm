/*
 * Copyright (C) 2014 Trillian AB
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
import java.util.Arrays;

import org.robovm.rt.VM;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.MachineSizedFloat;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * Points to either a {@code float} or {@code double} value depending on the 
 * machine word.
 */
public final class MachineSizedFloatPtr extends Struct<MachineSizedFloatPtr> {

    /**
     * Pointer to {@link MachineSizedFloatPtr}.
     */
    public static class MachineSizedFloatPtrPtr extends org.robovm.rt.bro.ptr.Ptr<MachineSizedFloatPtr, MachineSizedFloatPtrPtr> {}
    
    /**
     * Creates a new {@link MachineSizedFloatPtr} with a value of 0.0.
     */
    public MachineSizedFloatPtr() {
    }
    
    /**
     * Returns the current value.
     * 
     * @return the value.
     */
    @StructMember(0)
    public native @MachineSizedFloat double get();
    
    /**
     * Sets the value.
     * 
     * @param value the new value.
     */
    @StructMember(0)
    public native void set(@MachineSizedFloat double value);
    

    /**
     * Returns a {@link FloatBuffer} which reads and writes to the same memory
     * location pointed to by this {@link MachineSizedFloatPtr}. Must only be
     * called on 32-bit platforms.
     * 
     * @param n the maximum number of floats the {@link FloatBuffer} can 
     *        read/write. This will be the {@link FloatBuffer}'s 
     *        <code>capacity</code>.
     * @return the {@link FloatBuffer}.
     * @throws IllegalStateException if this isn't a 32-bit platform.
     */
    public FloatBuffer asFloatBuffer(int n) {
        if (_sizeOf() != 4) {
            throw new IllegalStateException("Not a 32-bit platform");
        }
        return VM.newDirectByteBuffer(getHandle(), n << 2)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
    }
    
    /**
     * Returns a {@link DoubleBuffer} which reads and writes to the same memory
     * location pointed to by this {@link MachineSizedFloatPtr}. Must only be
     * called on 64-bit platforms.
     * 
     * @param n the maximum number of doubles the {@link DoubleBuffer} can 
     *        read/write. This will be the {@link DoubleBuffer}'s 
     *        <code>capacity</code>.
     * @return the {@link DoubleBuffer}.
     * @throws IllegalStateException if this isn't a 64-bit platform.
     */
    public DoubleBuffer asDoubleBuffer(int n) {
        if (_sizeOf() != 8) {
            throw new IllegalStateException("Not a 64-bit platform");
        }
        return VM.newDirectByteBuffer(getHandle(), n << 3)
                .order(ByteOrder.nativeOrder()).asDoubleBuffer();
    }
    
    /**
     * Copies {@code n} floats from the memory pointed to by this 
     * {@link MachineSizedFloatPtr} to a new {@code float[]} instance. Does
     * {@code double} to {@code float} conversion if running on a 64-bit
     * platform.
     * 
     * @param n the number of floats to copy.
     * @return the {@code float[]}.
     */
    public float[] toFloatArray(int n) {
        float[] result = new float[n];
        get(result);
        return result;
    }

    /**
     * Copies {@code dst.length} floats from the memory pointed to by this 
     * {@link MachineSizedFloatPtr} to {@code dst}. Does 
     * {@code double} to {@code float} conversion if running on a 64-bit
     * platform.
     * 
     * @param dst the destination.
     */
    public void get(float[] dst) {
        get(dst, 0, dst.length);
    }

    /**
     * Copies {@code count} floats from the memory pointed to by this 
     * {@link MachineSizedFloatPtr} to {@code dst} starting at offset {@code offset}.
     * Does {@code double} to {@code float} conversion if running on 
     * a 64-bit platform.
     * 
     * @param dst the destination.
     * @param offset the offset within the destination array to start copying to.
     * @param count the number of elements to copy.
     */
    public void get(float[] dst, int offset, int count) {
        if (_sizeOf() == 4) {
            asFloatBuffer(count).get(dst, offset, count);
        } else {
            Arrays.checkOffsetAndCount(dst.length, offset, count);
            DoubleBuffer buf = asDoubleBuffer(count);
            for (int i = 0; i < count; i++) {
                dst[i + offset] = (float) buf.get();
            }
        }
    }

    /**
     * Copies {@code src.length} floats from {@code src} to the memory pointed 
     * to by this {@link MachineSizedFloatPtr}. Does {@code float} 
     * to {@code double} conversion if running on a 64-bit platform.
     * 
     * @param src the source.
     */
    public void set(float[] src) {
        set(src, 0, src.length);
    }
    
    /**
     * Copies {@code count} floats from {@code src} starting at offset {@code offset}
     * to the memory pointed to by this {@link MachineSizedFloatPtr}. Does  
     * {@code float} to {@code double} conversion if running on a 64-bit 
     * platform.
     * 
     * @param src the source.
     * @param offset the offset within the source array to start copying from.
     * @param count the number of elements to copy.
     */
    public void set(float[] src, int offset, int count) {
        if (_sizeOf() == 4) {
            asFloatBuffer(count).put(src, offset, count);
        } else {
            Arrays.checkOffsetAndCount(src.length, offset, count);
            DoubleBuffer buf = asDoubleBuffer(count);
            for (int i = 0; i < count; i++) {
                buf.put(src[i + offset]);
            }
        }
    }
    
    /**
     * Copies {@code n} doubles from the memory pointed to by this 
     * {@link MachineSizedFloatPtr} to a new {@code double[]} instance. Does
     * {@code float} to {@code double} conversion if running on a 32-bit
     * platform.
     * 
     * @param n the number of doubles to copy.
     * @return the {@code float[]}.
     */
    public double[] toDoubleArray(int n) {
        double[] result = new double[n];
        get(result);
        return result;
    }

    /**
     * Copies {@code dst.length} doubles from the memory pointed to by this 
     * {@link MachineSizedFloatPtr} to {@code dst}. Does {@code float} to 
     * {@code double} conversion if running on a 32-bit platform.
     * 
     * @param dst the destination.
     */
    public void get(double[] dst) {
        get(dst, 0, dst.length);
    }

    /**
     * Copies {@code count} doubles from the memory pointed to by this 
     * {@link MachineSizedFloatPtr} to {@code dst} starting at offset {@code offset}.
     * Does {@code float} to {@code double} conversion if running on 
     * a 32-bit platform.
     * 
     * @param dst the destination.
     * @param offset the offset within the destination array to start copying to.
     * @param count the number of elements to copy.
     */
    public void get(double[] dst, int offset, int count) {
        if (_sizeOf() == 8) {
            asDoubleBuffer(count).get(dst, offset, count);
        } else {
            Arrays.checkOffsetAndCount(dst.length, offset, count);
            FloatBuffer buf = asFloatBuffer(count);
            for (int i = 0; i < count; i++) {
                dst[i + offset] = buf.get();
            }
        }
    }

    /**
     * Copies {@code src.length} doubles from {@code src} to the memory pointed 
     * to by this {@link MachineSizedFloatPtr}. Does {@code double} to
     * {@code float} conversion if running on a 32-bit platform.
     * 
     * @param src the source.
     */
    public void set(double[] src) {
        set(src, 0, src.length);
    }
    
    /**
     * Copies {@code count} doubles from {@code src} starting at offset {@code offset}
     * to the memory pointed to by this {@link MachineSizedFloatPtr}. Does  
     * {@code double} to {@code float} conversion if running on a 32-bit 
     * platform.
     * 
     * @param src the source.
     * @param offset the offset within the source array to start copying from.
     * @param count the number of elements to copy.
     */
    public void set(double[] src, int offset, int count) {
        if (_sizeOf() == 4) {
            asDoubleBuffer(count).put(src, offset, count);
        } else {
            Arrays.checkOffsetAndCount(src.length, offset, count);
            FloatBuffer buf = asFloatBuffer(count);
            for (int i = 0; i < count; i++) {
                buf.put((float) src[i + offset]);
            }
        }
    }
}
