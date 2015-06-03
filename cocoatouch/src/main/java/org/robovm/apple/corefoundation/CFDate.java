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
package org.robovm.apple.corefoundation;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreservices.*;
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
    public static CFDate create(double at) {
        return create(null, at);
    }
    public CFComparisonResult compareTo(CFDate otherDate) {
        return compareTo(otherDate, null);
    }
    /*<methods>*/
    @GlobalValue(symbol="kCFAbsoluteTimeIntervalSince1970", optional=true)
    public static native double getAbsoluteTimeIntervalSince1970();
    @GlobalValue(symbol="kCFAbsoluteTimeIntervalSince1904", optional=true)
    public static native double getAbsoluteTimeIntervalSince1904();
    
    @Bridge(symbol="CFAbsoluteTimeGetCurrent", optional=true)
    public static native double getCurrentAbsoluteTime();
    @Bridge(symbol="CFDateGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFDateCreate", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFDate create(CFAllocator allocator, double at);
    @Bridge(symbol="CFDateGetAbsoluteTime", optional=true)
    public native double getAbsoluteTime();
    @Bridge(symbol="CFDateGetTimeIntervalSinceDate", optional=true)
    public native double getTimeIntervalSinceDate(CFDate otherDate);
    @Bridge(symbol="CFDateCompare", optional=true)
    private native CFComparisonResult compareTo(CFDate otherDate, VoidPtr context);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Bridge(symbol="CFAbsoluteTimeAddGregorianUnits", optional=true)
    public static native double addGregorianUnitsToAbsoluteTime(double at, CFTimeZone tz, @ByVal CFGregorianUnits units);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Bridge(symbol="CFAbsoluteTimeGetDifferenceAsGregorianUnits", optional=true)
    public static native @ByVal CFGregorianUnits getAbsoluteTimeDifferenceAsGregorianUnits(double at1, double at2, CFTimeZone tz, CFGregorianUnitFlags unitFlags);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Bridge(symbol="CFAbsoluteTimeGetDayOfWeek", optional=true)
    public static native int getDayOfWeekFromAbsoluteTime(double at, CFTimeZone tz);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Bridge(symbol="CFAbsoluteTimeGetDayOfYear", optional=true)
    public static native int getDayOfYearFromAbsoluteTime(double at, CFTimeZone tz);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Bridge(symbol="CFAbsoluteTimeGetWeekOfYear", optional=true)
    public static native int getWeekOfYearFromAbsoluteTime(double at, CFTimeZone tz);
    /*</methods>*/
}
