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
 * @since Available in iOS 4.1 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAssetReaderAudioMixOutput/*</name>*/ 
    extends /*<extends>*/AVAssetReaderOutput/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAssetReaderAudioMixOutputPtr extends Ptr<AVAssetReaderAudioMixOutput, AVAssetReaderAudioMixOutputPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAssetReaderAudioMixOutput.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAssetReaderAudioMixOutput() {}
    protected AVAssetReaderAudioMixOutput(SkipInit skipInit) { super(skipInit); }
    public AVAssetReaderAudioMixOutput(NSArray<AVAssetTrack> audioTracks, AVAudioSettings audioSettings) { super((SkipInit) null); initObject(init(audioTracks, audioSettings)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "audioTracks")
    public native NSArray<AVAssetTrack> getAudioTracks();
    @Property(selector = "audioSettings")
    public native AVAudioSettings getAudioSettings();
    @Property(selector = "audioMix")
    public native AVAudioMix getAudioMix();
    @Property(selector = "setAudioMix:")
    public native void setAudioMix(AVAudioMix v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "audioTimePitchAlgorithm")
    public native AVAudioTimePitchAlgorithm getAudioTimePitchAlgorithm();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setAudioTimePitchAlgorithm:")
    public native void setAudioTimePitchAlgorithm(AVAudioTimePitchAlgorithm v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithAudioTracks:audioSettings:")
    protected native @Pointer long init(NSArray<AVAssetTrack> audioTracks, AVAudioSettings audioSettings);
    @Method(selector = "assetReaderAudioMixOutputWithAudioTracks:audioSettings:")
    public static native AVAssetReaderAudioMixOutput create(NSArray<AVAssetTrack> audioTracks, AVAudioSettings audioSettings);
    /*</methods>*/
}
