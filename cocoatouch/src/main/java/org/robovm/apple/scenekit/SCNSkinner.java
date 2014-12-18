/*
 * Copyright (C) 2014 Trillian Mobile AB
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNSkinner/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SCNSkinnerPtr extends Ptr<SCNSkinner, SCNSkinnerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNSkinner.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNSkinner() {}
    protected SCNSkinner(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "skeleton")
    public native SCNNode getSkeleton();
    @Property(selector = "setSkeleton:")
    public native void setSkeleton(SCNNode v);
    @Property(selector = "baseGeometry")
    public native SCNGeometry getBaseGeometry();
    @Property(selector = "setBaseGeometry:")
    public native void setBaseGeometry(SCNGeometry v);
    @Property(selector = "baseGeometryBindTransform")
    public native @ByVal SCNMatrix4 getBaseGeometryBindTransform();
    @Property(selector = "setBaseGeometryBindTransform:")
    public native void setBaseGeometryBindTransform(@ByVal SCNMatrix4 v);
    @Property(selector = "boneInverseBindTransforms")
    public native @org.robovm.rt.bro.annotation.Marshaler(SCNMatrix4.AsListMarshaler.class) List<SCNMatrix4> getBoneInverseBindTransforms();
    @Property(selector = "bones")
    public native NSArray<SCNNode> getBones();
    @Property(selector = "boneWeights")
    public native SCNGeometrySource getBoneWeights();
    @Property(selector = "boneIndices")
    public native SCNGeometrySource getBoneIndices();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "skinnerWithBaseGeometry:bones:boneInverseBindTransforms:boneWeights:boneIndices:")
    public static native SCNSkinner create(SCNGeometry baseGeometry, NSArray<SCNNode> bones, @org.robovm.rt.bro.annotation.Marshaler(SCNMatrix4.AsListMarshaler.class) List<SCNMatrix4> boneInverseBindTransforms, SCNGeometrySource boneWeights, SCNGeometrySource boneIndices);
    /*</methods>*/
}
