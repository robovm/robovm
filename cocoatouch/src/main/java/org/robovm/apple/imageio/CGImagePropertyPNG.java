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
@Marshaler(/*<name>*/CGImagePropertyPNG/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyPNG/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CGImagePropertyPNG/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyPNG toObject(Class<CGImagePropertyPNG> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImagePropertyPNG.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyPNG o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CGImagePropertyPNG> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CGImagePropertyPNG> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CGImagePropertyPNG.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CGImagePropertyPNG> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CGImagePropertyPNG o : l) {
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
    public static final CGImagePropertyPNG Gamma = new CGImagePropertyPNG("Gamma");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyPNG InterlaceType = new CGImagePropertyPNG("InterlaceType");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyPNG XPixelsPerMeter = new CGImagePropertyPNG("XPixelsPerMeter");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyPNG YPixelsPerMeter = new CGImagePropertyPNG("YPixelsPerMeter");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyPNG sRGBIntent = new CGImagePropertyPNG("sRGBIntent");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyPNG Chromaticities = new CGImagePropertyPNG("Chromaticities");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyPNG Author = new CGImagePropertyPNG("Author");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyPNG Copyright = new CGImagePropertyPNG("Copyright");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyPNG CreationTime = new CGImagePropertyPNG("CreationTime");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyPNG Description = new CGImagePropertyPNG("Description");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyPNG ModificationTime = new CGImagePropertyPNG("ModificationTime");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyPNG Software = new CGImagePropertyPNG("Software");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final CGImagePropertyPNG Title = new CGImagePropertyPNG("Title");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CGImagePropertyPNG LoopCount = new CGImagePropertyPNG("LoopCount");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CGImagePropertyPNG DelayTime = new CGImagePropertyPNG("DelayTime");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CGImagePropertyPNG UnclampedDelayTime = new CGImagePropertyPNG("UnclampedDelayTime");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CGImagePropertyPNG CompressionFilter = new CGImagePropertyPNG("CompressionFilter");
    /*</constants>*/
    
    private static /*<name>*/CGImagePropertyPNG/*</name>*/[] values = new /*<name>*/CGImagePropertyPNG/*</name>*/[] {/*<value_list>*/Gamma, InterlaceType, XPixelsPerMeter, YPixelsPerMeter, sRGBIntent, Chromaticities, Author, Copyright, CreationTime, Description, ModificationTime, Software, Title, LoopCount, DelayTime, UnclampedDelayTime, CompressionFilter/*</value_list>*/};
    
    /*<name>*/CGImagePropertyPNG/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CGImagePropertyPNG/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CGImagePropertyPNG/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyPNG/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("ImageIO") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyPNGGamma", optional=true)
        public static native CFString Gamma();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyPNGInterlaceType", optional=true)
        public static native CFString InterlaceType();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyPNGXPixelsPerMeter", optional=true)
        public static native CFString XPixelsPerMeter();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyPNGYPixelsPerMeter", optional=true)
        public static native CFString YPixelsPerMeter();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyPNGsRGBIntent", optional=true)
        public static native CFString sRGBIntent();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyPNGChromaticities", optional=true)
        public static native CFString Chromaticities();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyPNGAuthor", optional=true)
        public static native CFString Author();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyPNGCopyright", optional=true)
        public static native CFString Copyright();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyPNGCreationTime", optional=true)
        public static native CFString CreationTime();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyPNGDescription", optional=true)
        public static native CFString Description();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyPNGModificationTime", optional=true)
        public static native CFString ModificationTime();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyPNGSoftware", optional=true)
        public static native CFString Software();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyPNGTitle", optional=true)
        public static native CFString Title();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyAPNGLoopCount", optional=true)
        public static native CFString LoopCount();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyAPNGDelayTime", optional=true)
        public static native CFString DelayTime();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyAPNGUnclampedDelayTime", optional=true)
        public static native CFString UnclampedDelayTime();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyPNGCompressionFilter", optional=true)
        public static native CFString CompressionFilter();
        /*</values>*/
    }
}
