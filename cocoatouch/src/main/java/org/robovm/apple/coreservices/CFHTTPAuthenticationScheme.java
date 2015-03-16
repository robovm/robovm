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
package org.robovm.apple.coreservices;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CFNetwork")/*</annotations>*/
@Marshaler(/*<name>*/CFHTTPAuthenticationScheme/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFHTTPAuthenticationScheme/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CFHTTPAuthenticationScheme/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CFHTTPAuthenticationScheme toObject(Class<CFHTTPAuthenticationScheme> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFHTTPAuthenticationScheme.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFHTTPAuthenticationScheme o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFHTTPAuthenticationScheme> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFHTTPAuthenticationScheme> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CFHTTPAuthenticationScheme.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFHTTPAuthenticationScheme> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFHTTPAuthenticationScheme i : l) {
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
    public static final CFHTTPAuthenticationScheme Basic = new CFHTTPAuthenticationScheme("Basic");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFHTTPAuthenticationScheme Digest = new CFHTTPAuthenticationScheme("Digest");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFHTTPAuthenticationScheme NTLM = new CFHTTPAuthenticationScheme("NTLM");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFHTTPAuthenticationScheme Kerberos = new CFHTTPAuthenticationScheme("Kerberos");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFHTTPAuthenticationScheme Negotiate = new CFHTTPAuthenticationScheme("Negotiate");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final CFHTTPAuthenticationScheme Negotiate2 = new CFHTTPAuthenticationScheme("Negotiate2");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static final CFHTTPAuthenticationScheme XMobileMeAuthToken = new CFHTTPAuthenticationScheme("XMobileMeAuthToken");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final CFHTTPAuthenticationScheme OAuth1 = new CFHTTPAuthenticationScheme("OAuth1");
    /*</constants>*/
    
    private static /*<name>*/CFHTTPAuthenticationScheme/*</name>*/[] values = new /*<name>*/CFHTTPAuthenticationScheme/*</name>*/[] {/*<value_list>*/Basic, Digest, NTLM, Kerberos, Negotiate, Negotiate2, XMobileMeAuthToken, OAuth1/*</value_list>*/};
    
    /*<name>*/CFHTTPAuthenticationScheme/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CFHTTPAuthenticationScheme/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CFHTTPAuthenticationScheme/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFHTTPAuthenticationScheme/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CFNetwork")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFHTTPAuthenticationSchemeBasic", optional=true)
        public static native CFString Basic();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFHTTPAuthenticationSchemeDigest", optional=true)
        public static native CFString Digest();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFHTTPAuthenticationSchemeNTLM", optional=true)
        public static native CFString NTLM();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFHTTPAuthenticationSchemeKerberos", optional=true)
        public static native CFString Kerberos();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFHTTPAuthenticationSchemeNegotiate", optional=true)
        public static native CFString Negotiate();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="kCFHTTPAuthenticationSchemeNegotiate2", optional=true)
        public static native CFString Negotiate2();
        /**
         * @since Available in iOS 4.3 and later.
         */
        @GlobalValue(symbol="kCFHTTPAuthenticationSchemeXMobileMeAuthToken", optional=true)
        public static native CFString XMobileMeAuthToken();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCFHTTPAuthenticationSchemeOAuth1", optional=true)
        public static native CFString OAuth1();
        /*</values>*/
    }
}
