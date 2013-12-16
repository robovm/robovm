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
}
