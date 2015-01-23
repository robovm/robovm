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
package org.robovm.apple.homekit;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(HMCharacteristicProperty.Marshaler.class)
/*<annotations>*/@Library("HomeKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HMCharacteristicProperty/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static HMCharacteristicProperty toObject(Class<HMCharacteristicProperty> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return HMCharacteristicProperty.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(HMCharacteristicProperty o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<HMCharacteristicProperty> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<HMCharacteristicProperty> list = new ArrayList<>();
            for (NSString str : o) {
                list.add(HMCharacteristicProperty.valueOf(str));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<HMCharacteristicProperty> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSString> array = new NSMutableArray<>();
            for (HMCharacteristicProperty i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(HMCharacteristicProperty.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicProperty SupportsEventNotification = new HMCharacteristicProperty("SupportsEventNotificationValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicProperty Readable = new HMCharacteristicProperty("ReadableValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMCharacteristicProperty Writable = new HMCharacteristicProperty("WritableValue");
    
    private static HMCharacteristicProperty[] values = new HMCharacteristicProperty[] {SupportsEventNotification, Readable, Writable};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private HMCharacteristicProperty(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static HMCharacteristicProperty valueOf(NSString value) {
        for (HMCharacteristicProperty v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/HMCharacteristicProperty/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicPropertySupportsEventNotification", optional=true)
    protected static native NSString SupportsEventNotificationValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicPropertyReadable", optional=true)
    protected static native NSString ReadableValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMCharacteristicPropertyWritable", optional=true)
    protected static native NSString WritableValue();
    /*</methods>*/
}
