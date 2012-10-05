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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIStepper /*</name>*/.class);

    /*<constructors>*/
    protected UIStepper(SkipInit skipInit) { super(skipInit); }
    public UIStepper() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/autorepeat">@property(nonatomic) BOOL autorepeat</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("autorepeat") public native boolean isAutorepeat();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/autorepeat">@property(nonatomic) BOOL autorepeat</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setAutorepeat:") public native void setAutorepeat(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/continuous">@property(nonatomic, getter=isContinuous) BOOL continuous</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("isContinuous") public native boolean isContinuous();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/continuous">@property(nonatomic, getter=isContinuous) BOOL continuous</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setContinuous:") public native void setContinuous(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/maximumValue">@property(nonatomic) double maximumValue</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("maximumValue") public native double getMaximumValue();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/maximumValue">@property(nonatomic) double maximumValue</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setMaximumValue:") public native void setMaximumValue(double v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/minimumValue">@property(nonatomic) double minimumValue</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("minimumValue") public native double getMinimumValue();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/minimumValue">@property(nonatomic) double minimumValue</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setMinimumValue:") public native void setMinimumValue(double v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/stepValue">@property(nonatomic) double stepValue</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("stepValue") public native double getStepValue();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/stepValue">@property(nonatomic) double stepValue</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setStepValue:") public native void setStepValue(double v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("tintColor") public native UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/value">@property(nonatomic) double value</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("value") public native double getValue();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/value">@property(nonatomic) double value</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setValue:") public native void setValue(double v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/wraps">@property(nonatomic) BOOL wraps</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("wraps") public native boolean isWraps();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instp/UIStepper/wraps">@property(nonatomic) BOOL wraps</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setWraps:") public native void setWraps(boolean v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector backgroundImageForState$ = Selector.register("backgroundImageForState:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getBackgroundImage(UIStepper __self__, Selector __cmd__, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getBackgroundImageSuper(ObjCSuper __super__, UIStepper __self__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/backgroundImageForState:">- (UIImage *)backgroundImageForState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage getBackgroundImage(UIControlState state) {
        if (customClass) { return objc_getBackgroundImageSuper(getSuper(), this, backgroundImageForState$, state); } else { return objc_getBackgroundImage(this, backgroundImageForState$, state); }
    }
    
    private static final Selector decrementImageForState$ = Selector.register("decrementImageForState:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getDecrementImage(UIStepper __self__, Selector __cmd__, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getDecrementImageSuper(ObjCSuper __super__, UIStepper __self__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/decrementImageForState:">- (UIImage *)decrementImageForState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage getDecrementImage(UIControlState state) {
        if (customClass) { return objc_getDecrementImageSuper(getSuper(), this, decrementImageForState$, state); } else { return objc_getDecrementImage(this, decrementImageForState$, state); }
    }
    
    private static final Selector dividerImageForLeftSegmentState$rightSegmentState$ = Selector.register("dividerImageForLeftSegmentState:rightSegmentState:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getDividerImage(UIStepper __self__, Selector __cmd__, UIControlState leftState, UIControlState rightState);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getDividerImageSuper(ObjCSuper __super__, UIStepper __self__, Selector __cmd__, UIControlState leftState, UIControlState rightState);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/dividerImageForLeftSegmentState:rightSegmentState:">- (UIImage *)dividerImageForLeftSegmentState:(UIControlState)leftState rightSegmentState:(UIControlState)rightState</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage getDividerImage(UIControlState leftState, UIControlState rightState) {
        if (customClass) { return objc_getDividerImageSuper(getSuper(), this, dividerImageForLeftSegmentState$rightSegmentState$, leftState, rightState); } else { return objc_getDividerImage(this, dividerImageForLeftSegmentState$rightSegmentState$, leftState, rightState); }
    }
    
    private static final Selector incrementImageForState$ = Selector.register("incrementImageForState:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getIncrementImage(UIStepper __self__, Selector __cmd__, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getIncrementImageSuper(ObjCSuper __super__, UIStepper __self__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/incrementImageForState:">- (UIImage *)incrementImageForState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage getIncrementImage(UIControlState state) {
        if (customClass) { return objc_getIncrementImageSuper(getSuper(), this, incrementImageForState$, state); } else { return objc_getIncrementImage(this, incrementImageForState$, state); }
    }
    
    private static final Selector setBackgroundImage$forState$ = Selector.register("setBackgroundImage:forState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setBackgroundImage(UIStepper __self__, Selector __cmd__, UIImage image, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setBackgroundImageSuper(ObjCSuper __super__, UIStepper __self__, Selector __cmd__, UIImage image, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/setBackgroundImage:forState:">- (void)setBackgroundImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setBackgroundImage(UIImage image, UIControlState state) {
        if (customClass) { objc_setBackgroundImageSuper(getSuper(), this, setBackgroundImage$forState$, image, state); } else { objc_setBackgroundImage(this, setBackgroundImage$forState$, image, state); }
    }
    
    private static final Selector setDecrementImage$forState$ = Selector.register("setDecrementImage:forState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setDecrementImage(UIStepper __self__, Selector __cmd__, UIImage image, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setDecrementImageSuper(ObjCSuper __super__, UIStepper __self__, Selector __cmd__, UIImage image, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/setDecrementImage:forState:">- (void)setDecrementImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setDecrementImage(UIImage image, UIControlState state) {
        if (customClass) { objc_setDecrementImageSuper(getSuper(), this, setDecrementImage$forState$, image, state); } else { objc_setDecrementImage(this, setDecrementImage$forState$, image, state); }
    }
    
    private static final Selector setDividerImage$forLeftSegmentState$rightSegmentState$ = Selector.register("setDividerImage:forLeftSegmentState:rightSegmentState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setDividerImage(UIStepper __self__, Selector __cmd__, UIImage image, UIControlState leftState, UIControlState rightState);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setDividerImageSuper(ObjCSuper __super__, UIStepper __self__, Selector __cmd__, UIImage image, UIControlState leftState, UIControlState rightState);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/setDividerImage:forLeftSegmentState:rightSegmentState:">- (void)setDividerImage:(UIImage *)image forLeftSegmentState:(UIControlState)leftState rightSegmentState:(UIControlState)rightState</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setDividerImage(UIImage image, UIControlState leftState, UIControlState rightState) {
        if (customClass) { objc_setDividerImageSuper(getSuper(), this, setDividerImage$forLeftSegmentState$rightSegmentState$, image, leftState, rightState); } else { objc_setDividerImage(this, setDividerImage$forLeftSegmentState$rightSegmentState$, image, leftState, rightState); }
    }
    
    private static final Selector setIncrementImage$forState$ = Selector.register("setIncrementImage:forState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setIncrementImage(UIStepper __self__, Selector __cmd__, UIImage image, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setIncrementImageSuper(ObjCSuper __super__, UIStepper __self__, Selector __cmd__, UIImage image, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIStepper_Class/Reference/Reference.html#//apple_ref/occ/instm/UIStepper/setIncrementImage:forState:">- (void)setIncrementImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setIncrementImage(UIImage image, UIControlState state) {
        if (customClass) { objc_setIncrementImageSuper(getSuper(), this, setIncrementImage$forState$, image, state); } else { objc_setIncrementImage(this, setIncrementImage$forState$, image, state); }
    }
    /*</methods>*/

}
