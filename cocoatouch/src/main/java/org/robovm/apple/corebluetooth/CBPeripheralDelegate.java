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
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/CBPeripheralDelegate/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "peripheralDidUpdateName:")
    void didUpdateName(CBPeripheral peripheral);
    /**
     * @since Available in iOS 6.0 and later.
     * @deprecated Deprecated in iOS 7.0.
     */
    @Deprecated
    @Method(selector = "peripheralDidInvalidateServices:")
    void didInvalidateServices(CBPeripheral peripheral);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "peripheral:didModifyServices:")
    void didModifyServices(CBPeripheral peripheral, NSArray<CBService> invalidatedServices);
    @Method(selector = "peripheralDidUpdateRSSI:error:")
    void didUpdateRSSI(CBPeripheral peripheral, NSError error);
    @Method(selector = "peripheral:didDiscoverServices:")
    void didDiscoverServices(CBPeripheral peripheral, NSError error);
    @Method(selector = "peripheral:didDiscoverIncludedServicesForService:error:")
    void didDiscoverIncludedServices(CBPeripheral peripheral, CBService service, NSError error);
    @Method(selector = "peripheral:didDiscoverCharacteristicsForService:error:")
    void didDiscoverCharacteristics(CBPeripheral peripheral, CBService service, NSError error);
    @Method(selector = "peripheral:didUpdateValueForCharacteristic:error:")
    void didUpdateValue(CBPeripheral peripheral, CBCharacteristic characteristic, NSError error);
    @Method(selector = "peripheral:didWriteValueForCharacteristic:error:")
    void didWriteValue(CBPeripheral peripheral, CBCharacteristic characteristic, NSError error);
    @Method(selector = "peripheral:didUpdateNotificationStateForCharacteristic:error:")
    void didUpdateNotificationState(CBPeripheral peripheral, CBCharacteristic characteristic, NSError error);
    @Method(selector = "peripheral:didDiscoverDescriptorsForCharacteristic:error:")
    void didDiscoverDescriptors(CBPeripheral peripheral, CBCharacteristic characteristic, NSError error);
    @Method(selector = "peripheral:didUpdateValueForDescriptor:error:")
    void didUpdateValue(CBPeripheral peripheral, CBDescriptor descriptor, NSError error);
    @Method(selector = "peripheral:didWriteValueForDescriptor:error:")
    void didWriteValue(CBPeripheral peripheral, CBDescriptor descriptor, NSError error);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
