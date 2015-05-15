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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDIIOErrorNotification/*</name>*/ 
    extends /*<extends>*/Struct<MIDIIOErrorNotification>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDIIOErrorNotificationPtr extends Ptr<MIDIIOErrorNotification, MIDIIOErrorNotificationPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MIDIIOErrorNotification() {}
    public MIDIIOErrorNotification(MIDINotificationMessageID messageID, int messageSize, MIDIDevice driverDevice, OSStatus errorCode) {
        this.setMessageID(messageID);
        this.setMessageSize(messageSize);
        this.setDriverDevice(driverDevice);
        this.setErrorCode(errorCode);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native MIDINotificationMessageID getMessageID();
    @StructMember(0) public native MIDIIOErrorNotification setMessageID(MIDINotificationMessageID messageID);
    @StructMember(1) public native int getMessageSize();
    @StructMember(1) public native MIDIIOErrorNotification setMessageSize(int messageSize);
    @StructMember(2) public native MIDIDevice getDriverDevice();
    @StructMember(2) public native MIDIIOErrorNotification setDriverDevice(MIDIDevice driverDevice);
    @StructMember(3) public native OSStatus getErrorCode();
    @StructMember(3) public native MIDIIOErrorNotification setErrorCode(OSStatus errorCode);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
