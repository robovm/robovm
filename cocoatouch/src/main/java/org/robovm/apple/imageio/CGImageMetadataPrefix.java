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
package org.robovm.apple.imageio;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CGImageMetadataPrefix.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImageMetadataPrefix/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static CGImageMetadataPrefix toObject(Class<CGImageMetadataPrefix> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImageMetadataPrefix.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImageMetadataPrefix o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImageMetadataPrefix.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataPrefix Exif = new CGImageMetadataPrefix("ExifValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataPrefix ExifAux = new CGImageMetadataPrefix("ExifAuxValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataPrefix ExifEX = new CGImageMetadataPrefix("ExifEXValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataPrefix DublinCore = new CGImageMetadataPrefix("DublinCoreValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataPrefix IPTCCore = new CGImageMetadataPrefix("IPTCCoreValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataPrefix Photoshop = new CGImageMetadataPrefix("PhotoshopValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataPrefix TIFF = new CGImageMetadataPrefix("TIFFValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataPrefix XMPBasic = new CGImageMetadataPrefix("XMPBasicValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataPrefix XMPRights = new CGImageMetadataPrefix("XMPRightsValue");
    
    private static CGImageMetadataPrefix[] values = new CGImageMetadataPrefix[] {Exif, ExifAux, ExifEX, DublinCore, 
        IPTCCore, Photoshop, TIFF, XMPBasic, XMPRights};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CGImageMetadataPrefix(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CGImageMetadataPrefix valueOf(CFString value) {
        for (CGImageMetadataPrefix v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImageMetadataPrefix/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataPrefixExif", optional=true)
    protected static native CFString ExifValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataPrefixExifAux", optional=true)
    protected static native CFString ExifAuxValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataPrefixExifEX", optional=true)
    protected static native CFString ExifEXValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataPrefixDublinCore", optional=true)
    protected static native CFString DublinCoreValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataPrefixIPTCCore", optional=true)
    protected static native CFString IPTCCoreValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataPrefixPhotoshop", optional=true)
    protected static native CFString PhotoshopValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataPrefixTIFF", optional=true)
    protected static native CFString TIFFValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataPrefixXMPBasic", optional=true)
    protected static native CFString XMPBasicValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataPrefixXMPRights", optional=true)
    protected static native CFString XMPRightsValue();
    /*</methods>*/
}
