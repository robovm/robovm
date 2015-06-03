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
package org.robovm.apple.audiotoolbox;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.audiounit.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremidi.*;
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
    public CAFAudioDescription(double sampleRate, AudioFormat format, CAFFormatFlags formatFlags, int bytesPerPacket, int framesPerPacket, int channelsPerFrame, int bitsPerChannel) {
        this.setSampleRate(sampleRate);
        this.setFormat(format);
        this.setFormatFlags(formatFlags);
        this.setBytesPerPacket(bytesPerPacket);
        this.setFramesPerPacket(framesPerPacket);
        this.setChannelsPerFrame(channelsPerFrame);
        this.setBitsPerChannel(bitsPerChannel);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native double getSampleRate();
    @StructMember(0) public native CAFAudioDescription setSampleRate(double sampleRate);
    @StructMember(1) public native AudioFormat getFormat();
    @StructMember(1) public native CAFAudioDescription setFormat(AudioFormat format);
    @StructMember(2) public native CAFFormatFlags getFormatFlags();
    @StructMember(2) public native CAFAudioDescription setFormatFlags(CAFFormatFlags formatFlags);
    @StructMember(3) public native int getBytesPerPacket();
    @StructMember(3) public native CAFAudioDescription setBytesPerPacket(int bytesPerPacket);
    @StructMember(4) public native int getFramesPerPacket();
    @StructMember(4) public native CAFAudioDescription setFramesPerPacket(int framesPerPacket);
    @StructMember(5) public native int getChannelsPerFrame();
    @StructMember(5) public native CAFAudioDescription setChannelsPerFrame(int channelsPerFrame);
    @StructMember(6) public native int getBitsPerChannel();
    @StructMember(6) public native CAFAudioDescription setBitsPerChannel(int bitsPerChannel);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
