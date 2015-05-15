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
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("SceneKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNTube/*</name>*/ 
    extends /*<extends>*/SCNGeometry/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SCNTubePtr extends Ptr<SCNTube, SCNTubePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNTube.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNTube() {}
    protected SCNTube(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "innerRadius")
    public native @MachineSizedFloat double getInnerRadius();
    @Property(selector = "setInnerRadius:")
    public native void setInnerRadius(@MachineSizedFloat double v);
    @Property(selector = "outerRadius")
    public native @MachineSizedFloat double getOuterRadius();
    @Property(selector = "setOuterRadius:")
    public native void setOuterRadius(@MachineSizedFloat double v);
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
    @Method(selector = "tubeWithInnerRadius:outerRadius:height:")
    public static native SCNTube create(@MachineSizedFloat double innerRadius, @MachineSizedFloat double outerRadius, @MachineSizedFloat double height);
    /*</methods>*/
}
