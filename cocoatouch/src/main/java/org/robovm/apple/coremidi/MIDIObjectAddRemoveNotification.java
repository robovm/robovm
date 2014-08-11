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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDIObjectAddRemoveNotification/*</name>*/ 
    extends /*<extends>*/Struct<MIDIObjectAddRemoveNotification>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDIObjectAddRemoveNotificationPtr extends Ptr<MIDIObjectAddRemoveNotification, MIDIObjectAddRemoveNotificationPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MIDIObjectAddRemoveNotification() {}
    public MIDIObjectAddRemoveNotification(MIDINotificationMessageID messageID, int messageSize, MIDIObject parent, MIDIObjectType parentType, MIDIObject child, MIDIObjectType childType) {
        this.messageID(messageID);
        this.messageSize(messageSize);
        this.parent(parent);
        this.parentType(parentType);
        this.child(child);
        this.childType(childType);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native MIDINotificationMessageID messageID();
    @StructMember(0) public native MIDIObjectAddRemoveNotification messageID(MIDINotificationMessageID messageID);
    @StructMember(1) public native int messageSize();
    @StructMember(1) public native MIDIObjectAddRemoveNotification messageSize(int messageSize);
    @StructMember(2) public native MIDIObject parent();
    @StructMember(2) public native MIDIObjectAddRemoveNotification parent(MIDIObject parent);
    @StructMember(3) public native MIDIObjectType parentType();
    @StructMember(3) public native MIDIObjectAddRemoveNotification parentType(MIDIObjectType parentType);
    @StructMember(4) public native MIDIObject child();
    @StructMember(4) public native MIDIObjectAddRemoveNotification child(MIDIObject child);
    @StructMember(5) public native MIDIObjectType childType();
    @StructMember(5) public native MIDIObjectAddRemoveNotification childType(MIDIObjectType childType);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
