/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.apple.coredata;

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
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(NSPersistentStoreCoordinatorChangeNotificationInfo.Marshaler.class)
/*<annotations>*/@Library("CoreData")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class NSPersistentStoreCoordinatorChangeNotificationInfo 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static NSPersistentStoreCoordinatorChangeNotificationInfo toObject(Class<NSPersistentStoreCoordinatorChangeNotificationInfo> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSPersistentStoreCoordinatorChangeNotificationInfo(o);
        }
        @MarshalsPointer
        public static long toNative(NSPersistentStoreCoordinatorChangeNotificationInfo o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected NSPersistentStoreCoordinatorChangeNotificationInfo(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(NSPersistentStoreCoordinatorChangeNotificationInfo.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    
    /**
     * @since Available in iOS 3.0 and later.
     */
    @SuppressWarnings("unchecked")
    public NSArray<NSPersistentStore> getAddedStores() {
        if (data.containsKey(AddedKey())) {
            NSArray<NSPersistentStore> val = (NSArray<NSPersistentStore>)data.get(AddedKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    @SuppressWarnings("unchecked")
    public NSArray<NSPersistentStore> getRemovedStores() {
        if (data.containsKey(RemovedKey())) {
            NSArray<NSPersistentStore> val = (NSArray<NSPersistentStore>)data.get(RemovedKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    @SuppressWarnings("unchecked")
    public NSArray<NSPersistentStore> getUUIDChangedStores() {
        if (data.containsKey(UUIDChangedKey())) {
            NSArray<NSPersistentStore> val = (NSArray<NSPersistentStore>)data.get(UUIDChangedKey());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSPersistentStoreUbiquitousTransitionType getUbiquitousTransitionType() {
        if (data.containsKey(UbiquitousTransitionTypeKey())) {
            NSNumber val = (NSNumber)data.get(UbiquitousTransitionTypeKey());
            return NSPersistentStoreUbiquitousTransitionType.valueOf(val.longValue());
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSAddedPersistentStoresKey", optional=true)
    protected static native NSString AddedKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSRemovedPersistentStoresKey", optional=true)
    protected static native NSString RemovedKey();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSUUIDChangedPersistentStoresKey", optional=true)
    protected static native NSString UUIDChangedKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreUbiquitousTransitionTypeKey", optional=true)
    protected static native NSString UbiquitousTransitionTypeKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
