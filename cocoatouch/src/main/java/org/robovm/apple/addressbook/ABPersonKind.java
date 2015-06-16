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
package org.robovm.apple.addressbook;

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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AddressBook") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/ABPersonKind/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABPersonKind/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFNumber>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/ABPersonKind/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static ABPersonKind toObject(Class<ABPersonKind> cls, long handle, long flags) {
            CFNumber o = (CFNumber) CFType.Marshaler.toObject(CFNumber.class, handle, flags);
            if (o == null) {
                return null;
            }
            return ABPersonKind.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(ABPersonKind o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<ABPersonKind> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<ABPersonKind> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(ABPersonKind.valueOf(o.get(i, CFNumber.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<ABPersonKind> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (ABPersonKind o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final ABPersonKind Person = new ABPersonKind("Person");
    public static final ABPersonKind Organization = new ABPersonKind("Organization");
    /*</constants>*/
    
    private static /*<name>*/ABPersonKind/*</name>*/[] values = new /*<name>*/ABPersonKind/*</name>*/[] {/*<value_list>*/Person, Organization/*</value_list>*/};
    
    /*<name>*/ABPersonKind/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/ABPersonKind/*</name>*/ valueOf(/*<type>*/CFNumber/*</type>*/ value) {
        for (/*<name>*/ABPersonKind/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ABPersonKind/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AddressBook") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="kABPersonKindPerson", optional=true)
        public static native CFNumber Person();
        @GlobalValue(symbol="kABPersonKindOrganization", optional=true)
        public static native CFNumber Organization();
        /*</values>*/
    }
}
