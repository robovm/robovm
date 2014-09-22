/*
 * Copyright (C) 2014 Trillian Mobile AB
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
@Marshaler(CFHTTPAuthenticationScheme.Marshaler.class)
/*<annotations>*/@Library("CFNetwork")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFHTTPAuthenticationScheme/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
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

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFHTTPAuthenticationScheme.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFHTTPAuthenticationScheme Basic = new CFHTTPAuthenticationScheme("BasicValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFHTTPAuthenticationScheme Digest = new CFHTTPAuthenticationScheme("DigestValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFHTTPAuthenticationScheme NTLM = new CFHTTPAuthenticationScheme("NTLMValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFHTTPAuthenticationScheme Negotiate = new CFHTTPAuthenticationScheme("NegotiateValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static CFHTTPAuthenticationScheme Negotiate2 = new CFHTTPAuthenticationScheme("Negotiate2Value");
    /**
     * @since Available in iOS 4.3 and later.
     */
    public static CFHTTPAuthenticationScheme XMobileMeAuthToken = new CFHTTPAuthenticationScheme("XMobileMeAuthTokenValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static CFHTTPAuthenticationScheme Kerberos = new CFHTTPAuthenticationScheme("KerberosValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static CFHTTPAuthenticationScheme OAuth1 = new CFHTTPAuthenticationScheme("OAuth1Value");
    private static CFHTTPAuthenticationScheme[] values = new CFHTTPAuthenticationScheme[] {Basic, Digest, NTLM, Negotiate, Kerberos, Negotiate2, XMobileMeAuthToken, OAuth1};
    
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CFHTTPAuthenticationScheme(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CFHTTPAuthenticationScheme valueOf(CFString value) {
        if (value == null) throw new NullPointerException("value");
        for (CFHTTPAuthenticationScheme v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFHTTPAuthenticationScheme/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFHTTPAuthenticationSchemeBasic", optional=true)
    protected static native CFString BasicValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFHTTPAuthenticationSchemeDigest", optional=true)
    protected static native CFString DigestValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFHTTPAuthenticationSchemeNTLM", optional=true)
    protected static native CFString NTLMValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFHTTPAuthenticationSchemeNegotiate", optional=true)
    protected static native CFString NegotiateValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="kCFHTTPAuthenticationSchemeNegotiate2", optional=true)
    protected static native CFString Negotiate2Value();
    /**
     * @since Available in iOS 4.3 and later.
     */
    @GlobalValue(symbol="kCFHTTPAuthenticationSchemeXMobileMeAuthToken", optional=true)
    protected static native CFString XMobileMeAuthTokenValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="kCFHTTPAuthenticationSchemeKerberos", optional=true)
    protected static native CFString KerberosValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCFHTTPAuthenticationSchemeOAuth1", optional=true)
    protected static native CFString OAuth1Value();
    /*</methods>*/
}
