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

    /*<constructors>*/
    public UIPopoverController() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instm/UIPopoverController/initWithContentViewController:">- (id)initWithContentViewController:(UIViewController *)viewController</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("initWithContentViewController:") public UIPopoverController(@Type("UIViewController *") UIViewController viewController) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/contentViewController">@property (nonatomic, retain) UIViewController *contentViewController</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("contentViewController") public native @Type("UIViewController *") UIViewController getContentViewController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/contentViewController">@property (nonatomic, retain) UIViewController *contentViewController</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setContentViewController:") public native void setContentViewController(@Type("UIViewController *") UIViewController v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/delegate">@property (nonatomic, assign) id &amp;lt;UIPopoverControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("delegate") public native @Type("id <UIPopoverControllerDelegate>") UIPopoverControllerDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/delegate">@property (nonatomic, assign) id &amp;lt;UIPopoverControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id <UIPopoverControllerDelegate>") UIPopoverControllerDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/passthroughViews">@property (nonatomic, copy) NSArray *passthroughViews</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("passthroughViews") public native @Type("NSArray *") NSArray getPassthroughViews();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/passthroughViews">@property (nonatomic, copy) NSArray *passthroughViews</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setPassthroughViews:") public native void setPassthroughViews(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverArrowDirection">@property (nonatomic, readonly) UIPopoverArrowDirection popoverArrowDirection</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("popoverArrowDirection") public native @Type("UIPopoverArrowDirection") UIPopoverArrowDirection getPopoverArrowDirection();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverBackgroundViewClass">@property (nonatomic, readwrite, retain) Class popoverBackgroundViewClass</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("popoverBackgroundViewClass") public native @Type("Class") ObjCClass getPopoverBackgroundViewClass();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverBackgroundViewClass">@property (nonatomic, readwrite, retain) Class popoverBackgroundViewClass</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setPopoverBackgroundViewClass:") public native void setPopoverBackgroundViewClass(@Type("Class") ObjCClass v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverContentSize">@property (nonatomic) CGSize popoverContentSize</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("popoverContentSize") public native @Type("CGSize") CGSize getPopoverContentSize();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverContentSize">@property (nonatomic) CGSize popoverContentSize</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setPopoverContentSize:") public native void setPopoverContentSize(@Type("CGSize") CGSize v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverLayoutMargins">@property (nonatomic, readwrite) UIEdgeInsets popoverLayoutMargins</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("popoverLayoutMargins") public native @Type("UIEdgeInsets") UIEdgeInsets getPopoverLayoutMargins();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverLayoutMargins">@property (nonatomic, readwrite) UIEdgeInsets popoverLayoutMargins</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setPopoverLayoutMargins:") public native void setPopoverLayoutMargins(@Type("UIEdgeInsets") UIEdgeInsets v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instp/UIPopoverController/popoverVisible">@property (nonatomic, readonly, getter=isPopoverVisible) BOOL popoverVisible</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("isPopoverVisible") public native @Type("BOOL") boolean isPopoverVisible();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instm/UIPopoverController/dismissPopoverAnimated:">- (void)dismissPopoverAnimated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("dismissPopoverAnimated:") public native @Type("void") void dismiss(@Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instm/UIPopoverController/presentPopoverFromBarButtonItem:permittedArrowDirections:animated:">- (void)presentPopoverFromBarButtonItem:(UIBarButtonItem *)item permittedArrowDirections:(UIPopoverArrowDirection)arrowDirections animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("presentPopoverFromBarButtonItem:permittedArrowDirections:animated:") public native @Type("void") void presentFromBarButtonItem(@Type("UIBarButtonItem *") UIBarButtonItem item, @Type("UIPopoverArrowDirection") EnumSet<UIPopoverArrowDirection> arrowDirections, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instm/UIPopoverController/presentPopoverFromRect:inView:permittedArrowDirections:animated:">- (void)presentPopoverFromRect:(CGRect)rect inView:(UIView *)view permittedArrowDirections:(UIPopoverArrowDirection)arrowDirections animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("presentPopoverFromRect:inView:permittedArrowDirections:animated:") public native @Type("void") void presentFromRectInView(@Type("CGRect") CGRect rect, @Type("UIView *") UIView view, @Type("UIPopoverArrowDirection") EnumSet<UIPopoverArrowDirection> arrowDirections, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instm/UIPopoverController/setContentViewController:animated:">- (void)setContentViewController:(UIViewController *)viewController animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setContentViewController:animated:") public native @Type("void") void setContentViewController(@Type("UIViewController *") UIViewController viewController, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPopoverController_class/Reference/Reference.html#//apple_ref/occ/instm/UIPopoverController/setPopoverContentSize:animated:">- (void)setPopoverContentSize:(CGSize)size animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setPopoverContentSize:animated:") public native @Type("void") void setPopoverContentSize(@Type("CGSize") CGSize size, @Type("BOOL") boolean animated);
    /*</methods>*/

}
