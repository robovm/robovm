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
@Marshaler(/*<name>*/SecAttrAuthenticationType/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecAttrAuthenticationType/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFType>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/SecAttrAuthenticationType/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SecAttrAuthenticationType toObject(Class<SecAttrAuthenticationType> cls, long handle, long flags) {
            CFType o = (CFType) CFType.Marshaler.toObject(CFType.class, handle, flags);
            if (o == null) {
                return null;
            }
            return SecAttrAuthenticationType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(SecAttrAuthenticationType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<SecAttrAuthenticationType> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<SecAttrAuthenticationType> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(SecAttrAuthenticationType.valueOf(o.get(i, CFType.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SecAttrAuthenticationType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (SecAttrAuthenticationType i : l) {
                array.add(i.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final SecAttrAuthenticationType NTLM = new SecAttrAuthenticationType("NTLM");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final SecAttrAuthenticationType MSN = new SecAttrAuthenticationType("MSN");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final SecAttrAuthenticationType DPA = new SecAttrAuthenticationType("DPA");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final SecAttrAuthenticationType RPA = new SecAttrAuthenticationType("RPA");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final SecAttrAuthenticationType HTTPBasic = new SecAttrAuthenticationType("HTTPBasic");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final SecAttrAuthenticationType HTTPDigest = new SecAttrAuthenticationType("HTTPDigest");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final SecAttrAuthenticationType HTMLForm = new SecAttrAuthenticationType("HTMLForm");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final SecAttrAuthenticationType Default = new SecAttrAuthenticationType("Default");
    /*</constants>*/
    
    private static /*<name>*/SecAttrAuthenticationType/*</name>*/[] values = new /*<name>*/SecAttrAuthenticationType/*</name>*/[] {/*<value_list>*/NTLM, MSN, DPA, RPA, HTTPBasic, HTTPDigest, HTMLForm, Default/*</value_list>*/};
    
    /*<name>*/SecAttrAuthenticationType/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/SecAttrAuthenticationType/*</name>*/ valueOf(/*<type>*/CFType/*</type>*/ value) {
        for (/*<name>*/SecAttrAuthenticationType/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/SecAttrAuthenticationType/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Security") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAuthenticationTypeNTLM", optional=true)
        public static native CFType NTLM();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAuthenticationTypeMSN", optional=true)
        public static native CFType MSN();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAuthenticationTypeDPA", optional=true)
        public static native CFType DPA();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAuthenticationTypeRPA", optional=true)
        public static native CFType RPA();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAuthenticationTypeHTTPBasic", optional=true)
        public static native CFType HTTPBasic();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAuthenticationTypeHTTPDigest", optional=true)
        public static native CFType HTTPDigest();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAuthenticationTypeHTMLForm", optional=true)
        public static native CFType HTMLForm();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kSecAttrAuthenticationTypeDefault", optional=true)
        public static native CFType Default();
        /*</values>*/
    }
}
