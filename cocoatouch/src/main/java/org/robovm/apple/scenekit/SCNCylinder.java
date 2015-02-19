/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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

/*</javadoc>*/
/*<annotations>*/@Library("SceneKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNCylinder/*</name>*/ 
    extends /*<extends>*/SCNGeometry/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SCNCylinderPtr extends Ptr<SCNCylinder, SCNCylinderPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNCylinder.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNCylinder() {}
    protected SCNCylinder(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "radius")
    public native @MachineSizedFloat double getRadius();
    @Property(selector = "setRadius:")
    public native void setRadius(@MachineSizedFloat double v);
    @Property(selector = "height")
    public native @MachineSizedFloat double getHeight();
    @Property(selector = "setHeight:")
    public native void setHeight(@MachineSizedFloat double v);
    @Property(selector = "radialSegmentCount")
    public native @MachineSizedSInt long getRadialSegmentCount();
    @Property(selector = "setRadialSegmentCount:")
    public native void setRadialSegmentCount(@MachineSizedSInt long v);
    @Property(selector = "heightSegmentCount")
    public native @MachineSizedSInt long getHeightSegmentCount();
    @Property(selector = "setHeightSegmentCount:")
    public native void setHeightSegmentCount(@MachineSizedSInt long v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "cylinderWithRadius:height:")
    public static native SCNCylinder create(@MachineSizedFloat double radius, @MachineSizedFloat double height);
    /*</methods>*/
}
