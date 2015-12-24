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
package org.robovm.apple.contacts;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Contacts") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CNPostalAddressPropertyKey/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNPostalAddressPropertyKey/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CNPostalAddressPropertyKey/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CNPostalAddressPropertyKey toObject(Class<CNPostalAddressPropertyKey> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CNPostalAddressPropertyKey.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CNPostalAddressPropertyKey o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CNPostalAddressPropertyKey> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CNPostalAddressPropertyKey> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CNPostalAddressPropertyKey.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CNPostalAddressPropertyKey> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CNPostalAddressPropertyKey o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNPostalAddressPropertyKey Street = new CNPostalAddressPropertyKey("Street");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNPostalAddressPropertyKey City = new CNPostalAddressPropertyKey("City");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNPostalAddressPropertyKey State = new CNPostalAddressPropertyKey("State");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNPostalAddressPropertyKey PostalCode = new CNPostalAddressPropertyKey("PostalCode");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNPostalAddressPropertyKey Country = new CNPostalAddressPropertyKey("Country");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNPostalAddressPropertyKey ISOCountryCode = new CNPostalAddressPropertyKey("ISOCountryCode");
    /*</constants>*/
    
    private static /*<name>*/CNPostalAddressPropertyKey/*</name>*/[] values = new /*<name>*/CNPostalAddressPropertyKey/*</name>*/[] {/*<value_list>*/Street, City, State, PostalCode, Country, ISOCountryCode/*</value_list>*/};
    
    /*<name>*/CNPostalAddressPropertyKey/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CNPostalAddressPropertyKey/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CNPostalAddressPropertyKey/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CNPostalAddressPropertyKey/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Contacts") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNPostalAddressStreetKey", optional=true)
        public static native NSString Street();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNPostalAddressCityKey", optional=true)
        public static native NSString City();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNPostalAddressStateKey", optional=true)
        public static native NSString State();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNPostalAddressPostalCodeKey", optional=true)
        public static native NSString PostalCode();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNPostalAddressCountryKey", optional=true)
        public static native NSString Country();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNPostalAddressISOCountryCodeKey", optional=true)
        public static native NSString ISOCountryCode();
        /*</values>*/
    }
}
