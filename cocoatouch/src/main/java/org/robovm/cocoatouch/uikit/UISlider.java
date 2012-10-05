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
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/continuous">@property(nonatomic, getter=isContinuous) BOOL continuous</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isContinuous") public native boolean isContinuous();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/continuous">@property(nonatomic, getter=isContinuous) BOOL continuous</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setContinuous:") public native void setContinuous(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/currentMaximumTrackImage">@property(nonatomic, readonly) UIImage *currentMaximumTrackImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("currentMaximumTrackImage") public native UIImage getCurrentMaximumTrackImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/currentMinimumTrackImage">@property(nonatomic, readonly) UIImage *currentMinimumTrackImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("currentMinimumTrackImage") public native UIImage getCurrentMinimumTrackImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/currentThumbImage">@property(nonatomic, readonly) UIImage *currentThumbImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("currentThumbImage") public native UIImage getCurrentThumbImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumTrackTintColor">@property(nonatomic, retain) UIColor *maximumTrackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("maximumTrackTintColor") public native UIColor getMaximumTrackTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumTrackTintColor">@property(nonatomic, retain) UIColor *maximumTrackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setMaximumTrackTintColor:") public native void setMaximumTrackTintColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumValue">@property(nonatomic) float maximumValue</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("maximumValue") public native float getMaximumValue();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumValue">@property(nonatomic) float maximumValue</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMaximumValue:") public native void setMaximumValue(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumValueImage">@property(nonatomic, retain) UIImage *maximumValueImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("maximumValueImage") public native UIImage getMaximumValueImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumValueImage">@property(nonatomic, retain) UIImage *maximumValueImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMaximumValueImage:") public native void setMaximumValueImage(UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumTrackTintColor">@property(nonatomic, retain) UIColor *minimumTrackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("minimumTrackTintColor") public native UIColor getMinimumTrackTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumTrackTintColor">@property(nonatomic, retain) UIColor *minimumTrackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setMinimumTrackTintColor:") public native void setMinimumTrackTintColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumValue">@property(nonatomic) float minimumValue</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("minimumValue") public native float getMinimumValue();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumValue">@property(nonatomic) float minimumValue</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMinimumValue:") public native void setMinimumValue(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumValueImage">@property(nonatomic, retain) UIImage *minimumValueImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("minimumValueImage") public native UIImage getMinimumValueImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumValueImage">@property(nonatomic, retain) UIImage *minimumValueImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMinimumValueImage:") public native void setMinimumValueImage(UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/thumbTintColor">@property(nonatomic, retain) UIColor *thumbTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("thumbTintColor") public native UIColor getThumbTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/thumbTintColor">@property(nonatomic, retain) UIColor *thumbTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setThumbTintColor:") public native void setThumbTintColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/value">@property(nonatomic) float value</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("value") public native float getValue();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/value">@property(nonatomic) float value</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setValue:") public native void setValue(float v);
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
