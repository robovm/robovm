/*
 * Copyright (C) 2014 Trillian AB
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
package org.robovm.apple.coreimage;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("CoreImage") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIImage/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class CIImagePtr extends Ptr<CIImage, CIImagePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CIImage.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CIImage() {}
    protected CIImage(SkipInit skipInit) { super(skipInit); }
    public CIImage(CGImage image) { super((SkipInit) null); initObject(initWithCGImage$(image)); }
    public CIImage(CGImage image, NSDictionary<?, ?> d) { super((SkipInit) null); initObject(initWithCGImage$options$(image, d)); }
    public CIImage(NSData data) { super((SkipInit) null); initObject(initWithData$(data)); }
    public CIImage(NSData data, NSDictionary<?, ?> d) { super((SkipInit) null); initObject(initWithData$options$(data, d)); }
    public CIImage(NSData d, @MachineSizedUInt long bpr, @ByVal CGSize size, int f, CGColorSpace c) { super((SkipInit) null); initObject(initWithBitmapData$bytesPerRow$size$format$colorSpace$(d, bpr, size, f, c)); }
    public CIImage(int name, @ByVal CGSize size, boolean flag, CGColorSpace cs) { super((SkipInit) null); initObject(initWithTexture$size$flipped$colorSpace$(name, size, flag, cs)); }
    public CIImage(NSURL url) { super((SkipInit) null); initObject(initWithContentsOfURL$(url)); }
    public CIImage(NSURL url, NSDictionary<?, ?> d) { super((SkipInit) null); initObject(initWithContentsOfURL$options$(url, d)); }
    public CIImage(CIColor color) { super((SkipInit) null); initObject(initWithColor$(color)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCIFormatARGB8")
    public static native int FormatARGB8();
    @GlobalValue(symbol="kCIFormatBGRA8")
    public static native int FormatBGRA8();
    @GlobalValue(symbol="kCIFormatRGBA8")
    public static native int FormatRGBA8();
    @GlobalValue(symbol="kCIFormatRGBA16")
    public static native int FormatRGBA16();
    @GlobalValue(symbol="kCIFormatRGBAf")
    public static native int FormatRGBAf();
    @GlobalValue(symbol="kCIFormatRGBAh")
    public static native int FormatRGBAh();
    @GlobalValue(symbol="kCIImageColorSpace")
    public static native NSString KeyColorSpace();
    @GlobalValue(symbol="kCIImageProperties")
    public static native NSString KeyProperties();
    @GlobalValue(symbol="kCIImageAutoAdjustEnhance")
    public static native NSString AutoAdjustEnhance();
    @GlobalValue(symbol="kCIImageAutoAdjustRedEye")
    public static native NSString AutoAdjustRedEye();
    @GlobalValue(symbol="kCIImageAutoAdjustFeatures")
    public static native NSString AutoAdjustFeatures();
    
    @Method(selector = "initWithCGImage:")
    protected native @Pointer long initWithCGImage$(CGImage image);
    @Method(selector = "initWithCGImage:options:")
    protected native @Pointer long initWithCGImage$options$(CGImage image, NSDictionary<?, ?> d);
    @Method(selector = "initWithData:")
    protected native @Pointer long initWithData$(NSData data);
    @Method(selector = "initWithData:options:")
    protected native @Pointer long initWithData$options$(NSData data, NSDictionary<?, ?> d);
    @Method(selector = "initWithBitmapData:bytesPerRow:size:format:colorSpace:")
    protected native @Pointer long initWithBitmapData$bytesPerRow$size$format$colorSpace$(NSData d, @MachineSizedUInt long bpr, @ByVal CGSize size, int f, CGColorSpace c);
    @Method(selector = "initWithTexture:size:flipped:colorSpace:")
    protected native @Pointer long initWithTexture$size$flipped$colorSpace$(int name, @ByVal CGSize size, boolean flag, CGColorSpace cs);
    @Method(selector = "initWithContentsOfURL:")
    protected native @Pointer long initWithContentsOfURL$(NSURL url);
    @Method(selector = "initWithContentsOfURL:options:")
    protected native @Pointer long initWithContentsOfURL$options$(NSURL url, NSDictionary<?, ?> d);
    @Method(selector = "initWithColor:")
    protected native @Pointer long initWithColor$(CIColor color);
    @Method(selector = "imageByApplyingTransform:")
    public native CIImage createImageByApplyingTransform(@ByVal CGAffineTransform matrix);
    @Method(selector = "imageByCroppingToRect:")
    public native CIImage createImageByCroppingToRect(@ByVal CGRect r);
    @Method(selector = "extent")
    public native @ByVal CGRect getExtent();
    @Method(selector = "properties")
    public native NSDictionary<?, ?> getProperties();
    @Method(selector = "regionOfInterestForImage:inRect:")
    public native @ByVal CGRect getRegionOfInterest(CIImage im, @ByVal CGRect r);
    @Method(selector = "emptyImage")
    public static native CIImage getEmptyImage();
    @Method(selector = "autoAdjustmentFilters")
    public native NSArray<CIFilter> getAutoAdjustmentFilters();
    @Method(selector = "autoAdjustmentFiltersWithOptions:")
    public native NSArray<CIFilter> getAutoAdjustmentFilters(NSDictionary<?, ?> dict);
    @Method(selector = "encodeWithCoder:")
    public native void encodeWithCoder$(NSCoder aCoder);
    /*</methods>*/
}
