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
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIPageViewController/*</name>*/ 
    extends /*<extends>*/UIViewController/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIPageViewControllerPtr extends Ptr<UIPageViewController, UIPageViewControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIPageViewController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIPageViewController() {}
    protected UIPageViewController(SkipInit skipInit) { super(skipInit); }
    public UIPageViewController(UIPageViewControllerTransitionStyle style, UIPageViewControllerNavigationOrientation navigationOrientation, UIPageViewControllerOptions options) { super((SkipInit) null); initObject(init(style, navigationOrientation, options)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native UIPageViewControllerDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UIPageViewControllerDelegate v);
    @Property(selector = "dataSource")
    public native UIPageViewControllerDataSource getDataSource();
    @Property(selector = "setDataSource:", strongRef = true)
    public native void setDataSource(UIPageViewControllerDataSource v);
    @Property(selector = "transitionStyle")
    public native UIPageViewControllerTransitionStyle getTransitionStyle();
    @Property(selector = "navigationOrientation")
    public native UIPageViewControllerNavigationOrientation getNavigationOrientation();
    @Property(selector = "spineLocation")
    public native UIPageViewControllerSpineLocation getSpineLocation();
    @Property(selector = "isDoubleSided")
    public native boolean isDoubleSided();
    @Property(selector = "setDoubleSided:")
    public native void setDoubleSided(boolean v);
    @Property(selector = "gestureRecognizers")
    public native NSArray<UIGestureRecognizer> getGestureRecognizers();
    @Property(selector = "viewControllers")
    public native NSArray<UIViewController> getViewControllers();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithTransitionStyle:navigationOrientation:options:")
    protected native @Pointer long init(UIPageViewControllerTransitionStyle style, UIPageViewControllerNavigationOrientation navigationOrientation, UIPageViewControllerOptions options);
    @Method(selector = "setViewControllers:direction:animated:completion:")
    public native void setViewControllers(NSArray<UIViewController> viewControllers, UIPageViewControllerNavigationDirection direction, boolean animated, @Block VoidBooleanBlock completion);
    /*</methods>*/
}
