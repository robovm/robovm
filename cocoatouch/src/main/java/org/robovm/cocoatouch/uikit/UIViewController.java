/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIViewController /*</name>*/ 
    extends /*<extends>*/ UIResponder /*</extends>*/ 
    /*<implements>*/ implements UIAppearanceContainer /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIViewController /*</name>*/.class);
    }

    /*<constructors>*/
    public UIViewController() {}
    @Bind("initWithNibName:bundle:") public UIViewController(@Type("NSString *") String nibName, @Type("NSBundle *") NSBundle nibBundle) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("childViewControllers") public native @Type("NSArray *") NSArray getChildViewControllers();
    @Bind("contentSizeForViewInPopover") public native @Type("CGSize") CGSize getContentSizeForViewInPopover();
    @Bind("setContentSizeForViewInPopover:") public native void setContentSizeForViewInPopover(@Type("CGSize") CGSize v);
    @Bind("definesPresentationContext") public native @Type("BOOL") boolean isDefinesPresentationContext();
    @Bind("setDefinesPresentationContext:") public native void setDefinesPresentationContext(@Type("BOOL") boolean v);
    @Bind("isEditing") public native @Type("BOOL") boolean isEditing();
    @Bind("setEditing:") public native void setEditing(@Type("BOOL") boolean v);
    @Bind("hidesBottomBarWhenPushed") public native @Type("BOOL") boolean isHidesBottomBarWhenPushed();
    @Bind("setHidesBottomBarWhenPushed:") public native void setHidesBottomBarWhenPushed(@Type("BOOL") boolean v);
    @Bind("interfaceOrientation") public native @Type("UIInterfaceOrientation") UIInterfaceOrientation getInterfaceOrientation();
    @Bind("isModalInPopover") public native @Type("BOOL") boolean isModalInPopover();
    @Bind("setModalInPopover:") public native void setModalInPopover(@Type("BOOL") boolean v);
    @Bind("modalPresentationStyle") public native @Type("UIModalPresentationStyle") UIModalPresentationStyle getModalPresentationStyle();
    @Bind("setModalPresentationStyle:") public native void setModalPresentationStyle(@Type("UIModalPresentationStyle") UIModalPresentationStyle v);
    @Bind("modalTransitionStyle") public native @Type("UIModalTransitionStyle") UIModalTransitionStyle getModalTransitionStyle();
    @Bind("setModalTransitionStyle:") public native void setModalTransitionStyle(@Type("UIModalTransitionStyle") UIModalTransitionStyle v);
    @Bind("navigationController") public native @Type("UINavigationController *") UINavigationController getNavigationController();
    @Bind("navigationItem") public native @Type("UINavigationItem *") UINavigationItem getNavigationItem();
    @Bind("nibBundle") public native @Type("NSBundle *") NSBundle getNibBundle();
    @Bind("nibName") public native @Type("NSString *") String getNibName();
    @Bind("parentViewController") public native @Type("UIViewController *") UIViewController getParentViewController();
    @Bind("presentedViewController") public native @Type("UIViewController *") UIViewController getPresentedViewController();
    @Bind("presentingViewController") public native @Type("UIViewController *") UIViewController getPresentingViewController();
    @Bind("providesPresentationContextTransitionStyle") public native @Type("BOOL") boolean isProvidesPresentationContextTransitionStyle();
    @Bind("setProvidesPresentationContextTransitionStyle:") public native void setProvidesPresentationContextTransitionStyle(@Type("BOOL") boolean v);
    @Bind("restorationClass") public native @Type("Class<UIViewControllerRestoration>") Class<UIViewControllerRestoration> getRestorationClass();
    @Bind("setRestorationClass:") public native void setRestorationClass(@Type("Class<UIViewControllerRestoration>") Class<UIViewControllerRestoration> v);
    @Bind("restorationIdentifier") public native @Type("NSString *") String getRestorationIdentifier();
    @Bind("setRestorationIdentifier:") public native void setRestorationIdentifier(@Type("NSString *") String v);
    @Bind("searchDisplayController") public native @Type("UISearchDisplayController *") UISearchDisplayController getSearchDisplayController();
    @Bind("splitViewController") public native @Type("UISplitViewController *") UISplitViewController getSplitViewController();
    @Bind("storyboard") public native @Type("UIStoryboard *") UIStoryboard getStoryboard();
    @Bind("tabBarController") public native @Type("UITabBarController *") UITabBarController getTabBarController();
    @Bind("tabBarItem") public native @Type("UITabBarItem *") UITabBarItem getTabBarItem();
    @Bind("setTabBarItem:") public native void setTabBarItem(@Type("UITabBarItem *") UITabBarItem v);
    @Bind("title") public native @Type("NSString *") String getTitle();
    @Bind("setTitle:") public native void setTitle(@Type("NSString *") String v);
    @Bind("toolbarItems") public native @Type("NSArray *") NSArray getToolbarItems();
    @Bind("setToolbarItems:") public native void setToolbarItems(@Type("NSArray *") NSArray v);
    @Bind("view") public native @Type("UIView *") UIView getView();
    @Bind("setView:") public native void setView(@Type("UIView *") UIView v);
    @Bind("wantsFullScreenLayout") public native @Type("BOOL") boolean isWantsFullScreenLayout();
    @Bind("setWantsFullScreenLayout:") public native void setWantsFullScreenLayout(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    @Bind("attemptRotationToDeviceOrientation") public native static @Type("void") void attemptRotationToDeviceOrientation();
    @Bind("addChildViewController:") public native @Type("void") void addChildViewController(@Type("UIViewController *") UIViewController childController);
    @Bind("beginAppearanceTransition:animated:") public native @Type("void") void beginAppearanceTransition(@Type("BOOL") boolean isAppearing, @Type("BOOL") boolean animated);
    @Bind("canPerformUnwindSegueAction:fromViewController:withSender:") public native @Type("BOOL") boolean canPerformUnwindSegueAction(@Type("SEL") Selector action, @Type("UIViewController *") UIViewController fromViewController, @Type("id") NSObject sender);
    @Bind("decodeRestorableStateWithCoder:") public native @Type("void") void decodeRestorableStateWithCoder(@Type("NSCoder *") NSCoder coder);
    @Bind("didMoveToParentViewController:") public native @Type("void") void didMoveToParentViewController(@Type("UIViewController *") UIViewController parent);
    @Bind("didReceiveMemoryWarning") public native @Type("void") void didReceiveMemoryWarning();
    @Bind("didRotateFromInterfaceOrientation:") public native @Type("void") void didRotate(@Type("UIInterfaceOrientation") UIInterfaceOrientation fromInterfaceOrientation);
    @Bind("disablesAutomaticKeyboardDismissal") public native @Type("BOOL") boolean disablesAutomaticKeyboardDismissal();
    @Bind("dismissViewControllerAnimated:completion:") public native @Type("void") void dismissViewController(@Type("BOOL") boolean flag, @Type("void (^)(void)") VoidBlock completion);
    @Bind("encodeRestorableStateWithCoder:") public native @Type("void") void encodeRestorableStateWithCoder(@Type("NSCoder *") NSCoder coder);
    @Bind("endAppearanceTransition") public native @Type("void") void endAppearanceTransition();
    @Bind("editButtonItem") public native @Type("UIBarButtonItem *") UIBarButtonItem getEditButtonItem();
    @Bind("rotatingFooterView") public native @Type("UIView *") UIView getRotatingFooterView();
    @Bind("rotatingHeaderView") public native @Type("UIView *") UIView getRotatingHeaderView();
    @Bind("isBeingDismissed") public native @Type("BOOL") boolean isBeingDismissed();
    @Bind("isBeingPresented") public native @Type("BOOL") boolean isBeingPresented();
    @Bind("isMovingFromParentViewController") public native @Type("BOOL") boolean isMovingFromParentViewController();
    @Bind("isMovingToParentViewController") public native @Type("BOOL") boolean isMovingToParentViewController();
    @Bind("isViewLoaded") public native @Type("BOOL") boolean isViewLoaded();
    @Bind("loadView") public native @Type("void") void loadView();
    @Bind("performSegueWithIdentifier:sender:") public native @Type("void") void performSegue(@Type("NSString *") String identifier, @Type("id") NSObject sender);
    @Bind("preferredInterfaceOrientationForPresentation") public native @Type("UIInterfaceOrientation") UIInterfaceOrientation preferredInterfaceOrientationForPresentation();
    @Bind("prepareForSegue:sender:") public native @Type("void") void prepareForSegue(@Type("UIStoryboardSegue *") UIStoryboardSegue segue, @Type("id") NSObject sender);
    @Bind("presentViewController:animated:completion:") public native @Type("void") void presentViewController(@Type("UIViewController *") UIViewController viewControllerToPresent, @Type("BOOL") boolean flag, @Type("void (^)(void)") VoidBlock completion);
    @Bind("removeFromParentViewController") public native @Type("void") void removeFromParentViewController();
    @Bind("segueForUnwindingToViewController:fromViewController:identifier:") public native @Type("UIStoryboardSegue *") UIStoryboardSegue segueForUnwindingToViewController(@Type("UIViewController *") UIViewController toViewController, @Type("UIViewController *") UIViewController fromViewController, @Type("NSString *") String identifier);
    @Bind("setEditing:animated:") public native @Type("void") void setEditing(@Type("BOOL") boolean editing, @Type("BOOL") boolean animated);
    @Bind("setToolbarItems:animated:") public native @Type("void") void setToolbarItems(@Type("NSArray *") NSArray toolbarItems, @Type("BOOL") boolean animated);
    @Bind("shouldAutomaticallyForwardAppearanceMethods") public native @Type("BOOL") boolean shouldAutomaticallyForwardAppearanceMethods();
    @Bind("shouldAutomaticallyForwardRotationMethods") public native @Type("BOOL") boolean shouldAutomaticallyForwardRotationMethods();
    @Bind("shouldAutorotate") public native @Type("BOOL") boolean shouldAutorotate();
    @Bind("shouldPerformSegueWithIdentifier:sender:") public native @Type("BOOL") boolean shouldPerformSegueWithIdentifier(@Type("NSString *") String identifier, @Type("id") NSObject sender);
    @Bind("supportedInterfaceOrientations") public native @Type("NSUInteger") int supportedInterfaceOrientations();
    @Bind("transitionFromViewController:toViewController:duration:options:animations:completion:") public native @Type("void") void transition(@Type("UIViewController *") UIViewController fromViewController, @Type("UIViewController *") UIViewController toViewController, @Type("NSTimeInterval") double duration, @Type("UIViewAnimationOptions") EnumSet<UIViewAnimationOption> options, @Type("void (^)(void)") VoidBlock animations, @Type("void (^)(BOOL finished)") VoidBooleanBlock completion);
    @Bind("updateViewConstraints") public native @Type("void") void updateViewConstraints();
    @Bind("viewControllerForUnwindSegueAction:fromViewController:withSender:") public native @Type("UIViewController *") UIViewController viewControllerForUnwindSegueAction(@Type("SEL") Selector action, @Type("UIViewController *") UIViewController fromViewController, @Type("id") NSObject sender);
    @Bind("viewDidAppear:") public native @Type("void") void viewDidAppear(@Type("BOOL") boolean animated);
    @Bind("viewDidDisappear:") public native @Type("void") void viewDidDisappear(@Type("BOOL") boolean animated);
    @Bind("viewDidLayoutSubviews") public native @Type("void") void viewDidLayoutSubviews();
    @Bind("viewDidLoad") public native @Type("void") void viewDidLoad();
    @Bind("viewWillAppear:") public native @Type("void") void viewWillAppear(@Type("BOOL") boolean animated);
    @Bind("viewWillDisappear:") public native @Type("void") void viewWillDisappear(@Type("BOOL") boolean animated);
    @Bind("viewWillLayoutSubviews") public native @Type("void") void viewWillLayoutSubviews();
    @Bind("willAnimateRotationToInterfaceOrientation:duration:") public native @Type("void") void willAnimateRotation(@Type("UIInterfaceOrientation") UIInterfaceOrientation interfaceOrientation, @Type("NSTimeInterval") double duration);
    @Bind("willMoveToParentViewController:") public native @Type("void") void willMoveToParentViewController(@Type("UIViewController *") UIViewController parent);
    @Bind("willRotateToInterfaceOrientation:duration:") public native @Type("void") void willRotate(@Type("UIInterfaceOrientation") UIInterfaceOrientation toInterfaceOrientation, @Type("NSTimeInterval") double duration);
    /*</methods>*/

}
