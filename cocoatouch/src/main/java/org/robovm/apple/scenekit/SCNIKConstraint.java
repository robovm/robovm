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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNIKConstraint/*</name>*/ 
    extends /*<extends>*/SCNConstraint/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SCNIKConstraintPtr extends Ptr<SCNIKConstraint, SCNIKConstraintPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNIKConstraint.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNIKConstraint() {}
    protected SCNIKConstraint(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "chainRootNode")
    public native SCNNode getChainRootNode();
    @Property(selector = "targetPosition")
    public native @ByVal SCNVector3 getTargetPosition();
    @Property(selector = "setTargetPosition:")
    public native void setTargetPosition(@ByVal SCNVector3 v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setMaxAllowedRotationAngle:forJoint:")
    public native void setMaxAllowedRotationAngleForJoint(@MachineSizedFloat double angle, SCNNode node);
    @Method(selector = "maxAllowedRotationAngleForJoint:")
    public native @MachineSizedFloat double getMaxAllowedRotationAngleForJoint(SCNNode node);
    @Method(selector = "inverseKinematicsConstraintWithChainRootNode:")
    public static native SCNIKConstraint create(SCNNode chainRootNode);
    /*</methods>*/
}
