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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKPolygonObstacle/*</name>*/ 
    extends /*<extends>*/GKObstacle/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GKPolygonObstaclePtr extends Ptr<GKPolygonObstacle, GKPolygonObstaclePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKPolygonObstacle.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKPolygonObstacle() {}
    protected GKPolygonObstacle(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    public GKPolygonObstacle(VectorFloat2[] points) {
        super((SkipInit) null);
        VectorFloat2.VectorFloat2Ptr ptr = new VectorFloat2.VectorFloat2Ptr();
        ptr.set(points);
        initObject(init(ptr, points.length));
    }
    /*<properties>*/
    @Property(selector = "vertexCount")
    public native @MachineSizedUInt long getVertexCount();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithPoints:count:")
    protected native @Pointer long init(VectorFloat2.VectorFloat2Ptr points, @MachineSizedUInt long numPoints);
    @Method(selector = "vertexAtIndex:")
    public native VectorFloat2 getVertex(@MachineSizedUInt long index);
    /*</methods>*/
}
