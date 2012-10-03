/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIDevice /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIDevice /*</name>*/.class);
    }

    /*<constructors>*/
    public UIDevice() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("batteryLevel") public native @Type("float") float getBatteryLevel();
    @Bind("isBatteryMonitoringEnabled") public native @Type("BOOL") boolean isBatteryMonitoringEnabled();
    @Bind("setBatteryMonitoringEnabled:") public native void setBatteryMonitoringEnabled(@Type("BOOL") boolean v);
    @Bind("batteryState") public native @Type("UIDeviceBatteryState") UIDeviceBatteryState getBatteryState();
    @Bind("isGeneratingDeviceOrientationNotifications") public native @Type("BOOL") boolean isGeneratesDeviceOrientationNotifications();
    @Bind("identifierForVendor") public native @Type("NSUUID *") NSUUID getIdentifierForVendor();
    @Bind("localizedModel") public native @Type("NSString *") String getLocalizedModel();
    @Bind("model") public native @Type("NSString *") String getModel();
    @Bind("isMultitaskingSupported") public native @Type("BOOL") boolean isMultitaskingSupported();
    @Bind("name") public native @Type("NSString *") String getName();
    @Bind("orientation") public native @Type("UIDeviceOrientation") UIDeviceOrientation getOrientation();
    @Bind("isProximityMonitoringEnabled") public native @Type("BOOL") boolean isProximityMonitoringEnabled();
    @Bind("setProximityMonitoringEnabled:") public native void setProximityMonitoringEnabled(@Type("BOOL") boolean v);
    @Bind("proximityState") public native @Type("BOOL") boolean isProximityState();
    @Bind("systemName") public native @Type("NSString *") String getSystemName();
    @Bind("systemVersion") public native @Type("NSString *") String getSystemVersion();
    @Bind("userInterfaceIdiom") public native @Type("UIUserInterfaceIdiom") UIUserInterfaceIdiom getUserInterfaceIdiom();
    /*</properties>*/
    /*<methods>*/
    @Bind("currentDevice") public native static @Type("UIDevice *") UIDevice currentDevice();
    @Bind("beginGeneratingDeviceOrientationNotifications") public native @Type("void") void beginGeneratingDeviceOrientationNotifications();
    @Bind("endGeneratingDeviceOrientationNotifications") public native @Type("void") void endGeneratingDeviceOrientationNotifications();
    @Bind("playInputClick") public native @Type("void") void playInputClick();
    /*</methods>*/

}
