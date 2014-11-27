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
@Marshaler(CGImageSourceOptions.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImageSourceOptions/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

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
    
    /*<ptr>*/
    /*</ptr>*/
    private CFDictionary data;
    
    protected CGImageSourceOptions(CFDictionary data) {
        this.data = data;
    }
    public CGImageSourceOptions() {
        this.data = CFMutableDictionary.create();
    }
    /*<bind>*/static { Bro.bind(CGImageSourceOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFDictionary getDictionary() {
        return data;
    }
    
    
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getTypeIdentifierHint() {
        if (data.containsKey(TypeIdentifierHintKey())) {
            CFString val = data.get(TypeIdentifierHintKey(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGImageSourceOptions setTypeIdentifierHint(String typeIdentifier) {
        data.put(TypeIdentifierHintKey(), new CFString(typeIdentifier));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean shouldCache() {
        if (data.containsKey(ShouldCacheKey())) {
            CFBoolean val = data.get(ShouldCacheKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGImageSourceOptions setShouldCache(boolean cache) {
        data.put(ShouldCacheKey(), CFBoolean.valueOf(cache));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean shouldCacheImmediately() {
        if (data.containsKey(ShouldCacheImmediatelyKey())) {
            CFBoolean val = data.get(ShouldCacheImmediatelyKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CGImageSourceOptions setShouldCacheImmediately(boolean cache) {
        data.put(ShouldCacheImmediatelyKey(), CFBoolean.valueOf(cache));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean shouldAllowFloat() {
        if (data.containsKey(ShouldAllowFloatKey())) {
            CFBoolean val = data.get(ShouldAllowFloatKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGImageSourceOptions setShouldAllowFloat(boolean allowFloat) {
        data.put(ShouldAllowFloatKey(), CFBoolean.valueOf(allowFloat));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean shouldCreateThumbnailFromImageIfAbsent() {
        if (data.containsKey(CreateThumbnailFromImageIfAbsentKey())) {
            CFBoolean val = data.get(CreateThumbnailFromImageIfAbsentKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGImageSourceOptions setShouldCreateThumbnailFromImageIfAbsent(boolean createThumbnail) {
        data.put(CreateThumbnailFromImageIfAbsentKey(), CFBoolean.valueOf(createThumbnail));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean shouldCreateThumbnailFromImageAlways() {
        if (data.containsKey(CreateThumbnailFromImageAlwaysKey())) {
            CFBoolean val = data.get(CreateThumbnailFromImageAlwaysKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGImageSourceOptions setShouldCreateThumbnailFromImageAlways(boolean createThumbnail) {
        data.put(CreateThumbnailFromImageAlwaysKey(), CFBoolean.valueOf(createThumbnail));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public long getThumbnailMaxPixelSize() {
        if (data.containsKey(ThumbnailMaxPixelSizeKey())) {
            CFNumber val = data.get(ThumbnailMaxPixelSizeKey(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGImageSourceOptions setThumbnailMaxPixelSize(long maxSize) {
        data.put(ThumbnailMaxPixelSizeKey(), CFNumber.valueOf(maxSize));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public boolean shouldCreateThumbnailWithTransform() {
        if (data.containsKey(CreateThumbnailWithTransformKey())) {
            CFBoolean val = data.get(CreateThumbnailWithTransformKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CGImageSourceOptions setShouldCreateThumbnailWithTransform(boolean transform) {
        data.put(CreateThumbnailWithTransformKey(), CFBoolean.valueOf(transform));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImageSourceTypeIdentifierHint", optional=true)
    protected static native CFString TypeIdentifierHintKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImageSourceShouldCache", optional=true)
    protected static native CFString ShouldCacheKey();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="kCGImageSourceShouldCacheImmediately", optional=true)
    protected static native CFString ShouldCacheImmediatelyKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImageSourceShouldAllowFloat", optional=true)
    protected static native CFString ShouldAllowFloatKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImageSourceCreateThumbnailFromImageIfAbsent", optional=true)
    protected static native CFString CreateThumbnailFromImageIfAbsentKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImageSourceCreateThumbnailFromImageAlways", optional=true)
    protected static native CFString CreateThumbnailFromImageAlwaysKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImageSourceThumbnailMaxPixelSize", optional=true)
    protected static native CFString ThumbnailMaxPixelSizeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImageSourceCreateThumbnailWithTransform", optional=true)
    protected static native CFString CreateThumbnailWithTransformKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
