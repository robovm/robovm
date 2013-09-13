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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html">UINavigationController Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UINavigationController /*</name>*/ 
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
    @Bridge private native static @Pointer long objc_initWithNavigationBarClass(UINavigationController __self__, Selector __cmd__, ObjCClass navigationBarClass, ObjCClass toolbarClass);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/initWithNavigationBarClass:toolbarClass:">- (instancetype)initWithNavigationBarClass:(Class)navigationBarClass toolbarClass:(Class)toolbarClass</a>
     * @since Available in iOS 5.0 and later.
     */
    public UINavigationController(ObjCClass navigationBarClass, ObjCClass toolbarClass) {
        super((SkipInit) null);
        initObject(objc_initWithNavigationBarClass(this, initWithNavigationBarClass$toolbarClass$, navigationBarClass, toolbarClass));
    }
    
    private static final Selector initWithRootViewController$ = Selector.register("initWithRootViewController:");
    @Bridge private native static @Pointer long objc_initWithRootViewController(UINavigationController __self__, Selector __cmd__, UIViewController rootViewController);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/initWithRootViewController:">- (id)initWithRootViewController:(UIViewController *)rootViewController</a>
     * @since Available in iOS 2.0 and later.
     */
    public UINavigationController(UIViewController rootViewController) {
        super((SkipInit) null);
        initObject(objc_initWithRootViewController(this, initWithRootViewController$, rootViewController));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UINavigationControllerDelegate objc_getDelegate(UINavigationController __self__, Selector __cmd__);
    @Bridge private native static UINavigationControllerDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/delegate">@property(nonatomic, assign) id&amp;lt;UINavigationControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public UINavigationControllerDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UINavigationController __self__, Selector __cmd__, UINavigationControllerDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UINavigationControllerDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/delegate">@property(nonatomic, assign) id&amp;lt;UINavigationControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDelegate(UINavigationControllerDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector navigationBar = Selector.register("navigationBar");
    @Bridge private native static UINavigationBar objc_getNavigationBar(UINavigationController __self__, Selector __cmd__);
    @Bridge private native static UINavigationBar objc_getNavigationBarSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/navigationBar">@property(nonatomic, readonly) UINavigationBar *navigationBar</a>
     * @since Available in iOS 2.0 and later.
     */
    public UINavigationBar getNavigationBar() {
        if (customClass) { return objc_getNavigationBarSuper(getSuper(), navigationBar); } else { return objc_getNavigationBar(this, navigationBar); }
    }
    
    private static final Selector isNavigationBarHidden = Selector.register("isNavigationBarHidden");
    @Bridge private native static boolean objc_isNavigationBarHidden(UINavigationController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isNavigationBarHiddenSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/navigationBarHidden">@property(nonatomic, getter=isNavigationBarHidden) BOOL navigationBarHidden</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isNavigationBarHidden() {
        if (customClass) { return objc_isNavigationBarHiddenSuper(getSuper(), isNavigationBarHidden); } else { return objc_isNavigationBarHidden(this, isNavigationBarHidden); }
    }
    
    private static final Selector setNavigationBarHidden$ = Selector.register("setNavigationBarHidden:");
    @Bridge private native static void objc_setNavigationBarHidden(UINavigationController __self__, Selector __cmd__, boolean navigationBarHidden);
    @Bridge private native static void objc_setNavigationBarHiddenSuper(ObjCSuper __super__, Selector __cmd__, boolean navigationBarHidden);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/navigationBarHidden">@property(nonatomic, getter=isNavigationBarHidden) BOOL navigationBarHidden</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setNavigationBarHidden(boolean navigationBarHidden) {
        if (customClass) { objc_setNavigationBarHiddenSuper(getSuper(), setNavigationBarHidden$, navigationBarHidden); } else { objc_setNavigationBarHidden(this, setNavigationBarHidden$, navigationBarHidden); }
    }
    
    private static final Selector toolbar = Selector.register("toolbar");
    @Bridge private native static UIToolbar objc_getToolbar(UINavigationController __self__, Selector __cmd__);
    @Bridge private native static UIToolbar objc_getToolbarSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/toolbar">@property(nonatomic,readonly) UIToolbar *toolbar</a>
     * @since Available in iOS 3.0 and later.
     */
    public UIToolbar getToolbar() {
        if (customClass) { return objc_getToolbarSuper(getSuper(), toolbar); } else { return objc_getToolbar(this, toolbar); }
    }
    
    private static final Selector isToolbarHidden = Selector.register("isToolbarHidden");
    @Bridge private native static boolean objc_isToolbarHidden(UINavigationController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isToolbarHiddenSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/toolbarHidden">@property(nonatomic,getter=isToolbarHidden) BOOL toolbarHidden</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isToolbarHidden() {
        if (customClass) { return objc_isToolbarHiddenSuper(getSuper(), isToolbarHidden); } else { return objc_isToolbarHidden(this, isToolbarHidden); }
    }
    
    private static final Selector setToolbarHidden$ = Selector.register("setToolbarHidden:");
    @Bridge private native static void objc_setToolbarHidden(UINavigationController __self__, Selector __cmd__, boolean toolbarHidden);
    @Bridge private native static void objc_setToolbarHiddenSuper(ObjCSuper __super__, Selector __cmd__, boolean toolbarHidden);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/toolbarHidden">@property(nonatomic,getter=isToolbarHidden) BOOL toolbarHidden</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setToolbarHidden(boolean toolbarHidden) {
        if (customClass) { objc_setToolbarHiddenSuper(getSuper(), setToolbarHidden$, toolbarHidden); } else { objc_setToolbarHidden(this, setToolbarHidden$, toolbarHidden); }
    }
    
    private static final Selector topViewController = Selector.register("topViewController");
    @Bridge private native static UIViewController objc_getTopViewController(UINavigationController __self__, Selector __cmd__);
    @Bridge private native static UIViewController objc_getTopViewControllerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/topViewController">@property(nonatomic, readonly, retain) UIViewController *topViewController</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIViewController getTopViewController() {
        if (customClass) { return objc_getTopViewControllerSuper(getSuper(), topViewController); } else { return objc_getTopViewController(this, topViewController); }
    }
    
    private static final Selector viewControllers = Selector.register("viewControllers");
    @Bridge private native static NSArray objc_getViewControllers(UINavigationController __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getViewControllersSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/viewControllers">@property(nonatomic, copy) NSArray *viewControllers</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray getViewControllers() {
        if (customClass) { return objc_getViewControllersSuper(getSuper(), viewControllers); } else { return objc_getViewControllers(this, viewControllers); }
    }
    
    private static final Selector setViewControllers$ = Selector.register("setViewControllers:");
    @Bridge private native static void objc_setViewControllers(UINavigationController __self__, Selector __cmd__, NSArray viewControllers);
    @Bridge private native static void objc_setViewControllersSuper(ObjCSuper __super__, Selector __cmd__, NSArray viewControllers);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/viewControllers">@property(nonatomic, copy) NSArray *viewControllers</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setViewControllers(NSArray viewControllers) {
        if (customClass) { objc_setViewControllersSuper(getSuper(), setViewControllers$, viewControllers); } else { objc_setViewControllers(this, setViewControllers$, viewControllers); }
    }
    
    private static final Selector visibleViewController = Selector.register("visibleViewController");
    @Bridge private native static UIViewController objc_getVisibleViewController(UINavigationController __self__, Selector __cmd__);
    @Bridge private native static UIViewController objc_getVisibleViewControllerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instp/UINavigationController/visibleViewController">@property(nonatomic, readonly, retain) UIViewController *visibleViewController</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIViewController getVisibleViewController() {
        if (customClass) { return objc_getVisibleViewControllerSuper(getSuper(), visibleViewController); } else { return objc_getVisibleViewController(this, visibleViewController); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector popToRootViewControllerAnimated$ = Selector.register("popToRootViewControllerAnimated:");
    @Bridge private native static NSArray objc_popToRootViewController(UINavigationController __self__, Selector __cmd__, boolean animated);
    @Bridge private native static NSArray objc_popToRootViewControllerSuper(ObjCSuper __super__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/popToRootViewControllerAnimated:">- (NSArray *)popToRootViewControllerAnimated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray popToRootViewController(boolean animated) {
        if (customClass) { return objc_popToRootViewControllerSuper(getSuper(), popToRootViewControllerAnimated$, animated); } else { return objc_popToRootViewController(this, popToRootViewControllerAnimated$, animated); }
    }
    
    private static final Selector popToViewController$animated$ = Selector.register("popToViewController:animated:");
    @Bridge private native static NSArray objc_popToViewController(UINavigationController __self__, Selector __cmd__, UIViewController viewController, boolean animated);
    @Bridge private native static NSArray objc_popToViewControllerSuper(ObjCSuper __super__, Selector __cmd__, UIViewController viewController, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/popToViewController:animated:">- (NSArray *)popToViewController:(UIViewController *)viewController animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray popToViewController(UIViewController viewController, boolean animated) {
        if (customClass) { return objc_popToViewControllerSuper(getSuper(), popToViewController$animated$, viewController, animated); } else { return objc_popToViewController(this, popToViewController$animated$, viewController, animated); }
    }
    
    private static final Selector popViewControllerAnimated$ = Selector.register("popViewControllerAnimated:");
    @Bridge private native static UIViewController objc_popViewControllerAnimated(UINavigationController __self__, Selector __cmd__, boolean animated);
    @Bridge private native static UIViewController objc_popViewControllerAnimatedSuper(ObjCSuper __super__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/popViewControllerAnimated:">- (UIViewController *)popViewControllerAnimated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIViewController popViewControllerAnimated(boolean animated) {
        if (customClass) { return objc_popViewControllerAnimatedSuper(getSuper(), popViewControllerAnimated$, animated); } else { return objc_popViewControllerAnimated(this, popViewControllerAnimated$, animated); }
    }
    
    private static final Selector pushViewController$animated$ = Selector.register("pushViewController:animated:");
    @Bridge private native static void objc_pushViewController(UINavigationController __self__, Selector __cmd__, UIViewController viewController, boolean animated);
    @Bridge private native static void objc_pushViewControllerSuper(ObjCSuper __super__, Selector __cmd__, UIViewController viewController, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/pushViewController:animated:">- (void)pushViewController:(UIViewController *)viewController animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void pushViewController(UIViewController viewController, boolean animated) {
        if (customClass) { objc_pushViewControllerSuper(getSuper(), pushViewController$animated$, viewController, animated); } else { objc_pushViewController(this, pushViewController$animated$, viewController, animated); }
    }
    
    private static final Selector setNavigationBarHidden$animated$ = Selector.register("setNavigationBarHidden:animated:");
    @Bridge private native static void objc_setNavigationBarHidden(UINavigationController __self__, Selector __cmd__, boolean hidden, boolean animated);
    @Bridge private native static void objc_setNavigationBarHiddenSuper(ObjCSuper __super__, Selector __cmd__, boolean hidden, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/setNavigationBarHidden:animated:">- (void)setNavigationBarHidden:(BOOL)hidden animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setNavigationBarHidden(boolean hidden, boolean animated) {
        if (customClass) { objc_setNavigationBarHiddenSuper(getSuper(), setNavigationBarHidden$animated$, hidden, animated); } else { objc_setNavigationBarHidden(this, setNavigationBarHidden$animated$, hidden, animated); }
    }
    
    private static final Selector setToolbarHidden$animated$ = Selector.register("setToolbarHidden:animated:");
    @Bridge private native static void objc_setToolbarHidden(UINavigationController __self__, Selector __cmd__, boolean hidden, boolean animated);
    @Bridge private native static void objc_setToolbarHiddenSuper(ObjCSuper __super__, Selector __cmd__, boolean hidden, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/setToolbarHidden:animated:">- (void)setToolbarHidden:(BOOL)hidden animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setToolbarHidden(boolean hidden, boolean animated) {
        if (customClass) { objc_setToolbarHiddenSuper(getSuper(), setToolbarHidden$animated$, hidden, animated); } else { objc_setToolbarHidden(this, setToolbarHidden$animated$, hidden, animated); }
    }
    
    private static final Selector setViewControllers$animated$ = Selector.register("setViewControllers:animated:");
    @Bridge private native static void objc_setViewControllers(UINavigationController __self__, Selector __cmd__, NSArray viewControllers, boolean animated);
    @Bridge private native static void objc_setViewControllersSuper(ObjCSuper __super__, Selector __cmd__, NSArray viewControllers, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationController_Class/Reference/Reference.html#//apple_ref/occ/instm/UINavigationController/setViewControllers:animated:">- (void)setViewControllers:(NSArray *)viewControllers animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setViewControllers(NSArray viewControllers, boolean animated) {
        if (customClass) { objc_setViewControllersSuper(getSuper(), setViewControllers$animated$, viewControllers, animated); } else { objc_setViewControllers(this, setViewControllers$animated$, viewControllers, animated); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("delegate") public static UINavigationControllerDelegate getDelegate(UINavigationController __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UINavigationController __self__, Selector __cmd__, UINavigationControllerDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("navigationBar") public static UINavigationBar getNavigationBar(UINavigationController __self__, Selector __cmd__) { return __self__.getNavigationBar(); }
        @Callback @BindSelector("isNavigationBarHidden") public static boolean isNavigationBarHidden(UINavigationController __self__, Selector __cmd__) { return __self__.isNavigationBarHidden(); }
        @Callback @BindSelector("setNavigationBarHidden:") public static void setNavigationBarHidden(UINavigationController __self__, Selector __cmd__, boolean navigationBarHidden) { __self__.setNavigationBarHidden(navigationBarHidden); }
        @Callback @BindSelector("toolbar") public static UIToolbar getToolbar(UINavigationController __self__, Selector __cmd__) { return __self__.getToolbar(); }
        @Callback @BindSelector("isToolbarHidden") public static boolean isToolbarHidden(UINavigationController __self__, Selector __cmd__) { return __self__.isToolbarHidden(); }
        @Callback @BindSelector("setToolbarHidden:") public static void setToolbarHidden(UINavigationController __self__, Selector __cmd__, boolean toolbarHidden) { __self__.setToolbarHidden(toolbarHidden); }
        @Callback @BindSelector("topViewController") public static UIViewController getTopViewController(UINavigationController __self__, Selector __cmd__) { return __self__.getTopViewController(); }
        @Callback @BindSelector("viewControllers") public static NSArray getViewControllers(UINavigationController __self__, Selector __cmd__) { return __self__.getViewControllers(); }
        @Callback @BindSelector("setViewControllers:") public static void setViewControllers(UINavigationController __self__, Selector __cmd__, NSArray viewControllers) { __self__.setViewControllers(viewControllers); }
        @Callback @BindSelector("visibleViewController") public static UIViewController getVisibleViewController(UINavigationController __self__, Selector __cmd__) { return __self__.getVisibleViewController(); }
        @Callback @BindSelector("popToRootViewControllerAnimated:") public static NSArray popToRootViewController(UINavigationController __self__, Selector __cmd__, boolean animated) { return __self__.popToRootViewController(animated); }
        @Callback @BindSelector("popToViewController:animated:") public static NSArray popToViewController(UINavigationController __self__, Selector __cmd__, UIViewController viewController, boolean animated) { return __self__.popToViewController(viewController, animated); }
        @Callback @BindSelector("popViewControllerAnimated:") public static UIViewController popViewControllerAnimated(UINavigationController __self__, Selector __cmd__, boolean animated) { return __self__.popViewControllerAnimated(animated); }
        @Callback @BindSelector("pushViewController:animated:") public static void pushViewController(UINavigationController __self__, Selector __cmd__, UIViewController viewController, boolean animated) { __self__.pushViewController(viewController, animated); }
        @Callback @BindSelector("setNavigationBarHidden:animated:") public static void setNavigationBarHidden(UINavigationController __self__, Selector __cmd__, boolean hidden, boolean animated) { __self__.setNavigationBarHidden(hidden, animated); }
        @Callback @BindSelector("setToolbarHidden:animated:") public static void setToolbarHidden(UINavigationController __self__, Selector __cmd__, boolean hidden, boolean animated) { __self__.setToolbarHidden(hidden, animated); }
        @Callback @BindSelector("setViewControllers:animated:") public static void setViewControllers(UINavigationController __self__, Selector __cmd__, NSArray viewControllers, boolean animated) { __self__.setViewControllers(viewControllers, animated); }
    }
    /*</callbacks>*/

}
