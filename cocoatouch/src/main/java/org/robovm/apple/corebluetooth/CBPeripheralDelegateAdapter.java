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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CBPeripheralDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements CBPeripheralDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @NotImplemented("peripheralDidUpdateName:")
    public void didUpdateName(CBPeripheral peripheral) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 6.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @NotImplemented("peripheralDidInvalidateServices:")
    public void didInvalidateServices(CBPeripheral peripheral) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("peripheral:didModifyServices:")
    public void didModifyServices(CBPeripheral peripheral, NSArray<CBService> invalidatedServices) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 5.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @NotImplemented("peripheralDidUpdateRSSI:error:")
    public void didUpdateRSSI(CBPeripheral peripheral, NSError error) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 8.0 and later.
     */
    @NotImplemented("peripheral:didReadRSSI:error:")
    public void didReadRSSI(CBPeripheral peripheral, NSNumber RSSI, NSError error) { throw new UnsupportedOperationException(); }
    @NotImplemented("peripheral:didDiscoverServices:")
    public void didDiscoverServices(CBPeripheral peripheral, NSError error) { throw new UnsupportedOperationException(); }
    @NotImplemented("peripheral:didDiscoverIncludedServicesForService:error:")
    public void didDiscoverIncludedServices(CBPeripheral peripheral, CBService service, NSError error) { throw new UnsupportedOperationException(); }
    @NotImplemented("peripheral:didDiscoverCharacteristicsForService:error:")
    public void didDiscoverCharacteristics(CBPeripheral peripheral, CBService service, NSError error) { throw new UnsupportedOperationException(); }
    @NotImplemented("peripheral:didUpdateValueForCharacteristic:error:")
    public void didUpdateValue(CBPeripheral peripheral, CBCharacteristic characteristic, NSError error) { throw new UnsupportedOperationException(); }
    @NotImplemented("peripheral:didWriteValueForCharacteristic:error:")
    public void didWriteValue(CBPeripheral peripheral, CBCharacteristic characteristic, NSError error) { throw new UnsupportedOperationException(); }
    @NotImplemented("peripheral:didUpdateNotificationStateForCharacteristic:error:")
    public void didUpdateNotificationState(CBPeripheral peripheral, CBCharacteristic characteristic, NSError error) { throw new UnsupportedOperationException(); }
    @NotImplemented("peripheral:didDiscoverDescriptorsForCharacteristic:error:")
    public void didDiscoverDescriptors(CBPeripheral peripheral, CBCharacteristic characteristic, NSError error) { throw new UnsupportedOperationException(); }
    @NotImplemented("peripheral:didUpdateValueForDescriptor:error:")
    public void didUpdateValue(CBPeripheral peripheral, CBDescriptor descriptor, NSError error) { throw new UnsupportedOperationException(); }
    @NotImplemented("peripheral:didWriteValueForDescriptor:error:")
    public void didWriteValue(CBPeripheral peripheral, CBDescriptor descriptor, NSError error) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
