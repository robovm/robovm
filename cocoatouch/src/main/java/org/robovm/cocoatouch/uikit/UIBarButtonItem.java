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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html">UIBarButtonItem Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIBarButtonItem /*</name>*/ 
    extends /*<extends>*/ UIBarItem /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIBarButtonItem /*</name>*/.class);
    }

    /*<constructors>*/
    public UIBarButtonItem() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/initWithBarButtonSystemItem:target:action:">- (id)initWithBarButtonSystemItem:(UIBarButtonSystemItem)systemItem target:(id)target action:(SEL)action</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithBarButtonSystemItem:target:action:") public UIBarButtonItem(@Type("UIBarButtonSystemItem") UIBarButtonSystemItem systemItem, @Type("id") NSObject target, @Type("SEL") Selector action) {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/initWithCustomView:">- (id)initWithCustomView:(UIView *)customView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithCustomView:") public UIBarButtonItem(@Type("UIView *") UIView customView) {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/initWithImage:landscapeImagePhone:style:target:action:">- (id)initWithImage:(UIImage *)image landscapeImagePhone:(UIImage *)landscapeImagePhone style:(UIBarButtonItemStyle)style target:(id)target action:(SEL)action</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("initWithImage:landscapeImagePhone:style:target:action:") public UIBarButtonItem(@Type("UIImage *") UIImage image, @Type("UIImage *") UIImage landscapeImagePhone, @Type("UIBarButtonItemStyle") UIBarButtonItemStyle style, @Type("id") NSObject target, @Type("SEL") Selector action) {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/initWithImage:style:target:action:">- (id)initWithImage:(UIImage *)image style:(UIBarButtonItemStyle)style target:(id)target action:(SEL)action</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithImage:style:target:action:") public UIBarButtonItem(@Type("UIImage *") UIImage image, @Type("UIBarButtonItemStyle") UIBarButtonItemStyle style, @Type("id") NSObject target, @Type("SEL") Selector action) {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/initWithTitle:style:target:action:">- (id)initWithTitle:(NSString *)title style:(UIBarButtonItemStyle)style target:(id)target action:(SEL)action</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithTitle:style:target:action:") public UIBarButtonItem(@Type("NSString *") String title, @Type("UIBarButtonItemStyle") UIBarButtonItemStyle style, @Type("id") NSObject target, @Type("SEL") Selector action) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/action">@property(nonatomic) SEL action</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("action") public native @Type("SEL") Selector getAction();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/action">@property(nonatomic) SEL action</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAction:") public native void setAction(@Type("SEL") Selector v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/customView">@property(nonatomic, retain) UIView *customView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("customView") public native @Type("UIView *") UIView getCustomView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/customView">@property(nonatomic, retain) UIView *customView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setCustomView:") public native void setCustomView(@Type("UIView *") UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/possibleTitles">@property(nonatomic, copy) NSSet *possibleTitles</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("possibleTitles") public native @Type("NSSet *") NSSet getPossibleTitles();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/possibleTitles">@property(nonatomic, copy) NSSet *possibleTitles</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setPossibleTitles:") public native void setPossibleTitles(@Type("NSSet *") NSSet v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/style">@property(nonatomic) UIBarButtonItemStyle style</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("style") public native @Type("UIBarButtonItemStyle") UIBarButtonItemStyle getStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/style">@property(nonatomic) UIBarButtonItemStyle style</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setStyle:") public native void setStyle(@Type("UIBarButtonItemStyle") UIBarButtonItemStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/target">@property(nonatomic, assign) id target</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("target") public native @Type("id") NSObject getTarget();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/target">@property(nonatomic, assign) id target</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTarget:") public native void setTarget(@Type("id") NSObject v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/width">@property(nonatomic) CGFloat width</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("width") public native @Type("CGFloat") float getWidth();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/width">@property(nonatomic) CGFloat width</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setWidth:") public native void setWidth(@Type("CGFloat") float v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/backButtonBackgroundImageForState:barMetrics:">- (UIImage *)backButtonBackgroundImageForState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("backButtonBackgroundImageForState:barMetrics:") public native @Type("UIImage *") UIImage getBackButtonBackgroundImage(@Type("UIControlState") UIControlState state, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/backButtonBackgroundVerticalPositionAdjustmentForBarMetrics:">- (CGFloat)backButtonBackgroundVerticalPositionAdjustmentForBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("backButtonBackgroundVerticalPositionAdjustmentForBarMetrics:") public native @Type("CGFloat") float getBackButtonBackgroundVerticalPositionAdjustment(@Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/backButtonTitlePositionAdjustmentForBarMetrics:">- (UIOffset)backButtonTitlePositionAdjustmentForBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("backButtonTitlePositionAdjustmentForBarMetrics:") public native @Type("UIOffset") UIOffset getBackButtonTitlePositionAdjustment(@Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/backgroundImageForState:style:barMetrics:">- (UIImage *)backgroundImageForState:(UIControlState)state style:(UIBarButtonItemStyle)style barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("backgroundImageForState:style:barMetrics:") public native @Type("UIImage *") UIImage getBackgroundImage(@Type("UIControlState") UIControlState state, @Type("UIBarButtonItemStyle") UIBarButtonItemStyle style, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/backgroundImageForState:barMetrics:">- (UIImage *)backgroundImageForState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("backgroundImageForState:barMetrics:") public native @Type("UIImage *") UIImage getBackgroundImage(@Type("UIControlState") UIControlState state, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/backgroundVerticalPositionAdjustmentForBarMetrics:">- (CGFloat)backgroundVerticalPositionAdjustmentForBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("backgroundVerticalPositionAdjustmentForBarMetrics:") public native @Type("CGFloat") float getBackgroundVerticalPositionAdjustment(@Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/titlePositionAdjustmentForBarMetrics:">- (UIOffset)titlePositionAdjustmentForBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("titlePositionAdjustmentForBarMetrics:") public native @Type("UIOffset") UIOffset getTitlePositionAdjustment(@Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setBackButtonBackgroundImage:forState:barMetrics:">- (void)setBackButtonBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setBackButtonBackgroundImage:forState:barMetrics:") public native @Type("void") void setBackButtonBackgroundImage(@Type("UIImage *") UIImage backgroundImage, @Type("UIControlState") UIControlState state, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setBackButtonBackgroundVerticalPositionAdjustment:forBarMetrics:">- (void)setBackButtonBackgroundVerticalPositionAdjustment:(CGFloat)adjustment forBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setBackButtonBackgroundVerticalPositionAdjustment:forBarMetrics:") public native @Type("void") void setBackButtonBackgroundVerticalPositionAdjustment(@Type("CGFloat") float adjustment, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setBackButtonTitlePositionAdjustment:forBarMetrics:">- (void)setBackButtonTitlePositionAdjustment:(UIOffset)adjustment forBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setBackButtonTitlePositionAdjustment:forBarMetrics:") public native @Type("void") void setBackButtonTitlePositionAdjustment(@Type("UIOffset") UIOffset adjustment, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setBackgroundImage:forState:barMetrics:">- (void)setBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setBackgroundImage:forState:barMetrics:") public native @Type("void") void setBackgroundImage(@Type("UIImage *") UIImage backgroundImage, @Type("UIControlState") UIControlState state, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setBackgroundImage:forState:style:barMetrics:">- (void)setBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state style:(UIBarButtonItemStyle)style barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setBackgroundImage:forState:style:barMetrics:") public native @Type("void") void setBackgroundImage(@Type("UIImage *") UIImage backgroundImage, @Type("UIControlState") UIControlState state, @Type("UIBarButtonItemStyle") UIBarButtonItemStyle style, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setBackgroundVerticalPositionAdjustment:forBarMetrics:">- (void)setBackgroundVerticalPositionAdjustment:(CGFloat)adjustment forBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setBackgroundVerticalPositionAdjustment:forBarMetrics:") public native @Type("void") void setBackgroundVerticalPositionAdjustment(@Type("CGFloat") float adjustment, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setTitlePositionAdjustment:forBarMetrics:">- (void)setTitlePositionAdjustment:(UIOffset)adjustment forBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setTitlePositionAdjustment:forBarMetrics:") public native @Type("void") void setTitlePositionAdjustment(@Type("UIOffset") UIOffset adjustment, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /*</methods>*/

}
