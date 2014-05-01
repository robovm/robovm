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

/**
 *
 * <div class="javadoc"></div>
 */
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
    @GlobalValue(symbol="NSCalendarIdentifierGregorian", optional=true)
    public static native String CalendarIdentifierGregorian();
    @GlobalValue(symbol="NSCalendarIdentifierBuddhist", optional=true)
    public static native String CalendarIdentifierBuddhist();
    @GlobalValue(symbol="NSCalendarIdentifierChinese", optional=true)
    public static native String CalendarIdentifierChinese();
    @GlobalValue(symbol="NSCalendarIdentifierCoptic", optional=true)
    public static native String CalendarIdentifierCoptic();
    @GlobalValue(symbol="NSCalendarIdentifierEthiopicAmeteMihret", optional=true)
    public static native String CalendarIdentifierEthiopicAmeteMihret();
    @GlobalValue(symbol="NSCalendarIdentifierEthiopicAmeteAlem", optional=true)
    public static native String CalendarIdentifierEthiopicAmeteAlem();
    @GlobalValue(symbol="NSCalendarIdentifierHebrew", optional=true)
    public static native String CalendarIdentifierHebrew();
    @GlobalValue(symbol="NSCalendarIdentifierISO8601", optional=true)
    public static native String CalendarIdentifierISO8601();
    @GlobalValue(symbol="NSCalendarIdentifierIndian", optional=true)
    public static native String CalendarIdentifierIndian();
    @GlobalValue(symbol="NSCalendarIdentifierIslamic", optional=true)
    public static native String CalendarIdentifierIslamic();
    @GlobalValue(symbol="NSCalendarIdentifierIslamicCivil", optional=true)
    public static native String CalendarIdentifierIslamicCivil();
    @GlobalValue(symbol="NSCalendarIdentifierJapanese", optional=true)
    public static native String CalendarIdentifierJapanese();
    @GlobalValue(symbol="NSCalendarIdentifierPersian", optional=true)
    public static native String CalendarIdentifierPersian();
    @GlobalValue(symbol="NSCalendarIdentifierRepublicOfChina", optional=true)
    public static native String CalendarIdentifierRepublicOfChina();
    @GlobalValue(symbol="NSCalendarDayChangedNotification", optional=true)
    public static native String NotificationDayChanged();
    
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
    @Method(selector = "eraSymbols")
    public native NSArray<?> eraSymbols();
    @Method(selector = "longEraSymbols")
    public native NSArray<?> longEraSymbols();
    @Method(selector = "monthSymbols")
    public native NSArray<?> monthSymbols();
    @Method(selector = "shortMonthSymbols")
    public native NSArray<?> shortMonthSymbols();
    @Method(selector = "veryShortMonthSymbols")
    public native NSArray<?> veryShortMonthSymbols();
    @Method(selector = "standaloneMonthSymbols")
    public native NSArray<?> standaloneMonthSymbols();
    @Method(selector = "shortStandaloneMonthSymbols")
    public native NSArray<?> shortStandaloneMonthSymbols();
    @Method(selector = "veryShortStandaloneMonthSymbols")
    public native NSArray<?> veryShortStandaloneMonthSymbols();
    @Method(selector = "weekdaySymbols")
    public native NSArray<?> weekdaySymbols();
    @Method(selector = "shortWeekdaySymbols")
    public native NSArray<?> shortWeekdaySymbols();
    @Method(selector = "veryShortWeekdaySymbols")
    public native NSArray<?> veryShortWeekdaySymbols();
    @Method(selector = "standaloneWeekdaySymbols")
    public native NSArray<?> standaloneWeekdaySymbols();
    @Method(selector = "shortStandaloneWeekdaySymbols")
    public native NSArray<?> shortStandaloneWeekdaySymbols();
    @Method(selector = "veryShortStandaloneWeekdaySymbols")
    public native NSArray<?> veryShortStandaloneWeekdaySymbols();
    @Method(selector = "quarterSymbols")
    public native NSArray<?> quarterSymbols();
    @Method(selector = "shortQuarterSymbols")
    public native NSArray<?> shortQuarterSymbols();
    @Method(selector = "standaloneQuarterSymbols")
    public native NSArray<?> standaloneQuarterSymbols();
    @Method(selector = "shortStandaloneQuarterSymbols")
    public native NSArray<?> shortStandaloneQuarterSymbols();
    @Method(selector = "AMSymbol")
    public native String AMSymbol();
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
    @Method(selector = "getEra:year:month:day:fromDate:")
    public native void getEra$year$month$day$fromDate$(MachineSizedSIntPtr eraValuePointer, MachineSizedSIntPtr yearValuePointer, MachineSizedSIntPtr monthValuePointer, MachineSizedSIntPtr dayValuePointer, NSDate date);
    @Method(selector = "getEra:yearForWeekOfYear:weekOfYear:weekday:fromDate:")
    public native void getEra$yearForWeekOfYear$weekOfYear$weekday$fromDate$(MachineSizedSIntPtr eraValuePointer, MachineSizedSIntPtr yearValuePointer, MachineSizedSIntPtr weekValuePointer, MachineSizedSIntPtr weekdayValuePointer, NSDate date);
    @Method(selector = "getHour:minute:second:nanosecond:fromDate:")
    public native void getHour$minute$second$nanosecond$fromDate$(MachineSizedSIntPtr hourValuePointer, MachineSizedSIntPtr minuteValuePointer, MachineSizedSIntPtr secondValuePointer, MachineSizedSIntPtr nanosecondValuePointer, NSDate date);
    @Method(selector = "component:fromDate:")
    public native @MachineSizedSInt long component$fromDate$(NSCalendarUnit unit, NSDate date);
    @Method(selector = "dateWithEra:year:month:day:hour:minute:second:nanosecond:")
    public native NSDate dateWithEra$year$month$day$hour$minute$second$nanosecond$(@MachineSizedSInt long eraValue, @MachineSizedSInt long yearValue, @MachineSizedSInt long monthValue, @MachineSizedSInt long dayValue, @MachineSizedSInt long hourValue, @MachineSizedSInt long minuteValue, @MachineSizedSInt long secondValue, @MachineSizedSInt long nanosecondValue);
    @Method(selector = "dateWithEra:yearForWeekOfYear:weekOfYear:weekday:hour:minute:second:nanosecond:")
    public native NSDate dateWithEra$yearForWeekOfYear$weekOfYear$weekday$hour$minute$second$nanosecond$(@MachineSizedSInt long eraValue, @MachineSizedSInt long yearValue, @MachineSizedSInt long weekValue, @MachineSizedSInt long weekdayValue, @MachineSizedSInt long hourValue, @MachineSizedSInt long minuteValue, @MachineSizedSInt long secondValue, @MachineSizedSInt long nanosecondValue);
    @Method(selector = "startOfDayForDate:")
    public native NSDate startOfDayForDate$(NSDate date);
    @Method(selector = "componentsInTimeZone:fromDate:")
    public native NSDateComponents componentsInTimeZone$fromDate$(NSTimeZone timezone, NSDate date);
    @Method(selector = "compareDate:toDate:toUnitGranularity:")
    public native NSComparisonResult compareDate$toDate$toUnitGranularity$(NSDate date1, NSDate date2, NSCalendarUnit unit);
    @Method(selector = "isDate:equalToDate:toUnitGranularity:")
    public native boolean isDate$equalToDate$toUnitGranularity$(NSDate date1, NSDate date2, NSCalendarUnit unit);
    @Method(selector = "isDate:inSameDayAsDate:")
    public native boolean isDate$inSameDayAsDate$(NSDate date1, NSDate date2);
    @Method(selector = "isDateInToday:")
    public native boolean isDateInToday$(NSDate date);
    @Method(selector = "isDateInYesterday:")
    public native boolean isDateInYesterday$(NSDate date);
    @Method(selector = "isDateInTomorrow:")
    public native boolean isDateInTomorrow$(NSDate date);
    @Method(selector = "isDateInWeekend:")
    public native boolean isDateInWeekend$(NSDate date);
    @Method(selector = "rangeOfWeekendStartDate:interval:containingDate:")
    public native boolean rangeOfWeekendStartDate$interval$containingDate$(NSDate.NSDatePtr datep, DoublePtr tip, NSDate date);
    @Method(selector = "nextWeekendStartDate:interval:options:afterDate:")
    public native boolean nextWeekendStartDate$interval$options$afterDate$(NSDate.NSDatePtr datep, DoublePtr tip, NSCalendarOptions options, NSDate date);
    @Method(selector = "components:fromDateComponents:toDateComponents:options:")
    public native NSDateComponents components$fromDateComponents$toDateComponents$options$(NSCalendarUnit unitFlags, NSDateComponents startingDateComp, NSDateComponents resultDateComp, NSCalendarOptions options);
    @Method(selector = "dateByAddingUnit:value:toDate:options:")
    public native NSDate dateByAddingUnit$value$toDate$options$(NSCalendarUnit unit, @MachineSizedSInt long value, NSDate date, NSCalendarOptions options);
    @Method(selector = "enumerateDatesStartingAfterDate:matchingComponents:options:usingBlock:")
    public native void enumerateDatesStartingAfterDate$matchingComponents$options$usingBlock$(NSDate start, NSDateComponents comps, NSCalendarOptions opts, ObjCBlock block);
    @Method(selector = "nextDateAfterDate:matchingComponents:options:")
    public native NSDate nextDateAfterDate$matchingComponents$options$(NSDate date, NSDateComponents comps, NSCalendarOptions options);
    @Method(selector = "nextDateAfterDate:matchingUnit:value:options:")
    public native NSDate nextDateAfterDate$matchingUnit$value$options$(NSDate date, NSCalendarUnit unit, @MachineSizedSInt long value, NSCalendarOptions options);
    @Method(selector = "nextDateAfterDate:matchingHour:minute:second:options:")
    public native NSDate nextDateAfterDate$matchingHour$minute$second$options$(NSDate date, @MachineSizedSInt long hourValue, @MachineSizedSInt long minuteValue, @MachineSizedSInt long secondValue, NSCalendarOptions options);
    @Method(selector = "dateBySettingUnit:value:ofDate:options:")
    public native NSDate dateBySettingUnit$value$ofDate$options$(NSCalendarUnit unit, @MachineSizedSInt long v, NSDate date, NSCalendarOptions opts);
    @Method(selector = "dateBySettingHour:minute:second:ofDate:options:")
    public native NSDate dateBySettingHour$minute$second$ofDate$options$(@MachineSizedSInt long h, @MachineSizedSInt long m, @MachineSizedSInt long s, NSDate date, NSCalendarOptions opts);
    @Method(selector = "date:matchesComponents:")
    public native boolean date$matchesComponents$(NSDate date, NSDateComponents components);
    @Method(selector = "currentCalendar")
    public static native NSObject currentCalendar();
    @Method(selector = "autoupdatingCurrentCalendar")
    public static native NSObject autoupdatingCurrentCalendar();
    @Method(selector = "calendarWithIdentifier:")
    public static native NSObject calendarWithIdentifier$(String calendarIdentifierConstant);
    /*</methods>*/
}
