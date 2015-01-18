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
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CFLocaleComponent.Marshaler.class)
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFLocaleComponent/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CFLocaleComponent toObject(Class<CFLocaleComponent> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFLocaleComponent.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFLocaleComponent o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    public static class AsMapMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static Map<CFLocaleComponent, NSObject> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            Map<CFLocaleComponent, NSObject> map = new HashMap<>();
            for (Map.Entry<NSString, NSObject> e : o.entrySet()) {
                map.put(CFLocaleComponent.valueOf(e.getKey()), e.getValue());
            }
            return map;
        }
        @MarshalsPointer
        public static long toNative(Map<CFLocaleComponent, NSObject> o, long flags) {
            if (o == null) {
                return 0L;
            }
            NSDictionary<NSString, NSObject> dict = new NSMutableDictionary<>();
            for (Map.Entry<CFLocaleComponent, NSObject> e : o.entrySet()) {
                dict.put(e.getKey().value(), e.getValue());
            }
            return NSObject.Marshaler.toNative(dict, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFLocaleComponent.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final CFLocaleComponent Identifier = new CFLocaleComponent("IdentifierValue");
    public static final CFLocaleComponent LanguageCode = new CFLocaleComponent("LanguageCodeValue");
    public static final CFLocaleComponent CountryCode = new CFLocaleComponent("CountryCodeValue");
    public static final CFLocaleComponent ScriptCode = new CFLocaleComponent("ScriptCodeValue");
    public static final CFLocaleComponent VariantCode = new CFLocaleComponent("VariantCodeValue");
    public static final CFLocaleComponent ExemplarCharacterSet = new CFLocaleComponent("ExemplarCharacterSetValue");
    public static final CFLocaleComponent CalendarIdentifier = new CFLocaleComponent("CalendarIdentifierValue");
    public static final CFLocaleComponent Calendar = new CFLocaleComponent("CalendarValue");
    public static final CFLocaleComponent CollationIdentifier = new CFLocaleComponent("CollationIdentifierValue");
    public static final CFLocaleComponent UsesMetricSystem = new CFLocaleComponent("UsesMetricSystemValue");
    public static final CFLocaleComponent MeasurementSystem = new CFLocaleComponent("MeasurementSystemValue");
    public static final CFLocaleComponent DecimalSeparator = new CFLocaleComponent("DecimalSeparatorValue");
    public static final CFLocaleComponent GroupingSeparator = new CFLocaleComponent("GroupingSeparatorValue");
    public static final CFLocaleComponent CurrencySymbol = new CFLocaleComponent("CurrencySymbolValue");
    public static final CFLocaleComponent CurrencyCode = new CFLocaleComponent("CurrencyCodeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFLocaleComponent CollatorIdentifier = new CFLocaleComponent("CollatorIdentifierValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFLocaleComponent QuotationBeginDelimiterKey = new CFLocaleComponent("QuotationBeginDelimiterKeyValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFLocaleComponent QuotationEndDelimiterKey = new CFLocaleComponent("QuotationEndDelimiterKeyValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFLocaleComponent AlternateQuotationBeginDelimiterKey = new CFLocaleComponent("AlternateQuotationBeginDelimiterKeyValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFLocaleComponent AlternateQuotationEndDelimiterKey = new CFLocaleComponent("AlternateQuotationEndDelimiterKeyValue");
    
    private static CFLocaleComponent[] values = new CFLocaleComponent[] {Identifier, LanguageCode, CountryCode, ScriptCode, VariantCode, 
        ExemplarCharacterSet, CalendarIdentifier, Calendar, CollationIdentifier, UsesMetricSystem, MeasurementSystem, DecimalSeparator, 
        GroupingSeparator, CurrencySymbol, CurrencyCode, CollatorIdentifier, QuotationBeginDelimiterKey, QuotationEndDelimiterKey, 
        AlternateQuotationBeginDelimiterKey, AlternateQuotationEndDelimiterKey};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private CFLocaleComponent(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static CFLocaleComponent valueOf(NSString value) {
        for (CFLocaleComponent v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFLocaleComponent/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="kCFLocaleIdentifier", optional=true)
    public static native NSString IdentifierValue();
    @GlobalValue(symbol="kCFLocaleLanguageCode", optional=true)
    public static native NSString LanguageCodeValue();
    @GlobalValue(symbol="kCFLocaleCountryCode", optional=true)
    public static native NSString CountryCodeValue();
    @GlobalValue(symbol="kCFLocaleScriptCode", optional=true)
    public static native NSString ScriptCodeValue();
    @GlobalValue(symbol="kCFLocaleVariantCode", optional=true)
    public static native NSString VariantCodeValue();
    @GlobalValue(symbol="kCFLocaleExemplarCharacterSet", optional=true)
    public static native NSString ExemplarCharacterSetValue();
    @GlobalValue(symbol="kCFLocaleCalendarIdentifier", optional=true)
    public static native NSString CalendarIdentifierValue();
    @GlobalValue(symbol="kCFLocaleCalendar", optional=true)
    public static native NSString CalendarValue();
    @GlobalValue(symbol="kCFLocaleCollationIdentifier", optional=true)
    public static native NSString CollationIdentifierValue();
    @GlobalValue(symbol="kCFLocaleUsesMetricSystem", optional=true)
    public static native NSString UsesMetricSystemValue();
    @GlobalValue(symbol="kCFLocaleMeasurementSystem", optional=true)
    public static native NSString MeasurementSystemValue();
    @GlobalValue(symbol="kCFLocaleDecimalSeparator", optional=true)
    public static native NSString DecimalSeparatorValue();
    @GlobalValue(symbol="kCFLocaleGroupingSeparator", optional=true)
    public static native NSString GroupingSeparatorValue();
    @GlobalValue(symbol="kCFLocaleCurrencySymbol", optional=true)
    public static native NSString CurrencySymbolValue();
    @GlobalValue(symbol="kCFLocaleCurrencyCode", optional=true)
    public static native NSString CurrencyCodeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFLocaleCollatorIdentifier", optional=true)
    public static native NSString CollatorIdentifierValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFLocaleQuotationBeginDelimiterKey", optional=true)
    public static native NSString QuotationBeginDelimiterKeyValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFLocaleQuotationEndDelimiterKey", optional=true)
    public static native NSString QuotationEndDelimiterKeyValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFLocaleAlternateQuotationBeginDelimiterKey", optional=true)
    public static native NSString AlternateQuotationBeginDelimiterKeyValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFLocaleAlternateQuotationEndDelimiterKey", optional=true)
    public static native NSString AlternateQuotationEndDelimiterKeyValue();
    /*</methods>*/
}
