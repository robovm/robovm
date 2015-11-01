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
package org.robovm.apple.homekit;

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
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("HomeKit") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/HMCharacteristicMetadataFormat/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HMCharacteristicMetadataFormat/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/HMCharacteristicMetadataFormat/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static HMCharacteristicMetadataFormat toObject(Class<HMCharacteristicMetadataFormat> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return HMCharacteristicMetadataFormat.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(HMCharacteristicMetadataFormat o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<HMCharacteristicMetadataFormat> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<HMCharacteristicMetadataFormat> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(HMCharacteristicMetadataFormat.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<HMCharacteristicMetadataFormat> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (HMCharacteristicMetadataFormat o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat Bool = new HMCharacteristicMetadataFormat("Bool");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat Int = new HMCharacteristicMetadataFormat("Int");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat Float = new HMCharacteristicMetadataFormat("Float");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat String = new HMCharacteristicMetadataFormat("String");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat Array = new HMCharacteristicMetadataFormat("Array");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat Dictionary = new HMCharacteristicMetadataFormat("Dictionary");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat UInt8 = new HMCharacteristicMetadataFormat("UInt8");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat UInt16 = new HMCharacteristicMetadataFormat("UInt16");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat UInt32 = new HMCharacteristicMetadataFormat("UInt32");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat UInt64 = new HMCharacteristicMetadataFormat("UInt64");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat Data = new HMCharacteristicMetadataFormat("Data");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicMetadataFormat TLV8 = new HMCharacteristicMetadataFormat("TLV8");
    /*</constants>*/
    
    private static /*<name>*/HMCharacteristicMetadataFormat/*</name>*/[] values = new /*<name>*/HMCharacteristicMetadataFormat/*</name>*/[] {/*<value_list>*/Bool, Int, Float, String, Array, Dictionary, UInt8, UInt16, UInt32, UInt64, Data, TLV8/*</value_list>*/};
    
    /*<name>*/HMCharacteristicMetadataFormat/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/HMCharacteristicMetadataFormat/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/HMCharacteristicMetadataFormat/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/HMCharacteristicMetadataFormat/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("HomeKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicMetadataFormatBool", optional=true)
        public static native NSString Bool();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicMetadataFormatInt", optional=true)
        public static native NSString Int();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicMetadataFormatFloat", optional=true)
        public static native NSString Float();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicMetadataFormatString", optional=true)
        public static native NSString String();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicMetadataFormatArray", optional=true)
        public static native NSString Array();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicMetadataFormatDictionary", optional=true)
        public static native NSString Dictionary();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicMetadataFormatUInt8", optional=true)
        public static native NSString UInt8();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicMetadataFormatUInt16", optional=true)
        public static native NSString UInt16();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicMetadataFormatUInt32", optional=true)
        public static native NSString UInt32();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicMetadataFormatUInt64", optional=true)
        public static native NSString UInt64();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicMetadataFormatData", optional=true)
        public static native NSString Data();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMCharacteristicMetadataFormatTLV8", optional=true)
        public static native NSString TLV8();
        /*</values>*/
    }
}
