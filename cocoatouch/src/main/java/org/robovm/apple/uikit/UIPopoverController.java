/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.apple.uikit;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 3.2 and later.
 * @deprecated Deprecated in iOS 9.0.
 */
@Deprecated
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIPopoverController/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UIAppearanceContainer/*</implements>*/ {

    /*<ptr>*/public static class UIPopoverControllerPtr extends Ptr<UIPopoverController, UIPopoverControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIPopoverController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIPopoverController() {}
    protected UIPopoverController(SkipInit skipInit) { super(skipInit); }
    public UIPopoverController(UIViewController viewController) { super((SkipInit) null); initObject(init(viewController)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native UIPopoverControllerDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UIPopoverControllerDelegate v);
    @Property(selector = "contentViewController")
    public native UIViewController getContentViewController();
    @Property(selector = "setContentViewController:")
    public native void setContentViewController(UIViewController v);
    @Property(selector = "popoverContentSize")
    public native @ByVal CGSize getPopoverContentSize();
    @Property(selector = "setPopoverContentSize:")
    public native void setPopoverContentSize(@ByVal CGSize v);
    @Property(selector = "isPopoverVisible")
    public native boolean isPopoverVisible();
    @Property(selector = "popoverArrowDirection")
    public native UIPopoverArrowDirection getPopoverArrowDirection();
    @Property(selector = "passthroughViews")
    public native NSArray<UIView> getPassthroughViews();
    @Property(selector = "setPassthroughViews:")
    public native void setPassthroughViews(NSArray<UIView> v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "backgroundColor")
    public native UIColor getBackgroundColor();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setBackgroundColor:")
    public native void setBackgroundColor(UIColor v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "popoverLayoutMargins")
    public native @ByVal UIEdgeInsets getPopoverLayoutMargins();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setPopoverLayoutMargins:")
    public native void setPopoverLayoutMargins(@ByVal UIEdgeInsets v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "popoverBackgroundViewClass")
    public native Class<? extends UIPopoverBackgroundView> getPopoverBackgroundViewClass();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setPopoverBackgroundViewClass:")
    public native void setPopoverBackgroundViewClass(Class<? extends UIPopoverBackgroundView> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithContentViewController:")
    protected native @Pointer long init(UIViewController viewController);
    @Method(selector = "setContentViewController:animated:")
    public native void setContentViewController(UIViewController viewController, boolean animated);
    @Method(selector = "setPopoverContentSize:animated:")
    public native void setPopoverContentSize(@ByVal CGSize size, boolean animated);
    @Method(selector = "presentPopoverFromRect:inView:permittedArrowDirections:animated:")
    public native void presentFromRectInView(@ByVal CGRect rect, UIView view, UIPopoverArrowDirection arrowDirections, boolean animated);
    @Method(selector = "presentPopoverFromBarButtonItem:permittedArrowDirections:animated:")
    public native void presentFromBarButtonItem(UIBarButtonItem item, UIPopoverArrowDirection arrowDirections, boolean animated);
    @Method(selector = "dismissPopoverAnimated:")
    public native void dismiss(boolean animated);
    /*</methods>*/
}
