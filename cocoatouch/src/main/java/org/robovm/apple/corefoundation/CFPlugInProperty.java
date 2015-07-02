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
@Marshaler(/*<name>*/CFPlugInProperty/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFPlugInProperty/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CFPlugInProperty/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CFPlugInProperty toObject(Class<CFPlugInProperty> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CFPlugInProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CFPlugInProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CFPlugInProperty> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CFPlugInProperty> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CFPlugInProperty.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CFPlugInProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CFPlugInProperty o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final CFPlugInProperty DynamicRegistration = new CFPlugInProperty("DynamicRegistration");
    public static final CFPlugInProperty DynamicRegisterFunction = new CFPlugInProperty("DynamicRegisterFunction");
    public static final CFPlugInProperty UnloadFunction = new CFPlugInProperty("UnloadFunction");
    public static final CFPlugInProperty Factories = new CFPlugInProperty("Factories");
    public static final CFPlugInProperty Types = new CFPlugInProperty("Types");
    /*</constants>*/
    
    private static /*<name>*/CFPlugInProperty/*</name>*/[] values = new /*<name>*/CFPlugInProperty/*</name>*/[] {/*<value_list>*/DynamicRegistration, DynamicRegisterFunction, UnloadFunction, Factories, Types/*</value_list>*/};
    
    /*<name>*/CFPlugInProperty/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CFPlugInProperty/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CFPlugInProperty/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CFPlugInProperty/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="kCFPlugInDynamicRegistrationKey", optional=true)
        public static native CFString DynamicRegistration();
        @GlobalValue(symbol="kCFPlugInDynamicRegisterFunctionKey", optional=true)
        public static native CFString DynamicRegisterFunction();
        @GlobalValue(symbol="kCFPlugInUnloadFunctionKey", optional=true)
        public static native CFString UnloadFunction();
        @GlobalValue(symbol="kCFPlugInFactoriesKey", optional=true)
        public static native CFString Factories();
        @GlobalValue(symbol="kCFPlugInTypesKey", optional=true)
        public static native CFString Types();
        /*</values>*/
    }
}
