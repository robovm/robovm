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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKGoal/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GKGoalPtr extends Ptr<GKGoal, GKGoalPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKGoal.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKGoal() {}
    protected GKGoal(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "goalToSeekAgent:")
    public static native GKGoal seekAgent(GKAgent agent);
    @Method(selector = "goalToFleeAgent:")
    public static native GKGoal fleeAgent(GKAgent agent);
    @Method(selector = "goalToAvoidObstacles:maxPredictionTime:")
    public static native GKGoal avoidObstacles(NSArray<GKObstacle> obstacles, double maxPredictionTime);
    @Method(selector = "goalToAvoidAgents:maxPredictionTime:")
    public static native GKGoal avoidAgents(NSArray<GKAgent> agents, double maxPredictionTime);
    @Method(selector = "goalToSeparateFromAgents:maxDistance:maxAngle:")
    public static native GKGoal separateFromAgents(NSArray<GKAgent> agents, float maxDistance, float maxAngle);
    @Method(selector = "goalToAlignWithAgents:maxDistance:maxAngle:")
    public static native GKGoal alignWithAgents(NSArray<GKAgent> agents, float maxDistance, float maxAngle);
    @Method(selector = "goalToCohereWithAgents:maxDistance:maxAngle:")
    public static native GKGoal cohereWithAgents(NSArray<GKAgent> agents, float maxDistance, float maxAngle);
    @Method(selector = "goalToReachTargetSpeed:")
    public static native GKGoal reachTargetSpeed(float targetSpeed);
    @Method(selector = "goalToWander:")
    public static native GKGoal wander(float speed);
    @Method(selector = "goalToInterceptAgent:maxPredictionTime:")
    public static native GKGoal interceptAgent(GKAgent target, double maxPredictionTime);
    @Method(selector = "goalToFollowPath:maxPredictionTime:forward:")
    public static native GKGoal followPath(GKPath path, double maxPredictionTime, boolean forward);
    @Method(selector = "goalToStayOnPath:maxPredictionTime:")
    public static native GKGoal stayOnPath(GKPath path, double maxPredictionTime);
    /*</methods>*/
}
