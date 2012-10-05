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
import org.robovm.cocoatouch.coredata.*;
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

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html">UIViewController Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIViewController /*</name>*/ 
    extends /*<extends>*/ UIResponder /*</extends>*/ 
    /*<implements>*/ implements UIAppearanceContainer /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIViewController /*</name>*/.class);
    }

    /*<constructors>*/
    public UIViewController() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/initWithNibName:bundle:">- (id)initWithNibName:(NSString *)nibName bundle:(NSBundle *)nibBundle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithNibName:bundle:") public UIViewController(@Type("NSString *") String nibName, @Type("NSBundle *") NSBundle nibBundle) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/childViewControllers">@property(nonatomic, readonly) NSArray *childViewControllers</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("childViewControllers") public native @Type("NSArray *") NSArray getChildViewControllers();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/contentSizeForViewInPopover">@property(nonatomic, readwrite) CGSize contentSizeForViewInPopover</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("contentSizeForViewInPopover") public native @Type("CGSize") CGSize getContentSizeForViewInPopover();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/contentSizeForViewInPopover">@property(nonatomic, readwrite) CGSize contentSizeForViewInPopover</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setContentSizeForViewInPopover:") public native void setContentSizeForViewInPopover(@Type("CGSize") CGSize v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/definesPresentationContext">@property(nonatomic, assign) BOOL definesPresentationContext</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("definesPresentationContext") public native @Type("BOOL") boolean isDefinesPresentationContext();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/definesPresentationContext">@property(nonatomic, assign) BOOL definesPresentationContext</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setDefinesPresentationContext:") public native void setDefinesPresentationContext(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isEditing") public native @Type("BOOL") boolean isEditing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setEditing:") public native void setEditing(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/hidesBottomBarWhenPushed">@property(nonatomic) BOOL hidesBottomBarWhenPushed</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("hidesBottomBarWhenPushed") public native @Type("BOOL") boolean isHidesBottomBarWhenPushed();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/hidesBottomBarWhenPushed">@property(nonatomic) BOOL hidesBottomBarWhenPushed</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setHidesBottomBarWhenPushed:") public native void setHidesBottomBarWhenPushed(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/interfaceOrientation">@property(nonatomic, readonly) UIInterfaceOrientation interfaceOrientation</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("interfaceOrientation") public native @Type("UIInterfaceOrientation") UIInterfaceOrientation getInterfaceOrientation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/modalInPopover">@property(nonatomic, readwrite, getter=isModalInPopover) BOOL modalInPopover</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("isModalInPopover") public native @Type("BOOL") boolean isModalInPopover();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/modalInPopover">@property(nonatomic, readwrite, getter=isModalInPopover) BOOL modalInPopover</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setModalInPopover:") public native void setModalInPopover(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/modalPresentationStyle">@property(nonatomic, assign) UIModalPresentationStyle modalPresentationStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("modalPresentationStyle") public native @Type("UIModalPresentationStyle") UIModalPresentationStyle getModalPresentationStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/modalPresentationStyle">@property(nonatomic, assign) UIModalPresentationStyle modalPresentationStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setModalPresentationStyle:") public native void setModalPresentationStyle(@Type("UIModalPresentationStyle") UIModalPresentationStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/modalTransitionStyle">@property(nonatomic, assign) UIModalTransitionStyle modalTransitionStyle</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("modalTransitionStyle") public native @Type("UIModalTransitionStyle") UIModalTransitionStyle getModalTransitionStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/modalTransitionStyle">@property(nonatomic, assign) UIModalTransitionStyle modalTransitionStyle</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setModalTransitionStyle:") public native void setModalTransitionStyle(@Type("UIModalTransitionStyle") UIModalTransitionStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/navigationController">@property(nonatomic, readonly, retain) UINavigationController *navigationController</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("navigationController") public native @Type("UINavigationController *") UINavigationController getNavigationController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/navigationItem">@property(nonatomic, readonly, retain) UINavigationItem *navigationItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("navigationItem") public native @Type("UINavigationItem *") UINavigationItem getNavigationItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/nibBundle">@property(nonatomic, readonly, retain) NSBundle *nibBundle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("nibBundle") public native @Type("NSBundle *") NSBundle getNibBundle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/nibName">@property(nonatomic, readonly, copy) NSString *nibName</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("nibName") public native @Type("NSString *") String getNibName();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/parentViewController">@property(nonatomic, readonly) UIViewController *parentViewController</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("parentViewController") public native @Type("UIViewController *") UIViewController getParentViewController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/presentedViewController">@property(nonatomic, readonly) UIViewController *presentedViewController</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("presentedViewController") public native @Type("UIViewController *") UIViewController getPresentedViewController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/presentingViewController">@property(nonatomic, readonly) UIViewController *presentingViewController</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("presentingViewController") public native @Type("UIViewController *") UIViewController getPresentingViewController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/providesPresentationContextTransitionStyle">@property(nonatomic, assign) BOOL providesPresentationContextTransitionStyle</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("providesPresentationContextTransitionStyle") public native @Type("BOOL") boolean isProvidesPresentationContextTransitionStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/providesPresentationContextTransitionStyle">@property(nonatomic, assign) BOOL providesPresentationContextTransitionStyle</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setProvidesPresentationContextTransitionStyle:") public native void setProvidesPresentationContextTransitionStyle(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/restorationClass">@property(nonatomic, readwrite, assign) Class&amp;lt;UIViewControllerRestoration&amp;gt; restorationClass</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("restorationClass") public native @Type("Class<UIViewControllerRestoration>") ObjCClass getRestorationClass();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/restorationClass">@property(nonatomic, readwrite, assign) Class&amp;lt;UIViewControllerRestoration&amp;gt; restorationClass</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setRestorationClass:") public native void setRestorationClass(@Type("Class<UIViewControllerRestoration>") ObjCClass v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/restorationIdentifier">@property(nonatomic, copy) NSString *restorationIdentifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("restorationIdentifier") public native @Type("NSString *") String getRestorationIdentifier();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/restorationIdentifier">@property(nonatomic, copy) NSString *restorationIdentifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setRestorationIdentifier:") public native void setRestorationIdentifier(@Type("NSString *") String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/searchDisplayController">@property(nonatomic, readonly, retain) UISearchDisplayController *searchDisplayController</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("searchDisplayController") public native @Type("UISearchDisplayController *") UISearchDisplayController getSearchDisplayController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/splitViewController">@property(nonatomic, readonly, retain) UISplitViewController *splitViewController</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("splitViewController") public native @Type("UISplitViewController *") UISplitViewController getSplitViewController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/storyboard">@property(nonatomic, readonly, retain) UIStoryboard *storyboard</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("storyboard") public native @Type("UIStoryboard *") UIStoryboard getStoryboard();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/tabBarController">@property(nonatomic, readonly, retain) UITabBarController *tabBarController</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tabBarController") public native @Type("UITabBarController *") UITabBarController getTabBarController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/tabBarItem">@property(nonatomic, retain) UITabBarItem *tabBarItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tabBarItem") public native @Type("UITabBarItem *") UITabBarItem getTabBarItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/tabBarItem">@property(nonatomic, retain) UITabBarItem *tabBarItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTabBarItem:") public native void setTabBarItem(@Type("UITabBarItem *") UITabBarItem v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("title") public native @Type("NSString *") String getTitle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTitle:") public native void setTitle(@Type("NSString *") String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/toolbarItems">@property(nonatomic, retain) NSArray *toolbarItems</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("toolbarItems") public native @Type("NSArray *") NSArray getToolbarItems();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/toolbarItems">@property(nonatomic, retain) NSArray *toolbarItems</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setToolbarItems:") public native void setToolbarItems(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/view">@property(nonatomic, retain) UIView *view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("view") public native @Type("UIView *") UIView getView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/view">@property(nonatomic, retain) UIView *view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setView:") public native void setView(@Type("UIView *") UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/wantsFullScreenLayout">@property(nonatomic, assign) BOOL wantsFullScreenLayout</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("wantsFullScreenLayout") public native @Type("BOOL") boolean isWantsFullScreenLayout();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/wantsFullScreenLayout">@property(nonatomic, assign) BOOL wantsFullScreenLayout</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setWantsFullScreenLayout:") public native void setWantsFullScreenLayout(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/clm/UIViewController/attemptRotationToDeviceOrientation">+ (void)attemptRotationToDeviceOrientation</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("attemptRotationToDeviceOrientation") public native static @Type("void") void attemptRotationToDeviceOrientation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/addChildViewController:">- (void)addChildViewController:(UIViewController *)childController</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("addChildViewController:") public native @Type("void") void addChildViewController(@Type("UIViewController *") UIViewController childController);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/beginAppearanceTransition:animated:">- (void)beginAppearanceTransition:(BOOL)isAppearing animated:(BOOL)animated</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("beginAppearanceTransition:animated:") public native @Type("void") void beginAppearanceTransition(@Type("BOOL") boolean isAppearing, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/canPerformUnwindSegueAction:fromViewController:withSender:">- (BOOL)canPerformUnwindSegueAction:(SEL)action fromViewController:(UIViewController *)fromViewController withSender:(id)sender</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("canPerformUnwindSegueAction:fromViewController:withSender:") public native @Type("BOOL") boolean canPerformUnwindSegueAction(@Type("SEL") Selector action, @Type("UIViewController *") UIViewController fromViewController, @Type("id") NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/decodeRestorableStateWithCoder:">- (void)decodeRestorableStateWithCoder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("decodeRestorableStateWithCoder:") public native @Type("void") void decodeRestorableState(@Type("NSCoder *") NSCoder coder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/didMoveToParentViewController:">- (void)didMoveToParentViewController:(UIViewController *)parent</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("didMoveToParentViewController:") public native @Type("void") void didMoveToParentViewController(@Type("UIViewController *") UIViewController parent);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/didReceiveMemoryWarning">- (void)didReceiveMemoryWarning</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("didReceiveMemoryWarning") public native @Type("void") void didReceiveMemoryWarning();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/didRotateFromInterfaceOrientation:">- (void)didRotateFromInterfaceOrientation:(UIInterfaceOrientation)fromInterfaceOrientation</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("didRotateFromInterfaceOrientation:") public native @Type("void") void didRotate(@Type("UIInterfaceOrientation") UIInterfaceOrientation fromInterfaceOrientation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/disablesAutomaticKeyboardDismissal">- (BOOL)disablesAutomaticKeyboardDismissal</a>
     * @since Available in iOS 4.3 and later.
     */
    @Bind("disablesAutomaticKeyboardDismissal") public native @Type("BOOL") boolean disablesAutomaticKeyboardDismissal();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/dismissViewControllerAnimated:completion:">- (void)dismissViewControllerAnimated:(BOOL)flag completion:(void (^)(void))completion</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("dismissViewControllerAnimated:completion:") public native @Type("void") void dismissViewController(@Type("BOOL") boolean flag, @Type("void (^)(void)") VoidBlock completion);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/encodeRestorableStateWithCoder:">- (void)encodeRestorableStateWithCoder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("encodeRestorableStateWithCoder:") public native @Type("void") void encodeRestorableState(@Type("NSCoder *") NSCoder coder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/endAppearanceTransition">- (void)endAppearanceTransition</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("endAppearanceTransition") public native @Type("void") void endAppearanceTransition();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/editButtonItem">- (UIBarButtonItem *)editButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("editButtonItem") public native @Type("UIBarButtonItem *") UIBarButtonItem getEditButtonItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/preferredInterfaceOrientationForPresentation">- (UIInterfaceOrientation)preferredInterfaceOrientationForPresentation</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("preferredInterfaceOrientationForPresentation") public native @Type("UIInterfaceOrientation") UIInterfaceOrientation getPreferredInterfaceOrientation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/rotatingFooterView">- (UIView *)rotatingFooterView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("rotatingFooterView") public native @Type("UIView *") UIView getRotatingFooterView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/rotatingHeaderView">- (UIView *)rotatingHeaderView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("rotatingHeaderView") public native @Type("UIView *") UIView getRotatingHeaderView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/supportedInterfaceOrientations">- (NSUInteger)supportedInterfaceOrientations</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("supportedInterfaceOrientations") public native @Type("NSUInteger") EnumSet<UIInterfaceOrientationMask> getSupportedInterfaceOrientations();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/segueForUnwindingToViewController:fromViewController:identifier:">- (UIStoryboardSegue *)segueForUnwindingToViewController:(UIViewController *)toViewController fromViewController:(UIViewController *)fromViewController identifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("segueForUnwindingToViewController:fromViewController:identifier:") public native @Type("UIStoryboardSegue *") UIStoryboardSegue getUnwindSegue(@Type("UIViewController *") UIViewController toViewController, @Type("UIViewController *") UIViewController fromViewController, @Type("NSString *") String identifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewControllerForUnwindSegueAction:fromViewController:withSender:">- (UIViewController *)viewControllerForUnwindSegueAction:(SEL)action fromViewController:(UIViewController *)fromViewController withSender:(id)sender</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("viewControllerForUnwindSegueAction:fromViewController:withSender:") public native @Type("UIViewController *") UIViewController getUnwindSegueActionViewController(@Type("SEL") Selector action, @Type("UIViewController *") UIViewController fromViewController, @Type("id") NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/isBeingDismissed">- (BOOL)isBeingDismissed</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("isBeingDismissed") public native @Type("BOOL") boolean isBeingDismissed();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/isBeingPresented">- (BOOL)isBeingPresented</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("isBeingPresented") public native @Type("BOOL") boolean isBeingPresented();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/isMovingFromParentViewController">- (BOOL)isMovingFromParentViewController</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("isMovingFromParentViewController") public native @Type("BOOL") boolean isMovingFromParentViewController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/isMovingToParentViewController">- (BOOL)isMovingToParentViewController</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("isMovingToParentViewController") public native @Type("BOOL") boolean isMovingToParentViewController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/isViewLoaded">- (BOOL)isViewLoaded</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isViewLoaded") public native @Type("BOOL") boolean isViewLoaded();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/loadView">- (void)loadView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("loadView") public native @Type("void") void loadView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/performSegueWithIdentifier:sender:">- (void)performSegueWithIdentifier:(NSString *)identifier sender:(id)sender</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("performSegueWithIdentifier:sender:") public native @Type("void") void performSegue(@Type("NSString *") String identifier, @Type("id") NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/prepareForSegue:sender:">- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("prepareForSegue:sender:") public native @Type("void") void prepareForSegue(@Type("UIStoryboardSegue *") UIStoryboardSegue segue, @Type("id") NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/presentViewController:animated:completion:">- (void)presentViewController:(UIViewController *)viewControllerToPresent animated:(BOOL)flag completion:(void (^)(void))completion</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("presentViewController:animated:completion:") public native @Type("void") void presentViewController(@Type("UIViewController *") UIViewController viewControllerToPresent, @Type("BOOL") boolean flag, @Type("void (^)(void)") VoidBlock completion);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/removeFromParentViewController">- (void)removeFromParentViewController</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("removeFromParentViewController") public native @Type("void") void removeFromParentViewController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/setEditing:animated:">- (void)setEditing:(BOOL)editing animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setEditing:animated:") public native @Type("void") void setEditing(@Type("BOOL") boolean editing, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/setToolbarItems:animated:">- (void)setToolbarItems:(NSArray *)toolbarItems animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setToolbarItems:animated:") public native @Type("void") void setToolbarItems(@Type("NSArray *") NSArray toolbarItems, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/shouldAutomaticallyForwardAppearanceMethods">- (BOOL)shouldAutomaticallyForwardAppearanceMethods</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("shouldAutomaticallyForwardAppearanceMethods") public native @Type("BOOL") boolean shouldAutomaticallyForwardAppearanceMethods();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/shouldAutomaticallyForwardRotationMethods">- (BOOL)shouldAutomaticallyForwardRotationMethods</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("shouldAutomaticallyForwardRotationMethods") public native @Type("BOOL") boolean shouldAutomaticallyForwardRotationMethods();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/shouldAutorotate">- (BOOL)shouldAutorotate</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("shouldAutorotate") public native @Type("BOOL") boolean shouldAutorotate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/shouldPerformSegueWithIdentifier:sender:">- (BOOL)shouldPerformSegueWithIdentifier:(NSString *)identifier sender:(id)sender</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("shouldPerformSegueWithIdentifier:sender:") public native @Type("BOOL") boolean shouldPerformSegue(@Type("NSString *") String identifier, @Type("id") NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/transitionFromViewController:toViewController:duration:options:animations:completion:">- (void)transitionFromViewController:(UIViewController *)fromViewController toViewController:(UIViewController *)toViewController duration:(NSTimeInterval)duration options:(UIViewAnimationOptions)options animations:(void (^)(void))animations completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("transitionFromViewController:toViewController:duration:options:animations:completion:") public native @Type("void") void transition(@Type("UIViewController *") UIViewController fromViewController, @Type("UIViewController *") UIViewController toViewController, @Type("NSTimeInterval") double duration, @Type("UIViewAnimationOptions") EnumSet<UIViewAnimationOption> options, @Type("void (^)(void)") VoidBlock animations, @Type("void (^)(BOOL finished)") VoidBooleanBlock completion);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/updateViewConstraints">- (void)updateViewConstraints</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("updateViewConstraints") public native @Type("void") void updateViewConstraints();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewDidAppear:">- (void)viewDidAppear:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("viewDidAppear:") public native @Type("void") void viewDidAppear(@Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewDidDisappear:">- (void)viewDidDisappear:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("viewDidDisappear:") public native @Type("void") void viewDidDisappear(@Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewDidLayoutSubviews">- (void)viewDidLayoutSubviews</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("viewDidLayoutSubviews") public native @Type("void") void viewDidLayoutSubviews();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewDidLoad">- (void)viewDidLoad</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("viewDidLoad") public native @Type("void") void viewDidLoad();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewWillAppear:">- (void)viewWillAppear:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("viewWillAppear:") public native @Type("void") void viewWillAppear(@Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewWillDisappear:">- (void)viewWillDisappear:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("viewWillDisappear:") public native @Type("void") void viewWillDisappear(@Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewWillLayoutSubviews">- (void)viewWillLayoutSubviews</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("viewWillLayoutSubviews") public native @Type("void") void viewWillLayoutSubviews();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/willAnimateRotationToInterfaceOrientation:duration:">- (void)willAnimateRotationToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation duration:(NSTimeInterval)duration</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("willAnimateRotationToInterfaceOrientation:duration:") public native @Type("void") void willAnimateRotation(@Type("UIInterfaceOrientation") UIInterfaceOrientation interfaceOrientation, @Type("NSTimeInterval") double duration);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/willMoveToParentViewController:">- (void)willMoveToParentViewController:(UIViewController *)parent</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("willMoveToParentViewController:") public native @Type("void") void willMoveToParent(@Type("UIViewController *") UIViewController parent);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/willRotateToInterfaceOrientation:duration:">- (void)willRotateToInterfaceOrientation:(UIInterfaceOrientation)toInterfaceOrientation duration:(NSTimeInterval)duration</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("willRotateToInterfaceOrientation:duration:") public native @Type("void") void willRotate(@Type("UIInterfaceOrientation") UIInterfaceOrientation toInterfaceOrientation, @Type("NSTimeInterval") double duration);
    /*</methods>*/

}
