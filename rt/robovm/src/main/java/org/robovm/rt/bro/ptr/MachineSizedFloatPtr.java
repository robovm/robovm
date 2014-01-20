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
    public static class Ptr extends org.robovm.rt.bro.ptr.Ptr<MachineSizedFloatPtr, Ptr> {}
    
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
}
