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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.security.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSLocale/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSLocalePtr extends Ptr<NSLocale, NSLocalePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSLocale.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSLocale() {}
    protected NSLocale(SkipInit skipInit) { super(skipInit); }
    public NSLocale(String string) { super((SkipInit) null); initObject(initWithLocaleIdentifier$(string)); }
    /*</constructors>*/
    
    public NSLocale(Locale locale) {
        super((SkipInit) null);
        initObject(initWithLocaleIdentifier$(locale.toString()));
    }
    
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    
    public Locale toLocale() {
        NSString language = (NSString) getComponent(KeyLanguageCode());
        NSString country = (NSString) getComponent(KeyCountryCode());
        NSString variant = (NSString) getComponent(KeyVariantCode());
        if (language != null && country != null && variant != null) {
            return new Locale(language.toString(), country.toString(), variant.toString());
        }
        if (language != null && country != null) {
            return new Locale(language.toString(), country.toString());
        }
        if (language != null) {
            return new Locale(language.toString());
        }
        throw new IllegalArgumentException("Failed to convert NSLocale " 
                + toString() + " to Java Locale");
    }
    
    /*<methods>*/
    @GlobalValue(symbol="NSCurrentLocaleDidChangeNotification")
    public static native String NotificationCurrentLocaleDidChange();
    @GlobalValue(symbol="NSLocaleIdentifier")
    public static native NSString KeyIdentifier();
    @GlobalValue(symbol="NSLocaleLanguageCode")
    public static native NSString KeyLanguageCode();
    @GlobalValue(symbol="NSLocaleCountryCode")
    public static native NSString KeyCountryCode();
    @GlobalValue(symbol="NSLocaleScriptCode")
    public static native NSString KeyScriptCode();
    @GlobalValue(symbol="NSLocaleVariantCode")
    public static native NSString KeyVariantCode();
    @GlobalValue(symbol="NSLocaleExemplarCharacterSet")
    public static native NSString KeyExemplarCharacterSet();
    @GlobalValue(symbol="NSLocaleCalendar")
    public static native String CalendarIdentifierLocale();
    @GlobalValue(symbol="NSLocaleCollationIdentifier")
    public static native NSString KeyCollationIdentifier();
    @GlobalValue(symbol="NSLocaleUsesMetricSystem")
    public static native NSString KeyUsesMetricSystem();
    @GlobalValue(symbol="NSLocaleMeasurementSystem")
    public static native NSString KeyMeasurementSystem();
    @GlobalValue(symbol="NSLocaleDecimalSeparator")
    public static native NSString KeyDecimalSeparator();
    @GlobalValue(symbol="NSLocaleGroupingSeparator")
    public static native NSString KeyGroupingSeparator();
    @GlobalValue(symbol="NSLocaleCurrencySymbol")
    public static native NSString KeyCurrencySymbol();
    @GlobalValue(symbol="NSLocaleCurrencyCode")
    public static native NSString KeyCurrencyCode();
    @GlobalValue(symbol="NSLocaleCollatorIdentifier")
    public static native NSString KeyCollatorIdentifier();
    @GlobalValue(symbol="NSLocaleQuotationBeginDelimiterKey")
    public static native NSString KeyQuotationBeginDelimiter();
    @GlobalValue(symbol="NSLocaleQuotationEndDelimiterKey")
    public static native NSString KeyQuotationEndDelimiter();
    @GlobalValue(symbol="NSLocaleAlternateQuotationBeginDelimiterKey")
    public static native NSString KeyAlternateQuotationBeginDelimiter();
    @GlobalValue(symbol="NSLocaleAlternateQuotationEndDelimiterKey")
    public static native NSString KeyAlternateQuotationEndDelimiter();
    @GlobalValue(symbol="NSGregorianCalendar")
    public static native String CalendarIdentifierGregorian();
    @GlobalValue(symbol="NSBuddhistCalendar")
    public static native String CalendarIdentifierBuddhist();
    @GlobalValue(symbol="NSChineseCalendar")
    public static native String CalendarIdentifierChinese();
    @GlobalValue(symbol="NSHebrewCalendar")
    public static native String CalendarIdentifierHebrew();
    @GlobalValue(symbol="NSIslamicCalendar")
    public static native String CalendarIdentifierIslamic();
    @GlobalValue(symbol="NSIslamicCivilCalendar")
    public static native String CalendarIdentifierIslamicCivil();
    @GlobalValue(symbol="NSJapaneseCalendar")
    public static native String CalendarIdentifierJapanese();
    @GlobalValue(symbol="NSRepublicOfChinaCalendar")
    public static native String CalendarIdentifierRepublicOfChina();
    @GlobalValue(symbol="NSPersianCalendar")
    public static native String CalendarIdentifierPersian();
    @GlobalValue(symbol="NSIndianCalendar")
    public static native String CalendarIdentifierIndian();
    @GlobalValue(symbol="NSISO8601Calendar")
    public static native String CalendarIdentifierISO8601();
    
    @Method(selector = "objectForKey:")
    public native NSObject getComponent(NSString key);
    @Method(selector = "displayNameForKey:value:")
    public native String getDisplayName(NSString key, NSObject value);
    @Method(selector = "localeIdentifier")
    public native String getLocaleIdentifier();
    @Method(selector = "initWithLocaleIdentifier:")
    protected native @Pointer long initWithLocaleIdentifier$(String string);
    @Method(selector = "autoupdatingCurrentLocale")
    public static native NSLocale getAutoupdatingCurrentLocale();
    @Method(selector = "currentLocale")
    public static native NSLocale getCurrentLocale();
    @Method(selector = "systemLocale")
    public static native NSLocale getSystemLocale();
    @Method(selector = "availableLocaleIdentifiers")
    public static native NSArray<NSString> getAvailableLocaleIdentifiers();
    @Method(selector = "ISOLanguageCodes")
    public static native NSArray<NSString> getISOLanguageCodes();
    @Method(selector = "ISOCountryCodes")
    public static native NSArray<NSString> getISOCountryCodes();
    @Method(selector = "ISOCurrencyCodes")
    public static native NSArray<NSString> getISOCurrencyCodes();
    @Method(selector = "commonISOCurrencyCodes")
    public static native NSArray<NSString> getCommonISOCurrencyCodes();
    @Method(selector = "preferredLanguages")
    public static native NSArray<NSString> getPreferredLanguages();
    @Method(selector = "componentsFromLocaleIdentifier:")
    public static native NSDictionary<NSString, ?> getCmponentsFromLocaleIdentifier(String string);
    @Method(selector = "localeIdentifierFromComponents:")
    public static native String getLocaleIdentifierFromComponents(NSDictionary<NSString, ?> dict);
    @Method(selector = "canonicalLocaleIdentifierFromString:")
    public static native String getCanonicalLocaleIdentifier(String string);
    @Method(selector = "canonicalLanguageIdentifierFromString:")
    public static native String getCanonicalLanguageIdentifier(String string);
    @Method(selector = "localeIdentifierFromWindowsLocaleCode:")
    public static native String getLocaleIdentifierFromWindowsLocaleCode(int lcid);
    @Method(selector = "windowsLocaleCodeFromLocaleIdentifier:")
    public static native int getWindowsLocaleCodeFromLocaleIdentifier(String localeIdentifier);
    @Method(selector = "characterDirectionForLanguage:")
    public static native NSLocaleLanguageDirection getCharacterDirection(String isoLangCode);
    @Method(selector = "lineDirectionForLanguage:")
    public static native NSLocaleLanguageDirection getLineDirection(String isoLangCode);
    /*</methods>*/
}
