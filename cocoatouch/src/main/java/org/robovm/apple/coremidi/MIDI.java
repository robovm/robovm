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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDI/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(MIDI.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIGetNumberOfDevices", optional=true)
    public static native @MachineSizedUInt long getNumberOfDevices();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIGetDevice", optional=true)
    public static native MIDIDevice getDevice(@MachineSizedUInt long deviceIndex0);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIGetNumberOfSources", optional=true)
    public static native @MachineSizedUInt long getNumberOfSources();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIGetSource", optional=true)
    public static native MIDIEndpoint getSource(@MachineSizedUInt long sourceIndex0);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIGetNumberOfDestinations", optional=true)
    public static native @MachineSizedUInt long getNumberOfDestinations();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIGetDestination", optional=true)
    public static native MIDIEndpoint getDestination(@MachineSizedUInt long destIndex0);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIGetNumberOfExternalDevices", optional=true)
    public static native @MachineSizedUInt long getNumberOfExternalDevices();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIGetExternalDevice", optional=true)
    public static native MIDIDevice getExternalDevice(@MachineSizedUInt long deviceIndex0);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDISend", optional=true)
    public static native MIDIError send(MIDIPort port, MIDIEndpoint dest, MIDIPacketList pktlist);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDISendSysex", optional=true)
    public static native MIDIError sendSysex(MIDISysexSendRequest request);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIFlushOutput", optional=true)
    public static native MIDIError flushOutput(MIDIEndpoint dest);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIRestart", optional=true)
    public static native MIDIError restart();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDISetupAddDevice", optional=true)
    public static native MIDIError addDevice(MIDIDevice device);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDISetupRemoveDevice", optional=true)
    public static native MIDIError removeDevice(MIDIDevice device);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDISetupAddExternalDevice", optional=true)
    public static native MIDIError addExternalDevice(MIDIDevice device);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDISetupRemoveExternalDevice", optional=true)
    public static native MIDIError removeExternalDevice(MIDIDevice device);
    /*</methods>*/
}
