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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
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
        NSString language = (NSString) getComponent(Foundation.LocaleLanguageCode());
        NSString country = (NSString) getComponent(Foundation.LocaleCountryCode());
        NSString variant = (NSString) getComponent(Foundation.LocaleVariantCode());
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
