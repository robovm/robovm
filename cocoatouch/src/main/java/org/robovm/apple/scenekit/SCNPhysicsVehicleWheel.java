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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNPhysicsVehicleWheel/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SCNPhysicsVehicleWheelPtr extends Ptr<SCNPhysicsVehicleWheel, SCNPhysicsVehicleWheelPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNPhysicsVehicleWheel.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNPhysicsVehicleWheel() {}
    protected SCNPhysicsVehicleWheel(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "node")
    public native SCNNode getNode();
    @Property(selector = "suspensionStiffness")
    public native @MachineSizedFloat double getSuspensionStiffness();
    @Property(selector = "setSuspensionStiffness:")
    public native void setSuspensionStiffness(@MachineSizedFloat double v);
    @Property(selector = "suspensionCompression")
    public native @MachineSizedFloat double getSuspensionCompression();
    @Property(selector = "setSuspensionCompression:")
    public native void setSuspensionCompression(@MachineSizedFloat double v);
    @Property(selector = "suspensionDamping")
    public native @MachineSizedFloat double getSuspensionDamping();
    @Property(selector = "setSuspensionDamping:")
    public native void setSuspensionDamping(@MachineSizedFloat double v);
    @Property(selector = "maximumSuspensionTravel")
    public native @MachineSizedFloat double getMaximumSuspensionTravel();
    @Property(selector = "setMaximumSuspensionTravel:")
    public native void setMaximumSuspensionTravel(@MachineSizedFloat double v);
    @Property(selector = "frictionSlip")
    public native @MachineSizedFloat double getFrictionSlip();
    @Property(selector = "setFrictionSlip:")
    public native void setFrictionSlip(@MachineSizedFloat double v);
    @Property(selector = "maximumSuspensionForce")
    public native @MachineSizedFloat double getMaximumSuspensionForce();
    @Property(selector = "setMaximumSuspensionForce:")
    public native void setMaximumSuspensionForce(@MachineSizedFloat double v);
    @Property(selector = "connectionPosition")
    public native @ByVal SCNVector3 getConnectionPosition();
    @Property(selector = "setConnectionPosition:")
    public native void setConnectionPosition(@ByVal SCNVector3 v);
    @Property(selector = "steeringAxis")
    public native @ByVal SCNVector3 getSteeringAxis();
    @Property(selector = "setSteeringAxis:")
    public native void setSteeringAxis(@ByVal SCNVector3 v);
    @Property(selector = "axle")
    public native @ByVal SCNVector3 getAxle();
    @Property(selector = "setAxle:")
    public native void setAxle(@ByVal SCNVector3 v);
    @Property(selector = "radius")
    public native @MachineSizedFloat double getRadius();
    @Property(selector = "setRadius:")
    public native void setRadius(@MachineSizedFloat double v);
    @Property(selector = "suspensionRestLength")
    public native @MachineSizedFloat double getSuspensionRestLength();
    @Property(selector = "setSuspensionRestLength:")
    public native void setSuspensionRestLength(@MachineSizedFloat double v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "wheelWithNode:")
    public static native SCNPhysicsVehicleWheel create(SCNNode node);
    /*</methods>*/
}
