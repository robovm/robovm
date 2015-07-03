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
/*<annotations>*/@Library("ImageIO") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CGImageMetadataNamespace/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImageMetadataNamespace/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CGImageMetadataNamespace/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CGImageMetadataNamespace> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CGImageMetadataNamespace> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CGImageMetadataNamespace.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CGImageMetadataNamespace> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CGImageMetadataNamespace o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataNamespace Exif = new CGImageMetadataNamespace("Exif");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataNamespace ExifAux = new CGImageMetadataNamespace("ExifAux");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataNamespace ExifEX = new CGImageMetadataNamespace("ExifEX");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataNamespace DublinCore = new CGImageMetadataNamespace("DublinCore");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataNamespace IPTCCore = new CGImageMetadataNamespace("IPTCCore");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataNamespace Photoshop = new CGImageMetadataNamespace("Photoshop");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataNamespace TIFF = new CGImageMetadataNamespace("TIFF");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataNamespace XMPBasic = new CGImageMetadataNamespace("XMPBasic");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataNamespace XMPRights = new CGImageMetadataNamespace("XMPRights");
    /*</constants>*/
    
    private static /*<name>*/CGImageMetadataNamespace/*</name>*/[] values = new /*<name>*/CGImageMetadataNamespace/*</name>*/[] {/*<value_list>*/Exif, ExifAux, ExifEX, DublinCore, IPTCCore, Photoshop, TIFF, XMPBasic, XMPRights/*</value_list>*/};
    
    /*<name>*/CGImageMetadataNamespace/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CGImageMetadataNamespace/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CGImageMetadataNamespace/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImageMetadataNamespace/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("ImageIO") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataNamespaceExif", optional=true)
        public static native CFString Exif();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataNamespaceExifAux", optional=true)
        public static native CFString ExifAux();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataNamespaceExifEX", optional=true)
        public static native CFString ExifEX();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataNamespaceDublinCore", optional=true)
        public static native CFString DublinCore();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataNamespaceIPTCCore", optional=true)
        public static native CFString IPTCCore();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataNamespacePhotoshop", optional=true)
        public static native CFString Photoshop();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataNamespaceTIFF", optional=true)
        public static native CFString TIFF();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataNamespaceXMPBasic", optional=true)
        public static native CFString XMPBasic();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataNamespaceXMPRights", optional=true)
        public static native CFString XMPRights();
        /*</values>*/
    }
}
