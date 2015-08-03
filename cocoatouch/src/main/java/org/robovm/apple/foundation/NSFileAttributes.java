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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Foundation")/*</annotations>*/
@Marshaler(/*<name>*/NSFileAttributes/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFileAttributes/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSFileAttributes toObject(Class<NSFileAttributes> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new NSFileAttributes(o);
        }
        @MarshalsPointer
        public static long toNative(NSFileAttributes o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<NSFileAttributes> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSFileAttributes> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new NSFileAttributes(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSFileAttributes> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (NSFileAttributes i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    NSFileAttributes(NSDictionary data) {
        super(data);
    }
    public NSFileAttributes() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(NSFileAttribute key) {
        return data.containsKey(key.value());
    }
    public NSObject get(NSFileAttribute key) {
        if (has(key)) {
            return data.get(key.value());
        }
        return null;
    }
    public NSFileAttributes set(NSFileAttribute key, NSObject value) {
        data.put(key.value(), value);
        return this;
    }
    

    public NSFileType getType() {
        if (has(NSFileAttribute.Type)) {
            NSString val = (NSString) get(NSFileAttribute.Type);
            return NSFileType.valueOf(val);
        }
        return null;
    }
    public NSFileAttributes setType(NSFileType type) {
        set(NSFileAttribute.Type, type.value());
        return this;
    }
    public long getSize() {
        if (has(NSFileAttribute.Size)) {
            NSNumber val = (NSNumber) get(NSFileAttribute.Size);
            return val.longValue();
        }
        return 0;
    }
    public NSFileAttributes setSize(long size) {
        set(NSFileAttribute.Size, NSNumber.valueOf(size));
        return this;
    }
    public NSDate getModificationDate() {
        if (has(NSFileAttribute.ModificationDate)) {
            NSDate val = (NSDate) get(NSFileAttribute.ModificationDate);
            return val;
        }
        return null;
    }
    public NSFileAttributes setModificationDate(NSDate modificationDate) {
        set(NSFileAttribute.ModificationDate, modificationDate);
        return this;
    }
    public long getReferenceCount() {
        if (has(NSFileAttribute.ReferenceCount)) {
            NSNumber val = (NSNumber) get(NSFileAttribute.ReferenceCount);
            return val.longValue();
        }
        return 0;
    }
    public NSFileAttributes setReferenceCount(long referenceCount) {
        set(NSFileAttribute.ReferenceCount, NSNumber.valueOf(referenceCount));
        return this;
    }
    public long getDeviceIdentifier() {
        if (has(NSFileAttribute.DeviceIdentifier)) {
            NSNumber val = (NSNumber) get(NSFileAttribute.DeviceIdentifier);
            return val.longValue();
        }
        return 0;
    }
    public NSFileAttributes setDeviceIdentifier(long deviceIdentifier) {
        set(NSFileAttribute.DeviceIdentifier, NSNumber.valueOf(deviceIdentifier));
        return this;
    }
    public String getOwnerAccountName() {
        if (has(NSFileAttribute.OwnerAccountName)) {
            NSString val = (NSString) get(NSFileAttribute.OwnerAccountName);
            return val.toString();
        }
        return null;
    }
    public NSFileAttributes setOwnerAccountName(String ownerAccountName) {
        set(NSFileAttribute.OwnerAccountName, new NSString(ownerAccountName));
        return this;
    }
    public String getGroupOwnerAccountName() {
        if (has(NSFileAttribute.GroupOwnerAccountName)) {
            NSString val = (NSString) get(NSFileAttribute.GroupOwnerAccountName);
            return val.toString();
        }
        return null;
    }
    public NSFileAttributes setGroupOwnerAccountName(String groupOwnerAccountName) {
        set(NSFileAttribute.GroupOwnerAccountName, new NSString(groupOwnerAccountName));
        return this;
    }
    public short getPosixPermissions() {
        if (has(NSFileAttribute.PosixPermissions)) {
            NSNumber val = (NSNumber) get(NSFileAttribute.PosixPermissions);
            return val.shortValue();
        }
        return 0;
    }
    public NSFileAttributes setPosixPermissions(short posixPermissions) {
        set(NSFileAttribute.PosixPermissions, NSNumber.valueOf(posixPermissions));
        return this;
    }
    public long getSystemFileNumber() {
        if (has(NSFileAttribute.SystemFileNumber)) {
            NSNumber val = (NSNumber) get(NSFileAttribute.SystemFileNumber);
            return val.longValue();
        }
        return 0;
    }
    public NSFileAttributes setSystemFileNumber(long systemFileNumber) {
        set(NSFileAttribute.SystemFileNumber, NSNumber.valueOf(systemFileNumber));
        return this;
    }
    public boolean isExtensionHidden() {
        if (has(NSFileAttribute.ExtensionHidden)) {
            NSNumber val = (NSNumber) get(NSFileAttribute.ExtensionHidden);
            return val.booleanValue();
        }
        return false;
    }
    public NSFileAttributes setExtensionHidden(boolean extensionHidden) {
        set(NSFileAttribute.ExtensionHidden, NSNumber.valueOf(extensionHidden));
        return this;
    }
    public long getHFSCreatorCode() {
        if (has(NSFileAttribute.HFSCreatorCode)) {
            NSNumber val = (NSNumber) get(NSFileAttribute.HFSCreatorCode);
            return val.longValue();
        }
        return 0;
    }
    public NSFileAttributes setHFSCreatorCode(long hFSCreatorCode) {
        set(NSFileAttribute.HFSCreatorCode, NSNumber.valueOf(hFSCreatorCode));
        return this;
    }
    public long getHFSTypeCode() {
        if (has(NSFileAttribute.HFSTypeCode)) {
            NSNumber val = (NSNumber) get(NSFileAttribute.HFSTypeCode);
            return val.longValue();
        }
        return 0;
    }
    public NSFileAttributes setHFSTypeCode(long hFSTypeCode) {
        set(NSFileAttribute.HFSTypeCode, NSNumber.valueOf(hFSTypeCode));
        return this;
    }
    public boolean isImmutable() {
        if (has(NSFileAttribute.Immutable)) {
            NSNumber val = (NSNumber) get(NSFileAttribute.Immutable);
            return val.booleanValue();
        }
        return false;
    }
    public NSFileAttributes setImmutable(boolean immutable) {
        set(NSFileAttribute.Immutable, NSNumber.valueOf(immutable));
        return this;
    }
    public boolean appendsOnly() {
        if (has(NSFileAttribute.AppendOnly)) {
            NSNumber val = (NSNumber) get(NSFileAttribute.AppendOnly);
            return val.booleanValue();
        }
        return false;
    }
    public NSFileAttributes setAppendsOnly(boolean appendsOnly) {
        set(NSFileAttribute.AppendOnly, NSNumber.valueOf(appendsOnly));
        return this;
    }
    public NSDate getCreationDate() {
        if (has(NSFileAttribute.CreationDate)) {
            NSDate val = (NSDate) get(NSFileAttribute.CreationDate);
            return val;
        }
        return null;
    }
    public NSFileAttributes setCreationDate(NSDate creationDate) {
        set(NSFileAttribute.CreationDate, creationDate);
        return this;
    }
    public long getOwnerAccountID() {
        if (has(NSFileAttribute.OwnerAccountID)) {
            NSNumber val = (NSNumber) get(NSFileAttribute.OwnerAccountID);
            return val.longValue();
        }
        return 0;
    }
    public NSFileAttributes setOwnerAccountID(long ownerAccountID) {
        set(NSFileAttribute.OwnerAccountID, NSNumber.valueOf(ownerAccountID));
        return this;
    }
    public long getGroupOwnerAccountID() {
        if (has(NSFileAttribute.GroupOwnerAccountID)) {
            NSNumber val = (NSNumber) get(NSFileAttribute.GroupOwnerAccountID);
            return val.longValue();
        }
        return 0;
    }
    public NSFileAttributes setGroupOwnerAccountID(long groupOwnerAccountID) {
        set(NSFileAttribute.GroupOwnerAccountID, NSNumber.valueOf(groupOwnerAccountID));
        return this;
    }
    public boolean isBusy() {
        if (has(NSFileAttribute.Busy)) {
            NSNumber val = (NSNumber) get(NSFileAttribute.Busy);
            return val.booleanValue();
        }
        return false;
    }
    public NSFileAttributes setBusy(boolean busy) {
        set(NSFileAttribute.Busy, NSNumber.valueOf(busy));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSFileProtection getProtection() {
        if (has(NSFileAttribute.ProtectionKey)) {
            NSString val = (NSString) get(NSFileAttribute.ProtectionKey);
            return NSFileProtection.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSFileAttributes setProtection(NSFileProtection protection) {
        set(NSFileAttribute.ProtectionKey, protection.value());
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    /*</keys>*/
}
