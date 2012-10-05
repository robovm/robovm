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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UISegmentedControl /*</name>*/.class);

    /*<constructors>*/
    protected UISegmentedControl(SkipInit skipInit) { super(skipInit); }
    public UISegmentedControl() {}
    
    private static final Selector initWithItems$ = Selector.register("initWithItems:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithItems(UISegmentedControl __self__, Selector __cmd__, NSArray items);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/initWithItems:">- (id)initWithItems:(NSArray *)items</a>
     * @since Available in iOS 2.0 and later.
     */
    public UISegmentedControl(NSArray items) {
        super((SkipInit) null);
        objc_initWithItems(this, initWithItems$, items);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/apportionsSegmentWidthsByContent">@property(nonatomic) BOOL apportionsSegmentWidthsByContent</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("apportionsSegmentWidthsByContent") public native boolean isApportionsSegmentWidthsByContent();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/apportionsSegmentWidthsByContent">@property(nonatomic) BOOL apportionsSegmentWidthsByContent</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setApportionsSegmentWidthsByContent:") public native void setApportionsSegmentWidthsByContent(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/segmentedControlStyle">@property(nonatomic) UISegmentedControlStyle segmentedControlStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("segmentedControlStyle") public native UISegmentedControlStyle getControlStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/segmentedControlStyle">@property(nonatomic) UISegmentedControlStyle segmentedControlStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSegmentedControlStyle:") public native void setControlStyle(UISegmentedControlStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/momentary">@property(nonatomic, getter=isMomentary) BOOL momentary</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isMomentary") public native boolean isMomentary();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/momentary">@property(nonatomic, getter=isMomentary) BOOL momentary</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMomentary:") public native void setMomentary(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/numberOfSegments">@property(nonatomic, readonly) NSUInteger numberOfSegments</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("numberOfSegments") public native int getNumberOfSegments();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/selectedSegmentIndex">@property(nonatomic) NSInteger selectedSegmentIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("selectedSegmentIndex") public native int getSelectedSegment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/selectedSegmentIndex">@property(nonatomic) NSInteger selectedSegmentIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSelectedSegmentIndex:") public native void setSelectedSegment(int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tintColor") public native UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(UIColor v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector backgroundImageForState$barMetrics$ = Selector.register("backgroundImageForState:barMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getBackgroundImage(UISegmentedControl __self__, Selector __cmd__, UIControlState state, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getBackgroundImageSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, UIControlState state, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/backgroundImageForState:barMetrics:">- (UIImage *)backgroundImageForState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getBackgroundImage(UIControlState state, UIBarMetrics barMetrics) {
        if (customClass) { return objc_getBackgroundImageSuper(getSuper(), this, backgroundImageForState$barMetrics$, state, barMetrics); } else { return objc_getBackgroundImage(this, backgroundImageForState$barMetrics$, state, barMetrics); }
    }
    
    private static final Selector dividerImageForLeftSegmentState$rightSegmentState$barMetrics$ = Selector.register("dividerImageForLeftSegmentState:rightSegmentState:barMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getDividerImage(UISegmentedControl __self__, Selector __cmd__, UIControlState leftState, UIControlState rightState, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getDividerImageSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, UIControlState leftState, UIControlState rightState, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/dividerImageForLeftSegmentState:rightSegmentState:barMetrics:">- (UIImage *)dividerImageForLeftSegmentState:(UIControlState)leftState rightSegmentState:(UIControlState)rightState barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getDividerImage(UIControlState leftState, UIControlState rightState, UIBarMetrics barMetrics) {
        if (customClass) { return objc_getDividerImageSuper(getSuper(), this, dividerImageForLeftSegmentState$rightSegmentState$barMetrics$, leftState, rightState, barMetrics); } else { return objc_getDividerImage(this, dividerImageForLeftSegmentState$rightSegmentState$barMetrics$, leftState, rightState, barMetrics); }
    }
    
    private static final Selector contentOffsetForSegmentAtIndex$ = Selector.register("contentOffsetForSegmentAtIndex:");
    @Bridge(symbol = "objc_msgSend") private native static CGSize objc_getSegmentContentOffset(UISegmentedControl __self__, Selector __cmd__, int segment);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGSize objc_getSegmentContentOffsetSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, int segment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/contentOffsetForSegmentAtIndex:">- (CGSize)contentOffsetForSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGSize getSegmentContentOffset(int segment) {
        if (customClass) { return objc_getSegmentContentOffsetSuper(getSuper(), this, contentOffsetForSegmentAtIndex$, segment); } else { return objc_getSegmentContentOffset(this, contentOffsetForSegmentAtIndex$, segment); }
    }
    
    private static final Selector contentPositionAdjustmentForSegmentType$barMetrics$ = Selector.register("contentPositionAdjustmentForSegmentType:barMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static UIOffset objc_getSegmentContentPositionAdjustment(UISegmentedControl __self__, Selector __cmd__, UISegmentedControlSegment leftCenterRightOrAlone, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIOffset objc_getSegmentContentPositionAdjustmentSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, UISegmentedControlSegment leftCenterRightOrAlone, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/contentPositionAdjustmentForSegmentType:barMetrics:">- (UIOffset)contentPositionAdjustmentForSegmentType:(UISegmentedControlSegment)leftCenterRightOrAlone barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIOffset getSegmentContentPositionAdjustment(UISegmentedControlSegment leftCenterRightOrAlone, UIBarMetrics barMetrics) {
        if (customClass) { return objc_getSegmentContentPositionAdjustmentSuper(getSuper(), this, contentPositionAdjustmentForSegmentType$barMetrics$, leftCenterRightOrAlone, barMetrics); } else { return objc_getSegmentContentPositionAdjustment(this, contentPositionAdjustmentForSegmentType$barMetrics$, leftCenterRightOrAlone, barMetrics); }
    }
    
    private static final Selector imageForSegmentAtIndex$ = Selector.register("imageForSegmentAtIndex:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getSegmentImage(UISegmentedControl __self__, Selector __cmd__, int segment);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getSegmentImageSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, int segment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/imageForSegmentAtIndex:">- (UIImage *)imageForSegmentAtIndex:(NSUInteger)segment.</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getSegmentImage(int segment) {
        if (customClass) { return objc_getSegmentImageSuper(getSuper(), this, imageForSegmentAtIndex$, segment); } else { return objc_getSegmentImage(this, imageForSegmentAtIndex$, segment); }
    }
    
    private static final Selector titleForSegmentAtIndex$ = Selector.register("titleForSegmentAtIndex:");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getSegmentTitle(UISegmentedControl __self__, Selector __cmd__, int segment);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getSegmentTitleSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, int segment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/titleForSegmentAtIndex:">- (NSString *)titleForSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getSegmentTitle(int segment) {
        if (customClass) { return objc_getSegmentTitleSuper(getSuper(), this, titleForSegmentAtIndex$, segment); } else { return objc_getSegmentTitle(this, titleForSegmentAtIndex$, segment); }
    }
    
    private static final Selector widthForSegmentAtIndex$ = Selector.register("widthForSegmentAtIndex:");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getSegmentWidth(UISegmentedControl __self__, Selector __cmd__, int segment);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getSegmentWidthSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, int segment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/widthForSegmentAtIndex:">- (CGFloat)widthForSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getSegmentWidth(int segment) {
        if (customClass) { return objc_getSegmentWidthSuper(getSuper(), this, widthForSegmentAtIndex$, segment); } else { return objc_getSegmentWidth(this, widthForSegmentAtIndex$, segment); }
    }
    
    private static final Selector titleTextAttributesForState$ = Selector.register("titleTextAttributesForState:");
    @Bridge(symbol = "objc_msgSend") private native static NSDictionary objc_getTitleTextAttributes(UISegmentedControl __self__, Selector __cmd__, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSDictionary objc_getTitleTextAttributesSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/titleTextAttributesForState:">- (NSDictionary *)titleTextAttributesForState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSDictionary getTitleTextAttributes(UIControlState state) {
        if (customClass) { return objc_getTitleTextAttributesSuper(getSuper(), this, titleTextAttributesForState$, state); } else { return objc_getTitleTextAttributes(this, titleTextAttributesForState$, state); }
    }
    
    private static final Selector insertSegmentWithImage$atIndex$animated$ = Selector.register("insertSegmentWithImage:atIndex:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_insertSegment(UISegmentedControl __self__, Selector __cmd__, UIImage image, int segment, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_insertSegmentSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, UIImage image, int segment, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/insertSegmentWithImage:atIndex:animated:">- (void)insertSegmentWithImage:(UIImage *)image atIndex:(NSUInteger)segment animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void insertSegment(UIImage image, int segment, boolean animated) {
        if (customClass) { objc_insertSegmentSuper(getSuper(), this, insertSegmentWithImage$atIndex$animated$, image, segment, animated); } else { objc_insertSegment(this, insertSegmentWithImage$atIndex$animated$, image, segment, animated); }
    }
    
    private static final Selector insertSegmentWithTitle$atIndex$animated$ = Selector.register("insertSegmentWithTitle:atIndex:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_insertSegment(UISegmentedControl __self__, Selector __cmd__, String title, int segment, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_insertSegmentSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, String title, int segment, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/insertSegmentWithTitle:atIndex:animated:">- (void)insertSegmentWithTitle:(NSString *)title atIndex:(NSUInteger)segment animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void insertSegment(String title, int segment, boolean animated) {
        if (customClass) { objc_insertSegmentSuper(getSuper(), this, insertSegmentWithTitle$atIndex$animated$, title, segment, animated); } else { objc_insertSegment(this, insertSegmentWithTitle$atIndex$animated$, title, segment, animated); }
    }
    
    private static final Selector isEnabledForSegmentAtIndex$ = Selector.register("isEnabledForSegmentAtIndex:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isSegmentEnabled(UISegmentedControl __self__, Selector __cmd__, int segment);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isSegmentEnabledSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, int segment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/isEnabledForSegmentAtIndex:">- (BOOL)isEnabledForSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isSegmentEnabled(int segment) {
        if (customClass) { return objc_isSegmentEnabledSuper(getSuper(), this, isEnabledForSegmentAtIndex$, segment); } else { return objc_isSegmentEnabled(this, isEnabledForSegmentAtIndex$, segment); }
    }
    
    private static final Selector removeAllSegments = Selector.register("removeAllSegments");
    @Bridge(symbol = "objc_msgSend") private native static void objc_removeAllSegments(UISegmentedControl __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_removeAllSegmentsSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/removeAllSegments">- (void)removeAllSegments</a>
     * @since Available in iOS 2.0 and later.
     */
    public void removeAllSegments() {
        if (customClass) { objc_removeAllSegmentsSuper(getSuper(), this, removeAllSegments); } else { objc_removeAllSegments(this, removeAllSegments); }
    }
    
    private static final Selector removeSegmentAtIndex$animated$ = Selector.register("removeSegmentAtIndex:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_removeSegmentAtIndex(UISegmentedControl __self__, Selector __cmd__, int segment, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_removeSegmentAtIndexSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, int segment, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/removeSegmentAtIndex:animated:">- (void)removeSegmentAtIndex:(NSUInteger)segment animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void removeSegmentAtIndex(int segment, boolean animated) {
        if (customClass) { objc_removeSegmentAtIndexSuper(getSuper(), this, removeSegmentAtIndex$animated$, segment, animated); } else { objc_removeSegmentAtIndex(this, removeSegmentAtIndex$animated$, segment, animated); }
    }
    
    private static final Selector setBackgroundImage$forState$barMetrics$ = Selector.register("setBackgroundImage:forState:barMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setBackgroundImage(UISegmentedControl __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setBackgroundImageSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setBackgroundImage:forState:barMetrics:">- (void)setBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setBackgroundImage(UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics) {
        if (customClass) { objc_setBackgroundImageSuper(getSuper(), this, setBackgroundImage$forState$barMetrics$, backgroundImage, state, barMetrics); } else { objc_setBackgroundImage(this, setBackgroundImage$forState$barMetrics$, backgroundImage, state, barMetrics); }
    }
    
    private static final Selector setContentOffset$forSegmentAtIndex$ = Selector.register("setContentOffset:forSegmentAtIndex:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setContentOffset(UISegmentedControl __self__, Selector __cmd__, CGSize offset, int segment);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setContentOffsetSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, CGSize offset, int segment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setContentOffset:forSegmentAtIndex:">- (void)setContentOffset:(CGSize)offset forSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setContentOffset(CGSize offset, int segment) {
        if (customClass) { objc_setContentOffsetSuper(getSuper(), this, setContentOffset$forSegmentAtIndex$, offset, segment); } else { objc_setContentOffset(this, setContentOffset$forSegmentAtIndex$, offset, segment); }
    }
    
    private static final Selector setContentPositionAdjustment$forSegmentType$barMetrics$ = Selector.register("setContentPositionAdjustment:forSegmentType:barMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setContentPositionAdjustment(UISegmentedControl __self__, Selector __cmd__, UIOffset adjustment, UISegmentedControlSegment leftCenterRightOrAlone, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setContentPositionAdjustmentSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, UIOffset adjustment, UISegmentedControlSegment leftCenterRightOrAlone, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setContentPositionAdjustment:forSegmentType:barMetrics:">- (void)setContentPositionAdjustment:(UIOffset)adjustment forSegmentType:(UISegmentedControlSegment)leftCenterRightOrAlone barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setContentPositionAdjustment(UIOffset adjustment, UISegmentedControlSegment leftCenterRightOrAlone, UIBarMetrics barMetrics) {
        if (customClass) { objc_setContentPositionAdjustmentSuper(getSuper(), this, setContentPositionAdjustment$forSegmentType$barMetrics$, adjustment, leftCenterRightOrAlone, barMetrics); } else { objc_setContentPositionAdjustment(this, setContentPositionAdjustment$forSegmentType$barMetrics$, adjustment, leftCenterRightOrAlone, barMetrics); }
    }
    
    private static final Selector setDividerImage$forLeftSegmentState$rightSegmentState$barMetrics$ = Selector.register("setDividerImage:forLeftSegmentState:rightSegmentState:barMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setDividerImage(UISegmentedControl __self__, Selector __cmd__, UIImage dividerImage, UIControlState leftState, UIControlState rightState, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setDividerImageSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, UIImage dividerImage, UIControlState leftState, UIControlState rightState, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setDividerImage:forLeftSegmentState:rightSegmentState:barMetrics:">- (void)setDividerImage:(UIImage *)dividerImage forLeftSegmentState:(UIControlState)leftState rightSegmentState:(UIControlState)rightState barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setDividerImage(UIImage dividerImage, UIControlState leftState, UIControlState rightState, UIBarMetrics barMetrics) {
        if (customClass) { objc_setDividerImageSuper(getSuper(), this, setDividerImage$forLeftSegmentState$rightSegmentState$barMetrics$, dividerImage, leftState, rightState, barMetrics); } else { objc_setDividerImage(this, setDividerImage$forLeftSegmentState$rightSegmentState$barMetrics$, dividerImage, leftState, rightState, barMetrics); }
    }
    
    private static final Selector setEnabled$forSegmentAtIndex$ = Selector.register("setEnabled:forSegmentAtIndex:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setEnabled(UISegmentedControl __self__, Selector __cmd__, boolean enabled, int segment);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setEnabledSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, boolean enabled, int segment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setEnabled:forSegmentAtIndex:">- (void)setEnabled:(BOOL)enabled forSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setEnabled(boolean enabled, int segment) {
        if (customClass) { objc_setEnabledSuper(getSuper(), this, setEnabled$forSegmentAtIndex$, enabled, segment); } else { objc_setEnabled(this, setEnabled$forSegmentAtIndex$, enabled, segment); }
    }
    
    private static final Selector setImage$forSegmentAtIndex$ = Selector.register("setImage:forSegmentAtIndex:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setImage(UISegmentedControl __self__, Selector __cmd__, UIImage image, int segment);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setImageSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, UIImage image, int segment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setImage:forSegmentAtIndex:">- (void)setImage:(UIImage *)image forSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setImage(UIImage image, int segment) {
        if (customClass) { objc_setImageSuper(getSuper(), this, setImage$forSegmentAtIndex$, image, segment); } else { objc_setImage(this, setImage$forSegmentAtIndex$, image, segment); }
    }
    
    private static final Selector setTitle$forSegmentAtIndex$ = Selector.register("setTitle:forSegmentAtIndex:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTitle(UISegmentedControl __self__, Selector __cmd__, String title, int segment);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTitleSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, String title, int segment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setTitle:forSegmentAtIndex:">- (void)setTitle:(NSString *)title forSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTitle(String title, int segment) {
        if (customClass) { objc_setTitleSuper(getSuper(), this, setTitle$forSegmentAtIndex$, title, segment); } else { objc_setTitle(this, setTitle$forSegmentAtIndex$, title, segment); }
    }
    
    private static final Selector setTitleTextAttributes$forState$ = Selector.register("setTitleTextAttributes:forState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTitleTextAttributes(UISegmentedControl __self__, Selector __cmd__, NSDictionary attributes, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTitleTextAttributesSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, NSDictionary attributes, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setTitleTextAttributes:forState:">- (void)setTitleTextAttributes:(NSDictionary *)attributes forState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setTitleTextAttributes(NSDictionary attributes, UIControlState state) {
        if (customClass) { objc_setTitleTextAttributesSuper(getSuper(), this, setTitleTextAttributes$forState$, attributes, state); } else { objc_setTitleTextAttributes(this, setTitleTextAttributes$forState$, attributes, state); }
    }
    
    private static final Selector setWidth$forSegmentAtIndex$ = Selector.register("setWidth:forSegmentAtIndex:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setWidth(UISegmentedControl __self__, Selector __cmd__, float width, int segment);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setWidthSuper(ObjCSuper __super__, UISegmentedControl __self__, Selector __cmd__, float width, int segment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setWidth:forSegmentAtIndex:">- (void)setWidth:(CGFloat)width forSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setWidth(float width, int segment) {
        if (customClass) { objc_setWidthSuper(getSuper(), this, setWidth$forSegmentAtIndex$, width, segment); } else { objc_setWidth(this, setWidth$forSegmentAtIndex$, width, segment); }
    }
    /*</methods>*/

}
