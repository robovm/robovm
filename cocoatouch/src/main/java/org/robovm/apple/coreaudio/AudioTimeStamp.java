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
        this.setMsampletime(mSampleTime);
        this.setMhosttime(mHostTime);
        this.setMratescalar(mRateScalar);
        this.setMwordclocktime(mWordClockTime);
        this.setMsmptetime(mSMPTETime);
        this.setMflags(mFlags);
        this.setMreserved(mReserved);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native double getMsampletime();
    @StructMember(0) public native AudioTimeStamp setMsampletime(double mSampleTime);
    
    @Deprecated
    @StructMember(0) public native double mSampleTime();
    @Deprecated
    @StructMember(0) public native AudioTimeStamp mSampleTime(double mSampleTime);
    
    @StructMember(1) public native long getMhosttime();
    @StructMember(1) public native AudioTimeStamp setMhosttime(long mHostTime);
    
    @Deprecated
    @StructMember(1) public native long mHostTime();
    @Deprecated
    @StructMember(1) public native AudioTimeStamp mHostTime(long mHostTime);
    
    @StructMember(2) public native double getMratescalar();
    @StructMember(2) public native AudioTimeStamp setMratescalar(double mRateScalar);
    
    @Deprecated
    @StructMember(2) public native double mRateScalar();
    @Deprecated
    @StructMember(2) public native AudioTimeStamp mRateScalar(double mRateScalar);
    
    @StructMember(3) public native long getMwordclocktime();
    @StructMember(3) public native AudioTimeStamp setMwordclocktime(long mWordClockTime);
    
    @Deprecated
    @StructMember(3) public native long mWordClockTime();
    @Deprecated
    @StructMember(3) public native AudioTimeStamp mWordClockTime(long mWordClockTime);
    
    @StructMember(4) public native @ByVal SMPTETime getMsmptetime();
    @StructMember(4) public native AudioTimeStamp setMsmptetime(@ByVal SMPTETime mSMPTETime);
    
    @Deprecated
    @StructMember(4) public native @ByVal SMPTETime mSMPTETime();
    @Deprecated
    @StructMember(4) public native AudioTimeStamp mSMPTETime(@ByVal SMPTETime mSMPTETime);
    
    @StructMember(5) public native int getMflags();
    @StructMember(5) public native AudioTimeStamp setMflags(int mFlags);
    
    @Deprecated
    @StructMember(5) public native int mFlags();
    @Deprecated
    @StructMember(5) public native AudioTimeStamp mFlags(int mFlags);
    
    @StructMember(6) public native int getMreserved();
    @StructMember(6) public native AudioTimeStamp setMreserved(int mReserved);
    
    @Deprecated
    @StructMember(6) public native int mReserved();
    @Deprecated
    @StructMember(6) public native AudioTimeStamp mReserved(int mReserved);
    
    /*</members>*/
    /*<methods>*//*</methods>*/
}
