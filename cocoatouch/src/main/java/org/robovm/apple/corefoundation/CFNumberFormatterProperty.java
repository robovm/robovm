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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CFNumberFormatterProperty.Marshaler.class)
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFNumberFormatterProperty/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CFNumberFormatterProperty toObject(Class<CFNumberFormatterProperty> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFNumberFormatterProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFNumberFormatterProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFNumberFormatterProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final CFNumberFormatterProperty CurrencyCode = new CFNumberFormatterProperty("CurrencyCodeValue");
    public static final CFNumberFormatterProperty DecimalSeparator = new CFNumberFormatterProperty("DecimalSeparatorValue");
    public static final CFNumberFormatterProperty CurrencyDecimalSeparator = new CFNumberFormatterProperty("CurrencyDecimalSeparatorValue");
    public static final CFNumberFormatterProperty AlwaysShowDecimalSeparator = new CFNumberFormatterProperty("AlwaysShowDecimalSeparatorValue");
    public static final CFNumberFormatterProperty GroupingSeparator = new CFNumberFormatterProperty("GroupingSeparatorValue");
    public static final CFNumberFormatterProperty UseGroupingSeparator = new CFNumberFormatterProperty("UseGroupingSeparatorValue");
    public static final CFNumberFormatterProperty PercentSymbol = new CFNumberFormatterProperty("PercentSymbolValue");
    public static final CFNumberFormatterProperty ZeroSymbol = new CFNumberFormatterProperty("ZeroSymbolValue");
    public static final CFNumberFormatterProperty NaNSymbol = new CFNumberFormatterProperty("NaNSymbolValue");
    public static final CFNumberFormatterProperty InfinitySymbol = new CFNumberFormatterProperty("InfinitySymbolValue");
    public static final CFNumberFormatterProperty MinusSign = new CFNumberFormatterProperty("MinusSignValue");
    public static final CFNumberFormatterProperty PlusSign = new CFNumberFormatterProperty("PlusSignValue");
    public static final CFNumberFormatterProperty CurrencySymbol = new CFNumberFormatterProperty("CurrencySymbolValue");
    public static final CFNumberFormatterProperty ExponentSymbol = new CFNumberFormatterProperty("ExponentSymbolValue");
    public static final CFNumberFormatterProperty MinIntegerDigits = new CFNumberFormatterProperty("MinIntegerDigitsValue");
    public static final CFNumberFormatterProperty MaxIntegerDigits = new CFNumberFormatterProperty("MaxIntegerDigitsValue");
    public static final CFNumberFormatterProperty MinFractionDigits = new CFNumberFormatterProperty("MinFractionDigitsValue");
    public static final CFNumberFormatterProperty MaxFractionDigits = new CFNumberFormatterProperty("MaxFractionDigitsValue");
    public static final CFNumberFormatterProperty GroupingSize = new CFNumberFormatterProperty("GroupingSizeValue");
    public static final CFNumberFormatterProperty SecondaryGroupingSize = new CFNumberFormatterProperty("SecondaryGroupingSizeValue");
    public static final CFNumberFormatterProperty RoundingMode = new CFNumberFormatterProperty("RoundingModeValue");
    public static final CFNumberFormatterProperty RoundingIncrement = new CFNumberFormatterProperty("RoundingIncrementValue");
    public static final CFNumberFormatterProperty FormatWidth = new CFNumberFormatterProperty("FormatWidthValue");
    public static final CFNumberFormatterProperty PaddingPosition = new CFNumberFormatterProperty("PaddingPositionValue");
    public static final CFNumberFormatterProperty PaddingCharacter = new CFNumberFormatterProperty("PaddingCharacterValue");
    public static final CFNumberFormatterProperty DefaultFormat = new CFNumberFormatterProperty("DefaultFormatValue");
    public static final CFNumberFormatterProperty Multiplier = new CFNumberFormatterProperty("MultiplierValue");
    public static final CFNumberFormatterProperty PositivePrefix = new CFNumberFormatterProperty("PositivePrefixValue");
    public static final CFNumberFormatterProperty PositiveSuffix = new CFNumberFormatterProperty("PositiveSuffixValue");
    public static final CFNumberFormatterProperty NegativePrefix = new CFNumberFormatterProperty("NegativePrefixValue");
    public static final CFNumberFormatterProperty NegativeSuffix = new CFNumberFormatterProperty("NegativeSuffixValue");
    public static final CFNumberFormatterProperty PerMillSymbol = new CFNumberFormatterProperty("PerMillSymbolValue");
    public static final CFNumberFormatterProperty InternationalCurrencySymbol = new CFNumberFormatterProperty("InternationalCurrencySymbolValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFNumberFormatterProperty CurrencyGroupingSeparator = new CFNumberFormatterProperty("CurrencyGroupingSeparatorValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFNumberFormatterProperty IsLenient = new CFNumberFormatterProperty("IsLenientValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFNumberFormatterProperty UseSignificantDigits = new CFNumberFormatterProperty("UseSignificantDigitsValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFNumberFormatterProperty MinSignificantDigits = new CFNumberFormatterProperty("MinSignificantDigitsValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFNumberFormatterProperty MaxSignificantDigits = new CFNumberFormatterProperty("MaxSignificantDigitsValue");
    
    private static CFNumberFormatterProperty[] values = new CFNumberFormatterProperty[] {CurrencyCode, DecimalSeparator, CurrencyDecimalSeparator, 
        AlwaysShowDecimalSeparator, GroupingSeparator, UseGroupingSeparator, PercentSymbol, ZeroSymbol, NaNSymbol, InfinitySymbol, MinusSign, PlusSign, 
        CurrencySymbol, ExponentSymbol, MinIntegerDigits, MaxIntegerDigits, MinFractionDigits, MaxFractionDigits, GroupingSize, SecondaryGroupingSize, 
        RoundingMode, RoundingIncrement, FormatWidth, PaddingPosition, PaddingCharacter, DefaultFormat, Multiplier, PositivePrefix, PositiveSuffix, 
        NegativePrefix, NegativeSuffix, PerMillSymbol, InternationalCurrencySymbol, CurrencyGroupingSeparator, IsLenient, UseSignificantDigits, 
        MinSignificantDigits, MaxSignificantDigits};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CFNumberFormatterProperty(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CFNumberFormatterProperty valueOf(CFString value) {
        for (CFNumberFormatterProperty v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFNumberFormatterProperty/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="kCFNumberFormatterCurrencyCode", optional=true)
    protected static native CFString CurrencyCodeValue();
    @GlobalValue(symbol="kCFNumberFormatterDecimalSeparator", optional=true)
    protected static native CFString DecimalSeparatorValue();
    @GlobalValue(symbol="kCFNumberFormatterCurrencyDecimalSeparator", optional=true)
    protected static native CFString CurrencyDecimalSeparatorValue();
    @GlobalValue(symbol="kCFNumberFormatterAlwaysShowDecimalSeparator", optional=true)
    protected static native CFString AlwaysShowDecimalSeparatorValue();
    @GlobalValue(symbol="kCFNumberFormatterGroupingSeparator", optional=true)
    protected static native CFString GroupingSeparatorValue();
    @GlobalValue(symbol="kCFNumberFormatterUseGroupingSeparator", optional=true)
    protected static native CFString UseGroupingSeparatorValue();
    @GlobalValue(symbol="kCFNumberFormatterPercentSymbol", optional=true)
    protected static native CFString PercentSymbolValue();
    @GlobalValue(symbol="kCFNumberFormatterZeroSymbol", optional=true)
    protected static native CFString ZeroSymbolValue();
    @GlobalValue(symbol="kCFNumberFormatterNaNSymbol", optional=true)
    protected static native CFString NaNSymbolValue();
    @GlobalValue(symbol="kCFNumberFormatterInfinitySymbol", optional=true)
    protected static native CFString InfinitySymbolValue();
    @GlobalValue(symbol="kCFNumberFormatterMinusSign", optional=true)
    protected static native CFString MinusSignValue();
    @GlobalValue(symbol="kCFNumberFormatterPlusSign", optional=true)
    protected static native CFString PlusSignValue();
    @GlobalValue(symbol="kCFNumberFormatterCurrencySymbol", optional=true)
    protected static native CFString CurrencySymbolValue();
    @GlobalValue(symbol="kCFNumberFormatterExponentSymbol", optional=true)
    protected static native CFString ExponentSymbolValue();
    @GlobalValue(symbol="kCFNumberFormatterMinIntegerDigits", optional=true)
    protected static native CFString MinIntegerDigitsValue();
    @GlobalValue(symbol="kCFNumberFormatterMaxIntegerDigits", optional=true)
    protected static native CFString MaxIntegerDigitsValue();
    @GlobalValue(symbol="kCFNumberFormatterMinFractionDigits", optional=true)
    protected static native CFString MinFractionDigitsValue();
    @GlobalValue(symbol="kCFNumberFormatterMaxFractionDigits", optional=true)
    protected static native CFString MaxFractionDigitsValue();
    @GlobalValue(symbol="kCFNumberFormatterGroupingSize", optional=true)
    protected static native CFString GroupingSizeValue();
    @GlobalValue(symbol="kCFNumberFormatterSecondaryGroupingSize", optional=true)
    protected static native CFString SecondaryGroupingSizeValue();
    @GlobalValue(symbol="kCFNumberFormatterRoundingMode", optional=true)
    protected static native CFString RoundingModeValue();
    @GlobalValue(symbol="kCFNumberFormatterRoundingIncrement", optional=true)
    protected static native CFString RoundingIncrementValue();
    @GlobalValue(symbol="kCFNumberFormatterFormatWidth", optional=true)
    protected static native CFString FormatWidthValue();
    @GlobalValue(symbol="kCFNumberFormatterPaddingPosition", optional=true)
    protected static native CFString PaddingPositionValue();
    @GlobalValue(symbol="kCFNumberFormatterPaddingCharacter", optional=true)
    protected static native CFString PaddingCharacterValue();
    @GlobalValue(symbol="kCFNumberFormatterDefaultFormat", optional=true)
    protected static native CFString DefaultFormatValue();
    @GlobalValue(symbol="kCFNumberFormatterMultiplier", optional=true)
    protected static native CFString MultiplierValue();
    @GlobalValue(symbol="kCFNumberFormatterPositivePrefix", optional=true)
    protected static native CFString PositivePrefixValue();
    @GlobalValue(symbol="kCFNumberFormatterPositiveSuffix", optional=true)
    protected static native CFString PositiveSuffixValue();
    @GlobalValue(symbol="kCFNumberFormatterNegativePrefix", optional=true)
    protected static native CFString NegativePrefixValue();
    @GlobalValue(symbol="kCFNumberFormatterNegativeSuffix", optional=true)
    protected static native CFString NegativeSuffixValue();
    @GlobalValue(symbol="kCFNumberFormatterPerMillSymbol", optional=true)
    protected static native CFString PerMillSymbolValue();
    @GlobalValue(symbol="kCFNumberFormatterInternationalCurrencySymbol", optional=true)
    protected static native CFString InternationalCurrencySymbolValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNumberFormatterCurrencyGroupingSeparator", optional=true)
    protected static native CFString CurrencyGroupingSeparatorValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNumberFormatterIsLenient", optional=true)
    protected static native CFString IsLenientValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNumberFormatterUseSignificantDigits", optional=true)
    protected static native CFString UseSignificantDigitsValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNumberFormatterMinSignificantDigits", optional=true)
    protected static native CFString MinSignificantDigitsValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNumberFormatterMaxSignificantDigits", optional=true)
    protected static native CFString MaxSignificantDigitsValue();
    /*</methods>*/
}
