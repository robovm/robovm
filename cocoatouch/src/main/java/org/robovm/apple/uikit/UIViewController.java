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
import org.robovm.apple.iad.ADInterstitialPresentationPolicy;
import org.robovm.apple.mediaplayer.MPMoviePlayerViewController;

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIViewController/*</name>*/ 
    extends /*<extends>*/UIResponder/*</extends>*/ 
    /*<implements>*/implements NSCoding, UIAppearanceContainer, UITraitEnvironment, UIStateRestoring, NSExtensionRequestHandling/*</implements>*/ {

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
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "storyboard")
    public native UIStoryboard getStoryboard();
    @Property(selector = "title")
    public native String getTitle();
    @Property(selector = "setTitle:")
    public native void setTitle(String v);
    @Property(selector = "parentViewController")
    public native UIViewController getParentViewController();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Property(selector = "modalViewController")
    public native UIViewController getModalViewController();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "presentedViewController")
    public native UIViewController getPresentedViewController();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "presentingViewController")
    public native UIViewController getPresentingViewController();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "definesPresentationContext")
    public native boolean isDefinesPresentationContext();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setDefinesPresentationContext:")
    public native void setDefinesPresentationContext(boolean v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "providesPresentationContextTransitionStyle")
    public native boolean isProvidesPresentationContextTransitionStyle();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setProvidesPresentationContextTransitionStyle:")
    public native void setProvidesPresentationContextTransitionStyle(boolean v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "modalTransitionStyle")
    public native UIModalTransitionStyle getModalTransitionStyle();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setModalTransitionStyle:")
    public native void setModalTransitionStyle(UIModalTransitionStyle v);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Property(selector = "modalPresentationStyle")
    public native UIModalPresentationStyle getModalPresentationStyle();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Property(selector = "setModalPresentationStyle:")
    public native void setModalPresentationStyle(UIModalPresentationStyle v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "modalPresentationCapturesStatusBarAppearance")
    public native boolean isModalPresentationCapturesStatusBarAppearance();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setModalPresentationCapturesStatusBarAppearance:")
    public native void setModalPresentationCapturesStatusBarAppearance(boolean v);
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "wantsFullScreenLayout")
    public native boolean isWantsFullScreenLayout();
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "setWantsFullScreenLayout:")
    public native void setWantsFullScreenLayout(boolean v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "edgesForExtendedLayout")
    public native UIRectEdge getEdgesForExtendedLayout();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setEdgesForExtendedLayout:")
    public native void setEdgesForExtendedLayout(UIRectEdge v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "extendedLayoutIncludesOpaqueBars")
    public native boolean isExtendedLayoutIncludesOpaqueBars();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setExtendedLayoutIncludesOpaqueBars:")
    public native void setExtendedLayoutIncludesOpaqueBars(boolean v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "automaticallyAdjustsScrollViewInsets")
    public native boolean isAutomaticallyAdjustsScrollViewInsets();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setAutomaticallyAdjustsScrollViewInsets:")
    public native void setAutomaticallyAdjustsScrollViewInsets(boolean v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "preferredContentSize")
    public native @ByVal CGSize getPreferredContentSize();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setPreferredContentSize:")
    public native void setPreferredContentSize(@ByVal CGSize v);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Property(selector = "interfaceOrientation")
    public native UIInterfaceOrientation getInterfaceOrientation();
    @Property(selector = "isEditing")
    public native boolean isEditing();
    @Property(selector = "setEditing:")
    public native void setEditing(boolean v);
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Property(selector = "searchDisplayController")
    public native UISearchDisplayController getSearchDisplayController();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "childViewControllers")
    public native NSArray<UIViewController> getChildViewControllers();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "restorationIdentifier")
    public native String getRestorationIdentifier();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setRestorationIdentifier:")
    public native void setRestorationIdentifier(String v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "restorationClass")
    public native ObjCClass getRestorationClass();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setRestorationClass:", strongRef = true)
    public native void setRestorationClass(ObjCClass v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "transitioningDelegate")
    public native UIViewControllerTransitioningDelegate getTransitioningDelegate();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setTransitioningDelegate:", strongRef = true)
    public native void setTransitioningDelegate(UIViewControllerTransitioningDelegate v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "topLayoutGuide")
    public native UILayoutSupport getTopLayoutGuide();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "bottomLayoutGuide")
    public native UILayoutSupport getBottomLayoutGuide();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "extensionContext")
    public native NSExtensionContext getExtensionContext();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "presentationController")
    public native UIPresentationController getPresentationController();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "popoverPresentationController")
    public native UIPopoverPresentationController getPopoverPresentationController();
    @Property(selector = "navigationItem")
    public native UINavigationItem getNavigationItem();
    @Property(selector = "hidesBottomBarWhenPushed")
    public native boolean isHidesBottomBarWhenPushed();
    @Property(selector = "setHidesBottomBarWhenPushed:")
    public native void setHidesBottomBarWhenPushed(boolean v);
    @Property(selector = "navigationController")
    public native UINavigationController getNavigationController();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "toolbarItems")
    public native NSArray<UIBarButtonItem> getToolbarItems();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setToolbarItems:")
    public native void setToolbarItems(NSArray<UIBarButtonItem> v);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Property(selector = "isModalInPopover")
    public native boolean isModalInPopover();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Property(selector = "setModalInPopover:")
    public native void setModalInPopover(boolean v);
    /**
     * @since Available in iOS 3.2 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Property(selector = "contentSizeForViewInPopover")
    public native @ByVal CGSize getContentSizeForViewInPopover();
    /**
     * @since Available in iOS 3.2 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
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
    @Property(selector = "traitCollection")
    public native UITraitCollection getTraitCollection();
    @Property(selector = "restorationParent")
    public native UIStateRestoring getRestorationParent();
    @Property(selector = "objectRestorationClass")
    public native ObjCClass getObjectRestorationClass();
    /*</properties>*/
    /*<members>*//*</members>*/
    
    /* iAd extensions */
    /**
     * @since Available in iOS 7.0 and later.
     */
    public ADInterstitialPresentationPolicy getInterstitialPresentationPolicy() {
        return org.robovm.apple.iad.UIViewControllerExtensions.getInterstitialPresentationPolicy(this);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void setInterstitialPresentationPolicy(ADInterstitialPresentationPolicy v) {
        org.robovm.apple.iad.UIViewControllerExtensions.setInterstitialPresentationPolicy(this, v);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isCanDisplayBannerAds() {
        return org.robovm.apple.iad.UIViewControllerExtensions.isCanDisplayBannerAds(this);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void setCanDisplayBannerAds(boolean v) {
        org.robovm.apple.iad.UIViewControllerExtensions.setCanDisplayBannerAds(this, v);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UIView getOriginalContentView() {
        return org.robovm.apple.iad.UIViewControllerExtensions.getOriginalContentView(this);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isPresentingFullScreenAd() {
        return org.robovm.apple.iad.UIViewControllerExtensions.isPresentingFullScreenAd(this);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isDisplayingBannerAd() {
        return org.robovm.apple.iad.UIViewControllerExtensions.isDisplayingBannerAd(this);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean requestInterstitialAdPresentation() {
        return org.robovm.apple.iad.UIViewControllerExtensions.requestInterstitialAdPresentation(this);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean shouldPresentInterstitialAd() {
        return org.robovm.apple.iad.UIViewControllerExtensions.shouldPresentInterstitialAd(this);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static void prepareInterstitialAds() {
        org.robovm.apple.iad.UIViewControllerExtensions.prepareInterstitialAds();
    }
    
    /* MediaPlayer extensions */
    public void presentMoviePlayerViewController(MPMoviePlayerViewController moviePlayerViewController) {
        org.robovm.apple.mediaplayer.UIViewControllerExtensions.presentMoviePlayerViewController(this, moviePlayerViewController);
    }
    public void dismissMoviePlayerViewController() {
        org.robovm.apple.mediaplayer.UIViewControllerExtensions.dismissMoviePlayerViewController(this);
    }
    
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
    public native UIStatusBarStyle getPreferredStatusBarStyle();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "prefersStatusBarHidden")
    public native boolean prefersStatusBarHidden();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "preferredStatusBarUpdateAnimation")
    public native UIStatusBarAnimation getPreferredStatusBarUpdateAnimation();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setNeedsStatusBarAppearanceUpdate")
    public native void setNeedsStatusBarAppearanceUpdate();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "targetViewControllerForAction:sender:")
    public native UIViewController getTargetViewControllerForAction(Selector action, NSObject sender);
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
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Method(selector = "rotatingHeaderView")
    public native UIView getRotatingHeaderView();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Method(selector = "rotatingFooterView")
    public native UIView getRotatingFooterView();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Method(selector = "willRotateToInterfaceOrientation:duration:")
    public native void willRotate(UIInterfaceOrientation toInterfaceOrientation, double duration);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Method(selector = "didRotateFromInterfaceOrientation:")
    public native void didRotate(UIInterfaceOrientation fromInterfaceOrientation);
    /**
     * @since Available in iOS 3.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
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
    public native UIViewController getChildViewControllerForStatusBarStyle();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "childViewControllerForStatusBarHidden")
    public native UIViewController getChildViewControllerForStatusBarHidden();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "setOverrideTraitCollection:forChildViewController:")
    public native void setOverrideTraitCollection(UITraitCollection collection, UIViewController childViewController);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "overrideTraitCollectionForChildViewController:")
    public native UITraitCollection getOverrideTraitCollection(UIViewController childViewController);
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 6.0.
     */
    @Deprecated
    @Method(selector = "automaticallyForwardAppearanceAndRotationMethodsToChildViewControllers")
    public native boolean isAutomaticallyForwardAppearanceAndRotationMethodsToChildViewControllers();
    /**
     * @since Available in iOS 6.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
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
    public native UIViewControllerTransitionCoordinator getTransitionCoordinator();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "collapseSecondaryViewController:forSplitViewController:")
    public native void collapseSecondaryViewController(UIViewController secondaryViewController, UISplitViewController splitViewController);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Method(selector = "separateSecondaryViewControllerForSplitViewController:")
    public native UIViewController separateSecondaryViewController(UISplitViewController splitViewController);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    @Method(selector = "traitCollectionDidChange:")
    public native void traitCollectionDidChange(UITraitCollection previousTraitCollection);
    @Method(selector = "beginRequestWithExtensionContext:")
    public native void beginRequest(NSExtensionContext context);
    /*</methods>*/
}
