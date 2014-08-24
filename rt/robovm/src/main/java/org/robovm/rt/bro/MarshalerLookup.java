/*
 * Copyright (C) 2014 Trillian Mobile AB
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

import org.robovm.rt.bro.annotation.MarshalsPointer;

/**
 * Looks up marshaler methods for converting {@code long} handles to objects
 * given a class extending {@link NativeObject}.
 */
public class MarshalerLookup {

    private static final ConcurrentHashMap<Class<?>, Method> TO_OBJECT_CACHE = new ConcurrentHashMap<Class<?>, Method>();

    /**
     * Convenience method which finds a marshaler method and runs it 
     * converting the specified handle to the specified type.
     * 
     * @param type the type to convert to.
     * @param handle the handle (pointer).
     * @return the marshaled instance.
     * @throws {@link Error} if no marshaler method could be found.
     */
    @SuppressWarnings("unchecked")
    public static <S extends NativeObject> S toObject(Class<S> type, long handle) {
        try {
            Method toObject = findMarshaler(type);
            return (S) toObject.invoke(null, type, handle, MarshalerFlags.CALL_TYPE_PTR);
        } catch (InvocationTargetException e) {
            throw new Error(e);
        } catch (IllegalAccessException e) {
            throw new Error(e);
        }
    }

    /**
     * Finds a {@code T toObject(Class<?>, long, long)} marshaler method which can
     * convert handles into the specified type.
     * 
     * @return the {@link Method}
     * @throws {@link Error} if no such method could be found.
     */
    public static Method findMarshaler(Class<? extends NativeObject> type) {
        Method toObject = TO_OBJECT_CACHE.get(type);
        if (toObject == null) {
            toObject = findMarshaler(type, type);
            TO_OBJECT_CACHE.put(type, toObject);
        }
        return toObject;
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
