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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html">UIAlertView Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIAlertView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIAlertView /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIAlertView /*</name>*/.class);

    /*<constructors>*/
    protected UIAlertView(SkipInit skipInit) { super(skipInit); }
    public UIAlertView() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/alertViewStyle">@property(nonatomic, assign) UIAlertViewStyle alertViewStyle</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("alertViewStyle") public native UIAlertViewStyle getAlertViewStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/alertViewStyle">@property(nonatomic, assign) UIAlertViewStyle alertViewStyle</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setAlertViewStyle:") public native void setAlertViewStyle(UIAlertViewStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/cancelButtonIndex">@property(nonatomic) NSInteger cancelButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("cancelButtonIndex") public native int getCancelButtonIndex();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/cancelButtonIndex">@property(nonatomic) NSInteger cancelButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setCancelButtonIndex:") public native void setCancelButtonIndex(int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/delegate">@property(nonatomic, assign) id delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native UIAlertViewDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/delegate">@property(nonatomic, assign) id delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(UIAlertViewDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/firstOtherButtonIndex">@property(nonatomic, readonly) NSInteger firstOtherButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("firstOtherButtonIndex") public native int getFirstOtherButtonIndex();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/message">@property(nonatomic, copy) NSString *message</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("message") public native String getMessage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/message">@property(nonatomic, copy) NSString *message</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMessage:") public native void setMessage(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/numberOfButtons">@property(nonatomic, readonly) NSInteger numberOfButtons</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("numberOfButtons") public native int getNumberOfButtons();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("title") public native String getTitle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTitle:") public native void setTitle(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/visible">@property(nonatomic, readonly, getter=isVisible) BOOL visible</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isVisible") public native boolean isVisible();
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector addButtonWithTitle$ = Selector.register("addButtonWithTitle:");
    @Bridge(symbol = "objc_msgSend") private native static int objc_addButton(UIAlertView __self__, Selector __cmd__, String title);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_addButtonSuper(ObjCSuper __super__, UIAlertView __self__, Selector __cmd__, String title);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instm/UIAlertView/addButtonWithTitle:">- (NSInteger)addButtonWithTitle:(NSString *)title</a>
     * @since Available in iOS 2.0 and later.
     */
    public int addButton(String title) {
        if (customClass) { return objc_addButtonSuper(getSuper(), this, addButtonWithTitle$, title); } else { return objc_addButton(this, addButtonWithTitle$, title); }
    }
    
    private static final Selector dismissWithClickedButtonIndex$animated$ = Selector.register("dismissWithClickedButtonIndex:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_dismiss(UIAlertView __self__, Selector __cmd__, int buttonIndex, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_dismissSuper(ObjCSuper __super__, UIAlertView __self__, Selector __cmd__, int buttonIndex, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instm/UIAlertView/dismissWithClickedButtonIndex:animated:">- (void)dismissWithClickedButtonIndex:(NSInteger)buttonIndex animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void dismiss(int buttonIndex, boolean animated) {
        if (customClass) { objc_dismissSuper(getSuper(), this, dismissWithClickedButtonIndex$animated$, buttonIndex, animated); } else { objc_dismiss(this, dismissWithClickedButtonIndex$animated$, buttonIndex, animated); }
    }
    
    private static final Selector buttonTitleAtIndex$ = Selector.register("buttonTitleAtIndex:");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getButtonTitle(UIAlertView __self__, Selector __cmd__, int buttonIndex);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getButtonTitleSuper(ObjCSuper __super__, UIAlertView __self__, Selector __cmd__, int buttonIndex);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instm/UIAlertView/buttonTitleAtIndex:">- (NSString *)buttonTitleAtIndex:(NSInteger)buttonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getButtonTitle(int buttonIndex) {
        if (customClass) { return objc_getButtonTitleSuper(getSuper(), this, buttonTitleAtIndex$, buttonIndex); } else { return objc_getButtonTitle(this, buttonTitleAtIndex$, buttonIndex); }
    }
    
    private static final Selector textFieldAtIndex$ = Selector.register("textFieldAtIndex:");
    @Bridge(symbol = "objc_msgSend") private native static UITextField objc_getTextField(UIAlertView __self__, Selector __cmd__, int textFieldIndex);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextField objc_getTextFieldSuper(ObjCSuper __super__, UIAlertView __self__, Selector __cmd__, int textFieldIndex);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instm/UIAlertView/textFieldAtIndex:">- (UITextField *)textFieldAtIndex:(NSInteger)textFieldIndex</a>
     * @since Available in iOS 5.0 and later.
     */
    public UITextField getTextField(int textFieldIndex) {
        if (customClass) { return objc_getTextFieldSuper(getSuper(), this, textFieldAtIndex$, textFieldIndex); } else { return objc_getTextField(this, textFieldAtIndex$, textFieldIndex); }
    }
    
    private static final Selector show = Selector.register("show");
    @Bridge(symbol = "objc_msgSend") private native static void objc_show(UIAlertView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_showSuper(ObjCSuper __super__, UIAlertView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instm/UIAlertView/show">- (void)show</a>
     * @since Available in iOS 2.0 and later.
     */
    public void show() {
        if (customClass) { objc_showSuper(getSuper(), this, show); } else { objc_show(this, show); }
    }
    /*</methods>*/

}
