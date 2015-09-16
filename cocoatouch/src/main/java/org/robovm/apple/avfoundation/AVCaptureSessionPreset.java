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
@Marshaler(/*<name>*/AVCaptureSessionPreset/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVCaptureSessionPreset/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVCaptureSessionPreset/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static AVCaptureSessionPreset toObject(Class<AVCaptureSessionPreset> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVCaptureSessionPreset.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVCaptureSessionPreset o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVCaptureSessionPreset> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVCaptureSessionPreset> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVCaptureSessionPreset.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVCaptureSessionPreset> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVCaptureSessionPreset o : l) {
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
    public static final AVCaptureSessionPreset Photo = new AVCaptureSessionPreset("Photo");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVCaptureSessionPreset High = new AVCaptureSessionPreset("High");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVCaptureSessionPreset Medium = new AVCaptureSessionPreset("Medium");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVCaptureSessionPreset Low = new AVCaptureSessionPreset("Low");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVCaptureSessionPreset Size352x288 = new AVCaptureSessionPreset("Size352x288");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVCaptureSessionPreset Size640x480 = new AVCaptureSessionPreset("Size640x480");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVCaptureSessionPreset Size1280x720 = new AVCaptureSessionPreset("Size1280x720");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVCaptureSessionPreset Size1920x1080 = new AVCaptureSessionPreset("Size1920x1080");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVCaptureSessionPreset iFrame960x540 = new AVCaptureSessionPreset("iFrame960x540");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVCaptureSessionPreset iFrame1280x720 = new AVCaptureSessionPreset("iFrame1280x720");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVCaptureSessionPreset InputPriority = new AVCaptureSessionPreset("InputPriority");
    /*</constants>*/
    
    private static /*<name>*/AVCaptureSessionPreset/*</name>*/[] values = new /*<name>*/AVCaptureSessionPreset/*</name>*/[] {/*<value_list>*/Photo, High, Medium, Low, Size352x288, Size640x480, Size1280x720, Size1920x1080, iFrame960x540, iFrame1280x720, InputPriority/*</value_list>*/};
    
    /*<name>*/AVCaptureSessionPreset/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVCaptureSessionPreset/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVCaptureSessionPreset/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVCaptureSessionPreset/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVCaptureSessionPresetPhoto", optional=true)
        public static native NSString Photo();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVCaptureSessionPresetHigh", optional=true)
        public static native NSString High();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVCaptureSessionPresetMedium", optional=true)
        public static native NSString Medium();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVCaptureSessionPresetLow", optional=true)
        public static native NSString Low();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="AVCaptureSessionPreset352x288", optional=true)
        public static native NSString Size352x288();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVCaptureSessionPreset640x480", optional=true)
        public static native NSString Size640x480();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVCaptureSessionPreset1280x720", optional=true)
        public static native NSString Size1280x720();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="AVCaptureSessionPreset1920x1080", optional=true)
        public static native NSString Size1920x1080();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="AVCaptureSessionPresetiFrame960x540", optional=true)
        public static native NSString iFrame960x540();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="AVCaptureSessionPresetiFrame1280x720", optional=true)
        public static native NSString iFrame1280x720();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVCaptureSessionPresetInputPriority", optional=true)
        public static native NSString InputPriority();
        /*</values>*/
    }
}
