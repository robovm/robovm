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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKObstacleGraph/*</name>*/ 
    extends /*<extends>*/GKGraph/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GKObstacleGraphPtr extends Ptr<GKObstacleGraph, GKObstacleGraphPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKObstacleGraph.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKObstacleGraph() {}
    protected GKObstacleGraph(SkipInit skipInit) { super(skipInit); }
    public GKObstacleGraph(NSArray<GKPolygonObstacle> obstacles, float bufferRadius) { super((SkipInit) null); initObject(init(obstacles, bufferRadius)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "obstacles")
    public native NSArray<GKPolygonObstacle> getObstacles();
    @Property(selector = "bufferRadius")
    public native float getBufferRadius();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithObstacles:bufferRadius:")
    protected native @Pointer long init(NSArray<GKPolygonObstacle> obstacles, float bufferRadius);
    @Method(selector = "connectNodeUsingObstacles:")
    public native void connectNode(GKGraphNode2D node);
    @Method(selector = "connectNodeUsingObstacles:ignoringObstacles:")
    public native void connectNodeIgnoringObstacles(GKGraphNode2D node, NSArray<?> obstaclesToIgnore);
    @Method(selector = "connectNodeUsingObstacles:ignoringBufferRadiusOfObstacles:")
    public native void connectNodeIgnoringBufferRadiusOfObstacles(GKGraphNode2D node, NSArray<GKPolygonObstacle> obstaclesBufferRadiusToIgnore);
    @Method(selector = "addObstacles:")
    public native void addObstacles(NSArray<GKPolygonObstacle> obstacles);
    @Method(selector = "removeObstacles:")
    public native void removeObstacles(NSArray<GKPolygonObstacle> obstacles);
    @Method(selector = "removeAllObstacles")
    public native void removeAllObstacles();
    @Method(selector = "nodesForObstacle:")
    public native NSArray<GKGraphNode2D> getNodesForObstacle(GKPolygonObstacle obstacle);
    @Method(selector = "lockConnectionFromNode:toNode:")
    public native void lockConnectionBetweenNodes(GKGraphNode2D startNode, GKGraphNode2D endNode);
    @Method(selector = "unlockConnectionFromNode:toNode:")
    public native void unlockConnectionBetweenNodes(GKGraphNode2D startNode, GKGraphNode2D endNode);
    @Method(selector = "isConnectionLockedFromNode:toNode:")
    public native boolean isConnectionLockedBetweenNodes(GKGraphNode2D startNode, GKGraphNode2D endNode);
    /*</methods>*/
}
