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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html">UIAlertView Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIAlertView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIAlertView /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIAlertView /*</name>*/.class);

    public UIAlertView(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UIAlertView(SkipInit skipInit) { super(skipInit); }
    public UIAlertView() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector alertViewStyle = Selector.register("alertViewStyle");
    @Bridge private native static UIAlertViewStyle objc_getAlertViewStyle(UIAlertView __self__, Selector __cmd__);
    @Bridge private native static UIAlertViewStyle objc_getAlertViewStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/alertViewStyle">@property(nonatomic, assign) UIAlertViewStyle alertViewStyle</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIAlertViewStyle getAlertViewStyle() {
        if (customClass) { return objc_getAlertViewStyleSuper(getSuper(), alertViewStyle); } else { return objc_getAlertViewStyle(this, alertViewStyle); }
    }
    
    private static final Selector setAlertViewStyle$ = Selector.register("setAlertViewStyle:");
    @Bridge private native static void objc_setAlertViewStyle(UIAlertView __self__, Selector __cmd__, UIAlertViewStyle alertViewStyle);
    @Bridge private native static void objc_setAlertViewStyleSuper(ObjCSuper __super__, Selector __cmd__, UIAlertViewStyle alertViewStyle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/alertViewStyle">@property(nonatomic, assign) UIAlertViewStyle alertViewStyle</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setAlertViewStyle(UIAlertViewStyle alertViewStyle) {
        if (customClass) { objc_setAlertViewStyleSuper(getSuper(), setAlertViewStyle$, alertViewStyle); } else { objc_setAlertViewStyle(this, setAlertViewStyle$, alertViewStyle); }
    }
    
    private static final Selector cancelButtonIndex = Selector.register("cancelButtonIndex");
    @Bridge private native static int objc_getCancelButtonIndex(UIAlertView __self__, Selector __cmd__);
    @Bridge private native static int objc_getCancelButtonIndexSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/cancelButtonIndex">@property(nonatomic) NSInteger cancelButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getCancelButtonIndex() {
        if (customClass) { return objc_getCancelButtonIndexSuper(getSuper(), cancelButtonIndex); } else { return objc_getCancelButtonIndex(this, cancelButtonIndex); }
    }
    
    private static final Selector setCancelButtonIndex$ = Selector.register("setCancelButtonIndex:");
    @Bridge private native static void objc_setCancelButtonIndex(UIAlertView __self__, Selector __cmd__, int cancelButtonIndex);
    @Bridge private native static void objc_setCancelButtonIndexSuper(ObjCSuper __super__, Selector __cmd__, int cancelButtonIndex);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/cancelButtonIndex">@property(nonatomic) NSInteger cancelButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setCancelButtonIndex(int cancelButtonIndex) {
        if (customClass) { objc_setCancelButtonIndexSuper(getSuper(), setCancelButtonIndex$, cancelButtonIndex); } else { objc_setCancelButtonIndex(this, setCancelButtonIndex$, cancelButtonIndex); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UIAlertViewDelegate objc_getDelegate(UIAlertView __self__, Selector __cmd__);
    @Bridge private native static UIAlertViewDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/delegate">@property(nonatomic, assign) id delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIAlertViewDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UIAlertView __self__, Selector __cmd__, UIAlertViewDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UIAlertViewDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/delegate">@property(nonatomic, assign) id delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDelegate(UIAlertViewDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector firstOtherButtonIndex = Selector.register("firstOtherButtonIndex");
    @Bridge private native static int objc_getFirstOtherButtonIndex(UIAlertView __self__, Selector __cmd__);
    @Bridge private native static int objc_getFirstOtherButtonIndexSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/firstOtherButtonIndex">@property(nonatomic, readonly) NSInteger firstOtherButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getFirstOtherButtonIndex() {
        if (customClass) { return objc_getFirstOtherButtonIndexSuper(getSuper(), firstOtherButtonIndex); } else { return objc_getFirstOtherButtonIndex(this, firstOtherButtonIndex); }
    }
    
    private static final Selector message = Selector.register("message");
    @Bridge private native static String objc_getMessage(UIAlertView __self__, Selector __cmd__);
    @Bridge private native static String objc_getMessageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/message">@property(nonatomic, copy) NSString *message</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getMessage() {
        if (customClass) { return objc_getMessageSuper(getSuper(), message); } else { return objc_getMessage(this, message); }
    }
    
    private static final Selector setMessage$ = Selector.register("setMessage:");
    @Bridge private native static void objc_setMessage(UIAlertView __self__, Selector __cmd__, String message);
    @Bridge private native static void objc_setMessageSuper(ObjCSuper __super__, Selector __cmd__, String message);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/message">@property(nonatomic, copy) NSString *message</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMessage(String message) {
        if (customClass) { objc_setMessageSuper(getSuper(), setMessage$, message); } else { objc_setMessage(this, setMessage$, message); }
    }
    
    private static final Selector numberOfButtons = Selector.register("numberOfButtons");
    @Bridge private native static int objc_getNumberOfButtons(UIAlertView __self__, Selector __cmd__);
    @Bridge private native static int objc_getNumberOfButtonsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/numberOfButtons">@property(nonatomic, readonly) NSInteger numberOfButtons</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getNumberOfButtons() {
        if (customClass) { return objc_getNumberOfButtonsSuper(getSuper(), numberOfButtons); } else { return objc_getNumberOfButtons(this, numberOfButtons); }
    }
    
    private static final Selector title = Selector.register("title");
    @Bridge private native static String objc_getTitle(UIAlertView __self__, Selector __cmd__);
    @Bridge private native static String objc_getTitleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getTitle() {
        if (customClass) { return objc_getTitleSuper(getSuper(), title); } else { return objc_getTitle(this, title); }
    }
    
    private static final Selector setTitle$ = Selector.register("setTitle:");
    @Bridge private native static void objc_setTitle(UIAlertView __self__, Selector __cmd__, String title);
    @Bridge private native static void objc_setTitleSuper(ObjCSuper __super__, Selector __cmd__, String title);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTitle(String title) {
        if (customClass) { objc_setTitleSuper(getSuper(), setTitle$, title); } else { objc_setTitle(this, setTitle$, title); }
    }
    
    private static final Selector isVisible = Selector.register("isVisible");
    @Bridge private native static boolean objc_isVisible(UIAlertView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isVisibleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/visible">@property(nonatomic, readonly, getter=isVisible) BOOL visible</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isVisible() {
        if (customClass) { return objc_isVisibleSuper(getSuper(), isVisible); } else { return objc_isVisible(this, isVisible); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector addButtonWithTitle$ = Selector.register("addButtonWithTitle:");
    @Bridge private native static int objc_addButton(UIAlertView __self__, Selector __cmd__, String title);
    @Bridge private native static int objc_addButtonSuper(ObjCSuper __super__, Selector __cmd__, String title);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instm/UIAlertView/addButtonWithTitle:">- (NSInteger)addButtonWithTitle:(NSString *)title</a>
     * @since Available in iOS 2.0 and later.
     */
    public int addButton(String title) {
        if (customClass) { return objc_addButtonSuper(getSuper(), addButtonWithTitle$, title); } else { return objc_addButton(this, addButtonWithTitle$, title); }
    }
    
    private static final Selector dismissWithClickedButtonIndex$animated$ = Selector.register("dismissWithClickedButtonIndex:animated:");
    @Bridge private native static void objc_dismiss(UIAlertView __self__, Selector __cmd__, int buttonIndex, boolean animated);
    @Bridge private native static void objc_dismissSuper(ObjCSuper __super__, Selector __cmd__, int buttonIndex, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instm/UIAlertView/dismissWithClickedButtonIndex:animated:">- (void)dismissWithClickedButtonIndex:(NSInteger)buttonIndex animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void dismiss(int buttonIndex, boolean animated) {
        if (customClass) { objc_dismissSuper(getSuper(), dismissWithClickedButtonIndex$animated$, buttonIndex, animated); } else { objc_dismiss(this, dismissWithClickedButtonIndex$animated$, buttonIndex, animated); }
    }
    
    private static final Selector buttonTitleAtIndex$ = Selector.register("buttonTitleAtIndex:");
    @Bridge private native static String objc_getButtonTitle(UIAlertView __self__, Selector __cmd__, int buttonIndex);
    @Bridge private native static String objc_getButtonTitleSuper(ObjCSuper __super__, Selector __cmd__, int buttonIndex);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instm/UIAlertView/buttonTitleAtIndex:">- (NSString *)buttonTitleAtIndex:(NSInteger)buttonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getButtonTitle(int buttonIndex) {
        if (customClass) { return objc_getButtonTitleSuper(getSuper(), buttonTitleAtIndex$, buttonIndex); } else { return objc_getButtonTitle(this, buttonTitleAtIndex$, buttonIndex); }
    }
    
    private static final Selector textFieldAtIndex$ = Selector.register("textFieldAtIndex:");
    @Bridge private native static UITextField objc_getTextField(UIAlertView __self__, Selector __cmd__, int textFieldIndex);
    @Bridge private native static UITextField objc_getTextFieldSuper(ObjCSuper __super__, Selector __cmd__, int textFieldIndex);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instm/UIAlertView/textFieldAtIndex:">- (UITextField *)textFieldAtIndex:(NSInteger)textFieldIndex</a>
     * @since Available in iOS 5.0 and later.
     */
    public UITextField getTextField(int textFieldIndex) {
        if (customClass) { return objc_getTextFieldSuper(getSuper(), textFieldAtIndex$, textFieldIndex); } else { return objc_getTextField(this, textFieldAtIndex$, textFieldIndex); }
    }
    
    private static final Selector show = Selector.register("show");
    @Bridge private native static void objc_show(UIAlertView __self__, Selector __cmd__);
    @Bridge private native static void objc_showSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instm/UIAlertView/show">- (void)show</a>
     * @since Available in iOS 2.0 and later.
     */
    public void show() {
        if (customClass) { objc_showSuper(getSuper(), show); } else { objc_show(this, show); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("alertViewStyle") public static UIAlertViewStyle getAlertViewStyle(UIAlertView __self__, Selector __cmd__) { return __self__.getAlertViewStyle(); }
        @Callback @BindSelector("setAlertViewStyle:") public static void setAlertViewStyle(UIAlertView __self__, Selector __cmd__, UIAlertViewStyle alertViewStyle) { __self__.setAlertViewStyle(alertViewStyle); }
        @Callback @BindSelector("cancelButtonIndex") public static int getCancelButtonIndex(UIAlertView __self__, Selector __cmd__) { return __self__.getCancelButtonIndex(); }
        @Callback @BindSelector("setCancelButtonIndex:") public static void setCancelButtonIndex(UIAlertView __self__, Selector __cmd__, int cancelButtonIndex) { __self__.setCancelButtonIndex(cancelButtonIndex); }
        @Callback @BindSelector("delegate") public static UIAlertViewDelegate getDelegate(UIAlertView __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UIAlertView __self__, Selector __cmd__, UIAlertViewDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("firstOtherButtonIndex") public static int getFirstOtherButtonIndex(UIAlertView __self__, Selector __cmd__) { return __self__.getFirstOtherButtonIndex(); }
        @Callback @BindSelector("message") public static String getMessage(UIAlertView __self__, Selector __cmd__) { return __self__.getMessage(); }
        @Callback @BindSelector("setMessage:") public static void setMessage(UIAlertView __self__, Selector __cmd__, String message) { __self__.setMessage(message); }
        @Callback @BindSelector("numberOfButtons") public static int getNumberOfButtons(UIAlertView __self__, Selector __cmd__) { return __self__.getNumberOfButtons(); }
        @Callback @BindSelector("title") public static String getTitle(UIAlertView __self__, Selector __cmd__) { return __self__.getTitle(); }
        @Callback @BindSelector("setTitle:") public static void setTitle(UIAlertView __self__, Selector __cmd__, String title) { __self__.setTitle(title); }
        @Callback @BindSelector("isVisible") public static boolean isVisible(UIAlertView __self__, Selector __cmd__) { return __self__.isVisible(); }
        @Callback @BindSelector("addButtonWithTitle:") public static int addButton(UIAlertView __self__, Selector __cmd__, String title) { return __self__.addButton(title); }
        @Callback @BindSelector("dismissWithClickedButtonIndex:animated:") public static void dismiss(UIAlertView __self__, Selector __cmd__, int buttonIndex, boolean animated) { __self__.dismiss(buttonIndex, animated); }
        @Callback @BindSelector("buttonTitleAtIndex:") public static String getButtonTitle(UIAlertView __self__, Selector __cmd__, int buttonIndex) { return __self__.getButtonTitle(buttonIndex); }
        @Callback @BindSelector("textFieldAtIndex:") public static UITextField getTextField(UIAlertView __self__, Selector __cmd__, int textFieldIndex) { return __self__.getTextField(textFieldIndex); }
        @Callback @BindSelector("show") public static void show(UIAlertView __self__, Selector __cmd__) { __self__.show(); }
    }
    /*</callbacks>*/

}
