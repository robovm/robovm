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
/*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/NSFileAttribute/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFileAttribute/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSFileAttribute/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSFileAttribute toObject(Class<NSFileAttribute> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSFileAttribute.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSFileAttribute o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSFileAttribute> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSFileAttribute> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSFileAttribute.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSFileAttribute> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSFileAttribute o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final NSFileAttribute Type = new NSFileAttribute("Type");
    public static final NSFileAttribute Size = new NSFileAttribute("Size");
    public static final NSFileAttribute ModificationDate = new NSFileAttribute("ModificationDate");
    public static final NSFileAttribute ReferenceCount = new NSFileAttribute("ReferenceCount");
    public static final NSFileAttribute DeviceIdentifier = new NSFileAttribute("DeviceIdentifier");
    public static final NSFileAttribute OwnerAccountName = new NSFileAttribute("OwnerAccountName");
    public static final NSFileAttribute GroupOwnerAccountName = new NSFileAttribute("GroupOwnerAccountName");
    public static final NSFileAttribute PosixPermissions = new NSFileAttribute("PosixPermissions");
    public static final NSFileAttribute SystemFileNumber = new NSFileAttribute("SystemFileNumber");
    public static final NSFileAttribute ExtensionHidden = new NSFileAttribute("ExtensionHidden");
    public static final NSFileAttribute HFSCreatorCode = new NSFileAttribute("HFSCreatorCode");
    public static final NSFileAttribute HFSTypeCode = new NSFileAttribute("HFSTypeCode");
    public static final NSFileAttribute Immutable = new NSFileAttribute("Immutable");
    public static final NSFileAttribute AppendOnly = new NSFileAttribute("AppendOnly");
    public static final NSFileAttribute CreationDate = new NSFileAttribute("CreationDate");
    public static final NSFileAttribute OwnerAccountID = new NSFileAttribute("OwnerAccountID");
    public static final NSFileAttribute GroupOwnerAccountID = new NSFileAttribute("GroupOwnerAccountID");
    public static final NSFileAttribute Busy = new NSFileAttribute("Busy");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final NSFileAttribute ProtectionKey = new NSFileAttribute("ProtectionKey");
    /*</constants>*/
    
    private static /*<name>*/NSFileAttribute/*</name>*/[] values = new /*<name>*/NSFileAttribute/*</name>*/[] {/*<value_list>*/Type, Size, ModificationDate, ReferenceCount, DeviceIdentifier, OwnerAccountName, GroupOwnerAccountName, PosixPermissions, SystemFileNumber, ExtensionHidden, HFSCreatorCode, HFSTypeCode, Immutable, AppendOnly, CreationDate, OwnerAccountID, GroupOwnerAccountID, Busy, ProtectionKey/*</value_list>*/};
    
    /*<name>*/NSFileAttribute/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSFileAttribute/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSFileAttribute/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSFileAttribute/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="NSFileType", optional=true)
        public static native NSString Type();
        @GlobalValue(symbol="NSFileSize", optional=true)
        public static native NSString Size();
        @GlobalValue(symbol="NSFileModificationDate", optional=true)
        public static native NSString ModificationDate();
        @GlobalValue(symbol="NSFileReferenceCount", optional=true)
        public static native NSString ReferenceCount();
        @GlobalValue(symbol="NSFileDeviceIdentifier", optional=true)
        public static native NSString DeviceIdentifier();
        @GlobalValue(symbol="NSFileOwnerAccountName", optional=true)
        public static native NSString OwnerAccountName();
        @GlobalValue(symbol="NSFileGroupOwnerAccountName", optional=true)
        public static native NSString GroupOwnerAccountName();
        @GlobalValue(symbol="NSFilePosixPermissions", optional=true)
        public static native NSString PosixPermissions();
        @GlobalValue(symbol="NSFileSystemFileNumber", optional=true)
        public static native NSString SystemFileNumber();
        @GlobalValue(symbol="NSFileExtensionHidden", optional=true)
        public static native NSString ExtensionHidden();
        @GlobalValue(symbol="NSFileHFSCreatorCode", optional=true)
        public static native NSString HFSCreatorCode();
        @GlobalValue(symbol="NSFileHFSTypeCode", optional=true)
        public static native NSString HFSTypeCode();
        @GlobalValue(symbol="NSFileImmutable", optional=true)
        public static native NSString Immutable();
        @GlobalValue(symbol="NSFileAppendOnly", optional=true)
        public static native NSString AppendOnly();
        @GlobalValue(symbol="NSFileCreationDate", optional=true)
        public static native NSString CreationDate();
        @GlobalValue(symbol="NSFileOwnerAccountID", optional=true)
        public static native NSString OwnerAccountID();
        @GlobalValue(symbol="NSFileGroupOwnerAccountID", optional=true)
        public static native NSString GroupOwnerAccountID();
        @GlobalValue(symbol="NSFileBusy", optional=true)
        public static native NSString Busy();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="NSFileProtectionKey", optional=true)
        public static native NSString ProtectionKey();
        /*</values>*/
    }
}
