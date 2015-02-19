/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreVideo")/*</annotations>*/
@Marshaler(/*<name>*/CVImageBufferAttribute/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVImageBufferAttribute/*</name>*/ 
    extends /*<extends>*/CVBufferAttribute/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CVImageBufferAttribute/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CVImageBufferAttribute toObject(Class<CVImageBufferAttribute> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CVImageBufferAttribute.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CVImageBufferAttribute o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CVImageBufferAttribute> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CVImageBufferAttribute> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CVImageBufferAttribute.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CVImageBufferAttribute> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CVImageBufferAttribute i : l) {
                array.add(i.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferAttribute CGColorSpace = new CVImageBufferAttribute("CGColorSpace");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferAttribute CleanAperture = new CVImageBufferAttribute("CleanAperture");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferAttribute PreferredCleanAperture = new CVImageBufferAttribute("PreferredCleanAperture");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferAttribute FieldCount = new CVImageBufferAttribute("FieldCount");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferAttribute FieldDetail = new CVImageBufferAttribute("FieldDetail");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferAttribute PixelAspectRatio = new CVImageBufferAttribute("PixelAspectRatio");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferAttribute DisplayDimensions = new CVImageBufferAttribute("DisplayDimensions");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferAttribute GammaLevel = new CVImageBufferAttribute("GammaLevel");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferAttribute ICCProfile = new CVImageBufferAttribute("ICCProfile");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferAttribute YCbCrMatrix = new CVImageBufferAttribute("YCbCrMatrix");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferAttribute ColorPrimaries = new CVImageBufferAttribute("ColorPrimaries");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferAttribute TransferFunction = new CVImageBufferAttribute("TransferFunction");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferAttribute ChromaLocationTopField = new CVImageBufferAttribute("ChromaLocationTopField");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferAttribute ChromaLocationBottomField = new CVImageBufferAttribute("ChromaLocationBottomField");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferAttribute ChromaSubsampling = new CVImageBufferAttribute("ChromaSubsampling");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CVImageBufferAttribute AlphaChannelIsOpaque = new CVImageBufferAttribute("AlphaChannelIsOpaque");
    /*</constants>*/
    
    private static /*<name>*/CVImageBufferAttribute/*</name>*/[] values = new /*<name>*/CVImageBufferAttribute/*</name>*/[] {/*<value_list>*/CGColorSpace, CleanAperture, PreferredCleanAperture, FieldCount, FieldDetail, PixelAspectRatio, DisplayDimensions, GammaLevel, ICCProfile, YCbCrMatrix, ColorPrimaries, TransferFunction, ChromaLocationTopField, ChromaLocationBottomField, ChromaSubsampling, AlphaChannelIsOpaque/*</value_list>*/};
    
    /*<name>*/CVImageBufferAttribute/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    CVImageBufferAttribute (Class<?> clazz, String getterName) {
        super(clazz, getterName);
    }
    
    public static /*<name>*/CVImageBufferAttribute/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CVImageBufferAttribute/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CVImageBufferAttribute/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreVideo")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferCGColorSpaceKey", optional=true)
        public static native CFString CGColorSpace();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferCleanApertureKey", optional=true)
        public static native CFString CleanAperture();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferPreferredCleanApertureKey", optional=true)
        public static native CFString PreferredCleanAperture();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferFieldCountKey", optional=true)
        public static native CFString FieldCount();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferFieldDetailKey", optional=true)
        public static native CFString FieldDetail();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferPixelAspectRatioKey", optional=true)
        public static native CFString PixelAspectRatio();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferDisplayDimensionsKey", optional=true)
        public static native CFString DisplayDimensions();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferGammaLevelKey", optional=true)
        public static native CFString GammaLevel();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferICCProfileKey", optional=true)
        public static native CFString ICCProfile();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferYCbCrMatrixKey", optional=true)
        public static native CFString YCbCrMatrix();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferColorPrimariesKey", optional=true)
        public static native CFString ColorPrimaries();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferTransferFunctionKey", optional=true)
        public static native CFString TransferFunction();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferChromaLocationTopFieldKey", optional=true)
        public static native CFString ChromaLocationTopField();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferChromaLocationBottomFieldKey", optional=true)
        public static native CFString ChromaLocationBottomField();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferChromaSubsamplingKey", optional=true)
        public static native CFString ChromaSubsampling();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferAlphaChannelIsOpaque", optional=true)
        public static native CFString AlphaChannelIsOpaque();
        /*</values>*/
    }
}
