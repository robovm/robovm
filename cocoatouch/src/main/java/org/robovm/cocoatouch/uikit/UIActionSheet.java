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
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html">UIActionSheet Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIActionSheet /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIActionSheet /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIActionSheet /*</name>*/.class);

    public UIActionSheet(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UIActionSheet(SkipInit skipInit) { super(skipInit); }
    public UIActionSheet() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector actionSheetStyle = Selector.register("actionSheetStyle");
    @Bridge private native static UIActionSheetStyle objc_getActionSheetStyle(UIActionSheet __self__, Selector __cmd__);
    @Bridge private native static UIActionSheetStyle objc_getActionSheetStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/actionSheetStyle">@property(nonatomic) UIActionSheetStyle actionSheetStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIActionSheetStyle getActionSheetStyle() {
        if (customClass) { return objc_getActionSheetStyleSuper(getSuper(), actionSheetStyle); } else { return objc_getActionSheetStyle(this, actionSheetStyle); }
    }
    
    private static final Selector setActionSheetStyle$ = Selector.register("setActionSheetStyle:");
    @Bridge private native static void objc_setActionSheetStyle(UIActionSheet __self__, Selector __cmd__, UIActionSheetStyle actionSheetStyle);
    @Bridge private native static void objc_setActionSheetStyleSuper(ObjCSuper __super__, Selector __cmd__, UIActionSheetStyle actionSheetStyle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/actionSheetStyle">@property(nonatomic) UIActionSheetStyle actionSheetStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setActionSheetStyle(UIActionSheetStyle actionSheetStyle) {
        if (customClass) { objc_setActionSheetStyleSuper(getSuper(), setActionSheetStyle$, actionSheetStyle); } else { objc_setActionSheetStyle(this, setActionSheetStyle$, actionSheetStyle); }
    }
    
    private static final Selector cancelButtonIndex = Selector.register("cancelButtonIndex");
    @Bridge private native static int objc_getCancelButtonIndex(UIActionSheet __self__, Selector __cmd__);
    @Bridge private native static int objc_getCancelButtonIndexSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/cancelButtonIndex">@property(nonatomic) NSInteger cancelButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getCancelButtonIndex() {
        if (customClass) { return objc_getCancelButtonIndexSuper(getSuper(), cancelButtonIndex); } else { return objc_getCancelButtonIndex(this, cancelButtonIndex); }
    }
    
    private static final Selector setCancelButtonIndex$ = Selector.register("setCancelButtonIndex:");
    @Bridge private native static void objc_setCancelButtonIndex(UIActionSheet __self__, Selector __cmd__, int cancelButtonIndex);
    @Bridge private native static void objc_setCancelButtonIndexSuper(ObjCSuper __super__, Selector __cmd__, int cancelButtonIndex);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/cancelButtonIndex">@property(nonatomic) NSInteger cancelButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setCancelButtonIndex(int cancelButtonIndex) {
        if (customClass) { objc_setCancelButtonIndexSuper(getSuper(), setCancelButtonIndex$, cancelButtonIndex); } else { objc_setCancelButtonIndex(this, setCancelButtonIndex$, cancelButtonIndex); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UIActionSheetDelegate objc_getDelegate(UIActionSheet __self__, Selector __cmd__);
    @Bridge private native static UIActionSheetDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/delegate">@property(nonatomic, assign) id&amp;lt;UIActionSheetDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIActionSheetDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UIActionSheet __self__, Selector __cmd__, UIActionSheetDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UIActionSheetDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/delegate">@property(nonatomic, assign) id&amp;lt;UIActionSheetDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDelegate(UIActionSheetDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector destructiveButtonIndex = Selector.register("destructiveButtonIndex");
    @Bridge private native static int objc_getDestructiveButtonIndex(UIActionSheet __self__, Selector __cmd__);
    @Bridge private native static int objc_getDestructiveButtonIndexSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/destructiveButtonIndex">@property(nonatomic) NSInteger destructiveButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getDestructiveButtonIndex() {
        if (customClass) { return objc_getDestructiveButtonIndexSuper(getSuper(), destructiveButtonIndex); } else { return objc_getDestructiveButtonIndex(this, destructiveButtonIndex); }
    }
    
    private static final Selector setDestructiveButtonIndex$ = Selector.register("setDestructiveButtonIndex:");
    @Bridge private native static void objc_setDestructiveButtonIndex(UIActionSheet __self__, Selector __cmd__, int destructiveButtonIndex);
    @Bridge private native static void objc_setDestructiveButtonIndexSuper(ObjCSuper __super__, Selector __cmd__, int destructiveButtonIndex);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/destructiveButtonIndex">@property(nonatomic) NSInteger destructiveButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDestructiveButtonIndex(int destructiveButtonIndex) {
        if (customClass) { objc_setDestructiveButtonIndexSuper(getSuper(), setDestructiveButtonIndex$, destructiveButtonIndex); } else { objc_setDestructiveButtonIndex(this, setDestructiveButtonIndex$, destructiveButtonIndex); }
    }
    
    private static final Selector firstOtherButtonIndex = Selector.register("firstOtherButtonIndex");
    @Bridge private native static int objc_getFirstOtherButtonIndex(UIActionSheet __self__, Selector __cmd__);
    @Bridge private native static int objc_getFirstOtherButtonIndexSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/firstOtherButtonIndex">@property(nonatomic, readonly) NSInteger firstOtherButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getFirstOtherButtonIndex() {
        if (customClass) { return objc_getFirstOtherButtonIndexSuper(getSuper(), firstOtherButtonIndex); } else { return objc_getFirstOtherButtonIndex(this, firstOtherButtonIndex); }
    }
    
    private static final Selector numberOfButtons = Selector.register("numberOfButtons");
    @Bridge private native static int objc_getNumberOfButtons(UIActionSheet __self__, Selector __cmd__);
    @Bridge private native static int objc_getNumberOfButtonsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/numberOfButtons">@property(nonatomic, readonly) NSInteger numberOfButtons</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getNumberOfButtons() {
        if (customClass) { return objc_getNumberOfButtonsSuper(getSuper(), numberOfButtons); } else { return objc_getNumberOfButtons(this, numberOfButtons); }
    }
    
    private static final Selector title = Selector.register("title");
    @Bridge private native static String objc_getTitle(UIActionSheet __self__, Selector __cmd__);
    @Bridge private native static String objc_getTitleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getTitle() {
        if (customClass) { return objc_getTitleSuper(getSuper(), title); } else { return objc_getTitle(this, title); }
    }
    
    private static final Selector setTitle$ = Selector.register("setTitle:");
    @Bridge private native static void objc_setTitle(UIActionSheet __self__, Selector __cmd__, String title);
    @Bridge private native static void objc_setTitleSuper(ObjCSuper __super__, Selector __cmd__, String title);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTitle(String title) {
        if (customClass) { objc_setTitleSuper(getSuper(), setTitle$, title); } else { objc_setTitle(this, setTitle$, title); }
    }
    
    private static final Selector isVisible = Selector.register("isVisible");
    @Bridge private native static boolean objc_isVisible(UIActionSheet __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isVisibleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/visible">@property(nonatomic, readonly, getter=isVisible) BOOL visible</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isVisible() {
        if (customClass) { return objc_isVisibleSuper(getSuper(), isVisible); } else { return objc_isVisible(this, isVisible); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector addButtonWithTitle$ = Selector.register("addButtonWithTitle:");
    @Bridge private native static int objc_addButton(UIActionSheet __self__, Selector __cmd__, String title);
    @Bridge private native static int objc_addButtonSuper(ObjCSuper __super__, Selector __cmd__, String title);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/addButtonWithTitle:">- (NSInteger)addButtonWithTitle:(NSString *)title</a>
     * @since Available in iOS 2.0 and later.
     */
    public int addButton(String title) {
        if (customClass) { return objc_addButtonSuper(getSuper(), addButtonWithTitle$, title); } else { return objc_addButton(this, addButtonWithTitle$, title); }
    }
    
    private static final Selector dismissWithClickedButtonIndex$animated$ = Selector.register("dismissWithClickedButtonIndex:animated:");
    @Bridge private native static void objc_dismiss(UIActionSheet __self__, Selector __cmd__, int buttonIndex, boolean animated);
    @Bridge private native static void objc_dismissSuper(ObjCSuper __super__, Selector __cmd__, int buttonIndex, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/dismissWithClickedButtonIndex:animated:">- (void)dismissWithClickedButtonIndex:(NSInteger)buttonIndex animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void dismiss(int buttonIndex, boolean animated) {
        if (customClass) { objc_dismissSuper(getSuper(), dismissWithClickedButtonIndex$animated$, buttonIndex, animated); } else { objc_dismiss(this, dismissWithClickedButtonIndex$animated$, buttonIndex, animated); }
    }
    
    private static final Selector buttonTitleAtIndex$ = Selector.register("buttonTitleAtIndex:");
    @Bridge private native static String objc_getButtonTitle(UIActionSheet __self__, Selector __cmd__, int buttonIndex);
    @Bridge private native static String objc_getButtonTitleSuper(ObjCSuper __super__, Selector __cmd__, int buttonIndex);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/buttonTitleAtIndex:">- (NSString *)buttonTitleAtIndex:(NSInteger)buttonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getButtonTitle(int buttonIndex) {
        if (customClass) { return objc_getButtonTitleSuper(getSuper(), buttonTitleAtIndex$, buttonIndex); } else { return objc_getButtonTitle(this, buttonTitleAtIndex$, buttonIndex); }
    }
    
    private static final Selector showFromBarButtonItem$animated$ = Selector.register("showFromBarButtonItem:animated:");
    @Bridge private native static void objc_showFrom(UIActionSheet __self__, Selector __cmd__, UIBarButtonItem item, boolean animated);
    @Bridge private native static void objc_showFromSuper(ObjCSuper __super__, Selector __cmd__, UIBarButtonItem item, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/showFromBarButtonItem:animated:">- (void)showFromBarButtonItem:(UIBarButtonItem *)item animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public void showFrom(UIBarButtonItem item, boolean animated) {
        if (customClass) { objc_showFromSuper(getSuper(), showFromBarButtonItem$animated$, item, animated); } else { objc_showFrom(this, showFromBarButtonItem$animated$, item, animated); }
    }
    
    private static final Selector showFromToolbar$ = Selector.register("showFromToolbar:");
    @Bridge private native static void objc_showFrom(UIActionSheet __self__, Selector __cmd__, UIToolbar view);
    @Bridge private native static void objc_showFromSuper(ObjCSuper __super__, Selector __cmd__, UIToolbar view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/showFromToolbar:">- (void)showFromToolbar:(UIToolbar *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public void showFrom(UIToolbar view) {
        if (customClass) { objc_showFromSuper(getSuper(), showFromToolbar$, view); } else { objc_showFrom(this, showFromToolbar$, view); }
    }
    
    private static final Selector showFromTabBar$ = Selector.register("showFromTabBar:");
    @Bridge private native static void objc_showFrom(UIActionSheet __self__, Selector __cmd__, UITabBar view);
    @Bridge private native static void objc_showFromSuper(ObjCSuper __super__, Selector __cmd__, UITabBar view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/showFromTabBar:">- (void)showFromTabBar:(UITabBar *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public void showFrom(UITabBar view) {
        if (customClass) { objc_showFromSuper(getSuper(), showFromTabBar$, view); } else { objc_showFrom(this, showFromTabBar$, view); }
    }
    
    private static final Selector showFromRect$inView$animated$ = Selector.register("showFromRect:inView:animated:");
    @Bridge private native static void objc_showFrom(UIActionSheet __self__, Selector __cmd__, @ByVal CGRect rect, UIView view, boolean animated);
    @Bridge private native static void objc_showFromSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect rect, UIView view, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/showFromRect:inView:animated:">- (void)showFromRect:(CGRect)rect inView:(UIView *)view animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public void showFrom(CGRect rect, UIView view, boolean animated) {
        if (customClass) { objc_showFromSuper(getSuper(), showFromRect$inView$animated$, rect, view, animated); } else { objc_showFrom(this, showFromRect$inView$animated$, rect, view, animated); }
    }
    
    private static final Selector showInView$ = Selector.register("showInView:");
    @Bridge private native static void objc_showInView(UIActionSheet __self__, Selector __cmd__, UIView view);
    @Bridge private native static void objc_showInViewSuper(ObjCSuper __super__, Selector __cmd__, UIView view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/showInView:">- (void)showInView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public void showInView(UIView view) {
        if (customClass) { objc_showInViewSuper(getSuper(), showInView$, view); } else { objc_showInView(this, showInView$, view); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("actionSheetStyle") public static UIActionSheetStyle getActionSheetStyle(UIActionSheet __self__, Selector __cmd__) { return __self__.getActionSheetStyle(); }
        @Callback @BindSelector("setActionSheetStyle:") public static void setActionSheetStyle(UIActionSheet __self__, Selector __cmd__, UIActionSheetStyle actionSheetStyle) { __self__.setActionSheetStyle(actionSheetStyle); }
        @Callback @BindSelector("cancelButtonIndex") public static int getCancelButtonIndex(UIActionSheet __self__, Selector __cmd__) { return __self__.getCancelButtonIndex(); }
        @Callback @BindSelector("setCancelButtonIndex:") public static void setCancelButtonIndex(UIActionSheet __self__, Selector __cmd__, int cancelButtonIndex) { __self__.setCancelButtonIndex(cancelButtonIndex); }
        @Callback @BindSelector("delegate") public static UIActionSheetDelegate getDelegate(UIActionSheet __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UIActionSheet __self__, Selector __cmd__, UIActionSheetDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("destructiveButtonIndex") public static int getDestructiveButtonIndex(UIActionSheet __self__, Selector __cmd__) { return __self__.getDestructiveButtonIndex(); }
        @Callback @BindSelector("setDestructiveButtonIndex:") public static void setDestructiveButtonIndex(UIActionSheet __self__, Selector __cmd__, int destructiveButtonIndex) { __self__.setDestructiveButtonIndex(destructiveButtonIndex); }
        @Callback @BindSelector("firstOtherButtonIndex") public static int getFirstOtherButtonIndex(UIActionSheet __self__, Selector __cmd__) { return __self__.getFirstOtherButtonIndex(); }
        @Callback @BindSelector("numberOfButtons") public static int getNumberOfButtons(UIActionSheet __self__, Selector __cmd__) { return __self__.getNumberOfButtons(); }
        @Callback @BindSelector("title") public static String getTitle(UIActionSheet __self__, Selector __cmd__) { return __self__.getTitle(); }
        @Callback @BindSelector("setTitle:") public static void setTitle(UIActionSheet __self__, Selector __cmd__, String title) { __self__.setTitle(title); }
        @Callback @BindSelector("isVisible") public static boolean isVisible(UIActionSheet __self__, Selector __cmd__) { return __self__.isVisible(); }
        @Callback @BindSelector("addButtonWithTitle:") public static int addButton(UIActionSheet __self__, Selector __cmd__, String title) { return __self__.addButton(title); }
        @Callback @BindSelector("dismissWithClickedButtonIndex:animated:") public static void dismiss(UIActionSheet __self__, Selector __cmd__, int buttonIndex, boolean animated) { __self__.dismiss(buttonIndex, animated); }
        @Callback @BindSelector("buttonTitleAtIndex:") public static String getButtonTitle(UIActionSheet __self__, Selector __cmd__, int buttonIndex) { return __self__.getButtonTitle(buttonIndex); }
        @Callback @BindSelector("showFromBarButtonItem:animated:") public static void showFrom(UIActionSheet __self__, Selector __cmd__, UIBarButtonItem item, boolean animated) { __self__.showFrom(item, animated); }
        @Callback @BindSelector("showFromToolbar:") public static void showFrom(UIActionSheet __self__, Selector __cmd__, UIToolbar view) { __self__.showFrom(view); }
        @Callback @BindSelector("showFromTabBar:") public static void showFrom(UIActionSheet __self__, Selector __cmd__, UITabBar view) { __self__.showFrom(view); }
        @Callback @BindSelector("showFromRect:inView:animated:") public static void showFrom(UIActionSheet __self__, Selector __cmd__, @ByVal CGRect rect, UIView view, boolean animated) { __self__.showFrom(rect, view, animated); }
        @Callback @BindSelector("showInView:") public static void showInView(UIActionSheet __self__, Selector __cmd__, UIView view) { __self__.showInView(view); }
    }
    /*</callbacks>*/

}
