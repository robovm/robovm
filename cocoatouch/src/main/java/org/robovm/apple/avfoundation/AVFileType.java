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
@Marshaler(/*<name>*/AVFileType/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVFileType/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVFileType/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static AVFileType toObject(Class<AVFileType> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVFileType.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVFileType o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVFileType> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVFileType> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVFileType.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVFileType> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVFileType o : l) {
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
    public static final AVFileType QuickTimeMovie = new AVFileType("QuickTimeMovie");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVFileType MPEG4 = new AVFileType("MPEG4");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVFileType AppleM4V = new AVFileType("AppleM4V");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVFileType AppleM4A = new AVFileType("AppleM4A");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVFileType _3GPP = new AVFileType("_3GPP");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVFileType _3GPP2 = new AVFileType("_3GPP2");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVFileType CoreAudioFormat = new AVFileType("CoreAudioFormat");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVFileType WAVE = new AVFileType("WAVE");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVFileType AIFF = new AVFileType("AIFF");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVFileType AIFC = new AVFileType("AIFC");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVFileType AMR = new AVFileType("AMR");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVFileType MPEGLayer3 = new AVFileType("MPEGLayer3");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVFileType SunAU = new AVFileType("SunAU");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVFileType AC3 = new AVFileType("AC3");
    /*</constants>*/
    
    private static /*<name>*/AVFileType/*</name>*/[] values = new /*<name>*/AVFileType/*</name>*/[] {/*<value_list>*/QuickTimeMovie, MPEG4, AppleM4V, AppleM4A, _3GPP, _3GPP2, CoreAudioFormat, WAVE, AIFF, AIFC, AMR, MPEGLayer3, SunAU, AC3/*</value_list>*/};
    
    /*<name>*/AVFileType/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVFileType/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVFileType/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVFileType/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVFileTypeQuickTimeMovie", optional=true)
        public static native NSString QuickTimeMovie();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVFileTypeMPEG4", optional=true)
        public static native NSString MPEG4();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVFileTypeAppleM4V", optional=true)
        public static native NSString AppleM4V();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVFileTypeAppleM4A", optional=true)
        public static native NSString AppleM4A();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVFileType3GPP", optional=true)
        public static native NSString _3GPP();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVFileType3GPP2", optional=true)
        public static native NSString _3GPP2();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVFileTypeCoreAudioFormat", optional=true)
        public static native NSString CoreAudioFormat();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVFileTypeWAVE", optional=true)
        public static native NSString WAVE();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVFileTypeAIFF", optional=true)
        public static native NSString AIFF();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVFileTypeAIFC", optional=true)
        public static native NSString AIFC();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVFileTypeAMR", optional=true)
        public static native NSString AMR();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVFileTypeMPEGLayer3", optional=true)
        public static native NSString MPEGLayer3();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVFileTypeSunAU", optional=true)
        public static native NSString SunAU();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVFileTypeAC3", optional=true)
        public static native NSString AC3();
        /*</values>*/
    }
}
