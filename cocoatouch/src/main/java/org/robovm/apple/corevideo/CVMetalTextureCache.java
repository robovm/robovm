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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreVideo")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CVMetalTextureCache/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CVMetalTextureCachePtr extends Ptr<CVMetalTextureCache, CVMetalTextureCachePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CVMetalTextureCache.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static CVMetalTextureCache create(CVMetalTextureCacheAttributes cacheAttributes, MTLDevice metalDevice, NSDictionary<NSString, ?> textureAttributes) {
        return create(null, cacheAttributes, metalDevice, textureAttributes);
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static CVMetalTextureCache create(CFAllocator allocator, CVMetalTextureCacheAttributes cacheAttributes, MTLDevice metalDevice, NSDictionary<NSString, ?> textureAttributes) {
        CVMetalTextureCache.CVMetalTextureCachePtr ptr = new CVMetalTextureCache.CVMetalTextureCachePtr();
        create(allocator, cacheAttributes, metalDevice, textureAttributes, ptr);
        return ptr.get();
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static CVMetalTexture createTexture(CVMetalTextureCache textureCache, CVImageBuffer sourceImage, NSDictionary<NSString, ?> textureAttributes, MTLPixelFormat pixelFormat, @MachineSizedUInt long width, @MachineSizedUInt long height, @MachineSizedUInt long planeIndex) {
        return createTexture(null, textureCache, sourceImage, textureAttributes, pixelFormat, width, height, planeIndex);
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static CVMetalTexture createTexture(CFAllocator allocator, CVMetalTextureCache textureCache, CVImageBuffer sourceImage, NSDictionary<NSString, ?> textureAttributes, MTLPixelFormat pixelFormat, @MachineSizedUInt long width, @MachineSizedUInt long height, @MachineSizedUInt long planeIndex) {
        CVMetalTexture.CVMetalTexturePtr ptr = new CVMetalTexture.CVMetalTexturePtr();
        createTexture(allocator, textureCache, sourceImage, textureAttributes, pixelFormat, width, height, planeIndex, ptr);
        return ptr.get();
    }

    /*<methods>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CVMetalTextureCacheGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CVMetalTextureCacheCreate", optional=true)
    private static native CVReturn create(CFAllocator allocator, CVMetalTextureCacheAttributes cacheAttributes, MTLDevice metalDevice, NSDictionary<NSString, ?> textureAttributes, CVMetalTextureCache.CVMetalTextureCachePtr cacheOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CVMetalTextureCacheCreateTextureFromImage", optional=true)
    private static native CVReturn createTexture(CFAllocator allocator, CVMetalTextureCache textureCache, CVImageBuffer sourceImage, NSDictionary<NSString, ?> textureAttributes, MTLPixelFormat pixelFormat, @MachineSizedUInt long width, @MachineSizedUInt long height, @MachineSizedUInt long planeIndex, CVMetalTexture.CVMetalTexturePtr textureOut);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Bridge(symbol="CVMetalTextureCacheFlush", optional=true)
    public native void flush(long options);
    /*</methods>*/
}
