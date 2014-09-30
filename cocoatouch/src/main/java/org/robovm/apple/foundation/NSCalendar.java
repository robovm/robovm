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
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="NSCalendarDayChangedNotification", optional=true)
    public static native NSString DayChangedNotification();
    
    @Method(selector = "initWithCalendarIdentifier:")
    protected native @Pointer long initWithCalendarIdentifier$(NSCalendarIdentifier ident);
    @Method(selector = "setLocale:")
    public native void setLocale(NSLocale locale);
    @Method(selector = "locale")
    public native NSLocale getLocale();
    @Method(selector = "setTimeZone:")
    public native void setTimeZone(NSTimeZone tz);
    @Method(selector = "timeZone")
    public native NSTimeZone getTimeZone();
    @Method(selector = "setFirstWeekday:")
    public native void setFirstWeekday(@MachineSizedUInt long weekday);
    @Method(selector = "firstWeekday")
    public native @MachineSizedUInt long getFirstWeekday();
    @Method(selector = "setMinimumDaysInFirstWeek:")
    public native void setMinimumDaysInFirstWeek(@MachineSizedUInt long mdw);
    @Method(selector = "minimumDaysInFirstWeek")
    public native @MachineSizedUInt long getMinimumDaysInFirstWeek();
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
