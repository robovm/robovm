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
@Marshaler(/*<name>*/CGImagePropertyDNG/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyDNG/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CGImagePropertyDNG/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyDNG toObject(Class<CGImagePropertyDNG> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImagePropertyDNG.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyDNG o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CGImagePropertyDNG> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CGImagePropertyDNG> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CGImagePropertyDNG.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CGImagePropertyDNG> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CGImagePropertyDNG o : l) {
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
    public static final CGImagePropertyDNG Version = new CGImagePropertyDNG("Version");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyDNG BackwardVersion = new CGImagePropertyDNG("BackwardVersion");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyDNG UniqueCameraModel = new CGImagePropertyDNG("UniqueCameraModel");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyDNG LocalizedCameraModel = new CGImagePropertyDNG("LocalizedCameraModel");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyDNG CameraSerialNumber = new CGImagePropertyDNG("CameraSerialNumber");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyDNG LensInfo = new CGImagePropertyDNG("LensInfo");
    /*</constants>*/
    
    private static /*<name>*/CGImagePropertyDNG/*</name>*/[] values = new /*<name>*/CGImagePropertyDNG/*</name>*/[] {/*<value_list>*/Version, BackwardVersion, UniqueCameraModel, LocalizedCameraModel, CameraSerialNumber, LensInfo/*</value_list>*/};
    
    /*<name>*/CGImagePropertyDNG/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CGImagePropertyDNG/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CGImagePropertyDNG/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyDNG/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("ImageIO") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyDNGVersion", optional=true)
        public static native CFString Version();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyDNGBackwardVersion", optional=true)
        public static native CFString BackwardVersion();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyDNGUniqueCameraModel", optional=true)
        public static native CFString UniqueCameraModel();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyDNGLocalizedCameraModel", optional=true)
        public static native CFString LocalizedCameraModel();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyDNGCameraSerialNumber", optional=true)
        public static native CFString CameraSerialNumber();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyDNGLensInfo", optional=true)
        public static native CFString LensInfo();
        /*</values>*/
    }
}
