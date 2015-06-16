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
import org.robovm.apple.avfoundation.AVVideoSettings.Keys;
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
    public AVPixelAspectRatio getPixelAspectRatio() {
        if (data.containsKey(AVVideoSettings.Keys.PixelAspectRatio())) {
            NSDictionary<?, ?> val = data.get(AVVideoSettings.Keys.PixelAspectRatio(), NSDictionary.class);
            AVPixelAspectRatio result = new AVPixelAspectRatio(val.getLong(AVPixelAspectRatio.HorizontalSpacing(), 0), val.getLong(AVPixelAspectRatio.VerticalSpacing(), 0));
            return result;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVPixelBufferAttributes setPixelAspectRatio(AVPixelAspectRatio pixelAspectRatio) {
        NSDictionary<NSString, NSObject> val = new NSMutableDictionary<>();
        val.put(AVPixelAspectRatio.HorizontalSpacing(), pixelAspectRatio.getHorizontalSpacing());
        val.put(AVPixelAspectRatio.VerticalSpacing(), pixelAspectRatio.getVerticalSpacing());
        data.put(AVVideoSettings.Keys.PixelAspectRatio(), val);
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @SuppressWarnings("unchecked")
    public AVVideoCleanAperture getCleanAperture() {
        if (data.containsKey(AVVideoSettings.Keys.CleanAperture())) {
            NSDictionary<NSString, NSObject> val = data.get(AVVideoSettings.Keys.CleanAperture(), NSDictionary.class);
            return new AVVideoCleanAperture(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public AVPixelBufferAttributes setCleanAperture(AVVideoCleanAperture cleanAperture) {
        data.put(AVVideoSettings.Keys.CleanAperture(), cleanAperture.getDictionary());
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public AVVideoScalingMode getScalingMode() {
        if (data.containsKey(AVVideoSettings.Keys.ScalingMode())) {
            NSString val = data.get(AVVideoSettings.Keys.ScalingMode(), NSString.class);
            return AVVideoScalingMode.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public AVPixelBufferAttributes setScalingMode(AVVideoScalingMode scalingMode) {
        data.put(AVVideoSettings.Keys.ScalingMode(), scalingMode.value());
        return this;
    }
    /*<methods>*/
    /*</methods>*/
}
