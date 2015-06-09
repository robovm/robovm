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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HKResultsQuantityTypeIdentifier/*</name>*/ 
    extends /*<extends>*/HKQuantityTypeIdentifier/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(HKResultsQuantityTypeIdentifier.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKResultsQuantityTypeIdentifier OxygenSaturation = new HKResultsQuantityTypeIdentifier("OxygenSaturationValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKResultsQuantityTypeIdentifier PeripheralPerfusionIndex = new HKResultsQuantityTypeIdentifier("PeripheralPerfusionIndexValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKResultsQuantityTypeIdentifier BloodGlucose = new HKResultsQuantityTypeIdentifier("BloodGlucoseValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKResultsQuantityTypeIdentifier NumberOfTimesFallen = new HKResultsQuantityTypeIdentifier("NumberOfTimesFallenValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKResultsQuantityTypeIdentifier ElectrodermalActivity = new HKResultsQuantityTypeIdentifier("ElectrodermalActivityValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKResultsQuantityTypeIdentifier InhalerUsage = new HKResultsQuantityTypeIdentifier("InhalerUsageValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKResultsQuantityTypeIdentifier BloodAlcoholContent = new HKResultsQuantityTypeIdentifier("BloodAlcoholContentValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKResultsQuantityTypeIdentifier ForcedVitalCapacity = new HKResultsQuantityTypeIdentifier("ForcedVitalCapacityValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKResultsQuantityTypeIdentifier ForcedExpiratoryVolume1 = new HKResultsQuantityTypeIdentifier("ForcedExpiratoryVolume1Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKResultsQuantityTypeIdentifier PeakExpiratoryFlowRate = new HKResultsQuantityTypeIdentifier("PeakExpiratoryFlowRateValue");
    
    private static HKResultsQuantityTypeIdentifier[] values = new HKResultsQuantityTypeIdentifier[] {OxygenSaturation, PeripheralPerfusionIndex, BloodGlucose, NumberOfTimesFallen, 
        ElectrodermalActivity, InhalerUsage, BloodAlcoholContent, ForcedVitalCapacity, ForcedExpiratoryVolume1, PeakExpiratoryFlowRate};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private HKResultsQuantityTypeIdentifier(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static HKResultsQuantityTypeIdentifier valueOf(NSString value) {
        for (HKResultsQuantityTypeIdentifier v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        return null;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierOxygenSaturation", optional=true)
    protected static native NSString OxygenSaturationValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierPeripheralPerfusionIndex", optional=true)
    protected static native NSString PeripheralPerfusionIndexValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierBloodGlucose", optional=true)
    protected static native NSString BloodGlucoseValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierNumberOfTimesFallen", optional=true)
    protected static native NSString NumberOfTimesFallenValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierElectrodermalActivity", optional=true)
    protected static native NSString ElectrodermalActivityValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierInhalerUsage", optional=true)
    protected static native NSString InhalerUsageValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierBloodAlcoholContent", optional=true)
    protected static native NSString BloodAlcoholContentValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierForcedVitalCapacity", optional=true)
    protected static native NSString ForcedVitalCapacityValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierForcedExpiratoryVolume1", optional=true)
    protected static native NSString ForcedExpiratoryVolume1Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierPeakExpiratoryFlowRate", optional=true)
    protected static native NSString PeakExpiratoryFlowRateValue();
    /*</methods>*/
}
