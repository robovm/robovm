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
public class /*<name>*/ UISwitch /*</name>*/ 
    extends /*<extends>*/ UIControl /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UISwitch /*</name>*/.class);
    }

    /*<constructors>*/
    public UISwitch() {}
    @Bind("initWithFrame:") public UISwitch(@Type("CGRect") CGRect frame) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("offImage") public native @Type("UIImage *") UIImage getOffImage();
    @Bind("setOffImage:") public native void setOffImage(@Type("UIImage *") UIImage v);
    @Bind("isOn") public native @Type("BOOL") boolean isOn();
    @Bind("setOn:") public native void setOn(@Type("BOOL") boolean v);
    @Bind("onImage") public native @Type("UIImage *") UIImage getOnImage();
    @Bind("setOnImage:") public native void setOnImage(@Type("UIImage *") UIImage v);
    @Bind("onTintColor") public native @Type("UIColor *") UIColor getOnTintColor();
    @Bind("setOnTintColor:") public native void setOnTintColor(@Type("UIColor *") UIColor v);
    @Bind("thumbTintColor") public native @Type("UIColor *") UIColor getThumbTintColor();
    @Bind("setThumbTintColor:") public native void setThumbTintColor(@Type("UIColor *") UIColor v);
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    /*</properties>*/
    /*<methods>*/
    @Bind("setOn:animated:") public native @Type("void") void setOn(@Type("BOOL") boolean on, @Type("BOOL") boolean animated);
    /*</methods>*/

}
