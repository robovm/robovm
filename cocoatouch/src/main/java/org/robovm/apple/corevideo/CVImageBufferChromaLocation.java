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
package org.robovm.apple.corevideo;

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
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreVideo") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CVImageBufferChromaLocation/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVImageBufferChromaLocation/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CVImageBufferChromaLocation/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CVImageBufferChromaLocation toObject(Class<CVImageBufferChromaLocation> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CVImageBufferChromaLocation.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CVImageBufferChromaLocation o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CVImageBufferChromaLocation> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CVImageBufferChromaLocation> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CVImageBufferChromaLocation.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CVImageBufferChromaLocation> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CVImageBufferChromaLocation o : l) {
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
    public static final CVImageBufferChromaLocation Left = new CVImageBufferChromaLocation("Left");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferChromaLocation Center = new CVImageBufferChromaLocation("Center");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferChromaLocation TopLeft = new CVImageBufferChromaLocation("TopLeft");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferChromaLocation Top = new CVImageBufferChromaLocation("Top");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferChromaLocation BottomLeft = new CVImageBufferChromaLocation("BottomLeft");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferChromaLocation Bottom = new CVImageBufferChromaLocation("Bottom");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferChromaLocation DV420 = new CVImageBufferChromaLocation("DV420");
    /*</constants>*/
    
    private static /*<name>*/CVImageBufferChromaLocation/*</name>*/[] values = new /*<name>*/CVImageBufferChromaLocation/*</name>*/[] {/*<value_list>*/Left, Center, TopLeft, Top, BottomLeft, Bottom, DV420/*</value_list>*/};
    
    /*<name>*/CVImageBufferChromaLocation/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CVImageBufferChromaLocation/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CVImageBufferChromaLocation/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CVImageBufferChromaLocation/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreVideo") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferChromaLocation_Left", optional=true)
        public static native CFString Left();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferChromaLocation_Center", optional=true)
        public static native CFString Center();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferChromaLocation_TopLeft", optional=true)
        public static native CFString TopLeft();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferChromaLocation_Top", optional=true)
        public static native CFString Top();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferChromaLocation_BottomLeft", optional=true)
        public static native CFString BottomLeft();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferChromaLocation_Bottom", optional=true)
        public static native CFString Bottom();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferChromaLocation_DV420", optional=true)
        public static native CFString DV420();
        /*</values>*/
    }
}
