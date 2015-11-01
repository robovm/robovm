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
package org.robovm.apple.contacts;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Contacts") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CNContainerPropertyKey/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CNContainerPropertyKey/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CNContainerPropertyKey/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CNContainerPropertyKey toObject(Class<CNContainerPropertyKey> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CNContainerPropertyKey.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CNContainerPropertyKey o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CNContainerPropertyKey> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CNContainerPropertyKey> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CNContainerPropertyKey.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CNContainerPropertyKey> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CNContainerPropertyKey o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContainerPropertyKey Identifier = new CNContainerPropertyKey("Identifier");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContainerPropertyKey Name = new CNContainerPropertyKey("Name");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final CNContainerPropertyKey Type = new CNContainerPropertyKey("Type");
    /*</constants>*/
    
    private static /*<name>*/CNContainerPropertyKey/*</name>*/[] values = new /*<name>*/CNContainerPropertyKey/*</name>*/[] {/*<value_list>*/Identifier, Name, Type/*</value_list>*/};
    
    /*<name>*/CNContainerPropertyKey/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CNContainerPropertyKey/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CNContainerPropertyKey/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CNContainerPropertyKey/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("Contacts") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContainerIdentifierKey", optional=true)
        public static native NSString Identifier();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContainerNameKey", optional=true)
        public static native NSString Name();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="CNContainerTypeKey", optional=true)
        public static native NSString Type();
        /*</values>*/
    }
}
