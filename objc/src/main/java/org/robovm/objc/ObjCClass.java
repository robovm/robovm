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
package org.robovm.objc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.robovm.objc.annotation.BindSelector;
import org.robovm.objc.annotation.CustomClass;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.objc.annotation.TypeEncoding;
import org.robovm.rt.VM;
import org.robovm.rt.bro.annotation.Callback;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.annotation.MarshalsPointer;

@Library("objc")
public final class ObjCClass extends ObjCObject {
    
    private static final Map<Class<? extends ObjCObject>, ObjCClass> typeToClass = new HashMap<Class<? extends ObjCObject>, ObjCClass>();
    private static final Map<String, ObjCClass> nameToClass = new HashMap<String, ObjCClass>();
    private static final Map<String, Class<? extends ObjCObject>> allNativeClasses = new HashMap<>();
    private static final Map<String, Class<? extends ObjCObject>> allCustomClasses = new HashMap<>();
    static final Map<String, Class<? extends ObjCObject>> allObjCProxyClasses = new HashMap<>();

    private static final int ACC_SYNTHETIC = 0x1000;
    private static final String CUSTOM_CLASS_NAME_PREFIX = "j_";
    
    static {
        ObjCRuntime.bind();
        @SuppressWarnings("unchecked")
        Class<? extends ObjCObject>[] classes = (Class<? extends ObjCObject>[]) 
                VM.listClasses(ObjCObject.class, ClassLoader.getSystemClassLoader());
        for (Class<? extends ObjCObject> cls : classes) {
            NativeClass nativeClassAnno = cls.getAnnotation(NativeClass.class);
            if (nativeClassAnno != null) {
                String name = nativeClassAnno.value();
                if (name.length() == 0) {
                    name = cls.getSimpleName();
                }
                allNativeClasses.put(name, cls);
            } else {
                CustomClass customClassAnno = cls.getAnnotation(CustomClass.class);
                String name = cls.getName();
                if (customClassAnno != null) {
                    String value = customClassAnno.value();
                    if (value.length() > 0) {
                        name = value;
                    }
                } else if (name.indexOf('.') == -1) {
                    name = "." + name;
                }
                allCustomClasses.put(name, cls);
            }
            
            if (isObjCProxy(cls)) {
                // Map protocol interface names to ObjC protocol proxy classes
                String name = cls.getName();
                String protocolName = name.substring(0, name.length() - 10);
                allObjCProxyClasses.put(protocolName, cls);
            }
        }
    }

    static boolean isObjCProxy(Class<?> cls) {
        return (cls.getModifiers() & ACC_SYNTHETIC) > 0 && cls.getName().endsWith("$ObjCProxy");
    }
    
