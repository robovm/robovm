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
package org.robovm.apple.coreimage;

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
import org.robovm.apple.corevideo.*;
import org.robovm.apple.imageio.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CIFilterCategory.Marshaler.class)
/*<annotations>*/@Library("CoreImage")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIFilterCategory/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CIFilterCategory toObject(Class<CIFilterCategory> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CIFilterCategory.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CIFilterCategory o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CIFilterCategory> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CIFilterCategory> list = new ArrayList<>();
            for (NSString str : o) {
                list.add(CIFilterCategory.valueOf(str));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CIFilterCategory> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSString> array = new NSMutableArray<>();
            for (CIFilterCategory i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CIFilterCategory.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final CIFilterCategory DistortionEffect = new CIFilterCategory("DistortionEffectValue");
    public static final CIFilterCategory GeometryAdjustment = new CIFilterCategory("GeometryAdjustmentValue");
    public static final CIFilterCategory CompositeOperation = new CIFilterCategory("CompositeOperationValue");
    public static final CIFilterCategory HalftoneEffect = new CIFilterCategory("HalftoneEffectValue");
    public static final CIFilterCategory ColorAdjustment = new CIFilterCategory("ColorAdjustmentValue");
    public static final CIFilterCategory ColorEffect = new CIFilterCategory("ColorEffectValue");
    public static final CIFilterCategory Transition = new CIFilterCategory("TransitionValue");
    public static final CIFilterCategory TileEffect = new CIFilterCategory("TileEffectValue");
    public static final CIFilterCategory Generator = new CIFilterCategory("GeneratorValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CIFilterCategory Reduction = new CIFilterCategory("ReductionValue");
    public static final CIFilterCategory Gradient = new CIFilterCategory("GradientValue");
    public static final CIFilterCategory Stylize = new CIFilterCategory("StylizeValue");
    public static final CIFilterCategory Sharpen = new CIFilterCategory("SharpenValue");
    public static final CIFilterCategory Blur = new CIFilterCategory("BlurValue");
    public static final CIFilterCategory Video = new CIFilterCategory("VideoValue");
    public static final CIFilterCategory StillImage = new CIFilterCategory("StillImageValue");
    public static final CIFilterCategory Interlaced = new CIFilterCategory("InterlacedValue");
    public static final CIFilterCategory NonSquarePixels = new CIFilterCategory("NonSquarePixelsValue");
    public static final CIFilterCategory HighDynamicRange = new CIFilterCategory("HighDynamicRangeValue");
    public static final CIFilterCategory BuiltIn = new CIFilterCategory("BuiltInValue");
    
    private static CIFilterCategory[] values = new CIFilterCategory[] {DistortionEffect, GeometryAdjustment, CompositeOperation, 
        HalftoneEffect, ColorAdjustment, ColorEffect, Transition, TileEffect, Generator, Reduction, Gradient, Stylize, Sharpen, 
        Blur, Video, StillImage, Interlaced, NonSquarePixels, HighDynamicRange, BuiltIn};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private CIFilterCategory(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static CIFilterCategory valueOf(NSString value) {
        for (CIFilterCategory v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CIFilterCategory/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="kCICategoryDistortionEffect", optional=true)
    protected static native NSString DistortionEffectValue();
    @GlobalValue(symbol="kCICategoryGeometryAdjustment", optional=true)
    protected static native NSString GeometryAdjustmentValue();
    @GlobalValue(symbol="kCICategoryCompositeOperation", optional=true)
    protected static native NSString CompositeOperationValue();
    @GlobalValue(symbol="kCICategoryHalftoneEffect", optional=true)
    protected static native NSString HalftoneEffectValue();
    @GlobalValue(symbol="kCICategoryColorAdjustment", optional=true)
    protected static native NSString ColorAdjustmentValue();
    @GlobalValue(symbol="kCICategoryColorEffect", optional=true)
    protected static native NSString ColorEffectValue();
    @GlobalValue(symbol="kCICategoryTransition", optional=true)
    protected static native NSString TransitionValue();
    @GlobalValue(symbol="kCICategoryTileEffect", optional=true)
    protected static native NSString TileEffectValue();
    @GlobalValue(symbol="kCICategoryGenerator", optional=true)
    protected static native NSString GeneratorValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCICategoryReduction", optional=true)
    protected static native NSString ReductionValue();
    @GlobalValue(symbol="kCICategoryGradient", optional=true)
    protected static native NSString GradientValue();
    @GlobalValue(symbol="kCICategoryStylize", optional=true)
    protected static native NSString StylizeValue();
    @GlobalValue(symbol="kCICategorySharpen", optional=true)
    protected static native NSString SharpenValue();
    @GlobalValue(symbol="kCICategoryBlur", optional=true)
    protected static native NSString BlurValue();
    @GlobalValue(symbol="kCICategoryVideo", optional=true)
    protected static native NSString VideoValue();
    @GlobalValue(symbol="kCICategoryStillImage", optional=true)
    protected static native NSString StillImageValue();
    @GlobalValue(symbol="kCICategoryInterlaced", optional=true)
    protected static native NSString InterlacedValue();
    @GlobalValue(symbol="kCICategoryNonSquarePixels", optional=true)
    protected static native NSString NonSquarePixelsValue();
    @GlobalValue(symbol="kCICategoryHighDynamicRange", optional=true)
    protected static native NSString HighDynamicRangeValue();
    @GlobalValue(symbol="kCICategoryBuiltIn", optional=true)
    protected static native NSString BuiltInValue();
    /*</methods>*/
}
