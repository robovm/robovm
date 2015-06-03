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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAssetReaderTrackOutput/*</name>*/ 
    extends /*<extends>*/AVAssetReaderOutput/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAssetReaderTrackOutputPtr extends Ptr<AVAssetReaderTrackOutput, AVAssetReaderTrackOutputPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAssetReaderTrackOutput.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAssetReaderTrackOutput() {}
    protected AVAssetReaderTrackOutput(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    public AVAssetReaderTrackOutput(AVAssetTrack track, AVAudioSettings outputSettings) {
        super((SkipInit)null);
        initObject(init(track, outputSettings.getDictionary()));
    }
    public AVAssetReaderTrackOutput(AVAssetTrack track, AVVideoSettings outputSettings) {
        super((SkipInit)null);
        initObject(init(track, outputSettings.getDictionary()));
    }
    @SuppressWarnings("unchecked")
    public AVAssetReaderTrackOutput(AVAssetTrack track, AVPixelBufferAttributes outputSettings) {
        super((SkipInit)null);
        initObject(init(track, outputSettings.getDictionary().as(NSDictionary.class)));
    }
    
    public AVAudioSettings getAudioOutputSettings() {
        return new AVAudioSettings(getOutputSettings());
    }
    public AVVideoSettings getVideoOutputSettings() {
        return new AVVideoSettings(getOutputSettings());
    }
    @WeaklyLinked
    public AVPixelBufferAttributes getPixelBufferOutputSettings() {
        return new AVPixelBufferAttributes(getOutputSettings().as(CFDictionary.class));
    }
    /*<properties>*/
    @Property(selector = "track")
    public native AVAssetTrack getTrack();
    @Property(selector = "outputSettings")
    protected native NSDictionary<NSString, NSObject> getOutputSettings();
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
    public static AVAssetReaderTrackOutput create(AVAssetTrack track, AVAudioSettings outputSettings) {
        return create(track, outputSettings.getDictionary());
    }
    public static AVAssetReaderTrackOutput create(AVAssetTrack track, AVVideoSettings outputSettings) {
        return create(track, outputSettings.getDictionary());
    }
    @SuppressWarnings("unchecked")
    public static AVAssetReaderTrackOutput create(AVAssetTrack track, AVPixelBufferAttributes outputSettings) {
        return create(track, outputSettings.getDictionary().as(NSDictionary.class));
    }
    /*<methods>*/
    @Method(selector = "initWithTrack:outputSettings:")
    protected native @Pointer long init(AVAssetTrack track, NSDictionary<NSString, NSObject> outputSettings);
    @Method(selector = "assetReaderTrackOutputWithTrack:outputSettings:")
    protected static native AVAssetReaderTrackOutput create(AVAssetTrack track, NSDictionary<NSString, NSObject> outputSettings);
    /*</methods>*/
}
