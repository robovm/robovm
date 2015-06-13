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
@Marshaler(/*<name>*/NSManagedObjectContextNotification/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSManagedObjectContextNotification/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSManagedObjectContextNotification toObject(Class<NSManagedObjectContextNotification> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSManagedObjectContextNotification(o);
        }
        @MarshalsPointer
        public static long toNative(NSManagedObjectContextNotification o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<NSManagedObjectContextNotification> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSManagedObjectContextNotification> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new NSManagedObjectContextNotification(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSManagedObjectContextNotification> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (NSManagedObjectContextNotification i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    NSManagedObjectContextNotification(NSDictionary<NSString, NSObject> data) {
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
    public NSSet<NSManagedObject> getInsertedObjects() {
        if (has(Keys.InsertedObjects())) {
            NSSet<NSManagedObject> val = (NSSet<NSManagedObject>) get(Keys.InsertedObjects());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSSet<NSManagedObject> getUpdatedObjects() {
        if (has(Keys.UpdatedObjects())) {
            NSSet<NSManagedObject> val = (NSSet<NSManagedObject>) get(Keys.UpdatedObjects());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSSet<NSManagedObject> getDeletedObjects() {
        if (has(Keys.DeletedObjects())) {
            NSSet<NSManagedObject> val = (NSSet<NSManagedObject>) get(Keys.DeletedObjects());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSSet<NSManagedObject> getRefreshedObjects() {
        if (has(Keys.RefreshedObjects())) {
            NSSet<NSManagedObject> val = (NSSet<NSManagedObject>) get(Keys.RefreshedObjects());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSSet<NSManagedObject> getInvalidatedObjects() {
        if (has(Keys.InvalidatedObjects())) {
            NSSet<NSManagedObject> val = (NSSet<NSManagedObject>) get(Keys.InvalidatedObjects());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSArray<NSManagedObjectID> getAllInvalidatedObjects() {
        if (has(Keys.InvalidatedAllObjects())) {
            NSArray<NSManagedObjectID> val = (NSArray<NSManagedObjectID>) get(Keys.InvalidatedAllObjects());
            return val;
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
        @GlobalValue(symbol="NSInsertedObjectsKey", optional=true)
        public static native NSString InsertedObjects();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSUpdatedObjectsKey", optional=true)
        public static native NSString UpdatedObjects();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSDeletedObjectsKey", optional=true)
        public static native NSString DeletedObjects();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSRefreshedObjectsKey", optional=true)
        public static native NSString RefreshedObjects();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSInvalidatedObjectsKey", optional=true)
        public static native NSString InvalidatedObjects();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSInvalidatedAllObjectsKey", optional=true)
        public static native NSString InvalidatedAllObjects();
    }
    /*</keys>*/
}
