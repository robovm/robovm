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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html">UIPopoverController Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIPopoverController /*</name>*/ 
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
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithContentViewController(UIPopoverController __self__, Selector __cmd__, UIViewController viewController);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instm/UIPopoverController/initWithContentViewController:">- (id)initWithContentViewController:(UIViewController *)viewController</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIPopoverController(UIViewController viewController) {
        super((SkipInit) null);
        objc_initWithContentViewController(this, initWithContentViewController$, viewController);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/contentViewController">@property (nonatomic, retain) UIViewController *contentViewController</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("contentViewController") public native UIViewController getContentViewController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/contentViewController">@property (nonatomic, retain) UIViewController *contentViewController</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setContentViewController:") public native void setContentViewController(UIViewController v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/delegate">@property (nonatomic, assign) id &amp;lt;UIPopoverControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("delegate") public native UIPopoverControllerDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/delegate">@property (nonatomic, assign) id &amp;lt;UIPopoverControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(UIPopoverControllerDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/passthroughViews">@property (nonatomic, copy) NSArray *passthroughViews</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("passthroughViews") public native NSArray getPassthroughViews();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/passthroughViews">@property (nonatomic, copy) NSArray *passthroughViews</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setPassthroughViews:") public native void setPassthroughViews(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverArrowDirection">@property (nonatomic, readonly) UIPopoverArrowDirection popoverArrowDirection</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("popoverArrowDirection") public native UIPopoverArrowDirection getPopoverArrowDirection();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverBackgroundViewClass">@property (nonatomic, readwrite, retain) Class popoverBackgroundViewClass</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("popoverBackgroundViewClass") public native ObjCClass getPopoverBackgroundViewClass();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverBackgroundViewClass">@property (nonatomic, readwrite, retain) Class popoverBackgroundViewClass</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setPopoverBackgroundViewClass:") public native void setPopoverBackgroundViewClass(ObjCClass v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverContentSize">@property (nonatomic) CGSize popoverContentSize</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("popoverContentSize") public native CGSize getPopoverContentSize();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverContentSize">@property (nonatomic) CGSize popoverContentSize</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setPopoverContentSize:") public native void setPopoverContentSize(CGSize v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverLayoutMargins">@property (nonatomic, readwrite) UIEdgeInsets popoverLayoutMargins</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("popoverLayoutMargins") public native UIEdgeInsets getPopoverLayoutMargins();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverLayoutMargins">@property (nonatomic, readwrite) UIEdgeInsets popoverLayoutMargins</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setPopoverLayoutMargins:") public native void setPopoverLayoutMargins(UIEdgeInsets v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverVisible">@property (nonatomic, readonly, getter=isPopoverVisible) BOOL popoverVisible</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("isPopoverVisible") public native boolean isPopoverVisible();
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector dismissPopoverAnimated$ = Selector.register("dismissPopoverAnimated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_dismiss(UIPopoverController __self__, Selector __cmd__, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_dismissSuper(ObjCSuper __super__, UIPopoverController __self__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instm/UIPopoverController/dismissPopoverAnimated:">- (void)dismissPopoverAnimated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public void dismiss(boolean animated) {
        if (customClass) { objc_dismissSuper(getSuper(), this, dismissPopoverAnimated$, animated); } else { objc_dismiss(this, dismissPopoverAnimated$, animated); }
    }
    
    private static final Selector presentPopoverFromBarButtonItem$permittedArrowDirections$animated$ = Selector.register("presentPopoverFromBarButtonItem:permittedArrowDirections:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_presentFromBarButtonItem(UIPopoverController __self__, Selector __cmd__, UIBarButtonItem item, EnumSet<UIPopoverArrowDirection> arrowDirections, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_presentFromBarButtonItemSuper(ObjCSuper __super__, UIPopoverController __self__, Selector __cmd__, UIBarButtonItem item, EnumSet<UIPopoverArrowDirection> arrowDirections, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instm/UIPopoverController/presentPopoverFromBarButtonItem:permittedArrowDirections:animated:">- (void)presentPopoverFromBarButtonItem:(UIBarButtonItem *)item permittedArrowDirections:(UIPopoverArrowDirection)arrowDirections animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public void presentFromBarButtonItem(UIBarButtonItem item, EnumSet<UIPopoverArrowDirection> arrowDirections, boolean animated) {
        if (customClass) { objc_presentFromBarButtonItemSuper(getSuper(), this, presentPopoverFromBarButtonItem$permittedArrowDirections$animated$, item, arrowDirections, animated); } else { objc_presentFromBarButtonItem(this, presentPopoverFromBarButtonItem$permittedArrowDirections$animated$, item, arrowDirections, animated); }
    }
    
    private static final Selector presentPopoverFromRect$inView$permittedArrowDirections$animated$ = Selector.register("presentPopoverFromRect:inView:permittedArrowDirections:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_presentFromRectInView(UIPopoverController __self__, Selector __cmd__, CGRect rect, UIView view, EnumSet<UIPopoverArrowDirection> arrowDirections, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_presentFromRectInViewSuper(ObjCSuper __super__, UIPopoverController __self__, Selector __cmd__, CGRect rect, UIView view, EnumSet<UIPopoverArrowDirection> arrowDirections, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instm/UIPopoverController/presentPopoverFromRect:inView:permittedArrowDirections:animated:">- (void)presentPopoverFromRect:(CGRect)rect inView:(UIView *)view permittedArrowDirections:(UIPopoverArrowDirection)arrowDirections animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public void presentFromRectInView(CGRect rect, UIView view, EnumSet<UIPopoverArrowDirection> arrowDirections, boolean animated) {
        if (customClass) { objc_presentFromRectInViewSuper(getSuper(), this, presentPopoverFromRect$inView$permittedArrowDirections$animated$, rect, view, arrowDirections, animated); } else { objc_presentFromRectInView(this, presentPopoverFromRect$inView$permittedArrowDirections$animated$, rect, view, arrowDirections, animated); }
    }
    
    private static final Selector setContentViewController$animated$ = Selector.register("setContentViewController:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setContentViewController(UIPopoverController __self__, Selector __cmd__, UIViewController viewController, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setContentViewControllerSuper(ObjCSuper __super__, UIPopoverController __self__, Selector __cmd__, UIViewController viewController, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instm/UIPopoverController/setContentViewController:animated:">- (void)setContentViewController:(UIViewController *)viewController animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setContentViewController(UIViewController viewController, boolean animated) {
        if (customClass) { objc_setContentViewControllerSuper(getSuper(), this, setContentViewController$animated$, viewController, animated); } else { objc_setContentViewController(this, setContentViewController$animated$, viewController, animated); }
    }
    
    private static final Selector setPopoverContentSize$animated$ = Selector.register("setPopoverContentSize:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setPopoverContentSize(UIPopoverController __self__, Selector __cmd__, CGSize size, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setPopoverContentSizeSuper(ObjCSuper __super__, UIPopoverController __self__, Selector __cmd__, CGSize size, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instm/UIPopoverController/setPopoverContentSize:animated:">- (void)setPopoverContentSize:(CGSize)size animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setPopoverContentSize(CGSize size, boolean animated) {
        if (customClass) { objc_setPopoverContentSizeSuper(getSuper(), this, setPopoverContentSize$animated$, size, animated); } else { objc_setPopoverContentSize(this, setPopoverContentSize$animated$, size, animated); }
    }
    /*</methods>*/

}
