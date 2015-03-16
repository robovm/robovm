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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVOutputSettingsAssistant/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVOutputSettingsAssistantPtr extends Ptr<AVOutputSettingsAssistant, AVOutputSettingsAssistantPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVOutputSettingsAssistant.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVOutputSettingsAssistant() {}
    protected AVOutputSettingsAssistant(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "audioSettings")
    public native AVAudioSettings getAudioSettings();
    @Property(selector = "videoSettings")
    public native AVVideoSettings getVideoSettings();
    @Property(selector = "outputFileType")
    public native String getOutputFileType();
    @Property(selector = "sourceAudioFormat")
    public native CMAudioFormatDescription getSourceAudioFormat();
    @Property(selector = "setSourceAudioFormat:")
    public native void setSourceAudioFormat(CMAudioFormatDescription v);
    @Property(selector = "sourceVideoFormat")
    public native CMVideoFormatDescription getSourceVideoFormat();
    @Property(selector = "setSourceVideoFormat:")
    public native void setSourceVideoFormat(CMVideoFormatDescription v);
    @Property(selector = "sourceVideoAverageFrameDuration")
    public native @ByVal CMTime getSourceVideoAverageFrameDuration();
    @Property(selector = "setSourceVideoAverageFrameDuration:")
    public native void setSourceVideoAverageFrameDuration(@ByVal CMTime v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "sourceVideoMinFrameDuration")
    public native @ByVal CMTime getSourceVideoMinFrameDuration();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setSourceVideoMinFrameDuration:")
    public native void setSourceVideoMinFrameDuration(@ByVal CMTime v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "availableOutputSettingsPresets")
    public static native @org.robovm.rt.bro.annotation.Marshaler(AVOutputSettingsPreset.AsListMarshaler.class) List<AVOutputSettingsPreset> getAvailableOutputSettingsPresets();
    @Method(selector = "outputSettingsAssistantWithPreset:")
    public static native AVOutputSettingsAssistant create(AVOutputSettingsPreset presetIdentifier);
    /*</methods>*/
}
