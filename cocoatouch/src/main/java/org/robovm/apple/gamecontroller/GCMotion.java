/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.apple.gamecontroller;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("GameController") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GCMotion/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GCMotionPtr extends Ptr<GCMotion, GCMotionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GCMotion.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GCMotion() {}
    protected GCMotion(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "controller")
    public native GCController getController();
    @Property(selector = "valueChangedHandler")
    public native @Block VoidBlock2<GCGamepad, GCControllerElement> getValueChangedHandler();
    @Property(selector = "setValueChangedHandler:")
    public native void setValueChangedHandler(@Block VoidBlock2<GCGamepad, GCControllerElement> v);
    @Property(selector = "gravity")
    public native @ByVal GCAcceleration getGravity();
    @Property(selector = "userAcceleration")
    public native @ByVal GCAcceleration getUserAcceleration();
    @Property(selector = "attitude")
    public native @ByVal GCQuaternion getAttitude();
    @Property(selector = "rotationRate")
    public native @ByVal GCRotationRate getRotationRate();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
