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
package org.robovm.apple.corevideo;

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
import org.robovm.apple.coremedia.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVTimeStamp/*</name>*/ 
    extends /*<extends>*/Struct<CVTimeStamp>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CVTimeStampPtr extends Ptr<CVTimeStamp, CVTimeStampPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*/
    public static final int VideoHostTimeValid = 3;
    public static final int IsInterlaced = 196608;
    /*</constants>*/
    /*<constructors>*/
    public CVTimeStamp() {}
    public CVTimeStamp(int version, int videoTimeScale, long videoTime, long hostTime, double rateScalar, long videoRefreshPeriod, CVSMPTETime smpteTime, CVTimeStampFlags flags, long reserved) {
        this.setVersion(version);
        this.setVideoTimeScale(videoTimeScale);
        this.setVideoTime(videoTime);
        this.setHostTime(hostTime);
        this.setRateScalar(rateScalar);
        this.setVideoRefreshPeriod(videoRefreshPeriod);
        this.setSmpteTime(smpteTime);
        this.setFlags(flags);
        this.setReserved(reserved);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getVersion();
    @StructMember(0) public native CVTimeStamp setVersion(int version);
    @StructMember(1) public native int getVideoTimeScale();
    @StructMember(1) public native CVTimeStamp setVideoTimeScale(int videoTimeScale);
    @StructMember(2) public native long getVideoTime();
    @StructMember(2) public native CVTimeStamp setVideoTime(long videoTime);
    @StructMember(3) public native long getHostTime();
    @StructMember(3) public native CVTimeStamp setHostTime(long hostTime);
    @StructMember(4) public native double getRateScalar();
    @StructMember(4) public native CVTimeStamp setRateScalar(double rateScalar);
    @StructMember(5) public native long getVideoRefreshPeriod();
    @StructMember(5) public native CVTimeStamp setVideoRefreshPeriod(long videoRefreshPeriod);
    @StructMember(6) public native @ByVal CVSMPTETime getSmpteTime();
    @StructMember(6) public native CVTimeStamp setSmpteTime(@ByVal CVSMPTETime smpteTime);
    @StructMember(7) public native CVTimeStampFlags getFlags();
    @StructMember(7) public native CVTimeStamp setFlags(CVTimeStampFlags flags);
    @StructMember(8) public native long getReserved();
    @StructMember(8) public native CVTimeStamp setReserved(long reserved);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
