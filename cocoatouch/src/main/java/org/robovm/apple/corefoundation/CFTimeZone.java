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
    @GlobalValue(symbol="kCFTimeZoneSystemTimeZoneDidChangeNotification")
    public static native CFString NotificationSystemTimeZoneDidChange();
    
    @Bridge(symbol="CFTimeZoneGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFTimeZoneCopySystem")
    public static native CFTimeZone copySystem();
    @Bridge(symbol="CFTimeZoneResetSystem")
    public static native void resetSystem();
    @Bridge(symbol="CFTimeZoneCopyDefault")
    public static native CFTimeZone copyDefault();
    @Bridge(symbol="CFTimeZoneSetDefault")
    public native void setDefault();
    @Bridge(symbol="CFTimeZoneCopyKnownNames")
    public static native CFArray copyKnownNames();
    @Bridge(symbol="CFTimeZoneCopyAbbreviationDictionary")
    public static native CFDictionary copyAbbreviationDictionary();
    @Bridge(symbol="CFTimeZoneSetAbbreviationDictionary")
    public static native void setAbbreviationDictionary(CFDictionary dict);
    @Bridge(symbol="CFTimeZoneCreate")
    public static native CFTimeZone create(CFAllocator allocator, CFString name, CFData data);
    @Bridge(symbol="CFTimeZoneCreateWithTimeIntervalFromGMT")
    public static native CFTimeZone createWithTimeIntervalFromGMT(CFAllocator allocator, double ti);
    @Bridge(symbol="CFTimeZoneCreateWithName")
    public static native CFTimeZone createWithName(CFAllocator allocator, CFString name, boolean tryAbbrev);
    @Bridge(symbol="CFTimeZoneGetName")
    public native CFString getName();
    @Bridge(symbol="CFTimeZoneGetData")
    public native CFData getData();
    @Bridge(symbol="CFTimeZoneGetSecondsFromGMT")
    public native double getSecondsFromGMT(double at);
    @Bridge(symbol="CFTimeZoneCopyAbbreviation")
    public native CFString copyAbbreviation(double at);
    @Bridge(symbol="CFTimeZoneIsDaylightSavingTime")
    public native boolean isDaylightSavingTime(double at);
    @Bridge(symbol="CFTimeZoneGetDaylightSavingTimeOffset")
    public native double getDaylightSavingTimeOffset(double at);
    @Bridge(symbol="CFTimeZoneGetNextDaylightSavingTimeTransition")
    public native double getNextDaylightSavingTimeTransition(double at);
    @Bridge(symbol="CFTimeZoneCopyLocalizedName")
    public native CFString copyLocalizedName(CFTimeZoneNameStyle style, CFLocale locale);
    /*</methods>*/
}
