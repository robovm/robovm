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
package org.robovm.apple.passkit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.addressbook.*;
import org.robovm.apple.contacts.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("PassKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PKAddPaymentPassRequest/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class PKAddPaymentPassRequestPtr extends Ptr<PKAddPaymentPassRequest, PKAddPaymentPassRequestPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(PKAddPaymentPassRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public PKAddPaymentPassRequest() {}
    protected PKAddPaymentPassRequest(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "encryptedPassData")
    public native NSData getEncryptedPassData();
    @Property(selector = "setEncryptedPassData:")
    public native void setEncryptedPassData(NSData v);
    @Property(selector = "activationData")
    public native NSData getActivationData();
    @Property(selector = "setActivationData:")
    public native void setActivationData(NSData v);
    @Property(selector = "ephemeralPublicKey")
    public native NSData getEphemeralPublicKey();
    @Property(selector = "setEphemeralPublicKey:")
    public native void setEphemeralPublicKey(NSData v);
    @Property(selector = "wrappedKey")
    public native NSData getWrappedKey();
    @Property(selector = "setWrappedKey:")
    public native void setWrappedKey(NSData v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
