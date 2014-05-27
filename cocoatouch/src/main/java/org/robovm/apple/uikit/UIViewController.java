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
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIViewController/*</name>*/ 
    extends /*<extends>*/UIResponder/*</extends>*/ 
    /*<implements>*/implements NSCoding, UIAppearanceContainer, UIStateRestoring/*</implements>*/ {

    /*<ptr>*/public static class UIViewControllerPtr extends Ptr<UIViewController, UIViewControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIViewController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIViewController() {}
    protected UIViewController(SkipInit skipInit) { super(skipInit); }
    public UIViewController(String nibNameOrNil, NSBundle nibBundleOrNil) { super((SkipInit) null); initObject(initWithNibName$bundle$(nibNameOrNil, nibBundleOrNil)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "view")
    public native UIView getView();
    @Property(selector = "setView:")
    public native void setView(UIView v);
    @Property(selector = "nibName")
    public native String getNibName();
    @Property(selector = "nibBundle")
    public native NSBundle getNibBundle();
    @Property(selector = "storyboard")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native UIStoryboard getStoryboard();
    @Property(selector = "title")
    public native String getTitle();
    @Property(selector = "setTitle:")
    public native void setTitle(String v);
    @Property(selector = "parentViewController")
    public native UIViewController getParentViewController();
    @Property(selector = "modalViewController")
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    public native UIViewController getModalViewController();
    @Property(selector = "presentedViewController")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native UIViewController getPresentedViewController();
    @Property(selector = "presentingViewController")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native UIViewController getPresentingViewController();
    @Property(selector = "definesPresentationContext")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native boolean isDefinesPresentationContext();
    @Property(selector = "setDefinesPresentationContext:")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native void setDefinesPresentationContext(boolean v);
    @Property(selector = "providesPresentationContextTransitionStyle")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native boolean isProvidesPresentationContextTransitionStyle();
    @Property(selector = "setProvidesPresentationContextTransitionStyle:")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native void setProvidesPresentationContextTransitionStyle(boolean v);
    @Property(selector = "modalTransitionStyle")
    /**
     * @since Available in iOS 3.0 and later.
     */
    public native UIModalTransitionStyle getModalTransitionStyle();
    @Property(selector = "setModalTransitionStyle:")
    /**
     * @since Available in iOS 3.0 and later.
     */
    public native void setModalTransitionStyle(UIModalTransitionStyle v);
    @Property(selector = "modalPresentationStyle")
    /**
     * @since Available in iOS 3.2 and later.
     */
    public native UIModalPresentationStyle getModalPresentationStyle();
    @Property(selector = "setModalPresentationStyle:")
    /**
     * @since Available in iOS 3.2 and later.
     */
    public native void setModalPresentationStyle(UIModalPresentationStyle v);
    @Property(selector = "modalPresentationCapturesStatusBarAppearance")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native boolean isModalPresentationCapturesStatusBarAppearance();
    @Property(selector = "setModalPresentationCapturesStatusBarAppearance:")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native void setModalPresentationCapturesStatusBarAppearance(boolean v);
    @Property(selector = "wantsFullScreenLayout")
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public native boolean isWantsFullScreenLayout();
    @Property(selector = "setWantsFullScreenLayout:")
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public native void setWantsFullScreenLayout(boolean v);
    @Property(selector = "edgesForExtendedLayout")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native UIRectEdge getEdgesForExtendedLayout();
    @Property(selector = "setEdgesForExtendedLayout:")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native void setEdgesForExtendedLayout(UIRectEdge v);
    @Property(selector = "extendedLayoutIncludesOpaqueBars")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native boolean isExtendedLayoutIncludesOpaqueBars();
    @Property(selector = "setExtendedLayoutIncludesOpaqueBars:")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native void setExtendedLayoutIncludesOpaqueBars(boolean v);
    @Property(selector = "automaticallyAdjustsScrollViewInsets")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native boolean isAutomaticallyAdjustsScrollViewInsets();
    @Property(selector = "setAutomaticallyAdjustsScrollViewInsets:")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native void setAutomaticallyAdjustsScrollViewInsets(boolean v);
    @Property(selector = "preferredContentSize")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native @ByVal CGSize getPreferredContentSize();
    @Property(selector = "setPreferredContentSize:")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native void setPreferredContentSize(@ByVal CGSize v);
    @Property(selector = "interfaceOrientation")
    public native UIInterfaceOrientation getInterfaceOrientation();
    @Property(selector = "isEditing")
    public native boolean isEditing();
    @Property(selector = "setEditing:")
    public native void setEditing(boolean v);
    @Property(selector = "searchDisplayController")
    public native UISearchDisplayController getSearchDisplayController();
    @Property(selector = "childViewControllers")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native NSArray<UIViewController> getChildViewControllers();
    @Property(selector = "restorationIdentifier")
    /**
     * @since Available in iOS 6.0 and later.
     */
    public native String getRestorationIdentifier();
    @Property(selector = "setRestorationIdentifier:")
    /**
     * @since Available in iOS 6.0 and later.
     */
    public native void setRestorationIdentifier(String v);
    @Property(selector = "restorationClass")
    /**
     * @since Available in iOS 6.0 and later.
     */
    public native ObjCClass getRestorationClass();
    @Property(selector = "setRestorationClass:", strongRef = true)
    /**
     * @since Available in iOS 6.0 and later.
     */
    public native void setRestorationClass(ObjCClass v);
    @Property(selector = "transitioningDelegate")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native UIViewControllerTransitioningDelegate getTransitioningDelegate();
    @Property(selector = "setTransitioningDelegate:", strongRef = true)
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native void setTransitioningDelegate(UIViewControllerTransitioningDelegate v);
    @Property(selector = "topLayoutGuide")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native UILayoutSupport getTopLayoutGuide();
    @Property(selector = "bottomLayoutGuide")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native UILayoutSupport getBottomLayoutGuide();
    @Property(selector = "navigationItem")
    public native UINavigationItem getNavigationItem();
    @Property(selector = "hidesBottomBarWhenPushed")
    public native boolean isHidesBottomBarWhenPushed();
    @Property(selector = "setHidesBottomBarWhenPushed:")
    public native void setHidesBottomBarWhenPushed(boolean v);
    @Property(selector = "navigationController")
    public native UINavigationController getNavigationController();
    @Property(selector = "toolbarItems")
    /**
     * @since Available in iOS 3.0 and later.
     */
    public native NSArray<UIBarButtonItem> getToolbarItems();
    @Property(selector = "setToolbarItems:")
    /**
     * @since Available in iOS 3.0 and later.
     */
    public native void setToolbarItems(NSArray<UIBarButtonItem> v);
    @Property(selector = "isModalInPopover")
    /**
     * @since Available in iOS 3.2 and later.
     */
    public native boolean isModalInPopover();
    @Property(selector = "setModalInPopover:")
    /**
     * @since Available in iOS 3.2 and later.
     */
    public native void setModalInPopover(boolean v);
    @Property(selector = "contentSizeForViewInPopover")
    /**
     * @since Available in iOS 3.2 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public native @ByVal CGSize getContentSizeForViewInPopover();
    @Property(selector = "setContentSizeForViewInPopover:")
    /**
     * @since Available in iOS 3.2 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public native void setContentSizeForViewInPopover(@ByVal CGSize v);
    @Property(selector = "splitViewController")
    public native UISplitViewController getSplitViewController();
    @Property(selector = "tabBarItem")
    public native UITabBarItem getTabBarItem();
    @Property(selector = "setTabBarItem:")
    public native void setTabBarItem(UITabBarItem v);
    @Property(selector = "tabBarController")
    public native UITabBarController getTabBarController();
    @Property(selector = "restorationParent")
    public native UIStateRestoring getRestorationParent();
    @Property(selector = "objectRestorationClass")
    public native ObjCClass getObjectRestorationClass();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithNibName:bundle:")
    protected native @Pointer long initWithNibName$bundle$(String nibNameOrNil, NSBundle nibBundleOrNil);
    @Method(selector = "loadView")
    public native void loadView();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Method(selector = "viewWillUnload")
    public native void viewWillUnload();
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Method(selector = "viewDidUnload")
    public native void viewDidUnload();
    @Method(selector = "viewDidLoad")
    public native void viewDidLoad();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "isViewLoaded")
    public native boolean isViewLoaded();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "performSegueWithIdentifier:sender:")
    public native void performSegue(String identifier, NSObject sender);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "shouldPerformSegueWithIdentifier:sender:")
    public native boolean shouldPerformSegue(String identifier, NSObject sender);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "prepareForSegue:sender:")
    public native void prepareForSegue(UIStoryboardSegue segue, NSObject sender);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "canPerformUnwindSegueAction:fromViewController:withSender:")
    public native boolean canPerformUnwind(Selector action, UIViewController fromViewController, NSObject sender);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "viewControllerForUnwindSegueAction:fromViewController:withSender:")
    public native UIViewController getViewControllerForUnwind(Selector action, UIViewController fromViewController, NSObject sender);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "segueForUnwindingToViewController:fromViewController:identifier:")
    public native UIStoryboardSegue getSegueForUnwinding(UIViewController toViewController, UIViewController fromViewController, String identifier);
    @Method(selector = "viewWillAppear:")
    public native void viewWillAppear(boolean animated);
    @Method(selector = "viewDidAppear:")
    public native void viewDidAppear(boolean animated);
    @Method(selector = "viewWillDisappear:")
    public native void viewWillDisappear(boolean animated);
    @Method(selector = "viewDidDisappear:")
    public native void viewDidDisappear(boolean animated);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "viewWillLayoutSubviews")
    public native void viewWillLayoutSubviews();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "viewDidLayoutSubviews")
    public native void viewDidLayoutSubviews();
    @Method(selector = "didReceiveMemoryWarning")
    public native void didReceiveMemoryWarning();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "isBeingPresented")
    public native boolean isBeingPresented();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "isBeingDismissed")
    public native boolean isBeingDismissed();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "isMovingToParentViewController")
    public native boolean isMovingToParentViewController();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "isMovingFromParentViewController")
    public native boolean isMovingFromParentViewController();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "presentViewController:animated:completion:")
    public native void presentViewController(UIViewController viewControllerToPresent, boolean flag, @Block Runnable completion);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "dismissViewControllerAnimated:completion:")
    public native void dismissViewController(boolean flag, @Block Runnable completion);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Method(selector = "presentModalViewController:animated:")
    public native void presentModalViewController(UIViewController modalViewController, boolean animated);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Method(selector = "dismissModalViewControllerAnimated:")
    public native void dismissModalViewController(boolean animated);
    /**
     * @since Available in iOS 4.3 and later.
     */
    @Method(selector = "disablesAutomaticKeyboardDismissal")
    public native boolean disablesAutomaticKeyboardDismissal();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "preferredStatusBarStyle")
    public native UIStatusBarStyle preferredStatusBarStyle();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "prefersStatusBarHidden")
    public native boolean prefersStatusBarHidden();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "preferredStatusBarUpdateAnimation")
    public native UIStatusBarAnimation preferredStatusBarUpdateAnimation();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setNeedsStatusBarAppearanceUpdate")
    public native void setNeedsStatusBarAppearanceUpdate();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Method(selector = "shouldAutorotateToInterfaceOrientation:")
    public native boolean shouldAutorotate(UIInterfaceOrientation toInterfaceOrientation);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "shouldAutorotate")
    public native boolean shouldAutorotate();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "supportedInterfaceOrientations")
    public native UIInterfaceOrientationMask getSupportedInterfaceOrientations();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "preferredInterfaceOrientationForPresentation")
    public native UIInterfaceOrientation getPreferredInterfaceOrientation();
    @Method(selector = "rotatingHeaderView")
    public native UIView getRotatingHeaderView();
    @Method(selector = "rotatingFooterView")
    public native UIView getRotatingFooterView();
    @Method(selector = "willRotateToInterfaceOrientation:duration:")
    public native void willRotate(UIInterfaceOrientation toInterfaceOrientation, double duration);
    @Method(selector = "didRotateFromInterfaceOrientation:")
    public native void didRotate(UIInterfaceOrientation fromInterfaceOrientation);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "willAnimateRotationToInterfaceOrientation:duration:")
    public native void willAnimateRotation(UIInterfaceOrientation toInterfaceOrientation, double duration);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 5.0.
     */
    @Deprecated
    @Method(selector = "willAnimateFirstHalfOfRotationToInterfaceOrientation:duration:")
    public native void willAnimateFirstHalfOfRotation(UIInterfaceOrientation toInterfaceOrientation, double duration);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 5.0.
     */
    @Deprecated
    @Method(selector = "didAnimateFirstHalfOfRotationToInterfaceOrientation:")
    public native void didAnimateFirstHalfOfRotation(UIInterfaceOrientation toInterfaceOrientation);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 5.0.
     */
    @Deprecated
    @Method(selector = "willAnimateSecondHalfOfRotationFromInterfaceOrientation:duration:")
    public native void willAnimateSecondHalfOfRotation(UIInterfaceOrientation fromInterfaceOrientation, double duration);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "attemptRotationToDeviceOrientation")
    public static native void attemptRotationToDeviceOrientation();
    @Method(selector = "setEditing:animated:")
    public native void setEditing(boolean editing, boolean animated);
    @Method(selector = "editButtonItem")
    public native UIBarButtonItem getEditButtonItem();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "addChildViewController:")
    public native void addChildViewController(UIViewController childController);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "removeFromParentViewController")
    public native void removeFromParentViewController();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "transitionFromViewController:toViewController:duration:options:animations:completion:")
    public native void transition(UIViewController fromViewController, UIViewController toViewController, double duration, UIViewAnimationOptions options, @Block Runnable animations, @Block VoidBooleanBlock completion);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "beginAppearanceTransition:animated:")
    public native void beginAppearanceTransition(boolean isAppearing, boolean animated);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "endAppearanceTransition")
    public native void endAppearanceTransition();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "childViewControllerForStatusBarStyle")
    public native UIViewController childViewControllerForStatusBarStyle();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "childViewControllerForStatusBarHidden")
    public native UIViewController childViewControllerForStatusBarHidden();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Method(selector = "automaticallyForwardAppearanceAndRotationMethodsToChildViewControllers")
    public native boolean isAutomaticallyForwardAppearanceAndRotationMethodsToChildViewControllers();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "shouldAutomaticallyForwardRotationMethods")
    public native boolean shouldAutomaticallyForwardRotationMethods();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "shouldAutomaticallyForwardAppearanceMethods")
    public native boolean shouldAutomaticallyForwardAppearanceMethods();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "willMoveToParentViewController:")
    public native void willMoveToParentViewController(UIViewController parent);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "didMoveToParentViewController:")
    public native void didMoveToParentViewController(UIViewController parent);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "encodeRestorableStateWithCoder:")
    public native void encodeRestorableState(NSCoder coder);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "decodeRestorableStateWithCoder:")
    public native void decodeRestorableState(NSCoder coder);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "applicationFinishedRestoringState")
    public native void applicationFinishedRestoringState();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "updateViewConstraints")
    public native void updateViewConstraints();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setToolbarItems:animated:")
    public native void setToolbarItems(NSArray<UIBarButtonItem> toolbarItems, boolean animated);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "transitionCoordinator")
    public native UIViewControllerTransitionCoordinator transitionCoordinator();
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
