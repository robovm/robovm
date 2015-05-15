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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNShape/*</name>*/ 
    extends /*<extends>*/SCNGeometry/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SCNShapePtr extends Ptr<SCNShape, SCNShapePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNShape.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNShape() {}
    protected SCNShape(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "path")
    public native UIBezierPath getPath();
    @Property(selector = "setPath:")
    public native void setPath(UIBezierPath v);
    @Property(selector = "extrusionDepth")
    public native @MachineSizedFloat double getExtrusionDepth();
    @Property(selector = "setExtrusionDepth:")
    public native void setExtrusionDepth(@MachineSizedFloat double v);
    @Property(selector = "chamferMode")
    public native SCNChamferMode getChamferMode();
    @Property(selector = "setChamferMode:")
    public native void setChamferMode(SCNChamferMode v);
    @Property(selector = "chamferRadius")
    public native @MachineSizedFloat double getChamferRadius();
    @Property(selector = "setChamferRadius:")
    public native void setChamferRadius(@MachineSizedFloat double v);
    @Property(selector = "chamferProfile")
    public native UIBezierPath getChamferProfile();
    @Property(selector = "setChamferProfile:")
    public native void setChamferProfile(UIBezierPath v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "shapeWithPath:extrusionDepth:")
    public static native SCNShape create(UIBezierPath path, @MachineSizedFloat double extrusionDepth);
    /*</methods>*/
}
