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
import org.robovm.apple.coremedia.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CFDateFormatterProperty/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFDateFormatterProperty/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CFDateFormatterProperty/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CFDateFormatterProperty toObject(Class<CFDateFormatterProperty> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFDateFormatterProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFDateFormatterProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFDateFormatterProperty> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFDateFormatterProperty> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CFDateFormatterProperty.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFDateFormatterProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFDateFormatterProperty o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final CFDateFormatterProperty IsLenient = new CFDateFormatterProperty("IsLenient");
    public static final CFDateFormatterProperty TimeZone = new CFDateFormatterProperty("TimeZone");
    public static final CFDateFormatterProperty CalendarName = new CFDateFormatterProperty("CalendarName");
    public static final CFDateFormatterProperty DefaultFormat = new CFDateFormatterProperty("DefaultFormat");
    public static final CFDateFormatterProperty TwoDigitStartDate = new CFDateFormatterProperty("TwoDigitStartDate");
    public static final CFDateFormatterProperty DefaultDate = new CFDateFormatterProperty("DefaultDate");
    public static final CFDateFormatterProperty Calendar = new CFDateFormatterProperty("Calendar");
    public static final CFDateFormatterProperty EraSymbols = new CFDateFormatterProperty("EraSymbols");
    public static final CFDateFormatterProperty MonthSymbols = new CFDateFormatterProperty("MonthSymbols");
    public static final CFDateFormatterProperty ShortMonthSymbols = new CFDateFormatterProperty("ShortMonthSymbols");
    public static final CFDateFormatterProperty WeekdaySymbols = new CFDateFormatterProperty("WeekdaySymbols");
    public static final CFDateFormatterProperty ShortWeekdaySymbols = new CFDateFormatterProperty("ShortWeekdaySymbols");
    public static final CFDateFormatterProperty AMSymbol = new CFDateFormatterProperty("AMSymbol");
    public static final CFDateFormatterProperty PMSymbol = new CFDateFormatterProperty("PMSymbol");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty LongEraSymbols = new CFDateFormatterProperty("LongEraSymbols");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty VeryShortMonthSymbols = new CFDateFormatterProperty("VeryShortMonthSymbols");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty StandaloneMonthSymbols = new CFDateFormatterProperty("StandaloneMonthSymbols");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty ShortStandaloneMonthSymbols = new CFDateFormatterProperty("ShortStandaloneMonthSymbols");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty VeryShortStandaloneMonthSymbols = new CFDateFormatterProperty("VeryShortStandaloneMonthSymbols");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty VeryShortWeekdaySymbols = new CFDateFormatterProperty("VeryShortWeekdaySymbols");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty StandaloneWeekdaySymbols = new CFDateFormatterProperty("StandaloneWeekdaySymbols");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty ShortStandaloneWeekdaySymbols = new CFDateFormatterProperty("ShortStandaloneWeekdaySymbols");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty VeryShortStandaloneWeekdaySymbols = new CFDateFormatterProperty("VeryShortStandaloneWeekdaySymbols");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty QuarterSymbols = new CFDateFormatterProperty("QuarterSymbols");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty ShortQuarterSymbols = new CFDateFormatterProperty("ShortQuarterSymbols");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty StandaloneQuarterSymbols = new CFDateFormatterProperty("StandaloneQuarterSymbols");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty ShortStandaloneQuarterSymbols = new CFDateFormatterProperty("ShortStandaloneQuarterSymbols");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty GregorianStartDate = new CFDateFormatterProperty("GregorianStartDate");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFDateFormatterProperty DoesRelativeDateFormattingKey = new CFDateFormatterProperty("DoesRelativeDateFormattingKey");
    /*</constants>*/
    
    private static /*<name>*/CFDateFormatterProperty/*</name>*/[] values = new /*<name>*/CFDateFormatterProperty/*</name>*/[] {/*<value_list>*/IsLenient, TimeZone, CalendarName, DefaultFormat, TwoDigitStartDate, DefaultDate, Calendar, EraSymbols, MonthSymbols, ShortMonthSymbols, WeekdaySymbols, ShortWeekdaySymbols, AMSymbol, PMSymbol, LongEraSymbols, VeryShortMonthSymbols, StandaloneMonthSymbols, ShortStandaloneMonthSymbols, VeryShortStandaloneMonthSymbols, VeryShortWeekdaySymbols, StandaloneWeekdaySymbols, ShortStandaloneWeekdaySymbols, VeryShortStandaloneWeekdaySymbols, QuarterSymbols, ShortQuarterSymbols, StandaloneQuarterSymbols, ShortStandaloneQuarterSymbols, GregorianStartDate, DoesRelativeDateFormattingKey/*</value_list>*/};
    
    /*<name>*/CFDateFormatterProperty/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CFDateFormatterProperty/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CFDateFormatterProperty/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFDateFormatterProperty/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="kCFDateFormatterIsLenient", optional=true)
        public static native CFString IsLenient();
        @GlobalValue(symbol="kCFDateFormatterTimeZone", optional=true)
        public static native CFString TimeZone();
        @GlobalValue(symbol="kCFDateFormatterCalendarName", optional=true)
        public static native CFString CalendarName();
        @GlobalValue(symbol="kCFDateFormatterDefaultFormat", optional=true)
        public static native CFString DefaultFormat();
        @GlobalValue(symbol="kCFDateFormatterTwoDigitStartDate", optional=true)
        public static native CFString TwoDigitStartDate();
        @GlobalValue(symbol="kCFDateFormatterDefaultDate", optional=true)
        public static native CFString DefaultDate();
        @GlobalValue(symbol="kCFDateFormatterCalendar", optional=true)
        public static native CFString Calendar();
        @GlobalValue(symbol="kCFDateFormatterEraSymbols", optional=true)
        public static native CFString EraSymbols();
        @GlobalValue(symbol="kCFDateFormatterMonthSymbols", optional=true)
        public static native CFString MonthSymbols();
        @GlobalValue(symbol="kCFDateFormatterShortMonthSymbols", optional=true)
        public static native CFString ShortMonthSymbols();
        @GlobalValue(symbol="kCFDateFormatterWeekdaySymbols", optional=true)
        public static native CFString WeekdaySymbols();
        @GlobalValue(symbol="kCFDateFormatterShortWeekdaySymbols", optional=true)
        public static native CFString ShortWeekdaySymbols();
        @GlobalValue(symbol="kCFDateFormatterAMSymbol", optional=true)
        public static native CFString AMSymbol();
        @GlobalValue(symbol="kCFDateFormatterPMSymbol", optional=true)
        public static native CFString PMSymbol();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFDateFormatterLongEraSymbols", optional=true)
        public static native CFString LongEraSymbols();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFDateFormatterVeryShortMonthSymbols", optional=true)
        public static native CFString VeryShortMonthSymbols();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFDateFormatterStandaloneMonthSymbols", optional=true)
        public static native CFString StandaloneMonthSymbols();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFDateFormatterShortStandaloneMonthSymbols", optional=true)
        public static native CFString ShortStandaloneMonthSymbols();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFDateFormatterVeryShortStandaloneMonthSymbols", optional=true)
        public static native CFString VeryShortStandaloneMonthSymbols();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFDateFormatterVeryShortWeekdaySymbols", optional=true)
        public static native CFString VeryShortWeekdaySymbols();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFDateFormatterStandaloneWeekdaySymbols", optional=true)
        public static native CFString StandaloneWeekdaySymbols();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFDateFormatterShortStandaloneWeekdaySymbols", optional=true)
        public static native CFString ShortStandaloneWeekdaySymbols();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFDateFormatterVeryShortStandaloneWeekdaySymbols", optional=true)
        public static native CFString VeryShortStandaloneWeekdaySymbols();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFDateFormatterQuarterSymbols", optional=true)
        public static native CFString QuarterSymbols();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFDateFormatterShortQuarterSymbols", optional=true)
        public static native CFString ShortQuarterSymbols();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFDateFormatterStandaloneQuarterSymbols", optional=true)
        public static native CFString StandaloneQuarterSymbols();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFDateFormatterShortStandaloneQuarterSymbols", optional=true)
        public static native CFString ShortStandaloneQuarterSymbols();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFDateFormatterGregorianStartDate", optional=true)
        public static native CFString GregorianStartDate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCFDateFormatterDoesRelativeDateFormattingKey", optional=true)
        public static native CFString DoesRelativeDateFormattingKey();
        /*</values>*/
    }
}
