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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAFSMPTETime/*</name>*/ 
    extends /*<extends>*/Struct<CAFSMPTETime>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CAFSMPTETimePtr extends Ptr<CAFSMPTETime, CAFSMPTETimePtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CAFSMPTETime() {}
    public CAFSMPTETime(byte hours, byte minutes, byte seconds, byte frames, int subFrameSampleOffset) {
        this.setHours(hours);
        this.setMinutes(minutes);
        this.setSeconds(seconds);
        this.setFrames(frames);
        this.setSubFrameSampleOffset(subFrameSampleOffset);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native byte getHours();
    @StructMember(0) public native CAFSMPTETime setHours(byte hours);
    @StructMember(1) public native byte getMinutes();
    @StructMember(1) public native CAFSMPTETime setMinutes(byte minutes);
    @StructMember(2) public native byte getSeconds();
    @StructMember(2) public native CAFSMPTETime setSeconds(byte seconds);
    @StructMember(3) public native byte getFrames();
    @StructMember(3) public native CAFSMPTETime setFrames(byte frames);
    @StructMember(4) public native int getSubFrameSampleOffset();
    @StructMember(4) public native CAFSMPTETime setSubFrameSampleOffset(int subFrameSampleOffset);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
