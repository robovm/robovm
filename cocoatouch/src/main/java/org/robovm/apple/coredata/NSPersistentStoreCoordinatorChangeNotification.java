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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreData")/*</annotations>*/
@Marshaler(/*<name>*/NSPersistentStoreCoordinatorChangeNotification/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSPersistentStoreCoordinatorChangeNotification/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSPersistentStoreCoordinatorChangeNotification toObject(Class<NSPersistentStoreCoordinatorChangeNotification> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSPersistentStoreCoordinatorChangeNotification(o);
        }
        @MarshalsPointer
        public static long toNative(NSPersistentStoreCoordinatorChangeNotification o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<NSPersistentStoreCoordinatorChangeNotification> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSPersistentStoreCoordinatorChangeNotification> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new NSPersistentStoreCoordinatorChangeNotification(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSPersistentStoreCoordinatorChangeNotification> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (NSPersistentStoreCoordinatorChangeNotification i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    NSPersistentStoreCoordinatorChangeNotification(NSDictionary data) {
        super(data);
    }
    /*</constructors>*/

    /*<methods>*/
    public boolean has(NSString key) {
        return data.containsKey(key);
    }
    public NSObject get(NSString key) {
        if (has(key)) {
            return data.get(key);
        }
        return null;
    }
    

    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSArray<NSPersistentStore> getAddedStores() {
        if (has(Keys.Added())) {
            NSArray<NSPersistentStore> val = (NSArray<NSPersistentStore>) get(Keys.Added());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSArray<NSPersistentStore> getRemovedStores() {
        if (has(Keys.Removed())) {
            NSArray<NSPersistentStore> val = (NSArray<NSPersistentStore>) get(Keys.Removed());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSArray<NSPersistentStore> getUUIDChangedStores() {
        if (has(Keys.UUIDChanged())) {
            NSArray<NSPersistentStore> val = (NSArray<NSPersistentStore>) get(Keys.UUIDChanged());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSPersistentStoreUbiquitousTransitionType getUbiquitousTransitionType() {
        if (has(Keys.UbiquitousTransitionType())) {
            NSNumber val = (NSNumber) get(Keys.UbiquitousTransitionType());
            return NSPersistentStoreUbiquitousTransitionType.valueOf(val.longValue());
        }
        return null;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CoreData")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSAddedPersistentStoresKey", optional=true)
        public static native NSString Added();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSRemovedPersistentStoresKey", optional=true)
        public static native NSString Removed();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSUUIDChangedPersistentStoresKey", optional=true)
        public static native NSString UUIDChanged();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="NSPersistentStoreUbiquitousTransitionTypeKey", optional=true)
        public static native NSString UbiquitousTransitionType();
    }
    /*</keys>*/
}
