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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioMixInputParameters/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVAudioMixInputParametersPtr extends Ptr<AVAudioMixInputParameters, AVAudioMixInputParametersPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVAudioMixInputParameters.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVAudioMixInputParameters() {}
    protected AVAudioMixInputParameters(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "trackID")
    public native int getTrackID();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "audioTimePitchAlgorithm")
    public native AVAudioTimePitchAlgorithm getAudioTimePitchAlgorithm();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "audioTapProcessor")
    public native MTAudioProcessingTap getAudioTapProcessor();
    /*</properties>*/
    /*<members>*//*</members>*/
    public AVTimeRamp<Float> getVolumeRamp(CMTime time) {
        FloatPtr start = new FloatPtr();
        FloatPtr end = new FloatPtr();
        CMTimeRange.CMTimeRangePtr timeRange = new CMTimeRange.CMTimeRangePtr();
        boolean valid = getVolumeRamp(time, start, end, timeRange);
        if (valid) {
            return new AVTimeRamp<Float>(start.get(), end.get(), timeRange.get());
        }
        return null;
    }
    /*<methods>*/
    @Method(selector = "getVolumeRampForTime:startVolume:endVolume:timeRange:")
    protected native boolean getVolumeRamp(@ByVal CMTime time, FloatPtr startVolume, FloatPtr endVolume, CMTimeRange.CMTimeRangePtr timeRange);
    /*</methods>*/
}
