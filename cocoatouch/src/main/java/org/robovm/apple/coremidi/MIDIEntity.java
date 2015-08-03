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
package org.robovm.apple.coremidi;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMIDI")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDIEntity/*</name>*/ 
    extends /*<extends>*/MIDIObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDIEntityPtr extends Ptr<MIDIEntity, MIDIEntityPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(MIDIEntity.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public MIDIDevice getDevice() {
        MIDIDevice.MIDIDevicePtr ptr = new MIDIDevice.MIDIDevicePtr();
        getDevice(ptr);
        return ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIEntityGetNumberOfSources", optional=true)
    public native @MachineSizedUInt long getNumberOfSources();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIEntityGetSource", optional=true)
    public native MIDIEndpoint getSource(@MachineSizedUInt long sourceIndex0);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIEntityGetNumberOfDestinations", optional=true)
    public native @MachineSizedUInt long getNumberOfDestinations();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIEntityGetDestination", optional=true)
    public native MIDIEndpoint getDestination(@MachineSizedUInt long destIndex0);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIEntityGetDevice", optional=true)
    protected native MIDIError getDevice(MIDIDevice.MIDIDevicePtr outDevice);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIEntityAddOrRemoveEndpoints", optional=true)
    public native MIDIError addOrRemoveEndpoints(@MachineSizedUInt long numSourceEndpoints, @MachineSizedUInt long numDestinationEndpoints);
    /*</methods>*/
}
