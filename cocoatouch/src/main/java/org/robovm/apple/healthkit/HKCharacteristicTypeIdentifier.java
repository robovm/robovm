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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("HealthKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HKCharacteristicTypeIdentifier/*</name>*/ 
    extends /*<extends>*/HKTypeIdentifier/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(HKCharacteristicTypeIdentifier.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKCharacteristicTypeIdentifier BiologicalSex = new HKCharacteristicTypeIdentifier("BiologicalSexValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKCharacteristicTypeIdentifier BloodType = new HKCharacteristicTypeIdentifier("BloodTypeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKCharacteristicTypeIdentifier DateOfBirth = new HKCharacteristicTypeIdentifier("DateOfBirthValue");
    
    private static HKCharacteristicTypeIdentifier[] values = new HKCharacteristicTypeIdentifier[] {BiologicalSex, BloodType, DateOfBirth};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private HKCharacteristicTypeIdentifier(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static HKCharacteristicTypeIdentifier valueOf(NSString value) {
        for (HKCharacteristicTypeIdentifier v : values) {
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
    @GlobalValue(symbol="HKCharacteristicTypeIdentifierBiologicalSex", optional=true)
    protected static native NSString BiologicalSexValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKCharacteristicTypeIdentifierBloodType", optional=true)
    protected static native NSString BloodTypeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKCharacteristicTypeIdentifierDateOfBirth", optional=true)
    protected static native NSString DateOfBirthValue();
    /*</methods>*/
}
