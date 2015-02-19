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
@Marshaler(AVCaptureSessionPreset.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVCaptureSessionPreset/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

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
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVCaptureSessionPreset.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVCaptureSessionPreset Photo = new AVCaptureSessionPreset("PhotoValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVCaptureSessionPreset High = new AVCaptureSessionPreset("HighValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVCaptureSessionPreset Medium = new AVCaptureSessionPreset("MediumValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVCaptureSessionPreset Low = new AVCaptureSessionPreset("LowValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVCaptureSessionPreset _352x288 = new AVCaptureSessionPreset("_352x288Value");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVCaptureSessionPreset _640x480 = new AVCaptureSessionPreset("_640x480Value");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVCaptureSessionPreset _1280x720 = new AVCaptureSessionPreset("_1280x720Value");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVCaptureSessionPreset _1920x1080 = new AVCaptureSessionPreset("_1920x1080Value");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVCaptureSessionPreset iFrame960x540 = new AVCaptureSessionPreset("iFrame960x540Value");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final AVCaptureSessionPreset iFrame1280x720 = new AVCaptureSessionPreset("iFrame1280x720Value");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVCaptureSessionPreset InputPriority = new AVCaptureSessionPreset("InputPriorityValue");
    
    private static AVCaptureSessionPreset[] values = new AVCaptureSessionPreset[] {Photo, High, Medium, Low, _352x288, 
        _640x480, _1280x720, _1920x1080, iFrame960x540, iFrame1280x720, InputPriority};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVCaptureSessionPreset(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVCaptureSessionPreset valueOf(NSString value) {
        for (AVCaptureSessionPreset v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVCaptureSessionPreset/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPresetPhoto", optional=true)
    protected static native NSString PhotoValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPresetHigh", optional=true)
    protected static native NSString HighValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPresetMedium", optional=true)
    protected static native NSString MediumValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPresetLow", optional=true)
    protected static native NSString LowValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPreset352x288", optional=true)
    protected static native NSString _352x288Value();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPreset640x480", optional=true)
    protected static native NSString _640x480Value();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPreset1280x720", optional=true)
    protected static native NSString _1280x720Value();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPreset1920x1080", optional=true)
    protected static native NSString _1920x1080Value();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPresetiFrame960x540", optional=true)
    protected static native NSString iFrame960x540Value();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPresetiFrame1280x720", optional=true)
    protected static native NSString iFrame1280x720Value();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVCaptureSessionPresetInputPriority", optional=true)
    protected static native NSString InputPriorityValue();
    /*</methods>*/
}
