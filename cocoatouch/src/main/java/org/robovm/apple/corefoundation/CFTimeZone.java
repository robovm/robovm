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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFTimeZone/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFTimeZonePtr extends Ptr<CFTimeZone, CFTimeZonePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFTimeZone.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFTimeZone() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFTimeZoneSystemTimeZoneDidChangeNotification", optional=true)
    public static native CFString NotificationSystemTimeZoneDidChange();
    
    @Bridge(symbol="CFTimeZoneGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFTimeZoneCopySystem", optional=true)
    public static native CFTimeZone copySystem();
    @Bridge(symbol="CFTimeZoneResetSystem", optional=true)
    public static native void resetSystem();
    @Bridge(symbol="CFTimeZoneCopyDefault", optional=true)
    public static native CFTimeZone copyDefault();
    @Bridge(symbol="CFTimeZoneSetDefault", optional=true)
    public native void setDefault();
    @Bridge(symbol="CFTimeZoneCopyKnownNames", optional=true)
    public static native CFArray copyKnownNames();
    @Bridge(symbol="CFTimeZoneCopyAbbreviationDictionary", optional=true)
    public static native CFDictionary copyAbbreviationDictionary();
    @Bridge(symbol="CFTimeZoneSetAbbreviationDictionary", optional=true)
    public static native void setAbbreviationDictionary(CFDictionary dict);
    @Bridge(symbol="CFTimeZoneCreate", optional=true)
    public static native CFTimeZone create(CFAllocator allocator, CFString name, CFData data);
    @Bridge(symbol="CFTimeZoneCreateWithTimeIntervalFromGMT", optional=true)
    public static native CFTimeZone createWithTimeIntervalFromGMT(CFAllocator allocator, double ti);
    @Bridge(symbol="CFTimeZoneCreateWithName", optional=true)
    public static native CFTimeZone createWithName(CFAllocator allocator, CFString name, boolean tryAbbrev);
    @Bridge(symbol="CFTimeZoneGetName", optional=true)
    public native CFString getName();
    @Bridge(symbol="CFTimeZoneGetData", optional=true)
    public native CFData getData();
    @Bridge(symbol="CFTimeZoneGetSecondsFromGMT", optional=true)
    public native double getSecondsFromGMT(double at);
    @Bridge(symbol="CFTimeZoneCopyAbbreviation", optional=true)
    public native CFString copyAbbreviation(double at);
    @Bridge(symbol="CFTimeZoneIsDaylightSavingTime", optional=true)
    public native boolean isDaylightSavingTime(double at);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFTimeZoneGetDaylightSavingTimeOffset", optional=true)
    public native double getDaylightSavingTimeOffset(double at);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFTimeZoneGetNextDaylightSavingTimeTransition", optional=true)
    public native double getNextDaylightSavingTimeTransition(double at);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFTimeZoneCopyLocalizedName", optional=true)
    public native CFString copyLocalizedName(CFTimeZoneNameStyle style, CFLocale locale);
    /*</methods>*/
}
