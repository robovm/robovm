/*
 * Copyright (C) 2012 Trillian Mobile AB
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

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.robovm.objc.annotation.NativeClass;
import org.robovm.objc.annotation.Property;
import org.robovm.rt.VM;
import org.robovm.rt.bro.NativeObject;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.Callback;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.annotation.Marshaler;
import org.robovm.rt.bro.annotation.MarshalsPointer;
import org.robovm.rt.bro.annotation.Pointer;
import org.robovm.rt.bro.annotation.StructMember;
import org.robovm.rt.bro.ptr.Ptr;
import org.robovm.rt.bro.ptr.VoidPtr;

/**
 *
 */
@Library("Foundation")
@NativeClass("Object")
@Marshaler(ObjCObject.Marshaler.class)
public abstract class ObjCObject extends NativeObject {

    public static class ObjCObjectPtr extends Ptr<ObjCObject, ObjCObjectPtr> {}
    
    static {
        ObjCRuntime.bind();
        
        try {
            Field f = ObjCObject.class.getDeclaredField("customClass");
            CUSTOM_CLASS_OFFSET = VM.getInstanceFieldOffset(VM.getFieldAddress(f));
        } catch (Throwable t) {
            throw new Error(t);
        }
    }

    /**
     * Common lock object used to prevent concurrent access to data in the
     * Obj-C bridge (such as {@link ObjCObject#peers} and 
     * {@link ObjCClass#typeToClass}). This should be used to prevent deadlock
     * situations from occurring. (#349)
     */
    static final Object objcBridgeLock = new Object();

    private static final LongMap<ObjCObjectRef> peers = new LongMap<>();

    private static final long CUSTOM_CLASS_OFFSET;
    
    private ObjCSuper zuper;
    protected final boolean customClass;
    
    protected ObjCObject() {
        long handle = alloc();
        setHandle(handle);
        if (handle != 0) {
            // Make sure the peer is set immediately even if a different handle
            // is set later with initObject().
            setPeerObject(handle, this);
        }
        customClass = getObjCClass().isCustom();
    }
    
    protected ObjCObject(long handle) {
        initObject(handle);
        customClass = getObjCClass().isCustom();
    }
    
    ObjCObject(long handle, boolean customClass) {
        initObject(handle);
        this.customClass = customClass;
    }
    
    protected void initObject(long handle) {
        if (handle == 0) {
            throw new RuntimeException("Objective-C initialization method returned nil");
        }
        long oldHandle = getHandle();
        if (handle != oldHandle) {
            if (oldHandle != 0) {
                removePeerObject(this);
            }
            setHandle(handle);
            setPeerObject(handle, this);
        }
    }
    
    protected long alloc() {
        throw new UnsupportedOperationException("Cannot create instances of " + getClass().getName());
    }
    
    @Override
    protected final void finalize() throws Throwable {
        dispose(true);
    }
    
    public final void dispose() {
        dispose(false);
    }
    
    protected void doDispose() {
    }
    