    public static class Marshaler {
        @MarshalsPointer
        public static Class<? extends ObjCObject> toObject(Class<ObjCClass> cls, long handle, long flags) {
            ObjCClass o = ObjCClass.toObjCClass(handle);
            if (o == null) {
                return null;
            }
            return o.getType();
        }
        @MarshalsPointer
        public static long toNative(Class<? extends ObjCObject> o, long flags) {
            if (o == null) {
                return 0L;
            }
            ObjCClass c = ObjCClass.getByType(o);
            return c.getHandle();
        }
    }
    
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
        synchronized (objcBridgeLock) {
            ObjCClass c = nameToClass.get(objcClassName);
            if (c == null) {
                c = getByNameNotLoaded(objcClassName);
                if (c == null) {
                    throw new ObjCClassNotFoundException("Could not find Java class corresponding to Objective-C class: " + objcClassName);
                }
            }
            return c;
        }
    }
    
    private static ObjCClass getByNameNotLoaded(String objcClassName) {
        Class<? extends ObjCObject> cls = allNativeClasses.get(objcClassName);
        if (cls != null) {
            return getByType(cls);
        }
        cls = allCustomClasses.get(objcClassName);
        if (cls != null) {
            return getByType(cls);
        }
        return null;
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
        long classPtr = ObjCRuntime.object_getClass(handle);
        return toObjCClass(classPtr);
    }
    
    public static ObjCClass getByType(Class<? extends ObjCObject> type) {
        if (type == null) {
            throw new NullPointerException("type");
        }
        synchronized (objcBridgeLock) {
            ObjCClass c = typeToClass.get(type);
            if (c == null) {
                NativeClass nativeClassAnno = type.getAnnotation(NativeClass.class);
                String name = null;
                if (nativeClassAnno != null) {
                    name = nativeClassAnno.value();
                    name = "".equals(name) ? type.getSimpleName() : name;
                } else {
                    name = getCustomClassName(type);
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

    public static ObjCClass toObjCClass(final long handle) {
        long classPtr = handle;
        ObjCClass c = ObjCObject.getPeerObject(classPtr);
        if (c == null) {
            c = getByNameNotLoaded(VM.newStringUTF(ObjCRuntime.class_getName(classPtr)));
        }
        while (c == null && classPtr != 0L) {
            classPtr = ObjCRuntime.class_getSuperclass(classPtr);
            c = ObjCObject.getPeerObject(classPtr);
            if (c == null) {
                c = getByNameNotLoaded(VM.newStringUTF(ObjCRuntime.class_getName(classPtr)));
            }
        }
        if (c == null) {
            String name = VM.newStringUTF(ObjCRuntime.class_getName(handle));
            throw new ObjCClassNotFoundException("Could not find Java class corresponding to Objective-C class: " + name);
        }
        return c;
    }

    public static ObjCClass registerCustomClass(Class<? extends ObjCObject> type) {
        if (type.getAnnotation(NativeClass.class) != null) {
            throw new IllegalArgumentException("@NativeClass annotated class " + type.getName() 
                    + " can not be registered as a custom class");
        }
        synchronized (objcBridgeLock) {
            ObjCClass c = typeToClass.get(type);
            if (c == null) {
                String name = getCustomClassName(type);
                c = register(type, name);
                typeToClass.put(type, c);
                nameToClass.put(name, c);
            }
            return c;
        }
    }

    private static String getCustomClassName(Class<? extends ObjCObject> type) {
        CustomClass customClassAnno = type.getAnnotation(CustomClass.class);
        String name = type.getName();
        if (customClassAnno != null && customClassAnno.value().length() > 0) {
            name = customClassAnno.value();
        } else {
            name = CUSTOM_CLASS_NAME_PREFIX + name;
        }
        name = name.replace('.', '_');
        return name;
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
            boolean isClassMethod = method.getParameterTypes()[0] == ObjCClass.class;
            if (isClassMethod && method.getDeclaringClass() != type) {
                // Java doesn't support overriding static methods so the callback 
                // method for a class method of a super class cannot be used to route
                // calls to a static method in a subclass. There must be a @Method
                // annotation on the static method (and thus a @Callback method) in
                // the custom class.
                continue;
            }
            Selector selector = Selector.register(selName);
            String encoding = null;
            TypeEncoding typeEncoding = method.getAnnotation(TypeEncoding.class);
            if (typeEncoding != null) {
                encoding = typeEncoding.value();
            } else {
                long methodPtr = isClassMethod 
                        ? ObjCRuntime.class_getClassMethod(superclass.getHandle(), selector.getHandle())
                        : ObjCRuntime.class_getInstanceMethod(superclass.getHandle(), selector.getHandle());
                if (methodPtr != 0L) {
                    long encodingPtr = ObjCRuntime.method_getTypeEncoding(methodPtr);
                    if (encodingPtr != 0L) {
                        encoding = VM.newStringUTF(encodingPtr);
                    }
                }
            }
            long impl = VM.getCallbackMethodImpl(method);
            // For class methods we need to add the method to the meta-class of the class we are creating
            long ownerHandle = isClassMethod ? ObjCRuntime.object_getClass(handle) : handle;
            if (!ObjCRuntime.class_addMethod(ownerHandle, selector.getHandle(), impl, encoding != null ? VM.getStringUTFChars(encoding) : 0L)) {
                throw new ObjCClassNotFoundException("Failed to add method " + selName + " to custom Objective-C class for Java class: " + type);
            }
        }
        ObjCObject.ObjectOwnershipHelper.registerClass(handle);
        ObjCRuntime.objc_registerClassPair(handle);                                  
        return new ObjCClass(handle, type, name, true);
    }
    
    private static Map<String, Method> getCallbacks(Class<?> type) {
        Map<String, Method> callbacks = new HashMap<String, Method>();
        findCallbacks(type, callbacks);
        return callbacks;
    }

    private static void findCallbacks(Class<?> type, Map<String, Method> result) {
        for (Method m : type.getDeclaredMethods()) {
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
}
