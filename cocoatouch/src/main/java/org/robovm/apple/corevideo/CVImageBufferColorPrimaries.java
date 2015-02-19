/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
@Marshaler(/*<name>*/CVImageBufferColorPrimaries/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVImageBufferColorPrimaries/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CVImageBufferColorPrimaries/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CVImageBufferColorPrimaries toObject(Class<CVImageBufferColorPrimaries> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CVImageBufferColorPrimaries.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CVImageBufferColorPrimaries o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CVImageBufferColorPrimaries> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CVImageBufferColorPrimaries> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CVImageBufferColorPrimaries.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CVImageBufferColorPrimaries> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CVImageBufferColorPrimaries i : l) {
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
    public static final CVImageBufferColorPrimaries ITU_R_709_2 = new CVImageBufferColorPrimaries("ITU_R_709_2");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferColorPrimaries EBU_3213 = new CVImageBufferColorPrimaries("EBU_3213");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CVImageBufferColorPrimaries SMPTE_C = new CVImageBufferColorPrimaries("SMPTE_C");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CVImageBufferColorPrimaries P22 = new CVImageBufferColorPrimaries("P22");
    /*</constants>*/
    
    private static /*<name>*/CVImageBufferColorPrimaries/*</name>*/[] values = new /*<name>*/CVImageBufferColorPrimaries/*</name>*/[] {/*<value_list>*/ITU_R_709_2, EBU_3213, SMPTE_C, P22/*</value_list>*/};
    
    /*<name>*/CVImageBufferColorPrimaries/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CVImageBufferColorPrimaries/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CVImageBufferColorPrimaries/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CVImageBufferColorPrimaries/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreVideo")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferColorPrimaries_ITU_R_709_2", optional=true)
        public static native CFString ITU_R_709_2();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferColorPrimaries_EBU_3213", optional=true)
        public static native CFString EBU_3213();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferColorPrimaries_SMPTE_C", optional=true)
        public static native CFString SMPTE_C();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferColorPrimaries_P22", optional=true)
        public static native CFString P22();
        /*</values>*/
    }
}
