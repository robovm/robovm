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
    public void didUpdateName(HMHome home) {}
    @NotImplemented("home:didAddAccessory:")
    public void didAddAccessory(HMHome home, HMAccessory accessory) {}
    @NotImplemented("home:didRemoveAccessory:")
    public void didRemoveAccessory(HMHome home, HMAccessory accessory) {}
    @NotImplemented("home:didAddUser:")
    public void didAddUser(HMHome home, HMUser user) {}
    @NotImplemented("home:didRemoveUser:")
    public void didRemoveUser(HMHome home, HMUser user) {}
    @NotImplemented("home:didUpdateRoom:forAccessory:")
    public void didUpdateRoom(HMHome home, HMRoom room, HMAccessory accessory) {}
    @NotImplemented("home:didAddRoom:")
    public void didAddRoom(HMHome home, HMRoom room) {}
    @NotImplemented("home:didRemoveRoom:")
    public void didRemoveRoom(HMHome home, HMRoom room) {}
    @NotImplemented("home:didUpdateNameForRoom:")
    public void didUpdateRoomName(HMHome home, HMRoom room) {}
    @NotImplemented("home:didAddZone:")
    public void didAddZone(HMHome home, HMZone zone) {}
    @NotImplemented("home:didRemoveZone:")
    public void didRemoveZone(HMHome home, HMZone zone) {}
    @NotImplemented("home:didUpdateNameForZone:")
    public void didUpdateZoneName(HMHome home, HMZone zone) {}
    @NotImplemented("home:didAddRoom:toZone:")
    public void didAddRoomToZone(HMHome home, HMRoom room, HMZone zone) {}
    @NotImplemented("home:didRemoveRoom:fromZone:")
    public void didRemoveRoomFromZone(HMHome home, HMRoom room, HMZone zone) {}
    @NotImplemented("home:didAddServiceGroup:")
    public void didAddServiceGroup(HMHome home, HMServiceGroup group) {}
    @NotImplemented("home:didRemoveServiceGroup:")
    public void didRemoveServiceGroup(HMHome home, HMServiceGroup group) {}
    @NotImplemented("home:didUpdateNameForServiceGroup:")
    public void didUpdateServiceGroupName(HMHome home, HMServiceGroup group) {}
    @NotImplemented("home:didAddService:toServiceGroup:")
    public void didAddServiceToServiceGroup(HMHome home, HMService service, HMServiceGroup group) {}
    @NotImplemented("home:didRemoveService:fromServiceGroup:")
    public void didRemoveServiceFromServiceGroup(HMHome home, HMService service, HMServiceGroup group) {}
    @NotImplemented("home:didAddActionSet:")
    public void didAddActionSet(HMHome home, HMActionSet actionSet) {}
    @NotImplemented("home:didRemoveActionSet:")
    public void didRemoveActionSet(HMHome home, HMActionSet actionSet) {}
    @NotImplemented("home:didUpdateNameForActionSet:")
    public void didUpdateActionSetName(HMHome home, HMActionSet actionSet) {}
    @NotImplemented("home:didUpdateActionsForActionSet:")
    public void didUpdateActions(HMHome home, HMActionSet actionSet) {}
    @NotImplemented("home:didAddTrigger:")
    public void didAddTrigger(HMHome home, HMTrigger trigger) {}
    @NotImplemented("home:didRemoveTrigger:")
    public void didRemoveTrigger(HMHome home, HMTrigger trigger) {}
    @NotImplemented("home:didUpdateNameForTrigger:")
    public void didUpdateTriggerName(HMHome home, HMTrigger trigger) {}
    @NotImplemented("home:didUpdateTrigger:")
    public void didUpdateTrigger(HMHome home, HMTrigger trigger) {}
    @NotImplemented("home:didUnblockAccessory:")
    public void didUnblockAccessory(HMHome home, HMAccessory accessory) {}
    @NotImplemented("home:didEncounterError:forAccessory:")
    public void didEncounterError(HMHome home, NSError error, HMAccessory accessory) {}
    /*</methods>*/
}
