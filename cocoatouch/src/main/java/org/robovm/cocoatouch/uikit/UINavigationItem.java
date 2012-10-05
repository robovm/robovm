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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html">UINavigationItem Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UINavigationItem /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UINavigationItem /*</name>*/.class);
    }

    /*<constructors>*/
    public UINavigationItem() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instm/UINavigationItem/initWithTitle:">- (id)initWithTitle:(NSString *)title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithTitle:") public UINavigationItem(@Type("NSString *") String title) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/backBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *backBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("backBarButtonItem") public native @Type("UIBarButtonItem *") UIBarButtonItem getBackBarButtonItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/backBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *backBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBackBarButtonItem:") public native void setBackBarButtonItem(@Type("UIBarButtonItem *") UIBarButtonItem v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/hidesBackButton">@property(nonatomic, assign) BOOL hidesBackButton</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("hidesBackButton") public native @Type("BOOL") boolean isHidesBackButton();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/hidesBackButton">@property(nonatomic, assign) BOOL hidesBackButton</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setHidesBackButton:") public native void setHidesBackButton(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *leftBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("leftBarButtonItem") public native @Type("UIBarButtonItem *") UIBarButtonItem getLeftBarButtonItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *leftBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setLeftBarButtonItem:") public native void setLeftBarButtonItem(@Type("UIBarButtonItem *") UIBarButtonItem v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftBarButtonItems">@property(nonatomic, copy) NSArray *leftBarButtonItems</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("leftBarButtonItems") public native @Type("NSArray *") NSArray getLeftBarButtonItems();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftBarButtonItems">@property(nonatomic, copy) NSArray *leftBarButtonItems</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setLeftBarButtonItems:") public native void setLeftBarButtonItems(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftItemsSupplementBackButton">@property BOOL leftItemsSupplementBackButton</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("leftItemsSupplementBackButton") public native @Type("BOOL") boolean isLeftItemsSupplementBackButton();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftItemsSupplementBackButton">@property BOOL leftItemsSupplementBackButton</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setLeftItemsSupplementBackButton:") public native void setLeftItemsSupplementBackButton(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/prompt">@property(nonatomic, copy) NSString *prompt</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("prompt") public native @Type("NSString *") String getPrompt();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/prompt">@property(nonatomic, copy) NSString *prompt</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setPrompt:") public native void setPrompt(@Type("NSString *") String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/rightBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *rightBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("rightBarButtonItem") public native @Type("UIBarButtonItem *") UIBarButtonItem getRightBarButtonItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/rightBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *rightBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setRightBarButtonItem:") public native void setRightBarButtonItem(@Type("UIBarButtonItem *") UIBarButtonItem v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/rightBarButtonItems">@property(nonatomic, copy) NSArray *rightBarButtonItems</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("rightBarButtonItems") public native @Type("NSArray *") NSArray getRightBarButtonItems();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/rightBarButtonItems">@property(nonatomic, copy) NSArray *rightBarButtonItems</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setRightBarButtonItems:") public native void setRightBarButtonItems(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("title") public native @Type("NSString *") String getTitle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTitle:") public native void setTitle(@Type("NSString *") String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/titleView">@property(nonatomic, retain) UIView *titleView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("titleView") public native @Type("UIView *") UIView getTitleView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/titleView">@property(nonatomic, retain) UIView *titleView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTitleView:") public native void setTitleView(@Type("UIView *") UIView v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instm/UINavigationItem/setHidesBackButton:animated:">- (void)setHidesBackButton:(BOOL)hidesBackButton animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setHidesBackButton:animated:") public native @Type("void") void setHidesBackButton(@Type("BOOL") boolean hidesBackButton, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instm/UINavigationItem/setLeftBarButtonItem:animated:">- (void)setLeftBarButtonItem:(UIBarButtonItem *)item animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setLeftBarButtonItem:animated:") public native @Type("void") void setLeftBarButtonItem(@Type("UIBarButtonItem *") UIBarButtonItem item, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instm/UINavigationItem/setLeftBarButtonItems:animated:">- (void)setLeftBarButtonItems:(NSArray *)items animated:(BOOL)animated</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setLeftBarButtonItems:animated:") public native @Type("void") void setLeftBarButtonItems(@Type("NSArray *") NSArray items, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instm/UINavigationItem/setRightBarButtonItem:animated:">- (void)setRightBarButtonItem:(UIBarButtonItem *)item animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setRightBarButtonItem:animated:") public native @Type("void") void setRightBarButtonItem(@Type("UIBarButtonItem *") UIBarButtonItem item, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instm/UINavigationItem/setRightBarButtonItems:animated:">- (void)setRightBarButtonItems:(NSArray *)items animated:(BOOL)animated</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setRightBarButtonItems:animated:") public native @Type("void") void setRightBarButtonItems(@Type("NSArray *") NSArray items, @Type("BOOL") boolean animated);
    /*</methods>*/

}
