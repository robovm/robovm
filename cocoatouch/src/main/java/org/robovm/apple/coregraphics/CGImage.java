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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
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
    @Bridge(symbol="CGImageGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CGImageCreate")
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage create(@MachineSizedUInt long width, @MachineSizedUInt long height, @MachineSizedUInt long bitsPerComponent, @MachineSizedUInt long bitsPerPixel, @MachineSizedUInt long bytesPerRow, CGColorSpace space, CGBitmapInfo bitmapInfo, CGDataProvider provider, @Pointer long decode, boolean shouldInterpolate, CGColorRenderingIntent intent);
    @Bridge(symbol="CGImageMaskCreate")
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage createMask(@MachineSizedUInt long width, @MachineSizedUInt long height, @MachineSizedUInt long bitsPerComponent, @MachineSizedUInt long bitsPerPixel, @MachineSizedUInt long bytesPerRow, CGDataProvider provider, @Pointer long decode, boolean shouldInterpolate);
    @Bridge(symbol="CGImageCreateCopy")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage copy(CGImage image);
    @Bridge(symbol="CGImageCreateWithJPEGDataProvider")
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage createWithJPEGDataProvider(CGDataProvider source, @Pointer long decode, boolean shouldInterpolate, CGColorRenderingIntent intent);
    @Bridge(symbol="CGImageCreateWithPNGDataProvider")
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage createWithPNGDataProvider(CGDataProvider source, @Pointer long decode, boolean shouldInterpolate, CGColorRenderingIntent intent);
    @Bridge(symbol="CGImageCreateWithImageInRect")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage createWithImageInRect(CGImage image, @ByVal CGRect rect);
    @Bridge(symbol="CGImageCreateWithMask")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage createWithMask(CGImage image, CGImage mask);
    @Bridge(symbol="CGImageCreateWithMaskingColors")
    protected static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage createWithMaskingColors(CGImage image, @Pointer long components);
    @Bridge(symbol="CGImageCreateCopyWithColorSpace")
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGImage copy(CGImage image, CGColorSpace space);
    @Bridge(symbol="CGImageIsMask")
    public native boolean isMask();
    @Bridge(symbol="CGImageGetWidth")
    public native @MachineSizedUInt long getWidth();
    @Bridge(symbol="CGImageGetHeight")
    public native @MachineSizedUInt long getHeight();
    @Bridge(symbol="CGImageGetBitsPerComponent")
    public native @MachineSizedUInt long getBitsPerComponent();
    @Bridge(symbol="CGImageGetBitsPerPixel")
    public native @MachineSizedUInt long getBitsPerPixel();
    @Bridge(symbol="CGImageGetBytesPerRow")
    public native @MachineSizedUInt long getBytesPerRow();
    @Bridge(symbol="CGImageGetColorSpace")
    public native CGColorSpace getColorSpace();
    @Bridge(symbol="CGImageGetAlphaInfo")
    public native CGImageAlphaInfo getAlphaInfo();
    @Bridge(symbol="CGImageGetDataProvider")
    public native CGDataProvider getDataProvider();
    @Bridge(symbol="CGImageGetDecode")
    public native MachineSizedFloatPtr getDecode();
    @Bridge(symbol="CGImageGetShouldInterpolate")
    public native boolean getShouldInterpolate();
    @Bridge(symbol="CGImageGetRenderingIntent")
    public native CGColorRenderingIntent getRenderingIntent();
    @Bridge(symbol="CGImageGetBitmapInfo")
    public native CGBitmapInfo getBitmapInfo();
    /*</methods>*/
}
