/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CloudKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CKSubscription/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CKSubscriptionPtr extends Ptr<CKSubscription, CKSubscriptionPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CKSubscription.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CKSubscription() {}
    protected CKSubscription(SkipInit skipInit) { super(skipInit); }
    public CKSubscription(NSCoder aDecoder) { super((SkipInit) null); initObject(init(aDecoder)); }
    public CKSubscription(String recordType, NSPredicate predicate, CKSubscriptionOptions subscriptionOptions) { super((SkipInit) null); initObject(init(recordType, predicate, subscriptionOptions)); }
    public CKSubscription(String recordType, NSPredicate predicate, String subscriptionID, CKSubscriptionOptions subscriptionOptions) { super((SkipInit) null); initObject(init(recordType, predicate, subscriptionID, subscriptionOptions)); }
    public CKSubscription(CKRecordZoneID zoneID, CKSubscriptionOptions subscriptionOptions) { super((SkipInit) null); initObject(init(zoneID, subscriptionOptions)); }
    public CKSubscription(CKRecordZoneID zoneID, String subscriptionID, CKSubscriptionOptions subscriptionOptions) { super((SkipInit) null); initObject(init(zoneID, subscriptionID, subscriptionOptions)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "subscriptionID")
    public native String getSubscriptionID();
    @Property(selector = "subscriptionType")
    public native CKSubscriptionType getSubscriptionType();
    @Property(selector = "recordType")
    public native String getRecordType();
    @Property(selector = "predicate")
    public native NSPredicate getPredicate();
    @Property(selector = "subscriptionOptions")
    public native CKSubscriptionOptions getSubscriptionOptions();
    @Property(selector = "notificationInfo")
    public native CKNotificationInfo getNotificationInfo();
    @Property(selector = "setNotificationInfo:")
    public native void setNotificationInfo(CKNotificationInfo v);
    @Property(selector = "zoneID")
    public native CKRecordZoneID getZoneID();
    @Property(selector = "setZoneID:")
    public native void setZoneID(CKRecordZoneID v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder aDecoder);
    @Method(selector = "initWithRecordType:predicate:options:")
    protected native @Pointer long init(String recordType, NSPredicate predicate, CKSubscriptionOptions subscriptionOptions);
    @Method(selector = "initWithRecordType:predicate:subscriptionID:options:")
    protected native @Pointer long init(String recordType, NSPredicate predicate, String subscriptionID, CKSubscriptionOptions subscriptionOptions);
    @Method(selector = "initWithZoneID:options:")
    protected native @Pointer long init(CKRecordZoneID zoneID, CKSubscriptionOptions subscriptionOptions);
    @Method(selector = "initWithZoneID:subscriptionID:options:")
    protected native @Pointer long init(CKRecordZoneID zoneID, String subscriptionID, CKSubscriptionOptions subscriptionOptions);
    /*</methods>*/
}
