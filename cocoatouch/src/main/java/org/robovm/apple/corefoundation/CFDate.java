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

/*<javadoc>*/
/*</javadoc>*/
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
    @Bridge(symbol="CFAbsoluteTimeGetCurrent", optional=true)
    public static native double absoluteTimeGetCurrent();
    @Bridge(symbol="CFDateGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFDateCreate", optional=true)
    public static native CFDate create(CFAllocator allocator, double at);
    @Bridge(symbol="CFDateGetAbsoluteTime", optional=true)
    public native double getAbsoluteTime();
    @Bridge(symbol="CFDateGetTimeIntervalSinceDate", optional=true)
    public native double getTimeIntervalSinceDate(CFDate otherDate);
    @Bridge(symbol="CFDateCompare", optional=true)
    public native CFComparisonResult compare(CFDate otherDate, VoidPtr context);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Bridge(symbol="CFGregorianDateIsValid", optional=true)
    public static native boolean gregorianDateIsValid(@ByVal CFGregorianDate gdate, CFGregorianUnitFlags unitFlags);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Bridge(symbol="CFGregorianDateGetAbsoluteTime", optional=true)
    public static native double gregorianDateGetAbsoluteTime(@ByVal CFGregorianDate gdate, CFTimeZone tz);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Bridge(symbol="CFAbsoluteTimeGetGregorianDate", optional=true)
    public static native @ByVal CFGregorianDate absoluteTimeGetGregorianDate(double at, CFTimeZone tz);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Bridge(symbol="CFAbsoluteTimeAddGregorianUnits", optional=true)
    public static native double absoluteTimeAddGregorianUnits(double at, CFTimeZone tz, @ByVal CFGregorianUnits units);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Bridge(symbol="CFAbsoluteTimeGetDifferenceAsGregorianUnits", optional=true)
    public static native @ByVal CFGregorianUnits absoluteTimeGetDifferenceAsGregorianUnits(double at1, double at2, CFTimeZone tz, CFGregorianUnitFlags unitFlags);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Bridge(symbol="CFAbsoluteTimeGetDayOfWeek", optional=true)
    public static native int absoluteTimeGetDayOfWeek(double at, CFTimeZone tz);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Bridge(symbol="CFAbsoluteTimeGetDayOfYear", optional=true)
    public static native int absoluteTimeGetDayOfYear(double at, CFTimeZone tz);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Bridge(symbol="CFAbsoluteTimeGetWeekOfYear", optional=true)
    public static native int absoluteTimeGetWeekOfYear(double at, CFTimeZone tz);
    /*</methods>*/
}
