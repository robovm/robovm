/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABSource/*</name>*/ 
    extends /*<extends>*/ABRecord/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<ABSource> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<ABSource> list = new ArrayList<>();
            for (long i = 0, n = o.size(); i < n; i++) {
                ABRecord record = o.get(i, ABRecord.class);
                list.add((ABSource)NativeObject.Marshaler.toObject(ABSource.class, record.getHandle(), flags));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<ABSource> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (ABSource i : l) {
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
        CFString val = getValue(ABSourceProperty.Name, CFString.class);
        if (val != null) return val.toString();
        return null;
    }
    public ABSource setName(String name) throws NSErrorException {
        if (name == null) {
            setValue(ABSourceProperty.Name, null);
        } else {
            setValue(ABSourceProperty.Name, new CFString(name));
        }
        return this;
    }
    public ABSourceType getType() {
        CFNumber val = getValue(ABSourceProperty.Type, CFNumber.class);
        if (val != null) return new ABSourceType(val.intValue());
        return null;
    }
    public ABSource setType(ABSourceType type) throws NSErrorException {
        if (type == null) {
            setValue(ABSourceProperty.Type, null);
        } else {
            setValue(ABSourceProperty.Type, CFNumber.valueOf((int)type.value()));
        }
        return this;
    }
    /*<methods>*/
    /*</methods>*/
}
