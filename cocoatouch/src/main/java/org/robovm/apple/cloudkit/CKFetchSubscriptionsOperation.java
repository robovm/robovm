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
package org.robovm.apple.cloudkit;

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
import org.robovm.apple.corelocation.*;
import org.robovm.apple.contacts.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CloudKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CKFetchSubscriptionsOperation/*</name>*/ 
    extends /*<extends>*/CKDatabaseOperation/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CKFetchSubscriptionsOperationPtr extends Ptr<CKFetchSubscriptionsOperation, CKFetchSubscriptionsOperationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CKFetchSubscriptionsOperation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CKFetchSubscriptionsOperation() {}
    protected CKFetchSubscriptionsOperation(SkipInit skipInit) { super(skipInit); }
    public CKFetchSubscriptionsOperation(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> subscriptionIDs) { super((SkipInit) null); initObject(init(subscriptionIDs)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "subscriptionIDs")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getSubscriptionIDs();
    @Property(selector = "setSubscriptionIDs:")
    public native void setSubscriptionIDs(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> v);
    @Property(selector = "fetchSubscriptionCompletionBlock")
    public native @Block VoidBlock2<NSDictionary<NSString, CKSubscription>, NSError> getFetchSubscriptionCompletionBlock();
    @Property(selector = "setFetchSubscriptionCompletionBlock:")
    public native void setFetchSubscriptionCompletionBlock(@Block VoidBlock2<NSDictionary<NSString, CKSubscription>, NSError> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithSubscriptionIDs:")
    protected native @Pointer long init(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> subscriptionIDs);
    @Method(selector = "fetchAllSubscriptionsOperation")
    public static native CKFetchSubscriptionsOperation createFetchAllSubscriptionsOperation();
    /*</methods>*/
}
