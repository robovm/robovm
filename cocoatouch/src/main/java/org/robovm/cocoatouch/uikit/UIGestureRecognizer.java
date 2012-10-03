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
public class /*<name>*/ UIGestureRecognizer /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIGestureRecognizer /*</name>*/.class);
    }

    /*<constructors>*/
    public UIGestureRecognizer() {}
    @Bind("initWithTarget:action:") public UIGestureRecognizer(@Type("id") NSObject target, @Type("SEL") Selector action) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("cancelsTouchesInView") public native @Type("BOOL") boolean isCancelsTouchesInView();
    @Bind("setCancelsTouchesInView:") public native void setCancelsTouchesInView(@Type("BOOL") boolean v);
    @Bind("delaysTouchesBegan") public native @Type("BOOL") boolean isDelaysTouchesBegan();
    @Bind("setDelaysTouchesBegan:") public native void setDelaysTouchesBegan(@Type("BOOL") boolean v);
    @Bind("delaysTouchesEnded") public native @Type("BOOL") boolean isDelaysTouchesEnded();
    @Bind("setDelaysTouchesEnded:") public native void setDelaysTouchesEnded(@Type("BOOL") boolean v);
    @Bind("delegate") public native @Type("id<UIGestureRecognizerDelegate>") UIGestureRecognizerDelegate getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UIGestureRecognizerDelegate>") UIGestureRecognizerDelegate v);
    @Bind("isEnabled") public native @Type("BOOL") boolean isEnabled();
    @Bind("setEnabled:") public native void setEnabled(@Type("BOOL") boolean v);
    @Bind("state") public native @Type("UIGestureRecognizerState") UIGestureRecognizerState getState();
    @Bind("view") public native @Type("UIView *") UIView getView();
    /*</properties>*/
    /*<methods>*/
    @Bind("addTarget:action:") public native @Type("void") void addTarget(@Type("id") NSObject target, @Type("SEL") Selector action);
    @Bind("canBePreventedByGestureRecognizer:") public native @Type("BOOL") boolean canBePreventedByGestureRecognizer(@Type("UIGestureRecognizer *") UIGestureRecognizer preventingGestureRecognizer);
    @Bind("canPreventGestureRecognizer:") public native @Type("BOOL") boolean canPreventGestureRecognizer(@Type("UIGestureRecognizer *") UIGestureRecognizer preventedGestureRecognizer);
    @Bind("locationInView:") public native @Type("CGPoint") CGPoint getLocation(@Type("UIView *") UIView view);
    @Bind("locationOfTouch:inView:") public native @Type("CGPoint") CGPoint getLocation(@Type("NSUInteger") int touchIndex, @Type("UIView *") UIView view);
    @Bind("numberOfTouches") public native @Type("NSUInteger") int getNumberOfTouches();
    @Bind("ignoreTouch:forEvent:") public native @Type("void") void ignoreTouch(@Type("UITouch *") UITouch touch, @Type("UIEvent *") UIEvent event);
    @Bind("removeTarget:action:") public native @Type("void") void removeTarget(@Type("id") NSObject target, @Type("SEL") Selector action);
    @Bind("requireGestureRecognizerToFail:") public native @Type("void") void requireGestureRecognizerToFail(@Type("UIGestureRecognizer *") UIGestureRecognizer otherGestureRecognizer);
    @Bind("reset") public native @Type("void") void reset();
    @Bind("touchesBegan:withEvent:") public native @Type("void") void touchesBegan(@Type("NSSet *") NSSet touches, @Type("UIEvent *") UIEvent event);
    @Bind("touchesCancelled:withEvent:") public native @Type("void") void touchesCancelled(@Type("NSSet *") NSSet touches, @Type("UIEvent *") UIEvent event);
    @Bind("touchesEnded:withEvent:") public native @Type("void") void touchesEnded(@Type("NSSet *") NSSet touches, @Type("UIEvent *") UIEvent event);
    @Bind("touchesMoved:withEvent:") public native @Type("void") void touchesMoved(@Type("NSSet *") NSSet touches, @Type("UIEvent *") UIEvent event);
    /*</methods>*/

}
