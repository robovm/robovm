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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html">UIMenuController Class Reference</a>
 *   @since Available in iOS 3.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIMenuController /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIMenuController /*</name>*/.class);
    }

    /*<constructors>*/
    public UIMenuController() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/arrowDirection">@property UIMenuControllerArrowDirection arrowDirection</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("arrowDirection") public native @Type("UIMenuControllerArrowDirection") UIMenuControllerArrowDirection getArrowDirection();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/arrowDirection">@property UIMenuControllerArrowDirection arrowDirection</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setArrowDirection:") public native void setArrowDirection(@Type("UIMenuControllerArrowDirection") UIMenuControllerArrowDirection v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/menuFrame">@property(nonatomic, readonly) CGRect menuFrame</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("menuFrame") public native @Type("CGRect") CGRect getMenuFrame();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/menuItems">@property(copy) NSArray *menuItems</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("menuItems") public native @Type("NSArray *") NSArray getMenuItems();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/menuItems">@property(copy) NSArray *menuItems</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setMenuItems:") public native void setMenuItems(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/menuVisible">@property(nonatomic, getter=isMenuVisible) BOOL menuVisible</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isMenuVisible") public native @Type("BOOL") boolean isMenuVisible();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/menuVisible">@property(nonatomic, getter=isMenuVisible) BOOL menuVisible</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setMenuVisible:") public native void setMenuVisible(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/clm/UIMenuController/sharedMenuController">+ (UIMenuController *)sharedMenuController</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("sharedMenuController") public native static @Type("UIMenuController *") UIMenuController getSharedMenuController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instm/UIMenuController/setMenuVisible:animated:">- (void)setMenuVisible:(BOOL)menuVisible animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setMenuVisible:animated:") public native @Type("void") void setMenuVisible(@Type("BOOL") boolean menuVisible, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instm/UIMenuController/setTargetRect:inView:">- (void)setTargetRect:(CGRect)targetRect inView:(UIView *)targetView</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setTargetRect:inView:") public native @Type("void") void setTargetRect(@Type("CGRect") CGRect targetRect, @Type("UIView *") UIView targetView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instm/UIMenuController/update">- (void)update</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("update") public native @Type("void") void update();
    /*</methods>*/

}
