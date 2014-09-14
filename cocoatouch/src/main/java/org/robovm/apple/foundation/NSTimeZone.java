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
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSTimeZone/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSTimeZonePtr extends Ptr<NSTimeZone, NSTimeZonePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSTimeZone.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSTimeZone() {}
    protected NSTimeZone(SkipInit skipInit) { super(skipInit); }
    public NSTimeZone(String tzName) { super((SkipInit) null); initObject(initWithName$(tzName)); }
    public NSTimeZone(String tzName, NSData aData) { super((SkipInit) null); initObject(initWithName$data$(tzName, aData)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "name")
    public native String name();
    @Method(selector = "data")
    public native NSData data();
    @Method(selector = "secondsFromGMTForDate:")
    public native @MachineSizedSInt long secondsFromGMTForDate$(NSDate aDate);
    @Method(selector = "abbreviationForDate:")
    public native String abbreviationForDate$(NSDate aDate);
    @Method(selector = "isDaylightSavingTimeForDate:")
    public native boolean isDaylightSavingTimeForDate$(NSDate aDate);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "daylightSavingTimeOffsetForDate:")
    public native double daylightSavingTimeOffsetForDate$(NSDate aDate);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "nextDaylightSavingTimeTransitionAfterDate:")
    public native NSDate nextDaylightSavingTimeTransitionAfterDate$(NSDate aDate);
    @Method(selector = "secondsFromGMT")
    public native @MachineSizedSInt long secondsFromGMT();
    @Method(selector = "abbreviation")
    public native String abbreviation();
    @Method(selector = "isDaylightSavingTime")
    public native boolean isDaylightSavingTime();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "daylightSavingTimeOffset")
    public native double daylightSavingTimeOffset();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "nextDaylightSavingTimeTransition")
    public native NSDate nextDaylightSavingTimeTransition();
    @Method(selector = "description")
    public native String description();
    @Method(selector = "isEqualToTimeZone:")
    public native boolean isEqualToTimeZone$(NSTimeZone aTimeZone);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Method(selector = "localizedName:locale:")
    public native String localizedName$locale$(NSTimeZoneNameStyle style, NSLocale locale);
    @Method(selector = "systemTimeZone")
    public static native NSTimeZone systemTimeZone();
    @Method(selector = "resetSystemTimeZone")
    public static native void resetSystemTimeZone();
    @Method(selector = "defaultTimeZone")
    public static native NSTimeZone defaultTimeZone();
    @Method(selector = "setDefaultTimeZone:")
    public static native void setDefaultTimeZone(NSTimeZone aTimeZone);
    @Method(selector = "localTimeZone")
    public static native NSTimeZone localTimeZone();
    @Method(selector = "knownTimeZoneNames")
    public static native NSArray<?> knownTimeZoneNames();
    @Method(selector = "abbreviationDictionary")
    public static native NSDictionary<?, ?> abbreviationDictionary();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "setAbbreviationDictionary:")
    public static native void setAbbreviationDictionary(NSDictionary<?, ?> dict);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "timeZoneDataVersion")
    public static native String timeZoneDataVersion();
    @Method(selector = "initWithName:")
    protected native @Pointer long initWithName$(String tzName);
    @Method(selector = "initWithName:data:")
    protected native @Pointer long initWithName$data$(String tzName, NSData aData);
    @Method(selector = "timeZoneWithName:")
    public static native NSObject timeZoneWithName$(String tzName);
    @Method(selector = "timeZoneWithName:data:")
    public static native NSObject timeZoneWithName$data$(String tzName, NSData aData);
    @Method(selector = "timeZoneForSecondsFromGMT:")
    public static native NSObject timeZoneForSecondsFromGMT$(@MachineSizedSInt long seconds);
    @Method(selector = "timeZoneWithAbbreviation:")
    public static native NSObject timeZoneWithAbbreviation$(String abbreviation);
    /*</methods>*/
}
