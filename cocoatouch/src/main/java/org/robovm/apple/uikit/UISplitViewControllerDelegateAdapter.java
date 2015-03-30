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
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UISplitViewControllerDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UISplitViewControllerDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("splitViewController:willChangeToDisplayMode:")
    public void willChangeToDisplayMode(UISplitViewController svc, UISplitViewControllerDisplayMode displayMode) {}
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("targetDisplayModeForActionInSplitViewController:")
    public UISplitViewControllerDisplayMode getTargetDisplayMode(UISplitViewController svc) { return null; }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("splitViewController:showViewController:sender:")
    public boolean showViewController(UISplitViewController splitViewController, UIViewController vc, NSObject sender) { return false; }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("splitViewController:showDetailViewController:sender:")
    public boolean showDetailViewController(UISplitViewController splitViewController, UIViewController vc, NSObject sender) { return false; }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("primaryViewControllerForCollapsingSplitViewController:")
    public UIViewController getPrimaryViewControllerForCollapsing(UISplitViewController splitViewController) { return null; }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("primaryViewControllerForExpandingSplitViewController:")
    public UIViewController getPrimaryViewControllerForExpanding(UISplitViewController splitViewController) { return null; }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("splitViewController:collapseSecondaryViewController:ontoPrimaryViewController:")
    public boolean collapseSecondaryViewController(UISplitViewController splitViewController, UIViewController secondaryViewController, UIViewController primaryViewController) { return false; }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("splitViewController:separateSecondaryViewControllerFromPrimaryViewController:")
    public UIViewController separateSecondaryViewController(UISplitViewController splitViewController, UIViewController primaryViewController) { return null; }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("splitViewControllerSupportedInterfaceOrientations:")
    public @MachineSizedUInt long getSupportedInterfaceOrientations(UISplitViewController splitViewController) { return 0; }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("splitViewControllerPreferredInterfaceOrientationForPresentation:")
    public UIInterfaceOrientation getPreferredInterfaceOrientation(UISplitViewController splitViewController) { return null; }
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @NotImplemented("splitViewController:willHideViewController:withBarButtonItem:forPopoverController:")
    public void willHideViewController(UISplitViewController svc, UIViewController aViewController, UIBarButtonItem barButtonItem, UIPopoverController pc) {}
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @NotImplemented("splitViewController:willShowViewController:invalidatingBarButtonItem:")
    public void willShowViewController(UISplitViewController svc, UIViewController aViewController, UIBarButtonItem barButtonItem) {}
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @NotImplemented("splitViewController:popoverController:willPresentViewController:")
    public void willPresentViewController(UISplitViewController svc, UIPopoverController pc, UIViewController aViewController) {}
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @NotImplemented("splitViewController:shouldHideViewController:inOrientation:")
    public boolean shouldHideViewController(UISplitViewController svc, UIViewController vc, UIInterfaceOrientation orientation) { return false; }
    /*</methods>*/
}
