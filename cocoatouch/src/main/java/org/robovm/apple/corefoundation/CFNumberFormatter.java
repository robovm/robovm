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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFNumberFormatter/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFNumberFormatterPtr extends Ptr<CFNumberFormatter, CFNumberFormatterPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFNumberFormatter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFNumberFormatter() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCFNumberFormatterCurrencyCode", optional=true)
    public static native CFString KeyCurrencyCode();
    @GlobalValue(symbol="kCFNumberFormatterDecimalSeparator", optional=true)
    public static native CFString KeyDecimalSeparator();
    @GlobalValue(symbol="kCFNumberFormatterCurrencyDecimalSeparator", optional=true)
    public static native CFString KeyCurrencyDecimalSeparator();
    @GlobalValue(symbol="kCFNumberFormatterAlwaysShowDecimalSeparator", optional=true)
    public static native CFString KeyAlwaysShowDecimalSeparator();
    @GlobalValue(symbol="kCFNumberFormatterGroupingSeparator", optional=true)
    public static native CFString KeyGroupingSeparator();
    @GlobalValue(symbol="kCFNumberFormatterUseGroupingSeparator", optional=true)
    public static native CFString KeyUseGroupingSeparator();
    @GlobalValue(symbol="kCFNumberFormatterPercentSymbol", optional=true)
    public static native CFString KeyPercentSymbol();
    @GlobalValue(symbol="kCFNumberFormatterZeroSymbol", optional=true)
    public static native CFString KeyZeroSymbol();
    @GlobalValue(symbol="kCFNumberFormatterNaNSymbol", optional=true)
    public static native CFString KeyNaNSymbol();
    @GlobalValue(symbol="kCFNumberFormatterInfinitySymbol", optional=true)
    public static native CFString KeyInfinitySymbol();
    @GlobalValue(symbol="kCFNumberFormatterMinusSign", optional=true)
    public static native CFString KeyMinusSign();
    @GlobalValue(symbol="kCFNumberFormatterPlusSign", optional=true)
    public static native CFString KeyPlusSign();
    @GlobalValue(symbol="kCFNumberFormatterCurrencySymbol", optional=true)
    public static native CFString KeyCurrencySymbol();
    @GlobalValue(symbol="kCFNumberFormatterExponentSymbol", optional=true)
    public static native CFString KeyExponentSymbol();
    @GlobalValue(symbol="kCFNumberFormatterMinIntegerDigits", optional=true)
    public static native CFString KeyMinIntegerDigits();
    @GlobalValue(symbol="kCFNumberFormatterMaxIntegerDigits", optional=true)
    public static native CFString KeyMaxIntegerDigits();
    @GlobalValue(symbol="kCFNumberFormatterMinFractionDigits", optional=true)
    public static native CFString KeyMinFractionDigits();
    @GlobalValue(symbol="kCFNumberFormatterMaxFractionDigits", optional=true)
    public static native CFString KeyMaxFractionDigits();
    @GlobalValue(symbol="kCFNumberFormatterGroupingSize", optional=true)
    public static native CFString KeyGroupingSize();
    @GlobalValue(symbol="kCFNumberFormatterSecondaryGroupingSize", optional=true)
    public static native CFString KeySecondaryGroupingSize();
    @GlobalValue(symbol="kCFNumberFormatterRoundingMode", optional=true)
    public static native CFString KeyRoundingMode();
    @GlobalValue(symbol="kCFNumberFormatterRoundingIncrement", optional=true)
    public static native CFString KeyRoundingIncrement();
    @GlobalValue(symbol="kCFNumberFormatterFormatWidth", optional=true)
    public static native CFString KeyFormatWidth();
    @GlobalValue(symbol="kCFNumberFormatterPaddingPosition", optional=true)
    public static native CFString KeyPaddingPosition();
    @GlobalValue(symbol="kCFNumberFormatterPaddingCharacter", optional=true)
    public static native CFString KeyPaddingCharacter();
    @GlobalValue(symbol="kCFNumberFormatterDefaultFormat", optional=true)
    public static native CFString KeyDefaultFormat();
    @GlobalValue(symbol="kCFNumberFormatterMultiplier", optional=true)
    public static native CFString KeyMultiplier();
    @GlobalValue(symbol="kCFNumberFormatterPositivePrefix", optional=true)
    public static native CFString KeyPositivePrefix();
    @GlobalValue(symbol="kCFNumberFormatterPositiveSuffix", optional=true)
    public static native CFString KeyPositiveSuffix();
    @GlobalValue(symbol="kCFNumberFormatterNegativePrefix", optional=true)
    public static native CFString KeyNegativePrefix();
    @GlobalValue(symbol="kCFNumberFormatterNegativeSuffix", optional=true)
    public static native CFString KeyNegativeSuffix();
    @GlobalValue(symbol="kCFNumberFormatterPerMillSymbol", optional=true)
    public static native CFString KeyPerMillSymbol();
    @GlobalValue(symbol="kCFNumberFormatterInternationalCurrencySymbol", optional=true)
    public static native CFString KeyInternationalCurrencySymbol();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNumberFormatterCurrencyGroupingSeparator", optional=true)
    public static native CFString KeyCurrencyGroupingSeparator();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNumberFormatterIsLenient", optional=true)
    public static native CFString KeyIsLenient();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNumberFormatterUseSignificantDigits", optional=true)
    public static native CFString KeyUseSignificantDigits();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNumberFormatterMinSignificantDigits", optional=true)
    public static native CFString KeyMinSignificantDigits();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNumberFormatterMaxSignificantDigits", optional=true)
    public static native CFString KeyMaxSignificantDigits();
    
    @Bridge(symbol="CFNumberCreate", optional=true)
    public static native CFNumber create(CFAllocator allocator, CFNumberType theType, VoidPtr valuePtr);
    @Bridge(symbol="CFNumberGetType", optional=true)
    public static native CFNumberType getType(CFNumber number);
    @Bridge(symbol="CFNumberGetByteSize", optional=true)
    public static native @MachineSizedSInt long getByteSize(CFNumber number);
    @Bridge(symbol="CFNumberIsFloatType", optional=true)
    public static native boolean isFloatType(CFNumber number);
    @Bridge(symbol="CFNumberGetValue", optional=true)
    public static native boolean getValue(CFNumber number, CFNumberType theType, VoidPtr valuePtr);
    @Bridge(symbol="CFNumberCompare", optional=true)
    public static native CFComparisonResult compare(CFNumber number, CFNumber otherNumber, VoidPtr context);
    @Bridge(symbol="CFNumberFormatterGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFNumberFormatterCreate", optional=true)
    public static native CFNumberFormatter create(CFAllocator allocator, CFLocale locale, CFNumberFormatterStyle style);
    @Bridge(symbol="CFNumberFormatterGetLocale", optional=true)
    public native CFLocale getLocale();
    @Bridge(symbol="CFNumberFormatterGetStyle", optional=true)
    public native CFNumberFormatterStyle getStyle();
    @Bridge(symbol="CFNumberFormatterGetFormat", optional=true)
    public native CFString getFormat();
    @Bridge(symbol="CFNumberFormatterSetFormat", optional=true)
    public native void setFormat(CFString formatString);
    @Bridge(symbol="CFNumberFormatterCreateStringWithNumber", optional=true)
    public static native CFString createStringWithNumber(CFAllocator allocator, CFNumberFormatter formatter, CFNumber number);
    @Bridge(symbol="CFNumberFormatterCreateStringWithValue", optional=true)
    public static native CFString createStringWithValue(CFAllocator allocator, CFNumberFormatter formatter, CFNumberType numberType, VoidPtr valuePtr);
    @Bridge(symbol="CFNumberFormatterCreateNumberFromString", optional=true)
    public static native CFNumber createNumberFromString(CFAllocator allocator, CFNumberFormatter formatter, CFString string, CFRange rangep, CFNumberFormatterOptionFlags options);
    @Bridge(symbol="CFNumberFormatterGetValueFromString", optional=true)
    public native boolean getValueFromString(CFString string, CFRange rangep, CFNumberType numberType, VoidPtr valuePtr);
    @Bridge(symbol="CFNumberFormatterSetProperty", optional=true)
    public native void setProperty(CFString key, CFType value);
    @Bridge(symbol="CFNumberFormatterCopyProperty", optional=true)
    public native CFType copyProperty(CFString key);
    @Bridge(symbol="CFNumberFormatterGetDecimalInfoForCurrencyCode", optional=true)
    public static native boolean getDecimalInfoForCurrencyCode(CFString currencyCode, IntPtr defaultFractionDigits, DoublePtr roundingIncrement);
    /*</methods>*/
}
