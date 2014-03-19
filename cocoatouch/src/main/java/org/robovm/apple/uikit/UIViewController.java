/*
 * Copyright (C) 2014 Trillian AB
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

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIViewController/*</name>*/ 
    extends /*<extends>*/UIResponder/*</extends>*/ 
    /*<implements>*/implements NSCoding, UIStateRestoring/*</implements>*/ {

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
    public native UIStoryboard getStoryboard();
    @Property(selector = "title")
    public native String getTitle();
    @Property(selector = "setTitle:")
    public native void setTitle(String v);
    @Property(selector = "parentViewController")
    public native UIViewController getParentViewController();
    @Property(selector = "modalViewController")
    public native UIViewController getModalViewController();
    @Property(selector = "presentedViewController")
    public native UIViewController getPresentedViewController();
    @Property(selector = "presentingViewController")
    public native UIViewController getPresentingViewController();
    @Property(selector = "definesPresentationContext")
    public native boolean isDefinesPresentationContext();
    @Property(selector = "setDefinesPresentationContext:")
    public native void setDefinesPresentationContext(boolean v);
    @Property(selector = "providesPresentationContextTransitionStyle")
    public native boolean isProvidesPresentationContextTransitionStyle();
    @Property(selector = "setProvidesPresentationContextTransitionStyle:")
    public native void setProvidesPresentationContextTransitionStyle(boolean v);
    @Property(selector = "modalTransitionStyle")
    public native UIModalTransitionStyle getModalTransitionStyle();
    @Property(selector = "setModalTransitionStyle:")
    public native void setModalTransitionStyle(UIModalTransitionStyle v);
    @Property(selector = "modalPresentationStyle")
    public native UIModalPresentationStyle getModalPresentationStyle();
    @Property(selector = "setModalPresentationStyle:")
    public native void setModalPresentationStyle(UIModalPresentationStyle v);
    @Property(selector = "modalPresentationCapturesStatusBarAppearance")
    public native boolean isModalPresentationCapturesStatusBarAppearance();
    @Property(selector = "setModalPresentationCapturesStatusBarAppearance:")
    public native void setModalPresentationCapturesStatusBarAppearance(boolean v);
    @Property(selector = "wantsFullScreenLayout")
    public native boolean isWantsFullScreenLayout();
    @Property(selector = "setWantsFullScreenLayout:")
    public native void setWantsFullScreenLayout(boolean v);
    @Property(selector = "edgesForExtendedLayout")
    public native UIRectEdge getEdgesForExtendedLayout();
    @Property(selector = "setEdgesForExtendedLayout:")
    public native void setEdgesForExtendedLayout(UIRectEdge v);
    @Property(selector = "extendedLayoutIncludesOpaqueBars")
    public native boolean isExtendedLayoutIncludesOpaqueBars();
    @Property(selector = "setExtendedLayoutIncludesOpaqueBars:")
    public native void setExtendedLayoutIncludesOpaqueBars(boolean v);
    @Property(selector = "automaticallyAdjustsScrollViewInsets")
    public native boolean isAutomaticallyAdjustsScrollViewInsets();
    @Property(selector = "setAutomaticallyAdjustsScrollViewInsets:")
    public native void setAutomaticallyAdjustsScrollViewInsets(boolean v);
    @Property(selector = "preferredContentSize")
    public native @ByVal CGSize getPreferredContentSize();
    @Property(selector = "setPreferredContentSize:")
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
    public native NSArray<?> getChildViewControllers();
    @Property(selector = "restorationIdentifier")
    public native String getRestorationIdentifier();
    @Property(selector = "setRestorationIdentifier:")
    public native void setRestorationIdentifier(String v);
    @Property(selector = "restorationClass")
    public native ObjCClass getRestorationClass();
    @Property(selector = "setRestorationClass:", strongRef = true)
    public native void setRestorationClass(ObjCClass v);
    @Property(selector = "transitioningDelegate")
    public native UIViewControllerTransitioningDelegate getTransitioningDelegate();
    @Property(selector = "setTransitioningDelegate:", strongRef = true)
    public native void setTransitioningDelegate(UIViewControllerTransitioningDelegate v);
    @Property(selector = "topLayoutGuide")
    public native UILayoutSupport getTopLayoutGuide();
    @Property(selector = "bottomLayoutGuide")
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
    public native NSArray<?> getToolbarItems();
    @Property(selector = "setToolbarItems:")
    public native void setToolbarItems(NSArray<?> v);
    @Property(selector = "isModalInPopover")
    public native boolean isModalInPopover();
    @Property(selector = "setModalInPopover:")
    public native void setModalInPopover(boolean v);
    @Property(selector = "contentSizeForViewInPopover")
    public native @ByVal CGSize getContentSizeForViewInPopover();
    @Property(selector = "setContentSizeForViewInPopover:")
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
    @Method(selector = "viewWillUnload")
    public native void viewWillUnload();
    @Method(selector = "viewDidUnload")
    public native void viewDidUnload();
    @Method(selector = "viewDidLoad")
    public native void viewDidLoad();
    @Method(selector = "isViewLoaded")
    public native boolean isViewLoaded();
    @Method(selector = "performSegueWithIdentifier:sender:")
    public native void performSegue(String identifier, NSObject sender);
    @Method(selector = "shouldPerformSegueWithIdentifier:sender:")
    public native boolean shouldPerformSegue(String identifier, NSObject sender);
    @Method(selector = "prepareForSegue:sender:")
    public native void prepareForSegue$sender$(UIStoryboardSegue segue, NSObject sender);
    @Method(selector = "canPerformUnwindSegueAction:fromViewController:withSender:")
    public native boolean canPerformUnwindSegueAction$fromViewController$withSender$(Selector action, UIViewController fromViewController, NSObject sender);
    @Method(selector = "viewControllerForUnwindSegueAction:fromViewController:withSender:")
    public native UIViewController getUnwindSegueActionViewController(Selector action, UIViewController fromViewController, NSObject sender);
    @Method(selector = "segueForUnwindingToViewController:fromViewController:identifier:")
    public native UIStoryboardSegue getUnwindSegue(UIViewController toViewController, UIViewController fromViewController, String identifier);
    @Method(selector = "viewWillAppear:")
    public native void viewWillAppear$(boolean animated);
    @Method(selector = "viewDidAppear:")
    public native void viewDidAppear$(boolean animated);
    @Method(selector = "viewWillDisappear:")
    public native void viewWillDisappear$(boolean animated);
    @Method(selector = "viewDidDisappear:")
    public native void viewDidDisappear$(boolean animated);
    @Method(selector = "viewWillLayoutSubviews")
    public native void viewWillLayoutSubviews();
    @Method(selector = "viewDidLayoutSubviews")
    public native void viewDidLayoutSubviews();
    @Method(selector = "didReceiveMemoryWarning")
    public native void didReceiveMemoryWarning();
    @Method(selector = "isBeingPresented")
    public native boolean isBeingPresented();
    @Method(selector = "isBeingDismissed")
    public native boolean isBeingDismissed();
    @Method(selector = "isMovingToParentViewController")
    public native boolean isMovingToParentViewController();
    @Method(selector = "isMovingFromParentViewController")
    public native boolean isMovingFromParentViewController();
    @Method(selector = "presentViewController:animated:completion:")
    public native void presentViewController$animated$completion$(UIViewController viewControllerToPresent, boolean flag, ObjCBlock completion);
    @Method(selector = "dismissViewControllerAnimated:completion:")
    public native void dismissViewController(boolean flag, ObjCBlock completion);
    @Method(selector = "presentModalViewController:animated:")
    public native void presentModalViewController$animated$(UIViewController modalViewController, boolean animated);
    @Method(selector = "dismissModalViewControllerAnimated:")
    public native void dismissModalViewController(boolean animated);
    @Method(selector = "disablesAutomaticKeyboardDismissal")
    public native boolean disablesAutomaticKeyboardDismissal();
    @Method(selector = "preferredStatusBarStyle")
    public native UIStatusBarStyle preferredStatusBarStyle();
    @Method(selector = "prefersStatusBarHidden")
    public native boolean prefersStatusBarHidden();
    @Method(selector = "preferredStatusBarUpdateAnimation")
    public native UIStatusBarAnimation preferredStatusBarUpdateAnimation();
    @Method(selector = "setNeedsStatusBarAppearanceUpdate")
    public native void setNeedsStatusBarAppearanceUpdate();
    @Method(selector = "shouldAutorotateToInterfaceOrientation:")
    public native boolean shouldAutorotateToInterfaceOrientation$(UIInterfaceOrientation toInterfaceOrientation);
    @Method(selector = "shouldAutorotate")
    public native boolean shouldAutorotate();
    @Method(selector = "supportedInterfaceOrientations")
    public native UIInterfaceOrientationMask getSupportedInterfaceOrientations();
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
    @Method(selector = "willAnimateRotationToInterfaceOrientation:duration:")
    public native void willAnimateRotation(UIInterfaceOrientation toInterfaceOrientation, double duration);
    @Method(selector = "willAnimateFirstHalfOfRotationToInterfaceOrientation:duration:")
    public native void willAnimateFirstHalfOfRotationToInterfaceOrientation$duration$(UIInterfaceOrientation toInterfaceOrientation, double duration);
    @Method(selector = "didAnimateFirstHalfOfRotationToInterfaceOrientation:")
    public native void didAnimateFirstHalfOfRotationToInterfaceOrientation$(UIInterfaceOrientation toInterfaceOrientation);
    @Method(selector = "willAnimateSecondHalfOfRotationFromInterfaceOrientation:duration:")
    public native void willAnimateSecondHalfOfRotationFromInterfaceOrientation$duration$(UIInterfaceOrientation fromInterfaceOrientation, double duration);
    @Method(selector = "attemptRotationToDeviceOrientation")
    public static native void attemptRotationToDeviceOrientation();
    @Method(selector = "setEditing:animated:")
    public native void setEditing$animated$(boolean editing, boolean animated);
    @Method(selector = "editButtonItem")
    public native UIBarButtonItem getEditButtonItem();
    @Method(selector = "addChildViewController:")
    public native void addChildViewController$(UIViewController childController);
    @Method(selector = "removeFromParentViewController")
    public native void removeFromParentViewController();
    @Method(selector = "transitionFromViewController:toViewController:duration:options:animations:completion:")
    public native void transition(UIViewController fromViewController, UIViewController toViewController, double duration, UIViewAnimationOptions options, ObjCBlock animations, ObjCBlock completion);
    @Method(selector = "beginAppearanceTransition:animated:")
    public native void beginAppearanceTransition$animated$(boolean isAppearing, boolean animated);
    @Method(selector = "endAppearanceTransition")
    public native void endAppearanceTransition();
    @Method(selector = "childViewControllerForStatusBarStyle")
    public native UIViewController childViewControllerForStatusBarStyle();
    @Method(selector = "childViewControllerForStatusBarHidden")
    public native UIViewController childViewControllerForStatusBarHidden();
    @Method(selector = "automaticallyForwardAppearanceAndRotationMethodsToChildViewControllers")
    public native boolean isAutomaticallyForwardAppearanceAndRotationMethodsToChildViewControllers();
    @Method(selector = "shouldAutomaticallyForwardRotationMethods")
    public native boolean shouldAutomaticallyForwardRotationMethods();
    @Method(selector = "shouldAutomaticallyForwardAppearanceMethods")
    public native boolean shouldAutomaticallyForwardAppearanceMethods();
    @Method(selector = "willMoveToParentViewController:")
    public native void willMoveToParent(UIViewController parent);
    @Method(selector = "didMoveToParentViewController:")
    public native void didMoveToParentViewController$(UIViewController parent);
    @Method(selector = "encodeRestorableStateWithCoder:")
    public native void encodeRestorableState(NSCoder coder);
    @Method(selector = "decodeRestorableStateWithCoder:")
    public native void decodeRestorableState(NSCoder coder);
    @Method(selector = "applicationFinishedRestoringState")
    public native void applicationFinishedRestoringState();
    @Method(selector = "updateViewConstraints")
    public native void updateViewConstraints();
    @Method(selector = "setToolbarItems:animated:")
    public native void setToolbarItems$animated$(NSArray<?> toolbarItems, boolean animated);
    @Method(selector = "transitionCoordinator")
    public native UIViewControllerTransitionCoordinator transitionCoordinator();
    @Method(selector = "encodeWithCoder:")
    public native void encodeWithCoder$(NSCoder aCoder);
    /*</methods>*/
}
