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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKUniform/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class SKUniformPtr extends Ptr<SKUniform, SKUniformPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKUniform.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKUniform() {}
    protected SKUniform(SkipInit skipInit) { super(skipInit); }
    public SKUniform(String name) { super((SkipInit) null); initObject(init(name)); }
    public SKUniform(String name, SKTexture texture) { super((SkipInit) null); initObject(init(name, texture)); }
    public SKUniform(String name, float value) { super((SkipInit) null); initObject(init(name, value)); }
    public SKUniform(String name, @ByVal GLKVector2 value) { super((SkipInit) null); initObject(init(name, value)); }
    public SKUniform(String name, @ByVal GLKVector3 value) { super((SkipInit) null); initObject(init(name, value)); }
    public SKUniform(String name, @ByVal GLKVector4 value) { super((SkipInit) null); initObject(init(name, value)); }
    public SKUniform(String name, @ByVal GLKMatrix2 value) { super((SkipInit) null); initObject(init(name, value)); }
    public SKUniform(String name, @ByVal GLKMatrix3 value) { super((SkipInit) null); initObject(init(name, value)); }
    public SKUniform(String name, @ByVal GLKMatrix4 value) { super((SkipInit) null); initObject(init(name, value)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "uniformType")
    public native SKUniformType getUniformType();
    @Property(selector = "textureValue")
    public native SKTexture getTextureValue();
    @Property(selector = "setTextureValue:")
    public native void setTextureValue(SKTexture v);
    @Property(selector = "floatValue")
    public native float getFloatValue();
    @Property(selector = "setFloatValue:")
    public native void setFloatValue(float v);
    @Property(selector = "floatVector2Value")
    public native @ByVal GLKVector2 getFloatVector2Value();
    @Property(selector = "setFloatVector2Value:")
    public native void setFloatVector2Value(@ByVal GLKVector2 v);
    @Property(selector = "floatVector3Value")
    public native @ByVal GLKVector3 getFloatVector3Value();
    @Property(selector = "setFloatVector3Value:")
    public native void setFloatVector3Value(@ByVal GLKVector3 v);
    @Property(selector = "floatVector4Value")
    public native @ByVal GLKVector4 getFloatVector4Value();
    @Property(selector = "setFloatVector4Value:")
    public native void setFloatVector4Value(@ByVal GLKVector4 v);
    @Property(selector = "floatMatrix2Value")
    public native @ByVal GLKMatrix2 getFloatMatrix2Value();
    @Property(selector = "setFloatMatrix2Value:")
    public native void setFloatMatrix2Value(@ByVal GLKMatrix2 v);
    @Property(selector = "floatMatrix3Value")
    public native @ByVal GLKMatrix3 getFloatMatrix3Value();
    @Property(selector = "setFloatMatrix3Value:")
    public native void setFloatMatrix3Value(@ByVal GLKMatrix3 v);
    @Property(selector = "floatMatrix4Value")
    public native @ByVal GLKMatrix4 getFloatMatrix4Value();
    @Property(selector = "setFloatMatrix4Value:")
    public native void setFloatMatrix4Value(@ByVal GLKMatrix4 v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithName:")
    protected native @Pointer long init(String name);
    @Method(selector = "initWithName:texture:")
    protected native @Pointer long init(String name, SKTexture texture);
    @Method(selector = "initWithName:float:")
    protected native @Pointer long init(String name, float value);
    @Method(selector = "initWithName:floatVector2:")
    protected native @Pointer long init(String name, @ByVal GLKVector2 value);
    @Method(selector = "initWithName:floatVector3:")
    protected native @Pointer long init(String name, @ByVal GLKVector3 value);
    @Method(selector = "initWithName:floatVector4:")
    protected native @Pointer long init(String name, @ByVal GLKVector4 value);
    @Method(selector = "initWithName:floatMatrix2:")
    protected native @Pointer long init(String name, @ByVal GLKMatrix2 value);
    @Method(selector = "initWithName:floatMatrix3:")
    protected native @Pointer long init(String name, @ByVal GLKMatrix3 value);
    @Method(selector = "initWithName:floatMatrix4:")
    protected native @Pointer long init(String name, @ByVal GLKMatrix4 value);
    @Method(selector = "uniformWithName:")
    public static native SKUniform create(String name);
    @Method(selector = "uniformWithName:texture:")
    public static native SKUniform create(String name, SKTexture texture);
    @Method(selector = "uniformWithName:float:")
    public static native SKUniform create(String name, float value);
    @Method(selector = "uniformWithName:floatVector2:")
    public static native SKUniform create(String name, @ByVal GLKVector2 value);
    @Method(selector = "uniformWithName:floatVector3:")
    public static native SKUniform create(String name, @ByVal GLKVector3 value);
    @Method(selector = "uniformWithName:floatVector4:")
    public static native SKUniform create(String name, @ByVal GLKVector4 value);
    @Method(selector = "uniformWithName:floatMatrix2:")
    public static native SKUniform create(String name, @ByVal GLKMatrix2 value);
    @Method(selector = "uniformWithName:floatMatrix3:")
    public static native SKUniform create(String name, @ByVal GLKMatrix3 value);
    @Method(selector = "uniformWithName:floatMatrix4:")
    public static native SKUniform create(String name, @ByVal GLKMatrix4 value);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
