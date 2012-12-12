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

import org.robovm.rt.VM;
import org.robovm.rt.bro.Bro;
import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.annotation.Pointer;
import org.robovm.rt.bro.annotation.StructRet;
import org.robovm.rt.bro.ptr.BytePtr;

/**
 *
 */
@Library("objc")
public class ObjCRuntime {

    static {
        Bro.bind();
    }
    
    public static void bind() {
        Class<?> caller = VM.getStackClasses(0, 1)[0];
        bind(caller);
    }
    
    public static void bind(Class<?> c) {
        Bro.bind(c);
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
