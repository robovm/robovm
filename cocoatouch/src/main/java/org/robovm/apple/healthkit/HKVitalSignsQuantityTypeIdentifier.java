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
@Marshaler(/*<name>*/HKVitalSignsQuantityTypeIdentifier/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HKVitalSignsQuantityTypeIdentifier/*</name>*/ 
    extends /*<extends>*/HKQuantityTypeIdentifier/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/HKVitalSignsQuantityTypeIdentifier/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static HKVitalSignsQuantityTypeIdentifier toObject(Class<HKVitalSignsQuantityTypeIdentifier> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return HKVitalSignsQuantityTypeIdentifier.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(HKVitalSignsQuantityTypeIdentifier o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<HKVitalSignsQuantityTypeIdentifier> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<HKVitalSignsQuantityTypeIdentifier> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(HKVitalSignsQuantityTypeIdentifier.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<HKVitalSignsQuantityTypeIdentifier> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (HKVitalSignsQuantityTypeIdentifier i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKVitalSignsQuantityTypeIdentifier HeartRate = new HKVitalSignsQuantityTypeIdentifier("HeartRate");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKVitalSignsQuantityTypeIdentifier BodyTemperature = new HKVitalSignsQuantityTypeIdentifier("BodyTemperature");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKVitalSignsQuantityTypeIdentifier BloodPressureSystolic = new HKVitalSignsQuantityTypeIdentifier("BloodPressureSystolic");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKVitalSignsQuantityTypeIdentifier BloodPressureDiastolic = new HKVitalSignsQuantityTypeIdentifier("BloodPressureDiastolic");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKVitalSignsQuantityTypeIdentifier RespiratoryRate = new HKVitalSignsQuantityTypeIdentifier("RespiratoryRate");
    /*</constants>*/
    
    private static /*<name>*/HKVitalSignsQuantityTypeIdentifier/*</name>*/[] values = new /*<name>*/HKVitalSignsQuantityTypeIdentifier/*</name>*/[] {/*<value_list>*/HeartRate, BodyTemperature, BloodPressureSystolic, BloodPressureDiastolic, RespiratoryRate/*</value_list>*/};
    
    /*<name>*/HKVitalSignsQuantityTypeIdentifier/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/HKVitalSignsQuantityTypeIdentifier/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/HKVitalSignsQuantityTypeIdentifier/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/HKVitalSignsQuantityTypeIdentifier/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("HealthKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierHeartRate", optional=true)
        public static native NSString HeartRate();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierBodyTemperature", optional=true)
        public static native NSString BodyTemperature();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierBloodPressureSystolic", optional=true)
        public static native NSString BloodPressureSystolic();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierBloodPressureDiastolic", optional=true)
        public static native NSString BloodPressureDiastolic();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierRespiratoryRate", optional=true)
        public static native NSString RespiratoryRate();
        /*</values>*/
    }
}
