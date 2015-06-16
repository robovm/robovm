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
package org.robovm.apple.corefoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreservices.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coretext.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CFSocketNameServerKey/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFSocketNameServerKey/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CFSocketNameServerKey/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CFSocketNameServerKey toObject(Class<CFSocketNameServerKey> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFSocketNameServerKey.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFSocketNameServerKey o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFSocketNameServerKey> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFSocketNameServerKey> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CFSocketNameServerKey.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFSocketNameServerKey> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFSocketNameServerKey o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final CFSocketNameServerKey CommandKey = new CFSocketNameServerKey("CommandKey");
    public static final CFSocketNameServerKey NameKey = new CFSocketNameServerKey("NameKey");
    public static final CFSocketNameServerKey ValueKey = new CFSocketNameServerKey("ValueKey");
    public static final CFSocketNameServerKey ResultKey = new CFSocketNameServerKey("ResultKey");
    public static final CFSocketNameServerKey ErrorKey = new CFSocketNameServerKey("ErrorKey");
    public static final CFSocketNameServerKey RegisterCommand = new CFSocketNameServerKey("RegisterCommand");
    public static final CFSocketNameServerKey RetrieveCommand = new CFSocketNameServerKey("RetrieveCommand");
    /*</constants>*/
    
    private static /*<name>*/CFSocketNameServerKey/*</name>*/[] values = new /*<name>*/CFSocketNameServerKey/*</name>*/[] {/*<value_list>*/CommandKey, NameKey, ValueKey, ResultKey, ErrorKey, RegisterCommand, RetrieveCommand/*</value_list>*/};
    
    /*<name>*/CFSocketNameServerKey/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CFSocketNameServerKey/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CFSocketNameServerKey/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFSocketNameServerKey/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="kCFSocketCommandKey", optional=true)
        public static native CFString CommandKey();
        @GlobalValue(symbol="kCFSocketNameKey", optional=true)
        public static native CFString NameKey();
        @GlobalValue(symbol="kCFSocketValueKey", optional=true)
        public static native CFString ValueKey();
        @GlobalValue(symbol="kCFSocketResultKey", optional=true)
        public static native CFString ResultKey();
        @GlobalValue(symbol="kCFSocketErrorKey", optional=true)
        public static native CFString ErrorKey();
        @GlobalValue(symbol="kCFSocketRegisterCommand", optional=true)
        public static native CFString RegisterCommand();
        @GlobalValue(symbol="kCFSocketRetrieveCommand", optional=true)
        public static native CFString RetrieveCommand();
        /*</values>*/
    }
}
