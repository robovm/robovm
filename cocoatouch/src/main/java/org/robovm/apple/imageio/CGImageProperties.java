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
package org.robovm.apple.imageio;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
@Marshaler(CGImageProperties.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImageProperties/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static CGImageProperties toObject(Class<CGImageProperties> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CGImageProperties(o);
        }
        @MarshalsPointer
        public static long toNative(CGImageProperties o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    protected CFDictionary data;
    
    public CGImageProperties(CFDictionary data) {
        this.data = data;
    }
    public CGImageProperties() {
        this.data = CFMutableDictionary.create();
    }
    /*<bind>*/static { Bro.bind(CGImageProperties.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFDictionary getDictionary() {
        return data;
    }
    
    
    public CGImagePropertyTIFFData getTIFFData() {
        if (data.containsKey(TIFFDictionaryKey())) {
            CFDictionary val = data.get(TIFFDictionaryKey(), CFDictionary.class);
            return new CGImagePropertyTIFFData(val);
        }
        return null;
    }
    public CGImageProperties setTIFFData(CGImagePropertyTIFFData metadata) {
        data.put(TIFFDictionaryKey(), metadata.getDictionary());
        return this;
    }
    public CGImagePropertyGIFData getGIFData() {
        if (data.containsKey(GIFDictionaryKey())) {
            CFDictionary val = data.get(GIFDictionaryKey(), CFDictionary.class);
            return new CGImagePropertyGIFData(val);
        }
        return null;
    }
    public CGImageProperties setGIFData(CGImagePropertyGIFData metadata) {
        data.put(GIFDictionaryKey(), metadata.getDictionary());
        return this;
    }
    public CGImagePropertyJFIFData getJFIFData() {
        if (data.containsKey(JFIFDictionaryKey())) {
            CFDictionary val = data.get(JFIFDictionaryKey(), CFDictionary.class);
            return new CGImagePropertyJFIFData(val);
        }
        return null;
    }
    public CGImageProperties setJFIFData(CGImagePropertyJFIFData metadata) {
        data.put(JFIFDictionaryKey(), metadata.getDictionary());
        return this;
    }
    public CGImagePropertyExifData getExifData() {
        if (data.containsKey(ExifDictionaryKey())) {
            CFDictionary val = data.get(ExifDictionaryKey(), CFDictionary.class);
            return new CGImagePropertyExifData(val);
        }
        return null;
    }
    public CGImageProperties setExifData(CGImagePropertyExifData metadata) {
        data.put(ExifDictionaryKey(), metadata.getDictionary());
        return this;
    }
    public CGImagePropertyPNGData getPNGData() {
        if (data.containsKey(PNGDictionaryKey())) {
            CFDictionary val = data.get(PNGDictionaryKey(), CFDictionary.class);
            return new CGImagePropertyPNGData(val);
        }
        return null;
    }
    public CGImageProperties setPNGData(CGImagePropertyPNGData metadata) {
        data.put(PNGDictionaryKey(), metadata.getDictionary());
        return this;
    }
    public CGImagePropertyIPTCData getIPTCData() {
        if (data.containsKey(IPTCDictionaryKey())) {
            CFDictionary val = data.get(IPTCDictionaryKey(), CFDictionary.class);
            return new CGImagePropertyIPTCData(val);
        }
        return null;
    }
    public CGImageProperties setIPTCData(CGImagePropertyIPTCData metadata) {
        data.put(PNGDictionaryKey(), metadata.getDictionary());
        return this;
    }
    public CGImagePropertyGPSData getGPSData() {
        if (data.containsKey(GPSDictionaryKey())) {
            CFDictionary val = data.get(GPSDictionaryKey(), CFDictionary.class);
            return new CGImagePropertyGPSData(val);
        }
        return null;
    }
    public CGImageProperties setGPSData(CGImagePropertyGPSData metadata) {
        data.put(GPSDictionaryKey(), metadata.getDictionary());
        return this;
    }
    public CFDictionary getRawData() {
        if (data.containsKey(RawDictionaryKey())) {
            CFDictionary val = data.get(RawDictionaryKey(), CFDictionary.class);
            return val;
        }
        return null;
    }
    public CGImageProperties setRawData(CFDictionary metadata) {
        data.put(RawDictionaryKey(), metadata);
        return this;
    }
    public CGImagePropertyCIFFData getCIFFData() {
        if (data.containsKey(CIFFDictionaryKey())) {
            CFDictionary val = data.get(CIFFDictionaryKey(), CFDictionary.class);
            return new CGImagePropertyCIFFData(val);
        }
        return null;
    }
    public CGImageProperties setCIFFData(CGImagePropertyCIFFData metadata) {
        data.put(CIFFDictionaryKey(), metadata.getDictionary());
        return this;
    }
    public CGImageProperty8BIMData get8BIMData() {
        if (data.containsKey(_8BIMDictionaryKey())) {
            CFDictionary val = data.get(_8BIMDictionaryKey(), CFDictionary.class);
            return new CGImageProperty8BIMData(val);
        }
        return null;
    }
    public CGImageProperties set8BIMData(CGImageProperty8BIMData metadata) {
        data.put(_8BIMDictionaryKey(), metadata.getDictionary());
        return this;
    }
    public CGImagePropertyDNGData getDNGData() {
        if (data.containsKey(DNGDictionaryKey())) {
            CFDictionary val = data.get(DNGDictionaryKey(), CFDictionary.class);
            return new CGImagePropertyDNGData(val);
        }
        return null;
    }
    public CGImageProperties setDNGData(CGImagePropertyDNGData metadata) {
        data.put(DNGDictionaryKey(), metadata.getDictionary());
        return this;
    }
    public CGImagePropertyExifAuxData getExifAuxData() {
        if (data.containsKey(ExifAuxDictionaryKey())) {
            CFDictionary val = data.get(ExifAuxDictionaryKey(), CFDictionary.class);
            return new CGImagePropertyExifAuxData(val);
        }
        return null;
    }
    public CGImageProperties setExifAuxData(CGImagePropertyExifAuxData metadata) {
        data.put(ExifAuxDictionaryKey(), metadata.getDictionary());
        return this;
    }
    public CGImagePropertyCanonData getMakerCanonData() {
        if (data.containsKey(MakerCanonDictionaryKey())) {
            CFDictionary val = data.get(MakerCanonDictionaryKey(), CFDictionary.class);
            return new CGImagePropertyCanonData(val);
        }
        return null;
    }
    public CGImageProperties setMakerCanonData(CGImagePropertyCanonData metadata) {
        data.put(MakerCanonDictionaryKey(), metadata.getDictionary());
        return this;
    }
    public CGImagePropertyNikonData getMakerNikonData() {
        if (data.containsKey(MakerNikonDictionaryKey())) {
            CFDictionary val = data.get(MakerNikonDictionaryKey(), CFDictionary.class);
            return new CGImagePropertyNikonData(val);
        }
        return null;
    }
    public CGImageProperties setMakerNikonData(CGImagePropertyNikonData metadata) {
        data.put(MakerNikonDictionaryKey(), metadata.getDictionary());
        return this;
    }
    public CFDictionary getMakerMinoltaData() {
        if (data.containsKey(MakerMinoltaDictionaryKey())) {
            CFDictionary val = data.get(MakerMinoltaDictionaryKey(), CFDictionary.class);
            return val;
        }
        return null;
    }
    public CGImageProperties setMakerMinoltaData(CFDictionary metadata) {
        data.put(MakerMinoltaDictionaryKey(), metadata);
        return this;
    }
    public CFDictionary getMakerFujiData() {
        if (data.containsKey(MakerFujiDictionaryKey())) {
            CFDictionary val = data.get(MakerFujiDictionaryKey(), CFDictionary.class);
            return val;
        }
        return null;
    }
    public CGImageProperties setMakerFujiData(CFDictionary metadata) {
        data.put(MakerFujiDictionaryKey(), metadata);
        return this;
    }
    public CFDictionary getMakerOlympusData() {
        if (data.containsKey(MakerOlympusDictionaryKey())) {
            CFDictionary val = data.get(MakerOlympusDictionaryKey(), CFDictionary.class);
            return val;
        }
        return null;
    }
    public CGImageProperties setMakerOlympusData(CFDictionary metadata) {
        data.put(MakerOlympusDictionaryKey(), metadata);
        return this;
    }
    public CFDictionary getMakerPentaxData() {
        if (data.containsKey(MakerPentaxDictionaryKey())) {
            CFDictionary val = data.get(MakerPentaxDictionaryKey(), CFDictionary.class);
            return val;
        }
        return null;
    }
    public CGImageProperties setMakerPentaxData(CFDictionary metadata) {
        data.put(MakerPentaxDictionaryKey(), metadata);
        return this;
    }
    
    public long getFileSize() {
        if (data.containsKey(FileSizeKey())) {
            CFNumber val = data.get(FileSizeKey(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    public CGImageProperties setFileSize(long fileSize) {
        data.put(FileSizeKey(), CFNumber.valueOf(fileSize));
        return this;
    }
    
    public long getDPIHeight() {
        if (data.containsKey(DPIHeightKey())) {
            CFNumber val = data.get(DPIHeightKey(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    public CGImageProperties setDPIHeight(long dpi) {
        data.put(DPIHeightKey(), CFNumber.valueOf(dpi));
        return this;
    }
    public long getDPIWidth() {
        if (data.containsKey(DPIWidthKey())) {
            CFNumber val = data.get(DPIWidthKey(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    public CGImageProperties setDPIWidth(long dpi) {
        data.put(DPIWidthKey(), CFNumber.valueOf(dpi));
        return this;
    }
    public long getPixelWidth() {
        if (data.containsKey(PixelWidthKey())) {
            CFNumber val = data.get(PixelWidthKey(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    public CGImageProperties setPixelWidth(long width) {
        data.put(PixelWidthKey(), CFNumber.valueOf(width));
        return this;
    }
    public long getPixelHeight() {
        if (data.containsKey(PixelHeightKey())) {
            CFNumber val = data.get(PixelHeightKey(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    public CGImageProperties setPixelHeight(long height) {
        data.put(PixelHeightKey(), CFNumber.valueOf(height));
        return this;
    }
    public int getDepth() {
        if (data.containsKey(DepthKey())) {
            CFNumber val = data.get(DepthKey(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    public CGImageProperties setDepth(int depth) {
        data.put(DepthKey(), CFNumber.valueOf(depth));
        return this;
    }
    public CGImagePropertyOrientation getOrientation() {
        if (data.containsKey(OrientationKey())) {
            CFNumber val = data.get(OrientationKey(), CFNumber.class);
            return CGImagePropertyOrientation.valueOf(val.longValue());
        }
        return null;
    }
    public CGImageProperties setOrientation(CGImagePropertyOrientation orientation) {
        data.put(OrientationKey(), CFNumber.valueOf(orientation.value()));
        return this;
    }
    public boolean isContainingFloatingPointPixels() {
        if (data.containsKey(IsFloatKey())) {
            CFBoolean val = data.get(IsFloatKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    public CGImageProperties setContainsFloatingPointPixels(boolean isFloat) {
        data.put(IsFloatKey(), CFBoolean.valueOf(isFloat));
        return this;
    }
    public boolean isIndexed() {
        if (data.containsKey(IsIndexedKey())) {
            CFBoolean val = data.get(IsIndexedKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    public CGImageProperties setIndexed(boolean isIndexed) {
        data.put(IsIndexedKey(), CFBoolean.valueOf(isIndexed));
        return this;
    }
    public boolean hasAlphaChannel() {
        if (data.containsKey(HasAlphaKey())) {
            CFBoolean val = data.get(HasAlphaKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    public CGImageProperties setHasAlphaChannel(boolean alphaChannel) {
        data.put(HasAlphaKey(), CFBoolean.valueOf(alphaChannel));
        return this;
    }
    public CGImagePropertyColorModel getColorModel() {
        if (data.containsKey(ColorModelKey())) {
            CFString val = data.get(ColorModelKey(), CFString.class);
            return CGImagePropertyColorModel.valueOf(val);
        }
        return null;
    }
    public CGImageProperties setColorModel(CGImagePropertyColorModel colorModel) {
        data.put(ColorModelKey(), colorModel.value());
        return this;
    }
    public String getICCProfile() {
        if (data.containsKey(ProfileNameKey())) {
            CFString val = data.get(ProfileNameKey(), CFString.class);
            return val.toString();
        }
        return null;
    }
    public CGImageProperties setICCProfile(String profile) {
        data.put(ProfileNameKey(), new CFString(profile));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyTIFFDictionary", optional=true)
    protected static native CFString TIFFDictionaryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGIFDictionary", optional=true)
    protected static native CFString GIFDictionaryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyJFIFDictionary", optional=true)
    protected static native CFString JFIFDictionaryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifDictionary", optional=true)
    protected static native CFString ExifDictionaryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyPNGDictionary", optional=true)
    protected static native CFString PNGDictionaryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIPTCDictionary", optional=true)
    protected static native CFString IPTCDictionaryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSDictionary", optional=true)
    protected static native CFString GPSDictionaryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyRawDictionary", optional=true)
    protected static native CFString RawDictionaryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyCIFFDictionary", optional=true)
    protected static native CFString CIFFDictionaryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerCanonDictionary", optional=true)
    protected static native CFString MakerCanonDictionaryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerNikonDictionary", optional=true)
    protected static native CFString MakerNikonDictionaryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerMinoltaDictionary", optional=true)
    protected static native CFString MakerMinoltaDictionaryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerFujiDictionary", optional=true)
    protected static native CFString MakerFujiDictionaryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerOlympusDictionary", optional=true)
    protected static native CFString MakerOlympusDictionaryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerPentaxDictionary", optional=true)
    protected static native CFString MakerPentaxDictionaryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImageProperty8BIMDictionary", optional=true)
    protected static native CFString _8BIMDictionaryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyDNGDictionary", optional=true)
    protected static native CFString DNGDictionaryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyExifAuxDictionary", optional=true)
    protected static native CFString ExifAuxDictionaryKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyMakerAppleDictionary", optional=true)
    protected static native CFString MakerAppleDictionaryKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyFileSize", optional=true)
    protected static native CFString FileSizeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyPixelHeight", optional=true)
    protected static native CFString PixelHeightKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyPixelWidth", optional=true)
    protected static native CFString PixelWidthKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyDPIHeight", optional=true)
    protected static native CFString DPIHeightKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyDPIWidth", optional=true)
    protected static native CFString DPIWidthKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyDepth", optional=true)
    protected static native CFString DepthKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyOrientation", optional=true)
    protected static native CFString OrientationKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIsFloat", optional=true)
    protected static native CFString IsFloatKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyIsIndexed", optional=true)
    protected static native CFString IsIndexedKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyHasAlpha", optional=true)
    protected static native CFString HasAlphaKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyColorModel", optional=true)
    protected static native CFString ColorModelKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyProfileName", optional=true)
    protected static native CFString ProfileNameKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
