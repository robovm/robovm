/*
 * Copyright (C) 2012 RoboVM AB
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
package org.robovm.rt.bro;

import org.robovm.rt.VM;
import org.robovm.rt.bro.annotation.Marshaler;
import org.robovm.rt.bro.annotation.MarshalsPointer;


/**
 * Common base class for objects that wraps native objects.
 */
@Marshaler(NativeObject.Marshaler.class)
public abstract class NativeObject {
    private long handle;

    protected NativeObject() {
    }
    
    public final long getHandle() {
        return handle;
    }
    
    protected final void setHandle(long handle) {
        this.handle = handle;
    }

    /**
     * Casts this {@link NativeObject} to another {@link NativeObject} type.
     * 
     * @param type the type to cast to.
     * @return a {@link NativeObject} that points to the same memory 
     *         location as this {@link NativeObject}.
     */
    @SuppressWarnings("unchecked")
    public <U extends NativeObject> U as(Class<U> type) {
        if (getClass() == type || type.isAssignableFrom(getClass())) {
            return (U) this;
        }
        return MarshalerLookup.toObject(type, handle);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (handle ^ (handle >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        NativeObject other = (NativeObject) obj;
        if (handle != other.handle) {
            return false;
        }
        return true;
    }
    
    public static class Marshaler {
        @MarshalsPointer
        public static NativeObject toObject(Class<?> cls, long handle, long flags) {
            if (handle == 0L) {
                return null;
            }
            NativeObject o = (NativeObject) VM.allocateObject(cls);
            o.setHandle(handle);
            return o;
        }
        @MarshalsPointer
        public static long toNative(NativeObject o, long flags) {
            if (o == null) {
                return 0L;
            }
            return o.getHandle();
        }
    }
}
