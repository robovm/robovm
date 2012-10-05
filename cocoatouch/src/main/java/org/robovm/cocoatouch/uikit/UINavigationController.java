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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html">UINavigationController Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UINavigationController /*</name>*/ 
    extends /*<extends>*/ UIViewController /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UINavigationController /*</name>*/.class);
    }

    /*<constructors>*/
    public UINavigationController() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/initWithNavigationBarClass:toolbarClass:">- (instancetype)initWithNavigationBarClass:(Class)navigationBarClass toolbarClass:(Class)toolbarClass</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("initWithNavigationBarClass:toolbarClass:") public UINavigationController(@Type("Class") ObjCClass navigationBarClass, @Type("Class") ObjCClass toolbarClass) {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/initWithRootViewController:">- (id)initWithRootViewController:(UIViewController *)rootViewController</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithRootViewController:") public UINavigationController(@Type("UIViewController *") UIViewController rootViewController) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/delegate">@property(nonatomic, assign) id&amp;lt;UINavigationControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native @Type("id<UINavigationControllerDelegate>") UINavigationControllerDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/delegate">@property(nonatomic, assign) id&amp;lt;UINavigationControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UINavigationControllerDelegate>") UINavigationControllerDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/navigationBar">@property(nonatomic, readonly) UINavigationBar *navigationBar</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("navigationBar") public native @Type("UINavigationBar *") UINavigationBar getNavigationBar();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/navigationBarHidden">@property(nonatomic, getter=isNavigationBarHidden) BOOL navigationBarHidden</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isNavigationBarHidden") public native @Type("BOOL") boolean isNavigationBarHidden();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/navigationBarHidden">@property(nonatomic, getter=isNavigationBarHidden) BOOL navigationBarHidden</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setNavigationBarHidden:") public native void setNavigationBarHidden(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/toolbar">@property(nonatomic,readonly) UIToolbar *toolbar</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("toolbar") public native @Type("UIToolbar *") UIToolbar getToolbar();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/toolbarHidden">@property(nonatomic,getter=isToolbarHidden) BOOL toolbarHidden</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isToolbarHidden") public native @Type("BOOL") boolean isToolbarHidden();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/toolbarHidden">@property(nonatomic,getter=isToolbarHidden) BOOL toolbarHidden</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setToolbarHidden:") public native void setToolbarHidden(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/topViewController">@property(nonatomic, readonly, retain) UIViewController *topViewController</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("topViewController") public native @Type("UIViewController *") UIViewController getTopViewController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/viewControllers">@property(nonatomic, copy) NSArray *viewControllers</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("viewControllers") public native @Type("NSArray *") NSArray getViewControllers();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/viewControllers">@property(nonatomic, copy) NSArray *viewControllers</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setViewControllers:") public native void setViewControllers(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/visibleViewController">@property(nonatomic, readonly, retain) UIViewController *visibleViewController</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("visibleViewController") public native @Type("UIViewController *") UIViewController getVisibleViewController();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/popToRootViewControllerAnimated:">- (NSArray *)popToRootViewControllerAnimated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("popToRootViewControllerAnimated:") public native @Type("NSArray *") NSArray popToRootViewController(@Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/popToViewController:animated:">- (NSArray *)popToViewController:(UIViewController *)viewController animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("popToViewController:animated:") public native @Type("NSArray *") NSArray popToViewController(@Type("UIViewController *") UIViewController viewController, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/popViewControllerAnimated:">- (UIViewController *)popViewControllerAnimated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("popViewControllerAnimated:") public native @Type("UIViewController *") UIViewController popViewControllerAnimated(@Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/pushViewController:animated:">- (void)pushViewController:(UIViewController *)viewController animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("pushViewController:animated:") public native @Type("void") void pushViewController(@Type("UIViewController *") UIViewController viewController, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/setNavigationBarHidden:animated:">- (void)setNavigationBarHidden:(BOOL)hidden animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setNavigationBarHidden:animated:") public native @Type("void") void setNavigationBarHidden(@Type("BOOL") boolean hidden, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/setToolbarHidden:animated:">- (void)setToolbarHidden:(BOOL)hidden animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setToolbarHidden:animated:") public native @Type("void") void setToolbarHidden(@Type("BOOL") boolean hidden, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/setViewControllers:animated:">- (void)setViewControllers:(NSArray *)viewControllers animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setViewControllers:animated:") public native @Type("void") void setViewControllers(@Type("NSArray *") NSArray viewControllers, @Type("BOOL") boolean animated);
    /*</methods>*/

}
