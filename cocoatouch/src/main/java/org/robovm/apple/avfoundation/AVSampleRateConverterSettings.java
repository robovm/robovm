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
@Marshaler(/*<name>*/AVSampleRateConverterSettings/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVSampleRateConverterSettings/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static AVSampleRateConverterSettings toObject(Class<AVSampleRateConverterSettings> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new AVSampleRateConverterSettings(o);
        }
        @MarshalsPointer
        public static long toNative(AVSampleRateConverterSettings o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<AVSampleRateConverterSettings> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVSampleRateConverterSettings> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new AVSampleRateConverterSettings(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVSampleRateConverterSettings> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (AVSampleRateConverterSettings i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    AVSampleRateConverterSettings(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    public AVSampleRateConverterSettings() {}
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
    public AVSampleRateConverterSettings set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVSampleRateConverterAlgorithm getAlgorithm() {
        if (has(Keys.Algorithm())) {
            NSString val = (NSString) get(Keys.Algorithm());
            return AVSampleRateConverterAlgorithm.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public AVSampleRateConverterSettings setAlgorithm(AVSampleRateConverterAlgorithm algorithm) {
        set(Keys.Algorithm(), algorithm.value());
        return this;
    }
    public AVAudioQuality getAudioQuality() {
        if (has(Keys.AudioQuality())) {
            NSNumber val = (NSNumber) get(Keys.AudioQuality());
            return AVAudioQuality.valueOf(val.longValue());
        }
        return null;
    }
    public AVSampleRateConverterSettings setAudioQuality(AVAudioQuality audioQuality) {
        set(Keys.AudioQuality(), NSNumber.valueOf(audioQuality.value()));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("AVFoundation")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVSampleRateConverterAlgorithmKey", optional=true)
        public static native NSString Algorithm();
        @GlobalValue(symbol="AVSampleRateConverterAudioQualityKey", optional=true)
        public static native NSString AudioQuality();
    }
    /*</keys>*/
}
