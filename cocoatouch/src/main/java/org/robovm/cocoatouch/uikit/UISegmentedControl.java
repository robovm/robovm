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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html">UISegmentedControl Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UISegmentedControl /*</name>*/ 
    extends /*<extends>*/ UIControl /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UISegmentedControl /*</name>*/.class);
    }

    /*<constructors>*/
    public UISegmentedControl() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/initWithItems:">- (id)initWithItems:(NSArray *)items</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("initWithItems:") public UISegmentedControl(@Type("NSArray *") NSArray items) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/apportionsSegmentWidthsByContent">@property(nonatomic) BOOL apportionsSegmentWidthsByContent</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("apportionsSegmentWidthsByContent") public native @Type("BOOL") boolean isApportionsSegmentWidthsByContent();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/apportionsSegmentWidthsByContent">@property(nonatomic) BOOL apportionsSegmentWidthsByContent</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setApportionsSegmentWidthsByContent:") public native void setApportionsSegmentWidthsByContent(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/segmentedControlStyle">@property(nonatomic) UISegmentedControlStyle segmentedControlStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("segmentedControlStyle") public native @Type("UISegmentedControlStyle") UISegmentedControlStyle getControlStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/segmentedControlStyle">@property(nonatomic) UISegmentedControlStyle segmentedControlStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSegmentedControlStyle:") public native void setControlStyle(@Type("UISegmentedControlStyle") UISegmentedControlStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/momentary">@property(nonatomic, getter=isMomentary) BOOL momentary</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isMomentary") public native @Type("BOOL") boolean isMomentary();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/momentary">@property(nonatomic, getter=isMomentary) BOOL momentary</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMomentary:") public native void setMomentary(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/numberOfSegments">@property(nonatomic, readonly) NSUInteger numberOfSegments</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("numberOfSegments") public native @Type("NSUInteger") int getNumberOfSegments();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/selectedSegmentIndex">@property(nonatomic) NSInteger selectedSegmentIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("selectedSegmentIndex") public native @Type("NSInteger") int getSelectedSegment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/selectedSegmentIndex">@property(nonatomic) NSInteger selectedSegmentIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSelectedSegmentIndex:") public native void setSelectedSegment(@Type("NSInteger") int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/backgroundImageForState:barMetrics:">- (UIImage *)backgroundImageForState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("backgroundImageForState:barMetrics:") public native @Type("UIImage *") UIImage getBackgroundImage(@Type("UIControlState") UIControlState state, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/dividerImageForLeftSegmentState:rightSegmentState:barMetrics:">- (UIImage *)dividerImageForLeftSegmentState:(UIControlState)leftState rightSegmentState:(UIControlState)rightState barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("dividerImageForLeftSegmentState:rightSegmentState:barMetrics:") public native @Type("UIImage *") UIImage getDividerImage(@Type("UIControlState") UIControlState leftState, @Type("UIControlState") UIControlState rightState, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/contentOffsetForSegmentAtIndex:">- (CGSize)contentOffsetForSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("contentOffsetForSegmentAtIndex:") public native @Type("CGSize") CGSize getSegmentContentOffset(@Type("NSUInteger") int segment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/contentPositionAdjustmentForSegmentType:barMetrics:">- (UIOffset)contentPositionAdjustmentForSegmentType:(UISegmentedControlSegment)leftCenterRightOrAlone barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("contentPositionAdjustmentForSegmentType:barMetrics:") public native @Type("UIOffset") UIOffset getSegmentContentPositionAdjustment(@Type("UISegmentedControlSegment") UISegmentedControlSegment leftCenterRightOrAlone, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/imageForSegmentAtIndex:">- (UIImage *)imageForSegmentAtIndex:(NSUInteger)segment.</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("imageForSegmentAtIndex:") public native @Type("UIImage *") UIImage getSegmentImage(@Type("NSUInteger") int segment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/titleForSegmentAtIndex:">- (NSString *)titleForSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("titleForSegmentAtIndex:") public native @Type("NSString *") String getSegmentTitle(@Type("NSUInteger") int segment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/widthForSegmentAtIndex:">- (CGFloat)widthForSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("widthForSegmentAtIndex:") public native @Type("CGFloat") float getSegmentWidth(@Type("NSUInteger") int segment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/titleTextAttributesForState:">- (NSDictionary *)titleTextAttributesForState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("titleTextAttributesForState:") public native @Type("NSDictionary *") NSDictionary getTitleTextAttributes(@Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/insertSegmentWithImage:atIndex:animated:">- (void)insertSegmentWithImage:(UIImage *)image atIndex:(NSUInteger)segment animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("insertSegmentWithImage:atIndex:animated:") public native @Type("void") void insertSegment(@Type("UIImage *") UIImage image, @Type("NSUInteger") int segment, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/insertSegmentWithTitle:atIndex:animated:">- (void)insertSegmentWithTitle:(NSString *)title atIndex:(NSUInteger)segment animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("insertSegmentWithTitle:atIndex:animated:") public native @Type("void") void insertSegment(@Type("NSString *") String title, @Type("NSUInteger") int segment, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/isEnabledForSegmentAtIndex:">- (BOOL)isEnabledForSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isEnabledForSegmentAtIndex:") public native @Type("BOOL") boolean isSegmentEnabled(@Type("NSUInteger") int segment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/removeAllSegments">- (void)removeAllSegments</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("removeAllSegments") public native @Type("void") void removeAllSegments();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/removeSegmentAtIndex:animated:">- (void)removeSegmentAtIndex:(NSUInteger)segment animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("removeSegmentAtIndex:animated:") public native @Type("void") void removeSegmentAtIndex(@Type("NSUInteger") int segment, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setBackgroundImage:forState:barMetrics:">- (void)setBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setBackgroundImage:forState:barMetrics:") public native @Type("void") void setBackgroundImage(@Type("UIImage *") UIImage backgroundImage, @Type("UIControlState") UIControlState state, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setContentOffset:forSegmentAtIndex:">- (void)setContentOffset:(CGSize)offset forSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setContentOffset:forSegmentAtIndex:") public native @Type("void") void setContentOffset(@Type("CGSize") CGSize offset, @Type("NSUInteger") int segment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setContentPositionAdjustment:forSegmentType:barMetrics:">- (void)setContentPositionAdjustment:(UIOffset)adjustment forSegmentType:(UISegmentedControlSegment)leftCenterRightOrAlone barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setContentPositionAdjustment:forSegmentType:barMetrics:") public native @Type("void") void setContentPositionAdjustment(@Type("UIOffset") UIOffset adjustment, @Type("UISegmentedControlSegment") UISegmentedControlSegment leftCenterRightOrAlone, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setDividerImage:forLeftSegmentState:rightSegmentState:barMetrics:">- (void)setDividerImage:(UIImage *)dividerImage forLeftSegmentState:(UIControlState)leftState rightSegmentState:(UIControlState)rightState barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setDividerImage:forLeftSegmentState:rightSegmentState:barMetrics:") public native @Type("void") void setDividerImage(@Type("UIImage *") UIImage dividerImage, @Type("UIControlState") UIControlState leftState, @Type("UIControlState") UIControlState rightState, @Type("UIBarMetrics") UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setEnabled:forSegmentAtIndex:">- (void)setEnabled:(BOOL)enabled forSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setEnabled:forSegmentAtIndex:") public native @Type("void") void setEnabled(@Type("BOOL") boolean enabled, @Type("NSUInteger") int segment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setImage:forSegmentAtIndex:">- (void)setImage:(UIImage *)image forSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setImage:forSegmentAtIndex:") public native @Type("void") void setImage(@Type("UIImage *") UIImage image, @Type("NSUInteger") int segment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setTitle:forSegmentAtIndex:">- (void)setTitle:(NSString *)title forSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTitle:forSegmentAtIndex:") public native @Type("void") void setTitle(@Type("NSString *") String title, @Type("NSUInteger") int segment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setTitleTextAttributes:forState:">- (void)setTitleTextAttributes:(NSDictionary *)attributes forState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setTitleTextAttributes:forState:") public native @Type("void") void setTitleTextAttributes(@Type("NSDictionary *") NSDictionary attributes, @Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setWidth:forSegmentAtIndex:">- (void)setWidth:(CGFloat)width forSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setWidth:forSegmentAtIndex:") public native @Type("void") void setWidth(@Type("CGFloat") float width, @Type("NSUInteger") int segment);
    /*</methods>*/

}
