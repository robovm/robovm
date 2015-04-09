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
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(AVAssetImageGeneratorApertureMode.Marshaler.class)
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAssetImageGeneratorApertureMode/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static AVAssetImageGeneratorApertureMode toObject(Class<AVAssetImageGeneratorApertureMode> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVAssetImageGeneratorApertureMode.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVAssetImageGeneratorApertureMode o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVAssetImageGeneratorApertureMode.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVAssetImageGeneratorApertureMode CleanAperture = new AVAssetImageGeneratorApertureMode("CleanApertureValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVAssetImageGeneratorApertureMode ProductionAperture = new AVAssetImageGeneratorApertureMode("ProductionApertureValue");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVAssetImageGeneratorApertureMode EncodedPixels = new AVAssetImageGeneratorApertureMode("EncodedPixelsValue");
    
    private static AVAssetImageGeneratorApertureMode[] values = new AVAssetImageGeneratorApertureMode[] {CleanAperture, ProductionAperture, EncodedPixels};
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private AVAssetImageGeneratorApertureMode(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static AVAssetImageGeneratorApertureMode valueOf(NSString value) {
        for (AVAssetImageGeneratorApertureMode v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVAssetImageGeneratorApertureMode/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetImageGeneratorApertureModeCleanAperture", optional=true)
    protected static native NSString CleanApertureValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetImageGeneratorApertureModeProductionAperture", optional=true)
    protected static native NSString ProductionApertureValue();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVAssetImageGeneratorApertureModeEncodedPixels", optional=true)
    protected static native NSString EncodedPixelsValue();
    /*</methods>*/
}
