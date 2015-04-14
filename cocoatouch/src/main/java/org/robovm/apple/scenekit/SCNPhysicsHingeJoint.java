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
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("SceneKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNPhysicsHingeJoint/*</name>*/ 
    extends /*<extends>*/SCNPhysicsBehavior/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SCNPhysicsHingeJointPtr extends Ptr<SCNPhysicsHingeJoint, SCNPhysicsHingeJointPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNPhysicsHingeJoint.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNPhysicsHingeJoint() {}
    protected SCNPhysicsHingeJoint(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "bodyA")
    public native SCNPhysicsBody getBodyA();
    @Property(selector = "axisA")
    public native @ByVal SCNVector3 getAxisA();
    @Property(selector = "setAxisA:")
    public native void setAxisA(@ByVal SCNVector3 v);
    @Property(selector = "anchorA")
    public native @ByVal SCNVector3 getAnchorA();
    @Property(selector = "setAnchorA:")
    public native void setAnchorA(@ByVal SCNVector3 v);
    @Property(selector = "bodyB")
    public native SCNPhysicsBody getBodyB();
    @Property(selector = "axisB")
    public native @ByVal SCNVector3 getAxisB();
    @Property(selector = "setAxisB:")
    public native void setAxisB(@ByVal SCNVector3 v);
    @Property(selector = "anchorB")
    public native @ByVal SCNVector3 getAnchorB();
    @Property(selector = "setAnchorB:")
    public native void setAnchorB(@ByVal SCNVector3 v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "jointWithBodyA:axisA:anchorA:bodyB:axisB:anchorB:")
    public static native SCNPhysicsHingeJoint create(SCNPhysicsBody bodyA, @ByVal SCNVector3 axisA, @ByVal SCNVector3 anchorA, SCNPhysicsBody bodyB, @ByVal SCNVector3 axisB, @ByVal SCNVector3 anchorB);
    @Method(selector = "jointWithBody:axis:anchor:")
    public static native SCNPhysicsHingeJoint create(SCNPhysicsBody body, @ByVal SCNVector3 axis, @ByVal SCNVector3 anchor);
    /*</methods>*/
}
