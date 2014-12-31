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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*//*</visibility>*/ class /*<name>*/CMBufferCallbacksStruct/*</name>*/ 
    extends /*<extends>*/Struct<CMBufferCallbacksStruct>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CMBufferCallbacksStructPtr extends Ptr<CMBufferCallbacksStruct, CMBufferCallbacksStructPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CMBufferCallbacksStruct() {}
    public CMBufferCallbacksStruct(int version, @Pointer long refcon, FunctionPtr getDecodeTimeStamp, FunctionPtr getPresentationTimeStamp, FunctionPtr getDuration, FunctionPtr isDataReady, FunctionPtr compare, String dataBecameReadyNotification, FunctionPtr getSize) {
        this.setVersion(version);
        this.setRefcon(refcon);
        this.setGetdecodetimestamp(getDecodeTimeStamp);
        this.setGetpresentationtimestamp(getPresentationTimeStamp);
        this.setGetduration(getDuration);
        this.setIsdataready(isDataReady);
        this.setCompare(compare);
        this.setDatabecamereadynotification(dataBecameReadyNotification);
        this.setGetsize(getSize);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getVersion();
    @StructMember(0) public native CMBufferCallbacksStruct setVersion(int version);
    
    @Deprecated
    @StructMember(0) public native int version();
    @Deprecated
    @StructMember(0) public native CMBufferCallbacksStruct version(int version);
    
    @StructMember(1) public native @Pointer long getRefcon();
    @StructMember(1) public native CMBufferCallbacksStruct setRefcon(@Pointer long refcon);
    
    @Deprecated
    @StructMember(1) public native @Pointer long refcon();
    @Deprecated
    @StructMember(1) public native CMBufferCallbacksStruct refcon(@Pointer long refcon);
    
    @StructMember(2) public native FunctionPtr getGetdecodetimestamp();
    @StructMember(2) public native CMBufferCallbacksStruct setGetdecodetimestamp(FunctionPtr getDecodeTimeStamp);
    
    @Deprecated
    @StructMember(2) public native FunctionPtr getDecodeTimeStamp();
    @Deprecated
    @StructMember(2) public native CMBufferCallbacksStruct getDecodeTimeStamp(FunctionPtr getDecodeTimeStamp);
    
    @StructMember(3) public native FunctionPtr getGetpresentationtimestamp();
    @StructMember(3) public native CMBufferCallbacksStruct setGetpresentationtimestamp(FunctionPtr getPresentationTimeStamp);
    
    @Deprecated
    @StructMember(3) public native FunctionPtr getPresentationTimeStamp();
    @Deprecated
    @StructMember(3) public native CMBufferCallbacksStruct getPresentationTimeStamp(FunctionPtr getPresentationTimeStamp);
    
    @StructMember(4) public native FunctionPtr getGetduration();
    @StructMember(4) public native CMBufferCallbacksStruct setGetduration(FunctionPtr getDuration);
    
    @Deprecated
    @StructMember(4) public native FunctionPtr getDuration();
    @Deprecated
    @StructMember(4) public native CMBufferCallbacksStruct getDuration(FunctionPtr getDuration);
    
    @StructMember(5) public native FunctionPtr getIsdataready();
    @StructMember(5) public native CMBufferCallbacksStruct setIsdataready(FunctionPtr isDataReady);
    
    @Deprecated
    @StructMember(5) public native FunctionPtr isDataReady();
    @Deprecated
    @StructMember(5) public native CMBufferCallbacksStruct isDataReady(FunctionPtr isDataReady);
    
    @StructMember(6) public native FunctionPtr getCompare();
    @StructMember(6) public native CMBufferCallbacksStruct setCompare(FunctionPtr compare);
    
    @Deprecated
    @StructMember(6) public native FunctionPtr compare();
    @Deprecated
    @StructMember(6) public native CMBufferCallbacksStruct compare(FunctionPtr compare);
    
    @StructMember(7) public native String getDatabecamereadynotification();
    @StructMember(7) public native CMBufferCallbacksStruct setDatabecamereadynotification(String dataBecameReadyNotification);
    
    @Deprecated
    @StructMember(7) public native String dataBecameReadyNotification();
    @Deprecated
    @StructMember(7) public native CMBufferCallbacksStruct dataBecameReadyNotification(String dataBecameReadyNotification);
    
    @StructMember(8) public native FunctionPtr getGetsize();
    @StructMember(8) public native CMBufferCallbacksStruct setGetsize(FunctionPtr getSize);
    
    @Deprecated
    @StructMember(8) public native FunctionPtr getSize();
    @Deprecated
    @StructMember(8) public native CMBufferCallbacksStruct getSize(FunctionPtr getSize);
    
    /*</members>*/
    /*<methods>*//*</methods>*/
}
