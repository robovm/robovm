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
package org.robovm.apple.modelio;

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
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("ModelIO") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MDLMaterialProperty/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements MDLNamed/*</implements>*/ {

    /*<ptr>*/public static class MDLMaterialPropertyPtr extends Ptr<MDLMaterialProperty, MDLMaterialPropertyPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MDLMaterialProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MDLMaterialProperty() {}
    protected MDLMaterialProperty(SkipInit skipInit) { super(skipInit); }
    public MDLMaterialProperty(String name, MDLMaterialSemantic semantic) { super((SkipInit) null); initObject(init(name, semantic)); }
    public MDLMaterialProperty(String name, MDLMaterialSemantic semantic, float value) { super((SkipInit) null); initObject(init(name, semantic, value)); }
    public MDLMaterialProperty(String name, MDLMaterialSemantic semantic, VectorFloat2 value) { super((SkipInit) null); initObject(init(name, semantic, value)); }
    public MDLMaterialProperty(String name, MDLMaterialSemantic semantic, VectorFloat3 value) { super((SkipInit) null); initObject(init(name, semantic, value)); }
    public MDLMaterialProperty(String name, MDLMaterialSemantic semantic, VectorFloat4 value) { super((SkipInit) null); initObject(init(name, semantic, value)); }
    public MDLMaterialProperty(String name, MDLMaterialSemantic semantic, MatrixFloat4x4 value) { super((SkipInit) null); initObject(init(name, semantic, value)); }
    public MDLMaterialProperty(String name, MDLMaterialSemantic semantic, NSURL URL) { super((SkipInit) null); initObject(init(name, semantic, URL)); }
    public MDLMaterialProperty(String name, MDLMaterialSemantic semantic, String string) { super((SkipInit) null); initObject(init(name, semantic, string)); }
    public MDLMaterialProperty(String name, MDLMaterialSemantic semantic, MDLTextureSampler textureSampler) { super((SkipInit) null); initObject(init(name, semantic, textureSampler)); }
    public MDLMaterialProperty(String name, MDLMaterialSemantic semantic, CGColor color) { super((SkipInit) null); initObject(init(name, semantic, color)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "semantic")
    public native MDLMaterialSemantic getSemantic();
    @Property(selector = "setSemantic:")
    public native void setSemantic(MDLMaterialSemantic v);
    @Property(selector = "type")
    public native MDLMaterialPropertyType getType();
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "setName:")
    public native void setName(String v);
    @Property(selector = "stringValue")
    public native String getStringValue();
    @Property(selector = "setStringValue:")
    public native void setStringValue(String v);
    @Property(selector = "URLValue")
    public native NSURL getURLValue();
    @Property(selector = "setURLValue:")
    public native void setURLValue(NSURL v);
    @Property(selector = "textureSamplerValue")
    public native MDLTextureSampler getTextureSamplerValue();
    @Property(selector = "setTextureSamplerValue:")
    public native void setTextureSamplerValue(MDLTextureSampler v);
    @Property(selector = "color")
    public native CGColor getColor();
    @Property(selector = "setColor:")
    public native void setColor(CGColor v);
    @Property(selector = "floatValue")
    public native float getFloatValue();
    @Property(selector = "setFloatValue:")
    public native void setFloatValue(float v);
    @Property(selector = "float2Value")
    public native VectorFloat2 getFloat2Value();
    @Property(selector = "setFloat2Value:", strongRef = true)
    public native void setFloat2Value(VectorFloat2 v);
    @Property(selector = "float3Value")
    public native VectorFloat3 getFloat3Value();
    @Property(selector = "setFloat3Value:", strongRef = true)
    public native void setFloat3Value(VectorFloat3 v);
    @Property(selector = "float4Value")
    public native VectorFloat4 getFloat4Value();
    @Property(selector = "setFloat4Value:", strongRef = true)
    public native void setFloat4Value(VectorFloat4 v);
    @Property(selector = "matrix4x4")
    public native MatrixFloat4x4 getMatrix4x4();
    @Property(selector = "setMatrix4x4:", strongRef = true)
    public native void setMatrix4x4(MatrixFloat4x4 v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithName:semantic:")
    protected native @Pointer long init(String name, MDLMaterialSemantic semantic);
    @Method(selector = "initWithName:semantic:float:")
    protected native @Pointer long init(String name, MDLMaterialSemantic semantic, float value);
    @Method(selector = "initWithName:semantic:float2:")
    protected native @Pointer long init(String name, MDLMaterialSemantic semantic, VectorFloat2 value);
    @Method(selector = "initWithName:semantic:float3:")
    protected native @Pointer long init(String name, MDLMaterialSemantic semantic, VectorFloat3 value);
    @Method(selector = "initWithName:semantic:float4:")
    protected native @Pointer long init(String name, MDLMaterialSemantic semantic, VectorFloat4 value);
    @Method(selector = "initWithName:semantic:matrix4x4:")
    protected native @Pointer long init(String name, MDLMaterialSemantic semantic, MatrixFloat4x4 value);
    @Method(selector = "initWithName:semantic:URL:")
    protected native @Pointer long init(String name, MDLMaterialSemantic semantic, NSURL URL);
    @Method(selector = "initWithName:semantic:string:")
    protected native @Pointer long init(String name, MDLMaterialSemantic semantic, String string);
    @Method(selector = "initWithName:semantic:textureSampler:")
    protected native @Pointer long init(String name, MDLMaterialSemantic semantic, MDLTextureSampler textureSampler);
    @Method(selector = "initWithName:semantic:color:")
    protected native @Pointer long init(String name, MDLMaterialSemantic semantic, CGColor color);
    @Method(selector = "setProperties:")
    public native void setProperties(MDLMaterialProperty property);
    /*</methods>*/
}
