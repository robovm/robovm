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
package org.robovm.apple.audiotoolbox;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.audiounit.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremidi.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CAFInstrumentChunk/*</name>*/ 
    extends /*<extends>*/Struct<CAFInstrumentChunk>/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CAFInstrumentChunkPtr extends Ptr<CAFInstrumentChunk, CAFInstrumentChunkPtr> {}/*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CAFInstrumentChunk() {}
    public CAFInstrumentChunk(float baseNote, byte MIDILowNote, byte MIDIHighNote, byte MIDILowVelocity, byte MIDIHighVelocity, float dBGain, int startRegionID, int sustainRegionID, int releaseRegionID, int instrumentID) {
        this.setBaseNote(baseNote);
        this.setMIDILowNote(MIDILowNote);
        this.setMIDIHighNote(MIDIHighNote);
        this.setMIDILowVelocity(MIDILowVelocity);
        this.setMIDIHighVelocity(MIDIHighVelocity);
        this.setDBGain(dBGain);
        this.setStartRegionID(startRegionID);
        this.setSustainRegionID(sustainRegionID);
        this.setReleaseRegionID(releaseRegionID);
        this.setInstrumentID(instrumentID);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native float getBaseNote();
    @StructMember(0) public native CAFInstrumentChunk setBaseNote(float baseNote);
    @StructMember(1) public native byte getMIDILowNote();
    @StructMember(1) public native CAFInstrumentChunk setMIDILowNote(byte MIDILowNote);
    @StructMember(2) public native byte getMIDIHighNote();
    @StructMember(2) public native CAFInstrumentChunk setMIDIHighNote(byte MIDIHighNote);
    @StructMember(3) public native byte getMIDILowVelocity();
    @StructMember(3) public native CAFInstrumentChunk setMIDILowVelocity(byte MIDILowVelocity);
    @StructMember(4) public native byte getMIDIHighVelocity();
    @StructMember(4) public native CAFInstrumentChunk setMIDIHighVelocity(byte MIDIHighVelocity);
    @StructMember(5) public native float getDBGain();
    @StructMember(5) public native CAFInstrumentChunk setDBGain(float dBGain);
    @StructMember(6) public native int getStartRegionID();
    @StructMember(6) public native CAFInstrumentChunk setStartRegionID(int startRegionID);
    @StructMember(7) public native int getSustainRegionID();
    @StructMember(7) public native CAFInstrumentChunk setSustainRegionID(int sustainRegionID);
    @StructMember(8) public native int getReleaseRegionID();
    @StructMember(8) public native CAFInstrumentChunk setReleaseRegionID(int releaseRegionID);
    @StructMember(9) public native int getInstrumentID();
    @StructMember(9) public native CAFInstrumentChunk setInstrumentID(int instrumentID);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
