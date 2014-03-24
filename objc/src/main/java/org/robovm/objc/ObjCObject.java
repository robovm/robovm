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
	
    private static final long CUSTOM_CLASS_OFFSET;
    
    private ObjCSuper zuper;
    protected final boolean customClass;
    
    protected ObjCObject() {
        long handle = alloc();
        setHandle(handle);
        // Make sure the peer is set immediately even if a different handle
        // is set later with initObject().
        AssociatedObjectHelper.setPeerObject(handle, this);
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
        setHandle(handle);
        AssociatedObjectHelper.setPeerObject(handle, this);
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
            AssociatedObjectHelper.setPeerObject(handle, null);
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
    
    public static <T extends ObjCObject> T getPeerObject(long handle) {
        return AssociatedObjectHelper.getPeerObject(handle);
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
    
    @SuppressWarnings("unchecked")
    public static <T extends ObjCObject> T toObjCObject(Class<T> cls, long handle) {
        if (handle == 0L) {
            return null;
        }
        T o = getPeerObject(handle);
        if (o != null && o.getHandle() != 0) {
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
        AssociatedObjectHelper.setPeerObject(handle, o);
        if (objCClass.isCustom()) {
            VM.setBoolean(VM.getObjectAddress(o) + CUSTOM_CLASS_OFFSET, true);
        }
        o.afterMarshaled();
        return o;
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
    
    static class AssociatedObjectHelper {
        private static final String STRONG_REFS_KEY = AssociatedObjectHelper.class.getName() + ".StrongRefs";

        private enum Key {Peer, AssociatedObjects};
        private static final int OBJC_ASSOCIATION_RETAIN_NONATOMIC = 1;
        private static final long NS_OBJECT_CLASS;
        private static final long CLS;
        private static final String KEY_IVAR_NAME = "key";
        private static final int KEY_IVAR_OFFSET;
        private static final String VALUE_IVAR_NAME = "value";
        private static final int VALUE_IVAR_OFFSET;
        private static final Selector alloc = Selector.register("alloc");
        private static final Selector init = Selector.register("init");
        private static final Selector release = Selector.register("release");
        private static final Selector retainCount = Selector.register("retainCount");
        private static final Map<Long, Map<Object, Object>> ASSOCIATED_OBJECTS = new HashMap<Long, Map<Object, Object>>();

        static {
            NS_OBJECT_CLASS = ObjCRuntime.objc_getClass(VM.getStringUTFChars("NSObject"));
            long cls = ObjCRuntime.objc_allocateClassPair(NS_OBJECT_CLASS, VM.getStringUTFChars("RoboVMAssocObjWrapper"), 8);
            if (cls == 0L) {
                throw new Error("Failed to create the RoboVMAssocObjWrapper Objective-C class: objc_allocateClassPair(...) failed");
            }
            if (!ObjCRuntime.class_addIvar(cls, VM.getStringUTFChars(VALUE_IVAR_NAME), 4, (byte) 2, VM.getStringUTFChars("?"))) {
                throw new Error("Failed to create the RoboVMAssocObjWrapper Objective-C class: class_addIvar(...) failed");
            }
            if (!ObjCRuntime.class_addIvar(cls, VM.getStringUTFChars(KEY_IVAR_NAME), 4, (byte) 2, VM.getStringUTFChars("I"))) {
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
                throw new Error("Failed to create the RoboVMAssocObjWrapper Objective-C class: class_addMethod(...) failed");                
            }
            ObjCRuntime.objc_registerClassPair(cls);
            
            CLS = cls;
            VALUE_IVAR_OFFSET = ObjCRuntime.ivar_getOffset(ObjCRuntime.class_getInstanceVariable(cls, VM.getStringUTFChars(VALUE_IVAR_NAME)));
            KEY_IVAR_OFFSET = ObjCRuntime.ivar_getOffset(ObjCRuntime.class_getInstanceVariable(cls, VM.getStringUTFChars(KEY_IVAR_NAME)));
        }

        private static Object getAssociatedObject(long handle, Key key) {
            if (handle == 0L) {
                return null;
            }
            long wrapper = ObjCRuntime.objc_getAssociatedObject(handle, key.hashCode());
            if (wrapper != 0L) {
                long address = VM.getPointer(wrapper + VALUE_IVAR_OFFSET);
                if (address != 0L) {
                    return VM.castAddressToObject(address);
                }
            }
            return null;
        }

        private static void setAssociatedObject(long handle, Key key, Object value, boolean weak) {
            long wrapper = ObjCRuntime.objc_getAssociatedObject(handle, key.hashCode());
            if (wrapper != 0L && weak) {
                VM.unregisterDisappearingLink(wrapper + VALUE_IVAR_OFFSET);
            }
            if (value == null) {
                ObjCRuntime.objc_setAssociatedObject(handle, key.hashCode(), 0L, 0);
            } else {
                wrapper = ObjCRuntime.ptr_objc_msgSend(CLS, alloc.getHandle());
                if (wrapper == 0L) {
                    throw new OutOfMemoryError();
                }
                wrapper = ObjCRuntime.ptr_objc_msgSend(wrapper, init.getHandle());
                long address = VM.getObjectAddress(value);
                VM.setPointer(wrapper + VALUE_IVAR_OFFSET, address);
                VM.setInt(wrapper + KEY_IVAR_OFFSET, key.ordinal());
                if (weak) {
                    VM.registerDisappearingLink(wrapper + VALUE_IVAR_OFFSET, value);
                }
                ObjCRuntime.objc_setAssociatedObject(handle, key.hashCode(), wrapper, OBJC_ASSOCIATION_RETAIN_NONATOMIC);
                ObjCRuntime.void_objc_msgSend(wrapper, release.getHandle());
            }
        }

        @SuppressWarnings("unchecked")
        public static <T extends ObjCObject> T getPeerObject(long handle) {
            return (T) getAssociatedObject(handle, Key.Peer);
        }
        
        public static void setPeerObject(long handle, ObjCObject o) {
            setAssociatedObject(handle, Key.Peer, o, true);
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
                    setAssociatedObject(object.getHandle(), Key.AssociatedObjects, map, false);
                    ASSOCIATED_OBJECTS.put(object.getHandle(), map);
                }
                if (value != null) {
                    map.put(key, value);
                } else {
                    map.remove(key);
                    if (map.isEmpty()) {
                        setAssociatedObject(object.getHandle(), Key.AssociatedObjects, null, false);
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
                Key key = Key.values()[VM.getInt(self + KEY_IVAR_OFFSET)];
                Object value = VM.castAddressToObject(VM.getLong(self + VALUE_IVAR_OFFSET));
                if (key == Key.Peer && value != null) {
                    VM.unregisterDisappearingLink(self + VALUE_IVAR_OFFSET);
                } else if (key == Key.AssociatedObjects) {
                    synchronized (ASSOCIATED_OBJECTS) {
                        ASSOCIATED_OBJECTS.remove(self);   
                    }
                }
//                if (value != null) {
//                    System.out.format("RoboVMAssocObjWrapper.release() called on 0x%x associated with " 
//                            + "value %s (a %s) for key %s", self, value, value.getClass().getName(), key);
//                } else {
//                    System.out.format("RoboVMAssocObjWrapper.release() called on 0x%x associated with " 
//                            + "null for key %s", self, key);
//                }
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
