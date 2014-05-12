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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFDateFormatter/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFDateFormatterPtr extends Ptr<CFDateFormatter, CFDateFormatterPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFDateFormatter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFDateFormatter() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCFDateFormatterIsLenient", optional=true)
    public static native CFString KeyIsLenient();
    @GlobalValue(symbol="kCFDateFormatterTimeZone", optional=true)
    public static native CFString KeyTimeZone();
    @GlobalValue(symbol="kCFDateFormatterCalendarName", optional=true)
    public static native CFString KeyCalendarName();
    @GlobalValue(symbol="kCFDateFormatterDefaultFormat", optional=true)
    public static native CFString KeyDefaultFormat();
    @GlobalValue(symbol="kCFDateFormatterTwoDigitStartDate", optional=true)
    public static native CFString KeyTwoDigitStartDate();
    @GlobalValue(symbol="kCFDateFormatterDefaultDate", optional=true)
    public static native CFString KeyDefaultDate();
    @GlobalValue(symbol="kCFDateFormatterCalendar", optional=true)
    public static native CFString KeyCalendar();
    @GlobalValue(symbol="kCFDateFormatterEraSymbols", optional=true)
    public static native CFString KeyEraSymbols();
    @GlobalValue(symbol="kCFDateFormatterMonthSymbols", optional=true)
    public static native CFString KeyMonthSymbols();
    @GlobalValue(symbol="kCFDateFormatterShortMonthSymbols", optional=true)
    public static native CFString KeyShortMonthSymbols();
    @GlobalValue(symbol="kCFDateFormatterWeekdaySymbols", optional=true)
    public static native CFString KeyWeekdaySymbols();
    @GlobalValue(symbol="kCFDateFormatterShortWeekdaySymbols", optional=true)
    public static native CFString KeyShortWeekdaySymbols();
    @GlobalValue(symbol="kCFDateFormatterAMSymbol", optional=true)
    public static native CFString KeyAMSymbol();
    @GlobalValue(symbol="kCFDateFormatterPMSymbol", optional=true)
    public static native CFString KeyPMSymbol();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterLongEraSymbols", optional=true)
    public static native CFString KeyLongEraSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterVeryShortMonthSymbols", optional=true)
    public static native CFString KeyVeryShortMonthSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterStandaloneMonthSymbols", optional=true)
    public static native CFString KeyStandaloneMonthSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterShortStandaloneMonthSymbols", optional=true)
    public static native CFString KeyShortStandaloneMonthSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterVeryShortStandaloneMonthSymbols", optional=true)
    public static native CFString KeyVeryShortStandaloneMonthSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterVeryShortWeekdaySymbols", optional=true)
    public static native CFString KeyVeryShortWeekdaySymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterStandaloneWeekdaySymbols", optional=true)
    public static native CFString KeyStandaloneWeekdaySymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterShortStandaloneWeekdaySymbols", optional=true)
    public static native CFString KeyShortStandaloneWeekdaySymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterVeryShortStandaloneWeekdaySymbols", optional=true)
    public static native CFString KeyVeryShortStandaloneWeekdaySymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterQuarterSymbols", optional=true)
    public static native CFString KeyQuarterSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterShortQuarterSymbols", optional=true)
    public static native CFString KeyShortQuarterSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterStandaloneQuarterSymbols", optional=true)
    public static native CFString KeyStandaloneQuarterSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterShortStandaloneQuarterSymbols", optional=true)
    public static native CFString KeyShortStandaloneQuarterSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterGregorianStartDate", optional=true)
    public static native CFString KeyGregorianStartDate();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCFDateFormatterDoesRelativeDateFormattingKey", optional=true)
    public static native CFString KeyDoesRelativeDateFormattingKey();
    
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFDateFormatterCreateDateFormatFromTemplate", optional=true)
    public static native CFString createDateFormatFromTemplate(CFAllocator allocator, CFString tmplate, @MachineSizedUInt long options, CFLocale locale);
    @Bridge(symbol="CFDateFormatterGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFDateFormatterCreate", optional=true)
    public static native CFDateFormatter create(CFAllocator allocator, CFLocale locale, CFDateFormatterStyle dateStyle, CFDateFormatterStyle timeStyle);
    @Bridge(symbol="CFDateFormatterGetLocale", optional=true)
    public native CFLocale getLocale();
    @Bridge(symbol="CFDateFormatterGetDateStyle", optional=true)
    public native CFDateFormatterStyle getDateStyle();
    @Bridge(symbol="CFDateFormatterGetTimeStyle", optional=true)
    public native CFDateFormatterStyle getTimeStyle();
    @Bridge(symbol="CFDateFormatterGetFormat", optional=true)
    public native CFString getFormat();
    @Bridge(symbol="CFDateFormatterSetFormat", optional=true)
    public native void setFormat(CFString formatString);
    @Bridge(symbol="CFDateFormatterCreateStringWithDate", optional=true)
    public static native CFString createStringWithDate(CFAllocator allocator, CFDateFormatter formatter, CFDate date);
    @Bridge(symbol="CFDateFormatterCreateStringWithAbsoluteTime", optional=true)
    public static native CFString createStringWithAbsoluteTime(CFAllocator allocator, CFDateFormatter formatter, double at);
    @Bridge(symbol="CFDateFormatterCreateDateFromString", optional=true)
    public static native CFDate createDateFromString(CFAllocator allocator, CFDateFormatter formatter, CFString string, CFRange rangep);
    @Bridge(symbol="CFDateFormatterGetAbsoluteTimeFromString", optional=true)
    public native boolean getAbsoluteTimeFromString(CFString string, CFRange rangep, DoublePtr atp);
    @Bridge(symbol="CFDateFormatterSetProperty", optional=true)
    public native void setProperty(CFString key, CFType value);
    @Bridge(symbol="CFDateFormatterCopyProperty", optional=true)
    public native CFType copyProperty(CFString key);
    /*</methods>*/
}
