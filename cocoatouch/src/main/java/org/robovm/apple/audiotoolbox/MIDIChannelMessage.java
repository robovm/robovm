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
package org.robovm.apple.audiotoolbox;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDIChannelMessage/*</name>*/ 
    extends /*<extends>*/Struct<MIDIChannelMessage>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDIChannelMessagePtr extends Ptr<MIDIChannelMessage, MIDIChannelMessagePtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MIDIChannelMessage() {}
    public MIDIChannelMessage(byte status, byte data1, byte data2, byte reserved) {
        this.status(status);
        this.data1(data1);
        this.data2(data2);
        this.reserved(reserved);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native byte status();
    @StructMember(0) public native MIDIChannelMessage status(byte status);
    @StructMember(1) public native byte data1();
    @StructMember(1) public native MIDIChannelMessage data1(byte data1);
    @StructMember(2) public native byte data2();
    @StructMember(2) public native MIDIChannelMessage data2(byte data2);
    @StructMember(3) public native byte reserved();
    @StructMember(3) public native MIDIChannelMessage reserved(byte reserved);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