    protected void dispose(boolean finalizing) {
        long handle = getHandle();
        if (handle != 0) {
            removePeerObject(this);
            doDispose();
            setHandle(0);
        }
        if (finalizing) {
            try {
                super.finalize();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
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
    
    @SuppressWarnings("unchecked")
    static <T extends ObjCObject> T getPeerObject(long handle) {
        synchronized (objcBridgeLock) {
            ObjCObjectRef ref = peers.get(handle);
            T o = ref != null ? (T) ref.get() : null;
            return o;
        }
    }
    
    private static void setPeerObject(long handle, ObjCObject o) {
        synchronized (objcBridgeLock) {
            if (o == null) {
                peers.remove(handle);
            } else {
                peers.put(handle, new ObjCObjectRef(o));
            }
        }
    }

    
    private static void removePeerObject(ObjCObject o) {
        synchronized (objcBridgeLock) {
            ObjCObjectRef ref = peers.remove(o.getHandle());
            ObjCObject p = ref != null ? ref.get() : null;
            if (p != null && o != p) {
                // Not the same peer. Put it back.
                peers.put(o.getHandle(), new ObjCObjectRef(o));
            }
        }
    }
    
    public <T extends Object> T addStrongRef(T to) {
        AssociatedObjectHelper.addStrongRef(this, to);
        return to;
    }
    
    public void removeStrongRef(Object to) {
        AssociatedObjectHelper.removeStrongRef(this, to, false);
    }
    
    /**
     * Updates a strong reference handling {@code null} values properly. This
     * is meant to be used for {@link Property} setter methods with 
     * {@code strongRef=true}.
     * 
     * @param before the previous value for the property. If not {@code null}
     *        and not equal to {@code after} {@link #removeStrongRef(Object)}
     *        will be called on this value.
     * @param after the new value for the property. If not {@code null}
     *        and not equal to {@code after} {@link #addStrongRef(Object)}
     *        will be called on this value.
     */
    public void updateStrongRef(Object before, Object after) {
        if (before == after) {
            // Either both are null or they reference the same object.
            // If not null we assume that the property has already been set so 
            // that there already exists a strong reference.
            return;
        }
        if (before != null) {
            // Don't fail if the before value didn't have a strong reference.
            // It could have been set from within ObjC.
            AssociatedObjectHelper.removeStrongRef(this, before, true);
        }
        if (after != null) {
            AssociatedObjectHelper.addStrongRef(this, after);
        }
    }
    
    public Object getAssociatedObject(Object key) {
        return AssociatedObjectHelper.getAssociatedObject(this, key);
    }

    public void setAssociatedObject(Object key, Object value) {
        AssociatedObjectHelper.setAssociatedObject(this, key, value);
    }
    
    public static <T extends ObjCObject> T toObjCObject(Class<T> cls, long handle) {
        return toObjCObject(cls, handle, false);
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends ObjCObject> T toObjCObject(Class<T> cls, long handle, boolean forceType) {
        if (handle == 0L) {
            return null;
        }
        if (cls == ObjCClass.class) {
            return (T) ObjCClass.toObjCClass(handle);
        }

        synchronized (objcBridgeLock) {
            T o = getPeerObject(handle);
            if (o != null && o.getHandle() != 0) {
                if (forceType && !cls.isAssignableFrom(o.getClass())) {
                    throw new IllegalStateException("The peer object type " + o.getClass().getName() 
                            + " is not compatible with the forced type " + cls.getName());
                }
                return o;
            }
    
            ObjCClass objCClass = forceType ? ObjCClass.getByType(cls) : ObjCClass.getFromObject(handle);
            Class<T> c = (Class<T>) objCClass.getType();
    
            o = VM.allocateObject(c);
            o.setHandle(handle);
            setPeerObject(handle, o);
            if (objCClass.isCustom()) {
                VM.setBoolean(VM.getObjectAddress(o) + CUSTOM_CLASS_OFFSET, true);
            }
            o.afterMarshaled();
            return o;
        }
    }
    
    public static class Marshaler {
        @MarshalsPointer
        public static ObjCObject toObject(Class<? extends ObjCObject> cls, long handle, long flags) {
            ObjCObject o = ObjCObject.toObjCObject(cls, handle);
            return o;
        }
        @MarshalsPointer
        public static long toNative(ObjCObject o, long flags) {
            if (o == null) {
                return 0L;
            }
            return o.getHandle();
        }
        @MarshalsPointer
        public static ObjCProtocol protocolToObject(Class<?> cls, long handle, long flags) {
            Class<? extends ObjCObject> proxyClass = ObjCClass.allObjCProxyClasses.get(cls.getName());
            if (proxyClass == null) {
                proxyClass = ObjCObject.class;
            }
            ObjCObject o = ObjCObject.toObjCObject(proxyClass, handle);
            return (ObjCProtocol) o;
        }
        @MarshalsPointer
        public static long protocolToNative(ObjCProtocol o, long flags) {
            if (o == null) {
                return 0L;
            }
            return ((ObjCObject) o).getHandle();
        }
    }
    
    static class ObjCObjectRef extends WeakReference<ObjCObject> {
        public final long handle;
        public ObjCObjectRef(ObjCObject referent) {
            super(referent);
            handle = referent.getHandle();
        }
    }
    
    static class AssociatedObjectHelper {
        private static final String STRONG_REFS_KEY = AssociatedObjectHelper.class.getName() + ".StrongRefs";

        private static final int OBJC_ASSOCIATION_RETAIN_NONATOMIC = 1;
        private static final long NS_OBJECT_CLASS;
        private static final long RELEASE_LISTENER_CLASS;
        private static final String OWNER_IVAR_NAME = "value";
        private static final int OWNER_IVAR_OFFSET;
        private static final Selector alloc = Selector.register("alloc");
        private static final Selector init = Selector.register("init");
        private static final Selector release = Selector.register("release");
        private static final Selector retainCount = Selector.register("retainCount");
        private static final Map<Long, Map<Object, Object>> ASSOCIATED_OBJECTS = new HashMap<Long, Map<Object, Object>>();

        static {
            int ptrSize = VoidPtr.sizeOf();
            int alignment = ptrSize == 4 ? 2 : 3;
            
            NS_OBJECT_CLASS = ObjCRuntime.objc_getClass(VM.getStringUTFChars("NSObject"));
            long cls = ObjCRuntime.objc_allocateClassPair(NS_OBJECT_CLASS, VM.getStringUTFChars("RoboVMReleaseListener"), ptrSize);
            if (cls == 0L) {
                throw new Error("Failed to create the RoboVMReleaseListener Objective-C class: objc_allocateClassPair(...) failed");
            }
            if (!ObjCRuntime.class_addIvar(cls, VM.getStringUTFChars(OWNER_IVAR_NAME), ptrSize, (byte) alignment, VM.getStringUTFChars("?"))) {
                throw new Error("Failed to create the RoboVMAssocObjWrapper Objective-C class: class_addIvar(...) failed");
            }
            Method releaseMethod = null;
            try {
                releaseMethod = AssociatedObjectHelper.class.getDeclaredMethod("release", Long.TYPE, Long.TYPE);
            } catch (Throwable t) {
                throw new Error(t);
            }
            long superReleaseMethod = ObjCRuntime.class_getInstanceMethod(NS_OBJECT_CLASS, release.getHandle());
            long releaseType = ObjCRuntime.method_getTypeEncoding(superReleaseMethod);
            if (!ObjCRuntime.class_addMethod(cls, release.getHandle(), VM.getCallbackMethodImpl(releaseMethod), releaseType)) {
                throw new Error("Failed to create the RoboVMReleaseListener Objective-C class: class_addMethod(...) failed");                
            }
            ObjCRuntime.objc_registerClassPair(cls);
            
            RELEASE_LISTENER_CLASS = cls;
            OWNER_IVAR_OFFSET = ObjCRuntime.ivar_getOffset(ObjCRuntime.class_getInstanceVariable(cls, VM.getStringUTFChars(OWNER_IVAR_NAME)));
        }

        private static void enableListener(long handle) {
            long releaseListener = ObjCRuntime.objc_getAssociatedObject(handle, RELEASE_LISTENER_CLASS);
            if (releaseListener == 0) {
                releaseListener = ObjCRuntime.ptr_objc_msgSend(RELEASE_LISTENER_CLASS, alloc.getHandle());
                if (releaseListener == 0L) {
                    throw new OutOfMemoryError();
                }
                releaseListener = ObjCRuntime.ptr_objc_msgSend(releaseListener, init.getHandle());
                VM.setPointer(releaseListener + OWNER_IVAR_OFFSET, handle);
                ObjCRuntime.objc_setAssociatedObject(handle, RELEASE_LISTENER_CLASS, releaseListener, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
                ObjCRuntime.void_objc_msgSend(releaseListener, release.getHandle());
            }
        }

        private static void disableListener(long handle) {
            ObjCRuntime.objc_setAssociatedObject(handle, RELEASE_LISTENER_CLASS, 0L, 0);
        }

        public static Object getAssociatedObject(ObjCObject object, Object key) {
            synchronized (ASSOCIATED_OBJECTS) {
                Map<Object, Object> map = ASSOCIATED_OBJECTS.get(object.getHandle());
                if (map == null) {
                    return null;
                }
                return map.get(key);
            }
        }

        public static void setAssociatedObject(ObjCObject object, Object key, Object value) {
            synchronized (ASSOCIATED_OBJECTS) {
                Map<Object, Object> map = ASSOCIATED_OBJECTS.get(object.getHandle());
                if (map == null && value == null) {
                    return;
                }
                if (map == null) {
                    map = new HashMap<Object, Object>();
                    enableListener(object.getHandle());
                    ASSOCIATED_OBJECTS.put(object.getHandle(), map);
                }
                if (value != null) {
                    map.put(key, value);
                } else {
                    map.remove(key);
                    if (map.isEmpty()) {
                        disableListener(object.getHandle());
                        ASSOCIATED_OBJECTS.remove(object.getHandle());                        
                    }
                }
            }
        }

        @SuppressWarnings({ "rawtypes", "unchecked" })
        public static void addStrongRef(ObjCObject from, Object to) {
            if (to == null) {
                throw new NullPointerException();
            }
            synchronized (ASSOCIATED_OBJECTS) {
                List l = (List) getAssociatedObject(from, STRONG_REFS_KEY);
                if (l == null) {
                    l = new ArrayList();
                    setAssociatedObject(from, STRONG_REFS_KEY, l);
                }
                l.add(to);
            }
        }

        @SuppressWarnings("rawtypes")
        public static void removeStrongRef(ObjCObject from, Object to, boolean ignoreNotExists) {
            if (to == null) {
                throw new NullPointerException();
            }
            synchronized (ASSOCIATED_OBJECTS) {
                List l = (List) getAssociatedObject(from, STRONG_REFS_KEY);
                if (!ignoreNotExists && (l == null || !l.remove(to))) {
                    throw new IllegalArgumentException("No strong ref exists from " + from 
                            + " (a " + from.getClass().getName() + ") to " + to 
                            + " a (" + to.getClass().getName() + ")");
                }
                if (l != null && l.isEmpty()) {
                    setAssociatedObject(from, STRONG_REFS_KEY, null);
                }
            }
        }
        
        @Callback
        static void release(@Pointer long self, @Pointer long sel) {
            if (ObjCRuntime.int_objc_msgSend(self, retainCount.getHandle()) == 1) {
                long owner = VM.getPointer(self + OWNER_IVAR_OFFSET);
                synchronized (ASSOCIATED_OBJECTS) {
                    ASSOCIATED_OBJECTS.remove(owner);   
                }
            }
            ObjCRuntime.void_objc_msgSendSuper(new Super(self, NS_OBJECT_CLASS).getHandle(), sel);
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
