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
package org.robovm.rt.bro;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.robovm.rt.VM;
import org.robovm.rt.bro.annotation.Marshaler;
import org.robovm.rt.bro.annotation.Pointer;
import org.robovm.rt.bro.ptr.Ptr;
import org.robovm.rt.bro.ptr.Ptr.MarshalerCallback;

/**
 *
 * @version $Id$
 */
@Marshaler(Struct.Marshaler.class)
public abstract class Struct<T extends Struct<T>> extends NativeObject implements Iterable<T> {

    protected Struct() {
        setHandle(VM.allocateMemory(_sizeOf()));
    }
    
    public T copy() {
        return copy(1);
    }
    
    @SuppressWarnings("unchecked")
    public T copy(int n) {
        T o = (T) allocate(getClass(), n);
        VM.memcpy(o.getHandle(), getHandle(), _sizeOf() * n);
        return o;
    }
    
    public T copyWithMalloc() {
        return copyWithMalloc(1);
    }
    
    @SuppressWarnings("unchecked")
    public T copyWithMalloc(int n) {
        T o = (T) malloc(getClass(), n);
        VM.memcpy(o.getHandle(), getHandle(), _sizeOf() * n);
        return o;
    }

    /**
     * Casts this {@link Struct} to another {@link Struct} type.
     * 
     * @param type the type to cast to.
     * @return a {@link Struct} that points to the same memory 
     *         location as this {@link Struct}.
     */
    public <U extends Struct<U>> U as(Class<U> type) {
        return Struct.toStruct(type, getHandle());
    }
    
    /**
     * Casts this {@link Struct} to a {@link Ptr} pointing to the specified 
     * target type.
     * 
     * @param type the target type.
     * @return a {@link Ptr} that points to the same memory 
     *         location as this {@link Struct}.
     */
    public <U extends Struct<U>> Ptr<U> asPtr(Class<U> type) {
        return Ptr.toPtr(type, getHandle());
    }
    
    /**
     * Casts this {@link Struct} to a {@link Ptr} pointing to a {@link Ptr} 
     * pointing to the specified target type.
     * 
     * @param type the target type.
     * @return a {@link Ptr} that points to the same memory 
     *         location as this {@link Struct}.
     */
    public <U extends Struct<U>> Ptr<Ptr<U>> asPtrPtr(Class<U> type) {
        return Ptr.toPtrPtr(type, getHandle());
    }
    
    protected int _sizeOf() {
        return 0;
    }
    
    public void clear() {
        clear(1);
    }

    public void clear(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n < 1");
        }
        VM.memset(getHandle(), (byte) 0, _sizeOf() * n);
    }
    
    public void free() {
        VM.free(getHandle());
    }
    
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator() {
        return new StructIterator<T>((T) this);
    }
    
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator(final int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n < 1");
        }
        return new StructIterator<T>((T) this, n);
    }
    
    @SuppressWarnings("unchecked")
    protected T wrap(long address) {
        return (T) toStruct(getClass(), address);        
    }
    
    public T next() {
        return next(1);
    }
    
    public T next(long delta) {
        return wrap(getHandle() + _sizeOf() * delta);
    }

    public T previous() {
        return previous(1);
    }
    
    public T previous(long delta) {
        return next(-delta);
    }
    
    @SuppressWarnings("unchecked")
    public T[] toArray(int n) {
        T[] array = (T[]) Array.newInstance(getClass(), n);
        for (int i = 0; i < n; i++) {
            array[i] = next(i);
        }
        return array;
    }

    public List<T> toList(int n) {
        List<T> l = new ArrayList<T>(n);
        for (int i = 0; i < n; i++) {
            l.add(next(i));
        }
        return l;
    }
    
    public static int sizeOf() {
        return 0;
    }
    
    public static int offsetOf(int index) {
        return 0;
    }

    public static <T extends Struct<T>> T allocate(Class<T> cls) {
        return allocate(cls, 1);
    }
    
    public static <T extends Struct<T>> T allocate(Class<T> cls, int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n < 1");
        }
        T o = VM.allocateObject(cls);
        long handle = VM.allocateMemory(o._sizeOf() * n);
        o.setHandle(handle);
        return o;
    }
    
    public static <T extends Struct<T>> T malloc(Class<T> cls) {
        return malloc(cls, 1);
    }
    
    public static <T extends Struct<T>> T malloc(Class<T> cls, int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n < 1");
        }
        T o = VM.allocateObject(cls);
        long handle = VM.malloc(o._sizeOf() * n);
        o.setHandle(handle);
        return o;        
    }

    public static <T extends Struct<T>> T toStruct(Class<T> cls, long handle) {
        if (handle == 0L) {
            return null;
        }
        T o = VM.allocateObject(cls);
        o.setHandle(handle);
        return o;
    }
    
    public static class Marshaler {
        @SuppressWarnings("rawtypes")
        public static final MarshalerCallback MARSHALER_CALLBACK = new MarshalerCallback() {
            @SuppressWarnings("unchecked")
            public NativeObject toObject(Class cls, long handle) {
                return Struct.toStruct(cls, handle);
            }
        };
        
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public static Object toObject(Class cls, long handle, boolean copy) {
            Struct o = Struct.toStruct(cls, handle);
            if (copy) {
                o = o.copy();
            }
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
            return ((Struct<?>) o).getHandle();
        }
        
        public static void updateNative(Object o, long handle) {
        }
    }
    
    static class StructIterator<T extends Struct<T>> implements Iterator<T> {
        private T next;
        private int n;
        private int i = 0;

        StructIterator(T first) {
            this(first, -1);
        }
        StructIterator(T first, int n) {
            this.next = first;
            this.n = n;
        }

        @Override
        public boolean hasNext() {
            return n == -1 || i < n;
        }
        
        @Override
        public T next() {
            T o = next;
            next = next.next();
            i++;
            return o;
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
