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
/*<annotations>*/@Library("CoreData") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/NSExpressionMigrationKey/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSExpressionMigrationKey/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSExpressionMigrationKey/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSExpressionMigrationKey toObject(Class<NSExpressionMigrationKey> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSExpressionMigrationKey.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSExpressionMigrationKey o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSExpressionMigrationKey> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSExpressionMigrationKey> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSExpressionMigrationKey.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSExpressionMigrationKey> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSExpressionMigrationKey o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSExpressionMigrationKey ManagerKey = new NSExpressionMigrationKey("ManagerKey");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSExpressionMigrationKey SourceObjectKey = new NSExpressionMigrationKey("SourceObjectKey");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSExpressionMigrationKey DestinationObjectKey = new NSExpressionMigrationKey("DestinationObjectKey");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSExpressionMigrationKey EntityMappingKey = new NSExpressionMigrationKey("EntityMappingKey");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSExpressionMigrationKey PropertyMappingKey = new NSExpressionMigrationKey("PropertyMappingKey");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSExpressionMigrationKey EntityPolicyKey = new NSExpressionMigrationKey("EntityPolicyKey");
    /*</constants>*/
    
    private static /*<name>*/NSExpressionMigrationKey/*</name>*/[] values = new /*<name>*/NSExpressionMigrationKey/*</name>*/[] {/*<value_list>*/ManagerKey, SourceObjectKey, DestinationObjectKey, EntityMappingKey, PropertyMappingKey, EntityPolicyKey/*</value_list>*/};
    
    /*<name>*/NSExpressionMigrationKey/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSExpressionMigrationKey/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSExpressionMigrationKey/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSExpressionMigrationKey/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreData") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSMigrationManagerKey", optional=true)
        public static native NSString ManagerKey();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSMigrationSourceObjectKey", optional=true)
        public static native NSString SourceObjectKey();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSMigrationDestinationObjectKey", optional=true)
        public static native NSString DestinationObjectKey();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSMigrationEntityMappingKey", optional=true)
        public static native NSString EntityMappingKey();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSMigrationPropertyMappingKey", optional=true)
        public static native NSString PropertyMappingKey();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSMigrationEntityPolicyKey", optional=true)
        public static native NSString EntityPolicyKey();
        /*</values>*/
    }
}
