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
package org.robovm.apple.homekit;

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
@Marshaler(HMCharacteristicType.Marshaler.class)
/*<annotations>*/@Library("HomeKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HMCharacteristicType/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static HMCharacteristicType toObject(Class<HMCharacteristicType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return HMCharacteristicType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(HMCharacteristicType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(HMCharacteristicType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType PowerState = new HMCharacteristicType("PowerStateValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType Hue = new HMCharacteristicType("HueValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType Saturation = new HMCharacteristicType("SaturationValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType Brightness = new HMCharacteristicType("BrightnessValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType TemperatureUnits = new HMCharacteristicType("TemperatureUnitsValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType CurrentTemperature = new HMCharacteristicType("CurrentTemperatureValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType TargetTemperature = new HMCharacteristicType("TargetTemperatureValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType CurrentHeatingCooling = new HMCharacteristicType("CurrentHeatingCoolingValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType TargetHeatingCooling = new HMCharacteristicType("TargetHeatingCoolingValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType CoolingThreshold = new HMCharacteristicType("CoolingThresholdValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType HeatingThreshold = new HMCharacteristicType("HeatingThresholdValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType CurrentRelativeHumidity = new HMCharacteristicType("CurrentRelativeHumidityValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType TargetRelativeHumidity = new HMCharacteristicType("TargetRelativeHumidityValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType CurrentDoorState = new HMCharacteristicType("CurrentDoorStateValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType TargetDoorState = new HMCharacteristicType("TargetDoorStateValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType ObstructionDetected = new HMCharacteristicType("ObstructionDetectedValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType Name = new HMCharacteristicType("NameValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType Manufacturer = new HMCharacteristicType("ManufacturerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType Model = new HMCharacteristicType("ModelValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType SerialNumber = new HMCharacteristicType("SerialNumberValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType Identify = new HMCharacteristicType("IdentifyValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType RotationDirection = new HMCharacteristicType("RotationDirectionValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType RotationSpeed = new HMCharacteristicType("RotationSpeedValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType OutletInUse = new HMCharacteristicType("OutletInUseValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType Version = new HMCharacteristicType("VersionValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType Logs = new HMCharacteristicType("LogsValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType AudioFeedback = new HMCharacteristicType("AudioFeedbackValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType AdminOnlyAccess = new HMCharacteristicType("AdminOnlyAccessValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType MotionDetected = new HMCharacteristicType("MotionDetectedValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType CurrentLockMechanismState = new HMCharacteristicType("CurrentLockMechanismStateValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType TargetLockMechanismState = new HMCharacteristicType("TargetLockMechanismStateValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType LockMechanismLastKnownAction = new HMCharacteristicType("LockMechanismLastKnownActionValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType LockManagementControlPoint = new HMCharacteristicType("LockManagementControlPointValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType LockManagementAutoSecureTimeout = new HMCharacteristicType("LockManagementAutoSecureTimeoutValue");
    
    private static HMCharacteristicType[] values = new HMCharacteristicType[] {PowerState, Hue, Saturation, Brightness, TemperatureUnits, CurrentTemperature, 
        TargetTemperature, CurrentHeatingCooling, TargetHeatingCooling, CoolingThreshold, HeatingThreshold, CurrentRelativeHumidity, TargetRelativeHumidity, 
        CurrentDoorState, TargetDoorState, ObstructionDetected, Name, Manufacturer, Model, SerialNumber, Identify, RotationDirection, RotationSpeed, OutletInUse, 
        Version, Logs, AudioFeedback, AdminOnlyAccess, MotionDetected, CurrentLockMechanismState, TargetLockMechanismState, LockMechanismLastKnownAction, 
        LockManagementControlPoint, LockManagementAutoSecureTimeout};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private HMCharacteristicType(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static HMCharacteristicType valueOf(NSString value) {
        for (HMCharacteristicType v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/HMCharacteristicType/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypePowerState", optional=true)
    protected static native NSString PowerStateValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeHue", optional=true)
    protected static native NSString HueValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeSaturation", optional=true)
    protected static native NSString SaturationValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeBrightness", optional=true)
    protected static native NSString BrightnessValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeTemperatureUnits", optional=true)
    protected static native NSString TemperatureUnitsValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeCurrentTemperature", optional=true)
    protected static native NSString CurrentTemperatureValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeTargetTemperature", optional=true)
    protected static native NSString TargetTemperatureValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeCurrentHeatingCooling", optional=true)
    protected static native NSString CurrentHeatingCoolingValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeTargetHeatingCooling", optional=true)
    protected static native NSString TargetHeatingCoolingValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeCoolingThreshold", optional=true)
    protected static native NSString CoolingThresholdValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeHeatingThreshold", optional=true)
    protected static native NSString HeatingThresholdValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeCurrentRelativeHumidity", optional=true)
    protected static native NSString CurrentRelativeHumidityValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeTargetRelativeHumidity", optional=true)
    protected static native NSString TargetRelativeHumidityValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeCurrentDoorState", optional=true)
    protected static native NSString CurrentDoorStateValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeTargetDoorState", optional=true)
    protected static native NSString TargetDoorStateValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeObstructionDetected", optional=true)
    protected static native NSString ObstructionDetectedValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeName", optional=true)
    protected static native NSString NameValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeManufacturer", optional=true)
    protected static native NSString ManufacturerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeModel", optional=true)
    protected static native NSString ModelValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeSerialNumber", optional=true)
    protected static native NSString SerialNumberValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeIdentify", optional=true)
    protected static native NSString IdentifyValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeRotationDirection", optional=true)
    protected static native NSString RotationDirectionValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeRotationSpeed", optional=true)
    protected static native NSString RotationSpeedValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeOutletInUse", optional=true)
    protected static native NSString OutletInUseValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeVersion", optional=true)
    protected static native NSString VersionValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeLogs", optional=true)
    protected static native NSString LogsValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeAudioFeedback", optional=true)
    protected static native NSString AudioFeedbackValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeAdminOnlyAccess", optional=true)
    protected static native NSString AdminOnlyAccessValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeMotionDetected", optional=true)
    protected static native NSString MotionDetectedValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeCurrentLockMechanismState", optional=true)
    protected static native NSString CurrentLockMechanismStateValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeTargetLockMechanismState", optional=true)
    protected static native NSString TargetLockMechanismStateValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeLockMechanismLastKnownAction", optional=true)
    protected static native NSString LockMechanismLastKnownActionValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeLockManagementControlPoint", optional=true)
    protected static native NSString LockManagementControlPointValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicTypeLockManagementAutoSecureTimeout", optional=true)
    protected static native NSString LockManagementAutoSecureTimeoutValue();
    /*</methods>*/
}
