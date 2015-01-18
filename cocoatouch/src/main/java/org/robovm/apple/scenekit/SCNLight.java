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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SCNLight/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements SCNAnimatable, SCNTechniqueSupport/*</implements>*/ {

    /*<ptr>*/public static class SCNLightPtr extends Ptr<SCNLight, SCNLightPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SCNLight.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SCNLight() {}
    protected SCNLight(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "type")
    public native SCNLightType getType();
    @Property(selector = "setType:")
    public native void setType(SCNLightType v);
    @Property(selector = "color")
    public native UIColor getColor();
    @Property(selector = "setColor:")
    public native void setColor(UIColor v);
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "setName:")
    public native void setName(String v);
    @Property(selector = "castsShadow")
    public native boolean castsShadow();
    @Property(selector = "setCastsShadow:")
    public native void setCastsShadow(boolean v);
    @Property(selector = "shadowColor")
    public native NSObject getShadowColor();
    @Property(selector = "setShadowColor:")
    public native void setShadowColor(NSObject v);
    @Property(selector = "shadowRadius")
    public native @MachineSizedFloat double getShadowRadius();
    @Property(selector = "setShadowRadius:")
    public native void setShadowRadius(@MachineSizedFloat double v);
    @Property(selector = "shadowMapSize")
    public native @ByVal CGSize getShadowMapSize();
    @Property(selector = "setShadowMapSize:")
    public native void setShadowMapSize(@ByVal CGSize v);
    @Property(selector = "shadowSampleCount")
    public native @MachineSizedUInt long getShadowSampleCount();
    @Property(selector = "setShadowSampleCount:")
    public native void setShadowSampleCount(@MachineSizedUInt long v);
    @Property(selector = "shadowMode")
    public native SCNShadowMode getShadowMode();
    @Property(selector = "setShadowMode:")
    public native void setShadowMode(SCNShadowMode v);
    @Property(selector = "shadowBias")
    public native @MachineSizedFloat double getShadowBias();
    @Property(selector = "setShadowBias:")
    public native void setShadowBias(@MachineSizedFloat double v);
    @Property(selector = "orthographicScale")
    public native @MachineSizedFloat double getOrthographicScale();
    @Property(selector = "setOrthographicScale:")
    public native void setOrthographicScale(@MachineSizedFloat double v);
    @Property(selector = "zNear")
    public native @MachineSizedFloat double getZNear();
    @Property(selector = "setZNear:")
    public native void setZNear(@MachineSizedFloat double v);
    @Property(selector = "zFar")
    public native @MachineSizedFloat double getZFar();
    @Property(selector = "setZFar:")
    public native void setZFar(@MachineSizedFloat double v);
    @Property(selector = "attenuationStartDistance")
    public native @MachineSizedFloat double getAttenuationStartDistance();
    @Property(selector = "setAttenuationStartDistance:")
    public native void setAttenuationStartDistance(@MachineSizedFloat double v);
    @Property(selector = "attenuationEndDistance")
    public native @MachineSizedFloat double getAttenuationEndDistance();
    @Property(selector = "setAttenuationEndDistance:")
    public native void setAttenuationEndDistance(@MachineSizedFloat double v);
    @Property(selector = "attenuationFalloffExponent")
    public native @MachineSizedFloat double getAttenuationFalloffExponent();
    @Property(selector = "setAttenuationFalloffExponent:")
    public native void setAttenuationFalloffExponent(@MachineSizedFloat double v);
    @Property(selector = "spotInnerAngle")
    public native @MachineSizedFloat double getSpotInnerAngle();
    @Property(selector = "setSpotInnerAngle:")
    public native void setSpotInnerAngle(@MachineSizedFloat double v);
    @Property(selector = "spotOuterAngle")
    public native @MachineSizedFloat double getSpotOuterAngle();
    @Property(selector = "setSpotOuterAngle:")
    public native void setSpotOuterAngle(@MachineSizedFloat double v);
    @Property(selector = "gobo")
    public native SCNMaterialProperty getGobo();
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
    @Method(selector = "light")
    public static native SCNLight create();
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
