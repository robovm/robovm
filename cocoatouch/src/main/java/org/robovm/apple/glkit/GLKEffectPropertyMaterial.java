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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GLKEffectPropertyMaterial/*</name>*/ 
    extends /*<extends>*/GLKEffectProperty/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class GLKEffectPropertyMaterialPtr extends Ptr<GLKEffectPropertyMaterial, GLKEffectPropertyMaterialPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GLKEffectPropertyMaterial.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GLKEffectPropertyMaterial() {}
    protected GLKEffectPropertyMaterial(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "ambientColor")
    public native @ByVal GLKVector4 getAmbientColor();
    @Property(selector = "setAmbientColor:")
    public native void setAmbientColor(@ByVal GLKVector4 v);
    @Property(selector = "diffuseColor")
    public native @ByVal GLKVector4 getDiffuseColor();
    @Property(selector = "setDiffuseColor:")
    public native void setDiffuseColor(@ByVal GLKVector4 v);
    @Property(selector = "specularColor")
    public native @ByVal GLKVector4 getSpecularColor();
    @Property(selector = "setSpecularColor:")
    public native void setSpecularColor(@ByVal GLKVector4 v);
    @Property(selector = "emissiveColor")
    public native @ByVal GLKVector4 getEmissiveColor();
    @Property(selector = "setEmissiveColor:")
    public native void setEmissiveColor(@ByVal GLKVector4 v);
    @Property(selector = "shininess")
    public native float getShininess();
    @Property(selector = "setShininess:")
    public native void setShininess(float v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
