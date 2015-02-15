/*
 * Copyright (C) 2015 Trillian Mobile AB
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
@Marshaler(/*<name>*/CVImageBufferChromaSubsampling/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVImageBufferChromaSubsampling/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CVImageBufferChromaSubsampling/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CVImageBufferChromaSubsampling toObject(Class<CVImageBufferChromaSubsampling> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CVImageBufferChromaSubsampling.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CVImageBufferChromaSubsampling o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CVImageBufferChromaSubsampling> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CVImageBufferChromaSubsampling> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CVImageBufferChromaSubsampling.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CVImageBufferChromaSubsampling> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CVImageBufferChromaSubsampling i : l) {
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
    public static final CVImageBufferChromaSubsampling _420 = new CVImageBufferChromaSubsampling("_420");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferChromaSubsampling _422 = new CVImageBufferChromaSubsampling("_422");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferChromaSubsampling _411 = new CVImageBufferChromaSubsampling("_411");
    /*</constants>*/
    
    private static /*<name>*/CVImageBufferChromaSubsampling/*</name>*/[] values = new /*<name>*/CVImageBufferChromaSubsampling/*</name>*/[] {/*<value_list>*/_420, _422, _411/*</value_list>*/};
    
    /*<name>*/CVImageBufferChromaSubsampling/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CVImageBufferChromaSubsampling/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CVImageBufferChromaSubsampling/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CVImageBufferChromaSubsampling/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreVideo")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferChromaSubsampling_420", optional=true)
        public static native CFString _420();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferChromaSubsampling_422", optional=true)
        public static native CFString _422();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferChromaSubsampling_411", optional=true)
        public static native CFString _411();
        /*</values>*/
    }
}
