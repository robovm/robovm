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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSCalendar/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSCalendarPtr extends Ptr<NSCalendar, NSCalendarPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSCalendar.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSCalendar() {}
    protected NSCalendar(SkipInit skipInit) { super(skipInit); }
    public NSCalendar(String ident) { super((SkipInit) null); initObject(initWithCalendarIdentifier$(ident)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithCalendarIdentifier:")
    protected native @Pointer long initWithCalendarIdentifier$(String ident);
    @Method(selector = "calendarIdentifier")
    public native String calendarIdentifier();
    @Method(selector = "setLocale:")
    public native void setLocale(NSLocale locale);
    @Method(selector = "locale")
    public native NSLocale locale();
    @Method(selector = "setTimeZone:")
    public native void setTimeZone(NSTimeZone tz);
    @Method(selector = "timeZone")
    public native NSTimeZone timeZone();
    @Method(selector = "setFirstWeekday:")
    public native void setFirstWeekday(@MachineSizedUInt long weekday);
    @Method(selector = "firstWeekday")
    public native @MachineSizedUInt long firstWeekday();
    @Method(selector = "setMinimumDaysInFirstWeek:")
    public native void setMinimumDaysInFirstWeek(@MachineSizedUInt long mdw);
    @Method(selector = "minimumDaysInFirstWeek")
    public native @MachineSizedUInt long minimumDaysInFirstWeek();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "eraSymbols")
    public native NSArray<?> eraSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "longEraSymbols")
    public native NSArray<?> longEraSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "monthSymbols")
    public native NSArray<?> monthSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "shortMonthSymbols")
    public native NSArray<?> shortMonthSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "veryShortMonthSymbols")
    public native NSArray<?> veryShortMonthSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "standaloneMonthSymbols")
    public native NSArray<?> standaloneMonthSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "shortStandaloneMonthSymbols")
    public native NSArray<?> shortStandaloneMonthSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "veryShortStandaloneMonthSymbols")
    public native NSArray<?> veryShortStandaloneMonthSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "weekdaySymbols")
    public native NSArray<?> weekdaySymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "shortWeekdaySymbols")
    public native NSArray<?> shortWeekdaySymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "veryShortWeekdaySymbols")
    public native NSArray<?> veryShortWeekdaySymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "standaloneWeekdaySymbols")
    public native NSArray<?> standaloneWeekdaySymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "shortStandaloneWeekdaySymbols")
    public native NSArray<?> shortStandaloneWeekdaySymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "veryShortStandaloneWeekdaySymbols")
    public native NSArray<?> veryShortStandaloneWeekdaySymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "quarterSymbols")
    public native NSArray<?> quarterSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "shortQuarterSymbols")
    public native NSArray<?> shortQuarterSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "standaloneQuarterSymbols")
    public native NSArray<?> standaloneQuarterSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "shortStandaloneQuarterSymbols")
    public native NSArray<?> shortStandaloneQuarterSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "AMSymbol")
    public native String AMSymbol();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "PMSymbol")
    public native String PMSymbol();
    @Method(selector = "minimumRangeOfUnit:")
    public native @ByVal NSRange minimumRangeOfUnit$(NSCalendarUnit unit);
    @Method(selector = "maximumRangeOfUnit:")
    public native @ByVal NSRange maximumRangeOfUnit$(NSCalendarUnit unit);
    @Method(selector = "rangeOfUnit:inUnit:forDate:")
    public native @ByVal NSRange rangeOfUnit$inUnit$forDate$(NSCalendarUnit smaller, NSCalendarUnit larger, NSDate date);
    @Method(selector = "ordinalityOfUnit:inUnit:forDate:")
    public native @MachineSizedUInt long ordinalityOfUnit$inUnit$forDate$(NSCalendarUnit smaller, NSCalendarUnit larger, NSDate date);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "rangeOfUnit:startDate:interval:forDate:")
    public native boolean rangeOfUnit$startDate$interval$forDate$(NSCalendarUnit unit, NSDate.NSDatePtr datep, DoublePtr tip, NSDate date);
    @Method(selector = "dateFromComponents:")
    public native NSDate dateFromComponents$(NSDateComponents comps);
    @Method(selector = "components:fromDate:")
    public native NSDateComponents components$fromDate$(NSCalendarUnit unitFlags, NSDate date);
    @Method(selector = "dateByAddingComponents:toDate:options:")
    public native NSDate dateByAddingComponents$toDate$options$(NSDateComponents comps, NSDate date, NSCalendarOptions opts);
    @Method(selector = "components:fromDate:toDate:options:")
    public native NSDateComponents components$fromDate$toDate$options$(NSCalendarUnit unitFlags, NSDate startingDate, NSDate resultDate, NSCalendarOptions opts);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "getEra:year:month:day:fromDate:")
    public native void getEra$year$month$day$fromDate$(MachineSizedSIntPtr eraValuePointer, MachineSizedSIntPtr yearValuePointer, MachineSizedSIntPtr monthValuePointer, MachineSizedSIntPtr dayValuePointer, NSDate date);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "getEra:yearForWeekOfYear:weekOfYear:weekday:fromDate:")
    public native void getEra$yearForWeekOfYear$weekOfYear$weekday$fromDate$(MachineSizedSIntPtr eraValuePointer, MachineSizedSIntPtr yearValuePointer, MachineSizedSIntPtr weekValuePointer, MachineSizedSIntPtr weekdayValuePointer, NSDate date);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "getHour:minute:second:nanosecond:fromDate:")
    public native void getHour$minute$second$nanosecond$fromDate$(MachineSizedSIntPtr hourValuePointer, MachineSizedSIntPtr minuteValuePointer, MachineSizedSIntPtr secondValuePointer, MachineSizedSIntPtr nanosecondValuePointer, NSDate date);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "component:fromDate:")
    public native @MachineSizedSInt long component$fromDate$(NSCalendarUnit unit, NSDate date);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "dateWithEra:year:month:day:hour:minute:second:nanosecond:")
    public native NSDate dateWithEra$year$month$day$hour$minute$second$nanosecond$(@MachineSizedSInt long eraValue, @MachineSizedSInt long yearValue, @MachineSizedSInt long monthValue, @MachineSizedSInt long dayValue, @MachineSizedSInt long hourValue, @MachineSizedSInt long minuteValue, @MachineSizedSInt long secondValue, @MachineSizedSInt long nanosecondValue);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "dateWithEra:yearForWeekOfYear:weekOfYear:weekday:hour:minute:second:nanosecond:")
    public native NSDate dateWithEra$yearForWeekOfYear$weekOfYear$weekday$hour$minute$second$nanosecond$(@MachineSizedSInt long eraValue, @MachineSizedSInt long yearValue, @MachineSizedSInt long weekValue, @MachineSizedSInt long weekdayValue, @MachineSizedSInt long hourValue, @MachineSizedSInt long minuteValue, @MachineSizedSInt long secondValue, @MachineSizedSInt long nanosecondValue);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "startOfDayForDate:")
    public native NSDate startOfDayForDate$(NSDate date);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "componentsInTimeZone:fromDate:")
    public native NSDateComponents componentsInTimeZone$fromDate$(NSTimeZone timezone, NSDate date);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "compareDate:toDate:toUnitGranularity:")
    public native NSComparisonResult compareDate$toDate$toUnitGranularity$(NSDate date1, NSDate date2, NSCalendarUnit unit);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "isDate:equalToDate:toUnitGranularity:")
    public native boolean isDate$equalToDate$toUnitGranularity$(NSDate date1, NSDate date2, NSCalendarUnit unit);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "isDate:inSameDayAsDate:")
    public native boolean isDate$inSameDayAsDate$(NSDate date1, NSDate date2);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "isDateInToday:")
    public native boolean isDateInToday$(NSDate date);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "isDateInYesterday:")
    public native boolean isDateInYesterday$(NSDate date);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "isDateInTomorrow:")
    public native boolean isDateInTomorrow$(NSDate date);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "isDateInWeekend:")
    public native boolean isDateInWeekend$(NSDate date);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "rangeOfWeekendStartDate:interval:containingDate:")
    public native boolean rangeOfWeekendStartDate$interval$containingDate$(NSDate.NSDatePtr datep, DoublePtr tip, NSDate date);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "nextWeekendStartDate:interval:options:afterDate:")
    public native boolean nextWeekendStartDate$interval$options$afterDate$(NSDate.NSDatePtr datep, DoublePtr tip, NSCalendarOptions options, NSDate date);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "components:fromDateComponents:toDateComponents:options:")
    public native NSDateComponents components$fromDateComponents$toDateComponents$options$(NSCalendarUnit unitFlags, NSDateComponents startingDateComp, NSDateComponents resultDateComp, NSCalendarOptions options);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "dateByAddingUnit:value:toDate:options:")
    public native NSDate dateByAddingUnit$value$toDate$options$(NSCalendarUnit unit, @MachineSizedSInt long value, NSDate date, NSCalendarOptions options);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "enumerateDatesStartingAfterDate:matchingComponents:options:usingBlock:")
    public native void enumerateDatesStartingAfterDate$matchingComponents$options$usingBlock$(NSDate start, NSDateComponents comps, NSCalendarOptions opts, ObjCBlock block);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "nextDateAfterDate:matchingComponents:options:")
    public native NSDate nextDateAfterDate$matchingComponents$options$(NSDate date, NSDateComponents comps, NSCalendarOptions options);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "nextDateAfterDate:matchingUnit:value:options:")
    public native NSDate nextDateAfterDate$matchingUnit$value$options$(NSDate date, NSCalendarUnit unit, @MachineSizedSInt long value, NSCalendarOptions options);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "nextDateAfterDate:matchingHour:minute:second:options:")
    public native NSDate nextDateAfterDate$matchingHour$minute$second$options$(NSDate date, @MachineSizedSInt long hourValue, @MachineSizedSInt long minuteValue, @MachineSizedSInt long secondValue, NSCalendarOptions options);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "dateBySettingUnit:value:ofDate:options:")
    public native NSDate dateBySettingUnit$value$ofDate$options$(NSCalendarUnit unit, @MachineSizedSInt long v, NSDate date, NSCalendarOptions opts);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "dateBySettingHour:minute:second:ofDate:options:")
    public native NSDate dateBySettingHour$minute$second$ofDate$options$(@MachineSizedSInt long h, @MachineSizedSInt long m, @MachineSizedSInt long s, NSDate date, NSCalendarOptions opts);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "date:matchesComponents:")
    public native boolean date$matchesComponents$(NSDate date, NSDateComponents components);
    @Method(selector = "currentCalendar")
    public static native NSObject currentCalendar();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "autoupdatingCurrentCalendar")
    public static native NSObject autoupdatingCurrentCalendar();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "calendarWithIdentifier:")
    public static native NSObject calendarWithIdentifier$(String calendarIdentifierConstant);
    /*</methods>*/
}
