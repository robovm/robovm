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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HKPredicateKeyPath/*</name>*/ 
    extends /*<extends>*/NSPredicateKeyPath/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(HKPredicateKeyPath.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKPredicateKeyPath UUID = new HKPredicateKeyPath("UUIDValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKPredicateKeyPath Source = new HKPredicateKeyPath("SourceValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKPredicateKeyPath Metadata = new HKPredicateKeyPath("MetadataValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKPredicateKeyPath Correlation = new HKPredicateKeyPath("CorrelationValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKPredicateKeyPath Workout = new HKPredicateKeyPath("WorkoutValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKPredicateKeyPath StartDate = new HKPredicateKeyPath("StartDateValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKPredicateKeyPath EndDate = new HKPredicateKeyPath("EndDateValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKPredicateKeyPath WorkoutDuration = new HKPredicateKeyPath("WorkoutDurationValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKPredicateKeyPath WorkoutTotalDistance = new HKPredicateKeyPath("WorkoutTotalDistanceValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKPredicateKeyPath WorkoutTotalEnergyBurned = new HKPredicateKeyPath("WorkoutTotalEnergyBurnedValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKPredicateKeyPath WorkoutType = new HKPredicateKeyPath("WorkoutTypeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKPredicateKeyPath CategoryValue = new HKPredicateKeyPath("CategoryValueValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKPredicateKeyPath Quantity = new HKPredicateKeyPath("QuantityValue");
    
    
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private HKPredicateKeyPath(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Override
    public NSString value() {
        return lazyGlobalValue.value();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKPredicateKeyPathUUID", optional=true)
    protected static native NSString UUIDValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKPredicateKeyPathSource", optional=true)
    protected static native NSString SourceValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKPredicateKeyPathMetadata", optional=true)
    protected static native NSString MetadataValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKPredicateKeyPathCorrelation", optional=true)
    protected static native NSString CorrelationValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKPredicateKeyPathWorkout", optional=true)
    protected static native NSString WorkoutValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKPredicateKeyPathStartDate", optional=true)
    protected static native NSString StartDateValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKPredicateKeyPathEndDate", optional=true)
    protected static native NSString EndDateValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKPredicateKeyPathWorkoutDuration", optional=true)
    protected static native NSString WorkoutDurationValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKPredicateKeyPathWorkoutTotalDistance", optional=true)
    protected static native NSString WorkoutTotalDistanceValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKPredicateKeyPathWorkoutTotalEnergyBurned", optional=true)
    protected static native NSString WorkoutTotalEnergyBurnedValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKPredicateKeyPathWorkoutType", optional=true)
    protected static native NSString WorkoutTypeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKPredicateKeyPathCategoryValue", optional=true)
    protected static native NSString CategoryValueValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKPredicateKeyPathQuantity", optional=true)
    protected static native NSString QuantityValue();
    /*</methods>*/
}
