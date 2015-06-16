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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreservices.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CFNumberFormatterProperty/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFNumberFormatterProperty/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CFNumberFormatterProperty/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFNumberFormatterProperty> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFNumberFormatterProperty> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CFNumberFormatterProperty.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFNumberFormatterProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFNumberFormatterProperty o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final CFNumberFormatterProperty CurrencyCode = new CFNumberFormatterProperty("CurrencyCode");
    public static final CFNumberFormatterProperty DecimalSeparator = new CFNumberFormatterProperty("DecimalSeparator");
    public static final CFNumberFormatterProperty CurrencyDecimalSeparator = new CFNumberFormatterProperty("CurrencyDecimalSeparator");
    public static final CFNumberFormatterProperty AlwaysShowDecimalSeparator = new CFNumberFormatterProperty("AlwaysShowDecimalSeparator");
    public static final CFNumberFormatterProperty GroupingSeparator = new CFNumberFormatterProperty("GroupingSeparator");
    public static final CFNumberFormatterProperty UseGroupingSeparator = new CFNumberFormatterProperty("UseGroupingSeparator");
    public static final CFNumberFormatterProperty PercentSymbol = new CFNumberFormatterProperty("PercentSymbol");
    public static final CFNumberFormatterProperty ZeroSymbol = new CFNumberFormatterProperty("ZeroSymbol");
    public static final CFNumberFormatterProperty NaNSymbol = new CFNumberFormatterProperty("NaNSymbol");
    public static final CFNumberFormatterProperty InfinitySymbol = new CFNumberFormatterProperty("InfinitySymbol");
    public static final CFNumberFormatterProperty MinusSign = new CFNumberFormatterProperty("MinusSign");
    public static final CFNumberFormatterProperty PlusSign = new CFNumberFormatterProperty("PlusSign");
    public static final CFNumberFormatterProperty CurrencySymbol = new CFNumberFormatterProperty("CurrencySymbol");
    public static final CFNumberFormatterProperty ExponentSymbol = new CFNumberFormatterProperty("ExponentSymbol");
    public static final CFNumberFormatterProperty MinIntegerDigits = new CFNumberFormatterProperty("MinIntegerDigits");
    public static final CFNumberFormatterProperty MaxIntegerDigits = new CFNumberFormatterProperty("MaxIntegerDigits");
    public static final CFNumberFormatterProperty MinFractionDigits = new CFNumberFormatterProperty("MinFractionDigits");
    public static final CFNumberFormatterProperty MaxFractionDigits = new CFNumberFormatterProperty("MaxFractionDigits");
    public static final CFNumberFormatterProperty GroupingSize = new CFNumberFormatterProperty("GroupingSize");
    public static final CFNumberFormatterProperty SecondaryGroupingSize = new CFNumberFormatterProperty("SecondaryGroupingSize");
    public static final CFNumberFormatterProperty RoundingMode = new CFNumberFormatterProperty("RoundingMode");
    public static final CFNumberFormatterProperty RoundingIncrement = new CFNumberFormatterProperty("RoundingIncrement");
    public static final CFNumberFormatterProperty FormatWidth = new CFNumberFormatterProperty("FormatWidth");
    public static final CFNumberFormatterProperty PaddingPosition = new CFNumberFormatterProperty("PaddingPosition");
    public static final CFNumberFormatterProperty PaddingCharacter = new CFNumberFormatterProperty("PaddingCharacter");
    public static final CFNumberFormatterProperty DefaultFormat = new CFNumberFormatterProperty("DefaultFormat");
    public static final CFNumberFormatterProperty Multiplier = new CFNumberFormatterProperty("Multiplier");
    public static final CFNumberFormatterProperty PositivePrefix = new CFNumberFormatterProperty("PositivePrefix");
    public static final CFNumberFormatterProperty PositiveSuffix = new CFNumberFormatterProperty("PositiveSuffix");
    public static final CFNumberFormatterProperty NegativePrefix = new CFNumberFormatterProperty("NegativePrefix");
    public static final CFNumberFormatterProperty NegativeSuffix = new CFNumberFormatterProperty("NegativeSuffix");
    public static final CFNumberFormatterProperty PerMillSymbol = new CFNumberFormatterProperty("PerMillSymbol");
    public static final CFNumberFormatterProperty InternationalCurrencySymbol = new CFNumberFormatterProperty("InternationalCurrencySymbol");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFNumberFormatterProperty CurrencyGroupingSeparator = new CFNumberFormatterProperty("CurrencyGroupingSeparator");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFNumberFormatterProperty IsLenient = new CFNumberFormatterProperty("IsLenient");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFNumberFormatterProperty UseSignificantDigits = new CFNumberFormatterProperty("UseSignificantDigits");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFNumberFormatterProperty MinSignificantDigits = new CFNumberFormatterProperty("MinSignificantDigits");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFNumberFormatterProperty MaxSignificantDigits = new CFNumberFormatterProperty("MaxSignificantDigits");
    /*</constants>*/
    
    private static /*<name>*/CFNumberFormatterProperty/*</name>*/[] values = new /*<name>*/CFNumberFormatterProperty/*</name>*/[] {/*<value_list>*/CurrencyCode, DecimalSeparator, CurrencyDecimalSeparator, AlwaysShowDecimalSeparator, GroupingSeparator, UseGroupingSeparator, PercentSymbol, ZeroSymbol, NaNSymbol, InfinitySymbol, MinusSign, PlusSign, CurrencySymbol, ExponentSymbol, MinIntegerDigits, MaxIntegerDigits, MinFractionDigits, MaxFractionDigits, GroupingSize, SecondaryGroupingSize, RoundingMode, RoundingIncrement, FormatWidth, PaddingPosition, PaddingCharacter, DefaultFormat, Multiplier, PositivePrefix, PositiveSuffix, NegativePrefix, NegativeSuffix, PerMillSymbol, InternationalCurrencySymbol, CurrencyGroupingSeparator, IsLenient, UseSignificantDigits, MinSignificantDigits, MaxSignificantDigits/*</value_list>*/};
    
    /*<name>*/CFNumberFormatterProperty/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CFNumberFormatterProperty/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CFNumberFormatterProperty/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFNumberFormatterProperty/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="kCFNumberFormatterCurrencyCode", optional=true)
        public static native CFString CurrencyCode();
        @GlobalValue(symbol="kCFNumberFormatterDecimalSeparator", optional=true)
        public static native CFString DecimalSeparator();
        @GlobalValue(symbol="kCFNumberFormatterCurrencyDecimalSeparator", optional=true)
        public static native CFString CurrencyDecimalSeparator();
        @GlobalValue(symbol="kCFNumberFormatterAlwaysShowDecimalSeparator", optional=true)
        public static native CFString AlwaysShowDecimalSeparator();
        @GlobalValue(symbol="kCFNumberFormatterGroupingSeparator", optional=true)
        public static native CFString GroupingSeparator();
        @GlobalValue(symbol="kCFNumberFormatterUseGroupingSeparator", optional=true)
        public static native CFString UseGroupingSeparator();
        @GlobalValue(symbol="kCFNumberFormatterPercentSymbol", optional=true)
        public static native CFString PercentSymbol();
        @GlobalValue(symbol="kCFNumberFormatterZeroSymbol", optional=true)
        public static native CFString ZeroSymbol();
        @GlobalValue(symbol="kCFNumberFormatterNaNSymbol", optional=true)
        public static native CFString NaNSymbol();
        @GlobalValue(symbol="kCFNumberFormatterInfinitySymbol", optional=true)
        public static native CFString InfinitySymbol();
        @GlobalValue(symbol="kCFNumberFormatterMinusSign", optional=true)
        public static native CFString MinusSign();
        @GlobalValue(symbol="kCFNumberFormatterPlusSign", optional=true)
        public static native CFString PlusSign();
        @GlobalValue(symbol="kCFNumberFormatterCurrencySymbol", optional=true)
        public static native CFString CurrencySymbol();
        @GlobalValue(symbol="kCFNumberFormatterExponentSymbol", optional=true)
        public static native CFString ExponentSymbol();
        @GlobalValue(symbol="kCFNumberFormatterMinIntegerDigits", optional=true)
        public static native CFString MinIntegerDigits();
        @GlobalValue(symbol="kCFNumberFormatterMaxIntegerDigits", optional=true)
        public static native CFString MaxIntegerDigits();
        @GlobalValue(symbol="kCFNumberFormatterMinFractionDigits", optional=true)
        public static native CFString MinFractionDigits();
        @GlobalValue(symbol="kCFNumberFormatterMaxFractionDigits", optional=true)
        public static native CFString MaxFractionDigits();
        @GlobalValue(symbol="kCFNumberFormatterGroupingSize", optional=true)
        public static native CFString GroupingSize();
        @GlobalValue(symbol="kCFNumberFormatterSecondaryGroupingSize", optional=true)
        public static native CFString SecondaryGroupingSize();
        @GlobalValue(symbol="kCFNumberFormatterRoundingMode", optional=true)
        public static native CFString RoundingMode();
        @GlobalValue(symbol="kCFNumberFormatterRoundingIncrement", optional=true)
        public static native CFString RoundingIncrement();
        @GlobalValue(symbol="kCFNumberFormatterFormatWidth", optional=true)
        public static native CFString FormatWidth();
        @GlobalValue(symbol="kCFNumberFormatterPaddingPosition", optional=true)
        public static native CFString PaddingPosition();
        @GlobalValue(symbol="kCFNumberFormatterPaddingCharacter", optional=true)
        public static native CFString PaddingCharacter();
        @GlobalValue(symbol="kCFNumberFormatterDefaultFormat", optional=true)
        public static native CFString DefaultFormat();
        @GlobalValue(symbol="kCFNumberFormatterMultiplier", optional=true)
        public static native CFString Multiplier();
        @GlobalValue(symbol="kCFNumberFormatterPositivePrefix", optional=true)
        public static native CFString PositivePrefix();
        @GlobalValue(symbol="kCFNumberFormatterPositiveSuffix", optional=true)
        public static native CFString PositiveSuffix();
        @GlobalValue(symbol="kCFNumberFormatterNegativePrefix", optional=true)
        public static native CFString NegativePrefix();
        @GlobalValue(symbol="kCFNumberFormatterNegativeSuffix", optional=true)
        public static native CFString NegativeSuffix();
        @GlobalValue(symbol="kCFNumberFormatterPerMillSymbol", optional=true)
        public static native CFString PerMillSymbol();
        @GlobalValue(symbol="kCFNumberFormatterInternationalCurrencySymbol", optional=true)
        public static native CFString InternationalCurrencySymbol();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFNumberFormatterCurrencyGroupingSeparator", optional=true)
        public static native CFString CurrencyGroupingSeparator();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFNumberFormatterIsLenient", optional=true)
        public static native CFString IsLenient();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFNumberFormatterUseSignificantDigits", optional=true)
        public static native CFString UseSignificantDigits();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFNumberFormatterMinSignificantDigits", optional=true)
        public static native CFString MinSignificantDigits();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFNumberFormatterMaxSignificantDigits", optional=true)
        public static native CFString MaxSignificantDigits();
        /*</values>*/
    }
}
