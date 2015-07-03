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
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/AVAudioSessionMode/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioSessionMode/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVAudioSessionMode/*</name>*/.class); }

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVAudioSessionMode> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVAudioSessionMode> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVAudioSessionMode.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVAudioSessionMode> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVAudioSessionMode o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVAudioSessionMode Default = new AVAudioSessionMode("Default");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVAudioSessionMode VoiceChat = new AVAudioSessionMode("VoiceChat");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVAudioSessionMode GameChat = new AVAudioSessionMode("GameChat");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVAudioSessionMode VideoRecording = new AVAudioSessionMode("VideoRecording");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVAudioSessionMode Measurement = new AVAudioSessionMode("Measurement");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionMode MoviePlayback = new AVAudioSessionMode("MoviePlayback");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVAudioSessionMode VideoChat = new AVAudioSessionMode("VideoChat");
    /*</constants>*/
    
    private static /*<name>*/AVAudioSessionMode/*</name>*/[] values = new /*<name>*/AVAudioSessionMode/*</name>*/[] {/*<value_list>*/Default, VoiceChat, GameChat, VideoRecording, Measurement, MoviePlayback, VideoChat/*</value_list>*/};
    
    /*<name>*/AVAudioSessionMode/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVAudioSessionMode/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVAudioSessionMode/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVAudioSessionMode/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionModeDefault", optional=true)
        public static native NSString Default();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionModeVoiceChat", optional=true)
        public static native NSString VoiceChat();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionModeGameChat", optional=true)
        public static native NSString GameChat();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionModeVideoRecording", optional=true)
        public static native NSString VideoRecording();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionModeMeasurement", optional=true)
        public static native NSString Measurement();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionModeMoviePlayback", optional=true)
        public static native NSString MoviePlayback();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionModeVideoChat", optional=true)
        public static native NSString VideoChat();
        /*</values>*/
    }
}
