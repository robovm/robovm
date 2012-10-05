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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UINavigationController /*</name>*/.class);

    /*<constructors>*/
    protected UINavigationController(SkipInit skipInit) { super(skipInit); }
    public UINavigationController() {}
    
    private static final Selector initWithNavigationBarClass$toolbarClass$ = Selector.register("initWithNavigationBarClass:toolbarClass:");
    @Bridge(symbol = "objc_msgSend") private native static UINavigationController objc_initWithNavigationBarClass(UINavigationController __self__, Selector __cmd__, ObjCClass navigationBarClass, ObjCClass toolbarClass);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/initWithNavigationBarClass:toolbarClass:">- (instancetype)initWithNavigationBarClass:(Class)navigationBarClass toolbarClass:(Class)toolbarClass</a>
     * @since Available in iOS 5.0 and later.
     */
    public UINavigationController(ObjCClass navigationBarClass, ObjCClass toolbarClass) {
        super((SkipInit) null);
        objc_initWithNavigationBarClass(this, initWithNavigationBarClass$toolbarClass$, navigationBarClass, toolbarClass);
    }
    
    private static final Selector initWithRootViewController$ = Selector.register("initWithRootViewController:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithRootViewController(UINavigationController __self__, Selector __cmd__, UIViewController rootViewController);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/initWithRootViewController:">- (id)initWithRootViewController:(UIViewController *)rootViewController</a>
     * @since Available in iOS 2.0 and later.
     */
    public UINavigationController(UIViewController rootViewController) {
        super((SkipInit) null);
        objc_initWithRootViewController(this, initWithRootViewController$, rootViewController);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/delegate">@property(nonatomic, assign) id&amp;lt;UINavigationControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native UINavigationControllerDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/delegate">@property(nonatomic, assign) id&amp;lt;UINavigationControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(UINavigationControllerDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/navigationBar">@property(nonatomic, readonly) UINavigationBar *navigationBar</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("navigationBar") public native UINavigationBar getNavigationBar();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/navigationBarHidden">@property(nonatomic, getter=isNavigationBarHidden) BOOL navigationBarHidden</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isNavigationBarHidden") public native boolean isNavigationBarHidden();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/navigationBarHidden">@property(nonatomic, getter=isNavigationBarHidden) BOOL navigationBarHidden</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setNavigationBarHidden:") public native void setNavigationBarHidden(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/toolbar">@property(nonatomic,readonly) UIToolbar *toolbar</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("toolbar") public native UIToolbar getToolbar();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/toolbarHidden">@property(nonatomic,getter=isToolbarHidden) BOOL toolbarHidden</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isToolbarHidden") public native boolean isToolbarHidden();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/toolbarHidden">@property(nonatomic,getter=isToolbarHidden) BOOL toolbarHidden</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setToolbarHidden:") public native void setToolbarHidden(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/topViewController">@property(nonatomic, readonly, retain) UIViewController *topViewController</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("topViewController") public native UIViewController getTopViewController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/viewControllers">@property(nonatomic, copy) NSArray *viewControllers</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("viewControllers") public native NSArray getViewControllers();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/viewControllers">@property(nonatomic, copy) NSArray *viewControllers</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setViewControllers:") public native void setViewControllers(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/visibleViewController">@property(nonatomic, readonly, retain) UIViewController *visibleViewController</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("visibleViewController") public native UIViewController getVisibleViewController();
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector popToRootViewControllerAnimated$ = Selector.register("popToRootViewControllerAnimated:");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_popToRootViewController(UINavigationController __self__, Selector __cmd__, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_popToRootViewControllerSuper(ObjCSuper __super__, UINavigationController __self__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/popToRootViewControllerAnimated:">- (NSArray *)popToRootViewControllerAnimated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray popToRootViewController(boolean animated) {
        if (customClass) { return objc_popToRootViewControllerSuper(getSuper(), this, popToRootViewControllerAnimated$, animated); } else { return objc_popToRootViewController(this, popToRootViewControllerAnimated$, animated); }
    }
    
    private static final Selector popToViewController$animated$ = Selector.register("popToViewController:animated:");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_popToViewController(UINavigationController __self__, Selector __cmd__, UIViewController viewController, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_popToViewControllerSuper(ObjCSuper __super__, UINavigationController __self__, Selector __cmd__, UIViewController viewController, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/popToViewController:animated:">- (NSArray *)popToViewController:(UIViewController *)viewController animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray popToViewController(UIViewController viewController, boolean animated) {
        if (customClass) { return objc_popToViewControllerSuper(getSuper(), this, popToViewController$animated$, viewController, animated); } else { return objc_popToViewController(this, popToViewController$animated$, viewController, animated); }
    }
    
    private static final Selector popViewControllerAnimated$ = Selector.register("popViewControllerAnimated:");
    @Bridge(symbol = "objc_msgSend") private native static UIViewController objc_popViewControllerAnimated(UINavigationController __self__, Selector __cmd__, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIViewController objc_popViewControllerAnimatedSuper(ObjCSuper __super__, UINavigationController __self__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/popViewControllerAnimated:">- (UIViewController *)popViewControllerAnimated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIViewController popViewControllerAnimated(boolean animated) {
        if (customClass) { return objc_popViewControllerAnimatedSuper(getSuper(), this, popViewControllerAnimated$, animated); } else { return objc_popViewControllerAnimated(this, popViewControllerAnimated$, animated); }
    }
    
    private static final Selector pushViewController$animated$ = Selector.register("pushViewController:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_pushViewController(UINavigationController __self__, Selector __cmd__, UIViewController viewController, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_pushViewControllerSuper(ObjCSuper __super__, UINavigationController __self__, Selector __cmd__, UIViewController viewController, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/pushViewController:animated:">- (void)pushViewController:(UIViewController *)viewController animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void pushViewController(UIViewController viewController, boolean animated) {
        if (customClass) { objc_pushViewControllerSuper(getSuper(), this, pushViewController$animated$, viewController, animated); } else { objc_pushViewController(this, pushViewController$animated$, viewController, animated); }
    }
    
    private static final Selector setNavigationBarHidden$animated$ = Selector.register("setNavigationBarHidden:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setNavigationBarHidden(UINavigationController __self__, Selector __cmd__, boolean hidden, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setNavigationBarHiddenSuper(ObjCSuper __super__, UINavigationController __self__, Selector __cmd__, boolean hidden, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/setNavigationBarHidden:animated:">- (void)setNavigationBarHidden:(BOOL)hidden animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setNavigationBarHidden(boolean hidden, boolean animated) {
        if (customClass) { objc_setNavigationBarHiddenSuper(getSuper(), this, setNavigationBarHidden$animated$, hidden, animated); } else { objc_setNavigationBarHidden(this, setNavigationBarHidden$animated$, hidden, animated); }
    }
    
    private static final Selector setToolbarHidden$animated$ = Selector.register("setToolbarHidden:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setToolbarHidden(UINavigationController __self__, Selector __cmd__, boolean hidden, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setToolbarHiddenSuper(ObjCSuper __super__, UINavigationController __self__, Selector __cmd__, boolean hidden, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/setToolbarHidden:animated:">- (void)setToolbarHidden:(BOOL)hidden animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setToolbarHidden(boolean hidden, boolean animated) {
        if (customClass) { objc_setToolbarHiddenSuper(getSuper(), this, setToolbarHidden$animated$, hidden, animated); } else { objc_setToolbarHidden(this, setToolbarHidden$animated$, hidden, animated); }
    }
    
    private static final Selector setViewControllers$animated$ = Selector.register("setViewControllers:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setViewControllers(UINavigationController __self__, Selector __cmd__, NSArray viewControllers, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setViewControllersSuper(ObjCSuper __super__, UINavigationController __self__, Selector __cmd__, NSArray viewControllers, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/setViewControllers:animated:">- (void)setViewControllers:(NSArray *)viewControllers animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setViewControllers(NSArray viewControllers, boolean animated) {
        if (customClass) { objc_setViewControllersSuper(getSuper(), this, setViewControllers$animated$, viewControllers, animated); } else { objc_setViewControllers(this, setViewControllers$animated$, viewControllers, animated); }
    }
    /*</methods>*/

}
