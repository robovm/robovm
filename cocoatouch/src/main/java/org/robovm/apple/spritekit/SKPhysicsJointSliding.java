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
import org.robovm.apple.glkit.*;
import org.robovm.apple.scenekit.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("SpriteKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKPhysicsJointSliding/*</name>*/ 
    extends /*<extends>*/SKPhysicsJoint/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SKPhysicsJointSlidingPtr extends Ptr<SKPhysicsJointSliding, SKPhysicsJointSlidingPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKPhysicsJointSliding.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKPhysicsJointSliding() {}
    protected SKPhysicsJointSliding(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "shouldEnableLimits")
    public native boolean shouldEnableLimits();
    @Property(selector = "setShouldEnableLimits:")
    public native void setShouldEnableLimits(boolean v);
    @Property(selector = "lowerDistanceLimit")
    public native @MachineSizedFloat double getLowerDistanceLimit();
    @Property(selector = "setLowerDistanceLimit:")
    public native void setLowerDistanceLimit(@MachineSizedFloat double v);
    @Property(selector = "upperDistanceLimit")
    public native @MachineSizedFloat double getUpperDistanceLimit();
    @Property(selector = "setUpperDistanceLimit:")
    public native void setUpperDistanceLimit(@MachineSizedFloat double v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "jointWithBodyA:bodyB:anchor:axis:")
    public static native SKPhysicsJointSliding create(SKPhysicsBody bodyA, SKPhysicsBody bodyB, @ByVal CGPoint anchor, @ByVal CGVector axis);
    /*</methods>*/
}
