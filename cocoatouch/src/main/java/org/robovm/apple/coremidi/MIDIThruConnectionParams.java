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
package org.robovm.apple.coremidi;

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
/*<annotations>*/@Library("CoreMIDI")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MIDIThruConnectionParams/*</name>*/ 
    extends /*<extends>*/Struct<MIDIThruConnectionParams>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MIDIThruConnectionParamsPtr extends Ptr<MIDIThruConnectionParams, MIDIThruConnectionParamsPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(MIDIThruConnectionParams.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MIDIThruConnectionParams() {}
    public MIDIThruConnectionParams(int version, int numSources, MIDIThruConnectionEndpoint sources, int numDestinations, MIDIThruConnectionEndpoint destinations, ByteBuffer channelMap, byte lowVelocity, byte highVelocity, byte lowNote, byte highNote, MIDITransform noteNumber, MIDITransform velocity, MIDITransform keyPressure, MIDITransform channelPressure, MIDITransform programChange, MIDITransform pitchBend, byte filterOutSysEx, byte filterOutMTC, byte filterOutBeatClock, byte filterOutTuneRequest, ByteBuffer reserved2, byte filterOutAllControls, short numControlTransforms, short numMaps, ShortBuffer reserved3) {
        this.version(version);
        this.numSources(numSources);
        this.sources(sources);
        this.numDestinations(numDestinations);
        this.destinations(destinations);
        this.channelMap(channelMap);
        this.lowVelocity(lowVelocity);
        this.highVelocity(highVelocity);
        this.lowNote(lowNote);
        this.highNote(highNote);
        this.noteNumber(noteNumber);
        this.velocity(velocity);
        this.keyPressure(keyPressure);
        this.channelPressure(channelPressure);
        this.programChange(programChange);
        this.pitchBend(pitchBend);
        this.filterOutSysEx(filterOutSysEx);
        this.filterOutMTC(filterOutMTC);
        this.filterOutBeatClock(filterOutBeatClock);
        this.filterOutTuneRequest(filterOutTuneRequest);
        this.reserved2(reserved2);
        this.filterOutAllControls(filterOutAllControls);
        this.numControlTransforms(numControlTransforms);
        this.numMaps(numMaps);
        this.reserved3(reserved3);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int version();
    @StructMember(0) public native MIDIThruConnectionParams version(int version);
    @StructMember(1) public native int numSources();
    @StructMember(1) public native MIDIThruConnectionParams numSources(int numSources);
    @StructMember(2) public native @Array({8}) MIDIThruConnectionEndpoint sources();
    @StructMember(2) public native MIDIThruConnectionParams sources(@Array({8}) MIDIThruConnectionEndpoint sources);
    @StructMember(3) public native int numDestinations();
    @StructMember(3) public native MIDIThruConnectionParams numDestinations(int numDestinations);
    @StructMember(4) public native @Array({8}) MIDIThruConnectionEndpoint destinations();
    @StructMember(4) public native MIDIThruConnectionParams destinations(@Array({8}) MIDIThruConnectionEndpoint destinations);
    @StructMember(5) public native @Array({16}) ByteBuffer channelMap();
    @StructMember(5) public native MIDIThruConnectionParams channelMap(@Array({16}) ByteBuffer channelMap);
    @StructMember(6) public native byte lowVelocity();
    @StructMember(6) public native MIDIThruConnectionParams lowVelocity(byte lowVelocity);
    @StructMember(7) public native byte highVelocity();
    @StructMember(7) public native MIDIThruConnectionParams highVelocity(byte highVelocity);
    @StructMember(8) public native byte lowNote();
    @StructMember(8) public native MIDIThruConnectionParams lowNote(byte lowNote);
    @StructMember(9) public native byte highNote();
    @StructMember(9) public native MIDIThruConnectionParams highNote(byte highNote);
    @StructMember(10) public native @ByVal MIDITransform noteNumber();
    @StructMember(10) public native MIDIThruConnectionParams noteNumber(@ByVal MIDITransform noteNumber);
    @StructMember(11) public native @ByVal MIDITransform velocity();
    @StructMember(11) public native MIDIThruConnectionParams velocity(@ByVal MIDITransform velocity);
    @StructMember(12) public native @ByVal MIDITransform keyPressure();
    @StructMember(12) public native MIDIThruConnectionParams keyPressure(@ByVal MIDITransform keyPressure);
    @StructMember(13) public native @ByVal MIDITransform channelPressure();
    @StructMember(13) public native MIDIThruConnectionParams channelPressure(@ByVal MIDITransform channelPressure);
    @StructMember(14) public native @ByVal MIDITransform programChange();
    @StructMember(14) public native MIDIThruConnectionParams programChange(@ByVal MIDITransform programChange);
    @StructMember(15) public native @ByVal MIDITransform pitchBend();
    @StructMember(15) public native MIDIThruConnectionParams pitchBend(@ByVal MIDITransform pitchBend);
    @StructMember(16) public native byte filterOutSysEx();
    @StructMember(16) public native MIDIThruConnectionParams filterOutSysEx(byte filterOutSysEx);
    @StructMember(17) public native byte filterOutMTC();
    @StructMember(17) public native MIDIThruConnectionParams filterOutMTC(byte filterOutMTC);
    @StructMember(18) public native byte filterOutBeatClock();
    @StructMember(18) public native MIDIThruConnectionParams filterOutBeatClock(byte filterOutBeatClock);
    @StructMember(19) public native byte filterOutTuneRequest();
    @StructMember(19) public native MIDIThruConnectionParams filterOutTuneRequest(byte filterOutTuneRequest);
    @StructMember(20) public native @Array({3}) ByteBuffer reserved2();
    @StructMember(20) public native MIDIThruConnectionParams reserved2(@Array({3}) ByteBuffer reserved2);
    @StructMember(21) public native byte filterOutAllControls();
    @StructMember(21) public native MIDIThruConnectionParams filterOutAllControls(byte filterOutAllControls);
    @StructMember(22) public native short numControlTransforms();
    @StructMember(22) public native MIDIThruConnectionParams numControlTransforms(short numControlTransforms);
    @StructMember(23) public native short numMaps();
    @StructMember(23) public native MIDIThruConnectionParams numMaps(short numMaps);
    @StructMember(24) public native @Array({4}) ShortBuffer reserved3();
    @StructMember(24) public native MIDIThruConnectionParams reserved3(@Array({4}) ShortBuffer reserved3);
    /*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIThruConnectionParamsInitialize", optional=true)
    public native void initialize();
    /*</methods>*/
}
