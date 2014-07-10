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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SMPTETime/*</name>*/ 
    extends /*<extends>*/Struct<SMPTETime>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SMPTETimePtr extends Ptr<SMPTETime, SMPTETimePtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SMPTETime() {}
    public SMPTETime(short mSubframes, short mSubframeDivisor, int mCounter, int mType, int mFlags, short mHours, short mMinutes, short mSeconds, short mFrames) {
        this.mSubframes(mSubframes);
        this.mSubframeDivisor(mSubframeDivisor);
        this.mCounter(mCounter);
        this.mType(mType);
        this.mFlags(mFlags);
        this.mHours(mHours);
        this.mMinutes(mMinutes);
        this.mSeconds(mSeconds);
        this.mFrames(mFrames);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native short mSubframes();
    @StructMember(0) public native SMPTETime mSubframes(short mSubframes);
    @StructMember(1) public native short mSubframeDivisor();
    @StructMember(1) public native SMPTETime mSubframeDivisor(short mSubframeDivisor);
    @StructMember(2) public native int mCounter();
    @StructMember(2) public native SMPTETime mCounter(int mCounter);
    @StructMember(3) public native int mType();
    @StructMember(3) public native SMPTETime mType(int mType);
    @StructMember(4) public native int mFlags();
    @StructMember(4) public native SMPTETime mFlags(int mFlags);
    @StructMember(5) public native short mHours();
    @StructMember(5) public native SMPTETime mHours(short mHours);
    @StructMember(6) public native short mMinutes();
    @StructMember(6) public native SMPTETime mMinutes(short mMinutes);
    @StructMember(7) public native short mSeconds();
    @StructMember(7) public native SMPTETime mSeconds(short mSeconds);
    @StructMember(8) public native short mFrames();
    @StructMember(8) public native SMPTETime mFrames(short mFrames);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
