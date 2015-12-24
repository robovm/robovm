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
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("GameplayKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKPath/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GKPathPtr extends Ptr<GKPath, GKPathPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKPath.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKPath() {}
    protected GKPath(SkipInit skipInit) { super(skipInit); }
    public GKPath(NSArray<GKGraphNode2D> graphNodes, float radius) { super((SkipInit) null); initObject(init(graphNodes, radius)); }
    /*</constructors>*/
    public GKPath(VectorFloat2[] points, float radius, boolean cyclical) {
        super((SkipInit) null);
        VectorFloat2.VectorFloat2Ptr ptr = new VectorFloat2.VectorFloat2Ptr();
        ptr.set(points);
        initObject(init(ptr, points.length, radius, cyclical));
    }
    /*<properties>*/
    @Property(selector = "radius")
    public native float getRadius();
    @Property(selector = "setRadius:")
    public native void setRadius(float v);
    @Property(selector = "isCyclical")
    public native boolean isCyclical();
    @Property(selector = "setCyclical:")
    public native void setCyclical(boolean v);
    @Property(selector = "numPoints")
    public native @MachineSizedUInt long getNumPoints();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithPoints:count:radius:cyclical:")
    protected native @Pointer long init(VectorFloat2.VectorFloat2Ptr points, @MachineSizedUInt long count, float radius, boolean cyclical);
    @Method(selector = "initWithGraphNodes:radius:")
    protected native @Pointer long init(NSArray<GKGraphNode2D> graphNodes, float radius);
    @Method(selector = "pointAtIndex:")
    public native VectorFloat2 getPoint(@MachineSizedUInt long index);
    /*</methods>*/
}
