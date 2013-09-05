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
package org.robovm.objc;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.robovm.rt.VM;
import org.robovm.rt.bro.Bro;
import org.robovm.rt.bro.Runtime;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.ByVal;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.annotation.Pointer;
import org.robovm.rt.bro.annotation.StructRet;
import org.robovm.rt.bro.ptr.BytePtr;

/**
 *
 */
@Library("objc")
public class ObjCRuntime {

    private static final Map<Class<?>, Integer> structSizes = new HashMap<Class<?>, Integer>();
    
    static {
        Bro.bind();
    }
    
    public static void bind() {
        Class<?> caller = VM.getStackClasses(0, 1)[0];
        bind(caller);
    }
    
    public static void bind(Class<?> c) {
        for (Method method : c.getDeclaredMethods()) {
            Bridge bridge = method.getAnnotation(Bridge.class);
            if (bridge != null && (bridge.symbol() == null || "".equals(bridge.symbol()))) {
                Class<?>[] paramTypes = method.getParameterTypes();
                if (paramTypes.length >= 2) {
                    // An ObjC method takes at least 2 parameters (self, selector)
                    // or (super, selector).
                    if (paramTypes[1] == Selector.class) {
                        String symbol = null;
                        if (paramTypes[0] == ObjCSuper.class) {
                            symbol = "objc_msgSendSuper";
                        } else if (ObjCObject.class.isAssignableFrom(paramTypes[0])) {
                            symbol = "objc_msgSend";
                        }
                        if (symbol != null) {
                            // So this is a bridge to an ObjC method. If the method
                            // returns a struct by value we may have to use the
                            // special stret versions of objc_msgSend/objc_msgSendSuper.
                            if (isStret(method)) {
                                symbol += "_stret";
                            }
                            long f = Runtime.resolveBridge("objc", symbol, method);
                            VM.bindBridgeMethod(method, f);
                        }
                    }
                    
                }
            }
        }
        Bro.bind(c);
    }
    
    static boolean isStret(Method method) {
        Class<?> returnType = method.getReturnType();
        if (returnType.getSuperclass() == Struct.class 
                && (method.getAnnotation(ByVal.class) != null 
                || returnType.getAnnotation(ByVal.class) != null)) {
            int structSize = getStructSize(returnType);
            if (Bro.IS_X86) {
                if (structSize > 2 && structSize != 4 && structSize != 8) {
                    // On x86 stret has to be used for all structs except
                    // of size 1, 2, 4 and 8 bytes.
                    return true;
                }
            } else {
                if (structSize > 4) {
                    // On ARM stret has to be used for structs
                    // larger than 4 bytes
                    return true;
                }
            }
        }
        return false;
    }
    
    static synchronized int getStructSize(Class<?> cls) {
        Integer size = structSizes.get(cls);
        if (size == null) {
            try {
                Method sizeOf = cls.getMethod("sizeOf");
                size = (Integer) sizeOf.invoke(null);
            } catch (Exception e) {
                throw new Error(e);
            }
            structSizes.put(cls, size);
        }
        return size;
    }
    
    @Bridge
    public static native @Pointer long objc_getClass(@Pointer long name);
    
    @Bridge
    public static native BytePtr sel_getName(Selector sel);

    @Bridge
    public static native Selector sel_registerName(BytePtr str);
    
    @Bridge
    public static native void objc_setAssociatedObject(@Pointer long object, @Pointer long key, @Pointer long value, int policy);

    @Bridge
    public static native @Pointer long objc_getAssociatedObject(@Pointer long object, @Pointer long key);

    @Bridge
    public static native @Pointer long objc_allocateClassPair(@Pointer long superclass, @Pointer long name, @Pointer long extraBytes);
    
    @Bridge
    public static native void objc_registerClassPair(@Pointer long cls);
    
    @Bridge
    public static native @Pointer long object_getClass(@Pointer long object);

    @Bridge
    public static native @Pointer long object_getClassName(@Pointer long object);

    @Bridge
    public static native @Pointer long class_getSuperclass(@Pointer long cls);
    
    @Bridge
    public static native @Pointer long class_getInstanceMethod(@Pointer long aClass, @Pointer long aSelector);
    
    @Bridge
    public static native boolean class_addMethod(@Pointer long cls, @Pointer long name, @Pointer long imp, @Pointer long types);
    
    @Bridge
    public static native boolean class_addIvar(@Pointer long cls, @Pointer long name, int size, byte alignment, @Pointer long types);
    
    @Bridge
    public static native @Pointer long class_getInstanceVariable(@Pointer long cls, @Pointer long name);
    
    @Bridge
    public static native @Pointer long class_getIvarLayout(@Pointer long cls);
    
    @Bridge
    public static native int ivar_getOffset(@Pointer long ivar);
    
    @Bridge
    public static native @Pointer long method_getTypeEncoding(@Pointer long method);
    
    @Bridge
    public static native @Pointer long method_getImplementation(@Pointer long method);
    
    @Bridge(symbol = "objc_msgSend")
    public static native @Pointer long ptr_objc_msgSend(@Pointer long receiver, @Pointer long selector);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native @Pointer long ptr_objc_msgSendSuper(@Pointer long zuper, @Pointer long selector);

    @Bridge(symbol = "objc_msgSend")
    public static native void void_objc_msgSend(@Pointer long receiver, @Pointer long selector);

    @Bridge(symbol = "objc_msgSendSuper")
    public static native void void_objc_msgSendSuper(@Pointer long zuper, @Pointer long selector);
    
    @Bridge(symbol = "objc_msgSend")
    public static native boolean boolean_objc_msgSend(@Pointer long receiver, @Pointer long selector);
    
    @Bridge(symbol = "objc_msgSend")
    public static native void void_objc_msgSend_ptr(@Pointer long receiver, @Pointer long selector, @Pointer long ptr);
    
    @Bridge(symbol = "objc_msgSend")
    public static native void void_objc_msgSend_boolean(@Pointer long receiver, @Pointer long selector, boolean b);
    
    @Bridge(symbol = "objc_msgSend")
    public static native int int_objc_msgSend(@Pointer long receiver, @Pointer long selector);

    @Bridge(symbol = "objc_msgSend")
    public static native @Pointer long ptr_objc_msgSend_ptr(@Pointer long receiver, @Pointer long selector, @Pointer long ptr);
    
    @Bridge(symbol = "objc_msgSend")
    public static native @Pointer long ptr_objc_msgSend_int(@Pointer long receiver, @Pointer long selector, int i);
    
    @Bridge(symbol = "objc_msgSend")
    public static native @Pointer long ptr_objc_msgSend_long(@Pointer long receiver, @Pointer long selector, long l);

    @Bridge(symbol = "objc_msgSend")
    public static native char char_objc_msgSend_int(@Pointer long receiver, @Pointer long selector, int i);
    
    @Bridge(symbol = "objc_msgSend_stret")
    public static native void objc_msgSend_stret(@StructRet @Pointer long ret, @Pointer long receiver, @Pointer long selector);
}
