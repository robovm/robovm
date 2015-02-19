/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
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
    public CAFInstrumentChunk(float mBaseNote, byte mMIDILowNote, byte mMIDIHighNote, byte mMIDILowVelocity, byte mMIDIHighVelocity, float mdBGain, int mStartRegionID, int mSustainRegionID, int mReleaseRegionID, int mInstrumentID) {
        this.setMBaseNote(mBaseNote);
        this.setMMIDILowNote(mMIDILowNote);
        this.setMMIDIHighNote(mMIDIHighNote);
        this.setMMIDILowVelocity(mMIDILowVelocity);
        this.setMMIDIHighVelocity(mMIDIHighVelocity);
        this.setMdBGain(mdBGain);
        this.setMStartRegionID(mStartRegionID);
        this.setMSustainRegionID(mSustainRegionID);
        this.setMReleaseRegionID(mReleaseRegionID);
        this.setMInstrumentID(mInstrumentID);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native float getMBaseNote();
    @StructMember(0) public native CAFInstrumentChunk setMBaseNote(float mBaseNote);
    @StructMember(1) public native byte getMMIDILowNote();
    @StructMember(1) public native CAFInstrumentChunk setMMIDILowNote(byte mMIDILowNote);
    @StructMember(2) public native byte getMMIDIHighNote();
    @StructMember(2) public native CAFInstrumentChunk setMMIDIHighNote(byte mMIDIHighNote);
    @StructMember(3) public native byte getMMIDILowVelocity();
    @StructMember(3) public native CAFInstrumentChunk setMMIDILowVelocity(byte mMIDILowVelocity);
    @StructMember(4) public native byte getMMIDIHighVelocity();
    @StructMember(4) public native CAFInstrumentChunk setMMIDIHighVelocity(byte mMIDIHighVelocity);
    @StructMember(5) public native float getMdBGain();
    @StructMember(5) public native CAFInstrumentChunk setMdBGain(float mdBGain);
    @StructMember(6) public native int getMStartRegionID();
    @StructMember(6) public native CAFInstrumentChunk setMStartRegionID(int mStartRegionID);
    @StructMember(7) public native int getMSustainRegionID();
    @StructMember(7) public native CAFInstrumentChunk setMSustainRegionID(int mSustainRegionID);
    @StructMember(8) public native int getMReleaseRegionID();
    @StructMember(8) public native CAFInstrumentChunk setMReleaseRegionID(int mReleaseRegionID);
    @StructMember(9) public native int getMInstrumentID();
    @StructMember(9) public native CAFInstrumentChunk setMInstrumentID(int mInstrumentID);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
