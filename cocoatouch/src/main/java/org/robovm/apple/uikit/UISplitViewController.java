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
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 3.2 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UISplitViewController/*</name>*/ 
    extends /*<extends>*/UIViewController/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UISplitViewControllerPtr extends Ptr<UISplitViewController, UISplitViewControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UISplitViewController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UISplitViewController() {}
    protected UISplitViewController(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "viewControllers")
    public native NSArray<UIViewController> getViewControllers();
    @Property(selector = "setViewControllers:")
    public native void setViewControllers(NSArray<UIViewController> v);
    @Property(selector = "delegate")
    public native UISplitViewControllerDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UISplitViewControllerDelegate v);
    /**
     * @since Available in iOS 5.1 and later.
     */
    @Property(selector = "presentsWithGesture")
    public native boolean isPresentsWithGesture();
    /**
     * @since Available in iOS 5.1 and later.
     */
    @Property(selector = "setPresentsWithGesture:")
    public native void setPresentsWithGesture(boolean v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "isCollapsed")
    public native boolean isCollapsed();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "preferredDisplayMode")
    public native UISplitViewControllerDisplayMode getPreferredDisplayMode();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setPreferredDisplayMode:")
    public native void setPreferredDisplayMode(UISplitViewControllerDisplayMode v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "displayMode")
    public native UISplitViewControllerDisplayMode getDisplayMode();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "preferredPrimaryColumnWidthFraction")
    public native @MachineSizedFloat double getPreferredPrimaryColumnWidthFraction();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setPreferredPrimaryColumnWidthFraction:")
    public native void setPreferredPrimaryColumnWidthFraction(@MachineSizedFloat double v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "minimumPrimaryColumnWidth")
    public native @MachineSizedFloat double getMinimumPrimaryColumnWidth();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setMinimumPrimaryColumnWidth:")
    public native void setMinimumPrimaryColumnWidth(@MachineSizedFloat double v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "maximumPrimaryColumnWidth")
    public native @MachineSizedFloat double getMaximumPrimaryColumnWidth();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setMaximumPrimaryColumnWidth:")
    public native void setMaximumPrimaryColumnWidth(@MachineSizedFloat double v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "primaryColumnWidth")
    public native @MachineSizedFloat double getPrimaryColumnWidth();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "displayModeButtonItem")
    public native UIBarButtonItem getDisplayModeButtonItem();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "showViewController:sender:")
    public native void showViewController(UIViewController vc, NSObject sender);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "showDetailViewController:sender:")
    public native void showDetailViewController(UIViewController vc, NSObject sender);
    /*</methods>*/
}
