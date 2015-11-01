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
import org.robovm.apple.coreimage.*;
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
@Marshaler(/*<name>*/AVAudioUnitType/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioUnitType/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVAudioUnitType/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static AVAudioUnitType toObject(Class<AVAudioUnitType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVAudioUnitType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVAudioUnitType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVAudioUnitType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVAudioUnitType> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVAudioUnitType.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVAudioUnitType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVAudioUnitType o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final AVAudioUnitType Output = new AVAudioUnitType("Output");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final AVAudioUnitType MusicDevice = new AVAudioUnitType("MusicDevice");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final AVAudioUnitType MusicEffect = new AVAudioUnitType("MusicEffect");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final AVAudioUnitType FormatConverter = new AVAudioUnitType("FormatConverter");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final AVAudioUnitType Effect = new AVAudioUnitType("Effect");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final AVAudioUnitType Mixer = new AVAudioUnitType("Mixer");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final AVAudioUnitType Panner = new AVAudioUnitType("Panner");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final AVAudioUnitType Generator = new AVAudioUnitType("Generator");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final AVAudioUnitType OfflineEffect = new AVAudioUnitType("OfflineEffect");
    /**
     * @since Available in iOS 9.0 and later.
     */
    public static final AVAudioUnitType MIDIProcessor = new AVAudioUnitType("MIDIProcessor");
    /*</constants>*/
    
    private static /*<name>*/AVAudioUnitType/*</name>*/[] values = new /*<name>*/AVAudioUnitType/*</name>*/[] {/*<value_list>*/Output, MusicDevice, MusicEffect, FormatConverter, Effect, Mixer, Panner, Generator, OfflineEffect, MIDIProcessor/*</value_list>*/};
    
    /*<name>*/AVAudioUnitType/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVAudioUnitType/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVAudioUnitType/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVAudioUnitType/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="AVAudioUnitTypeOutput", optional=true)
        public static native NSString Output();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="AVAudioUnitTypeMusicDevice", optional=true)
        public static native NSString MusicDevice();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="AVAudioUnitTypeMusicEffect", optional=true)
        public static native NSString MusicEffect();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="AVAudioUnitTypeFormatConverter", optional=true)
        public static native NSString FormatConverter();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="AVAudioUnitTypeEffect", optional=true)
        public static native NSString Effect();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="AVAudioUnitTypeMixer", optional=true)
        public static native NSString Mixer();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="AVAudioUnitTypePanner", optional=true)
        public static native NSString Panner();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="AVAudioUnitTypeGenerator", optional=true)
        public static native NSString Generator();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="AVAudioUnitTypeOfflineEffect", optional=true)
        public static native NSString OfflineEffect();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="AVAudioUnitTypeMIDIProcessor", optional=true)
        public static native NSString MIDIProcessor();
        /*</values>*/
    }
}
