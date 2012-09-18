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
package org.robovm.rt.bro.ref;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.Pointer;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * Used to map a <code>void **</code>.
 */
public final class PointerRef extends Struct {
    /**
     * Creates a new {@link PointerRef} with a value of NULL.
     */
    public PointerRef() {
    }
    
    /**
     * Creates a new {@link PointerRef} and initializes it with the specified value.
     * 
     * @param value the value.
     */
    public PointerRef(long value) {
        setValue(value);
    }

    @StructMember
    private native @Pointer long getValue();
    @StructMember
    private native void setValue(@Pointer long value);
    
    /**
     * Returns the current value.
     * 
     * @return the value.
     */
    public long get() {
        return getValue();
    }
    
    /**
     * Sets the value.
     * 
     * @param value the new value.
     */
    public void set(long value) {
        setValue(value);
    }
    
    /**
     * Returns whether the value pointed to by this {@link PointerRef} is NULL.
     * 
     * @return <code>true</code> if NULL, <code>false</code> otherwise.
     */
    public boolean isNull() {
        return getValue() == 0L;
    }
    
    public <T extends Struct> T asStruct(Class<T> cls) {
        long value = getValue();
        if (value == 0L) {
            return null;
        }
        return Struct.fromHandle(cls, value);
    }
}
