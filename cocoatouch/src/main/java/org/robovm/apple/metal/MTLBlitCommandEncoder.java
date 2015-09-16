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
package org.robovm.apple.metal;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Metal") @NativeProtocolProxy/*</annotations>*/
/*<visibility>*/public final/*</visibility>*/ class /*<name>*/MTLBlitCommandEncoder/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements MTLCommandEncoder/*</implements>*/ {

    /*<ptr>*/public static class MTLBlitCommandEncoderPtr extends Ptr<MTLBlitCommandEncoder, MTLBlitCommandEncoderPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MTLBlitCommandEncoder.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "device")
    public native MTLDevice getDevice();
    @Property(selector = "label")
    public native String getLabel();
    @Property(selector = "setLabel:")
    public native void setLabel(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "copyFromTexture:sourceSlice:sourceLevel:sourceOrigin:sourceSize:toTexture:destinationSlice:destinationLevel:destinationOrigin:")
    public native void copyFromTextureToTexture(MTLTexture sourceTexture, @MachineSizedUInt long sourceSlice, @MachineSizedUInt long sourceLevel, @ByVal MTLOrigin sourceOrigin, @ByVal MTLSize sourceSize, MTLTexture destinationTexture, @MachineSizedUInt long destinationSlice, @MachineSizedUInt long destinationLevel, @ByVal MTLOrigin destinationOrigin);
    @Method(selector = "copyFromBuffer:sourceOffset:sourceBytesPerRow:sourceBytesPerImage:sourceSize:toTexture:destinationSlice:destinationLevel:destinationOrigin:")
    public native void copyFromBufferToTexture(MTLBuffer sourceBuffer, @MachineSizedUInt long sourceOffset, @MachineSizedUInt long sourceBytesPerRow, @MachineSizedUInt long sourceBytesPerImage, @ByVal MTLSize sourceSize, MTLTexture destinationTexture, @MachineSizedUInt long destinationSlice, @MachineSizedUInt long destinationLevel, @ByVal MTLOrigin destinationOrigin);
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Method(selector = "copyFromBuffer:sourceOffset:sourceBytesPerRow:sourceBytesPerImage:sourceSize:toTexture:destinationSlice:destinationLevel:destinationOrigin:options:")
    public native void copyFromBufferToTexture(MTLBuffer sourceBuffer, @MachineSizedUInt long sourceOffset, @MachineSizedUInt long sourceBytesPerRow, @MachineSizedUInt long sourceBytesPerImage, @ByVal MTLSize sourceSize, MTLTexture destinationTexture, @MachineSizedUInt long destinationSlice, @MachineSizedUInt long destinationLevel, @ByVal MTLOrigin destinationOrigin, MTLBlitOption options);
    @Method(selector = "copyFromTexture:sourceSlice:sourceLevel:sourceOrigin:sourceSize:toBuffer:destinationOffset:destinationBytesPerRow:destinationBytesPerImage:")
    public native void copyFromTextureToBuffer(MTLTexture sourceTexture, @MachineSizedUInt long sourceSlice, @MachineSizedUInt long sourceLevel, @ByVal MTLOrigin sourceOrigin, @ByVal MTLSize sourceSize, MTLBuffer destinationBuffer, @MachineSizedUInt long destinationOffset, @MachineSizedUInt long destinationBytesPerRow, @MachineSizedUInt long destinationBytesPerImage);
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Method(selector = "copyFromTexture:sourceSlice:sourceLevel:sourceOrigin:sourceSize:toBuffer:destinationOffset:destinationBytesPerRow:destinationBytesPerImage:options:")
    public native void copyFromTextureToBuffer(MTLTexture sourceTexture, @MachineSizedUInt long sourceSlice, @MachineSizedUInt long sourceLevel, @ByVal MTLOrigin sourceOrigin, @ByVal MTLSize sourceSize, MTLBuffer destinationBuffer, @MachineSizedUInt long destinationOffset, @MachineSizedUInt long destinationBytesPerRow, @MachineSizedUInt long destinationBytesPerImage, MTLBlitOption options);
    @Method(selector = "generateMipmapsForTexture:")
    public native void generateMipmapsForTexture(MTLTexture texture);
    @Method(selector = "fillBuffer:range:value:")
    public native void fillBuffer(MTLBuffer buffer, @ByVal NSRange range, byte value);
    @Method(selector = "copyFromBuffer:sourceOffset:toBuffer:destinationOffset:size:")
    public native void copyFromBufferToBuffer(MTLBuffer sourceBuffer, @MachineSizedUInt long sourceOffset, MTLBuffer destinationBuffer, @MachineSizedUInt long destinationOffset, @MachineSizedUInt long size);
    @Method(selector = "endEncoding")
    public native void endEncoding();
    @Method(selector = "insertDebugSignpost:")
    public native void insertDebugSignpost(String string);
    @Method(selector = "pushDebugGroup:")
    public native void pushDebugGroup(String string);
    @Method(selector = "popDebugGroup")
    public native void popDebugGroup();
    /*</methods>*/
}
