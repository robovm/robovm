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
@Marshaler(/*<name>*/CGImagePropertyExifAux/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyExifAux/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CGImagePropertyExifAux/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyExifAux toObject(Class<CGImagePropertyExifAux> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImagePropertyExifAux.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyExifAux o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CGImagePropertyExifAux> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CGImagePropertyExifAux> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CGImagePropertyExifAux.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CGImagePropertyExifAux> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CGImagePropertyExifAux o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExifAux LensInfo = new CGImagePropertyExifAux("LensInfo");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExifAux LensModel = new CGImagePropertyExifAux("LensModel");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExifAux SerialNumber = new CGImagePropertyExifAux("SerialNumber");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExifAux LensID = new CGImagePropertyExifAux("LensID");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExifAux LensSerialNumber = new CGImagePropertyExifAux("LensSerialNumber");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExifAux ImageNumber = new CGImagePropertyExifAux("ImageNumber");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExifAux FlashCompensation = new CGImagePropertyExifAux("FlashCompensation");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExifAux OwnerName = new CGImagePropertyExifAux("OwnerName");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyExifAux Firmware = new CGImagePropertyExifAux("Firmware");
    /*</constants>*/
    
    private static /*<name>*/CGImagePropertyExifAux/*</name>*/[] values = new /*<name>*/CGImagePropertyExifAux/*</name>*/[] {/*<value_list>*/LensInfo, LensModel, SerialNumber, LensID, LensSerialNumber, ImageNumber, FlashCompensation, OwnerName, Firmware/*</value_list>*/};
    
    /*<name>*/CGImagePropertyExifAux/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CGImagePropertyExifAux/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CGImagePropertyExifAux/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyExifAux/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("ImageIO") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifAuxLensInfo", optional=true)
        public static native CFString LensInfo();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifAuxLensModel", optional=true)
        public static native CFString LensModel();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifAuxSerialNumber", optional=true)
        public static native CFString SerialNumber();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifAuxLensID", optional=true)
        public static native CFString LensID();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifAuxLensSerialNumber", optional=true)
        public static native CFString LensSerialNumber();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifAuxImageNumber", optional=true)
        public static native CFString ImageNumber();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifAuxFlashCompensation", optional=true)
        public static native CFString FlashCompensation();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifAuxOwnerName", optional=true)
        public static native CFString OwnerName();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyExifAuxFirmware", optional=true)
        public static native CFString Firmware();
        /*</values>*/
    }
}
