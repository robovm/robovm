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
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFGregorianDate/*</name>*/ 
    extends /*<extends>*/Struct<CFGregorianDate>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFGregorianDatePtr extends Ptr<CFGregorianDate, CFGregorianDatePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFGregorianDate.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CFGregorianDate() {}
    public CFGregorianDate(int year, byte month, byte day, byte hour, byte minute, double second) {
        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
        this.setHour(hour);
        this.setMinute(minute);
        this.setSecond(second);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getYear();
    @StructMember(0) public native CFGregorianDate setYear(int year);
    @StructMember(1) public native byte getMonth();
    @StructMember(1) public native CFGregorianDate setMonth(byte month);
    @StructMember(2) public native byte getDay();
    @StructMember(2) public native CFGregorianDate setDay(byte day);
    @StructMember(3) public native byte getHour();
    @StructMember(3) public native CFGregorianDate setHour(byte hour);
    @StructMember(4) public native byte getMinute();
    @StructMember(4) public native CFGregorianDate setMinute(byte minute);
    @StructMember(5) public native double getSecond();
    @StructMember(5) public native CFGregorianDate setSecond(double second);
    /*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    public boolean isValid(CFGregorianUnitFlags unitFlags) { return isValid(this, unitFlags); }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Bridge(symbol="CFGregorianDateIsValid", optional=true)
    private static native boolean isValid(@ByVal CFGregorianDate gdate, CFGregorianUnitFlags unitFlags);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    public double getAbsoluteTime(CFTimeZone tz) { return getAbsoluteTime(this, tz); }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Bridge(symbol="CFGregorianDateGetAbsoluteTime", optional=true)
    private static native double getAbsoluteTime(@ByVal CFGregorianDate gdate, CFTimeZone tz);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Bridge(symbol="CFAbsoluteTimeGetGregorianDate", optional=true)
    public static native @ByVal CFGregorianDate fromAbsoluteTime(double at, CFTimeZone tz);
    /*</methods>*/
}
