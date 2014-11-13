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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSCalendar/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        /**
         * @since Available in iOS 7.0 and later.
         */
        public static NSObject observeDayChanged(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DayChangedNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
    }
    
    /*<ptr>*/public static class NSCalendarPtr extends Ptr<NSCalendar, NSCalendarPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSCalendar.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSCalendar() {}
    protected NSCalendar(SkipInit skipInit) { super(skipInit); }
    public NSCalendar(NSCalendarIdentifier ident) { super((SkipInit) null); initObject(initWithCalendarIdentifier$(ident)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "calendarIdentifier")
    public native NSCalendarIdentifier getCalendarIdentifier();
    @Property(selector = "locale")
    public native NSLocale getLocale();
    @Property(selector = "setLocale:")
    public native void setLocale(NSLocale v);
    @Property(selector = "timeZone")
    public native NSTimeZone getTimeZone();
    @Property(selector = "setTimeZone:")
    public native void setTimeZone(NSTimeZone v);
    @Property(selector = "firstWeekday")
    public native @MachineSizedUInt long getFirstWeekday();
    @Property(selector = "setFirstWeekday:")
    public native void setFirstWeekday(@MachineSizedUInt long v);
    @Property(selector = "minimumDaysInFirstWeek")
    public native @MachineSizedUInt long getMinimumDaysInFirstWeek();
    @Property(selector = "setMinimumDaysInFirstWeek:")
    public native void setMinimumDaysInFirstWeek(@MachineSizedUInt long v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "eraSymbols")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getEraSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "longEraSymbols")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getLongEraSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "monthSymbols")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getMonthSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "shortMonthSymbols")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getShortMonthSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "veryShortMonthSymbols")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getVeryShortMonthSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "standaloneMonthSymbols")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getStandaloneMonthSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "shortStandaloneMonthSymbols")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getShortStandaloneMonthSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "veryShortStandaloneMonthSymbols")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getVeryShortStandaloneMonthSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "weekdaySymbols")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getWeekdaySymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "shortWeekdaySymbols")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getShortWeekdaySymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "veryShortWeekdaySymbols")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getVeryShortWeekdaySymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "standaloneWeekdaySymbols")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getStandaloneWeekdaySymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "shortStandaloneWeekdaySymbols")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getShortStandaloneWeekdaySymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "veryShortStandaloneWeekdaySymbols")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getVeryShortStandaloneWeekdaySymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "quarterSymbols")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getQuarterSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "shortQuarterSymbols")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getShortQuarterSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "standaloneQuarterSymbols")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getStandaloneQuarterSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "shortStandaloneQuarterSymbols")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getShortStandaloneQuarterSymbols();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "AMSymbol")
    public native String getAMSymbol();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "PMSymbol")
    public native String getPMSymbol();
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public NSDate getStartTime(NSCalendarUnit unit, NSDate date) {
        NSDate.NSDatePtr ptr = new NSDate.NSDatePtr();
        if (getRange(unit, ptr, new DoublePtr(), date)) {
            return ptr.get();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public double getDuration(NSCalendarUnit unit, NSDate date) {
        DoublePtr ptr = new DoublePtr();
        if (getRange(unit, new NSDate.NSDatePtr(), ptr, date)) {
            return ptr.get();
        }
        return 0;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="NSCalendarDayChangedNotification", optional=true)
    public static native NSString DayChangedNotification();
    
    @Method(selector = "initWithCalendarIdentifier:")
    protected native @Pointer long initWithCalendarIdentifier$(NSCalendarIdentifier ident);
    @Method(selector = "minimumRangeOfUnit:")
    public native @ByVal NSRange getMinimumRange(NSCalendarUnit unit);
    @Method(selector = "maximumRangeOfUnit:")
    public native @ByVal NSRange getMaximumRange(NSCalendarUnit unit);
    @Method(selector = "rangeOfUnit:inUnit:forDate:")
    public native @ByVal NSRange getRange(NSCalendarUnit smaller, NSCalendarUnit larger, NSDate date);
    @Method(selector = "ordinalityOfUnit:inUnit:forDate:")
    public native @MachineSizedUInt long getOrdinality(NSCalendarUnit smaller, NSCalendarUnit larger, NSDate date);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "rangeOfUnit:startDate:interval:forDate:")
    protected native boolean getRange(NSCalendarUnit unit, NSDate.NSDatePtr datep, DoublePtr tip, NSDate date);
    @Method(selector = "dateFromComponents:")
    public native NSDate newDateFromComponents(NSDateComponents comps);
    @Method(selector = "components:fromDate:")
    public native NSDateComponents getComponents(NSCalendarUnit unitFlags, NSDate date);
    @Method(selector = "dateByAddingComponents:toDate:options:")
    public native NSDate newDateByAddingComponents(NSDateComponents comps, NSDate date, NSCalendarOptions opts);
    @Method(selector = "components:fromDate:toDate:options:")
    public native NSDateComponents getComponents(NSCalendarUnit unitFlags, NSDate startingDate, NSDate resultDate, NSCalendarOptions opts);
    @Method(selector = "currentCalendar")
    public static native NSCalendar getCurrentCalendar();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "autoupdatingCurrentCalendar")
    public static native NSCalendar getAutoupdatingCurrentCalendar();
    /*</methods>*/
}
