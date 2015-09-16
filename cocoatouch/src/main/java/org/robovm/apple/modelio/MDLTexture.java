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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MDLTexture/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements MDLNamed/*</implements>*/ {

    /*<ptr>*/public static class MDLTexturePtr extends Ptr<MDLTexture, MDLTexturePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MDLTexture.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MDLTexture() {}
    protected MDLTexture(SkipInit skipInit) { super(skipInit); }
    public MDLTexture(NSData pixelData, boolean topLeftOrigin, String name, VectorInt2 dimensions, @MachineSizedSInt long rowStride, @MachineSizedUInt long channelCount, MDLTextureChannelEncoding channelEncoding, boolean isCube) { super((SkipInit) null); initObject(init(pixelData, topLeftOrigin, name, dimensions, rowStride, channelCount, channelEncoding, isCube)); }
    public MDLTexture(String name) { super(create(name)); retain(getHandle()); }
    public MDLTexture(String name, NSBundle bundleOrNil) { super(create(name, bundleOrNil)); retain(getHandle()); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "dimensions")
    public native VectorInt2 getDimensions();
    @Property(selector = "rowStride")
    public native @MachineSizedSInt long getRowStride();
    @Property(selector = "channelCount")
    public native @MachineSizedUInt long getChannelCount();
    @Property(selector = "mipLevelCount")
    public native @MachineSizedUInt long getMipLevelCount();
    @Property(selector = "channelEncoding")
    public native MDLTextureChannelEncoding getChannelEncoding();
    @Property(selector = "isCube")
    public native boolean isCube();
    @Property(selector = "setIsCube:")
    public native void setIsCube(boolean v);
    @Property(selector = "name")
    public native String getName();
    @Property(selector = "setName:")
    public native void setName(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithData:topLeftOrigin:name:dimensions:rowStride:channelCount:channelEncoding:isCube:")
    protected native @Pointer long init(NSData pixelData, boolean topLeftOrigin, String name, VectorInt2 dimensions, @MachineSizedSInt long rowStride, @MachineSizedUInt long channelCount, MDLTextureChannelEncoding channelEncoding, boolean isCube);
    @Method(selector = "writeToURL:")
    public native boolean write(NSURL URL);
    @Method(selector = "writeToURL:type:")
    public native boolean write(NSURL nsurl, String type);
    @Method(selector = "imageFromTexture")
    public native CGImage getImage();
    @Method(selector = "texelDataWithTopLeftOrigin")
    public native NSData getTexelDataWithTopLeftOrigin();
    @Method(selector = "texelDataWithBottomLeftOrigin")
    public native NSData getTexelDataWithBottomLeftOrigin();
    @Method(selector = "texelDataWithTopLeftOriginAtMipLevel:create:")
    public native NSData getTexelDataWithTopLeftOrigin(@MachineSizedSInt long level, boolean create);
    @Method(selector = "texelDataWithBottomLeftOriginAtMipLevel:create:")
    public native NSData getTexelDataWithBottomLeftOrigin(@MachineSizedSInt long level, boolean create);
    @Method(selector = "textureNamed:")
    protected static native @Pointer long create(String name);
    @Method(selector = "textureNamed:bundle:")
    protected static native @Pointer long create(String name, NSBundle bundleOrNil);
    @Method(selector = "textureCubeWithImagesNamed:")
    public static native MDLTexture newTextureCube(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> names);
    @Method(selector = "textureCubeWithImagesNamed:bundle:")
    public static native MDLTexture newTextureCube(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> names, NSBundle bundleOrNil);
    @Method(selector = "irradianceTextureCubeWithTexture:name:dimensions:")
    public static native MDLTexture newIrradianceTextureCube(MDLTexture texture, String name, VectorInt2 dimensions);
    @Method(selector = "irradianceTextureCubeWithTexture:name:dimensions:roughness:")
    public static native MDLTexture newIrradianceTextureCube(MDLTexture texture, String name, VectorInt2 dimensions, float roughness);
    /*</methods>*/
}
