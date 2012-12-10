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

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.robovm.objc.annotation.NativeClass;
import org.robovm.rt.VM;
import org.robovm.rt.bro.NativeObject;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.Callback;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.annotation.Marshaler;
import org.robovm.rt.bro.annotation.Pointer;
import org.robovm.rt.bro.annotation.StructMember;
import org.robovm.rt.bro.ptr.Ptr;
import org.robovm.rt.bro.ptr.Ptr.MarshalerCallback;

/**
 *
 */
@Library("Foundation")
@NativeClass("Object")
@Marshaler(ObjCObject.Marshaler.class)
public abstract class ObjCObject extends NativeObject {

    static {
        ObjCRuntime.bind();
        
        try {
            Field f = ObjCObject.class.getDeclaredField("customClass");
            CUSTOM_CLASS_OFFSET = VM.getInstanceFieldOffset(VM.getFieldAddress(f));
        } catch (Throwable t) {
            throw new Error(t);
        }
    }
	
    private static final long CUSTOM_CLASS_OFFSET;
    
    private ObjCSuper zuper;
    protected final boolean customClass;
    
    protected ObjCObject() {
        long handle = alloc();
        setHandle(handle);
        PeerWrapper.setPeerObject(handle, this);
        customClass = getObjCClass().isCustom();
    }
    
    protected ObjCObject(long handle) {
        setHandle(handle);
        PeerWrapper.setPeerObject(handle, this);
        customClass = getObjCClass().isCustom();
    }
    
    ObjCObject(long handle, boolean customClass) {
        setHandle(handle);
        PeerWrapper.setPeerObject(handle, this);
        this.customClass = customClass;
    }
    
    protected long alloc() {
        throw new UnsupportedOperationException("Cannot create instances of " + getClass().getName());
    }
    
    @SuppressWarnings("unchecked")
    protected ObjCSuper getSuper() {
        if (zuper == null) {
            Class<? extends ObjCObject> javaClass = (Class<? extends ObjCObject>) getClass().getSuperclass();
            ObjCClass objCClass = ObjCClass.getByType(javaClass);
            while (objCClass.isCustom()) {
                javaClass = (Class<? extends ObjCObject>) javaClass.getSuperclass();
                objCClass = ObjCClass.getByType(javaClass);
            }
            zuper = new ObjCSuper(this, objCClass);
        }
        return zuper;
    }
    
    protected void afterMarshaled() {
    }
    
    public final ObjCClass getObjCClass() {
        return ObjCClass.getFromObject(this);
    }
    
    public static <T extends ObjCObject> T getPeerObject(long handle) {
        return PeerWrapper.getPeerObject(handle);
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
        ObjCClass fallback = ObjCClass.getByType(cls.isInterface() ? ObjCObject.class : cls);
        ObjCClass objCClass = ObjCClass.getFromObject(handle, fallback);
        Class<T> c = (Class<T>) objCClass.getType();
        if (c == ObjCClass.class) {
            return (T) objCClass;
        }
        if (c == ObjCObject.class && cls.isInterface()) {
            throw new ObjCClassNotFoundException("Could not create a Java object for interface: " + cls.getName());
        }

        o = VM.allocateObject(c);
        o.setHandle(handle);
        PeerWrapper.setPeerObject(handle, o);
        if (objCClass.isCustom()) {
            VM.setBoolean(VM.getObjectAddress(o) + CUSTOM_CLASS_OFFSET, true);
        }
        o.afterMarshaled();
        return o;
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
        
        public static @Pointer long toNative(Object o) {
            if (o == null) {
                return 0L;
            }
            return ((ObjCObject) o).getHandle();
        }
        
        public static void updateNative(Object o, long handle) {
        }
    }
    
    public static class PeerWrapper {
        private static final long PEER_OBJECT_KEY = VM.getObjectAddress(PeerWrapper.class);
        private static final int OBJC_ASSOCIATION_RETAIN_NONATOMIC = 1;
        private static final long NS_OBJECT_CLS;
        private static final long CLS;
        private static final int PEER_IVAR_OFFSET;
        private static final Selector alloc = Selector.register("alloc");
        private static final Selector init = Selector.register("init");
        private static final Selector release = Selector.register("release");

