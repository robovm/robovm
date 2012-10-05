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

    /*<constructors>*/
    public UISlider() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/continuous">@property(nonatomic, getter=isContinuous) BOOL continuous</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isContinuous") public native @Type("BOOL") boolean isContinuous();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/continuous">@property(nonatomic, getter=isContinuous) BOOL continuous</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setContinuous:") public native void setContinuous(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/currentMaximumTrackImage">@property(nonatomic, readonly) UIImage *currentMaximumTrackImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("currentMaximumTrackImage") public native @Type("UIImage *") UIImage getCurrentMaximumTrackImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/currentMinimumTrackImage">@property(nonatomic, readonly) UIImage *currentMinimumTrackImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("currentMinimumTrackImage") public native @Type("UIImage *") UIImage getCurrentMinimumTrackImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/currentThumbImage">@property(nonatomic, readonly) UIImage *currentThumbImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("currentThumbImage") public native @Type("UIImage *") UIImage getCurrentThumbImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumTrackTintColor">@property(nonatomic, retain) UIColor *maximumTrackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("maximumTrackTintColor") public native @Type("UIColor *") UIColor getMaximumTrackTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumTrackTintColor">@property(nonatomic, retain) UIColor *maximumTrackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setMaximumTrackTintColor:") public native void setMaximumTrackTintColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumValue">@property(nonatomic) float maximumValue</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("maximumValue") public native @Type("float") float getMaximumValue();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumValue">@property(nonatomic) float maximumValue</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMaximumValue:") public native void setMaximumValue(@Type("float") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumValueImage">@property(nonatomic, retain) UIImage *maximumValueImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("maximumValueImage") public native @Type("UIImage *") UIImage getMaximumValueImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/maximumValueImage">@property(nonatomic, retain) UIImage *maximumValueImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMaximumValueImage:") public native void setMaximumValueImage(@Type("UIImage *") UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumTrackTintColor">@property(nonatomic, retain) UIColor *minimumTrackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("minimumTrackTintColor") public native @Type("UIColor *") UIColor getMinimumTrackTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumTrackTintColor">@property(nonatomic, retain) UIColor *minimumTrackTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setMinimumTrackTintColor:") public native void setMinimumTrackTintColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumValue">@property(nonatomic) float minimumValue</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("minimumValue") public native @Type("float") float getMinimumValue();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumValue">@property(nonatomic) float minimumValue</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMinimumValue:") public native void setMinimumValue(@Type("float") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumValueImage">@property(nonatomic, retain) UIImage *minimumValueImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("minimumValueImage") public native @Type("UIImage *") UIImage getMinimumValueImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/minimumValueImage">@property(nonatomic, retain) UIImage *minimumValueImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMinimumValueImage:") public native void setMinimumValueImage(@Type("UIImage *") UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/thumbTintColor">@property(nonatomic, retain) UIColor *thumbTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("thumbTintColor") public native @Type("UIColor *") UIColor getThumbTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/thumbTintColor">@property(nonatomic, retain) UIColor *thumbTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setThumbTintColor:") public native void setThumbTintColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/value">@property(nonatomic) float value</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("value") public native @Type("float") float getValue();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instp/UISlider/value">@property(nonatomic) float value</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setValue:") public native void setValue(@Type("float") float v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/maximumTrackImageForState:">- (UIImage *)maximumTrackImageForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("maximumTrackImageForState:") public native @Type("UIImage *") UIImage getMaximumTrackImage(@Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/maximumValueImageRectForBounds:">- (CGRect)maximumValueImageRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("maximumValueImageRectForBounds:") public native @Type("CGRect") CGRect getMaximumValueImageRect(@Type("CGRect") CGRect bounds);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/minimumTrackImageForState:">- (UIImage *)minimumTrackImageForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("minimumTrackImageForState:") public native @Type("UIImage *") UIImage getMinimumTrackImage(@Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/minimumValueImageRectForBounds:">- (CGRect)minimumValueImageRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("minimumValueImageRectForBounds:") public native @Type("CGRect") CGRect getMinimumValueImageRect(@Type("CGRect") CGRect bounds);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/thumbImageForState:">- (UIImage *)thumbImageForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("thumbImageForState:") public native @Type("UIImage *") UIImage getThumbImage(@Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/thumbRectForBounds:trackRect:value:">- (CGRect)thumbRectForBounds:(CGRect)bounds trackRect:(CGRect)rect value:(float)value</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("thumbRectForBounds:trackRect:value:") public native @Type("CGRect") CGRect getThumbRect(@Type("CGRect") CGRect bounds, @Type("CGRect") CGRect rect, @Type("float") float value);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/trackRectForBounds:">- (CGRect)trackRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("trackRectForBounds:") public native @Type("CGRect") CGRect getTrackRect(@Type("CGRect") CGRect bounds);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/setMaximumTrackImage:forState:">- (void)setMaximumTrackImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMaximumTrackImage:forState:") public native @Type("void") void setMaximumTrackImage(@Type("UIImage *") UIImage image, @Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/setMinimumTrackImage:forState:">- (void)setMinimumTrackImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMinimumTrackImage:forState:") public native @Type("void") void setMinimumTrackImage(@Type("UIImage *") UIImage image, @Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/setThumbImage:forState:">- (void)setThumbImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setThumbImage:forState:") public native @Type("void") void setThumbImage(@Type("UIImage *") UIImage image, @Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISlider_Class/Reference/Reference.html#//apple_ref/occ/instm/UISlider/setValue:animated:">- (void)setValue:(float)value animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setValue:animated:") public native @Type("void") void setValue(@Type("float") float value, @Type("BOOL") boolean animated);
    /*</methods>*/

}
