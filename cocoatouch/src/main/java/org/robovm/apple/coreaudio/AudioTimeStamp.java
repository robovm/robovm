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
package org.robovm.apple.coreaudio;

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
    public AudioTimeStamp(double sampleTime, long hostTime, double rateScalar, long wordClockTime, SMPTETime smpteTime, AudioTimeStampFlags flags) {
        this.setSampleTime(sampleTime);
        this.setHostTime(hostTime);
        this.setRateScalar(rateScalar);
        this.setWordClockTime(wordClockTime);
        this.setSmpteTime(smpteTime);
        this.setFlags(flags);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native double getSampleTime();
    @StructMember(0) public native AudioTimeStamp setSampleTime(double sampleTime);
    @StructMember(1) public native long getHostTime();
    @StructMember(1) public native AudioTimeStamp setHostTime(long hostTime);
    @StructMember(2) public native double getRateScalar();
    @StructMember(2) public native AudioTimeStamp setRateScalar(double rateScalar);
    @StructMember(3) public native long getWordClockTime();
    @StructMember(3) public native AudioTimeStamp setWordClockTime(long wordClockTime);
    @StructMember(4) public native @ByVal SMPTETime getSmpteTime();
    @StructMember(4) public native AudioTimeStamp setSmpteTime(@ByVal SMPTETime smpteTime);
    @StructMember(5) public native AudioTimeStampFlags getFlags();
    @StructMember(5) public native AudioTimeStamp setFlags(AudioTimeStampFlags flags);
    @StructMember(6) private native int getReserved();
    @StructMember(6) private native AudioTimeStamp setReserved(int reserved);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
