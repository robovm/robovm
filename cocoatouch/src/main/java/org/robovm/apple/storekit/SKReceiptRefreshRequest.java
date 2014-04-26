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
import org.robovm.apple.storekit.*;
/*</imports>*/

/**
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("StoreKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SKReceiptRefreshRequest/*</name>*/ 
    extends /*<extends>*/SKRequest/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SKReceiptRefreshRequestPtr extends Ptr<SKReceiptRefreshRequest, SKReceiptRefreshRequestPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SKReceiptRefreshRequest.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SKReceiptRefreshRequest() {}
    protected SKReceiptRefreshRequest(SkipInit skipInit) { super(skipInit); }
    public SKReceiptRefreshRequest(NSDictionary<?, ?> properties) { super((SkipInit) null); initObject(init(properties)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "receiptProperties")
    public native NSDictionary<?, ?> getReceiptProperties();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithReceiptProperties:")
    public native @Pointer long init(NSDictionary<?, ?> properties);
    @GlobalValue(symbol="SKReceiptPropertyIsExpired", optional=true)
    public static native String PropertyIsExpired();
    @GlobalValue(symbol="SKReceiptPropertyIsRevoked", optional=true)
    public static native String PropertyIsRevoked();
    @GlobalValue(symbol="SKReceiptPropertyIsVolumePurchase", optional=true)
    public static native String PropertyIsVolumePurchase();
    /*</methods>*/
}
