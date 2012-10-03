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
public class /*<name>*/ UIControl /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIControl /*</name>*/.class);
    }

    /*<constructors>*/
    public UIControl() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("contentHorizontalAlignment") public native @Type("UIControlContentHorizontalAlignment") UIControlContentHorizontalAlignment getContentHorizontalAlignment();
    @Bind("setContentHorizontalAlignment:") public native void setContentHorizontalAlignment(@Type("UIControlContentHorizontalAlignment") UIControlContentHorizontalAlignment v);
    @Bind("contentVerticalAlignment") public native @Type("UIControlContentVerticalAlignment") UIControlContentVerticalAlignment getContentVerticalAlignment();
    @Bind("setContentVerticalAlignment:") public native void setContentVerticalAlignment(@Type("UIControlContentVerticalAlignment") UIControlContentVerticalAlignment v);
    @Bind("isEnabled") public native @Type("BOOL") boolean isEnabled();
    @Bind("setEnabled:") public native void setEnabled(@Type("BOOL") boolean v);
    @Bind("isHighlighted") public native @Type("BOOL") boolean isHighlighted();
    @Bind("setHighlighted:") public native void setHighlighted(@Type("BOOL") boolean v);
    @Bind("isSelected") public native @Type("BOOL") boolean isSelected();
    @Bind("setSelected:") public native void setSelected(@Type("BOOL") boolean v);
    @Bind("state") public native @Type("UIControlState") UIControlState getState();
    @Bind("isTouchInside") public native @Type("BOOL") boolean isTouchInside();
    @Bind("isTracking") public native @Type("BOOL") boolean isTracking();
    /*</properties>*/
    /*<methods>*/
    @Bind("addTarget:action:forControlEvents:") public native @Type("void") void addTarget(@Type("id") NSObject target, @Type("SEL") Selector action, @Type("UIControlEvents") EnumSet<UIControlEvent> controlEvents);
    @Bind("beginTrackingWithTouch:withEvent:") public native @Type("BOOL") boolean beginTracking(@Type("UITouch *") UITouch touch, @Type("UIEvent *") UIEvent event);
    @Bind("cancelTrackingWithEvent:") public native @Type("void") void cancelTracking(@Type("UIEvent *") UIEvent event);
    @Bind("continueTrackingWithTouch:withEvent:") public native @Type("BOOL") boolean continueTracking(@Type("UITouch *") UITouch touch, @Type("UIEvent *") UIEvent event);
    @Bind("endTrackingWithTouch:withEvent:") public native @Type("void") void endTracking(@Type("UITouch *") UITouch touch, @Type("UIEvent *") UIEvent event);
    @Bind("actionsForTarget:forControlEvent:") public native @Type("NSArray *") NSArray getActions(@Type("id") NSObject target, @Type("UIControlEvents") UIControlEvent controlEvent);
    @Bind("allControlEvents") public native @Type("UIControlEvents") EnumSet<UIControlEvent> getAllControlEvents();
    @Bind("allTargets") public native @Type("NSSet *") NSSet getAllTargets();
    @Bind("removeTarget:action:forControlEvents:") public native @Type("void") void removeTarget(@Type("id") NSObject target, @Type("SEL") Selector action, @Type("UIControlEvents") EnumSet<UIControlEvent> controlEvents);
    @Bind("sendAction:to:forEvent:") public native @Type("void") void sendAction(@Type("SEL") Selector action, @Type("id") NSObject target, @Type("UIEvent *") UIEvent event);
    @Bind("sendActionsForControlEvents:") public native @Type("void") void sendControlEventsActions(@Type("UIControlEvents") EnumSet<UIControlEvent> controlEvents);
    /*</methods>*/

}
