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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HKDietaryQuantityTypeIdentifier/*</name>*/ 
    extends /*<extends>*/HKQuantityTypeIdentifier/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(HKDietaryQuantityTypeIdentifier.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier FatTotal = new HKDietaryQuantityTypeIdentifier("FatTotalValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier FatPolyunsaturated = new HKDietaryQuantityTypeIdentifier("FatPolyunsaturatedValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier FatMonounsaturated = new HKDietaryQuantityTypeIdentifier("FatMonounsaturatedValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier FatSaturated = new HKDietaryQuantityTypeIdentifier("FatSaturatedValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Cholesterol = new HKDietaryQuantityTypeIdentifier("CholesterolValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Sodium = new HKDietaryQuantityTypeIdentifier("SodiumValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Carbohydrates = new HKDietaryQuantityTypeIdentifier("CarbohydratesValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Fiber = new HKDietaryQuantityTypeIdentifier("FiberValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Sugar = new HKDietaryQuantityTypeIdentifier("SugarValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier EnergyConsumed = new HKDietaryQuantityTypeIdentifier("EnergyConsumedValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Protein = new HKDietaryQuantityTypeIdentifier("ProteinValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier VitaminA = new HKDietaryQuantityTypeIdentifier("VitaminAValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier VitaminB6 = new HKDietaryQuantityTypeIdentifier("VitaminB6Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier VitaminB12 = new HKDietaryQuantityTypeIdentifier("VitaminB12Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier VitaminC = new HKDietaryQuantityTypeIdentifier("VitaminCValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier VitaminD = new HKDietaryQuantityTypeIdentifier("VitaminDValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier VitaminE = new HKDietaryQuantityTypeIdentifier("VitaminEValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier VitaminK = new HKDietaryQuantityTypeIdentifier("VitaminKValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Calcium = new HKDietaryQuantityTypeIdentifier("CalciumValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Iron = new HKDietaryQuantityTypeIdentifier("IronValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Thiamin = new HKDietaryQuantityTypeIdentifier("ThiaminValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Riboflavin = new HKDietaryQuantityTypeIdentifier("RiboflavinValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Niacin = new HKDietaryQuantityTypeIdentifier("NiacinValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Folate = new HKDietaryQuantityTypeIdentifier("FolateValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Biotin = new HKDietaryQuantityTypeIdentifier("BiotinValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier PantothenicAcid = new HKDietaryQuantityTypeIdentifier("PantothenicAcidValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Phosphorus = new HKDietaryQuantityTypeIdentifier("PhosphorusValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Iodine = new HKDietaryQuantityTypeIdentifier("IodineValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Magnesium = new HKDietaryQuantityTypeIdentifier("MagnesiumValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Zinc = new HKDietaryQuantityTypeIdentifier("ZincValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Selenium = new HKDietaryQuantityTypeIdentifier("SeleniumValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Copper = new HKDietaryQuantityTypeIdentifier("CopperValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Manganese = new HKDietaryQuantityTypeIdentifier("ManganeseValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Chromium = new HKDietaryQuantityTypeIdentifier("ChromiumValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Molybdenum = new HKDietaryQuantityTypeIdentifier("MolybdenumValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Chloride = new HKDietaryQuantityTypeIdentifier("ChlorideValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Potassium = new HKDietaryQuantityTypeIdentifier("PotassiumValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HKDietaryQuantityTypeIdentifier Caffeine = new HKDietaryQuantityTypeIdentifier("CaffeineValue");
    
    private static HKDietaryQuantityTypeIdentifier[] values = new HKDietaryQuantityTypeIdentifier[] {FatTotal, FatPolyunsaturated, FatMonounsaturated, FatSaturated, Cholesterol, 
        Sodium, Carbohydrates, Fiber, Sugar, EnergyConsumed, Protein, VitaminA, VitaminB6, VitaminB12, VitaminC, VitaminD, VitaminE, VitaminK, Calcium, Iron, Thiamin, Riboflavin, 
        Niacin, Folate, Biotin, PantothenicAcid, Phosphorus, Iodine, Magnesium, Zinc, Selenium, Copper, Manganese, Chromium, Molybdenum, Chloride, Potassium, Caffeine};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private HKDietaryQuantityTypeIdentifier(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static HKDietaryQuantityTypeIdentifier valueOf(NSString value) {
        for (HKDietaryQuantityTypeIdentifier v : values) {
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
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryFatTotal", optional=true)
    protected static native NSString FatTotalValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryFatPolyunsaturated", optional=true)
    protected static native NSString FatPolyunsaturatedValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryFatMonounsaturated", optional=true)
    protected static native NSString FatMonounsaturatedValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryFatSaturated", optional=true)
    protected static native NSString FatSaturatedValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryCholesterol", optional=true)
    protected static native NSString CholesterolValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietarySodium", optional=true)
    protected static native NSString SodiumValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryCarbohydrates", optional=true)
    protected static native NSString CarbohydratesValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryFiber", optional=true)
    protected static native NSString FiberValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietarySugar", optional=true)
    protected static native NSString SugarValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryEnergyConsumed", optional=true)
    protected static native NSString EnergyConsumedValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryProtein", optional=true)
    protected static native NSString ProteinValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryVitaminA", optional=true)
    protected static native NSString VitaminAValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryVitaminB6", optional=true)
    protected static native NSString VitaminB6Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryVitaminB12", optional=true)
    protected static native NSString VitaminB12Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryVitaminC", optional=true)
    protected static native NSString VitaminCValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryVitaminD", optional=true)
    protected static native NSString VitaminDValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryVitaminE", optional=true)
    protected static native NSString VitaminEValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryVitaminK", optional=true)
    protected static native NSString VitaminKValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryCalcium", optional=true)
    protected static native NSString CalciumValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryIron", optional=true)
    protected static native NSString IronValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryThiamin", optional=true)
    protected static native NSString ThiaminValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryRiboflavin", optional=true)
    protected static native NSString RiboflavinValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryNiacin", optional=true)
    protected static native NSString NiacinValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryFolate", optional=true)
    protected static native NSString FolateValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryBiotin", optional=true)
    protected static native NSString BiotinValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryPantothenicAcid", optional=true)
    protected static native NSString PantothenicAcidValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryPhosphorus", optional=true)
    protected static native NSString PhosphorusValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryIodine", optional=true)
    protected static native NSString IodineValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryMagnesium", optional=true)
    protected static native NSString MagnesiumValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryZinc", optional=true)
    protected static native NSString ZincValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietarySelenium", optional=true)
    protected static native NSString SeleniumValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryCopper", optional=true)
    protected static native NSString CopperValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryManganese", optional=true)
    protected static native NSString ManganeseValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryChromium", optional=true)
    protected static native NSString ChromiumValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryMolybdenum", optional=true)
    protected static native NSString MolybdenumValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryChloride", optional=true)
    protected static native NSString ChlorideValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryPotassium", optional=true)
    protected static native NSString PotassiumValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HKQuantityTypeIdentifierDietaryCaffeine", optional=true)
    protected static native NSString CaffeineValue();
    /*</methods>*/
}
