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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNTorus/*</name>*/ 
    extends /*<extends>*/SCNGeometry/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SCNTorusPtr extends Ptr<SCNTorus, SCNTorusPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNTorus.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNTorus() {}
    protected SCNTorus(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "ringRadius")
    public native @MachineSizedFloat double getRingRadius();
    @Property(selector = "setRingRadius:")
    public native void setRingRadius(@MachineSizedFloat double v);
    @Property(selector = "pipeRadius")
    public native @MachineSizedFloat double getPipeRadius();
    @Property(selector = "setPipeRadius:")
    public native void setPipeRadius(@MachineSizedFloat double v);
    @Property(selector = "ringSegmentCount")
    public native @MachineSizedSInt long getRingSegmentCount();
    @Property(selector = "setRingSegmentCount:")
    public native void setRingSegmentCount(@MachineSizedSInt long v);
    @Property(selector = "pipeSegmentCount")
    public native @MachineSizedSInt long getPipeSegmentCount();
    @Property(selector = "setPipeSegmentCount:")
    public native void setPipeSegmentCount(@MachineSizedSInt long v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "torusWithRingRadius:pipeRadius:")
    public static native SCNTorus create(@MachineSizedFloat double ringRadius, @MachineSizedFloat double pipeRadius);
    /*</methods>*/
}
