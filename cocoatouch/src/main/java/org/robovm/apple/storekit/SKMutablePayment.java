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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKMutablePayment/*</name>*/ 
    extends /*<extends>*/SKPayment/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SKMutablePaymentPtr extends Ptr<SKMutablePayment, SKMutablePaymentPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKMutablePayment.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKMutablePayment() {}
    protected SKMutablePayment(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "applicationUsername")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native String getApplicationUsername();
    @Property(selector = "setApplicationUsername:")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native void setApplicationUsername(String v);
    @Property(selector = "productIdentifier")
    /**
     * @since Available in iOS 3.0 and later.
     */
    public native String getProductIdentifier();
    @Property(selector = "setProductIdentifier:")
    /**
     * @since Available in iOS 3.0 and later.
     */
    public native void setProductIdentifier(String v);
    @Property(selector = "quantity")
    /**
     * @since Available in iOS 3.0 and later.
     */
    public native @MachineSizedSInt long getQuantity();
    @Property(selector = "setQuantity:")
    /**
     * @since Available in iOS 3.0 and later.
     */
    public native void setQuantity(@MachineSizedSInt long v);
    @Property(selector = "requestData")
    /**
     * @since Available in iOS 3.0 and later.
     */
    public native NSData getRequestData();
    @Property(selector = "setRequestData:")
    /**
     * @since Available in iOS 3.0 and later.
     */
    public native void setRequestData(NSData v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
