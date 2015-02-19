/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.scenekit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.spritekit.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("SceneKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNPhysicsVehicle/*</name>*/ 
    extends /*<extends>*/SCNPhysicsBehavior/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SCNPhysicsVehiclePtr extends Ptr<SCNPhysicsVehicle, SCNPhysicsVehiclePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNPhysicsVehicle.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNPhysicsVehicle() {}
    protected SCNPhysicsVehicle(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "speedInKilometersPerHour")
    public native @MachineSizedFloat double getSpeedInKilometersPerHour();
    @Property(selector = "wheels")
    public native NSArray<SCNPhysicsVehicleWheel> getWheels();
    @Property(selector = "chassisBody")
    public native SCNPhysicsBody getChassisBody();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "applyEngineForce:forWheelAtIndex:")
    public native void applyEngineForceForWheel(@MachineSizedFloat double value, @MachineSizedSInt long index);
    @Method(selector = "setSteeringAngle:forWheelAtIndex:")
    public native void setSteeringAngleForWheel(@MachineSizedFloat double value, @MachineSizedSInt long index);
    @Method(selector = "applyBrakingForce:forWheelAtIndex:")
    public native void applyBrakingForceForWheel(@MachineSizedFloat double value, @MachineSizedSInt long index);
    @Method(selector = "vehicleWithChassisBody:wheels:")
    public static native SCNPhysicsVehicle create(SCNPhysicsBody chassisBody, NSArray<SCNPhysicsVehicleWheel> wheels);
    /*</methods>*/
}
