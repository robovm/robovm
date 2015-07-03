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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CMMetadataBaseDataType/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMMetadataBaseDataType/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CMMetadataBaseDataType/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CMMetadataBaseDataType toObject(Class<CMMetadataBaseDataType> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CMMetadataBaseDataType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CMMetadataBaseDataType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CMMetadataBaseDataType> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMMetadataBaseDataType> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CMMetadataBaseDataType.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMMetadataBaseDataType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CMMetadataBaseDataType o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType RawData = new CMMetadataBaseDataType("RawData");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType UTF8 = new CMMetadataBaseDataType("UTF8");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType UTF16 = new CMMetadataBaseDataType("UTF16");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType GIF = new CMMetadataBaseDataType("GIF");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType JPEG = new CMMetadataBaseDataType("JPEG");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType PNG = new CMMetadataBaseDataType("PNG");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType BMP = new CMMetadataBaseDataType("BMP");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType Float32 = new CMMetadataBaseDataType("Float32");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType Float64 = new CMMetadataBaseDataType("Float64");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType SInt8 = new CMMetadataBaseDataType("SInt8");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType SInt16 = new CMMetadataBaseDataType("SInt16");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType SInt32 = new CMMetadataBaseDataType("SInt32");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType SInt64 = new CMMetadataBaseDataType("SInt64");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType UInt8 = new CMMetadataBaseDataType("UInt8");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType UInt16 = new CMMetadataBaseDataType("UInt16");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType UInt32 = new CMMetadataBaseDataType("UInt32");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType UInt64 = new CMMetadataBaseDataType("UInt64");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType PointF32 = new CMMetadataBaseDataType("PointF32");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType DimensionsF32 = new CMMetadataBaseDataType("DimensionsF32");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType RectF32 = new CMMetadataBaseDataType("RectF32");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType AffineTransformF64 = new CMMetadataBaseDataType("AffineTransformF64");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType QuickTimeMetadataLocation_ISO6709 = new CMMetadataBaseDataType("QuickTimeMetadataLocation_ISO6709");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataBaseDataType QuickTimeMetadataDirection = new CMMetadataBaseDataType("QuickTimeMetadataDirection");
    /*</constants>*/
    
    private static /*<name>*/CMMetadataBaseDataType/*</name>*/[] values = new /*<name>*/CMMetadataBaseDataType/*</name>*/[] {/*<value_list>*/RawData, UTF8, UTF16, GIF, JPEG, PNG, BMP, Float32, Float64, SInt8, SInt16, SInt32, SInt64, UInt8, UInt16, UInt32, UInt64, PointF32, DimensionsF32, RectF32, AffineTransformF64, QuickTimeMetadataLocation_ISO6709, QuickTimeMetadataDirection/*</value_list>*/};
    
    /*<name>*/CMMetadataBaseDataType/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CMMetadataBaseDataType/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CMMetadataBaseDataType/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CMMetadataBaseDataType/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreMedia") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_RawData", optional=true)
        public static native CFString RawData();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_UTF8", optional=true)
        public static native CFString UTF8();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_UTF16", optional=true)
        public static native CFString UTF16();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_GIF", optional=true)
        public static native CFString GIF();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_JPEG", optional=true)
        public static native CFString JPEG();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_PNG", optional=true)
        public static native CFString PNG();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_BMP", optional=true)
        public static native CFString BMP();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_Float32", optional=true)
        public static native CFString Float32();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_Float64", optional=true)
        public static native CFString Float64();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_SInt8", optional=true)
        public static native CFString SInt8();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_SInt16", optional=true)
        public static native CFString SInt16();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_SInt32", optional=true)
        public static native CFString SInt32();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_SInt64", optional=true)
        public static native CFString SInt64();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_UInt8", optional=true)
        public static native CFString UInt8();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_UInt16", optional=true)
        public static native CFString UInt16();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_UInt32", optional=true)
        public static native CFString UInt32();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_UInt64", optional=true)
        public static native CFString UInt64();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_PointF32", optional=true)
        public static native CFString PointF32();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_DimensionsF32", optional=true)
        public static native CFString DimensionsF32();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_RectF32", optional=true)
        public static native CFString RectF32();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataBaseDataType_AffineTransformF64", optional=true)
        public static native CFString AffineTransformF64();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataDataType_QuickTimeMetadataLocation_ISO6709", optional=true)
        public static native CFString QuickTimeMetadataLocation_ISO6709();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCMMetadataDataType_QuickTimeMetadataDirection", optional=true)
        public static native CFString QuickTimeMetadataDirection();
        /*</values>*/
    }
}
