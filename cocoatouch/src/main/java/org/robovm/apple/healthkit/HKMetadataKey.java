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
/*<annotations>*/@Library("HealthKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HKMetadataKey/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(HKMetadataKey.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey DeviceSerialNumber = new HKMetadataKey("DeviceSerialNumberKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey BodyTemperatureSensorLocation = new HKMetadataKey("BodyTemperatureSensorLocationKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey HeartRateSensorLocation = new HKMetadataKey("HeartRateSensorLocationKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey FoodType = new HKMetadataKey("FoodTypeKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey UDIDeviceIdentifier = new HKMetadataKey("UDIDeviceIdentifierKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey UDIProductionIdentifier = new HKMetadataKey("UDIProductionIdentifierKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey DigitalSignature = new HKMetadataKey("DigitalSignatureKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey ExternalUUID = new HKMetadataKey("ExternalUUIDKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey TimeZone = new HKMetadataKey("TimeZoneKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey DeviceName = new HKMetadataKey("DeviceNameKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey DeviceManufacturerName = new HKMetadataKey("DeviceManufacturerNameKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey WasTakenInLab = new HKMetadataKey("WasTakenInLabKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey ReferenceRangeLowerLimit = new HKMetadataKey("ReferenceRangeLowerLimitKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey ReferenceRangeUpperLimit = new HKMetadataKey("ReferenceRangeUpperLimitKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey WasUserEntered = new HKMetadataKey("WasUserEnteredKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey WorkoutBrandName = new HKMetadataKey("WorkoutBrandNameKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey GroupFitness = new HKMetadataKey("GroupFitnessKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey IndoorWorkout = new HKMetadataKey("IndoorWorkoutKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKMetadataKey CoachedWorkout = new HKMetadataKey("CoachedWorkoutKey");
    
    private static HKMetadataKey[] values = new HKMetadataKey[] {DeviceSerialNumber, BodyTemperatureSensorLocation, HeartRateSensorLocation, FoodType, 
        UDIDeviceIdentifier, UDIProductionIdentifier, DigitalSignature, ExternalUUID, TimeZone, DeviceName, DeviceManufacturerName, WasTakenInLab, ReferenceRangeLowerLimit, 
        ReferenceRangeUpperLimit, WasUserEntered, WorkoutBrandName, GroupFitness, IndoorWorkout, CoachedWorkout};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private HKMetadataKey(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static HKMetadataKey valueOf(NSString value) {
        for (HKMetadataKey v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/HKMetadataKey/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKMetadataKeyDeviceSerialNumber", optional=true)
    protected static native NSString DeviceSerialNumberKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKMetadataKeyBodyTemperatureSensorLocation", optional=true)
    protected static native NSString BodyTemperatureSensorLocationKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKMetadataKeyHeartRateSensorLocation", optional=true)
    protected static native NSString HeartRateSensorLocationKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKMetadataKeyFoodType", optional=true)
    protected static native NSString FoodTypeKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKMetadataKeyUDIDeviceIdentifier", optional=true)
    protected static native NSString UDIDeviceIdentifierKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKMetadataKeyUDIProductionIdentifier", optional=true)
    protected static native NSString UDIProductionIdentifierKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKMetadataKeyDigitalSignature", optional=true)
    protected static native NSString DigitalSignatureKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKMetadataKeyExternalUUID", optional=true)
    protected static native NSString ExternalUUIDKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKMetadataKeyTimeZone", optional=true)
    protected static native NSString TimeZoneKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKMetadataKeyDeviceName", optional=true)
    protected static native NSString DeviceNameKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKMetadataKeyDeviceManufacturerName", optional=true)
    protected static native NSString DeviceManufacturerNameKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKMetadataKeyWasTakenInLab", optional=true)
    protected static native NSString WasTakenInLabKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKMetadataKeyReferenceRangeLowerLimit", optional=true)
    protected static native NSString ReferenceRangeLowerLimitKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKMetadataKeyReferenceRangeUpperLimit", optional=true)
    protected static native NSString ReferenceRangeUpperLimitKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKMetadataKeyWasUserEntered", optional=true)
    protected static native NSString WasUserEnteredKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKMetadataKeyWorkoutBrandName", optional=true)
    protected static native NSString WorkoutBrandNameKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKMetadataKeyGroupFitness", optional=true)
    protected static native NSString GroupFitnessKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKMetadataKeyIndoorWorkout", optional=true)
    protected static native NSString IndoorWorkoutKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKMetadataKeyCoachedWorkout", optional=true)
    protected static native NSString CoachedWorkoutKey();
    /*</methods>*/
}
