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
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
@Marshaler(/*<name>*/AVAudioSettings/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioSettings/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static AVAudioSettings toObject(Class<AVAudioSettings> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new AVAudioSettings(o);
        }
        @MarshalsPointer
        public static long toNative(AVAudioSettings o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<AVAudioSettings> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVAudioSettings> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new AVAudioSettings(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVAudioSettings> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (AVAudioSettings i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    AVAudioSettings(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    public AVAudioSettings() {}
    /*</constructors>*/

    private AVAudioEncoderSettings encoderSettings;
    
    public AVAudioEncoderSettings getEncoderSettings() {
        if (encoderSettings == null) {
            encoderSettings = new AVAudioEncoderSettings(data);
        }
        return encoderSettings;
    }
    public AVAudioSettings setEncoderSettings(AVAudioEncoderSettings settings) {
        if (settings == null) {
            throw new NullPointerException("settings");
        }
        this.encoderSettings = settings;
        data.putAll(settings.getDictionary());
        return this;
    }
    
    private AVSampleRateConverterSettings sampleRateConverterSettings;
    
    public AVSampleRateConverterSettings getSampleRateConverterSettings() {
        if (sampleRateConverterSettings == null) {
            sampleRateConverterSettings = new AVSampleRateConverterSettings(data);
        }
        return sampleRateConverterSettings;
    }
    public AVAudioSettings setSampleRateConverterSettings(AVSampleRateConverterSettings settings) {
        if (settings == null) {
            throw new NullPointerException("settings");
        }
        this.sampleRateConverterSettings = settings;
        data.putAll(settings.getDictionary());
        return this;
    }

    /*<methods>*/
    public boolean has(NSString key) {
        return data.containsKey(key);
    }
    public NSObject get(NSString key) {
        if (has(key)) {
            return data.get(key);
        }
        return null;
    }
    public AVAudioSettings set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    @WeaklyLinked
    public AudioFormat getFormat() {
        if (has(Keys.FormatID())) {
            NSNumber val = (NSNumber) get(Keys.FormatID());
            return AudioFormat.valueOf(val.longValue());
        }
        return null;
    }
    @WeaklyLinked
    public AVAudioSettings setFormat(AudioFormat format) {
        set(Keys.FormatID(), NSNumber.valueOf(format.value()));
        return this;
    }
    public double getSampleRate() {
        if (has(Keys.SampleRate())) {
            NSNumber val = (NSNumber) get(Keys.SampleRate());
            return val.doubleValue();
        }
        return 0;
    }
    public AVAudioSettings setSampleRate(double sampleRate) {
        set(Keys.SampleRate(), NSNumber.valueOf(sampleRate));
        return this;
    }
    public int getNumberOfChannels() {
        if (has(Keys.NumberOfChannels())) {
            NSNumber val = (NSNumber) get(Keys.NumberOfChannels());
            return val.intValue();
        }
        return 0;
    }
    public AVAudioSettings setNumberOfChannels(int numberOfChannels) {
        set(Keys.NumberOfChannels(), NSNumber.valueOf(numberOfChannels));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isLinearPCMNonInterleaved() {
        if (has(Keys.IsNonInterleaved())) {
            NSNumber val = (NSNumber) get(Keys.IsNonInterleaved());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVAudioSettings setLinearPCMNonInterleaved(boolean linearPCMNonInterleaved) {
        set(Keys.IsNonInterleaved(), NSNumber.valueOf(linearPCMNonInterleaved));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @WeaklyLinked
    public AudioChannelLayout getChannelLayout() {
        if (has(Keys.ChannelLayout())) {
            NSData val = (NSData) get(Keys.ChannelLayout());
            return val.getStructData(AudioChannelLayout.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @WeaklyLinked
    public AVAudioSettings setChannelLayout(AudioChannelLayout channelLayout) {
        set(Keys.ChannelLayout(), new NSData(channelLayout));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("AVFoundation")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        @GlobalValue(symbol="AVFormatIDKey", optional=true)
        public static native NSString FormatID();
        @GlobalValue(symbol="AVSampleRateKey", optional=true)
        public static native NSString SampleRate();
        @GlobalValue(symbol="AVNumberOfChannelsKey", optional=true)
        public static native NSString NumberOfChannels();
        @GlobalValue(symbol="AVLinearPCMBitDepthKey", optional=true)
        public static native NSString BitDepthKey();
        @GlobalValue(symbol="AVLinearPCMIsBigEndianKey", optional=true)
        public static native NSString IsBigEndianKey();
        @GlobalValue(symbol="AVLinearPCMIsFloatKey", optional=true)
        public static native NSString IsFloatKey();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVLinearPCMIsNonInterleaved", optional=true)
        public static native NSString IsNonInterleaved();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVChannelLayoutKey", optional=true)
        public static native NSString ChannelLayout();
    }
    /*</keys>*/
}
