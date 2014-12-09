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
package org.robovm.apple.metal;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Metal") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MTLTextureDescriptor/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MTLTextureDescriptorPtr extends Ptr<MTLTextureDescriptor, MTLTextureDescriptorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MTLTextureDescriptor.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MTLTextureDescriptor() {}
    protected MTLTextureDescriptor(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "textureType")
    public native MTLTextureType getTextureType();
    @Property(selector = "setTextureType:")
    public native void setTextureType(MTLTextureType v);
    @Property(selector = "pixelFormat")
    public native MTLPixelFormat getPixelFormat();
    @Property(selector = "setPixelFormat:")
    public native void setPixelFormat(MTLPixelFormat v);
    @Property(selector = "width")
    public native @MachineSizedUInt long getWidth();
    @Property(selector = "setWidth:")
    public native void setWidth(@MachineSizedUInt long v);
    @Property(selector = "height")
    public native @MachineSizedUInt long getHeight();
    @Property(selector = "setHeight:")
    public native void setHeight(@MachineSizedUInt long v);
    @Property(selector = "depth")
    public native @MachineSizedUInt long getDepth();
    @Property(selector = "setDepth:")
    public native void setDepth(@MachineSizedUInt long v);
    @Property(selector = "mipmapLevelCount")
    public native @MachineSizedUInt long getMipmapLevelCount();
    @Property(selector = "setMipmapLevelCount:")
    public native void setMipmapLevelCount(@MachineSizedUInt long v);
    @Property(selector = "sampleCount")
    public native @MachineSizedUInt long getSampleCount();
    @Property(selector = "setSampleCount:")
    public native void setSampleCount(@MachineSizedUInt long v);
    @Property(selector = "arrayLength")
    public native @MachineSizedUInt long getArrayLength();
    @Property(selector = "setArrayLength:")
    public native void setArrayLength(@MachineSizedUInt long v);
    @Property(selector = "resourceOptions")
    public native MTLResourceOptions getResourceOptions();
    @Property(selector = "setResourceOptions:")
    public native void setResourceOptions(MTLResourceOptions v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "texture2DDescriptorWithPixelFormat:width:height:mipmapped:")
    public static native MTLTextureDescriptor create2DDescriptor(MTLPixelFormat pixelFormat, @MachineSizedUInt long width, @MachineSizedUInt long height, boolean mipmapped);
    @Method(selector = "textureCubeDescriptorWithPixelFormat:size:mipmapped:")
    public static native MTLTextureDescriptor createCubeDescriptor(MTLPixelFormat pixelFormat, @MachineSizedUInt long size, boolean mipmapped);
    /*</methods>*/
}
