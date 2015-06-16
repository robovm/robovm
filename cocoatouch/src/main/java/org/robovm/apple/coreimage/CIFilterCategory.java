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
/*<annotations>*/@Library("CoreImage") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CIFilterCategory/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIFilterCategory/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CIFilterCategory/*</name>*/.class); }

    /*<marshalers>*/
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
            for (int i = 0; i < o.size(); i++) {
                list.add(CIFilterCategory.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CIFilterCategory> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CIFilterCategory o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final CIFilterCategory DistortionEffect = new CIFilterCategory("DistortionEffect");
    public static final CIFilterCategory GeometryAdjustment = new CIFilterCategory("GeometryAdjustment");
    public static final CIFilterCategory CompositeOperation = new CIFilterCategory("CompositeOperation");
    public static final CIFilterCategory HalftoneEffect = new CIFilterCategory("HalftoneEffect");
    public static final CIFilterCategory ColorAdjustment = new CIFilterCategory("ColorAdjustment");
    public static final CIFilterCategory ColorEffect = new CIFilterCategory("ColorEffect");
    public static final CIFilterCategory Transition = new CIFilterCategory("Transition");
    public static final CIFilterCategory TileEffect = new CIFilterCategory("TileEffect");
    public static final CIFilterCategory Generator = new CIFilterCategory("Generator");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CIFilterCategory Reduction = new CIFilterCategory("Reduction");
    public static final CIFilterCategory Gradient = new CIFilterCategory("Gradient");
    public static final CIFilterCategory Stylize = new CIFilterCategory("Stylize");
    public static final CIFilterCategory Sharpen = new CIFilterCategory("Sharpen");
    public static final CIFilterCategory Blur = new CIFilterCategory("Blur");
    public static final CIFilterCategory Video = new CIFilterCategory("Video");
    public static final CIFilterCategory StillImage = new CIFilterCategory("StillImage");
    public static final CIFilterCategory Interlaced = new CIFilterCategory("Interlaced");
    public static final CIFilterCategory NonSquarePixels = new CIFilterCategory("NonSquarePixels");
    public static final CIFilterCategory HighDynamicRange = new CIFilterCategory("HighDynamicRange");
    public static final CIFilterCategory BuiltIn = new CIFilterCategory("BuiltIn");
    /*</constants>*/
    
    private static /*<name>*/CIFilterCategory/*</name>*/[] values = new /*<name>*/CIFilterCategory/*</name>*/[] {/*<value_list>*/DistortionEffect, GeometryAdjustment, CompositeOperation, HalftoneEffect, ColorAdjustment, ColorEffect, Transition, TileEffect, Generator, Reduction, Gradient, Stylize, Sharpen, Blur, Video, StillImage, Interlaced, NonSquarePixels, HighDynamicRange, BuiltIn/*</value_list>*/};
    
    /*<name>*/CIFilterCategory/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CIFilterCategory/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CIFilterCategory/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CIFilterCategory/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreImage") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="kCICategoryDistortionEffect", optional=true)
        public static native NSString DistortionEffect();
        @GlobalValue(symbol="kCICategoryGeometryAdjustment", optional=true)
        public static native NSString GeometryAdjustment();
        @GlobalValue(symbol="kCICategoryCompositeOperation", optional=true)
        public static native NSString CompositeOperation();
        @GlobalValue(symbol="kCICategoryHalftoneEffect", optional=true)
        public static native NSString HalftoneEffect();
        @GlobalValue(symbol="kCICategoryColorAdjustment", optional=true)
        public static native NSString ColorAdjustment();
        @GlobalValue(symbol="kCICategoryColorEffect", optional=true)
        public static native NSString ColorEffect();
        @GlobalValue(symbol="kCICategoryTransition", optional=true)
        public static native NSString Transition();
        @GlobalValue(symbol="kCICategoryTileEffect", optional=true)
        public static native NSString TileEffect();
        @GlobalValue(symbol="kCICategoryGenerator", optional=true)
        public static native NSString Generator();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCICategoryReduction", optional=true)
        public static native NSString Reduction();
        @GlobalValue(symbol="kCICategoryGradient", optional=true)
        public static native NSString Gradient();
        @GlobalValue(symbol="kCICategoryStylize", optional=true)
        public static native NSString Stylize();
        @GlobalValue(symbol="kCICategorySharpen", optional=true)
        public static native NSString Sharpen();
        @GlobalValue(symbol="kCICategoryBlur", optional=true)
        public static native NSString Blur();
        @GlobalValue(symbol="kCICategoryVideo", optional=true)
        public static native NSString Video();
        @GlobalValue(symbol="kCICategoryStillImage", optional=true)
        public static native NSString StillImage();
        @GlobalValue(symbol="kCICategoryInterlaced", optional=true)
        public static native NSString Interlaced();
        @GlobalValue(symbol="kCICategoryNonSquarePixels", optional=true)
        public static native NSString NonSquarePixels();
        @GlobalValue(symbol="kCICategoryHighDynamicRange", optional=true)
        public static native NSString HighDynamicRange();
        @GlobalValue(symbol="kCICategoryBuiltIn", optional=true)
        public static native NSString BuiltIn();
        /*</values>*/
    }
}
