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

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * Points to a 32-bit signed value (<code>int32_t *</code> in C).
 */
public final class IntPtr extends Struct<IntPtr> {
    /**
     * Creates a new {@link IntPtr} with a value of 0.
     */
    public IntPtr() {
    }
    
    /**
     * Creates a new {@link IntPtr} and initializes it with the specified value.
     * 
     * @param value the value.
     */
    public IntPtr(int value) {
        set(value);
    }

    /**
     * Returns the current value.
     * 
     * @return the value.
     */
    @StructMember(0)
    public native int get();
    
    /**
     * Sets the value.
     * 
     * @param value the new value.
     */
    @StructMember(0)
    public native void set(int value);
    
    /**
     * Returns a {@link IntBuffer} which reads and writes to the same memory
     * location pointed to by this {@link IntPtr}.
     * 
     * @param n the maximum number of ints the {@link IntBuffer} can 
     *        read/write. This will be the {@link IntBuffer}'s 
     *        <code>capacity</code>.
     * @return the {@link IntBuffer}.
     */
    public IntBuffer asIntBuffer(int n) {
        return as(BytePtr.class).asByteBuffer(n >> 2).asIntBuffer();
    }
}
