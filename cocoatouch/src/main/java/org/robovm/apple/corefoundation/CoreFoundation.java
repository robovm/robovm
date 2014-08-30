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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CoreFoundation/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CoreFoundation.class); }/*</bind>*/
    /*<constants>*/
    public static final double CoreFoundationVersionNumber10_0 = 196.40;
    public static final double CoreFoundationVersionNumber10_0_3 = 196.50;
    public static final double CoreFoundationVersionNumber10_1 = 226.00;
    public static final double CoreFoundationVersionNumber10_1_1 = 226.00;
    public static final double CoreFoundationVersionNumber10_1_2 = 227.20;
    public static final double CoreFoundationVersionNumber10_1_3 = 227.20;
    public static final double CoreFoundationVersionNumber10_1_4 = 227.30;
    public static final double CoreFoundationVersionNumber10_2 = 263.00;
    public static final double CoreFoundationVersionNumber10_2_1 = 263.10;
    public static final double CoreFoundationVersionNumber10_2_2 = 263.10;
    public static final double CoreFoundationVersionNumber10_2_3 = 263.30;
    public static final double CoreFoundationVersionNumber10_2_4 = 263.30;
    public static final double CoreFoundationVersionNumber10_2_5 = 263.50;
    public static final double CoreFoundationVersionNumber10_2_6 = 263.50;
    public static final double CoreFoundationVersionNumber10_2_7 = 263.50;
    public static final double CoreFoundationVersionNumber10_2_8 = 263.50;
    public static final double CoreFoundationVersionNumber10_3 = 299.00;
    public static final double CoreFoundationVersionNumber10_3_1 = 299.00;
    public static final double CoreFoundationVersionNumber10_3_2 = 299.00;
    public static final double CoreFoundationVersionNumber10_3_3 = 299.30;
    public static final double CoreFoundationVersionNumber10_3_4 = 299.31;
    public static final double CoreFoundationVersionNumber10_3_5 = 299.31;
    public static final double CoreFoundationVersionNumber10_3_6 = 299.32;
    public static final double CoreFoundationVersionNumber10_3_7 = 299.33;
    public static final double CoreFoundationVersionNumber10_3_8 = 299.33;
    public static final double CoreFoundationVersionNumber10_3_9 = 299.35;
    public static final double CoreFoundationVersionNumber10_4 = 368.00;
    public static final double CoreFoundationVersionNumber10_4_1 = 368.10;
    public static final double CoreFoundationVersionNumber10_4_2 = 368.11;
    public static final double CoreFoundationVersionNumber10_4_3 = 368.18;
    public static final double CoreFoundationVersionNumber10_4_4_Intel = 368.26;
    public static final double CoreFoundationVersionNumber10_4_4_PowerPC = 368.25;
    public static final double CoreFoundationVersionNumber10_4_5_Intel = 368.26;
    public static final double CoreFoundationVersionNumber10_4_5_PowerPC = 368.25;
    public static final double CoreFoundationVersionNumber10_4_6_Intel = 368.26;
    public static final double CoreFoundationVersionNumber10_4_6_PowerPC = 368.25;
    public static final double CoreFoundationVersionNumber10_4_7 = 368.27;
    public static final double CoreFoundationVersionNumber10_4_8 = 368.27;
    public static final double CoreFoundationVersionNumber10_4_9 = 368.28;
    public static final double CoreFoundationVersionNumber10_4_10 = 368.28;
    public static final double CoreFoundationVersionNumber10_4_11 = 368.31;
    public static final double CoreFoundationVersionNumber10_5 = 476.00;
    public static final double CoreFoundationVersionNumber10_5_1 = 476.00;
    public static final double CoreFoundationVersionNumber10_5_2 = 476.10;
    public static final double CoreFoundationVersionNumber10_5_3 = 476.13;
    public static final double CoreFoundationVersionNumber10_5_4 = 476.14;
    public static final double CoreFoundationVersionNumber10_5_5 = 476.15;
    public static final double CoreFoundationVersionNumber10_5_6 = 476.17;
    public static final double CoreFoundationVersionNumber10_5_7 = 476.18;
    public static final double CoreFoundationVersionNumber10_5_8 = 476.19;
    public static final double CoreFoundationVersionNumber10_6 = 550.00;
    public static final double CoreFoundationVersionNumber10_6_1 = 550.00;
    public static final double CoreFoundationVersionNumber10_6_2 = 550.13;
    public static final double CoreFoundationVersionNumber10_6_3 = 550.19;
    public static final double CoreFoundationVersionNumber10_6_4 = 550.29;
    public static final double CoreFoundationVersionNumber10_6_5 = 550.42;
    public static final double CoreFoundationVersionNumber10_6_6 = 550.42;
    public static final double CoreFoundationVersionNumber10_6_7 = 550.42;
    public static final double CoreFoundationVersionNumber10_6_8 = 550.43;
    public static final double CoreFoundationVersionNumber10_7 = 635.00;
    public static final double CoreFoundationVersionNumber10_7_1 = 635.00;
    public static final double CoreFoundationVersionNumber10_7_2 = 635.15;
    public static final double CoreFoundationVersionNumber10_7_3 = 635.19;
    public static final double CoreFoundationVersionNumber10_7_4 = 635.21;
    public static final double CoreFoundationVersionNumber10_7_5 = 635.21;
    public static final double CoreFoundationVersionNumber10_8 = 744.00;
    public static final double CoreFoundationVersionNumber10_8_1 = 744.00;
    public static final double CoreFoundationVersionNumber10_8_2 = 744.12;
    public static final double CoreFoundationVersionNumber10_8_3 = 744.18;
    public static final double CoreFoundationVersionNumber10_8_4 = 744.19;
    public static final double CoreFoundationVersionNumber_iPhoneOS_2_0 = 478.23;
    public static final double CoreFoundationVersionNumber_iPhoneOS_2_1 = 478.26;
    public static final double CoreFoundationVersionNumber_iPhoneOS_2_2 = 478.29;
    public static final double CoreFoundationVersionNumber_iPhoneOS_3_0 = 478.47;
    public static final double CoreFoundationVersionNumber_iPhoneOS_3_1 = 478.52;
    public static final double CoreFoundationVersionNumber_iPhoneOS_3_2 = 478.61;
    public static final double CoreFoundationVersionNumber_iOS_4_0 = 550.32;
    public static final double CoreFoundationVersionNumber_iOS_4_1 = 550.38;
    public static final double CoreFoundationVersionNumber_iOS_4_2 = 550.52;
    public static final double CoreFoundationVersionNumber_iOS_4_3 = 550.52;
    public static final double CoreFoundationVersionNumber_iOS_5_0 = 675.00;
    public static final double CoreFoundationVersionNumber_iOS_5_1 = 690.10;
    public static final double CoreFoundationVersionNumber_iOS_6_0 = 793.00;
    public static final double CoreFoundationVersionNumber_iOS_6_1 = 793.00;
    public static final float StringEncodingInvalidId = 0xffffffff;
    public static final int NotFound = -1;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCFCoreFoundationVersionNumber", optional=true)
    public static native double CoreFoundationVersionNumber();
    @GlobalValue(symbol="kCFNull", optional=true)
    public static native CFNull Null();
    @GlobalValue(symbol="kCFAllocatorDefault", optional=true)
    public static native CFAllocator AllocatorDefault();
    @GlobalValue(symbol="kCFAllocatorSystemDefault", optional=true)
    public static native CFAllocator AllocatorSystemDefault();
    @GlobalValue(symbol="kCFAllocatorMalloc", optional=true)
    public static native CFAllocator AllocatorMalloc();
    @GlobalValue(symbol="kCFAllocatorMallocZone", optional=true)
    public static native CFAllocator AllocatorMallocZone();
    @GlobalValue(symbol="kCFAllocatorNull", optional=true)
    public static native CFAllocator AllocatorNull();
    @GlobalValue(symbol="kCFAllocatorUseContext", optional=true)
    public static native CFAllocator AllocatorUseContext();
    @GlobalValue(symbol="kCFTypeArrayCallBacks", optional=true)
    public static native @ByVal CFArrayCallBacks TypeArrayCallBacks();
    @GlobalValue(symbol="kCFTypeBagCallBacks", optional=true)
    public static native @ByVal CFBagCallBacks TypeBagCallBacks();
    @GlobalValue(symbol="kCFCopyStringBagCallBacks", optional=true)
    public static native @ByVal CFBagCallBacks CopyStringBagCallBacks();
    @GlobalValue(symbol="kCFStringBinaryHeapCallBacks", optional=true)
    public static native @ByVal CFBinaryHeapCallBacks StringBinaryHeapCallBacks();
    @GlobalValue(symbol="kCFTypeDictionaryKeyCallBacks", optional=true)
    public static native @ByVal CFDictionaryKeyCallBacks TypeDictionaryKeyCallBacks();
    @GlobalValue(symbol="kCFCopyStringDictionaryKeyCallBacks", optional=true)
    public static native @ByVal CFDictionaryKeyCallBacks CopyStringDictionaryKeyCallBacks();
    @GlobalValue(symbol="kCFTypeDictionaryValueCallBacks", optional=true)
    public static native @ByVal CFDictionaryValueCallBacks TypeDictionaryValueCallBacks();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFLocaleCurrentLocaleDidChangeNotification", optional=true)
    public static native CFString LocaleCurrentLocaleDidChangeNotification();
    @GlobalValue(symbol="kCFLocaleIdentifier", optional=true)
    public static native CFString LocaleIdentifier();
    @GlobalValue(symbol="kCFLocaleLanguageCode", optional=true)
    public static native CFString LocaleLanguageCode();
    @GlobalValue(symbol="kCFLocaleCountryCode", optional=true)
    public static native CFString LocaleCountryCode();
    @GlobalValue(symbol="kCFLocaleScriptCode", optional=true)
    public static native CFString LocaleScriptCode();
    @GlobalValue(symbol="kCFLocaleVariantCode", optional=true)
    public static native CFString LocaleVariantCode();
    @GlobalValue(symbol="kCFLocaleExemplarCharacterSet", optional=true)
    public static native CFString LocaleExemplarCharacterSet();
    @GlobalValue(symbol="kCFLocaleCalendarIdentifier", optional=true)
    public static native CFString LocaleCalendarIdentifier();
    @GlobalValue(symbol="kCFLocaleCalendar", optional=true)
    public static native CFString LocaleCalendar();
    @GlobalValue(symbol="kCFLocaleCollationIdentifier", optional=true)
    public static native CFString LocaleCollationIdentifier();
    @GlobalValue(symbol="kCFLocaleUsesMetricSystem", optional=true)
    public static native CFString LocaleUsesMetricSystem();
    @GlobalValue(symbol="kCFLocaleMeasurementSystem", optional=true)
    public static native CFString LocaleMeasurementSystem();
    @GlobalValue(symbol="kCFLocaleDecimalSeparator", optional=true)
    public static native CFString LocaleDecimalSeparator();
    @GlobalValue(symbol="kCFLocaleGroupingSeparator", optional=true)
    public static native CFString LocaleGroupingSeparator();
    @GlobalValue(symbol="kCFLocaleCurrencySymbol", optional=true)
    public static native CFString LocaleCurrencySymbol();
    @GlobalValue(symbol="kCFLocaleCurrencyCode", optional=true)
    public static native CFString LocaleCurrencyCode();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFLocaleCollatorIdentifier", optional=true)
    public static native CFString LocaleCollatorIdentifier();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFLocaleQuotationBeginDelimiterKey", optional=true)
    public static native CFString LocaleQuotationBeginDelimiterKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFLocaleQuotationEndDelimiterKey", optional=true)
    public static native CFString LocaleQuotationEndDelimiterKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFLocaleAlternateQuotationBeginDelimiterKey", optional=true)
    public static native CFString LocaleAlternateQuotationBeginDelimiterKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFLocaleAlternateQuotationEndDelimiterKey", optional=true)
    public static native CFString LocaleAlternateQuotationEndDelimiterKey();
    @GlobalValue(symbol="kCFGregorianCalendar", optional=true)
    public static native CFString GregorianCalendar();
    @GlobalValue(symbol="kCFBuddhistCalendar", optional=true)
    public static native CFString BuddhistCalendar();
    @GlobalValue(symbol="kCFChineseCalendar", optional=true)
    public static native CFString ChineseCalendar();
    @GlobalValue(symbol="kCFHebrewCalendar", optional=true)
    public static native CFString HebrewCalendar();
    @GlobalValue(symbol="kCFIslamicCalendar", optional=true)
    public static native CFString IslamicCalendar();
    @GlobalValue(symbol="kCFIslamicCivilCalendar", optional=true)
    public static native CFString IslamicCivilCalendar();
    @GlobalValue(symbol="kCFJapaneseCalendar", optional=true)
    public static native CFString JapaneseCalendar();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFRepublicOfChinaCalendar", optional=true)
    public static native CFString RepublicOfChinaCalendar();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFPersianCalendar", optional=true)
    public static native CFString PersianCalendar();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFIndianCalendar", optional=true)
    public static native CFString IndianCalendar();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFISO8601Calendar", optional=true)
    public static native CFString ISO8601Calendar();
    @GlobalValue(symbol="kCFAbsoluteTimeIntervalSince1970", optional=true)
    public static native double AbsoluteTimeIntervalSince1970();
    @GlobalValue(symbol="kCFAbsoluteTimeIntervalSince1904", optional=true)
    public static native double AbsoluteTimeIntervalSince1904();
    @GlobalValue(symbol="kCFStringTransformStripCombiningMarks", optional=true)
    public static native CFString StringTransformStripCombiningMarks();
    @GlobalValue(symbol="kCFStringTransformToLatin", optional=true)
    public static native CFString StringTransformToLatin();
    @GlobalValue(symbol="kCFStringTransformFullwidthHalfwidth", optional=true)
    public static native CFString StringTransformFullwidthHalfwidth();
    @GlobalValue(symbol="kCFStringTransformLatinKatakana", optional=true)
    public static native CFString StringTransformLatinKatakana();
    @GlobalValue(symbol="kCFStringTransformLatinHiragana", optional=true)
    public static native CFString StringTransformLatinHiragana();
    @GlobalValue(symbol="kCFStringTransformHiraganaKatakana", optional=true)
    public static native CFString StringTransformHiraganaKatakana();
    @GlobalValue(symbol="kCFStringTransformMandarinLatin", optional=true)
    public static native CFString StringTransformMandarinLatin();
    @GlobalValue(symbol="kCFStringTransformLatinHangul", optional=true)
    public static native CFString StringTransformLatinHangul();
    @GlobalValue(symbol="kCFStringTransformLatinArabic", optional=true)
    public static native CFString StringTransformLatinArabic();
    @GlobalValue(symbol="kCFStringTransformLatinHebrew", optional=true)
    public static native CFString StringTransformLatinHebrew();
    @GlobalValue(symbol="kCFStringTransformLatinThai", optional=true)
    public static native CFString StringTransformLatinThai();
    @GlobalValue(symbol="kCFStringTransformLatinCyrillic", optional=true)
    public static native CFString StringTransformLatinCyrillic();
    @GlobalValue(symbol="kCFStringTransformLatinGreek", optional=true)
    public static native CFString StringTransformLatinGreek();
    @GlobalValue(symbol="kCFStringTransformToXMLHex", optional=true)
    public static native CFString StringTransformToXMLHex();
    @GlobalValue(symbol="kCFStringTransformToUnicodeName", optional=true)
    public static native CFString StringTransformToUnicodeName();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFStringTransformStripDiacritics", optional=true)
    public static native CFString StringTransformStripDiacritics();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFTimeZoneSystemTimeZoneDidChangeNotification", optional=true)
    public static native CFString TimeZoneSystemTimeZoneDidChangeNotification();
    @GlobalValue(symbol="kCFDateFormatterIsLenient", optional=true)
    public static native CFString DateFormatterIsLenient();
    @GlobalValue(symbol="kCFDateFormatterTimeZone", optional=true)
    public static native CFString DateFormatterTimeZone();
    @GlobalValue(symbol="kCFDateFormatterCalendarName", optional=true)
    public static native CFString DateFormatterCalendarName();
    @GlobalValue(symbol="kCFDateFormatterDefaultFormat", optional=true)
    public static native CFString DateFormatterDefaultFormat();
    @GlobalValue(symbol="kCFDateFormatterTwoDigitStartDate", optional=true)
    public static native CFString DateFormatterTwoDigitStartDate();
    @GlobalValue(symbol="kCFDateFormatterDefaultDate", optional=true)
    public static native CFString DateFormatterDefaultDate();
    @GlobalValue(symbol="kCFDateFormatterCalendar", optional=true)
    public static native CFString DateFormatterCalendar();
    @GlobalValue(symbol="kCFDateFormatterEraSymbols", optional=true)
    public static native CFString DateFormatterEraSymbols();
    @GlobalValue(symbol="kCFDateFormatterMonthSymbols", optional=true)
    public static native CFString DateFormatterMonthSymbols();
    @GlobalValue(symbol="kCFDateFormatterShortMonthSymbols", optional=true)
    public static native CFString DateFormatterShortMonthSymbols();
    @GlobalValue(symbol="kCFDateFormatterWeekdaySymbols", optional=true)
    public static native CFString DateFormatterWeekdaySymbols();
    @GlobalValue(symbol="kCFDateFormatterShortWeekdaySymbols", optional=true)
    public static native CFString DateFormatterShortWeekdaySymbols();
    @GlobalValue(symbol="kCFDateFormatterAMSymbol", optional=true)
    public static native CFString DateFormatterAMSymbol();
    @GlobalValue(symbol="kCFDateFormatterPMSymbol", optional=true)
    public static native CFString DateFormatterPMSymbol();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterLongEraSymbols", optional=true)
    public static native CFString DateFormatterLongEraSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterVeryShortMonthSymbols", optional=true)
    public static native CFString DateFormatterVeryShortMonthSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterStandaloneMonthSymbols", optional=true)
    public static native CFString DateFormatterStandaloneMonthSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterShortStandaloneMonthSymbols", optional=true)
    public static native CFString DateFormatterShortStandaloneMonthSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterVeryShortStandaloneMonthSymbols", optional=true)
    public static native CFString DateFormatterVeryShortStandaloneMonthSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterVeryShortWeekdaySymbols", optional=true)
    public static native CFString DateFormatterVeryShortWeekdaySymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterStandaloneWeekdaySymbols", optional=true)
    public static native CFString DateFormatterStandaloneWeekdaySymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterShortStandaloneWeekdaySymbols", optional=true)
    public static native CFString DateFormatterShortStandaloneWeekdaySymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterVeryShortStandaloneWeekdaySymbols", optional=true)
    public static native CFString DateFormatterVeryShortStandaloneWeekdaySymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterQuarterSymbols", optional=true)
    public static native CFString DateFormatterQuarterSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterShortQuarterSymbols", optional=true)
    public static native CFString DateFormatterShortQuarterSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterStandaloneQuarterSymbols", optional=true)
    public static native CFString DateFormatterStandaloneQuarterSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterShortStandaloneQuarterSymbols", optional=true)
    public static native CFString DateFormatterShortStandaloneQuarterSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterGregorianStartDate", optional=true)
    public static native CFString DateFormatterGregorianStartDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterDoesRelativeDateFormattingKey", optional=true)
    public static native CFString DateFormatterDoesRelativeDateFormattingKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorDomainPOSIX", optional=true)
    public static native CFString ErrorDomainPOSIX();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorDomainOSStatus", optional=true)
    public static native CFString ErrorDomainOSStatus();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorDomainMach", optional=true)
    public static native CFString ErrorDomainMach();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorDomainCocoa", optional=true)
    public static native CFString ErrorDomainCocoa();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorLocalizedDescriptionKey", optional=true)
    public static native CFString ErrorLocalizedDescriptionKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorLocalizedFailureReasonKey", optional=true)
    public static native CFString ErrorLocalizedFailureReasonKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorLocalizedRecoverySuggestionKey", optional=true)
    public static native CFString ErrorLocalizedRecoverySuggestionKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorDescriptionKey", optional=true)
    public static native CFString ErrorDescriptionKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFErrorUnderlyingErrorKey", optional=true)
    public static native CFString ErrorUnderlyingErrorKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFErrorURLKey", optional=true)
    public static native CFString ErrorURLKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFErrorFilePathKey", optional=true)
    public static native CFString ErrorFilePathKey();
    @GlobalValue(symbol="kCFNumberPositiveInfinity", optional=true)
    public static native CFNumber NumberPositiveInfinity();
    @GlobalValue(symbol="kCFNumberNegativeInfinity", optional=true)
    public static native CFNumber NumberNegativeInfinity();
    @GlobalValue(symbol="kCFNumberNaN", optional=true)
    public static native CFNumber NumberNaN();
    @GlobalValue(symbol="kCFNumberFormatterCurrencyCode", optional=true)
    public static native CFString NumberFormatterCurrencyCode();
    @GlobalValue(symbol="kCFNumberFormatterDecimalSeparator", optional=true)
    public static native CFString NumberFormatterDecimalSeparator();
    @GlobalValue(symbol="kCFNumberFormatterCurrencyDecimalSeparator", optional=true)
    public static native CFString NumberFormatterCurrencyDecimalSeparator();
    @GlobalValue(symbol="kCFNumberFormatterAlwaysShowDecimalSeparator", optional=true)
    public static native CFString NumberFormatterAlwaysShowDecimalSeparator();
    @GlobalValue(symbol="kCFNumberFormatterGroupingSeparator", optional=true)
    public static native CFString NumberFormatterGroupingSeparator();
    @GlobalValue(symbol="kCFNumberFormatterUseGroupingSeparator", optional=true)
    public static native CFString NumberFormatterUseGroupingSeparator();
    @GlobalValue(symbol="kCFNumberFormatterPercentSymbol", optional=true)
    public static native CFString NumberFormatterPercentSymbol();
    @GlobalValue(symbol="kCFNumberFormatterZeroSymbol", optional=true)
    public static native CFString NumberFormatterZeroSymbol();
    @GlobalValue(symbol="kCFNumberFormatterNaNSymbol", optional=true)
    public static native CFString NumberFormatterNaNSymbol();
    @GlobalValue(symbol="kCFNumberFormatterInfinitySymbol", optional=true)
    public static native CFString NumberFormatterInfinitySymbol();
    @GlobalValue(symbol="kCFNumberFormatterMinusSign", optional=true)
    public static native CFString NumberFormatterMinusSign();
    @GlobalValue(symbol="kCFNumberFormatterPlusSign", optional=true)
    public static native CFString NumberFormatterPlusSign();
    @GlobalValue(symbol="kCFNumberFormatterCurrencySymbol", optional=true)
    public static native CFString NumberFormatterCurrencySymbol();
    @GlobalValue(symbol="kCFNumberFormatterExponentSymbol", optional=true)
    public static native CFString NumberFormatterExponentSymbol();
    @GlobalValue(symbol="kCFNumberFormatterMinIntegerDigits", optional=true)
    public static native CFString NumberFormatterMinIntegerDigits();
    @GlobalValue(symbol="kCFNumberFormatterMaxIntegerDigits", optional=true)
    public static native CFString NumberFormatterMaxIntegerDigits();
    @GlobalValue(symbol="kCFNumberFormatterMinFractionDigits", optional=true)
    public static native CFString NumberFormatterMinFractionDigits();
    @GlobalValue(symbol="kCFNumberFormatterMaxFractionDigits", optional=true)
    public static native CFString NumberFormatterMaxFractionDigits();
    @GlobalValue(symbol="kCFNumberFormatterGroupingSize", optional=true)
    public static native CFString NumberFormatterGroupingSize();
    @GlobalValue(symbol="kCFNumberFormatterSecondaryGroupingSize", optional=true)
    public static native CFString NumberFormatterSecondaryGroupingSize();
    @GlobalValue(symbol="kCFNumberFormatterRoundingMode", optional=true)
    public static native CFString NumberFormatterRoundingMode();
    @GlobalValue(symbol="kCFNumberFormatterRoundingIncrement", optional=true)
    public static native CFString NumberFormatterRoundingIncrement();
    @GlobalValue(symbol="kCFNumberFormatterFormatWidth", optional=true)
    public static native CFString NumberFormatterFormatWidth();
    @GlobalValue(symbol="kCFNumberFormatterPaddingPosition", optional=true)
    public static native CFString NumberFormatterPaddingPosition();
    @GlobalValue(symbol="kCFNumberFormatterPaddingCharacter", optional=true)
    public static native CFString NumberFormatterPaddingCharacter();
    @GlobalValue(symbol="kCFNumberFormatterDefaultFormat", optional=true)
    public static native CFString NumberFormatterDefaultFormat();
    @GlobalValue(symbol="kCFNumberFormatterMultiplier", optional=true)
    public static native CFString NumberFormatterMultiplier();
    @GlobalValue(symbol="kCFNumberFormatterPositivePrefix", optional=true)
    public static native CFString NumberFormatterPositivePrefix();
    @GlobalValue(symbol="kCFNumberFormatterPositiveSuffix", optional=true)
    public static native CFString NumberFormatterPositiveSuffix();
    @GlobalValue(symbol="kCFNumberFormatterNegativePrefix", optional=true)
    public static native CFString NumberFormatterNegativePrefix();
    @GlobalValue(symbol="kCFNumberFormatterNegativeSuffix", optional=true)
    public static native CFString NumberFormatterNegativeSuffix();
    @GlobalValue(symbol="kCFNumberFormatterPerMillSymbol", optional=true)
    public static native CFString NumberFormatterPerMillSymbol();
    @GlobalValue(symbol="kCFNumberFormatterInternationalCurrencySymbol", optional=true)
    public static native CFString NumberFormatterInternationalCurrencySymbol();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNumberFormatterCurrencyGroupingSeparator", optional=true)
    public static native CFString NumberFormatterCurrencyGroupingSeparator();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNumberFormatterIsLenient", optional=true)
    public static native CFString NumberFormatterIsLenient();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNumberFormatterUseSignificantDigits", optional=true)
    public static native CFString NumberFormatterUseSignificantDigits();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNumberFormatterMinSignificantDigits", optional=true)
    public static native CFString NumberFormatterMinSignificantDigits();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFNumberFormatterMaxSignificantDigits", optional=true)
    public static native CFString NumberFormatterMaxSignificantDigits();
    @GlobalValue(symbol="kCFPreferencesAnyApplication", optional=true)
    public static native CFString PreferencesAnyApplication();
    @GlobalValue(symbol="kCFPreferencesCurrentApplication", optional=true)
    public static native CFString PreferencesCurrentApplication();
    @GlobalValue(symbol="kCFPreferencesAnyHost", optional=true)
    public static native CFString PreferencesAnyHost();
    @GlobalValue(symbol="kCFPreferencesCurrentHost", optional=true)
    public static native CFString PreferencesCurrentHost();
    @GlobalValue(symbol="kCFPreferencesAnyUser", optional=true)
    public static native CFString PreferencesAnyUser();
    @GlobalValue(symbol="kCFPreferencesCurrentUser", optional=true)
    public static native CFString PreferencesCurrentUser();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLKeysOfUnsetValuesKey", optional=true)
    public static native CFString URLKeysOfUnsetValuesKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLNameKey", optional=true)
    public static native CFString URLNameKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLLocalizedNameKey", optional=true)
    public static native CFString URLLocalizedNameKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsRegularFileKey", optional=true)
    public static native CFString URLIsRegularFileKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsDirectoryKey", optional=true)
    public static native CFString URLIsDirectoryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsSymbolicLinkKey", optional=true)
    public static native CFString URLIsSymbolicLinkKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsVolumeKey", optional=true)
    public static native CFString URLIsVolumeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsPackageKey", optional=true)
    public static native CFString URLIsPackageKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsSystemImmutableKey", optional=true)
    public static native CFString URLIsSystemImmutableKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsUserImmutableKey", optional=true)
    public static native CFString URLIsUserImmutableKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsHiddenKey", optional=true)
    public static native CFString URLIsHiddenKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLHasHiddenExtensionKey", optional=true)
    public static native CFString URLHasHiddenExtensionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLCreationDateKey", optional=true)
    public static native CFString URLCreationDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLContentAccessDateKey", optional=true)
    public static native CFString URLContentAccessDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLContentModificationDateKey", optional=true)
    public static native CFString URLContentModificationDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLAttributeModificationDateKey", optional=true)
    public static native CFString URLAttributeModificationDateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLLinkCountKey", optional=true)
    public static native CFString URLLinkCountKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLParentDirectoryURLKey", optional=true)
    public static native CFString URLParentDirectoryURLKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeURLKey", optional=true)
    public static native CFString URLVolumeURLKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLTypeIdentifierKey", optional=true)
    public static native CFString URLTypeIdentifierKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLLocalizedTypeDescriptionKey", optional=true)
    public static native CFString URLLocalizedTypeDescriptionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLLabelNumberKey", optional=true)
    public static native CFString URLLabelNumberKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLLabelColorKey", optional=true)
    public static native CFString URLLabelColorKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLLocalizedLabelKey", optional=true)
    public static native CFString URLLocalizedLabelKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLEffectiveIconKey", optional=true)
    public static native CFString URLEffectiveIconKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLCustomIconKey", optional=true)
    public static native CFString URLCustomIconKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceIdentifierKey", optional=true)
    public static native CFString URLFileResourceIdentifierKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIdentifierKey", optional=true)
    public static native CFString URLVolumeIdentifierKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLPreferredIOBlockSizeKey", optional=true)
    public static native CFString URLPreferredIOBlockSizeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsReadableKey", optional=true)
    public static native CFString URLIsReadableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsWritableKey", optional=true)
    public static native CFString URLIsWritableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsExecutableKey", optional=true)
    public static native CFString URLIsExecutableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileSecurityKey", optional=true)
    public static native CFString URLFileSecurityKey();
    /**
     * @since Available in iOS 5.1 and later.
     */
    @GlobalValue(symbol="kCFURLIsExcludedFromBackupKey", optional=true)
    public static native CFString URLIsExcludedFromBackupKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kCFURLPathKey", optional=true)
    public static native CFString URLPathKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsMountTriggerKey", optional=true)
    public static native CFString URLIsMountTriggerKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeKey", optional=true)
    public static native CFString URLFileResourceTypeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeNamedPipe", optional=true)
    public static native CFString URLFileResourceTypeNamedPipe();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeCharacterSpecial", optional=true)
    public static native CFString URLFileResourceTypeCharacterSpecial();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeDirectory", optional=true)
    public static native CFString URLFileResourceTypeDirectory();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeBlockSpecial", optional=true)
    public static native CFString URLFileResourceTypeBlockSpecial();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeRegular", optional=true)
    public static native CFString URLFileResourceTypeRegular();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeSymbolicLink", optional=true)
    public static native CFString URLFileResourceTypeSymbolicLink();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeSocket", optional=true)
    public static native CFString URLFileResourceTypeSocket();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileResourceTypeUnknown", optional=true)
    public static native CFString URLFileResourceTypeUnknown();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileSizeKey", optional=true)
    public static native CFString URLFileSizeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLFileAllocatedSizeKey", optional=true)
    public static native CFString URLFileAllocatedSizeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLTotalFileSizeKey", optional=true)
    public static native CFString URLTotalFileSizeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLTotalFileAllocatedSizeKey", optional=true)
    public static native CFString URLTotalFileAllocatedSizeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsAliasFileKey", optional=true)
    public static native CFString URLIsAliasFileKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeLocalizedFormatDescriptionKey", optional=true)
    public static native CFString URLVolumeLocalizedFormatDescriptionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeTotalCapacityKey", optional=true)
    public static native CFString URLVolumeTotalCapacityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeAvailableCapacityKey", optional=true)
    public static native CFString URLVolumeAvailableCapacityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeResourceCountKey", optional=true)
    public static native CFString URLVolumeResourceCountKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsPersistentIDsKey", optional=true)
    public static native CFString URLVolumeSupportsPersistentIDsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsSymbolicLinksKey", optional=true)
    public static native CFString URLVolumeSupportsSymbolicLinksKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsHardLinksKey", optional=true)
    public static native CFString URLVolumeSupportsHardLinksKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsJournalingKey", optional=true)
    public static native CFString URLVolumeSupportsJournalingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsJournalingKey", optional=true)
    public static native CFString URLVolumeIsJournalingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsSparseFilesKey", optional=true)
    public static native CFString URLVolumeSupportsSparseFilesKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsZeroRunsKey", optional=true)
    public static native CFString URLVolumeSupportsZeroRunsKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsCaseSensitiveNamesKey", optional=true)
    public static native CFString URLVolumeSupportsCaseSensitiveNamesKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsCasePreservedNamesKey", optional=true)
    public static native CFString URLVolumeSupportsCasePreservedNamesKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsRootDirectoryDatesKey", optional=true)
    public static native CFString URLVolumeSupportsRootDirectoryDatesKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsVolumeSizesKey", optional=true)
    public static native CFString URLVolumeSupportsVolumeSizesKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsRenamingKey", optional=true)
    public static native CFString URLVolumeSupportsRenamingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsAdvisoryFileLockingKey", optional=true)
    public static native CFString URLVolumeSupportsAdvisoryFileLockingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeSupportsExtendedSecurityKey", optional=true)
    public static native CFString URLVolumeSupportsExtendedSecurityKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsBrowsableKey", optional=true)
    public static native CFString URLVolumeIsBrowsableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeMaximumFileSizeKey", optional=true)
    public static native CFString URLVolumeMaximumFileSizeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsEjectableKey", optional=true)
    public static native CFString URLVolumeIsEjectableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsRemovableKey", optional=true)
    public static native CFString URLVolumeIsRemovableKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsInternalKey", optional=true)
    public static native CFString URLVolumeIsInternalKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsAutomountedKey", optional=true)
    public static native CFString URLVolumeIsAutomountedKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsLocalKey", optional=true)
    public static native CFString URLVolumeIsLocalKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeIsReadOnlyKey", optional=true)
    public static native CFString URLVolumeIsReadOnlyKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeCreationDateKey", optional=true)
    public static native CFString URLVolumeCreationDateKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeURLForRemountingKey", optional=true)
    public static native CFString URLVolumeURLForRemountingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeUUIDStringKey", optional=true)
    public static native CFString URLVolumeUUIDStringKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeNameKey", optional=true)
    public static native CFString URLVolumeNameKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLVolumeLocalizedNameKey", optional=true)
    public static native CFString URLVolumeLocalizedNameKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLIsUbiquitousItemKey", optional=true)
    public static native CFString URLIsUbiquitousItemKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemHasUnresolvedConflictsKey", optional=true)
    public static native CFString URLUbiquitousItemHasUnresolvedConflictsKey();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLUbiquitousItemIsDownloadedKey", optional=true)
    public static native CFString URLUbiquitousItemIsDownloadedKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemIsDownloadingKey", optional=true)
    public static native CFString URLUbiquitousItemIsDownloadingKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemIsUploadedKey", optional=true)
    public static native CFString URLUbiquitousItemIsUploadedKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemIsUploadingKey", optional=true)
    public static native CFString URLUbiquitousItemIsUploadingKey();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLUbiquitousItemPercentDownloadedKey", optional=true)
    public static native CFString URLUbiquitousItemPercentDownloadedKey();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLUbiquitousItemPercentUploadedKey", optional=true)
    public static native CFString URLUbiquitousItemPercentUploadedKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusKey", optional=true)
    public static native CFString URLUbiquitousItemDownloadingStatusKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingErrorKey", optional=true)
    public static native CFString URLUbiquitousItemDownloadingErrorKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemUploadingErrorKey", optional=true)
    public static native CFString URLUbiquitousItemUploadingErrorKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusNotDownloaded", optional=true)
    public static native CFString URLUbiquitousItemDownloadingStatusNotDownloaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusDownloaded", optional=true)
    public static native CFString URLUbiquitousItemDownloadingStatusDownloaded();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCFURLUbiquitousItemDownloadingStatusCurrent", optional=true)
    public static native CFString URLUbiquitousItemDownloadingStatusCurrent();
    @GlobalValue(symbol="kCFRunLoopDefaultMode", optional=true)
    public static native CFString RunLoopDefaultMode();
    @GlobalValue(symbol="kCFRunLoopCommonModes", optional=true)
    public static native CFString RunLoopCommonModes();
    @GlobalValue(symbol="kCFSocketCommandKey", optional=true)
    public static native CFString SocketCommandKey();
    @GlobalValue(symbol="kCFSocketNameKey", optional=true)
    public static native CFString SocketNameKey();
    @GlobalValue(symbol="kCFSocketValueKey", optional=true)
    public static native CFString SocketValueKey();
    @GlobalValue(symbol="kCFSocketResultKey", optional=true)
    public static native CFString SocketResultKey();
    @GlobalValue(symbol="kCFSocketErrorKey", optional=true)
    public static native CFString SocketErrorKey();
    @GlobalValue(symbol="kCFSocketRegisterCommand", optional=true)
    public static native CFString SocketRegisterCommand();
    @GlobalValue(symbol="kCFSocketRetrieveCommand", optional=true)
    public static native CFString SocketRetrieveCommand();
    @GlobalValue(symbol="kCFStreamPropertyDataWritten", optional=true)
    public static native CFString StreamPropertyDataWritten();
    @GlobalValue(symbol="kCFStreamPropertyAppendToFile", optional=true)
    public static native CFString StreamPropertyAppendToFile();
    @GlobalValue(symbol="kCFStreamPropertyFileCurrentOffset", optional=true)
    public static native CFString StreamPropertyFileCurrentOffset();
    @GlobalValue(symbol="kCFStreamPropertySocketNativeHandle", optional=true)
    public static native CFString StreamPropertySocketNativeHandle();
    @GlobalValue(symbol="kCFStreamPropertySocketRemoteHostName", optional=true)
    public static native CFString StreamPropertySocketRemoteHostName();
    @GlobalValue(symbol="kCFStreamPropertySocketRemotePortNumber", optional=true)
    public static native CFString StreamPropertySocketRemotePortNumber();
    @GlobalValue(symbol="kCFTypeSetCallBacks", optional=true)
    public static native @ByVal CFSetCallBacks TypeSetCallBacks();
    @GlobalValue(symbol="kCFCopyStringSetCallBacks", optional=true)
    public static native @ByVal CFSetCallBacks CopyStringSetCallBacks();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLFileExists", optional=true)
    public static native CFString URLFileExists();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLFileDirectoryContents", optional=true)
    public static native CFString URLFileDirectoryContents();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLFileLength", optional=true)
    public static native CFString URLFileLength();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLFileLastModificationTime", optional=true)
    public static native CFString URLFileLastModificationTime();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLFilePOSIXMode", optional=true)
    public static native CFString URLFilePOSIXMode();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLFileOwnerID", optional=true)
    public static native CFString URLFileOwnerID();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLHTTPStatusCode", optional=true)
    public static native CFString URLHTTPStatusCode();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="kCFURLHTTPStatusLine", optional=true)
    public static native CFString URLHTTPStatusLine();
    @GlobalValue(symbol="kCFBundleInfoDictionaryVersionKey", optional=true)
    public static native CFString BundleInfoDictionaryVersionKey();
    @GlobalValue(symbol="kCFBundleExecutableKey", optional=true)
    public static native CFString BundleExecutableKey();
    @GlobalValue(symbol="kCFBundleIdentifierKey", optional=true)
    public static native CFString BundleIdentifierKey();
    @GlobalValue(symbol="kCFBundleVersionKey", optional=true)
    public static native CFString BundleVersionKey();
    @GlobalValue(symbol="kCFBundleDevelopmentRegionKey", optional=true)
    public static native CFString BundleDevelopmentRegionKey();
    @GlobalValue(symbol="kCFBundleNameKey", optional=true)
    public static native CFString BundleNameKey();
    @GlobalValue(symbol="kCFBundleLocalizationsKey", optional=true)
    public static native CFString BundleLocalizationsKey();
    @GlobalValue(symbol="kCFPlugInDynamicRegistrationKey", optional=true)
    public static native CFString PlugInDynamicRegistrationKey();
    @GlobalValue(symbol="kCFPlugInDynamicRegisterFunctionKey", optional=true)
    public static native CFString PlugInDynamicRegisterFunctionKey();
    @GlobalValue(symbol="kCFPlugInUnloadFunctionKey", optional=true)
    public static native CFString PlugInUnloadFunctionKey();
    @GlobalValue(symbol="kCFPlugInFactoriesKey", optional=true)
    public static native CFString PlugInFactoriesKey();
    @GlobalValue(symbol="kCFPlugInTypesKey", optional=true)
    public static native CFString PlugInTypesKey();
    /*</methods>*/
}
