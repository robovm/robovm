/*
 * Copyright (C) 2014 Trillian Mobile AB
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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 3.2 and later.
 */
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
    public UIPopoverController(UIViewController viewController) { super((SkipInit) null); initObject(initWithContentViewController$(viewController)); }
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
    @Property(selector = "backgroundColor")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native UIColor getBackgroundColor();
    @Property(selector = "setBackgroundColor:")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native void setBackgroundColor(UIColor v);
    @Property(selector = "popoverLayoutMargins")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native @ByVal UIEdgeInsets getPopoverLayoutMargins();
    @Property(selector = "setPopoverLayoutMargins:")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native void setPopoverLayoutMargins(@ByVal UIEdgeInsets v);
    @Property(selector = "popoverBackgroundViewClass")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native ObjCClass getPopoverBackgroundViewClass();
    @Property(selector = "setPopoverBackgroundViewClass:")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native void setPopoverBackgroundViewClass(ObjCClass v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithContentViewController:")
    protected native @Pointer long initWithContentViewController$(UIViewController viewController);
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
