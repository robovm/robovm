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

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.robovm.rt.VM;
import org.robovm.rt.bro.NativeObject;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.Pointer;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * Generic pointer to pointer (<code>&lt;type&gt; **</code> in C).
 */
public final class Ptr<T extends NativeObject> extends Struct<Ptr<T>> {
    
    
    public interface MarshalerCallback<T extends NativeObject> {
        T toObject(Class<T> cls, long handle);
    }
    
    private static final MarshalerCallback<NativeObject> LAZY_MARSHALER_CALLBACK = 
            new LazyMarshalerCallback<NativeObject>();
    
    private static Map<Class<?>, MarshalerCallback<NativeObject>> callbacks = 
            new HashMap<Class<?>, MarshalerCallback<NativeObject>>();
    
    private Class<T> targetClass;
    private MarshalerCallback<T> marshalerCallback;
    
    private Ptr() {
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
    
    public T get() {
        return get(marshalerCallback);
    }

    @SuppressWarnings("unchecked")
    public T get(MarshalerCallback<T> marshaler) {
        long v = getValue();
        if (v == 0L) {
            return null;
        }
        if (marshaler == null) {
            marshaler = lazy();
        }
        Class<T> cls = targetClass;
        if (cls == null) {
            cls = (Class<T>) VoidPtr.class;
        }
        return marshaler.toObject(cls, v);
    }
    
    public Ptr<T> set(T o) {
        if (o == null) {
            setValue(0L);
        } else {
            setValue(o.getHandle());
        }
        return this;
    }

    @Override
    public Ptr<T> copy(int n) {
        Ptr<T> p = super.copy(n);
        p.targetClass = targetClass;
        p.marshalerCallback = marshalerCallback;
        return p;
    }

    @Override
    public Ptr<T> copyWithMalloc(int n) {
        Ptr<T> p = super.copyWithMalloc(n);
        p.targetClass = targetClass;
        p.marshalerCallback = marshalerCallback;
        return p;
    }
    
    @Override
    protected Ptr<T> wrap(long address) {
        Ptr<T> p = super.wrap(address);
        p.targetClass = targetClass;
        p.marshalerCallback = marshalerCallback;
        return p;
    }
    
    @SuppressWarnings("unchecked")
    private static <T extends NativeObject> MarshalerCallback<T> findMarshalerCallback(Class<T> targetClass) {
        if (targetClass.getSuperclass() == Struct.class) {
            return Struct.Marshaler.MARSHALER_CALLBACK;
        }
        
        MarshalerCallback<T> callback = null;
        synchronized (callbacks) {
            callback = (MarshalerCallback<T>) callbacks.get(targetClass);
        }
        if (callback != null) {
            return callback;
        }
        
        Class<?> cls = targetClass;
        while (cls.getSuperclass() != null && cls.getAnnotation(org.robovm.rt.bro.annotation.Marshaler.class) == null) {
            cls = cls.getSuperclass();
        }
        org.robovm.rt.bro.annotation.Marshaler marshalerAnno = 
                (org.robovm.rt.bro.annotation.Marshaler) cls.getAnnotation(org.robovm.rt.bro.annotation.Marshaler.class);
        if (marshalerAnno != null) {
            Class<?> marshaler = marshalerAnno.value();
            try {
                Field f = marshaler.getField("MARSHALLER_CALLBACK");
                callback = (MarshalerCallback<T>) f.get(null);
                synchronized (callbacks) {
                    callbacks.put(targetClass, (MarshalerCallback<NativeObject>) callback);
                }
                return callback;
            } catch (NoSuchFieldException e) {
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            }
        }
        throw new RuntimeException("No MarshalerCallback found for class " 
                                        + targetClass.getName());
    }

    @SuppressWarnings("unchecked")
    public static <T extends NativeObject> Ptr<T> newPtr(Class<T> cls) {
        return newPtr(cls, 1, 1);
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends NativeObject> Ptr<T> newPtr(Class<T> cls, int n) {
        return newPtr(cls, 1, n);
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends NativeObject> Ptr<Ptr<T>> newPtrPtr(Class<T> cls) {
        return newPtr(cls, 2, 1);
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends NativeObject> Ptr<Ptr<T>> newPtrPtr(Class<T> cls, int n) {
        return newPtr(cls, 2, n);
    }
    
    @SuppressWarnings("rawtypes")
    public static Ptr newPtr(Class cls, int wrapCount, int n) {
        if (wrapCount < 1) {
            throw new IllegalArgumentException("wrapCount < 1");
        }
        if (n < 1) {
            throw new IllegalArgumentException("n < 1");
        }
        return toPtr(cls, VM.allocateMemory(Ptr.sizeOf() * n), wrapCount,
                lazy());
    }

    @SuppressWarnings("unchecked")
    public static <T extends NativeObject> Ptr<T> mallocPtr(Class<T> cls) {
        return mallocPtr(cls, 1, 1);
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends NativeObject> Ptr<T> mallocPtr(Class<T> cls, int n) {
        return mallocPtr(cls, 1, n);
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends NativeObject> Ptr<Ptr<T>> mallocPtrPtr(Class<T> cls) {
        return mallocPtr(cls, 2, 1);
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends NativeObject> Ptr<Ptr<T>> mallocPtrPtr(Class<T> cls, int n) {
        return mallocPtr(cls, 2, n);
    }
    
    @SuppressWarnings("rawtypes")
    public static Ptr mallocPtr(Class cls, int wrapCount, int n) {
        if (wrapCount < 1) {
            throw new IllegalArgumentException("wrapCount < 1");
        }
        if (n < 1) {
            throw new IllegalArgumentException("n < 1");
        }
        long handle = VM.malloc(Ptr.sizeOf() * n);
        VM.memset(handle, (byte) 0, Ptr.sizeOf() * n);
        return toPtr(cls, handle, wrapCount, lazy());
    }
    
    public static <T extends NativeObject> Ptr<T> toPtr(Class<T> cls, long handle) {
        return toPtr(cls, handle, Ptr.<T>lazy());
    }
    
    public static <T extends NativeObject> Ptr<Ptr<T>> toPtrPtr(Class<T> cls, long handle) {
        return toPtrPtr(cls, handle, Ptr.<T>lazy());
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends NativeObject> Ptr<T> toPtr(Class<T> cls, long handle, MarshalerCallback<T> callback) {
        return toPtr(cls, handle, 1, callback);
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends NativeObject> Ptr<Ptr<T>> toPtrPtr(Class<T> cls, long handle, MarshalerCallback<T> callback) {
        return toPtr(cls, handle, 2, callback);
    }
    
    @SuppressWarnings("rawtypes")
    public static Ptr toPtr(Class cls, long handle, int wrapCount) {
        return toPtr(cls, handle, wrapCount, Ptr.lazy());
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Ptr toPtr(Class cls, long handle, 
            int wrapCount, MarshalerCallback callback) {
        
        if (wrapCount < 1) {
            throw new IllegalArgumentException("wrapCount < 1");
        }
        if (handle == 0L) {
            return null;
        }
        Ptr p = Struct.toStruct(Ptr.class, handle);
        p.targetClass = cls;
        if (wrapCount > 1) {
            p.marshalerCallback = new UnwrappingMarshalerCallback(wrapCount, callback);
        } else {
            p.marshalerCallback = callback;
        }
        return p;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void updatePtr(Ptr ptr, Class cls, int wrapCount, MarshalerCallback callback) {
        if (wrapCount < 1) {
            throw new IllegalArgumentException("wrapCount < 1");
        }
        Ptr tmpPtr = toPtr(cls, ptr.getHandle(), wrapCount, callback);
        ptr.targetClass = tmpPtr.targetClass;
        ptr.marshalerCallback = tmpPtr.marshalerCallback;
    }
    
    @SuppressWarnings("unchecked")
    private static <T extends NativeObject> LazyMarshalerCallback<T> lazy() {
        return (LazyMarshalerCallback<T>) LAZY_MARSHALER_CALLBACK;
    }
    
    private static class LazyMarshalerCallback<T extends NativeObject> implements MarshalerCallback<T> {
        @Override
        public T toObject(Class<T> cls, long handle) {
            MarshalerCallback<T> callback = findMarshalerCallback(cls);
            return callback.toObject(cls, handle);
        }
    }
    
    private static class UnwrappingMarshalerCallback implements MarshalerCallback<Ptr<?>> {
        private final int wrapCount;
        private final MarshalerCallback<Ptr<?>> callback;
        
        public UnwrappingMarshalerCallback(int wrapCount, MarshalerCallback<Ptr<?>> callback) {
            this.wrapCount = wrapCount;
            this.callback = callback;
        }
        
        @SuppressWarnings("rawtypes")
        @Override
        public Ptr toObject(Class cls, long handle) {
            return toPtr(cls, handle, wrapCount - 1, callback);
        }
    }
}
