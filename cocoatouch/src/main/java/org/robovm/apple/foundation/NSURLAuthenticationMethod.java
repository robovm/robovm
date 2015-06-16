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
@Marshaler(/*<name>*/NSURLAuthenticationMethod/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLAuthenticationMethod/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSURLAuthenticationMethod/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSURLAuthenticationMethod toObject(Class<NSURLAuthenticationMethod> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSURLAuthenticationMethod.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSURLAuthenticationMethod o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSURLAuthenticationMethod> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSURLAuthenticationMethod> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSURLAuthenticationMethod.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSURLAuthenticationMethod> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSURLAuthenticationMethod o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final NSURLAuthenticationMethod Default = new NSURLAuthenticationMethod("Default");
    public static final NSURLAuthenticationMethod HTTPBasic = new NSURLAuthenticationMethod("HTTPBasic");
    public static final NSURLAuthenticationMethod HTTPDigest = new NSURLAuthenticationMethod("HTTPDigest");
    public static final NSURLAuthenticationMethod HTMLForm = new NSURLAuthenticationMethod("HTMLForm");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSURLAuthenticationMethod NTLM = new NSURLAuthenticationMethod("NTLM");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSURLAuthenticationMethod Negotiate = new NSURLAuthenticationMethod("Negotiate");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSURLAuthenticationMethod ClientCertificate = new NSURLAuthenticationMethod("ClientCertificate");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSURLAuthenticationMethod ServerTrust = new NSURLAuthenticationMethod("ServerTrust");
    /*</constants>*/
    
    private static /*<name>*/NSURLAuthenticationMethod/*</name>*/[] values = new /*<name>*/NSURLAuthenticationMethod/*</name>*/[] {/*<value_list>*/Default, HTTPBasic, HTTPDigest, HTMLForm, NTLM, Negotiate, ClientCertificate, ServerTrust/*</value_list>*/};
    
    /*<name>*/NSURLAuthenticationMethod/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSURLAuthenticationMethod/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSURLAuthenticationMethod/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSURLAuthenticationMethod/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="NSURLAuthenticationMethodDefault", optional=true)
        public static native NSString Default();
        @GlobalValue(symbol="NSURLAuthenticationMethodHTTPBasic", optional=true)
        public static native NSString HTTPBasic();
        @GlobalValue(symbol="NSURLAuthenticationMethodHTTPDigest", optional=true)
        public static native NSString HTTPDigest();
        @GlobalValue(symbol="NSURLAuthenticationMethodHTMLForm", optional=true)
        public static native NSString HTMLForm();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="NSURLAuthenticationMethodNTLM", optional=true)
        public static native NSString NTLM();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="NSURLAuthenticationMethodNegotiate", optional=true)
        public static native NSString Negotiate();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSURLAuthenticationMethodClientCertificate", optional=true)
        public static native NSString ClientCertificate();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSURLAuthenticationMethodServerTrust", optional=true)
        public static native NSString ServerTrust();
        /*</values>*/
    }
}
