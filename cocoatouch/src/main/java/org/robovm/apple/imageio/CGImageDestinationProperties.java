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
package org.robovm.apple.imageio;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CGImageDestinationProperties.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImageDestinationProperties/*</name>*/ 
    extends /*<extends>*/CGImageProperties/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CGImageDestinationProperties toObject(Class<CGImageDestinationProperties> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CGImageDestinationProperties(o);
        }
        @MarshalsPointer
        public static long toNative(CGImageDestinationProperties o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    public CGImageDestinationProperties(CFDictionary data) {
        this.data = data;
    }
    public CGImageDestinationProperties() {
        this.data = CFMutableDictionary.create();
    }
    /*<bind>*/static { Bro.bind(CGImageDestinationProperties.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public double getLossyCompressionQuality() {
        if (data.containsKey(LossyCompressionQualityKey())) {
            CFNumber val = data.get(LossyCompressionQualityKey(), CFNumber.class);
            return val.doubleValue();
        }
        return 1;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGImageDestinationProperties setLossyCompressionQuality(double quality) {
        data.put(LossyCompressionQualityKey(), CFNumber.valueOf(quality));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGColor getBackgroundColor() {
        if (data.containsKey(BackgroundColorKey())) {
            CGColor val = data.get(BackgroundColorKey(), CGColor.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGImageDestinationProperties setBackgroundColor(CGColor color) {
        data.put(BackgroundColorKey(), color);
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public long getMaxPixelSize() {
        if (data.containsKey(ImageMaxPixelSizeKey())) {
            CFNumber val = data.get(ImageMaxPixelSizeKey(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CGImageDestinationProperties setMaxPixelSize(long size) {
        data.put(ImageMaxPixelSizeKey(), CFNumber.valueOf(size));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean isEmbeddingThumbnail() {
        if (data.containsKey(EmbedThumbnailKey())) {
            CFBoolean val = data.get(EmbedThumbnailKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CGImageDestinationProperties setEmbedThumbnail(boolean embed) {
        data.put(EmbedThumbnailKey(), CFBoolean.valueOf(embed));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImageDestinationLossyCompressionQuality", optional=true)
    protected static native CFString LossyCompressionQualityKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImageDestinationBackgroundColor", optional=true)
    protected static native CFString BackgroundColorKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCGImageDestinationImageMaxPixelSize", optional=true)
    protected static native CFString ImageMaxPixelSizeKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCGImageDestinationEmbedThumbnail", optional=true)
    protected static native CFString EmbedThumbnailKey();
    /*</methods>*/
}
