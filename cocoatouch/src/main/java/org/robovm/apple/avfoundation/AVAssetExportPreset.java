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
@Marshaler(AVAssetExportPreset.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAssetExportPreset/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static AVAssetExportPreset toObject(Class<AVAssetExportPreset> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVAssetExportPreset.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVAssetExportPreset o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVAssetExportPreset> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVAssetExportPreset> list = new ArrayList<>();
            for (NSString str : o) {
                list.add(AVAssetExportPreset.valueOf(str));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVAssetExportPreset> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSString> array = new NSMutableArray<>();
            for (AVAssetExportPreset i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVAssetExportPreset.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVAssetExportPreset LowQuality = new AVAssetExportPreset("LowQualityValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVAssetExportPreset MediumQuality = new AVAssetExportPreset("MediumQualityValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVAssetExportPreset HighestQuality = new AVAssetExportPreset("HighestQualityValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVAssetExportPreset Size640x480 = new AVAssetExportPreset("Size640x480Value");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVAssetExportPreset Size960x540 = new AVAssetExportPreset("Size960x540Value");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVAssetExportPreset Size1280x720 = new AVAssetExportPreset("Size1280x720Value");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVAssetExportPreset Size1920x1080 = new AVAssetExportPreset("Size1920x1080Value");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVAssetExportPreset AppleM4A = new AVAssetExportPreset("AppleM4AValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVAssetExportPreset Passthrough = new AVAssetExportPreset("PassthroughValue");
    
    private static AVAssetExportPreset[] values = new AVAssetExportPreset[] {LowQuality, MediumQuality, HighestQuality, 
        Size640x480, Size960x540, Size1280x720, Size1920x1080, AppleM4A, Passthrough};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVAssetExportPreset(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVAssetExportPreset valueOf(NSString value) {
        for (AVAssetExportPreset v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVAssetExportPreset/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetExportPresetLowQuality", optional=true)
    protected static native NSString LowQualityValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetExportPresetMediumQuality", optional=true)
    protected static native NSString MediumQualityValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetExportPresetHighestQuality", optional=true)
    protected static native NSString HighestQualityValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetExportPreset640x480", optional=true)
    protected static native NSString Size640x480Value();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetExportPreset960x540", optional=true)
    protected static native NSString Size960x540Value();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetExportPreset1280x720", optional=true)
    protected static native NSString Size1280x720Value();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVAssetExportPreset1920x1080", optional=true)
    protected static native NSString Size1920x1080Value();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetExportPresetAppleM4A", optional=true)
    protected static native NSString AppleM4AValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetExportPresetPassthrough", optional=true)
    protected static native NSString PassthroughValue();
    /*</methods>*/
}
