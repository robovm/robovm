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
@Marshaler(AVAudioSessionPort.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioSessionPort/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

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
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVAudioSessionPort.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort LineIn = new AVAudioSessionPort("LineInValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort BuiltInMic = new AVAudioSessionPort("BuiltInMicValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort HeadsetMic = new AVAudioSessionPort("HeadsetMicValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort LineOut = new AVAudioSessionPort("LineOutValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort Headphones = new AVAudioSessionPort("HeadphonesValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort BluetoothA2DP = new AVAudioSessionPort("BluetoothA2DPValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort BuiltInReceiver = new AVAudioSessionPort("BuiltInReceiverValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort BuiltInSpeaker = new AVAudioSessionPort("BuiltInSpeakerValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort HDMI = new AVAudioSessionPort("HDMIValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort AirPlay = new AVAudioSessionPort("AirPlayValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort BluetoothHFP = new AVAudioSessionPort("BluetoothHFPValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionPort USBAudio = new AVAudioSessionPort("USBAudioValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVAudioSessionPort CarAudio = new AVAudioSessionPort("CarAudioValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVAudioSessionPort BluetoothLE = new AVAudioSessionPort("BluetoothLEValue");
    
    private static AVAudioSessionPort[] values = new AVAudioSessionPort[] {LineIn, BuiltInMic, HeadsetMic, LineOut, 
        Headphones, BluetoothA2DP, BuiltInReceiver, BuiltInSpeaker, HDMI, AirPlay, BluetoothHFP, USBAudio, CarAudio, 
        BluetoothLE};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVAudioSessionPort(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVAudioSessionPort valueOf(NSString value) {
        for (AVAudioSessionPort v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVAudioSessionPort/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortLineIn", optional=true)
    protected static native NSString LineInValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortBuiltInMic", optional=true)
    protected static native NSString BuiltInMicValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortHeadsetMic", optional=true)
    protected static native NSString HeadsetMicValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortLineOut", optional=true)
    protected static native NSString LineOutValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortHeadphones", optional=true)
    protected static native NSString HeadphonesValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortBluetoothA2DP", optional=true)
    protected static native NSString BluetoothA2DPValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortBuiltInReceiver", optional=true)
    protected static native NSString BuiltInReceiverValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortBuiltInSpeaker", optional=true)
    protected static native NSString BuiltInSpeakerValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortHDMI", optional=true)
    protected static native NSString HDMIValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortAirPlay", optional=true)
    protected static native NSString AirPlayValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortBluetoothLE", optional=true)
    protected static native NSString BluetoothLEValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortBluetoothHFP", optional=true)
    protected static native NSString BluetoothHFPValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortUSBAudio", optional=true)
    protected static native NSString USBAudioValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioSessionPortCarAudio", optional=true)
    protected static native NSString CarAudioValue();
    /*</methods>*/
}
