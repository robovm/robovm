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
@Marshaler(/*<name>*/HKDietaryQuantityTypeIdentifier/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HKDietaryQuantityTypeIdentifier/*</name>*/ 
    extends /*<extends>*/HKQuantityTypeIdentifier/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/HKDietaryQuantityTypeIdentifier/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static HKDietaryQuantityTypeIdentifier toObject(Class<HKDietaryQuantityTypeIdentifier> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return HKDietaryQuantityTypeIdentifier.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(HKDietaryQuantityTypeIdentifier o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<HKDietaryQuantityTypeIdentifier> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<HKDietaryQuantityTypeIdentifier> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(HKDietaryQuantityTypeIdentifier.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<HKDietaryQuantityTypeIdentifier> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (HKDietaryQuantityTypeIdentifier o : l) {
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
    public static final HKDietaryQuantityTypeIdentifier FatTotal = new HKDietaryQuantityTypeIdentifier("FatTotal");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier FatPolyunsaturated = new HKDietaryQuantityTypeIdentifier("FatPolyunsaturated");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier FatMonounsaturated = new HKDietaryQuantityTypeIdentifier("FatMonounsaturated");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier FatSaturated = new HKDietaryQuantityTypeIdentifier("FatSaturated");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Cholesterol = new HKDietaryQuantityTypeIdentifier("Cholesterol");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Sodium = new HKDietaryQuantityTypeIdentifier("Sodium");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Carbohydrates = new HKDietaryQuantityTypeIdentifier("Carbohydrates");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Fiber = new HKDietaryQuantityTypeIdentifier("Fiber");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Sugar = new HKDietaryQuantityTypeIdentifier("Sugar");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier EnergyConsumed = new HKDietaryQuantityTypeIdentifier("EnergyConsumed");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Protein = new HKDietaryQuantityTypeIdentifier("Protein");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier VitaminA = new HKDietaryQuantityTypeIdentifier("VitaminA");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier VitaminB6 = new HKDietaryQuantityTypeIdentifier("VitaminB6");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier VitaminB12 = new HKDietaryQuantityTypeIdentifier("VitaminB12");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier VitaminC = new HKDietaryQuantityTypeIdentifier("VitaminC");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier VitaminD = new HKDietaryQuantityTypeIdentifier("VitaminD");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier VitaminE = new HKDietaryQuantityTypeIdentifier("VitaminE");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier VitaminK = new HKDietaryQuantityTypeIdentifier("VitaminK");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Calcium = new HKDietaryQuantityTypeIdentifier("Calcium");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Iron = new HKDietaryQuantityTypeIdentifier("Iron");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Thiamin = new HKDietaryQuantityTypeIdentifier("Thiamin");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Riboflavin = new HKDietaryQuantityTypeIdentifier("Riboflavin");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Niacin = new HKDietaryQuantityTypeIdentifier("Niacin");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Folate = new HKDietaryQuantityTypeIdentifier("Folate");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Biotin = new HKDietaryQuantityTypeIdentifier("Biotin");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier PantothenicAcid = new HKDietaryQuantityTypeIdentifier("PantothenicAcid");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Phosphorus = new HKDietaryQuantityTypeIdentifier("Phosphorus");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Iodine = new HKDietaryQuantityTypeIdentifier("Iodine");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Magnesium = new HKDietaryQuantityTypeIdentifier("Magnesium");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Zinc = new HKDietaryQuantityTypeIdentifier("Zinc");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Selenium = new HKDietaryQuantityTypeIdentifier("Selenium");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Copper = new HKDietaryQuantityTypeIdentifier("Copper");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Manganese = new HKDietaryQuantityTypeIdentifier("Manganese");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Chromium = new HKDietaryQuantityTypeIdentifier("Chromium");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Molybdenum = new HKDietaryQuantityTypeIdentifier("Molybdenum");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Chloride = new HKDietaryQuantityTypeIdentifier("Chloride");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Potassium = new HKDietaryQuantityTypeIdentifier("Potassium");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Caffeine = new HKDietaryQuantityTypeIdentifier("Caffeine");
    /*</constants>*/
    
    private static /*<name>*/HKDietaryQuantityTypeIdentifier/*</name>*/[] values = new /*<name>*/HKDietaryQuantityTypeIdentifier/*</name>*/[] {/*<value_list>*/FatTotal, FatPolyunsaturated, FatMonounsaturated, FatSaturated, Cholesterol, Sodium, Carbohydrates, Fiber, Sugar, EnergyConsumed, Protein, VitaminA, VitaminB6, VitaminB12, VitaminC, VitaminD, VitaminE, VitaminK, Calcium, Iron, Thiamin, Riboflavin, Niacin, Folate, Biotin, PantothenicAcid, Phosphorus, Iodine, Magnesium, Zinc, Selenium, Copper, Manganese, Chromium, Molybdenum, Chloride, Potassium, Caffeine/*</value_list>*/};
    
    /*<name>*/HKDietaryQuantityTypeIdentifier/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/HKDietaryQuantityTypeIdentifier/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/HKDietaryQuantityTypeIdentifier/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/HKDietaryQuantityTypeIdentifier/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("HealthKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryFatTotal", optional=true)
        public static native NSString FatTotal();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryFatPolyunsaturated", optional=true)
        public static native NSString FatPolyunsaturated();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryFatMonounsaturated", optional=true)
        public static native NSString FatMonounsaturated();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryFatSaturated", optional=true)
        public static native NSString FatSaturated();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryCholesterol", optional=true)
        public static native NSString Cholesterol();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietarySodium", optional=true)
        public static native NSString Sodium();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryCarbohydrates", optional=true)
        public static native NSString Carbohydrates();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryFiber", optional=true)
        public static native NSString Fiber();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietarySugar", optional=true)
        public static native NSString Sugar();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryEnergyConsumed", optional=true)
        public static native NSString EnergyConsumed();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryProtein", optional=true)
        public static native NSString Protein();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryVitaminA", optional=true)
        public static native NSString VitaminA();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryVitaminB6", optional=true)
        public static native NSString VitaminB6();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryVitaminB12", optional=true)
        public static native NSString VitaminB12();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryVitaminC", optional=true)
        public static native NSString VitaminC();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryVitaminD", optional=true)
        public static native NSString VitaminD();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryVitaminE", optional=true)
        public static native NSString VitaminE();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryVitaminK", optional=true)
        public static native NSString VitaminK();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryCalcium", optional=true)
        public static native NSString Calcium();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryIron", optional=true)
        public static native NSString Iron();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryThiamin", optional=true)
        public static native NSString Thiamin();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryRiboflavin", optional=true)
        public static native NSString Riboflavin();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryNiacin", optional=true)
        public static native NSString Niacin();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryFolate", optional=true)
        public static native NSString Folate();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryBiotin", optional=true)
        public static native NSString Biotin();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryPantothenicAcid", optional=true)
        public static native NSString PantothenicAcid();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryPhosphorus", optional=true)
        public static native NSString Phosphorus();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryIodine", optional=true)
        public static native NSString Iodine();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryMagnesium", optional=true)
        public static native NSString Magnesium();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryZinc", optional=true)
        public static native NSString Zinc();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietarySelenium", optional=true)
        public static native NSString Selenium();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryCopper", optional=true)
        public static native NSString Copper();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryManganese", optional=true)
        public static native NSString Manganese();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryChromium", optional=true)
        public static native NSString Chromium();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryMolybdenum", optional=true)
        public static native NSString Molybdenum();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryChloride", optional=true)
        public static native NSString Chloride();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryPotassium", optional=true)
        public static native NSString Potassium();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryCaffeine", optional=true)
        public static native NSString Caffeine();
        /*</values>*/
    }
}
