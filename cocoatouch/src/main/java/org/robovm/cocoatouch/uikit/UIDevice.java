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
import org.robovm.cocoatouch.coredata.*;
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

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html">UIDevice Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
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
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/batteryLevel">@property(nonatomic, readonly) float batteryLevel</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("batteryLevel") public native @Type("float") float getBatteryLevel();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/batteryMonitoringEnabled">@property(nonatomic, getter=isBatteryMonitoringEnabled) BOOL batteryMonitoringEnabled</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isBatteryMonitoringEnabled") public native @Type("BOOL") boolean isBatteryMonitoringEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/batteryMonitoringEnabled">@property(nonatomic, getter=isBatteryMonitoringEnabled) BOOL batteryMonitoringEnabled</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setBatteryMonitoringEnabled:") public native void setBatteryMonitoringEnabled(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/batteryState">@property(nonatomic, readonly) UIDeviceBatteryState batteryState</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("batteryState") public native @Type("UIDeviceBatteryState") UIDeviceBatteryState getBatteryState();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/generatesDeviceOrientationNotifications">@property(nonatomic, readonly, getter=isGeneratingDeviceOrientationNotifications) BOOL generatesDeviceOrientationNotifications</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isGeneratingDeviceOrientationNotifications") public native @Type("BOOL") boolean isGeneratesDeviceOrientationNotifications();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/localizedModel">@property(nonatomic, readonly, retain) NSString *localizedModel</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("localizedModel") public native @Type("NSString *") String getLocalizedModel();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/model">@property(nonatomic, readonly, retain) NSString *model</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("model") public native @Type("NSString *") String getModel();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/multitaskingSupported">@property(nonatomic, readonly, getter=isMultitaskingSupported) BOOL multitaskingSupported</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("isMultitaskingSupported") public native @Type("BOOL") boolean isMultitaskingSupported();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/name">@property(nonatomic, readonly, retain) NSString *name</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("name") public native @Type("NSString *") String getName();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/orientation">@property(nonatomic, readonly) UIDeviceOrientation orientation</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("orientation") public native @Type("UIDeviceOrientation") UIDeviceOrientation getOrientation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/proximityMonitoringEnabled">@property(nonatomic, getter=isProximityMonitoringEnabled) BOOL proximityMonitoringEnabled</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isProximityMonitoringEnabled") public native @Type("BOOL") boolean isProximityMonitoringEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/proximityMonitoringEnabled">@property(nonatomic, getter=isProximityMonitoringEnabled) BOOL proximityMonitoringEnabled</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setProximityMonitoringEnabled:") public native void setProximityMonitoringEnabled(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/proximityState">@property(nonatomic, readonly) BOOL proximityState</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("proximityState") public native @Type("BOOL") boolean isProximityState();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/systemName">@property(nonatomic, readonly, retain) NSString *systemName</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("systemName") public native @Type("NSString *") String getSystemName();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/systemVersion">@property(nonatomic, readonly, retain) NSString *systemVersion</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("systemVersion") public native @Type("NSString *") String getSystemVersion();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/userInterfaceIdiom">@property(nonatomic, readonly) UIUserInterfaceIdiom userInterfaceIdiom</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("userInterfaceIdiom") public native @Type("UIUserInterfaceIdiom") UIUserInterfaceIdiom getUserInterfaceIdiom();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/identifierForVendor">@property(nonatomic, readonly, retain) NSUUID *identifierForVendor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("identifierForVendor") public native @Type("NSUUID *") NSUUID getVendorIdentifier();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/clm/UIDevice/currentDevice">+ (UIDevice *)currentDevice</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("currentDevice") public native static @Type("UIDevice *") UIDevice currentDevice();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instm/UIDevice/beginGeneratingDeviceOrientationNotifications">- (void)beginGeneratingDeviceOrientationNotifications</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("beginGeneratingDeviceOrientationNotifications") public native @Type("void") void beginGeneratingDeviceOrientationNotifications();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instm/UIDevice/endGeneratingDeviceOrientationNotifications">- (void)endGeneratingDeviceOrientationNotifications</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("endGeneratingDeviceOrientationNotifications") public native @Type("void") void endGeneratingDeviceOrientationNotifications();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instm/UIDevice/playInputClick">- (void)playInputClick</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("playInputClick") public native @Type("void") void playInputClick();
    /*</methods>*/

}
