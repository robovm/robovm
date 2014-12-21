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
/*<annotations>*/@Library("AddressBook")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABGroup/*</name>*/ 
    extends /*<extends>*/ABRecord/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<ABGroup> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<ABGroup> list = new ArrayList<>();
            for (long i = 0, n = o.size(); i < n; i++) {
                ABRecord record = o.get(i, ABRecord.class);
                list.add((ABGroup)NativeObject.Marshaler.toObject(ABGroup.class, record.getHandle(), flags));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<ABGroup> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (ABGroup i : l) {
                array.add(i);
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ABGroup.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public String getName() {
        CFString val = (CFString)getValue(ABGroupProperty.Name);
        if (val != null) return val.toString();
        return null;
    }
    public ABGroup setName(String name) throws NSErrorException {
        setValue(ABGroupProperty.Name, new CFString(name));
        return this;
    }
    
    /**
     * 
     * @param person
     * @return
     * @throws NSErrorException
     */
    public boolean addMember(ABPerson member) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = addMember(member, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /**
     * 
     * @param member
     * @return
     * @throws NSErrorException
     */
    public boolean removeMember(ABPerson member) throws NSErrorException {
        NSError.NSErrorPtr err = new NSError.NSErrorPtr();
        boolean result = removeMember(member, err);
        if (err.get() != null) {
            throw new NSErrorException(err.get());
        }
        return result;
    }
    /*<methods>*/
    @Bridge(symbol="ABGroupCreate", optional=true)
    public static native ABGroup create();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="ABGroupCreateInSource", optional=true)
    public static native ABGroup create(ABSource source);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="ABGroupCopySource", optional=true)
    public native ABSource source();
    @Bridge(symbol="ABGroupCopyArrayOfAllMembers", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(ABPerson.AsListMarshaler.class) List<ABPerson> getAllMembers();
    @Bridge(symbol="ABGroupCopyArrayOfAllMembersWithSortOrdering", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(ABPerson.AsListMarshaler.class) List<ABPerson> getAllMembers(ABPersonSortOrdering sortOrdering);
    @Bridge(symbol="ABGroupAddMember", optional=true)
    protected native boolean addMember(ABPerson person, NSError.NSErrorPtr error);
    @Bridge(symbol="ABGroupRemoveMember", optional=true)
    protected native boolean removeMember(ABPerson member, NSError.NSErrorPtr error);
    /*</methods>*/
}
