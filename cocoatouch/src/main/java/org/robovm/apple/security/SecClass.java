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
package org.robovm.apple.security;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Security") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/SecClass/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecClass/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFType>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/SecClass/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SecClass toObject(Class<SecClass> cls, long handle, long flags) {
            CFType o = (CFType) CFType.Marshaler.toObject(CFType.class, handle, flags);
            if (o == null) {
                return null;
            }
            return SecClass.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(SecClass o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<SecClass> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<SecClass> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(SecClass.valueOf(o.get(i, CFType.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SecClass> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (SecClass o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final SecClass GenericPassword = new SecClass("GenericPassword");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final SecClass InternetPassword = new SecClass("InternetPassword");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final SecClass Certificate = new SecClass("Certificate");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final SecClass Key = new SecClass("Key");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final SecClass Identity = new SecClass("Identity");
    /*</constants>*/
    
    private static /*<name>*/SecClass/*</name>*/[] values = new /*<name>*/SecClass/*</name>*/[] {/*<value_list>*/GenericPassword, InternetPassword, Certificate, Key, Identity/*</value_list>*/};
    
    /*<name>*/SecClass/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/SecClass/*</name>*/ valueOf(/*<type>*/CFType/*</type>*/ value) {
        for (/*<name>*/SecClass/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/SecClass/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Security") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecClassGenericPassword", optional=true)
        public static native CFType GenericPassword();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecClassInternetPassword", optional=true)
        public static native CFType InternetPassword();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecClassCertificate", optional=true)
        public static native CFType Certificate();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecClassKey", optional=true)
        public static native CFType Key();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecClassIdentity", optional=true)
        public static native CFType Identity();
        /*</values>*/
    }
}
