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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html">UIStepper Class Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIStepper /*</name>*/ 
    extends /*<extends>*/ UIControl /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIStepper /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIStepper /*</name>*/.class);

    public UIStepper(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UIStepper(SkipInit skipInit) { super(skipInit); }
    public UIStepper() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector autorepeat = Selector.register("autorepeat");
    @Bridge private native static boolean objc_isAutorepeat(UIStepper __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isAutorepeatSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/autorepeat">@property(nonatomic) BOOL autorepeat</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isAutorepeat() {
        if (customClass) { return objc_isAutorepeatSuper(getSuper(), autorepeat); } else { return objc_isAutorepeat(this, autorepeat); }
    }
    
    private static final Selector setAutorepeat$ = Selector.register("setAutorepeat:");
    @Bridge private native static void objc_setAutorepeat(UIStepper __self__, Selector __cmd__, boolean autorepeat);
    @Bridge private native static void objc_setAutorepeatSuper(ObjCSuper __super__, Selector __cmd__, boolean autorepeat);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/autorepeat">@property(nonatomic) BOOL autorepeat</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setAutorepeat(boolean autorepeat) {
        if (customClass) { objc_setAutorepeatSuper(getSuper(), setAutorepeat$, autorepeat); } else { objc_setAutorepeat(this, setAutorepeat$, autorepeat); }
    }
    
    private static final Selector isContinuous = Selector.register("isContinuous");
    @Bridge private native static boolean objc_isContinuous(UIStepper __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isContinuousSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/continuous">@property(nonatomic, getter=isContinuous) BOOL continuous</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isContinuous() {
        if (customClass) { return objc_isContinuousSuper(getSuper(), isContinuous); } else { return objc_isContinuous(this, isContinuous); }
    }
    
    private static final Selector setContinuous$ = Selector.register("setContinuous:");
    @Bridge private native static void objc_setContinuous(UIStepper __self__, Selector __cmd__, boolean continuous);
    @Bridge private native static void objc_setContinuousSuper(ObjCSuper __super__, Selector __cmd__, boolean continuous);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/continuous">@property(nonatomic, getter=isContinuous) BOOL continuous</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setContinuous(boolean continuous) {
        if (customClass) { objc_setContinuousSuper(getSuper(), setContinuous$, continuous); } else { objc_setContinuous(this, setContinuous$, continuous); }
    }
    
    private static final Selector maximumValue = Selector.register("maximumValue");
    @Bridge private native static double objc_getMaximumValue(UIStepper __self__, Selector __cmd__);
    @Bridge private native static double objc_getMaximumValueSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/maximumValue">@property(nonatomic) double maximumValue</a>
     * @since Available in iOS 5.0 and later.
     */
    public double getMaximumValue() {
        if (customClass) { return objc_getMaximumValueSuper(getSuper(), maximumValue); } else { return objc_getMaximumValue(this, maximumValue); }
    }
    
    private static final Selector setMaximumValue$ = Selector.register("setMaximumValue:");
    @Bridge private native static void objc_setMaximumValue(UIStepper __self__, Selector __cmd__, double maximumValue);
    @Bridge private native static void objc_setMaximumValueSuper(ObjCSuper __super__, Selector __cmd__, double maximumValue);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/maximumValue">@property(nonatomic) double maximumValue</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setMaximumValue(double maximumValue) {
        if (customClass) { objc_setMaximumValueSuper(getSuper(), setMaximumValue$, maximumValue); } else { objc_setMaximumValue(this, setMaximumValue$, maximumValue); }
    }
    
    private static final Selector minimumValue = Selector.register("minimumValue");
    @Bridge private native static double objc_getMinimumValue(UIStepper __self__, Selector __cmd__);
    @Bridge private native static double objc_getMinimumValueSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/minimumValue">@property(nonatomic) double minimumValue</a>
     * @since Available in iOS 5.0 and later.
     */
    public double getMinimumValue() {
        if (customClass) { return objc_getMinimumValueSuper(getSuper(), minimumValue); } else { return objc_getMinimumValue(this, minimumValue); }
    }
    
    private static final Selector setMinimumValue$ = Selector.register("setMinimumValue:");
    @Bridge private native static void objc_setMinimumValue(UIStepper __self__, Selector __cmd__, double minimumValue);
    @Bridge private native static void objc_setMinimumValueSuper(ObjCSuper __super__, Selector __cmd__, double minimumValue);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/minimumValue">@property(nonatomic) double minimumValue</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setMinimumValue(double minimumValue) {
        if (customClass) { objc_setMinimumValueSuper(getSuper(), setMinimumValue$, minimumValue); } else { objc_setMinimumValue(this, setMinimumValue$, minimumValue); }
    }
    
    private static final Selector stepValue = Selector.register("stepValue");
    @Bridge private native static double objc_getStepValue(UIStepper __self__, Selector __cmd__);
    @Bridge private native static double objc_getStepValueSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/stepValue">@property(nonatomic) double stepValue</a>
     * @since Available in iOS 5.0 and later.
     */
    public double getStepValue() {
        if (customClass) { return objc_getStepValueSuper(getSuper(), stepValue); } else { return objc_getStepValue(this, stepValue); }
    }
    
    private static final Selector setStepValue$ = Selector.register("setStepValue:");
    @Bridge private native static void objc_setStepValue(UIStepper __self__, Selector __cmd__, double stepValue);
    @Bridge private native static void objc_setStepValueSuper(ObjCSuper __super__, Selector __cmd__, double stepValue);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/stepValue">@property(nonatomic) double stepValue</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setStepValue(double stepValue) {
        if (customClass) { objc_setStepValueSuper(getSuper(), setStepValue$, stepValue); } else { objc_setStepValue(this, setStepValue$, stepValue); }
    }
    
    private static final Selector tintColor = Selector.register("tintColor");
    @Bridge private native static UIColor objc_getTintColor(UIStepper __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIColor getTintColor() {
        if (customClass) { return objc_getTintColorSuper(getSuper(), tintColor); } else { return objc_getTintColor(this, tintColor); }
    }
    
    private static final Selector setTintColor$ = Selector.register("setTintColor:");
    @Bridge private native static void objc_setTintColor(UIStepper __self__, Selector __cmd__, UIColor tintColor);
    @Bridge private native static void objc_setTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor tintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setTintColor(UIColor tintColor) {
        if (customClass) { objc_setTintColorSuper(getSuper(), setTintColor$, tintColor); } else { objc_setTintColor(this, setTintColor$, tintColor); }
    }
    
    private static final Selector value = Selector.register("value");
    @Bridge private native static double objc_getValue(UIStepper __self__, Selector __cmd__);
    @Bridge private native static double objc_getValueSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/value">@property(nonatomic) double value</a>
     * @since Available in iOS 5.0 and later.
     */
    public double getValue() {
        if (customClass) { return objc_getValueSuper(getSuper(), value); } else { return objc_getValue(this, value); }
    }
    
    private static final Selector setValue$ = Selector.register("setValue:");
    @Bridge private native static void objc_setValue(UIStepper __self__, Selector __cmd__, double value);
    @Bridge private native static void objc_setValueSuper(ObjCSuper __super__, Selector __cmd__, double value);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/value">@property(nonatomic) double value</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setValue(double value) {
        if (customClass) { objc_setValueSuper(getSuper(), setValue$, value); } else { objc_setValue(this, setValue$, value); }
    }
    
    private static final Selector wraps = Selector.register("wraps");
    @Bridge private native static boolean objc_isWraps(UIStepper __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isWrapsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/wraps">@property(nonatomic) BOOL wraps</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isWraps() {
        if (customClass) { return objc_isWrapsSuper(getSuper(), wraps); } else { return objc_isWraps(this, wraps); }
    }
    
    private static final Selector setWraps$ = Selector.register("setWraps:");
    @Bridge private native static void objc_setWraps(UIStepper __self__, Selector __cmd__, boolean wraps);
    @Bridge private native static void objc_setWrapsSuper(ObjCSuper __super__, Selector __cmd__, boolean wraps);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/wraps">@property(nonatomic) BOOL wraps</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setWraps(boolean wraps) {
        if (customClass) { objc_setWrapsSuper(getSuper(), setWraps$, wraps); } else { objc_setWraps(this, setWraps$, wraps); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector backgroundImageForState$ = Selector.register("backgroundImageForState:");
    @Bridge private native static UIImage objc_getBackgroundImage(UIStepper __self__, Selector __cmd__, UIControlState state);
    @Bridge private native static UIImage objc_getBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/backgroundImageForState:">- (UIImage *)backgroundImageForState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage getBackgroundImage(UIControlState state) {
        if (customClass) { return objc_getBackgroundImageSuper(getSuper(), backgroundImageForState$, state); } else { return objc_getBackgroundImage(this, backgroundImageForState$, state); }
    }
    
    private static final Selector decrementImageForState$ = Selector.register("decrementImageForState:");
    @Bridge private native static UIImage objc_getDecrementImage(UIStepper __self__, Selector __cmd__, UIControlState state);
    @Bridge private native static UIImage objc_getDecrementImageSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/decrementImageForState:">- (UIImage *)decrementImageForState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage getDecrementImage(UIControlState state) {
        if (customClass) { return objc_getDecrementImageSuper(getSuper(), decrementImageForState$, state); } else { return objc_getDecrementImage(this, decrementImageForState$, state); }
    }
    
    private static final Selector dividerImageForLeftSegmentState$rightSegmentState$ = Selector.register("dividerImageForLeftSegmentState:rightSegmentState:");
    @Bridge private native static UIImage objc_getDividerImage(UIStepper __self__, Selector __cmd__, UIControlState leftState, UIControlState rightState);
    @Bridge private native static UIImage objc_getDividerImageSuper(ObjCSuper __super__, Selector __cmd__, UIControlState leftState, UIControlState rightState);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/dividerImageForLeftSegmentState:rightSegmentState:">- (UIImage *)dividerImageForLeftSegmentState:(UIControlState)leftState rightSegmentState:(UIControlState)rightState</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage getDividerImage(UIControlState leftState, UIControlState rightState) {
        if (customClass) { return objc_getDividerImageSuper(getSuper(), dividerImageForLeftSegmentState$rightSegmentState$, leftState, rightState); } else { return objc_getDividerImage(this, dividerImageForLeftSegmentState$rightSegmentState$, leftState, rightState); }
    }
    
    private static final Selector incrementImageForState$ = Selector.register("incrementImageForState:");
    @Bridge private native static UIImage objc_getIncrementImage(UIStepper __self__, Selector __cmd__, UIControlState state);
    @Bridge private native static UIImage objc_getIncrementImageSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/incrementImageForState:">- (UIImage *)incrementImageForState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage getIncrementImage(UIControlState state) {
        if (customClass) { return objc_getIncrementImageSuper(getSuper(), incrementImageForState$, state); } else { return objc_getIncrementImage(this, incrementImageForState$, state); }
    }
    
    private static final Selector setBackgroundImage$forState$ = Selector.register("setBackgroundImage:forState:");
    @Bridge private native static void objc_setBackgroundImage(UIStepper __self__, Selector __cmd__, UIImage image, UIControlState state);
    @Bridge private native static void objc_setBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage image, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/setBackgroundImage:forState:">- (void)setBackgroundImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setBackgroundImage(UIImage image, UIControlState state) {
        if (customClass) { objc_setBackgroundImageSuper(getSuper(), setBackgroundImage$forState$, image, state); } else { objc_setBackgroundImage(this, setBackgroundImage$forState$, image, state); }
    }
    
    private static final Selector setDecrementImage$forState$ = Selector.register("setDecrementImage:forState:");
    @Bridge private native static void objc_setDecrementImage(UIStepper __self__, Selector __cmd__, UIImage image, UIControlState state);
    @Bridge private native static void objc_setDecrementImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage image, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/setDecrementImage:forState:">- (void)setDecrementImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setDecrementImage(UIImage image, UIControlState state) {
        if (customClass) { objc_setDecrementImageSuper(getSuper(), setDecrementImage$forState$, image, state); } else { objc_setDecrementImage(this, setDecrementImage$forState$, image, state); }
    }
    
    private static final Selector setDividerImage$forLeftSegmentState$rightSegmentState$ = Selector.register("setDividerImage:forLeftSegmentState:rightSegmentState:");
    @Bridge private native static void objc_setDividerImage(UIStepper __self__, Selector __cmd__, UIImage image, UIControlState leftState, UIControlState rightState);
    @Bridge private native static void objc_setDividerImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage image, UIControlState leftState, UIControlState rightState);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/setDividerImage:forLeftSegmentState:rightSegmentState:">- (void)setDividerImage:(UIImage *)image forLeftSegmentState:(UIControlState)leftState rightSegmentState:(UIControlState)rightState</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setDividerImage(UIImage image, UIControlState leftState, UIControlState rightState) {
        if (customClass) { objc_setDividerImageSuper(getSuper(), setDividerImage$forLeftSegmentState$rightSegmentState$, image, leftState, rightState); } else { objc_setDividerImage(this, setDividerImage$forLeftSegmentState$rightSegmentState$, image, leftState, rightState); }
    }
    
    private static final Selector setIncrementImage$forState$ = Selector.register("setIncrementImage:forState:");
    @Bridge private native static void objc_setIncrementImage(UIStepper __self__, Selector __cmd__, UIImage image, UIControlState state);
    @Bridge private native static void objc_setIncrementImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage image, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/setIncrementImage:forState:">- (void)setIncrementImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setIncrementImage(UIImage image, UIControlState state) {
        if (customClass) { objc_setIncrementImageSuper(getSuper(), setIncrementImage$forState$, image, state); } else { objc_setIncrementImage(this, setIncrementImage$forState$, image, state); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("autorepeat") public static boolean isAutorepeat(UIStepper __self__, Selector __cmd__) { return __self__.isAutorepeat(); }
        @Callback @BindSelector("setAutorepeat:") public static void setAutorepeat(UIStepper __self__, Selector __cmd__, boolean autorepeat) { __self__.setAutorepeat(autorepeat); }
        @Callback @BindSelector("isContinuous") public static boolean isContinuous(UIStepper __self__, Selector __cmd__) { return __self__.isContinuous(); }
        @Callback @BindSelector("setContinuous:") public static void setContinuous(UIStepper __self__, Selector __cmd__, boolean continuous) { __self__.setContinuous(continuous); }
        @Callback @BindSelector("maximumValue") public static double getMaximumValue(UIStepper __self__, Selector __cmd__) { return __self__.getMaximumValue(); }
        @Callback @BindSelector("setMaximumValue:") public static void setMaximumValue(UIStepper __self__, Selector __cmd__, double maximumValue) { __self__.setMaximumValue(maximumValue); }
        @Callback @BindSelector("minimumValue") public static double getMinimumValue(UIStepper __self__, Selector __cmd__) { return __self__.getMinimumValue(); }
        @Callback @BindSelector("setMinimumValue:") public static void setMinimumValue(UIStepper __self__, Selector __cmd__, double minimumValue) { __self__.setMinimumValue(minimumValue); }
        @Callback @BindSelector("stepValue") public static double getStepValue(UIStepper __self__, Selector __cmd__) { return __self__.getStepValue(); }
        @Callback @BindSelector("setStepValue:") public static void setStepValue(UIStepper __self__, Selector __cmd__, double stepValue) { __self__.setStepValue(stepValue); }
        @Callback @BindSelector("tintColor") public static UIColor getTintColor(UIStepper __self__, Selector __cmd__) { return __self__.getTintColor(); }
        @Callback @BindSelector("setTintColor:") public static void setTintColor(UIStepper __self__, Selector __cmd__, UIColor tintColor) { __self__.setTintColor(tintColor); }
        @Callback @BindSelector("value") public static double getValue(UIStepper __self__, Selector __cmd__) { return __self__.getValue(); }
        @Callback @BindSelector("setValue:") public static void setValue(UIStepper __self__, Selector __cmd__, double value) { __self__.setValue(value); }
        @Callback @BindSelector("wraps") public static boolean isWraps(UIStepper __self__, Selector __cmd__) { return __self__.isWraps(); }
        @Callback @BindSelector("setWraps:") public static void setWraps(UIStepper __self__, Selector __cmd__, boolean wraps) { __self__.setWraps(wraps); }
        @Callback @BindSelector("backgroundImageForState:") public static UIImage getBackgroundImage(UIStepper __self__, Selector __cmd__, UIControlState state) { return __self__.getBackgroundImage(state); }
        @Callback @BindSelector("decrementImageForState:") public static UIImage getDecrementImage(UIStepper __self__, Selector __cmd__, UIControlState state) { return __self__.getDecrementImage(state); }
        @Callback @BindSelector("dividerImageForLeftSegmentState:rightSegmentState:") public static UIImage getDividerImage(UIStepper __self__, Selector __cmd__, UIControlState leftState, UIControlState rightState) { return __self__.getDividerImage(leftState, rightState); }
        @Callback @BindSelector("incrementImageForState:") public static UIImage getIncrementImage(UIStepper __self__, Selector __cmd__, UIControlState state) { return __self__.getIncrementImage(state); }
        @Callback @BindSelector("setBackgroundImage:forState:") public static void setBackgroundImage(UIStepper __self__, Selector __cmd__, UIImage image, UIControlState state) { __self__.setBackgroundImage(image, state); }
        @Callback @BindSelector("setDecrementImage:forState:") public static void setDecrementImage(UIStepper __self__, Selector __cmd__, UIImage image, UIControlState state) { __self__.setDecrementImage(image, state); }
        @Callback @BindSelector("setDividerImage:forLeftSegmentState:rightSegmentState:") public static void setDividerImage(UIStepper __self__, Selector __cmd__, UIImage image, UIControlState leftState, UIControlState rightState) { __self__.setDividerImage(image, leftState, rightState); }
        @Callback @BindSelector("setIncrementImage:forState:") public static void setIncrementImage(UIStepper __self__, Selector __cmd__, UIImage image, UIControlState state) { __self__.setIncrementImage(image, state); }
    }
    /*</callbacks>*/

}
