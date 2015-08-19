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
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("PassKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PKPaymentRequest/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class PKPaymentRequestPtr extends Ptr<PKPaymentRequest, PKPaymentRequestPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(PKPaymentRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public PKPaymentRequest() {}
    protected PKPaymentRequest(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "merchantIdentifier")
    public native String getMerchantIdentifier();
    @Property(selector = "setMerchantIdentifier:")
    public native void setMerchantIdentifier(String v);
    @Property(selector = "countryCode")
    public native String getCountryCode();
    @Property(selector = "setCountryCode:")
    public native void setCountryCode(String v);
    @Property(selector = "supportedNetworks")
    public native @org.robovm.rt.bro.annotation.Marshaler(PKPaymentNetwork.AsListMarshaler.class) List<PKPaymentNetwork> getSupportedNetworks();
    @Property(selector = "setSupportedNetworks:")
    public native void setSupportedNetworks(@org.robovm.rt.bro.annotation.Marshaler(PKPaymentNetwork.AsListMarshaler.class) List<PKPaymentNetwork> v);
    @Property(selector = "merchantCapabilities")
    public native PKMerchantCapability getMerchantCapabilities();
    @Property(selector = "setMerchantCapabilities:")
    public native void setMerchantCapabilities(PKMerchantCapability v);
    @Property(selector = "paymentSummaryItems")
    public native NSArray<PKPaymentSummaryItem> getPaymentSummaryItems();
    @Property(selector = "setPaymentSummaryItems:")
    public native void setPaymentSummaryItems(NSArray<PKPaymentSummaryItem> v);
    @Property(selector = "currencyCode")
    public native String getCurrencyCode();
    @Property(selector = "setCurrencyCode:")
    public native void setCurrencyCode(String v);
    @Property(selector = "requiredBillingAddressFields")
    public native PKAddressField getRequiredBillingAddressFields();
    @Property(selector = "setRequiredBillingAddressFields:")
    public native void setRequiredBillingAddressFields(PKAddressField v);
    /**
     * @since Available in iOS 8.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @Property(selector = "billingAddress")
    public native ABRecord getBillingAddress();
    /**
     * @since Available in iOS 8.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @Property(selector = "setBillingAddress:", strongRef = true)
    public native void setBillingAddress(ABRecord v);
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "billingContact")
    public native PKContact getBillingContact();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "setBillingContact:")
    public native void setBillingContact(PKContact v);
    @Property(selector = "requiredShippingAddressFields")
    public native PKAddressField getRequiredShippingAddressFields();
    @Property(selector = "setRequiredShippingAddressFields:")
    public native void setRequiredShippingAddressFields(PKAddressField v);
    /**
     * @since Available in iOS 8.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @Property(selector = "shippingAddress")
    public native ABRecord getShippingAddress();
    /**
     * @since Available in iOS 8.0 and later.
     * @deprecated Deprecated in iOS 9.0.
     */
    @Deprecated
    @Property(selector = "setShippingAddress:", strongRef = true)
    public native void setShippingAddress(ABRecord v);
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "shippingContact")
    public native PKContact getShippingContact();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "setShippingContact:")
    public native void setShippingContact(PKContact v);
    @Property(selector = "shippingMethods")
    public native NSArray<PKShippingMethod> getShippingMethods();
    @Property(selector = "setShippingMethods:")
    public native void setShippingMethods(NSArray<PKShippingMethod> v);
    /**
     * @since Available in iOS 8.3 and later.
     */
    @Property(selector = "shippingType")
    public native PKShippingType getShippingType();
    /**
     * @since Available in iOS 8.3 and later.
     */
    @Property(selector = "setShippingType:")
    public native void setShippingType(PKShippingType v);
    @Property(selector = "applicationData")
    public native NSData getApplicationData();
    @Property(selector = "setApplicationData:")
    public native void setApplicationData(NSData v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
