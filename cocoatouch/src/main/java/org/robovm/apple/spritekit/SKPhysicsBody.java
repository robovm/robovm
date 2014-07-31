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
package org.robovm.apple.spritekit;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.avfoundation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("SpriteKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKPhysicsBody/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class SKPhysicsBodyPtr extends Ptr<SKPhysicsBody, SKPhysicsBodyPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKPhysicsBody.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKPhysicsBody() {}
    protected SKPhysicsBody(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "isDynamic")
    public native boolean isDynamic();
    @Property(selector = "setDynamic:")
    public native void setDynamic(boolean v);
    @Property(selector = "usesPreciseCollisionDetection")
    public native boolean isUsesPreciseCollisionDetection();
    @Property(selector = "setUsesPreciseCollisionDetection:")
    public native void setUsesPreciseCollisionDetection(boolean v);
    @Property(selector = "allowsRotation")
    public native boolean isAllowsRotation();
    @Property(selector = "setAllowsRotation:")
    public native void setAllowsRotation(boolean v);
    @Property(selector = "isResting")
    public native boolean isResting();
    @Property(selector = "setResting:")
    public native void setResting(boolean v);
    @Property(selector = "friction")
    public native @MachineSizedFloat double getFriction();
    @Property(selector = "setFriction:")
    public native void setFriction(@MachineSizedFloat double v);
    @Property(selector = "restitution")
    public native @MachineSizedFloat double getRestitution();
    @Property(selector = "setRestitution:")
    public native void setRestitution(@MachineSizedFloat double v);
    @Property(selector = "linearDamping")
    public native @MachineSizedFloat double getLinearDamping();
    @Property(selector = "setLinearDamping:")
    public native void setLinearDamping(@MachineSizedFloat double v);
    @Property(selector = "angularDamping")
    public native @MachineSizedFloat double getAngularDamping();
    @Property(selector = "setAngularDamping:")
    public native void setAngularDamping(@MachineSizedFloat double v);
    @Property(selector = "density")
    public native @MachineSizedFloat double getDensity();
    @Property(selector = "setDensity:")
    public native void setDensity(@MachineSizedFloat double v);
    @Property(selector = "mass")
    public native @MachineSizedFloat double getMass();
    @Property(selector = "setMass:")
    public native void setMass(@MachineSizedFloat double v);
    @Property(selector = "area")
    public native @MachineSizedFloat double getArea();
    @Property(selector = "affectedByGravity")
    public native boolean isAffectedByGravity();
    @Property(selector = "setAffectedByGravity:")
    public native void setAffectedByGravity(boolean v);
    @Property(selector = "categoryBitMask")
    public native int getCategoryBitMask();
    @Property(selector = "setCategoryBitMask:")
    public native void setCategoryBitMask(int v);
    @Property(selector = "collisionBitMask")
    public native int getCollisionBitMask();
    @Property(selector = "setCollisionBitMask:")
    public native void setCollisionBitMask(int v);
    @Property(selector = "contactTestBitMask")
    public native int getContactTestBitMask();
    @Property(selector = "setContactTestBitMask:")
    public native void setContactTestBitMask(int v);
    @Property(selector = "joints")
    public native NSArray<SKPhysicsJoint> getJoints();
    @Property(selector = "node")
    public native SKNode getNode();
    @Property(selector = "velocity")
    public native @ByVal CGVector getVelocity();
    @Property(selector = "setVelocity:")
    public native void setVelocity(@ByVal CGVector v);
    @Property(selector = "angularVelocity")
    public native @MachineSizedFloat double getAngularVelocity();
    @Property(selector = "setAngularVelocity:")
    public native void setAngularVelocity(@MachineSizedFloat double v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "applyForce:")
    public native void applyForce(@ByVal CGVector force);
    @Method(selector = "applyForce:atPoint:")
    public native void applyForce(@ByVal CGVector force, @ByVal CGPoint point);
    @Method(selector = "applyTorque:")
    public native void applyTorque(@MachineSizedFloat double torque);
    @Method(selector = "applyImpulse:")
    public native void applyImpulse(@ByVal CGVector impulse);
    @Method(selector = "applyImpulse:atPoint:")
    public native void applyImpulse$atPoint$(@ByVal CGVector impulse, @ByVal CGPoint point);
    @Method(selector = "applyAngularImpulse:")
    public native void applyAngularImpulse(@MachineSizedFloat double impulse);
    @Method(selector = "allContactedBodies")
    public native NSArray<SKPhysicsBody> getAllContactedBodies();
    @Method(selector = "bodyWithCircleOfRadius:")
    public static native SKPhysicsBody createCircle(@MachineSizedFloat double r);
    @Method(selector = "bodyWithCircleOfRadius:center:")
    public static native SKPhysicsBody createCircle(@MachineSizedFloat double r, @ByVal CGPoint center);
    @Method(selector = "bodyWithRectangleOfSize:")
    public static native SKPhysicsBody createRectangle(@ByVal CGSize s);
    @Method(selector = "bodyWithRectangleOfSize:center:")
    public static native SKPhysicsBody createRectangle(@ByVal CGSize s, @ByVal CGPoint center);
    @Method(selector = "bodyWithPolygonFromPath:")
    public static native SKPhysicsBody createPolygon(CGPath path);
    @Method(selector = "bodyWithEdgeFromPoint:toPoint:")
    public static native SKPhysicsBody createEdge(@ByVal CGPoint p1, @ByVal CGPoint p2);
    @Method(selector = "bodyWithEdgeChainFromPath:")
    public static native SKPhysicsBody createEdgeChain(CGPath path);
    @Method(selector = "bodyWithEdgeLoopFromPath:")
    public static native SKPhysicsBody createEdgeLoop(CGPath path);
    @Method(selector = "bodyWithEdgeLoopFromRect:")
    public static native SKPhysicsBody createEdgeLoop(@ByVal CGRect rect);
    @Method(selector = "bodyWithBodies:")
    public static native SKPhysicsBody create(NSArray<SKPhysicsBody> bodies);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
