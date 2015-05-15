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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.addressbook.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PKPaymentAuthorizationViewControllerDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements PKPaymentAuthorizationViewControllerDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("paymentAuthorizationViewController:didAuthorizePayment:completion:")
    public void didAuthorizePayment(PKPaymentAuthorizationViewController controller, PKPayment payment, @Block VoidBlock1<PKPaymentAuthorizationStatus> completion) {}
    @NotImplemented("paymentAuthorizationViewControllerDidFinish:")
    public void didFinish(PKPaymentAuthorizationViewController controller) {}
    /**
     * @since Available in iOS 8.3 and later.
     */
    @NotImplemented("paymentAuthorizationViewControllerWillAuthorizePayment:")
    public void willAuthorizePayment(PKPaymentAuthorizationViewController controller) {}
    @NotImplemented("paymentAuthorizationViewController:didSelectShippingMethod:completion:")
    public void didSelectShippingMethod(PKPaymentAuthorizationViewController controller, PKShippingMethod shippingMethod, @Block VoidBlock2<PKPaymentAuthorizationStatus, NSArray<PKPaymentSummaryItem>> completion) {}
    @NotImplemented("paymentAuthorizationViewController:didSelectShippingAddress:completion:")
    public void didSelectShippingAddress(PKPaymentAuthorizationViewController controller, ABRecord address, @Block VoidBlock3<PKPaymentAuthorizationStatus, NSArray<PKShippingMethod>, NSArray<PKPaymentSummaryItem>> completion) {}
    /*</methods>*/
}
