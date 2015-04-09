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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.mediatoolbox.*;
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMutableAudioMixInputParameters/*</name>*/ 
    extends /*<extends>*/AVAudioMixInputParameters/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVMutableAudioMixInputParametersPtr extends Ptr<AVMutableAudioMixInputParameters, AVMutableAudioMixInputParametersPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVMutableAudioMixInputParameters.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVMutableAudioMixInputParameters() {}
    protected AVMutableAudioMixInputParameters(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "trackID")
    public native int getTrackID();
    @Property(selector = "setTrackID:")
    public native void setTrackID(int v);
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
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "audioTapProcessor")
    public native MTAudioProcessingTap getAudioTapProcessor();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setAudioTapProcessor:")
    public native void setAudioTapProcessor(MTAudioProcessingTap v);
    /*</properties>*/
    /*<members>*//*</members>*/
    public void setVolumeRamp(AVTimeRamp<Float> ramp) {
        setVolumeRamp(ramp.getStart(), ramp.getEnd(), ramp.getTimeRange());
    }
    /*<methods>*/
    @Method(selector = "setVolumeRampFromStartVolume:toEndVolume:timeRange:")
    public native void setVolumeRamp(float startVolume, float endVolume, @ByVal CMTimeRange timeRange);
    @Method(selector = "setVolume:atTime:")
    public native void setVolume(float volume, @ByVal CMTime time);
    @Method(selector = "audioMixInputParametersWithTrack:")
    public static native AVMutableAudioMixInputParameters create(AVAssetTrack track);
    @Method(selector = "audioMixInputParameters")
    public static native AVMutableAudioMixInputParameters create();
    /*</methods>*/
}
