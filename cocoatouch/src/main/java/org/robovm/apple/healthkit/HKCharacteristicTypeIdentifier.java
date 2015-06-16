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
package org.robovm.apple.healthkit;

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
/*<annotations>*/@Library("HealthKit") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/HKCharacteristicTypeIdentifier/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HKCharacteristicTypeIdentifier/*</name>*/ 
    extends /*<extends>*/HKTypeIdentifier/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/HKCharacteristicTypeIdentifier/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static HKCharacteristicTypeIdentifier toObject(Class<HKCharacteristicTypeIdentifier> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return HKCharacteristicTypeIdentifier.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(HKCharacteristicTypeIdentifier o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<HKCharacteristicTypeIdentifier> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<HKCharacteristicTypeIdentifier> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(HKCharacteristicTypeIdentifier.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<HKCharacteristicTypeIdentifier> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (HKCharacteristicTypeIdentifier o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKCharacteristicTypeIdentifier BiologicalSex = new HKCharacteristicTypeIdentifier("BiologicalSex");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKCharacteristicTypeIdentifier BloodType = new HKCharacteristicTypeIdentifier("BloodType");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKCharacteristicTypeIdentifier DateOfBirth = new HKCharacteristicTypeIdentifier("DateOfBirth");
    /*</constants>*/
    
    private static /*<name>*/HKCharacteristicTypeIdentifier/*</name>*/[] values = new /*<name>*/HKCharacteristicTypeIdentifier/*</name>*/[] {/*<value_list>*/BiologicalSex, BloodType, DateOfBirth/*</value_list>*/};
    
    /*<name>*/HKCharacteristicTypeIdentifier/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/HKCharacteristicTypeIdentifier/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/HKCharacteristicTypeIdentifier/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/HKCharacteristicTypeIdentifier/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("HealthKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKCharacteristicTypeIdentifierBiologicalSex", optional=true)
        public static native NSString BiologicalSex();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKCharacteristicTypeIdentifierBloodType", optional=true)
        public static native NSString BloodType();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKCharacteristicTypeIdentifierDateOfBirth", optional=true)
        public static native NSString DateOfBirth();
        /*</values>*/
    }
}
