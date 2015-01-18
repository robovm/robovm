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
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UISearchController/*</name>*/ 
    extends /*<extends>*/UIViewController/*</extends>*/ 
    /*<implements>*/implements UIViewControllerTransitioningDelegate, UIViewControllerAnimatedTransitioning/*</implements>*/ {

    /*<ptr>*/public static class UISearchControllerPtr extends Ptr<UISearchController, UISearchControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UISearchController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UISearchController() {}
    protected UISearchController(SkipInit skipInit) { super(skipInit); }
    public UISearchController(UIViewController searchResultsController) { super((SkipInit) null); initObject(init(searchResultsController)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "searchResultsUpdater")
    public native UISearchResultsUpdating getSearchResultsUpdater();
    @Property(selector = "setSearchResultsUpdater:", strongRef = true)
    public native void setSearchResultsUpdater(UISearchResultsUpdating v);
    @Property(selector = "isActive")
    public native boolean isActive();
    @Property(selector = "setActive:")
    public native void setActive(boolean v);
    @Property(selector = "delegate")
    public native UISearchControllerDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UISearchControllerDelegate v);
    @Property(selector = "dimsBackgroundDuringPresentation")
    public native boolean dimsBackgroundDuringPresentation();
    @Property(selector = "setDimsBackgroundDuringPresentation:")
    public native void setDimsBackgroundDuringPresentation(boolean v);
    @Property(selector = "hidesNavigationBarDuringPresentation")
    public native boolean hidesNavigationBarDuringPresentation();
    @Property(selector = "setHidesNavigationBarDuringPresentation:")
    public native void setHidesNavigationBarDuringPresentation(boolean v);
    @Property(selector = "searchResultsController")
    public native UIViewController getSearchResultsController();
    @Property(selector = "searchBar")
    public native UISearchBar getSearchBar();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithSearchResultsController:")
    protected native @Pointer long init(UIViewController searchResultsController);
    @Method(selector = "animationControllerForPresentedController:presentingController:sourceController:")
    public native UIViewControllerAnimatedTransitioning getAnimationControllerForPresentedController(UIViewController presented, UIViewController presenting, UIViewController source);
    @Method(selector = "animationControllerForDismissedController:")
    public native UIViewControllerAnimatedTransitioning getAnimationControllerForDismissedController(UIViewController dismissed);
    @Method(selector = "interactionControllerForPresentation:")
    public native UIViewControllerInteractiveTransitioning getInteractionControllerForPresentation(UIViewControllerAnimatedTransitioning animator);
    @Method(selector = "interactionControllerForDismissal:")
    public native UIViewControllerInteractiveTransitioning getInteractionControllerForDismissal(UIViewControllerAnimatedTransitioning animator);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "presentationControllerForPresentedViewController:presentingViewController:sourceViewController:")
    public native UIPresentationController getPresentationControllerForPresentedViewController(UIViewController presented, UIViewController presenting, UIViewController source);
    @Method(selector = "transitionDuration:")
    public native double getTransitionDuration(UIViewControllerContextTransitioning transitionContext);
    @Method(selector = "animateTransition:")
    public native void animateTransition(UIViewControllerContextTransitioning transitionContext);
    @Method(selector = "animationEnded:")
    public native void animationEnded(boolean transitionCompleted);
    /*</methods>*/
}
