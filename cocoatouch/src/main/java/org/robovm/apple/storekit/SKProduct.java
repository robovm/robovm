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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKProduct/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SKProductPtr extends Ptr<SKProduct, SKProductPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKProduct.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKProduct() {}
    protected SKProduct(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "localizedDescription")
    public native String getLocalizedDescription();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "localizedTitle")
    public native String getLocalizedTitle();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "price")
    public native NSDecimalNumber getPrice();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "priceLocale")
    public native NSLocale getPriceLocale();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "productIdentifier")
    public native String getProductIdentifier();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "isDownloadable")
    public native boolean isDownloadable();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "downloadContentLengths")
    public native NSArray<NSNumber> getDownloadContentLengths();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "downloadContentVersion")
    public native String getDownloadContentVersion();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
