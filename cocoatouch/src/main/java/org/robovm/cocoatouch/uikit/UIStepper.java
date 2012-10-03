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
    @Bind("autorepeat") public native @Type("BOOL") boolean isAutorepeat();
    @Bind("setAutorepeat:") public native void setAutorepeat(@Type("BOOL") boolean v);
    @Bind("isContinuous") public native @Type("BOOL") boolean isContinuous();
    @Bind("setContinuous:") public native void setContinuous(@Type("BOOL") boolean v);
    @Bind("maximumValue") public native @Type("double") double getMaximumValue();
    @Bind("setMaximumValue:") public native void setMaximumValue(@Type("double") double v);
    @Bind("minimumValue") public native @Type("double") double getMinimumValue();
    @Bind("setMinimumValue:") public native void setMinimumValue(@Type("double") double v);
    @Bind("stepValue") public native @Type("double") double getStepValue();
    @Bind("setStepValue:") public native void setStepValue(@Type("double") double v);
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    @Bind("value") public native @Type("double") double getValue();
    @Bind("setValue:") public native void setValue(@Type("double") double v);
    @Bind("wraps") public native @Type("BOOL") boolean isWraps();
    @Bind("setWraps:") public native void setWraps(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    @Bind("backgroundImageForState:") public native @Type("UIImage *") UIImage backgroundImageForState(@Type("UIControlState") UIControlState state);
    @Bind("decrementImageForState:") public native @Type("UIImage *") UIImage decrementImageForState(@Type("UIControlState") UIControlState state);
    @Bind("dividerImageForLeftSegmentState:rightSegmentState:") public native @Type("UIImage *") UIImage dividerImageForLeftSegmentState(@Type("UIControlState") UIControlState leftState, @Type("UIControlState") UIControlState rightState);
    @Bind("incrementImageForState:") public native @Type("UIImage *") UIImage incrementImageForState(@Type("UIControlState") UIControlState state);
    @Bind("setBackgroundImage:forState:") public native @Type("void") void setBackgroundImage(@Type("UIImage *") UIImage image, @Type("UIControlState") UIControlState state);
    @Bind("setDecrementImage:forState:") public native @Type("void") void setDecrementImage(@Type("UIImage *") UIImage image, @Type("UIControlState") UIControlState state);
    @Bind("setDividerImage:forLeftSegmentState:rightSegmentState:") public native @Type("void") void setDividerImage(@Type("UIImage *") UIImage image, @Type("UIControlState") UIControlState leftState, @Type("UIControlState") UIControlState rightState);
    @Bind("setIncrementImage:forState:") public native @Type("void") void setIncrementImage(@Type("UIImage *") UIImage image, @Type("UIControlState") UIControlState state);
    /*</methods>*/

}
