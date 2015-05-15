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
package org.robovm.apple.spritekit;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.avfoundation.*;
import org.robovm.apple.glkit.*;
import org.robovm.apple.scenekit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("SpriteKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKFieldNode/*</name>*/ 
    extends /*<extends>*/SKNode/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SKFieldNodePtr extends Ptr<SKFieldNode, SKFieldNodePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKFieldNode.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKFieldNode() {}
    protected SKFieldNode(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "region")
    public native SKRegion getRegion();
    @Property(selector = "setRegion:")
    public native void setRegion(SKRegion v);
    @Property(selector = "strength")
    public native float getStrength();
    @Property(selector = "setStrength:")
    public native void setStrength(float v);
    @Property(selector = "falloff")
    public native float getFalloff();
    @Property(selector = "setFalloff:")
    public native void setFalloff(float v);
    @Property(selector = "minimumRadius")
    public native float getMinimumRadius();
    @Property(selector = "setMinimumRadius:")
    public native void setMinimumRadius(float v);
    @Property(selector = "isEnabled")
    public native boolean isEnabled();
    @Property(selector = "setEnabled:")
    public native void setEnabled(boolean v);
    @Property(selector = "isExclusive")
    public native boolean isExclusive();
    @Property(selector = "setExclusive:")
    public native void setExclusive(boolean v);
    @Property(selector = "categoryBitMask")
    public native int getCategoryBitMask();
    @Property(selector = "setCategoryBitMask:")
    public native void setCategoryBitMask(int v);
    @Property(selector = "direction")
    public native @ByVal SKVector3 getDirection();
    @Property(selector = "setDirection:")
    public native void setDirection(@ByVal SKVector3 v);
    @Property(selector = "smoothness")
    public native float getSmoothness();
    @Property(selector = "setSmoothness:")
    public native void setSmoothness(float v);
    @Property(selector = "animationSpeed")
    public native float getAnimationSpeed();
    @Property(selector = "setAnimationSpeed:")
    public native void setAnimationSpeed(float v);
    @Property(selector = "texture")
    public native SKTexture getTexture();
    @Property(selector = "setTexture:")
    public native void setTexture(SKTexture v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "dragField")
    public static native SKFieldNode createDragField();
    @Method(selector = "vortexField")
    public static native SKFieldNode createVortexField();
    @Method(selector = "radialGravityField")
    public static native SKFieldNode createRadialGravityField();
    @Method(selector = "linearGravityFieldWithVector:")
    public static native SKFieldNode createLinearGravityField(@ByVal SKVector3 direction);
    @Method(selector = "velocityFieldWithVector:")
    public static native SKFieldNode createVelocityField(@ByVal SKVector3 direction);
    @Method(selector = "velocityFieldWithTexture:")
    public static native SKFieldNode createVelocityField(SKTexture velocityTexture);
    @Method(selector = "noiseFieldWithSmoothness:animationSpeed:")
    public static native SKFieldNode createNoiseField(@MachineSizedFloat double smoothness, @MachineSizedFloat double speed);
    @Method(selector = "turbulenceFieldWithSmoothness:animationSpeed:")
    public static native SKFieldNode createTurbulenceField(@MachineSizedFloat double smoothness, @MachineSizedFloat double speed);
    @Method(selector = "springField")
    public static native SKFieldNode createSpringField();
    @Method(selector = "electricField")
    public static native SKFieldNode createElectricField();
    @Method(selector = "magneticField")
    public static native SKFieldNode createMagneticField();
    @Method(selector = "customFieldWithEvaluationBlock:")
    public static native SKFieldNode createCustomField(@Block Block5<SKVector3, SKVector3, Float, Float, Double, SKVector3> block);
    /*</methods>*/
}
