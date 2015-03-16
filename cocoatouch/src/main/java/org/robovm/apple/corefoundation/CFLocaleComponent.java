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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
@Marshaler(/*<name>*/CFLocaleComponent/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFLocaleComponent/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CFLocaleComponent/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CFLocaleComponent toObject(Class<CFLocaleComponent> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
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
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFLocaleComponent> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFLocaleComponent> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CFLocaleComponent.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFLocaleComponent> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFLocaleComponent i : l) {
                array.add(i.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final CFLocaleComponent Identifier = new CFLocaleComponent("Identifier");
    public static final CFLocaleComponent LanguageCode = new CFLocaleComponent("LanguageCode");
    public static final CFLocaleComponent CountryCode = new CFLocaleComponent("CountryCode");
    public static final CFLocaleComponent ScriptCode = new CFLocaleComponent("ScriptCode");
    public static final CFLocaleComponent VariantCode = new CFLocaleComponent("VariantCode");
    public static final CFLocaleComponent ExemplarCharacterSet = new CFLocaleComponent("ExemplarCharacterSet");
    public static final CFLocaleComponent CalendarIdentifier = new CFLocaleComponent("CalendarIdentifier");
    public static final CFLocaleComponent Calendar = new CFLocaleComponent("Calendar");
    public static final CFLocaleComponent CollationIdentifier = new CFLocaleComponent("CollationIdentifier");
    public static final CFLocaleComponent UsesMetricSystem = new CFLocaleComponent("UsesMetricSystem");
    public static final CFLocaleComponent MeasurementSystem = new CFLocaleComponent("MeasurementSystem");
    public static final CFLocaleComponent DecimalSeparator = new CFLocaleComponent("DecimalSeparator");
    public static final CFLocaleComponent GroupingSeparator = new CFLocaleComponent("GroupingSeparator");
    public static final CFLocaleComponent CurrencySymbol = new CFLocaleComponent("CurrencySymbol");
    public static final CFLocaleComponent CurrencyCode = new CFLocaleComponent("CurrencyCode");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFLocaleComponent CollatorIdentifier = new CFLocaleComponent("CollatorIdentifier");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFLocaleComponent QuotationBeginDelimiterKey = new CFLocaleComponent("QuotationBeginDelimiterKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFLocaleComponent QuotationEndDelimiterKey = new CFLocaleComponent("QuotationEndDelimiterKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFLocaleComponent AlternateQuotationBeginDelimiterKey = new CFLocaleComponent("AlternateQuotationBeginDelimiterKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFLocaleComponent AlternateQuotationEndDelimiterKey = new CFLocaleComponent("AlternateQuotationEndDelimiterKey");
    /*</constants>*/
    
    private static /*<name>*/CFLocaleComponent/*</name>*/[] values = new /*<name>*/CFLocaleComponent/*</name>*/[] {/*<value_list>*/Identifier, LanguageCode, CountryCode, ScriptCode, VariantCode, ExemplarCharacterSet, CalendarIdentifier, Calendar, CollationIdentifier, UsesMetricSystem, MeasurementSystem, DecimalSeparator, GroupingSeparator, CurrencySymbol, CurrencyCode, CollatorIdentifier, QuotationBeginDelimiterKey, QuotationEndDelimiterKey, AlternateQuotationBeginDelimiterKey, AlternateQuotationEndDelimiterKey/*</value_list>*/};
    
    /*<name>*/CFLocaleComponent/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CFLocaleComponent/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CFLocaleComponent/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFLocaleComponent/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="kCFLocaleIdentifier", optional=true)
        public static native CFString Identifier();
        @GlobalValue(symbol="kCFLocaleLanguageCode", optional=true)
        public static native CFString LanguageCode();
        @GlobalValue(symbol="kCFLocaleCountryCode", optional=true)
        public static native CFString CountryCode();
        @GlobalValue(symbol="kCFLocaleScriptCode", optional=true)
        public static native CFString ScriptCode();
        @GlobalValue(symbol="kCFLocaleVariantCode", optional=true)
        public static native CFString VariantCode();
        @GlobalValue(symbol="kCFLocaleExemplarCharacterSet", optional=true)
        public static native CFString ExemplarCharacterSet();
        @GlobalValue(symbol="kCFLocaleCalendarIdentifier", optional=true)
        public static native CFString CalendarIdentifier();
        @GlobalValue(symbol="kCFLocaleCalendar", optional=true)
        public static native CFString Calendar();
        @GlobalValue(symbol="kCFLocaleCollationIdentifier", optional=true)
        public static native CFString CollationIdentifier();
        @GlobalValue(symbol="kCFLocaleUsesMetricSystem", optional=true)
        public static native CFString UsesMetricSystem();
        @GlobalValue(symbol="kCFLocaleMeasurementSystem", optional=true)
        public static native CFString MeasurementSystem();
        @GlobalValue(symbol="kCFLocaleDecimalSeparator", optional=true)
        public static native CFString DecimalSeparator();
        @GlobalValue(symbol="kCFLocaleGroupingSeparator", optional=true)
        public static native CFString GroupingSeparator();
        @GlobalValue(symbol="kCFLocaleCurrencySymbol", optional=true)
        public static native CFString CurrencySymbol();
        @GlobalValue(symbol="kCFLocaleCurrencyCode", optional=true)
        public static native CFString CurrencyCode();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFLocaleCollatorIdentifier", optional=true)
        public static native CFString CollatorIdentifier();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFLocaleQuotationBeginDelimiterKey", optional=true)
        public static native CFString QuotationBeginDelimiterKey();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFLocaleQuotationEndDelimiterKey", optional=true)
        public static native CFString QuotationEndDelimiterKey();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFLocaleAlternateQuotationBeginDelimiterKey", optional=true)
        public static native CFString AlternateQuotationBeginDelimiterKey();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFLocaleAlternateQuotationEndDelimiterKey", optional=true)
        public static native CFString AlternateQuotationEndDelimiterKey();
        /*</values>*/
    }
}
