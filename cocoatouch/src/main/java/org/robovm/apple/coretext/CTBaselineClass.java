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
package org.robovm.apple.coretext;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreText")/*</annotations>*/
@Marshaler(/*<name>*/CTBaselineClass/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTBaselineClass/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CTBaselineClass/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CTBaselineClass toObject(Class<CTBaselineClass> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CTBaselineClass.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CTBaselineClass o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CTBaselineClass> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CTBaselineClass> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CTBaselineClass.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CTBaselineClass> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CTBaselineClass i : l) {
                array.add(i.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CTBaselineClass Roman = new CTBaselineClass("Roman");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CTBaselineClass IdeographicCentered = new CTBaselineClass("IdeographicCentered");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CTBaselineClass IdeographicLow = new CTBaselineClass("IdeographicLow");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CTBaselineClass IdeographicHigh = new CTBaselineClass("IdeographicHigh");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CTBaselineClass Hanging = new CTBaselineClass("Hanging");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final CTBaselineClass Math = new CTBaselineClass("Math");
    /*</constants>*/
    
    private static /*<name>*/CTBaselineClass/*</name>*/[] values = new /*<name>*/CTBaselineClass/*</name>*/[] {/*<value_list>*/Roman, IdeographicCentered, IdeographicLow, IdeographicHigh, Hanging, Math/*</value_list>*/};
    
    /*<name>*/CTBaselineClass/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CTBaselineClass/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CTBaselineClass/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CTBaselineClass/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreText")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCTBaselineClassRoman", optional=true)
        public static native CFString Roman();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCTBaselineClassIdeographicCentered", optional=true)
        public static native CFString IdeographicCentered();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCTBaselineClassIdeographicLow", optional=true)
        public static native CFString IdeographicLow();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCTBaselineClassIdeographicHigh", optional=true)
        public static native CFString IdeographicHigh();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCTBaselineClassHanging", optional=true)
        public static native CFString Hanging();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="kCTBaselineClassMath", optional=true)
        public static native CFString Math();
        /*</values>*/
    }
}
