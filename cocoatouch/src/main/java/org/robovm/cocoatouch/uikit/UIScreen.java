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
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/applicationFrame">@property(nonatomic, readonly) CGRect applicationFrame</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("applicationFrame") public native CGRect getApplicationFrame();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/availableModes">@property(nonatomic, readonly, copy) NSArray *availableModes</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("availableModes") public native NSArray getAvailableModes();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/bounds">@property(nonatomic, readonly) CGRect bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("bounds") public native CGRect getBounds();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/brightness">@property(nonatomic) CGFloat brightness</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("brightness") public native float getBrightness();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/brightness">@property(nonatomic) CGFloat brightness</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setBrightness:") public native void setBrightness(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/currentMode">@property(nonatomic, retain) UIScreenMode *currentMode</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("currentMode") public native UIScreenMode getCurrentMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/currentMode">@property(nonatomic, retain) UIScreenMode *currentMode</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setCurrentMode:") public native void setCurrentMode(UIScreenMode v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/mirroredScreen">@property(nonatomic, readonly, retain) UIScreen *mirroredScreen</a>
     * @since Available in iOS 4.3 and later.
     */
    @Bind("mirroredScreen") public native UIScreen getMirroredScreen();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/overscanCompensation">@property(nonatomic) UIScreenOverscanCompensation overscanCompensation</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("overscanCompensation") public native UIScreenOverscanCompensation getOverscanCompensation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/overscanCompensation">@property(nonatomic) UIScreenOverscanCompensation overscanCompensation</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setOverscanCompensation:") public native void setOverscanCompensation(UIScreenOverscanCompensation v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/preferredMode">@property(nonatomic, readonly, retain) UIScreenMode *preferredMode</a>
     * @since Available in iOS 4.3 and later.
     */
    @Bind("preferredMode") public native UIScreenMode getPreferredMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/scale">@property(nonatomic, readonly) CGFloat scale</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("scale") public native float getScale();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/wantsSoftwareDimming">@property(nonatomic) BOOL wantsSoftwareDimming</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("wantsSoftwareDimming") public native boolean isWantsSoftwareDimming();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/wantsSoftwareDimming">@property(nonatomic) BOOL wantsSoftwareDimming</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setWantsSoftwareDimming:") public native void setWantsSoftwareDimming(boolean v);
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

}
