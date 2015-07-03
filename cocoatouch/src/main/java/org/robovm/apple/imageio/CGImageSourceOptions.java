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
@Marshaler(/*<name>*/CGImageSourceOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImageSourceOptions/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CGImageSourceOptions toObject(Class<CGImageSourceOptions> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CGImageSourceOptions(o);
        }
        @MarshalsPointer
        public static long toNative(CGImageSourceOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CGImageSourceOptions> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CGImageSourceOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CGImageSourceOptions(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CGImageSourceOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CGImageSourceOptions i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CGImageSourceOptions(CFDictionary data) {
        super(data);
    }
    public CGImageSourceOptions() {}
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
    public CGImageSourceOptions set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getTypeIdentifierHint() {
        if (has(Keys.TypeIdentifierHint())) {
            CFString val = get(Keys.TypeIdentifierHint(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGImageSourceOptions setTypeIdentifierHint(String typeIdentifierHint) {
        set(Keys.TypeIdentifierHint(), new CFString(typeIdentifierHint));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean shouldCache() {
        if (has(Keys.ShouldCache())) {
            CFBoolean val = get(Keys.ShouldCache(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGImageSourceOptions setShouldCache(boolean shouldCache) {
        set(Keys.ShouldCache(), CFBoolean.valueOf(shouldCache));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean shouldCacheImmediately() {
        if (has(Keys.ShouldCacheImmediately())) {
            CFBoolean val = get(Keys.ShouldCacheImmediately(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGImageSourceOptions setShouldCacheImmediately(boolean shouldCacheImmediately) {
        set(Keys.ShouldCacheImmediately(), CFBoolean.valueOf(shouldCacheImmediately));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean shouldAllowFloat() {
        if (has(Keys.ShouldAllowFloat())) {
            CFBoolean val = get(Keys.ShouldAllowFloat(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGImageSourceOptions setShouldAllowFloat(boolean shouldAllowFloat) {
        set(Keys.ShouldAllowFloat(), CFBoolean.valueOf(shouldAllowFloat));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean shouldCreateThumbnailFromImageIfAbsent() {
        if (has(Keys.CreateThumbnailFromImageIfAbsent())) {
            CFBoolean val = get(Keys.CreateThumbnailFromImageIfAbsent(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGImageSourceOptions setShouldCreateThumbnailFromImageIfAbsent(boolean shouldCreateThumbnailFromImageIfAbsent) {
        set(Keys.CreateThumbnailFromImageIfAbsent(), CFBoolean.valueOf(shouldCreateThumbnailFromImageIfAbsent));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getThumbnailMaxPixelSize() {
        if (has(Keys.ThumbnailMaxPixelSize())) {
            CFNumber val = get(Keys.ThumbnailMaxPixelSize(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGImageSourceOptions setThumbnailMaxPixelSize(long thumbnailMaxPixelSize) {
        set(Keys.ThumbnailMaxPixelSize(), CFNumber.valueOf(thumbnailMaxPixelSize));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean shouldCreateThumbnailWithTransform() {
        if (has(Keys.CreateThumbnailWithTransform())) {
            CFBoolean val = get(Keys.CreateThumbnailWithTransform(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGImageSourceOptions setShouldCreateThumbnailWithTransform(boolean shouldCreateThumbnailWithTransform) {
        set(Keys.CreateThumbnailWithTransform(), CFBoolean.valueOf(shouldCreateThumbnailWithTransform));
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
        @GlobalValue(symbol="kCGImageSourceTypeIdentifierHint", optional=true)
        public static native CFString TypeIdentifierHint();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImageSourceShouldCache", optional=true)
        public static native CFString ShouldCache();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="kCGImageSourceShouldCacheImmediately", optional=true)
        public static native CFString ShouldCacheImmediately();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImageSourceShouldAllowFloat", optional=true)
        public static native CFString ShouldAllowFloat();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImageSourceCreateThumbnailFromImageIfAbsent", optional=true)
        public static native CFString CreateThumbnailFromImageIfAbsent();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImageSourceCreateThumbnailFromImageAlways", optional=true)
        public static native CFString CreateThumbnailFromImageAlways();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImageSourceThumbnailMaxPixelSize", optional=true)
        public static native CFString ThumbnailMaxPixelSize();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImageSourceCreateThumbnailWithTransform", optional=true)
        public static native CFString CreateThumbnailWithTransform();
    }
    /*</keys>*/
}
