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
package org.robovm.apple.metalkit;

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
import org.robovm.apple.metal.*;
import org.robovm.apple.modelio.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreanimation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("MetalKit")/*</annotations>*/
@Marshaler(/*<name>*/MTKTextureLoaderOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MTKTextureLoaderOptions/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static MTKTextureLoaderOptions toObject(Class<MTKTextureLoaderOptions> cls, long handle, long flags) {
            NSDictionary o = (NSDictionary) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new MTKTextureLoaderOptions(o);
        }
        @MarshalsPointer
        public static long toNative(MTKTextureLoaderOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<MTKTextureLoaderOptions> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary> o = (NSArray<NSDictionary>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<MTKTextureLoaderOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new MTKTextureLoaderOptions(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<MTKTextureLoaderOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary> array = new NSMutableArray<>();
            for (MTKTextureLoaderOptions i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    MTKTextureLoaderOptions(NSDictionary data) {
        super(data);
    }
    public MTKTextureLoaderOptions() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(NSString key) {
        return data.containsKey(key);
    }
    public NSObject get(NSString key) {
        if (has(key)) {
            return data.get(key);
        }
        return null;
    }
    public MTKTextureLoaderOptions set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 9.0 and later.
     */
    public boolean allocatesMipmaps() {
        if (has(Keys.AllocateMipmaps())) {
            NSNumber val = (NSNumber) get(Keys.AllocateMipmaps());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 9.0 and later.
     */
    public MTKTextureLoaderOptions setAllocatesMipmaps(boolean allocatesMipmaps) {
        set(Keys.AllocateMipmaps(), NSNumber.valueOf(allocatesMipmaps));
        return this;
    }
    /**
     * @since Available in iOS 9.0 and later.
     */
    public boolean isSRGB() {
        if (has(Keys.SRGB())) {
            NSNumber val = (NSNumber) get(Keys.SRGB());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 9.0 and later.
     */
    public MTKTextureLoaderOptions setSRGB(boolean sRGB) {
        set(Keys.SRGB(), NSNumber.valueOf(sRGB));
        return this;
    }
    /**
     * @since Available in iOS 9.0 and later.
     */
    public MTLTextureUsage getTextureUsage() {
        if (has(Keys.TextureUsage())) {
            NSNumber val = (NSNumber) get(Keys.TextureUsage());
            return new MTLTextureUsage(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 9.0 and later.
     */
    public MTKTextureLoaderOptions setTextureUsage(MTLTextureUsage textureUsage) {
        set(Keys.TextureUsage(), NSNumber.valueOf(textureUsage.value()));
        return this;
    }
    /**
     * @since Available in iOS 9.0 and later.
     */
    public MTLCPUCacheMode getTextureCPUCacheMode() {
        if (has(Keys.TextureCPUCacheMode())) {
            NSNumber val = (NSNumber) get(Keys.TextureCPUCacheMode());
            return MTLCPUCacheMode.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 9.0 and later.
     */
    public MTKTextureLoaderOptions setTextureCPUCacheMode(MTLCPUCacheMode textureCPUCacheMode) {
        set(Keys.TextureCPUCacheMode(), NSNumber.valueOf(textureCPUCacheMode.value()));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("MetalKit")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="MTKTextureLoaderOptionAllocateMipmaps", optional=true)
        public static native NSString AllocateMipmaps();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="MTKTextureLoaderOptionSRGB", optional=true)
        public static native NSString SRGB();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="MTKTextureLoaderOptionTextureUsage", optional=true)
        public static native NSString TextureUsage();
        /**
         * @since Available in iOS 9.0 and later.
         */
        @GlobalValue(symbol="MTKTextureLoaderOptionTextureCPUCacheMode", optional=true)
        public static native NSString TextureCPUCacheMode();
    }
    /*</keys>*/
}
