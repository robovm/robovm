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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFLocale/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        public static NSObject observeCurrentLocaleDidChange(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(CurrentLocaleDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
    }
    
    /*<ptr>*/public static class CFLocalePtr extends Ptr<CFLocale, CFLocalePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFLocale.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFLocale() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static String getCanonicalLanguageIdentifier(String localeIdentifier) {
        return getCanonicalLanguageIdentifier(null, localeIdentifier);
    }
    public static String getCanonicalLocaleIdentifier(String localeIdentifier) {
        return getCanonicalLocaleIdentifier(null, localeIdentifier);
    }
    public static String getCanonicalLocaleIdentifier(short lcode, short rcode) {
        return getCanonicalLocaleIdentifier(null, lcode, rcode);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static String getLocaleIdentifierFromWindowsLocaleCode(int lcid) {
        return getLocaleIdentifierFromWindowsLocaleCode(null, lcid);
    }
    public static CFLocaleComponents getComponentsFromLocaleIdentifier(String localeID) {
        return getComponentsFromLocaleIdentifier(null, localeID);
    }
    public static String getLocaleIdentifierFromComponents(CFLocaleComponents components) {
        return getLocaleIdentifierFromComponents(null, components);
    }
    public static CFLocale create(String localeIdentifier) {
        return create(null, localeIdentifier);
    }
    public static CFLocale createCopy(CFLocale locale) {
        return createCopy(null, locale);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFLocaleCurrentLocaleDidChangeNotification", optional=true)
    public static native NSString CurrentLocaleDidChangeNotification();
    
    @Bridge(symbol="CFLocaleGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFLocaleGetSystem", optional=true)
    public static native CFLocale getSystem();
    @Bridge(symbol="CFLocaleCopyCurrent", optional=true)
    public static native CFLocale getCurrent();
    @Bridge(symbol="CFLocaleCopyAvailableLocaleIdentifiers", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> getAvailableLocaleIdentifiers();
    @Bridge(symbol="CFLocaleCopyISOLanguageCodes", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> getISOLanguageCodes();
    @Bridge(symbol="CFLocaleCopyISOCountryCodes", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> getISOCountryCodes();
    @Bridge(symbol="CFLocaleCopyISOCurrencyCodes", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> getISOCurrencyCodes();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFLocaleCopyCommonISOCurrencyCodes", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> getCommonISOCurrencyCodes();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFLocaleCopyPreferredLanguages", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> getPreferredLanguages();
    @Bridge(symbol="CFLocaleCreateCanonicalLanguageIdentifierFromString", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getCanonicalLanguageIdentifier(CFAllocator allocator, String localeIdentifier);
    @Bridge(symbol="CFLocaleCreateCanonicalLocaleIdentifierFromString", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getCanonicalLocaleIdentifier(CFAllocator allocator, String localeIdentifier);
    @Bridge(symbol="CFLocaleCreateCanonicalLocaleIdentifierFromScriptManagerCodes", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getCanonicalLocaleIdentifier(CFAllocator allocator, short lcode, short rcode);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFLocaleCreateLocaleIdentifierFromWindowsLocaleCode", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getLocaleIdentifierFromWindowsLocaleCode(CFAllocator allocator, int lcid);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFLocaleGetWindowsLocaleCodeFromLocaleIdentifier", optional=true)
    public static native int getWindowsLocaleCodeFromLocaleIdentifier(String localeIdentifier);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFLocaleGetLanguageCharacterDirection", optional=true)
    public static native CFLocaleLanguageDirection getLanguageCharacterDirection(String isoLangCode);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFLocaleGetLanguageLineDirection", optional=true)
    public static native CFLocaleLanguageDirection getLanguageLineDirection(String isoLangCode);
    @Bridge(symbol="CFLocaleCreateComponentsFromLocaleIdentifier", optional=true)
    protected static native CFLocaleComponents getComponentsFromLocaleIdentifier(CFAllocator allocator, String localeID);
    @Bridge(symbol="CFLocaleCreateLocaleIdentifierFromComponents", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getLocaleIdentifierFromComponents(CFAllocator allocator, CFLocaleComponents dictionary);
    @Bridge(symbol="CFLocaleCreate", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFLocale create(CFAllocator allocator, String localeIdentifier);
    @Bridge(symbol="CFLocaleCreateCopy", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFLocale createCopy(CFAllocator allocator, CFLocale locale);
    @Bridge(symbol="CFLocaleGetIdentifier", optional=true)
    public native String getIdentifier();
    @Bridge(symbol="CFLocaleGetValue", optional=true)
    public native CFType getComponent(CFLocaleComponent key);
    @Bridge(symbol="CFLocaleCopyDisplayNameForPropertyValue", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getComponentDisplayName(CFLocaleComponent key, String value);
    /*</methods>*/
}
