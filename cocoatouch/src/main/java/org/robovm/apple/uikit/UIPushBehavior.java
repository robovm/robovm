/*
 * Copyright (C) 2014 Trillian Mobile AB
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
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIPushBehavior/*</name>*/ 
    extends /*<extends>*/UIDynamicBehavior/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIPushBehaviorPtr extends Ptr<UIPushBehavior, UIPushBehaviorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIPushBehavior.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIPushBehavior() {}
    protected UIPushBehavior(SkipInit skipInit) { super(skipInit); }
    public UIPushBehavior(List<UIDynamicItem> items, UIPushBehaviorMode mode) { super((SkipInit) null); initObject(init(items, mode)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "items")
    public native List<UIDynamicItem> getItems();
    @Property(selector = "mode")
    public native UIPushBehaviorMode getMode();
    @Property(selector = "active")
    public native boolean isActive();
    @Property(selector = "setActive:")
    public native void setActive(boolean v);
    @Property(selector = "angle")
    public native @MachineSizedFloat double getAngle();
    @Property(selector = "setAngle:")
    public native void setAngle(@MachineSizedFloat double v);
    @Property(selector = "magnitude")
    public native @MachineSizedFloat double getMagnitude();
    @Property(selector = "setMagnitude:")
    public native void setMagnitude(@MachineSizedFloat double v);
    @Property(selector = "pushDirection")
    public native @ByVal CGVector getPushDirection();
    @Property(selector = "setPushDirection:")
    public native void setPushDirection(@ByVal CGVector v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithItems:mode:")
    protected native @Pointer long init(List<UIDynamicItem> items, UIPushBehaviorMode mode);
    @Method(selector = "addItem:")
    public native void addItem(UIDynamicItem item);
    @Method(selector = "removeItem:")
    public native void removeItem(UIDynamicItem item);
    @Method(selector = "targetOffsetFromCenterForItem:")
    public native @ByVal UIOffset getTargetOffset(UIDynamicItem item);
    @Method(selector = "setTargetOffsetFromCenter:forItem:")
    public native void setTargetOffset(@ByVal UIOffset o, UIDynamicItem item);
    @Method(selector = "setAngle:magnitude:")
    public native void setAngleAndMagnitude(@MachineSizedFloat double angle, @MachineSizedFloat double magnitude);
    /*</methods>*/
}
