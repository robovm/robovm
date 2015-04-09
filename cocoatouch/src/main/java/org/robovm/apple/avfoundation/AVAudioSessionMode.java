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
/*</javadoc>*/
@Marshaler(AVAudioSessionMode.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioSessionMode/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static AVAudioSessionMode toObject(Class<AVAudioSessionMode> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVAudioSessionMode.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVAudioSessionMode o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVAudioSessionMode.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVAudioSessionMode Default = new AVAudioSessionMode("DefaultValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVAudioSessionMode VoiceChat = new AVAudioSessionMode("VoiceChatValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVAudioSessionMode GameChat = new AVAudioSessionMode("GameChatValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVAudioSessionMode VideoRecording = new AVAudioSessionMode("VideoRecordingValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVAudioSessionMode Measurement = new AVAudioSessionMode("MeasurementValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionMode MoviePlayback = new AVAudioSessionMode("MoviePlaybackValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVAudioSessionMode VideoChat = new AVAudioSessionMode("VideoChatValue");
    
    private static AVAudioSessionMode[] values = new AVAudioSessionMode[] {Default, VoiceChat, GameChat, VideoRecording, 
        Measurement, MoviePlayback, VideoChat};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVAudioSessionMode(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVAudioSessionMode valueOf(NSString value) {
        for (AVAudioSessionMode v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVAudioSessionMode/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionModeDefault", optional=true)
    protected static native NSString DefaultValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionModeVoiceChat", optional=true)
    protected static native NSString VoiceChatValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionModeGameChat", optional=true)
    protected static native NSString GameChatValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionModeVideoRecording", optional=true)
    protected static native NSString VideoRecordingValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionModeMeasurement", optional=true)
    protected static native NSString MeasurementValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionModeMoviePlayback", optional=true)
    protected static native NSString MoviePlaybackValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionModeVideoChat", optional=true)
    protected static native NSString VideoChatValue();
    /*</methods>*/
}
