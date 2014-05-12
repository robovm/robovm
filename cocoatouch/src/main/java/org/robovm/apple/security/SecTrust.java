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

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Security")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecTrust/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SecTrustPtr extends Ptr<SecTrust, SecTrustPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(SecTrust.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected SecTrust() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kSecTrustEvaluationDate", optional=true)
    public static native CFType EvaluationDate();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kSecTrustExtendedValidation", optional=true)
    public static native CFType ExtendedValidation();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kSecTrustOrganizationName", optional=true)
    public static native CFType OrganizationName();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kSecTrustResultValue", optional=true)
    public static native CFType ResultValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kSecTrustRevocationChecked", optional=true)
    public static native CFType RevocationChecked();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kSecTrustRevocationValidUntilDate", optional=true)
    public static native CFType RevocationValidUntilDate();
    
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustCreateWithCertificates", optional=true)
    public static native int createWithCertificates(CFType certificates, CFType policies, SecTrust.SecTrustPtr trust);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="SecTrustSetPolicies", optional=true)
    public native int setPolicies(CFType policies);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecTrustCopyPolicies", optional=true)
    public native int copyPolicies(CFArray.CFArrayPtr policies);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecTrustSetNetworkFetchAllowed", optional=true)
    public native int setNetworkFetchAllowed(boolean allowFetch);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecTrustGetNetworkFetchAllowed", optional=true)
    public native int getNetworkFetchAllowed(BytePtr allowFetch);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustSetAnchorCertificates", optional=true)
    public native int setAnchorCertificates(CFArray anchorCertificates);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustSetAnchorCertificatesOnly", optional=true)
    public native int setAnchorCertificatesOnly(boolean anchorCertificatesOnly);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecTrustCopyCustomAnchorCertificates", optional=true)
    public native int copyCustomAnchorCertificates(CFArray.CFArrayPtr anchors);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustSetVerifyDate", optional=true)
    public native int setVerifyDate(CFDate verifyDate);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustGetVerifyTime", optional=true)
    public native double getVerifyTime();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustEvaluate", optional=true)
    public native int evaluate(IntPtr result);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecTrustEvaluateAsync", optional=true)
    public native int evaluateAsync(DispatchQueue queue, FunctionPtr result);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecTrustGetTrustResult", optional=true)
    public native int getTrustResult(IntPtr result);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustCopyPublicKey", optional=true)
    public native SecKey copyPublicKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustGetCertificateCount", optional=true)
    public native @MachineSizedSInt long getCertificateCount();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustGetCertificateAtIndex", optional=true)
    public native SecCertificate getCertificateAtIndex(@MachineSizedSInt long ix);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="SecTrustCopyExceptions", optional=true)
    public native CFData copyExceptions();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="SecTrustSetExceptions", optional=true)
    public native boolean setExceptions(CFData exceptions);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustCopyProperties", optional=true)
    public native CFArray copyProperties();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecTrustCopyResult", optional=true)
    public native CFDictionary copyResult();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecTrustSetOCSPResponse", optional=true)
    public native int setOCSPResponse(CFType responseData);
    /*</methods>*/
}
