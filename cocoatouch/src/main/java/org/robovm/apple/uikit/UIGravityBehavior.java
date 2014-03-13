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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIGravityBehavior/*</name>*/ 
    extends /*<extends>*/UIDynamicBehavior/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIGravityBehaviorPtr extends Ptr<UIGravityBehavior, UIGravityBehaviorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIGravityBehavior.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIGravityBehavior() {}
    protected UIGravityBehavior(SkipInit skipInit) { super(skipInit); }
    public UIGravityBehavior(NSArray<?> items) { super((SkipInit) null); initObject(initWithItems$(items)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "items")
    public native NSArray<?> getItems();
    @Property(selector = "gravityDirection")
    public native @ByVal CGVector getGravityDirection();
    @Property(selector = "setGravityDirection:")
    public native void setGravityDirection(@ByVal CGVector v);
    @Property(selector = "angle")
    public native @MachineSizedFloat double getAngle();
    @Property(selector = "setAngle:")
    public native void setAngle(@MachineSizedFloat double v);
    @Property(selector = "magnitude")
    public native @MachineSizedFloat double getMagnitude();
    @Property(selector = "setMagnitude:")
    public native void setMagnitude(@MachineSizedFloat double v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithItems:")
    protected native @Pointer long initWithItems$(NSArray<?> items);
    @Method(selector = "addItem:")
    public native void addItem$(UIDynamicItem item);
    @Method(selector = "removeItem:")
    public native void removeItem$(UIDynamicItem item);
    @Method(selector = "setAngle:magnitude:")
    public native void setAngle$magnitude$(@MachineSizedFloat double angle, @MachineSizedFloat double magnitude);
    /*</methods>*/
}
