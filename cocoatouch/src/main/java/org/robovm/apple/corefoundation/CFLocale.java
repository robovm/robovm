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
    @GlobalValue(symbol="kCFLocaleCurrentLocaleDidChangeNotification")
    public static native CFString NotificationCurrentLocaleDidChange();
    @GlobalValue(symbol="kCFLocaleIdentifier")
    public static native CFString KeyIdentifier();
    @GlobalValue(symbol="kCFLocaleLanguageCode")
    public static native CFString KeyLanguageCode();
    @GlobalValue(symbol="kCFLocaleCountryCode")
    public static native CFString KeyCountryCode();
    @GlobalValue(symbol="kCFLocaleScriptCode")
    public static native CFString KeyScriptCode();
    @GlobalValue(symbol="kCFLocaleVariantCode")
    public static native CFString KeyVariantCode();
    @GlobalValue(symbol="kCFLocaleExemplarCharacterSet")
    public static native CFString KeyExemplarCharacterSet();
    @GlobalValue(symbol="kCFLocaleCalendarIdentifier")
    public static native CFString KeyCalendarIdentifier();
    @GlobalValue(symbol="kCFLocaleCalendar")
    public static native CFString CalendarIdentifierLocaleCalendar();
    @GlobalValue(symbol="kCFLocaleCollationIdentifier")
    public static native CFString KeyCollationIdentifier();
    @GlobalValue(symbol="kCFLocaleUsesMetricSystem")
    public static native CFString KeyUsesMetricSystem();
    @GlobalValue(symbol="kCFLocaleMeasurementSystem")
    public static native CFString KeyMeasurementSystem();
    @GlobalValue(symbol="kCFLocaleDecimalSeparator")
    public static native CFString KeyDecimalSeparator();
    @GlobalValue(symbol="kCFLocaleGroupingSeparator")
    public static native CFString KeyGroupingSeparator();
    @GlobalValue(symbol="kCFLocaleCurrencySymbol")
    public static native CFString KeyCurrencySymbol();
    @GlobalValue(symbol="kCFLocaleCurrencyCode")
    public static native CFString KeyCurrencyCode();
    @GlobalValue(symbol="kCFLocaleCollatorIdentifier")
    public static native CFString KeyCollatorIdentifier();
    @GlobalValue(symbol="kCFLocaleQuotationBeginDelimiterKey")
    public static native CFString KeyQuotationBeginDelimiterKey();
    @GlobalValue(symbol="kCFLocaleQuotationEndDelimiterKey")
    public static native CFString KeyQuotationEndDelimiterKey();
    @GlobalValue(symbol="kCFLocaleAlternateQuotationBeginDelimiterKey")
    public static native CFString KeyAlternateQuotationBeginDelimiterKey();
    @GlobalValue(symbol="kCFLocaleAlternateQuotationEndDelimiterKey")
    public static native CFString KeyAlternateQuotationEndDelimiterKey();
    @GlobalValue(symbol="kCFGregorianCalendar")
    public static native CFString CalendarIdentifierGregorianCalendar();
    @GlobalValue(symbol="kCFBuddhistCalendar")
    public static native CFString CalendarIdentifierBuddhistCalendar();
    @GlobalValue(symbol="kCFChineseCalendar")
    public static native CFString CalendarIdentifierChineseCalendar();
    @GlobalValue(symbol="kCFHebrewCalendar")
    public static native CFString CalendarIdentifierHebrewCalendar();
    @GlobalValue(symbol="kCFIslamicCalendar")
    public static native CFString CalendarIdentifierIslamicCalendar();
    @GlobalValue(symbol="kCFIslamicCivilCalendar")
    public static native CFString CalendarIdentifierIslamicCivilCalendar();
    @GlobalValue(symbol="kCFJapaneseCalendar")
    public static native CFString CalendarIdentifierJapaneseCalendar();
    @GlobalValue(symbol="kCFRepublicOfChinaCalendar")
    public static native CFString CalendarIdentifierRepublicOfChinaCalendar();
    @GlobalValue(symbol="kCFPersianCalendar")
    public static native CFString CalendarIdentifierPersianCalendar();
    @GlobalValue(symbol="kCFIndianCalendar")
    public static native CFString CalendarIdentifierIndianCalendar();
    @GlobalValue(symbol="kCFISO8601Calendar")
    public static native CFString CalendarIdentifierISO8601Calendar();
    
    @Bridge(symbol="CFLocaleGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFLocaleGetSystem")
    public static native CFLocale getSystem();
    @Bridge(symbol="CFLocaleCopyCurrent")
    public static native CFLocale copyCurrent();
    @Bridge(symbol="CFLocaleCopyAvailableLocaleIdentifiers")
    public static native CFArray copyAvailableLocaleIdentifiers();
    @Bridge(symbol="CFLocaleCopyISOLanguageCodes")
    public static native CFArray copyISOLanguageCodes();
    @Bridge(symbol="CFLocaleCopyISOCountryCodes")
    public static native CFArray copyISOCountryCodes();
    @Bridge(symbol="CFLocaleCopyISOCurrencyCodes")
    public static native CFArray copyISOCurrencyCodes();
    @Bridge(symbol="CFLocaleCopyCommonISOCurrencyCodes")
    public static native CFArray copyCommonISOCurrencyCodes();
    @Bridge(symbol="CFLocaleCopyPreferredLanguages")
    public static native CFArray copyPreferredLanguages();
    @Bridge(symbol="CFLocaleCreateCanonicalLanguageIdentifierFromString")
    public static native CFString createCanonicalLanguageIdentifierFromString(CFAllocator allocator, CFString localeIdentifier);
    @Bridge(symbol="CFLocaleCreateCanonicalLocaleIdentifierFromString")
    public static native CFString createCanonicalLocaleIdentifierFromString(CFAllocator allocator, CFString localeIdentifier);
    @Bridge(symbol="CFLocaleCreateCanonicalLocaleIdentifierFromScriptManagerCodes")
    public static native CFString createCanonicalLocaleIdentifierFromScriptManagerCodes(CFAllocator allocator, short lcode, short rcode);
    @Bridge(symbol="CFLocaleCreateLocaleIdentifierFromWindowsLocaleCode")
    public static native CFString createLocaleIdentifierFromWindowsLocaleCode(CFAllocator allocator, int lcid);
    @Bridge(symbol="CFLocaleGetWindowsLocaleCodeFromLocaleIdentifier")
    public static native int getWindowsLocaleCodeFromLocaleIdentifier(CFString localeIdentifier);
    @Bridge(symbol="CFLocaleGetLanguageCharacterDirection")
    public static native CFLocaleLanguageDirection getLanguageCharacterDirection(CFString isoLangCode);
    @Bridge(symbol="CFLocaleGetLanguageLineDirection")
    public static native CFLocaleLanguageDirection getLanguageLineDirection(CFString isoLangCode);
    @Bridge(symbol="CFLocaleCreateComponentsFromLocaleIdentifier")
    public static native CFDictionary createComponentsFromLocaleIdentifier(CFAllocator allocator, CFString localeID);
    @Bridge(symbol="CFLocaleCreateLocaleIdentifierFromComponents")
    public static native CFString createLocaleIdentifierFromComponents(CFAllocator allocator, CFDictionary dictionary);
    @Bridge(symbol="CFLocaleCreate")
    public static native CFLocale create(CFAllocator allocator, CFString localeIdentifier);
    @Bridge(symbol="CFLocaleCreateCopy")
    public static native CFLocale createCopy(CFAllocator allocator, CFLocale locale);
    @Bridge(symbol="CFLocaleGetIdentifier")
    public native CFString getIdentifier();
    @Bridge(symbol="CFLocaleGetValue")
    public native CFType getValue(CFString key);
    @Bridge(symbol="CFLocaleCopyDisplayNameForPropertyValue")
    public native CFString copyDisplayNameForPropertyValue(CFString key, CFString value);
    /*</methods>*/
}
