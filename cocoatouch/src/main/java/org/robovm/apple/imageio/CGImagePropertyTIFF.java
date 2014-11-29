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
@Marshaler(CGImagePropertyTIFF.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyTIFF/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyTIFF toObject(Class<CGImagePropertyTIFF> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImagePropertyTIFF.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyTIFF o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImagePropertyTIFF.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyTIFF Compression = new CGImagePropertyTIFF("CompressionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyTIFF PhotometricInterpretation = new CGImagePropertyTIFF("PhotometricInterpretationKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyTIFF DocumentName = new CGImagePropertyTIFF("DocumentNameKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyTIFF ImageDescription = new CGImagePropertyTIFF("ImageDescriptionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyTIFF Make = new CGImagePropertyTIFF("MakeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyTIFF Model = new CGImagePropertyTIFF("ModelKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyTIFF Orientation = new CGImagePropertyTIFF("OrientationKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyTIFF XResolution = new CGImagePropertyTIFF("XResolutionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyTIFF YResolution = new CGImagePropertyTIFF("YResolutionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyTIFF ResolutionUnit = new CGImagePropertyTIFF("ResolutionUnitKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyTIFF Software = new CGImagePropertyTIFF("SoftwareKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyTIFF TransferFunction = new CGImagePropertyTIFF("TransferFunctionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyTIFF DateTime = new CGImagePropertyTIFF("DateTimeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyTIFF Artist = new CGImagePropertyTIFF("ArtistKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyTIFF HostComputer = new CGImagePropertyTIFF("HostComputerKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyTIFF Copyright = new CGImagePropertyTIFF("CopyrightKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyTIFF WhitePoint = new CGImagePropertyTIFF("WhitePointKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyTIFF PrimaryChromaticities = new CGImagePropertyTIFF("PrimaryChromaticitiesKey");
    
    private static CGImagePropertyTIFF[] values = new CGImagePropertyTIFF[] {Compression, PhotometricInterpretation, DocumentName, 
        ImageDescription, Make, Model, Orientation, XResolution, YResolution, ResolutionUnit, Software, TransferFunction, 
        DateTime, Artist, HostComputer, Copyright, WhitePoint, PrimaryChromaticities};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CGImagePropertyTIFF(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CGImagePropertyTIFF valueOf(CFString value) {
        for (CGImagePropertyTIFF v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyTIFF/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyTIFFCompression", optional=true)
    protected static native CFString CompressionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyTIFFPhotometricInterpretation", optional=true)
    protected static native CFString PhotometricInterpretationKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyTIFFDocumentName", optional=true)
    protected static native CFString DocumentNameKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyTIFFImageDescription", optional=true)
    protected static native CFString ImageDescriptionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyTIFFMake", optional=true)
    protected static native CFString MakeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyTIFFModel", optional=true)
    protected static native CFString ModelKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyTIFFOrientation", optional=true)
    protected static native CFString OrientationKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyTIFFXResolution", optional=true)
    protected static native CFString XResolutionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyTIFFYResolution", optional=true)
    protected static native CFString YResolutionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyTIFFResolutionUnit", optional=true)
    protected static native CFString ResolutionUnitKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyTIFFSoftware", optional=true)
    protected static native CFString SoftwareKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyTIFFTransferFunction", optional=true)
    protected static native CFString TransferFunctionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyTIFFDateTime", optional=true)
    protected static native CFString DateTimeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyTIFFArtist", optional=true)
    protected static native CFString ArtistKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyTIFFHostComputer", optional=true)
    protected static native CFString HostComputerKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyTIFFCopyright", optional=true)
    protected static native CFString CopyrightKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyTIFFWhitePoint", optional=true)
    protected static native CFString WhitePointKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyTIFFPrimaryChromaticities", optional=true)
    protected static native CFString PrimaryChromaticitiesKey();
    /*</methods>*/
}
