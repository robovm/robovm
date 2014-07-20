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
package org.robovm.apple.corevideo;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreVideo")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVPixelBuffer/*</name>*/ 
    extends /*<extends>*/CVImageBuffer/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CVPixelBufferPtr extends Ptr<CVPixelBuffer, CVPixelBufferPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CVPixelBuffer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferRetain", optional=true)
    public native CVPixelBuffer retain();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferRelease", optional=true)
    public native void release();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferCreateResolvedAttributesDictionary", optional=true)
    protected static native CVReturn createResolvedAttributesDictionary(CFAllocator allocator, NSArray<?> attributes, NSDictionary.NSDictionaryPtr resolvedDictionaryOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferCreate", optional=true)
    protected static native CVReturn create(CFAllocator allocator, @MachineSizedUInt long width, @MachineSizedUInt long height, int pixelFormatType, NSDictionary<?, ?> pixelBufferAttributes, CVPixelBuffer.CVPixelBufferPtr pixelBufferOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferCreateWithBytes", optional=true)
    protected static native CVReturn create(CFAllocator allocator, @MachineSizedUInt long width, @MachineSizedUInt long height, int pixelFormatType, VoidPtr baseAddress, @MachineSizedUInt long bytesPerRow, FunctionPtr releaseCallback, VoidPtr releaseRefCon, NSDictionary<?, ?> pixelBufferAttributes, CVPixelBuffer.CVPixelBufferPtr pixelBufferOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferCreateWithPlanarBytes", optional=true)
    protected static native CVReturn create(CFAllocator allocator, @MachineSizedUInt long width, @MachineSizedUInt long height, int pixelFormatType, VoidPtr dataPtr, @MachineSizedUInt long dataSize, @MachineSizedUInt long numberOfPlanes, VoidPtr.VoidPtrPtr planeBaseAddress, MachineSizedUIntPtr planeWidth, MachineSizedUIntPtr planeHeight, MachineSizedUIntPtr planeBytesPerRow, FunctionPtr releaseCallback, VoidPtr releaseRefCon, NSDictionary<?, ?> pixelBufferAttributes, CVPixelBuffer.CVPixelBufferPtr pixelBufferOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferLockBaseAddress", optional=true)
    public native CVReturn lockBaseAddress(CVPixelBufferLockFlags lockFlags);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferUnlockBaseAddress", optional=true)
    public native CVReturn unlockBaseAddress(CVPixelBufferLockFlags unlockFlags);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferGetWidth", optional=true)
    public native @MachineSizedUInt long getWidth();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferGetHeight", optional=true)
    public native @MachineSizedUInt long getHeight();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferGetPixelFormatType", optional=true)
    public native CVPixelFormatType getPixelFormatType();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferGetBaseAddress", optional=true)
    public native VoidPtr getBaseAddress();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferGetBytesPerRow", optional=true)
    public native @MachineSizedUInt long getBytesPerRow();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferGetDataSize", optional=true)
    public native @MachineSizedUInt long getDataSize();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferIsPlanar", optional=true)
    public native boolean isPlanar();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferGetPlaneCount", optional=true)
    public native @MachineSizedUInt long getPlaneCount();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferGetWidthOfPlane", optional=true)
    public native @MachineSizedUInt long getWidthOfPlane(@MachineSizedUInt long planeIndex);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferGetHeightOfPlane", optional=true)
    public native @MachineSizedUInt long getHeightOfPlane(@MachineSizedUInt long planeIndex);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferGetBaseAddressOfPlane", optional=true)
    public native VoidPtr getBaseAddressOfPlane(@MachineSizedUInt long planeIndex);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferGetBytesPerRowOfPlane", optional=true)
    public native @MachineSizedUInt long getBytesPerRowOfPlane(@MachineSizedUInt long planeIndex);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferGetExtendedPixels", optional=true)
    protected native void getExtendedPixels(MachineSizedUIntPtr extraColumnsOnLeft, MachineSizedUIntPtr extraColumnsOnRight, MachineSizedUIntPtr extraRowsOnTop, MachineSizedUIntPtr extraRowsOnBottom);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferFillExtendedPixels", optional=true)
    public native CVReturn fillExtendedPixels();
    /*</methods>*/
}
