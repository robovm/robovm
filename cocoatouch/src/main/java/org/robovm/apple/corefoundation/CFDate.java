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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFDate/*</name>*/ 
    extends /*<extends>*/CFPropertyList/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFDatePtr extends Ptr<CFDate, CFDatePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFDate.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFDate() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCFAbsoluteTimeIntervalSince1970")
    public static native double AbsoluteTimeIntervalSince1970();
    @GlobalValue(symbol="kCFAbsoluteTimeIntervalSince1904")
    public static native double AbsoluteTimeIntervalSince1904();
    
    @Bridge(symbol="CFAbsoluteTimeGetCurrent")
    public static native double absoluteTimeGetCurrent();
    @Bridge(symbol="CFDateGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFDateCreate")
    public static native CFDate create(CFAllocator allocator, double at);
    @Bridge(symbol="CFDateGetAbsoluteTime")
    public native double getAbsoluteTime();
    @Bridge(symbol="CFDateGetTimeIntervalSinceDate")
    public native double getTimeIntervalSinceDate(CFDate otherDate);
    @Bridge(symbol="CFDateCompare")
    public native CFComparisonResult compare(CFDate otherDate, VoidPtr context);
    @Bridge(symbol="CFGregorianDateIsValid")
    public static native boolean gregorianDateIsValid(@ByVal CFGregorianDate gdate, CFGregorianUnitFlags unitFlags);
    @Bridge(symbol="CFGregorianDateGetAbsoluteTime")
    public static native double gregorianDateGetAbsoluteTime(@ByVal CFGregorianDate gdate, CFTimeZone tz);
    @Bridge(symbol="CFAbsoluteTimeGetGregorianDate")
    public static native @ByVal CFGregorianDate absoluteTimeGetGregorianDate(double at, CFTimeZone tz);
    @Bridge(symbol="CFAbsoluteTimeAddGregorianUnits")
    public static native double absoluteTimeAddGregorianUnits(double at, CFTimeZone tz, @ByVal CFGregorianUnits units);
    @Bridge(symbol="CFAbsoluteTimeGetDifferenceAsGregorianUnits")
    public static native @ByVal CFGregorianUnits absoluteTimeGetDifferenceAsGregorianUnits(double at1, double at2, CFTimeZone tz, CFGregorianUnitFlags unitFlags);
    @Bridge(symbol="CFAbsoluteTimeGetDayOfWeek")
    public static native int absoluteTimeGetDayOfWeek(double at, CFTimeZone tz);
    @Bridge(symbol="CFAbsoluteTimeGetDayOfYear")
    public static native int absoluteTimeGetDayOfYear(double at, CFTimeZone tz);
    @Bridge(symbol="CFAbsoluteTimeGetWeekOfYear")
    public static native int absoluteTimeGetWeekOfYear(double at, CFTimeZone tz);
    /*</methods>*/
}
