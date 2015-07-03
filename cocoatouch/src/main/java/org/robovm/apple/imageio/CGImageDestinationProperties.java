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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
@Marshaler(/*<name>*/CGImageDestinationProperties/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImageDestinationProperties/*</name>*/ 
    extends /*<extends>*/CGImageProperties/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
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
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CGImageDestinationProperties> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CGImageDestinationProperties> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CGImageDestinationProperties(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CGImageDestinationProperties> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CGImageDestinationProperties i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CGImageDestinationProperties(CFDictionary data) {
        super(data);
    }
    public CGImageDestinationProperties() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(CFString key) {
        return data.containsKey(key);
    }
    public <T extends NativeObject> T get(CFString key, Class<T> type) {
        if (has(key)) {
            return data.get(key, type);
        }
        return null;
    }
    public CGImageDestinationProperties set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 4.0 and later.
     */
    public double getLossyCompressionQuality() {
        if (has(Keys.LossyCompressionQuality())) {
            CFNumber val = get(Keys.LossyCompressionQuality(), CFNumber.class);
            return val.doubleValue();
        }
        return 1;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGImageDestinationProperties setLossyCompressionQuality(double lossyCompressionQuality) {
        set(Keys.LossyCompressionQuality(), CFNumber.valueOf(lossyCompressionQuality));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGColor getBackgroundColor() {
        if (has(Keys.BackgroundColor())) {
            CGColor val = get(Keys.BackgroundColor(), CGColor.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGImageDestinationProperties setBackgroundColor(CGColor backgroundColor) {
        set(Keys.BackgroundColor(), backgroundColor);
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public long getMaxPixelSize() {
        if (has(Keys.ImageMaxPixelSize())) {
            CFNumber val = get(Keys.ImageMaxPixelSize(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CGImageDestinationProperties setMaxPixelSize(long maxPixelSize) {
        set(Keys.ImageMaxPixelSize(), CFNumber.valueOf(maxPixelSize));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean embedsThumbnail() {
        if (has(Keys.EmbedThumbnail())) {
            CFBoolean val = get(Keys.EmbedThumbnail(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CGImageDestinationProperties setEmbedsThumbnail(boolean embedsThumbnail) {
        set(Keys.EmbedThumbnail(), CFBoolean.valueOf(embedsThumbnail));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("ImageIO")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImageDestinationLossyCompressionQuality", optional=true)
        public static native CFString LossyCompressionQuality();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImageDestinationBackgroundColor", optional=true)
        public static native CFString BackgroundColor();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCGImageDestinationImageMaxPixelSize", optional=true)
        public static native CFString ImageMaxPixelSize();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCGImageDestinationEmbedThumbnail", optional=true)
        public static native CFString EmbedThumbnail();
    }
    /*</keys>*/
}
