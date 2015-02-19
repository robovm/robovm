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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
@Marshaler(/*<name>*/CFPreferencesDomain/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFPreferencesDomain/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CFPreferencesDomain/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CFPreferencesDomain toObject(Class<CFPreferencesDomain> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFPreferencesDomain.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFPreferencesDomain o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFPreferencesDomain> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFPreferencesDomain> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CFPreferencesDomain.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFPreferencesDomain> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFPreferencesDomain i : l) {
                array.add(i.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final CFPreferencesDomain AnyApplication = new CFPreferencesDomain("AnyApplication");
    public static final CFPreferencesDomain CurrentApplication = new CFPreferencesDomain("CurrentApplication");
    public static final CFPreferencesDomain AnyHost = new CFPreferencesDomain("AnyHost");
    public static final CFPreferencesDomain CurrentHost = new CFPreferencesDomain("CurrentHost");
    public static final CFPreferencesDomain AnyUser = new CFPreferencesDomain("AnyUser");
    public static final CFPreferencesDomain CurrentUser = new CFPreferencesDomain("CurrentUser");
    /*</constants>*/
    
    private static /*<name>*/CFPreferencesDomain/*</name>*/[] values = new /*<name>*/CFPreferencesDomain/*</name>*/[] {/*<value_list>*/AnyApplication, CurrentApplication, AnyHost, CurrentHost, AnyUser, CurrentUser/*</value_list>*/};
    
    /*<name>*/CFPreferencesDomain/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CFPreferencesDomain/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CFPreferencesDomain/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFPreferencesDomain/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="kCFPreferencesAnyApplication", optional=true)
        public static native CFString AnyApplication();
        @GlobalValue(symbol="kCFPreferencesCurrentApplication", optional=true)
        public static native CFString CurrentApplication();
        @GlobalValue(symbol="kCFPreferencesAnyHost", optional=true)
        public static native CFString AnyHost();
        @GlobalValue(symbol="kCFPreferencesCurrentHost", optional=true)
        public static native CFString CurrentHost();
        @GlobalValue(symbol="kCFPreferencesAnyUser", optional=true)
        public static native CFString AnyUser();
        @GlobalValue(symbol="kCFPreferencesCurrentUser", optional=true)
        public static native CFString CurrentUser();
        /*</values>*/
    }
}
