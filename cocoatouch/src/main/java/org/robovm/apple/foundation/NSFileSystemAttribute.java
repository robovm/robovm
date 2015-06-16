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
@Marshaler(/*<name>*/NSFileSystemAttribute/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSFileSystemAttribute/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSFileSystemAttribute/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSFileSystemAttribute toObject(Class<NSFileSystemAttribute> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSFileSystemAttribute.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSFileSystemAttribute o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSFileSystemAttribute> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSFileSystemAttribute> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSFileSystemAttribute.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSFileSystemAttribute> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSFileSystemAttribute o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final NSFileSystemAttribute Number = new NSFileSystemAttribute("Number");
    public static final NSFileSystemAttribute Size = new NSFileSystemAttribute("Size");
    public static final NSFileSystemAttribute FreeSize = new NSFileSystemAttribute("FreeSize");
    public static final NSFileSystemAttribute Nodes = new NSFileSystemAttribute("Nodes");
    public static final NSFileSystemAttribute FreeNodes = new NSFileSystemAttribute("FreeNodes");
    /*</constants>*/
    
    private static /*<name>*/NSFileSystemAttribute/*</name>*/[] values = new /*<name>*/NSFileSystemAttribute/*</name>*/[] {/*<value_list>*/Number, Size, FreeSize, Nodes, FreeNodes/*</value_list>*/};
    
    /*<name>*/NSFileSystemAttribute/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSFileSystemAttribute/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSFileSystemAttribute/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSFileSystemAttribute/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Foundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="NSFileSystemNumber", optional=true)
        public static native NSString Number();
        @GlobalValue(symbol="NSFileSystemSize", optional=true)
        public static native NSString Size();
        @GlobalValue(symbol="NSFileSystemFreeSize", optional=true)
        public static native NSString FreeSize();
        @GlobalValue(symbol="NSFileSystemNodes", optional=true)
        public static native NSString Nodes();
        @GlobalValue(symbol="NSFileSystemFreeNodes", optional=true)
        public static native NSString FreeNodes();
        /*</values>*/
    }
}
