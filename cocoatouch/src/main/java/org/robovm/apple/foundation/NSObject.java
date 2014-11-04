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
    public native ObjCClass getClassForCoder();
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
    public native ObjCClass getClassForKeyedArchiver();
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
    
    public void addObserver(NSObject observer, String keyPath, NSKeyValueObservingOptions options) {
        if (observer.customClass) {
            updateStrongRef(null, observer);
        }
        addObserver$forKeyPath$options$context$(observer, keyPath, options, null);
    }
    
    public void removeObserver(NSObject observer, String keyPath) {
        removeObserver$forKeyPath$context$(observer, keyPath, null);
        if (observer.customClass) {
            updateStrongRef(observer, null);
        }
    }
    
    protected void observeValue(String keyPath, NSObject object, NSKeyValueChangeInfo change) {
        
    }
    
    @Method(selector = "observeValueForKeyPath:ofObject:change:context:")
    private void observeValueForKeyPath$ofObject$change$context$(String keyPath, NSObject object, NSKeyValueChangeInfo change, VoidPtr context) {
        observeValue(keyPath, object, change);
    }

    /*<methods>*/
    @Method(selector = "init")
    private native @Pointer long init();
    @Method(selector = "copy")
    public native NSObject copy();
    @Method(selector = "mutableCopy")
    public native NSObject mutableCopy();
    @Method(selector = "valueForKey:")
    public native NSObject getValueForKey(String key);
    @Method(selector = "setValue:forKey:")
    public native void setValueForKey(NSObject value, String key);
    @Method(selector = "validateValue:forKey:error:")
    public native boolean validateValueForKey(NSObject ioValue, String inKey, NSError.NSErrorPtr outError);
    @Method(selector = "mutableArrayValueForKey:")
    public native NSMutableArray<?> getMutableArrayValueForKey(String key);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "mutableOrderedSetValueForKey:")
    public native NSMutableOrderedSet<?> getMutableOrderedSetValueForKey(String key);
    @Method(selector = "mutableSetValueForKey:")
    public native NSMutableSet<?> getMutableSetValueForKey(String key);
    @Method(selector = "valueForKeyPath:")
    public native NSObject getValueForKeyPath(String keyPath);
    @Method(selector = "setValue:forKeyPath:")
    public native void setValueForKeyPath(NSObject value, String keyPath);
    @Method(selector = "validateValue:forKeyPath:error:")
    public native boolean validateValueForKeyPath(NSObject ioValue, String inKeyPath, NSError.NSErrorPtr outError);
    @Method(selector = "mutableArrayValueForKeyPath:")
    public native NSMutableArray<?> getMutableArrayValueForKeyPath(String keyPath);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "mutableOrderedSetValueForKeyPath:")
    public native NSMutableOrderedSet<?> getMutableOrderedSetValueForKeyPath(String keyPath);
    @Method(selector = "mutableSetValueForKeyPath:")
    public native NSMutableSet<?> getMutableSetValueForKeyPath(String keyPath);
    @Method(selector = "valueForUndefinedKey:")
    public native NSObject getValueForUndefinedKey(String key);
    @Method(selector = "setValue:forUndefinedKey:")
    public native void setValueForUndefinedKey(NSObject value, String key);
    @Method(selector = "setNilValueForKey:")
    public native void setNullValueForKey(String key);
    @Method(selector = "dictionaryWithValuesForKeys:")
    public native NSDictionary<?, ?> getDictionaryWithValuesForKeys(NSArray<?> keys);
    @Method(selector = "setValuesForKeysWithDictionary:")
    public native void setValuesForKeys(NSDictionary<?, ?> keyedValues);
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
    public native void willChangeValues(NSKeyValueChange changeKind, NSIndexSet indexes, String key);
    @Method(selector = "didChange:valuesAtIndexes:forKey:")
    public native void didChangeValues(NSKeyValueChange changeKind, NSIndexSet indexes, String key);
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
