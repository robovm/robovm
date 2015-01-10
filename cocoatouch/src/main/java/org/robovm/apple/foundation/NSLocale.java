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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSLocale/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 2.0 and later.
         */
        public static NSObject observeCurrentLocaleDidChange(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(CurrentLocaleDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
    }
    
    /*<ptr>*/public static class NSLocalePtr extends Ptr<NSLocale, NSLocalePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSLocale.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected NSLocale(SkipInit skipInit) { super(skipInit); }
    public NSLocale(String string) { super((SkipInit) null); initObject(init(string)); }
    /*</constructors>*/
    
    public NSLocale(Locale locale) {
        super((SkipInit) null);
        initObject(init(locale.toString()));
    }
    
    /*<properties>*/
    @Property(selector = "localeIdentifier")
    public native String getLocaleIdentifier();
    /*</properties>*/
    /*<members>*//*</members>*/
    
    public Locale toLocale() {
        String language = getLanguageCode();
        String country = getCountryCode();
        String variant = getVariantCode();
        if (language != null && country != null && variant != null) {
            return new Locale(language, country, variant);
        }
        if (language != null && country != null) {
            return new Locale(language, country);
        }
        if (language != null) {
            return new Locale(language);
        }
        throw new IllegalArgumentException("Failed to convert NSLocale " + toString() + " to Java Locale");
    }
    
    /* Convenience methods */
    public String getLanguageCode() {
        NSString val = (NSString)getComponent(NSLocaleComponent.LanguageCode);
        return val.toString();
    }
    public String getCountryCode() {
        NSString val = (NSString)getComponent(NSLocaleComponent.CountryCode);
        return val.toString();
    }
    public String getScriptCode() {
        NSString val = (NSString)getComponent(NSLocaleComponent.ScriptCode);
        return val.toString();
    }
    public String getVariantCode() {
        NSString val = (NSString)getComponent(NSLocaleComponent.VariantCode);
        return val.toString();
    }
    public NSCharacterSet getExemplarCharacterSet() {
        NSCharacterSet val = (NSCharacterSet)getComponent(NSLocaleComponent.ExemplarCharacterSet);
        return val;
    }
    public String getCollationIdentifier() {
        NSString val = (NSString)getComponent(NSLocaleComponent.CollationIdentifier);
        return val.toString();
    }
    public boolean isUsingMetricSystem() {
        NSNumber val = (NSNumber)getComponent(NSLocaleComponent.UsesMetricSystem);
        return val.booleanValue();
    }
    public String getMeasurementSystem() {
        NSString val = (NSString)getComponent(NSLocaleComponent.MeasurementSystem);
        return val.toString();
    }
    public String getDecimalSeparator() {
        NSString val = (NSString)getComponent(NSLocaleComponent.DecimalSeparator);
        return val.toString();
    }
    public String getGroupingSeparator() {
        NSString val = (NSString)getComponent(NSLocaleComponent.GroupingSeparator);
        return val.toString();
    }
    public String getCurrencySymbol() {
        NSString val = (NSString)getComponent(NSLocaleComponent.CurrencySymbol);
        return val.toString();
    }
    public String getCurrencyCode() {
        NSString val = (NSString)getComponent(NSLocaleComponent.CurrencyCode);
        return val.toString();
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getCollatorIdentifier() {
        NSString val = (NSString)getComponent(NSLocaleComponent.CollatorIdentifier);
        return val.toString();
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getQuotationBeginDelimiterKey() {
        NSString val = (NSString)getComponent(NSLocaleComponent.QuotationBeginDelimiterKey);
        return val.toString();
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getQuotationEndDelimiterKey() {
        NSString val = (NSString)getComponent(NSLocaleComponent.QuotationEndDelimiterKey);
        return val.toString();
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getAlternateQuotationBeginDelimiterKey() {
        NSString val = (NSString)getComponent(NSLocaleComponent.AlternateQuotationBeginDelimiterKey);
        return val.toString();
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getAlternateQuotationEndDelimiterKey() {
        NSString val = (NSString)getComponent(NSLocaleComponent.AlternateQuotationEndDelimiterKey);
        return val.toString();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSCurrentLocaleDidChangeNotification", optional=true)
    public static native NSString CurrentLocaleDidChangeNotification();
    
    @Method(selector = "objectForKey:")
    public native NSObject getComponent(NSLocaleComponent key);
    @Method(selector = "displayNameForKey:value:")
    public native String getComponentDisplayName(NSLocaleComponent key, NSObject value);
    @Method(selector = "initWithLocaleIdentifier:")
    protected native @Pointer long init(String string);
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
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getAvailableLocaleIdentifiers();
    @Method(selector = "ISOLanguageCodes")
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getISOLanguageCodes();
    @Method(selector = "ISOCountryCodes")
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getISOCountryCodes();
    @Method(selector = "ISOCurrencyCodes")
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getISOCurrencyCodes();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "commonISOCurrencyCodes")
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getCommonISOCurrencyCodes();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "preferredLanguages")
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getPreferredLanguages();
    @Method(selector = "componentsFromLocaleIdentifier:")
    public static native @org.robovm.rt.bro.annotation.Marshaler(NSLocaleComponent.AsMapMarshaler.class) Map<NSLocaleComponent, NSObject> getComponentsFromLocaleIdentifier(String string);
    @Method(selector = "localeIdentifierFromComponents:")
    public static native String getLocaleIdentifierFromComponents(@org.robovm.rt.bro.annotation.Marshaler(NSLocaleComponent.AsMapMarshaler.class) Map<NSLocaleComponent, NSObject> dict);
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
