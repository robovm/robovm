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

/**
 *
 * @version $Id$
 */
@Marshaler(Struct.Marshaler.class)
public abstract class Struct<T extends Struct<T>> extends NativeObject {

    protected Struct() {
        setHandle(VM.allocateMemoryAtomic(_sizeOf()));
    }
    
    @SuppressWarnings("unchecked")
    public T copy() {
        long h = VM.allocateMemoryAtomic(_sizeOf());
        T o = (T) fromHandle(getClass(), h);
        VM.memcpy(o.getHandle(), getHandle(), _sizeOf());
        return o;
    }
    
    @SuppressWarnings("unchecked")
    public T copyWithMalloc() {
        T o = (T) malloc(getClass());
        VM.memcpy(o.getHandle(), getHandle(), _sizeOf());
        return o;
    }
    
    protected int _sizeOf() {
        return 0;
    }
    
    public void free() {
        VM.free(getHandle());
    }
    
    public static int sizeOf() {
        return 0;
    }
    
    public static int offsetOf(int index) {
        return 0;
    }

    public static <T extends Struct<T>> T malloc(Class<T> cls) {
        T o = VM.allocateObject(cls);
        long handle = VM.malloc(o._sizeOf());
        VM.memset(handle, (byte) 0, o._sizeOf());
        o.setHandle(handle);
        return o;        
    }

    public static <T extends Struct<T>> T fromHandle(Class<T> cls, long handle) {
        if (handle == 0L) {
            return null;
        }
        T o = VM.allocateObject(cls);
        o.setHandle(handle);
        return o;
    }
    
    public static class Marshaler {
        public static <T extends Struct<T>> Object toObject(Class<T> cls, long handle, boolean copy) {
            T o = Struct.fromHandle(cls, handle);
            if (copy) {
                o = o.copy();
            }
            return o;
        }

        public static void updateObject(Object o, long handle) {
        }
        
        public static long toNative(Object o) {
            return ((Struct<?>) o).getHandle();
        }
        
        public static void updateNative(Object o, long handle) {
        }
    }
}
