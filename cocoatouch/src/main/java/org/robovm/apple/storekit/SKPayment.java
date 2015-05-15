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
package org.robovm.apple.storekit;

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
import org.robovm.apple.accounts.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 3.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("StoreKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKPayment/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SKPaymentPtr extends Ptr<SKPayment, SKPaymentPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKPayment.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKPayment() {}
    protected SKPayment(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "productIdentifier")
    public native String getProductIdentifier();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "requestData")
    public native NSData getRequestData();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "quantity")
    public native @MachineSizedSInt long getQuantity();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "applicationUsername")
    public native String getApplicationUsername();
    /**
     * @since Available in iOS 8.3 and later.
     */
    @Property(selector = "simulatesAskToBuyInSandbox")
    public native boolean simulatesAskToBuyInSandbox();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "paymentWithProduct:")
    public static native SKPayment create(SKProduct product);
    /*</methods>*/
}
