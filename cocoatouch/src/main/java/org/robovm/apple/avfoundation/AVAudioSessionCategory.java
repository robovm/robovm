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
@Marshaler(/*<name>*/AVAudioSessionCategory/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioSessionCategory/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVAudioSessionCategory/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static AVAudioSessionCategory toObject(Class<AVAudioSessionCategory> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVAudioSessionCategory.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVAudioSessionCategory o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVAudioSessionCategory> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVAudioSessionCategory> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVAudioSessionCategory.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVAudioSessionCategory> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVAudioSessionCategory o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    public static final AVAudioSessionCategory Ambient = new AVAudioSessionCategory("Ambient");
    public static final AVAudioSessionCategory SoloAmbient = new AVAudioSessionCategory("SoloAmbient");
    public static final AVAudioSessionCategory Playback = new AVAudioSessionCategory("Playback");
    public static final AVAudioSessionCategory Record = new AVAudioSessionCategory("Record");
    public static final AVAudioSessionCategory PlayAndRecord = new AVAudioSessionCategory("PlayAndRecord");
    public static final AVAudioSessionCategory AudioProcessing = new AVAudioSessionCategory("AudioProcessing");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final AVAudioSessionCategory MultiRoute = new AVAudioSessionCategory("MultiRoute");
    /*</constants>*/
    
    private static /*<name>*/AVAudioSessionCategory/*</name>*/[] values = new /*<name>*/AVAudioSessionCategory/*</name>*/[] {/*<value_list>*/Ambient, SoloAmbient, Playback, Record, PlayAndRecord, AudioProcessing, MultiRoute/*</value_list>*/};
    
    /*<name>*/AVAudioSessionCategory/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVAudioSessionCategory/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVAudioSessionCategory/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVAudioSessionCategory/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        @GlobalValue(symbol="AVAudioSessionCategoryAmbient", optional=true)
        public static native NSString Ambient();
        @GlobalValue(symbol="AVAudioSessionCategorySoloAmbient", optional=true)
        public static native NSString SoloAmbient();
        @GlobalValue(symbol="AVAudioSessionCategoryPlayback", optional=true)
        public static native NSString Playback();
        @GlobalValue(symbol="AVAudioSessionCategoryRecord", optional=true)
        public static native NSString Record();
        @GlobalValue(symbol="AVAudioSessionCategoryPlayAndRecord", optional=true)
        public static native NSString PlayAndRecord();
        @GlobalValue(symbol="AVAudioSessionCategoryAudioProcessing", optional=true)
        public static native NSString AudioProcessing();
        /**
         * @since Available in iOS 6.0 and later.
         */
        @GlobalValue(symbol="AVAudioSessionCategoryMultiRoute", optional=true)
        public static native NSString MultiRoute();
        /*</values>*/
    }
}
