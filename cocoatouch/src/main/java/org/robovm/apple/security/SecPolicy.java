/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Security")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecPolicy/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SecPolicyPtr extends Ptr<SecPolicy, SecPolicyPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(SecPolicy.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected SecPolicy() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
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
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kSecPolicyOid", optional=true)
    public static native CFType Oid();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kSecPolicyName", optional=true)
    public static native CFType Name();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kSecPolicyClient", optional=true)
    public static native CFType Client();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kSecPolicyRevocationFlags", optional=true)
    public static native CFType RevocationFlags();
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecPolicyGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecPolicyCopyProperties", optional=true)
    public native CFDictionary copyProperties();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecPolicyCreateBasicX509", optional=true)
    public static native SecPolicy createBasicX509();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecPolicyCreateSSL", optional=true)
    public static native SecPolicy createSSL(boolean server, CFString hostname);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecPolicyCreateRevocation", optional=true)
    public static native SecPolicy createRevocation(SecRevocation revocationFlags);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecPolicyCreateWithProperties", optional=true)
    public static native SecPolicy createWithProperties(CFType policyIdentifier, CFDictionary properties);
    /*</methods>*/
}
