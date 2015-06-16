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
@Marshaler(/*<name>*/NSPersistentStoreMetadata/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSPersistentStoreMetadata/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSPersistentStoreMetadata toObject(Class<NSPersistentStoreMetadata> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSPersistentStoreMetadata(o);
        }
        @MarshalsPointer
        public static long toNative(NSPersistentStoreMetadata o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<NSPersistentStoreMetadata> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSPersistentStoreMetadata> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new NSPersistentStoreMetadata(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSPersistentStoreMetadata> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (NSPersistentStoreMetadata i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    NSPersistentStoreMetadata(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    public NSPersistentStoreMetadata() {}
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
    public NSPersistentStoreMetadata set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 3.0 and later.
     */
    public String getType() {
        if (has(Keys.Type())) {
            NSString val = (NSString) get(Keys.Type());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSPersistentStoreMetadata setType(String type) {
        set(Keys.Type(), new NSString(type));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSUUID getUUID() {
        if (has(Keys.UUID())) {
            NSUUID val = (NSUUID) get(Keys.UUID());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSPersistentStoreMetadata setUUID(NSUUID uUID) {
        set(Keys.UUID(), uUID);
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSDictionary<?, ?> getModelVersionHashes() {
        if (has(Keys.ModelVersionHashes())) {
            NSDictionary<?, ?> val = (NSDictionary<?, ?>) get(Keys.ModelVersionHashes());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSSet<?> getModelVersionIdentifiers() {
        if (has(Keys.ModelVersionIdentifiers())) {
            NSSet<?> val = (NSSet<?>) get(Keys.ModelVersionIdentifiers());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public long getOSCompatibility() {
        if (has(Keys.OSCompatibility())) {
            NSNumber val = (NSNumber) get(Keys.OSCompatibility());
            return val.longValue();
        }
        return 0;
    }
    /*</methods>*/
    public NSPersistentStoreMetadata setType(NSPersistentStoreType type) {
        data.put(Keys.Type(), type.value());
        return this;
    }
    
    /*<keys>*/
    @Library("CoreData")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSStoreTypeKey", optional=true)
        public static native NSString Type();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSStoreUUIDKey", optional=true)
        public static native NSString UUID();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSStoreModelVersionHashesKey", optional=true)
        public static native NSString ModelVersionHashes();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSStoreModelVersionIdentifiersKey", optional=true)
        public static native NSString ModelVersionIdentifiers();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSPersistentStoreOSCompatibility", optional=true)
        public static native NSString OSCompatibility();
    }
    /*</keys>*/
}
