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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNPhysicsWorld/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SCNPhysicsWorldPtr extends Ptr<SCNPhysicsWorld, SCNPhysicsWorldPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNPhysicsWorld.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNPhysicsWorld() {}
    protected SCNPhysicsWorld(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "gravity")
    public native @ByVal SCNVector3 getGravity();
    @Property(selector = "setGravity:")
    public native void setGravity(@ByVal SCNVector3 v);
    @Property(selector = "speed")
    public native @MachineSizedFloat double getSpeed();
    @Property(selector = "setSpeed:")
    public native void setSpeed(@MachineSizedFloat double v);
    @Property(selector = "timeStep")
    public native double getTimeStep();
    @Property(selector = "setTimeStep:")
    public native void setTimeStep(double v);
    @Property(selector = "contactDelegate")
    public native SCNPhysicsContactDelegate getContactDelegate();
    @Property(selector = "setContactDelegate:", strongRef = true)
    public native void setContactDelegate(SCNPhysicsContactDelegate v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "addBehavior:")
    public native void addBehavior(SCNPhysicsBehavior behavior);
    @Method(selector = "removeBehavior:")
    public native void removeBehavior(SCNPhysicsBehavior behavior);
    @Method(selector = "removeAllBehaviors")
    public native void removeAllBehaviors();
    @Method(selector = "allBehaviors")
    public native NSArray<SCNPhysicsBehavior> getAllBehaviors();
    @Method(selector = "rayTestWithSegmentFromPoint:toPoint:options:")
    public native NSArray<SCNHitTestResult> rayTestWithSegment(@ByVal SCNVector3 origin, @ByVal SCNVector3 dest, SCNPhysicsTestOptions options);
    @Method(selector = "contactTestBetweenBody:andBody:options:")
    public native NSArray<SCNHitTestResult> contactTestBetweenBodies(SCNPhysicsBody bodyA, SCNPhysicsBody bodyB, SCNPhysicsTestOptions options);
    @Method(selector = "contactTestWithBody:options:")
    public native NSArray<SCNHitTestResult> contactTestWithBody(SCNPhysicsBody body, SCNPhysicsTestOptions options);
    @Method(selector = "convexSweepTestWithShape:fromTransform:toTransform:options:")
    public native NSArray<SCNHitTestResult> convexSweepTestWithShape(SCNPhysicsShape shape, @ByVal SCNMatrix4 from, @ByVal SCNMatrix4 to, SCNPhysicsTestOptions options);
    @Method(selector = "updateCollisionPairs")
    public native void updateCollisionPairs();
    /*</methods>*/
}
