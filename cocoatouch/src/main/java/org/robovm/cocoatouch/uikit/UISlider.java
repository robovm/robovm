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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html">UISlider Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UISlider /*</name>*/ 
    extends /*<extends>*/ UIControl /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UISlider /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UISlider /*</name>*/.class);

    public UISlider(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UISlider(SkipInit skipInit) { super(skipInit); }
    public UISlider() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector isContinuous = Selector.register("isContinuous");
    @Bridge private native static boolean objc_isContinuous(UISlider __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isContinuousSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/continuous">@property(nonatomic, getter=isContinuous) BOOL continuous</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isContinuous() {
        if (customClass) { return objc_isContinuousSuper(getSuper(), isContinuous); } else { return objc_isContinuous(this, isContinuous); }
    }
    
    private static final Selector setContinuous$ = Selector.register("setContinuous:");
    @Bridge private native static void objc_setContinuous(UISlider __self__, Selector __cmd__, boolean continuous);
    @Bridge private native static void objc_setContinuousSuper(ObjCSuper __super__, Selector __cmd__, boolean continuous);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/continuous">@property(nonatomic, getter=isContinuous) BOOL continuous</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setContinuous(boolean continuous) {
        if (customClass) { objc_setContinuousSuper(getSuper(), setContinuous$, continuous); } else { objc_setContinuous(this, setContinuous$, continuous); }
    }
    
    private static final Selector currentMaximumTrackImage = Selector.register("currentMaximumTrackImage");
    @Bridge private native static UIImage objc_getCurrentMaximumTrackImage(UISlider __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getCurrentMaximumTrackImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/currentMaximumTrackImage">@property(nonatomic, readonly) UIImage *currentMaximumTrackImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getCurrentMaximumTrackImage() {
        if (customClass) { return objc_getCurrentMaximumTrackImageSuper(getSuper(), currentMaximumTrackImage); } else { return objc_getCurrentMaximumTrackImage(this, currentMaximumTrackImage); }
    }
    
    private static final Selector currentMinimumTrackImage = Selector.register("currentMinimumTrackImage");
    @Bridge private native static UIImage objc_getCurrentMinimumTrackImage(UISlider __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getCurrentMinimumTrackImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/currentMinimumTrackImage">@property(nonatomic, readonly) UIImage *currentMinimumTrackImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getCurrentMinimumTrackImage() {
        if (customClass) { return objc_getCurrentMinimumTrackImageSuper(getSuper(), currentMinimumTrackImage); } else { return objc_getCurrentMinimumTrackImage(this, currentMinimumTrackImage); }
    }
    
    private static final Selector currentThumbImage = Selector.register("currentThumbImage");
    @Bridge private native static UIImage objc_getCurrentThumbImage(UISlider __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getCurrentThumbImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/currentThumbImage">@property(nonatomic, readonly) UIImage *currentThumbImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getCurrentThumbImage() {
        if (customClass) { return objc_getCurrentThumbImageSuper(getSuper(), currentThumbImage); } else { return objc_getCurrentThumbImage(this, currentThumbImage); }
    }
    
    private static final Selector maximumTrackTintColor = Selector.register("maximumTrackTintColor");
    @Bridge private native static UIColor objc_getMaximumTrackTintColor(UISlider __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getMaximumTrackTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumTrackTintColor">@property(nonatomic, retain) UIColor *maximumTrackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIColor getMaximumTrackTintColor() {
        if (customClass) { return objc_getMaximumTrackTintColorSuper(getSuper(), maximumTrackTintColor); } else { return objc_getMaximumTrackTintColor(this, maximumTrackTintColor); }
    }
    
    private static final Selector setMaximumTrackTintColor$ = Selector.register("setMaximumTrackTintColor:");
    @Bridge private native static void objc_setMaximumTrackTintColor(UISlider __self__, Selector __cmd__, UIColor maximumTrackTintColor);
    @Bridge private native static void objc_setMaximumTrackTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor maximumTrackTintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumTrackTintColor">@property(nonatomic, retain) UIColor *maximumTrackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setMaximumTrackTintColor(UIColor maximumTrackTintColor) {
        if (customClass) { objc_setMaximumTrackTintColorSuper(getSuper(), setMaximumTrackTintColor$, maximumTrackTintColor); } else { objc_setMaximumTrackTintColor(this, setMaximumTrackTintColor$, maximumTrackTintColor); }
    }
    
    private static final Selector maximumValue = Selector.register("maximumValue");
    @Bridge private native static float objc_getMaximumValue(UISlider __self__, Selector __cmd__);
    @Bridge private native static float objc_getMaximumValueSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumValue">@property(nonatomic) float maximumValue</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getMaximumValue() {
        if (customClass) { return objc_getMaximumValueSuper(getSuper(), maximumValue); } else { return objc_getMaximumValue(this, maximumValue); }
    }
    
    private static final Selector setMaximumValue$ = Selector.register("setMaximumValue:");
    @Bridge private native static void objc_setMaximumValue(UISlider __self__, Selector __cmd__, float maximumValue);
    @Bridge private native static void objc_setMaximumValueSuper(ObjCSuper __super__, Selector __cmd__, float maximumValue);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumValue">@property(nonatomic) float maximumValue</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMaximumValue(float maximumValue) {
        if (customClass) { objc_setMaximumValueSuper(getSuper(), setMaximumValue$, maximumValue); } else { objc_setMaximumValue(this, setMaximumValue$, maximumValue); }
    }
    
    private static final Selector maximumValueImage = Selector.register("maximumValueImage");
    @Bridge private native static UIImage objc_getMaximumValueImage(UISlider __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getMaximumValueImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumValueImage">@property(nonatomic, retain) UIImage *maximumValueImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getMaximumValueImage() {
        if (customClass) { return objc_getMaximumValueImageSuper(getSuper(), maximumValueImage); } else { return objc_getMaximumValueImage(this, maximumValueImage); }
    }
    
    private static final Selector setMaximumValueImage$ = Selector.register("setMaximumValueImage:");
    @Bridge private native static void objc_setMaximumValueImage(UISlider __self__, Selector __cmd__, UIImage maximumValueImage);
    @Bridge private native static void objc_setMaximumValueImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage maximumValueImage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumValueImage">@property(nonatomic, retain) UIImage *maximumValueImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMaximumValueImage(UIImage maximumValueImage) {
        if (customClass) { objc_setMaximumValueImageSuper(getSuper(), setMaximumValueImage$, maximumValueImage); } else { objc_setMaximumValueImage(this, setMaximumValueImage$, maximumValueImage); }
    }
    
    private static final Selector minimumTrackTintColor = Selector.register("minimumTrackTintColor");
    @Bridge private native static UIColor objc_getMinimumTrackTintColor(UISlider __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getMinimumTrackTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumTrackTintColor">@property(nonatomic, retain) UIColor *minimumTrackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIColor getMinimumTrackTintColor() {
        if (customClass) { return objc_getMinimumTrackTintColorSuper(getSuper(), minimumTrackTintColor); } else { return objc_getMinimumTrackTintColor(this, minimumTrackTintColor); }
    }
    
    private static final Selector setMinimumTrackTintColor$ = Selector.register("setMinimumTrackTintColor:");
    @Bridge private native static void objc_setMinimumTrackTintColor(UISlider __self__, Selector __cmd__, UIColor minimumTrackTintColor);
    @Bridge private native static void objc_setMinimumTrackTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor minimumTrackTintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumTrackTintColor">@property(nonatomic, retain) UIColor *minimumTrackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setMinimumTrackTintColor(UIColor minimumTrackTintColor) {
        if (customClass) { objc_setMinimumTrackTintColorSuper(getSuper(), setMinimumTrackTintColor$, minimumTrackTintColor); } else { objc_setMinimumTrackTintColor(this, setMinimumTrackTintColor$, minimumTrackTintColor); }
    }
    
    private static final Selector minimumValue = Selector.register("minimumValue");
    @Bridge private native static float objc_getMinimumValue(UISlider __self__, Selector __cmd__);
    @Bridge private native static float objc_getMinimumValueSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumValue">@property(nonatomic) float minimumValue</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getMinimumValue() {
        if (customClass) { return objc_getMinimumValueSuper(getSuper(), minimumValue); } else { return objc_getMinimumValue(this, minimumValue); }
    }
    
    private static final Selector setMinimumValue$ = Selector.register("setMinimumValue:");
    @Bridge private native static void objc_setMinimumValue(UISlider __self__, Selector __cmd__, float minimumValue);
    @Bridge private native static void objc_setMinimumValueSuper(ObjCSuper __super__, Selector __cmd__, float minimumValue);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumValue">@property(nonatomic) float minimumValue</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMinimumValue(float minimumValue) {
        if (customClass) { objc_setMinimumValueSuper(getSuper(), setMinimumValue$, minimumValue); } else { objc_setMinimumValue(this, setMinimumValue$, minimumValue); }
    }
    
    private static final Selector minimumValueImage = Selector.register("minimumValueImage");
    @Bridge private native static UIImage objc_getMinimumValueImage(UISlider __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getMinimumValueImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumValueImage">@property(nonatomic, retain) UIImage *minimumValueImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getMinimumValueImage() {
        if (customClass) { return objc_getMinimumValueImageSuper(getSuper(), minimumValueImage); } else { return objc_getMinimumValueImage(this, minimumValueImage); }
    }
    
    private static final Selector setMinimumValueImage$ = Selector.register("setMinimumValueImage:");
    @Bridge private native static void objc_setMinimumValueImage(UISlider __self__, Selector __cmd__, UIImage minimumValueImage);
    @Bridge private native static void objc_setMinimumValueImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage minimumValueImage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumValueImage">@property(nonatomic, retain) UIImage *minimumValueImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMinimumValueImage(UIImage minimumValueImage) {
        if (customClass) { objc_setMinimumValueImageSuper(getSuper(), setMinimumValueImage$, minimumValueImage); } else { objc_setMinimumValueImage(this, setMinimumValueImage$, minimumValueImage); }
    }
    
    private static final Selector thumbTintColor = Selector.register("thumbTintColor");
    @Bridge private native static UIColor objc_getThumbTintColor(UISlider __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getThumbTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/thumbTintColor">@property(nonatomic, retain) UIColor *thumbTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIColor getThumbTintColor() {
        if (customClass) { return objc_getThumbTintColorSuper(getSuper(), thumbTintColor); } else { return objc_getThumbTintColor(this, thumbTintColor); }
    }
    
    private static final Selector setThumbTintColor$ = Selector.register("setThumbTintColor:");
    @Bridge private native static void objc_setThumbTintColor(UISlider __self__, Selector __cmd__, UIColor thumbTintColor);
    @Bridge private native static void objc_setThumbTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor thumbTintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/thumbTintColor">@property(nonatomic, retain) UIColor *thumbTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setThumbTintColor(UIColor thumbTintColor) {
        if (customClass) { objc_setThumbTintColorSuper(getSuper(), setThumbTintColor$, thumbTintColor); } else { objc_setThumbTintColor(this, setThumbTintColor$, thumbTintColor); }
    }
    
    private static final Selector value = Selector.register("value");
    @Bridge private native static float objc_getValue(UISlider __self__, Selector __cmd__);
    @Bridge private native static float objc_getValueSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/value">@property(nonatomic) float value</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getValue() {
        if (customClass) { return objc_getValueSuper(getSuper(), value); } else { return objc_getValue(this, value); }
    }
    
    private static final Selector setValue$ = Selector.register("setValue:");
    @Bridge private native static void objc_setValue(UISlider __self__, Selector __cmd__, float value);
    @Bridge private native static void objc_setValueSuper(ObjCSuper __super__, Selector __cmd__, float value);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/value">@property(nonatomic) float value</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setValue(float value) {
        if (customClass) { objc_setValueSuper(getSuper(), setValue$, value); } else { objc_setValue(this, setValue$, value); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector maximumTrackImageForState$ = Selector.register("maximumTrackImageForState:");
    @Bridge private native static UIImage objc_getMaximumTrackImage(UISlider __self__, Selector __cmd__, UIControlState state);
    @Bridge private native static UIImage objc_getMaximumTrackImageSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/maximumTrackImageForState:">- (UIImage *)maximumTrackImageForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getMaximumTrackImage(UIControlState state) {
        if (customClass) { return objc_getMaximumTrackImageSuper(getSuper(), maximumTrackImageForState$, state); } else { return objc_getMaximumTrackImage(this, maximumTrackImageForState$, state); }
    }
    
    private static final Selector maximumValueImageRectForBounds$ = Selector.register("maximumValueImageRectForBounds:");
    @Bridge private native static @ByVal CGRect objc_getMaximumValueImageRect(UISlider __self__, Selector __cmd__, @ByVal CGRect bounds);
    @Bridge private native static @ByVal CGRect objc_getMaximumValueImageRectSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect bounds);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/maximumValueImageRectForBounds:">- (CGRect)maximumValueImageRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getMaximumValueImageRect(CGRect bounds) {
        if (customClass) { return objc_getMaximumValueImageRectSuper(getSuper(), maximumValueImageRectForBounds$, bounds); } else { return objc_getMaximumValueImageRect(this, maximumValueImageRectForBounds$, bounds); }
    }
    
    private static final Selector minimumTrackImageForState$ = Selector.register("minimumTrackImageForState:");
    @Bridge private native static UIImage objc_getMinimumTrackImage(UISlider __self__, Selector __cmd__, UIControlState state);
    @Bridge private native static UIImage objc_getMinimumTrackImageSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/minimumTrackImageForState:">- (UIImage *)minimumTrackImageForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getMinimumTrackImage(UIControlState state) {
        if (customClass) { return objc_getMinimumTrackImageSuper(getSuper(), minimumTrackImageForState$, state); } else { return objc_getMinimumTrackImage(this, minimumTrackImageForState$, state); }
    }
    
    private static final Selector minimumValueImageRectForBounds$ = Selector.register("minimumValueImageRectForBounds:");
    @Bridge private native static @ByVal CGRect objc_getMinimumValueImageRect(UISlider __self__, Selector __cmd__, @ByVal CGRect bounds);
    @Bridge private native static @ByVal CGRect objc_getMinimumValueImageRectSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect bounds);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/minimumValueImageRectForBounds:">- (CGRect)minimumValueImageRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getMinimumValueImageRect(CGRect bounds) {
        if (customClass) { return objc_getMinimumValueImageRectSuper(getSuper(), minimumValueImageRectForBounds$, bounds); } else { return objc_getMinimumValueImageRect(this, minimumValueImageRectForBounds$, bounds); }
    }
    
    private static final Selector thumbImageForState$ = Selector.register("thumbImageForState:");
    @Bridge private native static UIImage objc_getThumbImage(UISlider __self__, Selector __cmd__, UIControlState state);
    @Bridge private native static UIImage objc_getThumbImageSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/thumbImageForState:">- (UIImage *)thumbImageForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getThumbImage(UIControlState state) {
        if (customClass) { return objc_getThumbImageSuper(getSuper(), thumbImageForState$, state); } else { return objc_getThumbImage(this, thumbImageForState$, state); }
    }
    
    private static final Selector thumbRectForBounds$trackRect$value$ = Selector.register("thumbRectForBounds:trackRect:value:");
    @Bridge private native static @ByVal CGRect objc_getThumbRect(UISlider __self__, Selector __cmd__, @ByVal CGRect bounds, @ByVal CGRect rect, float value);
    @Bridge private native static @ByVal CGRect objc_getThumbRectSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect bounds, @ByVal CGRect rect, float value);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/thumbRectForBounds:trackRect:value:">- (CGRect)thumbRectForBounds:(CGRect)bounds trackRect:(CGRect)rect value:(float)value</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getThumbRect(CGRect bounds, CGRect rect, float value) {
        if (customClass) { return objc_getThumbRectSuper(getSuper(), thumbRectForBounds$trackRect$value$, bounds, rect, value); } else { return objc_getThumbRect(this, thumbRectForBounds$trackRect$value$, bounds, rect, value); }
    }
    
    private static final Selector trackRectForBounds$ = Selector.register("trackRectForBounds:");
    @Bridge private native static @ByVal CGRect objc_getTrackRect(UISlider __self__, Selector __cmd__, @ByVal CGRect bounds);
    @Bridge private native static @ByVal CGRect objc_getTrackRectSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect bounds);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/trackRectForBounds:">- (CGRect)trackRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getTrackRect(CGRect bounds) {
        if (customClass) { return objc_getTrackRectSuper(getSuper(), trackRectForBounds$, bounds); } else { return objc_getTrackRect(this, trackRectForBounds$, bounds); }
    }
    
    private static final Selector setMaximumTrackImage$forState$ = Selector.register("setMaximumTrackImage:forState:");
    @Bridge private native static void objc_setMaximumTrackImage(UISlider __self__, Selector __cmd__, UIImage image, UIControlState state);
    @Bridge private native static void objc_setMaximumTrackImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage image, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/setMaximumTrackImage:forState:">- (void)setMaximumTrackImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMaximumTrackImage(UIImage image, UIControlState state) {
        if (customClass) { objc_setMaximumTrackImageSuper(getSuper(), setMaximumTrackImage$forState$, image, state); } else { objc_setMaximumTrackImage(this, setMaximumTrackImage$forState$, image, state); }
    }
    
    private static final Selector setMinimumTrackImage$forState$ = Selector.register("setMinimumTrackImage:forState:");
    @Bridge private native static void objc_setMinimumTrackImage(UISlider __self__, Selector __cmd__, UIImage image, UIControlState state);
    @Bridge private native static void objc_setMinimumTrackImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage image, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/setMinimumTrackImage:forState:">- (void)setMinimumTrackImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMinimumTrackImage(UIImage image, UIControlState state) {
        if (customClass) { objc_setMinimumTrackImageSuper(getSuper(), setMinimumTrackImage$forState$, image, state); } else { objc_setMinimumTrackImage(this, setMinimumTrackImage$forState$, image, state); }
    }
    
    private static final Selector setThumbImage$forState$ = Selector.register("setThumbImage:forState:");
    @Bridge private native static void objc_setThumbImage(UISlider __self__, Selector __cmd__, UIImage image, UIControlState state);
    @Bridge private native static void objc_setThumbImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage image, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/setThumbImage:forState:">- (void)setThumbImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setThumbImage(UIImage image, UIControlState state) {
        if (customClass) { objc_setThumbImageSuper(getSuper(), setThumbImage$forState$, image, state); } else { objc_setThumbImage(this, setThumbImage$forState$, image, state); }
    }
    
    private static final Selector setValue$animated$ = Selector.register("setValue:animated:");
    @Bridge private native static void objc_setValue(UISlider __self__, Selector __cmd__, float value, boolean animated);
    @Bridge private native static void objc_setValueSuper(ObjCSuper __super__, Selector __cmd__, float value, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/setValue:animated:">- (void)setValue:(float)value animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setValue(float value, boolean animated) {
        if (customClass) { objc_setValueSuper(getSuper(), setValue$animated$, value, animated); } else { objc_setValue(this, setValue$animated$, value, animated); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("isContinuous") public static boolean isContinuous(UISlider __self__, Selector __cmd__) { return __self__.isContinuous(); }
        @Callback @BindSelector("setContinuous:") public static void setContinuous(UISlider __self__, Selector __cmd__, boolean continuous) { __self__.setContinuous(continuous); }
        @Callback @BindSelector("currentMaximumTrackImage") public static UIImage getCurrentMaximumTrackImage(UISlider __self__, Selector __cmd__) { return __self__.getCurrentMaximumTrackImage(); }
        @Callback @BindSelector("currentMinimumTrackImage") public static UIImage getCurrentMinimumTrackImage(UISlider __self__, Selector __cmd__) { return __self__.getCurrentMinimumTrackImage(); }
        @Callback @BindSelector("currentThumbImage") public static UIImage getCurrentThumbImage(UISlider __self__, Selector __cmd__) { return __self__.getCurrentThumbImage(); }
        @Callback @BindSelector("maximumTrackTintColor") public static UIColor getMaximumTrackTintColor(UISlider __self__, Selector __cmd__) { return __self__.getMaximumTrackTintColor(); }
        @Callback @BindSelector("setMaximumTrackTintColor:") public static void setMaximumTrackTintColor(UISlider __self__, Selector __cmd__, UIColor maximumTrackTintColor) { __self__.setMaximumTrackTintColor(maximumTrackTintColor); }
        @Callback @BindSelector("maximumValue") public static float getMaximumValue(UISlider __self__, Selector __cmd__) { return __self__.getMaximumValue(); }
        @Callback @BindSelector("setMaximumValue:") public static void setMaximumValue(UISlider __self__, Selector __cmd__, float maximumValue) { __self__.setMaximumValue(maximumValue); }
        @Callback @BindSelector("maximumValueImage") public static UIImage getMaximumValueImage(UISlider __self__, Selector __cmd__) { return __self__.getMaximumValueImage(); }
        @Callback @BindSelector("setMaximumValueImage:") public static void setMaximumValueImage(UISlider __self__, Selector __cmd__, UIImage maximumValueImage) { __self__.setMaximumValueImage(maximumValueImage); }
        @Callback @BindSelector("minimumTrackTintColor") public static UIColor getMinimumTrackTintColor(UISlider __self__, Selector __cmd__) { return __self__.getMinimumTrackTintColor(); }
        @Callback @BindSelector("setMinimumTrackTintColor:") public static void setMinimumTrackTintColor(UISlider __self__, Selector __cmd__, UIColor minimumTrackTintColor) { __self__.setMinimumTrackTintColor(minimumTrackTintColor); }
        @Callback @BindSelector("minimumValue") public static float getMinimumValue(UISlider __self__, Selector __cmd__) { return __self__.getMinimumValue(); }
        @Callback @BindSelector("setMinimumValue:") public static void setMinimumValue(UISlider __self__, Selector __cmd__, float minimumValue) { __self__.setMinimumValue(minimumValue); }
        @Callback @BindSelector("minimumValueImage") public static UIImage getMinimumValueImage(UISlider __self__, Selector __cmd__) { return __self__.getMinimumValueImage(); }
        @Callback @BindSelector("setMinimumValueImage:") public static void setMinimumValueImage(UISlider __self__, Selector __cmd__, UIImage minimumValueImage) { __self__.setMinimumValueImage(minimumValueImage); }
        @Callback @BindSelector("thumbTintColor") public static UIColor getThumbTintColor(UISlider __self__, Selector __cmd__) { return __self__.getThumbTintColor(); }
        @Callback @BindSelector("setThumbTintColor:") public static void setThumbTintColor(UISlider __self__, Selector __cmd__, UIColor thumbTintColor) { __self__.setThumbTintColor(thumbTintColor); }
        @Callback @BindSelector("value") public static float getValue(UISlider __self__, Selector __cmd__) { return __self__.getValue(); }
        @Callback @BindSelector("setValue:") public static void setValue(UISlider __self__, Selector __cmd__, float value) { __self__.setValue(value); }
        @Callback @BindSelector("maximumTrackImageForState:") public static UIImage getMaximumTrackImage(UISlider __self__, Selector __cmd__, UIControlState state) { return __self__.getMaximumTrackImage(state); }
        @Callback @BindSelector("maximumValueImageRectForBounds:") public static @ByVal CGRect getMaximumValueImageRect(UISlider __self__, Selector __cmd__, @ByVal CGRect bounds) { return __self__.getMaximumValueImageRect(bounds); }
        @Callback @BindSelector("minimumTrackImageForState:") public static UIImage getMinimumTrackImage(UISlider __self__, Selector __cmd__, UIControlState state) { return __self__.getMinimumTrackImage(state); }
        @Callback @BindSelector("minimumValueImageRectForBounds:") public static @ByVal CGRect getMinimumValueImageRect(UISlider __self__, Selector __cmd__, @ByVal CGRect bounds) { return __self__.getMinimumValueImageRect(bounds); }
        @Callback @BindSelector("thumbImageForState:") public static UIImage getThumbImage(UISlider __self__, Selector __cmd__, UIControlState state) { return __self__.getThumbImage(state); }
        @Callback @BindSelector("thumbRectForBounds:trackRect:value:") public static @ByVal CGRect getThumbRect(UISlider __self__, Selector __cmd__, @ByVal CGRect bounds, @ByVal CGRect rect, float value) { return __self__.getThumbRect(bounds, rect, value); }
        @Callback @BindSelector("trackRectForBounds:") public static @ByVal CGRect getTrackRect(UISlider __self__, Selector __cmd__, @ByVal CGRect bounds) { return __self__.getTrackRect(bounds); }
        @Callback @BindSelector("setMaximumTrackImage:forState:") public static void setMaximumTrackImage(UISlider __self__, Selector __cmd__, UIImage image, UIControlState state) { __self__.setMaximumTrackImage(image, state); }
        @Callback @BindSelector("setMinimumTrackImage:forState:") public static void setMinimumTrackImage(UISlider __self__, Selector __cmd__, UIImage image, UIControlState state) { __self__.setMinimumTrackImage(image, state); }
        @Callback @BindSelector("setThumbImage:forState:") public static void setThumbImage(UISlider __self__, Selector __cmd__, UIImage image, UIControlState state) { __self__.setThumbImage(image, state); }
        @Callback @BindSelector("setValue:animated:") public static void setValue(UISlider __self__, Selector __cmd__, float value, boolean animated) { __self__.setValue(value, animated); }
    }
    /*</callbacks>*/

}
