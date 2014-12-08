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
@Marshaler(AVFileType.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVFileType/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
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

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVFileType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVFileType QuickTimeMovie = new AVFileType("QuickTimeMovieValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVFileType MPEG4 = new AVFileType("MPEG4Value");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVFileType AppleM4V = new AVFileType("AppleM4VValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVFileType AppleM4A = new AVFileType("AppleM4AValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVFileType _3GPP = new AVFileType("_3GPPValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVFileType CoreAudioFormat = new AVFileType("CoreAudioFormatValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVFileType WAVE = new AVFileType("WAVEValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVFileType AIFF = new AVFileType("AIFFValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVFileType AIFC = new AVFileType("AIFCValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVFileType AMR = new AVFileType("AMRValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVFileType _3GPP2 = new AVFileType("_3GPP2Value");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVFileType MPEGLayer3 = new AVFileType("MPEGLayer3Value");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVFileType SunAU = new AVFileType("SunAUValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVFileType AC3 = new AVFileType("AC3Value");
    
    private static AVFileType[] values = new AVFileType[] {QuickTimeMovie, MPEG4, AppleM4V, AppleM4A, _3GPP, CoreAudioFormat, 
        WAVE, AIFF, AIFC, AMR, _3GPP2, MPEGLayer3, SunAU, AC3};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVFileType(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVFileType valueOf(NSString value) {
        for (AVFileType v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVFileType/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeQuickTimeMovie", optional=true)
    protected static native NSString QuickTimeMovieValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeMPEG4", optional=true)
    protected static native NSString MPEG4Value();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeAppleM4V", optional=true)
    protected static native NSString AppleM4VValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeAppleM4A", optional=true)
    protected static native NSString AppleM4AValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeCoreAudioFormat", optional=true)
    protected static native NSString CoreAudioFormatValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeWAVE", optional=true)
    protected static native NSString WAVEValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeAIFF", optional=true)
    protected static native NSString AIFFValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeAIFC", optional=true)
    protected static native NSString AIFCValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeAMR", optional=true)
    protected static native NSString AMRValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeMPEGLayer3", optional=true)
    protected static native NSString MPEGLayer3Value();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeSunAU", optional=true)
    protected static native NSString SunAUValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeAC3", optional=true)
    protected static native NSString AC3Value();
    /*</methods>*/
}
