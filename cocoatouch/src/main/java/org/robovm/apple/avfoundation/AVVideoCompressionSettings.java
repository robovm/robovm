/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
@Marshaler(AVVideoCompressionSettings.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVVideoCompressionSettings/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static AVVideoCompressionSettings toObject(Class<AVVideoCompressionSettings> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
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
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected AVVideoCompressionSettings(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public AVVideoCompressionSettings() {
        data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(AVVideoCompressionSettings.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    
    /**
     * @since Available in iOS 4.0 and later.
     */
    public double getAverageBitRate() {
        if (data.containsKey(AverageBitRateKey())) {
            NSNumber val = (NSNumber) data.get(AverageBitRateKey());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVVideoCompressionSettings setAverageBitRate(double bitRate) {
        data.put(AverageBitRateKey(), NSNumber.valueOf(bitRate));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public double getQuality() {
        if (data.containsKey(QualityKey())) {
            NSNumber val = (NSNumber) data.get(QualityKey());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public AVVideoCompressionSettings setQuality(double quality) {
        data.put(QualityKey(), NSNumber.valueOf(quality));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public double getMaxKeyFrameInterval() {
        if (data.containsKey(MaxKeyFrameIntervalKey())) {
            NSNumber val = (NSNumber) data.get(MaxKeyFrameIntervalKey());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVVideoCompressionSettings setMaxKeyFrameInterval(double interval) {
        data.put(MaxKeyFrameIntervalKey(), NSNumber.valueOf(interval));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getMaxKeyFrameIntervalDuration() {
        if (data.containsKey(MaxKeyFrameIntervalDurationKey())) {
            NSNumber val = (NSNumber) data.get(MaxKeyFrameIntervalDurationKey());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVVideoCompressionSettings setMaxKeyFrameIntervalDuration(double duration) {
        data.put(MaxKeyFrameIntervalDurationKey(), NSNumber.valueOf(duration));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoAverageBitRateKey", optional=true)
    protected static native NSString AverageBitRateKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVVideoQualityKey", optional=true)
    protected static native NSString QualityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoMaxKeyFrameIntervalKey", optional=true)
    protected static native NSString MaxKeyFrameIntervalKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVVideoMaxKeyFrameIntervalDurationKey", optional=true)
    protected static native NSString MaxKeyFrameIntervalDurationKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
