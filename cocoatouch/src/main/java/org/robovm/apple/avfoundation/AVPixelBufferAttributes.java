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
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVPixelBufferAttributes/*</name>*/ 
    extends /*<extends>*/CVPixelBufferAttributes/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVPixelBufferAttributes.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    protected AVPixelBufferAttributes(CFDictionary data) {
        this.data = data;
    }
    public AVPixelBufferAttributes() {
        data = CFMutableDictionary.create();
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public double getPixelAspectRatioHorizontalSpacing() {
        if (data.containsKey(AVVideoSettings.PixelAspectRatioKey())) {
            CFDictionary val = data.get(AVVideoSettings.PixelAspectRatioKey(), CFDictionary.class);
            if (val.containsKey(AVVideoSettings.PixelAspectRatioHorizontalSpacingKey())) {
                CFNumber n = val.get(AVVideoSettings.PixelAspectRatioHorizontalSpacingKey(), CFNumber.class);
                return n.doubleValue();
            }
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVPixelBufferAttributes setPixelAspectRatioHorizontalSpacing(double spacing) {
        CFDictionary val = null;
        if (data.containsKey(AVVideoSettings.PixelAspectRatioKey())) {
            val = data.get(AVVideoSettings.PixelAspectRatioKey(), CFDictionary.class);
        } else {
            val = CFMutableDictionary.create();
        }
        val.put(AVVideoSettings.PixelAspectRatioHorizontalSpacingKey(), CFNumber.valueOf(spacing));
        data.put(AVVideoSettings.PixelAspectRatioKey(), val);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public double getPixelAspectRatioVerticalSpacing() {
        if (data.containsKey(AVVideoSettings.PixelAspectRatioKey())) {
            CFDictionary val = data.get(AVVideoSettings.PixelAspectRatioKey(), CFDictionary.class);
            if (val.containsKey(AVVideoSettings.PixelAspectRatioVerticalSpacingKey())) {
                CFNumber n = val.get(AVVideoSettings.PixelAspectRatioVerticalSpacingKey(), CFNumber.class);
                return n.doubleValue();
            }
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVPixelBufferAttributes setPixelAspectRatioVerticalSpacing(double spacing) {
        CFDictionary val = null;
        if (data.containsKey(AVVideoSettings.PixelAspectRatioKey())) {
            val = data.get(AVVideoSettings.PixelAspectRatioKey(), CFDictionary.class);
        } else {
            val = CFMutableDictionary.create();
        }
        val.put(AVVideoSettings.PixelAspectRatioVerticalSpacingKey(), CFNumber.valueOf(spacing));
        data.put(AVVideoSettings.PixelAspectRatioKey(), val);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @SuppressWarnings("unchecked")
    public AVVideoCleanAperture getCleanAperture() {
        if (data.containsKey(AVVideoSettings.CleanApertureKey())) {
            NSDictionary<NSString, NSObject> val = data.get(AVVideoSettings.CleanApertureKey(), NSDictionary.class);
            return new AVVideoCleanAperture(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVPixelBufferAttributes setCleanAperture(AVVideoCleanAperture cleanAperture) {
        data.put(AVVideoSettings.CleanApertureKey(), cleanAperture.getDictionary());
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public AVVideoScalingMode getScalingMode() {
        if (data.containsKey(AVVideoSettings.ScalingModeKey())) {
            NSString val = data.get(AVVideoSettings.ScalingModeKey(), NSString.class);
            return AVVideoScalingMode.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public AVPixelBufferAttributes setScalingMode(AVVideoScalingMode scalingMode) {
        data.put(AVVideoSettings.ScalingModeKey(), scalingMode.value());
        return this;
    }
    /*<methods>*/
    /*</methods>*/
}
