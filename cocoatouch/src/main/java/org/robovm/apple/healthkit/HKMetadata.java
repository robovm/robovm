/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
@Marshaler(HKMetadata.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class HKMetadata 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static HKMetadata toObject(Class<HKMetadata> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new HKMetadata(o);
        }
        @MarshalsPointer
        public static long toNative(HKMetadata o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected HKMetadata(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public HKMetadata() {
    	this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(HKMetadata.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    public NSObject get(String property) {
        return data.get(new NSString(property));
    }
    public NSObject get(HKMetadataKey property) {
        return data.get(property.value());
    }
    public boolean contains(String property) {
        return data.containsKey(new NSString(property));
    }
    public boolean contains(HKMetadataKey property) {
        return data.containsKey(property.value());
    }
    public HKMetadata put(String property, NSObject value) {
        data.put(new NSString(property), value);
        return this;
    }
    public HKMetadata put(HKMetadataKey property, NSObject value) {
        data.put(property.value(), value);
        return this;
    }
    
    
    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getDeviceSerialNumber() {
        if (contains(HKMetadataKey.DeviceSerialNumber)) {
            NSString val = (NSString)get(HKMetadataKey.DeviceSerialNumber);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKMetadata setDeviceSerialNumber(String serial) {
        put(HKMetadataKey.DeviceSerialNumber, new NSString(serial));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKBodyTemperatureSensorLocation getBodyTemperatureSensorLocation() {
        if (contains(HKMetadataKey.BodyTemperatureSensorLocation)) {
            NSNumber val = (NSNumber)get(HKMetadataKey.BodyTemperatureSensorLocation);
            return HKBodyTemperatureSensorLocation.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKMetadata setBodyTemperatureSensorLocation(HKBodyTemperatureSensorLocation location) {
        put(HKMetadataKey.BodyTemperatureSensorLocation, NSNumber.valueOf(location.value()));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKHeartRateSensorLocation getHeartRateSensorLocation() {
        if (contains(HKMetadataKey.HeartRateSensorLocation)) {
            NSNumber val = (NSNumber)get(HKMetadataKey.HeartRateSensorLocation);
            return HKHeartRateSensorLocation.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKMetadata setHeartRateSensorLocation(HKHeartRateSensorLocation location) {
        put(HKMetadataKey.HeartRateSensorLocation, NSNumber.valueOf(location.value()));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getFoodType() {
        if (contains(HKMetadataKey.FoodType)) {
            NSString val = (NSString)get(HKMetadataKey.FoodType);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKMetadata setFoodType(String foodType) {
        put(HKMetadataKey.FoodType, new NSString(foodType));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getUDIDeviceIdentifier() {
        if (contains(HKMetadataKey.UDIDeviceIdentifier)) {
            NSString val = (NSString)get(HKMetadataKey.UDIDeviceIdentifier);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKMetadata setUDIDeviceIdentifier(String deviceIdentifier) {
        put(HKMetadataKey.UDIDeviceIdentifier, new NSString(deviceIdentifier));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getUDIProductionIdentifier() {
        if (contains(HKMetadataKey.UDIProductionIdentifier)) {
            NSString val = (NSString)get(HKMetadataKey.UDIProductionIdentifier);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKMetadata setUDIProductionIdentifier(String identifier) {
        put(HKMetadataKey.UDIProductionIdentifier, new NSString(identifier));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getDigitalSignature() {
        if (contains(HKMetadataKey.DigitalSignature)) {
            NSString val = (NSString)get(HKMetadataKey.DigitalSignature);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKMetadata setDigitalSignature(String signature) {
        put(HKMetadataKey.DigitalSignature, new NSString(signature));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getExternalUUID() {
        if (contains(HKMetadataKey.ExternalUUID)) {
            NSString val = (NSString)get(HKMetadataKey.ExternalUUID);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKMetadata setExternalUUID(String uuid) {
        put(HKMetadataKey.ExternalUUID, new NSString(uuid));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public NSTimeZone getTimeZone() {
        if (contains(HKMetadataKey.TimeZone)) {
            NSString val = (NSString)get(HKMetadataKey.TimeZone);
            return NSTimeZone.fromName(val.toString());
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKMetadata setTimeZone(NSTimeZone timeZone) {
        put(HKMetadataKey.ExternalUUID, new NSString(timeZone.getName()));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getDeviceName() {
        if (contains(HKMetadataKey.DeviceName)) {
            NSString val = (NSString)get(HKMetadataKey.DeviceName);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKMetadata setDeviceName(String deviceName) {
        put(HKMetadataKey.DeviceName, new NSString(deviceName));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getDeviceManufacturerName() {
        if (contains(HKMetadataKey.DeviceManufacturerName)) {
            NSString val = (NSString)get(HKMetadataKey.DeviceManufacturerName);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKMetadata setDeviceManufacturerName(String name) {
        put(HKMetadataKey.DeviceManufacturerName, new NSString(name));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean wasTakenInLab() {
        if (contains(HKMetadataKey.WasTakenInLab)) {
            NSNumber val = (NSNumber)get(HKMetadataKey.WasTakenInLab);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKMetadata setWasTakenInLab(boolean lab) {
        put(HKMetadataKey.WasTakenInLab, NSNumber.valueOf(lab));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public double getReferenceRangeLowerLimit() {
        if (contains(HKMetadataKey.ReferenceRangeLowerLimit)) {
            NSNumber val = (NSNumber)get(HKMetadataKey.ReferenceRangeLowerLimit);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKMetadata setReferenceRangeLowerLimit(double limit) {
        put(HKMetadataKey.ReferenceRangeLowerLimit, NSNumber.valueOf(limit));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public double getReferenceRangeUpperLimit() {
        if (contains(HKMetadataKey.ReferenceRangeUpperLimit)) {
            NSNumber val = (NSNumber)get(HKMetadataKey.ReferenceRangeUpperLimit);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKMetadata setReferenceRangeUpperLimit(double limit) {
        put(HKMetadataKey.ReferenceRangeUpperLimit, NSNumber.valueOf(limit));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean wasUserEntered() {
        if (contains(HKMetadataKey.WasUserEntered)) {
            NSNumber val = (NSNumber)get(HKMetadataKey.WasUserEntered);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKMetadata setWasUserEntered(boolean userEntered) {
        put(HKMetadataKey.WasUserEntered, NSNumber.valueOf(userEntered));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public String getWorkoutBrandName() {
        if (contains(HKMetadataKey.WorkoutBrandName)) {
            NSString val = (NSString)get(HKMetadataKey.WorkoutBrandName);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKMetadata setWorkoutBrandName(String name) {
        put(HKMetadataKey.WorkoutBrandName, new NSString(name));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean isGroupFitness() {
        if (contains(HKMetadataKey.GroupFitness)) {
            NSNumber val = (NSNumber)get(HKMetadataKey.GroupFitness);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKMetadata setGroupFitness(boolean groupFitness) {
        put(HKMetadataKey.GroupFitness, NSNumber.valueOf(groupFitness));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean isIndoorWorkout() {
        if (contains(HKMetadataKey.IndoorWorkout)) {
            NSNumber val = (NSNumber)get(HKMetadataKey.IndoorWorkout);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKMetadata setIndoorWorkout(boolean indoorWorkout) {
        put(HKMetadataKey.IndoorWorkout, NSNumber.valueOf(indoorWorkout));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean isCoachedWorkout() {
        if (contains(HKMetadataKey.CoachedWorkout)) {
            NSNumber val = (NSNumber)get(HKMetadataKey.CoachedWorkout);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public HKMetadata setCoachedWorkout(boolean coachedWorkout) {
        put(HKMetadataKey.CoachedWorkout, NSNumber.valueOf(coachedWorkout));
        return this;
    }
    /*<methods>*/
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
