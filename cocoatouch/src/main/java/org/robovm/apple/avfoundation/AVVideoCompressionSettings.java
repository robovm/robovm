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
@Marshaler(/*<name>*/AVVideoCompressionSettings/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVVideoCompressionSettings/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static AVVideoCompressionSettings toObject(Class<AVVideoCompressionSettings> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new AVVideoCompressionSettings(o);
        }
        @MarshalsPointer
        public static long toNative(AVVideoCompressionSettings o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<AVVideoCompressionSettings> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVVideoCompressionSettings> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new AVVideoCompressionSettings(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVVideoCompressionSettings> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (AVVideoCompressionSettings i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    AVVideoCompressionSettings(NSDictionary data) {
        super(data);
    }
    public AVVideoCompressionSettings() {}
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
    public AVVideoCompressionSettings set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 4.0 and later.
     */
    public double getAverageBitRate() {
        if (has(Keys.AverageBitRate())) {
            NSNumber val = (NSNumber) get(Keys.AverageBitRate());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVVideoCompressionSettings setAverageBitRate(double averageBitRate) {
        set(Keys.AverageBitRate(), NSNumber.valueOf(averageBitRate));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public double getQuality() {
        if (has(Keys.Quality())) {
            NSNumber val = (NSNumber) get(Keys.Quality());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public AVVideoCompressionSettings setQuality(double quality) {
        set(Keys.Quality(), NSNumber.valueOf(quality));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public double getMaxKeyFrameInterval() {
        if (has(Keys.MaxKeyFrameInterval())) {
            NSNumber val = (NSNumber) get(Keys.MaxKeyFrameInterval());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVVideoCompressionSettings setMaxKeyFrameInterval(double maxKeyFrameInterval) {
        set(Keys.MaxKeyFrameInterval(), NSNumber.valueOf(maxKeyFrameInterval));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getMaxKeyFrameIntervalDuration() {
        if (has(Keys.MaxKeyFrameIntervalDuration())) {
            NSNumber val = (NSNumber) get(Keys.MaxKeyFrameIntervalDuration());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVVideoCompressionSettings setMaxKeyFrameIntervalDuration(double maxKeyFrameIntervalDuration) {
        set(Keys.MaxKeyFrameIntervalDuration(), NSNumber.valueOf(maxKeyFrameIntervalDuration));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("AVFoundation")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVVideoAverageBitRateKey", optional=true)
        public static native NSString AverageBitRate();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="AVVideoQualityKey", optional=true)
        public static native NSString Quality();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVVideoMaxKeyFrameIntervalKey", optional=true)
        public static native NSString MaxKeyFrameInterval();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVVideoMaxKeyFrameIntervalDurationKey", optional=true)
        public static native NSString MaxKeyFrameIntervalDuration();
    }
    /*</keys>*/
}
