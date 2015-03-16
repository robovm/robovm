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
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSDateComponents/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSDateComponentsPtr extends Ptr<NSDateComponents, NSDateComponentsPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSDateComponents.class); }/*</bind>*/
    /*<constants>*/
    public static final long UndefinedComponent = Bro.IS_32BIT ? 0x7fffffffL : 0x7fffffffffffffffL;
    /*</constants>*/
    /*<constructors>*/
    public NSDateComponents() {}
    protected NSDateComponents(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "calendar")
    public native NSCalendar getCalendar();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "setCalendar:")
    public native void setCalendar(NSCalendar v);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "timeZone")
    public native NSTimeZone getTimeZone();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "setTimeZone:")
    public native void setTimeZone(NSTimeZone v);
    @Property(selector = "era")
    public native @MachineSizedSInt long getEra();
    @Property(selector = "setEra:")
    public native void setEra(@MachineSizedSInt long v);
    @Property(selector = "year")
    public native @MachineSizedSInt long getYear();
    @Property(selector = "setYear:")
    public native void setYear(@MachineSizedSInt long v);
    @Property(selector = "month")
    public native @MachineSizedSInt long getMonth();
    @Property(selector = "setMonth:")
    public native void setMonth(@MachineSizedSInt long v);
    @Property(selector = "day")
    public native @MachineSizedSInt long getDay();
    @Property(selector = "setDay:")
    public native void setDay(@MachineSizedSInt long v);
    @Property(selector = "hour")
    public native @MachineSizedSInt long getHour();
    @Property(selector = "setHour:")
    public native void setHour(@MachineSizedSInt long v);
    @Property(selector = "minute")
    public native @MachineSizedSInt long getMinute();
    @Property(selector = "setMinute:")
    public native void setMinute(@MachineSizedSInt long v);
    @Property(selector = "second")
    public native @MachineSizedSInt long getSecond();
    @Property(selector = "setSecond:")
    public native void setSecond(@MachineSizedSInt long v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "nanosecond")
    public native @MachineSizedSInt long getNanosecond();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setNanosecond:")
    public native void setNanosecond(@MachineSizedSInt long v);
    @Property(selector = "weekday")
    public native @MachineSizedSInt long getWeekday();
    @Property(selector = "setWeekday:")
    public native void setWeekday(@MachineSizedSInt long v);
    @Property(selector = "weekdayOrdinal")
    public native @MachineSizedSInt long getWeekdayOrdinal();
    @Property(selector = "setWeekdayOrdinal:")
    public native void setWeekdayOrdinal(@MachineSizedSInt long v);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "quarter")
    public native @MachineSizedSInt long getQuarter();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "setQuarter:")
    public native void setQuarter(@MachineSizedSInt long v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "weekOfMonth")
    public native @MachineSizedSInt long getWeekOfMonth();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setWeekOfMonth:")
    public native void setWeekOfMonth(@MachineSizedSInt long v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "weekOfYear")
    public native @MachineSizedSInt long getWeekOfYear();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setWeekOfYear:")
    public native void setWeekOfYear(@MachineSizedSInt long v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "yearForWeekOfYear")
    public native @MachineSizedSInt long getYearForWeekOfYear();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setYearForWeekOfYear:")
    public native void setYearForWeekOfYear(@MachineSizedSInt long v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "isLeapMonth")
    public native boolean isLeapMonth();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setLeapMonth:")
    public native void setLeapMonth(boolean v);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "date")
    public native NSDate getDate();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "isValidDate")
    public native boolean isValidDate();
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public void setValue(NSCalendarUnit unit, @MachineSizedSInt long value) {
        setValue(value, unit);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "week")
    public native @MachineSizedSInt long getWeek();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "setWeek:")
    public native void setWeek(@MachineSizedSInt long v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "setValue:forComponent:")
    protected native void setValue(@MachineSizedSInt long value, NSCalendarUnit unit);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "valueForComponent:")
    public native @MachineSizedSInt long getValue(NSCalendarUnit unit);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "isValidDateInCalendar:")
    public native boolean isValidDateInCalendar(NSCalendar calendar);
    /*</methods>*/
}
