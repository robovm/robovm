/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.glkit;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("GLKit")/*</annotations>*/
@Marshaler(/*<name>*/GLKTextureLoaderOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GLKTextureLoaderOptions/*</name>*/ 
    extends /*<extends>*/NSDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static GLKTextureLoaderOptions toObject(Class<GLKTextureLoaderOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new GLKTextureLoaderOptions(o);
        }
        @MarshalsPointer
        public static long toNative(GLKTextureLoaderOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<GLKTextureLoaderOptions> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSDictionary<NSString, NSObject>> o = (NSArray<NSDictionary<NSString, NSObject>>) NSObject.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<GLKTextureLoaderOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new GLKTextureLoaderOptions(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<GLKTextureLoaderOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSDictionary<NSString, NSObject>> array = new NSMutableArray<>();
            for (GLKTextureLoaderOptions i : l) {
                array.add(i.getDictionary());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
     GLKTextureLoaderOptions(NSDictionary<NSString, NSObject> data) {
        super(data);
    }
    public GLKTextureLoaderOptions() {}
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
    public GLKTextureLoaderOptions set(NSString key, NSObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean shouldApplyPremultiplication() {
        if (has(Keys.ApplyPremultiplication())) {
            NSNumber val = (NSNumber) get(Keys.ApplyPremultiplication());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public GLKTextureLoaderOptions setShouldApplyPremultiplication(boolean shouldApplyPremultiplication) {
        set(Keys.ApplyPremultiplication(), NSNumber.valueOf(shouldApplyPremultiplication));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean shouldGenerateMipmaps() {
        if (has(Keys.GenerateMipmaps())) {
            NSNumber val = (NSNumber) get(Keys.GenerateMipmaps());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public GLKTextureLoaderOptions setShouldGenerateMipmaps(boolean shouldGenerateMipmaps) {
        set(Keys.GenerateMipmaps(), NSNumber.valueOf(shouldGenerateMipmaps));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean isOriginBottomLeft() {
        if (has(Keys.OriginBottomLeft())) {
            NSNumber val = (NSNumber) get(Keys.OriginBottomLeft());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public GLKTextureLoaderOptions setOriginBottomLeft(boolean originBottomLeft) {
        set(Keys.OriginBottomLeft(), NSNumber.valueOf(originBottomLeft));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean shouldTreatGrayscaleAsAlpha() {
        if (has(Keys.GrayscaleAsAlpha())) {
            NSNumber val = (NSNumber) get(Keys.GrayscaleAsAlpha());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public GLKTextureLoaderOptions setShouldTreatGrayscaleAsAlpha(boolean shouldTreatGrayscaleAsAlpha) {
        set(Keys.GrayscaleAsAlpha(), NSNumber.valueOf(shouldTreatGrayscaleAsAlpha));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public boolean isSRGB() {
        if (has(Keys.SRGB())) {
            NSNumber val = (NSNumber) get(Keys.SRGB());
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public GLKTextureLoaderOptions setSRGB(boolean sRGB) {
        set(Keys.SRGB(), NSNumber.valueOf(sRGB));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("GLKit")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="GLKTextureLoaderApplyPremultiplication", optional=true)
        public static native NSString ApplyPremultiplication();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="GLKTextureLoaderGenerateMipmaps", optional=true)
        public static native NSString GenerateMipmaps();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="GLKTextureLoaderOriginBottomLeft", optional=true)
        public static native NSString OriginBottomLeft();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="GLKTextureLoaderGrayscaleAsAlpha", optional=true)
        public static native NSString GrayscaleAsAlpha();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="GLKTextureLoaderSRGB", optional=true)
        public static native NSString SRGB();
    }
    /*</keys>*/
}
