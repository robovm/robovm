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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKGraph/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GKGraphPtr extends Ptr<GKGraph, GKGraphPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKGraph.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKGraph() {}
    protected GKGraph(SkipInit skipInit) { super(skipInit); }
    public GKGraph(NSArray<GKGraph> nodes) { super((SkipInit) null); initObject(init(nodes)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "nodes")
    public native NSArray<GKGraph> getNodes();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithNodes:")
    protected native @Pointer long init(NSArray<GKGraph> nodes);
    @Method(selector = "connectNodeToLowestCostNode:bidirectional:")
    public native void connectNodeToLowestCostNode(GKGraphNode node, boolean bidirectional);
    @Method(selector = "removeNodes:")
    public native void removeNodes(NSArray<GKGraph> nodes);
    @Method(selector = "addNodes:")
    public native void addNodes(NSArray<GKGraph> nodes);
    @Method(selector = "findPathFromNode:toNode:")
    public native NSArray<GKGraphNode> findPathBetweenNodes(GKGraphNode startNode, GKGraphNode endNode);
    /*</methods>*/
}
