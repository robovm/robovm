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

import java.nio.ShortBuffer;

import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * Points to a 16-bit signed value (<code>short *</code> in C).
 */
public final class ShortPtr extends Struct<ShortPtr> {
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
        return as(BytePtr.class).asByteBuffer(n >> 1).asShortBuffer();
    }
}
