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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAFPacketTableHeader/*</name>*/ 
    extends /*<extends>*/Struct<CAFPacketTableHeader>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CAFPacketTableHeaderPtr extends Ptr<CAFPacketTableHeader, CAFPacketTableHeaderPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CAFPacketTableHeader() {}
    public CAFPacketTableHeader(long mNumberPackets, long mNumberValidFrames, int mPrimingFrames, int mRemainderFrames, ByteBuffer mPacketDescriptions) {
        this.setMNumberPackets(mNumberPackets);
        this.setMNumberValidFrames(mNumberValidFrames);
        this.setMPrimingFrames(mPrimingFrames);
        this.setMRemainderFrames(mRemainderFrames);
        this.setMPacketDescriptions(mPacketDescriptions);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native long getMNumberPackets();
    @StructMember(0) public native CAFPacketTableHeader setMNumberPackets(long mNumberPackets);
    @StructMember(1) public native long getMNumberValidFrames();
    @StructMember(1) public native CAFPacketTableHeader setMNumberValidFrames(long mNumberValidFrames);
    @StructMember(2) public native int getMPrimingFrames();
    @StructMember(2) public native CAFPacketTableHeader setMPrimingFrames(int mPrimingFrames);
    @StructMember(3) public native int getMRemainderFrames();
    @StructMember(3) public native CAFPacketTableHeader setMRemainderFrames(int mRemainderFrames);
    @StructMember(4) public native @Array({1}) ByteBuffer getMPacketDescriptions();
    @StructMember(4) public native CAFPacketTableHeader setMPacketDescriptions(@Array({1}) ByteBuffer mPacketDescriptions);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
