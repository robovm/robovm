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
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/CBCentralManagerDelegate/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "centralManagerDidUpdateState:")
    void didUpdateState(CBCentralManager central);
    @Method(selector = "centralManager:willRestoreState:")
    void willRestoreState(CBCentralManager central, CBCentralManagerRestoredState dict);
    @Method(selector = "centralManager:didRetrievePeripherals:")
    void didRetrievePeripherals(CBCentralManager central, NSArray<CBPeripheral> peripherals);
    @Method(selector = "centralManager:didRetrieveConnectedPeripherals:")
    void didRetrieveConnectedPeripherals(CBCentralManager central, NSArray<CBPeripheral> peripherals);
    @Method(selector = "centralManager:didDiscoverPeripheral:advertisementData:RSSI:")
    void didDiscoverPeripheral(CBCentralManager central, CBPeripheral peripheral, CBAdvertisementData advertisementData, NSNumber rssi);
    @Method(selector = "centralManager:didConnectPeripheral:")
    void didConnectPeripheral(CBCentralManager central, CBPeripheral peripheral);
    @Method(selector = "centralManager:didFailToConnectPeripheral:error:")
    void didFailToConnectPeripheral(CBCentralManager central, CBPeripheral peripheral, NSError error);
    @Method(selector = "centralManager:didDisconnectPeripheral:error:")
    void didDisconnectPeripheral(CBCentralManager central, CBPeripheral peripheral, NSError error);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
