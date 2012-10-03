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
public class /*<name>*/ UILongPressGestureRecognizer /*</name>*/ 
    extends /*<extends>*/ UIGestureRecognizer /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UILongPressGestureRecognizer /*</name>*/.class);
    }

    /*<constructors>*/
    public UILongPressGestureRecognizer() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("allowableMovement") public native @Type("CGFloat") float getAllowableMovement();
    @Bind("setAllowableMovement:") public native void setAllowableMovement(@Type("CGFloat") float v);
    @Bind("minimumPressDuration") public native @Type("CFTimeInterval") double getMinimumPressDuration();
    @Bind("setMinimumPressDuration:") public native void setMinimumPressDuration(@Type("CFTimeInterval") double v);
    @Bind("numberOfTapsRequired") public native @Type("NSUInteger") int getNumberOfTapsRequired();
    @Bind("setNumberOfTapsRequired:") public native void setNumberOfTapsRequired(@Type("NSUInteger") int v);
    @Bind("numberOfTouchesRequired") public native @Type("NSInteger") int getNumberOfTouchesRequired();
    @Bind("setNumberOfTouchesRequired:") public native void setNumberOfTouchesRequired(@Type("NSInteger") int v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
