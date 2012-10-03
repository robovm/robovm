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
public class /*<name>*/ UITouch /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITouch /*</name>*/.class);
    }

    /*<constructors>*/
    public UITouch() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("gestureRecognizers") public native @Type("NSArray *") NSArray getGestureRecognizers();
    @Bind("phase") public native @Type("UITouchPhase") UITouchPhase getPhase();
    @Bind("tapCount") public native @Type("NSUInteger") int getTapCount();
    @Bind("timestamp") public native @Type("NSTimeInterval") double getTimestamp();
    @Bind("view") public native @Type("UIView *") UIView getView();
    @Bind("window") public native @Type("UIWindow *") UIWindow getWindow();
    /*</properties>*/
    /*<methods>*/
    @Bind("locationInView:") public native @Type("CGPoint") CGPoint getLocation(@Type("UIView *") UIView view);
    @Bind("previousLocationInView:") public native @Type("CGPoint") CGPoint getPreviousLocation(@Type("UIView *") UIView view);
    /*</methods>*/

}
