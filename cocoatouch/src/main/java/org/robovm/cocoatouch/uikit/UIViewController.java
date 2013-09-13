/*
 * Copyright (C) 2012 Trillian AB
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
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html">UIViewController Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIViewController /*</name>*/ 
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
    @Bridge private native static @Pointer long objc_initWithNibName(UIViewController __self__, Selector __cmd__, String nibName, NSBundle nibBundle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/initWithNibName:bundle:">- (id)initWithNibName:(NSString *)nibName bundle:(NSBundle *)nibBundle</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIViewController(String nibName, NSBundle nibBundle) {
        super((SkipInit) null);
        initObject(objc_initWithNibName(this, initWithNibName$bundle$, nibName, nibBundle));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector childViewControllers = Selector.register("childViewControllers");
    @Bridge private native static NSArray objc_getChildViewControllers(UIViewController __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getChildViewControllersSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/childViewControllers">@property(nonatomic, readonly) NSArray *childViewControllers</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSArray getChildViewControllers() {
        if (customClass) { return objc_getChildViewControllersSuper(getSuper(), childViewControllers); } else { return objc_getChildViewControllers(this, childViewControllers); }
    }
    
    private static final Selector contentSizeForViewInPopover = Selector.register("contentSizeForViewInPopover");
    @Bridge private native static @ByVal CGSize objc_getContentSizeForViewInPopover(UIViewController __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGSize objc_getContentSizeForViewInPopoverSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/contentSizeForViewInPopover">@property(nonatomic, readwrite) CGSize contentSizeForViewInPopover</a>
     * @since Available in iOS 3.2 and later.
     */
    public CGSize getContentSizeForViewInPopover() {
        if (customClass) { return objc_getContentSizeForViewInPopoverSuper(getSuper(), contentSizeForViewInPopover); } else { return objc_getContentSizeForViewInPopover(this, contentSizeForViewInPopover); }
    }
    
    private static final Selector setContentSizeForViewInPopover$ = Selector.register("setContentSizeForViewInPopover:");
    @Bridge private native static void objc_setContentSizeForViewInPopover(UIViewController __self__, Selector __cmd__, @ByVal CGSize contentSizeForViewInPopover);
    @Bridge private native static void objc_setContentSizeForViewInPopoverSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGSize contentSizeForViewInPopover);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/contentSizeForViewInPopover">@property(nonatomic, readwrite) CGSize contentSizeForViewInPopover</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setContentSizeForViewInPopover(CGSize contentSizeForViewInPopover) {
        if (customClass) { objc_setContentSizeForViewInPopoverSuper(getSuper(), setContentSizeForViewInPopover$, contentSizeForViewInPopover); } else { objc_setContentSizeForViewInPopover(this, setContentSizeForViewInPopover$, contentSizeForViewInPopover); }
    }
    
    private static final Selector definesPresentationContext = Selector.register("definesPresentationContext");
    @Bridge private native static boolean objc_isDefinesPresentationContext(UIViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isDefinesPresentationContextSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/definesPresentationContext">@property(nonatomic, assign) BOOL definesPresentationContext</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isDefinesPresentationContext() {
        if (customClass) { return objc_isDefinesPresentationContextSuper(getSuper(), definesPresentationContext); } else { return objc_isDefinesPresentationContext(this, definesPresentationContext); }
    }
    
    private static final Selector setDefinesPresentationContext$ = Selector.register("setDefinesPresentationContext:");
    @Bridge private native static void objc_setDefinesPresentationContext(UIViewController __self__, Selector __cmd__, boolean definesPresentationContext);
    @Bridge private native static void objc_setDefinesPresentationContextSuper(ObjCSuper __super__, Selector __cmd__, boolean definesPresentationContext);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/definesPresentationContext">@property(nonatomic, assign) BOOL definesPresentationContext</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setDefinesPresentationContext(boolean definesPresentationContext) {
        if (customClass) { objc_setDefinesPresentationContextSuper(getSuper(), setDefinesPresentationContext$, definesPresentationContext); } else { objc_setDefinesPresentationContext(this, setDefinesPresentationContext$, definesPresentationContext); }
    }
    
    private static final Selector isEditing = Selector.register("isEditing");
    @Bridge private native static boolean objc_isEditing(UIViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isEditingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isEditing() {
        if (customClass) { return objc_isEditingSuper(getSuper(), isEditing); } else { return objc_isEditing(this, isEditing); }
    }
    
    private static final Selector setEditing$ = Selector.register("setEditing:");
    @Bridge private native static void objc_setEditing(UIViewController __self__, Selector __cmd__, boolean editing);
    @Bridge private native static void objc_setEditingSuper(ObjCSuper __super__, Selector __cmd__, boolean editing);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/editing">@property(nonatomic, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setEditing(boolean editing) {
        if (customClass) { objc_setEditingSuper(getSuper(), setEditing$, editing); } else { objc_setEditing(this, setEditing$, editing); }
    }
    
    private static final Selector hidesBottomBarWhenPushed = Selector.register("hidesBottomBarWhenPushed");
    @Bridge private native static boolean objc_isHidesBottomBarWhenPushed(UIViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isHidesBottomBarWhenPushedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/hidesBottomBarWhenPushed">@property(nonatomic) BOOL hidesBottomBarWhenPushed</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isHidesBottomBarWhenPushed() {
        if (customClass) { return objc_isHidesBottomBarWhenPushedSuper(getSuper(), hidesBottomBarWhenPushed); } else { return objc_isHidesBottomBarWhenPushed(this, hidesBottomBarWhenPushed); }
    }
    
    private static final Selector setHidesBottomBarWhenPushed$ = Selector.register("setHidesBottomBarWhenPushed:");
    @Bridge private native static void objc_setHidesBottomBarWhenPushed(UIViewController __self__, Selector __cmd__, boolean hidesBottomBarWhenPushed);
    @Bridge private native static void objc_setHidesBottomBarWhenPushedSuper(ObjCSuper __super__, Selector __cmd__, boolean hidesBottomBarWhenPushed);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/hidesBottomBarWhenPushed">@property(nonatomic) BOOL hidesBottomBarWhenPushed</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setHidesBottomBarWhenPushed(boolean hidesBottomBarWhenPushed) {
        if (customClass) { objc_setHidesBottomBarWhenPushedSuper(getSuper(), setHidesBottomBarWhenPushed$, hidesBottomBarWhenPushed); } else { objc_setHidesBottomBarWhenPushed(this, setHidesBottomBarWhenPushed$, hidesBottomBarWhenPushed); }
    }
    
    private static final Selector interfaceOrientation = Selector.register("interfaceOrientation");
    @Bridge private native static UIInterfaceOrientation objc_getInterfaceOrientation(UIViewController __self__, Selector __cmd__);
    @Bridge private native static UIInterfaceOrientation objc_getInterfaceOrientationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/interfaceOrientation">@property(nonatomic, readonly) UIInterfaceOrientation interfaceOrientation</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIInterfaceOrientation getInterfaceOrientation() {
        if (customClass) { return objc_getInterfaceOrientationSuper(getSuper(), interfaceOrientation); } else { return objc_getInterfaceOrientation(this, interfaceOrientation); }
    }
    
    private static final Selector isModalInPopover = Selector.register("isModalInPopover");
    @Bridge private native static boolean objc_isModalInPopover(UIViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isModalInPopoverSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/modalInPopover">@property(nonatomic, readwrite, getter=isModalInPopover) BOOL modalInPopover</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean isModalInPopover() {
        if (customClass) { return objc_isModalInPopoverSuper(getSuper(), isModalInPopover); } else { return objc_isModalInPopover(this, isModalInPopover); }
    }
    
    private static final Selector setModalInPopover$ = Selector.register("setModalInPopover:");
    @Bridge private native static void objc_setModalInPopover(UIViewController __self__, Selector __cmd__, boolean modalInPopover);
    @Bridge private native static void objc_setModalInPopoverSuper(ObjCSuper __super__, Selector __cmd__, boolean modalInPopover);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/modalInPopover">@property(nonatomic, readwrite, getter=isModalInPopover) BOOL modalInPopover</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setModalInPopover(boolean modalInPopover) {
        if (customClass) { objc_setModalInPopoverSuper(getSuper(), setModalInPopover$, modalInPopover); } else { objc_setModalInPopover(this, setModalInPopover$, modalInPopover); }
    }
    
    private static final Selector modalPresentationStyle = Selector.register("modalPresentationStyle");
    @Bridge private native static UIModalPresentationStyle objc_getModalPresentationStyle(UIViewController __self__, Selector __cmd__);
    @Bridge private native static UIModalPresentationStyle objc_getModalPresentationStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/modalPresentationStyle">@property(nonatomic, assign) UIModalPresentationStyle modalPresentationStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIModalPresentationStyle getModalPresentationStyle() {
        if (customClass) { return objc_getModalPresentationStyleSuper(getSuper(), modalPresentationStyle); } else { return objc_getModalPresentationStyle(this, modalPresentationStyle); }
    }
    
    private static final Selector setModalPresentationStyle$ = Selector.register("setModalPresentationStyle:");
    @Bridge private native static void objc_setModalPresentationStyle(UIViewController __self__, Selector __cmd__, UIModalPresentationStyle modalPresentationStyle);
    @Bridge private native static void objc_setModalPresentationStyleSuper(ObjCSuper __super__, Selector __cmd__, UIModalPresentationStyle modalPresentationStyle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/modalPresentationStyle">@property(nonatomic, assign) UIModalPresentationStyle modalPresentationStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setModalPresentationStyle(UIModalPresentationStyle modalPresentationStyle) {
        if (customClass) { objc_setModalPresentationStyleSuper(getSuper(), setModalPresentationStyle$, modalPresentationStyle); } else { objc_setModalPresentationStyle(this, setModalPresentationStyle$, modalPresentationStyle); }
    }
    
    private static final Selector modalTransitionStyle = Selector.register("modalTransitionStyle");
    @Bridge private native static UIModalTransitionStyle objc_getModalTransitionStyle(UIViewController __self__, Selector __cmd__);
    @Bridge private native static UIModalTransitionStyle objc_getModalTransitionStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/modalTransitionStyle">@property(nonatomic, assign) UIModalTransitionStyle modalTransitionStyle</a>
     * @since Available in iOS 3.0 and later.
     */
    public UIModalTransitionStyle getModalTransitionStyle() {
        if (customClass) { return objc_getModalTransitionStyleSuper(getSuper(), modalTransitionStyle); } else { return objc_getModalTransitionStyle(this, modalTransitionStyle); }
    }
    
    private static final Selector setModalTransitionStyle$ = Selector.register("setModalTransitionStyle:");
    @Bridge private native static void objc_setModalTransitionStyle(UIViewController __self__, Selector __cmd__, UIModalTransitionStyle modalTransitionStyle);
    @Bridge private native static void objc_setModalTransitionStyleSuper(ObjCSuper __super__, Selector __cmd__, UIModalTransitionStyle modalTransitionStyle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/modalTransitionStyle">@property(nonatomic, assign) UIModalTransitionStyle modalTransitionStyle</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setModalTransitionStyle(UIModalTransitionStyle modalTransitionStyle) {
        if (customClass) { objc_setModalTransitionStyleSuper(getSuper(), setModalTransitionStyle$, modalTransitionStyle); } else { objc_setModalTransitionStyle(this, setModalTransitionStyle$, modalTransitionStyle); }
    }
    
    private static final Selector navigationController = Selector.register("navigationController");
    @Bridge private native static UINavigationController objc_getNavigationController(UIViewController __self__, Selector __cmd__);
    @Bridge private native static UINavigationController objc_getNavigationControllerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/navigationController">@property(nonatomic, readonly, retain) UINavigationController *navigationController</a>
     * @since Available in iOS 2.0 and later.
     */
    public UINavigationController getNavigationController() {
        if (customClass) { return objc_getNavigationControllerSuper(getSuper(), navigationController); } else { return objc_getNavigationController(this, navigationController); }
    }
    
    private static final Selector navigationItem = Selector.register("navigationItem");
    @Bridge private native static UINavigationItem objc_getNavigationItem(UIViewController __self__, Selector __cmd__);
    @Bridge private native static UINavigationItem objc_getNavigationItemSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/navigationItem">@property(nonatomic, readonly, retain) UINavigationItem *navigationItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public UINavigationItem getNavigationItem() {
        if (customClass) { return objc_getNavigationItemSuper(getSuper(), navigationItem); } else { return objc_getNavigationItem(this, navigationItem); }
    }
    
    private static final Selector nibBundle = Selector.register("nibBundle");
    @Bridge private native static NSBundle objc_getNibBundle(UIViewController __self__, Selector __cmd__);
    @Bridge private native static NSBundle objc_getNibBundleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/nibBundle">@property(nonatomic, readonly, retain) NSBundle *nibBundle</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSBundle getNibBundle() {
        if (customClass) { return objc_getNibBundleSuper(getSuper(), nibBundle); } else { return objc_getNibBundle(this, nibBundle); }
    }
    
    private static final Selector nibName = Selector.register("nibName");
    @Bridge private native static String objc_getNibName(UIViewController __self__, Selector __cmd__);
    @Bridge private native static String objc_getNibNameSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/nibName">@property(nonatomic, readonly, copy) NSString *nibName</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getNibName() {
        if (customClass) { return objc_getNibNameSuper(getSuper(), nibName); } else { return objc_getNibName(this, nibName); }
    }
    
    private static final Selector parentViewController = Selector.register("parentViewController");
    @Bridge private native static UIViewController objc_getParentViewController(UIViewController __self__, Selector __cmd__);
    @Bridge private native static UIViewController objc_getParentViewControllerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/parentViewController">@property(nonatomic, readonly) UIViewController *parentViewController</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIViewController getParentViewController() {
        if (customClass) { return objc_getParentViewControllerSuper(getSuper(), parentViewController); } else { return objc_getParentViewController(this, parentViewController); }
    }
    
    private static final Selector presentedViewController = Selector.register("presentedViewController");
    @Bridge private native static UIViewController objc_getPresentedViewController(UIViewController __self__, Selector __cmd__);
    @Bridge private native static UIViewController objc_getPresentedViewControllerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/presentedViewController">@property(nonatomic, readonly) UIViewController *presentedViewController</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIViewController getPresentedViewController() {
        if (customClass) { return objc_getPresentedViewControllerSuper(getSuper(), presentedViewController); } else { return objc_getPresentedViewController(this, presentedViewController); }
    }
    
    private static final Selector presentingViewController = Selector.register("presentingViewController");
    @Bridge private native static UIViewController objc_getPresentingViewController(UIViewController __self__, Selector __cmd__);
    @Bridge private native static UIViewController objc_getPresentingViewControllerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/presentingViewController">@property(nonatomic, readonly) UIViewController *presentingViewController</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIViewController getPresentingViewController() {
        if (customClass) { return objc_getPresentingViewControllerSuper(getSuper(), presentingViewController); } else { return objc_getPresentingViewController(this, presentingViewController); }
    }
    
    private static final Selector providesPresentationContextTransitionStyle = Selector.register("providesPresentationContextTransitionStyle");
    @Bridge private native static boolean objc_isProvidesPresentationContextTransitionStyle(UIViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isProvidesPresentationContextTransitionStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/providesPresentationContextTransitionStyle">@property(nonatomic, assign) BOOL providesPresentationContextTransitionStyle</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isProvidesPresentationContextTransitionStyle() {
        if (customClass) { return objc_isProvidesPresentationContextTransitionStyleSuper(getSuper(), providesPresentationContextTransitionStyle); } else { return objc_isProvidesPresentationContextTransitionStyle(this, providesPresentationContextTransitionStyle); }
    }
    
    private static final Selector setProvidesPresentationContextTransitionStyle$ = Selector.register("setProvidesPresentationContextTransitionStyle:");
    @Bridge private native static void objc_setProvidesPresentationContextTransitionStyle(UIViewController __self__, Selector __cmd__, boolean providesPresentationContextTransitionStyle);
    @Bridge private native static void objc_setProvidesPresentationContextTransitionStyleSuper(ObjCSuper __super__, Selector __cmd__, boolean providesPresentationContextTransitionStyle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/providesPresentationContextTransitionStyle">@property(nonatomic, assign) BOOL providesPresentationContextTransitionStyle</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setProvidesPresentationContextTransitionStyle(boolean providesPresentationContextTransitionStyle) {
        if (customClass) { objc_setProvidesPresentationContextTransitionStyleSuper(getSuper(), setProvidesPresentationContextTransitionStyle$, providesPresentationContextTransitionStyle); } else { objc_setProvidesPresentationContextTransitionStyle(this, setProvidesPresentationContextTransitionStyle$, providesPresentationContextTransitionStyle); }
    }
    
    private static final Selector restorationClass = Selector.register("restorationClass");
    @Bridge private native static ObjCClass objc_getRestorationClass(UIViewController __self__, Selector __cmd__);
    @Bridge private native static ObjCClass objc_getRestorationClassSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/restorationClass">@property(nonatomic, readwrite, assign) Class&amp;lt;UIViewControllerRestoration&amp;gt; restorationClass</a>
     * @since Available in iOS 6.0 and later.
     */
    public ObjCClass getRestorationClass() {
        if (customClass) { return objc_getRestorationClassSuper(getSuper(), restorationClass); } else { return objc_getRestorationClass(this, restorationClass); }
    }
    
    private static final Selector setRestorationClass$ = Selector.register("setRestorationClass:");
    @Bridge private native static void objc_setRestorationClass(UIViewController __self__, Selector __cmd__, ObjCClass restorationClass);
    @Bridge private native static void objc_setRestorationClassSuper(ObjCSuper __super__, Selector __cmd__, ObjCClass restorationClass);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/restorationClass">@property(nonatomic, readwrite, assign) Class&amp;lt;UIViewControllerRestoration&amp;gt; restorationClass</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setRestorationClass(ObjCClass restorationClass) {
        if (customClass) { objc_setRestorationClassSuper(getSuper(), setRestorationClass$, restorationClass); } else { objc_setRestorationClass(this, setRestorationClass$, restorationClass); }
    }
    
    private static final Selector restorationIdentifier = Selector.register("restorationIdentifier");
    @Bridge private native static String objc_getRestorationIdentifier(UIViewController __self__, Selector __cmd__);
    @Bridge private native static String objc_getRestorationIdentifierSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/restorationIdentifier">@property(nonatomic, copy) NSString *restorationIdentifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public String getRestorationIdentifier() {
        if (customClass) { return objc_getRestorationIdentifierSuper(getSuper(), restorationIdentifier); } else { return objc_getRestorationIdentifier(this, restorationIdentifier); }
    }
    
    private static final Selector setRestorationIdentifier$ = Selector.register("setRestorationIdentifier:");
    @Bridge private native static void objc_setRestorationIdentifier(UIViewController __self__, Selector __cmd__, String restorationIdentifier);
    @Bridge private native static void objc_setRestorationIdentifierSuper(ObjCSuper __super__, Selector __cmd__, String restorationIdentifier);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/restorationIdentifier">@property(nonatomic, copy) NSString *restorationIdentifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setRestorationIdentifier(String restorationIdentifier) {
        if (customClass) { objc_setRestorationIdentifierSuper(getSuper(), setRestorationIdentifier$, restorationIdentifier); } else { objc_setRestorationIdentifier(this, setRestorationIdentifier$, restorationIdentifier); }
    }
    
    private static final Selector searchDisplayController = Selector.register("searchDisplayController");
    @Bridge private native static UISearchDisplayController objc_getSearchDisplayController(UIViewController __self__, Selector __cmd__);
    @Bridge private native static UISearchDisplayController objc_getSearchDisplayControllerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/searchDisplayController">@property(nonatomic, readonly, retain) UISearchDisplayController *searchDisplayController</a>
     * @since Available in iOS 3.0 and later.
     */
    public UISearchDisplayController getSearchDisplayController() {
        if (customClass) { return objc_getSearchDisplayControllerSuper(getSuper(), searchDisplayController); } else { return objc_getSearchDisplayController(this, searchDisplayController); }
    }
    
    private static final Selector splitViewController = Selector.register("splitViewController");
    @Bridge private native static UISplitViewController objc_getSplitViewController(UIViewController __self__, Selector __cmd__);
    @Bridge private native static UISplitViewController objc_getSplitViewControllerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/splitViewController">@property(nonatomic, readonly, retain) UISplitViewController *splitViewController</a>
     * @since Available in iOS 3.2 and later.
     */
    public UISplitViewController getSplitViewController() {
        if (customClass) { return objc_getSplitViewControllerSuper(getSuper(), splitViewController); } else { return objc_getSplitViewController(this, splitViewController); }
    }
    
    private static final Selector storyboard = Selector.register("storyboard");
    @Bridge private native static UIStoryboard objc_getStoryboard(UIViewController __self__, Selector __cmd__);
    @Bridge private native static UIStoryboard objc_getStoryboardSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/storyboard">@property(nonatomic, readonly, retain) UIStoryboard *storyboard</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIStoryboard getStoryboard() {
        if (customClass) { return objc_getStoryboardSuper(getSuper(), storyboard); } else { return objc_getStoryboard(this, storyboard); }
    }
    
    private static final Selector tabBarController = Selector.register("tabBarController");
    @Bridge private native static UITabBarController objc_getTabBarController(UIViewController __self__, Selector __cmd__);
    @Bridge private native static UITabBarController objc_getTabBarControllerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/tabBarController">@property(nonatomic, readonly, retain) UITabBarController *tabBarController</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITabBarController getTabBarController() {
        if (customClass) { return objc_getTabBarControllerSuper(getSuper(), tabBarController); } else { return objc_getTabBarController(this, tabBarController); }
    }
    
    private static final Selector tabBarItem = Selector.register("tabBarItem");
    @Bridge private native static UITabBarItem objc_getTabBarItem(UIViewController __self__, Selector __cmd__);
    @Bridge private native static UITabBarItem objc_getTabBarItemSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/tabBarItem">@property(nonatomic, retain) UITabBarItem *tabBarItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITabBarItem getTabBarItem() {
        if (customClass) { return objc_getTabBarItemSuper(getSuper(), tabBarItem); } else { return objc_getTabBarItem(this, tabBarItem); }
    }
    
    private static final Selector setTabBarItem$ = Selector.register("setTabBarItem:");
    @Bridge private native static void objc_setTabBarItem(UIViewController __self__, Selector __cmd__, UITabBarItem tabBarItem);
    @Bridge private native static void objc_setTabBarItemSuper(ObjCSuper __super__, Selector __cmd__, UITabBarItem tabBarItem);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/tabBarItem">@property(nonatomic, retain) UITabBarItem *tabBarItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTabBarItem(UITabBarItem tabBarItem) {
        if (customClass) { objc_setTabBarItemSuper(getSuper(), setTabBarItem$, tabBarItem); } else { objc_setTabBarItem(this, setTabBarItem$, tabBarItem); }
    }
    
    private static final Selector title = Selector.register("title");
    @Bridge private native static String objc_getTitle(UIViewController __self__, Selector __cmd__);
    @Bridge private native static String objc_getTitleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getTitle() {
        if (customClass) { return objc_getTitleSuper(getSuper(), title); } else { return objc_getTitle(this, title); }
    }
    
    private static final Selector setTitle$ = Selector.register("setTitle:");
    @Bridge private native static void objc_setTitle(UIViewController __self__, Selector __cmd__, String title);
    @Bridge private native static void objc_setTitleSuper(ObjCSuper __super__, Selector __cmd__, String title);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTitle(String title) {
        if (customClass) { objc_setTitleSuper(getSuper(), setTitle$, title); } else { objc_setTitle(this, setTitle$, title); }
    }
    
    private static final Selector toolbarItems = Selector.register("toolbarItems");
    @Bridge private native static NSArray objc_getToolbarItems(UIViewController __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getToolbarItemsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/toolbarItems">@property(nonatomic, retain) NSArray *toolbarItems</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSArray getToolbarItems() {
        if (customClass) { return objc_getToolbarItemsSuper(getSuper(), toolbarItems); } else { return objc_getToolbarItems(this, toolbarItems); }
    }
    
    private static final Selector setToolbarItems$ = Selector.register("setToolbarItems:");
    @Bridge private native static void objc_setToolbarItems(UIViewController __self__, Selector __cmd__, NSArray toolbarItems);
    @Bridge private native static void objc_setToolbarItemsSuper(ObjCSuper __super__, Selector __cmd__, NSArray toolbarItems);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/toolbarItems">@property(nonatomic, retain) NSArray *toolbarItems</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setToolbarItems(NSArray toolbarItems) {
        if (customClass) { objc_setToolbarItemsSuper(getSuper(), setToolbarItems$, toolbarItems); } else { objc_setToolbarItems(this, setToolbarItems$, toolbarItems); }
    }
    
    private static final Selector view = Selector.register("view");
    @Bridge private native static UIView objc_getView(UIViewController __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/view">@property(nonatomic, retain) UIView *view</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getView() {
        if (customClass) { return objc_getViewSuper(getSuper(), view); } else { return objc_getView(this, view); }
    }
    
    private static final Selector setView$ = Selector.register("setView:");
    @Bridge private native static void objc_setView(UIViewController __self__, Selector __cmd__, UIView view);
    @Bridge private native static void objc_setViewSuper(ObjCSuper __super__, Selector __cmd__, UIView view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/view">@property(nonatomic, retain) UIView *view</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setView(UIView view) {
        if (customClass) { objc_setViewSuper(getSuper(), setView$, view); } else { objc_setView(this, setView$, view); }
    }
    
    private static final Selector wantsFullScreenLayout = Selector.register("wantsFullScreenLayout");
    @Bridge private native static boolean objc_isWantsFullScreenLayout(UIViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isWantsFullScreenLayoutSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/wantsFullScreenLayout">@property(nonatomic, assign) BOOL wantsFullScreenLayout</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isWantsFullScreenLayout() {
        if (customClass) { return objc_isWantsFullScreenLayoutSuper(getSuper(), wantsFullScreenLayout); } else { return objc_isWantsFullScreenLayout(this, wantsFullScreenLayout); }
    }
    
    private static final Selector setWantsFullScreenLayout$ = Selector.register("setWantsFullScreenLayout:");
    @Bridge private native static void objc_setWantsFullScreenLayout(UIViewController __self__, Selector __cmd__, boolean wantsFullScreenLayout);
    @Bridge private native static void objc_setWantsFullScreenLayoutSuper(ObjCSuper __super__, Selector __cmd__, boolean wantsFullScreenLayout);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UIViewController/wantsFullScreenLayout">@property(nonatomic, assign) BOOL wantsFullScreenLayout</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setWantsFullScreenLayout(boolean wantsFullScreenLayout) {
        if (customClass) { objc_setWantsFullScreenLayoutSuper(getSuper(), setWantsFullScreenLayout$, wantsFullScreenLayout); } else { objc_setWantsFullScreenLayout(this, setWantsFullScreenLayout$, wantsFullScreenLayout); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector attemptRotationToDeviceOrientation = Selector.register("attemptRotationToDeviceOrientation");
    @Bridge private native static void objc_attemptRotationToDeviceOrientation(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/clm/UIViewController/attemptRotationToDeviceOrientation">+ (void)attemptRotationToDeviceOrientation</a>
     * @since Available in iOS 5.0 and later.
     */
    public static void attemptRotationToDeviceOrientation() {
        objc_attemptRotationToDeviceOrientation(objCClass, attemptRotationToDeviceOrientation);
    }
    
    private static final Selector addChildViewController$ = Selector.register("addChildViewController:");
    @Bridge private native static void objc_addChildViewController(UIViewController __self__, Selector __cmd__, UIViewController childController);
    @Bridge private native static void objc_addChildViewControllerSuper(ObjCSuper __super__, Selector __cmd__, UIViewController childController);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/addChildViewController:">- (void)addChildViewController:(UIViewController *)childController</a>
     * @since Available in iOS 5.0 and later.
     */
    public void addChildViewController(UIViewController childController) {
        if (customClass) { objc_addChildViewControllerSuper(getSuper(), addChildViewController$, childController); } else { objc_addChildViewController(this, addChildViewController$, childController); }
    }
    
    private static final Selector beginAppearanceTransition$animated$ = Selector.register("beginAppearanceTransition:animated:");
    @Bridge private native static void objc_beginAppearanceTransition(UIViewController __self__, Selector __cmd__, boolean isAppearing, boolean animated);
    @Bridge private native static void objc_beginAppearanceTransitionSuper(ObjCSuper __super__, Selector __cmd__, boolean isAppearing, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/beginAppearanceTransition:animated:">- (void)beginAppearanceTransition:(BOOL)isAppearing animated:(BOOL)animated</a>
     * @since Available in iOS 5.0 and later.
     */
    public void beginAppearanceTransition(boolean isAppearing, boolean animated) {
        if (customClass) { objc_beginAppearanceTransitionSuper(getSuper(), beginAppearanceTransition$animated$, isAppearing, animated); } else { objc_beginAppearanceTransition(this, beginAppearanceTransition$animated$, isAppearing, animated); }
    }
    
    private static final Selector canPerformUnwindSegueAction$fromViewController$withSender$ = Selector.register("canPerformUnwindSegueAction:fromViewController:withSender:");
    @Bridge private native static boolean objc_canPerformUnwindSegueAction(UIViewController __self__, Selector __cmd__, Selector action, UIViewController fromViewController, NSObject sender);
    @Bridge private native static boolean objc_canPerformUnwindSegueActionSuper(ObjCSuper __super__, Selector __cmd__, Selector action, UIViewController fromViewController, NSObject sender);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/canPerformUnwindSegueAction:fromViewController:withSender:">- (BOOL)canPerformUnwindSegueAction:(SEL)action fromViewController:(UIViewController *)fromViewController withSender:(id)sender</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean canPerformUnwindSegueAction(Selector action, UIViewController fromViewController, NSObject sender) {
        if (customClass) { return objc_canPerformUnwindSegueActionSuper(getSuper(), canPerformUnwindSegueAction$fromViewController$withSender$, action, fromViewController, sender); } else { return objc_canPerformUnwindSegueAction(this, canPerformUnwindSegueAction$fromViewController$withSender$, action, fromViewController, sender); }
    }
    
    private static final Selector decodeRestorableStateWithCoder$ = Selector.register("decodeRestorableStateWithCoder:");
    @Bridge private native static void objc_decodeRestorableState(UIViewController __self__, Selector __cmd__, NSCoder coder);
    @Bridge private native static void objc_decodeRestorableStateSuper(ObjCSuper __super__, Selector __cmd__, NSCoder coder);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/decodeRestorableStateWithCoder:">- (void)decodeRestorableStateWithCoder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    public void decodeRestorableState(NSCoder coder) {
        if (customClass) { objc_decodeRestorableStateSuper(getSuper(), decodeRestorableStateWithCoder$, coder); } else { objc_decodeRestorableState(this, decodeRestorableStateWithCoder$, coder); }
    }
    
    private static final Selector didMoveToParentViewController$ = Selector.register("didMoveToParentViewController:");
    @Bridge private native static void objc_didMoveToParentViewController(UIViewController __self__, Selector __cmd__, UIViewController parent);
    @Bridge private native static void objc_didMoveToParentViewControllerSuper(ObjCSuper __super__, Selector __cmd__, UIViewController parent);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/didMoveToParentViewController:">- (void)didMoveToParentViewController:(UIViewController *)parent</a>
     * @since Available in iOS 5.0 and later.
     */
    public void didMoveToParentViewController(UIViewController parent) {
        if (customClass) { objc_didMoveToParentViewControllerSuper(getSuper(), didMoveToParentViewController$, parent); } else { objc_didMoveToParentViewController(this, didMoveToParentViewController$, parent); }
    }
    
    private static final Selector didReceiveMemoryWarning = Selector.register("didReceiveMemoryWarning");
    @Bridge private native static void objc_didReceiveMemoryWarning(UIViewController __self__, Selector __cmd__);
    @Bridge private native static void objc_didReceiveMemoryWarningSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/didReceiveMemoryWarning">- (void)didReceiveMemoryWarning</a>
     * @since Available in iOS 2.0 and later.
     */
    public void didReceiveMemoryWarning() {
        if (customClass) { objc_didReceiveMemoryWarningSuper(getSuper(), didReceiveMemoryWarning); } else { objc_didReceiveMemoryWarning(this, didReceiveMemoryWarning); }
    }
    
    private static final Selector didRotateFromInterfaceOrientation$ = Selector.register("didRotateFromInterfaceOrientation:");
    @Bridge private native static void objc_didRotate(UIViewController __self__, Selector __cmd__, UIInterfaceOrientation fromInterfaceOrientation);
    @Bridge private native static void objc_didRotateSuper(ObjCSuper __super__, Selector __cmd__, UIInterfaceOrientation fromInterfaceOrientation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/didRotateFromInterfaceOrientation:">- (void)didRotateFromInterfaceOrientation:(UIInterfaceOrientation)fromInterfaceOrientation</a>
     * @since Available in iOS 2.0 and later.
     */
    public void didRotate(UIInterfaceOrientation fromInterfaceOrientation) {
        if (customClass) { objc_didRotateSuper(getSuper(), didRotateFromInterfaceOrientation$, fromInterfaceOrientation); } else { objc_didRotate(this, didRotateFromInterfaceOrientation$, fromInterfaceOrientation); }
    }
    
    private static final Selector disablesAutomaticKeyboardDismissal = Selector.register("disablesAutomaticKeyboardDismissal");
    @Bridge private native static boolean objc_disablesAutomaticKeyboardDismissal(UIViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_disablesAutomaticKeyboardDismissalSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/disablesAutomaticKeyboardDismissal">- (BOOL)disablesAutomaticKeyboardDismissal</a>
     * @since Available in iOS 4.3 and later.
     */
    public boolean disablesAutomaticKeyboardDismissal() {
        if (customClass) { return objc_disablesAutomaticKeyboardDismissalSuper(getSuper(), disablesAutomaticKeyboardDismissal); } else { return objc_disablesAutomaticKeyboardDismissal(this, disablesAutomaticKeyboardDismissal); }
    }
    
    private static final Selector dismissViewControllerAnimated$completion$ = Selector.register("dismissViewControllerAnimated:completion:");
    @Bridge private native static void objc_dismissViewController(UIViewController __self__, Selector __cmd__, boolean flag, VoidBlock completion);
    @Bridge private native static void objc_dismissViewControllerSuper(ObjCSuper __super__, Selector __cmd__, boolean flag, VoidBlock completion);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/dismissViewControllerAnimated:completion:">- (void)dismissViewControllerAnimated:(BOOL)flag completion:(void (^)(void))completion</a>
     * @since Available in iOS 5.0 and later.
     */
    public void dismissViewController(boolean flag, VoidBlock completion) {
        if (customClass) { objc_dismissViewControllerSuper(getSuper(), dismissViewControllerAnimated$completion$, flag, completion); } else { objc_dismissViewController(this, dismissViewControllerAnimated$completion$, flag, completion); }
    }
    
    private static final Selector encodeRestorableStateWithCoder$ = Selector.register("encodeRestorableStateWithCoder:");
    @Bridge private native static void objc_encodeRestorableState(UIViewController __self__, Selector __cmd__, NSCoder coder);
    @Bridge private native static void objc_encodeRestorableStateSuper(ObjCSuper __super__, Selector __cmd__, NSCoder coder);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/encodeRestorableStateWithCoder:">- (void)encodeRestorableStateWithCoder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    public void encodeRestorableState(NSCoder coder) {
        if (customClass) { objc_encodeRestorableStateSuper(getSuper(), encodeRestorableStateWithCoder$, coder); } else { objc_encodeRestorableState(this, encodeRestorableStateWithCoder$, coder); }
    }
    
    private static final Selector endAppearanceTransition = Selector.register("endAppearanceTransition");
    @Bridge private native static void objc_endAppearanceTransition(UIViewController __self__, Selector __cmd__);
    @Bridge private native static void objc_endAppearanceTransitionSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/endAppearanceTransition">- (void)endAppearanceTransition</a>
     * @since Available in iOS 5.0 and later.
     */
    public void endAppearanceTransition() {
        if (customClass) { objc_endAppearanceTransitionSuper(getSuper(), endAppearanceTransition); } else { objc_endAppearanceTransition(this, endAppearanceTransition); }
    }
    
    private static final Selector editButtonItem = Selector.register("editButtonItem");
    @Bridge private native static UIBarButtonItem objc_getEditButtonItem(UIViewController __self__, Selector __cmd__);
    @Bridge private native static UIBarButtonItem objc_getEditButtonItemSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/editButtonItem">- (UIBarButtonItem *)editButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarButtonItem getEditButtonItem() {
        if (customClass) { return objc_getEditButtonItemSuper(getSuper(), editButtonItem); } else { return objc_getEditButtonItem(this, editButtonItem); }
    }
    
    private static final Selector preferredInterfaceOrientationForPresentation = Selector.register("preferredInterfaceOrientationForPresentation");
    @Bridge private native static UIInterfaceOrientation objc_getPreferredInterfaceOrientation(UIViewController __self__, Selector __cmd__);
    @Bridge private native static UIInterfaceOrientation objc_getPreferredInterfaceOrientationSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/preferredInterfaceOrientationForPresentation">- (UIInterfaceOrientation)preferredInterfaceOrientationForPresentation</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIInterfaceOrientation getPreferredInterfaceOrientation() {
        if (customClass) { return objc_getPreferredInterfaceOrientationSuper(getSuper(), preferredInterfaceOrientationForPresentation); } else { return objc_getPreferredInterfaceOrientation(this, preferredInterfaceOrientationForPresentation); }
    }
    
    private static final Selector rotatingFooterView = Selector.register("rotatingFooterView");
    @Bridge private native static UIView objc_getRotatingFooterView(UIViewController __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getRotatingFooterViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/rotatingFooterView">- (UIView *)rotatingFooterView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getRotatingFooterView() {
        if (customClass) { return objc_getRotatingFooterViewSuper(getSuper(), rotatingFooterView); } else { return objc_getRotatingFooterView(this, rotatingFooterView); }
    }
    
    private static final Selector rotatingHeaderView = Selector.register("rotatingHeaderView");
    @Bridge private native static UIView objc_getRotatingHeaderView(UIViewController __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getRotatingHeaderViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/rotatingHeaderView">- (UIView *)rotatingHeaderView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getRotatingHeaderView() {
        if (customClass) { return objc_getRotatingHeaderViewSuper(getSuper(), rotatingHeaderView); } else { return objc_getRotatingHeaderView(this, rotatingHeaderView); }
    }
    
    private static final Selector supportedInterfaceOrientations = Selector.register("supportedInterfaceOrientations");
    @Bridge private native static UIInterfaceOrientationMask objc_getSupportedInterfaceOrientations(UIViewController __self__, Selector __cmd__);
    @Bridge private native static UIInterfaceOrientationMask objc_getSupportedInterfaceOrientationsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/supportedInterfaceOrientations">- (NSUInteger)supportedInterfaceOrientations</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIInterfaceOrientationMask getSupportedInterfaceOrientations() {
        if (customClass) { return objc_getSupportedInterfaceOrientationsSuper(getSuper(), supportedInterfaceOrientations); } else { return objc_getSupportedInterfaceOrientations(this, supportedInterfaceOrientations); }
    }
    
    private static final Selector segueForUnwindingToViewController$fromViewController$identifier$ = Selector.register("segueForUnwindingToViewController:fromViewController:identifier:");
    @Bridge private native static UIStoryboardSegue objc_getUnwindSegue(UIViewController __self__, Selector __cmd__, UIViewController toViewController, UIViewController fromViewController, String identifier);
    @Bridge private native static UIStoryboardSegue objc_getUnwindSegueSuper(ObjCSuper __super__, Selector __cmd__, UIViewController toViewController, UIViewController fromViewController, String identifier);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/segueForUnwindingToViewController:fromViewController:identifier:">- (UIStoryboardSegue *)segueForUnwindingToViewController:(UIViewController *)toViewController fromViewController:(UIViewController *)fromViewController identifier:(NSString *)identifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIStoryboardSegue getUnwindSegue(UIViewController toViewController, UIViewController fromViewController, String identifier) {
        if (customClass) { return objc_getUnwindSegueSuper(getSuper(), segueForUnwindingToViewController$fromViewController$identifier$, toViewController, fromViewController, identifier); } else { return objc_getUnwindSegue(this, segueForUnwindingToViewController$fromViewController$identifier$, toViewController, fromViewController, identifier); }
    }
    
    private static final Selector viewControllerForUnwindSegueAction$fromViewController$withSender$ = Selector.register("viewControllerForUnwindSegueAction:fromViewController:withSender:");
    @Bridge private native static UIViewController objc_getUnwindSegueActionViewController(UIViewController __self__, Selector __cmd__, Selector action, UIViewController fromViewController, NSObject sender);
    @Bridge private native static UIViewController objc_getUnwindSegueActionViewControllerSuper(ObjCSuper __super__, Selector __cmd__, Selector action, UIViewController fromViewController, NSObject sender);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewControllerForUnwindSegueAction:fromViewController:withSender:">- (UIViewController *)viewControllerForUnwindSegueAction:(SEL)action fromViewController:(UIViewController *)fromViewController withSender:(id)sender</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIViewController getUnwindSegueActionViewController(Selector action, UIViewController fromViewController, NSObject sender) {
        if (customClass) { return objc_getUnwindSegueActionViewControllerSuper(getSuper(), viewControllerForUnwindSegueAction$fromViewController$withSender$, action, fromViewController, sender); } else { return objc_getUnwindSegueActionViewController(this, viewControllerForUnwindSegueAction$fromViewController$withSender$, action, fromViewController, sender); }
    }
    
    private static final Selector isBeingDismissed = Selector.register("isBeingDismissed");
    @Bridge private native static boolean objc_isBeingDismissed(UIViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isBeingDismissedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/isBeingDismissed">- (BOOL)isBeingDismissed</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isBeingDismissed() {
        if (customClass) { return objc_isBeingDismissedSuper(getSuper(), isBeingDismissed); } else { return objc_isBeingDismissed(this, isBeingDismissed); }
    }
    
    private static final Selector isBeingPresented = Selector.register("isBeingPresented");
    @Bridge private native static boolean objc_isBeingPresented(UIViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isBeingPresentedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/isBeingPresented">- (BOOL)isBeingPresented</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isBeingPresented() {
        if (customClass) { return objc_isBeingPresentedSuper(getSuper(), isBeingPresented); } else { return objc_isBeingPresented(this, isBeingPresented); }
    }
    
    private static final Selector isMovingFromParentViewController = Selector.register("isMovingFromParentViewController");
    @Bridge private native static boolean objc_isMovingFromParentViewController(UIViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isMovingFromParentViewControllerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/isMovingFromParentViewController">- (BOOL)isMovingFromParentViewController</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isMovingFromParentViewController() {
        if (customClass) { return objc_isMovingFromParentViewControllerSuper(getSuper(), isMovingFromParentViewController); } else { return objc_isMovingFromParentViewController(this, isMovingFromParentViewController); }
    }
    
    private static final Selector isMovingToParentViewController = Selector.register("isMovingToParentViewController");
    @Bridge private native static boolean objc_isMovingToParentViewController(UIViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isMovingToParentViewControllerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/isMovingToParentViewController">- (BOOL)isMovingToParentViewController</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isMovingToParentViewController() {
        if (customClass) { return objc_isMovingToParentViewControllerSuper(getSuper(), isMovingToParentViewController); } else { return objc_isMovingToParentViewController(this, isMovingToParentViewController); }
    }
    
    private static final Selector isViewLoaded = Selector.register("isViewLoaded");
    @Bridge private native static boolean objc_isViewLoaded(UIViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isViewLoadedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/isViewLoaded">- (BOOL)isViewLoaded</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isViewLoaded() {
        if (customClass) { return objc_isViewLoadedSuper(getSuper(), isViewLoaded); } else { return objc_isViewLoaded(this, isViewLoaded); }
    }
    
    private static final Selector loadView = Selector.register("loadView");
    @Bridge private native static void objc_loadView(UIViewController __self__, Selector __cmd__);
    @Bridge private native static void objc_loadViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/loadView">- (void)loadView</a>
     * @since Available in iOS 2.0 and later.
     */
    public void loadView() {
        if (customClass) { objc_loadViewSuper(getSuper(), loadView); } else { objc_loadView(this, loadView); }
    }
    
    private static final Selector performSegueWithIdentifier$sender$ = Selector.register("performSegueWithIdentifier:sender:");
    @Bridge private native static void objc_performSegue(UIViewController __self__, Selector __cmd__, String identifier, NSObject sender);
    @Bridge private native static void objc_performSegueSuper(ObjCSuper __super__, Selector __cmd__, String identifier, NSObject sender);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/performSegueWithIdentifier:sender:">- (void)performSegueWithIdentifier:(NSString *)identifier sender:(id)sender</a>
     * @since Available in iOS 5.0 and later.
     */
    public void performSegue(String identifier, NSObject sender) {
        if (customClass) { objc_performSegueSuper(getSuper(), performSegueWithIdentifier$sender$, identifier, sender); } else { objc_performSegue(this, performSegueWithIdentifier$sender$, identifier, sender); }
    }
    
    private static final Selector prepareForSegue$sender$ = Selector.register("prepareForSegue:sender:");
    @Bridge private native static void objc_prepareForSegue(UIViewController __self__, Selector __cmd__, UIStoryboardSegue segue, NSObject sender);
    @Bridge private native static void objc_prepareForSegueSuper(ObjCSuper __super__, Selector __cmd__, UIStoryboardSegue segue, NSObject sender);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/prepareForSegue:sender:">- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender</a>
     * @since Available in iOS 5.0 and later.
     */
    public void prepareForSegue(UIStoryboardSegue segue, NSObject sender) {
        if (customClass) { objc_prepareForSegueSuper(getSuper(), prepareForSegue$sender$, segue, sender); } else { objc_prepareForSegue(this, prepareForSegue$sender$, segue, sender); }
    }
    
    private static final Selector presentViewController$animated$completion$ = Selector.register("presentViewController:animated:completion:");
    @Bridge private native static void objc_presentViewController(UIViewController __self__, Selector __cmd__, UIViewController viewControllerToPresent, boolean flag, VoidBlock completion);
    @Bridge private native static void objc_presentViewControllerSuper(ObjCSuper __super__, Selector __cmd__, UIViewController viewControllerToPresent, boolean flag, VoidBlock completion);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/presentViewController:animated:completion:">- (void)presentViewController:(UIViewController *)viewControllerToPresent animated:(BOOL)flag completion:(void (^)(void))completion</a>
     * @since Available in iOS 5.0 and later.
     */
    public void presentViewController(UIViewController viewControllerToPresent, boolean flag, VoidBlock completion) {
        if (customClass) { objc_presentViewControllerSuper(getSuper(), presentViewController$animated$completion$, viewControllerToPresent, flag, completion); } else { objc_presentViewController(this, presentViewController$animated$completion$, viewControllerToPresent, flag, completion); }
    }
    
    private static final Selector removeFromParentViewController = Selector.register("removeFromParentViewController");
    @Bridge private native static void objc_removeFromParentViewController(UIViewController __self__, Selector __cmd__);
    @Bridge private native static void objc_removeFromParentViewControllerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/removeFromParentViewController">- (void)removeFromParentViewController</a>
     * @since Available in iOS 5.0 and later.
     */
    public void removeFromParentViewController() {
        if (customClass) { objc_removeFromParentViewControllerSuper(getSuper(), removeFromParentViewController); } else { objc_removeFromParentViewController(this, removeFromParentViewController); }
    }
    
    private static final Selector setEditing$animated$ = Selector.register("setEditing:animated:");
    @Bridge private native static void objc_setEditing(UIViewController __self__, Selector __cmd__, boolean editing, boolean animated);
    @Bridge private native static void objc_setEditingSuper(ObjCSuper __super__, Selector __cmd__, boolean editing, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/setEditing:animated:">- (void)setEditing:(BOOL)editing animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setEditing(boolean editing, boolean animated) {
        if (customClass) { objc_setEditingSuper(getSuper(), setEditing$animated$, editing, animated); } else { objc_setEditing(this, setEditing$animated$, editing, animated); }
    }
    
    private static final Selector setToolbarItems$animated$ = Selector.register("setToolbarItems:animated:");
    @Bridge private native static void objc_setToolbarItems(UIViewController __self__, Selector __cmd__, NSArray toolbarItems, boolean animated);
    @Bridge private native static void objc_setToolbarItemsSuper(ObjCSuper __super__, Selector __cmd__, NSArray toolbarItems, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/setToolbarItems:animated:">- (void)setToolbarItems:(NSArray *)toolbarItems animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setToolbarItems(NSArray toolbarItems, boolean animated) {
        if (customClass) { objc_setToolbarItemsSuper(getSuper(), setToolbarItems$animated$, toolbarItems, animated); } else { objc_setToolbarItems(this, setToolbarItems$animated$, toolbarItems, animated); }
    }
    
    private static final Selector shouldAutomaticallyForwardAppearanceMethods = Selector.register("shouldAutomaticallyForwardAppearanceMethods");
    @Bridge private native static boolean objc_shouldAutomaticallyForwardAppearanceMethods(UIViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_shouldAutomaticallyForwardAppearanceMethodsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/shouldAutomaticallyForwardAppearanceMethods">- (BOOL)shouldAutomaticallyForwardAppearanceMethods</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean shouldAutomaticallyForwardAppearanceMethods() {
        if (customClass) { return objc_shouldAutomaticallyForwardAppearanceMethodsSuper(getSuper(), shouldAutomaticallyForwardAppearanceMethods); } else { return objc_shouldAutomaticallyForwardAppearanceMethods(this, shouldAutomaticallyForwardAppearanceMethods); }
    }
    
    private static final Selector shouldAutomaticallyForwardRotationMethods = Selector.register("shouldAutomaticallyForwardRotationMethods");
    @Bridge private native static boolean objc_shouldAutomaticallyForwardRotationMethods(UIViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_shouldAutomaticallyForwardRotationMethodsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/shouldAutomaticallyForwardRotationMethods">- (BOOL)shouldAutomaticallyForwardRotationMethods</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean shouldAutomaticallyForwardRotationMethods() {
        if (customClass) { return objc_shouldAutomaticallyForwardRotationMethodsSuper(getSuper(), shouldAutomaticallyForwardRotationMethods); } else { return objc_shouldAutomaticallyForwardRotationMethods(this, shouldAutomaticallyForwardRotationMethods); }
    }
    
    private static final Selector shouldAutorotate = Selector.register("shouldAutorotate");
    @Bridge private native static boolean objc_shouldAutorotate(UIViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_shouldAutorotateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/shouldAutorotate">- (BOOL)shouldAutorotate</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean shouldAutorotate() {
        if (customClass) { return objc_shouldAutorotateSuper(getSuper(), shouldAutorotate); } else { return objc_shouldAutorotate(this, shouldAutorotate); }
    }
    
    private static final Selector shouldPerformSegueWithIdentifier$sender$ = Selector.register("shouldPerformSegueWithIdentifier:sender:");
    @Bridge private native static boolean objc_shouldPerformSegue(UIViewController __self__, Selector __cmd__, String identifier, NSObject sender);
    @Bridge private native static boolean objc_shouldPerformSegueSuper(ObjCSuper __super__, Selector __cmd__, String identifier, NSObject sender);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/shouldPerformSegueWithIdentifier:sender:">- (BOOL)shouldPerformSegueWithIdentifier:(NSString *)identifier sender:(id)sender</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean shouldPerformSegue(String identifier, NSObject sender) {
        if (customClass) { return objc_shouldPerformSegueSuper(getSuper(), shouldPerformSegueWithIdentifier$sender$, identifier, sender); } else { return objc_shouldPerformSegue(this, shouldPerformSegueWithIdentifier$sender$, identifier, sender); }
    }
    
    private static final Selector transitionFromViewController$toViewController$duration$options$animations$completion$ = Selector.register("transitionFromViewController:toViewController:duration:options:animations:completion:");
    @Bridge private native static void objc_transition(UIViewController __self__, Selector __cmd__, UIViewController fromViewController, UIViewController toViewController, double duration, UIViewAnimationOptions options, VoidBlock animations, VoidBooleanBlock completion);
    @Bridge private native static void objc_transitionSuper(ObjCSuper __super__, Selector __cmd__, UIViewController fromViewController, UIViewController toViewController, double duration, UIViewAnimationOptions options, VoidBlock animations, VoidBooleanBlock completion);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/transitionFromViewController:toViewController:duration:options:animations:completion:">- (void)transitionFromViewController:(UIViewController *)fromViewController toViewController:(UIViewController *)toViewController duration:(NSTimeInterval)duration options:(UIViewAnimationOptions)options animations:(void (^)(void))animations completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 5.0 and later.
     */
    public void transition(UIViewController fromViewController, UIViewController toViewController, double duration, UIViewAnimationOptions options, VoidBlock animations, VoidBooleanBlock completion) {
        if (customClass) { objc_transitionSuper(getSuper(), transitionFromViewController$toViewController$duration$options$animations$completion$, fromViewController, toViewController, duration, options, animations, completion); } else { objc_transition(this, transitionFromViewController$toViewController$duration$options$animations$completion$, fromViewController, toViewController, duration, options, animations, completion); }
    }
    
    private static final Selector updateViewConstraints = Selector.register("updateViewConstraints");
    @Bridge private native static void objc_updateViewConstraints(UIViewController __self__, Selector __cmd__);
    @Bridge private native static void objc_updateViewConstraintsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/updateViewConstraints">- (void)updateViewConstraints</a>
     * @since Available in iOS 6.0 and later.
     */
    public void updateViewConstraints() {
        if (customClass) { objc_updateViewConstraintsSuper(getSuper(), updateViewConstraints); } else { objc_updateViewConstraints(this, updateViewConstraints); }
    }
    
    private static final Selector viewDidAppear$ = Selector.register("viewDidAppear:");
    @Bridge private native static void objc_viewDidAppear(UIViewController __self__, Selector __cmd__, boolean animated);
    @Bridge private native static void objc_viewDidAppearSuper(ObjCSuper __super__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewDidAppear:">- (void)viewDidAppear:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void viewDidAppear(boolean animated) {
        if (customClass) { objc_viewDidAppearSuper(getSuper(), viewDidAppear$, animated); } else { objc_viewDidAppear(this, viewDidAppear$, animated); }
    }
    
    private static final Selector viewDidDisappear$ = Selector.register("viewDidDisappear:");
    @Bridge private native static void objc_viewDidDisappear(UIViewController __self__, Selector __cmd__, boolean animated);
    @Bridge private native static void objc_viewDidDisappearSuper(ObjCSuper __super__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewDidDisappear:">- (void)viewDidDisappear:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void viewDidDisappear(boolean animated) {
        if (customClass) { objc_viewDidDisappearSuper(getSuper(), viewDidDisappear$, animated); } else { objc_viewDidDisappear(this, viewDidDisappear$, animated); }
    }
    
    private static final Selector viewDidLayoutSubviews = Selector.register("viewDidLayoutSubviews");
    @Bridge private native static void objc_viewDidLayoutSubviews(UIViewController __self__, Selector __cmd__);
    @Bridge private native static void objc_viewDidLayoutSubviewsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewDidLayoutSubviews">- (void)viewDidLayoutSubviews</a>
     * @since Available in iOS 5.0 and later.
     */
    public void viewDidLayoutSubviews() {
        if (customClass) { objc_viewDidLayoutSubviewsSuper(getSuper(), viewDidLayoutSubviews); } else { objc_viewDidLayoutSubviews(this, viewDidLayoutSubviews); }
    }
    
    private static final Selector viewDidLoad = Selector.register("viewDidLoad");
    @Bridge private native static void objc_viewDidLoad(UIViewController __self__, Selector __cmd__);
    @Bridge private native static void objc_viewDidLoadSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewDidLoad">- (void)viewDidLoad</a>
     * @since Available in iOS 2.0 and later.
     */
    public void viewDidLoad() {
        if (customClass) { objc_viewDidLoadSuper(getSuper(), viewDidLoad); } else { objc_viewDidLoad(this, viewDidLoad); }
    }
    
    private static final Selector viewWillAppear$ = Selector.register("viewWillAppear:");
    @Bridge private native static void objc_viewWillAppear(UIViewController __self__, Selector __cmd__, boolean animated);
    @Bridge private native static void objc_viewWillAppearSuper(ObjCSuper __super__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewWillAppear:">- (void)viewWillAppear:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void viewWillAppear(boolean animated) {
        if (customClass) { objc_viewWillAppearSuper(getSuper(), viewWillAppear$, animated); } else { objc_viewWillAppear(this, viewWillAppear$, animated); }
    }
    
    private static final Selector viewWillDisappear$ = Selector.register("viewWillDisappear:");
    @Bridge private native static void objc_viewWillDisappear(UIViewController __self__, Selector __cmd__, boolean animated);
    @Bridge private native static void objc_viewWillDisappearSuper(ObjCSuper __super__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewWillDisappear:">- (void)viewWillDisappear:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void viewWillDisappear(boolean animated) {
        if (customClass) { objc_viewWillDisappearSuper(getSuper(), viewWillDisappear$, animated); } else { objc_viewWillDisappear(this, viewWillDisappear$, animated); }
    }
    
    private static final Selector viewWillLayoutSubviews = Selector.register("viewWillLayoutSubviews");
    @Bridge private native static void objc_viewWillLayoutSubviews(UIViewController __self__, Selector __cmd__);
    @Bridge private native static void objc_viewWillLayoutSubviewsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/viewWillLayoutSubviews">- (void)viewWillLayoutSubviews</a>
     * @since Available in iOS 5.0 and later.
     */
    public void viewWillLayoutSubviews() {
        if (customClass) { objc_viewWillLayoutSubviewsSuper(getSuper(), viewWillLayoutSubviews); } else { objc_viewWillLayoutSubviews(this, viewWillLayoutSubviews); }
    }
    
    private static final Selector willAnimateRotationToInterfaceOrientation$duration$ = Selector.register("willAnimateRotationToInterfaceOrientation:duration:");
    @Bridge private native static void objc_willAnimateRotation(UIViewController __self__, Selector __cmd__, UIInterfaceOrientation interfaceOrientation, double duration);
    @Bridge private native static void objc_willAnimateRotationSuper(ObjCSuper __super__, Selector __cmd__, UIInterfaceOrientation interfaceOrientation, double duration);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/willAnimateRotationToInterfaceOrientation:duration:">- (void)willAnimateRotationToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation duration:(NSTimeInterval)duration</a>
     * @since Available in iOS 3.0 and later.
     */
    public void willAnimateRotation(UIInterfaceOrientation interfaceOrientation, double duration) {
        if (customClass) { objc_willAnimateRotationSuper(getSuper(), willAnimateRotationToInterfaceOrientation$duration$, interfaceOrientation, duration); } else { objc_willAnimateRotation(this, willAnimateRotationToInterfaceOrientation$duration$, interfaceOrientation, duration); }
    }
    
    private static final Selector willMoveToParentViewController$ = Selector.register("willMoveToParentViewController:");
    @Bridge private native static void objc_willMoveToParent(UIViewController __self__, Selector __cmd__, UIViewController parent);
    @Bridge private native static void objc_willMoveToParentSuper(ObjCSuper __super__, Selector __cmd__, UIViewController parent);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/willMoveToParentViewController:">- (void)willMoveToParentViewController:(UIViewController *)parent</a>
     * @since Available in iOS 5.0 and later.
     */
    public void willMoveToParent(UIViewController parent) {
        if (customClass) { objc_willMoveToParentSuper(getSuper(), willMoveToParentViewController$, parent); } else { objc_willMoveToParent(this, willMoveToParentViewController$, parent); }
    }
    
    private static final Selector willRotateToInterfaceOrientation$duration$ = Selector.register("willRotateToInterfaceOrientation:duration:");
    @Bridge private native static void objc_willRotate(UIViewController __self__, Selector __cmd__, UIInterfaceOrientation toInterfaceOrientation, double duration);
    @Bridge private native static void objc_willRotateSuper(ObjCSuper __super__, Selector __cmd__, UIInterfaceOrientation toInterfaceOrientation, double duration);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UIViewController/willRotateToInterfaceOrientation:duration:">- (void)willRotateToInterfaceOrientation:(UIInterfaceOrientation)toInterfaceOrientation duration:(NSTimeInterval)duration</a>
     * @since Available in iOS 2.0 and later.
     */
    public void willRotate(UIInterfaceOrientation toInterfaceOrientation, double duration) {
        if (customClass) { objc_willRotateSuper(getSuper(), willRotateToInterfaceOrientation$duration$, toInterfaceOrientation, duration); } else { objc_willRotate(this, willRotateToInterfaceOrientation$duration$, toInterfaceOrientation, duration); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("childViewControllers") public static NSArray getChildViewControllers(UIViewController __self__, Selector __cmd__) { return __self__.getChildViewControllers(); }
        @Callback @BindSelector("contentSizeForViewInPopover") public static @ByVal CGSize getContentSizeForViewInPopover(UIViewController __self__, Selector __cmd__) { return __self__.getContentSizeForViewInPopover(); }
        @Callback @BindSelector("setContentSizeForViewInPopover:") public static void setContentSizeForViewInPopover(UIViewController __self__, Selector __cmd__, @ByVal CGSize contentSizeForViewInPopover) { __self__.setContentSizeForViewInPopover(contentSizeForViewInPopover); }
        @Callback @BindSelector("definesPresentationContext") public static boolean isDefinesPresentationContext(UIViewController __self__, Selector __cmd__) { return __self__.isDefinesPresentationContext(); }
        @Callback @BindSelector("setDefinesPresentationContext:") public static void setDefinesPresentationContext(UIViewController __self__, Selector __cmd__, boolean definesPresentationContext) { __self__.setDefinesPresentationContext(definesPresentationContext); }
        @Callback @BindSelector("isEditing") public static boolean isEditing(UIViewController __self__, Selector __cmd__) { return __self__.isEditing(); }
        @Callback @BindSelector("setEditing:") public static void setEditing(UIViewController __self__, Selector __cmd__, boolean editing) { __self__.setEditing(editing); }
        @Callback @BindSelector("hidesBottomBarWhenPushed") public static boolean isHidesBottomBarWhenPushed(UIViewController __self__, Selector __cmd__) { return __self__.isHidesBottomBarWhenPushed(); }
        @Callback @BindSelector("setHidesBottomBarWhenPushed:") public static void setHidesBottomBarWhenPushed(UIViewController __self__, Selector __cmd__, boolean hidesBottomBarWhenPushed) { __self__.setHidesBottomBarWhenPushed(hidesBottomBarWhenPushed); }
        @Callback @BindSelector("interfaceOrientation") public static UIInterfaceOrientation getInterfaceOrientation(UIViewController __self__, Selector __cmd__) { return __self__.getInterfaceOrientation(); }
        @Callback @BindSelector("isModalInPopover") public static boolean isModalInPopover(UIViewController __self__, Selector __cmd__) { return __self__.isModalInPopover(); }
        @Callback @BindSelector("setModalInPopover:") public static void setModalInPopover(UIViewController __self__, Selector __cmd__, boolean modalInPopover) { __self__.setModalInPopover(modalInPopover); }
        @Callback @BindSelector("modalPresentationStyle") public static UIModalPresentationStyle getModalPresentationStyle(UIViewController __self__, Selector __cmd__) { return __self__.getModalPresentationStyle(); }
        @Callback @BindSelector("setModalPresentationStyle:") public static void setModalPresentationStyle(UIViewController __self__, Selector __cmd__, UIModalPresentationStyle modalPresentationStyle) { __self__.setModalPresentationStyle(modalPresentationStyle); }
        @Callback @BindSelector("modalTransitionStyle") public static UIModalTransitionStyle getModalTransitionStyle(UIViewController __self__, Selector __cmd__) { return __self__.getModalTransitionStyle(); }
        @Callback @BindSelector("setModalTransitionStyle:") public static void setModalTransitionStyle(UIViewController __self__, Selector __cmd__, UIModalTransitionStyle modalTransitionStyle) { __self__.setModalTransitionStyle(modalTransitionStyle); }
        @Callback @BindSelector("navigationController") public static UINavigationController getNavigationController(UIViewController __self__, Selector __cmd__) { return __self__.getNavigationController(); }
        @Callback @BindSelector("navigationItem") public static UINavigationItem getNavigationItem(UIViewController __self__, Selector __cmd__) { return __self__.getNavigationItem(); }
        @Callback @BindSelector("nibBundle") public static NSBundle getNibBundle(UIViewController __self__, Selector __cmd__) { return __self__.getNibBundle(); }
        @Callback @BindSelector("nibName") public static String getNibName(UIViewController __self__, Selector __cmd__) { return __self__.getNibName(); }
        @Callback @BindSelector("parentViewController") public static UIViewController getParentViewController(UIViewController __self__, Selector __cmd__) { return __self__.getParentViewController(); }
        @Callback @BindSelector("presentedViewController") public static UIViewController getPresentedViewController(UIViewController __self__, Selector __cmd__) { return __self__.getPresentedViewController(); }
        @Callback @BindSelector("presentingViewController") public static UIViewController getPresentingViewController(UIViewController __self__, Selector __cmd__) { return __self__.getPresentingViewController(); }
        @Callback @BindSelector("providesPresentationContextTransitionStyle") public static boolean isProvidesPresentationContextTransitionStyle(UIViewController __self__, Selector __cmd__) { return __self__.isProvidesPresentationContextTransitionStyle(); }
        @Callback @BindSelector("setProvidesPresentationContextTransitionStyle:") public static void setProvidesPresentationContextTransitionStyle(UIViewController __self__, Selector __cmd__, boolean providesPresentationContextTransitionStyle) { __self__.setProvidesPresentationContextTransitionStyle(providesPresentationContextTransitionStyle); }
        @Callback @BindSelector("restorationClass") public static ObjCClass getRestorationClass(UIViewController __self__, Selector __cmd__) { return __self__.getRestorationClass(); }
        @Callback @BindSelector("setRestorationClass:") public static void setRestorationClass(UIViewController __self__, Selector __cmd__, ObjCClass restorationClass) { __self__.setRestorationClass(restorationClass); }
        @Callback @BindSelector("restorationIdentifier") public static String getRestorationIdentifier(UIViewController __self__, Selector __cmd__) { return __self__.getRestorationIdentifier(); }
        @Callback @BindSelector("setRestorationIdentifier:") public static void setRestorationIdentifier(UIViewController __self__, Selector __cmd__, String restorationIdentifier) { __self__.setRestorationIdentifier(restorationIdentifier); }
        @Callback @BindSelector("searchDisplayController") public static UISearchDisplayController getSearchDisplayController(UIViewController __self__, Selector __cmd__) { return __self__.getSearchDisplayController(); }
        @Callback @BindSelector("splitViewController") public static UISplitViewController getSplitViewController(UIViewController __self__, Selector __cmd__) { return __self__.getSplitViewController(); }
        @Callback @BindSelector("storyboard") public static UIStoryboard getStoryboard(UIViewController __self__, Selector __cmd__) { return __self__.getStoryboard(); }
        @Callback @BindSelector("tabBarController") public static UITabBarController getTabBarController(UIViewController __self__, Selector __cmd__) { return __self__.getTabBarController(); }
        @Callback @BindSelector("tabBarItem") public static UITabBarItem getTabBarItem(UIViewController __self__, Selector __cmd__) { return __self__.getTabBarItem(); }
        @Callback @BindSelector("setTabBarItem:") public static void setTabBarItem(UIViewController __self__, Selector __cmd__, UITabBarItem tabBarItem) { __self__.setTabBarItem(tabBarItem); }
        @Callback @BindSelector("title") public static String getTitle(UIViewController __self__, Selector __cmd__) { return __self__.getTitle(); }
        @Callback @BindSelector("setTitle:") public static void setTitle(UIViewController __self__, Selector __cmd__, String title) { __self__.setTitle(title); }
        @Callback @BindSelector("toolbarItems") public static NSArray getToolbarItems(UIViewController __self__, Selector __cmd__) { return __self__.getToolbarItems(); }
        @Callback @BindSelector("setToolbarItems:") public static void setToolbarItems(UIViewController __self__, Selector __cmd__, NSArray toolbarItems) { __self__.setToolbarItems(toolbarItems); }
        @Callback @BindSelector("view") public static UIView getView(UIViewController __self__, Selector __cmd__) { return __self__.getView(); }
        @Callback @BindSelector("setView:") public static void setView(UIViewController __self__, Selector __cmd__, UIView view) { __self__.setView(view); }
        @Callback @BindSelector("wantsFullScreenLayout") public static boolean isWantsFullScreenLayout(UIViewController __self__, Selector __cmd__) { return __self__.isWantsFullScreenLayout(); }
        @Callback @BindSelector("setWantsFullScreenLayout:") public static void setWantsFullScreenLayout(UIViewController __self__, Selector __cmd__, boolean wantsFullScreenLayout) { __self__.setWantsFullScreenLayout(wantsFullScreenLayout); }
        @Callback @BindSelector("addChildViewController:") public static void addChildViewController(UIViewController __self__, Selector __cmd__, UIViewController childController) { __self__.addChildViewController(childController); }
        @Callback @BindSelector("beginAppearanceTransition:animated:") public static void beginAppearanceTransition(UIViewController __self__, Selector __cmd__, boolean isAppearing, boolean animated) { __self__.beginAppearanceTransition(isAppearing, animated); }
        @Callback @BindSelector("canPerformUnwindSegueAction:fromViewController:withSender:") public static boolean canPerformUnwindSegueAction(UIViewController __self__, Selector __cmd__, Selector action, UIViewController fromViewController, NSObject sender) { return __self__.canPerformUnwindSegueAction(action, fromViewController, sender); }
        @Callback @BindSelector("decodeRestorableStateWithCoder:") public static void decodeRestorableState(UIViewController __self__, Selector __cmd__, NSCoder coder) { __self__.decodeRestorableState(coder); }
        @Callback @BindSelector("didMoveToParentViewController:") public static void didMoveToParentViewController(UIViewController __self__, Selector __cmd__, UIViewController parent) { __self__.didMoveToParentViewController(parent); }
        @Callback @BindSelector("didReceiveMemoryWarning") public static void didReceiveMemoryWarning(UIViewController __self__, Selector __cmd__) { __self__.didReceiveMemoryWarning(); }
        @Callback @BindSelector("didRotateFromInterfaceOrientation:") public static void didRotate(UIViewController __self__, Selector __cmd__, UIInterfaceOrientation fromInterfaceOrientation) { __self__.didRotate(fromInterfaceOrientation); }
        @Callback @BindSelector("disablesAutomaticKeyboardDismissal") public static boolean disablesAutomaticKeyboardDismissal(UIViewController __self__, Selector __cmd__) { return __self__.disablesAutomaticKeyboardDismissal(); }
        @Callback @BindSelector("dismissViewControllerAnimated:completion:") public static void dismissViewController(UIViewController __self__, Selector __cmd__, boolean flag, VoidBlock completion) { __self__.dismissViewController(flag, completion); }
        @Callback @BindSelector("encodeRestorableStateWithCoder:") public static void encodeRestorableState(UIViewController __self__, Selector __cmd__, NSCoder coder) { __self__.encodeRestorableState(coder); }
        @Callback @BindSelector("endAppearanceTransition") public static void endAppearanceTransition(UIViewController __self__, Selector __cmd__) { __self__.endAppearanceTransition(); }
        @Callback @BindSelector("editButtonItem") public static UIBarButtonItem getEditButtonItem(UIViewController __self__, Selector __cmd__) { return __self__.getEditButtonItem(); }
        @Callback @BindSelector("preferredInterfaceOrientationForPresentation") public static UIInterfaceOrientation getPreferredInterfaceOrientation(UIViewController __self__, Selector __cmd__) { return __self__.getPreferredInterfaceOrientation(); }
        @Callback @BindSelector("rotatingFooterView") public static UIView getRotatingFooterView(UIViewController __self__, Selector __cmd__) { return __self__.getRotatingFooterView(); }
        @Callback @BindSelector("rotatingHeaderView") public static UIView getRotatingHeaderView(UIViewController __self__, Selector __cmd__) { return __self__.getRotatingHeaderView(); }
        @Callback @BindSelector("supportedInterfaceOrientations") public static UIInterfaceOrientationMask getSupportedInterfaceOrientations(UIViewController __self__, Selector __cmd__) { return __self__.getSupportedInterfaceOrientations(); }
        @Callback @BindSelector("segueForUnwindingToViewController:fromViewController:identifier:") public static UIStoryboardSegue getUnwindSegue(UIViewController __self__, Selector __cmd__, UIViewController toViewController, UIViewController fromViewController, String identifier) { return __self__.getUnwindSegue(toViewController, fromViewController, identifier); }
        @Callback @BindSelector("viewControllerForUnwindSegueAction:fromViewController:withSender:") public static UIViewController getUnwindSegueActionViewController(UIViewController __self__, Selector __cmd__, Selector action, UIViewController fromViewController, NSObject sender) { return __self__.getUnwindSegueActionViewController(action, fromViewController, sender); }
        @Callback @BindSelector("isBeingDismissed") public static boolean isBeingDismissed(UIViewController __self__, Selector __cmd__) { return __self__.isBeingDismissed(); }
        @Callback @BindSelector("isBeingPresented") public static boolean isBeingPresented(UIViewController __self__, Selector __cmd__) { return __self__.isBeingPresented(); }
        @Callback @BindSelector("isMovingFromParentViewController") public static boolean isMovingFromParentViewController(UIViewController __self__, Selector __cmd__) { return __self__.isMovingFromParentViewController(); }
        @Callback @BindSelector("isMovingToParentViewController") public static boolean isMovingToParentViewController(UIViewController __self__, Selector __cmd__) { return __self__.isMovingToParentViewController(); }
        @Callback @BindSelector("isViewLoaded") public static boolean isViewLoaded(UIViewController __self__, Selector __cmd__) { return __self__.isViewLoaded(); }
        @Callback @BindSelector("loadView") public static void loadView(UIViewController __self__, Selector __cmd__) { __self__.loadView(); }
        @Callback @BindSelector("performSegueWithIdentifier:sender:") public static void performSegue(UIViewController __self__, Selector __cmd__, String identifier, NSObject sender) { __self__.performSegue(identifier, sender); }
        @Callback @BindSelector("prepareForSegue:sender:") public static void prepareForSegue(UIViewController __self__, Selector __cmd__, UIStoryboardSegue segue, NSObject sender) { __self__.prepareForSegue(segue, sender); }
        @Callback @BindSelector("presentViewController:animated:completion:") public static void presentViewController(UIViewController __self__, Selector __cmd__, UIViewController viewControllerToPresent, boolean flag, VoidBlock completion) { __self__.presentViewController(viewControllerToPresent, flag, completion); }
        @Callback @BindSelector("removeFromParentViewController") public static void removeFromParentViewController(UIViewController __self__, Selector __cmd__) { __self__.removeFromParentViewController(); }
        @Callback @BindSelector("setEditing:animated:") public static void setEditing(UIViewController __self__, Selector __cmd__, boolean editing, boolean animated) { __self__.setEditing(editing, animated); }
        @Callback @BindSelector("setToolbarItems:animated:") public static void setToolbarItems(UIViewController __self__, Selector __cmd__, NSArray toolbarItems, boolean animated) { __self__.setToolbarItems(toolbarItems, animated); }
        @Callback @BindSelector("shouldAutomaticallyForwardAppearanceMethods") public static boolean shouldAutomaticallyForwardAppearanceMethods(UIViewController __self__, Selector __cmd__) { return __self__.shouldAutomaticallyForwardAppearanceMethods(); }
        @Callback @BindSelector("shouldAutomaticallyForwardRotationMethods") public static boolean shouldAutomaticallyForwardRotationMethods(UIViewController __self__, Selector __cmd__) { return __self__.shouldAutomaticallyForwardRotationMethods(); }
        @Callback @BindSelector("shouldAutorotate") public static boolean shouldAutorotate(UIViewController __self__, Selector __cmd__) { return __self__.shouldAutorotate(); }
        @Callback @BindSelector("shouldPerformSegueWithIdentifier:sender:") public static boolean shouldPerformSegue(UIViewController __self__, Selector __cmd__, String identifier, NSObject sender) { return __self__.shouldPerformSegue(identifier, sender); }
        @Callback @BindSelector("transitionFromViewController:toViewController:duration:options:animations:completion:") public static void transition(UIViewController __self__, Selector __cmd__, UIViewController fromViewController, UIViewController toViewController, double duration, UIViewAnimationOptions options, VoidBlock animations, VoidBooleanBlock completion) { __self__.transition(fromViewController, toViewController, duration, options, animations, completion); }
        @Callback @BindSelector("updateViewConstraints") public static void updateViewConstraints(UIViewController __self__, Selector __cmd__) { __self__.updateViewConstraints(); }
        @Callback @BindSelector("viewDidAppear:") public static void viewDidAppear(UIViewController __self__, Selector __cmd__, boolean animated) { __self__.viewDidAppear(animated); }
        @Callback @BindSelector("viewDidDisappear:") public static void viewDidDisappear(UIViewController __self__, Selector __cmd__, boolean animated) { __self__.viewDidDisappear(animated); }
        @Callback @BindSelector("viewDidLayoutSubviews") public static void viewDidLayoutSubviews(UIViewController __self__, Selector __cmd__) { __self__.viewDidLayoutSubviews(); }
        @Callback @BindSelector("viewDidLoad") public static void viewDidLoad(UIViewController __self__, Selector __cmd__) { __self__.viewDidLoad(); }
        @Callback @BindSelector("viewWillAppear:") public static void viewWillAppear(UIViewController __self__, Selector __cmd__, boolean animated) { __self__.viewWillAppear(animated); }
        @Callback @BindSelector("viewWillDisappear:") public static void viewWillDisappear(UIViewController __self__, Selector __cmd__, boolean animated) { __self__.viewWillDisappear(animated); }
        @Callback @BindSelector("viewWillLayoutSubviews") public static void viewWillLayoutSubviews(UIViewController __self__, Selector __cmd__) { __self__.viewWillLayoutSubviews(); }
        @Callback @BindSelector("willAnimateRotationToInterfaceOrientation:duration:") public static void willAnimateRotation(UIViewController __self__, Selector __cmd__, UIInterfaceOrientation interfaceOrientation, double duration) { __self__.willAnimateRotation(interfaceOrientation, duration); }
        @Callback @BindSelector("willMoveToParentViewController:") public static void willMoveToParent(UIViewController __self__, Selector __cmd__, UIViewController parent) { __self__.willMoveToParent(parent); }
        @Callback @BindSelector("willRotateToInterfaceOrientation:duration:") public static void willRotate(UIViewController __self__, Selector __cmd__, UIInterfaceOrientation toInterfaceOrientation, double duration) { __self__.willRotate(toInterfaceOrientation, duration); }
    }
    /*</callbacks>*/

}
