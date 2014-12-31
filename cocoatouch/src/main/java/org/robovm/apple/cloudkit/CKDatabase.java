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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CKDatabase/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CKDatabasePtr extends Ptr<CKDatabase, CKDatabasePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CKDatabase.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CKDatabase() {}
    protected CKDatabase(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "addOperation:")
    public native void addOperation(CKDatabaseOperation operation);
    @Method(selector = "fetchRecordWithID:completionHandler:")
    public native void fetchRecord(CKRecordID recordID, @Block VoidBlock2<CKRecord, NSError> completionHandler);
    @Method(selector = "saveRecord:completionHandler:")
    public native void saveRecord(CKRecord record, @Block VoidBlock2<CKRecord, NSError> completionHandler);
    @Method(selector = "deleteRecordWithID:completionHandler:")
    public native void deleteRecord(CKRecordID recordID, @Block VoidBlock2<CKRecordID, NSError> completionHandler);
    @Method(selector = "performQuery:inZoneWithID:completionHandler:")
    public native void performQuery(CKQuery query, CKRecordZoneID zoneID, @Block VoidBlock2<NSArray<CKRecord>, NSError> completionHandler);
    @Method(selector = "fetchAllRecordZonesWithCompletionHandler:")
    public native void fetchAllRecordZones(@Block VoidBlock2<NSArray<CKRecordZone>, NSError> completionHandler);
    @Method(selector = "fetchRecordZoneWithID:completionHandler:")
    public native void fetchRecordZone(CKRecordZoneID zoneID, @Block VoidBlock2<CKRecordZone, NSError> completionHandler);
    @Method(selector = "saveRecordZone:completionHandler:")
    public native void saveRecordZone(CKRecordZone zone, @Block VoidBlock2<CKRecordZone, NSError> completionHandler);
    @Method(selector = "deleteRecordZoneWithID:completionHandler:")
    public native void deleteRecordZone(CKRecordZoneID zoneID, @Block VoidBlock2<CKRecordZoneID, NSError> completionHandler);
    @Method(selector = "fetchSubscriptionWithID:completionHandler:")
    public native void fetchSubscription(String subscriptionID, @Block VoidBlock2<CKSubscription, NSError> completionHandler);
    @Method(selector = "fetchAllSubscriptionsWithCompletionHandler:")
    public native void fetchAllSubscriptions(@Block VoidBlock2<NSArray<CKSubscription>, NSError> completionHandler);
    @Method(selector = "saveSubscription:completionHandler:")
    public native void saveSubscription(CKSubscription subscription, @Block VoidBlock2<CKSubscription, NSError> completionHandler);
    @Method(selector = "deleteSubscriptionWithID:completionHandler:")
    public native void deleteSubscription(String subscriptionID, @Block VoidBlock2<String, NSError> completionHandler);
    /*</methods>*/
}
