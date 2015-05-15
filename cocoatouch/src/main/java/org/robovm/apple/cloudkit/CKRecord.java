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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CKRecord/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CKRecordPtr extends Ptr<CKRecord, CKRecordPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CKRecord.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CKRecord() {}
    protected CKRecord(SkipInit skipInit) { super(skipInit); }
    public CKRecord(String recordType) { super((SkipInit) null); initObject(init(recordType)); }
    public CKRecord(String recordType, CKRecordID recordID) { super((SkipInit) null); initObject(init(recordType, recordID)); }
    public CKRecord(String recordType, CKRecordZoneID zoneID) { super((SkipInit) null); initObject(init(recordType, zoneID)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "recordType")
    public native String getRecordType();
    @Property(selector = "recordID")
    public native CKRecordID getRecordID();
    @Property(selector = "recordChangeTag")
    public native String getRecordChangeTag();
    @Property(selector = "creatorUserRecordID")
    public native CKRecordID getCreatorUserRecordID();
    @Property(selector = "creationDate")
    public native NSDate getCreationDate();
    @Property(selector = "lastModifiedUserRecordID")
    public native CKRecordID getLastModifiedUserRecordID();
    @Property(selector = "modificationDate")
    public native NSDate getModificationDate();
    /*</properties>*/
    /*<members>*//*</members>*/
    public void put(String key, String object) {
        put(key, new NSString(object));
    }
    public void put(String key, NSString object) {
        put(object, key);
    }
    public void put(String key, Number object) {
        put(key, NSNumber.valueOf(object));
    }
    public void put(String key, NSNumber object) {
        put(object, key);
    }
    public void put(String key, NSData object) {
        put(object, key);
    }
    public void put(String key, NSDate object) {
        put(object, key);
    }
    public void put(String key, CLLocation object) {
        put(object, key);
    }
    public void put(String key, CKAsset object) {
        put(object, key);
    }
    public void put(String key, CKReference object) {
        put(object, key);
    }
    public void put(String key, NSArray<?> object) {
        put(object, key);
    }
    public void put(String key, List<String> object) {
        put(NSArray.fromStrings(object), key);
    }
    /*<methods>*/
    @Method(selector = "initWithRecordType:")
    protected native @Pointer long init(String recordType);
    @Method(selector = "initWithRecordType:recordID:")
    protected native @Pointer long init(String recordType, CKRecordID recordID);
    @Method(selector = "initWithRecordType:zoneID:")
    protected native @Pointer long init(String recordType, CKRecordZoneID zoneID);
    @Method(selector = "objectForKey:")
    public native NSObject get(String key);
    @Method(selector = "setObject:forKey:")
    protected native void put(NSObject object, String key);
    @Method(selector = "allKeys")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getAllKeys();
    @Method(selector = "allTokens")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getAllTokens();
    @Method(selector = "changedKeys")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getChangedKeys();
    @Method(selector = "encodeSystemFieldsWithCoder:")
    public native void encodeSystemFields(NSCoder coder);
    /*</methods>*/
}
