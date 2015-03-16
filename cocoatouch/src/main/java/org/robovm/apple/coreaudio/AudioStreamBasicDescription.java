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
package org.robovm.apple.coreaudio;

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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioStreamBasicDescription/*</name>*/ 
    extends /*<extends>*/Struct<AudioStreamBasicDescription>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioStreamBasicDescriptionPtr extends Ptr<AudioStreamBasicDescription, AudioStreamBasicDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AudioStreamBasicDescription() {}
    public AudioStreamBasicDescription(double mSampleRate, AudioFormat mFormatID, int mFormatFlags, int mBytesPerPacket, int mFramesPerPacket, int mBytesPerFrame, int mChannelsPerFrame, int mBitsPerChannel, int mReserved) {
        this.setMSampleRate(mSampleRate);
        this.setMFormatID(mFormatID);
        this.setMFormatFlags(mFormatFlags);
        this.setMBytesPerPacket(mBytesPerPacket);
        this.setMFramesPerPacket(mFramesPerPacket);
        this.setMBytesPerFrame(mBytesPerFrame);
        this.setMChannelsPerFrame(mChannelsPerFrame);
        this.setMBitsPerChannel(mBitsPerChannel);
        this.setMReserved(mReserved);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native double getMSampleRate();
    @StructMember(0) public native AudioStreamBasicDescription setMSampleRate(double mSampleRate);
    @StructMember(1) public native AudioFormat getMFormatID();
    @StructMember(1) public native AudioStreamBasicDescription setMFormatID(AudioFormat mFormatID);
    @StructMember(2) public native int getMFormatFlags();
    @StructMember(2) public native AudioStreamBasicDescription setMFormatFlags(int mFormatFlags);
    @StructMember(3) public native int getMBytesPerPacket();
    @StructMember(3) public native AudioStreamBasicDescription setMBytesPerPacket(int mBytesPerPacket);
    @StructMember(4) public native int getMFramesPerPacket();
    @StructMember(4) public native AudioStreamBasicDescription setMFramesPerPacket(int mFramesPerPacket);
    @StructMember(5) public native int getMBytesPerFrame();
    @StructMember(5) public native AudioStreamBasicDescription setMBytesPerFrame(int mBytesPerFrame);
    @StructMember(6) public native int getMChannelsPerFrame();
    @StructMember(6) public native AudioStreamBasicDescription setMChannelsPerFrame(int mChannelsPerFrame);
    @StructMember(7) public native int getMBitsPerChannel();
    @StructMember(7) public native AudioStreamBasicDescription setMBitsPerChannel(int mBitsPerChannel);
    @StructMember(8) public native int getMReserved();
    @StructMember(8) public native AudioStreamBasicDescription setMReserved(int mReserved);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