        static {
            NS_OBJECT_CLS = ObjCRuntime.objc_getClass(VM.getStringUTFChars("NSObject"));
            long cls = ObjCRuntime.objc_allocateClassPair(NS_OBJECT_CLS, VM.getStringUTFChars("RoboVMPeerWrapper"), 4);
            if (cls == 0L) {
                throw new Error("Failed to create the RoboVMPeerWrapper Objective-C class: objc_allocateClassPair(...) failed");
            }
            if (!ObjCRuntime.class_addIvar(cls, VM.getStringUTFChars("peer"), 4, (byte) 2, VM.getStringUTFChars("?"))) {
                throw new Error("Failed to create the RoboVMPeerWrapper Objective-C class: class_addIvar(...) failed");
            }
            Method releaseMethod = null;
            try {
                releaseMethod = PeerWrapper.class.getDeclaredMethod("release", Long.TYPE, Long.TYPE);
            } catch (Throwable t) {
                throw new Error(t);
            }
            long superReleaseMethod = ObjCRuntime.class_getInstanceMethod(NS_OBJECT_CLS, release.getHandle());
            long releaseType = ObjCRuntime.method_getTypeEncoding(superReleaseMethod);
            if (!ObjCRuntime.class_addMethod(cls, release.getHandle(), VM.getCallbackMethodImpl(releaseMethod), releaseType)) {
                throw new Error("Failed to create the RoboVMPeerWrapper Objective-C class: class_addMethod(...) failed");                
            }
            ObjCRuntime.objc_registerClassPair(cls);
            
            CLS = cls;
            PEER_IVAR_OFFSET = ObjCRuntime.ivar_getOffset(ObjCRuntime.class_getInstanceVariable(cls, VM.getStringUTFChars("peer")));
        }
        
        @SuppressWarnings("unchecked")
        public static <T extends ObjCObject> T getPeerObject(long handle) {
            if (handle == 0L) {
                return null;
            }
            long peerWrapper = ObjCRuntime.objc_getAssociatedObject(handle, PEER_OBJECT_KEY);
            if (peerWrapper != 0L) {
                long address = VM.getPointer(peerWrapper + PEER_IVAR_OFFSET);
                if (address != 0L) {
                    return (T) VM.castAddressToObject(address);
                }
                System.out.println("RoboVMPeerWrapper " + Long.toHexString(peerWrapper) + " associated with " 
                        + Long.toHexString(handle) + " points to null");
            }
            return null;
        }
        
        public static void setPeerObject(long handle, Object o) {
            if (o == null) {
                ObjCRuntime.objc_setAssociatedObject(handle, PEER_OBJECT_KEY, 0L, 0);
            } else {
                long peerWrapper = ObjCRuntime.ptr_objc_msgSend(CLS, alloc.getHandle());
                if (peerWrapper == 0L) {
                    throw new OutOfMemoryError();
                }
                peerWrapper = ObjCRuntime.ptr_objc_msgSend(peerWrapper, init.getHandle());
                long address = VM.getObjectAddress(o);
                VM.setPointer(peerWrapper + PEER_IVAR_OFFSET, address);
                VM.registerDisappearingLink(peerWrapper + PEER_IVAR_OFFSET, o);
                ObjCRuntime.objc_setAssociatedObject(handle, PEER_OBJECT_KEY, peerWrapper, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
                System.out.println("RoboVMPeerWrapper " + Long.toHexString(peerWrapper) + " associated with " 
                        + Long.toHexString(handle) + " a " + o.getClass().getName());
            }
        }
        
        @Callback
        private static void release(@Pointer long self, @Pointer long sel) {
            ObjCObject o = (ObjCObject) VM.castAddressToObject(VM.getLong(self + PEER_IVAR_OFFSET));
            if (o != null) {
                System.out.println("RoboVMPeerWrapper.release() called on " + Long.toHexString(self) + " associated with " 
                        + Long.toHexString(o.getHandle()) + " a " + o.getClass().getName());
            } else {
                System.out.println("RoboVMPeerWrapper.release() called on " + Long.toHexString(self) + " which points to null");
            }
            ObjCRuntime.void_objc_msgSendSuper(new Super(self, NS_OBJECT_CLS).getHandle(), sel);
        }
        
        public static final class Super extends Struct<Super> {

            public Super(long receiver, long objcClass) {
                receiver(receiver);
                objCClass(objcClass);
            }
            
            @StructMember(0)
            public native @Pointer long receiver();
            
            @StructMember(0)
            public native Super receiver(@Pointer long receiver);
            
            @StructMember(1)
            public native @Pointer long objCClass();
            
            @StructMember(1)
            public native Super objCClass(@Pointer long objCClass);
        }
    }
}
