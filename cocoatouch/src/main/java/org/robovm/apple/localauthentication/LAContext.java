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
package org.robovm.apple.localauthentication;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("LocalAuthentication") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/LAContext/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class LAContextPtr extends Ptr<LAContext, LAContextPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(LAContext.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public LAContext() {}
    protected LAContext(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "localizedFallbackTitle")
    public native String getLocalizedFallbackTitle();
    @Property(selector = "setLocalizedFallbackTitle:")
    public native void setLocalizedFallbackTitle(String v);
    /**
     * @since Available in iOS 8.3 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @Property(selector = "maxBiometryFailures")
    public native NSNumber getMaxBiometryFailures();
    /**
     * @since Available in iOS 8.3 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @Property(selector = "setMaxBiometryFailures:")
    public native void setMaxBiometryFailures(NSNumber v);
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "evaluatedPolicyDomainState")
    public native NSData getEvaluatedPolicyDomainState();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "touchIDAuthenticationAllowableReuseDuration")
    public native double getTouchIDAuthenticationAllowableReuseDuration();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "setTouchIDAuthenticationAllowableReuseDuration:")
    public native void setTouchIDAuthenticationAllowableReuseDuration(double v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 9.0 and later.
     */
    @GlobalValue(symbol="LATouchIDAuthenticationMaximumAllowableReuseDuration", optional=true)
    public static native double getMaximumAllowableReuseDuration();
    
    public boolean canEvaluatePolicy(LAPolicy policy) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = canEvaluatePolicy(policy, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "canEvaluatePolicy:error:")
    private native boolean canEvaluatePolicy(LAPolicy policy, NSError.NSErrorPtr error);
    @Method(selector = "evaluatePolicy:localizedReason:reply:")
    public native void evaluatePolicy(LAPolicy policy, String localizedReason, @Block VoidBlock2<Boolean, NSError> reply);
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Method(selector = "invalidate")
    public native void invalidate();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Method(selector = "setCredential:type:")
    public native boolean setCredential(NSData credential, LACredentialType type);
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Method(selector = "isCredentialSet:")
    public native boolean isCredentialSet(LACredentialType type);
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Method(selector = "evaluateAccessControl:operation:localizedReason:reply:")
    public native void evaluateAccessControl(SecAccessControl accessControl, LAAccessControlOperation operation, String localizedReason, @Block VoidBlock2<Boolean, NSError> reply);
    /*</methods>*/
}
