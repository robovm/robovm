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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAFAudioDescription/*</name>*/ 
    extends /*<extends>*/Struct<CAFAudioDescription>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CAFAudioDescriptionPtr extends Ptr<CAFAudioDescription, CAFAudioDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CAFAudioDescription() {}
    public CAFAudioDescription(double mSampleRate, int mFormatID, int mFormatFlags, int mBytesPerPacket, int mFramesPerPacket, int mChannelsPerFrame, int mBitsPerChannel) {
        this.setMSampleRate(mSampleRate);
        this.setMFormatID(mFormatID);
        this.setMFormatFlags(mFormatFlags);
        this.setMBytesPerPacket(mBytesPerPacket);
        this.setMFramesPerPacket(mFramesPerPacket);
        this.setMChannelsPerFrame(mChannelsPerFrame);
        this.setMBitsPerChannel(mBitsPerChannel);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native double getMSampleRate();
    @StructMember(0) public native CAFAudioDescription setMSampleRate(double mSampleRate);
    @StructMember(1) public native int getMFormatID();
    @StructMember(1) public native CAFAudioDescription setMFormatID(int mFormatID);
    @StructMember(2) public native int getMFormatFlags();
    @StructMember(2) public native CAFAudioDescription setMFormatFlags(int mFormatFlags);
    @StructMember(3) public native int getMBytesPerPacket();
    @StructMember(3) public native CAFAudioDescription setMBytesPerPacket(int mBytesPerPacket);
    @StructMember(4) public native int getMFramesPerPacket();
    @StructMember(4) public native CAFAudioDescription setMFramesPerPacket(int mFramesPerPacket);
    @StructMember(5) public native int getMChannelsPerFrame();
    @StructMember(5) public native CAFAudioDescription setMChannelsPerFrame(int mChannelsPerFrame);
    @StructMember(6) public native int getMBitsPerChannel();
    @StructMember(6) public native CAFAudioDescription setMBitsPerChannel(int mBitsPerChannel);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
