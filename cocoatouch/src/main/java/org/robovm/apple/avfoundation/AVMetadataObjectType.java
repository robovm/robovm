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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.mediatoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(AVMetadataObjectType.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMetadataObjectType/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static AVMetadataObjectType toObject(Class<AVMetadataObjectType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMetadataObjectType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMetadataObjectType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVMetadataObjectType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVMetadataObjectType> list = new ArrayList<>();
            for (NSString str : o) {
                list.add(AVMetadataObjectType.valueOf(str));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVMetadataObjectType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSString> array = new NSMutableArray<>();
            for (AVMetadataObjectType i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVMetadataObjectType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVMetadataObjectType Face = new AVMetadataObjectType("FaceValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataObjectType UPCECode = new AVMetadataObjectType("UPCECodeValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataObjectType Code39Code = new AVMetadataObjectType("Code39CodeValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataObjectType Code39Mod43Code = new AVMetadataObjectType("Code39Mod43CodeValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataObjectType EAN13Code = new AVMetadataObjectType("EAN13CodeValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataObjectType EAN8Code = new AVMetadataObjectType("EAN8CodeValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataObjectType Code93Code = new AVMetadataObjectType("Code93CodeValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataObjectType Code128Code = new AVMetadataObjectType("Code128CodeValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataObjectType PDF417Code = new AVMetadataObjectType("PDF417CodeValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataObjectType QRCode = new AVMetadataObjectType("QRCodeValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVMetadataObjectType AztecCode = new AVMetadataObjectType("AztecCodeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataObjectType Interleaved2of5Code = new AVMetadataObjectType("Interleaved2of5CodeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataObjectType ITF14Code = new AVMetadataObjectType("ITF14CodeValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final AVMetadataObjectType DataMatrixCode = new AVMetadataObjectType("DataMatrixCodeValue");
    
    private static AVMetadataObjectType[] values = new AVMetadataObjectType[] {Face, UPCECode, Code39Code, Code39Mod43Code, 
        EAN13Code, EAN8Code, Code93Code, Code128Code, PDF417Code, QRCode, AztecCode, Interleaved2of5Code, ITF14Code, DataMatrixCode};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVMetadataObjectType(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVMetadataObjectType valueOf(NSString value) {
        for (AVMetadataObjectType v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVMetadataObjectType/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeFace", optional=true)
    protected static native NSString FaceValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeUPCECode", optional=true)
    protected static native NSString UPCECodeValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeCode39Code", optional=true)
    protected static native NSString Code39CodeValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeCode39Mod43Code", optional=true)
    protected static native NSString Code39Mod43CodeValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeEAN13Code", optional=true)
    protected static native NSString EAN13CodeValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeEAN8Code", optional=true)
    protected static native NSString EAN8CodeValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeCode93Code", optional=true)
    protected static native NSString Code93CodeValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeCode128Code", optional=true)
    protected static native NSString Code128CodeValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypePDF417Code", optional=true)
    protected static native NSString PDF417CodeValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeQRCode", optional=true)
    protected static native NSString QRCodeValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeAztecCode", optional=true)
    protected static native NSString AztecCodeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeInterleaved2of5Code", optional=true)
    protected static native NSString Interleaved2of5CodeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeITF14Code", optional=true)
    protected static native NSString ITF14CodeValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="AVMetadataObjectTypeDataMatrixCode", optional=true)
    protected static native NSString DataMatrixCodeValue();
    /*</methods>*/
}
