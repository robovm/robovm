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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.audiounit.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.coremidi.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AudioToolbox")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MusicSequence/*</name>*/ 
    extends /*<extends>*/NativeObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MusicSequencePtr extends Ptr<MusicSequence, MusicSequencePtr> {}/*</ptr>*/
    
    public interface UserCallback {
        void invoke(MusicSequence sequence, MusicTrack track, double eventTime, MusicEventUserData eventData,
                double startSliceBeat, double endSliceBeat);
    }
    
    private static java.util.concurrent.atomic.AtomicLong callbackId = new java.util.concurrent.atomic.AtomicLong();
    private static final LongMap<UserCallback> userCallbacks = new LongMap<>();
    private static final java.lang.reflect.Method cbUser;
    
    static {
        try {
            cbUser = MusicSequence.class.getDeclaredMethod("cbUser", Long.TYPE, MusicSequence.class, MusicTrack.class, 
                    Double.TYPE, MusicEventUserData.class, Double.TYPE, Double.TYPE);
        } catch (Throwable e) {
            throw new Error(e);
        }
    }
    /*<bind>*/static { Bro.bind(MusicSequence.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected MusicSequence() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Callback
    private static void cbUser(@Pointer long clientData, MusicSequence sequence, MusicTrack track, double eventTime, MusicEventUserData eventData, 
                double startSliceBeat, double endSliceBeat) {
        synchronized (userCallbacks) {
            userCallbacks.get(clientData).invoke(sequence, track, eventTime, eventData, startSliceBeat, endSliceBeat);
        }
    }
    
    private long cid;
    
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public static MusicSequence create() throws OSStatusException {
        MusicSequence.MusicSequencePtr ptr = new MusicSequence.MusicSequencePtr();
        OSStatus status = create0(ptr);
        OSStatusException.throwIfNecessary(status);
        
        MusicSequence result = ptr.get();
        result.cid = callbackId.getAndIncrement();
        return result;
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void dispose() throws OSStatusException {
        OSStatus status = dispose0();
        if (OSStatusException.throwIfNecessary(status)) {
            synchronized (userCallbacks) {
                userCallbacks.remove(cid);
            }
        }
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public MusicTrack newTrack() throws OSStatusException {
        MusicTrack.MusicTrackPtr ptr = new MusicTrack.MusicTrackPtr();
        OSStatus status = newTrack0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void disposeTrack(MusicTrack track) throws OSStatusException {
        OSStatus status = disposeTrack0(track);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public int getTrackCount() throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getTrackCount0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public MusicTrack getIndTrack(int trackIndex) throws OSStatusException {
        MusicTrack.MusicTrackPtr ptr = new MusicTrack.MusicTrackPtr();
        OSStatus status = getIndTrack0(trackIndex, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public int getTrackIndex(MusicTrack track) throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getTrackIndex0(track, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public MusicTrack getTempoTrack() throws OSStatusException {
        MusicTrack.MusicTrackPtr ptr = new MusicTrack.MusicTrackPtr();
        OSStatus status = getTempoTrack0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void setAUGraph(AUGraph graph) throws OSStatusException {
        OSStatus status = setAUGraph0(graph);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public AUGraph getAUGraph() throws OSStatusException {
        AUGraph.AUGraphPtr ptr = new AUGraph.AUGraphPtr();
        OSStatus status = getAUGraph0(ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void setMIDIEndpoint(MIDIEndpoint endpoint) throws OSStatusException {
        OSStatus status = setMIDIEndpoint0(endpoint);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void setSequenceType(MusicSequenceType type) throws OSStatusException {
        OSStatus status = setSequenceType0(type);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public MusicSequenceType getSequenceType() throws OSStatusException {
        IntPtr ptr = new IntPtr();
        OSStatus status = getSequenceType0(ptr);
        OSStatusException.throwIfNecessary(status);
        return MusicSequenceType.valueOf(ptr.get());
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void loadFile(NSURL fileRef, MusicSequenceFileType fileTypeHint, MusicSequenceLoadFlags flags) throws OSStatusException {
        OSStatus status = loadFile0(fileRef, fileTypeHint, flags);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void loadFileData(NSData data, MusicSequenceFileType fileTypeHint, MusicSequenceLoadFlags flags) throws OSStatusException {
        OSStatus status = loadFileData0(data, fileTypeHint, flags);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void createFile(NSURL fileRef, MusicSequenceFileType fileType, MusicSequenceFileFlags flags, short resolution) throws OSStatusException {
        OSStatus status = createFile0(fileRef, fileType, flags, resolution);
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public NSData createFileData(MusicSequenceFileType fileType, MusicSequenceFileFlags flags, short resolution) throws OSStatusException {
        NSData.NSDataPtr ptr = new NSData.NSDataPtr();
        OSStatus status = createFileData0(fileType, flags, resolution, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void reverse() throws OSStatusException {
        OSStatus status = reverse0();
        OSStatusException.throwIfNecessary(status);
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public double getSecondsForBeats(double beats) throws OSStatusException {
        DoublePtr ptr = new DoublePtr();
        OSStatus status = getSecondsForBeats0(beats, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public double getBeatsForSeconds(double seconds) throws OSStatusException {
        DoublePtr ptr = new DoublePtr();
        OSStatus status = getBeatsForSeconds0(seconds, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public void setUserCallback(UserCallback callback) throws OSStatusException {
        if (callback == null) {
            synchronized (userCallbacks) {
                userCallbacks.remove(cid);
            }
            OSStatus status = setUserCallback0(null, cid);
            OSStatusException.throwIfNecessary(status);
        } else {
            OSStatus status = setUserCallback0(new FunctionPtr(cbUser), cid);
            if (OSStatusException.throwIfNecessary(status)) {
                synchronized (userCallbacks) {
                    userCallbacks.put(cid, callback);
                }
            }
        }
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public CABarBeatTime convertBeatsToBarBeatTime(double beats, int subbeatDivisor) throws OSStatusException {
        CABarBeatTime.CABarBeatTimePtr ptr = new CABarBeatTime.CABarBeatTimePtr();
        OSStatus status = convertBeatsToBarBeatTime0(beats, subbeatDivisor, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /**
     * @throws OSStatusException 
     * @since Available in iOS 5.0 and later.
     */
    public double convertBarBeatTimeToBeats(CABarBeatTime barBeatTime) throws OSStatusException {
        DoublePtr ptr = new DoublePtr();
        OSStatus status = convertBarBeatTimeToBeats0(barBeatTime, ptr);
        OSStatusException.throwIfNecessary(status);
        return ptr.get();
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="NewMusicSequence", optional=true)
    protected static native OSStatus create0(MusicSequence.MusicSequencePtr outSequence);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="DisposeMusicSequence", optional=true)
    protected native OSStatus dispose0();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceNewTrack", optional=true)
    protected native OSStatus newTrack0(MusicTrack.MusicTrackPtr outTrack);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceDisposeTrack", optional=true)
    protected native OSStatus disposeTrack0(MusicTrack inTrack);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceGetTrackCount", optional=true)
    protected native OSStatus getTrackCount0(IntPtr outNumberOfTracks);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceGetIndTrack", optional=true)
    protected native OSStatus getIndTrack0(int inTrackIndex, MusicTrack.MusicTrackPtr outTrack);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceGetTrackIndex", optional=true)
    protected native OSStatus getTrackIndex0(MusicTrack inTrack, IntPtr outTrackIndex);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceGetTempoTrack", optional=true)
    protected native OSStatus getTempoTrack0(MusicTrack.MusicTrackPtr outTrack);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceSetAUGraph", optional=true)
    protected native OSStatus setAUGraph0(AUGraph inGraph);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceGetAUGraph", optional=true)
    protected native OSStatus getAUGraph0(AUGraph.AUGraphPtr outGraph);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceSetMIDIEndpoint", optional=true)
    protected native OSStatus setMIDIEndpoint0(MIDIEndpoint inEndpoint);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceSetSequenceType", optional=true)
    protected native OSStatus setSequenceType0(MusicSequenceType inType);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceGetSequenceType", optional=true)
    protected native OSStatus getSequenceType0(IntPtr outType);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceFileLoad", optional=true)
    protected native OSStatus loadFile0(NSURL inFileRef, MusicSequenceFileType inFileTypeHint, MusicSequenceLoadFlags inFlags);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceFileLoadData", optional=true)
    protected native OSStatus loadFileData0(NSData inData, MusicSequenceFileType inFileTypeHint, MusicSequenceLoadFlags inFlags);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceFileCreate", optional=true)
    protected native OSStatus createFile0(NSURL inFileRef, MusicSequenceFileType inFileType, MusicSequenceFileFlags inFlags, short inResolution);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceFileCreateData", optional=true)
    protected native OSStatus createFileData0(MusicSequenceFileType inFileType, MusicSequenceFileFlags inFlags, short inResolution, NSData.NSDataPtr outData);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceReverse", optional=true)
    protected native OSStatus reverse0();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceGetSecondsForBeats", optional=true)
    protected native OSStatus getSecondsForBeats0(double inBeats, DoublePtr outSeconds);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceGetBeatsForSeconds", optional=true)
    protected native OSStatus getBeatsForSeconds0(double inSeconds, DoublePtr outBeats);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceSetUserCallback", optional=true)
    public native OSStatus setUserCallback0(FunctionPtr inCallback, @Pointer long inClientData);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceBeatsToBarBeatTime", optional=true)
    protected native OSStatus convertBeatsToBarBeatTime0(double inBeats, int inSubbeatDivisor, CABarBeatTime.CABarBeatTimePtr outBarBeatTime);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceBarBeatTimeToBeats", optional=true)
    protected native OSStatus convertBarBeatTimeToBeats0(CABarBeatTime inBarBeatTime, DoublePtr outBeats);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Bridge(symbol="MusicSequenceGetInfoDictionary", optional=true)
    public native AudioFileInfoDictionary getInfoDictionary();
    /*</methods>*/
}
