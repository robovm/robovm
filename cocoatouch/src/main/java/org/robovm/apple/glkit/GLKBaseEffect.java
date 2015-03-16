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
package org.robovm.apple.glkit;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("GLKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GLKBaseEffect/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements GLKNamedEffect/*</implements>*/ {

    /*<ptr>*/public static class GLKBaseEffectPtr extends Ptr<GLKBaseEffect, GLKBaseEffectPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GLKBaseEffect.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GLKBaseEffect() {}
    protected GLKBaseEffect(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "colorMaterialEnabled")
    public native boolean isColorMaterialEnabled();
    @Property(selector = "setColorMaterialEnabled:")
    public native void setColorMaterialEnabled(boolean v);
    @Property(selector = "lightModelTwoSided")
    public native boolean isLightModelTwoSided();
    @Property(selector = "setLightModelTwoSided:")
    public native void setLightModelTwoSided(boolean v);
    @Property(selector = "useConstantColor")
    public native boolean usesConstantColor();
    @Property(selector = "setUseConstantColor:")
    public native void setUsesConstantColor(boolean v);
    @Property(selector = "transform")
    public native GLKEffectPropertyTransform getTransform();
    @Property(selector = "light0")
    public native GLKEffectPropertyLight getLight0();
    @Property(selector = "light1")
    public native GLKEffectPropertyLight getLight1();
    @Property(selector = "light2")
    public native GLKEffectPropertyLight getLight2();
    @Property(selector = "lightingType")
    public native GLKLightingType getLightingType();
    @Property(selector = "setLightingType:")
    public native void setLightingType(GLKLightingType v);
    @Property(selector = "lightModelAmbientColor")
    public native @ByVal GLKVector4 getLightModelAmbientColor();
    @Property(selector = "setLightModelAmbientColor:")
    public native void setLightModelAmbientColor(@ByVal GLKVector4 v);
    @Property(selector = "material")
    public native GLKEffectPropertyMaterial getMaterial();
    @Property(selector = "texture2d0")
    public native GLKEffectPropertyTexture getTexture2d0();
    @Property(selector = "texture2d1")
    public native GLKEffectPropertyTexture getTexture2d1();
    @Property(selector = "textureOrder")
    public native NSArray<GLKEffectPropertyTexture> getTextureOrder();
    @Property(selector = "setTextureOrder:")
    public native void setTextureOrder(NSArray<GLKEffectPropertyTexture> v);
    @Property(selector = "constantColor")
    public native @ByVal GLKVector4 getConstantColor();
    @Property(selector = "setConstantColor:")
    public native void setConstantColor(@ByVal GLKVector4 v);
    @Property(selector = "fog")
    public native GLKEffectPropertyFog getFog();
    @Property(selector = "label")
    public native String getLabel();
    @Property(selector = "setLabel:")
    public native void setLabel(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "prepareToDraw")
    public native void prepareToDraw();
    /*</methods>*/
}
