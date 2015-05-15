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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("GameController") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GCExtendedGamepad/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GCExtendedGamepadPtr extends Ptr<GCExtendedGamepad, GCExtendedGamepadPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GCExtendedGamepad.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GCExtendedGamepad() {}
    protected GCExtendedGamepad(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "controller")
    public native GCController getController();
    @Property(selector = "valueChangedHandler")
    public native @Block VoidBlock2<GCExtendedGamepad, GCControllerElement> getValueChangedHandler();
    @Property(selector = "setValueChangedHandler:")
    public native void setValueChangedHandler(@Block VoidBlock2<GCExtendedGamepad, GCControllerElement> v);
    @Property(selector = "dpad")
    public native GCControllerDirectionPad getDpad();
    @Property(selector = "buttonA")
    public native GCControllerButtonInput getButtonA();
    @Property(selector = "buttonB")
    public native GCControllerButtonInput getButtonB();
    @Property(selector = "buttonX")
    public native GCControllerButtonInput getButtonX();
    @Property(selector = "buttonY")
    public native GCControllerButtonInput getButtonY();
    @Property(selector = "leftThumbstick")
    public native GCControllerDirectionPad getLeftThumbstick();
    @Property(selector = "rightThumbstick")
    public native GCControllerDirectionPad getRightThumbstick();
    @Property(selector = "leftShoulder")
    public native GCControllerButtonInput getLeftShoulder();
    @Property(selector = "rightShoulder")
    public native GCControllerButtonInput getRightShoulder();
    @Property(selector = "leftTrigger")
    public native GCControllerButtonInput getLeftTrigger();
    @Property(selector = "rightTrigger")
    public native GCControllerButtonInput getRightTrigger();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "saveSnapshot")
    public native GCExtendedGamepadSnapshot saveSnapshot();
    /*</methods>*/
}
