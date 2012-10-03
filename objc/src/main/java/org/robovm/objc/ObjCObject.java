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

import org.robovm.objc.annotation.BindClass;
import org.robovm.rt.VM;
import org.robovm.rt.bro.NativeObject;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.annotation.Marshaler;
import org.robovm.rt.bro.ptr.Ptr;
import org.robovm.rt.bro.ptr.Ptr.MarshalerCallback;

/**
 *
 */
@Library("Foundation")
@BindClass("Object")
@Marshaler(ObjCObject.Marshaler.class)
public abstract class ObjCObject extends NativeObject {

    static {
        ObjCRuntime.bind();
    }
    
    private static final long PEER_OBJECT_KEY = VM.getObjectAddress(ObjCObject.class);
    private static final long NSNUMBER_CLASS = ObjCRuntime.objc_getClass(VM.getStringUTFChars("NSNumber"));
    private static final int OBJC_ASSOCIATION_RETAIN_NONATOMIC = 1;
    private static final Selector alloc = Selector.register("alloc");
    private static final Selector initWithLongLong$ = Selector.register("initWithLongLong:");
    private static final Selector longLongValue = Selector.register("longLongValue");
    
    private ObjCSuper zuper;
    
    protected ObjCObject() {
        long handle = alloc();
        setHandle(handle);
        setAssociatedObject(handle, PEER_OBJECT_KEY, this);
    }
    
    protected ObjCObject(long handle) {
        setHandle(handle);
        setAssociatedObject(handle, PEER_OBJECT_KEY, this);
    }
    
    protected long alloc() {
        throw new UnsupportedOperationException("Cannot create instances of " + getClass().getName());
    }
    
    @SuppressWarnings("unchecked")
    protected ObjCSuper getSuper() {
        if (zuper == null) {
            Class<? extends ObjCObject> javaClass = (Class<? extends ObjCObject>) getClass().getSuperclass();
            ObjCClass objCClass = ObjCClass.getByType(javaClass);
            while (objCClass.isCustomClass()) {
                javaClass = (Class<? extends ObjCObject>) javaClass.getSuperclass();
                objCClass = ObjCClass.getByType(javaClass);
            }
            zuper = new ObjCSuper(this, objCClass);
        }
        return zuper;
    }
    
//    protected void setHandle(long h) {
//        if (h == 0L) {
//            throw new IllegalArgumentException("Trying to set handle to 0");
//        }
//        if (this.handle != h) {
//            if (this.handle != 0L) {
//                setAssociatedObject(this.handle, PEER_OBJECT_KEY, null);
//            }
//            this.handle = h;
//            setAssociatedObject(this.handle, PEER_OBJECT_KEY, this);
//        }
//    }
    
    protected void afterMarshaled() {
    }
    
    public final ObjCClass getObjCClass() {
        return ObjCClass.getFromObject(this);
    }
    
//    public <T> T getAssociatedObject(Object key) {
//        return getAssociatedObject(getHandle(), VM.getObjectAddress(key));
//    }
//    
//    public void setAssociatedObject(Object key, Object value) {
//        setAssociatedObject(getHandle(), VM.getObjectAddress(key), value);
//    }
    
//    @SuppressWarnings("unchecked")
//    public static <T extends Id> T fromHandle(long handle, Class<T> fallback) {
//        T o = Id.getPeerObject(handle);
//        if (handle == 0L || o != null) {
//            return o;
//        }
//        ObjCClass objcClass = ObjCClass.getFromObject(handle, ObjCClass.getByType(fallback));
//        Class<T> c = (Class<T>) objcClass.getType();
//        if (c == ObjCClass.class) {
//            return (T) objcClass;
//        }
//
//        try {
//            Constructor<T> constructor = c.getDeclaredConstructor(Initializer.class);
//            constructor.setAccessible(true);
//            return constructor.newInstance(new SetHandleInitializer(handle));
//        } catch (Exception e) {
//            if (e instanceof RuntimeException) {
//                throw (RuntimeException) e;
//            }
//            throw new ObjCUnmarshalException("Failed to unmarshal object of type: " + c, e);
//        }
//    }
    
    public static <T extends ObjCObject> T getPeerObject(long handle) {
        return getAssociatedObject(handle, PEER_OBJECT_KEY);
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends ObjCObject> T toObjCObject(Class<T> cls, long handle) {
        if (handle == 0L) {
            return null;
        }
        T o = getPeerObject(handle);
        if (o != null) {
            return o;
        }
        ObjCClass fallback = ObjCClass.getByType(cls);
        ObjCClass objCClass = ObjCClass.getFromObject(handle, fallback);
        Class<T> c = (Class<T>) objCClass.getType();
        if (c == ObjCClass.class) {
            return (T) objCClass;
        }

        o = VM.allocateObject(c);
        o.setHandle(handle);
        setAssociatedObject(handle, PEER_OBJECT_KEY, o);
        o.afterMarshaled();
        return o;
    }
    
    @SuppressWarnings("unchecked")
    private static <T> T getAssociatedObject(long handle, long key) {
        if (handle == 0L) {
            return null;
        }
        long numberPtr = ObjCRuntime.objc_getAssociatedObject(handle, key);
        if (numberPtr != 0L) {
            long address = ObjCRuntime.ptr_objc_msgSend(numberPtr, longLongValue.getHandle());
            return (T) VM.castAddressToObject(address);
        }
        return null;
    }

    private static void setAssociatedObject(long handle, long key, Object o) {
        if (o == null) {
            ObjCRuntime.objc_setAssociatedObject(handle, key, 0L, 0);
        } else {
            long address = VM.getObjectAddress(o);

            /*
             * Wrap the address in an NSNumber as objc_setAssociatedObject seems to expect 
             * an NSObject. We cannot use the Java NSNumber class since that could result 
             * in infinite recursion. Also, we cannot use the class method 
             * numberWithLongLong: since it requires an NSAutoReleasePool.
             */
            long numberPtr = ObjCRuntime.ptr_objc_msgSend(NSNUMBER_CLASS, alloc.getHandle());
            if (numberPtr == 0L) {
                throw new OutOfMemoryError();
            }
            numberPtr = ObjCRuntime.ptr_objc_msgSend_long(numberPtr, initWithLongLong$.getHandle(), address);
            ObjCRuntime.objc_setAssociatedObject(handle, PEER_OBJECT_KEY, numberPtr, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
        }
    }
    
    public static class Marshaler {
        @SuppressWarnings("rawtypes")
        public static final MarshalerCallback MARSHALER_CALLBACK = new MarshalerCallback() {
            @SuppressWarnings("unchecked")
            public NativeObject toObject(Class cls, long handle) {
                return ObjCObject.toObjCObject(cls, handle);
            }
        };
        
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public static Object toObject(Class cls, long handle, boolean copy) {
            ObjCObject o = ObjCObject.toObjCObject(cls, handle);
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
        
        public static long toNative(Object o) {
            if (o == null) {
                return 0L;
            }
            return ((ObjCObject) o).getHandle();
        }
        
        public static void updateNative(Object o, long handle) {
        }
    }
}
