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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html">UIStepper Class Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIStepper /*</name>*/ 
    extends /*<extends>*/ UIControl /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIStepper /*</name>*/.class);
    }

    /*<constructors>*/
    public UIStepper() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/autorepeat">@property(nonatomic) BOOL autorepeat</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("autorepeat") public native @Type("BOOL") boolean isAutorepeat();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/autorepeat">@property(nonatomic) BOOL autorepeat</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setAutorepeat:") public native void setAutorepeat(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/continuous">@property(nonatomic, getter=isContinuous) BOOL continuous</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("isContinuous") public native @Type("BOOL") boolean isContinuous();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/continuous">@property(nonatomic, getter=isContinuous) BOOL continuous</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setContinuous:") public native void setContinuous(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/maximumValue">@property(nonatomic) double maximumValue</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("maximumValue") public native @Type("double") double getMaximumValue();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/maximumValue">@property(nonatomic) double maximumValue</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setMaximumValue:") public native void setMaximumValue(@Type("double") double v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/minimumValue">@property(nonatomic) double minimumValue</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("minimumValue") public native @Type("double") double getMinimumValue();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/minimumValue">@property(nonatomic) double minimumValue</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setMinimumValue:") public native void setMinimumValue(@Type("double") double v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/stepValue">@property(nonatomic) double stepValue</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("stepValue") public native @Type("double") double getStepValue();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/stepValue">@property(nonatomic) double stepValue</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setStepValue:") public native void setStepValue(@Type("double") double v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/value">@property(nonatomic) double value</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("value") public native @Type("double") double getValue();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/value">@property(nonatomic) double value</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setValue:") public native void setValue(@Type("double") double v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/wraps">@property(nonatomic) BOOL wraps</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("wraps") public native @Type("BOOL") boolean isWraps();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/wraps">@property(nonatomic) BOOL wraps</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setWraps:") public native void setWraps(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/backgroundImageForState:">- (UIImage *)backgroundImageForState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("backgroundImageForState:") public native @Type("UIImage *") UIImage getBackgroundImage(@Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/decrementImageForState:">- (UIImage *)decrementImageForState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("decrementImageForState:") public native @Type("UIImage *") UIImage getDecrementImage(@Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/dividerImageForLeftSegmentState:rightSegmentState:">- (UIImage *)dividerImageForLeftSegmentState:(UIControlState)leftState rightSegmentState:(UIControlState)rightState</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("dividerImageForLeftSegmentState:rightSegmentState:") public native @Type("UIImage *") UIImage getDividerImage(@Type("UIControlState") UIControlState leftState, @Type("UIControlState") UIControlState rightState);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/incrementImageForState:">- (UIImage *)incrementImageForState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("incrementImageForState:") public native @Type("UIImage *") UIImage getIncrementImage(@Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/setBackgroundImage:forState:">- (void)setBackgroundImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setBackgroundImage:forState:") public native @Type("void") void setBackgroundImage(@Type("UIImage *") UIImage image, @Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/setDecrementImage:forState:">- (void)setDecrementImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setDecrementImage:forState:") public native @Type("void") void setDecrementImage(@Type("UIImage *") UIImage image, @Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/setDividerImage:forLeftSegmentState:rightSegmentState:">- (void)setDividerImage:(UIImage *)image forLeftSegmentState:(UIControlState)leftState rightSegmentState:(UIControlState)rightState</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setDividerImage:forLeftSegmentState:rightSegmentState:") public native @Type("void") void setDividerImage(@Type("UIImage *") UIImage image, @Type("UIControlState") UIControlState leftState, @Type("UIControlState") UIControlState rightState);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/setIncrementImage:forState:">- (void)setIncrementImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setIncrementImage:forState:") public native @Type("void") void setIncrementImage(@Type("UIImage *") UIImage image, @Type("UIControlState") UIControlState state);
    /*</methods>*/

}
