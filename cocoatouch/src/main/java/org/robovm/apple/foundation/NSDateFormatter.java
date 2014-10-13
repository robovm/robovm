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
import org.robovm.apple.dispatch.*;
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
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "formattingContext")
    public native NSFormattingContext getFormattingContext();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setFormattingContext:")
    public native void setFormattingContext(NSFormattingContext v);
    @Property(selector = "dateFormat")
    public native String getDateFormat();
    @Property(selector = "setDateFormat:")
    public native void setDateFormat(String v);
    @Property(selector = "dateStyle")
    public native NSDateFormatterStyle getDateStyle();
    @Property(selector = "setDateStyle:")
    public native void setDateStyle(NSDateFormatterStyle v);
    @Property(selector = "timeStyle")
    public native NSDateFormatterStyle getTimeStyle();
    @Property(selector = "setTimeStyle:")
    public native void setTimeStyle(NSDateFormatterStyle v);
    @Property(selector = "locale")
    public native NSLocale getLocale();
    @Property(selector = "setLocale:")
    public native void setLocale(NSLocale v);
    @Property(selector = "generatesCalendarDates")
    public native boolean isGeneratesCalendarDates();
    @Property(selector = "setGeneratesCalendarDates:")
    public native void setGeneratesCalendarDates(boolean v);
    @Property(selector = "formatterBehavior")
    public native NSDateFormatterBehavior getFormatterBehavior();
    @Property(selector = "setFormatterBehavior:")
    public native void setFormatterBehavior(NSDateFormatterBehavior v);
    @Property(selector = "timeZone")
    public native NSTimeZone getTimeZone();
    @Property(selector = "setTimeZone:")
    public native void setTimeZone(NSTimeZone v);
    @Property(selector = "calendar")
    public native NSCalendar getCalendar();
    @Property(selector = "setCalendar:")
    public native void setCalendar(NSCalendar v);
    @Property(selector = "isLenient")
    public native boolean isLenient();
    @Property(selector = "setLenient:")
    public native void setLenient(boolean v);
    @Property(selector = "twoDigitStartDate")
    public native NSDate getTwoDigitStartDate();
    @Property(selector = "setTwoDigitStartDate:")
    public native void setTwoDigitStartDate(NSDate v);
    @Property(selector = "defaultDate")
    public native NSDate getDefaultDate();
    @Property(selector = "setDefaultDate:")
    public native void setDefaultDate(NSDate v);
    @Property(selector = "eraSymbols")
    public native NSArray<?> getEraSymbols();
    @Property(selector = "setEraSymbols:")
    public native void setEraSymbols(NSArray<?> v);
    @Property(selector = "monthSymbols")
    public native NSArray<?> getMonthSymbols();
    @Property(selector = "setMonthSymbols:")
    public native void setMonthSymbols(NSArray<?> v);
    @Property(selector = "shortMonthSymbols")
    public native NSArray<?> getShortMonthSymbols();
    @Property(selector = "setShortMonthSymbols:")
    public native void setShortMonthSymbols(NSArray<?> v);
    @Property(selector = "weekdaySymbols")
    public native NSArray<?> getWeekdaySymbols();
    @Property(selector = "setWeekdaySymbols:")
    public native void setWeekdaySymbols(NSArray<?> v);
    @Property(selector = "shortWeekdaySymbols")
    public native NSArray<?> getShortWeekdaySymbols();
    @Property(selector = "setShortWeekdaySymbols:")
    public native void setShortWeekdaySymbols(NSArray<?> v);
    @Property(selector = "AMSymbol")
    public native String getAMSymbol();
    @Property(selector = "setAMSymbol:")
    public native void setAMSymbol(String v);
    @Property(selector = "PMSymbol")
    public native String getPMSymbol();
    @Property(selector = "setPMSymbol:")
    public native void setPMSymbol(String v);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "longEraSymbols")
    public native NSArray<?> getLongEraSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "setLongEraSymbols:")
    public native void setLongEraSymbols(NSArray<?> v);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "veryShortMonthSymbols")
    public native NSArray<?> getVeryShortMonthSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "setVeryShortMonthSymbols:")
    public native void setVeryShortMonthSymbols(NSArray<?> v);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "standaloneMonthSymbols")
    public native NSArray<?> getStandaloneMonthSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "setStandaloneMonthSymbols:")
    public native void setStandaloneMonthSymbols(NSArray<?> v);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "shortStandaloneMonthSymbols")
    public native NSArray<?> getShortStandaloneMonthSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "setShortStandaloneMonthSymbols:")
    public native void setShortStandaloneMonthSymbols(NSArray<?> v);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "veryShortStandaloneMonthSymbols")
    public native NSArray<?> getVeryShortStandaloneMonthSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "setVeryShortStandaloneMonthSymbols:")
    public native void setVeryShortStandaloneMonthSymbols(NSArray<?> v);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "veryShortWeekdaySymbols")
    public native NSArray<?> getVeryShortWeekdaySymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "setVeryShortWeekdaySymbols:")
    public native void setVeryShortWeekdaySymbols(NSArray<?> v);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "standaloneWeekdaySymbols")
    public native NSArray<?> getStandaloneWeekdaySymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "setStandaloneWeekdaySymbols:")
    public native void setStandaloneWeekdaySymbols(NSArray<?> v);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "shortStandaloneWeekdaySymbols")
    public native NSArray<?> getShortStandaloneWeekdaySymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "setShortStandaloneWeekdaySymbols:")
    public native void setShortStandaloneWeekdaySymbols(NSArray<?> v);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "veryShortStandaloneWeekdaySymbols")
    public native NSArray<?> getVeryShortStandaloneWeekdaySymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "setVeryShortStandaloneWeekdaySymbols:")
    public native void setVeryShortStandaloneWeekdaySymbols(NSArray<?> v);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "quarterSymbols")
    public native NSArray<?> getQuarterSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "setQuarterSymbols:")
    public native void setQuarterSymbols(NSArray<?> v);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "shortQuarterSymbols")
    public native NSArray<?> getShortQuarterSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "setShortQuarterSymbols:")
    public native void setShortQuarterSymbols(NSArray<?> v);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "standaloneQuarterSymbols")
    public native NSArray<?> getStandaloneQuarterSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "setStandaloneQuarterSymbols:")
    public native void setStandaloneQuarterSymbols(NSArray<?> v);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "shortStandaloneQuarterSymbols")
    public native NSArray<?> getShortStandaloneQuarterSymbols();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "setShortStandaloneQuarterSymbols:")
    public native void setShortStandaloneQuarterSymbols(NSArray<?> v);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "gregorianStartDate")
    public native NSDate getGregorianStartDate();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Property(selector = "setGregorianStartDate:")
    public native void setGregorianStartDate(NSDate v);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "doesRelativeDateFormatting")
    public native boolean isDoesRelativeDateFormatting();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "setDoesRelativeDateFormatting:")
    public native void setDoesRelativeDateFormatting(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "stringFromDate:")
    public native String format(NSDate date);
    @Method(selector = "dateFromString:")
    public native NSDate parse(String string);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "setLocalizedDateFormatFromTemplate:")
    public native void setLocalizedDateFormatFromTemplate(String dateFormatTemplate);
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
