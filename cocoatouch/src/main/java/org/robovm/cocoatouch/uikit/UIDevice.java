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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIDevice /*</name>*/.class);

    /*<constructors>*/
    protected UIDevice(SkipInit skipInit) { super(skipInit); }
    public UIDevice() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/batteryLevel">@property(nonatomic, readonly) float batteryLevel</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("batteryLevel") public native float getBatteryLevel();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/batteryMonitoringEnabled">@property(nonatomic, getter=isBatteryMonitoringEnabled) BOOL batteryMonitoringEnabled</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isBatteryMonitoringEnabled") public native boolean isBatteryMonitoringEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/batteryMonitoringEnabled">@property(nonatomic, getter=isBatteryMonitoringEnabled) BOOL batteryMonitoringEnabled</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setBatteryMonitoringEnabled:") public native void setBatteryMonitoringEnabled(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/batteryState">@property(nonatomic, readonly) UIDeviceBatteryState batteryState</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("batteryState") public native UIDeviceBatteryState getBatteryState();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/generatesDeviceOrientationNotifications">@property(nonatomic, readonly, getter=isGeneratingDeviceOrientationNotifications) BOOL generatesDeviceOrientationNotifications</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isGeneratingDeviceOrientationNotifications") public native boolean isGeneratesDeviceOrientationNotifications();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/localizedModel">@property(nonatomic, readonly, retain) NSString *localizedModel</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("localizedModel") public native String getLocalizedModel();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/model">@property(nonatomic, readonly, retain) NSString *model</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("model") public native String getModel();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/multitaskingSupported">@property(nonatomic, readonly, getter=isMultitaskingSupported) BOOL multitaskingSupported</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("isMultitaskingSupported") public native boolean isMultitaskingSupported();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/name">@property(nonatomic, readonly, retain) NSString *name</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("name") public native String getName();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/orientation">@property(nonatomic, readonly) UIDeviceOrientation orientation</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("orientation") public native UIDeviceOrientation getOrientation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/proximityMonitoringEnabled">@property(nonatomic, getter=isProximityMonitoringEnabled) BOOL proximityMonitoringEnabled</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isProximityMonitoringEnabled") public native boolean isProximityMonitoringEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/proximityMonitoringEnabled">@property(nonatomic, getter=isProximityMonitoringEnabled) BOOL proximityMonitoringEnabled</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setProximityMonitoringEnabled:") public native void setProximityMonitoringEnabled(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/proximityState">@property(nonatomic, readonly) BOOL proximityState</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("proximityState") public native boolean isProximityState();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/systemName">@property(nonatomic, readonly, retain) NSString *systemName</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("systemName") public native String getSystemName();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/systemVersion">@property(nonatomic, readonly, retain) NSString *systemVersion</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("systemVersion") public native String getSystemVersion();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/userInterfaceIdiom">@property(nonatomic, readonly) UIUserInterfaceIdiom userInterfaceIdiom</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("userInterfaceIdiom") public native UIUserInterfaceIdiom getUserInterfaceIdiom();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/identifierForVendor">@property(nonatomic, readonly, retain) NSUUID *identifierForVendor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("identifierForVendor") public native NSUUID getVendorIdentifier();
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector currentDevice = Selector.register("currentDevice");
    @Bridge(symbol = "objc_msgSend") private native static UIDevice objc_currentDevice(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/clm/UIDevice/currentDevice">+ (UIDevice *)currentDevice</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIDevice currentDevice() {
        return objc_currentDevice(objCClass, currentDevice);
    }
    
    private static final Selector beginGeneratingDeviceOrientationNotifications = Selector.register("beginGeneratingDeviceOrientationNotifications");
    @Bridge(symbol = "objc_msgSend") private native static void objc_beginGeneratingDeviceOrientationNotifications(UIDevice __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_beginGeneratingDeviceOrientationNotificationsSuper(ObjCSuper __super__, UIDevice __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instm/UIDevice/beginGeneratingDeviceOrientationNotifications">- (void)beginGeneratingDeviceOrientationNotifications</a>
     * @since Available in iOS 2.0 and later.
     */
    public void beginGeneratingDeviceOrientationNotifications() {
        if (customClass) { objc_beginGeneratingDeviceOrientationNotificationsSuper(getSuper(), this, beginGeneratingDeviceOrientationNotifications); } else { objc_beginGeneratingDeviceOrientationNotifications(this, beginGeneratingDeviceOrientationNotifications); }
    }
    
    private static final Selector endGeneratingDeviceOrientationNotifications = Selector.register("endGeneratingDeviceOrientationNotifications");
    @Bridge(symbol = "objc_msgSend") private native static void objc_endGeneratingDeviceOrientationNotifications(UIDevice __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_endGeneratingDeviceOrientationNotificationsSuper(ObjCSuper __super__, UIDevice __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instm/UIDevice/endGeneratingDeviceOrientationNotifications">- (void)endGeneratingDeviceOrientationNotifications</a>
     * @since Available in iOS 2.0 and later.
     */
    public void endGeneratingDeviceOrientationNotifications() {
        if (customClass) { objc_endGeneratingDeviceOrientationNotificationsSuper(getSuper(), this, endGeneratingDeviceOrientationNotifications); } else { objc_endGeneratingDeviceOrientationNotifications(this, endGeneratingDeviceOrientationNotifications); }
    }
    
    private static final Selector playInputClick = Selector.register("playInputClick");
    @Bridge(symbol = "objc_msgSend") private native static void objc_playInputClick(UIDevice __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_playInputClickSuper(ObjCSuper __super__, UIDevice __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instm/UIDevice/playInputClick">- (void)playInputClick</a>
     * @since Available in iOS 4.2 and later.
     */
    public void playInputClick() {
        if (customClass) { objc_playInputClickSuper(getSuper(), this, playInputClick); } else { objc_playInputClick(this, playInputClick); }
    }
    /*</methods>*/

}
