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

/**
 *
 * <div class="javadoc"></div>
 */
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
    @GlobalValue(symbol="kSecPolicyAppleX509Basic")
    public static native CFType AppleX509Basic();
    @GlobalValue(symbol="kSecPolicyAppleSSL")
    public static native CFType AppleSSL();
    @GlobalValue(symbol="kSecPolicyAppleSMIME")
    public static native CFType AppleSMIME();
    @GlobalValue(symbol="kSecPolicyAppleEAP")
    public static native CFType AppleEAP();
    @GlobalValue(symbol="kSecPolicyAppleIPsec")
    public static native CFType AppleIPsec();
    @GlobalValue(symbol="kSecPolicyApplePKINITClient")
    public static native CFType ApplePKINITClient();
    @GlobalValue(symbol="kSecPolicyApplePKINITServer")
    public static native CFType ApplePKINITServer();
    @GlobalValue(symbol="kSecPolicyAppleCodeSigning")
    public static native CFType AppleCodeSigning();
    @GlobalValue(symbol="kSecPolicyMacAppStoreReceipt")
    public static native CFType MacAppStoreReceipt();
    @GlobalValue(symbol="kSecPolicyAppleIDValidation")
    public static native CFType AppleIDValidation();
    @GlobalValue(symbol="kSecPolicyAppleTimeStamping")
    public static native CFType AppleTimeStamping();
    @GlobalValue(symbol="kSecPolicyAppleRevocation")
    public static native CFType AppleRevocation();
    @GlobalValue(symbol="kSecPolicyOid")
    public static native CFType Oid();
    @GlobalValue(symbol="kSecPolicyName")
    public static native CFType Name();
    @GlobalValue(symbol="kSecPolicyClient")
    public static native CFType Client();
    @GlobalValue(symbol="kSecPolicyRevocationFlags")
    public static native CFType RevocationFlags();
    
    @Bridge(symbol="SecPolicyGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="SecPolicyCopyProperties")
    public native CFDictionary copyProperties();
    @Bridge(symbol="SecPolicyCreateBasicX509")
    public static native SecPolicy createBasicX509();
    @Bridge(symbol="SecPolicyCreateSSL")
    public static native SecPolicy createSSL(boolean server, CFString hostname);
    @Bridge(symbol="SecPolicyCreateRevocation")
    public static native SecPolicy createRevocation(SecRevocation revocationFlags);
    @Bridge(symbol="SecPolicyCreateWithProperties")
    public static native SecPolicy createWithProperties(CFType policyIdentifier, CFDictionary properties);
    /*</methods>*/
}
