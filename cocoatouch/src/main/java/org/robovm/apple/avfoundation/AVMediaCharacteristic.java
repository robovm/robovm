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
@Marshaler(/*<name>*/AVMediaCharacteristic/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMediaCharacteristic/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVMediaCharacteristic/*</name>*/.class); }

    /*<marshalers>*/
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
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVMediaCharacteristic> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVMediaCharacteristic.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVMediaCharacteristic> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVMediaCharacteristic o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMediaCharacteristic Visual = new AVMediaCharacteristic("Visual");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMediaCharacteristic Audible = new AVMediaCharacteristic("Audible");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMediaCharacteristic Legible = new AVMediaCharacteristic("Legible");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVMediaCharacteristic FrameBased = new AVMediaCharacteristic("FrameBased");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVMediaCharacteristic IsMainProgramContent = new AVMediaCharacteristic("IsMainProgramContent");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVMediaCharacteristic IsAuxiliaryContent = new AVMediaCharacteristic("IsAuxiliaryContent");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVMediaCharacteristic ContainsOnlyForcedSubtitles = new AVMediaCharacteristic("ContainsOnlyForcedSubtitles");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVMediaCharacteristic TranscribesSpokenDialogForAccessibility = new AVMediaCharacteristic("TranscribesSpokenDialogForAccessibility");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVMediaCharacteristic DescribesMusicAndSoundForAccessibility = new AVMediaCharacteristic("DescribesMusicAndSoundForAccessibility");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVMediaCharacteristic EasyToRead = new AVMediaCharacteristic("EasyToRead");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVMediaCharacteristic DescribesVideoForAccessibility = new AVMediaCharacteristic("DescribesVideoForAccessibility");
    /*</constants>*/
    
    private static /*<name>*/AVMediaCharacteristic/*</name>*/[] values = new /*<name>*/AVMediaCharacteristic/*</name>*/[] {/*<value_list>*/Visual, Audible, Legible, FrameBased, IsMainProgramContent, IsAuxiliaryContent, ContainsOnlyForcedSubtitles, TranscribesSpokenDialogForAccessibility, DescribesMusicAndSoundForAccessibility, EasyToRead, DescribesVideoForAccessibility/*</value_list>*/};
    
    /*<name>*/AVMediaCharacteristic/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVMediaCharacteristic/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVMediaCharacteristic/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVMediaCharacteristic/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMediaCharacteristicVisual", optional=true)
        public static native NSString Visual();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMediaCharacteristicAudible", optional=true)
        public static native NSString Audible();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMediaCharacteristicLegible", optional=true)
        public static native NSString Legible();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVMediaCharacteristicFrameBased", optional=true)
        public static native NSString FrameBased();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="AVMediaCharacteristicIsMainProgramContent", optional=true)
        public static native NSString IsMainProgramContent();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="AVMediaCharacteristicIsAuxiliaryContent", optional=true)
        public static native NSString IsAuxiliaryContent();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="AVMediaCharacteristicContainsOnlyForcedSubtitles", optional=true)
        public static native NSString ContainsOnlyForcedSubtitles();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="AVMediaCharacteristicTranscribesSpokenDialogForAccessibility", optional=true)
        public static native NSString TranscribesSpokenDialogForAccessibility();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="AVMediaCharacteristicDescribesMusicAndSoundForAccessibility", optional=true)
        public static native NSString DescribesMusicAndSoundForAccessibility();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="AVMediaCharacteristicEasyToRead", optional=true)
        public static native NSString EasyToRead();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="AVMediaCharacteristicDescribesVideoForAccessibility", optional=true)
        public static native NSString DescribesVideoForAccessibility();
        /*</values>*/
    }
}
