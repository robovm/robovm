/*
 * Copyright (C) 2014 Trillian AB
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

/**
 *
 * <div class="javadoc"></div>
 */
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
    @Method(selector = "getObjectValue:forString:range:error:")
    public native boolean getObjectValue$forString$range$error$(NSObject obj, String string, NSRange rangep, NSError.NSErrorPtr error);
    @Method(selector = "stringFromDate:")
    public native String stringFromDate$(NSDate date);
    @Method(selector = "dateFromString:")
    public native NSDate dateFromString$(String string);
    @Method(selector = "dateFormat")
    public native String dateFormat();
    @Method(selector = "dateStyle")
    public native NSDateFormatterStyle dateStyle();
    @Method(selector = "setDateStyle:")
    public native void setDateStyle$(NSDateFormatterStyle style);
    @Method(selector = "timeStyle")
    public native NSDateFormatterStyle timeStyle();
    @Method(selector = "setTimeStyle:")
    public native void setTimeStyle$(NSDateFormatterStyle style);
    @Method(selector = "locale")
    public native NSLocale locale();
    @Method(selector = "setLocale:")
    public native void setLocale$(NSLocale locale);
    @Method(selector = "generatesCalendarDates")
    public native boolean generatesCalendarDates();
    @Method(selector = "setGeneratesCalendarDates:")
    public native void setGeneratesCalendarDates$(boolean b);
    @Method(selector = "formatterBehavior")
    public native NSDateFormatterBehavior formatterBehavior();
    @Method(selector = "setFormatterBehavior:")
    public native void setFormatterBehavior$(NSDateFormatterBehavior behavior);
    @Method(selector = "setDateFormat:")
    public native void setDateFormat$(String string);
    @Method(selector = "timeZone")
    public native NSTimeZone timeZone();
    @Method(selector = "setTimeZone:")
    public native void setTimeZone$(NSTimeZone tz);
    @Method(selector = "calendar")
    public native NSCalendar calendar();
    @Method(selector = "setCalendar:")
    public native void setCalendar$(NSCalendar calendar);
    @Method(selector = "isLenient")
    public native boolean isLenient();
    @Method(selector = "setLenient:")
    public native void setLenient$(boolean b);
    @Method(selector = "twoDigitStartDate")
    public native NSDate twoDigitStartDate();
    @Method(selector = "setTwoDigitStartDate:")
    public native void setTwoDigitStartDate$(NSDate date);
    @Method(selector = "defaultDate")
    public native NSDate defaultDate();
    @Method(selector = "setDefaultDate:")
    public native void setDefaultDate$(NSDate date);
    @Method(selector = "eraSymbols")
    public native NSArray<?> eraSymbols();
    @Method(selector = "setEraSymbols:")
    public native void setEraSymbols$(NSArray<?> array);
    @Method(selector = "monthSymbols")
    public native NSArray<?> monthSymbols();
    @Method(selector = "setMonthSymbols:")
    public native void setMonthSymbols$(NSArray<?> array);
    @Method(selector = "shortMonthSymbols")
    public native NSArray<?> shortMonthSymbols();
    @Method(selector = "setShortMonthSymbols:")
    public native void setShortMonthSymbols$(NSArray<?> array);
    @Method(selector = "weekdaySymbols")
    public native NSArray<?> weekdaySymbols();
    @Method(selector = "setWeekdaySymbols:")
    public native void setWeekdaySymbols$(NSArray<?> array);
    @Method(selector = "shortWeekdaySymbols")
    public native NSArray<?> shortWeekdaySymbols();
    @Method(selector = "setShortWeekdaySymbols:")
    public native void setShortWeekdaySymbols$(NSArray<?> array);
    @Method(selector = "AMSymbol")
    public native String AMSymbol();
    @Method(selector = "setAMSymbol:")
    public native void setAMSymbol$(String string);
    @Method(selector = "PMSymbol")
    public native String PMSymbol();
    @Method(selector = "setPMSymbol:")
    public native void setPMSymbol$(String string);
    @Method(selector = "longEraSymbols")
    public native NSArray<?> longEraSymbols();
    @Method(selector = "setLongEraSymbols:")
    public native void setLongEraSymbols$(NSArray<?> array);
    @Method(selector = "veryShortMonthSymbols")
    public native NSArray<?> veryShortMonthSymbols();
    @Method(selector = "setVeryShortMonthSymbols:")
    public native void setVeryShortMonthSymbols$(NSArray<?> array);
    @Method(selector = "standaloneMonthSymbols")
    public native NSArray<?> standaloneMonthSymbols();
    @Method(selector = "setStandaloneMonthSymbols:")
    public native void setStandaloneMonthSymbols$(NSArray<?> array);
    @Method(selector = "shortStandaloneMonthSymbols")
    public native NSArray<?> shortStandaloneMonthSymbols();
    @Method(selector = "setShortStandaloneMonthSymbols:")
    public native void setShortStandaloneMonthSymbols$(NSArray<?> array);
    @Method(selector = "veryShortStandaloneMonthSymbols")
    public native NSArray<?> veryShortStandaloneMonthSymbols();
    @Method(selector = "setVeryShortStandaloneMonthSymbols:")
    public native void setVeryShortStandaloneMonthSymbols$(NSArray<?> array);
    @Method(selector = "veryShortWeekdaySymbols")
    public native NSArray<?> veryShortWeekdaySymbols();
    @Method(selector = "setVeryShortWeekdaySymbols:")
    public native void setVeryShortWeekdaySymbols$(NSArray<?> array);
    @Method(selector = "standaloneWeekdaySymbols")
    public native NSArray<?> standaloneWeekdaySymbols();
    @Method(selector = "setStandaloneWeekdaySymbols:")
    public native void setStandaloneWeekdaySymbols$(NSArray<?> array);
    @Method(selector = "shortStandaloneWeekdaySymbols")
    public native NSArray<?> shortStandaloneWeekdaySymbols();
    @Method(selector = "setShortStandaloneWeekdaySymbols:")
    public native void setShortStandaloneWeekdaySymbols$(NSArray<?> array);
    @Method(selector = "veryShortStandaloneWeekdaySymbols")
    public native NSArray<?> veryShortStandaloneWeekdaySymbols();
    @Method(selector = "setVeryShortStandaloneWeekdaySymbols:")
    public native void setVeryShortStandaloneWeekdaySymbols$(NSArray<?> array);
    @Method(selector = "quarterSymbols")
    public native NSArray<?> quarterSymbols();
    @Method(selector = "setQuarterSymbols:")
    public native void setQuarterSymbols$(NSArray<?> array);
    @Method(selector = "shortQuarterSymbols")
    public native NSArray<?> shortQuarterSymbols();
    @Method(selector = "setShortQuarterSymbols:")
    public native void setShortQuarterSymbols$(NSArray<?> array);
    @Method(selector = "standaloneQuarterSymbols")
    public native NSArray<?> standaloneQuarterSymbols();
    @Method(selector = "setStandaloneQuarterSymbols:")
    public native void setStandaloneQuarterSymbols$(NSArray<?> array);
    @Method(selector = "shortStandaloneQuarterSymbols")
    public native NSArray<?> shortStandaloneQuarterSymbols();
    @Method(selector = "setShortStandaloneQuarterSymbols:")
    public native void setShortStandaloneQuarterSymbols$(NSArray<?> array);
    @Method(selector = "gregorianStartDate")
    public native NSDate gregorianStartDate();
    @Method(selector = "setGregorianStartDate:")
    public native void setGregorianStartDate$(NSDate date);
    @Method(selector = "doesRelativeDateFormatting")
    public native boolean doesRelativeDateFormatting();
    @Method(selector = "setDoesRelativeDateFormatting:")
    public native void setDoesRelativeDateFormatting$(boolean b);
    @Method(selector = "localizedStringFromDate:dateStyle:timeStyle:")
    public static native String localizedStringFromDate$dateStyle$timeStyle$(NSDate date, NSDateFormatterStyle dstyle, NSDateFormatterStyle tstyle);
    @Method(selector = "dateFormatFromTemplate:options:locale:")
    public static native String dateFormatFromTemplate$options$locale$(String tmplate, @MachineSizedUInt long opts, NSLocale locale);
    @Method(selector = "defaultFormatterBehavior")
    public static native NSDateFormatterBehavior defaultFormatterBehavior();
    @Method(selector = "setDefaultFormatterBehavior:")
    public static native void setDefaultFormatterBehavior$(NSDateFormatterBehavior behavior);
    /*</methods>*/
}
