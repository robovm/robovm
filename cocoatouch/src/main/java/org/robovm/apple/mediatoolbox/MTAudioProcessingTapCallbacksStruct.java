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
package org.robovm.apple.mediatoolbox;

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
import org.robovm.apple.coremedia.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*//*</visibility>*/ class /*<name>*/MTAudioProcessingTapCallbacksStruct/*</name>*/ 
    extends /*<extends>*/Struct<MTAudioProcessingTapCallbacksStruct>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MTAudioProcessingTapCallbacksStructPtr extends Ptr<MTAudioProcessingTapCallbacksStruct, MTAudioProcessingTapCallbacksStructPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MTAudioProcessingTapCallbacksStruct() {}
    public MTAudioProcessingTapCallbacksStruct(int version, VoidPtr clientInfo, FunctionPtr init, FunctionPtr finalizeCbk, FunctionPtr prepare, FunctionPtr unprepare, FunctionPtr process) {
        this.setVersion(version);
        this.setClientInfo(clientInfo);
        this.setInit(init);
        this.setFinalizeCbk(finalizeCbk);
        this.setPrepare(prepare);
        this.setUnprepare(unprepare);
        this.setProcess(process);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getVersion();
    @StructMember(0) public native MTAudioProcessingTapCallbacksStruct setVersion(int version);
    @StructMember(1) public native VoidPtr getClientInfo();
    @StructMember(1) public native MTAudioProcessingTapCallbacksStruct setClientInfo(VoidPtr clientInfo);
    @StructMember(2) public native FunctionPtr getInit();
    @StructMember(2) public native MTAudioProcessingTapCallbacksStruct setInit(FunctionPtr init);
    @StructMember(3) public native FunctionPtr getFinalizeCbk();
    @StructMember(3) public native MTAudioProcessingTapCallbacksStruct setFinalizeCbk(FunctionPtr finalizeCbk);
    @StructMember(4) public native FunctionPtr getPrepare();
    @StructMember(4) public native MTAudioProcessingTapCallbacksStruct setPrepare(FunctionPtr prepare);
    @StructMember(5) public native FunctionPtr getUnprepare();
    @StructMember(5) public native MTAudioProcessingTapCallbacksStruct setUnprepare(FunctionPtr unprepare);
    @StructMember(6) public native FunctionPtr getProcess();
    @StructMember(6) public native MTAudioProcessingTapCallbacksStruct setProcess(FunctionPtr process);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
