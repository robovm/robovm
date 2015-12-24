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
package org.robovm.apple.gameplaykit;

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
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("GameplayKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKAgent/*</name>*/ 
    extends /*<extends>*/GKComponent/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GKAgentPtr extends Ptr<GKAgent, GKAgentPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKAgent.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKAgent() {}
    protected GKAgent(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native GKAgentDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(GKAgentDelegate v);
    @Property(selector = "behavior")
    public native GKBehavior getBehavior();
    @Property(selector = "setBehavior:")
    public native void setBehavior(GKBehavior v);
    @Property(selector = "mass")
    public native float getMass();
    @Property(selector = "setMass:")
    public native void setMass(float v);
    @Property(selector = "radius")
    public native float getRadius();
    @Property(selector = "setRadius:")
    public native void setRadius(float v);
    @Property(selector = "speed")
    public native float getSpeed();
    @Property(selector = "maxAcceleration")
    public native float getMaxAcceleration();
    @Property(selector = "setMaxAcceleration:")
    public native void setMaxAcceleration(float v);
    @Property(selector = "maxSpeed")
    public native float getMaxSpeed();
    @Property(selector = "setMaxSpeed:")
    public native void setMaxSpeed(float v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
