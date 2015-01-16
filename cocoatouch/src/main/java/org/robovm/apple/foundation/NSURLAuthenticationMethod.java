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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(NSURLAuthenticationMethod.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLAuthenticationMethod/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

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
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSURLAuthenticationMethod.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final NSURLAuthenticationMethod Default = new NSURLAuthenticationMethod("DefaultValue");
    public static final NSURLAuthenticationMethod HTTPBasic = new NSURLAuthenticationMethod("HTTPBasicValue");
    public static final NSURLAuthenticationMethod HTTPDigest = new NSURLAuthenticationMethod("HTTPDigestValue");
    public static final NSURLAuthenticationMethod HTMLForm = new NSURLAuthenticationMethod("HTMLFormValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSURLAuthenticationMethod NTLM = new NSURLAuthenticationMethod("NTLMValue");
    /**
     * @since Available in iOS 2.0 and later.
     */
    public static final NSURLAuthenticationMethod Negotiate = new NSURLAuthenticationMethod("NegotiateValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSURLAuthenticationMethod ClientCertificate = new NSURLAuthenticationMethod("ClientCertificateValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSURLAuthenticationMethod ServerTrust = new NSURLAuthenticationMethod("ServerTrustValue");
    
    private static NSURLAuthenticationMethod[] values = new NSURLAuthenticationMethod[] {Default, HTTPBasic, HTTPDigest, HTMLForm, NTLM, Negotiate, ClientCertificate, ServerTrust};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSURLAuthenticationMethod(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSURLAuthenticationMethod valueOf(NSString value) {
        for (NSURLAuthenticationMethod v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSURLAuthenticationMethod/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="NSURLAuthenticationMethodDefault", optional=true)
    protected static native NSString DefaultValue();
    @GlobalValue(symbol="NSURLAuthenticationMethodHTTPBasic", optional=true)
    protected static native NSString HTTPBasicValue();
    @GlobalValue(symbol="NSURLAuthenticationMethodHTTPDigest", optional=true)
    protected static native NSString HTTPDigestValue();
    @GlobalValue(symbol="NSURLAuthenticationMethodHTMLForm", optional=true)
    protected static native NSString HTMLFormValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSURLAuthenticationMethodNTLM", optional=true)
    protected static native NSString NTLMValue();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @GlobalValue(symbol="NSURLAuthenticationMethodNegotiate", optional=true)
    protected static native NSString NegotiateValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSURLAuthenticationMethodClientCertificate", optional=true)
    protected static native NSString ClientCertificateValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSURLAuthenticationMethodServerTrust", optional=true)
    protected static native NSString ServerTrustValue();
    /*</methods>*/
}
