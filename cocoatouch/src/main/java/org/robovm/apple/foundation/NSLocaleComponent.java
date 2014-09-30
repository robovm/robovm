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
@Marshaler(NSLocaleComponent.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSLocaleComponent/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static NSLocaleComponent toObject(Class<NSLocaleComponent> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSLocaleComponent.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSLocaleComponent o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSLocaleComponent.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final NSLocaleComponent Identifier = new NSLocaleComponent("IdentifierValue");
    public static final NSLocaleComponent LanguageCode = new NSLocaleComponent("LanguageCodeValue");
    public static final NSLocaleComponent CountryCode = new NSLocaleComponent("CountryCodeValue");
    public static final NSLocaleComponent ScriptCode = new NSLocaleComponent("ScriptCodeValue");
    public static final NSLocaleComponent VariantCode = new NSLocaleComponent("VariantCodeValue");
    public static final NSLocaleComponent ExemplarCharacterSet = new NSLocaleComponent("ExemplarCharacterSetValue");
    public static final NSLocaleComponent CollationIdentifier = new NSLocaleComponent("CollationIdentifierValue");
    public static final NSLocaleComponent UsesMetricSystem = new NSLocaleComponent("UsesMetricSystemValue");
    public static final NSLocaleComponent MeasurementSystem = new NSLocaleComponent("MeasurementSystemValue");
    public static final NSLocaleComponent DecimalSeparator = new NSLocaleComponent("DecimalSeparatorValue");
    public static final NSLocaleComponent GroupingSeparator = new NSLocaleComponent("GroupingSeparatorValue");
    public static final NSLocaleComponent CurrencySymbol = new NSLocaleComponent("CurrencySymbolValue");
    public static final NSLocaleComponent CurrencyCode = new NSLocaleComponent("CurrencyCodeValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSLocaleComponent CollatorIdentifier = new NSLocaleComponent("CollatorIdentifierValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSLocaleComponent QuotationBeginDelimiterKey = new NSLocaleComponent("QuotationBeginDelimiterKeyValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSLocaleComponent QuotationEndDelimiterKey = new NSLocaleComponent("QuotationEndDelimiterKeyValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSLocaleComponent AlternateQuotationBeginDelimiterKey = new NSLocaleComponent("AlternateQuotationBeginDelimiterKeyValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSLocaleComponent AlternateQuotationEndDelimiterKey = new NSLocaleComponent("AlternateQuotationEndDelimiterKeyValue");
    
    private static NSLocaleComponent[] values = new NSLocaleComponent[] {Identifier, LanguageCode, CountryCode, ScriptCode, VariantCode, ExemplarCharacterSet, CollationIdentifier, 
        UsesMetricSystem, MeasurementSystem, DecimalSeparator, GroupingSeparator, CurrencySymbol, CurrencyCode, CollatorIdentifier, QuotationBeginDelimiterKey, QuotationEndDelimiterKey, 
        AlternateQuotationBeginDelimiterKey, AlternateQuotationEndDelimiterKey};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSLocaleComponent(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSLocaleComponent valueOf(NSString value) {
        for (NSLocaleComponent v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSLocaleComponent/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="NSLocaleIdentifier", optional=true)
    protected static native NSString IdentifierValue();
    @GlobalValue(symbol="NSLocaleLanguageCode", optional=true)
    protected static native NSString LanguageCodeValue();
    @GlobalValue(symbol="NSLocaleCountryCode", optional=true)
    protected static native NSString CountryCodeValue();
    @GlobalValue(symbol="NSLocaleScriptCode", optional=true)
    protected static native NSString ScriptCodeValue();
    @GlobalValue(symbol="NSLocaleVariantCode", optional=true)
    protected static native NSString VariantCodeValue();
    @GlobalValue(symbol="NSLocaleExemplarCharacterSet", optional=true)
    protected static native NSString ExemplarCharacterSetValue();
    @GlobalValue(symbol="NSLocaleCollationIdentifier", optional=true)
    protected static native NSString CollationIdentifierValue();
    @GlobalValue(symbol="NSLocaleUsesMetricSystem", optional=true)
    protected static native NSString UsesMetricSystemValue();
    @GlobalValue(symbol="NSLocaleMeasurementSystem", optional=true)
    protected static native NSString MeasurementSystemValue();
    @GlobalValue(symbol="NSLocaleDecimalSeparator", optional=true)
    protected static native NSString DecimalSeparatorValue();
    @GlobalValue(symbol="NSLocaleGroupingSeparator", optional=true)
    protected static native NSString GroupingSeparatorValue();
    @GlobalValue(symbol="NSLocaleCurrencySymbol", optional=true)
    protected static native NSString CurrencySymbolValue();
    @GlobalValue(symbol="NSLocaleCurrencyCode", optional=true)
    protected static native NSString CurrencyCodeValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleCollatorIdentifier", optional=true)
    protected static native NSString CollatorIdentifierValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleQuotationBeginDelimiterKey", optional=true)
    protected static native NSString QuotationBeginDelimiterKeyValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleQuotationEndDelimiterKey", optional=true)
    protected static native NSString QuotationEndDelimiterKeyValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleAlternateQuotationBeginDelimiterKey", optional=true)
    protected static native NSString AlternateQuotationBeginDelimiterKeyValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSLocaleAlternateQuotationEndDelimiterKey", optional=true)
    protected static native NSString AlternateQuotationEndDelimiterKeyValue();
    /*</methods>*/
}
