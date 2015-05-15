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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVSMPTETime/*</name>*/ 
    extends /*<extends>*/Struct<CVSMPTETime>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CVSMPTETimePtr extends Ptr<CVSMPTETime, CVSMPTETimePtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CVSMPTETime() {}
    public CVSMPTETime(short subframes, short subframeDivisor, int counter, CVSMPTETimeType type, CVSMPTETimeFlags flags, short hours, short minutes, short seconds, short frames) {
        this.setSubframes(subframes);
        this.setSubframeDivisor(subframeDivisor);
        this.setCounter(counter);
        this.setType(type);
        this.setFlags(flags);
        this.setHours(hours);
        this.setMinutes(minutes);
        this.setSeconds(seconds);
        this.setFrames(frames);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native short getSubframes();
    @StructMember(0) public native CVSMPTETime setSubframes(short subframes);
    @StructMember(1) public native short getSubframeDivisor();
    @StructMember(1) public native CVSMPTETime setSubframeDivisor(short subframeDivisor);
    @StructMember(2) public native int getCounter();
    @StructMember(2) public native CVSMPTETime setCounter(int counter);
    @StructMember(3) public native CVSMPTETimeType getType();
    @StructMember(3) public native CVSMPTETime setType(CVSMPTETimeType type);
    @StructMember(4) public native CVSMPTETimeFlags getFlags();
    @StructMember(4) public native CVSMPTETime setFlags(CVSMPTETimeFlags flags);
    @StructMember(5) public native short getHours();
    @StructMember(5) public native CVSMPTETime setHours(short hours);
    @StructMember(6) public native short getMinutes();
    @StructMember(6) public native CVSMPTETime setMinutes(short minutes);
    @StructMember(7) public native short getSeconds();
    @StructMember(7) public native CVSMPTETime setSeconds(short seconds);
    @StructMember(8) public native short getFrames();
    @StructMember(8) public native CVSMPTETime setFrames(short frames);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
