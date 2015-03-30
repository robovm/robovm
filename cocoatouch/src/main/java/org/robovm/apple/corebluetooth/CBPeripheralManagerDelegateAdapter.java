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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CBPeripheralManagerDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements CBPeripheralManagerDelegate/*</implements>*/ {

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
    @NotImplemented("peripheralManagerDidUpdateState:")
    public void didUpdateState(CBPeripheralManager peripheral) {}
    @NotImplemented("peripheralManager:willRestoreState:")
    public void willRestoreState(CBPeripheralManager peripheral, CBPeripheralManagerRestoredState dict) {}
    @NotImplemented("peripheralManagerDidStartAdvertising:error:")
    public void didStartAdvertising(CBPeripheralManager peripheral, NSError error) {}
    @NotImplemented("peripheralManager:didAddService:error:")
    public void didAddService(CBPeripheralManager peripheral, CBService service, NSError error) {}
    @NotImplemented("peripheralManager:central:didSubscribeToCharacteristic:")
    public void didSubscribeToCharacteristic(CBPeripheralManager peripheral, CBCentral central, CBCharacteristic characteristic) {}
    @NotImplemented("peripheralManager:central:didUnsubscribeFromCharacteristic:")
    public void didUnsubscribeFromCharacteristic(CBPeripheralManager peripheral, CBCentral central, CBCharacteristic characteristic) {}
    @NotImplemented("peripheralManager:didReceiveReadRequest:")
    public void didReceiveReadRequest(CBPeripheralManager peripheral, CBATTRequest request) {}
    @NotImplemented("peripheralManager:didReceiveWriteRequests:")
    public void didReceiveWriteRequests(CBPeripheralManager peripheral, NSArray<CBATTRequest> requests) {}
    @NotImplemented("peripheralManagerIsReadyToUpdateSubscribers:")
    public void readyToUpdateSubscribers(CBPeripheralManager peripheral) {}
    /*</methods>*/
}
