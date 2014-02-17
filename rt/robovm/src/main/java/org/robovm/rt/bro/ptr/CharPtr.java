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
import java.nio.CharBuffer;
import java.nio.ShortBuffer;

import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * Points to a 16-bit unsigned value (<code>unsigned short *</code> in C).
 */
public final class CharPtr extends Struct<CharPtr> {

    /**
     * Pointer to {@link CharPtr} (<code>unsigned short **</code> in C)
     */
    public static class CharPtrPtr extends org.robovm.rt.bro.ptr.Ptr<CharPtr, CharPtrPtr> {}

    /**
     * Creates a new {@link CharPtr} with a value of 0.
     */
    public CharPtr() {
    }
    
    /**
     * Creates a new {@link CharPtr} and initializes it with the specified value.
     * 
     * @param value the value.
     */
    public CharPtr(char value) {
        set(value);
    }

    /**
     * Returns the current value.
     * 
     * @return the value.
     */
    @StructMember(0)
    public native char get();
    
    /**
     * Sets the value.
     * 
     * @param value the new value.
     */
    @StructMember(0)
    public native void set(char value);
    
    /**
     * Returns a {@link CharBuffer} which reads and writes to the same memory
     * location pointed to by this {@link CharPtr}.
     * 
     * @param n the maximum number of chars the {@link CharBuffer} can 
     *        read/write. This will be the {@link CharBuffer}'s 
     *        <code>capacity</code>.
     * @return the {@link ShortBuffer}.
     */
    public CharBuffer asCharBuffer(int n) {
        return as(BytePtr.class).asByteBuffer(n << 1).order(ByteOrder.nativeOrder()).asCharBuffer();
    }
}
