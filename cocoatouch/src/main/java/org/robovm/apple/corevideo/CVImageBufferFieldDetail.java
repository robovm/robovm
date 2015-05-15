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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreVideo")/*</annotations>*/
@Marshaler(/*<name>*/CVImageBufferFieldDetail/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVImageBufferFieldDetail/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CVImageBufferFieldDetail/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CVImageBufferFieldDetail toObject(Class<CVImageBufferFieldDetail> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CVImageBufferFieldDetail.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CVImageBufferFieldDetail o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CVImageBufferFieldDetail> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CVImageBufferFieldDetail> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CVImageBufferFieldDetail.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CVImageBufferFieldDetail> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CVImageBufferFieldDetail i : l) {
                array.add(i.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferFieldDetail TemporalTopFirst = new CVImageBufferFieldDetail("TemporalTopFirst");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferFieldDetail TemporalBottomFirst = new CVImageBufferFieldDetail("TemporalBottomFirst");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferFieldDetail SpatialFirstLineEarly = new CVImageBufferFieldDetail("SpatialFirstLineEarly");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferFieldDetail SpatialFirstLineLate = new CVImageBufferFieldDetail("SpatialFirstLineLate");
    /*</constants>*/
    
    private static /*<name>*/CVImageBufferFieldDetail/*</name>*/[] values = new /*<name>*/CVImageBufferFieldDetail/*</name>*/[] {/*<value_list>*/TemporalTopFirst, TemporalBottomFirst, SpatialFirstLineEarly, SpatialFirstLineLate/*</value_list>*/};
    
    /*<name>*/CVImageBufferFieldDetail/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CVImageBufferFieldDetail/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CVImageBufferFieldDetail/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CVImageBufferFieldDetail/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreVideo")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferFieldDetailTemporalTopFirst", optional=true)
        public static native CFString TemporalTopFirst();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferFieldDetailTemporalBottomFirst", optional=true)
        public static native CFString TemporalBottomFirst();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferFieldDetailSpatialFirstLineEarly", optional=true)
        public static native CFString SpatialFirstLineEarly();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferFieldDetailSpatialFirstLineLate", optional=true)
        public static native CFString SpatialFirstLineLate();
        /*</values>*/
    }
}
