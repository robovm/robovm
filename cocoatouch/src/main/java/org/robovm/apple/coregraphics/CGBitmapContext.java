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
package org.robovm.apple.coregraphics;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreGraphics")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGBitmapContext/*</name>*/ 
    extends /*<extends>*/CGContext/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public interface ReleaseDataCallback {
        void release(IntPtr data);
    }
    
    private static java.util.concurrent.atomic.AtomicLong releaseInfo = new java.util.concurrent.atomic.AtomicLong();
    private static Map<Long, ReleaseDataCallback> callbacks = new HashMap<>();
    private static final java.lang.reflect.Method cbReleaseData;
    
    static {
        try {
            cbReleaseData = CGBitmapContext.class.getDeclaredMethod("cbReleaseData", long.class, IntPtr.class);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CGBitmapContext.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbReleaseData(@Pointer long refcon, IntPtr data) {
        ReleaseDataCallback callback = null;
        synchronized (callbacks) {
            callback = callbacks.get(refcon);
        }
        callback.release(data);
    }
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CGBitmapContext create(long width, long height, long bitsPerComponent, long bytesPerRow, CGColorSpace space, CGBitmapInfo bitmapInfo) {
        return create((IntPtr)null, width, height, bitsPerComponent, bytesPerRow, space, bitmapInfo);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CGBitmapContext create(long width, long height, long bitsPerComponent, long bytesPerRow, CGColorSpace space, CGImageAlphaInfo alphaInfo) {
        return create((IntPtr)null, width, height, bitsPerComponent, bytesPerRow, space, new CGBitmapInfo(alphaInfo.value()));
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CGBitmapContext create(byte[] data, long width, long height, long bitsPerComponent, long bytesPerRow, CGColorSpace space, CGBitmapInfo bitmapInfo) {
        BytePtr ptr = new BytePtr();
        ptr.set(data);
        return create(ptr.as(IntPtr.class), width, height, bitsPerComponent, bytesPerRow, space, bitmapInfo);
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CGBitmapContext create(byte[] data, long width, long height, long bitsPerComponent, long bytesPerRow, CGColorSpace space, CGImageAlphaInfo alphaInfo) {
        BytePtr ptr = new BytePtr();
        ptr.set(data);
        return create(ptr.as(IntPtr.class), width, height, bitsPerComponent, bytesPerRow, space, new CGBitmapInfo(alphaInfo.value()));
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static CGBitmapContext create(long width, long height, long bitsPerComponent, long bytesPerRow, CGColorSpace space, CGBitmapInfo bitmapInfo, ReleaseDataCallback releaseCallback) {
        return create((IntPtr)null, width, height, bitsPerComponent, bytesPerRow, space, bitmapInfo, releaseCallback);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static CGBitmapContext create(byte[] data, long width, long height, long bitsPerComponent, long bytesPerRow, CGColorSpace space, CGBitmapInfo bitmapInfo, ReleaseDataCallback releaseCallback) {
        BytePtr ptr = new BytePtr();
        ptr.set(data);
        return create(ptr.as(IntPtr.class), width, height, bitsPerComponent, bytesPerRow, space, bitmapInfo, releaseCallback);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static CGBitmapContext create(IntPtr data, long width, long height, long bitsPerComponent, long bytesPerRow, CGColorSpace space, CGBitmapInfo bitmapInfo, ReleaseDataCallback releaseCallback) {
        long releaseInfo = CGBitmapContext.releaseInfo.getAndIncrement();
        CGBitmapContext result = create(data, width, height, bitsPerComponent, bytesPerRow, space, bitmapInfo, new FunctionPtr(cbReleaseData), releaseInfo);
        if (result != null) {
            synchronized (callbacks) {
                callbacks.put(releaseInfo, releaseCallback);
            }
        }
        return result;
    }

    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CGBitmapContextCreateWithData", optional=true)
    private static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGBitmapContext create(IntPtr data, @MachineSizedUInt long width, @MachineSizedUInt long height, @MachineSizedUInt long bitsPerComponent, @MachineSizedUInt long bytesPerRow, CGColorSpace space, CGBitmapInfo bitmapInfo, FunctionPtr releaseCallback, @Pointer long releaseInfo);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGBitmapContextCreate", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGBitmapContext create(IntPtr data, @MachineSizedUInt long width, @MachineSizedUInt long height, @MachineSizedUInt long bitsPerComponent, @MachineSizedUInt long bytesPerRow, CGColorSpace space, CGBitmapInfo bitmapInfo);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGBitmapContextGetData", optional=true)
    public native IntPtr getData();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGBitmapContextGetWidth", optional=true)
    public native @MachineSizedUInt long getWidth();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGBitmapContextGetHeight", optional=true)
    public native @MachineSizedUInt long getHeight();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGBitmapContextGetBitsPerComponent", optional=true)
    public native @MachineSizedUInt long getBitsPerComponent();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGBitmapContextGetBitsPerPixel", optional=true)
    public native @MachineSizedUInt long getBitsPerPixel();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGBitmapContextGetBytesPerRow", optional=true)
    public native @MachineSizedUInt long getBytesPerRow();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGBitmapContextGetColorSpace", optional=true)
    public native CGColorSpace getColorSpace();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGBitmapContextGetAlphaInfo", optional=true)
    public native CGImageAlphaInfo getAlphaInfo();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGBitmapContextGetBitmapInfo", optional=true)
    public native CGBitmapInfo getBitmapInfo();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGBitmapContextCreateImage", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage toImage();
    /*</methods>*/
}
