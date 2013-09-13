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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html">UIPopoverController Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIPopoverController /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ implements UIAppearanceContainer /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPopoverController /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIPopoverController /*</name>*/.class);

    /*<constructors>*/
    protected UIPopoverController(SkipInit skipInit) { super(skipInit); }
    public UIPopoverController() {}
    
    private static final Selector initWithContentViewController$ = Selector.register("initWithContentViewController:");
    @Bridge private native static @Pointer long objc_initWithContentViewController(UIPopoverController __self__, Selector __cmd__, UIViewController viewController);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instm/UIPopoverController/initWithContentViewController:">- (id)initWithContentViewController:(UIViewController *)viewController</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIPopoverController(UIViewController viewController) {
        super((SkipInit) null);
        initObject(objc_initWithContentViewController(this, initWithContentViewController$, viewController));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector contentViewController = Selector.register("contentViewController");
    @Bridge private native static UIViewController objc_getContentViewController(UIPopoverController __self__, Selector __cmd__);
    @Bridge private native static UIViewController objc_getContentViewControllerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/contentViewController">@property (nonatomic, retain) UIViewController *contentViewController</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIViewController getContentViewController() {
        if (customClass) { return objc_getContentViewControllerSuper(getSuper(), contentViewController); } else { return objc_getContentViewController(this, contentViewController); }
    }
    
    private static final Selector setContentViewController$ = Selector.register("setContentViewController:");
    @Bridge private native static void objc_setContentViewController(UIPopoverController __self__, Selector __cmd__, UIViewController contentViewController);
    @Bridge private native static void objc_setContentViewControllerSuper(ObjCSuper __super__, Selector __cmd__, UIViewController contentViewController);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/contentViewController">@property (nonatomic, retain) UIViewController *contentViewController</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setContentViewController(UIViewController contentViewController) {
        if (customClass) { objc_setContentViewControllerSuper(getSuper(), setContentViewController$, contentViewController); } else { objc_setContentViewController(this, setContentViewController$, contentViewController); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UIPopoverControllerDelegate objc_getDelegate(UIPopoverController __self__, Selector __cmd__);
    @Bridge private native static UIPopoverControllerDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/delegate">@property (nonatomic, assign) id &amp;lt;UIPopoverControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIPopoverControllerDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UIPopoverController __self__, Selector __cmd__, UIPopoverControllerDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UIPopoverControllerDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/delegate">@property (nonatomic, assign) id &amp;lt;UIPopoverControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setDelegate(UIPopoverControllerDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector passthroughViews = Selector.register("passthroughViews");
    @Bridge private native static NSArray objc_getPassthroughViews(UIPopoverController __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getPassthroughViewsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/passthroughViews">@property (nonatomic, copy) NSArray *passthroughViews</a>
     * @since Available in iOS 3.2 and later.
     */
    public NSArray getPassthroughViews() {
        if (customClass) { return objc_getPassthroughViewsSuper(getSuper(), passthroughViews); } else { return objc_getPassthroughViews(this, passthroughViews); }
    }
    
    private static final Selector setPassthroughViews$ = Selector.register("setPassthroughViews:");
    @Bridge private native static void objc_setPassthroughViews(UIPopoverController __self__, Selector __cmd__, NSArray passthroughViews);
    @Bridge private native static void objc_setPassthroughViewsSuper(ObjCSuper __super__, Selector __cmd__, NSArray passthroughViews);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/passthroughViews">@property (nonatomic, copy) NSArray *passthroughViews</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setPassthroughViews(NSArray passthroughViews) {
        if (customClass) { objc_setPassthroughViewsSuper(getSuper(), setPassthroughViews$, passthroughViews); } else { objc_setPassthroughViews(this, setPassthroughViews$, passthroughViews); }
    }
    
    private static final Selector popoverArrowDirection = Selector.register("popoverArrowDirection");
    @Bridge private native static UIPopoverArrowDirection objc_getPopoverArrowDirection(UIPopoverController __self__, Selector __cmd__);
    @Bridge private native static UIPopoverArrowDirection objc_getPopoverArrowDirectionSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverArrowDirection">@property (nonatomic, readonly) UIPopoverArrowDirection popoverArrowDirection</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIPopoverArrowDirection getPopoverArrowDirection() {
        if (customClass) { return objc_getPopoverArrowDirectionSuper(getSuper(), popoverArrowDirection); } else { return objc_getPopoverArrowDirection(this, popoverArrowDirection); }
    }
    
    private static final Selector popoverBackgroundViewClass = Selector.register("popoverBackgroundViewClass");
    @Bridge private native static ObjCClass objc_getPopoverBackgroundViewClass(UIPopoverController __self__, Selector __cmd__);
    @Bridge private native static ObjCClass objc_getPopoverBackgroundViewClassSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverBackgroundViewClass">@property (nonatomic, readwrite, retain) Class popoverBackgroundViewClass</a>
     * @since Available in iOS 5.0 and later.
     */
    public ObjCClass getPopoverBackgroundViewClass() {
        if (customClass) { return objc_getPopoverBackgroundViewClassSuper(getSuper(), popoverBackgroundViewClass); } else { return objc_getPopoverBackgroundViewClass(this, popoverBackgroundViewClass); }
    }
    
    private static final Selector setPopoverBackgroundViewClass$ = Selector.register("setPopoverBackgroundViewClass:");
    @Bridge private native static void objc_setPopoverBackgroundViewClass(UIPopoverController __self__, Selector __cmd__, ObjCClass popoverBackgroundViewClass);
    @Bridge private native static void objc_setPopoverBackgroundViewClassSuper(ObjCSuper __super__, Selector __cmd__, ObjCClass popoverBackgroundViewClass);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverBackgroundViewClass">@property (nonatomic, readwrite, retain) Class popoverBackgroundViewClass</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setPopoverBackgroundViewClass(ObjCClass popoverBackgroundViewClass) {
        if (customClass) { objc_setPopoverBackgroundViewClassSuper(getSuper(), setPopoverBackgroundViewClass$, popoverBackgroundViewClass); } else { objc_setPopoverBackgroundViewClass(this, setPopoverBackgroundViewClass$, popoverBackgroundViewClass); }
    }
    
    private static final Selector popoverContentSize = Selector.register("popoverContentSize");
    @Bridge private native static @ByVal CGSize objc_getPopoverContentSize(UIPopoverController __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGSize objc_getPopoverContentSizeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverContentSize">@property (nonatomic) CGSize popoverContentSize</a>
     * @since Available in iOS 3.2 and later.
     */
    public CGSize getPopoverContentSize() {
        if (customClass) { return objc_getPopoverContentSizeSuper(getSuper(), popoverContentSize); } else { return objc_getPopoverContentSize(this, popoverContentSize); }
    }
    
    private static final Selector setPopoverContentSize$ = Selector.register("setPopoverContentSize:");
    @Bridge private native static void objc_setPopoverContentSize(UIPopoverController __self__, Selector __cmd__, @ByVal CGSize popoverContentSize);
    @Bridge private native static void objc_setPopoverContentSizeSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGSize popoverContentSize);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverContentSize">@property (nonatomic) CGSize popoverContentSize</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setPopoverContentSize(CGSize popoverContentSize) {
        if (customClass) { objc_setPopoverContentSizeSuper(getSuper(), setPopoverContentSize$, popoverContentSize); } else { objc_setPopoverContentSize(this, setPopoverContentSize$, popoverContentSize); }
    }
    
    private static final Selector popoverLayoutMargins = Selector.register("popoverLayoutMargins");
    @Bridge private native static @ByVal UIEdgeInsets objc_getPopoverLayoutMargins(UIPopoverController __self__, Selector __cmd__);
    @Bridge private native static @ByVal UIEdgeInsets objc_getPopoverLayoutMarginsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverLayoutMargins">@property (nonatomic, readwrite) UIEdgeInsets popoverLayoutMargins</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIEdgeInsets getPopoverLayoutMargins() {
        if (customClass) { return objc_getPopoverLayoutMarginsSuper(getSuper(), popoverLayoutMargins); } else { return objc_getPopoverLayoutMargins(this, popoverLayoutMargins); }
    }
    
    private static final Selector setPopoverLayoutMargins$ = Selector.register("setPopoverLayoutMargins:");
    @Bridge private native static void objc_setPopoverLayoutMargins(UIPopoverController __self__, Selector __cmd__, @ByVal UIEdgeInsets popoverLayoutMargins);
    @Bridge private native static void objc_setPopoverLayoutMarginsSuper(ObjCSuper __super__, Selector __cmd__, @ByVal UIEdgeInsets popoverLayoutMargins);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverLayoutMargins">@property (nonatomic, readwrite) UIEdgeInsets popoverLayoutMargins</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setPopoverLayoutMargins(UIEdgeInsets popoverLayoutMargins) {
        if (customClass) { objc_setPopoverLayoutMarginsSuper(getSuper(), setPopoverLayoutMargins$, popoverLayoutMargins); } else { objc_setPopoverLayoutMargins(this, setPopoverLayoutMargins$, popoverLayoutMargins); }
    }
    
    private static final Selector isPopoverVisible = Selector.register("isPopoverVisible");
    @Bridge private native static boolean objc_isPopoverVisible(UIPopoverController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isPopoverVisibleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverVisible">@property (nonatomic, readonly, getter=isPopoverVisible) BOOL popoverVisible</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean isPopoverVisible() {
        if (customClass) { return objc_isPopoverVisibleSuper(getSuper(), isPopoverVisible); } else { return objc_isPopoverVisible(this, isPopoverVisible); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector dismissPopoverAnimated$ = Selector.register("dismissPopoverAnimated:");
    @Bridge private native static void objc_dismiss(UIPopoverController __self__, Selector __cmd__, boolean animated);
    @Bridge private native static void objc_dismissSuper(ObjCSuper __super__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instm/UIPopoverController/dismissPopoverAnimated:">- (void)dismissPopoverAnimated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public void dismiss(boolean animated) {
        if (customClass) { objc_dismissSuper(getSuper(), dismissPopoverAnimated$, animated); } else { objc_dismiss(this, dismissPopoverAnimated$, animated); }
    }
    
    private static final Selector presentPopoverFromBarButtonItem$permittedArrowDirections$animated$ = Selector.register("presentPopoverFromBarButtonItem:permittedArrowDirections:animated:");
    @Bridge private native static void objc_presentFromBarButtonItem(UIPopoverController __self__, Selector __cmd__, UIBarButtonItem item, UIPopoverArrowDirection arrowDirections, boolean animated);
    @Bridge private native static void objc_presentFromBarButtonItemSuper(ObjCSuper __super__, Selector __cmd__, UIBarButtonItem item, UIPopoverArrowDirection arrowDirections, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instm/UIPopoverController/presentPopoverFromBarButtonItem:permittedArrowDirections:animated:">- (void)presentPopoverFromBarButtonItem:(UIBarButtonItem *)item permittedArrowDirections:(UIPopoverArrowDirection)arrowDirections animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public void presentFromBarButtonItem(UIBarButtonItem item, UIPopoverArrowDirection arrowDirections, boolean animated) {
        if (customClass) { objc_presentFromBarButtonItemSuper(getSuper(), presentPopoverFromBarButtonItem$permittedArrowDirections$animated$, item, arrowDirections, animated); } else { objc_presentFromBarButtonItem(this, presentPopoverFromBarButtonItem$permittedArrowDirections$animated$, item, arrowDirections, animated); }
    }
    
    private static final Selector presentPopoverFromRect$inView$permittedArrowDirections$animated$ = Selector.register("presentPopoverFromRect:inView:permittedArrowDirections:animated:");
    @Bridge private native static void objc_presentFromRectInView(UIPopoverController __self__, Selector __cmd__, @ByVal CGRect rect, UIView view, UIPopoverArrowDirection arrowDirections, boolean animated);
    @Bridge private native static void objc_presentFromRectInViewSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect rect, UIView view, UIPopoverArrowDirection arrowDirections, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instm/UIPopoverController/presentPopoverFromRect:inView:permittedArrowDirections:animated:">- (void)presentPopoverFromRect:(CGRect)rect inView:(UIView *)view permittedArrowDirections:(UIPopoverArrowDirection)arrowDirections animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public void presentFromRectInView(CGRect rect, UIView view, UIPopoverArrowDirection arrowDirections, boolean animated) {
        if (customClass) { objc_presentFromRectInViewSuper(getSuper(), presentPopoverFromRect$inView$permittedArrowDirections$animated$, rect, view, arrowDirections, animated); } else { objc_presentFromRectInView(this, presentPopoverFromRect$inView$permittedArrowDirections$animated$, rect, view, arrowDirections, animated); }
    }
    
    private static final Selector setContentViewController$animated$ = Selector.register("setContentViewController:animated:");
    @Bridge private native static void objc_setContentViewController(UIPopoverController __self__, Selector __cmd__, UIViewController viewController, boolean animated);
    @Bridge private native static void objc_setContentViewControllerSuper(ObjCSuper __super__, Selector __cmd__, UIViewController viewController, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instm/UIPopoverController/setContentViewController:animated:">- (void)setContentViewController:(UIViewController *)viewController animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setContentViewController(UIViewController viewController, boolean animated) {
        if (customClass) { objc_setContentViewControllerSuper(getSuper(), setContentViewController$animated$, viewController, animated); } else { objc_setContentViewController(this, setContentViewController$animated$, viewController, animated); }
    }
    
    private static final Selector setPopoverContentSize$animated$ = Selector.register("setPopoverContentSize:animated:");
    @Bridge private native static void objc_setPopoverContentSize(UIPopoverController __self__, Selector __cmd__, @ByVal CGSize size, boolean animated);
    @Bridge private native static void objc_setPopoverContentSizeSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGSize size, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instm/UIPopoverController/setPopoverContentSize:animated:">- (void)setPopoverContentSize:(CGSize)size animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setPopoverContentSize(CGSize size, boolean animated) {
        if (customClass) { objc_setPopoverContentSizeSuper(getSuper(), setPopoverContentSize$animated$, size, animated); } else { objc_setPopoverContentSize(this, setPopoverContentSize$animated$, size, animated); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("contentViewController") public static UIViewController getContentViewController(UIPopoverController __self__, Selector __cmd__) { return __self__.getContentViewController(); }
        @Callback @BindSelector("setContentViewController:") public static void setContentViewController(UIPopoverController __self__, Selector __cmd__, UIViewController contentViewController) { __self__.setContentViewController(contentViewController); }
        @Callback @BindSelector("delegate") public static UIPopoverControllerDelegate getDelegate(UIPopoverController __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UIPopoverController __self__, Selector __cmd__, UIPopoverControllerDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("passthroughViews") public static NSArray getPassthroughViews(UIPopoverController __self__, Selector __cmd__) { return __self__.getPassthroughViews(); }
        @Callback @BindSelector("setPassthroughViews:") public static void setPassthroughViews(UIPopoverController __self__, Selector __cmd__, NSArray passthroughViews) { __self__.setPassthroughViews(passthroughViews); }
        @Callback @BindSelector("popoverArrowDirection") public static UIPopoverArrowDirection getPopoverArrowDirection(UIPopoverController __self__, Selector __cmd__) { return __self__.getPopoverArrowDirection(); }
        @Callback @BindSelector("popoverBackgroundViewClass") public static ObjCClass getPopoverBackgroundViewClass(UIPopoverController __self__, Selector __cmd__) { return __self__.getPopoverBackgroundViewClass(); }
        @Callback @BindSelector("setPopoverBackgroundViewClass:") public static void setPopoverBackgroundViewClass(UIPopoverController __self__, Selector __cmd__, ObjCClass popoverBackgroundViewClass) { __self__.setPopoverBackgroundViewClass(popoverBackgroundViewClass); }
        @Callback @BindSelector("popoverContentSize") public static @ByVal CGSize getPopoverContentSize(UIPopoverController __self__, Selector __cmd__) { return __self__.getPopoverContentSize(); }
        @Callback @BindSelector("setPopoverContentSize:") public static void setPopoverContentSize(UIPopoverController __self__, Selector __cmd__, @ByVal CGSize popoverContentSize) { __self__.setPopoverContentSize(popoverContentSize); }
        @Callback @BindSelector("popoverLayoutMargins") public static @ByVal UIEdgeInsets getPopoverLayoutMargins(UIPopoverController __self__, Selector __cmd__) { return __self__.getPopoverLayoutMargins(); }
        @Callback @BindSelector("setPopoverLayoutMargins:") public static void setPopoverLayoutMargins(UIPopoverController __self__, Selector __cmd__, @ByVal UIEdgeInsets popoverLayoutMargins) { __self__.setPopoverLayoutMargins(popoverLayoutMargins); }
        @Callback @BindSelector("isPopoverVisible") public static boolean isPopoverVisible(UIPopoverController __self__, Selector __cmd__) { return __self__.isPopoverVisible(); }
        @Callback @BindSelector("dismissPopoverAnimated:") public static void dismiss(UIPopoverController __self__, Selector __cmd__, boolean animated) { __self__.dismiss(animated); }
        @Callback @BindSelector("presentPopoverFromBarButtonItem:permittedArrowDirections:animated:") public static void presentFromBarButtonItem(UIPopoverController __self__, Selector __cmd__, UIBarButtonItem item, UIPopoverArrowDirection arrowDirections, boolean animated) { __self__.presentFromBarButtonItem(item, arrowDirections, animated); }
        @Callback @BindSelector("presentPopoverFromRect:inView:permittedArrowDirections:animated:") public static void presentFromRectInView(UIPopoverController __self__, Selector __cmd__, @ByVal CGRect rect, UIView view, UIPopoverArrowDirection arrowDirections, boolean animated) { __self__.presentFromRectInView(rect, view, arrowDirections, animated); }
        @Callback @BindSelector("setContentViewController:animated:") public static void setContentViewController(UIPopoverController __self__, Selector __cmd__, UIViewController viewController, boolean animated) { __self__.setContentViewController(viewController, animated); }
        @Callback @BindSelector("setPopoverContentSize:animated:") public static void setPopoverContentSize(UIPopoverController __self__, Selector __cmd__, @ByVal CGSize size, boolean animated) { __self__.setPopoverContentSize(size, animated); }
    }
    /*</callbacks>*/

}
