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
package org.robovm.apple.corevideo;

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
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreVideo")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVPixelBuffer/*</name>*/ 
    extends /*<extends>*/CVImageBuffer/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CVPixelBufferPtr extends Ptr<CVPixelBuffer, CVPixelBufferPtr> {}/*</ptr>*/
    
    public interface ReleaseBytesCallback {
        void release(VoidPtr baseAddress);
    }
    public interface ReleasePlanarBytesCallback {
        void release(VoidPtr dataPtr, @MachineSizedUInt long dataSize, @MachineSizedUInt long numberOfPlanes, VoidPtr planeAddresses);
    }
    
    private static java.util.concurrent.atomic.AtomicLong refconId = new java.util.concurrent.atomic.AtomicLong();
    private long localRefconId;
    private static LongMap<ReleaseBytesCallback> releaseBytesCallbacks = new LongMap<>();
    private static LongMap<ReleasePlanarBytesCallback> releasePlanarBytesCallbacks = new LongMap<>();
    private static final java.lang.reflect.Method cbReleaseBytes;
    private static final java.lang.reflect.Method cbReleasePlanarBytes;
    
    static {
        try {
            cbReleaseBytes = CVPixelBuffer.class.getDeclaredMethod("cbReleaseBytes", long.class, VoidPtr.class);
            cbReleasePlanarBytes = CVPixelBuffer.class.getDeclaredMethod("cbReleasePlanarBytes", long.class, VoidPtr.class, long.class, long.class, VoidPtr.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<bind>*/static { Bro.bind(CVPixelBuffer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbReleaseBytes(long refcon, VoidPtr baseAddress) {
        ReleaseBytesCallback callback = null;
        synchronized (releaseBytesCallbacks) {
            callback = releaseBytesCallbacks.get(refcon);
        }
        callback.release(baseAddress);
    }
    @Callback
    private static void cbReleasePlanarBytes(long refcon, VoidPtr dataPtr, @MachineSizedUInt long dataSize, @MachineSizedUInt long numberOfPlanes, VoidPtr planeAddresses) {
        ReleasePlanarBytesCallback callback = null;
        synchronized (releasePlanarBytesCallbacks) {
            callback = releasePlanarBytesCallbacks.get(refcon);
        }
        callback.release(dataPtr, dataSize, numberOfPlanes, planeAddresses);
    }
    
    public static CVPixelBufferAttributes createResolvedAttributesDictionary(List<CVPixelBufferAttributes> attributes) {
        return createResolvedAttributesDictionary(null, attributes);
    }
    public static CVPixelBufferAttributes createResolvedAttributesDictionary(CFAllocator allocator, List<CVPixelBufferAttributes> attributes) {
        CFDictionary.CFDictionaryPtr ptr = new CFDictionary.CFDictionaryPtr();
        createResolvedAttributesDictionary(allocator, attributes, ptr);
        return new CVPixelBufferAttributes(ptr.get());
    }
    public static CVPixelBuffer create(long width, long height, CVPixelFormatType pixelFormatType, CVPixelBufferAttributes pixelBufferAttributes) {
        return create(null, width, height, pixelFormatType, pixelBufferAttributes);
    }
    public static CVPixelBuffer create(CFAllocator allocator, long width, long height, CVPixelFormatType pixelFormatType, CVPixelBufferAttributes pixelBufferAttributes) {
        long refconId = CVPixelBuffer.refconId.getAndIncrement();
        
        CVPixelBufferPtr ptr = new CVPixelBufferPtr();
        CVReturn err = create(allocator, width, height, pixelFormatType, pixelBufferAttributes, ptr);
        if (err == CVReturn.Success) {
            CVPixelBuffer buffer = ptr.get();
            buffer.localRefconId = refconId;
            return buffer;
        }
        return null;
    }
    public static CVPixelBuffer create(long width, long height, CVPixelFormatType pixelFormatType, VoidPtr baseAddress, long bytesPerRow, ReleaseBytesCallback releaseCallback, CVPixelBufferAttributes pixelBufferAttributes) {
        return create(null, width, height, pixelFormatType, baseAddress, bytesPerRow, releaseCallback, pixelBufferAttributes);
    }
    public static CVPixelBuffer create(CFAllocator allocator, long width, long height, CVPixelFormatType pixelFormatType, VoidPtr baseAddress, long bytesPerRow, ReleaseBytesCallback releaseCallback, CVPixelBufferAttributes pixelBufferAttributes) {
        long refconId = CVPixelBuffer.refconId.getAndIncrement();
        
        CVPixelBufferPtr ptr = new CVPixelBufferPtr();
        CVReturn err = create(allocator, width, height, pixelFormatType, baseAddress, bytesPerRow, new FunctionPtr(cbReleaseBytes), refconId, pixelBufferAttributes, ptr);
        if (err == CVReturn.Success) {
            CVPixelBuffer buffer = ptr.get();
            buffer.localRefconId = refconId;
            synchronized (releaseBytesCallbacks) {
                releaseBytesCallbacks.put(refconId, releaseCallback);
            }
            return buffer;
        }
        return null;
    }
    
    public long[] getExtendedPixels() {
        MachineSizedUIntPtr left = new MachineSizedUIntPtr();
        MachineSizedUIntPtr right = new MachineSizedUIntPtr();
        MachineSizedUIntPtr top = new MachineSizedUIntPtr();
        MachineSizedUIntPtr bottom = new MachineSizedUIntPtr();
        getExtendedPixels(left, right, top, bottom);
        return new long[] {left.get(), right.get(), top.get(), bottom.get()};
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferCreateResolvedAttributesDictionary", optional=true)
    private static native CVReturn createResolvedAttributesDictionary(CFAllocator allocator, @org.robovm.rt.bro.annotation.Marshaler(CVPixelBufferAttributes.AsListMarshaler.class) List<CVPixelBufferAttributes> attributes, CFDictionary.CFDictionaryPtr resolvedDictionaryOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferCreate", optional=true)
    protected static native CVReturn create(CFAllocator allocator, @MachineSizedUInt long width, @MachineSizedUInt long height, CVPixelFormatType pixelFormatType, CVPixelBufferAttributes pixelBufferAttributes, CVPixelBuffer.CVPixelBufferPtr pixelBufferOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferCreateWithBytes", optional=true)
    protected static native CVReturn create(CFAllocator allocator, @MachineSizedUInt long width, @MachineSizedUInt long height, CVPixelFormatType pixelFormatType, VoidPtr baseAddress, @MachineSizedUInt long bytesPerRow, FunctionPtr releaseCallback, @Pointer long releaseRefCon, CVPixelBufferAttributes pixelBufferAttributes, CVPixelBuffer.CVPixelBufferPtr pixelBufferOut);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CVPixelBufferCreateWithPlanarBytes", optional=true)
    protected static native CVReturn create(CFAllocator allocator, @MachineSizedUInt long width, @MachineSizedUInt long height, CVPixelFormatType pixelFormatType, VoidPtr dataPtr, @MachineSizedUInt long dataSize, @MachineSizedUInt long numberOfPlanes, VoidPtr.VoidPtrPtr planeBaseAddress, MachineSizedUIntPtr planeWidth, MachineSizedUIntPtr planeHeight, MachineSizedUIntPtr planeBytesPerRow, FunctionPtr releaseCallback, VoidPtr releaseRefCon, CVPixelBufferAttributes pixelBufferAttributes, CVPixelBuffer.CVPixelBufferPtr pixelBufferOut);
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
