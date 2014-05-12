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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFLocale/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFLocalePtr extends Ptr<CFLocale, CFLocalePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFLocale.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFLocale() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFLocaleCurrentLocaleDidChangeNotification", optional=true)
    public static native CFString NotificationCurrentLocaleDidChange();
    @GlobalValue(symbol="kCFLocaleIdentifier", optional=true)
    public static native CFString KeyIdentifier();
    @GlobalValue(symbol="kCFLocaleLanguageCode", optional=true)
    public static native CFString KeyLanguageCode();
    @GlobalValue(symbol="kCFLocaleCountryCode", optional=true)
    public static native CFString KeyCountryCode();
    @GlobalValue(symbol="kCFLocaleScriptCode", optional=true)
    public static native CFString KeyScriptCode();
    @GlobalValue(symbol="kCFLocaleVariantCode", optional=true)
    public static native CFString KeyVariantCode();
    @GlobalValue(symbol="kCFLocaleExemplarCharacterSet", optional=true)
    public static native CFString KeyExemplarCharacterSet();
    @GlobalValue(symbol="kCFLocaleCalendarIdentifier", optional=true)
    public static native CFString KeyCalendarIdentifier();
    @GlobalValue(symbol="kCFLocaleCalendar", optional=true)
    public static native CFString CalendarIdentifierLocaleCalendar();
    @GlobalValue(symbol="kCFLocaleCollationIdentifier", optional=true)
    public static native CFString KeyCollationIdentifier();
    @GlobalValue(symbol="kCFLocaleUsesMetricSystem", optional=true)
    public static native CFString KeyUsesMetricSystem();
    @GlobalValue(symbol="kCFLocaleMeasurementSystem", optional=true)
    public static native CFString KeyMeasurementSystem();
    @GlobalValue(symbol="kCFLocaleDecimalSeparator", optional=true)
    public static native CFString KeyDecimalSeparator();
    @GlobalValue(symbol="kCFLocaleGroupingSeparator", optional=true)
    public static native CFString KeyGroupingSeparator();
    @GlobalValue(symbol="kCFLocaleCurrencySymbol", optional=true)
    public static native CFString KeyCurrencySymbol();
    @GlobalValue(symbol="kCFLocaleCurrencyCode", optional=true)
    public static native CFString KeyCurrencyCode();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFLocaleCollatorIdentifier", optional=true)
    public static native CFString KeyCollatorIdentifier();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFLocaleQuotationBeginDelimiterKey", optional=true)
    public static native CFString KeyQuotationBeginDelimiterKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFLocaleQuotationEndDelimiterKey", optional=true)
    public static native CFString KeyQuotationEndDelimiterKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFLocaleAlternateQuotationBeginDelimiterKey", optional=true)
    public static native CFString KeyAlternateQuotationBeginDelimiterKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFLocaleAlternateQuotationEndDelimiterKey", optional=true)
    public static native CFString KeyAlternateQuotationEndDelimiterKey();
    @GlobalValue(symbol="kCFGregorianCalendar", optional=true)
    public static native CFString CalendarIdentifierGregorianCalendar();
    @GlobalValue(symbol="kCFBuddhistCalendar", optional=true)
    public static native CFString CalendarIdentifierBuddhistCalendar();
    @GlobalValue(symbol="kCFChineseCalendar", optional=true)
    public static native CFString CalendarIdentifierChineseCalendar();
    @GlobalValue(symbol="kCFHebrewCalendar", optional=true)
    public static native CFString CalendarIdentifierHebrewCalendar();
    @GlobalValue(symbol="kCFIslamicCalendar", optional=true)
    public static native CFString CalendarIdentifierIslamicCalendar();
    @GlobalValue(symbol="kCFIslamicCivilCalendar", optional=true)
    public static native CFString CalendarIdentifierIslamicCivilCalendar();
    @GlobalValue(symbol="kCFJapaneseCalendar", optional=true)
    public static native CFString CalendarIdentifierJapaneseCalendar();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFRepublicOfChinaCalendar", optional=true)
    public static native CFString CalendarIdentifierRepublicOfChinaCalendar();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFPersianCalendar", optional=true)
    public static native CFString CalendarIdentifierPersianCalendar();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFIndianCalendar", optional=true)
    public static native CFString CalendarIdentifierIndianCalendar();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFISO8601Calendar", optional=true)
    public static native CFString CalendarIdentifierISO8601Calendar();
    
    @Bridge(symbol="CFLocaleGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFLocaleGetSystem", optional=true)
    public static native CFLocale getSystem();
    @Bridge(symbol="CFLocaleCopyCurrent", optional=true)
    public static native CFLocale copyCurrent();
    @Bridge(symbol="CFLocaleCopyAvailableLocaleIdentifiers", optional=true)
    public static native CFArray copyAvailableLocaleIdentifiers();
    @Bridge(symbol="CFLocaleCopyISOLanguageCodes", optional=true)
    public static native CFArray copyISOLanguageCodes();
    @Bridge(symbol="CFLocaleCopyISOCountryCodes", optional=true)
    public static native CFArray copyISOCountryCodes();
    @Bridge(symbol="CFLocaleCopyISOCurrencyCodes", optional=true)
    public static native CFArray copyISOCurrencyCodes();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFLocaleCopyCommonISOCurrencyCodes", optional=true)
    public static native CFArray copyCommonISOCurrencyCodes();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFLocaleCopyPreferredLanguages", optional=true)
    public static native CFArray copyPreferredLanguages();
    @Bridge(symbol="CFLocaleCreateCanonicalLanguageIdentifierFromString", optional=true)
    public static native CFString createCanonicalLanguageIdentifierFromString(CFAllocator allocator, CFString localeIdentifier);
    @Bridge(symbol="CFLocaleCreateCanonicalLocaleIdentifierFromString", optional=true)
    public static native CFString createCanonicalLocaleIdentifierFromString(CFAllocator allocator, CFString localeIdentifier);
    @Bridge(symbol="CFLocaleCreateCanonicalLocaleIdentifierFromScriptManagerCodes", optional=true)
    public static native CFString createCanonicalLocaleIdentifierFromScriptManagerCodes(CFAllocator allocator, short lcode, short rcode);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFLocaleCreateLocaleIdentifierFromWindowsLocaleCode", optional=true)
    public static native CFString createLocaleIdentifierFromWindowsLocaleCode(CFAllocator allocator, int lcid);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFLocaleGetWindowsLocaleCodeFromLocaleIdentifier", optional=true)
    public static native int getWindowsLocaleCodeFromLocaleIdentifier(CFString localeIdentifier);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFLocaleGetLanguageCharacterDirection", optional=true)
    public static native CFLocaleLanguageDirection getLanguageCharacterDirection(CFString isoLangCode);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFLocaleGetLanguageLineDirection", optional=true)
    public static native CFLocaleLanguageDirection getLanguageLineDirection(CFString isoLangCode);
    @Bridge(symbol="CFLocaleCreateComponentsFromLocaleIdentifier", optional=true)
    public static native CFDictionary createComponentsFromLocaleIdentifier(CFAllocator allocator, CFString localeID);
    @Bridge(symbol="CFLocaleCreateLocaleIdentifierFromComponents", optional=true)
    public static native CFString createLocaleIdentifierFromComponents(CFAllocator allocator, CFDictionary dictionary);
    @Bridge(symbol="CFLocaleCreate", optional=true)
    public static native CFLocale create(CFAllocator allocator, CFString localeIdentifier);
    @Bridge(symbol="CFLocaleCreateCopy", optional=true)
    public static native CFLocale createCopy(CFAllocator allocator, CFLocale locale);
    @Bridge(symbol="CFLocaleGetIdentifier", optional=true)
    public native CFString getIdentifier();
    @Bridge(symbol="CFLocaleGetValue", optional=true)
    public native CFType getValue(CFString key);
    @Bridge(symbol="CFLocaleCopyDisplayNameForPropertyValue", optional=true)
    public native CFString copyDisplayNameForPropertyValue(CFString key, CFString value);
    /*</methods>*/
}
