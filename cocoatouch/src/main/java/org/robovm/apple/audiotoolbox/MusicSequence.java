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
/*<annotations>*/@Library("AudioToolbox")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MusicSequence/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MusicSequencePtr extends Ptr<MusicSequence, MusicSequencePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(MusicSequence.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected MusicSequence() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="NewMusicSequence", optional=true)
    public static native int create(MusicSequence.MusicSequencePtr outSequence);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="DisposeMusicSequence", optional=true)
    public native int dispose();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceNewTrack", optional=true)
    public native int newTrack(MusicTrack.MusicTrackPtr outTrack);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceDisposeTrack", optional=true)
    public native int disposeTrack(MusicTrack inTrack);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceGetTrackCount", optional=true)
    public native int getTrackCount(IntPtr outNumberOfTracks);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceGetIndTrack", optional=true)
    public native int getIndTrack(int inTrackIndex, MusicTrack.MusicTrackPtr outTrack);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceGetTrackIndex", optional=true)
    public native int getTrackIndex(MusicTrack inTrack, IntPtr outTrackIndex);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceGetTempoTrack", optional=true)
    public native int getTempoTrack(MusicTrack.MusicTrackPtr outTrack);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceSetMIDIEndpoint", optional=true)
    public native int setMIDIEndpoint(MIDIEndpoint inEndpoint);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceSetSequenceType", optional=true)
    public native int setSequenceType(MusicSequenceType inType);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceGetSequenceType", optional=true)
    public native int getSequenceType(MachineSizedUIntPtr outType);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceFileLoad", optional=true)
    public native int fileLoad(NSURL inFileRef, int inFileTypeHint, int inFlags);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceFileLoadData", optional=true)
    public native int fileLoadData(NSData inData, int inFileTypeHint, int inFlags);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceFileCreate", optional=true)
    public native int fileCreate(NSURL inFileRef, int inFileType, int inFlags, short inResolution);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceFileCreateData", optional=true)
    public native int fileCreateData(int inFileType, int inFlags, short inResolution, NSData.NSDataPtr outData);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceReverse", optional=true)
    public native int reverse();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceGetSecondsForBeats", optional=true)
    public native int getSecondsForBeats(double inBeats, DoublePtr outSeconds);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceGetBeatsForSeconds", optional=true)
    public native int getBeatsForSeconds(double inSeconds, DoublePtr outBeats);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceSetUserCallback", optional=true)
    public native int setUserCallback(FunctionPtr inCallback, VoidPtr inClientData);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceBeatsToBarBeatTime", optional=true)
    public native int beatsToBarBeatTime(double inBeats, int inSubbeatDivisor, CABarBeatTime outBarBeatTime);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceBarBeatTimeToBeats", optional=true)
    public native int barBeatTimeToBeats(CABarBeatTime inBarBeatTime, DoublePtr outBeats);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceGetInfoDictionary", optional=true)
    public native NSDictionary<?, ?> getInfoDictionary();
    /*</methods>*/
}
