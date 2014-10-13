/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.apple.uikit;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIStepper/*</name>*/ 
    extends /*<extends>*/UIControl/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIStepperPtr extends Ptr<UIStepper, UIStepperPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIStepper.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIStepper() {}
    protected UIStepper(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public UIStepper(CGRect frame) {
        super(frame);
    }
    
    /*<properties>*/
    @Property(selector = "isContinuous")
    public native boolean isContinuous();
    @Property(selector = "setContinuous:")
    public native void setContinuous(boolean v);
    @Property(selector = "autorepeat")
    public native boolean isAutorepeat();
    @Property(selector = "setAutorepeat:")
    public native void setAutorepeat(boolean v);
    @Property(selector = "wraps")
    public native boolean isWraps();
    @Property(selector = "setWraps:")
    public native void setWraps(boolean v);
    @Property(selector = "value")
    public native double getValue();
    @Property(selector = "setValue:")
    public native void setValue(double v);
    @Property(selector = "minimumValue")
    public native double getMinimumValue();
    @Property(selector = "setMinimumValue:")
    public native void setMinimumValue(double v);
    @Property(selector = "maximumValue")
    public native double getMaximumValue();
    @Property(selector = "setMaximumValue:")
    public native void setMaximumValue(double v);
    @Property(selector = "stepValue")
    public native double getStepValue();
    @Property(selector = "setStepValue:")
    public native void setStepValue(double v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "tintColor")
    public native UIColor getTintColor();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setTintColor:")
    public native void setTintColor(UIColor v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setBackgroundImage:forState:")
    public native void setBackgroundImage(UIImage image, UIControlState state);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "backgroundImageForState:")
    public native UIImage getBackgroundImage(UIControlState state);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setDividerImage:forLeftSegmentState:rightSegmentState:")
    public native void setDividerImage(UIImage image, UIControlState leftState, UIControlState rightState);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "dividerImageForLeftSegmentState:rightSegmentState:")
    public native UIImage getDividerImage(UIControlState leftState, UIControlState rightState);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setIncrementImage:forState:")
    public native void setIncrementImage(UIImage image, UIControlState state);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "incrementImageForState:")
    public native UIImage getIncrementImage(UIControlState state);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "setDecrementImage:forState:")
    public native void setDecrementImage(UIImage image, UIControlState state);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "decrementImageForState:")
    public native UIImage getDecrementImage(UIControlState state);
    /*</methods>*/
}
