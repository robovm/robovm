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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AudioTimeStamp/*</name>*/ 
    extends /*<extends>*/Struct<AudioTimeStamp>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AudioTimeStampPtr extends Ptr<AudioTimeStamp, AudioTimeStampPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AudioTimeStamp() {}
    public AudioTimeStamp(double mSampleTime, long mHostTime, double mRateScalar, long mWordClockTime, SMPTETime mSMPTETime, int mFlags, int mReserved) {
        this.setMSampleTime(mSampleTime);
        this.setMHostTime(mHostTime);
        this.setMRateScalar(mRateScalar);
        this.setMWordClockTime(mWordClockTime);
        this.setMSMPTETime(mSMPTETime);
        this.setMFlags(mFlags);
        this.setMReserved(mReserved);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native double getMSampleTime();
    @StructMember(0) public native AudioTimeStamp setMSampleTime(double mSampleTime);
    @StructMember(1) public native long getMHostTime();
    @StructMember(1) public native AudioTimeStamp setMHostTime(long mHostTime);
    @StructMember(2) public native double getMRateScalar();
    @StructMember(2) public native AudioTimeStamp setMRateScalar(double mRateScalar);
    @StructMember(3) public native long getMWordClockTime();
    @StructMember(3) public native AudioTimeStamp setMWordClockTime(long mWordClockTime);
    @StructMember(4) public native @ByVal SMPTETime getMSMPTETime();
    @StructMember(4) public native AudioTimeStamp setMSMPTETime(@ByVal SMPTETime mSMPTETime);
    @StructMember(5) public native int getMFlags();
    @StructMember(5) public native AudioTimeStamp setMFlags(int mFlags);
    @StructMember(6) public native int getMReserved();
    @StructMember(6) public native AudioTimeStamp setMReserved(int mReserved);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
