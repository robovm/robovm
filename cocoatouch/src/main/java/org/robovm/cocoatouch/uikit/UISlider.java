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
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html">UISlider Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UISlider /*</name>*/ 
    extends /*<extends>*/ UIControl /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UISlider /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UISlider /*</name>*/.class);

    /*<constructors>*/
    protected UISlider(SkipInit skipInit) { super(skipInit); }
    public UISlider() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector isContinuous = Selector.register("isContinuous");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isContinuous(UISlider __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isContinuousSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/continuous">@property(nonatomic, getter=isContinuous) BOOL continuous</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isContinuous() {
        if (customClass) { return objc_isContinuousSuper(getSuper(), this, isContinuous); } else { return objc_isContinuous(this, isContinuous); }
    }
    
    private static final Selector setContinuous$ = Selector.register("setContinuous:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setContinuous(UISlider __self__, Selector __cmd__, boolean continuous);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setContinuousSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, boolean continuous);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/continuous">@property(nonatomic, getter=isContinuous) BOOL continuous</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setContinuous(boolean continuous) {
        if (customClass) { objc_setContinuousSuper(getSuper(), this, setContinuous$, continuous); } else { objc_setContinuous(this, setContinuous$, continuous); }
    }
    
    private static final Selector currentMaximumTrackImage = Selector.register("currentMaximumTrackImage");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getCurrentMaximumTrackImage(UISlider __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getCurrentMaximumTrackImageSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/currentMaximumTrackImage">@property(nonatomic, readonly) UIImage *currentMaximumTrackImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getCurrentMaximumTrackImage() {
        if (customClass) { return objc_getCurrentMaximumTrackImageSuper(getSuper(), this, currentMaximumTrackImage); } else { return objc_getCurrentMaximumTrackImage(this, currentMaximumTrackImage); }
    }
    
    private static final Selector currentMinimumTrackImage = Selector.register("currentMinimumTrackImage");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getCurrentMinimumTrackImage(UISlider __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getCurrentMinimumTrackImageSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/currentMinimumTrackImage">@property(nonatomic, readonly) UIImage *currentMinimumTrackImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getCurrentMinimumTrackImage() {
        if (customClass) { return objc_getCurrentMinimumTrackImageSuper(getSuper(), this, currentMinimumTrackImage); } else { return objc_getCurrentMinimumTrackImage(this, currentMinimumTrackImage); }
    }
    
    private static final Selector currentThumbImage = Selector.register("currentThumbImage");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getCurrentThumbImage(UISlider __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getCurrentThumbImageSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/currentThumbImage">@property(nonatomic, readonly) UIImage *currentThumbImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getCurrentThumbImage() {
        if (customClass) { return objc_getCurrentThumbImageSuper(getSuper(), this, currentThumbImage); } else { return objc_getCurrentThumbImage(this, currentThumbImage); }
    }
    
    private static final Selector maximumTrackTintColor = Selector.register("maximumTrackTintColor");
    @Bridge(symbol = "objc_msgSend") private native static UIColor objc_getMaximumTrackTintColor(UISlider __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIColor objc_getMaximumTrackTintColorSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumTrackTintColor">@property(nonatomic, retain) UIColor *maximumTrackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIColor getMaximumTrackTintColor() {
        if (customClass) { return objc_getMaximumTrackTintColorSuper(getSuper(), this, maximumTrackTintColor); } else { return objc_getMaximumTrackTintColor(this, maximumTrackTintColor); }
    }
    
    private static final Selector setMaximumTrackTintColor$ = Selector.register("setMaximumTrackTintColor:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setMaximumTrackTintColor(UISlider __self__, Selector __cmd__, UIColor maximumTrackTintColor);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setMaximumTrackTintColorSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, UIColor maximumTrackTintColor);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumTrackTintColor">@property(nonatomic, retain) UIColor *maximumTrackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setMaximumTrackTintColor(UIColor maximumTrackTintColor) {
        if (customClass) { objc_setMaximumTrackTintColorSuper(getSuper(), this, setMaximumTrackTintColor$, maximumTrackTintColor); } else { objc_setMaximumTrackTintColor(this, setMaximumTrackTintColor$, maximumTrackTintColor); }
    }
    
    private static final Selector maximumValue = Selector.register("maximumValue");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getMaximumValue(UISlider __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getMaximumValueSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumValue">@property(nonatomic) float maximumValue</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getMaximumValue() {
        if (customClass) { return objc_getMaximumValueSuper(getSuper(), this, maximumValue); } else { return objc_getMaximumValue(this, maximumValue); }
    }
    
    private static final Selector setMaximumValue$ = Selector.register("setMaximumValue:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setMaximumValue(UISlider __self__, Selector __cmd__, float maximumValue);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setMaximumValueSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, float maximumValue);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumValue">@property(nonatomic) float maximumValue</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMaximumValue(float maximumValue) {
        if (customClass) { objc_setMaximumValueSuper(getSuper(), this, setMaximumValue$, maximumValue); } else { objc_setMaximumValue(this, setMaximumValue$, maximumValue); }
    }
    
    private static final Selector maximumValueImage = Selector.register("maximumValueImage");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getMaximumValueImage(UISlider __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getMaximumValueImageSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumValueImage">@property(nonatomic, retain) UIImage *maximumValueImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getMaximumValueImage() {
        if (customClass) { return objc_getMaximumValueImageSuper(getSuper(), this, maximumValueImage); } else { return objc_getMaximumValueImage(this, maximumValueImage); }
    }
    
    private static final Selector setMaximumValueImage$ = Selector.register("setMaximumValueImage:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setMaximumValueImage(UISlider __self__, Selector __cmd__, UIImage maximumValueImage);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setMaximumValueImageSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, UIImage maximumValueImage);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumValueImage">@property(nonatomic, retain) UIImage *maximumValueImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMaximumValueImage(UIImage maximumValueImage) {
        if (customClass) { objc_setMaximumValueImageSuper(getSuper(), this, setMaximumValueImage$, maximumValueImage); } else { objc_setMaximumValueImage(this, setMaximumValueImage$, maximumValueImage); }
    }
    
    private static final Selector minimumTrackTintColor = Selector.register("minimumTrackTintColor");
    @Bridge(symbol = "objc_msgSend") private native static UIColor objc_getMinimumTrackTintColor(UISlider __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIColor objc_getMinimumTrackTintColorSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumTrackTintColor">@property(nonatomic, retain) UIColor *minimumTrackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIColor getMinimumTrackTintColor() {
        if (customClass) { return objc_getMinimumTrackTintColorSuper(getSuper(), this, minimumTrackTintColor); } else { return objc_getMinimumTrackTintColor(this, minimumTrackTintColor); }
    }
    
    private static final Selector setMinimumTrackTintColor$ = Selector.register("setMinimumTrackTintColor:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setMinimumTrackTintColor(UISlider __self__, Selector __cmd__, UIColor minimumTrackTintColor);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setMinimumTrackTintColorSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, UIColor minimumTrackTintColor);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumTrackTintColor">@property(nonatomic, retain) UIColor *minimumTrackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setMinimumTrackTintColor(UIColor minimumTrackTintColor) {
        if (customClass) { objc_setMinimumTrackTintColorSuper(getSuper(), this, setMinimumTrackTintColor$, minimumTrackTintColor); } else { objc_setMinimumTrackTintColor(this, setMinimumTrackTintColor$, minimumTrackTintColor); }
    }
    
    private static final Selector minimumValue = Selector.register("minimumValue");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getMinimumValue(UISlider __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getMinimumValueSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumValue">@property(nonatomic) float minimumValue</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getMinimumValue() {
        if (customClass) { return objc_getMinimumValueSuper(getSuper(), this, minimumValue); } else { return objc_getMinimumValue(this, minimumValue); }
    }
    
    private static final Selector setMinimumValue$ = Selector.register("setMinimumValue:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setMinimumValue(UISlider __self__, Selector __cmd__, float minimumValue);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setMinimumValueSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, float minimumValue);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumValue">@property(nonatomic) float minimumValue</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMinimumValue(float minimumValue) {
        if (customClass) { objc_setMinimumValueSuper(getSuper(), this, setMinimumValue$, minimumValue); } else { objc_setMinimumValue(this, setMinimumValue$, minimumValue); }
    }
    
    private static final Selector minimumValueImage = Selector.register("minimumValueImage");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getMinimumValueImage(UISlider __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getMinimumValueImageSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumValueImage">@property(nonatomic, retain) UIImage *minimumValueImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getMinimumValueImage() {
        if (customClass) { return objc_getMinimumValueImageSuper(getSuper(), this, minimumValueImage); } else { return objc_getMinimumValueImage(this, minimumValueImage); }
    }
    
    private static final Selector setMinimumValueImage$ = Selector.register("setMinimumValueImage:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setMinimumValueImage(UISlider __self__, Selector __cmd__, UIImage minimumValueImage);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setMinimumValueImageSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, UIImage minimumValueImage);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumValueImage">@property(nonatomic, retain) UIImage *minimumValueImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMinimumValueImage(UIImage minimumValueImage) {
        if (customClass) { objc_setMinimumValueImageSuper(getSuper(), this, setMinimumValueImage$, minimumValueImage); } else { objc_setMinimumValueImage(this, setMinimumValueImage$, minimumValueImage); }
    }
    
    private static final Selector thumbTintColor = Selector.register("thumbTintColor");
    @Bridge(symbol = "objc_msgSend") private native static UIColor objc_getThumbTintColor(UISlider __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIColor objc_getThumbTintColorSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/thumbTintColor">@property(nonatomic, retain) UIColor *thumbTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIColor getThumbTintColor() {
        if (customClass) { return objc_getThumbTintColorSuper(getSuper(), this, thumbTintColor); } else { return objc_getThumbTintColor(this, thumbTintColor); }
    }
    
    private static final Selector setThumbTintColor$ = Selector.register("setThumbTintColor:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setThumbTintColor(UISlider __self__, Selector __cmd__, UIColor thumbTintColor);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setThumbTintColorSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, UIColor thumbTintColor);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/thumbTintColor">@property(nonatomic, retain) UIColor *thumbTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setThumbTintColor(UIColor thumbTintColor) {
        if (customClass) { objc_setThumbTintColorSuper(getSuper(), this, setThumbTintColor$, thumbTintColor); } else { objc_setThumbTintColor(this, setThumbTintColor$, thumbTintColor); }
    }
    
    private static final Selector value = Selector.register("value");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getValue(UISlider __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getValueSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/value">@property(nonatomic) float value</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getValue() {
        if (customClass) { return objc_getValueSuper(getSuper(), this, value); } else { return objc_getValue(this, value); }
    }
    
    private static final Selector setValue$ = Selector.register("setValue:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setValue(UISlider __self__, Selector __cmd__, float value);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setValueSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, float value);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/value">@property(nonatomic) float value</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setValue(float value) {
        if (customClass) { objc_setValueSuper(getSuper(), this, setValue$, value); } else { objc_setValue(this, setValue$, value); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector maximumTrackImageForState$ = Selector.register("maximumTrackImageForState:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getMaximumTrackImage(UISlider __self__, Selector __cmd__, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getMaximumTrackImageSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/maximumTrackImageForState:">- (UIImage *)maximumTrackImageForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getMaximumTrackImage(UIControlState state) {
        if (customClass) { return objc_getMaximumTrackImageSuper(getSuper(), this, maximumTrackImageForState$, state); } else { return objc_getMaximumTrackImage(this, maximumTrackImageForState$, state); }
    }
    
    private static final Selector maximumValueImageRectForBounds$ = Selector.register("maximumValueImageRectForBounds:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getMaximumValueImageRect(UISlider __self__, Selector __cmd__, CGRect bounds);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getMaximumValueImageRectSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, CGRect bounds);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/maximumValueImageRectForBounds:">- (CGRect)maximumValueImageRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getMaximumValueImageRect(CGRect bounds) {
        if (customClass) { return objc_getMaximumValueImageRectSuper(getSuper(), this, maximumValueImageRectForBounds$, bounds); } else { return objc_getMaximumValueImageRect(this, maximumValueImageRectForBounds$, bounds); }
    }
    
    private static final Selector minimumTrackImageForState$ = Selector.register("minimumTrackImageForState:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getMinimumTrackImage(UISlider __self__, Selector __cmd__, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getMinimumTrackImageSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/minimumTrackImageForState:">- (UIImage *)minimumTrackImageForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getMinimumTrackImage(UIControlState state) {
        if (customClass) { return objc_getMinimumTrackImageSuper(getSuper(), this, minimumTrackImageForState$, state); } else { return objc_getMinimumTrackImage(this, minimumTrackImageForState$, state); }
    }
    
    private static final Selector minimumValueImageRectForBounds$ = Selector.register("minimumValueImageRectForBounds:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getMinimumValueImageRect(UISlider __self__, Selector __cmd__, CGRect bounds);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getMinimumValueImageRectSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, CGRect bounds);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/minimumValueImageRectForBounds:">- (CGRect)minimumValueImageRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getMinimumValueImageRect(CGRect bounds) {
        if (customClass) { return objc_getMinimumValueImageRectSuper(getSuper(), this, minimumValueImageRectForBounds$, bounds); } else { return objc_getMinimumValueImageRect(this, minimumValueImageRectForBounds$, bounds); }
    }
    
    private static final Selector thumbImageForState$ = Selector.register("thumbImageForState:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getThumbImage(UISlider __self__, Selector __cmd__, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getThumbImageSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/thumbImageForState:">- (UIImage *)thumbImageForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getThumbImage(UIControlState state) {
        if (customClass) { return objc_getThumbImageSuper(getSuper(), this, thumbImageForState$, state); } else { return objc_getThumbImage(this, thumbImageForState$, state); }
    }
    
    private static final Selector thumbRectForBounds$trackRect$value$ = Selector.register("thumbRectForBounds:trackRect:value:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getThumbRect(UISlider __self__, Selector __cmd__, CGRect bounds, CGRect rect, float value);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getThumbRectSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, CGRect bounds, CGRect rect, float value);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/thumbRectForBounds:trackRect:value:">- (CGRect)thumbRectForBounds:(CGRect)bounds trackRect:(CGRect)rect value:(float)value</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getThumbRect(CGRect bounds, CGRect rect, float value) {
        if (customClass) { return objc_getThumbRectSuper(getSuper(), this, thumbRectForBounds$trackRect$value$, bounds, rect, value); } else { return objc_getThumbRect(this, thumbRectForBounds$trackRect$value$, bounds, rect, value); }
    }
    
    private static final Selector trackRectForBounds$ = Selector.register("trackRectForBounds:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getTrackRect(UISlider __self__, Selector __cmd__, CGRect bounds);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getTrackRectSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, CGRect bounds);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/trackRectForBounds:">- (CGRect)trackRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getTrackRect(CGRect bounds) {
        if (customClass) { return objc_getTrackRectSuper(getSuper(), this, trackRectForBounds$, bounds); } else { return objc_getTrackRect(this, trackRectForBounds$, bounds); }
    }
    
    private static final Selector setMaximumTrackImage$forState$ = Selector.register("setMaximumTrackImage:forState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setMaximumTrackImage(UISlider __self__, Selector __cmd__, UIImage image, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setMaximumTrackImageSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, UIImage image, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/setMaximumTrackImage:forState:">- (void)setMaximumTrackImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMaximumTrackImage(UIImage image, UIControlState state) {
        if (customClass) { objc_setMaximumTrackImageSuper(getSuper(), this, setMaximumTrackImage$forState$, image, state); } else { objc_setMaximumTrackImage(this, setMaximumTrackImage$forState$, image, state); }
    }
    
    private static final Selector setMinimumTrackImage$forState$ = Selector.register("setMinimumTrackImage:forState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setMinimumTrackImage(UISlider __self__, Selector __cmd__, UIImage image, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setMinimumTrackImageSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, UIImage image, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/setMinimumTrackImage:forState:">- (void)setMinimumTrackImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMinimumTrackImage(UIImage image, UIControlState state) {
        if (customClass) { objc_setMinimumTrackImageSuper(getSuper(), this, setMinimumTrackImage$forState$, image, state); } else { objc_setMinimumTrackImage(this, setMinimumTrackImage$forState$, image, state); }
    }
    
    private static final Selector setThumbImage$forState$ = Selector.register("setThumbImage:forState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setThumbImage(UISlider __self__, Selector __cmd__, UIImage image, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setThumbImageSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, UIImage image, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/setThumbImage:forState:">- (void)setThumbImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setThumbImage(UIImage image, UIControlState state) {
        if (customClass) { objc_setThumbImageSuper(getSuper(), this, setThumbImage$forState$, image, state); } else { objc_setThumbImage(this, setThumbImage$forState$, image, state); }
    }
    
    private static final Selector setValue$animated$ = Selector.register("setValue:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setValue(UISlider __self__, Selector __cmd__, float value, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setValueSuper(ObjCSuper __super__, UISlider __self__, Selector __cmd__, float value, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/setValue:animated:">- (void)setValue:(float)value animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setValue(float value, boolean animated) {
        if (customClass) { objc_setValueSuper(getSuper(), this, setValue$animated$, value, animated); } else { objc_setValue(this, setValue$animated$, value, animated); }
    }
    /*</methods>*/

}
