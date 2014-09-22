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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKProductsRequest/*</name>*/ 
    extends /*<extends>*/SKRequest/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SKProductsRequestPtr extends Ptr<SKProductsRequest, SKProductsRequestPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKProductsRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKProductsRequest() {}
    protected SKProductsRequest(SkipInit skipInit) { super(skipInit); }
    /**
     * @since Available in iOS 3.0 and later.
     */
    public SKProductsRequest(@org.robovm.rt.bro.annotation.Marshaler(NSSet.AsStringSetMarshaler.class) Set<String> productIdentifiers) { super((SkipInit) null); initObject(init(productIdentifiers)); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "delegate")
    public native SKProductsRequestDelegate getDelegate();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(SKProductsRequestDelegate v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "initWithProductIdentifiers:")
    protected native @Pointer long init(@org.robovm.rt.bro.annotation.Marshaler(NSSet.AsStringSetMarshaler.class) Set<String> productIdentifiers);
    /*</methods>*/
}
