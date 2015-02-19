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
@Marshaler(AVVideoSettings.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVVideoSettings/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static AVVideoSettings toObject(Class<AVVideoSettings> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new AVVideoSettings(o);
        }
        @MarshalsPointer
        public static long toNative(AVVideoSettings o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSObject> data;
    
    protected AVVideoSettings(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    public AVVideoSettings() {
        data = new NSMutableDictionary<>();
    }
    /*<bind>*/static { Bro.bind(AVVideoSettings.class); }/*</bind>*/
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
    public CMVideoCodecType getCodec() {
        if (data.containsKey(CodecKey())) {
            NSString val = (NSString) data.get(CodecKey());
            return CMVideoCodecType.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVVideoSettings setCodec(CMVideoCodecType codec) {
        data.put(CodecKey(), new NSString(codec.asFourCharCode()));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getWidth() {
        if (data.containsKey(WidthKey())) {
            NSNumber val = (NSNumber) data.get(WidthKey());
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVVideoSettings setWidth(long width) {
        data.put(WidthKey(), NSNumber.valueOf(width));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getHeight() {
        if (data.containsKey(HeightKey())) {
            NSNumber val = (NSNumber) data.get(HeightKey());
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVVideoSettings setHeight(long height) {
        data.put(HeightKey(), NSNumber.valueOf(height));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @SuppressWarnings("unchecked")
    public double getPixelAspectRatioHorizontalSpacing() {
        if (data.containsKey(PixelAspectRatioKey())) {
            NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>) data.get(PixelAspectRatioKey());
            if (val.containsKey(PixelAspectRatioHorizontalSpacingKey())) {
                NSNumber n = (NSNumber) val.get(PixelAspectRatioHorizontalSpacingKey());
                return n.doubleValue();
            }
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @SuppressWarnings("unchecked")
    public AVVideoSettings setPixelAspectRatioHorizontalSpacing(double spacing) {
        NSDictionary<NSString, NSObject> val = null;
        if (data.containsKey(PixelAspectRatioKey())) {
            val = (NSDictionary<NSString, NSObject>) data.get(PixelAspectRatioKey());
        } else {
            val = new NSMutableDictionary<>();
        }
        val.put(PixelAspectRatioHorizontalSpacingKey(), NSNumber.valueOf(spacing));
        data.put(PixelAspectRatioKey(), val);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @SuppressWarnings("unchecked")
    public double getPixelAspectRatioVerticalSpacing() {
        if (data.containsKey(PixelAspectRatioKey())) {
            NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>) data.get(PixelAspectRatioKey());
            if (val.containsKey(PixelAspectRatioVerticalSpacingKey())) {
                NSNumber n = (NSNumber) val.get(PixelAspectRatioVerticalSpacingKey());
                return n.doubleValue();
            }
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @SuppressWarnings("unchecked")
    public AVVideoSettings setPixelAspectRatioVerticalSpacing(double spacing) {
        NSDictionary<NSString, NSObject> val = null;
        if (data.containsKey(PixelAspectRatioKey())) {
            val = (NSDictionary<NSString, NSObject>) data.get(PixelAspectRatioKey());
        } else {
            val = new NSMutableDictionary<>();
        }
        val.put(PixelAspectRatioVerticalSpacingKey(), NSNumber.valueOf(spacing));
        data.put(PixelAspectRatioKey(), val);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @SuppressWarnings("unchecked")
    public AVVideoCleanAperture getCleanAperture() {
        if (data.containsKey(CleanApertureKey())) {
            NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>) data.get(CleanApertureKey());
            return new AVVideoCleanAperture(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVVideoSettings setCleanAperture(AVVideoCleanAperture cleanAperture) {
        data.put(CleanApertureKey(), cleanAperture.getDictionary());
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public AVVideoScalingMode getScalingMode() {
        if (data.containsKey(ScalingModeKey())) {
            NSString val = (NSString) data.get(ScalingModeKey());
            return AVVideoScalingMode.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public AVVideoSettings setScalingMode(AVVideoScalingMode scalingMode) {
        data.put(ScalingModeKey(), scalingMode.value());
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @SuppressWarnings("unchecked")
    public AVVideoCompressionSettings getCompressionSettings() {
        if (data.containsKey(CompressionPropertiesKey())) {
            NSDictionary<NSString, NSObject> val = (NSDictionary<NSString, NSObject>) data.get(CompressionPropertiesKey());
            return new AVVideoCompressionSettings(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVVideoSettings setCompressionSettings(AVVideoCompressionSettings settings) {
        data.put(CompressionPropertiesKey(), settings.getDictionary());
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean allowsFrameReordering() {
        if (data.containsKey(AllowFrameReorderingKey())) {
            NSNumber val = (NSNumber) data.get(AllowFrameReorderingKey());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVVideoSettings setAllowsFrameReordering(boolean frameReordering) {
        data.put(AllowFrameReorderingKey(), NSNumber.valueOf(frameReordering));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVVideoProfileLevel getProfileLevel() {
        if (data.containsKey(ProfileLevelKey())) {
            NSString val = (NSString) data.get(ProfileLevelKey());
            return AVVideoProfileLevel.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVVideoSettings setProfileLevel(AVVideoProfileLevel profileLevel) {
        data.put(ProfileLevelKey(), profileLevel.value());
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVVideoH264EntropyMode getH264EntropyMode() {
        if (data.containsKey(H264EntropyModeKey())) {
            NSString val = (NSString) data.get(H264EntropyModeKey());
            return AVVideoH264EntropyMode.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVVideoSettings setH264EntropyMode(AVVideoH264EntropyMode mode) {
        data.put(H264EntropyModeKey(), mode.value());
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getExpectedSourceFrameRate() {
        if (data.containsKey(ExpectedSourceFrameRateKey())) {
            NSNumber val = (NSNumber) data.get(ExpectedSourceFrameRateKey());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVVideoSettings setExpectedSourceFrameRate(double frameRate) {
        data.put(ExpectedSourceFrameRateKey(), NSNumber.valueOf(frameRate));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getAverageNonDroppableFrameRate() {
        if (data.containsKey(AverageNonDroppableFrameRateKey())) {
            NSNumber val = (NSNumber) data.get(AverageNonDroppableFrameRateKey());
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVVideoSettings setAverageNonDroppableFrameRate(double frameRate) {
        data.put(AverageNonDroppableFrameRateKey(), NSNumber.valueOf(frameRate));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoCodecKey", optional=true)
    protected static native NSString CodecKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoWidthKey", optional=true)
    protected static native NSString WidthKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoHeightKey", optional=true)
    protected static native NSString HeightKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoPixelAspectRatioKey", optional=true)
    protected static native NSString PixelAspectRatioKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoPixelAspectRatioHorizontalSpacingKey", optional=true)
    protected static native NSString PixelAspectRatioHorizontalSpacingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoPixelAspectRatioVerticalSpacingKey", optional=true)
    protected static native NSString PixelAspectRatioVerticalSpacingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoCleanApertureKey", optional=true)
    protected static native NSString CleanApertureKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVVideoScalingModeKey", optional=true)
    protected static native NSString ScalingModeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoCompressionPropertiesKey", optional=true)
    protected static native NSString CompressionPropertiesKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVVideoAllowFrameReorderingKey", optional=true)
    protected static native NSString AllowFrameReorderingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVVideoProfileLevelKey", optional=true)
    protected static native NSString ProfileLevelKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVVideoH264EntropyModeKey", optional=true)
    protected static native NSString H264EntropyModeKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVVideoExpectedSourceFrameRateKey", optional=true)
    protected static native NSString ExpectedSourceFrameRateKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVVideoAverageNonDroppableFrameRateKey", optional=true)
    protected static native NSString AverageNonDroppableFrameRateKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
