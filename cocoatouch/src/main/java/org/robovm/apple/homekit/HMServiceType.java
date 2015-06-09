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
package org.robovm.apple.homekit;

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
/*<annotations>*/@Library("HomeKit") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/HMServiceType/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HMServiceType/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/HMServiceType/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static HMServiceType toObject(Class<HMServiceType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return HMServiceType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(HMServiceType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<HMServiceType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<HMServiceType> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(HMServiceType.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<HMServiceType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (HMServiceType i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMServiceType Lightbulb = new HMServiceType("Lightbulb");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMServiceType Switch = new HMServiceType("Switch");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMServiceType Thermostat = new HMServiceType("Thermostat");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMServiceType GarageDoorOpener = new HMServiceType("GarageDoorOpener");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMServiceType AccessoryInformation = new HMServiceType("AccessoryInformation");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMServiceType Fan = new HMServiceType("Fan");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMServiceType Outlet = new HMServiceType("Outlet");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMServiceType LockMechanism = new HMServiceType("LockMechanism");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMServiceType LockManagement = new HMServiceType("LockManagement");
    /*</constants>*/
    
    private static /*<name>*/HMServiceType/*</name>*/[] values = new /*<name>*/HMServiceType/*</name>*/[] {/*<value_list>*/Lightbulb, Switch, Thermostat, GarageDoorOpener, AccessoryInformation, Fan, Outlet, LockMechanism, LockManagement/*</value_list>*/};
    
    /*<name>*/HMServiceType/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/HMServiceType/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/HMServiceType/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/HMServiceType/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("HomeKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMServiceTypeLightbulb", optional=true)
        public static native NSString Lightbulb();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMServiceTypeSwitch", optional=true)
        public static native NSString Switch();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMServiceTypeThermostat", optional=true)
        public static native NSString Thermostat();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMServiceTypeGarageDoorOpener", optional=true)
        public static native NSString GarageDoorOpener();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMServiceTypeAccessoryInformation", optional=true)
        public static native NSString AccessoryInformation();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMServiceTypeFan", optional=true)
        public static native NSString Fan();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMServiceTypeOutlet", optional=true)
        public static native NSString Outlet();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMServiceTypeLockMechanism", optional=true)
        public static native NSString LockMechanism();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="HMServiceTypeLockManagement", optional=true)
        public static native NSString LockManagement();
        /*</values>*/
    }
}
