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
/*<annotations>*/@Library("HealthKit") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/HKFitnessQuantityTypeIdentifier/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HKFitnessQuantityTypeIdentifier/*</name>*/ 
    extends /*<extends>*/HKQuantityTypeIdentifier/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/HKFitnessQuantityTypeIdentifier/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static HKFitnessQuantityTypeIdentifier toObject(Class<HKFitnessQuantityTypeIdentifier> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return HKFitnessQuantityTypeIdentifier.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(HKFitnessQuantityTypeIdentifier o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<HKFitnessQuantityTypeIdentifier> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<HKFitnessQuantityTypeIdentifier> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(HKFitnessQuantityTypeIdentifier.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<HKFitnessQuantityTypeIdentifier> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (HKFitnessQuantityTypeIdentifier o : l) {
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
    public static final HKFitnessQuantityTypeIdentifier StepCount = new HKFitnessQuantityTypeIdentifier("StepCount");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKFitnessQuantityTypeIdentifier DistanceWalkingRunning = new HKFitnessQuantityTypeIdentifier("DistanceWalkingRunning");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKFitnessQuantityTypeIdentifier DistanceCycling = new HKFitnessQuantityTypeIdentifier("DistanceCycling");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKFitnessQuantityTypeIdentifier BasalEnergyBurned = new HKFitnessQuantityTypeIdentifier("BasalEnergyBurned");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKFitnessQuantityTypeIdentifier ActiveEnergyBurned = new HKFitnessQuantityTypeIdentifier("ActiveEnergyBurned");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKFitnessQuantityTypeIdentifier FlightsClimbed = new HKFitnessQuantityTypeIdentifier("FlightsClimbed");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKFitnessQuantityTypeIdentifier NikeFuel = new HKFitnessQuantityTypeIdentifier("NikeFuel");
    /*</constants>*/
    
    private static /*<name>*/HKFitnessQuantityTypeIdentifier/*</name>*/[] values = new /*<name>*/HKFitnessQuantityTypeIdentifier/*</name>*/[] {/*<value_list>*/StepCount, DistanceWalkingRunning, DistanceCycling, BasalEnergyBurned, ActiveEnergyBurned, FlightsClimbed, NikeFuel/*</value_list>*/};
    
    /*<name>*/HKFitnessQuantityTypeIdentifier/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/HKFitnessQuantityTypeIdentifier/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/HKFitnessQuantityTypeIdentifier/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/HKFitnessQuantityTypeIdentifier/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("HealthKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierStepCount", optional=true)
        public static native NSString StepCount();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDistanceWalkingRunning", optional=true)
        public static native NSString DistanceWalkingRunning();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDistanceCycling", optional=true)
        public static native NSString DistanceCycling();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierBasalEnergyBurned", optional=true)
        public static native NSString BasalEnergyBurned();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierActiveEnergyBurned", optional=true)
        public static native NSString ActiveEnergyBurned();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierFlightsClimbed", optional=true)
        public static native NSString FlightsClimbed();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierNikeFuel", optional=true)
        public static native NSString NikeFuel();
        /*</values>*/
    }
}
