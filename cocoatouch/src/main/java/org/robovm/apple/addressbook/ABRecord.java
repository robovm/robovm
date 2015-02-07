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
package org.robovm.apple.addressbook;

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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(ABRecord.Marshaler.class)
/*<annotations>*/@Library("AddressBook")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABRecord/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ABRecord.class); }/*</bind>*/

    public static class Marshaler {
        @MarshalsPointer
        public static ABRecord toObject(Class<? extends ABRecord> cls, long handle, long flags) {
            return toObject(cls, handle, flags, true);
        }
        static ABRecord toObject(Class<? extends ABRecord> cls, long handle, long flags, boolean retain) {
            if (handle == 0) {
                return null;
            }
            int recordType = getRecordType(handle);
            Class<? extends ABRecord> subcls = null;
            switch (recordType) {
            case 0: // kABPersonType
                subcls = ABPerson.class;
                break;
            case 1: // kABGroupType
                subcls = ABGroup.class;
                break;
            case 2: // kABSourceType
                subcls = ABSource.class;
                break;
            default:
                throw new Error("Unrecognized record type " + recordType);
            }
            ABRecord o = (ABRecord) NativeObject.Marshaler.toObject(subcls, handle, flags);
            if (retain) {
                retain(handle);
            }
            return o;
        }
    }
    public static class NoRetainMarshaler {
        @MarshalsPointer
        public static ABRecord toObject(Class<? extends ABRecord> cls, long handle, long flags) {
            return Marshaler.toObject(cls, handle, flags, false);
        }
    }

    /*<constants>*/
    public static final int InvalidID = -1;
    /*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/

    @Bridge(symbol="ABRecordGetRecordType", optional=true)
    static native int getRecordType(@Pointer long handle);

    public <T extends NativeObject> T getValue(ABProperty property, Class<T> type) {
        CFType val = getValue(property);
        if (val != null) return val.as(type);
        return null;
    }
    /*<methods>*/
    @Bridge(symbol="ABRecordGetRecordID", optional=true)
    public native int getRecordID();
    @Bridge(symbol="ABRecordGetRecordType", optional=true)
    public native ABRecordType getRecordType();
    @Bridge(symbol="ABRecordCopyValue", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFType getValue(ABProperty property);
    public boolean setValue(ABProperty property, CFType value) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = setValue(property, value, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Bridge(symbol="ABRecordSetValue", optional=true)
    private native boolean setValue(ABProperty property, CFType value, NSError.NSErrorPtr error);
    public boolean removeValue(ABProperty property) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = removeValue(property, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Bridge(symbol="ABRecordRemoveValue", optional=true)
    private native boolean removeValue(ABProperty property, NSError.NSErrorPtr error);
    @Bridge(symbol="ABRecordCopyCompositeName", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFString.AsStringNoRetainMarshaler.class) String getCompositeName();
    /*</methods>*/
}
