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
package org.robovm.apple.corebluetooth;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreBluetooth") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CBUUIDIdentifier/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CBUUIDIdentifier/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CBUUIDIdentifier/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CBUUIDIdentifier toObject(Class<CBUUIDIdentifier> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CBUUIDIdentifier.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CBUUIDIdentifier o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<CBUUIDIdentifier> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CBUUIDIdentifier> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CBUUIDIdentifier.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CBUUIDIdentifier> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (CBUUIDIdentifier o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final CBUUIDIdentifier CharacteristicExtendedProperties = new CBUUIDIdentifier("CharacteristicExtendedProperties");
    public static final CBUUIDIdentifier CharacteristicUserDescription = new CBUUIDIdentifier("CharacteristicUserDescription");
    public static final CBUUIDIdentifier ClientCharacteristicConfiguration = new CBUUIDIdentifier("ClientCharacteristicConfiguration");
    public static final CBUUIDIdentifier ServerCharacteristicConfiguration = new CBUUIDIdentifier("ServerCharacteristicConfiguration");
    public static final CBUUIDIdentifier CharacteristicFormat = new CBUUIDIdentifier("CharacteristicFormat");
    public static final CBUUIDIdentifier CharacteristicAggregateFormat = new CBUUIDIdentifier("CharacteristicAggregateFormat");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final CBUUIDIdentifier GenericAccessProfile = new CBUUIDIdentifier("GenericAccessProfile");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final CBUUIDIdentifier GenericAttributeProfile = new CBUUIDIdentifier("GenericAttributeProfile");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final CBUUIDIdentifier DeviceName = new CBUUIDIdentifier("DeviceName");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final CBUUIDIdentifier Appearance = new CBUUIDIdentifier("Appearance");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final CBUUIDIdentifier PeripheralPrivacyFlag = new CBUUIDIdentifier("PeripheralPrivacyFlag");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final CBUUIDIdentifier ReconnectionAddress = new CBUUIDIdentifier("ReconnectionAddress");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final CBUUIDIdentifier PeripheralPreferredConnectionParameters = new CBUUIDIdentifier("PeripheralPreferredConnectionParameters");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final CBUUIDIdentifier ServiceChanged = new CBUUIDIdentifier("ServiceChanged");
    /*</constants>*/
    
    private static /*<name>*/CBUUIDIdentifier/*</name>*/[] values = new /*<name>*/CBUUIDIdentifier/*</name>*/[] {/*<value_list>*/CharacteristicExtendedProperties, CharacteristicUserDescription, ClientCharacteristicConfiguration, ServerCharacteristicConfiguration, CharacteristicFormat, CharacteristicAggregateFormat, GenericAccessProfile, GenericAttributeProfile, DeviceName, Appearance, PeripheralPrivacyFlag, ReconnectionAddress, PeripheralPreferredConnectionParameters, ServiceChanged/*</value_list>*/};
    
    /*<name>*/CBUUIDIdentifier/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CBUUIDIdentifier/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/CBUUIDIdentifier/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CBUUIDIdentifier/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("CoreBluetooth") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="CBUUIDCharacteristicExtendedPropertiesString", optional=true)
        public static native NSString CharacteristicExtendedProperties();
        @GlobalValue(symbol="CBUUIDCharacteristicUserDescriptionString", optional=true)
        public static native NSString CharacteristicUserDescription();
        @GlobalValue(symbol="CBUUIDClientCharacteristicConfigurationString", optional=true)
        public static native NSString ClientCharacteristicConfiguration();
        @GlobalValue(symbol="CBUUIDServerCharacteristicConfigurationString", optional=true)
        public static native NSString ServerCharacteristicConfiguration();
        @GlobalValue(symbol="CBUUIDCharacteristicFormatString", optional=true)
        public static native NSString CharacteristicFormat();
        @GlobalValue(symbol="CBUUIDCharacteristicAggregateFormatString", optional=true)
        public static native NSString CharacteristicAggregateFormat();
        /**
         * @since Available in iOS 5.0 and later.
         * @deprecated Deprecated in iOS 7.0.
         */
        @Deprecated
        @GlobalValue(symbol="CBUUIDGenericAccessProfileString", optional=true)
        public static native NSString GenericAccessProfile();
        /**
         * @since Available in iOS 5.0 and later.
         * @deprecated Deprecated in iOS 7.0.
         */
        @Deprecated
        @GlobalValue(symbol="CBUUIDGenericAttributeProfileString", optional=true)
        public static native NSString GenericAttributeProfile();
        /**
         * @since Available in iOS 5.0 and later.
         * @deprecated Deprecated in iOS 7.0.
         */
        @Deprecated
        @GlobalValue(symbol="CBUUIDDeviceNameString", optional=true)
        public static native NSString DeviceName();
        /**
         * @since Available in iOS 5.0 and later.
         * @deprecated Deprecated in iOS 7.0.
         */
        @Deprecated
        @GlobalValue(symbol="CBUUIDAppearanceString", optional=true)
        public static native NSString Appearance();
        /**
         * @since Available in iOS 5.0 and later.
         * @deprecated Deprecated in iOS 7.0.
         */
        @Deprecated
        @GlobalValue(symbol="CBUUIDPeripheralPrivacyFlagString", optional=true)
        public static native NSString PeripheralPrivacyFlag();
        /**
         * @since Available in iOS 5.0 and later.
         * @deprecated Deprecated in iOS 7.0.
         */
        @Deprecated
        @GlobalValue(symbol="CBUUIDReconnectionAddressString", optional=true)
        public static native NSString ReconnectionAddress();
        /**
         * @since Available in iOS 5.0 and later.
         * @deprecated Deprecated in iOS 7.0.
         */
        @Deprecated
        @GlobalValue(symbol="CBUUIDPeripheralPreferredConnectionParametersString", optional=true)
        public static native NSString PeripheralPreferredConnectionParameters();
        /**
         * @since Available in iOS 5.0 and later.
         * @deprecated Deprecated in iOS 7.0.
         */
        @Deprecated
        @GlobalValue(symbol="CBUUIDServiceChangedString", optional=true)
        public static native NSString ServiceChanged();
        /*</values>*/
    }
}
