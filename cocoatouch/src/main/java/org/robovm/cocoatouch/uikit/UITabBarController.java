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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarController_Class/Reference/Reference.html">UITabBarController Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UITabBarController /*</name>*/ 
    extends /*<extends>*/ UIViewController /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITabBarController /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UITabBarController /*</name>*/.class);

    /*<constructors>*/
    protected UITabBarController(SkipInit skipInit) { super(skipInit); }
    public UITabBarController() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBarController/customizableViewControllers">@property(nonatomic, copy) NSArray *customizableViewControllers</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("customizableViewControllers") public native NSArray getCustomizableViewControllers();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBarController/customizableViewControllers">@property(nonatomic, copy) NSArray *customizableViewControllers</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setCustomizableViewControllers:") public native void setCustomizableViewControllers(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBarController/delegate">@property(nonatomic, assign) id&amp;lt;UITabBarControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native UITabBarControllerDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBarController/delegate">@property(nonatomic, assign) id&amp;lt;UITabBarControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(UITabBarControllerDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBarController/moreNavigationController">@property(nonatomic, readonly) UINavigationController *moreNavigationController</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("moreNavigationController") public native UINavigationController getMoreNavigationController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBarController/selectedIndex">@property(nonatomic) NSUInteger selectedIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("selectedIndex") public native int getSelectedIndex();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBarController/selectedIndex">@property(nonatomic) NSUInteger selectedIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSelectedIndex:") public native void setSelectedIndex(int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBarController/selectedViewController">@property(nonatomic, assign) UIViewController *selectedViewController</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("selectedViewController") public native UIViewController getSelectedViewController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBarController/selectedViewController">@property(nonatomic, assign) UIViewController *selectedViewController</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSelectedViewController:") public native void setSelectedViewController(UIViewController v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBarController/tabBar">@property(nonatomic,readonly) UITabBar *tabBar</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("tabBar") public native UITabBar getTabBar();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBarController/viewControllers">@property(nonatomic, copy) NSArray *viewControllers</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("viewControllers") public native NSArray getViewControllers();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBarController/viewControllers">@property(nonatomic, copy) NSArray *viewControllers</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setViewControllers:") public native void setViewControllers(NSArray v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector setViewControllers$animated$ = Selector.register("setViewControllers:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setViewControllers(UITabBarController __self__, Selector __cmd__, NSArray viewControllers, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setViewControllersSuper(ObjCSuper __super__, UITabBarController __self__, Selector __cmd__, NSArray viewControllers, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarController_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBarController/setViewControllers:animated:">- (void)setViewControllers:(NSArray *)viewControllers  animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setViewControllers(NSArray viewControllers, boolean animated) {
        if (customClass) { objc_setViewControllersSuper(getSuper(), this, setViewControllers$animated$, viewControllers, animated); } else { objc_setViewControllers(this, setViewControllers$animated$, viewControllers, animated); }
    }
    /*</methods>*/

}
