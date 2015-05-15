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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDIPacket/*</name>*/ 
    extends /*<extends>*/Struct<MIDIPacket>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDIPacketPtr extends Ptr<MIDIPacket, MIDIPacketPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(MIDIPacket.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MIDIPacket() {}
    public MIDIPacket(long timeStamp, short length, ByteBuffer data) {
        this.setTimeStamp(timeStamp);
        this.setLength(length);
        this.setData(data);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native long getTimeStamp();
    @StructMember(0) public native MIDIPacket setTimeStamp(long timeStamp);
    @StructMember(1) public native short getLength();
    @StructMember(1) public native MIDIPacket setLength(short length);
    @StructMember(2) public native @Array({256}) ByteBuffer getData();
    @StructMember(2) public native MIDIPacket setData(@Array({256}) ByteBuffer data);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
