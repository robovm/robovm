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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HKWorkoutSortIdentifier/*</name>*/ 
    extends /*<extends>*/NSSortIdentifier/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(HKWorkoutSortIdentifier.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKWorkoutSortIdentifier Duration = new HKWorkoutSortIdentifier("DurationValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKWorkoutSortIdentifier TotalDistance = new HKWorkoutSortIdentifier("TotalDistanceValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKWorkoutSortIdentifier TotalEnergyBurned = new HKWorkoutSortIdentifier("TotalEnergyBurnedValue");
    
    
    private final LazyGlobalValue<String> lazyGlobalValue;
    
    private HKWorkoutSortIdentifier(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Override
    public String value() {
        return lazyGlobalValue.value();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKWorkoutSortIdentifierDuration", optional=true)
    protected static native String DurationValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKWorkoutSortIdentifierTotalDistance", optional=true)
    protected static native String TotalDistanceValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKWorkoutSortIdentifierTotalEnergyBurned", optional=true)
    protected static native String TotalEnergyBurnedValue();
    /*</methods>*/
}
