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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioBytePacketTranslation/*</name>*/ 
    extends /*<extends>*/Struct<AudioBytePacketTranslation>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioBytePacketTranslationPtr extends Ptr<AudioBytePacketTranslation, AudioBytePacketTranslationPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AudioBytePacketTranslation() {}
    public AudioBytePacketTranslation(long mByte, long mPacket, int mByteOffsetInPacket, int mFlags) {
        this.mByte(mByte);
        this.mPacket(mPacket);
        this.mByteOffsetInPacket(mByteOffsetInPacket);
        this.mFlags(mFlags);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native long mByte();
    @StructMember(0) public native AudioBytePacketTranslation mByte(long mByte);
    @StructMember(1) public native long mPacket();
    @StructMember(1) public native AudioBytePacketTranslation mPacket(long mPacket);
    @StructMember(2) public native int mByteOffsetInPacket();
    @StructMember(2) public native AudioBytePacketTranslation mByteOffsetInPacket(int mByteOffsetInPacket);
    @StructMember(3) public native int mFlags();
    @StructMember(3) public native AudioBytePacketTranslation mFlags(int mFlags);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
