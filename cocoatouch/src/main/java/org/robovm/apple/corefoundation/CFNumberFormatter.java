/*
 * Copyright (C) 2014 Trillian AB
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

/**
 *
 * <div class="javadoc"></div>
 */
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
    @GlobalValue(symbol="kCFNumberFormatterCurrencyCode")
    public static native CFString KeyCurrencyCode();
    @GlobalValue(symbol="kCFNumberFormatterDecimalSeparator")
    public static native CFString KeyDecimalSeparator();
    @GlobalValue(symbol="kCFNumberFormatterCurrencyDecimalSeparator")
    public static native CFString KeyCurrencyDecimalSeparator();
    @GlobalValue(symbol="kCFNumberFormatterAlwaysShowDecimalSeparator")
    public static native CFString KeyAlwaysShowDecimalSeparator();
    @GlobalValue(symbol="kCFNumberFormatterGroupingSeparator")
    public static native CFString KeyGroupingSeparator();
    @GlobalValue(symbol="kCFNumberFormatterUseGroupingSeparator")
    public static native CFString KeyUseGroupingSeparator();
    @GlobalValue(symbol="kCFNumberFormatterPercentSymbol")
    public static native CFString KeyPercentSymbol();
    @GlobalValue(symbol="kCFNumberFormatterZeroSymbol")
    public static native CFString KeyZeroSymbol();
    @GlobalValue(symbol="kCFNumberFormatterNaNSymbol")
    public static native CFString KeyNaNSymbol();
    @GlobalValue(symbol="kCFNumberFormatterInfinitySymbol")
    public static native CFString KeyInfinitySymbol();
    @GlobalValue(symbol="kCFNumberFormatterMinusSign")
    public static native CFString KeyMinusSign();
    @GlobalValue(symbol="kCFNumberFormatterPlusSign")
    public static native CFString KeyPlusSign();
    @GlobalValue(symbol="kCFNumberFormatterCurrencySymbol")
    public static native CFString KeyCurrencySymbol();
    @GlobalValue(symbol="kCFNumberFormatterExponentSymbol")
    public static native CFString KeyExponentSymbol();
    @GlobalValue(symbol="kCFNumberFormatterMinIntegerDigits")
    public static native CFString KeyMinIntegerDigits();
    @GlobalValue(symbol="kCFNumberFormatterMaxIntegerDigits")
    public static native CFString KeyMaxIntegerDigits();
    @GlobalValue(symbol="kCFNumberFormatterMinFractionDigits")
    public static native CFString KeyMinFractionDigits();
    @GlobalValue(symbol="kCFNumberFormatterMaxFractionDigits")
    public static native CFString KeyMaxFractionDigits();
    @GlobalValue(symbol="kCFNumberFormatterGroupingSize")
    public static native CFString KeyGroupingSize();
    @GlobalValue(symbol="kCFNumberFormatterSecondaryGroupingSize")
    public static native CFString KeySecondaryGroupingSize();
    @GlobalValue(symbol="kCFNumberFormatterRoundingMode")
    public static native CFString KeyRoundingMode();
    @GlobalValue(symbol="kCFNumberFormatterRoundingIncrement")
    public static native CFString KeyRoundingIncrement();
    @GlobalValue(symbol="kCFNumberFormatterFormatWidth")
    public static native CFString KeyFormatWidth();
    @GlobalValue(symbol="kCFNumberFormatterPaddingPosition")
    public static native CFString KeyPaddingPosition();
    @GlobalValue(symbol="kCFNumberFormatterPaddingCharacter")
    public static native CFString KeyPaddingCharacter();
    @GlobalValue(symbol="kCFNumberFormatterDefaultFormat")
    public static native CFString KeyDefaultFormat();
    @GlobalValue(symbol="kCFNumberFormatterMultiplier")
    public static native CFString KeyMultiplier();
    @GlobalValue(symbol="kCFNumberFormatterPositivePrefix")
    public static native CFString KeyPositivePrefix();
    @GlobalValue(symbol="kCFNumberFormatterPositiveSuffix")
    public static native CFString KeyPositiveSuffix();
    @GlobalValue(symbol="kCFNumberFormatterNegativePrefix")
    public static native CFString KeyNegativePrefix();
    @GlobalValue(symbol="kCFNumberFormatterNegativeSuffix")
    public static native CFString KeyNegativeSuffix();
    @GlobalValue(symbol="kCFNumberFormatterPerMillSymbol")
    public static native CFString KeyPerMillSymbol();
    @GlobalValue(symbol="kCFNumberFormatterInternationalCurrencySymbol")
    public static native CFString KeyInternationalCurrencySymbol();
    @GlobalValue(symbol="kCFNumberFormatterCurrencyGroupingSeparator")
    public static native CFString KeyCurrencyGroupingSeparator();
    @GlobalValue(symbol="kCFNumberFormatterIsLenient")
    public static native CFString KeyIsLenient();
    @GlobalValue(symbol="kCFNumberFormatterUseSignificantDigits")
    public static native CFString KeyUseSignificantDigits();
    @GlobalValue(symbol="kCFNumberFormatterMinSignificantDigits")
    public static native CFString KeyMinSignificantDigits();
    @GlobalValue(symbol="kCFNumberFormatterMaxSignificantDigits")
    public static native CFString KeyMaxSignificantDigits();
    
    @Bridge(symbol="CFNumberCreate")
    public static native CFNumber create(CFAllocator allocator, CFNumberType theType, VoidPtr valuePtr);
    @Bridge(symbol="CFNumberGetType")
    public static native CFNumberType getType(CFNumber number);
    @Bridge(symbol="CFNumberGetByteSize")
    public static native @MachineSizedSInt long getByteSize(CFNumber number);
    @Bridge(symbol="CFNumberIsFloatType")
    public static native boolean isFloatType(CFNumber number);
    @Bridge(symbol="CFNumberGetValue")
    public static native boolean getValue(CFNumber number, CFNumberType theType, VoidPtr valuePtr);
    @Bridge(symbol="CFNumberCompare")
    public static native CFComparisonResult compare(CFNumber number, CFNumber otherNumber, VoidPtr context);
    @Bridge(symbol="CFNumberFormatterGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFNumberFormatterCreate")
    public static native CFNumberFormatter create(CFAllocator allocator, CFLocale locale, CFNumberFormatterStyle style);
    @Bridge(symbol="CFNumberFormatterGetLocale")
    public native CFLocale getLocale();
    @Bridge(symbol="CFNumberFormatterGetStyle")
    public native CFNumberFormatterStyle getStyle();
    @Bridge(symbol="CFNumberFormatterGetFormat")
    public native CFString getFormat();
    @Bridge(symbol="CFNumberFormatterSetFormat")
    public native void setFormat(CFString formatString);
    @Bridge(symbol="CFNumberFormatterCreateStringWithNumber")
    public static native CFString createStringWithNumber(CFAllocator allocator, CFNumberFormatter formatter, CFNumber number);
    @Bridge(symbol="CFNumberFormatterCreateStringWithValue")
    public static native CFString createStringWithValue(CFAllocator allocator, CFNumberFormatter formatter, CFNumberType numberType, VoidPtr valuePtr);
    @Bridge(symbol="CFNumberFormatterCreateNumberFromString")
    public static native CFNumber createNumberFromString(CFAllocator allocator, CFNumberFormatter formatter, CFString string, CFRange rangep, CFNumberFormatterOptionFlags options);
    @Bridge(symbol="CFNumberFormatterGetValueFromString")
    public native boolean getValueFromString(CFString string, CFRange rangep, CFNumberType numberType, VoidPtr valuePtr);
    @Bridge(symbol="CFNumberFormatterSetProperty")
    public native void setProperty(CFString key, CFType value);
    @Bridge(symbol="CFNumberFormatterCopyProperty")
    public native CFType copyProperty(CFString key);
    @Bridge(symbol="CFNumberFormatterGetDecimalInfoForCurrencyCode")
    public static native boolean getDecimalInfoForCurrencyCode(CFString currencyCode, IntPtr defaultFractionDigits, DoublePtr roundingIncrement);
    /*</methods>*/
}
