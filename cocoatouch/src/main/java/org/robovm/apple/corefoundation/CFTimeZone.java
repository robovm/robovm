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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFTimeZone/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Notifications {
        public NSObject observeSystemTimeZoneDidChangeNotification(final Runnable block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(SystemTimeZoneDidChangeNotification(), null, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.run();
                }
            });
        }
    }
    
    /*<ptr>*/public static class CFTimeZonePtr extends Ptr<CFTimeZone, CFTimeZonePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFTimeZone.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFTimeZone() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CFTimeZone create(String name, CFData data) {
        return create(null, name, data);
    }
    public static CFTimeZone create(double ti) {
        return create(null, ti);
    }
    public static CFTimeZone create(String name, boolean tryAbbrev) {
        return create(null, name, tryAbbrev);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFTimeZoneSystemTimeZoneDidChangeNotification", optional=true)
    public static native NSString SystemTimeZoneDidChangeNotification();
    
    @Bridge(symbol="CFTimeZoneGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFTimeZoneCopySystem", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFTimeZone getSystem();
    @Bridge(symbol="CFTimeZoneResetSystem", optional=true)
    public static native void resetSystem();
    @Bridge(symbol="CFTimeZoneCopyDefault", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFTimeZone getDefault();
    @Bridge(symbol="CFTimeZoneSetDefault", optional=true)
    public static native void setDefault(CFTimeZone tz);
    @Bridge(symbol="CFTimeZoneCopyKnownNames", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFArray.AsStringListMarshaler.class) List<String> getKnownNames();
    @Bridge(symbol="CFTimeZoneCopyAbbreviationDictionary", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFDictionary getAbbreviationDictionary();
    @Bridge(symbol="CFTimeZoneSetAbbreviationDictionary", optional=true)
    public static native void setAbbreviationDictionary(CFDictionary dict);
    @Bridge(symbol="CFTimeZoneCreate", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFTimeZone create(CFAllocator allocator, String name, CFData data);
    @Bridge(symbol="CFTimeZoneCreateWithTimeIntervalFromGMT", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFTimeZone create(CFAllocator allocator, double ti);
    @Bridge(symbol="CFTimeZoneCreateWithName", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFTimeZone create(CFAllocator allocator, String name, boolean tryAbbrev);
    @Bridge(symbol="CFTimeZoneGetName", optional=true)
    public native String getName();
    @Bridge(symbol="CFTimeZoneGetData", optional=true)
    public native CFData getData();
    @Bridge(symbol="CFTimeZoneGetSecondsFromGMT", optional=true)
    public native double getSecondsFromGMT(double at);
    @Bridge(symbol="CFTimeZoneCopyAbbreviation", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getAbbreviation(double at);
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
    public native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getLocalizedName(CFTimeZoneNameStyle style, CFLocale locale);
    /*</methods>*/
}
