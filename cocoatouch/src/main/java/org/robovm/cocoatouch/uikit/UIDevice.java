/*
 * Copyright (C) 2012 Trillian AB
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
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html">UIDevice Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIDevice /*</name>*/ 
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
    
    private static final Selector batteryLevel = Selector.register("batteryLevel");
    @Bridge private native static float objc_getBatteryLevel(UIDevice __self__, Selector __cmd__);
    @Bridge private native static float objc_getBatteryLevelSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/batteryLevel">@property(nonatomic, readonly) float batteryLevel</a>
     * @since Available in iOS 3.0 and later.
     */
    public float getBatteryLevel() {
        if (customClass) { return objc_getBatteryLevelSuper(getSuper(), batteryLevel); } else { return objc_getBatteryLevel(this, batteryLevel); }
    }
    
    private static final Selector isBatteryMonitoringEnabled = Selector.register("isBatteryMonitoringEnabled");
    @Bridge private native static boolean objc_isBatteryMonitoringEnabled(UIDevice __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isBatteryMonitoringEnabledSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/batteryMonitoringEnabled">@property(nonatomic, getter=isBatteryMonitoringEnabled) BOOL batteryMonitoringEnabled</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isBatteryMonitoringEnabled() {
        if (customClass) { return objc_isBatteryMonitoringEnabledSuper(getSuper(), isBatteryMonitoringEnabled); } else { return objc_isBatteryMonitoringEnabled(this, isBatteryMonitoringEnabled); }
    }
    
    private static final Selector setBatteryMonitoringEnabled$ = Selector.register("setBatteryMonitoringEnabled:");
    @Bridge private native static void objc_setBatteryMonitoringEnabled(UIDevice __self__, Selector __cmd__, boolean batteryMonitoringEnabled);
    @Bridge private native static void objc_setBatteryMonitoringEnabledSuper(ObjCSuper __super__, Selector __cmd__, boolean batteryMonitoringEnabled);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/batteryMonitoringEnabled">@property(nonatomic, getter=isBatteryMonitoringEnabled) BOOL batteryMonitoringEnabled</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setBatteryMonitoringEnabled(boolean batteryMonitoringEnabled) {
        if (customClass) { objc_setBatteryMonitoringEnabledSuper(getSuper(), setBatteryMonitoringEnabled$, batteryMonitoringEnabled); } else { objc_setBatteryMonitoringEnabled(this, setBatteryMonitoringEnabled$, batteryMonitoringEnabled); }
    }
    
    private static final Selector batteryState = Selector.register("batteryState");
    @Bridge private native static UIDeviceBatteryState objc_getBatteryState(UIDevice __self__, Selector __cmd__);
    @Bridge private native static UIDeviceBatteryState objc_getBatteryStateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/batteryState">@property(nonatomic, readonly) UIDeviceBatteryState batteryState</a>
     * @since Available in iOS 3.0 and later.
     */
    public UIDeviceBatteryState getBatteryState() {
        if (customClass) { return objc_getBatteryStateSuper(getSuper(), batteryState); } else { return objc_getBatteryState(this, batteryState); }
    }
    
    private static final Selector isGeneratingDeviceOrientationNotifications = Selector.register("isGeneratingDeviceOrientationNotifications");
    @Bridge private native static boolean objc_isGeneratesDeviceOrientationNotifications(UIDevice __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isGeneratesDeviceOrientationNotificationsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/generatesDeviceOrientationNotifications">@property(nonatomic, readonly, getter=isGeneratingDeviceOrientationNotifications) BOOL generatesDeviceOrientationNotifications</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isGeneratesDeviceOrientationNotifications() {
        if (customClass) { return objc_isGeneratesDeviceOrientationNotificationsSuper(getSuper(), isGeneratingDeviceOrientationNotifications); } else { return objc_isGeneratesDeviceOrientationNotifications(this, isGeneratingDeviceOrientationNotifications); }
    }
    
    private static final Selector localizedModel = Selector.register("localizedModel");
    @Bridge private native static String objc_getLocalizedModel(UIDevice __self__, Selector __cmd__);
    @Bridge private native static String objc_getLocalizedModelSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/localizedModel">@property(nonatomic, readonly, retain) NSString *localizedModel</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getLocalizedModel() {
        if (customClass) { return objc_getLocalizedModelSuper(getSuper(), localizedModel); } else { return objc_getLocalizedModel(this, localizedModel); }
    }
    
    private static final Selector model = Selector.register("model");
    @Bridge private native static String objc_getModel(UIDevice __self__, Selector __cmd__);
    @Bridge private native static String objc_getModelSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/model">@property(nonatomic, readonly, retain) NSString *model</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getModel() {
        if (customClass) { return objc_getModelSuper(getSuper(), model); } else { return objc_getModel(this, model); }
    }
    
    private static final Selector isMultitaskingSupported = Selector.register("isMultitaskingSupported");
    @Bridge private native static boolean objc_isMultitaskingSupported(UIDevice __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isMultitaskingSupportedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/multitaskingSupported">@property(nonatomic, readonly, getter=isMultitaskingSupported) BOOL multitaskingSupported</a>
     * @since Available in iOS 4.0 and later.
     */
    public boolean isMultitaskingSupported() {
        if (customClass) { return objc_isMultitaskingSupportedSuper(getSuper(), isMultitaskingSupported); } else { return objc_isMultitaskingSupported(this, isMultitaskingSupported); }
    }
    
    private static final Selector name = Selector.register("name");
    @Bridge private native static String objc_getName(UIDevice __self__, Selector __cmd__);
    @Bridge private native static String objc_getNameSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/name">@property(nonatomic, readonly, retain) NSString *name</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getName() {
        if (customClass) { return objc_getNameSuper(getSuper(), name); } else { return objc_getName(this, name); }
    }
    
    private static final Selector orientation = Selector.register("orientation");
    @Bridge private native static UIDeviceOrientation objc_getOrientation(UIDevice __self__, Selector __cmd__);
    @Bridge private native static UIDeviceOrientation objc_getOrientationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/orientation">@property(nonatomic, readonly) UIDeviceOrientation orientation</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIDeviceOrientation getOrientation() {
        if (customClass) { return objc_getOrientationSuper(getSuper(), orientation); } else { return objc_getOrientation(this, orientation); }
    }
    
    private static final Selector isProximityMonitoringEnabled = Selector.register("isProximityMonitoringEnabled");
    @Bridge private native static boolean objc_isProximityMonitoringEnabled(UIDevice __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isProximityMonitoringEnabledSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/proximityMonitoringEnabled">@property(nonatomic, getter=isProximityMonitoringEnabled) BOOL proximityMonitoringEnabled</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isProximityMonitoringEnabled() {
        if (customClass) { return objc_isProximityMonitoringEnabledSuper(getSuper(), isProximityMonitoringEnabled); } else { return objc_isProximityMonitoringEnabled(this, isProximityMonitoringEnabled); }
    }
    
    private static final Selector setProximityMonitoringEnabled$ = Selector.register("setProximityMonitoringEnabled:");
    @Bridge private native static void objc_setProximityMonitoringEnabled(UIDevice __self__, Selector __cmd__, boolean proximityMonitoringEnabled);
    @Bridge private native static void objc_setProximityMonitoringEnabledSuper(ObjCSuper __super__, Selector __cmd__, boolean proximityMonitoringEnabled);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/proximityMonitoringEnabled">@property(nonatomic, getter=isProximityMonitoringEnabled) BOOL proximityMonitoringEnabled</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setProximityMonitoringEnabled(boolean proximityMonitoringEnabled) {
        if (customClass) { objc_setProximityMonitoringEnabledSuper(getSuper(), setProximityMonitoringEnabled$, proximityMonitoringEnabled); } else { objc_setProximityMonitoringEnabled(this, setProximityMonitoringEnabled$, proximityMonitoringEnabled); }
    }
    
    private static final Selector proximityState = Selector.register("proximityState");
    @Bridge private native static boolean objc_isProximityState(UIDevice __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isProximityStateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/proximityState">@property(nonatomic, readonly) BOOL proximityState</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isProximityState() {
        if (customClass) { return objc_isProximityStateSuper(getSuper(), proximityState); } else { return objc_isProximityState(this, proximityState); }
    }
    
    private static final Selector systemName = Selector.register("systemName");
    @Bridge private native static String objc_getSystemName(UIDevice __self__, Selector __cmd__);
    @Bridge private native static String objc_getSystemNameSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/systemName">@property(nonatomic, readonly, retain) NSString *systemName</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getSystemName() {
        if (customClass) { return objc_getSystemNameSuper(getSuper(), systemName); } else { return objc_getSystemName(this, systemName); }
    }
    
    private static final Selector systemVersion = Selector.register("systemVersion");
    @Bridge private native static String objc_getSystemVersion(UIDevice __self__, Selector __cmd__);
    @Bridge private native static String objc_getSystemVersionSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/systemVersion">@property(nonatomic, readonly, retain) NSString *systemVersion</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getSystemVersion() {
        if (customClass) { return objc_getSystemVersionSuper(getSuper(), systemVersion); } else { return objc_getSystemVersion(this, systemVersion); }
    }
    
    private static final Selector userInterfaceIdiom = Selector.register("userInterfaceIdiom");
    @Bridge private native static UIUserInterfaceIdiom objc_getUserInterfaceIdiom(UIDevice __self__, Selector __cmd__);
    @Bridge private native static UIUserInterfaceIdiom objc_getUserInterfaceIdiomSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/userInterfaceIdiom">@property(nonatomic, readonly) UIUserInterfaceIdiom userInterfaceIdiom</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIUserInterfaceIdiom getUserInterfaceIdiom() {
        if (customClass) { return objc_getUserInterfaceIdiomSuper(getSuper(), userInterfaceIdiom); } else { return objc_getUserInterfaceIdiom(this, userInterfaceIdiom); }
    }
    
    private static final Selector identifierForVendor = Selector.register("identifierForVendor");
    @Bridge private native static NSUUID objc_getVendorIdentifier(UIDevice __self__, Selector __cmd__);
    @Bridge private native static NSUUID objc_getVendorIdentifierSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instp/UIDevice/identifierForVendor">@property(nonatomic, readonly, retain) NSUUID *identifierForVendor</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSUUID getVendorIdentifier() {
        if (customClass) { return objc_getVendorIdentifierSuper(getSuper(), identifierForVendor); } else { return objc_getVendorIdentifier(this, identifierForVendor); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector currentDevice = Selector.register("currentDevice");
    @Bridge private native static UIDevice objc_getCurrentDevice(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/clm/UIDevice/currentDevice">+ (UIDevice *)currentDevice</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIDevice getCurrentDevice() {
        return objc_getCurrentDevice(objCClass, currentDevice);
    }
    
    private static final Selector beginGeneratingDeviceOrientationNotifications = Selector.register("beginGeneratingDeviceOrientationNotifications");
    @Bridge private native static void objc_beginGeneratingDeviceOrientationNotifications(UIDevice __self__, Selector __cmd__);
    @Bridge private native static void objc_beginGeneratingDeviceOrientationNotificationsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instm/UIDevice/beginGeneratingDeviceOrientationNotifications">- (void)beginGeneratingDeviceOrientationNotifications</a>
     * @since Available in iOS 2.0 and later.
     */
    public void beginGeneratingDeviceOrientationNotifications() {
        if (customClass) { objc_beginGeneratingDeviceOrientationNotificationsSuper(getSuper(), beginGeneratingDeviceOrientationNotifications); } else { objc_beginGeneratingDeviceOrientationNotifications(this, beginGeneratingDeviceOrientationNotifications); }
    }
    
    private static final Selector endGeneratingDeviceOrientationNotifications = Selector.register("endGeneratingDeviceOrientationNotifications");
    @Bridge private native static void objc_endGeneratingDeviceOrientationNotifications(UIDevice __self__, Selector __cmd__);
    @Bridge private native static void objc_endGeneratingDeviceOrientationNotificationsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instm/UIDevice/endGeneratingDeviceOrientationNotifications">- (void)endGeneratingDeviceOrientationNotifications</a>
     * @since Available in iOS 2.0 and later.
     */
    public void endGeneratingDeviceOrientationNotifications() {
        if (customClass) { objc_endGeneratingDeviceOrientationNotificationsSuper(getSuper(), endGeneratingDeviceOrientationNotifications); } else { objc_endGeneratingDeviceOrientationNotifications(this, endGeneratingDeviceOrientationNotifications); }
    }
    
    private static final Selector playInputClick = Selector.register("playInputClick");
    @Bridge private native static void objc_playInputClick(UIDevice __self__, Selector __cmd__);
    @Bridge private native static void objc_playInputClickSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDevice_Class/Reference/UIDevice.html#//apple_ref/occ/instm/UIDevice/playInputClick">- (void)playInputClick</a>
     * @since Available in iOS 4.2 and later.
     */
    public void playInputClick() {
        if (customClass) { objc_playInputClickSuper(getSuper(), playInputClick); } else { objc_playInputClick(this, playInputClick); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("batteryLevel") public static float getBatteryLevel(UIDevice __self__, Selector __cmd__) { return __self__.getBatteryLevel(); }
        @Callback @BindSelector("isBatteryMonitoringEnabled") public static boolean isBatteryMonitoringEnabled(UIDevice __self__, Selector __cmd__) { return __self__.isBatteryMonitoringEnabled(); }
        @Callback @BindSelector("setBatteryMonitoringEnabled:") public static void setBatteryMonitoringEnabled(UIDevice __self__, Selector __cmd__, boolean batteryMonitoringEnabled) { __self__.setBatteryMonitoringEnabled(batteryMonitoringEnabled); }
        @Callback @BindSelector("batteryState") public static UIDeviceBatteryState getBatteryState(UIDevice __self__, Selector __cmd__) { return __self__.getBatteryState(); }
        @Callback @BindSelector("isGeneratingDeviceOrientationNotifications") public static boolean isGeneratesDeviceOrientationNotifications(UIDevice __self__, Selector __cmd__) { return __self__.isGeneratesDeviceOrientationNotifications(); }
        @Callback @BindSelector("localizedModel") public static String getLocalizedModel(UIDevice __self__, Selector __cmd__) { return __self__.getLocalizedModel(); }
        @Callback @BindSelector("model") public static String getModel(UIDevice __self__, Selector __cmd__) { return __self__.getModel(); }
        @Callback @BindSelector("isMultitaskingSupported") public static boolean isMultitaskingSupported(UIDevice __self__, Selector __cmd__) { return __self__.isMultitaskingSupported(); }
        @Callback @BindSelector("name") public static String getName(UIDevice __self__, Selector __cmd__) { return __self__.getName(); }
        @Callback @BindSelector("orientation") public static UIDeviceOrientation getOrientation(UIDevice __self__, Selector __cmd__) { return __self__.getOrientation(); }
        @Callback @BindSelector("isProximityMonitoringEnabled") public static boolean isProximityMonitoringEnabled(UIDevice __self__, Selector __cmd__) { return __self__.isProximityMonitoringEnabled(); }
        @Callback @BindSelector("setProximityMonitoringEnabled:") public static void setProximityMonitoringEnabled(UIDevice __self__, Selector __cmd__, boolean proximityMonitoringEnabled) { __self__.setProximityMonitoringEnabled(proximityMonitoringEnabled); }
        @Callback @BindSelector("proximityState") public static boolean isProximityState(UIDevice __self__, Selector __cmd__) { return __self__.isProximityState(); }
        @Callback @BindSelector("systemName") public static String getSystemName(UIDevice __self__, Selector __cmd__) { return __self__.getSystemName(); }
        @Callback @BindSelector("systemVersion") public static String getSystemVersion(UIDevice __self__, Selector __cmd__) { return __self__.getSystemVersion(); }
        @Callback @BindSelector("userInterfaceIdiom") public static UIUserInterfaceIdiom getUserInterfaceIdiom(UIDevice __self__, Selector __cmd__) { return __self__.getUserInterfaceIdiom(); }
        @Callback @BindSelector("identifierForVendor") public static NSUUID getVendorIdentifier(UIDevice __self__, Selector __cmd__) { return __self__.getVendorIdentifier(); }
        @Callback @BindSelector("beginGeneratingDeviceOrientationNotifications") public static void beginGeneratingDeviceOrientationNotifications(UIDevice __self__, Selector __cmd__) { __self__.beginGeneratingDeviceOrientationNotifications(); }
        @Callback @BindSelector("endGeneratingDeviceOrientationNotifications") public static void endGeneratingDeviceOrientationNotifications(UIDevice __self__, Selector __cmd__) { __self__.endGeneratingDeviceOrientationNotifications(); }
        @Callback @BindSelector("playInputClick") public static void playInputClick(UIDevice __self__, Selector __cmd__) { __self__.playInputClick(); }
    }
    /*</callbacks>*/

}
