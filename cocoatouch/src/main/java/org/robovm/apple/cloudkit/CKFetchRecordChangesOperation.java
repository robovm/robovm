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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CKFetchRecordChangesOperation/*</name>*/ 
    extends /*<extends>*/CKDatabaseOperation/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CKFetchRecordChangesOperationPtr extends Ptr<CKFetchRecordChangesOperation, CKFetchRecordChangesOperationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CKFetchRecordChangesOperation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CKFetchRecordChangesOperation() {}
    protected CKFetchRecordChangesOperation(SkipInit skipInit) { super(skipInit); }
    public CKFetchRecordChangesOperation(CKRecordZoneID recordZoneID, CKServerChangeToken previousServerChangeToken) { super((SkipInit) null); initObject(init(recordZoneID, previousServerChangeToken)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "recordZoneID")
    public native CKRecordZoneID getRecordZoneID();
    @Property(selector = "setRecordZoneID:")
    public native void setRecordZoneID(CKRecordZoneID v);
    @Property(selector = "previousServerChangeToken")
    public native CKServerChangeToken getPreviousServerChangeToken();
    @Property(selector = "setPreviousServerChangeToken:")
    public native void setPreviousServerChangeToken(CKServerChangeToken v);
    @Property(selector = "resultsLimit")
    public native @MachineSizedUInt long getResultsLimit();
    @Property(selector = "setResultsLimit:")
    public native void setResultsLimit(@MachineSizedUInt long v);
    @Property(selector = "desiredKeys")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getDesiredKeys();
    @Property(selector = "setDesiredKeys:")
    public native void setDesiredKeys(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> v);
    @Property(selector = "recordChangedBlock")
    public native @Block VoidBlock1<CKRecord> getRecordChangedBlock();
    @Property(selector = "setRecordChangedBlock:")
    public native void setRecordChangedBlock(@Block VoidBlock1<CKRecord> v);
    @Property(selector = "recordWithIDWasDeletedBlock")
    public native @Block VoidBlock1<CKRecordID> getRecordWithIDWasDeletedBlock();
    @Property(selector = "setRecordWithIDWasDeletedBlock:")
    public native void setRecordWithIDWasDeletedBlock(@Block VoidBlock1<CKRecordID> v);
    @Property(selector = "moreComing")
    public native boolean isMoreComing();
    @Property(selector = "fetchRecordChangesCompletionBlock")
    public native @Block VoidBlock3<CKServerChangeToken, NSData, NSError> getFetchRecordChangesCompletionBlock();
    @Property(selector = "setFetchRecordChangesCompletionBlock:")
    public native void setFetchRecordChangesCompletionBlock(@Block VoidBlock3<CKServerChangeToken, NSData, NSError> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithRecordZoneID:previousServerChangeToken:")
    protected native @Pointer long init(CKRecordZoneID recordZoneID, CKServerChangeToken previousServerChangeToken);
    /*</methods>*/
}
