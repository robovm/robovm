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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CMMetadataDataType.Marshaler.class)
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMMetadataDataType/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CMMetadataDataType toObject(Class<CMMetadataDataType> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CMMetadataDataType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CMMetadataDataType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CMMetadataDataType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType RawData = new CMMetadataDataType("RawDataValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType UTF8 = new CMMetadataDataType("UTF8Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType UTF16 = new CMMetadataDataType("UTF16Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType GIF = new CMMetadataDataType("GIFValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType JPEG = new CMMetadataDataType("JPEGValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType PNG = new CMMetadataDataType("PNGValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType BMP = new CMMetadataDataType("BMPValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType Float32 = new CMMetadataDataType("Float32Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType Float64 = new CMMetadataDataType("Float64Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType SInt8 = new CMMetadataDataType("SInt8Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType SInt16 = new CMMetadataDataType("SInt16Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType SInt32 = new CMMetadataDataType("SInt32Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType SInt64 = new CMMetadataDataType("SInt64Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType UInt8 = new CMMetadataDataType("UInt8Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType UInt16 = new CMMetadataDataType("UInt16Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType UInt32 = new CMMetadataDataType("UInt32Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType UInt64 = new CMMetadataDataType("UInt64Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType PointF32 = new CMMetadataDataType("PointF32Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType DimensionsF32 = new CMMetadataDataType("DimensionsF32Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType RectF32 = new CMMetadataDataType("RectF32Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType AffineTransformF64 = new CMMetadataDataType("AffineTransformF64Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType QuickTimeMetadataLocation_ISO6709 = new CMMetadataDataType("QuickTimeMetadataLocation_ISO6709Value");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CMMetadataDataType QuickTimeMetadataDirection = new CMMetadataDataType("QuickTimeMetadataDirectionValue");
    
    private static CMMetadataDataType[] values = new CMMetadataDataType[] {RawData, UTF8, UTF16, GIF, JPEG, PNG, BMP, Float32, Float64, SInt8, 
        SInt16, SInt32, SInt64, UInt8, UInt16, UInt32, UInt64, PointF32, DimensionsF32, RectF32, AffineTransformF64, QuickTimeMetadataLocation_ISO6709, 
        QuickTimeMetadataDirection};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CMMetadataDataType(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CMMetadataDataType valueOf(CFString value) {
        for (CMMetadataDataType v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CMMetadataDataType/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_RawData", optional=true)
    protected static native CFString RawDataValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_UTF8", optional=true)
    protected static native CFString UTF8Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_UTF16", optional=true)
    protected static native CFString UTF16Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_GIF", optional=true)
    protected static native CFString GIFValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_JPEG", optional=true)
    protected static native CFString JPEGValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_PNG", optional=true)
    protected static native CFString PNGValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_BMP", optional=true)
    protected static native CFString BMPValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_Float32", optional=true)
    protected static native CFString Float32Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_Float64", optional=true)
    protected static native CFString Float64Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_SInt8", optional=true)
    protected static native CFString SInt8Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_SInt16", optional=true)
    protected static native CFString SInt16Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_SInt32", optional=true)
    protected static native CFString SInt32Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_SInt64", optional=true)
    protected static native CFString SInt64Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_UInt8", optional=true)
    protected static native CFString UInt8Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_UInt16", optional=true)
    protected static native CFString UInt16Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_UInt32", optional=true)
    protected static native CFString UInt32Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_UInt64", optional=true)
    protected static native CFString UInt64Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_PointF32", optional=true)
    protected static native CFString PointF32Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_DimensionsF32", optional=true)
    protected static native CFString DimensionsF32Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_RectF32", optional=true)
    protected static native CFString RectF32Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataBaseDataType_AffineTransformF64", optional=true)
    protected static native CFString AffineTransformF64Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataDataType_QuickTimeMetadataLocation_ISO6709", optional=true)
    protected static native CFString QuickTimeMetadataLocation_ISO6709Value();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCMMetadataDataType_QuickTimeMetadataDirection", optional=true)
    protected static native CFString QuickTimeMetadataDirectionValue();
    /*</methods>*/
}
