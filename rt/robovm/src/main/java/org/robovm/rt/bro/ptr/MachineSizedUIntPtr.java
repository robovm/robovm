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

import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.MachineSizedUInt;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * Points to a 32-bit or 64-bit unsigned value depending on the machine word
 * size (<code>unsigned long *</code> in C).
 */
public final class MachineSizedUIntPtr extends Struct<MachineSizedUIntPtr> {

    /**
     * Pointer to {@link MachineSizedUIntPtr} (<code>unsigned long **</code> in C)
     */
    public static class MachineSizedUIntPtrPtr extends org.robovm.rt.bro.ptr.Ptr<MachineSizedUIntPtr, MachineSizedUIntPtrPtr> {}
    
    /**
     * Creates a new {@link MachineSizedUIntPtr} with a value of 0.
     */
    public MachineSizedUIntPtr() {
    }
    
    /**
     * Returns the current value.
     * 
     * @return the value.
     */
    @StructMember(0)
    public native @MachineSizedUInt long get();
    
    /**
     * Sets the value.
     * 
     * @param value the new value.
     */
    @StructMember(0)
    public native void set(@MachineSizedUInt long value);
}
