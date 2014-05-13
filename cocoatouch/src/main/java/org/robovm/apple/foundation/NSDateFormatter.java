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
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSDateFormatter/*</name>*/ 
    extends /*<extends>*/NSFormatter/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSDateFormatterPtr extends Ptr<NSDateFormatter, NSDateFormatterPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSDateFormatter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSDateFormatter() {}
    protected NSDateFormatter(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "stringFromDate:")
    public native String format(NSDate date);
    @Method(selector = "dateFromString:")
    public native NSDate parse(String string);
    @Method(selector = "dateFormat")
    public native String getDateFormat();
    @Method(selector = "dateStyle")
    public native NSDateFormatterStyle getDateStyle();
    @Method(selector = "setDateStyle:")
    public native void setDateStyle(NSDateFormatterStyle style);
    @Method(selector = "timeStyle")
    public native NSDateFormatterStyle getTimeStyle();
    @Method(selector = "setTimeStyle:")
    public native void setTimeStyle(NSDateFormatterStyle style);
    @Method(selector = "locale")
    public native NSLocale getLocale();
    @Method(selector = "setLocale:")
    public native void setLocale(NSLocale locale);
    @Method(selector = "generatesCalendarDates")
    public native boolean generatesCalendarDates();
    @Method(selector = "setGeneratesCalendarDates:")
    public native void setGeneratesCalendarDates(boolean b);
    @Method(selector = "formatterBehavior")
    public native NSDateFormatterBehavior getFormatterBehavior();
    @Method(selector = "setFormatterBehavior:")
    public native void setFormatterBehavior(NSDateFormatterBehavior behavior);
    @Method(selector = "setDateFormat:")
    public native void setDateFormat(String string);
    @Method(selector = "timeZone")
    public native NSTimeZone getTimeZone();
    @Method(selector = "setTimeZone:")
    public native void setTimeZone(NSTimeZone tz);
    @Method(selector = "calendar")
    public native NSCalendar getCalendar();
    @Method(selector = "setCalendar:")
    public native void setCalendar(NSCalendar calendar);
    @Method(selector = "isLenient")
    public native boolean isLenient();
    @Method(selector = "setLenient:")
    public native void setLenient(boolean b);
    @Method(selector = "twoDigitStartDate")
    public native NSDate getTwoDigitStartDate();
    @Method(selector = "setTwoDigitStartDate:")
    public native void setTwoDigitStartDate(NSDate date);
    @Method(selector = "defaultDate")
    public native NSDate getDefaultDate();
    @Method(selector = "setDefaultDate:")
    public native void setDefaultDate(NSDate date);
    @Method(selector = "eraSymbols")
    public native NSArray<NSString> getEraSymbols();
    @Method(selector = "setEraSymbols:")
    public native void setEraSymbols(NSArray<NSString> array);
    @Method(selector = "monthSymbols")
    public native NSArray<NSString> getMonthSymbols();
    @Method(selector = "setMonthSymbols:")
    public native void setMonthSymbols(NSArray<NSString> array);
    @Method(selector = "shortMonthSymbols")
    public native NSArray<NSString> getShortMonthSymbols();
    @Method(selector = "setShortMonthSymbols:")
    public native void setShortMonthSymbols(NSArray<NSString> array);
    @Method(selector = "weekdaySymbols")
    public native NSArray<NSString> getWeekdaySymbols();
    @Method(selector = "setWeekdaySymbols:")
    public native void setWeekdaySymbols(NSArray<NSString> array);
    @Method(selector = "shortWeekdaySymbols")
    public native NSArray<NSString> getShortWeekdaySymbols();
    @Method(selector = "setShortWeekdaySymbols:")
    public native void setShortWeekdaySymbols(NSArray<NSString> array);
    @Method(selector = "AMSymbol")
    public native String getAMSymbol();
    @Method(selector = "setAMSymbol:")
    public native void setAMSymbol(String string);
    @Method(selector = "PMSymbol")
    public native String getPMSymbol();
    @Method(selector = "setPMSymbol:")
    public native void setPMSymbol(String string);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "longEraSymbols")
    public native NSArray<NSString> getLongEraSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setLongEraSymbols:")
    public native void setLongEraSymbols(NSArray<NSString> array);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "veryShortMonthSymbols")
    public native NSArray<NSString> getVeryShortMonthSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setVeryShortMonthSymbols:")
    public native void setVeryShortMonthSymbols(NSArray<NSString> array);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "standaloneMonthSymbols")
    public native NSArray<NSString> getStandaloneMonthSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setStandaloneMonthSymbols:")
    public native void setStandaloneMonthSymbols(NSArray<NSString> array);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "shortStandaloneMonthSymbols")
    public native NSArray<NSString> getShortStandaloneMonthSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setShortStandaloneMonthSymbols:")
    public native void setShortStandaloneMonthSymbols(NSArray<NSString> array);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "veryShortStandaloneMonthSymbols")
    public native NSArray<NSString> getVeryShortStandaloneMonthSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setVeryShortStandaloneMonthSymbols:")
    public native void setVeryShortStandaloneMonthSymbols(NSArray<NSString> array);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "veryShortWeekdaySymbols")
    public native NSArray<NSString> getVeryShortWeekdaySymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setVeryShortWeekdaySymbols:")
    public native void setVeryShortWeekdaySymbols(NSArray<NSString> array);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "standaloneWeekdaySymbols")
    public native NSArray<NSString> getStandaloneWeekdaySymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setStandaloneWeekdaySymbols:")
    public native void setStandaloneWeekdaySymbols(NSArray<NSString> array);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "shortStandaloneWeekdaySymbols")
    public native NSArray<NSString> getShortStandaloneWeekdaySymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setShortStandaloneWeekdaySymbols:")
    public native void setShortStandaloneWeekdaySymbols(NSArray<NSString> array);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "veryShortStandaloneWeekdaySymbols")
    public native NSArray<NSString> getVeryShortStandaloneWeekdaySymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setVeryShortStandaloneWeekdaySymbols:")
    public native void setVeryShortStandaloneWeekdaySymbols(NSArray<NSString> array);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "quarterSymbols")
    public native NSArray<NSString> getQuarterSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setQuarterSymbols:")
    public native void setQuarterSymbols(NSArray<NSString> array);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "shortQuarterSymbols")
    public native NSArray<NSString> getShortQuarterSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setShortQuarterSymbols:")
    public native void setShortQuarterSymbols(NSArray<NSString> array);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "standaloneQuarterSymbols")
    public native NSArray<NSString> getStandaloneQuarterSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setStandaloneQuarterSymbols:")
    public native void setStandaloneQuarterSymbols(NSArray<NSString> array);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "shortStandaloneQuarterSymbols")
    public native NSArray<NSString> getShortStandaloneQuarterSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setShortStandaloneQuarterSymbols:")
    public native void setShortStandaloneQuarterSymbols(NSArray<NSString> array);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "gregorianStartDate")
    public native NSDate getGregorianStartDate();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "setGregorianStartDate:")
    public native void setGregorianStartDate(NSDate date);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "doesRelativeDateFormatting")
    public native boolean doesRelativeDateFormatting();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "setDoesRelativeDateFormatting:")
    public native void setDoesRelativeDateFormatting(boolean b);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "localizedStringFromDate:dateStyle:timeStyle:")
    public static native String format(NSDate date, NSDateFormatterStyle dstyle, NSDateFormatterStyle tstyle);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "dateFormatFromTemplate:options:locale:")
    public static native String getDateFormatFromTemplate(String tmplate, @MachineSizedUInt long opts, NSLocale locale);
    @Method(selector = "defaultFormatterBehavior")
    public static native NSDateFormatterBehavior getDefaultFormatterBehavior();
    @Method(selector = "setDefaultFormatterBehavior:")
    public static native void setDefaultFormatterBehavior(NSDateFormatterBehavior behavior);
    /*</methods>*/
}
