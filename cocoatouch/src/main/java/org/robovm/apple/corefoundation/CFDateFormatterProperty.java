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
@Marshaler(CFDateFormatterProperty.Marshaler.class)
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFDateFormatterProperty/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

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
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFDateFormatterProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final CFDateFormatterProperty IsLenient = new CFDateFormatterProperty("IsLenientValue");
    public static final CFDateFormatterProperty TimeZone = new CFDateFormatterProperty("TimeZoneValue");
    public static final CFDateFormatterProperty CalendarName = new CFDateFormatterProperty("CalendarNameValue");
    public static final CFDateFormatterProperty DefaultFormat = new CFDateFormatterProperty("DefaultFormatValue");
    public static final CFDateFormatterProperty TwoDigitStartDate = new CFDateFormatterProperty("TwoDigitStartDateValue");
    public static final CFDateFormatterProperty DefaultDate = new CFDateFormatterProperty("DefaultDateValue");
    public static final CFDateFormatterProperty Calendar = new CFDateFormatterProperty("CalendarValue");
    public static final CFDateFormatterProperty EraSymbols = new CFDateFormatterProperty("EraSymbolsValue");
    public static final CFDateFormatterProperty MonthSymbols = new CFDateFormatterProperty("MonthSymbolsValue");
    public static final CFDateFormatterProperty ShortMonthSymbols = new CFDateFormatterProperty("ShortMonthSymbolsValue");
    public static final CFDateFormatterProperty WeekdaySymbols = new CFDateFormatterProperty("WeekdaySymbolsValue");
    public static final CFDateFormatterProperty ShortWeekdaySymbols = new CFDateFormatterProperty("ShortWeekdaySymbolsValue");
    public static final CFDateFormatterProperty AMSymbol = new CFDateFormatterProperty("AMSymbolValue");
    public static final CFDateFormatterProperty PMSymbol = new CFDateFormatterProperty("PMSymbolValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty LongEraSymbols = new CFDateFormatterProperty("LongEraSymbolsValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty VeryShortMonthSymbols = new CFDateFormatterProperty("VeryShortMonthSymbolsValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty StandaloneMonthSymbols = new CFDateFormatterProperty("StandaloneMonthSymbolsValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty ShortStandaloneMonthSymbols = new CFDateFormatterProperty("ShortStandaloneMonthSymbolsValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty VeryShortStandaloneMonthSymbols = new CFDateFormatterProperty("VeryShortStandaloneMonthSymbolsValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty VeryShortWeekdaySymbols = new CFDateFormatterProperty("VeryShortWeekdaySymbolsValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty StandaloneWeekdaySymbols = new CFDateFormatterProperty("StandaloneWeekdaySymbolsValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty ShortStandaloneWeekdaySymbols = new CFDateFormatterProperty("ShortStandaloneWeekdaySymbolsValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty VeryShortStandaloneWeekdaySymbols = new CFDateFormatterProperty("VeryShortStandaloneWeekdaySymbolsValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty QuarterSymbols = new CFDateFormatterProperty("QuarterSymbolsValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty ShortQuarterSymbols = new CFDateFormatterProperty("ShortQuarterSymbolsValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty StandaloneQuarterSymbols = new CFDateFormatterProperty("StandaloneQuarterSymbolsValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty ShortStandaloneQuarterSymbols = new CFDateFormatterProperty("ShortStandaloneQuarterSymbolsValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFDateFormatterProperty GregorianStartDate = new CFDateFormatterProperty("GregorianStartDateValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CFDateFormatterProperty DoesRelativeDateFormatting = new CFDateFormatterProperty("DoesRelativeDateFormattingKeyValue");
    
    private static CFDateFormatterProperty[] values = new CFDateFormatterProperty[] {IsLenient, TimeZone, CalendarName, DefaultFormat, 
        TwoDigitStartDate, DefaultDate, Calendar, EraSymbols, MonthSymbols, ShortMonthSymbols, WeekdaySymbols, ShortWeekdaySymbols, 
        AMSymbol, PMSymbol, LongEraSymbols, VeryShortMonthSymbols, StandaloneMonthSymbols, ShortStandaloneMonthSymbols, VeryShortStandaloneMonthSymbols, 
        VeryShortWeekdaySymbols, StandaloneWeekdaySymbols, ShortStandaloneWeekdaySymbols, VeryShortStandaloneWeekdaySymbols, QuarterSymbols, 
        ShortQuarterSymbols, StandaloneQuarterSymbols, ShortStandaloneQuarterSymbols, GregorianStartDate, DoesRelativeDateFormatting};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CFDateFormatterProperty(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CFDateFormatterProperty valueOf(CFString value) {
        for (CFDateFormatterProperty v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFDateFormatterProperty/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="kCFDateFormatterIsLenient", optional=true)
    protected static native CFString IsLenientValue();
    @GlobalValue(symbol="kCFDateFormatterTimeZone", optional=true)
    protected static native CFString TimeZoneValue();
    @GlobalValue(symbol="kCFDateFormatterCalendarName", optional=true)
    protected static native CFString CalendarNameValue();
    @GlobalValue(symbol="kCFDateFormatterDefaultFormat", optional=true)
    protected static native CFString DefaultFormatValue();
    @GlobalValue(symbol="kCFDateFormatterTwoDigitStartDate", optional=true)
    protected static native CFString TwoDigitStartDateValue();
    @GlobalValue(symbol="kCFDateFormatterDefaultDate", optional=true)
    protected static native CFString DefaultDateValue();
    @GlobalValue(symbol="kCFDateFormatterCalendar", optional=true)
    protected static native CFString CalendarValue();
    @GlobalValue(symbol="kCFDateFormatterEraSymbols", optional=true)
    protected static native CFString EraSymbolsValue();
    @GlobalValue(symbol="kCFDateFormatterMonthSymbols", optional=true)
    protected static native CFString MonthSymbolsValue();
    @GlobalValue(symbol="kCFDateFormatterShortMonthSymbols", optional=true)
    protected static native CFString ShortMonthSymbolsValue();
    @GlobalValue(symbol="kCFDateFormatterWeekdaySymbols", optional=true)
    protected static native CFString WeekdaySymbolsValue();
    @GlobalValue(symbol="kCFDateFormatterShortWeekdaySymbols", optional=true)
    protected static native CFString ShortWeekdaySymbolsValue();
    @GlobalValue(symbol="kCFDateFormatterAMSymbol", optional=true)
    protected static native CFString AMSymbolValue();
    @GlobalValue(symbol="kCFDateFormatterPMSymbol", optional=true)
    protected static native CFString PMSymbolValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterLongEraSymbols", optional=true)
    protected static native CFString LongEraSymbolsValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterVeryShortMonthSymbols", optional=true)
    protected static native CFString VeryShortMonthSymbolsValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterStandaloneMonthSymbols", optional=true)
    protected static native CFString StandaloneMonthSymbolsValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterShortStandaloneMonthSymbols", optional=true)
    protected static native CFString ShortStandaloneMonthSymbolsValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterVeryShortStandaloneMonthSymbols", optional=true)
    protected static native CFString VeryShortStandaloneMonthSymbolsValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterVeryShortWeekdaySymbols", optional=true)
    protected static native CFString VeryShortWeekdaySymbolsValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterStandaloneWeekdaySymbols", optional=true)
    protected static native CFString StandaloneWeekdaySymbolsValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterShortStandaloneWeekdaySymbols", optional=true)
    protected static native CFString ShortStandaloneWeekdaySymbolsValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterVeryShortStandaloneWeekdaySymbols", optional=true)
    protected static native CFString VeryShortStandaloneWeekdaySymbolsValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterQuarterSymbols", optional=true)
    protected static native CFString QuarterSymbolsValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterShortQuarterSymbols", optional=true)
    protected static native CFString ShortQuarterSymbolsValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterStandaloneQuarterSymbols", optional=true)
    protected static native CFString StandaloneQuarterSymbolsValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterShortStandaloneQuarterSymbols", optional=true)
    protected static native CFString ShortStandaloneQuarterSymbolsValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterGregorianStartDate", optional=true)
    protected static native CFString GregorianStartDateValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterDoesRelativeDateFormattingKey", optional=true)
    protected static native CFString DoesRelativeDateFormattingKeyValue();
    /*</methods>*/
}
