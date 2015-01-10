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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAFSMPTETime/*</name>*/ 
    extends /*<extends>*/Struct<CAFSMPTETime>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CAFSMPTETimePtr extends Ptr<CAFSMPTETime, CAFSMPTETimePtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CAFSMPTETime() {}
    public CAFSMPTETime(byte mHours, byte mMinutes, byte mSeconds, byte mFrames, int mSubFrameSampleOffset) {
        this.setMHours(mHours);
        this.setMMinutes(mMinutes);
        this.setMSeconds(mSeconds);
        this.setMFrames(mFrames);
        this.setMSubFrameSampleOffset(mSubFrameSampleOffset);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native byte getMHours();
    @StructMember(0) public native CAFSMPTETime setMHours(byte mHours);
    @StructMember(1) public native byte getMMinutes();
    @StructMember(1) public native CAFSMPTETime setMMinutes(byte mMinutes);
    @StructMember(2) public native byte getMSeconds();
    @StructMember(2) public native CAFSMPTETime setMSeconds(byte mSeconds);
    @StructMember(3) public native byte getMFrames();
    @StructMember(3) public native CAFSMPTETime setMFrames(byte mFrames);
    @StructMember(4) public native int getMSubFrameSampleOffset();
    @StructMember(4) public native CAFSMPTETime setMSubFrameSampleOffset(int mSubFrameSampleOffset);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
