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
package org.robovm.apple.coremidi;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMIDI")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDIDeviceList/*</name>*/ 
    extends /*<extends>*/MIDIObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDIDeviceListPtr extends Ptr<MIDIDeviceList, MIDIDeviceListPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(MIDIDeviceList.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected MIDIDeviceList() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIDeviceListGetNumberOfDevices", optional=true)
    public native @MachineSizedUInt long getNumberOfDevices();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIDeviceListGetDevice", optional=true)
    public native MIDIDevice getDevice(@MachineSizedUInt long index0);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIDeviceListAddDevice", optional=true)
    public native MIDIError addDevice(MIDIDevice dev);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIDeviceListDispose", optional=true)
    public native MIDIError dispose();
    /*</methods>*/
}
