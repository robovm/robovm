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
@Marshaler(NSExpressionMigrationKey.Marshaler.class)
/*<annotations>*/@Library("CoreData")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSExpressionMigrationKey/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static NSExpressionMigrationKey toObject(Class<NSExpressionMigrationKey> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSExpressionMigrationKey.valueOf(o.toString());
        }
        @MarshalsPointer
        public static long toNative(NSExpressionMigrationKey o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(new NSString(o.value()), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSExpressionMigrationKey.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSExpressionMigrationKey Manager = new NSExpressionMigrationKey("ManagerKeyValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSExpressionMigrationKey SourceObject = new NSExpressionMigrationKey("SourceObjectKeyValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSExpressionMigrationKey DestinationObject = new NSExpressionMigrationKey("DestinationObjectKeyValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSExpressionMigrationKey EntityMapping = new NSExpressionMigrationKey("EntityMappingKeyValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSExpressionMigrationKey PropertyMapping = new NSExpressionMigrationKey("PropertyMappingKeyValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSExpressionMigrationKey EntityPolicy = new NSExpressionMigrationKey("EntityPolicyKeyValue");
    
    private static NSExpressionMigrationKey[] values = new NSExpressionMigrationKey[] {Manager, SourceObject, DestinationObject, EntityMapping,
        PropertyMapping, EntityPolicy};
    private final LazyGlobalValue<String> lazyGlobalValue;
    
    private NSExpressionMigrationKey(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public String value() {
        return lazyGlobalValue.value();
    }
    
    public static NSExpressionMigrationKey valueOf (String value) {
        for (NSExpressionMigrationKey v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSExpressionMigrationKey/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigrationManagerKey", optional=true)
    protected static native String ManagerKeyValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigrationSourceObjectKey", optional=true)
    protected static native String SourceObjectKeyValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigrationDestinationObjectKey", optional=true)
    protected static native String DestinationObjectKeyValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigrationEntityMappingKey", optional=true)
    protected static native String EntityMappingKeyValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigrationPropertyMappingKey", optional=true)
    protected static native String PropertyMappingKeyValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSMigrationEntityPolicyKey", optional=true)
    protected static native String EntityPolicyKeyValue();
    /*</methods>*/
}
