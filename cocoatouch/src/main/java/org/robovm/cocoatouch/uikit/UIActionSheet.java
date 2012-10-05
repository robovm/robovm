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

    /*<constructors>*/
    public UIActionSheet() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/actionSheetStyle">@property(nonatomic) UIActionSheetStyle actionSheetStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("actionSheetStyle") public native @Type("UIActionSheetStyle") UIActionSheetStyle getActionSheetStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/actionSheetStyle">@property(nonatomic) UIActionSheetStyle actionSheetStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setActionSheetStyle:") public native void setActionSheetStyle(@Type("UIActionSheetStyle") UIActionSheetStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/cancelButtonIndex">@property(nonatomic) NSInteger cancelButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("cancelButtonIndex") public native @Type("NSInteger") int getCancelButtonIndex();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/cancelButtonIndex">@property(nonatomic) NSInteger cancelButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setCancelButtonIndex:") public native void setCancelButtonIndex(@Type("NSInteger") int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/delegate">@property(nonatomic, assign) id&amp;lt;UIActionSheetDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native @Type("id<UIActionSheetDelegate>") UIActionSheetDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/delegate">@property(nonatomic, assign) id&amp;lt;UIActionSheetDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UIActionSheetDelegate>") UIActionSheetDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/destructiveButtonIndex">@property(nonatomic) NSInteger destructiveButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("destructiveButtonIndex") public native @Type("NSInteger") int getDestructiveButtonIndex();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/destructiveButtonIndex">@property(nonatomic) NSInteger destructiveButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDestructiveButtonIndex:") public native void setDestructiveButtonIndex(@Type("NSInteger") int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/firstOtherButtonIndex">@property(nonatomic, readonly) NSInteger firstOtherButtonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("firstOtherButtonIndex") public native @Type("NSInteger") int getFirstOtherButtonIndex();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/numberOfButtons">@property(nonatomic, readonly) NSInteger numberOfButtons</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("numberOfButtons") public native @Type("NSInteger") int getNumberOfButtons();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("title") public native @Type("NSString *") String getTitle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTitle:") public native void setTitle(@Type("NSString *") String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instp/UIActionSheet/visible">@property(nonatomic, readonly, getter=isVisible) BOOL visible</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isVisible") public native @Type("BOOL") boolean isVisible();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/addButtonWithTitle:">- (NSInteger)addButtonWithTitle:(NSString *)title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("addButtonWithTitle:") public native @Type("NSInteger") int addButton(@Type("NSString *") String title);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/dismissWithClickedButtonIndex:animated:">- (void)dismissWithClickedButtonIndex:(NSInteger)buttonIndex animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("dismissWithClickedButtonIndex:animated:") public native @Type("void") void dismiss(@Type("NSInteger") int buttonIndex, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/buttonTitleAtIndex:">- (NSString *)buttonTitleAtIndex:(NSInteger)buttonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("buttonTitleAtIndex:") public native @Type("NSString *") String getButtonTitle(@Type("NSInteger") int buttonIndex);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/showFromBarButtonItem:animated:">- (void)showFromBarButtonItem:(UIBarButtonItem *)item animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("showFromBarButtonItem:animated:") public native @Type("void") void showFrom(@Type("UIBarButtonItem *") UIBarButtonItem item, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/showFromToolbar:">- (void)showFromToolbar:(UIToolbar *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("showFromToolbar:") public native @Type("void") void showFrom(@Type("UIToolbar *") UIToolbar view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/showFromTabBar:">- (void)showFromTabBar:(UITabBar *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("showFromTabBar:") public native @Type("void") void showFrom(@Type("UITabBar *") UITabBar view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/showFromRect:inView:animated:">- (void)showFromRect:(CGRect)rect inView:(UIView *)view animated:(BOOL)animated</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("showFromRect:inView:animated:") public native @Type("void") void showFrom(@Type("CGRect") CGRect rect, @Type("UIView *") UIView view, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActionSheet_Class/Reference/Reference.html#//apple_ref/occ/instm/UIActionSheet/showInView:">- (void)showInView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("showInView:") public native @Type("void") void showInView(@Type("UIView *") UIView view);
    /*</methods>*/

}
