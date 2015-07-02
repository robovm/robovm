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
package org.robovm.apple.coreimage;

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
import org.robovm.apple.corevideo.*;
import org.robovm.apple.imageio.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreImage")/*</annotations>*/
@Marshaler(/*<name>*/CIImageAutoAdjustOptions/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIImageAutoAdjustOptions/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CIImageAutoAdjustOptions toObject(Class<CIImageAutoAdjustOptions> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CIImageAutoAdjustOptions(o);
        }
        @MarshalsPointer
        public static long toNative(CIImageAutoAdjustOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CIImageAutoAdjustOptions> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CIImageAutoAdjustOptions> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CIImageAutoAdjustOptions(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CIImageAutoAdjustOptions> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CIImageAutoAdjustOptions i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CIImageAutoAdjustOptions(CFDictionary data) {
        super(data);
    }
    public CIImageAutoAdjustOptions() {}
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
    public CIImageAutoAdjustOptions set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean appliesEnhancementFilter() {
        if (has(Keys.Enhance())) {
            CFBoolean val = get(Keys.Enhance(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CIImageAutoAdjustOptions setAppliesEnhancementFilter(boolean appliesEnhancementFilter) {
        set(Keys.Enhance(), CFBoolean.valueOf(appliesEnhancementFilter));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean appliesRedEyeFilter() {
        if (has(Keys.RedEye())) {
            CFBoolean val = get(Keys.RedEye(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CIImageAutoAdjustOptions setAppliesRedEyeFilter(boolean appliesRedEyeFilter) {
        set(Keys.RedEye(), CFBoolean.valueOf(appliesRedEyeFilter));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public List<CIFeature> getFeatures() {
        if (has(Keys.Features())) {
            CFArray val = get(Keys.Features(), CFArray.class);
            return val.toList(CIFeature.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CIImageAutoAdjustOptions setFeatures(List<CIFeature> features) {
        set(Keys.Features(), CFArray.create(features));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean appliesCropFilter() {
        if (has(Keys.Crop())) {
            CFBoolean val = get(Keys.Crop(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CIImageAutoAdjustOptions setAppliesCropFilter(boolean appliesCropFilter) {
        set(Keys.Crop(), CFBoolean.valueOf(appliesCropFilter));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean appliesAutoLevel() {
        if (has(Keys.Level())) {
            CFBoolean val = get(Keys.Level(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CIImageAutoAdjustOptions setAppliesAutoLevel(boolean appliesAutoLevel) {
        set(Keys.Level(), CFBoolean.valueOf(appliesAutoLevel));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CoreImage")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCIImageAutoAdjustEnhance", optional=true)
        public static native CFString Enhance();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCIImageAutoAdjustRedEye", optional=true)
        public static native CFString RedEye();
        /**
         * @since Available in iOS 5.0 and later.
         */
        @GlobalValue(symbol="kCIImageAutoAdjustFeatures", optional=true)
        public static native CFString Features();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCIImageAutoAdjustCrop", optional=true)
        public static native CFString Crop();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCIImageAutoAdjustLevel", optional=true)
        public static native CFString Level();
    }
    /*</keys>*/
}
