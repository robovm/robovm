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
@Marshaler(/*<name>*/HKBodyQuantityTypeIdentifier/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HKBodyQuantityTypeIdentifier/*</name>*/ 
    extends /*<extends>*/HKQuantityTypeIdentifier/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/HKBodyQuantityTypeIdentifier/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static HKBodyQuantityTypeIdentifier toObject(Class<HKBodyQuantityTypeIdentifier> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return HKBodyQuantityTypeIdentifier.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(HKBodyQuantityTypeIdentifier o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<HKBodyQuantityTypeIdentifier> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<HKBodyQuantityTypeIdentifier> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(HKBodyQuantityTypeIdentifier.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<HKBodyQuantityTypeIdentifier> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (HKBodyQuantityTypeIdentifier i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKBodyQuantityTypeIdentifier BodyMassIndex = new HKBodyQuantityTypeIdentifier("BodyMassIndex");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKBodyQuantityTypeIdentifier BodyFatPercentage = new HKBodyQuantityTypeIdentifier("BodyFatPercentage");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKBodyQuantityTypeIdentifier Height = new HKBodyQuantityTypeIdentifier("Height");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKBodyQuantityTypeIdentifier BodyMass = new HKBodyQuantityTypeIdentifier("BodyMass");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKBodyQuantityTypeIdentifier LeanBodyMass = new HKBodyQuantityTypeIdentifier("LeanBodyMass");
    /*</constants>*/
    
    private static /*<name>*/HKBodyQuantityTypeIdentifier/*</name>*/[] values = new /*<name>*/HKBodyQuantityTypeIdentifier/*</name>*/[] {/*<value_list>*/BodyMassIndex, BodyFatPercentage, Height, BodyMass, LeanBodyMass/*</value_list>*/};
    
    /*<name>*/HKBodyQuantityTypeIdentifier/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/HKBodyQuantityTypeIdentifier/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/HKBodyQuantityTypeIdentifier/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/HKBodyQuantityTypeIdentifier/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("HealthKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierBodyMassIndex", optional=true)
        public static native NSString BodyMassIndex();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierBodyFatPercentage", optional=true)
        public static native NSString BodyFatPercentage();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierHeight", optional=true)
        public static native NSString Height();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierBodyMass", optional=true)
        public static native NSString BodyMass();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierLeanBodyMass", optional=true)
        public static native NSString LeanBodyMass();
        /*</values>*/
    }
}
