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
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("HomeKit") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/HMAccessoryCategoryType/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HMAccessoryCategoryType/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/HMAccessoryCategoryType/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static HMAccessoryCategoryType toObject(Class<HMAccessoryCategoryType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return HMAccessoryCategoryType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(HMAccessoryCategoryType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<HMAccessoryCategoryType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<HMAccessoryCategoryType> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(HMAccessoryCategoryType.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<HMAccessoryCategoryType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (HMAccessoryCategoryType o : l) {
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
    public static final HMAccessoryCategoryType Other = new HMAccessoryCategoryType("Other");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final HMAccessoryCategoryType SecuritySystem = new HMAccessoryCategoryType("SecuritySystem");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final HMAccessoryCategoryType Bridge = new HMAccessoryCategoryType("Bridge");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final HMAccessoryCategoryType Door = new HMAccessoryCategoryType("Door");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final HMAccessoryCategoryType DoorLock = new HMAccessoryCategoryType("DoorLock");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final HMAccessoryCategoryType Fan = new HMAccessoryCategoryType("Fan");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final HMAccessoryCategoryType GarageDoorOpener = new HMAccessoryCategoryType("GarageDoorOpener");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final HMAccessoryCategoryType Lightbulb = new HMAccessoryCategoryType("Lightbulb");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final HMAccessoryCategoryType Outlet = new HMAccessoryCategoryType("Outlet");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final HMAccessoryCategoryType ProgrammableSwitch = new HMAccessoryCategoryType("ProgrammableSwitch");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final HMAccessoryCategoryType Sensor = new HMAccessoryCategoryType("Sensor");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final HMAccessoryCategoryType Switch = new HMAccessoryCategoryType("Switch");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final HMAccessoryCategoryType Thermostat = new HMAccessoryCategoryType("Thermostat");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final HMAccessoryCategoryType Window = new HMAccessoryCategoryType("Window");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final HMAccessoryCategoryType WindowCovering = new HMAccessoryCategoryType("WindowCovering");
    /*</constants>*/
    
    private static /*<name>*/HMAccessoryCategoryType/*</name>*/[] values = new /*<name>*/HMAccessoryCategoryType/*</name>*/[] {/*<value_list>*/Other, SecuritySystem, Bridge, Door, DoorLock, Fan, GarageDoorOpener, Lightbulb, Outlet, ProgrammableSwitch, Sensor, Switch, Thermostat, Window, WindowCovering/*</value_list>*/};
    
    /*<name>*/HMAccessoryCategoryType/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/HMAccessoryCategoryType/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/HMAccessoryCategoryType/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/HMAccessoryCategoryType/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("HomeKit") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMAccessoryCategoryTypeOther", optional=true)
        public static native NSString Other();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMAccessoryCategoryTypeSecuritySystem", optional=true)
        public static native NSString SecuritySystem();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMAccessoryCategoryTypeBridge", optional=true)
        public static native NSString Bridge();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMAccessoryCategoryTypeDoor", optional=true)
        public static native NSString Door();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMAccessoryCategoryTypeDoorLock", optional=true)
        public static native NSString DoorLock();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMAccessoryCategoryTypeFan", optional=true)
        public static native NSString Fan();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMAccessoryCategoryTypeGarageDoorOpener", optional=true)
        public static native NSString GarageDoorOpener();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMAccessoryCategoryTypeLightbulb", optional=true)
        public static native NSString Lightbulb();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMAccessoryCategoryTypeOutlet", optional=true)
        public static native NSString Outlet();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMAccessoryCategoryTypeProgrammableSwitch", optional=true)
        public static native NSString ProgrammableSwitch();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMAccessoryCategoryTypeSensor", optional=true)
        public static native NSString Sensor();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMAccessoryCategoryTypeSwitch", optional=true)
        public static native NSString Switch();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMAccessoryCategoryTypeThermostat", optional=true)
        public static native NSString Thermostat();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMAccessoryCategoryTypeWindow", optional=true)
        public static native NSString Window();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="HMAccessoryCategoryTypeWindowCovering", optional=true)
        public static native NSString WindowCovering();
        /*</values>*/
    }
}
