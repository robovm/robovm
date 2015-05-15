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
package org.robovm.rt.bro.ptr;

import java.lang.reflect.ParameterizedType;
import java.util.concurrent.ConcurrentHashMap;

import org.robovm.rt.VM;
import org.robovm.rt.bro.MarshalerLookup;
import org.robovm.rt.bro.NativeObject;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.Pointer;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * Generic pointer to pointer (<code>&lt;type&gt; **</code> in C).
 */
public abstract class Ptr<S extends NativeObject, T extends Ptr<S, T>> extends Struct<T> {
    
    private static final ConcurrentHashMap<Class<?>, Class<?>> TYPE_CACHE = new ConcurrentHashMap<>();
    
    public Ptr() {
    }

    public Ptr(T o) {
    }

    /**
     * Hidden.
     */
    @StructMember(0)
    native @Pointer long getValue();
    
    /**
     * Hidden.
     */
    @StructMember(0)
    native void setValue(@Pointer long value);
    
    public S get() {
        long v = getValue();
        if (v == 0L) {
            return null;
        }
        return toObject(v);
    }

    @SuppressWarnings("unchecked")
    public T set(S o) {
        if (o == null) {
            setValue(0L);
        } else {
            setValue(o.getHandle());
        }
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T set(long handle) {
        setValue(handle);
        return (T) this;
    }

    /**
     * Copies {@code src.length} objects from {@code src} to the memory pointed 
     * to by this {@link Ptr}. 
     * 
     * @param src the source.
     */
    public void set(S[] src) {
        set(src, 0, src.length);
    }
    
    /**
     * Copies {@code count} objects from {@code src} starting at offset {@code offset}
     * to the memory pointed to by this {@link Ptr}. 
     * 
     * @param src the source.
     * @param offset the offset within the source array to start copying from.
     * @param count the number of elements to copy.
     */
    public void set(S[] src, int offset, int count) {
        int ptrSize = _sizeOf();
        long ptr = getHandle() + ptrSize * offset;
        for (int i = 0; i < count; i++) {
            S o = src[i];
            VM.setPointer(ptr, o == null ? 0L : o.getHandle());
            ptr += ptrSize;
        }
    }
    
    @SuppressWarnings("unchecked")
    private S toObject(long handle) {
        Class<S> type = (Class<S>) TYPE_CACHE.get(getClass());
        if (type == null) {
            type = (Class<S>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            TYPE_CACHE.put(getClass(), type);
        }
        return MarshalerLookup.toObject(type, handle);
    }
}
