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
package org.robovm.apple.coreimage;

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
import org.robovm.apple.opengles.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.imageio.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreImage") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CIFormat/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIFormat/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<Integer>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CIFormat/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CIFormat toObject(Class<CIFormat> cls, long handle, long flags) {
            NSNumber o = (NSNumber) NSObject.Marshaler.toObject(NSNumber.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CIFormat.valueOf(o.intValue());
        }
        @MarshalsPointer
        public static long toNative(CIFormat o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(NSNumber.valueOf(o.value()), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CIFormat> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSNumber> o = (NSArray<NSNumber>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CIFormat> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CIFormat.valueOf(o.get(i).intValue()));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CIFormat> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSNumber> array = new NSMutableArray<>();
            for (CIFormat o : l) {
                array.add(NSNumber.valueOf(o.value()));
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CIFormat ARGB8 = new CIFormat("ARGB8");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CIFormat BGRA8 = new CIFormat("BGRA8");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CIFormat RGBA8 = new CIFormat("RGBA8");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CIFormat RGBAf = new CIFormat("RGBAf");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CIFormat RGBAh = new CIFormat("RGBAh");
    /*</constants>*/
    
    private static /*<name>*/CIFormat/*</name>*/[] values = new /*<name>*/CIFormat/*</name>*/[] {/*<value_list>*/ARGB8, BGRA8, RGBA8, RGBAf, RGBAh/*</value_list>*/};
    
    /*<name>*/CIFormat/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CIFormat/*</name>*/ valueOf(/*<type>*/int/*</type>*/ value) {
        for (/*<name>*/CIFormat/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CIFormat/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreImage") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCIFormatARGB8", optional=true)
        public static native int ARGB8();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCIFormatBGRA8", optional=true)
        public static native int BGRA8();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCIFormatRGBA8", optional=true)
        public static native int RGBA8();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCIFormatRGBAf", optional=true)
        public static native int RGBAf();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCIFormatRGBAh", optional=true)
        public static native int RGBAh();
        /*</values>*/
    }
}
