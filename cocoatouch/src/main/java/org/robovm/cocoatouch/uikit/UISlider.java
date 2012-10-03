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
    @Bind("isContinuous") public native @Type("BOOL") boolean isContinuous();
    @Bind("setContinuous:") public native void setContinuous(@Type("BOOL") boolean v);
    @Bind("currentMaximumTrackImage") public native @Type("UIImage *") UIImage getCurrentMaximumTrackImage();
    @Bind("currentMinimumTrackImage") public native @Type("UIImage *") UIImage getCurrentMinimumTrackImage();
    @Bind("currentThumbImage") public native @Type("UIImage *") UIImage getCurrentThumbImage();
    @Bind("maximumTrackTintColor") public native @Type("UIColor *") UIColor getMaximumTrackTintColor();
    @Bind("setMaximumTrackTintColor:") public native void setMaximumTrackTintColor(@Type("UIColor *") UIColor v);
    @Bind("maximumValue") public native @Type("float") float getMaximumValue();
    @Bind("setMaximumValue:") public native void setMaximumValue(@Type("float") float v);
    @Bind("maximumValueImage") public native @Type("UIImage *") UIImage getMaximumValueImage();
    @Bind("setMaximumValueImage:") public native void setMaximumValueImage(@Type("UIImage *") UIImage v);
    @Bind("minimumTrackTintColor") public native @Type("UIColor *") UIColor getMinimumTrackTintColor();
    @Bind("setMinimumTrackTintColor:") public native void setMinimumTrackTintColor(@Type("UIColor *") UIColor v);
    @Bind("minimumValue") public native @Type("float") float getMinimumValue();
    @Bind("setMinimumValue:") public native void setMinimumValue(@Type("float") float v);
    @Bind("minimumValueImage") public native @Type("UIImage *") UIImage getMinimumValueImage();
    @Bind("setMinimumValueImage:") public native void setMinimumValueImage(@Type("UIImage *") UIImage v);
    @Bind("thumbTintColor") public native @Type("UIColor *") UIColor getThumbTintColor();
    @Bind("setThumbTintColor:") public native void setThumbTintColor(@Type("UIColor *") UIColor v);
    @Bind("value") public native @Type("float") float getValue();
    @Bind("setValue:") public native void setValue(@Type("float") float v);
    /*</properties>*/
    /*<methods>*/
    @Bind("maximumTrackImageForState:") public native @Type("UIImage *") UIImage getMaximumTrackImage(@Type("UIControlState") UIControlState state);
    @Bind("maximumValueImageRectForBounds:") public native @Type("CGRect") CGRect getMaximumValueImageRect(@Type("CGRect") CGRect bounds);
    @Bind("minimumTrackImageForState:") public native @Type("UIImage *") UIImage getMinimumTrackImage(@Type("UIControlState") UIControlState state);
    @Bind("minimumValueImageRectForBounds:") public native @Type("CGRect") CGRect getMinimumValueImageRect(@Type("CGRect") CGRect bounds);
    @Bind("thumbImageForState:") public native @Type("UIImage *") UIImage getThumbImage(@Type("UIControlState") UIControlState state);
    @Bind("thumbRectForBounds:trackRect:value:") public native @Type("CGRect") CGRect getThumbRect(@Type("CGRect") CGRect bounds, @Type("CGRect") CGRect rect, @Type("float") float value);
    @Bind("trackRectForBounds:") public native @Type("CGRect") CGRect getTrackRect(@Type("CGRect") CGRect bounds);
    @Bind("setMaximumTrackImage:forState:") public native @Type("void") void setMaximumTrackImage(@Type("UIImage *") UIImage image, @Type("UIControlState") UIControlState state);
    @Bind("setMinimumTrackImage:forState:") public native @Type("void") void setMinimumTrackImage(@Type("UIImage *") UIImage image, @Type("UIControlState") UIControlState state);
    @Bind("setThumbImage:forState:") public native @Type("void") void setThumbImage(@Type("UIImage *") UIImage image, @Type("UIControlState") UIControlState state);
    @Bind("setValue:animated:") public native @Type("void") void setValue(@Type("float") float value, @Type("BOOL") boolean animated);
    /*</methods>*/

}
