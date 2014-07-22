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
        this.version(version);
        this.clientInfo(clientInfo);
        this.init(init);
        this.finalizeCbk(finalizeCbk);
        this.prepare(prepare);
        this.unprepare(unprepare);
        this.process(process);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int version();
    @StructMember(0) public native MTAudioProcessingTapCallbacksStruct version(int version);
    @StructMember(1) public native VoidPtr clientInfo();
    @StructMember(1) public native MTAudioProcessingTapCallbacksStruct clientInfo(VoidPtr clientInfo);
    @StructMember(2) public native FunctionPtr init();
    @StructMember(2) public native MTAudioProcessingTapCallbacksStruct init(FunctionPtr init);
    @StructMember(3) public native FunctionPtr finalizeCbk();
    @StructMember(3) public native MTAudioProcessingTapCallbacksStruct finalizeCbk(FunctionPtr finalizeCbk);
    @StructMember(4) public native FunctionPtr prepare();
    @StructMember(4) public native MTAudioProcessingTapCallbacksStruct prepare(FunctionPtr prepare);
    @StructMember(5) public native FunctionPtr unprepare();
    @StructMember(5) public native MTAudioProcessingTapCallbacksStruct unprepare(FunctionPtr unprepare);
    @StructMember(6) public native FunctionPtr process();
    @StructMember(6) public native MTAudioProcessingTapCallbacksStruct process(FunctionPtr process);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
