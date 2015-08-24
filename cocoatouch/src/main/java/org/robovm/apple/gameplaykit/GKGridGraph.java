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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKGridGraph/*</name>*/ 
    extends /*<extends>*/GKGraph/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GKGridGraphPtr extends Ptr<GKGridGraph, GKGridGraphPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKGridGraph.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKGridGraph() {}
    protected GKGridGraph(SkipInit skipInit) { super(skipInit); }
    public GKGridGraph(VectorInt2 position, int width, int height, boolean diagonalsAllowed) { super((SkipInit) null); initObject(init(position, width, height, diagonalsAllowed)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "gridOrigin")
    public native VectorInt2 getGridOrigin();
    @Property(selector = "gridWidth")
    public native @MachineSizedUInt long getGridWidth();
    @Property(selector = "gridHeight")
    public native @MachineSizedUInt long getGridHeight();
    @Property(selector = "diagonalsAllowed")
    public native boolean areDiagonalsAllowed();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initFromGridStartingAt:width:height:diagonalsAllowed:")
    protected native @Pointer long init(VectorInt2 position, int width, int height, boolean diagonalsAllowed);
    @Method(selector = "nodeAtGridPosition:")
    public native GKGridGraphNode getNodeAtGridPosition(VectorInt2 position);
    @Method(selector = "connectNodeToAdjacentNodes:")
    public native void connectNodeToAdjacentNodes(GKGridGraphNode node);
    /*</methods>*/
}
