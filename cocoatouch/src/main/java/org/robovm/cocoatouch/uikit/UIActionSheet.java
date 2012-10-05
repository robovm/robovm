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
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html">UIActionSheet Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIActionSheet /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIActionSheet /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIActionSheet /*</name>*/.class);

    /*<constructors>*/
    protected UIActionSheet(SkipInit skipInit) { super(skipInit); }
    public UIActionSheet() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/actionSheetStyle">@property(nonatomic) UIActionSheetStyle actionSheetStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("actionSheetStyle") public native UIActionSheetStyle getActionSheetStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/actionSheetStyle">@property(nonatomic) UIActionSheetStyle actionSheetStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setActionSheetStyle:") public native void setActionSheetStyle(UIActionSheetStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/cancelButtonIndex">@property(nonatomic) NSInteger cancelButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("cancelButtonIndex") public native int getCancelButtonIndex();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/cancelButtonIndex">@property(nonatomic) NSInteger cancelButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setCancelButtonIndex:") public native void setCancelButtonIndex(int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/delegate">@property(nonatomic, assign) id&amp;lt;UIActionSheetDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native UIActionSheetDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/delegate">@property(nonatomic, assign) id&amp;lt;UIActionSheetDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(UIActionSheetDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/destructiveButtonIndex">@property(nonatomic) NSInteger destructiveButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("destructiveButtonIndex") public native int getDestructiveButtonIndex();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/destructiveButtonIndex">@property(nonatomic) NSInteger destructiveButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDestructiveButtonIndex:") public native void setDestructiveButtonIndex(int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/firstOtherButtonIndex">@property(nonatomic, readonly) NSInteger firstOtherButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("firstOtherButtonIndex") public native int getFirstOtherButtonIndex();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/numberOfButtons">@property(nonatomic, readonly) NSInteger numberOfButtons</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("numberOfButtons") public native int getNumberOfButtons();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("title") public native String getTitle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTitle:") public native void setTitle(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/visible">@property(nonatomic, readonly, getter=isVisible) BOOL visible</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isVisible") public native boolean isVisible();
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector addButtonWithTitle$ = Selector.register("addButtonWithTitle:");
    @Bridge(symbol = "objc_msgSend") private native static int objc_addButton(UIActionSheet __self__, Selector __cmd__, String title);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_addButtonSuper(ObjCSuper __super__, UIActionSheet __self__, Selector __cmd__, String title);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/addButtonWithTitle:">- (NSInteger)addButtonWithTitle:(NSString *)title</a>
     * @since Available in iOS 2.0 and later.
     */
    public int addButton(String title) {
        if (customClass) { return objc_addButtonSuper(getSuper(), this, addButtonWithTitle$, title); } else { return objc_addButton(this, addButtonWithTitle$, title); }
    }
    
    private static final Selector dismissWithClickedButtonIndex$animated$ = Selector.register("dismissWithClickedButtonIndex:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_dismiss(UIActionSheet __self__, Selector __cmd__, int buttonIndex, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_dismissSuper(ObjCSuper __super__, UIActionSheet __self__, Selector __cmd__, int buttonIndex, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/dismissWithClickedButtonIndex:animated:">- (void)dismissWithClickedButtonIndex:(NSInteger)buttonIndex animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void dismiss(int buttonIndex, boolean animated) {
        if (customClass) { objc_dismissSuper(getSuper(), this, dismissWithClickedButtonIndex$animated$, buttonIndex, animated); } else { objc_dismiss(this, dismissWithClickedButtonIndex$animated$, buttonIndex, animated); }
    }
    
    private static final Selector buttonTitleAtIndex$ = Selector.register("buttonTitleAtIndex:");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getButtonTitle(UIActionSheet __self__, Selector __cmd__, int buttonIndex);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getButtonTitleSuper(ObjCSuper __super__, UIActionSheet __self__, Selector __cmd__, int buttonIndex);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/buttonTitleAtIndex:">- (NSString *)buttonTitleAtIndex:(NSInteger)buttonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getButtonTitle(int buttonIndex) {
        if (customClass) { return objc_getButtonTitleSuper(getSuper(), this, buttonTitleAtIndex$, buttonIndex); } else { return objc_getButtonTitle(this, buttonTitleAtIndex$, buttonIndex); }
    }
    
    private static final Selector showFromBarButtonItem$animated$ = Selector.register("showFromBarButtonItem:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_showFrom(UIActionSheet __self__, Selector __cmd__, UIBarButtonItem item, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_showFromSuper(ObjCSuper __super__, UIActionSheet __self__, Selector __cmd__, UIBarButtonItem item, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/showFromBarButtonItem:animated:">- (void)showFromBarButtonItem:(UIBarButtonItem *)item animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public void showFrom(UIBarButtonItem item, boolean animated) {
        if (customClass) { objc_showFromSuper(getSuper(), this, showFromBarButtonItem$animated$, item, animated); } else { objc_showFrom(this, showFromBarButtonItem$animated$, item, animated); }
    }
    
    private static final Selector showFromToolbar$ = Selector.register("showFromToolbar:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_showFrom(UIActionSheet __self__, Selector __cmd__, UIToolbar view);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_showFromSuper(ObjCSuper __super__, UIActionSheet __self__, Selector __cmd__, UIToolbar view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/showFromToolbar:">- (void)showFromToolbar:(UIToolbar *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public void showFrom(UIToolbar view) {
        if (customClass) { objc_showFromSuper(getSuper(), this, showFromToolbar$, view); } else { objc_showFrom(this, showFromToolbar$, view); }
    }
    
    private static final Selector showFromTabBar$ = Selector.register("showFromTabBar:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_showFrom(UIActionSheet __self__, Selector __cmd__, UITabBar view);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_showFromSuper(ObjCSuper __super__, UIActionSheet __self__, Selector __cmd__, UITabBar view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/showFromTabBar:">- (void)showFromTabBar:(UITabBar *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public void showFrom(UITabBar view) {
        if (customClass) { objc_showFromSuper(getSuper(), this, showFromTabBar$, view); } else { objc_showFrom(this, showFromTabBar$, view); }
    }
    
    private static final Selector showFromRect$inView$animated$ = Selector.register("showFromRect:inView:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_showFrom(UIActionSheet __self__, Selector __cmd__, CGRect rect, UIView view, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_showFromSuper(ObjCSuper __super__, UIActionSheet __self__, Selector __cmd__, CGRect rect, UIView view, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/showFromRect:inView:animated:">- (void)showFromRect:(CGRect)rect inView:(UIView *)view animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    public void showFrom(CGRect rect, UIView view, boolean animated) {
        if (customClass) { objc_showFromSuper(getSuper(), this, showFromRect$inView$animated$, rect, view, animated); } else { objc_showFrom(this, showFromRect$inView$animated$, rect, view, animated); }
    }
    
    private static final Selector showInView$ = Selector.register("showInView:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_showInView(UIActionSheet __self__, Selector __cmd__, UIView view);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_showInViewSuper(ObjCSuper __super__, UIActionSheet __self__, Selector __cmd__, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/showInView:">- (void)showInView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public void showInView(UIView view) {
        if (customClass) { objc_showInViewSuper(getSuper(), this, showInView$, view); } else { objc_showInView(this, showInView$, view); }
    }
    /*</methods>*/

}
