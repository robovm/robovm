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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html">UIMenuController Class Reference</a>
 *   @since Available in iOS 3.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIMenuController /*</name>*/ 
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
    
    private static final Selector arrowDirection = Selector.register("arrowDirection");
    @Bridge private native static UIMenuControllerArrowDirection objc_getArrowDirection(UIMenuController __self__, Selector __cmd__);
    @Bridge private native static UIMenuControllerArrowDirection objc_getArrowDirectionSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/arrowDirection">@property UIMenuControllerArrowDirection arrowDirection</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIMenuControllerArrowDirection getArrowDirection() {
        if (customClass) { return objc_getArrowDirectionSuper(getSuper(), arrowDirection); } else { return objc_getArrowDirection(this, arrowDirection); }
    }
    
    private static final Selector setArrowDirection$ = Selector.register("setArrowDirection:");
    @Bridge private native static void objc_setArrowDirection(UIMenuController __self__, Selector __cmd__, UIMenuControllerArrowDirection arrowDirection);
    @Bridge private native static void objc_setArrowDirectionSuper(ObjCSuper __super__, Selector __cmd__, UIMenuControllerArrowDirection arrowDirection);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/arrowDirection">@property UIMenuControllerArrowDirection arrowDirection</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setArrowDirection(UIMenuControllerArrowDirection arrowDirection) {
        if (customClass) { objc_setArrowDirectionSuper(getSuper(), setArrowDirection$, arrowDirection); } else { objc_setArrowDirection(this, setArrowDirection$, arrowDirection); }
    }
    
    private static final Selector menuFrame = Selector.register("menuFrame");
    @Bridge private native static @ByVal CGRect objc_getMenuFrame(UIMenuController __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGRect objc_getMenuFrameSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/menuFrame">@property(nonatomic, readonly) CGRect menuFrame</a>
     * @since Available in iOS 3.0 and later.
     */
    public CGRect getMenuFrame() {
        if (customClass) { return objc_getMenuFrameSuper(getSuper(), menuFrame); } else { return objc_getMenuFrame(this, menuFrame); }
    }
    
    private static final Selector menuItems = Selector.register("menuItems");
    @Bridge private native static NSArray objc_getMenuItems(UIMenuController __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getMenuItemsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/menuItems">@property(copy) NSArray *menuItems</a>
     * @since Available in iOS 3.2 and later.
     */
    public NSArray getMenuItems() {
        if (customClass) { return objc_getMenuItemsSuper(getSuper(), menuItems); } else { return objc_getMenuItems(this, menuItems); }
    }
    
    private static final Selector setMenuItems$ = Selector.register("setMenuItems:");
    @Bridge private native static void objc_setMenuItems(UIMenuController __self__, Selector __cmd__, NSArray menuItems);
    @Bridge private native static void objc_setMenuItemsSuper(ObjCSuper __super__, Selector __cmd__, NSArray menuItems);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/menuItems">@property(copy) NSArray *menuItems</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setMenuItems(NSArray menuItems) {
        if (customClass) { objc_setMenuItemsSuper(getSuper(), setMenuItems$, menuItems); } else { objc_setMenuItems(this, setMenuItems$, menuItems); }
    }
    
    private static final Selector isMenuVisible = Selector.register("isMenuVisible");
    @Bridge private native static boolean objc_isMenuVisible(UIMenuController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isMenuVisibleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/menuVisible">@property(nonatomic, getter=isMenuVisible) BOOL menuVisible</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isMenuVisible() {
        if (customClass) { return objc_isMenuVisibleSuper(getSuper(), isMenuVisible); } else { return objc_isMenuVisible(this, isMenuVisible); }
    }
    
    private static final Selector setMenuVisible$ = Selector.register("setMenuVisible:");
    @Bridge private native static void objc_setMenuVisible(UIMenuController __self__, Selector __cmd__, boolean menuVisible);
    @Bridge private native static void objc_setMenuVisibleSuper(ObjCSuper __super__, Selector __cmd__, boolean menuVisible);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instp/UIMenuController/menuVisible">@property(nonatomic, getter=isMenuVisible) BOOL menuVisible</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setMenuVisible(boolean menuVisible) {
        if (customClass) { objc_setMenuVisibleSuper(getSuper(), setMenuVisible$, menuVisible); } else { objc_setMenuVisible(this, setMenuVisible$, menuVisible); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector sharedMenuController = Selector.register("sharedMenuController");
    @Bridge private native static UIMenuController objc_getSharedMenuController(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/clm/UIMenuController/sharedMenuController">+ (UIMenuController *)sharedMenuController</a>
     * @since Available in iOS 3.0 and later.
     */
    public static UIMenuController getSharedMenuController() {
        return objc_getSharedMenuController(objCClass, sharedMenuController);
    }
    
    private static final Selector setMenuVisible$animated$ = Selector.register("setMenuVisible:animated:");
    @Bridge private native static void objc_setMenuVisible(UIMenuController __self__, Selector __cmd__, boolean menuVisible, boolean animated);
    @Bridge private native static void objc_setMenuVisibleSuper(ObjCSuper __super__, Selector __cmd__, boolean menuVisible, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instm/UIMenuController/setMenuVisible:animated:">- (void)setMenuVisible:(BOOL)menuVisible animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setMenuVisible(boolean menuVisible, boolean animated) {
        if (customClass) { objc_setMenuVisibleSuper(getSuper(), setMenuVisible$animated$, menuVisible, animated); } else { objc_setMenuVisible(this, setMenuVisible$animated$, menuVisible, animated); }
    }
    
    private static final Selector setTargetRect$inView$ = Selector.register("setTargetRect:inView:");
    @Bridge private native static void objc_setTargetRect(UIMenuController __self__, Selector __cmd__, @ByVal CGRect targetRect, UIView targetView);
    @Bridge private native static void objc_setTargetRectSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect targetRect, UIView targetView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instm/UIMenuController/setTargetRect:inView:">- (void)setTargetRect:(CGRect)targetRect inView:(UIView *)targetView</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setTargetRect(CGRect targetRect, UIView targetView) {
        if (customClass) { objc_setTargetRectSuper(getSuper(), setTargetRect$inView$, targetRect, targetView); } else { objc_setTargetRect(this, setTargetRect$inView$, targetRect, targetView); }
    }
    
    private static final Selector update = Selector.register("update");
    @Bridge private native static void objc_update(UIMenuController __self__, Selector __cmd__);
    @Bridge private native static void objc_updateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../../../iPhone/Reference/UIMenuController_Class/UIMenuController.html#//apple_ref/occ/instm/UIMenuController/update">- (void)update</a>
     * @since Available in iOS 3.0 and later.
     */
    public void update() {
        if (customClass) { objc_updateSuper(getSuper(), update); } else { objc_update(this, update); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("arrowDirection") public static UIMenuControllerArrowDirection getArrowDirection(UIMenuController __self__, Selector __cmd__) { return __self__.getArrowDirection(); }
        @Callback @BindSelector("setArrowDirection:") public static void setArrowDirection(UIMenuController __self__, Selector __cmd__, UIMenuControllerArrowDirection arrowDirection) { __self__.setArrowDirection(arrowDirection); }
        @Callback @BindSelector("menuFrame") public static @ByVal CGRect getMenuFrame(UIMenuController __self__, Selector __cmd__) { return __self__.getMenuFrame(); }
        @Callback @BindSelector("menuItems") public static NSArray getMenuItems(UIMenuController __self__, Selector __cmd__) { return __self__.getMenuItems(); }
        @Callback @BindSelector("setMenuItems:") public static void setMenuItems(UIMenuController __self__, Selector __cmd__, NSArray menuItems) { __self__.setMenuItems(menuItems); }
        @Callback @BindSelector("isMenuVisible") public static boolean isMenuVisible(UIMenuController __self__, Selector __cmd__) { return __self__.isMenuVisible(); }
        @Callback @BindSelector("setMenuVisible:") public static void setMenuVisible(UIMenuController __self__, Selector __cmd__, boolean menuVisible) { __self__.setMenuVisible(menuVisible); }
        @Callback @BindSelector("setMenuVisible:animated:") public static void setMenuVisible(UIMenuController __self__, Selector __cmd__, boolean menuVisible, boolean animated) { __self__.setMenuVisible(menuVisible, animated); }
        @Callback @BindSelector("setTargetRect:inView:") public static void setTargetRect(UIMenuController __self__, Selector __cmd__, @ByVal CGRect targetRect, UIView targetView) { __self__.setTargetRect(targetRect, targetView); }
        @Callback @BindSelector("update") public static void update(UIMenuController __self__, Selector __cmd__) { __self__.update(); }
    }
    /*</callbacks>*/

}
