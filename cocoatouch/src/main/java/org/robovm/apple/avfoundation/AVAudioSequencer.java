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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.mediatoolbox.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 9.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioSequencer/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAudioSequencerPtr extends Ptr<AVAudioSequencer, AVAudioSequencerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAudioSequencer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAudioSequencer() {}
    protected AVAudioSequencer(SkipInit skipInit) { super(skipInit); }
    public AVAudioSequencer(AVAudioEngine engine) { super((SkipInit) null); initObject(init(engine)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "tracks")
    public native NSArray<AVMusicTrack> getTracks();
    @Property(selector = "tempoTrack")
    public native AVMusicTrack getTempoTrack();
    @Property(selector = "userInfo")
    public native NSDictionary<NSString, ?> getUserInfo();
    @Property(selector = "currentPositionInSeconds")
    public native double getCurrentPositionInSeconds();
    @Property(selector = "setCurrentPositionInSeconds:")
    public native void setCurrentPositionInSeconds(double v);
    @Property(selector = "currentPositionInBeats")
    public native double getCurrentPositionInBeats();
    @Property(selector = "setCurrentPositionInBeats:")
    public native void setCurrentPositionInBeats(double v);
    @Property(selector = "isPlaying")
    public native boolean isPlaying();
    @Property(selector = "rate")
    public native float getRate();
    @Property(selector = "setRate:")
    public native void setRate(float v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithAudioEngine:")
    protected native @Pointer long init(AVAudioEngine engine);
    public boolean load(NSURL fileURL, AVMusicSequenceLoadOptions options) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = load(fileURL, options, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "loadFromURL:options:error:")
    private native boolean load(NSURL fileURL, AVMusicSequenceLoadOptions options, NSError.NSErrorPtr outError);
    public boolean load(NSData data, AVMusicSequenceLoadOptions options) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = load(data, options, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "loadFromData:options:error:")
    private native boolean load(NSData data, AVMusicSequenceLoadOptions options, NSError.NSErrorPtr outError);
    public boolean write(NSURL fileURL, @MachineSizedSInt long resolution, boolean replace) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = write(fileURL, resolution, replace, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "writeToURL:SMPTEResolution:replaceExisting:error:")
    private native boolean write(NSURL fileURL, @MachineSizedSInt long resolution, boolean replace, NSError.NSErrorPtr outError);
    public NSData getData(@MachineSizedSInt long SMPTEResolution) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSData result = getData(SMPTEResolution, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "dataWithSMPTEResolution:error:")
    private native NSData getData(@MachineSizedSInt long SMPTEResolution, NSError.NSErrorPtr outError);
    @Method(selector = "secondsForBeats:")
    public native double convertBeatsToSeconds(double beats);
    @Method(selector = "beatsForSeconds:")
    public native double convertSecondsToBeats(double seconds);
    public long convertBeatsToHostTime(double beats) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       long result = convertBeatsToHostTime(beats, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "hostTimeForBeats:error:")
    private native long convertBeatsToHostTime(double beats, NSError.NSErrorPtr outError);
    public double convertHostTimeToBeats(long hostTime) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       double result = convertHostTimeToBeats(hostTime, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "beatsForHostTime:error:")
    private native double convertHostTimeToBeats(long hostTime, NSError.NSErrorPtr outError);
    @Method(selector = "prepareToPlay")
    public native void prepareToPlay();
    public boolean start() throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       boolean result = start(ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "startAndReturnError:")
    private native boolean start(NSError.NSErrorPtr outError);
    @Method(selector = "stop")
    public native void stop();
    /*</methods>*/
}
