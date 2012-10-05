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

    /*<constructors>*/
    public UIScreen() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/applicationFrame">@property(nonatomic, readonly) CGRect applicationFrame</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("applicationFrame") public native @Type("CGRect") CGRect getApplicationFrame();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/availableModes">@property(nonatomic, readonly, copy) NSArray *availableModes</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("availableModes") public native @Type("NSArray *") NSArray getAvailableModes();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/bounds">@property(nonatomic, readonly) CGRect bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("bounds") public native @Type("CGRect") CGRect getBounds();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/brightness">@property(nonatomic) CGFloat brightness</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("brightness") public native @Type("CGFloat") float getBrightness();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/brightness">@property(nonatomic) CGFloat brightness</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setBrightness:") public native void setBrightness(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/currentMode">@property(nonatomic, retain) UIScreenMode *currentMode</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("currentMode") public native @Type("UIScreenMode *") UIScreenMode getCurrentMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/currentMode">@property(nonatomic, retain) UIScreenMode *currentMode</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setCurrentMode:") public native void setCurrentMode(@Type("UIScreenMode *") UIScreenMode v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/mirroredScreen">@property(nonatomic, readonly, retain) UIScreen *mirroredScreen</a>
     * @since Available in iOS 4.3 and later.
     */
    @Bind("mirroredScreen") public native @Type("UIScreen *") UIScreen getMirroredScreen();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/overscanCompensation">@property(nonatomic) UIScreenOverscanCompensation overscanCompensation</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("overscanCompensation") public native @Type("UIScreenOverscanCompensation") UIScreenOverscanCompensation getOverscanCompensation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/overscanCompensation">@property(nonatomic) UIScreenOverscanCompensation overscanCompensation</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setOverscanCompensation:") public native void setOverscanCompensation(@Type("UIScreenOverscanCompensation") UIScreenOverscanCompensation v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/preferredMode">@property(nonatomic, readonly, retain) UIScreenMode *preferredMode</a>
     * @since Available in iOS 4.3 and later.
     */
    @Bind("preferredMode") public native @Type("UIScreenMode *") UIScreenMode getPreferredMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/scale">@property(nonatomic, readonly) CGFloat scale</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("scale") public native @Type("CGFloat") float getScale();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/wantsSoftwareDimming">@property(nonatomic) BOOL wantsSoftwareDimming</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("wantsSoftwareDimming") public native @Type("BOOL") boolean isWantsSoftwareDimming();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instp/UIScreen/wantsSoftwareDimming">@property(nonatomic) BOOL wantsSoftwareDimming</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setWantsSoftwareDimming:") public native void setWantsSoftwareDimming(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/clm/UIScreen/mainScreen">+ (UIScreen *)mainScreen</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("mainScreen") public native static @Type("UIScreen *") UIScreen getMainScreen();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/clm/UIScreen/screens">+ (NSArray *)screens</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("screens") public native static @Type("NSArray *") NSArray getScreens();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScreen_Class/Reference/UIScreen.html#//apple_ref/occ/instm/UIScreen/displayLinkWithTarget:selector:">- (CADisplayLink *)displayLinkWithTarget:(id)target selector:(SEL)sel</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("displayLinkWithTarget:selector:") public native @Type("CADisplayLink *") CADisplayLink createDisplayLink(@Type("id") NSObject target, @Type("SEL") Selector sel);
    /*</methods>*/

}
