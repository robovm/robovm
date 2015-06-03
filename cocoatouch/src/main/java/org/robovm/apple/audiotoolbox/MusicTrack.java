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
/*<annotations>*/@Library("AudioToolbox")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MusicTrack/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MusicTrackPtr extends Ptr<MusicTrack, MusicTrackPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(MusicTrack.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected MusicTrack() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public MusicSequence getSequence() throws OSStatusException {
        MusicSequence.MusicSequencePtr ptr = new MusicSequence.MusicSequencePtr();
        OSStatus status = getSequence0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void setDestNode(int node) throws OSStatusException {
        OSStatus status = setDestNode0(node);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    @WeaklyLinked
    public void setDestMIDIEndpoint(MIDIEndpoint endpoint) throws OSStatusException {
        OSStatus status = setDestMIDIEndpoint0(endpoint);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public int getDestNode() throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getDestNode0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    @WeaklyLinked
    public MIDIEndpoint getDestMIDIEndpoint() throws OSStatusException {
        MIDIEndpoint.MIDIEndpointPtr ptr = new MIDIEndpoint.MIDIEndpointPtr();
        OSStatus status = getDestMIDIEndpoint0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public <T extends Struct<T>> T getProperty(MusicTrackProperty id, Class<T> type) throws OSStatusException {
        T data = Struct.allocate(type);
        IntPtr dataSize = new IntPtr(Struct.sizeOf(data));
        OSStatus status = getProperty0(id, data.as(VoidPtr.class), dataSize);
        OSStatusException.throwIfNecessary(status);
        return data;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public <T extends Struct<T>> void setProperty(MusicTrackProperty id, T data) throws OSStatusException {
        OSStatus status = setProperty0(id, data == null ? null : data.as(VoidPtr.class), data == null ? 0 : Struct.sizeOf(data));
        OSStatusException.throwIfNecessary(status);
    }
    public int getPropertyAsInt(MusicTrackProperty id) throws OSStatusException {
        IntPtr ptr = getProperty(id, IntPtr.class);
        return ptr.get();
    }
    public long getPropertyAsLong(MusicTrackProperty id) throws OSStatusException {
        LongPtr ptr = getProperty(id, LongPtr.class);
        return ptr.get();
    }
    public float getPropertyAsFloat(MusicTrackProperty id) throws OSStatusException {
        FloatPtr ptr = getProperty(id, FloatPtr.class);
        return ptr.get();
    }
    public double getPropertyAsDouble(MusicTrackProperty id) throws OSStatusException {
        DoublePtr ptr = getProperty(id, DoublePtr.class);
        return ptr.get();
    }
    public void setProperty(MusicTrackProperty id, int value) throws OSStatusException {
        setProperty(id, new IntPtr(value));
    }
    public void setProperty(MusicTrackProperty id, long value) throws OSStatusException {
        setProperty(id, new LongPtr(value));
    }
    public void setProperty(MusicTrackProperty id, float value) throws OSStatusException {
        setProperty(id, new FloatPtr(value));
    }
    public void setProperty(MusicTrackProperty id, double value) throws OSStatusException {
        setProperty(id, new DoublePtr(value));
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void moveEvents(double startTime, double endTime, double moveTime) throws OSStatusException {
        OSStatus status = moveEvents0(startTime, endTime, moveTime);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void clear(double startTime, double endTime) throws OSStatusException {
        OSStatus status = clear0(startTime, endTime);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void cut(double startTime, double endTime) throws OSStatusException {
        OSStatus status = cut0(startTime, endTime);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void copyInsert(double sourceStartTime, double sourceEndTime, MusicTrack destTrack, double destInsertTime) throws OSStatusException {
        OSStatus status = copyInsert0(sourceStartTime, sourceEndTime, destTrack, destInsertTime);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void merge(double sourceStartTime, double sourceEndTime, MusicTrack destTrack, double destInsertTime) throws OSStatusException {
        OSStatus status = merge0(sourceStartTime, sourceEndTime, destTrack, destInsertTime);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void newMIDINoteEvent(double timeStamp, MIDINoteMessage message) throws OSStatusException {
        OSStatus status = newMIDINoteEvent0(timeStamp, message);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void newMIDIChannelEvent(double timeStamp, MIDIChannelMessage message) throws OSStatusException {
        OSStatus status = newMIDIChannelEvent0(timeStamp, message);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void newMIDIRawDataEvent(double timeStamp, MIDIRawData rawData) throws OSStatusException {
        OSStatus status = newMIDIRawDataEvent0(timeStamp, rawData);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void newExtendedNoteEvent(double timeStamp, ExtendedNoteOnEvent info) throws OSStatusException {
        OSStatus status = newExtendedNoteEvent0(timeStamp, info);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void newParameterEvent(double timeStamp, AUParameterEvent info) throws OSStatusException {
        OSStatus status = newParameterEvent0(timeStamp, info);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void newExtendedTempoEvent(double timeStamp, double bpm) throws OSStatusException {
        OSStatus status = newExtendedTempoEvent0(timeStamp, bpm);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void newMetaEvent(double timeStamp, MIDIMetaEvent metaEvent) throws OSStatusException {
        OSStatus status = newMetaEvent0(timeStamp, metaEvent);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void newUserEvent(double timeStamp, MusicEventUserData userData) throws OSStatusException {
        OSStatus status = newUserEvent0(timeStamp, userData);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void newAUPresetEvent(double timeStamp, AUPresetEvent presetEvent) throws OSStatusException {
        OSStatus status = newAUPresetEvent0(timeStamp, presetEvent);
        OSStatusException.throwIfNecessary(status);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicTrackGetSequence", optional=true)
    protected native OSStatus getSequence0(MusicSequence.MusicSequencePtr outSequence);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicTrackSetDestNode", optional=true)
    protected native OSStatus setDestNode0(int inNode);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @WeaklyLinked
    @Bridge(symbol="MusicTrackSetDestMIDIEndpoint", optional=true)
    protected native OSStatus setDestMIDIEndpoint0(MIDIEndpoint inEndpoint);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicTrackGetDestNode", optional=true)
    protected native OSStatus getDestNode0(IntPtr outNode);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @WeaklyLinked
    @Bridge(symbol="MusicTrackGetDestMIDIEndpoint", optional=true)
    protected native OSStatus getDestMIDIEndpoint0(MIDIEndpoint.MIDIEndpointPtr outEndpoint);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicTrackSetProperty", optional=true)
    protected native OSStatus setProperty0(MusicTrackProperty inPropertyID, VoidPtr inData, int inLength);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicTrackGetProperty", optional=true)
    protected native OSStatus getProperty0(MusicTrackProperty inPropertyID, VoidPtr outData, IntPtr ioLength);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicTrackMoveEvents", optional=true)
    protected native OSStatus moveEvents0(double inStartTime, double inEndTime, double inMoveTime);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicTrackClear", optional=true)
    protected native OSStatus clear0(double inStartTime, double inEndTime);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicTrackCut", optional=true)
    protected native OSStatus cut0(double inStartTime, double inEndTime);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicTrackCopyInsert", optional=true)
    protected native OSStatus copyInsert0(double inSourceStartTime, double inSourceEndTime, MusicTrack inDestTrack, double inDestInsertTime);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicTrackMerge", optional=true)
    protected native OSStatus merge0(double inSourceStartTime, double inSourceEndTime, MusicTrack inDestTrack, double inDestInsertTime);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicTrackNewMIDINoteEvent", optional=true)
    protected native OSStatus newMIDINoteEvent0(double inTimeStamp, MIDINoteMessage inMessage);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicTrackNewMIDIChannelEvent", optional=true)
    protected native OSStatus newMIDIChannelEvent0(double inTimeStamp, MIDIChannelMessage inMessage);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicTrackNewMIDIRawDataEvent", optional=true)
    protected native OSStatus newMIDIRawDataEvent0(double inTimeStamp, MIDIRawData inRawData);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicTrackNewExtendedNoteEvent", optional=true)
    protected native OSStatus newExtendedNoteEvent0(double inTimeStamp, ExtendedNoteOnEvent inInfo);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicTrackNewParameterEvent", optional=true)
    protected native OSStatus newParameterEvent0(double inTimeStamp, AUParameterEvent inInfo);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicTrackNewExtendedTempoEvent", optional=true)
    protected native OSStatus newExtendedTempoEvent0(double inTimeStamp, double inBPM);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicTrackNewMetaEvent", optional=true)
    protected native OSStatus newMetaEvent0(double inTimeStamp, MIDIMetaEvent inMetaEvent);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicTrackNewUserEvent", optional=true)
    protected native OSStatus newUserEvent0(double inTimeStamp, MusicEventUserData inUserData);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicTrackNewAUPresetEvent", optional=true)
    protected native OSStatus newAUPresetEvent0(double inTimeStamp, AUPresetEvent inPresetEvent);
    /*</methods>*/
}
