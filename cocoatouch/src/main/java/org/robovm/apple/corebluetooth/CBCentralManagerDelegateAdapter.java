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
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CBCentralManagerDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements CBCentralManagerDelegate/*</implements>*/ {

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
    @NotImplemented("centralManagerDidUpdateState:")
    public void didUpdateState(CBCentralManager central) {}
    @NotImplemented("centralManager:willRestoreState:")
    public void willRestoreState(CBCentralManager central, CBCentralManagerRestoredState dict) {}
    @NotImplemented("centralManager:didRetrievePeripherals:")
    public void didRetrievePeripherals(CBCentralManager central, NSArray<CBPeripheral> peripherals) {}
    @NotImplemented("centralManager:didRetrieveConnectedPeripherals:")
    public void didRetrieveConnectedPeripherals(CBCentralManager central, NSArray<CBPeripheral> peripherals) {}
    @NotImplemented("centralManager:didDiscoverPeripheral:advertisementData:RSSI:")
    public void didDiscoverPeripheral(CBCentralManager central, CBPeripheral peripheral, CBAdvertisementData advertisementData, NSNumber rssi) {}
    @NotImplemented("centralManager:didConnectPeripheral:")
    public void didConnectPeripheral(CBCentralManager central, CBPeripheral peripheral) {}
    @NotImplemented("centralManager:didFailToConnectPeripheral:error:")
    public void didFailToConnectPeripheral(CBCentralManager central, CBPeripheral peripheral, NSError error) {}
    @NotImplemented("centralManager:didDisconnectPeripheral:error:")
    public void didDisconnectPeripheral(CBCentralManager central, CBPeripheral peripheral, NSError error) {}
    /*</methods>*/
}
