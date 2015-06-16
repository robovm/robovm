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
package org.robovm.apple.homekit;

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
/*<annotations>*/@Library("HomeKit") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/HMCharacteristicType/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HMCharacteristicType/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/HMCharacteristicType/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<HMCharacteristicType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<HMCharacteristicType> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(HMCharacteristicType.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<HMCharacteristicType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (HMCharacteristicType o : l) {
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
    public static final HMCharacteristicType PowerState = new HMCharacteristicType("PowerState");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType Hue = new HMCharacteristicType("Hue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType Saturation = new HMCharacteristicType("Saturation");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType Brightness = new HMCharacteristicType("Brightness");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType TemperatureUnits = new HMCharacteristicType("TemperatureUnits");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType CurrentTemperature = new HMCharacteristicType("CurrentTemperature");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType TargetTemperature = new HMCharacteristicType("TargetTemperature");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType CurrentHeatingCooling = new HMCharacteristicType("CurrentHeatingCooling");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType TargetHeatingCooling = new HMCharacteristicType("TargetHeatingCooling");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType CoolingThreshold = new HMCharacteristicType("CoolingThreshold");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType HeatingThreshold = new HMCharacteristicType("HeatingThreshold");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType CurrentRelativeHumidity = new HMCharacteristicType("CurrentRelativeHumidity");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType TargetRelativeHumidity = new HMCharacteristicType("TargetRelativeHumidity");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType CurrentDoorState = new HMCharacteristicType("CurrentDoorState");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType TargetDoorState = new HMCharacteristicType("TargetDoorState");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType ObstructionDetected = new HMCharacteristicType("ObstructionDetected");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType Name = new HMCharacteristicType("Name");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType Manufacturer = new HMCharacteristicType("Manufacturer");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType Model = new HMCharacteristicType("Model");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType SerialNumber = new HMCharacteristicType("SerialNumber");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType Identify = new HMCharacteristicType("Identify");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType RotationDirection = new HMCharacteristicType("RotationDirection");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType RotationSpeed = new HMCharacteristicType("RotationSpeed");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType OutletInUse = new HMCharacteristicType("OutletInUse");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType Version = new HMCharacteristicType("Version");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType Logs = new HMCharacteristicType("Logs");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType AudioFeedback = new HMCharacteristicType("AudioFeedback");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType AdminOnlyAccess = new HMCharacteristicType("AdminOnlyAccess");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType MotionDetected = new HMCharacteristicType("MotionDetected");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType CurrentLockMechanismState = new HMCharacteristicType("CurrentLockMechanismState");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType TargetLockMechanismState = new HMCharacteristicType("TargetLockMechanismState");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType LockMechanismLastKnownAction = new HMCharacteristicType("LockMechanismLastKnownAction");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType LockManagementControlPoint = new HMCharacteristicType("LockManagementControlPoint");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicType LockManagementAutoSecureTimeout = new HMCharacteristicType("LockManagementAutoSecureTimeout");
    /*</constants>*/
    
    private static /*<name>*/HMCharacteristicType/*</name>*/[] values = new /*<name>*/HMCharacteristicType/*</name>*/[] {/*<value_list>*/PowerState, Hue, Saturation, Brightness, TemperatureUnits, CurrentTemperature, TargetTemperature, CurrentHeatingCooling, TargetHeatingCooling, CoolingThreshold, HeatingThreshold, CurrentRelativeHumidity, TargetRelativeHumidity, CurrentDoorState, TargetDoorState, ObstructionDetected, Name, Manufacturer, Model, SerialNumber, Identify, RotationDirection, RotationSpeed, OutletInUse, Version, Logs, AudioFeedback, AdminOnlyAccess, MotionDetected, CurrentLockMechanismState, TargetLockMechanismState, LockMechanismLastKnownAction, LockManagementControlPoint, LockManagementAutoSecureTimeout/*</value_list>*/};
    
    /*<name>*/HMCharacteristicType/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/HMCharacteristicType/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/HMCharacteristicType/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/HMCharacteristicType/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("HomeKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypePowerState", optional=true)
        public static native NSString PowerState();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeHue", optional=true)
        public static native NSString Hue();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeSaturation", optional=true)
        public static native NSString Saturation();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeBrightness", optional=true)
        public static native NSString Brightness();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeTemperatureUnits", optional=true)
        public static native NSString TemperatureUnits();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeCurrentTemperature", optional=true)
        public static native NSString CurrentTemperature();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeTargetTemperature", optional=true)
        public static native NSString TargetTemperature();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeCurrentHeatingCooling", optional=true)
        public static native NSString CurrentHeatingCooling();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeTargetHeatingCooling", optional=true)
        public static native NSString TargetHeatingCooling();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeCoolingThreshold", optional=true)
        public static native NSString CoolingThreshold();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeHeatingThreshold", optional=true)
        public static native NSString HeatingThreshold();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeCurrentRelativeHumidity", optional=true)
        public static native NSString CurrentRelativeHumidity();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeTargetRelativeHumidity", optional=true)
        public static native NSString TargetRelativeHumidity();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeCurrentDoorState", optional=true)
        public static native NSString CurrentDoorState();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeTargetDoorState", optional=true)
        public static native NSString TargetDoorState();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeObstructionDetected", optional=true)
        public static native NSString ObstructionDetected();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeName", optional=true)
        public static native NSString Name();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeManufacturer", optional=true)
        public static native NSString Manufacturer();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeModel", optional=true)
        public static native NSString Model();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeSerialNumber", optional=true)
        public static native NSString SerialNumber();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeIdentify", optional=true)
        public static native NSString Identify();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeRotationDirection", optional=true)
        public static native NSString RotationDirection();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeRotationSpeed", optional=true)
        public static native NSString RotationSpeed();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeOutletInUse", optional=true)
        public static native NSString OutletInUse();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeVersion", optional=true)
        public static native NSString Version();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeLogs", optional=true)
        public static native NSString Logs();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeAudioFeedback", optional=true)
        public static native NSString AudioFeedback();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeAdminOnlyAccess", optional=true)
        public static native NSString AdminOnlyAccess();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeMotionDetected", optional=true)
        public static native NSString MotionDetected();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeCurrentLockMechanismState", optional=true)
        public static native NSString CurrentLockMechanismState();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeTargetLockMechanismState", optional=true)
        public static native NSString TargetLockMechanismState();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeLockMechanismLastKnownAction", optional=true)
        public static native NSString LockMechanismLastKnownAction();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeLockManagementControlPoint", optional=true)
        public static native NSString LockManagementControlPoint();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicTypeLockManagementAutoSecureTimeout", optional=true)
        public static native NSString LockManagementAutoSecureTimeout();
        /*</values>*/
    }
}
