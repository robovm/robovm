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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/NSStreamSocketSecurityLevel/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSStreamSocketSecurityLevel/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSStreamSocketSecurityLevel/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSStreamSocketSecurityLevel toObject(Class<NSStreamSocketSecurityLevel> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSStreamSocketSecurityLevel.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSStreamSocketSecurityLevel o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSStreamSocketSecurityLevel> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSStreamSocketSecurityLevel> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSStreamSocketSecurityLevel.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSStreamSocketSecurityLevel> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSStreamSocketSecurityLevel o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSStreamSocketSecurityLevel None = new NSStreamSocketSecurityLevel("None");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSStreamSocketSecurityLevel SSLv2 = new NSStreamSocketSecurityLevel("SSLv2");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSStreamSocketSecurityLevel SSLv3 = new NSStreamSocketSecurityLevel("SSLv3");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSStreamSocketSecurityLevel TLSv1 = new NSStreamSocketSecurityLevel("TLSv1");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSStreamSocketSecurityLevel NegotiatedSSL = new NSStreamSocketSecurityLevel("NegotiatedSSL");
    /*</constants>*/
    
    private static /*<name>*/NSStreamSocketSecurityLevel/*</name>*/[] values = new /*<name>*/NSStreamSocketSecurityLevel/*</name>*/[] {/*<value_list>*/None, SSLv2, SSLv3, TLSv1, NegotiatedSSL/*</value_list>*/};
    
    /*<name>*/NSStreamSocketSecurityLevel/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSStreamSocketSecurityLevel/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSStreamSocketSecurityLevel/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSStreamSocketSecurityLevel/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="NSStreamSocketSecurityLevelNone", optional=true)
        public static native NSString None();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="NSStreamSocketSecurityLevelSSLv2", optional=true)
        public static native NSString SSLv2();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="NSStreamSocketSecurityLevelSSLv3", optional=true)
        public static native NSString SSLv3();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="NSStreamSocketSecurityLevelTLSv1", optional=true)
        public static native NSString TLSv1();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="NSStreamSocketSecurityLevelNegotiatedSSL", optional=true)
        public static native NSString NegotiatedSSL();
        /*</values>*/
    }
}
