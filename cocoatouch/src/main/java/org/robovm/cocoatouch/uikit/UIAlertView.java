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

    /*<constructors>*/
    public UIAlertView() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/alertViewStyle">@property(nonatomic, assign) UIAlertViewStyle alertViewStyle</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("alertViewStyle") public native @Type("UIAlertViewStyle") UIAlertViewStyle getAlertViewStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/alertViewStyle">@property(nonatomic, assign) UIAlertViewStyle alertViewStyle</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setAlertViewStyle:") public native void setAlertViewStyle(@Type("UIAlertViewStyle") UIAlertViewStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/cancelButtonIndex">@property(nonatomic) NSInteger cancelButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("cancelButtonIndex") public native @Type("NSInteger") int getCancelButtonIndex();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/cancelButtonIndex">@property(nonatomic) NSInteger cancelButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setCancelButtonIndex:") public native void setCancelButtonIndex(@Type("NSInteger") int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/delegate">@property(nonatomic, assign) id delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native @Type("id") UIAlertViewDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/delegate">@property(nonatomic, assign) id delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id") UIAlertViewDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/firstOtherButtonIndex">@property(nonatomic, readonly) NSInteger firstOtherButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("firstOtherButtonIndex") public native @Type("NSInteger") int getFirstOtherButtonIndex();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/message">@property(nonatomic, copy) NSString *message</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("message") public native @Type("NSString *") String getMessage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/message">@property(nonatomic, copy) NSString *message</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMessage:") public native void setMessage(@Type("NSString *") String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/numberOfButtons">@property(nonatomic, readonly) NSInteger numberOfButtons</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("numberOfButtons") public native @Type("NSInteger") int getNumberOfButtons();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("title") public native @Type("NSString *") String getTitle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTitle:") public native void setTitle(@Type("NSString *") String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instp/UIAlertView/visible">@property(nonatomic, readonly, getter=isVisible) BOOL visible</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isVisible") public native @Type("BOOL") boolean isVisible();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instm/UIAlertView/addButtonWithTitle:">- (NSInteger)addButtonWithTitle:(NSString *)title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("addButtonWithTitle:") public native @Type("NSInteger") int addButton(@Type("NSString *") String title);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instm/UIAlertView/dismissWithClickedButtonIndex:animated:">- (void)dismissWithClickedButtonIndex:(NSInteger)buttonIndex animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("dismissWithClickedButtonIndex:animated:") public native @Type("void") void dismiss(@Type("NSInteger") int buttonIndex, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instm/UIAlertView/buttonTitleAtIndex:">- (NSString *)buttonTitleAtIndex:(NSInteger)buttonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("buttonTitleAtIndex:") public native @Type("NSString *") String getButtonTitle(@Type("NSInteger") int buttonIndex);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instm/UIAlertView/textFieldAtIndex:">- (UITextField *)textFieldAtIndex:(NSInteger)textFieldIndex</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("textFieldAtIndex:") public native @Type("UITextField *") UITextField getTextField(@Type("NSInteger") int textFieldIndex);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertView_Class/UIAlertView/UIAlertView.html#//apple_ref/occ/instm/UIAlertView/show">- (void)show</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("show") public native @Type("void") void show();
    /*</methods>*/

}
