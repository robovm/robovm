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

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSLocale/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSLocalePtr extends Ptr<NSLocale, NSLocalePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSLocale.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
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
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSCurrentLocaleDidChangeNotification", optional=true)
    public static native String NotificationCurrentLocaleDidChange();
    @GlobalValue(symbol="NSLocaleIdentifier", optional=true)
    public static native NSString KeyIdentifier();
    @GlobalValue(symbol="NSLocaleLanguageCode", optional=true)
    public static native NSString KeyLanguageCode();
    @GlobalValue(symbol="NSLocaleCountryCode", optional=true)
    public static native NSString KeyCountryCode();
    @GlobalValue(symbol="NSLocaleScriptCode", optional=true)
    public static native NSString KeyScriptCode();
    @GlobalValue(symbol="NSLocaleVariantCode", optional=true)
    public static native NSString KeyVariantCode();
    @GlobalValue(symbol="NSLocaleExemplarCharacterSet", optional=true)
    public static native NSString KeyExemplarCharacterSet();
    @GlobalValue(symbol="NSLocaleCalendar", optional=true)
    public static native String CalendarIdentifierLocale();
    @GlobalValue(symbol="NSLocaleCollationIdentifier", optional=true)
    public static native NSString KeyCollationIdentifier();
    @GlobalValue(symbol="NSLocaleUsesMetricSystem", optional=true)
    public static native NSString KeyUsesMetricSystem();
    @GlobalValue(symbol="NSLocaleMeasurementSystem", optional=true)
    public static native NSString KeyMeasurementSystem();
    @GlobalValue(symbol="NSLocaleDecimalSeparator", optional=true)
    public static native NSString KeyDecimalSeparator();
    @GlobalValue(symbol="NSLocaleGroupingSeparator", optional=true)
    public static native NSString KeyGroupingSeparator();
    @GlobalValue(symbol="NSLocaleCurrencySymbol", optional=true)
    public static native NSString KeyCurrencySymbol();
    @GlobalValue(symbol="NSLocaleCurrencyCode", optional=true)
    public static native NSString KeyCurrencyCode();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleCollatorIdentifier", optional=true)
    public static native NSString KeyCollatorIdentifier();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleQuotationBeginDelimiterKey", optional=true)
    public static native NSString KeyQuotationBeginDelimiter();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleQuotationEndDelimiterKey", optional=true)
    public static native NSString KeyQuotationEndDelimiter();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleAlternateQuotationBeginDelimiterKey", optional=true)
    public static native NSString KeyAlternateQuotationBeginDelimiter();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleAlternateQuotationEndDelimiterKey", optional=true)
    public static native NSString KeyAlternateQuotationEndDelimiter();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSGregorianCalendar", optional=true)
    public static native String CalendarIdentifierGregorian();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSBuddhistCalendar", optional=true)
    public static native String CalendarIdentifierBuddhist();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSChineseCalendar", optional=true)
    public static native String CalendarIdentifierChinese();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSHebrewCalendar", optional=true)
    public static native String CalendarIdentifierHebrew();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSIslamicCalendar", optional=true)
    public static native String CalendarIdentifierIslamic();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSIslamicCivilCalendar", optional=true)
    public static native String CalendarIdentifierIslamicCivil();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSJapaneseCalendar", optional=true)
    public static native String CalendarIdentifierJapanese();
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSRepublicOfChinaCalendar", optional=true)
    public static native String CalendarIdentifierRepublicOfChina();
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSPersianCalendar", optional=true)
    public static native String CalendarIdentifierPersian();
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSIndianCalendar", optional=true)
    public static native String CalendarIdentifierIndian();
    /**
     * @since Available in iOS 4.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="NSISO8601Calendar", optional=true)
    public static native String CalendarIdentifierISO8601();
    
    @Method(selector = "objectForKey:")
    public native NSObject getComponent(NSString key);
    @Method(selector = "displayNameForKey:value:")
    public native String getDisplayName(NSString key, NSObject value);
    @Method(selector = "localeIdentifier")
    public native String getLocaleIdentifier();
    @Method(selector = "initWithLocaleIdentifier:")
    protected native @Pointer long initWithLocaleIdentifier$(String string);
    /**
     * @since Available in iOS 2.0 and later.
     */
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
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "commonISOCurrencyCodes")
    public static native NSArray<NSString> getCommonISOCurrencyCodes();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "preferredLanguages")
    public static native NSArray<NSString> getPreferredLanguages();
    @Method(selector = "componentsFromLocaleIdentifier:")
    public static native NSDictionary<NSString, ?> getComponentsFromLocaleIdentifier(String string);
    @Method(selector = "localeIdentifierFromComponents:")
    public static native String getLocaleIdentifierFromComponents(NSDictionary<NSString, ?> dict);
    @Method(selector = "canonicalLocaleIdentifierFromString:")
    public static native String getCanonicalLocaleIdentifier(String string);
    @Method(selector = "canonicalLanguageIdentifierFromString:")
    public static native String getCanonicalLanguageIdentifier(String string);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "localeIdentifierFromWindowsLocaleCode:")
    public static native String getLocaleIdentifierFromWindowsLocaleCode(int lcid);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "windowsLocaleCodeFromLocaleIdentifier:")
    public static native int getWindowsLocaleCodeFromLocaleIdentifier(String localeIdentifier);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "characterDirectionForLanguage:")
    public static native NSLocaleLanguageDirection getCharacterDirection(String isoLangCode);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "lineDirectionForLanguage:")
    public static native NSLocaleLanguageDirection getLineDirection(String isoLangCode);
    /*</methods>*/
}
