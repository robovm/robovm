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

import java.nio.DoubleBuffer;

import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * Points to a <code>double</code> value (<code>double *</code> in C).
 */
public final class DoublePtr extends Struct<DoublePtr> {
    /**
     * Creates a new {@link DoublePtr} with a value of 0.0.
     */
    public DoublePtr() {
    }
    
    /**
     * Creates a new {@link DoublePtr} and initializes it with the specified value.
     * 
     * @param value the value.
     */
    public DoublePtr(double value) {
        set(value);
    }

    /**
     * Returns the current value.
     * 
     * @return the value.
     */
    @StructMember(0)
    public native double get();
    
    /**
     * Sets the value.
     * 
     * @param value the new value.
     */
    @StructMember(0)
    public native void set(double value);
    
    /**
     * Returns a {@link DoubleBuffer} which reads and writes to the same memory
     * location pointed to by this {@link DoublePtr}.
     * 
     * @param n the maximum number of doubles the {@link DoubleBuffer} can 
     *        read/write. This will be the {@link DoubleBuffer}'s 
     *        <code>capacity</code>.
     * @return the {@link DoubleBuffer}.
     */
    public DoubleBuffer asDoubleBuffer(int n) {
        return as(BytePtr.class).asByteBuffer(n >> 3).asDoubleBuffer();
    }
}
