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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/NSLocaleComponent/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSLocaleComponent/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSLocaleComponent/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSLocaleComponent> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSLocaleComponent> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSLocaleComponent.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSLocaleComponent> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSLocaleComponent i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final NSLocaleComponent Identifier = new NSLocaleComponent("Identifier");
    public static final NSLocaleComponent LanguageCode = new NSLocaleComponent("LanguageCode");
    public static final NSLocaleComponent CountryCode = new NSLocaleComponent("CountryCode");
    public static final NSLocaleComponent ScriptCode = new NSLocaleComponent("ScriptCode");
    public static final NSLocaleComponent VariantCode = new NSLocaleComponent("VariantCode");
    public static final NSLocaleComponent ExemplarCharacterSet = new NSLocaleComponent("ExemplarCharacterSet");
    public static final NSLocaleComponent CollationIdentifier = new NSLocaleComponent("CollationIdentifier");
    public static final NSLocaleComponent UsesMetricSystem = new NSLocaleComponent("UsesMetricSystem");
    public static final NSLocaleComponent MeasurementSystem = new NSLocaleComponent("MeasurementSystem");
    public static final NSLocaleComponent DecimalSeparator = new NSLocaleComponent("DecimalSeparator");
    public static final NSLocaleComponent GroupingSeparator = new NSLocaleComponent("GroupingSeparator");
    public static final NSLocaleComponent CurrencySymbol = new NSLocaleComponent("CurrencySymbol");
    public static final NSLocaleComponent CurrencyCode = new NSLocaleComponent("CurrencyCode");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSLocaleComponent CollatorIdentifier = new NSLocaleComponent("CollatorIdentifier");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSLocaleComponent QuotationBeginDelimiterKey = new NSLocaleComponent("QuotationBeginDelimiterKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSLocaleComponent QuotationEndDelimiterKey = new NSLocaleComponent("QuotationEndDelimiterKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSLocaleComponent AlternateQuotationBeginDelimiterKey = new NSLocaleComponent("AlternateQuotationBeginDelimiterKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSLocaleComponent AlternateQuotationEndDelimiterKey = new NSLocaleComponent("AlternateQuotationEndDelimiterKey");
    /*</constants>*/
    
    private static /*<name>*/NSLocaleComponent/*</name>*/[] values = new /*<name>*/NSLocaleComponent/*</name>*/[] {/*<value_list>*/Identifier, LanguageCode, CountryCode, ScriptCode, VariantCode, ExemplarCharacterSet, CollationIdentifier, UsesMetricSystem, MeasurementSystem, DecimalSeparator, GroupingSeparator, CurrencySymbol, CurrencyCode, CollatorIdentifier, QuotationBeginDelimiterKey, QuotationEndDelimiterKey, AlternateQuotationBeginDelimiterKey, AlternateQuotationEndDelimiterKey/*</value_list>*/};
    
    /*<name>*/NSLocaleComponent/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSLocaleComponent/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSLocaleComponent/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSLocaleComponent/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="NSLocaleIdentifier", optional=true)
        public static native NSString Identifier();
        @GlobalValue(symbol="NSLocaleLanguageCode", optional=true)
        public static native NSString LanguageCode();
        @GlobalValue(symbol="NSLocaleCountryCode", optional=true)
        public static native NSString CountryCode();
        @GlobalValue(symbol="NSLocaleScriptCode", optional=true)
        public static native NSString ScriptCode();
        @GlobalValue(symbol="NSLocaleVariantCode", optional=true)
        public static native NSString VariantCode();
        @GlobalValue(symbol="NSLocaleExemplarCharacterSet", optional=true)
        public static native NSString ExemplarCharacterSet();
        @GlobalValue(symbol="NSLocaleCollationIdentifier", optional=true)
        public static native NSString CollationIdentifier();
        @GlobalValue(symbol="NSLocaleUsesMetricSystem", optional=true)
        public static native NSString UsesMetricSystem();
        @GlobalValue(symbol="NSLocaleMeasurementSystem", optional=true)
        public static native NSString MeasurementSystem();
        @GlobalValue(symbol="NSLocaleDecimalSeparator", optional=true)
        public static native NSString DecimalSeparator();
        @GlobalValue(symbol="NSLocaleGroupingSeparator", optional=true)
        public static native NSString GroupingSeparator();
        @GlobalValue(symbol="NSLocaleCurrencySymbol", optional=true)
        public static native NSString CurrencySymbol();
        @GlobalValue(symbol="NSLocaleCurrencyCode", optional=true)
        public static native NSString CurrencyCode();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSLocaleCollatorIdentifier", optional=true)
        public static native NSString CollatorIdentifier();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSLocaleQuotationBeginDelimiterKey", optional=true)
        public static native NSString QuotationBeginDelimiterKey();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSLocaleQuotationEndDelimiterKey", optional=true)
        public static native NSString QuotationEndDelimiterKey();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSLocaleAlternateQuotationBeginDelimiterKey", optional=true)
        public static native NSString AlternateQuotationBeginDelimiterKey();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSLocaleAlternateQuotationEndDelimiterKey", optional=true)
        public static native NSString AlternateQuotationEndDelimiterKey();
        /*</values>*/
    }
}
