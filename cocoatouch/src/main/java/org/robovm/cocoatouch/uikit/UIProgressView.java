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
public class /*<name>*/ UIProgressView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIProgressView /*</name>*/.class);
    }

    /*<constructors>*/
    public UIProgressView() {}
    @Bind("initWithProgressViewStyle:") public UIProgressView(@Type("UIProgressViewStyle") UIProgressViewStyle style) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("progress") public native @Type("float") float getProgress();
    @Bind("setProgress:") public native void setProgress(@Type("float") float v);
    @Bind("progressImage") public native @Type("UIImage *") UIImage getProgressImage();
    @Bind("setProgressImage:") public native void setProgressImage(@Type("UIImage *") UIImage v);
    @Bind("progressTintColor") public native @Type("UIColor *") UIColor getProgressTintColor();
    @Bind("setProgressTintColor:") public native void setProgressTintColor(@Type("UIColor *") UIColor v);
    @Bind("progressViewStyle") public native @Type("UIProgressViewStyle") UIProgressViewStyle getProgressViewStyle();
    @Bind("setProgressViewStyle:") public native void setProgressViewStyle(@Type("UIProgressViewStyle") UIProgressViewStyle v);
    @Bind("trackImage") public native @Type("UIImage *") UIImage getTrackImage();
    @Bind("setTrackImage:") public native void setTrackImage(@Type("UIImage *") UIImage v);
    @Bind("trackTintColor") public native @Type("UIColor *") UIColor getTrackTintColor();
    @Bind("setTrackTintColor:") public native void setTrackTintColor(@Type("UIColor *") UIColor v);
    /*</properties>*/
    /*<methods>*/
    @Bind("setProgress:animated:") public native @Type("void") void setProgress(@Type("float") float progress, @Type("BOOL") boolean animated);
    /*</methods>*/

}
