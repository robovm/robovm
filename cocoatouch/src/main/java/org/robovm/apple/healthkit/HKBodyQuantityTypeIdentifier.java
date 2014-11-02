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
/*<annotations>*/@Library("HealthKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HKBodyQuantityTypeIdentifier/*</name>*/ 
    extends /*<extends>*/HKQuantityTypeIdentifier/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(HKBodyQuantityTypeIdentifier.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKBodyQuantityTypeIdentifier BodyMassIndex = new HKBodyQuantityTypeIdentifier("BodyMassIndexValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKBodyQuantityTypeIdentifier BodyFatPercentage = new HKBodyQuantityTypeIdentifier("BodyFatPercentageValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKBodyQuantityTypeIdentifier Height = new HKBodyQuantityTypeIdentifier("HeightValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKBodyQuantityTypeIdentifier BodyMass = new HKBodyQuantityTypeIdentifier("BodyMassValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKBodyQuantityTypeIdentifier LeanBodyMass = new HKBodyQuantityTypeIdentifier("LeanBodyMassValue");
    
    private static HKBodyQuantityTypeIdentifier[] values = new HKBodyQuantityTypeIdentifier[] {BodyMassIndex, BodyFatPercentage, Height, BodyMass, LeanBodyMass};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private HKBodyQuantityTypeIdentifier(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static HKBodyQuantityTypeIdentifier valueOf(NSString value) {
        for (HKBodyQuantityTypeIdentifier v : values) {
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
    @GlobalValue(symbol="HKQuantityTypeIdentifierBodyMassIndex", optional=true)
    protected static native NSString BodyMassIndexValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierBodyFatPercentage", optional=true)
    protected static native NSString BodyFatPercentageValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierHeight", optional=true)
    protected static native NSString HeightValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierBodyMass", optional=true)
    protected static native NSString BodyMassValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierLeanBodyMass", optional=true)
    protected static native NSString LeanBodyMassValue();
    /*</methods>*/
}
