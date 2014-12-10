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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVSMPTETime/*</name>*/ 
    extends /*<extends>*/Struct<CVSMPTETime>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CVSMPTETimePtr extends Ptr<CVSMPTETime, CVSMPTETimePtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CVSMPTETime() {}
    public CVSMPTETime(short subframes, short subframeDivisor, int counter, int type, int flags, short hours, short minutes, short seconds, short frames) {
        this.setSubframes(subframes);
        this.setSubframedivisor(subframeDivisor);
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
    
    @Deprecated
    @StructMember(0) public native short subframes();
    @Deprecated
    @StructMember(0) public native CVSMPTETime subframes(short subframes);
    
    @StructMember(1) public native short getSubframedivisor();
    @StructMember(1) public native CVSMPTETime setSubframedivisor(short subframeDivisor);
    
    @Deprecated
    @StructMember(1) public native short subframeDivisor();
    @Deprecated
    @StructMember(1) public native CVSMPTETime subframeDivisor(short subframeDivisor);
    
    @StructMember(2) public native int getCounter();
    @StructMember(2) public native CVSMPTETime setCounter(int counter);
    
    @Deprecated
    @StructMember(2) public native int counter();
    @Deprecated
    @StructMember(2) public native CVSMPTETime counter(int counter);
    
    @StructMember(3) public native int getType();
    @StructMember(3) public native CVSMPTETime setType(int type);
    
    @Deprecated
    @StructMember(3) public native int type();
    @Deprecated
    @StructMember(3) public native CVSMPTETime type(int type);
    
    @StructMember(4) public native int getFlags();
    @StructMember(4) public native CVSMPTETime setFlags(int flags);
    
    @Deprecated
    @StructMember(4) public native int flags();
    @Deprecated
    @StructMember(4) public native CVSMPTETime flags(int flags);
    
    @StructMember(5) public native short getHours();
    @StructMember(5) public native CVSMPTETime setHours(short hours);
    
    @Deprecated
    @StructMember(5) public native short hours();
    @Deprecated
    @StructMember(5) public native CVSMPTETime hours(short hours);
    
    @StructMember(6) public native short getMinutes();
    @StructMember(6) public native CVSMPTETime setMinutes(short minutes);
    
    @Deprecated
    @StructMember(6) public native short minutes();
    @Deprecated
    @StructMember(6) public native CVSMPTETime minutes(short minutes);
    
    @StructMember(7) public native short getSeconds();
    @StructMember(7) public native CVSMPTETime setSeconds(short seconds);
    
    @Deprecated
    @StructMember(7) public native short seconds();
    @Deprecated
    @StructMember(7) public native CVSMPTETime seconds(short seconds);
    
    @StructMember(8) public native short getFrames();
    @StructMember(8) public native CVSMPTETime setFrames(short frames);
    
    @Deprecated
    @StructMember(8) public native short frames();
    @Deprecated
    @StructMember(8) public native CVSMPTETime frames(short frames);
    
    /*</members>*/
    /*<methods>*//*</methods>*/
}
