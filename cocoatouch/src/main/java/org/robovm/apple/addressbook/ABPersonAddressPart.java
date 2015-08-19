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
package org.robovm.apple.addressbook;

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
/*<annotations>*/@Library("AddressBook") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/ABPersonAddressPart/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABPersonAddressPart/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/ABPersonAddressPart/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static ABPersonAddressPart toObject(Class<ABPersonAddressPart> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return ABPersonAddressPart.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(ABPersonAddressPart o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<ABPersonAddressPart> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<ABPersonAddressPart> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(ABPersonAddressPart.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<ABPersonAddressPart> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (ABPersonAddressPart o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final ABPersonAddressPart Street = new ABPersonAddressPart("Street");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final ABPersonAddressPart City = new ABPersonAddressPart("City");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final ABPersonAddressPart State = new ABPersonAddressPart("State");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final ABPersonAddressPart ZIP = new ABPersonAddressPart("ZIP");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final ABPersonAddressPart Country = new ABPersonAddressPart("Country");
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    public static final ABPersonAddressPart CountryCode = new ABPersonAddressPart("CountryCode");
    /*</constants>*/
    
    private static /*<name>*/ABPersonAddressPart/*</name>*/[] values = new /*<name>*/ABPersonAddressPart/*</name>*/[] {/*<value_list>*/Street, City, State, ZIP, Country, CountryCode/*</value_list>*/};
    
    /*<name>*/ABPersonAddressPart/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/ABPersonAddressPart/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/ABPersonAddressPart/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ABPersonAddressPart/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AddressBook") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kABPersonAddressStreetKey", optional=true)
        public static native CFString Street();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kABPersonAddressCityKey", optional=true)
        public static native CFString City();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kABPersonAddressStateKey", optional=true)
        public static native CFString State();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kABPersonAddressZIPKey", optional=true)
        public static native CFString ZIP();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kABPersonAddressCountryKey", optional=true)
        public static native CFString Country();
        /**
         * @since Available in iOS 2.0 and later.
         * @deprecated Deprecated in iOS 9.0.
         */
        @Deprecated
        @GlobalValue(symbol="kABPersonAddressCountryCodeKey", optional=true)
        public static native CFString CountryCode();
        /*</values>*/
    }
}
