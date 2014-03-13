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
package org.robovm.apple.uikit;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIDevice/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIDevicePtr extends Ptr<UIDevice, UIDevicePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIDevice.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIDevice() {}
    protected UIDevice(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "model")
    public native String getModel();
    @Property(selector = "localizedModel")
    public native String getLocalizedModel();
    @Property(selector = "systemName")
    public native String getSystemName();
    @Property(selector = "systemVersion")
    public native String getSystemVersion();
    @Property(selector = "orientation")
    public native UIDeviceOrientation getOrientation();
    @Property(selector = "identifierForVendor")
    public native NSUUID getVendorIdentifier();
    @Property(selector = "isGeneratingDeviceOrientationNotifications")
    public native boolean isGeneratesDeviceOrientationNotifications();
    @Property(selector = "isBatteryMonitoringEnabled")
    public native boolean isBatteryMonitoringEnabled();
    @Property(selector = "setBatteryMonitoringEnabled:")
    public native void setBatteryMonitoringEnabled(boolean v);
    @Property(selector = "batteryState")
    public native UIDeviceBatteryState getBatteryState();
    @Property(selector = "batteryLevel")
    public native float getBatteryLevel();
    @Property(selector = "isProximityMonitoringEnabled")
    public native boolean isProximityMonitoringEnabled();
    @Property(selector = "setProximityMonitoringEnabled:")
    public native void setProximityMonitoringEnabled(boolean v);
    @Property(selector = "proximityState")
    public native boolean isProximityState();
    @Property(selector = "isMultitaskingSupported")
    public native boolean isMultitaskingSupported();
    @Property(selector = "userInterfaceIdiom")
    public native UIUserInterfaceIdiom getUserInterfaceIdiom();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "beginGeneratingDeviceOrientationNotifications")
    public native void beginGeneratingDeviceOrientationNotifications();
    @Method(selector = "endGeneratingDeviceOrientationNotifications")
    public native void endGeneratingDeviceOrientationNotifications();
    @Method(selector = "playInputClick")
    public native void playInputClick();
    @Method(selector = "currentDevice")
    public static native UIDevice getCurrentDevice();
    /*</methods>*/
}
