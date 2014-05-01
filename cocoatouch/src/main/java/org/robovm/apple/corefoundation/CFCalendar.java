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

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFCalendar/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFCalendarPtr extends Ptr<CFCalendar, CFCalendarPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFCalendar.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFCalendar() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFCalendarGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFCalendarCopyCurrent", optional=true)
    public static native CFCalendar copyCurrent();
    @Bridge(symbol="CFCalendarCreateWithIdentifier", optional=true)
    public static native CFCalendar createWithIdentifier(CFAllocator allocator, CFString identifier);
    @Bridge(symbol="CFCalendarGetIdentifier", optional=true)
    public native CFString getIdentifier();
    @Bridge(symbol="CFCalendarCopyLocale", optional=true)
    public native CFLocale copyLocale();
    @Bridge(symbol="CFCalendarSetLocale", optional=true)
    public native void setLocale(CFLocale locale);
    @Bridge(symbol="CFCalendarCopyTimeZone", optional=true)
    public native CFTimeZone copyTimeZone();
    @Bridge(symbol="CFCalendarSetTimeZone", optional=true)
    public native void setTimeZone(CFTimeZone tz);
    @Bridge(symbol="CFCalendarGetFirstWeekday", optional=true)
    public native @MachineSizedSInt long getFirstWeekday();
    @Bridge(symbol="CFCalendarSetFirstWeekday", optional=true)
    public native void setFirstWeekday(@MachineSizedSInt long wkdy);
    @Bridge(symbol="CFCalendarGetMinimumDaysInFirstWeek", optional=true)
    public native @MachineSizedSInt long getMinimumDaysInFirstWeek();
    @Bridge(symbol="CFCalendarSetMinimumDaysInFirstWeek", optional=true)
    public native void setMinimumDaysInFirstWeek(@MachineSizedSInt long mwd);
    @Bridge(symbol="CFCalendarGetMinimumRangeOfUnit", optional=true)
    public native @ByVal CFRange getMinimumRangeOfUnit(CFCalendarUnit unit);
    @Bridge(symbol="CFCalendarGetMaximumRangeOfUnit", optional=true)
    public native @ByVal CFRange getMaximumRangeOfUnit(CFCalendarUnit unit);
    @Bridge(symbol="CFCalendarGetRangeOfUnit", optional=true)
    public native @ByVal CFRange getRangeOfUnit(CFCalendarUnit smallerUnit, CFCalendarUnit biggerUnit, double at);
    @Bridge(symbol="CFCalendarGetOrdinalityOfUnit", optional=true)
    public native @MachineSizedSInt long getOrdinalityOfUnit(CFCalendarUnit smallerUnit, CFCalendarUnit biggerUnit, double at);
    @Bridge(symbol="CFCalendarGetTimeRangeOfUnit", optional=true)
    public native boolean getTimeRangeOfUnit(CFCalendarUnit unit, double at, DoublePtr startp, DoublePtr tip);
    /*</methods>*/
}
