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
package org.robovm.objc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.robovm.objc.annotation.BindClass;
import org.robovm.objc.annotation.BindSelector;
import org.robovm.objc.annotation.TypeEncoding;
import org.robovm.rt.VM;
import org.robovm.rt.bro.annotation.Callback;
import org.robovm.rt.bro.annotation.Library;

@Library("objc")
public final class ObjCClass extends ObjCObject {
    
    static {
        ObjCRuntime.bind();
    }
    
    private static final Pattern KNOWN_NATIVE_CLASSES_PATTERN = Pattern.compile("org\\.robovm\\.(objc|cocoatouch)\\.[.$_a-zA-Z0-9]+");
    private static final Map<Class<? extends ObjCObject>, ObjCClass> typeToClass = new HashMap<Class<? extends ObjCObject>, ObjCClass>();
    private static final Map<String, ObjCClass> nameToClass = new HashMap<String, ObjCClass>();

    private final Class<? extends ObjCObject> type;
    private final String name;
    private final boolean custom;
    
    private ObjCClass(long handle, Class<? extends ObjCObject> type, String name, boolean custom) {
        super(handle, false);
        this.type = type;
        this.name = name;
        this.custom = custom;
    }
    
    public Class<? extends ObjCObject> getType() {
        return type;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isCustom() {
        return custom;
    }
    
    @Override
    public String toString() {
        return type.getName();
    }
    
    public static ObjCClass getByName(String objcClassName) {
        synchronized (typeToClass) {
            ObjCClass c = nameToClass.get(objcClassName);
            if (c == null) {
                throw new ObjCClassNotFoundException("Could not find Java class corresponding to Objective-C class: " + objcClassName);
            }
            return c;
        }
    }
    
    public static ObjCClass getFromObject(ObjCObject id) {
        long handle = id.getHandle();
        ObjCClass c = null;
        if (handle != 0L) {
            long classPtr = ObjCRuntime.object_getClass(handle);
            c = ObjCObject.getPeerObject(classPtr);
        }
        if (c != null) {
            return c;
        }
        return getByType(id.getClass());
    }
    
    public static ObjCClass getFromObject(long handle) {
        ObjCClass c = null;
        long classPtr = ObjCRuntime.object_getClass(handle);
        c = ObjCObject.getPeerObject(classPtr);
        if (c == null) {
            String name = VM.newStringUTF(ObjCRuntime.object_getClassName(handle));
            c = getByName(name);
        }
        return c;
    }
    
    public static ObjCClass getFromObject(long handle, ObjCClass fallback) {
        long fallbackHandle = fallback.getHandle();
        ObjCClass c = null;
        long classPtr = ObjCRuntime.object_getClass(handle);
        c = ObjCObject.getPeerObject(classPtr);
        while (c == null && classPtr != 0L && classPtr != fallbackHandle) {
            classPtr = ObjCRuntime.class_getSuperclass(classPtr);
            c = ObjCObject.getPeerObject(classPtr);
        }
        if (c == null) {
            String name = VM.newStringUTF(ObjCRuntime.object_getClassName(handle));
            throw new ObjCClassNotFoundException("Could not find Java class corresponding to Objective-C class: " + name);
        }
        return c;
    }
    
    public static ObjCClass getByType(Class<? extends ObjCObject> type) {
        if (type == null) {
            throw new NullPointerException("type");
        }
        System.out.println("getByType: " + type.getName());
        synchronized (typeToClass) {
            ObjCClass c = typeToClass.get(type);
            if (c == null) {
                BindClass bindToClass = type.getAnnotation(BindClass.class);
                String name = null;
                if (bindToClass != null) {
                    name = bindToClass.value();
                } else if (KNOWN_NATIVE_CLASSES_PATTERN.matcher(type.getName()).matches()) {
                    name = type.getSimpleName();
                } else {
                    name = "Java." + type.getName();
                    c = register(type, name);
                }
                if (c == null) {
                    long classPtr = ObjCRuntime.objc_getClass(VM.getStringUTFChars(name));
                    if (classPtr == 0L) {
                        throw new ObjCClassNotFoundException(name);
                    }
                    c = new ObjCClass(classPtr, type, name, false);
                }
                typeToClass.put(type, c);
                nameToClass.put(name, c);
            }
            return c;
        }
    }
    
    @SuppressWarnings("unchecked")
    private static ObjCClass register(Class<? extends ObjCObject> type, String name) {
        ObjCClass superclass = getByType((Class<? extends ObjCObject>) type.getSuperclass());
        long handle = ObjCRuntime.objc_allocateClassPair(superclass.getHandle(), VM.getStringUTFChars(name), 0);
        if (handle == 0L) {
            throw new ObjCClassNotFoundException("Failed to create custom Objective-C class for Java class: " + type);
        }
        for (Entry<String, Method> entry : getCallbacks(type).entrySet()) {
            String selName = entry.getKey();
            Method method = entry.getValue();
            Selector selector = Selector.register(selName);
            String encoding = null;
            TypeEncoding typeEncoding = method.getAnnotation(TypeEncoding.class);
            if (typeEncoding != null) {
                encoding = typeEncoding.value();
            } else {
                long methodPtr = ObjCRuntime.class_getInstanceMethod(superclass.getHandle(), selector.getHandle());
                if (methodPtr != 0L) {
                    long encodingPtr = ObjCRuntime.method_getTypeEncoding(methodPtr);
                    if (encodingPtr != 0L) {
                        encoding = VM.newStringUTF(encodingPtr);
                    }
                }
            }
            long impl = VM.getCallbackMethodImpl(method);
            if (!ObjCRuntime.class_addMethod(handle, selector.getHandle(), impl, encoding != null ? VM.getStringUTFChars(encoding) : 0L)) {
                throw new ObjCClassNotFoundException("Failed to add method " + selName + " to custom Objective-C class for Java class: " + type);
            }
        }
        ObjCRuntime.objc_registerClassPair(handle);
        return new ObjCClass(handle, type, name, true);
    }
    
    private static Map<String, Method> getCallbacks(Class<?> type) {
        Map<String, Method> callbacks = new HashMap<String, Method>();
        Class<?> c = type;
        while (c != null && c != Object.class) {
            findCallbacks(c, callbacks);
            c = c.getSuperclass();
        }
        return callbacks;
    }
    
    private static void findCallbacks(Class<?> type, Map<String, Method> result) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        if (!type.isInterface()) {
            // No need to search interfaces since interfaces cannot have 
            // static methods and callbacks must be static.
            classes.add(type);
        }
        classes.addAll(Arrays.asList(type.getDeclaredClasses()));
        for (Class<?> c : classes) {
            for (Method m : c.getDeclaredMethods()) {
                if (m.getAnnotation(Callback.class) != null) {
                    BindSelector bindSelector = m.getAnnotation(BindSelector.class);
                    if (bindSelector != null) {
                        if (!result.containsKey(bindSelector.value())) {
                            result.put(bindSelector.value(), m);
                        }
                    }
                }
            }
        }
        for (Class<?> iface : type.getInterfaces()) {
            findCallbacks(iface, result);
        }
    }
}
