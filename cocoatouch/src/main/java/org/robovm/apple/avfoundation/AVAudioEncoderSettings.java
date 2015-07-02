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
@Marshaler(/*<name>*/AVAudioEncoderSettings/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioEncoderSettings/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
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
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<AVAudioEncoderSettings> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVAudioEncoderSettings> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new AVAudioEncoderSettings(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVAudioEncoderSettings> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (AVAudioEncoderSettings i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    AVAudioEncoderSettings(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    public AVAudioEncoderSettings() {}
    /*</constructors>*/

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
    public AVAudioEncoderSettings set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    public AVAudioQuality getAudioQuality() {
        if (has(Keys.AudioQuality())) {
            NSNumber val = (NSNumber) get(Keys.AudioQuality());
            return AVAudioQuality.valueOf(val.longValue());
        }
        return null;
    }
    public AVAudioEncoderSettings setAudioQuality(AVAudioQuality audioQuality) {
        set(Keys.AudioQuality(), NSNumber.valueOf(audioQuality.value()));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVAudioQuality getAudioQualityForVBR() {
        if (has(Keys.AudioQualityForVBR())) {
            NSNumber val = (NSNumber) get(Keys.AudioQualityForVBR());
            return AVAudioQuality.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVAudioEncoderSettings setAudioQualityForVBR(AVAudioQuality audioQualityForVBR) {
        set(Keys.AudioQualityForVBR(), NSNumber.valueOf(audioQualityForVBR.value()));
        return this;
    }
    public int getBitRate() {
        if (has(Keys.BitRate())) {
            NSNumber val = (NSNumber) get(Keys.BitRate());
            return val.intValue();
        }
        return 0;
    }
    public AVAudioEncoderSettings setBitRate(int bitRate) {
        set(Keys.BitRate(), NSNumber.valueOf(bitRate));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getBitRatePerChannel() {
        if (has(Keys.BitRatePerChannel())) {
            NSNumber val = (NSNumber) get(Keys.BitRatePerChannel());
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVAudioEncoderSettings setBitRatePerChannel(int bitRatePerChannel) {
        set(Keys.BitRatePerChannel(), NSNumber.valueOf(bitRatePerChannel));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVAudioBitRateStrategy getBitRateStrategy() {
        if (has(Keys.BitRateStrategy())) {
            NSString val = (NSString) get(Keys.BitRateStrategy());
            return AVAudioBitRateStrategy.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVAudioEncoderSettings setBitRateStrategy(AVAudioBitRateStrategy bitRateStrategy) {
        set(Keys.BitRateStrategy(), bitRateStrategy.value());
        return this;
    }
    public int getBitDepthHint() {
        if (has(Keys.BitDepthHint())) {
            NSNumber val = (NSNumber) get(Keys.BitDepthHint());
            return val.intValue();
        }
        return 0;
    }
    public AVAudioEncoderSettings setBitDepthHint(int bitDepthHint) {
        set(Keys.BitDepthHint(), NSNumber.valueOf(bitDepthHint));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("AVFoundation")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        @GlobalValue(symbol="AVEncoderAudioQualityKey", optional=true)
        public static native NSString AudioQuality();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVEncoderAudioQualityForVBRKey", optional=true)
        public static native NSString AudioQualityForVBR();
        @GlobalValue(symbol="AVEncoderBitRateKey", optional=true)
        public static native NSString BitRate();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVEncoderBitRatePerChannelKey", optional=true)
        public static native NSString BitRatePerChannel();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVEncoderBitRateStrategyKey", optional=true)
        public static native NSString BitRateStrategy();
        @GlobalValue(symbol="AVEncoderBitDepthHintKey", optional=true)
        public static native NSString BitDepthHint();
    }
    /*</keys>*/
}
