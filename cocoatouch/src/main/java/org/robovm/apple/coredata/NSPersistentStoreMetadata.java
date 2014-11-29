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
@Marshaler(NSPersistentStoreMetadata.Marshaler.class)
/*<annotations>*/@Library("CoreData")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSPersistentStoreMetadata/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @SuppressWarnings("unchecked")
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

    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected NSPersistentStoreMetadata(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public NSPersistentStoreMetadata() {
    	this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(NSPersistentStoreMetadata.class); }/*</bind>*/
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
    public String getType() {
        if (data.containsKey(TypeValue())) {
            NSString val = (NSString)data.get(TypeValue());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSPersistentStoreMetadata setType(NSPersistentStoreType type) {
        data.put(TypeValue(), new NSString(type.value()));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSPersistentStoreMetadata setType(String type) {
        data.put(TypeValue(), new NSString(type));
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSUUID getUUID() {
        if (data.containsKey(UUIDValue())) {
            NSUUID val = (NSUUID)data.get(UUIDValue());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSPersistentStoreMetadata setUUID(NSUUID uuid) {
        data.put(UUIDValue(), uuid);
        return this;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSDictionary<?, ?> getModelVersionHashes() {
        if (data.containsKey(ModelVersionHashesValue())) {
            NSDictionary<?, ?> val = (NSDictionary<?, ?>)data.get(ModelVersionHashesValue());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public NSSet<?> getModelVersionIdentifiers() {
        if (data.containsKey(ModelVersionIdentifiersValue())) {
            NSSet<?> val = (NSSet<?>)data.get(ModelVersionIdentifiersValue());
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public long getOSCompatibility() {
        if (data.containsKey(OSCompatibilityValue())) {
            NSNumber val = (NSNumber)data.get(OSCompatibilityValue());
            return val.longValue();
        }
        return 0;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSStoreTypeKey", optional=true)
    protected static native NSString TypeValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSStoreUUIDKey", optional=true)
    protected static native NSString UUIDValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSStoreModelVersionHashesKey", optional=true)
    protected static native NSString ModelVersionHashesValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSStoreModelVersionIdentifiersKey", optional=true)
    protected static native NSString ModelVersionIdentifiersValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSPersistentStoreOSCompatibility", optional=true)
    protected static native NSString OSCompatibilityValue();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
