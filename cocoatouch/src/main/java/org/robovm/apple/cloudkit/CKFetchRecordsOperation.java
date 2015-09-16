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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CKFetchRecordsOperation/*</name>*/ 
    extends /*<extends>*/CKDatabaseOperation/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CKFetchRecordsOperationPtr extends Ptr<CKFetchRecordsOperation, CKFetchRecordsOperationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CKFetchRecordsOperation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CKFetchRecordsOperation() {}
    protected CKFetchRecordsOperation(SkipInit skipInit) { super(skipInit); }
    public CKFetchRecordsOperation(NSArray<CKRecordID> recordIDs) { super((SkipInit) null); initObject(init(recordIDs)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "recordIDs")
    public native NSArray<CKRecordID> getRecordIDs();
    @Property(selector = "setRecordIDs:")
    public native void setRecordIDs(NSArray<CKRecordID> v);
    @Property(selector = "desiredKeys")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getDesiredKeys();
    @Property(selector = "setDesiredKeys:")
    public native void setDesiredKeys(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> v);
    @Property(selector = "perRecordProgressBlock")
    public native @Block VoidBlock2<CKRecordID, Double> getPerRecordProgressBlock();
    @Property(selector = "setPerRecordProgressBlock:")
    public native void setPerRecordProgressBlock(@Block VoidBlock2<CKRecordID, Double> v);
    @Property(selector = "perRecordCompletionBlock")
    public native @Block VoidBlock3<CKRecord, CKRecordID, NSError> getPerRecordCompletionBlock();
    @Property(selector = "setPerRecordCompletionBlock:")
    public native void setPerRecordCompletionBlock(@Block VoidBlock3<CKRecord, CKRecordID, NSError> v);
    @Property(selector = "fetchRecordsCompletionBlock")
    public native @Block VoidBlock2<NSDictionary<CKRecordID, CKRecord>, NSError> getFetchRecordsCompletionBlock();
    @Property(selector = "setFetchRecordsCompletionBlock:")
    public native void setFetchRecordsCompletionBlock(@Block VoidBlock2<NSDictionary<CKRecordID, CKRecord>, NSError> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithRecordIDs:")
    protected native @Pointer long init(NSArray<CKRecordID> recordIDs);
    @Method(selector = "fetchCurrentUserRecordOperation")
    public static native CKFetchRecordsOperation createFetchCurrentUserRecordOperation();
    /*</methods>*/
}
