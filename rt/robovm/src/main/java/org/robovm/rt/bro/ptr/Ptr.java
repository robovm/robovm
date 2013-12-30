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
package org.robovm.rt.bro.ptr;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.concurrent.ConcurrentHashMap;

import org.robovm.rt.bro.BuiltinMarshalers;
import org.robovm.rt.bro.MarshalerFlags;
import org.robovm.rt.bro.NativeObject;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.MarshalsPointer;
import org.robovm.rt.bro.annotation.Pointer;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * Generic pointer to pointer (<code>&lt;type&gt; **</code> in C).
 */
public abstract class Ptr<S extends NativeObject, T extends Ptr<S, T>> extends Struct<T> {
    
    private static final ConcurrentHashMap<Class<?>, Class<?>> TYPE_CACHE = new ConcurrentHashMap<Class<?>, Class<?>>();
    private static final ConcurrentHashMap<Class<?>, Method> TO_OBJECT_CACHE = new ConcurrentHashMap<Class<?>, Method>();
    
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

    @SuppressWarnings("unchecked")
    private S toObject(long handle) {
        Class<?> type = TYPE_CACHE.get(getClass());
        if (type == null) {
            type = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            TYPE_CACHE.put(getClass(), type);
        }
        try {
            Method toObject = TO_OBJECT_CACHE.get(type);
            if (toObject == null) {
                toObject = findMarshaler(type, type);
                TO_OBJECT_CACHE.put(type, toObject);
            }
            return (S) toObject.invoke(null, type, handle, MarshalerFlags.CALL_TYPE_PTR);
        } catch (InvocationTargetException e) {
            throw new Error(e);
        } catch (IllegalAccessException e) {
            throw new Error(e);
        }
    }
    
    private static Method find(Class<?> findClass, Class<?> inClass, org.robovm.rt.bro.annotation.Marshaler anno) {
        Class<?> marshalerClass = anno.value();
        for (Method method : marshalerClass.getMethods()) {
            if (method.isAnnotationPresent(MarshalsPointer.class)) {
                // Is this a valid marshaler method? The signature should match
                // T toObject(Class, long, long)
                Class<?> returnType = method.getReturnType();
                Class<?>[] paramTypes = method.getParameterTypes();
                if (!returnType.isPrimitive() 
                        && paramTypes.length == 3 && paramTypes[0] == Class.class 
                        && paramTypes[1] == long.class && paramTypes[2] == long.class) {
                    if (returnType.isAssignableFrom(findClass)) {
                        return method;
                    }
                }
            }
        }
        return null;
    }
    
    private static Method findMarshaler0(Class<?> findClass, Class<?> inClass) {
        org.robovm.rt.bro.annotation.Marshaler anno1 = 
                inClass.getAnnotation(org.robovm.rt.bro.annotation.Marshaler.class);
        org.robovm.rt.bro.annotation.Marshalers anno2 = 
                inClass.getAnnotation(org.robovm.rt.bro.annotation.Marshalers.class);
        if (anno1 != null) {
            Method method = find(findClass, inClass, anno1);
            if (method != null) {
                return method;
            }
        }
        if (anno2 != null) {
            for (org.robovm.rt.bro.annotation.Marshaler m : anno2.value()) {
                Method method = find(findClass, inClass, m);
                if (method != null) {
                    return method;
                }
            }
        }
        return null;
    }
    
    private static Method findMarshaler(Class<?> findClass, Class<?> inClass) {
        // Search for a marshaler on the class and its superclasses.
        Class<?> c = inClass;
        while (c != null) {
            Method marshaler = findMarshaler0(findClass, c);
            if (marshaler != null) {
                return marshaler;
            }
            c = c.getSuperclass();
        }
        for (Class<?> intf : inClass.getInterfaces()) {
            Method marshaler = findMarshaler(findClass, intf);
            if (marshaler != null) {
                return marshaler;
            }
        }
        
        Method marshaler = findMarshaler0(findClass, BuiltinMarshalers.class);
        if (marshaler != null) {
            return marshaler;
        }

        throw new Error("No marshaler found for class " + findClass.getName());
    }

}
