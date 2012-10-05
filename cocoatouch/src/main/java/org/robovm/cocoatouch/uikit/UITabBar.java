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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html">UITabBar Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UITabBar /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITabBar /*</name>*/.class);
    }

    /*<constructors>*/
    public UITabBar() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/backgroundImage">@property(nonatomic, retain) UIImage *backgroundImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("backgroundImage") public native @Type("UIImage *") UIImage getBackgroundImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/backgroundImage">@property(nonatomic, retain) UIImage *backgroundImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setBackgroundImage:") public native void setBackgroundImage(@Type("UIImage *") UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/delegate">@property(nonatomic, assign) id&amp;lt;UITabBarDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native @Type("id<UITabBarDelegate>") UITabBarDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/delegate">@property(nonatomic, assign) id&amp;lt;UITabBarDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UITabBarDelegate>") UITabBarDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/items">@property(nonatomic, copy) NSArray *items</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("items") public native @Type("NSArray *") NSArray getItems();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/items">@property(nonatomic, copy) NSArray *items</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setItems:") public native void setItems(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/selectedImageTintColor">@property(nonatomic, retain) UIColor *selectedImageTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("selectedImageTintColor") public native @Type("UIColor *") UIColor getSelectedImageTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/selectedImageTintColor">@property(nonatomic, retain) UIColor *selectedImageTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setSelectedImageTintColor:") public native void setSelectedImageTintColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/selectedItem">@property(nonatomic, assign) UITabBarItem *selectedItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("selectedItem") public native @Type("UITabBarItem *") UITabBarItem getSelectedItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/selectedItem">@property(nonatomic, assign) UITabBarItem *selectedItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSelectedItem:") public native void setSelectedItem(@Type("UITabBarItem *") UITabBarItem v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/selectionIndicatorImage">@property(nonatomic, retain) UIImage *selectionIndicatorImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("selectionIndicatorImage") public native @Type("UIImage *") UIImage getSelectionIndicatorImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/selectionIndicatorImage">@property(nonatomic, retain) UIImage *selectionIndicatorImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setSelectionIndicatorImage:") public native void setSelectionIndicatorImage(@Type("UIImage *") UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/shadowImage">@property(nonatomic,retain) UIImage *shadowImage</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("shadowImage") public native @Type("UIImage *") UIImage getShadowImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/shadowImage">@property(nonatomic,retain) UIImage *shadowImage</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setShadowImage:") public native void setShadowImage(@Type("UIImage *") UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBar/beginCustomizingItems:">- (void)beginCustomizingItems:(NSArray *)items</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("beginCustomizingItems:") public native @Type("void") void beginCustomizing(@Type("NSArray *") NSArray items);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBar/endCustomizingAnimated:">- (BOOL)endCustomizingAnimated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("endCustomizingAnimated:") public native @Type("BOOL") boolean endCustomizing(@Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBar/isCustomizing">- (BOOL)isCustomizing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isCustomizing") public native @Type("BOOL") boolean isCustomizing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBar/setItems:animated:">- (void)setItems:(NSArray *)items animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setItems:animated:") public native @Type("void") void setItems(@Type("NSArray *") NSArray items, @Type("BOOL") boolean animated);
    /*</methods>*/

}
