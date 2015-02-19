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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDIMetaEvent/*</name>*/ 
    extends /*<extends>*/Struct<MIDIMetaEvent>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDIMetaEventPtr extends Ptr<MIDIMetaEvent, MIDIMetaEventPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MIDIMetaEvent() {}
    public MIDIMetaEvent(byte metaEventType, byte unused1, byte unused2, byte unused3, int dataLength, ByteBuffer data) {
        this.setMetaEventType(metaEventType);
        this.setUnused1(unused1);
        this.setUnused2(unused2);
        this.setUnused3(unused3);
        this.setDataLength(dataLength);
        this.setData(data);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native byte getMetaEventType();
    @StructMember(0) public native MIDIMetaEvent setMetaEventType(byte metaEventType);
    @StructMember(1) public native byte getUnused1();
    @StructMember(1) public native MIDIMetaEvent setUnused1(byte unused1);
    @StructMember(2) public native byte getUnused2();
    @StructMember(2) public native MIDIMetaEvent setUnused2(byte unused2);
    @StructMember(3) public native byte getUnused3();
    @StructMember(3) public native MIDIMetaEvent setUnused3(byte unused3);
    @StructMember(4) public native int getDataLength();
    @StructMember(4) public native MIDIMetaEvent setDataLength(int dataLength);
    @StructMember(5) public native @Array({1}) ByteBuffer getData();
    @StructMember(5) public native MIDIMetaEvent setData(@Array({1}) ByteBuffer data);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
