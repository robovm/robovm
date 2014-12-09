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
    public SMPTETime(short mSubframes, short mSubframeDivisor, int mCounter, SMPTETimeType mType, SMPTEStateFlags mFlags, short mHours, short mMinutes, short mSeconds, short mFrames) {
        this.setMsubframes(mSubframes);
        this.setMsubframedivisor(mSubframeDivisor);
        this.setMcounter(mCounter);
        this.setMtype(mType);
        this.setMflags(mFlags);
        this.setMhours(mHours);
        this.setMminutes(mMinutes);
        this.setMseconds(mSeconds);
        this.setMframes(mFrames);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native short getMsubframes();
    @StructMember(0) public native SMPTETime setMsubframes(short mSubframes);
    
    @Deprecated
    @StructMember(0) public native short mSubframes();
    @Deprecated
    @StructMember(0) public native SMPTETime mSubframes(short mSubframes);
    
    @StructMember(1) public native short getMsubframedivisor();
    @StructMember(1) public native SMPTETime setMsubframedivisor(short mSubframeDivisor);
    
    @Deprecated
    @StructMember(1) public native short mSubframeDivisor();
    @Deprecated
    @StructMember(1) public native SMPTETime mSubframeDivisor(short mSubframeDivisor);
    
    @StructMember(2) public native int getMcounter();
    @StructMember(2) public native SMPTETime setMcounter(int mCounter);
    
    @Deprecated
    @StructMember(2) public native int mCounter();
    @Deprecated
    @StructMember(2) public native SMPTETime mCounter(int mCounter);
    
    @StructMember(3) public native SMPTETimeType getMtype();
    @StructMember(3) public native SMPTETime setMtype(SMPTETimeType mType);
    
    @Deprecated
    @StructMember(3) public native SMPTETimeType mType();
    @Deprecated
    @StructMember(3) public native SMPTETime mType(SMPTETimeType mType);
    
    @StructMember(4) public native SMPTEStateFlags getMflags();
    @StructMember(4) public native SMPTETime setMflags(SMPTEStateFlags mFlags);
    
    @Deprecated
    @StructMember(4) public native SMPTEStateFlags mFlags();
    @Deprecated
    @StructMember(4) public native SMPTETime mFlags(SMPTEStateFlags mFlags);
    
    @StructMember(5) public native short getMhours();
    @StructMember(5) public native SMPTETime setMhours(short mHours);
    
    @Deprecated
    @StructMember(5) public native short mHours();
    @Deprecated
    @StructMember(5) public native SMPTETime mHours(short mHours);
    
    @StructMember(6) public native short getMminutes();
    @StructMember(6) public native SMPTETime setMminutes(short mMinutes);
    
    @Deprecated
    @StructMember(6) public native short mMinutes();
    @Deprecated
    @StructMember(6) public native SMPTETime mMinutes(short mMinutes);
    
    @StructMember(7) public native short getMseconds();
    @StructMember(7) public native SMPTETime setMseconds(short mSeconds);
    
    @Deprecated
    @StructMember(7) public native short mSeconds();
    @Deprecated
    @StructMember(7) public native SMPTETime mSeconds(short mSeconds);
    
    @StructMember(8) public native short getMframes();
    @StructMember(8) public native SMPTETime setMframes(short mFrames);
    
    @Deprecated
    @StructMember(8) public native short mFrames();
    @Deprecated
    @StructMember(8) public native SMPTETime mFrames(short mFrames);
    
    /*</members>*/
    /*<methods>*//*</methods>*/
}
