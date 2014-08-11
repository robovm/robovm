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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDIObjectPropertyChangeNotification/*</name>*/ 
    extends /*<extends>*/Struct<MIDIObjectPropertyChangeNotification>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDIObjectPropertyChangeNotificationPtr extends Ptr<MIDIObjectPropertyChangeNotification, MIDIObjectPropertyChangeNotificationPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MIDIObjectPropertyChangeNotification() {}
    public MIDIObjectPropertyChangeNotification(MIDINotificationMessageID messageID, int messageSize, MIDIObject object, MIDIObjectType objectType, String propertyName) {
        this.messageID(messageID);
        this.messageSize(messageSize);
        this.object(object);
        this.objectType(objectType);
        this.propertyName(propertyName);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native MIDINotificationMessageID messageID();
    @StructMember(0) public native MIDIObjectPropertyChangeNotification messageID(MIDINotificationMessageID messageID);
    @StructMember(1) public native int messageSize();
    @StructMember(1) public native MIDIObjectPropertyChangeNotification messageSize(int messageSize);
    @StructMember(2) public native MIDIObject object();
    @StructMember(2) public native MIDIObjectPropertyChangeNotification object(MIDIObject object);
    @StructMember(3) public native MIDIObjectType objectType();
    @StructMember(3) public native MIDIObjectPropertyChangeNotification objectType(MIDIObjectType objectType);
    @StructMember(4) public native String propertyName();
    @StructMember(4) public native MIDIObjectPropertyChangeNotification propertyName(String propertyName);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
