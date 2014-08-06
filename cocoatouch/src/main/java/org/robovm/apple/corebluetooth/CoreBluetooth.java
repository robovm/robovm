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
package org.robovm.apple.corebluetooth;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreBluetooth") @Marshaler(NSString.AsStringMarshaler.class)/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CoreBluetooth/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CoreBluetooth.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="CBAdvertisementDataLocalNameKey", optional=true)
    public static native NSString CBAdvertisementDataLocalNameKey();
    @GlobalValue(symbol="CBAdvertisementDataTxPowerLevelKey", optional=true)
    public static native NSString CBAdvertisementDataTxPowerLevelKey();
    @GlobalValue(symbol="CBAdvertisementDataServiceUUIDsKey", optional=true)
    public static native NSString CBAdvertisementDataServiceUUIDsKey();
    @GlobalValue(symbol="CBAdvertisementDataServiceDataKey", optional=true)
    public static native NSString CBAdvertisementDataServiceDataKey();
    @GlobalValue(symbol="CBAdvertisementDataManufacturerDataKey", optional=true)
    public static native NSString CBAdvertisementDataManufacturerDataKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="CBAdvertisementDataOverflowServiceUUIDsKey", optional=true)
    public static native NSString CBAdvertisementDataOverflowServiceUUIDsKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CBAdvertisementDataIsConnectable", optional=true)
    public static native NSString CBAdvertisementDataIsConnectable();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CBAdvertisementDataSolicitedServiceUUIDsKey", optional=true)
    public static native NSString CBAdvertisementDataSolicitedServiceUUIDsKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CBCentralManagerOptionShowPowerAlertKey", optional=true)
    public static native NSString CBCentralManagerOptionShowPowerAlertKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CBCentralManagerOptionRestoreIdentifierKey", optional=true)
    public static native NSString CBCentralManagerOptionRestoreIdentifierKey();
    @GlobalValue(symbol="CBCentralManagerScanOptionAllowDuplicatesKey", optional=true)
    public static native NSString CBCentralManagerScanOptionAllowDuplicatesKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CBCentralManagerScanOptionSolicitedServiceUUIDsKey", optional=true)
    public static native NSString CBCentralManagerScanOptionSolicitedServiceUUIDsKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="CBConnectPeripheralOptionNotifyOnConnectionKey", optional=true)
    public static native NSString CBConnectPeripheralOptionNotifyOnConnectionKey();
    @GlobalValue(symbol="CBConnectPeripheralOptionNotifyOnDisconnectionKey", optional=true)
    public static native NSString CBConnectPeripheralOptionNotifyOnDisconnectionKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="CBConnectPeripheralOptionNotifyOnNotificationKey", optional=true)
    public static native NSString CBConnectPeripheralOptionNotifyOnNotificationKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CBCentralManagerRestoredStatePeripheralsKey", optional=true)
    public static native NSString CBCentralManagerRestoredStatePeripheralsKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CBCentralManagerRestoredStateScanServicesKey", optional=true)
    public static native NSString CBCentralManagerRestoredStateScanServicesKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CBCentralManagerRestoredStateScanOptionsKey", optional=true)
    public static native NSString CBCentralManagerRestoredStateScanOptionsKey();
    @GlobalValue(symbol="CBErrorDomain", optional=true)
    public static native NSString ErrorDomain();
    @GlobalValue(symbol="CBATTErrorDomain", optional=true)
    public static native NSString ATTErrorDomain();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CBPeripheralManagerOptionShowPowerAlertKey", optional=true)
    public static native NSString CBPeripheralManagerOptionShowPowerAlertKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CBPeripheralManagerOptionRestoreIdentifierKey", optional=true)
    public static native NSString CBPeripheralManagerOptionRestoreIdentifierKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CBPeripheralManagerRestoredStateServicesKey", optional=true)
    public static native NSString CBPeripheralManagerRestoredStateServicesKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="CBPeripheralManagerRestoredStateAdvertisementDataKey", optional=true)
    public static native NSString CBPeripheralManagerRestoredStateAdvertisementDataKey();
    @GlobalValue(symbol="CBUUIDCharacteristicExtendedPropertiesString", optional=true)
    public static native NSString CBUUIDCharacteristicExtendedPropertiesString();
    @GlobalValue(symbol="CBUUIDCharacteristicUserDescriptionString", optional=true)
    public static native NSString CBUUIDCharacteristicUserDescriptionString();
    @GlobalValue(symbol="CBUUIDClientCharacteristicConfigurationString", optional=true)
    public static native NSString CBUUIDClientCharacteristicConfigurationString();
    @GlobalValue(symbol="CBUUIDServerCharacteristicConfigurationString", optional=true)
    public static native NSString CBUUIDServerCharacteristicConfigurationString();
    @GlobalValue(symbol="CBUUIDCharacteristicFormatString", optional=true)
    public static native NSString CBUUIDCharacteristicFormatString();
    @GlobalValue(symbol="CBUUIDCharacteristicAggregateFormatString", optional=true)
    public static native NSString CBUUIDCharacteristicAggregateFormatString();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="CBUUIDGenericAccessProfileString", optional=true)
    public static native NSString CBUUIDGenericAccessProfileString();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="CBUUIDGenericAttributeProfileString", optional=true)
    public static native NSString CBUUIDGenericAttributeProfileString();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="CBUUIDDeviceNameString", optional=true)
    public static native NSString CBUUIDDeviceNameString();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="CBUUIDAppearanceString", optional=true)
    public static native NSString CBUUIDAppearanceString();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="CBUUIDPeripheralPrivacyFlagString", optional=true)
    public static native NSString CBUUIDPeripheralPrivacyFlagString();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="CBUUIDReconnectionAddressString", optional=true)
    public static native NSString CBUUIDReconnectionAddressString();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="CBUUIDPeripheralPreferredConnectionParametersString", optional=true)
    public static native NSString CBUUIDPeripheralPreferredConnectionParametersString();
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @GlobalValue(symbol="CBUUIDServiceChangedString", optional=true)
    public static native NSString CBUUIDServiceChangedString();
    /*</methods>*/
}
