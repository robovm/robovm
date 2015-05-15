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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SecTrust/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    private static final java.lang.reflect.Method cbEvaluate;
    private VoidBlock2<SecTrust, SecTrustResultType> evaluateCallback;
    
    static {
        try {
            cbEvaluate = SecTrust.class.getDeclaredMethod("cbEvaluate", SecTrust.class, SecTrustResultType.class);
        } catch (Throwable throwable) {
            throw new Error(throwable);
        }
    }
    
    /*<ptr>*/public static class SecTrustPtr extends Ptr<SecTrust, SecTrustPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(SecTrust.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected SecTrust() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbEvaluate(SecTrust secTrust, SecTrustResultType trustResult) {
        if (secTrust.evaluateCallback != null) {
            secTrust.evaluateCallback.invoke(secTrust, trustResult);
            secTrust.evaluateCallback = null;
        }
    }
    
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static SecTrust create(SecCertificate certificate, SecPolicy policy) throws OSStatusException {
        if (certificate == null) {
            throw new NullPointerException("certificate");
        }
        if (policy == null) {
            throw new NullPointerException("policy");
        }
        SecTrust.SecTrustPtr ptr = new SecTrust.SecTrustPtr();
        OSStatus status = create0(certificate, policy, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public static SecTrust create(List<SecCertificate> certificates, List<SecPolicy> policies) throws OSStatusException {
        if (certificates == null) {
            throw new NullPointerException("certificates");
        }
        if (policies == null) {
            throw new NullPointerException("policies");
        }
        SecTrust.SecTrustPtr ptr = new SecTrust.SecTrustPtr();
        OSStatus status = create0(CFArray.create(certificates), CFArray.create(policies), ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 6.0 and later.
     */
    public void setPolicy(SecPolicy policy) throws OSStatusException {
        OSStatus status = setPolicies0(policy);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 6.0 and later.
     */
    public void setPolicies(List<SecPolicy> policies) throws OSStatusException {
        OSStatus status = setPolicies0(CFArray.create(policies));
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 7.0 and later.
     */
    public List<SecPolicy> getPolicies() throws OSStatusException {
        CFArray.CFArrayPtr ptr = new CFArray.CFArrayPtr();
        OSStatus status = getPolicies0(ptr);
        OSStatusException.throwIfNecessary(status);
        if (ptr.get() != null) {
            return ptr.get().toList(SecPolicy.class);
        }
        return null;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 7.0 and later.
     */
    public void setNetworkFetchAllowed(boolean allowFetch) throws OSStatusException {
        OSStatus status = setNetworkFetchAllowed0(allowFetch);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 7.0 and later.
     */
    public boolean isNetworkFetchAllowed() throws OSStatusException {
        BooleanPtr ptr = new BooleanPtr();
        OSStatus status = isNetworkFetchAllowed0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void setAnchorCertificates(List<SecCertificate> anchorCertificates) throws OSStatusException {
        OSStatus status = setAnchorCertificates0(anchorCertificates);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void setAnchorCertificatesOnly(boolean anchorCertificatesOnly) throws OSStatusException {
        OSStatus status = setAnchorCertificatesOnly0(anchorCertificatesOnly);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 7.0 and later.
     */
    public List<SecCertificate> getCustomAnchorCertificates() throws OSStatusException {
        CFArray.CFArrayPtr ptr = new CFArray.CFArrayPtr();
        OSStatus status = getCustomAnchorCertificates0(ptr);
        OSStatusException.throwIfNecessary(status);
        if (ptr.get() != null) {
            return ptr.get().toList(SecCertificate.class);
        }
        return null;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public void setVerifyDate(NSDate verifyDate) throws OSStatusException {
        OSStatus status = setVerifyDate0(verifyDate);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 2.0 and later.
     */
    public SecTrustResultType evaluate() throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = evaluate0(ptr);
        OSStatusException.throwIfNecessary(status);
        return SecTrustResultType.valueOf(ptr.get());
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 7.0 and later.
     */
    public void evaluateAsync(DispatchQueue queue, VoidBlock2<SecTrust, SecTrustResultType> result) throws OSStatusException {
        OSStatus status = evaluateAsync0(queue, new FunctionPtr(cbEvaluate));
        OSStatusException.throwIfNecessary(status);
        evaluateCallback = result;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 7.0 and later.
     */
    public SecTrustResultType getTrustResult() throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getTrustResult0(ptr);
        OSStatusException.throwIfNecessary(status);
        return SecTrustResultType.valueOf(ptr.get());
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 7.0 and later.
     */
    public void setOCSPResponse(NSData responseData) throws OSStatusException {
        if (responseData == null) {
            throw new NullPointerException("responseData");
        }
        OSStatus status = setOCSPResponse0(responseData.as(CFData.class));
        OSStatusException.throwIfNecessary(status);
    }
    /**
    * @throws OSStatusException 
     * @since Available in iOS 7.0 and later.
    */
    public void setOCSPResponse(List<NSData> responseData) throws OSStatusException {
        if (responseData == null) {
            throw new NullPointerException("responseData");
        }
        OSStatus status = setOCSPResponse0(CFArray.create(responseData));
        OSStatusException.throwIfNecessary(status);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustCreateWithCertificates", optional=true)
    protected static native OSStatus create0(CFType certificates, CFType policies, SecTrust.SecTrustPtr trust);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="SecTrustSetPolicies", optional=true)
    protected native OSStatus setPolicies0(CFType policies);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecTrustCopyPolicies", optional=true)
    protected native OSStatus getPolicies0(CFArray.CFArrayPtr policies);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecTrustSetNetworkFetchAllowed", optional=true)
    protected native OSStatus setNetworkFetchAllowed0(boolean allowFetch);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecTrustGetNetworkFetchAllowed", optional=true)
    protected native OSStatus isNetworkFetchAllowed0(BooleanPtr allowFetch);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustSetAnchorCertificates", optional=true)
    protected native OSStatus setAnchorCertificates0(@org.robovm.rt.bro.annotation.Marshaler(CFArray.AsListMarshaler.class) List<SecCertificate> anchorCertificates);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustSetAnchorCertificatesOnly", optional=true)
    protected native OSStatus setAnchorCertificatesOnly0(boolean anchorCertificatesOnly);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecTrustCopyCustomAnchorCertificates", optional=true)
    protected native OSStatus getCustomAnchorCertificates0(CFArray.CFArrayPtr anchors);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustSetVerifyDate", optional=true)
    protected native OSStatus setVerifyDate0(NSDate verifyDate);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustGetVerifyTime", optional=true)
    public native double getVerifyTime();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustEvaluate", optional=true)
    protected native OSStatus evaluate0(IntPtr result);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecTrustEvaluateAsync", optional=true)
    protected native OSStatus evaluateAsync0(DispatchQueue queue, FunctionPtr result);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecTrustGetTrustResult", optional=true)
    protected native OSStatus getTrustResult0(IntPtr result);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustCopyPublicKey", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) SecKey getPublicKey();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustGetCertificateCount", optional=true)
    public native @MachineSizedSInt long getCertificateCount();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustGetCertificateAtIndex", optional=true)
    public native SecCertificate getCertificate(@MachineSizedSInt long ix);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="SecTrustCopyExceptions", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(NSObject.NoRetainMarshaler.class) NSData getExceptions();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="SecTrustSetExceptions", optional=true)
    public native boolean setExceptions(NSData exceptions);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="SecTrustCopyProperties", optional=true)
    public native SecTrustProperties getProperties();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecTrustCopyResult", optional=true)
    public native SecTrustResult getResult();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="SecTrustSetOCSPResponse", optional=true)
    protected native OSStatus setOCSPResponse0(CFType responseData);
    /*</methods>*/
}
