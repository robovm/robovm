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

import java.util.Arrays;

import org.robovm.rt.VM;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * Points to an 8-bit {@code boolean} value. When reading 0 is interpreted as
 * {@code false}. and non-zero values are interpreted as {@code true}. When
 * writing 0 will be written for {@code false} and 1 will be written for
 * {@code true}.
 */
public final class BooleanPtr extends Struct<BooleanPtr> {

    /**
     * Pointer to {@link BooleanPtr}
     */
    public static class BooleanPtrPtr extends org.robovm.rt.bro.ptr.Ptr<BooleanPtr, BooleanPtrPtr> {}

    /**
     * Creates a new {@link BooleanPtr} with a value of {@code false}.
     */
    public BooleanPtr() {}

    /**
     * Creates a new {@link BooleanPtr} and initializes it with the specified
     * value.
     * 
     * @param value the value.
     */
    public BooleanPtr(boolean value) {
        set(value);
    }

    /**
     * Returns the current value.
     * 
     * @return the value.
     */
    public boolean get() {
        return getByte() != 0;
    }

    /**
     * Hidden
     */
    @StructMember(0)
    native byte getByte();

    /**
     * Hidden
     */
    @StructMember(0)
    native void setByte(byte value);

    /**
     * Sets the value.
     * 
     * @param value the new value.
     */
    public void set(boolean value) {
        setByte((byte) (value ? 1 : 0));
    }

    /**
     * Copies {@code n} {@code boolean}s from the memory pointed to by this
     * {@link BooleanPtr} to a new {@code boolean[]} instance.
     * 
     * @param n the number of {@code boolean}s to copy.
     * @return the {@code boolean[]}.
     */
    public boolean[] toBooleanArray(int n) {
        boolean[] result = new boolean[n];
        get(result);
        return result;
    }

    /**
     * Copies {@code dst.length} bytes from the memory pointed to by this
     * {@link BooleanPtr} to {@code dst}.
     * 
     * @param dst the destination.
     */
    public void get(boolean[] dst) {
        get(dst, 0, dst.length);
    }

    /**
     * Copies {@code count} {@code boolean}s from the memory pointed to by this
     * {@link BooleanPtr} to {@code dst} starting at offset {@code offset}.
     * 
     * @param dst the destination.
     * @param offset the offset within the destination array to start copying
     *            to.
     * @param count the number of elements to copy.
     */
    public void get(boolean[] dst, int offset, int count) {
        Arrays.checkOffsetAndCount(dst.length, offset, count);
        long h = getHandle();
        for (int i = 0; i < count; i++) {
            dst[i + offset] = VM.getByte(h++) != 0;
        }
    }

    /**
     * Copies {@code src.length} {@code boolean}s from {@code src} to the memory
     * pointed to by this {@link BooleanPtr}.
     * 
     * @param src the source.
     */
    public void set(boolean[] src) {
        set(src, 0, src.length);
    }

    /**
     * Copies {@code count} {@code boolean}s from {@code src} starting at offset
     * {@code offset} to the memory pointed to by this {@link BooleanPtr}.
     * 
     * @param src the source.
     * @param offset the offset within the source array to start copying from.
     * @param count the number of elements to copy.
     */
    public void set(boolean[] src, int offset, int count) {
        Arrays.checkOffsetAndCount(src.length, offset, count);
        long h = getHandle();
        for (int i = 0; i < count; i++) {
            VM.setByte(h++, (byte) (src[i + offset] ? 1 : 0));
        }
    }
}
