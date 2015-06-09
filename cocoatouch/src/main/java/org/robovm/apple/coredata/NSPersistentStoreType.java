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
@Marshaler(NSPersistentStoreType.Marshaler.class)
/*<annotations>*/@Library("CoreData")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSPersistentStoreType/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static NSPersistentStoreType toObject(Class<NSPersistentStoreType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return NSPersistentStoreType.valueOf(o.toString());
        }
        @MarshalsPointer
        public static long toNative(NSPersistentStoreType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(new NSString(o.value()), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSPersistentStoreType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSPersistentStoreType SQLite = new NSPersistentStoreType("SQLiteValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSPersistentStoreType Binary = new NSPersistentStoreType("BinaryValue");
    /**
     * @since Available in iOS 3.0 and later.
     */
    public static final NSPersistentStoreType InMemory = new NSPersistentStoreType("InMemoryValue");
    
    private static NSPersistentStoreType[] values = new NSPersistentStoreType[] {SQLite, Binary, InMemory};
    private final LazyGlobalValue<String> lazyGlobalValue;
    
    private NSPersistentStoreType(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public String value() {
        return lazyGlobalValue.value();
    }
    
    public static NSPersistentStoreType valueOf(String value) {
        for (NSPersistentStoreType v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/NSPersistentStoreType/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSSQLiteStoreType", optional=true)
    protected static native String SQLiteValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSBinaryStoreType", optional=true)
    protected static native String BinaryValue();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="NSInMemoryStoreType", optional=true)
    protected static native String InMemoryValue();
    /*</methods>*/
}
