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
/*</javadoc>*/
/*<annotations>*/@Library("StoreKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/StoreKit/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(StoreKit.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="SKDownloadTimeRemainingUnknown", optional=true)
    public static native double DownloadTimeRemainingUnknown();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="SKErrorDomain", optional=true)
    public static native NSString ErrorDomain();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="SKReceiptPropertyIsExpired", optional=true)
    public static native NSString ReceiptPropertyIsExpired();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="SKReceiptPropertyIsRevoked", optional=true)
    public static native NSString ReceiptPropertyIsRevoked();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="SKReceiptPropertyIsVolumePurchase", optional=true)
    public static native NSString ReceiptPropertyIsVolumePurchase();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="SKStoreProductParameterITunesItemIdentifier", optional=true)
    public static native NSString StoreProductParameterITunesItemIdentifier();
    /*</methods>*/
}
