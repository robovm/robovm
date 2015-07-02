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
package org.robovm.apple.corevideo;

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
import org.robovm.apple.opengles.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreVideo")/*</annotations>*/
@Marshaler(/*<name>*/CVImageBufferCleanAperture/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVImageBufferCleanAperture/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CVImageBufferCleanAperture toObject(Class<CVImageBufferCleanAperture> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CVImageBufferCleanAperture(o);
        }
        @MarshalsPointer
        public static long toNative(CVImageBufferCleanAperture o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CVImageBufferCleanAperture> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CVImageBufferCleanAperture> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CVImageBufferCleanAperture(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CVImageBufferCleanAperture> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CVImageBufferCleanAperture i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CVImageBufferCleanAperture(CFDictionary data) {
        super(data);
    }
    public CVImageBufferCleanAperture() {}
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
    public CVImageBufferCleanAperture set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getWidth() {
        if (has(Keys.Width())) {
            CFNumber val = get(Keys.Width(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVImageBufferCleanAperture setWidth(long width) {
        set(Keys.Width(), CFNumber.valueOf(width));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getHeight() {
        if (has(Keys.Height())) {
            CFNumber val = get(Keys.Height(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVImageBufferCleanAperture setHeight(long height) {
        set(Keys.Height(), CFNumber.valueOf(height));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getHorizontalOffset() {
        if (has(Keys.HorizontalOffset())) {
            CFNumber val = get(Keys.HorizontalOffset(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVImageBufferCleanAperture setHorizontalOffset(long horizontalOffset) {
        set(Keys.HorizontalOffset(), CFNumber.valueOf(horizontalOffset));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getVerticalOffset() {
        if (has(Keys.VerticalOffset())) {
            CFNumber val = get(Keys.VerticalOffset(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CVImageBufferCleanAperture setVerticalOffset(long verticalOffset) {
        set(Keys.VerticalOffset(), CFNumber.valueOf(verticalOffset));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CoreVideo")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferCleanApertureWidthKey", optional=true)
        public static native CFString Width();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferCleanApertureHeightKey", optional=true)
        public static native CFString Height();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferCleanApertureHorizontalOffsetKey", optional=true)
        public static native CFString HorizontalOffset();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCVImageBufferCleanApertureVerticalOffsetKey", optional=true)
        public static native CFString VerticalOffset();
    }
    /*</keys>*/
}
