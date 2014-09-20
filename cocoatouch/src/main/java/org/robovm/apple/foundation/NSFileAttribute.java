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
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFileAttribute/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSFileAttribute.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final NSFileAttribute Type = new NSFileAttribute("TypeAttribute");
    public static final NSFileAttribute Size = new NSFileAttribute("SizeAttribute");
    public static final NSFileAttribute ModificationDate = new NSFileAttribute("ModificationDateAttribute");
    public static final NSFileAttribute ReferenceCount = new NSFileAttribute("ReferenceCountAttribute");
    public static final NSFileAttribute DeviceIdentifier = new NSFileAttribute("DeviceIdentifierAttribute");
    public static final NSFileAttribute OwnerAccountName = new NSFileAttribute("OwnerAccountNameAttribute");
    public static final NSFileAttribute GroupOwnerAccountName = new NSFileAttribute("GroupOwnerAccountNameAttribute");
    public static final NSFileAttribute PosixPermissions = new NSFileAttribute("PosixPermissionsAttribute");
    public static final NSFileAttribute SystemNumber = new NSFileAttribute("SystemNumberAttribute");
    public static final NSFileAttribute SystemFileNumber = new NSFileAttribute("SystemFileNumberAttribute");
    public static final NSFileAttribute ExtensionHidden = new NSFileAttribute("ExtensionHiddenAttribute");
    public static final NSFileAttribute HFSCreatorCode = new NSFileAttribute("HFSCreatorCodeAttribute");
    public static final NSFileAttribute HFSTypeCode = new NSFileAttribute("HFSTypeCodeAttribute");
    public static final NSFileAttribute Immutable = new NSFileAttribute("ImmutableAttribute");
    public static final NSFileAttribute AppendOnly = new NSFileAttribute("AppendOnlyAttribute");
    public static final NSFileAttribute CreationDate = new NSFileAttribute("CreationDateAttribute");
    public static final NSFileAttribute OwnerAccountID = new NSFileAttribute("OwnerAccountIDAttribute");
    public static final NSFileAttribute GroupOwnerAccountID = new NSFileAttribute("GroupOwnerAccountIDAttribute");
    public static final NSFileAttribute Busy = new NSFileAttribute("BusyAttribute");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSFileAttribute ProtectionKey = new NSFileAttribute("ProtectionKeyAttribute");
    private static NSFileAttribute[] values = new NSFileAttribute[] {Type, Size, ModificationDate, ReferenceCount, DeviceIdentifier, OwnerAccountName, GroupOwnerAccountName, 
        PosixPermissions, SystemNumber, SystemFileNumber, ExtensionHidden, HFSCreatorCode, HFSTypeCode, Immutable, AppendOnly, CreationDate, OwnerAccountID, GroupOwnerAccountID, 
        Busy, ProtectionKey};
    
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private NSFileAttribute(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static NSFileAttribute valueOf(NSString value) {
        for (NSFileAttribute v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSFileAttribute/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="NSFileType", optional=true)
    protected static native NSString TypeAttribute();
    @GlobalValue(symbol="NSFileSize", optional=true)
    protected static native NSString SizeAttribute();
    @GlobalValue(symbol="NSFileModificationDate", optional=true)
    protected static native NSString ModificationDateAttribute();
    @GlobalValue(symbol="NSFileReferenceCount", optional=true)
    protected static native NSString ReferenceCountAttribute();
    @GlobalValue(symbol="NSFileDeviceIdentifier", optional=true)
    protected static native NSString DeviceIdentifierAttribute();
    @GlobalValue(symbol="NSFileOwnerAccountName", optional=true)
    protected static native NSString OwnerAccountNameAttribute();
    @GlobalValue(symbol="NSFileGroupOwnerAccountName", optional=true)
    protected static native NSString GroupOwnerAccountNameAttribute();
    @GlobalValue(symbol="NSFilePosixPermissions", optional=true)
    protected static native NSString PosixPermissionsAttribute();
    @GlobalValue(symbol="NSFileSystemNumber", optional=true)
    protected static native NSString SystemNumberAttribute();
    @GlobalValue(symbol="NSFileSystemFileNumber", optional=true)
    protected static native NSString SystemFileNumberAttribute();
    @GlobalValue(symbol="NSFileExtensionHidden", optional=true)
    protected static native NSString ExtensionHiddenAttribute();
    @GlobalValue(symbol="NSFileHFSCreatorCode", optional=true)
    protected static native NSString HFSCreatorCodeAttribute();
    @GlobalValue(symbol="NSFileHFSTypeCode", optional=true)
    protected static native NSString HFSTypeCodeAttribute();
    @GlobalValue(symbol="NSFileImmutable", optional=true)
    protected static native NSString ImmutableAttribute();
    @GlobalValue(symbol="NSFileAppendOnly", optional=true)
    protected static native NSString AppendOnlyAttribute();
    @GlobalValue(symbol="NSFileCreationDate", optional=true)
    protected static native NSString CreationDateAttribute();
    @GlobalValue(symbol="NSFileOwnerAccountID", optional=true)
    protected static native NSString OwnerAccountIDAttribute();
    @GlobalValue(symbol="NSFileGroupOwnerAccountID", optional=true)
    protected static native NSString GroupOwnerAccountIDAttribute();
    @GlobalValue(symbol="NSFileBusy", optional=true)
    protected static native NSString BusyAttribute();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="NSFileProtectionKey", optional=true)
    protected static native NSString ProtectionKeyAttribute();
    /*</methods>*/
}
