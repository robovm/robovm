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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioStreamPacketDescription/*</name>*/ 
    extends /*<extends>*/Struct<AudioStreamPacketDescription>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioStreamPacketDescriptionPtr extends Ptr<AudioStreamPacketDescription, AudioStreamPacketDescriptionPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AudioStreamPacketDescription() {}
    public AudioStreamPacketDescription(long mStartOffset, int mVariableFramesInPacket, int mDataByteSize) {
        this.setMstartoffset(mStartOffset);
        this.setMvariableframesinpacket(mVariableFramesInPacket);
        this.setMdatabytesize(mDataByteSize);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native long getMstartoffset();
    @StructMember(0) public native AudioStreamPacketDescription setMstartoffset(long mStartOffset);
    
    @Deprecated
    @StructMember(0) public native long mStartOffset();
    @Deprecated
    @StructMember(0) public native AudioStreamPacketDescription mStartOffset(long mStartOffset);
    
    @StructMember(1) public native int getMvariableframesinpacket();
    @StructMember(1) public native AudioStreamPacketDescription setMvariableframesinpacket(int mVariableFramesInPacket);
    
    @Deprecated
    @StructMember(1) public native int mVariableFramesInPacket();
    @Deprecated
    @StructMember(1) public native AudioStreamPacketDescription mVariableFramesInPacket(int mVariableFramesInPacket);
    
    @StructMember(2) public native int getMdatabytesize();
    @StructMember(2) public native AudioStreamPacketDescription setMdatabytesize(int mDataByteSize);
    
    @Deprecated
    @StructMember(2) public native int mDataByteSize();
    @Deprecated
    @StructMember(2) public native AudioStreamPacketDescription mDataByteSize(int mDataByteSize);
    
    /*</members>*/
    /*<methods>*//*</methods>*/
}
