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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("CloudKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CKModifyRecordsOperation/*</name>*/ 
    extends /*<extends>*/CKDatabaseOperation/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CKModifyRecordsOperationPtr extends Ptr<CKModifyRecordsOperation, CKModifyRecordsOperationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CKModifyRecordsOperation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CKModifyRecordsOperation() {}
    protected CKModifyRecordsOperation(SkipInit skipInit) { super(skipInit); }
    public CKModifyRecordsOperation(NSArray<CKRecord> records, NSArray<CKRecordID> recordIDs) { super((SkipInit) null); initObject(init(records, recordIDs)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "recordsToSave")
    public native NSArray<CKRecord> getRecordsToSave();
    @Property(selector = "setRecordsToSave:")
    public native void setRecordsToSave(NSArray<CKRecord> v);
    @Property(selector = "recordIDsToDelete")
    public native NSArray<CKRecordID> getRecordIDsToDelete();
    @Property(selector = "setRecordIDsToDelete:")
    public native void setRecordIDsToDelete(NSArray<CKRecordID> v);
    @Property(selector = "savePolicy")
    public native CKRecordSavePolicy getSavePolicy();
    @Property(selector = "setSavePolicy:")
    public native void setSavePolicy(CKRecordSavePolicy v);
    @Property(selector = "clientChangeTokenData")
    public native NSData getClientChangeTokenData();
    @Property(selector = "setClientChangeTokenData:")
    public native void setClientChangeTokenData(NSData v);
    @Property(selector = "atomic")
    public native boolean isAtomic();
    @Property(selector = "setAtomic:")
    public native void setAtomic(boolean v);
    @Property(selector = "perRecordProgressBlock")
    public native @Block VoidBlock2<CKRecord, Double> getPerRecordProgressBlock();
    @Property(selector = "setPerRecordProgressBlock:")
    public native void setPerRecordProgressBlock(@Block VoidBlock2<CKRecord, Double> v);
    @Property(selector = "perRecordCompletionBlock")
    public native @Block VoidBlock2<CKRecord, NSError> getPerRecordCompletionBlock();
    @Property(selector = "setPerRecordCompletionBlock:")
    public native void setPerRecordCompletionBlock(@Block VoidBlock2<CKRecord, NSError> v);
    @Property(selector = "modifyRecordsCompletionBlock")
    public native @Block VoidBlock3<NSArray<CKRecord>, NSArray<CKRecordID>, NSError> getModifyRecordsCompletionBlock();
    @Property(selector = "setModifyRecordsCompletionBlock:")
    public native void setModifyRecordsCompletionBlock(@Block VoidBlock3<NSArray<CKRecord>, NSArray<CKRecordID>, NSError> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithRecordsToSave:recordIDsToDelete:")
    protected native @Pointer long init(NSArray<CKRecord> records, NSArray<CKRecordID> recordIDs);
    /*</methods>*/
}
