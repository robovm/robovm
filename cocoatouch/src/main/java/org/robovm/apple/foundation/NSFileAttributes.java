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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(NSFileAttributes.Marshaler.class)
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFileAttributes/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static NSFileAttributes toObject(Class<NSFileAttributes> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
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

    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected NSFileAttributes(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public NSFileAttributes() {
        this.data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(NSFileAttributes.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    public void set(NSFileAttribute attribute, NSObject value) {
        data.put(attribute.value(), value);
    }
    public NSObject get(NSFileAttribute attribute) {
        return data.get(attribute.value());
    }
    public boolean contains(NSFileAttribute attribute) {
        return data.containsKey(attribute.value());
    }
    
    
    public NSFileType getType() {
        if (contains(NSFileAttribute.Type)) {
            NSString val = (NSString)get(NSFileAttribute.Type);
            return NSFileType.valueOf(val);
        }
        return null;
    }
    public void setType(NSFileType type) {
        set(NSFileAttribute.Type, type.value());
    }
    public long getSize() {
        if (contains(NSFileAttribute.Size)) {
            NSNumber val = (NSNumber)get(NSFileAttribute.Size);
            return val.longValue();
        }
        return 0;
    }
    public void setSize(long size) {
        set(NSFileAttribute.Size, NSNumber.valueOf(size));
    }
    public NSDate getModificationDate() {
        if (contains(NSFileAttribute.ModificationDate)) {
            NSDate val = (NSDate)get(NSFileAttribute.ModificationDate);
            return val;
        }
        return null;
    }
    public void setModificationDate(NSDate date) {
        set(NSFileAttribute.ModificationDate, date);
    }
    public long getReferenceCount() {
        if (contains(NSFileAttribute.ReferenceCount)) {
            NSNumber val = (NSNumber)get(NSFileAttribute.ReferenceCount);
            return val.longValue();
        }
        return 0;
    }
    public void setReferenceCount(long count) {
        set(NSFileAttribute.ReferenceCount, NSNumber.valueOf(count));
    }
    public long getDeviceIdentifier() {
        if (contains(NSFileAttribute.DeviceIdentifier)) {
            NSNumber val = (NSNumber)get(NSFileAttribute.DeviceIdentifier);
            return val.longValue();
        }
        return 0;
    }
    public void setDeviceIdentifier(long id) {
        set(NSFileAttribute.DeviceIdentifier, NSNumber.valueOf(id));
    }
    public String getOwnerAccountName() {
        if (contains(NSFileAttribute.OwnerAccountName)) {
            NSString val = (NSString)get(NSFileAttribute.OwnerAccountName);
            return val.toString();
        }
        return null;
    }
    public void setOwnerAccountName(String name) {
        set(NSFileAttribute.OwnerAccountName, new NSString(name));
    }
    public String getGroupOwnerAccountName() {
        if (contains(NSFileAttribute.GroupOwnerAccountName)) {
            NSString val = (NSString)get(NSFileAttribute.GroupOwnerAccountName);
            return val.toString();
        }
        return null;
    }
    public void setGroupOwnerAccountName(String name) {
        set(NSFileAttribute.GroupOwnerAccountName, new NSString(name));
    }
    public short getPosixPermissions() {
        if (contains(NSFileAttribute.PosixPermissions)) {
            NSNumber val = (NSNumber)get(NSFileAttribute.PosixPermissions);
            return val.shortValue();
        }
        return 0;
    }
    public void setPosixPermissions(short permissions) {
        set(NSFileAttribute.PosixPermissions, NSNumber.valueOf(permissions));
    }
    public long getSystemFileNumber() {
        if (contains(NSFileAttribute.SystemFileNumber)) {
            NSNumber val = (NSNumber)get(NSFileAttribute.SystemFileNumber);
            return val.longValue();
        }
        return 0;
    }
    public void setSystemFileNumber(long number) {
        set(NSFileAttribute.SystemFileNumber, NSNumber.valueOf(number));
    }
    public boolean isExtensionHidden() {
        if (contains(NSFileAttribute.ExtensionHidden)) {
            NSNumber val = (NSNumber)get(NSFileAttribute.ExtensionHidden);
            return val.booleanValue();
        }
        return false;
    }
    public void setExtensionHidden(boolean hideExtension) {
        set(NSFileAttribute.ExtensionHidden, NSNumber.valueOf(hideExtension));
    }
    public long getHFSCreatorCode() {
        if (contains(NSFileAttribute.HFSCreatorCode)) {
            NSNumber val = (NSNumber)get(NSFileAttribute.HFSCreatorCode);
            return val.longValue();
        }
        return 0;
    }
    public void setHFSCreatorCode(long code) {
        set(NSFileAttribute.HFSCreatorCode, NSNumber.valueOf(code));
    }
    public long getHFSTypeCode() {
        if (contains(NSFileAttribute.HFSTypeCode)) {
            NSNumber val = (NSNumber)get(NSFileAttribute.HFSTypeCode);
            return val.longValue();
        }
        return 0;
    }
    public void setHFSTypeCode(long code) {
        set(NSFileAttribute.HFSTypeCode, NSNumber.valueOf(code));
    }
    public boolean isImmutable() {
        if (contains(NSFileAttribute.Immutable)) {
            NSNumber val = (NSNumber)get(NSFileAttribute.Immutable);
            return val.booleanValue();
        }
        return false;
    }
    public void setImmutable(boolean immutable) {
        set(NSFileAttribute.Immutable, NSNumber.valueOf(immutable));
    }
    public boolean isAppendOnly() {
        if (contains(NSFileAttribute.AppendOnly)) {
            NSNumber val = (NSNumber)get(NSFileAttribute.AppendOnly);
            return val.booleanValue();
        }
        return false;
    }
    public void setAppendOnly(boolean appendOnly) {
        set(NSFileAttribute.AppendOnly, NSNumber.valueOf(appendOnly));
    }
    public NSDate getCreationDate() {
        if (contains(NSFileAttribute.CreationDate)) {
            NSDate val = (NSDate)get(NSFileAttribute.CreationDate);
            return val;
        }
        return null;
    }
    public void setCreationDate(NSDate date) {
        set(NSFileAttribute.CreationDate, date);
    }
    public long getOwnerAccountID() {
        if (contains(NSFileAttribute.OwnerAccountID)) {
            NSNumber val = (NSNumber)get(NSFileAttribute.OwnerAccountID);
            return val.longValue();
        }
        return 0;
    }
    public void setOwnerAccountID(long id) {
        set(NSFileAttribute.OwnerAccountID, NSNumber.valueOf(id));
    }
    public long getGroupOwnerAccountID() {
        if (contains(NSFileAttribute.GroupOwnerAccountID)) {
            NSNumber val = (NSNumber)get(NSFileAttribute.GroupOwnerAccountID);
            return val.longValue();
        }
        return 0;
    }
    public void setGroupOwnerAccountID(long id) {
        set(NSFileAttribute.GroupOwnerAccountID, NSNumber.valueOf(id));
    }
    public boolean isBusy() {
        if (contains(NSFileAttribute.Busy)) {
            NSNumber val = (NSNumber)get(NSFileAttribute.Busy);
            return val.booleanValue();
        }
        return false;
    }
    public void setBusy(boolean busy) {
        set(NSFileAttribute.Busy, NSNumber.valueOf(busy));
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSFileProtection getProtection() {
        if (contains(NSFileAttribute.ProtectionKey)) {
            NSString val = (NSString)get(NSFileAttribute.ProtectionKey);
            return NSFileProtection.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public void setProtection(NSFileProtection protection) {
        set(NSFileAttribute.ProtectionKey, protection.value());
    }
    /*<methods>*/
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
