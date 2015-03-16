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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/HMHomeDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements HMHomeDelegate/*</implements>*/ {

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
    @NotImplemented("homeDidUpdateName:")
    public void didUpdateName(HMHome home) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didAddAccessory:")
    public void didAddAccessory(HMHome home, HMAccessory accessory) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didRemoveAccessory:")
    public void didRemoveAccessory(HMHome home, HMAccessory accessory) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didAddUser:")
    public void didAddUser(HMHome home, HMUser user) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didRemoveUser:")
    public void didRemoveUser(HMHome home, HMUser user) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didUpdateRoom:forAccessory:")
    public void didUpdateRoom(HMHome home, HMRoom room, HMAccessory accessory) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didAddRoom:")
    public void didAddRoom(HMHome home, HMRoom room) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didRemoveRoom:")
    public void didRemoveRoom(HMHome home, HMRoom room) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didUpdateNameForRoom:")
    public void didUpdateRoomName(HMHome home, HMRoom room) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didAddZone:")
    public void didAddZone(HMHome home, HMZone zone) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didRemoveZone:")
    public void didRemoveZone(HMHome home, HMZone zone) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didUpdateNameForZone:")
    public void didUpdateZoneName(HMHome home, HMZone zone) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didAddRoom:toZone:")
    public void didAddRoomToZone(HMHome home, HMRoom room, HMZone zone) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didRemoveRoom:fromZone:")
    public void didRemoveRoomFromZone(HMHome home, HMRoom room, HMZone zone) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didAddServiceGroup:")
    public void didAddServiceGroup(HMHome home, HMServiceGroup group) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didRemoveServiceGroup:")
    public void didRemoveServiceGroup(HMHome home, HMServiceGroup group) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didUpdateNameForServiceGroup:")
    public void didUpdateServiceGroupName(HMHome home, HMServiceGroup group) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didAddService:toServiceGroup:")
    public void didAddServiceToServiceGroup(HMHome home, HMService service, HMServiceGroup group) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didRemoveService:fromServiceGroup:")
    public void didRemoveServiceFromServiceGroup(HMHome home, HMService service, HMServiceGroup group) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didAddActionSet:")
    public void didAddActionSet(HMHome home, HMActionSet actionSet) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didRemoveActionSet:")
    public void didRemoveActionSet(HMHome home, HMActionSet actionSet) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didUpdateNameForActionSet:")
    public void didUpdateActionSetName(HMHome home, HMActionSet actionSet) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didUpdateActionsForActionSet:")
    public void didUpdateActions(HMHome home, HMActionSet actionSet) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didAddTrigger:")
    public void didAddTrigger(HMHome home, HMTrigger trigger) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didRemoveTrigger:")
    public void didRemoveTrigger(HMHome home, HMTrigger trigger) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didUpdateNameForTrigger:")
    public void didUpdateTriggerName(HMHome home, HMTrigger trigger) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didUpdateTrigger:")
    public void didUpdateTrigger(HMHome home, HMTrigger trigger) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didUnblockAccessory:")
    public void didUnblockAccessory(HMHome home, HMAccessory accessory) { throw new UnsupportedOperationException(); }
    @NotImplemented("home:didEncounterError:forAccessory:")
    public void didEncounterError(HMHome home, NSError error, HMAccessory accessory) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
