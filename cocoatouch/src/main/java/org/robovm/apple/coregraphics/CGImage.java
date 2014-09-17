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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImage/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGImagePtr extends Ptr<CGImage, CGImagePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImage.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CGImage() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/

    public static CGImage create(long width, long height, long bitsPerComponent, 
            long bitsPerPixel, long bytesPerRow, CGColorSpace space, 
            CGBitmapInfo bitmapInfo, CGDataProvider provider, double[] decode, 
            boolean shouldInterpolate, CGColorRenderingIntent intent) {
        
        return create(width, height, bitsPerComponent, bitsPerPixel, bytesPerRow, space, 
                bitmapInfo, provider, 
                decode != null ? VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(decode)) : 0, 
                shouldInterpolate, intent);
    }
    
    public static CGImage createWithMaskingColors(CGImage image, double[] components) {
        return createWithMaskingColors(image, VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(components)));
    }
    
    public static CGImage createWithJPEGDataProvider(CGDataProvider source, 
            boolean shouldInterpolate, CGColorRenderingIntent intent) {
        return createWithJPEGDataProvider(source, null, shouldInterpolate, intent);
    }
    
    public static CGImage createWithJPEGDataProvider(CGDataProvider source, double[] decode, 
            boolean shouldInterpolate, CGColorRenderingIntent intent) {
        
        return createWithJPEGDataProvider(source, 
                decode != null ? VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(decode)) : 0,
                shouldInterpolate, intent);
    }

    public static CGImage createWithPNGDataProvider(CGDataProvider source, 
            boolean shouldInterpolate, CGColorRenderingIntent intent) {
        return createWithPNGDataProvider(source, null, shouldInterpolate, intent);
    }
    public static CGImage createWithPNGDataProvider(CGDataProvider source, double[] decode, 
            boolean shouldInterpolate, CGColorRenderingIntent intent) {
        
        return createWithPNGDataProvider(source, 
                decode != null ? VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(decode)) : 0,
                shouldInterpolate, intent);
    }
    
    public static CGImage createMask(long width, long height, long bitsPerComponent, long bitsPerPixel, long bytesPerRow, CGDataProvider provider, double[] decode, boolean shouldInterpolate) {
        return createMask(width, height, bitsPerComponent, bitsPerPixel, bytesPerRow, provider, 
                decode != null ? VM.getArrayValuesAddress(CoreGraphics.toMachineSizedFloatArray(decode)) : 0, 
                shouldInterpolate);
    }
    
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageCreate", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage create(@MachineSizedUInt long width, @MachineSizedUInt long height, @MachineSizedUInt long bitsPerComponent, @MachineSizedUInt long bitsPerPixel, @MachineSizedUInt long bytesPerRow, CGColorSpace space, CGBitmapInfo bitmapInfo, CGDataProvider provider, @Pointer long decode, boolean shouldInterpolate, CGColorRenderingIntent intent);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageMaskCreate", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage createMask(@MachineSizedUInt long width, @MachineSizedUInt long height, @MachineSizedUInt long bitsPerComponent, @MachineSizedUInt long bitsPerPixel, @MachineSizedUInt long bytesPerRow, CGDataProvider provider, @Pointer long decode, boolean shouldInterpolate);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageCreateCopy", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage copy(CGImage image);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageCreateWithJPEGDataProvider", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage createWithJPEGDataProvider(CGDataProvider source, @Pointer long decode, boolean shouldInterpolate, CGColorRenderingIntent intent);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageCreateWithPNGDataProvider", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage createWithPNGDataProvider(CGDataProvider source, @Pointer long decode, boolean shouldInterpolate, CGColorRenderingIntent intent);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageCreateWithImageInRect", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage createWithImageInRect(CGImage image, @ByVal CGRect rect);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageCreateWithMask", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage createWithMask(CGImage image, CGImage mask);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageCreateWithMaskingColors", optional=true)
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage createWithMaskingColors(CGImage image, @Pointer long components);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageCreateCopyWithColorSpace", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage copy(CGImage image, CGColorSpace space);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageIsMask", optional=true)
    public native boolean isMask();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageGetWidth", optional=true)
    public native @MachineSizedUInt long getWidth();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageGetHeight", optional=true)
    public native @MachineSizedUInt long getHeight();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageGetBitsPerComponent", optional=true)
    public native @MachineSizedUInt long getBitsPerComponent();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageGetBitsPerPixel", optional=true)
    public native @MachineSizedUInt long getBitsPerPixel();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageGetBytesPerRow", optional=true)
    public native @MachineSizedUInt long getBytesPerRow();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageGetColorSpace", optional=true)
    public native CGColorSpace getColorSpace();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageGetAlphaInfo", optional=true)
    public native CGImageAlphaInfo getAlphaInfo();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageGetDataProvider", optional=true)
    public native CGDataProvider getDataProvider();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageGetDecode", optional=true)
    public native MachineSizedFloatPtr getDecode();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageGetShouldInterpolate", optional=true)
    public native boolean getShouldInterpolate();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageGetRenderingIntent", optional=true)
    public native CGColorRenderingIntent getRenderingIntent();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGImageGetBitmapInfo", optional=true)
    public native CGBitmapInfo getBitmapInfo();
    /*</methods>*/
}
