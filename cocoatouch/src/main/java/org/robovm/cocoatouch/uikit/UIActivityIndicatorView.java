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
public class /*<name>*/ UIActivityIndicatorView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIActivityIndicatorView /*</name>*/.class);
    }

    /*<constructors>*/
    public UIActivityIndicatorView() {}
    @Bind("initWithActivityIndicatorStyle:") public UIActivityIndicatorView(@Type("UIActivityIndicatorViewStyle") UIActivityIndicatorViewStyle style) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("activityIndicatorViewStyle") public native @Type("UIActivityIndicatorViewStyle") UIActivityIndicatorViewStyle getActivityIndicatorViewStyle();
    @Bind("setActivityIndicatorViewStyle:") public native void setActivityIndicatorViewStyle(@Type("UIActivityIndicatorViewStyle") UIActivityIndicatorViewStyle v);
    @Bind("color") public native @Type("UIColor *") UIColor getColor();
    @Bind("setColor:") public native void setColor(@Type("UIColor *") UIColor v);
    @Bind("hidesWhenStopped") public native @Type("BOOL") boolean isHidesWhenStopped();
    @Bind("setHidesWhenStopped:") public native void setHidesWhenStopped(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    @Bind("isAnimating") public native @Type("BOOL") boolean isAnimating();
    @Bind("startAnimating") public native @Type("void") void startAnimating();
    @Bind("stopAnimating") public native @Type("void") void stopAnimating();
    /*</methods>*/

}
