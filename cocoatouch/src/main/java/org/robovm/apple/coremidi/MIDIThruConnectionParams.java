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
        this.setVersion(version);
        this.setNumSources(numSources);
        this.setSources(sources);
        this.setNumDestinations(numDestinations);
        this.setDestinations(destinations);
        this.setChannelMap(channelMap);
        this.setLowVelocity(lowVelocity);
        this.setHighVelocity(highVelocity);
        this.setLowNote(lowNote);
        this.setHighNote(highNote);
        this.setNoteNumber(noteNumber);
        this.setVelocity(velocity);
        this.setKeyPressure(keyPressure);
        this.setChannelPressure(channelPressure);
        this.setProgramChange(programChange);
        this.setPitchBend(pitchBend);
        this.setFilterOutSysEx(filterOutSysEx);
        this.setFilterOutMTC(filterOutMTC);
        this.setFilterOutBeatClock(filterOutBeatClock);
        this.setFilterOutTuneRequest(filterOutTuneRequest);
        this.setReserved2(reserved2);
        this.setFilterOutAllControls(filterOutAllControls);
        this.setNumControlTransforms(numControlTransforms);
        this.setNumMaps(numMaps);
        this.setReserved3(reserved3);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native int getVersion();
    @StructMember(0) public native MIDIThruConnectionParams setVersion(int version);
    @StructMember(1) public native int getNumSources();
    @StructMember(1) public native MIDIThruConnectionParams setNumSources(int numSources);
    @StructMember(2) public native @Array({8}) MIDIThruConnectionEndpoint getSources();
    @StructMember(2) public native MIDIThruConnectionParams setSources(@Array({8}) MIDIThruConnectionEndpoint sources);
    @StructMember(3) public native int getNumDestinations();
    @StructMember(3) public native MIDIThruConnectionParams setNumDestinations(int numDestinations);
    @StructMember(4) public native @Array({8}) MIDIThruConnectionEndpoint getDestinations();
    @StructMember(4) public native MIDIThruConnectionParams setDestinations(@Array({8}) MIDIThruConnectionEndpoint destinations);
    @StructMember(5) public native @Array({16}) ByteBuffer getChannelMap();
    @StructMember(5) public native MIDIThruConnectionParams setChannelMap(@Array({16}) ByteBuffer channelMap);
    @StructMember(6) public native byte getLowVelocity();
    @StructMember(6) public native MIDIThruConnectionParams setLowVelocity(byte lowVelocity);
    @StructMember(7) public native byte getHighVelocity();
    @StructMember(7) public native MIDIThruConnectionParams setHighVelocity(byte highVelocity);
    @StructMember(8) public native byte getLowNote();
    @StructMember(8) public native MIDIThruConnectionParams setLowNote(byte lowNote);
    @StructMember(9) public native byte getHighNote();
    @StructMember(9) public native MIDIThruConnectionParams setHighNote(byte highNote);
    @StructMember(10) public native @ByVal MIDITransform getNoteNumber();
    @StructMember(10) public native MIDIThruConnectionParams setNoteNumber(@ByVal MIDITransform noteNumber);
    @StructMember(11) public native @ByVal MIDITransform getVelocity();
    @StructMember(11) public native MIDIThruConnectionParams setVelocity(@ByVal MIDITransform velocity);
    @StructMember(12) public native @ByVal MIDITransform getKeyPressure();
    @StructMember(12) public native MIDIThruConnectionParams setKeyPressure(@ByVal MIDITransform keyPressure);
    @StructMember(13) public native @ByVal MIDITransform getChannelPressure();
    @StructMember(13) public native MIDIThruConnectionParams setChannelPressure(@ByVal MIDITransform channelPressure);
    @StructMember(14) public native @ByVal MIDITransform getProgramChange();
    @StructMember(14) public native MIDIThruConnectionParams setProgramChange(@ByVal MIDITransform programChange);
    @StructMember(15) public native @ByVal MIDITransform getPitchBend();
    @StructMember(15) public native MIDIThruConnectionParams setPitchBend(@ByVal MIDITransform pitchBend);
    @StructMember(16) public native byte getFilterOutSysEx();
    @StructMember(16) public native MIDIThruConnectionParams setFilterOutSysEx(byte filterOutSysEx);
    @StructMember(17) public native byte getFilterOutMTC();
    @StructMember(17) public native MIDIThruConnectionParams setFilterOutMTC(byte filterOutMTC);
    @StructMember(18) public native byte getFilterOutBeatClock();
    @StructMember(18) public native MIDIThruConnectionParams setFilterOutBeatClock(byte filterOutBeatClock);
    @StructMember(19) public native byte getFilterOutTuneRequest();
    @StructMember(19) public native MIDIThruConnectionParams setFilterOutTuneRequest(byte filterOutTuneRequest);
    @StructMember(20) public native @Array({3}) ByteBuffer getReserved2();
    @StructMember(20) public native MIDIThruConnectionParams setReserved2(@Array({3}) ByteBuffer reserved2);
    @StructMember(21) public native byte getFilterOutAllControls();
    @StructMember(21) public native MIDIThruConnectionParams setFilterOutAllControls(byte filterOutAllControls);
    @StructMember(22) public native short getNumControlTransforms();
    @StructMember(22) public native MIDIThruConnectionParams setNumControlTransforms(short numControlTransforms);
    @StructMember(23) public native short getNumMaps();
    @StructMember(23) public native MIDIThruConnectionParams setNumMaps(short numMaps);
    @StructMember(24) public native @Array({4}) ShortBuffer getReserved3();
    @StructMember(24) public native MIDIThruConnectionParams setReserved3(@Array({4}) ShortBuffer reserved3);
    /*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="MIDIThruConnectionParamsInitialize", optional=true)
    public native void initialize();
    /*</methods>*/
}
