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
@Marshaler(CGImageMetadataNamespace.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImageMetadataNamespace/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CGImageMetadataNamespace toObject(Class<CGImageMetadataNamespace> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImageMetadataNamespace.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImageMetadataNamespace o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImageMetadataNamespace.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataNamespace Exif = new CGImageMetadataNamespace("ExifValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataNamespace ExifAux = new CGImageMetadataNamespace("ExifAuxValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataNamespace ExifEX = new CGImageMetadataNamespace("ExifEXValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataNamespace DublinCore = new CGImageMetadataNamespace("DublinCoreValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataNamespace IPTCCore = new CGImageMetadataNamespace("IPTCCoreValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataNamespace Photoshop = new CGImageMetadataNamespace("PhotoshopValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataNamespace TIFF = new CGImageMetadataNamespace("TIFFValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataNamespace XMPBasic = new CGImageMetadataNamespace("XMPBasicValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataNamespace XMPRights = new CGImageMetadataNamespace("XMPRightsValue");
    
    private static CGImageMetadataNamespace[] values = new CGImageMetadataNamespace[] {Exif, ExifAux, ExifEX, DublinCore, 
        IPTCCore, Photoshop, TIFF, XMPBasic, XMPRights};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CGImageMetadataNamespace(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CGImageMetadataNamespace valueOf(CFString value) {
        for (CGImageMetadataNamespace v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImageMetadataNamespace/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataNamespaceExif", optional=true)
    protected static native CFString ExifValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataNamespaceExifAux", optional=true)
    protected static native CFString ExifAuxValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataNamespaceExifEX", optional=true)
    protected static native CFString ExifEXValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataNamespaceDublinCore", optional=true)
    protected static native CFString DublinCoreValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataNamespaceIPTCCore", optional=true)
    protected static native CFString IPTCCoreValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataNamespacePhotoshop", optional=true)
    protected static native CFString PhotoshopValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataNamespaceTIFF", optional=true)
    protected static native CFString TIFFValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataNamespaceXMPBasic", optional=true)
    protected static native CFString XMPBasicValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageMetadataNamespaceXMPRights", optional=true)
    protected static native CFString XMPRightsValue();
    /*</methods>*/
}
