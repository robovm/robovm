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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioQueueBuffer/*</name>*/ 
    extends /*<extends>*/Struct<AudioQueueBuffer>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioQueueBufferPtr extends Ptr<AudioQueueBuffer, AudioQueueBufferPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AudioQueueBuffer() {}
    public AudioQueueBuffer(int mAudioDataBytesCapacity, VoidPtr mAudioData, int mAudioDataByteSize, VoidPtr mUserData, int mPacketDescriptionCapacity, AudioStreamPacketDescription mPacketDescriptions, int mPacketDescriptionCount) {
        this.setMAudioDataBytesCapacity(mAudioDataBytesCapacity);
        this.setMAudioData(mAudioData);
        this.setMAudioDataByteSize(mAudioDataByteSize);
        this.setMUserData(mUserData);
        this.setMPacketDescriptionCapacity(mPacketDescriptionCapacity);
        this.setMPacketDescriptions(mPacketDescriptions);
        this.setMPacketDescriptionCount(mPacketDescriptionCount);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getMAudioDataBytesCapacity();
    @StructMember(0) public native AudioQueueBuffer setMAudioDataBytesCapacity(int mAudioDataBytesCapacity);
    @StructMember(1) public native VoidPtr getMAudioData();
    @StructMember(1) public native AudioQueueBuffer setMAudioData(VoidPtr mAudioData);
    @StructMember(2) public native int getMAudioDataByteSize();
    @StructMember(2) public native AudioQueueBuffer setMAudioDataByteSize(int mAudioDataByteSize);
    @StructMember(3) public native VoidPtr getMUserData();
    @StructMember(3) public native AudioQueueBuffer setMUserData(VoidPtr mUserData);
    @StructMember(4) public native int getMPacketDescriptionCapacity();
    @StructMember(4) public native AudioQueueBuffer setMPacketDescriptionCapacity(int mPacketDescriptionCapacity);
    @StructMember(5) public native AudioStreamPacketDescription getMPacketDescriptions();
    @StructMember(5) public native AudioQueueBuffer setMPacketDescriptions(AudioStreamPacketDescription mPacketDescriptions);
    @StructMember(6) public native int getMPacketDescriptionCount();
    @StructMember(6) public native AudioQueueBuffer setMPacketDescriptionCount(int mPacketDescriptionCount);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
