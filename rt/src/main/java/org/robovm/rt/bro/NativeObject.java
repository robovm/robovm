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
package org.robovm.rt.bro;

import org.robovm.rt.VM;
import org.robovm.rt.bro.annotation.Marshaler;
import org.robovm.rt.bro.annotation.Pointer;
import org.robovm.rt.bro.ptr.Ptr;
import org.robovm.rt.bro.ptr.Ptr.MarshalerCallback;


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
        @SuppressWarnings("rawtypes")
        public static final MarshalerCallback MARSHALER_CALLBACK = new MarshalerCallback() {
            public NativeObject toObject(Class cls, long handle) {
                return (NativeObject) NativeObject.Marshaler.toObject(cls, handle, false);
            }
        };
        
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public static Object toObject(Class cls, long handle, boolean copy) {
            if (handle == 0L) {
                return null;
            }
            NativeObject o = (NativeObject) VM.allocateObject(cls);
            o.setHandle(handle);
            return o;
        }

        public static void updateObject(Object o, long handle) {
        }
        
        @SuppressWarnings("rawtypes")
        public static Ptr toPtr(Class cls, long handle, int wrapCount) {
            return Ptr.toPtr(cls, handle, wrapCount, MARSHALER_CALLBACK);
        }
        
        @SuppressWarnings("rawtypes")
        public static void updatePtr(Ptr ptr, Class cls, long handle, int wrapCount) {
            Ptr.updatePtr(ptr, cls, wrapCount, MARSHALER_CALLBACK);
        }
        
        public static @Pointer long toNative(Object o) {
            if (o == null) {
                return 0L;
            }
            return ((NativeObject) o).getHandle();
        }
        
        public static void updateNative(Object o, long handle) {
        }
    }
}
