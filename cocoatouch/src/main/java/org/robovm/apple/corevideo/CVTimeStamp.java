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
        this.version(version);
        this.videoTimeScale(videoTimeScale);
        this.videoTime(videoTime);
        this.hostTime(hostTime);
        this.rateScalar(rateScalar);
        this.videoRefreshPeriod(videoRefreshPeriod);
        this.smpteTime(smpteTime);
        this.flags(flags);
        this.reserved(reserved);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int version();
    @StructMember(0) public native CVTimeStamp version(int version);
    @StructMember(1) public native int videoTimeScale();
    @StructMember(1) public native CVTimeStamp videoTimeScale(int videoTimeScale);
    @StructMember(2) public native long videoTime();
    @StructMember(2) public native CVTimeStamp videoTime(long videoTime);
    @StructMember(3) public native long hostTime();
    @StructMember(3) public native CVTimeStamp hostTime(long hostTime);
    @StructMember(4) public native double rateScalar();
    @StructMember(4) public native CVTimeStamp rateScalar(double rateScalar);
    @StructMember(5) public native long videoRefreshPeriod();
    @StructMember(5) public native CVTimeStamp videoRefreshPeriod(long videoRefreshPeriod);
    @StructMember(6) public native @ByVal CVSMPTETime smpteTime();
    @StructMember(6) public native CVTimeStamp smpteTime(@ByVal CVSMPTETime smpteTime);
    @StructMember(7) public native long flags();
    @StructMember(7) public native CVTimeStamp flags(long flags);
    @StructMember(8) public native long reserved();
    @StructMember(8) public native CVTimeStamp reserved(long reserved);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
