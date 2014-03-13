/*
 * Copyright (C) 2014 Trillian AB
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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIControl/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIControlPtr extends Ptr<UIControl, UIControlPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIControl.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIControl() {}
    protected UIControl(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "isEnabled")
    public native boolean isEnabled();
    @Property(selector = "setEnabled:")
    public native void setEnabled(boolean v);
    @Property(selector = "isSelected")
    public native boolean isSelected();
    @Property(selector = "setSelected:")
    public native void setSelected(boolean v);
    @Property(selector = "isHighlighted")
    public native boolean isHighlighted();
    @Property(selector = "setHighlighted:")
    public native void setHighlighted(boolean v);
    @Property(selector = "contentVerticalAlignment")
    public native UIControlContentVerticalAlignment getContentVerticalAlignment();
    @Property(selector = "setContentVerticalAlignment:")
    public native void setContentVerticalAlignment(UIControlContentVerticalAlignment v);
    @Property(selector = "contentHorizontalAlignment")
    public native UIControlContentHorizontalAlignment getContentHorizontalAlignment();
    @Property(selector = "setContentHorizontalAlignment:")
    public native void setContentHorizontalAlignment(UIControlContentHorizontalAlignment v);
    @Property(selector = "state")
    public native UIControlState getState();
    @Property(selector = "isTracking")
    public native boolean isTracking();
    @Property(selector = "isTouchInside")
    public native boolean isTouchInside();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "beginTrackingWithTouch:withEvent:")
    public native boolean beginTracking(UITouch touch, UIEvent event);
    @Method(selector = "continueTrackingWithTouch:withEvent:")
    public native boolean continueTracking(UITouch touch, UIEvent event);
    @Method(selector = "endTrackingWithTouch:withEvent:")
    public native void endTracking(UITouch touch, UIEvent event);
    @Method(selector = "cancelTrackingWithEvent:")
    public native void cancelTracking(UIEvent event);
    @Method(selector = "addTarget:action:forControlEvents:")
    public native void addTarget$action$forControlEvents$(NSObject target, Selector action, UIControlEvents controlEvents);
    @Method(selector = "removeTarget:action:forControlEvents:")
    public native void removeTarget$action$forControlEvents$(NSObject target, Selector action, UIControlEvents controlEvents);
    @Method(selector = "allTargets")
    public native NSSet<?> getAllTargets();
    @Method(selector = "allControlEvents")
    public native UIControlEvents getAllControlEvents();
    @Method(selector = "actionsForTarget:forControlEvent:")
    public native NSArray<?> getActions(NSObject target, UIControlEvents controlEvent);
    @Method(selector = "sendAction:to:forEvent:")
    public native void sendAction$to$forEvent$(Selector action, NSObject target, UIEvent event);
    @Method(selector = "sendActionsForControlEvents:")
    public native void sendControlEventsActions(UIControlEvents controlEvents);
    /*</methods>*/
}
