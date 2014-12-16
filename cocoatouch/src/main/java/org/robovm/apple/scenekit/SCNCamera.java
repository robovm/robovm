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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNCamera/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements SCNAnimatable, SCNTechniqueSupport/*</implements>*/ {

    /*<ptr>*/public static class SCNCameraPtr extends Ptr<SCNCamera, SCNCameraPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNCamera.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNCamera() {}
    protected SCNCamera(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "setName:")
    public native void setName(String v);
    @Property(selector = "xFov")
    public native double getXFov();
    @Property(selector = "setXFov:")
    public native void setXFov(double v);
    @Property(selector = "yFov")
    public native double getYFov();
    @Property(selector = "setYFov:")
    public native void setYFov(double v);
    @Property(selector = "zNear")
    public native double getZNear();
    @Property(selector = "setZNear:")
    public native void setZNear(double v);
    @Property(selector = "zFar")
    public native double getZFar();
    @Property(selector = "setZFar:")
    public native void setZFar(double v);
    @Property(selector = "automaticallyAdjustsZRange")
    public native boolean isAutomaticallyAdjustsZRange();
    @Property(selector = "setAutomaticallyAdjustsZRange:")
    public native void setAutomaticallyAdjustsZRange(boolean v);
    @Property(selector = "usesOrthographicProjection")
    public native boolean isUsesOrthographicProjection();
    @Property(selector = "setUsesOrthographicProjection:")
    public native void setUsesOrthographicProjection(boolean v);
    @Property(selector = "orthographicScale")
    public native double getOrthographicScale();
    @Property(selector = "setOrthographicScale:")
    public native void setOrthographicScale(double v);
    @Property(selector = "focalDistance")
    public native @MachineSizedFloat double getFocalDistance();
    @Property(selector = "setFocalDistance:")
    public native void setFocalDistance(@MachineSizedFloat double v);
    @Property(selector = "focalSize")
    public native @MachineSizedFloat double getFocalSize();
    @Property(selector = "setFocalSize:")
    public native void setFocalSize(@MachineSizedFloat double v);
    @Property(selector = "focalBlurRadius")
    public native @MachineSizedFloat double getFocalBlurRadius();
    @Property(selector = "setFocalBlurRadius:")
    public native void setFocalBlurRadius(@MachineSizedFloat double v);
    @Property(selector = "aperture")
    public native @MachineSizedFloat double getAperture();
    @Property(selector = "setAperture:")
    public native void setAperture(@MachineSizedFloat double v);
    @Property(selector = "categoryBitMask")
    public native @MachineSizedUInt long getCategoryBitMask();
    @Property(selector = "setCategoryBitMask:")
    public native void setCategoryBitMask(@MachineSizedUInt long v);
    @Property(selector = "technique")
    public native SCNTechnique getTechnique();
    @Property(selector = "setTechnique:")
    public native void setTechnique(SCNTechnique v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "projectionTransform")
    public native @ByVal SCNMatrix4 getProjectionTransform();
    @Method(selector = "setProjectionTransform:")
    public native void setProjectionTransform(@ByVal SCNMatrix4 projectionTransform);
    @Method(selector = "camera")
    public static native SCNCamera create();
    @Method(selector = "addAnimation:forKey:")
    public native void addAnimation(CAAnimation animation, String key);
    @Method(selector = "removeAllAnimations")
    public native void removeAllAnimations();
    @Method(selector = "removeAnimationForKey:")
    public native void removeAnimation(String key);
    @Method(selector = "animationKeys")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getAnimationKeys();
    @Method(selector = "animationForKey:")
    public native CAAnimation getAnimation(String key);
    @Method(selector = "pauseAnimationForKey:")
    public native void pauseAnimation(String key);
    @Method(selector = "resumeAnimationForKey:")
    public native void resumeAnimation(String key);
    @Method(selector = "isAnimationForKeyPaused:")
    public native boolean isAnimationPaused(String key);
    @Method(selector = "removeAnimationForKey:fadeOutDuration:")
    public native void removeAnimation(String key, @MachineSizedFloat double duration);
    /*</methods>*/
}
