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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html">UINavigationBar Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UINavigationBar /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UINavigationBar /*</name>*/.class);
    }

    /*<constructors>*/
    public UINavigationBar() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/backItem">@property(nonatomic, readonly, retain) UINavigationItem *backItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("backItem") public native @Type("UINavigationItem *") UINavigationItem getBackItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/barStyle">@property(nonatomic, assign) UIBarStyle barStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("barStyle") public native @Type("UIBarStyle") UIBarStyle getBarStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/barStyle">@property(nonatomic, assign) UIBarStyle barStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBarStyle:") public native void setBarStyle(@Type("UIBarStyle") UIBarStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/delegate">@property(nonatomic, assign) id delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native @Type("id") NSObject getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/delegate">@property(nonatomic, assign) id delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id") NSObject v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/items">@property(nonatomic, copy) NSArray *items</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("items") public native @Type("NSArray *") NSArray getItems();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/items">@property(nonatomic, copy) NSArray *items</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setItems:") public native void setItems(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/shadowImage">@property(nonatomic,retain) UIImage *shadowImage</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("shadowImage") public native @Type("UIImage *") UIImage getShadowImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/shadowImage">@property(nonatomic,retain) UIImage *shadowImage</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setShadowImage:") public native void setShadowImage(@Type("UIImage *") UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/titleTextAttributes">@property(nonatomic, copy) NSDictionary *titleTextAttributes</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("titleTextAttributes") public native @Type("NSDictionary *") NSDictionary getTitleTextAttributes();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/titleTextAttributes">@property(nonatomic, copy) NSDictionary *titleTextAttributes</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setTitleTextAttributes:") public native void setTitleTextAttributes(@Type("NSDictionary *") NSDictionary v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/topItem">@property(nonatomic, readonly, retain) UINavigationItem *topItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("topItem") public native @Type("UINavigationItem *") UINavigationItem getTopItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/translucent">@property(nonatomic, assign, getter=isTranslucent) BOOL translucent</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isTranslucent") public native @Type("BOOL") boolean isTranslucent();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/translucent">@property(nonatomic, assign, getter=isTranslucent) BOOL translucent</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setTranslucent:") public native void setTranslucent(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/backgroundImageForBarMetrics:">- (UIImage *)backgroundImageForBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("backgroundImageForBarMetrics:") public native @Type("UIImage *") UIImage getBackgroundImage(@Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/titleVerticalPositionAdjustmentForBarMetrics:">- (CGFloat)titleVerticalPositionAdjustmentForBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("titleVerticalPositionAdjustmentForBarMetrics:") public native @Type("CGFloat") float getTitleVerticalPositionAdjustment(@Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/popNavigationItemAnimated:">- (UINavigationItem *)popNavigationItemAnimated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("popNavigationItemAnimated:") public native @Type("UINavigationItem *") UINavigationItem popNavigationItem(@Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/pushNavigationItem:animated:">- (void)pushNavigationItem:(UINavigationItem *)item animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("pushNavigationItem:animated:") public native @Type("void") void pushNavigationItem(@Type("UINavigationItem *") UINavigationItem item, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/setBackgroundImage:forBarMetrics:">- (void)setBackgroundImage:(UIImage *)backgroundImage forBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setBackgroundImage:forBarMetrics:") public native @Type("void") void setBackgroundImage(@Type("UIImage *") UIImage backgroundImage, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/setItems:animated:">- (void)setItems:(NSArray *)items animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setItems:animated:") public native @Type("void") void setItems(@Type("NSArray *") NSArray items, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/setTitleVerticalPositionAdjustment:forBarMetrics:">- (void)setTitleVerticalPositionAdjustment:(CGFloat)adjustment forBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setTitleVerticalPositionAdjustment:forBarMetrics:") public native @Type("void") void setTitleVerticalPositionAdjustment(@Type("CGFloat") float adjustment, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /*</methods>*/

}
