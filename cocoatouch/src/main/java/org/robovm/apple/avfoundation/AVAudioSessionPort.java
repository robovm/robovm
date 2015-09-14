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
@Marshaler(/*<name>*/AVAudioSessionPort/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioSessionPort/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVAudioSessionPort/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static AVAudioSessionPort toObject(Class<AVAudioSessionPort> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVAudioSessionPort.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVAudioSessionPort o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVAudioSessionPort> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVAudioSessionPort> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVAudioSessionPort.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVAudioSessionPort> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVAudioSessionPort o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort LineIn = new AVAudioSessionPort("LineIn");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort BuiltInMic = new AVAudioSessionPort("BuiltInMic");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort HeadsetMic = new AVAudioSessionPort("HeadsetMic");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort LineOut = new AVAudioSessionPort("LineOut");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort Headphones = new AVAudioSessionPort("Headphones");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort BluetoothA2DP = new AVAudioSessionPort("BluetoothA2DP");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort BuiltInReceiver = new AVAudioSessionPort("BuiltInReceiver");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort BuiltInSpeaker = new AVAudioSessionPort("BuiltInSpeaker");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort HDMI = new AVAudioSessionPort("HDMI");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort AirPlay = new AVAudioSessionPort("AirPlay");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVAudioSessionPort BluetoothLE = new AVAudioSessionPort("BluetoothLE");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort BluetoothHFP = new AVAudioSessionPort("BluetoothHFP");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort USBAudio = new AVAudioSessionPort("USBAudio");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVAudioSessionPort CarAudio = new AVAudioSessionPort("CarAudio");
    /*</constants>*/
    
    private static /*<name>*/AVAudioSessionPort/*</name>*/[] values = new /*<name>*/AVAudioSessionPort/*</name>*/[] {/*<value_list>*/LineIn, BuiltInMic, HeadsetMic, LineOut, Headphones, BluetoothA2DP, BuiltInReceiver, BuiltInSpeaker, HDMI, AirPlay, BluetoothLE, BluetoothHFP, USBAudio, CarAudio/*</value_list>*/};
    
    /*<name>*/AVAudioSessionPort/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVAudioSessionPort/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVAudioSessionPort/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVAudioSessionPort/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionPortLineIn", optional=true)
        public static native NSString LineIn();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionPortBuiltInMic", optional=true)
        public static native NSString BuiltInMic();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionPortHeadsetMic", optional=true)
        public static native NSString HeadsetMic();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionPortLineOut", optional=true)
        public static native NSString LineOut();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionPortHeadphones", optional=true)
        public static native NSString Headphones();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionPortBluetoothA2DP", optional=true)
        public static native NSString BluetoothA2DP();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionPortBuiltInReceiver", optional=true)
        public static native NSString BuiltInReceiver();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionPortBuiltInSpeaker", optional=true)
        public static native NSString BuiltInSpeaker();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionPortHDMI", optional=true)
        public static native NSString HDMI();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionPortAirPlay", optional=true)
        public static native NSString AirPlay();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionPortBluetoothLE", optional=true)
        public static native NSString BluetoothLE();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionPortBluetoothHFP", optional=true)
        public static native NSString BluetoothHFP();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionPortUSBAudio", optional=true)
        public static native NSString USBAudio();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionPortCarAudio", optional=true)
        public static native NSString CarAudio();
        /*</values>*/
    }
}
