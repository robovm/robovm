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
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CVTimeStamp() {}
    public CVTimeStamp(int version, int videoTimeScale, long videoTime, long hostTime, double rateScalar, long videoRefreshPeriod, CVSMPTETime smpteTime, long flags, long reserved) {
        this.setVersion(version);
        this.setVideotimescale(videoTimeScale);
        this.setVideotime(videoTime);
        this.setHosttime(hostTime);
        this.setRatescalar(rateScalar);
        this.setVideorefreshperiod(videoRefreshPeriod);
        this.setSmptetime(smpteTime);
        this.setFlags(flags);
        this.setReserved(reserved);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getVersion();
    @StructMember(0) public native CVTimeStamp setVersion(int version);
    
    @Deprecated
    @StructMember(0) public native int version();
    @Deprecated
    @StructMember(0) public native CVTimeStamp version(int version);
    
    @StructMember(1) public native int getVideotimescale();
    @StructMember(1) public native CVTimeStamp setVideotimescale(int videoTimeScale);
    
    @Deprecated
    @StructMember(1) public native int videoTimeScale();
    @Deprecated
    @StructMember(1) public native CVTimeStamp videoTimeScale(int videoTimeScale);
    
    @StructMember(2) public native long getVideotime();
    @StructMember(2) public native CVTimeStamp setVideotime(long videoTime);
    
    @Deprecated
    @StructMember(2) public native long videoTime();
    @Deprecated
    @StructMember(2) public native CVTimeStamp videoTime(long videoTime);
    
    @StructMember(3) public native long getHosttime();
    @StructMember(3) public native CVTimeStamp setHosttime(long hostTime);
    
    @Deprecated
    @StructMember(3) public native long hostTime();
    @Deprecated
    @StructMember(3) public native CVTimeStamp hostTime(long hostTime);
    
    @StructMember(4) public native double getRatescalar();
    @StructMember(4) public native CVTimeStamp setRatescalar(double rateScalar);
    
    @Deprecated
    @StructMember(4) public native double rateScalar();
    @Deprecated
    @StructMember(4) public native CVTimeStamp rateScalar(double rateScalar);
    
    @StructMember(5) public native long getVideorefreshperiod();
    @StructMember(5) public native CVTimeStamp setVideorefreshperiod(long videoRefreshPeriod);
    
    @Deprecated
    @StructMember(5) public native long videoRefreshPeriod();
    @Deprecated
    @StructMember(5) public native CVTimeStamp videoRefreshPeriod(long videoRefreshPeriod);
    
    @StructMember(6) public native @ByVal CVSMPTETime getSmptetime();
    @StructMember(6) public native CVTimeStamp setSmptetime(@ByVal CVSMPTETime smpteTime);
    
    @Deprecated
    @StructMember(6) public native @ByVal CVSMPTETime smpteTime();
    @Deprecated
    @StructMember(6) public native CVTimeStamp smpteTime(@ByVal CVSMPTETime smpteTime);
    
    @StructMember(7) public native long getFlags();
    @StructMember(7) public native CVTimeStamp setFlags(long flags);
    
    @Deprecated
    @StructMember(7) public native long flags();
    @Deprecated
    @StructMember(7) public native CVTimeStamp flags(long flags);
    
    @StructMember(8) public native long getReserved();
    @StructMember(8) public native CVTimeStamp setReserved(long reserved);
    
    @Deprecated
    @StructMember(8) public native long reserved();
    @Deprecated
    @StructMember(8) public native CVTimeStamp reserved(long reserved);
    
    /*</members>*/
    /*<methods>*//*</methods>*/
}
