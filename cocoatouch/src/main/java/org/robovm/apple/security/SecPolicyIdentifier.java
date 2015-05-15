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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Security")/*</annotations>*/
@Marshaler(/*<name>*/SecPolicyIdentifier/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecPolicyIdentifier/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFType>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/SecPolicyIdentifier/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static SecPolicyIdentifier toObject(Class<SecPolicyIdentifier> cls, long handle, long flags) {
            CFType o = (CFType) CFType.Marshaler.toObject(CFType.class, handle, flags);
            if (o == null) {
                return null;
            }
            return SecPolicyIdentifier.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(SecPolicyIdentifier o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<SecPolicyIdentifier> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<SecPolicyIdentifier> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(SecPolicyIdentifier.valueOf(o.get(i, CFType.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<SecPolicyIdentifier> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (SecPolicyIdentifier i : l) {
                array.add(i.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final SecPolicyIdentifier AppleX509Basic = new SecPolicyIdentifier("AppleX509Basic");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final SecPolicyIdentifier AppleSSL = new SecPolicyIdentifier("AppleSSL");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final SecPolicyIdentifier AppleSMIME = new SecPolicyIdentifier("AppleSMIME");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final SecPolicyIdentifier AppleEAP = new SecPolicyIdentifier("AppleEAP");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final SecPolicyIdentifier AppleIPsec = new SecPolicyIdentifier("AppleIPsec");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final SecPolicyIdentifier AppleCodeSigning = new SecPolicyIdentifier("AppleCodeSigning");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final SecPolicyIdentifier AppleIDValidation = new SecPolicyIdentifier("AppleIDValidation");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final SecPolicyIdentifier AppleTimeStamping = new SecPolicyIdentifier("AppleTimeStamping");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final SecPolicyIdentifier AppleRevocation = new SecPolicyIdentifier("AppleRevocation");
    /*</constants>*/
    
    private static /*<name>*/SecPolicyIdentifier/*</name>*/[] values = new /*<name>*/SecPolicyIdentifier/*</name>*/[] {/*<value_list>*/AppleX509Basic, AppleSSL, AppleSMIME, AppleEAP, AppleIPsec, AppleCodeSigning, AppleIDValidation, AppleTimeStamping, AppleRevocation/*</value_list>*/};
    
    /*<name>*/SecPolicyIdentifier/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/SecPolicyIdentifier/*</name>*/ valueOf(/*<type>*/CFType/*</type>*/ value) {
        for (/*<name>*/SecPolicyIdentifier/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/SecPolicyIdentifier/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Security")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecPolicyAppleX509Basic", optional=true)
        public static native CFType AppleX509Basic();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecPolicyAppleSSL", optional=true)
        public static native CFType AppleSSL();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecPolicyAppleSMIME", optional=true)
        public static native CFType AppleSMIME();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecPolicyAppleEAP", optional=true)
        public static native CFType AppleEAP();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecPolicyAppleIPsec", optional=true)
        public static native CFType AppleIPsec();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecPolicyAppleCodeSigning", optional=true)
        public static native CFType AppleCodeSigning();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecPolicyAppleIDValidation", optional=true)
        public static native CFType AppleIDValidation();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecPolicyAppleTimeStamping", optional=true)
        public static native CFType AppleTimeStamping();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kSecPolicyAppleRevocation", optional=true)
        public static native CFType AppleRevocation();
        /*</values>*/
    }
}
