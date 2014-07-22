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
        this.mBaseNote(mBaseNote);
        this.mMIDILowNote(mMIDILowNote);
        this.mMIDIHighNote(mMIDIHighNote);
        this.mMIDILowVelocity(mMIDILowVelocity);
        this.mMIDIHighVelocity(mMIDIHighVelocity);
        this.mdBGain(mdBGain);
        this.mStartRegionID(mStartRegionID);
        this.mSustainRegionID(mSustainRegionID);
        this.mReleaseRegionID(mReleaseRegionID);
        this.mInstrumentID(mInstrumentID);
    }
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*/
    @StructMember(0) public native float mBaseNote();
    @StructMember(0) public native CAFInstrumentChunk mBaseNote(float mBaseNote);
    @StructMember(1) public native byte mMIDILowNote();
    @StructMember(1) public native CAFInstrumentChunk mMIDILowNote(byte mMIDILowNote);
    @StructMember(2) public native byte mMIDIHighNote();
    @StructMember(2) public native CAFInstrumentChunk mMIDIHighNote(byte mMIDIHighNote);
    @StructMember(3) public native byte mMIDILowVelocity();
    @StructMember(3) public native CAFInstrumentChunk mMIDILowVelocity(byte mMIDILowVelocity);
    @StructMember(4) public native byte mMIDIHighVelocity();
    @StructMember(4) public native CAFInstrumentChunk mMIDIHighVelocity(byte mMIDIHighVelocity);
    @StructMember(5) public native float mdBGain();
    @StructMember(5) public native CAFInstrumentChunk mdBGain(float mdBGain);
    @StructMember(6) public native int mStartRegionID();
    @StructMember(6) public native CAFInstrumentChunk mStartRegionID(int mStartRegionID);
    @StructMember(7) public native int mSustainRegionID();
    @StructMember(7) public native CAFInstrumentChunk mSustainRegionID(int mSustainRegionID);
    @StructMember(8) public native int mReleaseRegionID();
    @StructMember(8) public native CAFInstrumentChunk mReleaseRegionID(int mReleaseRegionID);
    @StructMember(9) public native int mInstrumentID();
    @StructMember(9) public native CAFInstrumentChunk mInstrumentID(int mInstrumentID);
    /*</members>*/
    /*<methods>*//*</methods>*/
}
