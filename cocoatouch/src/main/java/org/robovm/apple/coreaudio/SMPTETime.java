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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SMPTETime/*</name>*/ 
    extends /*<extends>*/Struct<SMPTETime>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SMPTETimePtr extends Ptr<SMPTETime, SMPTETimePtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SMPTETime() {}
    public SMPTETime(short mSubframes, short mSubframeDivisor, int mCounter, SMPTETimeType mType, SMPTEStateFlags mFlags, short mHours, short mMinutes, short mSeconds, short mFrames) {
        this.setMSubframes(mSubframes);
        this.setMSubframeDivisor(mSubframeDivisor);
        this.setMCounter(mCounter);
        this.setMType(mType);
        this.setMFlags(mFlags);
        this.setMHours(mHours);
        this.setMMinutes(mMinutes);
        this.setMSeconds(mSeconds);
        this.setMFrames(mFrames);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native short getMSubframes();
    @StructMember(0) public native SMPTETime setMSubframes(short mSubframes);
    @StructMember(1) public native short getMSubframeDivisor();
    @StructMember(1) public native SMPTETime setMSubframeDivisor(short mSubframeDivisor);
    @StructMember(2) public native int getMCounter();
    @StructMember(2) public native SMPTETime setMCounter(int mCounter);
    @StructMember(3) public native SMPTETimeType getMType();
    @StructMember(3) public native SMPTETime setMType(SMPTETimeType mType);
    @StructMember(4) public native SMPTEStateFlags getMFlags();
    @StructMember(4) public native SMPTETime setMFlags(SMPTEStateFlags mFlags);
    @StructMember(5) public native short getMHours();
    @StructMember(5) public native SMPTETime setMHours(short mHours);
    @StructMember(6) public native short getMMinutes();
    @StructMember(6) public native SMPTETime setMMinutes(short mMinutes);
    @StructMember(7) public native short getMSeconds();
    @StructMember(7) public native SMPTETime setMSeconds(short mSeconds);
    @StructMember(8) public native short getMFrames();
    @StructMember(8) public native SMPTETime setMFrames(short mFrames);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
