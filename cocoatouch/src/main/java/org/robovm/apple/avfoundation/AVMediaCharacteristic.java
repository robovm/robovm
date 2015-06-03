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
@Marshaler(AVMediaCharacteristic.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMediaCharacteristic/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static AVMediaCharacteristic toObject(Class<AVMediaCharacteristic> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVMediaCharacteristic.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVMediaCharacteristic o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVMediaCharacteristic> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVMediaCharacteristic> list = new ArrayList<>();
            for (NSString str : o) {
                list.add(AVMediaCharacteristic.valueOf(str));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVMediaCharacteristic> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSMutableArray<NSString> array = new NSMutableArray<>();
            for (AVMediaCharacteristic i : l) {
                array.add(i.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVMediaCharacteristic.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMediaCharacteristic Visual = new AVMediaCharacteristic("VisualValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMediaCharacteristic Audible = new AVMediaCharacteristic("AudibleValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMediaCharacteristic Legible = new AVMediaCharacteristic("LegibleValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMediaCharacteristic FrameBased = new AVMediaCharacteristic("FrameBasedValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVMediaCharacteristic IsMainProgramContent = new AVMediaCharacteristic("IsMainProgramContentValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVMediaCharacteristic IsAuxiliaryContent = new AVMediaCharacteristic("IsAuxiliaryContentValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVMediaCharacteristic ContainsOnlyForcedSubtitles = new AVMediaCharacteristic("ContainsOnlyForcedSubtitlesValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVMediaCharacteristic TranscribesSpokenDialogForAccessibility = new AVMediaCharacteristic("TranscribesSpokenDialogForAccessibilityValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVMediaCharacteristic DescribesMusicAndSoundForAccessibility = new AVMediaCharacteristic("DescribesMusicAndSoundForAccessibilityValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVMediaCharacteristic DescribesVideoForAccessibility = new AVMediaCharacteristic("DescribesVideoForAccessibilityValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVMediaCharacteristic EasyToRead = new AVMediaCharacteristic("EasyToReadValue");
    
    private static AVMediaCharacteristic[] values = new AVMediaCharacteristic[] {Visual, Audible, Legible, FrameBased, IsMainProgramContent, IsAuxiliaryContent, 
        ContainsOnlyForcedSubtitles, TranscribesSpokenDialogForAccessibility, DescribesMusicAndSoundForAccessibility, DescribesVideoForAccessibility, EasyToRead};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVMediaCharacteristic(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVMediaCharacteristic valueOf(NSString value) {
        for (AVMediaCharacteristic v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVMediaCharacteristic/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicVisual", optional=true)
    protected static native NSString VisualValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicAudible", optional=true)
    protected static native NSString AudibleValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicLegible", optional=true)
    protected static native NSString LegibleValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicFrameBased", optional=true)
    protected static native NSString FrameBasedValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicIsMainProgramContent", optional=true)
    protected static native NSString IsMainProgramContentValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicIsAuxiliaryContent", optional=true)
    protected static native NSString IsAuxiliaryContentValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicContainsOnlyForcedSubtitles", optional=true)
    protected static native NSString ContainsOnlyForcedSubtitlesValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicTranscribesSpokenDialogForAccessibility", optional=true)
    protected static native NSString TranscribesSpokenDialogForAccessibilityValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicDescribesMusicAndSoundForAccessibility", optional=true)
    protected static native NSString DescribesMusicAndSoundForAccessibilityValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicEasyToRead", optional=true)
    protected static native NSString EasyToReadValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVMediaCharacteristicDescribesVideoForAccessibility", optional=true)
    protected static native NSString DescribesVideoForAccessibilityValue();
    /*</methods>*/
}
