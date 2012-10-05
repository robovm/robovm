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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIMenuController /*</name>*/.class);

    /*<constructors>*/
    protected UIMenuController(SkipInit skipInit) { super(skipInit); }
    public UIMenuController() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/arrowDirection">@property UIMenuControllerArrowDirection arrowDirection</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("arrowDirection") public native UIMenuControllerArrowDirection getArrowDirection();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/arrowDirection">@property UIMenuControllerArrowDirection arrowDirection</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setArrowDirection:") public native void setArrowDirection(UIMenuControllerArrowDirection v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/menuFrame">@property(nonatomic, readonly) CGRect menuFrame</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("menuFrame") public native CGRect getMenuFrame();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/menuItems">@property(copy) NSArray *menuItems</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("menuItems") public native NSArray getMenuItems();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/menuItems">@property(copy) NSArray *menuItems</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setMenuItems:") public native void setMenuItems(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/menuVisible">@property(nonatomic, getter=isMenuVisible) BOOL menuVisible</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isMenuVisible") public native boolean isMenuVisible();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/menuVisible">@property(nonatomic, getter=isMenuVisible) BOOL menuVisible</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setMenuVisible:") public native void setMenuVisible(boolean v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector sharedMenuController = Selector.register("sharedMenuController");
    @Bridge(symbol = "objc_msgSend") private native static UIMenuController objc_getSharedMenuController(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/clm/UIMenuController/sharedMenuController">+ (UIMenuController *)sharedMenuController</a>
     * @since Available in iOS 3.0 and later.
     */
    public static UIMenuController getSharedMenuController() {
        return objc_getSharedMenuController(objCClass, sharedMenuController);
    }
    
    private static final Selector setMenuVisible$animated$ = Selector.register("setMenuVisible:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setMenuVisible(UIMenuController __self__, Selector __cmd__, boolean menuVisible, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setMenuVisibleSuper(ObjCSuper __super__, UIMenuController __self__, Selector __cmd__, boolean menuVisible, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instm/UIMenuController/setMenuVisible:animated:">- (void)setMenuVisible:(BOOL)menuVisible animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setMenuVisible(boolean menuVisible, boolean animated) {
        if (customClass) { objc_setMenuVisibleSuper(getSuper(), this, setMenuVisible$animated$, menuVisible, animated); } else { objc_setMenuVisible(this, setMenuVisible$animated$, menuVisible, animated); }
    }
    
    private static final Selector setTargetRect$inView$ = Selector.register("setTargetRect:inView:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTargetRect(UIMenuController __self__, Selector __cmd__, CGRect targetRect, UIView targetView);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTargetRectSuper(ObjCSuper __super__, UIMenuController __self__, Selector __cmd__, CGRect targetRect, UIView targetView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instm/UIMenuController/setTargetRect:inView:">- (void)setTargetRect:(CGRect)targetRect inView:(UIView *)targetView</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setTargetRect(CGRect targetRect, UIView targetView) {
        if (customClass) { objc_setTargetRectSuper(getSuper(), this, setTargetRect$inView$, targetRect, targetView); } else { objc_setTargetRect(this, setTargetRect$inView$, targetRect, targetView); }
    }
    
    private static final Selector update = Selector.register("update");
    @Bridge(symbol = "objc_msgSend") private native static void objc_update(UIMenuController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_updateSuper(ObjCSuper __super__, UIMenuController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instm/UIMenuController/update">- (void)update</a>
     * @since Available in iOS 3.0 and later.
     */
    public void update() {
        if (customClass) { objc_updateSuper(getSuper(), this, update); } else { objc_update(this, update); }
    }
    /*</methods>*/

}
