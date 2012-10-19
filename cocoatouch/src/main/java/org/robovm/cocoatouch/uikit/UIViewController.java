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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIViewController /*</name>*/.class);

    /*<constructors>*/
    protected UIViewController(SkipInit skipInit) { super(skipInit); }
    public UIViewController() {}
    
    private static final Selector initWithNibName$bundle$ = Selector.register("initWithNibName:bundle:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithNibName(UIViewController __self__, Selector __cmd__, String nibName, NSBundle nibBundle);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/initWithNibName:bundle:">- (id)initWithNibName:(NSString *)nibName bundle:(NSBundle *)nibBundle</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIViewController(String nibName, NSBundle nibBundle) {
        super((SkipInit) null);
        objc_initWithNibName(this, initWithNibName$bundle$, nibName, nibBundle);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/childViewControllers">@property(nonatomic, readonly) NSArray *childViewControllers</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("childViewControllers") public native NSArray getChildViewControllers();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/contentSizeForViewInPopover">@property(nonatomic, readwrite) CGSize contentSizeForViewInPopover</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("contentSizeForViewInPopover") public native CGSize getContentSizeForViewInPopover();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/contentSizeForViewInPopover">@property(nonatomic, readwrite) CGSize contentSizeForViewInPopover</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setContentSizeForViewInPopover:") public native void setContentSizeForViewInPopover(CGSize v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/definesPresentationContext">@property(nonatomic, assign) BOOL definesPresentationContext</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("definesPresentationContext") public native boolean isDefinesPresentationContext();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/definesPresentationContext">@property(nonatomic, assign) BOOL definesPresentationContext</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setDefinesPresentationContext:") public native void setDefinesPresentationContext(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isEditing") public native boolean isEditing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setEditing:") public native void setEditing(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/hidesBottomBarWhenPushed">@property(nonatomic) BOOL hidesBottomBarWhenPushed</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("hidesBottomBarWhenPushed") public native boolean isHidesBottomBarWhenPushed();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/hidesBottomBarWhenPushed">@property(nonatomic) BOOL hidesBottomBarWhenPushed</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setHidesBottomBarWhenPushed:") public native void setHidesBottomBarWhenPushed(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/interfaceOrientation">@property(nonatomic, readonly) UIInterfaceOrientation interfaceOrientation</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("interfaceOrientation") public native UIInterfaceOrientation getInterfaceOrientation();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/modalInPopover">@property(nonatomic, readwrite, getter=isModalInPopover) BOOL modalInPopover</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("isModalInPopover") public native boolean isModalInPopover();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/modalInPopover">@property(nonatomic, readwrite, getter=isModalInPopover) BOOL modalInPopover</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setModalInPopover:") public native void setModalInPopover(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/modalPresentationStyle">@property(nonatomic, assign) UIModalPresentationStyle modalPresentationStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("modalPresentationStyle") public native UIModalPresentationStyle getModalPresentationStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/modalPresentationStyle">@property(nonatomic, assign) UIModalPresentationStyle modalPresentationStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setModalPresentationStyle:") public native void setModalPresentationStyle(UIModalPresentationStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/modalTransitionStyle">@property(nonatomic, assign) UIModalTransitionStyle modalTransitionStyle</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("modalTransitionStyle") public native UIModalTransitionStyle getModalTransitionStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/modalTransitionStyle">@property(nonatomic, assign) UIModalTransitionStyle modalTransitionStyle</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setModalTransitionStyle:") public native void setModalTransitionStyle(UIModalTransitionStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/navigationController">@property(nonatomic, readonly, retain) UINavigationController *navigationController</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("navigationController") public native UINavigationController getNavigationController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/navigationItem">@property(nonatomic, readonly, retain) UINavigationItem *navigationItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("navigationItem") public native UINavigationItem getNavigationItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/nibBundle">@property(nonatomic, readonly, retain) NSBundle *nibBundle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("nibBundle") public native NSBundle getNibBundle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/nibName">@property(nonatomic, readonly, copy) NSString *nibName</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("nibName") public native String getNibName();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/parentViewController">@property(nonatomic, readonly) UIViewController *parentViewController</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("parentViewController") public native UIViewController getParentViewController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/presentedViewController">@property(nonatomic, readonly) UIViewController *presentedViewController</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("presentedViewController") public native UIViewController getPresentedViewController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/presentingViewController">@property(nonatomic, readonly) UIViewController *presentingViewController</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("presentingViewController") public native UIViewController getPresentingViewController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/providesPresentationContextTransitionStyle">@property(nonatomic, assign) BOOL providesPresentationContextTransitionStyle</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("providesPresentationContextTransitionStyle") public native boolean isProvidesPresentationContextTransitionStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/providesPresentationContextTransitionStyle">@property(nonatomic, assign) BOOL providesPresentationContextTransitionStyle</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setProvidesPresentationContextTransitionStyle:") public native void setProvidesPresentationContextTransitionStyle(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/restorationClass">@property(nonatomic, readwrite, assign) Class&amp;lt;UIViewControllerRestoration&amp;gt; restorationClass</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("restorationClass") public native ObjCClass getRestorationClass();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/restorationClass">@property(nonatomic, readwrite, assign) Class&amp;lt;UIViewControllerRestoration&amp;gt; restorationClass</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setRestorationClass:") public native void setRestorationClass(ObjCClass v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/restorationIdentifier">@property(nonatomic, copy) NSString *restorationIdentifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("restorationIdentifier") public native String getRestorationIdentifier();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/restorationIdentifier">@property(nonatomic, copy) NSString *restorationIdentifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setRestorationIdentifier:") public native void setRestorationIdentifier(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/searchDisplayController">@property(nonatomic, readonly, retain) UISearchDisplayController *searchDisplayController</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("searchDisplayController") public native UISearchDisplayController getSearchDisplayController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/splitViewController">@property(nonatomic, readonly, retain) UISplitViewController *splitViewController</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("splitViewController") public native UISplitViewController getSplitViewController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/storyboard">@property(nonatomic, readonly, retain) UIStoryboard *storyboard</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("storyboard") public native UIStoryboard getStoryboard();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/tabBarController">@property(nonatomic, readonly, retain) UITabBarController *tabBarController</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tabBarController") public native UITabBarController getTabBarController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/tabBarItem">@property(nonatomic, retain) UITabBarItem *tabBarItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tabBarItem") public native UITabBarItem getTabBarItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/tabBarItem">@property(nonatomic, retain) UITabBarItem *tabBarItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTabBarItem:") public native void setTabBarItem(UITabBarItem v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("title") public native String getTitle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTitle:") public native void setTitle(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/toolbarItems">@property(nonatomic, retain) NSArray *toolbarItems</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("toolbarItems") public native NSArray getToolbarItems();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/toolbarItems">@property(nonatomic, retain) NSArray *toolbarItems</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setToolbarItems:") public native void setToolbarItems(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/view">@property(nonatomic, retain) UIView *view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("view") public native UIView getView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/view">@property(nonatomic, retain) UIView *view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setView:") public native void setView(UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/wantsFullScreenLayout">@property(nonatomic, assign) BOOL wantsFullScreenLayout</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("wantsFullScreenLayout") public native boolean isWantsFullScreenLayout();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/wantsFullScreenLayout">@property(nonatomic, assign) BOOL wantsFullScreenLayout</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setWantsFullScreenLayout:") public native void setWantsFullScreenLayout(boolean v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector attemptRotationToDeviceOrientation = Selector.register("attemptRotationToDeviceOrientation");
    @Bridge(symbol = "objc_msgSend") private native static void objc_attemptRotationToDeviceOrientation(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/clm/UIViewController/attemptRotationToDeviceOrientation">+ (void)attemptRotationToDeviceOrientation</a>
     * @since Available in iOS 5.0 and later.
     */
    public static void attemptRotationToDeviceOrientation() {
        objc_attemptRotationToDeviceOrientation(objCClass, attemptRotationToDeviceOrientation);
    }
    
    private static final Selector addChildViewController$ = Selector.register("addChildViewController:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_addChildViewController(UIViewController __self__, Selector __cmd__, UIViewController childController);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_addChildViewControllerSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, UIViewController childController);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/addChildViewController:">- (void)addChildViewController:(UIViewController *)childController</a>
     * @since Available in iOS 5.0 and later.
     */
    public void addChildViewController(UIViewController childController) {
        if (customClass) { objc_addChildViewControllerSuper(getSuper(), this, addChildViewController$, childController); } else { objc_addChildViewController(this, addChildViewController$, childController); }
    }
    
    private static final Selector beginAppearanceTransition$animated$ = Selector.register("beginAppearanceTransition:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_beginAppearanceTransition(UIViewController __self__, Selector __cmd__, boolean isAppearing, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_beginAppearanceTransitionSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, boolean isAppearing, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/beginAppearanceTransition:animated:">- (void)beginAppearanceTransition:(BOOL)isAppearing animated:(BOOL)animated</a>
     * @since Available in iOS 6.0 and later.
     */
    public void beginAppearanceTransition(boolean isAppearing, boolean animated) {
        if (customClass) { objc_beginAppearanceTransitionSuper(getSuper(), this, beginAppearanceTransition$animated$, isAppearing, animated); } else { objc_beginAppearanceTransition(this, beginAppearanceTransition$animated$, isAppearing, animated); }
    }
    
    private static final Selector canPerformUnwindSegueAction$fromViewController$withSender$ = Selector.register("canPerformUnwindSegueAction:fromViewController:withSender:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_canPerformUnwindSegueAction(UIViewController __self__, Selector __cmd__, Selector action, UIViewController fromViewController, NSObject sender);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_canPerformUnwindSegueActionSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, Selector action, UIViewController fromViewController, NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/canPerformUnwindSegueAction:fromViewController:withSender:">- (BOOL)canPerformUnwindSegueAction:(SEL)action fromViewController:(UIViewController *)fromViewController withSender:(id)sender</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean canPerformUnwindSegueAction(Selector action, UIViewController fromViewController, NSObject sender) {
        if (customClass) { return objc_canPerformUnwindSegueActionSuper(getSuper(), this, canPerformUnwindSegueAction$fromViewController$withSender$, action, fromViewController, sender); } else { return objc_canPerformUnwindSegueAction(this, canPerformUnwindSegueAction$fromViewController$withSender$, action, fromViewController, sender); }
    }
    
    private static final Selector decodeRestorableStateWithCoder$ = Selector.register("decodeRestorableStateWithCoder:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_decodeRestorableState(UIViewController __self__, Selector __cmd__, NSCoder coder);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_decodeRestorableStateSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, NSCoder coder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/decodeRestorableStateWithCoder:">- (void)decodeRestorableStateWithCoder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    public void decodeRestorableState(NSCoder coder) {
        if (customClass) { objc_decodeRestorableStateSuper(getSuper(), this, decodeRestorableStateWithCoder$, coder); } else { objc_decodeRestorableState(this, decodeRestorableStateWithCoder$, coder); }
    }
    
    private static final Selector didMoveToParentViewController$ = Selector.register("didMoveToParentViewController:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_didMoveToParentViewController(UIViewController __self__, Selector __cmd__, UIViewController parent);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_didMoveToParentViewControllerSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, UIViewController parent);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/didMoveToParentViewController:">- (void)didMoveToParentViewController:(UIViewController *)parent</a>
     * @since Available in iOS 5.0 and later.
     */
    public void didMoveToParentViewController(UIViewController parent) {
        if (customClass) { objc_didMoveToParentViewControllerSuper(getSuper(), this, didMoveToParentViewController$, parent); } else { objc_didMoveToParentViewController(this, didMoveToParentViewController$, parent); }
    }
    
    private static final Selector didReceiveMemoryWarning = Selector.register("didReceiveMemoryWarning");
    @Bridge(symbol = "objc_msgSend") private native static void objc_didReceiveMemoryWarning(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_didReceiveMemoryWarningSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/didReceiveMemoryWarning">- (void)didReceiveMemoryWarning</a>
     * @since Available in iOS 2.0 and later.
     */
    public void didReceiveMemoryWarning() {
        if (customClass) { objc_didReceiveMemoryWarningSuper(getSuper(), this, didReceiveMemoryWarning); } else { objc_didReceiveMemoryWarning(this, didReceiveMemoryWarning); }
    }
    
    private static final Selector didRotateFromInterfaceOrientation$ = Selector.register("didRotateFromInterfaceOrientation:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_didRotate(UIViewController __self__, Selector __cmd__, UIInterfaceOrientation fromInterfaceOrientation);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_didRotateSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, UIInterfaceOrientation fromInterfaceOrientation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/didRotateFromInterfaceOrientation:">- (void)didRotateFromInterfaceOrientation:(UIInterfaceOrientation)fromInterfaceOrientation</a>
     * @since Available in iOS 2.0 and later.
     */
    public void didRotate(UIInterfaceOrientation fromInterfaceOrientation) {
        if (customClass) { objc_didRotateSuper(getSuper(), this, didRotateFromInterfaceOrientation$, fromInterfaceOrientation); } else { objc_didRotate(this, didRotateFromInterfaceOrientation$, fromInterfaceOrientation); }
    }
    
    private static final Selector disablesAutomaticKeyboardDismissal = Selector.register("disablesAutomaticKeyboardDismissal");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_disablesAutomaticKeyboardDismissal(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_disablesAutomaticKeyboardDismissalSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/disablesAutomaticKeyboardDismissal">- (BOOL)disablesAutomaticKeyboardDismissal</a>
     * @since Available in iOS 4.3 and later.
     */
    public boolean disablesAutomaticKeyboardDismissal() {
        if (customClass) { return objc_disablesAutomaticKeyboardDismissalSuper(getSuper(), this, disablesAutomaticKeyboardDismissal); } else { return objc_disablesAutomaticKeyboardDismissal(this, disablesAutomaticKeyboardDismissal); }
    }
    
    private static final Selector dismissViewControllerAnimated$completion$ = Selector.register("dismissViewControllerAnimated:completion:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_dismissViewController(UIViewController __self__, Selector __cmd__, boolean flag, VoidBlock completion);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_dismissViewControllerSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, boolean flag, VoidBlock completion);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/dismissViewControllerAnimated:completion:">- (void)dismissViewControllerAnimated:(BOOL)flag completion:(void (^)(void))completion</a>
     * @since Available in iOS 5.0 and later.
     */
    public void dismissViewController(boolean flag, VoidBlock completion) {
        if (customClass) { objc_dismissViewControllerSuper(getSuper(), this, dismissViewControllerAnimated$completion$, flag, completion); } else { objc_dismissViewController(this, dismissViewControllerAnimated$completion$, flag, completion); }
    }
    
    private static final Selector encodeRestorableStateWithCoder$ = Selector.register("encodeRestorableStateWithCoder:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_encodeRestorableState(UIViewController __self__, Selector __cmd__, NSCoder coder);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_encodeRestorableStateSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, NSCoder coder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/encodeRestorableStateWithCoder:">- (void)encodeRestorableStateWithCoder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    public void encodeRestorableState(NSCoder coder) {
        if (customClass) { objc_encodeRestorableStateSuper(getSuper(), this, encodeRestorableStateWithCoder$, coder); } else { objc_encodeRestorableState(this, encodeRestorableStateWithCoder$, coder); }
    }
    
    private static final Selector endAppearanceTransition = Selector.register("endAppearanceTransition");
    @Bridge(symbol = "objc_msgSend") private native static void objc_endAppearanceTransition(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_endAppearanceTransitionSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/endAppearanceTransition">- (void)endAppearanceTransition</a>
     * @since Available in iOS 6.0 and later.
     */
    public void endAppearanceTransition() {
        if (customClass) { objc_endAppearanceTransitionSuper(getSuper(), this, endAppearanceTransition); } else { objc_endAppearanceTransition(this, endAppearanceTransition); }
    }
    
    private static final Selector editButtonItem = Selector.register("editButtonItem");
    @Bridge(symbol = "objc_msgSend") private native static UIBarButtonItem objc_getEditButtonItem(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIBarButtonItem objc_getEditButtonItemSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/editButtonItem">- (UIBarButtonItem *)editButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarButtonItem getEditButtonItem() {
        if (customClass) { return objc_getEditButtonItemSuper(getSuper(), this, editButtonItem); } else { return objc_getEditButtonItem(this, editButtonItem); }
    }
    
    private static final Selector preferredInterfaceOrientationForPresentation = Selector.register("preferredInterfaceOrientationForPresentation");
    @Bridge(symbol = "objc_msgSend") private native static UIInterfaceOrientation objc_getPreferredInterfaceOrientation(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIInterfaceOrientation objc_getPreferredInterfaceOrientationSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/preferredInterfaceOrientationForPresentation">- (UIInterfaceOrientation)preferredInterfaceOrientationForPresentation</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIInterfaceOrientation getPreferredInterfaceOrientation() {
        if (customClass) { return objc_getPreferredInterfaceOrientationSuper(getSuper(), this, preferredInterfaceOrientationForPresentation); } else { return objc_getPreferredInterfaceOrientation(this, preferredInterfaceOrientationForPresentation); }
    }
    
    private static final Selector rotatingFooterView = Selector.register("rotatingFooterView");
    @Bridge(symbol = "objc_msgSend") private native static UIView objc_getRotatingFooterView(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIView objc_getRotatingFooterViewSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/rotatingFooterView">- (UIView *)rotatingFooterView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getRotatingFooterView() {
        if (customClass) { return objc_getRotatingFooterViewSuper(getSuper(), this, rotatingFooterView); } else { return objc_getRotatingFooterView(this, rotatingFooterView); }
    }
    
    private static final Selector rotatingHeaderView = Selector.register("rotatingHeaderView");
    @Bridge(symbol = "objc_msgSend") private native static UIView objc_getRotatingHeaderView(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIView objc_getRotatingHeaderViewSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/rotatingHeaderView">- (UIView *)rotatingHeaderView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getRotatingHeaderView() {
        if (customClass) { return objc_getRotatingHeaderViewSuper(getSuper(), this, rotatingHeaderView); } else { return objc_getRotatingHeaderView(this, rotatingHeaderView); }
    }
    
    private static final Selector supportedInterfaceOrientations = Selector.register("supportedInterfaceOrientations");
    @Bridge(symbol = "objc_msgSend") private native static int objc_getSupportedInterfaceOrientations(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_getSupportedInterfaceOrientationsSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/supportedInterfaceOrientations">- (NSUInteger)supportedInterfaceOrientations</a>
     * @since Available in iOS 6.0 and later.
     */
    public int getSupportedInterfaceOrientations() {
        if (customClass) { return objc_getSupportedInterfaceOrientationsSuper(getSuper(), this, supportedInterfaceOrientations); } else { return objc_getSupportedInterfaceOrientations(this, supportedInterfaceOrientations); }
    }
    
    private static final Selector segueForUnwindingToViewController$fromViewController$identifier$ = Selector.register("segueForUnwindingToViewController:fromViewController:identifier:");
    @Bridge(symbol = "objc_msgSend") private native static UIStoryboardSegue objc_getUnwindSegue(UIViewController __self__, Selector __cmd__, UIViewController toViewController, UIViewController fromViewController, String identifier);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIStoryboardSegue objc_getUnwindSegueSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, UIViewController toViewController, UIViewController fromViewController, String identifier);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/segueForUnwindingToViewController:fromViewController:identifier:">- (UIStoryboardSegue *)segueForUnwindingToViewController:(UIViewController *)toViewController fromViewController:(UIViewController *)fromViewController identifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIStoryboardSegue getUnwindSegue(UIViewController toViewController, UIViewController fromViewController, String identifier) {
        if (customClass) { return objc_getUnwindSegueSuper(getSuper(), this, segueForUnwindingToViewController$fromViewController$identifier$, toViewController, fromViewController, identifier); } else { return objc_getUnwindSegue(this, segueForUnwindingToViewController$fromViewController$identifier$, toViewController, fromViewController, identifier); }
    }
    
    private static final Selector viewControllerForUnwindSegueAction$fromViewController$withSender$ = Selector.register("viewControllerForUnwindSegueAction:fromViewController:withSender:");
    @Bridge(symbol = "objc_msgSend") private native static UIViewController objc_getUnwindSegueActionViewController(UIViewController __self__, Selector __cmd__, Selector action, UIViewController fromViewController, NSObject sender);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIViewController objc_getUnwindSegueActionViewControllerSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, Selector action, UIViewController fromViewController, NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewControllerForUnwindSegueAction:fromViewController:withSender:">- (UIViewController *)viewControllerForUnwindSegueAction:(SEL)action fromViewController:(UIViewController *)fromViewController withSender:(id)sender</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIViewController getUnwindSegueActionViewController(Selector action, UIViewController fromViewController, NSObject sender) {
        if (customClass) { return objc_getUnwindSegueActionViewControllerSuper(getSuper(), this, viewControllerForUnwindSegueAction$fromViewController$withSender$, action, fromViewController, sender); } else { return objc_getUnwindSegueActionViewController(this, viewControllerForUnwindSegueAction$fromViewController$withSender$, action, fromViewController, sender); }
    }
    
    private static final Selector isBeingDismissed = Selector.register("isBeingDismissed");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isBeingDismissed(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isBeingDismissedSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/isBeingDismissed">- (BOOL)isBeingDismissed</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isBeingDismissed() {
        if (customClass) { return objc_isBeingDismissedSuper(getSuper(), this, isBeingDismissed); } else { return objc_isBeingDismissed(this, isBeingDismissed); }
    }
    
    private static final Selector isBeingPresented = Selector.register("isBeingPresented");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isBeingPresented(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isBeingPresentedSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/isBeingPresented">- (BOOL)isBeingPresented</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isBeingPresented() {
        if (customClass) { return objc_isBeingPresentedSuper(getSuper(), this, isBeingPresented); } else { return objc_isBeingPresented(this, isBeingPresented); }
    }
    
    private static final Selector isMovingFromParentViewController = Selector.register("isMovingFromParentViewController");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isMovingFromParentViewController(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isMovingFromParentViewControllerSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/isMovingFromParentViewController">- (BOOL)isMovingFromParentViewController</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isMovingFromParentViewController() {
        if (customClass) { return objc_isMovingFromParentViewControllerSuper(getSuper(), this, isMovingFromParentViewController); } else { return objc_isMovingFromParentViewController(this, isMovingFromParentViewController); }
    }
    
    private static final Selector isMovingToParentViewController = Selector.register("isMovingToParentViewController");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isMovingToParentViewController(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isMovingToParentViewControllerSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/isMovingToParentViewController">- (BOOL)isMovingToParentViewController</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isMovingToParentViewController() {
        if (customClass) { return objc_isMovingToParentViewControllerSuper(getSuper(), this, isMovingToParentViewController); } else { return objc_isMovingToParentViewController(this, isMovingToParentViewController); }
    }
    
    private static final Selector isViewLoaded = Selector.register("isViewLoaded");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isViewLoaded(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isViewLoadedSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/isViewLoaded">- (BOOL)isViewLoaded</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isViewLoaded() {
        if (customClass) { return objc_isViewLoadedSuper(getSuper(), this, isViewLoaded); } else { return objc_isViewLoaded(this, isViewLoaded); }
    }
    
    private static final Selector loadView = Selector.register("loadView");
    @Bridge(symbol = "objc_msgSend") private native static void objc_loadView(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_loadViewSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/loadView">- (void)loadView</a>
     * @since Available in iOS 2.0 and later.
     */
    public void loadView() {
        if (customClass) { objc_loadViewSuper(getSuper(), this, loadView); } else { objc_loadView(this, loadView); }
    }
    
    private static final Selector performSegueWithIdentifier$sender$ = Selector.register("performSegueWithIdentifier:sender:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_performSegue(UIViewController __self__, Selector __cmd__, String identifier, NSObject sender);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_performSegueSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, String identifier, NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/performSegueWithIdentifier:sender:">- (void)performSegueWithIdentifier:(NSString *)identifier sender:(id)sender</a>
     * @since Available in iOS 5.0 and later.
     */
    public void performSegue(String identifier, NSObject sender) {
        if (customClass) { objc_performSegueSuper(getSuper(), this, performSegueWithIdentifier$sender$, identifier, sender); } else { objc_performSegue(this, performSegueWithIdentifier$sender$, identifier, sender); }
    }
    
    private static final Selector prepareForSegue$sender$ = Selector.register("prepareForSegue:sender:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_prepareForSegue(UIViewController __self__, Selector __cmd__, UIStoryboardSegue segue, NSObject sender);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_prepareForSegueSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, UIStoryboardSegue segue, NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/prepareForSegue:sender:">- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender</a>
     * @since Available in iOS 5.0 and later.
     */
    public void prepareForSegue(UIStoryboardSegue segue, NSObject sender) {
        if (customClass) { objc_prepareForSegueSuper(getSuper(), this, prepareForSegue$sender$, segue, sender); } else { objc_prepareForSegue(this, prepareForSegue$sender$, segue, sender); }
    }
    
    private static final Selector presentViewController$animated$completion$ = Selector.register("presentViewController:animated:completion:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_presentViewController(UIViewController __self__, Selector __cmd__, UIViewController viewControllerToPresent, boolean flag, VoidBlock completion);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_presentViewControllerSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, UIViewController viewControllerToPresent, boolean flag, VoidBlock completion);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/presentViewController:animated:completion:">- (void)presentViewController:(UIViewController *)viewControllerToPresent animated:(BOOL)flag completion:(void (^)(void))completion</a>
     * @since Available in iOS 5.0 and later.
     */
    public void presentViewController(UIViewController viewControllerToPresent, boolean flag, VoidBlock completion) {
        if (customClass) { objc_presentViewControllerSuper(getSuper(), this, presentViewController$animated$completion$, viewControllerToPresent, flag, completion); } else { objc_presentViewController(this, presentViewController$animated$completion$, viewControllerToPresent, flag, completion); }
    }
    
    private static final Selector removeFromParentViewController = Selector.register("removeFromParentViewController");
    @Bridge(symbol = "objc_msgSend") private native static void objc_removeFromParentViewController(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_removeFromParentViewControllerSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/removeFromParentViewController">- (void)removeFromParentViewController</a>
     * @since Available in iOS 5.0 and later.
     */
    public void removeFromParentViewController() {
        if (customClass) { objc_removeFromParentViewControllerSuper(getSuper(), this, removeFromParentViewController); } else { objc_removeFromParentViewController(this, removeFromParentViewController); }
    }
    
    private static final Selector setEditing$animated$ = Selector.register("setEditing:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setEditing(UIViewController __self__, Selector __cmd__, boolean editing, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setEditingSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, boolean editing, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/setEditing:animated:">- (void)setEditing:(BOOL)editing animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setEditing(boolean editing, boolean animated) {
        if (customClass) { objc_setEditingSuper(getSuper(), this, setEditing$animated$, editing, animated); } else { objc_setEditing(this, setEditing$animated$, editing, animated); }
    }
    
    private static final Selector setToolbarItems$animated$ = Selector.register("setToolbarItems:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setToolbarItems(UIViewController __self__, Selector __cmd__, NSArray toolbarItems, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setToolbarItemsSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, NSArray toolbarItems, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/setToolbarItems:animated:">- (void)setToolbarItems:(NSArray *)toolbarItems animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setToolbarItems(NSArray toolbarItems, boolean animated) {
        if (customClass) { objc_setToolbarItemsSuper(getSuper(), this, setToolbarItems$animated$, toolbarItems, animated); } else { objc_setToolbarItems(this, setToolbarItems$animated$, toolbarItems, animated); }
    }
    
    private static final Selector shouldAutomaticallyForwardAppearanceMethods = Selector.register("shouldAutomaticallyForwardAppearanceMethods");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_shouldAutomaticallyForwardAppearanceMethods(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_shouldAutomaticallyForwardAppearanceMethodsSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/shouldAutomaticallyForwardAppearanceMethods">- (BOOL)shouldAutomaticallyForwardAppearanceMethods</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean shouldAutomaticallyForwardAppearanceMethods() {
        if (customClass) { return objc_shouldAutomaticallyForwardAppearanceMethodsSuper(getSuper(), this, shouldAutomaticallyForwardAppearanceMethods); } else { return objc_shouldAutomaticallyForwardAppearanceMethods(this, shouldAutomaticallyForwardAppearanceMethods); }
    }
    
    private static final Selector shouldAutomaticallyForwardRotationMethods = Selector.register("shouldAutomaticallyForwardRotationMethods");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_shouldAutomaticallyForwardRotationMethods(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_shouldAutomaticallyForwardRotationMethodsSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/shouldAutomaticallyForwardRotationMethods">- (BOOL)shouldAutomaticallyForwardRotationMethods</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean shouldAutomaticallyForwardRotationMethods() {
        if (customClass) { return objc_shouldAutomaticallyForwardRotationMethodsSuper(getSuper(), this, shouldAutomaticallyForwardRotationMethods); } else { return objc_shouldAutomaticallyForwardRotationMethods(this, shouldAutomaticallyForwardRotationMethods); }
    }
    
    private static final Selector shouldAutorotate = Selector.register("shouldAutorotate");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_shouldAutorotate(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_shouldAutorotateSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/shouldAutorotate">- (BOOL)shouldAutorotate</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean shouldAutorotate() {
        if (customClass) { return objc_shouldAutorotateSuper(getSuper(), this, shouldAutorotate); } else { return objc_shouldAutorotate(this, shouldAutorotate); }
    }
    
    private static final Selector shouldPerformSegueWithIdentifier$sender$ = Selector.register("shouldPerformSegueWithIdentifier:sender:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_shouldPerformSegue(UIViewController __self__, Selector __cmd__, String identifier, NSObject sender);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_shouldPerformSegueSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, String identifier, NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/shouldPerformSegueWithIdentifier:sender:">- (BOOL)shouldPerformSegueWithIdentifier:(NSString *)identifier sender:(id)sender</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean shouldPerformSegue(String identifier, NSObject sender) {
        if (customClass) { return objc_shouldPerformSegueSuper(getSuper(), this, shouldPerformSegueWithIdentifier$sender$, identifier, sender); } else { return objc_shouldPerformSegue(this, shouldPerformSegueWithIdentifier$sender$, identifier, sender); }
    }
    
    private static final Selector transitionFromViewController$toViewController$duration$options$animations$completion$ = Selector.register("transitionFromViewController:toViewController:duration:options:animations:completion:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_transition(UIViewController __self__, Selector __cmd__, UIViewController fromViewController, UIViewController toViewController, double duration, UIViewAnimationOptions options, VoidBlock animations, VoidBooleanBlock completion);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_transitionSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, UIViewController fromViewController, UIViewController toViewController, double duration, UIViewAnimationOptions options, VoidBlock animations, VoidBooleanBlock completion);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/transitionFromViewController:toViewController:duration:options:animations:completion:">- (void)transitionFromViewController:(UIViewController *)fromViewController toViewController:(UIViewController *)toViewController duration:(NSTimeInterval)duration options:(UIViewAnimationOptions)options animations:(void (^)(void))animations completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 5.0 and later.
     */
    public void transition(UIViewController fromViewController, UIViewController toViewController, double duration, UIViewAnimationOptions options, VoidBlock animations, VoidBooleanBlock completion) {
        if (customClass) { objc_transitionSuper(getSuper(), this, transitionFromViewController$toViewController$duration$options$animations$completion$, fromViewController, toViewController, duration, options, animations, completion); } else { objc_transition(this, transitionFromViewController$toViewController$duration$options$animations$completion$, fromViewController, toViewController, duration, options, animations, completion); }
    }
    
    private static final Selector updateViewConstraints = Selector.register("updateViewConstraints");
    @Bridge(symbol = "objc_msgSend") private native static void objc_updateViewConstraints(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_updateViewConstraintsSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/updateViewConstraints">- (void)updateViewConstraints</a>
     * @since Available in iOS 6.0 and later.
     */
    public void updateViewConstraints() {
        if (customClass) { objc_updateViewConstraintsSuper(getSuper(), this, updateViewConstraints); } else { objc_updateViewConstraints(this, updateViewConstraints); }
    }
    
    private static final Selector viewDidAppear$ = Selector.register("viewDidAppear:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_viewDidAppear(UIViewController __self__, Selector __cmd__, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_viewDidAppearSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewDidAppear:">- (void)viewDidAppear:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void viewDidAppear(boolean animated) {
        if (customClass) { objc_viewDidAppearSuper(getSuper(), this, viewDidAppear$, animated); } else { objc_viewDidAppear(this, viewDidAppear$, animated); }
    }
    
    private static final Selector viewDidDisappear$ = Selector.register("viewDidDisappear:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_viewDidDisappear(UIViewController __self__, Selector __cmd__, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_viewDidDisappearSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewDidDisappear:">- (void)viewDidDisappear:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void viewDidDisappear(boolean animated) {
        if (customClass) { objc_viewDidDisappearSuper(getSuper(), this, viewDidDisappear$, animated); } else { objc_viewDidDisappear(this, viewDidDisappear$, animated); }
    }
    
    private static final Selector viewDidLayoutSubviews = Selector.register("viewDidLayoutSubviews");
    @Bridge(symbol = "objc_msgSend") private native static void objc_viewDidLayoutSubviews(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_viewDidLayoutSubviewsSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewDidLayoutSubviews">- (void)viewDidLayoutSubviews</a>
     * @since Available in iOS 5.0 and later.
     */
    public void viewDidLayoutSubviews() {
        if (customClass) { objc_viewDidLayoutSubviewsSuper(getSuper(), this, viewDidLayoutSubviews); } else { objc_viewDidLayoutSubviews(this, viewDidLayoutSubviews); }
    }
    
    private static final Selector viewDidLoad = Selector.register("viewDidLoad");
    @Bridge(symbol = "objc_msgSend") private native static void objc_viewDidLoad(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_viewDidLoadSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewDidLoad">- (void)viewDidLoad</a>
     * @since Available in iOS 2.0 and later.
     */
    public void viewDidLoad() {
        if (customClass) { objc_viewDidLoadSuper(getSuper(), this, viewDidLoad); } else { objc_viewDidLoad(this, viewDidLoad); }
    }
    
    private static final Selector viewWillAppear$ = Selector.register("viewWillAppear:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_viewWillAppear(UIViewController __self__, Selector __cmd__, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_viewWillAppearSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewWillAppear:">- (void)viewWillAppear:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void viewWillAppear(boolean animated) {
        if (customClass) { objc_viewWillAppearSuper(getSuper(), this, viewWillAppear$, animated); } else { objc_viewWillAppear(this, viewWillAppear$, animated); }
    }
    
    private static final Selector viewWillDisappear$ = Selector.register("viewWillDisappear:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_viewWillDisappear(UIViewController __self__, Selector __cmd__, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_viewWillDisappearSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewWillDisappear:">- (void)viewWillDisappear:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void viewWillDisappear(boolean animated) {
        if (customClass) { objc_viewWillDisappearSuper(getSuper(), this, viewWillDisappear$, animated); } else { objc_viewWillDisappear(this, viewWillDisappear$, animated); }
    }
    
    private static final Selector viewWillLayoutSubviews = Selector.register("viewWillLayoutSubviews");
    @Bridge(symbol = "objc_msgSend") private native static void objc_viewWillLayoutSubviews(UIViewController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_viewWillLayoutSubviewsSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewWillLayoutSubviews">- (void)viewWillLayoutSubviews</a>
     * @since Available in iOS 5.0 and later.
     */
    public void viewWillLayoutSubviews() {
        if (customClass) { objc_viewWillLayoutSubviewsSuper(getSuper(), this, viewWillLayoutSubviews); } else { objc_viewWillLayoutSubviews(this, viewWillLayoutSubviews); }
    }
    
    private static final Selector willAnimateRotationToInterfaceOrientation$duration$ = Selector.register("willAnimateRotationToInterfaceOrientation:duration:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_willAnimateRotation(UIViewController __self__, Selector __cmd__, UIInterfaceOrientation interfaceOrientation, double duration);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_willAnimateRotationSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, UIInterfaceOrientation interfaceOrientation, double duration);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/willAnimateRotationToInterfaceOrientation:duration:">- (void)willAnimateRotationToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation duration:(NSTimeInterval)duration</a>
     * @since Available in iOS 3.0 and later.
     */
    public void willAnimateRotation(UIInterfaceOrientation interfaceOrientation, double duration) {
        if (customClass) { objc_willAnimateRotationSuper(getSuper(), this, willAnimateRotationToInterfaceOrientation$duration$, interfaceOrientation, duration); } else { objc_willAnimateRotation(this, willAnimateRotationToInterfaceOrientation$duration$, interfaceOrientation, duration); }
    }
    
    private static final Selector willMoveToParentViewController$ = Selector.register("willMoveToParentViewController:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_willMoveToParent(UIViewController __self__, Selector __cmd__, UIViewController parent);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_willMoveToParentSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, UIViewController parent);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/willMoveToParentViewController:">- (void)willMoveToParentViewController:(UIViewController *)parent</a>
     * @since Available in iOS 5.0 and later.
     */
    public void willMoveToParent(UIViewController parent) {
        if (customClass) { objc_willMoveToParentSuper(getSuper(), this, willMoveToParentViewController$, parent); } else { objc_willMoveToParent(this, willMoveToParentViewController$, parent); }
    }
    
    private static final Selector willRotateToInterfaceOrientation$duration$ = Selector.register("willRotateToInterfaceOrientation:duration:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_willRotate(UIViewController __self__, Selector __cmd__, UIInterfaceOrientation toInterfaceOrientation, double duration);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_willRotateSuper(ObjCSuper __super__, UIViewController __self__, Selector __cmd__, UIInterfaceOrientation toInterfaceOrientation, double duration);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/willRotateToInterfaceOrientation:duration:">- (void)willRotateToInterfaceOrientation:(UIInterfaceOrientation)toInterfaceOrientation duration:(NSTimeInterval)duration</a>
     * @since Available in iOS 2.0 and later.
     */
    public void willRotate(UIInterfaceOrientation toInterfaceOrientation, double duration) {
        if (customClass) { objc_willRotateSuper(getSuper(), this, willRotateToInterfaceOrientation$duration$, toInterfaceOrientation, duration); } else { objc_willRotate(this, willRotateToInterfaceOrientation$duration$, toInterfaceOrientation, duration); }
    }
    /*</methods>*/

}
