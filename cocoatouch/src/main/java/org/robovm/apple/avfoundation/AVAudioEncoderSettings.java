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
@Marshaler(AVAudioEncoderSettings.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class AVAudioEncoderSettings 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static AVAudioEncoderSettings toObject(Class<AVAudioEncoderSettings> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new AVAudioEncoderSettings(o);
        }
        @MarshalsPointer
        public static long toNative(AVAudioEncoderSettings o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected AVAudioEncoderSettings(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public AVAudioEncoderSettings() {
        data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(AVAudioEncoderSettings.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    
    public AVAudioQuality getAudioQuality() {
        if (data.containsKey(AudioQualityKey())) {
            NSNumber val = (NSNumber) data.get(AudioQualityKey());
            return AVAudioQuality.valueOf(val.longValue());
        }
        return null;
    }
    public AVAudioEncoderSettings setAudioQuality(AVAudioQuality quality) {
        data.put(AudioQualityKey(), NSNumber.valueOf(quality.value()));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVAudioQuality getAudioQualityForVBR() {
        if (data.containsKey(AudioQualityForVBRKey())) {
            NSNumber val = (NSNumber) data.get(AudioQualityForVBRKey());
            return AVAudioQuality.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVAudioEncoderSettings setAudioQualityForVBR(AVAudioQuality quality) {
        data.put(AudioQualityForVBRKey(), NSNumber.valueOf(quality.value()));
        return this;
    }
    public int getBitRate() {
        if (data.containsKey(BitRateKey())) {
            NSNumber val = (NSNumber) data.get(BitRateKey());
            return val.intValue();
        }
        return 0;
    }
    public AVAudioEncoderSettings setBitRate(int bitRate) {
        data.put(BitRateKey(), NSNumber.valueOf(bitRate));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getBitRatePerChannel() {
        if (data.containsKey(BitRatePerChannelKey())) {
            NSNumber val = (NSNumber) data.get(BitRatePerChannelKey());
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVAudioEncoderSettings setBitRatePerChannel(int bitRate) {
        data.put(BitRatePerChannelKey(), NSNumber.valueOf(bitRate));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVAudioBitRateStrategy getBitRateStrategy() {
        if (data.containsKey(BitRateStrategyKey())) {
            NSString val = (NSString) data.get(BitRateStrategyKey());
            return AVAudioBitRateStrategy.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVAudioEncoderSettings setBitRateStrategy(AVAudioBitRateStrategy bitRateStrategy) {
        data.put(BitRateStrategyKey(), bitRateStrategy.value());
        return this;
    }
    public int getBitDepthHint() {
        if (data.containsKey(BitDepthHintKey())) {
            NSNumber val = (NSNumber) data.get(BitDepthHintKey());
            return val.intValue();
        }
        return 0;
    }
    public AVAudioEncoderSettings setBitDepthHint(int bitDepth) {
        data.put(BitDepthHintKey(), NSNumber.valueOf(bitDepth));
        return this;
    } 
    /*<methods>*/
    @GlobalValue(symbol="AVEncoderAudioQualityKey", optional=true)
    protected static native NSString AudioQualityKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVEncoderAudioQualityForVBRKey", optional=true)
    protected static native NSString AudioQualityForVBRKey();
    @GlobalValue(symbol="AVEncoderBitRateKey", optional=true)
    protected static native NSString BitRateKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVEncoderBitRatePerChannelKey", optional=true)
    protected static native NSString BitRatePerChannelKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVEncoderBitRateStrategyKey", optional=true)
    protected static native NSString BitRateStrategyKey();
    @GlobalValue(symbol="AVEncoderBitDepthHintKey", optional=true)
    protected static native NSString BitDepthHintKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
