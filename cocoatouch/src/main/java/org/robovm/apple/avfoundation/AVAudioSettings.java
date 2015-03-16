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
/*</javadoc>*/
@Marshaler(AVAudioSettings.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioSettings/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @SuppressWarnings("unchecked")
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

    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected AVAudioSettings(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public AVAudioSettings() {
        data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(AVAudioSettings.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
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
    
    
    public AudioFormat getFormat() {
        if (data.containsKey(FormatIDKey())) {
            NSNumber val = (NSNumber) data.get(FormatIDKey());
            return AudioFormat.valueOf(val.longValue());
        }
        return null;
    }
    public AVAudioSettings setFormat(AudioFormat format) {
        data.put(FormatIDKey(), NSNumber.valueOf(format.value()));
        return this;
    }
    public double getSampleRate() {
        if (data.containsKey(SampleRateKey())) {
            NSNumber val = (NSNumber) data.get(SampleRateKey());
            return val.doubleValue();
        }
        return 0;
    }
    public AVAudioSettings setSampleRate(double sampleRate) {
        data.put(SampleRateKey(), NSNumber.valueOf(sampleRate));
        return this;
    }
    public int getNumberOfChannels() {
        if (data.containsKey(NumberOfChannelsKey())) {
            NSNumber val = (NSNumber) data.get(NumberOfChannelsKey());
            return val.intValue();
        }
        return 0;
    }
    public AVAudioSettings setNumberOfChannels(int channels) {
        data.put(NumberOfChannelsKey(), NSNumber.valueOf(channels));
        return this;
    }
    public int getLinearPCMBitDepth() {
        if (data.containsKey(BitDepthKey())) {
            NSNumber val = (NSNumber) data.get(BitDepthKey());
            return val.intValue();
        }
        return 0;
    }
    public AVAudioSettings setLinearPCMBitDepth(int bitDepth) {
        data.put(BitDepthKey(), NSNumber.valueOf(bitDepth));
        return this;
    }
    public boolean isLinearPCMBigEndian() {
        if (data.containsKey(IsBigEndianKey())) {
            NSNumber val = (NSNumber) data.get(IsBigEndianKey());
            return val.booleanValue();
        }
        return false;
    }
    public AVAudioSettings setLinearPCMBigEndian(boolean bigEndian) {
        data.put(IsBigEndianKey(), NSNumber.valueOf(bigEndian));
        return this;
    }
    public boolean isLinearPCMFloat() {
        if (data.containsKey(IsFloatKey())) {
            NSNumber val = (NSNumber) data.get(IsFloatKey());
            return val.booleanValue();
        }
        return false;
    }
    public AVAudioSettings setLinearPCMFloat(boolean isFloat) {
        data.put(IsFloatKey(), NSNumber.valueOf(isFloat));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean isLinearPCMNonInterleaved() {
        if (data.containsKey(IsNonInterleaved())) {
            NSNumber val = (NSNumber) data.get(IsNonInterleaved());
            return val.booleanValue();
        }
        return false;
    }
    public AVAudioSettings setLinearPCMNonInterleaved(boolean nonInterleaved) {
        data.put(IsNonInterleaved(), NSNumber.valueOf(nonInterleaved));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AudioChannelLayout getChannelLayout() {
        if (data.containsKey(ChannelLayoutKey())) {
            NSData val = (NSData) data.get(ChannelLayoutKey());
            val.getStructData(AudioChannelLayout.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVAudioSettings setChannelLayout(AudioChannelLayout channelLayout) {
        data.put(ChannelLayoutKey(), new NSData(channelLayout));
        return this;
    }
    /*<methods>*/
    @GlobalValue(symbol="AVFormatIDKey", optional=true)
    protected static native NSString FormatIDKey();
    @GlobalValue(symbol="AVSampleRateKey", optional=true)
    protected static native NSString SampleRateKey();
    @GlobalValue(symbol="AVNumberOfChannelsKey", optional=true)
    protected static native NSString NumberOfChannelsKey();
    @GlobalValue(symbol="AVLinearPCMBitDepthKey", optional=true)
    protected static native NSString BitDepthKey();
    @GlobalValue(symbol="AVLinearPCMIsBigEndianKey", optional=true)
    protected static native NSString IsBigEndianKey();
    @GlobalValue(symbol="AVLinearPCMIsFloatKey", optional=true)
    protected static native NSString IsFloatKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVLinearPCMIsNonInterleaved", optional=true)
    protected static native NSString IsNonInterleaved();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVChannelLayoutKey", optional=true)
    protected static native NSString ChannelLayoutKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
