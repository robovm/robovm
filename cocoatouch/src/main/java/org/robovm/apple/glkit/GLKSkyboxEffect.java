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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GLKSkyboxEffect/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements GLKNamedEffect/*</implements>*/ {

    /*<ptr>*/public static class GLKSkyboxEffectPtr extends Ptr<GLKSkyboxEffect, GLKSkyboxEffectPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GLKSkyboxEffect.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GLKSkyboxEffect() {}
    protected GLKSkyboxEffect(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "center")
    public native @ByVal GLKVector3 getCenter();
    @Property(selector = "setCenter:")
    public native void setCenter(@ByVal GLKVector3 v);
    @Property(selector = "xSize")
    public native float getXSize();
    @Property(selector = "setXSize:")
    public native void setXSize(float v);
    @Property(selector = "ySize")
    public native float getYSize();
    @Property(selector = "setYSize:")
    public native void setYSize(float v);
    @Property(selector = "zSize")
    public native float getZSize();
    @Property(selector = "setZSize:")
    public native void setZSize(float v);
    @Property(selector = "textureCubeMap")
    public native GLKEffectPropertyTexture getTextureCubeMap();
    @Property(selector = "transform")
    public native GLKEffectPropertyTransform getTransform();
    @Property(selector = "label")
    public native String getLabel();
    @Property(selector = "setLabel:")
    public native void setLabel(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "prepareToDraw")
    public native void prepareToDraw();
    @Method(selector = "draw")
    public native void draw();
    /*</methods>*/
}
