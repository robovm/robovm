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
@Marshaler(CBUUIDIdentifier.Marshaler.class)
/*<annotations>*/@Library("CoreBluetooth")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CBUUIDIdentifier/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static CBUUIDIdentifier toObject(Class<CBUUIDIdentifier> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CBUUIDIdentifier.valueOf(o.toString());
        }
        @MarshalsPointer
        public static long toNative(CBUUIDIdentifier o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(new NSString(o.value()), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CBUUIDIdentifier.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    public static final CBUUIDIdentifier CharacteristicExtendedProperties = new CBUUIDIdentifier("CharacteristicExtendedPropertiesValue");
    public static final CBUUIDIdentifier CharacteristicUserDescription = new CBUUIDIdentifier("CharacteristicUserDescriptionValue");
    public static final CBUUIDIdentifier ClientCharacteristicConfiguration = new CBUUIDIdentifier("ClientCharacteristicConfigurationValue");
    public static final CBUUIDIdentifier ServerCharacteristicConfiguration = new CBUUIDIdentifier("ServerCharacteristicConfigurationValue");
    public static final CBUUIDIdentifier CharacteristicFormat = new CBUUIDIdentifier("CharacteristicFormatValue");
    public static final CBUUIDIdentifier CharacteristicAggregateFormat = new CBUUIDIdentifier("CharacteristicAggregateFormatValue");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final CBUUIDIdentifier GenericAccessProfile = new CBUUIDIdentifier("GenericAccessProfileValue");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final CBUUIDIdentifier GenericAttributeProfile = new CBUUIDIdentifier("GenericAttributeProfileValue");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final CBUUIDIdentifier DeviceName = new CBUUIDIdentifier("DeviceNameValue");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final CBUUIDIdentifier Appearance = new CBUUIDIdentifier("AppearanceValue");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final CBUUIDIdentifier PeripheralPrivacyFlag = new CBUUIDIdentifier("PeripheralPrivacyFlagValue");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final CBUUIDIdentifier ReconnectionAddress = new CBUUIDIdentifier("ReconnectionAddressValue");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final CBUUIDIdentifier PeripheralPreferredConnectionParameters = new CBUUIDIdentifier("PeripheralPreferredConnectionParametersValue");
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    public static final CBUUIDIdentifier ServiceChanged = new CBUUIDIdentifier("ServiceChangedValue");
    
    private static CBUUIDIdentifier[] values = new CBUUIDIdentifier[] {CharacteristicExtendedProperties, CharacteristicUserDescription, ClientCharacteristicConfiguration, ServerCharacteristicConfiguration, 
        CharacteristicFormat, CharacteristicAggregateFormat, GenericAccessProfile, GenericAttributeProfile, DeviceName, Appearance, PeripheralPrivacyFlag, ReconnectionAddress, PeripheralPreferredConnectionParameters, 
        ServiceChanged};
    private final LazyGlobalValue<String> lazyGlobalValue;
    
    private CBUUIDIdentifier(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public String value() {
        return lazyGlobalValue.value();
    }
    
    public static CBUUIDIdentifier valueOf(String value) {
        for (CBUUIDIdentifier v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CBUUIDIdentifier/*</name>*/.class.getName());
    }
    /*<methods>*/
    @GlobalValue(symbol="CBUUIDCharacteristicExtendedPropertiesString", optional=true)
    protected static native String CharacteristicExtendedPropertiesValue();
    @GlobalValue(symbol="CBUUIDCharacteristicUserDescriptionString", optional=true)
    protected static native String CharacteristicUserDescriptionValue();
    @GlobalValue(symbol="CBUUIDClientCharacteristicConfigurationString", optional=true)
    protected static native String ClientCharacteristicConfigurationValue();
    @GlobalValue(symbol="CBUUIDServerCharacteristicConfigurationString", optional=true)
    protected static native String ServerCharacteristicConfigurationValue();
    @GlobalValue(symbol="CBUUIDCharacteristicFormatString", optional=true)
    protected static native String CharacteristicFormatValue();
    @GlobalValue(symbol="CBUUIDCharacteristicAggregateFormatString", optional=true)
    protected static native String CharacteristicAggregateFormatValue();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="CBUUIDGenericAccessProfileString", optional=true)
    protected static native String GenericAccessProfileValue();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="CBUUIDGenericAttributeProfileString", optional=true)
    protected static native String GenericAttributeProfileValue();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="CBUUIDDeviceNameString", optional=true)
    protected static native String DeviceNameValue();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="CBUUIDAppearanceString", optional=true)
    protected static native String AppearanceValue();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="CBUUIDPeripheralPrivacyFlagString", optional=true)
    protected static native String PeripheralPrivacyFlagValue();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="CBUUIDReconnectionAddressString", optional=true)
    protected static native String ReconnectionAddressValue();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="CBUUIDPeripheralPreferredConnectionParametersString", optional=true)
    protected static native String PeripheralPreferredConnectionParametersValue();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="CBUUIDServiceChangedString", optional=true)
    protected static native String ServiceChangedValue();
    /*</methods>*/
}
