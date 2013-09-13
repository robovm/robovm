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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html">UISegmentedControl Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UISegmentedControl /*</name>*/ 
    extends /*<extends>*/ UIControl /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UISegmentedControl /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UISegmentedControl /*</name>*/.class);

    public UISegmentedControl(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UISegmentedControl(SkipInit skipInit) { super(skipInit); }
    public UISegmentedControl() {}
    
    private static final Selector initWithItems$ = Selector.register("initWithItems:");
    @Bridge private native static @Pointer long objc_initWithItems(UISegmentedControl __self__, Selector __cmd__, NSArray items);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/initWithItems:">- (id)initWithItems:(NSArray *)items</a>
     * @since Available in iOS 2.0 and later.
     */
    public UISegmentedControl(NSArray items) {
        super((SkipInit) null);
        initObject(objc_initWithItems(this, initWithItems$, items));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector apportionsSegmentWidthsByContent = Selector.register("apportionsSegmentWidthsByContent");
    @Bridge private native static boolean objc_isApportionsSegmentWidthsByContent(UISegmentedControl __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isApportionsSegmentWidthsByContentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/apportionsSegmentWidthsByContent">@property(nonatomic) BOOL apportionsSegmentWidthsByContent</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isApportionsSegmentWidthsByContent() {
        if (customClass) { return objc_isApportionsSegmentWidthsByContentSuper(getSuper(), apportionsSegmentWidthsByContent); } else { return objc_isApportionsSegmentWidthsByContent(this, apportionsSegmentWidthsByContent); }
    }
    
    private static final Selector setApportionsSegmentWidthsByContent$ = Selector.register("setApportionsSegmentWidthsByContent:");
    @Bridge private native static void objc_setApportionsSegmentWidthsByContent(UISegmentedControl __self__, Selector __cmd__, boolean apportionsSegmentWidthsByContent);
    @Bridge private native static void objc_setApportionsSegmentWidthsByContentSuper(ObjCSuper __super__, Selector __cmd__, boolean apportionsSegmentWidthsByContent);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/apportionsSegmentWidthsByContent">@property(nonatomic) BOOL apportionsSegmentWidthsByContent</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setApportionsSegmentWidthsByContent(boolean apportionsSegmentWidthsByContent) {
        if (customClass) { objc_setApportionsSegmentWidthsByContentSuper(getSuper(), setApportionsSegmentWidthsByContent$, apportionsSegmentWidthsByContent); } else { objc_setApportionsSegmentWidthsByContent(this, setApportionsSegmentWidthsByContent$, apportionsSegmentWidthsByContent); }
    }
    
    private static final Selector segmentedControlStyle = Selector.register("segmentedControlStyle");
    @Bridge private native static UISegmentedControlStyle objc_getControlStyle(UISegmentedControl __self__, Selector __cmd__);
    @Bridge private native static UISegmentedControlStyle objc_getControlStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/segmentedControlStyle">@property(nonatomic) UISegmentedControlStyle segmentedControlStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public UISegmentedControlStyle getControlStyle() {
        if (customClass) { return objc_getControlStyleSuper(getSuper(), segmentedControlStyle); } else { return objc_getControlStyle(this, segmentedControlStyle); }
    }
    
    private static final Selector setSegmentedControlStyle$ = Selector.register("setSegmentedControlStyle:");
    @Bridge private native static void objc_setControlStyle(UISegmentedControl __self__, Selector __cmd__, UISegmentedControlStyle controlStyle);
    @Bridge private native static void objc_setControlStyleSuper(ObjCSuper __super__, Selector __cmd__, UISegmentedControlStyle controlStyle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/segmentedControlStyle">@property(nonatomic) UISegmentedControlStyle segmentedControlStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setControlStyle(UISegmentedControlStyle controlStyle) {
        if (customClass) { objc_setControlStyleSuper(getSuper(), setSegmentedControlStyle$, controlStyle); } else { objc_setControlStyle(this, setSegmentedControlStyle$, controlStyle); }
    }
    
    private static final Selector isMomentary = Selector.register("isMomentary");
    @Bridge private native static boolean objc_isMomentary(UISegmentedControl __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isMomentarySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/momentary">@property(nonatomic, getter=isMomentary) BOOL momentary</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isMomentary() {
        if (customClass) { return objc_isMomentarySuper(getSuper(), isMomentary); } else { return objc_isMomentary(this, isMomentary); }
    }
    
    private static final Selector setMomentary$ = Selector.register("setMomentary:");
    @Bridge private native static void objc_setMomentary(UISegmentedControl __self__, Selector __cmd__, boolean momentary);
    @Bridge private native static void objc_setMomentarySuper(ObjCSuper __super__, Selector __cmd__, boolean momentary);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/momentary">@property(nonatomic, getter=isMomentary) BOOL momentary</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMomentary(boolean momentary) {
        if (customClass) { objc_setMomentarySuper(getSuper(), setMomentary$, momentary); } else { objc_setMomentary(this, setMomentary$, momentary); }
    }
    
    private static final Selector numberOfSegments = Selector.register("numberOfSegments");
    @Bridge private native static int objc_getNumberOfSegments(UISegmentedControl __self__, Selector __cmd__);
    @Bridge private native static int objc_getNumberOfSegmentsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/numberOfSegments">@property(nonatomic, readonly) NSUInteger numberOfSegments</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getNumberOfSegments() {
        if (customClass) { return objc_getNumberOfSegmentsSuper(getSuper(), numberOfSegments); } else { return objc_getNumberOfSegments(this, numberOfSegments); }
    }
    
    private static final Selector selectedSegmentIndex = Selector.register("selectedSegmentIndex");
    @Bridge private native static int objc_getSelectedSegment(UISegmentedControl __self__, Selector __cmd__);
    @Bridge private native static int objc_getSelectedSegmentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/selectedSegmentIndex">@property(nonatomic) NSInteger selectedSegmentIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getSelectedSegment() {
        if (customClass) { return objc_getSelectedSegmentSuper(getSuper(), selectedSegmentIndex); } else { return objc_getSelectedSegment(this, selectedSegmentIndex); }
    }
    
    private static final Selector setSelectedSegmentIndex$ = Selector.register("setSelectedSegmentIndex:");
    @Bridge private native static void objc_setSelectedSegment(UISegmentedControl __self__, Selector __cmd__, int selectedSegment);
    @Bridge private native static void objc_setSelectedSegmentSuper(ObjCSuper __super__, Selector __cmd__, int selectedSegment);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/selectedSegmentIndex">@property(nonatomic) NSInteger selectedSegmentIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSelectedSegment(int selectedSegment) {
        if (customClass) { objc_setSelectedSegmentSuper(getSuper(), setSelectedSegmentIndex$, selectedSegment); } else { objc_setSelectedSegment(this, setSelectedSegmentIndex$, selectedSegment); }
    }
    
    private static final Selector tintColor = Selector.register("tintColor");
    @Bridge private native static UIColor objc_getTintColor(UISegmentedControl __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor getTintColor() {
        if (customClass) { return objc_getTintColorSuper(getSuper(), tintColor); } else { return objc_getTintColor(this, tintColor); }
    }
    
    private static final Selector setTintColor$ = Selector.register("setTintColor:");
    @Bridge private native static void objc_setTintColor(UISegmentedControl __self__, Selector __cmd__, UIColor tintColor);
    @Bridge private native static void objc_setTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor tintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instp/UISegmentedControl/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTintColor(UIColor tintColor) {
        if (customClass) { objc_setTintColorSuper(getSuper(), setTintColor$, tintColor); } else { objc_setTintColor(this, setTintColor$, tintColor); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector backgroundImageForState$barMetrics$ = Selector.register("backgroundImageForState:barMetrics:");
    @Bridge private native static UIImage objc_getBackgroundImage(UISegmentedControl __self__, Selector __cmd__, UIControlState state, UIBarMetrics barMetrics);
    @Bridge private native static UIImage objc_getBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/backgroundImageForState:barMetrics:">- (UIImage *)backgroundImageForState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getBackgroundImage(UIControlState state, UIBarMetrics barMetrics) {
        if (customClass) { return objc_getBackgroundImageSuper(getSuper(), backgroundImageForState$barMetrics$, state, barMetrics); } else { return objc_getBackgroundImage(this, backgroundImageForState$barMetrics$, state, barMetrics); }
    }
    
    private static final Selector dividerImageForLeftSegmentState$rightSegmentState$barMetrics$ = Selector.register("dividerImageForLeftSegmentState:rightSegmentState:barMetrics:");
    @Bridge private native static UIImage objc_getDividerImage(UISegmentedControl __self__, Selector __cmd__, UIControlState leftState, UIControlState rightState, UIBarMetrics barMetrics);
    @Bridge private native static UIImage objc_getDividerImageSuper(ObjCSuper __super__, Selector __cmd__, UIControlState leftState, UIControlState rightState, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/dividerImageForLeftSegmentState:rightSegmentState:barMetrics:">- (UIImage *)dividerImageForLeftSegmentState:(UIControlState)leftState rightSegmentState:(UIControlState)rightState barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getDividerImage(UIControlState leftState, UIControlState rightState, UIBarMetrics barMetrics) {
        if (customClass) { return objc_getDividerImageSuper(getSuper(), dividerImageForLeftSegmentState$rightSegmentState$barMetrics$, leftState, rightState, barMetrics); } else { return objc_getDividerImage(this, dividerImageForLeftSegmentState$rightSegmentState$barMetrics$, leftState, rightState, barMetrics); }
    }
    
    private static final Selector contentOffsetForSegmentAtIndex$ = Selector.register("contentOffsetForSegmentAtIndex:");
    @Bridge private native static @ByVal CGSize objc_getSegmentContentOffset(UISegmentedControl __self__, Selector __cmd__, int segment);
    @Bridge private native static @ByVal CGSize objc_getSegmentContentOffsetSuper(ObjCSuper __super__, Selector __cmd__, int segment);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/contentOffsetForSegmentAtIndex:">- (CGSize)contentOffsetForSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGSize getSegmentContentOffset(int segment) {
        if (customClass) { return objc_getSegmentContentOffsetSuper(getSuper(), contentOffsetForSegmentAtIndex$, segment); } else { return objc_getSegmentContentOffset(this, contentOffsetForSegmentAtIndex$, segment); }
    }
    
    private static final Selector contentPositionAdjustmentForSegmentType$barMetrics$ = Selector.register("contentPositionAdjustmentForSegmentType:barMetrics:");
    @Bridge private native static @ByVal UIOffset objc_getSegmentContentPositionAdjustment(UISegmentedControl __self__, Selector __cmd__, UISegmentedControlSegment leftCenterRightOrAlone, UIBarMetrics barMetrics);
    @Bridge private native static @ByVal UIOffset objc_getSegmentContentPositionAdjustmentSuper(ObjCSuper __super__, Selector __cmd__, UISegmentedControlSegment leftCenterRightOrAlone, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/contentPositionAdjustmentForSegmentType:barMetrics:">- (UIOffset)contentPositionAdjustmentForSegmentType:(UISegmentedControlSegment)leftCenterRightOrAlone barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIOffset getSegmentContentPositionAdjustment(UISegmentedControlSegment leftCenterRightOrAlone, UIBarMetrics barMetrics) {
        if (customClass) { return objc_getSegmentContentPositionAdjustmentSuper(getSuper(), contentPositionAdjustmentForSegmentType$barMetrics$, leftCenterRightOrAlone, barMetrics); } else { return objc_getSegmentContentPositionAdjustment(this, contentPositionAdjustmentForSegmentType$barMetrics$, leftCenterRightOrAlone, barMetrics); }
    }
    
    private static final Selector imageForSegmentAtIndex$ = Selector.register("imageForSegmentAtIndex:");
    @Bridge private native static UIImage objc_getSegmentImage(UISegmentedControl __self__, Selector __cmd__, int segment);
    @Bridge private native static UIImage objc_getSegmentImageSuper(ObjCSuper __super__, Selector __cmd__, int segment);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/imageForSegmentAtIndex:">- (UIImage *)imageForSegmentAtIndex:(NSUInteger)segment.</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getSegmentImage(int segment) {
        if (customClass) { return objc_getSegmentImageSuper(getSuper(), imageForSegmentAtIndex$, segment); } else { return objc_getSegmentImage(this, imageForSegmentAtIndex$, segment); }
    }
    
    private static final Selector titleForSegmentAtIndex$ = Selector.register("titleForSegmentAtIndex:");
    @Bridge private native static String objc_getSegmentTitle(UISegmentedControl __self__, Selector __cmd__, int segment);
    @Bridge private native static String objc_getSegmentTitleSuper(ObjCSuper __super__, Selector __cmd__, int segment);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/titleForSegmentAtIndex:">- (NSString *)titleForSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getSegmentTitle(int segment) {
        if (customClass) { return objc_getSegmentTitleSuper(getSuper(), titleForSegmentAtIndex$, segment); } else { return objc_getSegmentTitle(this, titleForSegmentAtIndex$, segment); }
    }
    
    private static final Selector widthForSegmentAtIndex$ = Selector.register("widthForSegmentAtIndex:");
    @Bridge private native static float objc_getSegmentWidth(UISegmentedControl __self__, Selector __cmd__, int segment);
    @Bridge private native static float objc_getSegmentWidthSuper(ObjCSuper __super__, Selector __cmd__, int segment);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/widthForSegmentAtIndex:">- (CGFloat)widthForSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getSegmentWidth(int segment) {
        if (customClass) { return objc_getSegmentWidthSuper(getSuper(), widthForSegmentAtIndex$, segment); } else { return objc_getSegmentWidth(this, widthForSegmentAtIndex$, segment); }
    }
    
    private static final Selector titleTextAttributesForState$ = Selector.register("titleTextAttributesForState:");
    @Bridge private native static NSDictionary objc_getTitleTextAttributes(UISegmentedControl __self__, Selector __cmd__, UIControlState state);
    @Bridge private native static NSDictionary objc_getTitleTextAttributesSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/titleTextAttributesForState:">- (NSDictionary *)titleTextAttributesForState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSDictionary getTitleTextAttributes(UIControlState state) {
        if (customClass) { return objc_getTitleTextAttributesSuper(getSuper(), titleTextAttributesForState$, state); } else { return objc_getTitleTextAttributes(this, titleTextAttributesForState$, state); }
    }
    
    private static final Selector insertSegmentWithImage$atIndex$animated$ = Selector.register("insertSegmentWithImage:atIndex:animated:");
    @Bridge private native static void objc_insertSegment(UISegmentedControl __self__, Selector __cmd__, UIImage image, int segment, boolean animated);
    @Bridge private native static void objc_insertSegmentSuper(ObjCSuper __super__, Selector __cmd__, UIImage image, int segment, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/insertSegmentWithImage:atIndex:animated:">- (void)insertSegmentWithImage:(UIImage *)image atIndex:(NSUInteger)segment animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void insertSegment(UIImage image, int segment, boolean animated) {
        if (customClass) { objc_insertSegmentSuper(getSuper(), insertSegmentWithImage$atIndex$animated$, image, segment, animated); } else { objc_insertSegment(this, insertSegmentWithImage$atIndex$animated$, image, segment, animated); }
    }
    
    private static final Selector insertSegmentWithTitle$atIndex$animated$ = Selector.register("insertSegmentWithTitle:atIndex:animated:");
    @Bridge private native static void objc_insertSegment(UISegmentedControl __self__, Selector __cmd__, String title, int segment, boolean animated);
    @Bridge private native static void objc_insertSegmentSuper(ObjCSuper __super__, Selector __cmd__, String title, int segment, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/insertSegmentWithTitle:atIndex:animated:">- (void)insertSegmentWithTitle:(NSString *)title atIndex:(NSUInteger)segment animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void insertSegment(String title, int segment, boolean animated) {
        if (customClass) { objc_insertSegmentSuper(getSuper(), insertSegmentWithTitle$atIndex$animated$, title, segment, animated); } else { objc_insertSegment(this, insertSegmentWithTitle$atIndex$animated$, title, segment, animated); }
    }
    
    private static final Selector isEnabledForSegmentAtIndex$ = Selector.register("isEnabledForSegmentAtIndex:");
    @Bridge private native static boolean objc_isSegmentEnabled(UISegmentedControl __self__, Selector __cmd__, int segment);
    @Bridge private native static boolean objc_isSegmentEnabledSuper(ObjCSuper __super__, Selector __cmd__, int segment);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/isEnabledForSegmentAtIndex:">- (BOOL)isEnabledForSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isSegmentEnabled(int segment) {
        if (customClass) { return objc_isSegmentEnabledSuper(getSuper(), isEnabledForSegmentAtIndex$, segment); } else { return objc_isSegmentEnabled(this, isEnabledForSegmentAtIndex$, segment); }
    }
    
    private static final Selector removeAllSegments = Selector.register("removeAllSegments");
    @Bridge private native static void objc_removeAllSegments(UISegmentedControl __self__, Selector __cmd__);
    @Bridge private native static void objc_removeAllSegmentsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/removeAllSegments">- (void)removeAllSegments</a>
     * @since Available in iOS 2.0 and later.
     */
    public void removeAllSegments() {
        if (customClass) { objc_removeAllSegmentsSuper(getSuper(), removeAllSegments); } else { objc_removeAllSegments(this, removeAllSegments); }
    }
    
    private static final Selector removeSegmentAtIndex$animated$ = Selector.register("removeSegmentAtIndex:animated:");
    @Bridge private native static void objc_removeSegmentAtIndex(UISegmentedControl __self__, Selector __cmd__, int segment, boolean animated);
    @Bridge private native static void objc_removeSegmentAtIndexSuper(ObjCSuper __super__, Selector __cmd__, int segment, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/removeSegmentAtIndex:animated:">- (void)removeSegmentAtIndex:(NSUInteger)segment animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void removeSegmentAtIndex(int segment, boolean animated) {
        if (customClass) { objc_removeSegmentAtIndexSuper(getSuper(), removeSegmentAtIndex$animated$, segment, animated); } else { objc_removeSegmentAtIndex(this, removeSegmentAtIndex$animated$, segment, animated); }
    }
    
    private static final Selector setBackgroundImage$forState$barMetrics$ = Selector.register("setBackgroundImage:forState:barMetrics:");
    @Bridge private native static void objc_setBackgroundImage(UISegmentedControl __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics);
    @Bridge private native static void objc_setBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setBackgroundImage:forState:barMetrics:">- (void)setBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setBackgroundImage(UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics) {
        if (customClass) { objc_setBackgroundImageSuper(getSuper(), setBackgroundImage$forState$barMetrics$, backgroundImage, state, barMetrics); } else { objc_setBackgroundImage(this, setBackgroundImage$forState$barMetrics$, backgroundImage, state, barMetrics); }
    }
    
    private static final Selector setContentOffset$forSegmentAtIndex$ = Selector.register("setContentOffset:forSegmentAtIndex:");
    @Bridge private native static void objc_setContentOffset(UISegmentedControl __self__, Selector __cmd__, @ByVal CGSize offset, int segment);
    @Bridge private native static void objc_setContentOffsetSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGSize offset, int segment);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setContentOffset:forSegmentAtIndex:">- (void)setContentOffset:(CGSize)offset forSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setContentOffset(CGSize offset, int segment) {
        if (customClass) { objc_setContentOffsetSuper(getSuper(), setContentOffset$forSegmentAtIndex$, offset, segment); } else { objc_setContentOffset(this, setContentOffset$forSegmentAtIndex$, offset, segment); }
    }
    
    private static final Selector setContentPositionAdjustment$forSegmentType$barMetrics$ = Selector.register("setContentPositionAdjustment:forSegmentType:barMetrics:");
    @Bridge private native static void objc_setContentPositionAdjustment(UISegmentedControl __self__, Selector __cmd__, @ByVal UIOffset adjustment, UISegmentedControlSegment leftCenterRightOrAlone, UIBarMetrics barMetrics);
    @Bridge private native static void objc_setContentPositionAdjustmentSuper(ObjCSuper __super__, Selector __cmd__, @ByVal UIOffset adjustment, UISegmentedControlSegment leftCenterRightOrAlone, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setContentPositionAdjustment:forSegmentType:barMetrics:">- (void)setContentPositionAdjustment:(UIOffset)adjustment forSegmentType:(UISegmentedControlSegment)leftCenterRightOrAlone barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setContentPositionAdjustment(UIOffset adjustment, UISegmentedControlSegment leftCenterRightOrAlone, UIBarMetrics barMetrics) {
        if (customClass) { objc_setContentPositionAdjustmentSuper(getSuper(), setContentPositionAdjustment$forSegmentType$barMetrics$, adjustment, leftCenterRightOrAlone, barMetrics); } else { objc_setContentPositionAdjustment(this, setContentPositionAdjustment$forSegmentType$barMetrics$, adjustment, leftCenterRightOrAlone, barMetrics); }
    }
    
    private static final Selector setDividerImage$forLeftSegmentState$rightSegmentState$barMetrics$ = Selector.register("setDividerImage:forLeftSegmentState:rightSegmentState:barMetrics:");
    @Bridge private native static void objc_setDividerImage(UISegmentedControl __self__, Selector __cmd__, UIImage dividerImage, UIControlState leftState, UIControlState rightState, UIBarMetrics barMetrics);
    @Bridge private native static void objc_setDividerImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage dividerImage, UIControlState leftState, UIControlState rightState, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setDividerImage:forLeftSegmentState:rightSegmentState:barMetrics:">- (void)setDividerImage:(UIImage *)dividerImage forLeftSegmentState:(UIControlState)leftState rightSegmentState:(UIControlState)rightState barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setDividerImage(UIImage dividerImage, UIControlState leftState, UIControlState rightState, UIBarMetrics barMetrics) {
        if (customClass) { objc_setDividerImageSuper(getSuper(), setDividerImage$forLeftSegmentState$rightSegmentState$barMetrics$, dividerImage, leftState, rightState, barMetrics); } else { objc_setDividerImage(this, setDividerImage$forLeftSegmentState$rightSegmentState$barMetrics$, dividerImage, leftState, rightState, barMetrics); }
    }
    
    private static final Selector setEnabled$forSegmentAtIndex$ = Selector.register("setEnabled:forSegmentAtIndex:");
    @Bridge private native static void objc_setEnabled(UISegmentedControl __self__, Selector __cmd__, boolean enabled, int segment);
    @Bridge private native static void objc_setEnabledSuper(ObjCSuper __super__, Selector __cmd__, boolean enabled, int segment);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setEnabled:forSegmentAtIndex:">- (void)setEnabled:(BOOL)enabled forSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setEnabled(boolean enabled, int segment) {
        if (customClass) { objc_setEnabledSuper(getSuper(), setEnabled$forSegmentAtIndex$, enabled, segment); } else { objc_setEnabled(this, setEnabled$forSegmentAtIndex$, enabled, segment); }
    }
    
    private static final Selector setImage$forSegmentAtIndex$ = Selector.register("setImage:forSegmentAtIndex:");
    @Bridge private native static void objc_setImage(UISegmentedControl __self__, Selector __cmd__, UIImage image, int segment);
    @Bridge private native static void objc_setImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage image, int segment);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setImage:forSegmentAtIndex:">- (void)setImage:(UIImage *)image forSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setImage(UIImage image, int segment) {
        if (customClass) { objc_setImageSuper(getSuper(), setImage$forSegmentAtIndex$, image, segment); } else { objc_setImage(this, setImage$forSegmentAtIndex$, image, segment); }
    }
    
    private static final Selector setTitle$forSegmentAtIndex$ = Selector.register("setTitle:forSegmentAtIndex:");
    @Bridge private native static void objc_setTitle(UISegmentedControl __self__, Selector __cmd__, String title, int segment);
    @Bridge private native static void objc_setTitleSuper(ObjCSuper __super__, Selector __cmd__, String title, int segment);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setTitle:forSegmentAtIndex:">- (void)setTitle:(NSString *)title forSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTitle(String title, int segment) {
        if (customClass) { objc_setTitleSuper(getSuper(), setTitle$forSegmentAtIndex$, title, segment); } else { objc_setTitle(this, setTitle$forSegmentAtIndex$, title, segment); }
    }
    
    private static final Selector setTitleTextAttributes$forState$ = Selector.register("setTitleTextAttributes:forState:");
    @Bridge private native static void objc_setTitleTextAttributes(UISegmentedControl __self__, Selector __cmd__, NSDictionary attributes, UIControlState state);
    @Bridge private native static void objc_setTitleTextAttributesSuper(ObjCSuper __super__, Selector __cmd__, NSDictionary attributes, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setTitleTextAttributes:forState:">- (void)setTitleTextAttributes:(NSDictionary *)attributes forState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setTitleTextAttributes(NSDictionary attributes, UIControlState state) {
        if (customClass) { objc_setTitleTextAttributesSuper(getSuper(), setTitleTextAttributes$forState$, attributes, state); } else { objc_setTitleTextAttributes(this, setTitleTextAttributes$forState$, attributes, state); }
    }
    
    private static final Selector setWidth$forSegmentAtIndex$ = Selector.register("setWidth:forSegmentAtIndex:");
    @Bridge private native static void objc_setWidth(UISegmentedControl __self__, Selector __cmd__, float width, int segment);
    @Bridge private native static void objc_setWidthSuper(ObjCSuper __super__, Selector __cmd__, float width, int segment);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISegmentedControl_Class/Reference/UISegmentedControl.html#//apple_ref/occ/instm/UISegmentedControl/setWidth:forSegmentAtIndex:">- (void)setWidth:(CGFloat)width forSegmentAtIndex:(NSUInteger)segment</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setWidth(float width, int segment) {
        if (customClass) { objc_setWidthSuper(getSuper(), setWidth$forSegmentAtIndex$, width, segment); } else { objc_setWidth(this, setWidth$forSegmentAtIndex$, width, segment); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("apportionsSegmentWidthsByContent") public static boolean isApportionsSegmentWidthsByContent(UISegmentedControl __self__, Selector __cmd__) { return __self__.isApportionsSegmentWidthsByContent(); }
        @Callback @BindSelector("setApportionsSegmentWidthsByContent:") public static void setApportionsSegmentWidthsByContent(UISegmentedControl __self__, Selector __cmd__, boolean apportionsSegmentWidthsByContent) { __self__.setApportionsSegmentWidthsByContent(apportionsSegmentWidthsByContent); }
        @Callback @BindSelector("segmentedControlStyle") public static UISegmentedControlStyle getControlStyle(UISegmentedControl __self__, Selector __cmd__) { return __self__.getControlStyle(); }
        @Callback @BindSelector("setSegmentedControlStyle:") public static void setControlStyle(UISegmentedControl __self__, Selector __cmd__, UISegmentedControlStyle controlStyle) { __self__.setControlStyle(controlStyle); }
        @Callback @BindSelector("isMomentary") public static boolean isMomentary(UISegmentedControl __self__, Selector __cmd__) { return __self__.isMomentary(); }
        @Callback @BindSelector("setMomentary:") public static void setMomentary(UISegmentedControl __self__, Selector __cmd__, boolean momentary) { __self__.setMomentary(momentary); }
        @Callback @BindSelector("numberOfSegments") public static int getNumberOfSegments(UISegmentedControl __self__, Selector __cmd__) { return __self__.getNumberOfSegments(); }
        @Callback @BindSelector("selectedSegmentIndex") public static int getSelectedSegment(UISegmentedControl __self__, Selector __cmd__) { return __self__.getSelectedSegment(); }
        @Callback @BindSelector("setSelectedSegmentIndex:") public static void setSelectedSegment(UISegmentedControl __self__, Selector __cmd__, int selectedSegment) { __self__.setSelectedSegment(selectedSegment); }
        @Callback @BindSelector("tintColor") public static UIColor getTintColor(UISegmentedControl __self__, Selector __cmd__) { return __self__.getTintColor(); }
        @Callback @BindSelector("setTintColor:") public static void setTintColor(UISegmentedControl __self__, Selector __cmd__, UIColor tintColor) { __self__.setTintColor(tintColor); }
        @Callback @BindSelector("backgroundImageForState:barMetrics:") public static UIImage getBackgroundImage(UISegmentedControl __self__, Selector __cmd__, UIControlState state, UIBarMetrics barMetrics) { return __self__.getBackgroundImage(state, barMetrics); }
        @Callback @BindSelector("dividerImageForLeftSegmentState:rightSegmentState:barMetrics:") public static UIImage getDividerImage(UISegmentedControl __self__, Selector __cmd__, UIControlState leftState, UIControlState rightState, UIBarMetrics barMetrics) { return __self__.getDividerImage(leftState, rightState, barMetrics); }
        @Callback @BindSelector("contentOffsetForSegmentAtIndex:") public static @ByVal CGSize getSegmentContentOffset(UISegmentedControl __self__, Selector __cmd__, int segment) { return __self__.getSegmentContentOffset(segment); }
        @Callback @BindSelector("contentPositionAdjustmentForSegmentType:barMetrics:") public static @ByVal UIOffset getSegmentContentPositionAdjustment(UISegmentedControl __self__, Selector __cmd__, UISegmentedControlSegment leftCenterRightOrAlone, UIBarMetrics barMetrics) { return __self__.getSegmentContentPositionAdjustment(leftCenterRightOrAlone, barMetrics); }
        @Callback @BindSelector("imageForSegmentAtIndex:") public static UIImage getSegmentImage(UISegmentedControl __self__, Selector __cmd__, int segment) { return __self__.getSegmentImage(segment); }
        @Callback @BindSelector("titleForSegmentAtIndex:") public static String getSegmentTitle(UISegmentedControl __self__, Selector __cmd__, int segment) { return __self__.getSegmentTitle(segment); }
        @Callback @BindSelector("widthForSegmentAtIndex:") public static float getSegmentWidth(UISegmentedControl __self__, Selector __cmd__, int segment) { return __self__.getSegmentWidth(segment); }
        @Callback @BindSelector("titleTextAttributesForState:") public static NSDictionary getTitleTextAttributes(UISegmentedControl __self__, Selector __cmd__, UIControlState state) { return __self__.getTitleTextAttributes(state); }
        @Callback @BindSelector("insertSegmentWithImage:atIndex:animated:") public static void insertSegment(UISegmentedControl __self__, Selector __cmd__, UIImage image, int segment, boolean animated) { __self__.insertSegment(image, segment, animated); }
        @Callback @BindSelector("insertSegmentWithTitle:atIndex:animated:") public static void insertSegment(UISegmentedControl __self__, Selector __cmd__, String title, int segment, boolean animated) { __self__.insertSegment(title, segment, animated); }
        @Callback @BindSelector("isEnabledForSegmentAtIndex:") public static boolean isSegmentEnabled(UISegmentedControl __self__, Selector __cmd__, int segment) { return __self__.isSegmentEnabled(segment); }
        @Callback @BindSelector("removeAllSegments") public static void removeAllSegments(UISegmentedControl __self__, Selector __cmd__) { __self__.removeAllSegments(); }
        @Callback @BindSelector("removeSegmentAtIndex:animated:") public static void removeSegmentAtIndex(UISegmentedControl __self__, Selector __cmd__, int segment, boolean animated) { __self__.removeSegmentAtIndex(segment, animated); }
        @Callback @BindSelector("setBackgroundImage:forState:barMetrics:") public static void setBackgroundImage(UISegmentedControl __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics) { __self__.setBackgroundImage(backgroundImage, state, barMetrics); }
        @Callback @BindSelector("setContentOffset:forSegmentAtIndex:") public static void setContentOffset(UISegmentedControl __self__, Selector __cmd__, @ByVal CGSize offset, int segment) { __self__.setContentOffset(offset, segment); }
        @Callback @BindSelector("setContentPositionAdjustment:forSegmentType:barMetrics:") public static void setContentPositionAdjustment(UISegmentedControl __self__, Selector __cmd__, @ByVal UIOffset adjustment, UISegmentedControlSegment leftCenterRightOrAlone, UIBarMetrics barMetrics) { __self__.setContentPositionAdjustment(adjustment, leftCenterRightOrAlone, barMetrics); }
        @Callback @BindSelector("setDividerImage:forLeftSegmentState:rightSegmentState:barMetrics:") public static void setDividerImage(UISegmentedControl __self__, Selector __cmd__, UIImage dividerImage, UIControlState leftState, UIControlState rightState, UIBarMetrics barMetrics) { __self__.setDividerImage(dividerImage, leftState, rightState, barMetrics); }
        @Callback @BindSelector("setEnabled:forSegmentAtIndex:") public static void setEnabled(UISegmentedControl __self__, Selector __cmd__, boolean enabled, int segment) { __self__.setEnabled(enabled, segment); }
        @Callback @BindSelector("setImage:forSegmentAtIndex:") public static void setImage(UISegmentedControl __self__, Selector __cmd__, UIImage image, int segment) { __self__.setImage(image, segment); }
        @Callback @BindSelector("setTitle:forSegmentAtIndex:") public static void setTitle(UISegmentedControl __self__, Selector __cmd__, String title, int segment) { __self__.setTitle(title, segment); }
        @Callback @BindSelector("setTitleTextAttributes:forState:") public static void setTitleTextAttributes(UISegmentedControl __self__, Selector __cmd__, NSDictionary attributes, UIControlState state) { __self__.setTitleTextAttributes(attributes, state); }
        @Callback @BindSelector("setWidth:forSegmentAtIndex:") public static void setWidth(UISegmentedControl __self__, Selector __cmd__, float width, int segment) { __self__.setWidth(width, segment); }
    }
    /*</callbacks>*/

}
