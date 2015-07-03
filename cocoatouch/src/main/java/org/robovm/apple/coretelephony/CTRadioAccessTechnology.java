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
package org.robovm.apple.coretelephony;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreTelephony") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CTRadioAccessTechnology/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTRadioAccessTechnology/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CTRadioAccessTechnology/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CTRadioAccessTechnology toObject(Class<CTRadioAccessTechnology> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CTRadioAccessTechnology.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CTRadioAccessTechnology o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CTRadioAccessTechnology> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CTRadioAccessTechnology> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CTRadioAccessTechnology.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CTRadioAccessTechnology> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CTRadioAccessTechnology o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CTRadioAccessTechnology GPRS = new CTRadioAccessTechnology("GPRS");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CTRadioAccessTechnology Edge = new CTRadioAccessTechnology("Edge");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CTRadioAccessTechnology WCDMA = new CTRadioAccessTechnology("WCDMA");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CTRadioAccessTechnology HSDPA = new CTRadioAccessTechnology("HSDPA");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CTRadioAccessTechnology HSUPA = new CTRadioAccessTechnology("HSUPA");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CTRadioAccessTechnology CDMA1x = new CTRadioAccessTechnology("CDMA1x");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CTRadioAccessTechnology CDMAEVDORev0 = new CTRadioAccessTechnology("CDMAEVDORev0");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CTRadioAccessTechnology CDMAEVDORevA = new CTRadioAccessTechnology("CDMAEVDORevA");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CTRadioAccessTechnology CDMAEVDORevB = new CTRadioAccessTechnology("CDMAEVDORevB");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CTRadioAccessTechnology eHRPD = new CTRadioAccessTechnology("eHRPD");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CTRadioAccessTechnology LTE = new CTRadioAccessTechnology("LTE");
    /*</constants>*/
    
    private static /*<name>*/CTRadioAccessTechnology/*</name>*/[] values = new /*<name>*/CTRadioAccessTechnology/*</name>*/[] {/*<value_list>*/GPRS, Edge, WCDMA, HSDPA, HSUPA, CDMA1x, CDMAEVDORev0, CDMAEVDORevA, CDMAEVDORevB, eHRPD, LTE/*</value_list>*/};
    
    /*<name>*/CTRadioAccessTechnology/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CTRadioAccessTechnology/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CTRadioAccessTechnology/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CTRadioAccessTechnology/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreTelephony") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="CTRadioAccessTechnologyGPRS", optional=true)
        public static native NSString GPRS();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="CTRadioAccessTechnologyEdge", optional=true)
        public static native NSString Edge();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="CTRadioAccessTechnologyWCDMA", optional=true)
        public static native NSString WCDMA();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="CTRadioAccessTechnologyHSDPA", optional=true)
        public static native NSString HSDPA();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="CTRadioAccessTechnologyHSUPA", optional=true)
        public static native NSString HSUPA();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="CTRadioAccessTechnologyCDMA1x", optional=true)
        public static native NSString CDMA1x();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="CTRadioAccessTechnologyCDMAEVDORev0", optional=true)
        public static native NSString CDMAEVDORev0();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="CTRadioAccessTechnologyCDMAEVDORevA", optional=true)
        public static native NSString CDMAEVDORevA();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="CTRadioAccessTechnologyCDMAEVDORevB", optional=true)
        public static native NSString CDMAEVDORevB();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="CTRadioAccessTechnologyeHRPD", optional=true)
        public static native NSString eHRPD();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="CTRadioAccessTechnologyLTE", optional=true)
        public static native NSString LTE();
        /*</values>*/
    }
}
