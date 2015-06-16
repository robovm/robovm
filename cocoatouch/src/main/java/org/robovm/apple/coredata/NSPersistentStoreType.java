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
package org.robovm.apple.coredata;

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
/*<annotations>*/@Library("CoreData") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/NSPersistentStoreType/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSPersistentStoreType/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/NSPersistentStoreType/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static NSPersistentStoreType toObject(Class<NSPersistentStoreType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSPersistentStoreType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(NSPersistentStoreType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<NSPersistentStoreType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<NSPersistentStoreType> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(NSPersistentStoreType.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<NSPersistentStoreType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (NSPersistentStoreType o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSPersistentStoreType SQLite = new NSPersistentStoreType("SQLite");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSPersistentStoreType Binary = new NSPersistentStoreType("Binary");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSPersistentStoreType InMemory = new NSPersistentStoreType("InMemory");
    /*</constants>*/
    
    private static /*<name>*/NSPersistentStoreType/*</name>*/[] values = new /*<name>*/NSPersistentStoreType/*</name>*/[] {/*<value_list>*/SQLite, Binary, InMemory/*</value_list>*/};
    
    /*<name>*/NSPersistentStoreType/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/NSPersistentStoreType/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/NSPersistentStoreType/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSPersistentStoreType/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreData") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSSQLiteStoreType", optional=true)
        public static native NSString SQLite();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSBinaryStoreType", optional=true)
        public static native NSString Binary();
        /**
         * @since Available in iOS 3.0 and later.
         */
        @GlobalValue(symbol="NSInMemoryStoreType", optional=true)
        public static native NSString InMemory();
        /*</values>*/
    }
}
