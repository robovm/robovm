/*
 * Copyright (C) 2014 Trillian Mobile AB
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
@Marshaler(AVAudioTimePitchAlgorithm.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioTimePitchAlgorithm/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Marshaler {
        @MarshalsPointer
        public static AVAudioTimePitchAlgorithm toObject(Class<AVAudioTimePitchAlgorithm> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVAudioTimePitchAlgorithm.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVAudioTimePitchAlgorithm o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVAudioTimePitchAlgorithm.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVAudioTimePitchAlgorithm LowQualityZeroLatency = new AVAudioTimePitchAlgorithm("LowQualityZeroLatencyValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVAudioTimePitchAlgorithm TimeDomain = new AVAudioTimePitchAlgorithm("TimeDomainValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVAudioTimePitchAlgorithm Spectral = new AVAudioTimePitchAlgorithm("SpectralValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVAudioTimePitchAlgorithm Varispeed = new AVAudioTimePitchAlgorithm("VarispeedValue");
    
    private static AVAudioTimePitchAlgorithm[] values = new AVAudioTimePitchAlgorithm[] {LowQualityZeroLatency, TimeDomain, Spectral, Varispeed};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVAudioTimePitchAlgorithm(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVAudioTimePitchAlgorithm valueOf(NSString value) {
        for (AVAudioTimePitchAlgorithm v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVAudioTimePitchAlgorithm/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioTimePitchAlgorithmLowQualityZeroLatency", optional=true)
    protected static native NSString LowQualityZeroLatencyValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioTimePitchAlgorithmTimeDomain", optional=true)
    protected static native NSString TimeDomainValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioTimePitchAlgorithmSpectral", optional=true)
    protected static native NSString SpectralValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVAudioTimePitchAlgorithmVarispeed", optional=true)
    protected static native NSString VarispeedValue();
    /*</methods>*/
}
