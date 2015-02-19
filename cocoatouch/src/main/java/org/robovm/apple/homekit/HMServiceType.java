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
@Marshaler(HMServiceType.Marshaler.class)
/*<annotations>*/@Library("HomeKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HMServiceType/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
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
            for (NSString str : o) {
                list.add(HMServiceType.valueOf(str));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<HMServiceType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSString> array = new NSMutableArray<>();
            for (HMServiceType i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(HMServiceType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMServiceType Lightbulb = new HMServiceType("LightbulbValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMServiceType Switch = new HMServiceType("SwitchValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMServiceType Thermostat = new HMServiceType("ThermostatValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMServiceType GarageDoorOpener = new HMServiceType("GarageDoorOpenerValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMServiceType AccessoryInformation = new HMServiceType("AccessoryInformationValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMServiceType Fan = new HMServiceType("FanValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMServiceType Outlet = new HMServiceType("OutletValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMServiceType LockMechanism = new HMServiceType("LockMechanismValue");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final HMServiceType LockManagement = new HMServiceType("LockManagementValue");
    
    private static HMServiceType[] values = new HMServiceType[] {Lightbulb, Switch, Thermostat, GarageDoorOpener, 
        AccessoryInformation, Fan, Outlet, LockMechanism, LockManagement};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private HMServiceType(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static HMServiceType valueOf(NSString value) {
        for (HMServiceType v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/HMServiceType/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMServiceTypeLightbulb", optional=true)
    protected static native NSString LightbulbValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMServiceTypeSwitch", optional=true)
    protected static native NSString SwitchValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMServiceTypeThermostat", optional=true)
    protected static native NSString ThermostatValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMServiceTypeGarageDoorOpener", optional=true)
    protected static native NSString GarageDoorOpenerValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMServiceTypeAccessoryInformation", optional=true)
    protected static native NSString AccessoryInformationValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMServiceTypeFan", optional=true)
    protected static native NSString FanValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMServiceTypeOutlet", optional=true)
    protected static native NSString OutletValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMServiceTypeLockMechanism", optional=true)
    protected static native NSString LockMechanismValue();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="HMServiceTypeLockManagement", optional=true)
    protected static native NSString LockManagementValue();
    /*</methods>*/
}
