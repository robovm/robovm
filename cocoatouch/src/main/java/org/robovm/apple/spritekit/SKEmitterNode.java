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

/*</javadoc>*/
/*<annotations>*/@Library("SpriteKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKEmitterNode/*</name>*/ 
    extends /*<extends>*/SKNode/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SKEmitterNodePtr extends Ptr<SKEmitterNode, SKEmitterNodePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKEmitterNode.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKEmitterNode() {}
    protected SKEmitterNode(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "particleTexture")
    public native SKTexture getParticleTexture();
    @Property(selector = "setParticleTexture:")
    public native void setParticleTexture(SKTexture v);
    @Property(selector = "particleZPosition")
    public native @MachineSizedFloat double getParticleZPosition();
    @Property(selector = "setParticleZPosition:")
    public native void setParticleZPosition(@MachineSizedFloat double v);
    @Property(selector = "particleZPositionRange")
    public native @MachineSizedFloat double getParticleZPositionRange();
    @Property(selector = "setParticleZPositionRange:")
    public native void setParticleZPositionRange(@MachineSizedFloat double v);
    @Property(selector = "particleZPositionSpeed")
    public native @MachineSizedFloat double getParticleZPositionSpeed();
    @Property(selector = "setParticleZPositionSpeed:")
    public native void setParticleZPositionSpeed(@MachineSizedFloat double v);
    @Property(selector = "particleBlendMode")
    public native SKBlendMode getParticleBlendMode();
    @Property(selector = "setParticleBlendMode:")
    public native void setParticleBlendMode(SKBlendMode v);
    @Property(selector = "particleColor")
    public native UIColor getParticleColor();
    @Property(selector = "setParticleColor:")
    public native void setParticleColor(UIColor v);
    @Property(selector = "particleColorRedRange")
    public native @MachineSizedFloat double getParticleColorRedRange();
    @Property(selector = "setParticleColorRedRange:")
    public native void setParticleColorRedRange(@MachineSizedFloat double v);
    @Property(selector = "particleColorGreenRange")
    public native @MachineSizedFloat double getParticleColorGreenRange();
    @Property(selector = "setParticleColorGreenRange:")
    public native void setParticleColorGreenRange(@MachineSizedFloat double v);
    @Property(selector = "particleColorBlueRange")
    public native @MachineSizedFloat double getParticleColorBlueRange();
    @Property(selector = "setParticleColorBlueRange:")
    public native void setParticleColorBlueRange(@MachineSizedFloat double v);
    @Property(selector = "particleColorAlphaRange")
    public native @MachineSizedFloat double getParticleColorAlphaRange();
    @Property(selector = "setParticleColorAlphaRange:")
    public native void setParticleColorAlphaRange(@MachineSizedFloat double v);
    @Property(selector = "particleColorRedSpeed")
    public native @MachineSizedFloat double getParticleColorRedSpeed();
    @Property(selector = "setParticleColorRedSpeed:")
    public native void setParticleColorRedSpeed(@MachineSizedFloat double v);
    @Property(selector = "particleColorGreenSpeed")
    public native @MachineSizedFloat double getParticleColorGreenSpeed();
    @Property(selector = "setParticleColorGreenSpeed:")
    public native void setParticleColorGreenSpeed(@MachineSizedFloat double v);
    @Property(selector = "particleColorBlueSpeed")
    public native @MachineSizedFloat double getParticleColorBlueSpeed();
    @Property(selector = "setParticleColorBlueSpeed:")
    public native void setParticleColorBlueSpeed(@MachineSizedFloat double v);
    @Property(selector = "particleColorAlphaSpeed")
    public native @MachineSizedFloat double getParticleColorAlphaSpeed();
    @Property(selector = "setParticleColorAlphaSpeed:")
    public native void setParticleColorAlphaSpeed(@MachineSizedFloat double v);
    @Property(selector = "particleColorSequence")
    public native SKKeyframeSequence getParticleColorSequence();
    @Property(selector = "setParticleColorSequence:")
    public native void setParticleColorSequence(SKKeyframeSequence v);
    @Property(selector = "particleColorBlendFactor")
    public native @MachineSizedFloat double getParticleColorBlendFactor();
    @Property(selector = "setParticleColorBlendFactor:")
    public native void setParticleColorBlendFactor(@MachineSizedFloat double v);
    @Property(selector = "particleColorBlendFactorRange")
    public native @MachineSizedFloat double getParticleColorBlendFactorRange();
    @Property(selector = "setParticleColorBlendFactorRange:")
    public native void setParticleColorBlendFactorRange(@MachineSizedFloat double v);
    @Property(selector = "particleColorBlendFactorSpeed")
    public native @MachineSizedFloat double getParticleColorBlendFactorSpeed();
    @Property(selector = "setParticleColorBlendFactorSpeed:")
    public native void setParticleColorBlendFactorSpeed(@MachineSizedFloat double v);
    @Property(selector = "particleColorBlendFactorSequence")
    public native SKKeyframeSequence getParticleColorBlendFactorSequence();
    @Property(selector = "setParticleColorBlendFactorSequence:")
    public native void setParticleColorBlendFactorSequence(SKKeyframeSequence v);
    @Property(selector = "particlePosition")
    public native @ByVal CGPoint getParticlePosition();
    @Property(selector = "setParticlePosition:")
    public native void setParticlePosition(@ByVal CGPoint v);
    @Property(selector = "particlePositionRange")
    public native @ByVal CGVector getParticlePositionRange();
    @Property(selector = "setParticlePositionRange:")
    public native void setParticlePositionRange(@ByVal CGVector v);
    @Property(selector = "particleSpeed")
    public native @MachineSizedFloat double getParticleSpeed();
    @Property(selector = "setParticleSpeed:")
    public native void setParticleSpeed(@MachineSizedFloat double v);
    @Property(selector = "particleSpeedRange")
    public native @MachineSizedFloat double getParticleSpeedRange();
    @Property(selector = "setParticleSpeedRange:")
    public native void setParticleSpeedRange(@MachineSizedFloat double v);
    @Property(selector = "emissionAngle")
    public native @MachineSizedFloat double getEmissionAngle();
    @Property(selector = "setEmissionAngle:")
    public native void setEmissionAngle(@MachineSizedFloat double v);
    @Property(selector = "emissionAngleRange")
    public native @MachineSizedFloat double getEmissionAngleRange();
    @Property(selector = "setEmissionAngleRange:")
    public native void setEmissionAngleRange(@MachineSizedFloat double v);
    @Property(selector = "xAcceleration")
    public native @MachineSizedFloat double getXAcceleration();
    @Property(selector = "setXAcceleration:")
    public native void setXAcceleration(@MachineSizedFloat double v);
    @Property(selector = "yAcceleration")
    public native @MachineSizedFloat double getYAcceleration();
    @Property(selector = "setYAcceleration:")
    public native void setYAcceleration(@MachineSizedFloat double v);
    @Property(selector = "particleBirthRate")
    public native @MachineSizedFloat double getParticleBirthRate();
    @Property(selector = "setParticleBirthRate:")
    public native void setParticleBirthRate(@MachineSizedFloat double v);
    @Property(selector = "numParticlesToEmit")
    public native @MachineSizedUInt long getNumParticlesToEmit();
    @Property(selector = "setNumParticlesToEmit:")
    public native void setNumParticlesToEmit(@MachineSizedUInt long v);
    @Property(selector = "particleLifetime")
    public native @MachineSizedFloat double getParticleLifetime();
    @Property(selector = "setParticleLifetime:")
    public native void setParticleLifetime(@MachineSizedFloat double v);
    @Property(selector = "particleLifetimeRange")
    public native @MachineSizedFloat double getParticleLifetimeRange();
    @Property(selector = "setParticleLifetimeRange:")
    public native void setParticleLifetimeRange(@MachineSizedFloat double v);
    @Property(selector = "particleRotation")
    public native @MachineSizedFloat double getParticleRotation();
    @Property(selector = "setParticleRotation:")
    public native void setParticleRotation(@MachineSizedFloat double v);
    @Property(selector = "particleRotationRange")
    public native @MachineSizedFloat double getParticleRotationRange();
    @Property(selector = "setParticleRotationRange:")
    public native void setParticleRotationRange(@MachineSizedFloat double v);
    @Property(selector = "particleRotationSpeed")
    public native @MachineSizedFloat double getParticleRotationSpeed();
    @Property(selector = "setParticleRotationSpeed:")
    public native void setParticleRotationSpeed(@MachineSizedFloat double v);
    @Property(selector = "particleSize")
    public native @ByVal CGSize getParticleSize();
    @Property(selector = "setParticleSize:")
    public native void setParticleSize(@ByVal CGSize v);
    @Property(selector = "particleScale")
    public native @MachineSizedFloat double getParticleScale();
    @Property(selector = "setParticleScale:")
    public native void setParticleScale(@MachineSizedFloat double v);
    @Property(selector = "particleScaleRange")
    public native @MachineSizedFloat double getParticleScaleRange();
    @Property(selector = "setParticleScaleRange:")
    public native void setParticleScaleRange(@MachineSizedFloat double v);
    @Property(selector = "particleScaleSpeed")
    public native @MachineSizedFloat double getParticleScaleSpeed();
    @Property(selector = "setParticleScaleSpeed:")
    public native void setParticleScaleSpeed(@MachineSizedFloat double v);
    @Property(selector = "particleScaleSequence")
    public native SKKeyframeSequence getParticleScaleSequence();
    @Property(selector = "setParticleScaleSequence:")
    public native void setParticleScaleSequence(SKKeyframeSequence v);
    @Property(selector = "particleAlpha")
    public native @MachineSizedFloat double getParticleAlpha();
    @Property(selector = "setParticleAlpha:")
    public native void setParticleAlpha(@MachineSizedFloat double v);
    @Property(selector = "particleAlphaRange")
    public native @MachineSizedFloat double getParticleAlphaRange();
    @Property(selector = "setParticleAlphaRange:")
    public native void setParticleAlphaRange(@MachineSizedFloat double v);
    @Property(selector = "particleAlphaSpeed")
    public native @MachineSizedFloat double getParticleAlphaSpeed();
    @Property(selector = "setParticleAlphaSpeed:")
    public native void setParticleAlphaSpeed(@MachineSizedFloat double v);
    @Property(selector = "particleAlphaSequence")
    public native SKKeyframeSequence getParticleAlphaSequence();
    @Property(selector = "setParticleAlphaSequence:")
    public native void setParticleAlphaSequence(SKKeyframeSequence v);
    @Property(selector = "particleAction")
    public native SKAction getParticleAction();
    @Property(selector = "setParticleAction:")
    public native void setParticleAction(SKAction v);
    @Property(selector = "fieldBitMask")
    public native int getFieldBitMask();
    @Property(selector = "setFieldBitMask:")
    public native void setFieldBitMask(int v);
    @Property(selector = "targetNode")
    public native SKNode getTargetNode();
    @Property(selector = "setTargetNode:", strongRef = true)
    public native void setTargetNode(SKNode v);
    @Property(selector = "shader")
    public native SKShader getShader();
    @Property(selector = "setShader:")
    public native void setShader(SKShader v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "advanceSimulationTime:")
    public native void advanceSimulationTime(double sec);
    @Method(selector = "resetSimulation")
    public native void resetSimulation();
    /*</methods>*/
}
