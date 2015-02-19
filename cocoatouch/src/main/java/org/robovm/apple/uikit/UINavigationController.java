/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UINavigationController/*</name>*/ 
    extends /*<extends>*/UIViewController/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UINavigationControllerPtr extends Ptr<UINavigationController, UINavigationControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UINavigationController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UINavigationController() {}
    protected UINavigationController(SkipInit skipInit) { super(skipInit); }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public UINavigationController(Class<?> navigationBarClass, Class<?> toolbarClass) { super((SkipInit) null); initObject(init(navigationBarClass, toolbarClass)); }
    public UINavigationController(UIViewController rootViewController) { super((SkipInit) null); initObject(init(rootViewController)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "topViewController")
    public native UIViewController getTopViewController();
    @Property(selector = "visibleViewController")
    public native UIViewController getVisibleViewController();
    @Property(selector = "viewControllers")
    public native NSArray<UIViewController> getViewControllers();
    @Property(selector = "setViewControllers:")
    public native void setViewControllers(NSArray<UIViewController> v);
    @Property(selector = "isNavigationBarHidden")
    public native boolean isNavigationBarHidden();
    @Property(selector = "setNavigationBarHidden:")
    public native void setNavigationBarHidden(boolean v);
    @Property(selector = "navigationBar")
    public native UINavigationBar getNavigationBar();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "isToolbarHidden")
    public native boolean isToolbarHidden();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setToolbarHidden:")
    public native void setToolbarHidden(boolean v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "toolbar")
    public native UIToolbar getToolbar();
    @Property(selector = "delegate")
    public native UINavigationControllerDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UINavigationControllerDelegate v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "interactivePopGestureRecognizer")
    public native UIGestureRecognizer getInteractivePopGestureRecognizer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "hidesBarsWhenKeyboardAppears")
    public native boolean hidesBarsWhenKeyboardAppears();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setHidesBarsWhenKeyboardAppears:")
    public native void setHidesBarsWhenKeyboardAppears(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "hidesBarsOnSwipe")
    public native boolean hidesBarsOnSwipe();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setHidesBarsOnSwipe:")
    public native void setHidesBarsOnSwipe(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "barHideOnSwipeGestureRecognizer")
    public native UIPanGestureRecognizer getBarHideOnSwipeGestureRecognizer();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "hidesBarsWhenVerticallyCompact")
    public native boolean hidesBarsWhenVerticallyCompact();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setHidesBarsWhenVerticallyCompact:")
    public native void setHidesBarsWhenVerticallyCompact(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "hidesBarsOnTap")
    public native boolean hidesBarsOnTap();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setHidesBarsOnTap:")
    public native void setHidesBarsOnTap(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "barHideOnTapGestureRecognizer")
    public native UITapGestureRecognizer getBarHideOnTapGestureRecognizer();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="UINavigationControllerHideShowBarDuration", optional=true)
    public static native @MachineSizedFloat double getHideShowBarDuration();
    
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "initWithNavigationBarClass:toolbarClass:")
    protected native @Pointer long init(Class<?> navigationBarClass, Class<?> toolbarClass);
    @Method(selector = "initWithRootViewController:")
    protected native @Pointer long init(UIViewController rootViewController);
    @Method(selector = "pushViewController:animated:")
    public native void pushViewController(UIViewController viewController, boolean animated);
    @Method(selector = "popViewControllerAnimated:")
    public native UIViewController popViewController(boolean animated);
    @Method(selector = "popToViewController:animated:")
    public native NSArray<UIViewController> popToViewController(UIViewController viewController, boolean animated);
    @Method(selector = "popToRootViewControllerAnimated:")
    public native NSArray<UIViewController> popToRootViewController(boolean animated);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setViewControllers:animated:")
    public native void setViewControllers(NSArray<UIViewController> viewControllers, boolean animated);
    @Method(selector = "setNavigationBarHidden:animated:")
    public native void setNavigationBarHidden(boolean hidden, boolean animated);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setToolbarHidden:animated:")
    public native void setToolbarHidden(boolean hidden, boolean animated);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "showViewController:sender:")
    public native void showViewController(UIViewController vc, NSObject sender);
    /*</methods>*/
}
