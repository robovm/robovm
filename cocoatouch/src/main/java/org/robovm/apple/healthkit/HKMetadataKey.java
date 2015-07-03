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
@Marshaler(/*<name>*/HKMetadataKey/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HKMetadataKey/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/HKMetadataKey/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static HKMetadataKey toObject(Class<HKMetadataKey> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return HKMetadataKey.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(HKMetadataKey o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<HKMetadataKey> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<HKMetadataKey> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(HKMetadataKey.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<HKMetadataKey> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (HKMetadataKey o : l) {
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
    public static final HKMetadataKey DeviceSerialNumber = new HKMetadataKey("DeviceSerialNumber");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey BodyTemperatureSensorLocation = new HKMetadataKey("BodyTemperatureSensorLocation");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey HeartRateSensorLocation = new HKMetadataKey("HeartRateSensorLocation");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey FoodType = new HKMetadataKey("FoodType");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey UDIDeviceIdentifier = new HKMetadataKey("UDIDeviceIdentifier");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey UDIProductionIdentifier = new HKMetadataKey("UDIProductionIdentifier");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey DigitalSignature = new HKMetadataKey("DigitalSignature");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey ExternalUUID = new HKMetadataKey("ExternalUUID");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey TimeZone = new HKMetadataKey("TimeZone");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey DeviceName = new HKMetadataKey("DeviceName");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey DeviceManufacturerName = new HKMetadataKey("DeviceManufacturerName");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey WasTakenInLab = new HKMetadataKey("WasTakenInLab");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey ReferenceRangeLowerLimit = new HKMetadataKey("ReferenceRangeLowerLimit");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey ReferenceRangeUpperLimit = new HKMetadataKey("ReferenceRangeUpperLimit");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey WasUserEntered = new HKMetadataKey("WasUserEntered");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey WorkoutBrandName = new HKMetadataKey("WorkoutBrandName");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey GroupFitness = new HKMetadataKey("GroupFitness");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey IndoorWorkout = new HKMetadataKey("IndoorWorkout");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey CoachedWorkout = new HKMetadataKey("CoachedWorkout");
    /*</constants>*/
    
    private static /*<name>*/HKMetadataKey/*</name>*/[] values = new /*<name>*/HKMetadataKey/*</name>*/[] {/*<value_list>*/DeviceSerialNumber, BodyTemperatureSensorLocation, HeartRateSensorLocation, FoodType, UDIDeviceIdentifier, UDIProductionIdentifier, DigitalSignature, ExternalUUID, TimeZone, DeviceName, DeviceManufacturerName, WasTakenInLab, ReferenceRangeLowerLimit, ReferenceRangeUpperLimit, WasUserEntered, WorkoutBrandName, GroupFitness, IndoorWorkout, CoachedWorkout/*</value_list>*/};
    
    /*<name>*/HKMetadataKey/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/HKMetadataKey/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/HKMetadataKey/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/HKMetadataKey/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("HealthKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKMetadataKeyDeviceSerialNumber", optional=true)
        public static native NSString DeviceSerialNumber();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKMetadataKeyBodyTemperatureSensorLocation", optional=true)
        public static native NSString BodyTemperatureSensorLocation();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKMetadataKeyHeartRateSensorLocation", optional=true)
        public static native NSString HeartRateSensorLocation();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKMetadataKeyFoodType", optional=true)
        public static native NSString FoodType();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKMetadataKeyUDIDeviceIdentifier", optional=true)
        public static native NSString UDIDeviceIdentifier();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKMetadataKeyUDIProductionIdentifier", optional=true)
        public static native NSString UDIProductionIdentifier();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKMetadataKeyDigitalSignature", optional=true)
        public static native NSString DigitalSignature();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKMetadataKeyExternalUUID", optional=true)
        public static native NSString ExternalUUID();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKMetadataKeyTimeZone", optional=true)
        public static native NSString TimeZone();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKMetadataKeyDeviceName", optional=true)
        public static native NSString DeviceName();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKMetadataKeyDeviceManufacturerName", optional=true)
        public static native NSString DeviceManufacturerName();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKMetadataKeyWasTakenInLab", optional=true)
        public static native NSString WasTakenInLab();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKMetadataKeyReferenceRangeLowerLimit", optional=true)
        public static native NSString ReferenceRangeLowerLimit();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKMetadataKeyReferenceRangeUpperLimit", optional=true)
        public static native NSString ReferenceRangeUpperLimit();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKMetadataKeyWasUserEntered", optional=true)
        public static native NSString WasUserEntered();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKMetadataKeyWorkoutBrandName", optional=true)
        public static native NSString WorkoutBrandName();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKMetadataKeyGroupFitness", optional=true)
        public static native NSString GroupFitness();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKMetadataKeyIndoorWorkout", optional=true)
        public static native NSString IndoorWorkout();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKMetadataKeyCoachedWorkout", optional=true)
        public static native NSString CoachedWorkout();
        /*</values>*/
    }
}
