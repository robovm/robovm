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
@Marshaler(/*<name>*/CGImageMetadataPrefix/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImageMetadataPrefix/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CGImageMetadataPrefix/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CGImageMetadataPrefix> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CGImageMetadataPrefix> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CGImageMetadataPrefix.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CGImageMetadataPrefix> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CGImageMetadataPrefix o : l) {
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
    public static final CGImageMetadataPrefix Exif = new CGImageMetadataPrefix("Exif");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataPrefix ExifAux = new CGImageMetadataPrefix("ExifAux");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataPrefix ExifEX = new CGImageMetadataPrefix("ExifEX");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataPrefix DublinCore = new CGImageMetadataPrefix("DublinCore");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataPrefix IPTCCore = new CGImageMetadataPrefix("IPTCCore");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataPrefix Photoshop = new CGImageMetadataPrefix("Photoshop");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataPrefix TIFF = new CGImageMetadataPrefix("TIFF");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataPrefix XMPBasic = new CGImageMetadataPrefix("XMPBasic");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CGImageMetadataPrefix XMPRights = new CGImageMetadataPrefix("XMPRights");
    /*</constants>*/
    
    private static /*<name>*/CGImageMetadataPrefix/*</name>*/[] values = new /*<name>*/CGImageMetadataPrefix/*</name>*/[] {/*<value_list>*/Exif, ExifAux, ExifEX, DublinCore, IPTCCore, Photoshop, TIFF, XMPBasic, XMPRights/*</value_list>*/};
    
    /*<name>*/CGImageMetadataPrefix/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CGImageMetadataPrefix/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CGImageMetadataPrefix/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImageMetadataPrefix/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("ImageIO") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataPrefixExif", optional=true)
        public static native CFString Exif();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataPrefixExifAux", optional=true)
        public static native CFString ExifAux();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataPrefixExifEX", optional=true)
        public static native CFString ExifEX();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataPrefixDublinCore", optional=true)
        public static native CFString DublinCore();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataPrefixIPTCCore", optional=true)
        public static native CFString IPTCCore();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataPrefixPhotoshop", optional=true)
        public static native CFString Photoshop();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataPrefixTIFF", optional=true)
        public static native CFString TIFF();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataPrefixXMPBasic", optional=true)
        public static native CFString XMPBasic();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageMetadataPrefixXMPRights", optional=true)
        public static native CFString XMPRights();
        /*</values>*/
    }
}
