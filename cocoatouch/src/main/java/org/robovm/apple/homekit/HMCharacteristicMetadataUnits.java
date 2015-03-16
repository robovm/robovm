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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(HMCharacteristicMetadataUnits.Marshaler.class)
/*<annotations>*/@Library("HomeKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HMCharacteristicMetadataUnits/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static HMCharacteristicMetadataUnits toObject(Class<HMCharacteristicMetadataUnits> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return HMCharacteristicMetadataUnits.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(HMCharacteristicMetadataUnits o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(HMCharacteristicMetadataUnits.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataUnits Celsius = new HMCharacteristicMetadataUnits("CelsiusValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataUnits Fahrenheit = new HMCharacteristicMetadataUnits("FahrenheitValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataUnits Percentage = new HMCharacteristicMetadataUnits("PercentageValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataUnits ArcDegree = new HMCharacteristicMetadataUnits("ArcDegreeValue");
    
    private static HMCharacteristicMetadataUnits[] values = new HMCharacteristicMetadataUnits[] {Celsius, Fahrenheit, 
        Percentage, ArcDegree};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private HMCharacteristicMetadataUnits(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static HMCharacteristicMetadataUnits valueOf(NSString value) {
        for (HMCharacteristicMetadataUnits v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/HMCharacteristicMetadataUnits/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicMetadataUnitsCelsius", optional=true)
    protected static native NSString CelsiusValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicMetadataUnitsFahrenheit", optional=true)
    protected static native NSString FahrenheitValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicMetadataUnitsPercentage", optional=true)
    protected static native NSString PercentageValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicMetadataUnitsArcDegree", optional=true)
    protected static native NSString ArcDegreeValue();
    /*</methods>*/
}
