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
package org.robovm.apple.foundation;

import static org.robovm.rt.bro.MarshalerFlags.*;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;

import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
@Marshalers({
    @Marshaler(NSString.AsStringMarshaler.class),
    @Marshaler(NSArray.AsListMarshaler.class),
    @Marshaler(NSObject.Marshaler.class)
})
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSObject/*</name>*/ 
    extends /*<extends>*/ObjCObject/*</extends>*/ 
    /*<implements>*/implements NSObjectProtocol/*</implements>*/ {

    protected static class SkipInit {}

    /*<ptr>*/public static class NSObjectPtr extends Ptr<NSObject, NSObjectPtr> {}/*</ptr>*/
    
    public static class Marshaler {
        @MarshalsPointer
        public static NSObject toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSObject o = ObjCObject.toObjCObject(cls, handle);
            return o;
        }
        @MarshalsPointer
        public static long toNative(NSObject o, long flags) {
            if (o == null) {
                return 0L;
            }
            long handle = o.getHandle();
            if ((flags & (CALL_TYPE_CALLBACK | CALL_TYPE_GLOBAL_VALUE | CALL_TYPE_STRUCT_MEMBER)) > 0) {
                // Make sure the object outlives the GC
                retain(handle);
                if ((flags & CALL_TYPE_CALLBACK) > 0) {
                    // NSObjects returned by callbacks should be autoreleased
                    autorelease(handle);
                }
            }
            return handle;
        }
        @MarshalsPointer
        public static long toNative(NSObjectProtocol o, long flags) {
            return toNative((NSObject) o, flags) ;
        }
    }
    
    /*<bind>*/static { ObjCRuntime.bind(NSObject.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    private NSKeyValueCoder keyValueCoder;
    
    
    public NSObject() {
        initObject(init());
    }

    protected NSObject(long handle) {
        super(handle);
    }
    
    protected NSObject(SkipInit skipInit) {
    }
    
    /*<constructors>*/
    
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "classForCoder")
    public native Class<?> getClassForCoder();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "autoContentAccessingProxy")
    public native NSObject getAutoContentAccessingProxy();
    @Property(selector = "observationInfo")
    public native VoidPtr getObservationInfo();
    @Property(selector = "setObservationInfo:")
    public native void setObservationInfo(VoidPtr v);
    @Property(selector = "classForKeyedArchiver")
    public native Class<?> getClassForKeyedArchiver();
    /*</properties>*/
    /*<members>*//*</members>*/
    
    @Override
    protected void afterMarshaled() {
        super.afterMarshaled();
        retain(getHandle());
    }
    
    protected long alloc() {
        return alloc(getObjCClass());
    }

    private static final Selector alloc = Selector.register("alloc");
    static long alloc(ObjCClass c) {
        long h = ObjCRuntime.ptr_objc_msgSend(c.getHandle(), alloc.getHandle());
        if (h == 0L) {
            throw new OutOfMemoryError();
        }
        return h;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NSObject)) {
            return false;
        }
        return isEqual((NSObject) obj);
    }
    
    @Override
    public int hashCode() {
        long h = hash();
        return (int) (h ^ (h >>> 32));
    }
    
    @Override
    protected void doDispose() {
        super.doDispose();
        // Only release once
        long handle = getHandle();
        if (handle != 0) {
            release(handle);
        }
    }

    @Override
    public String toString() {
        return description();
    }
    
    private static final Selector retain = Selector.register("retain");
    protected static void retain(long handle) {
        ObjCRuntime.void_objc_msgSend(handle, retain.getHandle());
    }
    
    private static final Selector release = Selector.register("release");
    protected static void release(long handle) {
        ObjCRuntime.void_objc_msgSend(handle, release.getHandle());
    }

    private static final Selector autorelease = Selector.register("autorelease");
    protected static void autorelease(long handle) {
        ObjCRuntime.void_objc_msgSend(handle, autorelease.getHandle());
    }

    // The methods below are defined in the NSObject protocol. We define them here
    // instead. The performSelector methods in particular must be defined here since
    // we need them to be final. If not final calls to these methods on Java classes
    // subclassing an ObjC class will be routed through Java and the marshalers will
    // try to marshal the return value to an NSObject even if the target method 
    // returns void. If there's garbage in the register used to return values the
    // marshaler is likely to cause a crash.

    @Method(selector = "performSelector:")
    public native final NSObject performSelector(Selector aSelector);
    @Method(selector = "performSelector:withObject:")
    public native final NSObject performSelector(Selector aSelector, NSObject object);
    @Method(selector = "performSelector:withObject:withObject:")
    public native final NSObject performSelector(Selector aSelector, NSObject object1, NSObject object2);
    @Method(selector = "isEqual:")
    public native boolean isEqual(NSObject object);
    @Method(selector = "hash")
    public native @MachineSizedUInt long hash();
    @Method(selector = "isKindOfClass:")
    public native boolean isKindOfClass(ObjCClass aClass);
    @Method(selector = "isMemberOfClass:")
    public native boolean isMemberOfClass(ObjCClass aClass);
    @Method(selector = "conformsToProtocol:")
    public native boolean conformsToProtocol(ObjCProtocol aProtocol);
    @Method(selector = "respondsToSelector:")
    public native boolean respondsToSelector(Selector aSelector);
    @Method(selector = "retain")
    public native NSObject retain();
    @Method(selector = "release")
    public native void release();
    @Method(selector = "autorelease")
    public native NSObject autorelease();
    @Method(selector = "retainCount")
    public native @MachineSizedUInt long retainCount();
    @Method(selector = "description")
    public native String description();
    
    // Versions of the performSelector methods which must be used when calling
    // methods which don't return any value.
    @Method(selector = "performSelector:")
    public native final void performSelectorV(Selector aSelector);
    @Method(selector = "performSelector:withObject:")
    public native final void performSelectorV(Selector aSelector, NSObject object);
    @Method(selector = "performSelector:withObject:withObject:")
    public native final void performSelectorV(Selector aSelector, NSObject object1, NSObject object2);
    
    public interface NSKeyValueObserver {
        void observeValue(String keyPath, NSObject object, NSKeyValueChangeInfo change);
    }
    
    private Set<KeyValueObserverWrapper> keyValueObservers;

    private class KeyValueObserverWrapper extends NSObject {
        private NSKeyValueObserver observer;
        private Set<String> keyPaths = new HashSet<>();
        private KeyValueObserverWrapper(NSKeyValueObserver observer, String keyPath) {
            this.observer = observer;
            this.keyPaths.add(keyPath);
        }
        
        @Method(selector = "observeValueForKeyPath:ofObject:change:context:")
        private void observeValueForKeyPath$ofObject$change$context$(String keyPath, NSObject object, NSKeyValueChangeInfo change, VoidPtr context) {
            observer.observeValue(keyPath, object, change);
        }
    }
    
    /**
     * Add a new observer to listen for value changes of the specified key path.
     * @param keyPath
     * @param observer
     */
    public void addKeyValueObserver(String keyPath, NSKeyValueObserver observer) {
        addKeyValueObserver(keyPath, observer, NSKeyValueObservingOptions.None);
    }
    /**
     * Add a new observer to listen for value changes of the specified key path. Use options to listen only for specific value changes.
     * @param keyPath
     * @param observer
     * @param options
     */
    public void addKeyValueObserver(String keyPath, NSKeyValueObserver observer, NSKeyValueObservingOptions options) {
        if (keyPath == null) {
            throw new NullPointerException("keyPath");
        }
        if (observer == null) {
            throw new NullPointerException("observer");
        }
        
        if (keyValueObservers == null) {
            keyValueObservers = new HashSet<>();
        }
        KeyValueObserverWrapper wrapper = null;
        synchronized (keyValueObservers) {
            for (KeyValueObserverWrapper w : keyValueObservers) {
                if (w.observer == observer) {
                    wrapper = w;
                    break;
                }
            }
            // If a wrapper with this observer exists, reuse that one, otherwise create a new one.
            if (wrapper != null) {
                wrapper.keyPaths.add(keyPath);
            } else {
                wrapper = new KeyValueObserverWrapper(observer, keyPath);
                addStrongRef(wrapper);
                keyValueObservers.add(wrapper);
            }
        }
        addObserver$forKeyPath$options$context$(wrapper, keyPath, options, null);
    }
    /**
     * Removes all key value observers that listen for value changes to keyPath.
     * @param keyPath
     */
    public void removeKeyValueObservers(String keyPath) {
        if (keyPath == null) {
            throw new NullPointerException("keyPath");
        }
        if (keyValueObservers == null) {
            return;
        }
        synchronized (keyValueObservers) {
            for (Iterator<KeyValueObserverWrapper> i = keyValueObservers.iterator(); i.hasNext();) {
                KeyValueObserverWrapper wrapper = i.next();
                if (wrapper.keyPaths.remove(keyPath)) {
                    removeObserver$forKeyPath$context$(wrapper, keyPath, null);
                    if (wrapper.keyPaths.size() == 0) {
                        i.remove();
                        removeStrongRef(wrapper);
                    }
                }
            }
        }
    }
    /**
     * Remove the key value observer from observing value changes to keyPath.
     * @param keyPath
     * @param observer
     */
    public void removeKeyValueObserver(String keyPath, NSKeyValueObserver observer) {
        if (keyPath == null) {
            throw new NullPointerException("keyPath");
        }
        if (observer == null) {
            throw new NullPointerException("observer");
        }
        if (keyValueObservers == null) {
            return;
        }
        KeyValueObserverWrapper wrapper = null;
        synchronized (keyValueObservers) {
            for (KeyValueObserverWrapper w : keyValueObservers) {
                if (w.observer == observer) {
                    wrapper = w;
                    break;
                }
            }
            if (wrapper != null) {
                if (wrapper.keyPaths.remove(keyPath)) {
                    removeObserver$forKeyPath$context$(wrapper, keyPath, null);
                    if (wrapper.keyPaths.size() == 0) {
                        keyValueObservers.remove(wrapper);
                        removeStrongRef(wrapper);
                    }
                }
            }
        }
    }
    
    public NSKeyValueCoder getKeyValueCoder() {
        if (keyValueCoder == null) {
            keyValueCoder = new NSKeyValueCoder(this);
        }
        return keyValueCoder;
    }

    public void willChangeValues(String key, NSKeyValueChange changeKind, NSIndexSet indexes) {
        willChangeValues(changeKind, indexes, key);
    }
    public void didChangeValues(String key, NSKeyValueChange changeKind, NSIndexSet indexes) {
        didChangeValues(changeKind, indexes, key);
    }
    /*<methods>*/
    @Method(selector = "init")
    private native @Pointer long init();
    @Method(selector = "copy")
    public native NSObject copy();
    @Method(selector = "mutableCopy")
    public native NSObject mutableCopy();
    @Method(selector = "addObserver:forKeyPath:options:context:")
    private native void addObserver$forKeyPath$options$context$(NSObject observer, String keyPath, NSKeyValueObservingOptions options, VoidPtr context);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "removeObserver:forKeyPath:context:")
    private native void removeObserver$forKeyPath$context$(NSObject observer, String keyPath, VoidPtr context);
    @Method(selector = "willChangeValueForKey:")
    public native void willChangeValue(String key);
    @Method(selector = "didChangeValueForKey:")
    public native void didChangeValue(String key);
    @Method(selector = "willChange:valuesAtIndexes:forKey:")
    private native void willChangeValues(NSKeyValueChange changeKind, NSIndexSet indexes, String key);
    @Method(selector = "didChange:valuesAtIndexes:forKey:")
    private native void didChangeValues(NSKeyValueChange changeKind, NSIndexSet indexes, String key);
    @Method(selector = "willChangeValueForKey:withSetMutation:usingObjects:")
    public native void willChangeValue(String key, NSKeyValueSetMutationKind mutationKind, NSSet<?> objects);
    @Method(selector = "didChangeValueForKey:withSetMutation:usingObjects:")
    public native void didChangeValue(String key, NSKeyValueSetMutationKind mutationKind, NSSet<?> objects);
    @Method(selector = "performSelector:withObject:afterDelay:inModes:")
    public final native void performSelector(Selector aSelector, NSObject anArgument, double delay, NSArray<?> modes);
    @Method(selector = "performSelector:withObject:afterDelay:")
    public final native void performSelector(Selector aSelector, NSObject anArgument, double delay);
    @Method(selector = "performSelectorOnMainThread:withObject:waitUntilDone:modes:")
    public final native void performSelectorOnMainThread(Selector aSelector, NSObject arg, boolean wait, NSArray<?> array);
    @Method(selector = "performSelectorOnMainThread:withObject:waitUntilDone:")
    public final native void performSelectorOnMainThread(Selector aSelector, NSObject arg, boolean wait);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "performSelector:onThread:withObject:waitUntilDone:modes:")
    public final native void performSelector(Selector aSelector, NSThread thr, NSObject arg, boolean wait, NSArray<?> array);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "performSelector:onThread:withObject:waitUntilDone:")
    public final native void performSelector(Selector aSelector, NSThread thr, NSObject arg, boolean wait);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "performSelectorInBackground:withObject:")
    public final native void performSelectorInBackground(Selector aSelector, NSObject arg);
    /*</methods>*/
}
