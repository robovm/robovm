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
@Marshaler(/*<name>*/CGImagePropertyCanon/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyCanon/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CGImagePropertyCanon/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyCanon toObject(Class<CGImagePropertyCanon> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImagePropertyCanon.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyCanon o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CGImagePropertyCanon> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CGImagePropertyCanon> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CGImagePropertyCanon.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CGImagePropertyCanon> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CGImagePropertyCanon o : l) {
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
    public static final CGImagePropertyCanon OwnerName = new CGImagePropertyCanon("OwnerName");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCanon CameraSerialNumber = new CGImagePropertyCanon("CameraSerialNumber");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCanon ImageSerialNumber = new CGImagePropertyCanon("ImageSerialNumber");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCanon FlashExposureComp = new CGImagePropertyCanon("FlashExposureComp");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCanon ContinuousDrive = new CGImagePropertyCanon("ContinuousDrive");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCanon LensModel = new CGImagePropertyCanon("LensModel");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCanon Firmware = new CGImagePropertyCanon("Firmware");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyCanon AspectRatioInfo = new CGImagePropertyCanon("AspectRatioInfo");
    /*</constants>*/
    
    private static /*<name>*/CGImagePropertyCanon/*</name>*/[] values = new /*<name>*/CGImagePropertyCanon/*</name>*/[] {/*<value_list>*/OwnerName, CameraSerialNumber, ImageSerialNumber, FlashExposureComp, ContinuousDrive, LensModel, Firmware, AspectRatioInfo/*</value_list>*/};
    
    /*<name>*/CGImagePropertyCanon/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CGImagePropertyCanon/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CGImagePropertyCanon/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyCanon/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("ImageIO") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyMakerCanonOwnerName", optional=true)
        public static native CFString OwnerName();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyMakerCanonCameraSerialNumber", optional=true)
        public static native CFString CameraSerialNumber();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyMakerCanonImageSerialNumber", optional=true)
        public static native CFString ImageSerialNumber();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyMakerCanonFlashExposureComp", optional=true)
        public static native CFString FlashExposureComp();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyMakerCanonContinuousDrive", optional=true)
        public static native CFString ContinuousDrive();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyMakerCanonLensModel", optional=true)
        public static native CFString LensModel();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyMakerCanonFirmware", optional=true)
        public static native CFString Firmware();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyMakerCanonAspectRatioInfo", optional=true)
        public static native CFString AspectRatioInfo();
        /*</values>*/
    }
}
