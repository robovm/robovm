/*
 * Copyright (C) 2015 Trillian Mobile AB
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
@Marshaler(/*<name>*/CFStreamSocketSecurityLevel/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFStreamSocketSecurityLevel/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CFStreamSocketSecurityLevel/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CFStreamSocketSecurityLevel toObject(Class<CFStreamSocketSecurityLevel> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFStreamSocketSecurityLevel.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFStreamSocketSecurityLevel o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFStreamSocketSecurityLevel> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFStreamSocketSecurityLevel> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CFStreamSocketSecurityLevel.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFStreamSocketSecurityLevel> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFStreamSocketSecurityLevel i : l) {
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
    public static final CFStreamSocketSecurityLevel None = new CFStreamSocketSecurityLevel("None");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFStreamSocketSecurityLevel SSLv2 = new CFStreamSocketSecurityLevel("SSLv2");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFStreamSocketSecurityLevel SSLv3 = new CFStreamSocketSecurityLevel("SSLv3");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFStreamSocketSecurityLevel TLSv1 = new CFStreamSocketSecurityLevel("TLSv1");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final CFStreamSocketSecurityLevel NegotiatedSSL = new CFStreamSocketSecurityLevel("NegotiatedSSL");
    /*</constants>*/
    
    private static /*<name>*/CFStreamSocketSecurityLevel/*</name>*/[] values = new /*<name>*/CFStreamSocketSecurityLevel/*</name>*/[] {/*<value_list>*/None, SSLv2, SSLv3, TLSv1, NegotiatedSSL/*</value_list>*/};
    
    /*<name>*/CFStreamSocketSecurityLevel/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CFStreamSocketSecurityLevel/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CFStreamSocketSecurityLevel/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFStreamSocketSecurityLevel/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CFNetwork")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamSocketSecurityLevelNone", optional=true)
        public static native CFString None();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamSocketSecurityLevelSSLv2", optional=true)
        public static native CFString SSLv2();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamSocketSecurityLevelSSLv3", optional=true)
        public static native CFString SSLv3();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamSocketSecurityLevelTLSv1", optional=true)
        public static native CFString TLSv1();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCFStreamSocketSecurityLevelNegotiatedSSL", optional=true)
        public static native CFString NegotiatedSSL();
        /*</values>*/
    }
}
