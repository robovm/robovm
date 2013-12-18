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
    
    @SuppressWarnings("unchecked")
    public T next(long delta) {
        if (delta == 0) {
            return (T) this;
        }
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

    /**
     * Updates the memory starting at this {@link Struct} with the 
     * {@link Struct} starting at the specified instance.
     * 
     * @param o the {@link Struct} to write to the address of this 
     *        {@link Struct}.
     * @throws NullPointerException if {@code o} is {@code null}.
     * @throws IllegalArgumentException if the class of {@code o} is not the 
     *         same as this {@link Struct}'s class.
     */
    public void update(T o) {
        update(o, 1);
    }
    
    /**
     * Updates the memory starting at this {@link Struct} with the 
     * {@link Struct}s starting at the specified instance.
     * 
     * @param o the first {@link Struct} to write to the address of this 
     *        {@link Struct}.
     * @param n the number of {@link Struct}s to write.
     * @throws NullPointerException if {@code o} is {@code null}.
     * @throws IllegalArgumentException if the class of {@code o} is not the 
     *         same as this {@link Struct}'s class.
     */
    public void update(T o, int n) {
        if (o == null) {
            throw new NullPointerException("o");
        }
        if (o.getClass() != this.getClass()) {
            throw new IllegalArgumentException("Expected an instance of " 
                    + this.getClass().getName() + ". Actual type: " 
                    + o.getClass().getName());
        }
        if (n < 1) {
            throw new IllegalArgumentException("n < 1");
        }
        VM.memcpy(getHandle(), o.getHandle(), _sizeOf() * n);
    }
    
    
    /**
     * Updates the memory starting at this {@link Struct} with the 
     * {@link Struct}s in the specified array.
     * 
     * @param array the array of {@link Struct}s to write.
     * @throws NullPointerException if {@code array} or any of the objects in 
     *         {@code array} are {@code null}.
     * @throws IllegalArgumentException if the class of any of the objects in 
     *         the array is not the same as this {@link Struct}'s class.
     */
    public void update(T[] array) {
        if (array == null) {
            throw new NullPointerException("array");
        }
        Class<?> cls = this.getClass();
        int len = array.length;
        for (int i = 0; i < len; i++) {
            T o = array[i];
            if (o == null) {
                throw new NullPointerException("null at index " + i);
            }
            if (o.getClass() != cls) {
                throw new IllegalArgumentException("Expected an instance of " 
                        + cls.getName() + " at index " + i + ". Actual type: " 
                        + o.getClass().getName());
            }
        }
        long dst = getHandle();
        int size = _sizeOf();
        for (int i = 0; i < len; i++) {
            VM.memcpy(dst, array[i].getHandle(), size);
            dst += size;
        }
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
        
        public static @Pointer long toNative(Object o) {
            if (o == null) {
                return 0L;
            }
            return ((Struct<?>) o).getHandle();
        }
        
        public static void updateNative(Object o, long handle) {
        }
    }
    
    /**
     * Marshals to/from {@code Struct[]}, {@code Struct[][]} and
     * {@code Struct[][][]}.
     */
    public static class StructArrayMarshaler {
        private static void checkDimensions(Class<?> baseType, String format, int actual, int expected) {
            if (actual != expected) {
                String suffixActual = String.format(format, actual);
                String suffixExpected = String.format(format, expected);
                throw new IllegalArgumentException(
                        "Expected " + baseType.getName() + suffixExpected 
                        + ". Got " + baseType.getName() + suffixActual);
            }
        }
        
        @SuppressWarnings("unchecked")
        public static <T extends Struct<T>> Object toObject(Class<T[]> arrayClass, long handle, int d1) {
            T s = Struct.toStruct((Class<T>) arrayClass.getComponentType(), handle);
            return s.toArray(d1);
        }
        @SuppressWarnings("unchecked")
        public static <T extends Struct<T>> void toNative(Object object, long handle, int d1) {
            final T[] o = (T[]) object;
            Class<T> structClass = (Class<T>) object.getClass().getComponentType();
            checkDimensions(structClass, "[%d]", o.length, d1);
            Struct<T> s = Struct.toStruct((Class<T>) structClass, handle);
            s.update(o);
        }
        @SuppressWarnings("unchecked")
        public static <T extends Struct<T>> Object toObject(Class<T[]> arrayClass, long handle, int d1, int d2) {
            Class<T> structClass = (Class<T>) arrayClass.getComponentType().getComponentType();
            T[][] o = (T[][]) Array.newInstance(structClass, d1, d2);
            T s = Struct.toStruct((Class<T>) structClass, handle);
            int len1 = o.length;
            for (int i = 0; i < len1; i++) {
                o[i] = s.toArray(d2);
                s = s.next(d2);
            }
            return o;
        }
        @SuppressWarnings("unchecked")
        public static <T extends Struct<T>> void toNative(Object object, long handle, int d1, int d2) {
            final T[][] o = (T[][]) object;
            Class<T> structClass = (Class<T>) object.getClass().getComponentType().getComponentType();
            checkDimensions(structClass, "[%d][]", o.length, d1);
            int len1 = o.length;
            for (int i = 0; i < len1; i++) {
                checkDimensions(structClass, "[][%d]", o[i].length, d2);
            }
            Struct<T> s = Struct.toStruct((Class<T>) structClass, handle);
            for (int i = 0; i < len1; i++) {
                s.update(o[i]);
                s = s.next(d2);
            }
        }
        @SuppressWarnings("unchecked")
        public static <T extends Struct<T>> Object toObject(Class<T[]> arrayClass, long handle, int d1, int d2, int d3) {
            Class<T> structClass = (Class<T>) arrayClass.getComponentType().getComponentType().getComponentType();
            T[][][] o = (T[][][]) Array.newInstance(structClass, d1, d2, d3);
            T s = Struct.toStruct((Class<T>) structClass, handle);
            int len1 = o.length;
            for (int i = 0; i < len1; i++) {
                int len2 = o[i].length;
                for (int j = 0; j < len2; j++) {
                    o[i][j] = s.toArray(d3);
                    s = s.next(d3);
                }
            }
            return o;
        }
        @SuppressWarnings("unchecked")
        public static <T extends Struct<T>> void toNative(Object object, long handle, int d1, int d2, int d3) {
            final T[][][] o = (T[][][]) object;
            Class<T> structClass = (Class<T>) object.getClass().getComponentType().getComponentType().getComponentType();
            checkDimensions(structClass, "[%d][][]", o.length, d1);
            int len1 = o.length;
            for (int i = 0; i < len1; i++) {
                T[][] p = o[i];
                checkDimensions(structClass, "[][%d][]", p.length, d2);
                int len2 = p.length;
                for (int j = 0; j < len2; j++) {
                    checkDimensions(structClass, "[][][%d]", p[j].length, d3);
                }
            }
            Struct<T> s = Struct.toStruct((Class<T>) structClass, handle);
            for (int i = 0; i < len1; i++) {
                T[][] p = o[i];
                int len2 = p.length;
                for (int j = 0; j < len2; j++) {
                    s.update(o[i][j]);
                    s = s.next(d3);
                }
            }
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
