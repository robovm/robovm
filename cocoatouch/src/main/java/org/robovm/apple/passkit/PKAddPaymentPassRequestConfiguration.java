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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("PassKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PKAddPaymentPassRequestConfiguration/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class PKAddPaymentPassRequestConfigurationPtr extends Ptr<PKAddPaymentPassRequestConfiguration, PKAddPaymentPassRequestConfigurationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(PKAddPaymentPassRequestConfiguration.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public PKAddPaymentPassRequestConfiguration() {}
    protected PKAddPaymentPassRequestConfiguration(SkipInit skipInit) { super(skipInit); }
    public PKAddPaymentPassRequestConfiguration(String encryptionScheme) { super((SkipInit) null); initObject(init(encryptionScheme)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "encryptionScheme")
    public native String getEncryptionScheme();
    @Property(selector = "cardholderName")
    public native String getCardholderName();
    @Property(selector = "setCardholderName:")
    public native void setCardholderName(String v);
    @Property(selector = "primaryAccountSuffix")
    public native String getPrimaryAccountSuffix();
    @Property(selector = "setPrimaryAccountSuffix:")
    public native void setPrimaryAccountSuffix(String v);
    @Property(selector = "localizedDescription")
    public native String getLocalizedDescription();
    @Property(selector = "setLocalizedDescription:")
    public native void setLocalizedDescription(String v);
    @Property(selector = "primaryAccountIdentifier")
    public native String getPrimaryAccountIdentifier();
    @Property(selector = "setPrimaryAccountIdentifier:")
    public native void setPrimaryAccountIdentifier(String v);
    @Property(selector = "paymentNetwork")
    public native String getPaymentNetwork();
    @Property(selector = "setPaymentNetwork:")
    public native void setPaymentNetwork(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithEncryptionScheme:")
    protected native @Pointer long init(String encryptionScheme);
    /*</methods>*/
}
