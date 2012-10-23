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
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html">UIScreen Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIScreen /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIScreen /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIScreen /*</name>*/.class);

    /*<constructors>*/
    protected UIScreen(SkipInit skipInit) { super(skipInit); }
    public UIScreen() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector applicationFrame = Selector.register("applicationFrame");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getApplicationFrame(UIScreen __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getApplicationFrameSuper(ObjCSuper __super__, UIScreen __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/applicationFrame">@property(nonatomic, readonly) CGRect applicationFrame</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getApplicationFrame() {
        if (customClass) { return objc_getApplicationFrameSuper(getSuper(), this, applicationFrame); } else { return objc_getApplicationFrame(this, applicationFrame); }
    }
    
    private static final Selector availableModes = Selector.register("availableModes");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getAvailableModes(UIScreen __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getAvailableModesSuper(ObjCSuper __super__, UIScreen __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/availableModes">@property(nonatomic, readonly, copy) NSArray *availableModes</a>
     * @since Available in iOS 3.2 and later.
     */
    public NSArray getAvailableModes() {
        if (customClass) { return objc_getAvailableModesSuper(getSuper(), this, availableModes); } else { return objc_getAvailableModes(this, availableModes); }
    }
    
    private static final Selector bounds = Selector.register("bounds");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getBounds(UIScreen __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getBoundsSuper(ObjCSuper __super__, UIScreen __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/bounds">@property(nonatomic, readonly) CGRect bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getBounds() {
        if (customClass) { return objc_getBoundsSuper(getSuper(), this, bounds); } else { return objc_getBounds(this, bounds); }
    }
    
    private static final Selector brightness = Selector.register("brightness");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getBrightness(UIScreen __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getBrightnessSuper(ObjCSuper __super__, UIScreen __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/brightness">@property(nonatomic) CGFloat brightness</a>
     * @since Available in iOS 5.0 and later.
     */
    public float getBrightness() {
        if (customClass) { return objc_getBrightnessSuper(getSuper(), this, brightness); } else { return objc_getBrightness(this, brightness); }
    }
    
    private static final Selector setBrightness$ = Selector.register("setBrightness:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setBrightness(UIScreen __self__, Selector __cmd__, float brightness);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setBrightnessSuper(ObjCSuper __super__, UIScreen __self__, Selector __cmd__, float brightness);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/brightness">@property(nonatomic) CGFloat brightness</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setBrightness(float brightness) {
        if (customClass) { objc_setBrightnessSuper(getSuper(), this, setBrightness$, brightness); } else { objc_setBrightness(this, setBrightness$, brightness); }
    }
    
    private static final Selector currentMode = Selector.register("currentMode");
    @Bridge(symbol = "objc_msgSend") private native static UIScreenMode objc_getCurrentMode(UIScreen __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIScreenMode objc_getCurrentModeSuper(ObjCSuper __super__, UIScreen __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/currentMode">@property(nonatomic, retain) UIScreenMode *currentMode</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIScreenMode getCurrentMode() {
        if (customClass) { return objc_getCurrentModeSuper(getSuper(), this, currentMode); } else { return objc_getCurrentMode(this, currentMode); }
    }
    
    private static final Selector setCurrentMode$ = Selector.register("setCurrentMode:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setCurrentMode(UIScreen __self__, Selector __cmd__, UIScreenMode currentMode);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setCurrentModeSuper(ObjCSuper __super__, UIScreen __self__, Selector __cmd__, UIScreenMode currentMode);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/currentMode">@property(nonatomic, retain) UIScreenMode *currentMode</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setCurrentMode(UIScreenMode currentMode) {
        if (customClass) { objc_setCurrentModeSuper(getSuper(), this, setCurrentMode$, currentMode); } else { objc_setCurrentMode(this, setCurrentMode$, currentMode); }
    }
    
    private static final Selector mirroredScreen = Selector.register("mirroredScreen");
    @Bridge(symbol = "objc_msgSend") private native static UIScreen objc_getMirroredScreen(UIScreen __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIScreen objc_getMirroredScreenSuper(ObjCSuper __super__, UIScreen __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/mirroredScreen">@property(nonatomic, readonly, retain) UIScreen *mirroredScreen</a>
     * @since Available in iOS 4.3 and later.
     */
    public UIScreen getMirroredScreen() {
        if (customClass) { return objc_getMirroredScreenSuper(getSuper(), this, mirroredScreen); } else { return objc_getMirroredScreen(this, mirroredScreen); }
    }
    
    private static final Selector overscanCompensation = Selector.register("overscanCompensation");
    @Bridge(symbol = "objc_msgSend") private native static UIScreenOverscanCompensation objc_getOverscanCompensation(UIScreen __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIScreenOverscanCompensation objc_getOverscanCompensationSuper(ObjCSuper __super__, UIScreen __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/overscanCompensation">@property(nonatomic) UIScreenOverscanCompensation overscanCompensation</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIScreenOverscanCompensation getOverscanCompensation() {
        if (customClass) { return objc_getOverscanCompensationSuper(getSuper(), this, overscanCompensation); } else { return objc_getOverscanCompensation(this, overscanCompensation); }
    }
    
    private static final Selector setOverscanCompensation$ = Selector.register("setOverscanCompensation:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setOverscanCompensation(UIScreen __self__, Selector __cmd__, UIScreenOverscanCompensation overscanCompensation);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setOverscanCompensationSuper(ObjCSuper __super__, UIScreen __self__, Selector __cmd__, UIScreenOverscanCompensation overscanCompensation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/overscanCompensation">@property(nonatomic) UIScreenOverscanCompensation overscanCompensation</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setOverscanCompensation(UIScreenOverscanCompensation overscanCompensation) {
        if (customClass) { objc_setOverscanCompensationSuper(getSuper(), this, setOverscanCompensation$, overscanCompensation); } else { objc_setOverscanCompensation(this, setOverscanCompensation$, overscanCompensation); }
    }
    
    private static final Selector preferredMode = Selector.register("preferredMode");
    @Bridge(symbol = "objc_msgSend") private native static UIScreenMode objc_getPreferredMode(UIScreen __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIScreenMode objc_getPreferredModeSuper(ObjCSuper __super__, UIScreen __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/preferredMode">@property(nonatomic, readonly, retain) UIScreenMode *preferredMode</a>
     * @since Available in iOS 4.3 and later.
     */
    public UIScreenMode getPreferredMode() {
        if (customClass) { return objc_getPreferredModeSuper(getSuper(), this, preferredMode); } else { return objc_getPreferredMode(this, preferredMode); }
    }
    
    private static final Selector scale = Selector.register("scale");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getScale(UIScreen __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getScaleSuper(ObjCSuper __super__, UIScreen __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/scale">@property(nonatomic, readonly) CGFloat scale</a>
     * @since Available in iOS 4.0 and later.
     */
    public float getScale() {
        if (customClass) { return objc_getScaleSuper(getSuper(), this, scale); } else { return objc_getScale(this, scale); }
    }
    
    private static final Selector wantsSoftwareDimming = Selector.register("wantsSoftwareDimming");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isWantsSoftwareDimming(UIScreen __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isWantsSoftwareDimmingSuper(ObjCSuper __super__, UIScreen __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/wantsSoftwareDimming">@property(nonatomic) BOOL wantsSoftwareDimming</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isWantsSoftwareDimming() {
        if (customClass) { return objc_isWantsSoftwareDimmingSuper(getSuper(), this, wantsSoftwareDimming); } else { return objc_isWantsSoftwareDimming(this, wantsSoftwareDimming); }
    }
    
    private static final Selector setWantsSoftwareDimming$ = Selector.register("setWantsSoftwareDimming:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setWantsSoftwareDimming(UIScreen __self__, Selector __cmd__, boolean wantsSoftwareDimming);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setWantsSoftwareDimmingSuper(ObjCSuper __super__, UIScreen __self__, Selector __cmd__, boolean wantsSoftwareDimming);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/wantsSoftwareDimming">@property(nonatomic) BOOL wantsSoftwareDimming</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setWantsSoftwareDimming(boolean wantsSoftwareDimming) {
        if (customClass) { objc_setWantsSoftwareDimmingSuper(getSuper(), this, setWantsSoftwareDimming$, wantsSoftwareDimming); } else { objc_setWantsSoftwareDimming(this, setWantsSoftwareDimming$, wantsSoftwareDimming); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector mainScreen = Selector.register("mainScreen");
    @Bridge(symbol = "objc_msgSend") private native static UIScreen objc_getMainScreen(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/clm/UIScreen/mainScreen">+ (UIScreen *)mainScreen</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIScreen getMainScreen() {
        return objc_getMainScreen(objCClass, mainScreen);
    }
    
    private static final Selector screens = Selector.register("screens");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getScreens(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/clm/UIScreen/screens">+ (NSArray *)screens</a>
     * @since Available in iOS 3.2 and later.
     */
    public static NSArray getScreens() {
        return objc_getScreens(objCClass, screens);
    }
    
    private static final Selector displayLinkWithTarget$selector$ = Selector.register("displayLinkWithTarget:selector:");
    @Bridge(symbol = "objc_msgSend") private native static CADisplayLink objc_createDisplayLink(UIScreen __self__, Selector __cmd__, NSObject target, Selector sel);
    @Bridge(symbol = "objc_msgSendSuper") private native static CADisplayLink objc_createDisplayLinkSuper(ObjCSuper __super__, UIScreen __self__, Selector __cmd__, NSObject target, Selector sel);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instm/UIScreen/displayLinkWithTarget:selector:">- (CADisplayLink *)displayLinkWithTarget:(id)target selector:(SEL)sel</a>
     * @since Available in iOS 4.0 and later.
     */
    public CADisplayLink createDisplayLink(NSObject target, Selector sel) {
        if (customClass) { return objc_createDisplayLinkSuper(getSuper(), this, displayLinkWithTarget$selector$, target, sel); } else { return objc_createDisplayLink(this, displayLinkWithTarget$selector$, target, sel); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("displayLinkWithTarget:selector:") public static CADisplayLink createDisplayLink(UIScreen __self__, Selector __cmd__, NSObject target, Selector sel) { return __self__.createDisplayLink(target, sel); }
    }
    /*</callbacks>*/

}
