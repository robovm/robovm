/*
 * Copyright (C) 2015 Trillian Mobile AB
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
package org.robovm.apple.coregraphics;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreGraphics")/*</annotations>*/
@Marshaler(/*<name>*/CGFontVariationAxis/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGFontVariationAxis/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CGFontVariationAxis toObject(Class<CGFontVariationAxis> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CGFontVariationAxis(o);
        }
        @MarshalsPointer
        public static long toNative(CGFontVariationAxis o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CGFontVariationAxis> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CGFontVariationAxis> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CGFontVariationAxis(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CGFontVariationAxis> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CGFontVariationAxis i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CGFontVariationAxis(CFDictionary data) {
        super(data);
    }
    public CGFontVariationAxis() {}
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
    public CGFontVariationAxis set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 2.0 and later.
     */
    public String getName() {
        if (has(Keys.Name())) {
            CFString val = get(Keys.Name(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGFontVariationAxis setName(String name) {
        set(Keys.Name(), new CFString(name));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public double getMinValue() {
        if (has(Keys.MinValue())) {
            CFNumber val = get(Keys.MinValue(), CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGFontVariationAxis setMinValue(double minValue) {
        set(Keys.MinValue(), CFNumber.valueOf(minValue));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public double getMaxValue() {
        if (has(Keys.MaxValue())) {
            CFNumber val = get(Keys.MaxValue(), CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGFontVariationAxis setMaxValue(double maxValue) {
        set(Keys.MaxValue(), CFNumber.valueOf(maxValue));
        return this;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public double getDefaultValue() {
        if (has(Keys.DefaultValue())) {
            CFNumber val = get(Keys.DefaultValue(), CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 2.0 and later.
     */
    public CGFontVariationAxis setDefaultValue(double defaultValue) {
        set(Keys.DefaultValue(), CFNumber.valueOf(defaultValue));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CoreGraphics")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCGFontVariationAxisName", optional=true)
        public static native CFString Name();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCGFontVariationAxisMinValue", optional=true)
        public static native CFString MinValue();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCGFontVariationAxisMaxValue", optional=true)
        public static native CFString MaxValue();
        /**
         * @since Available in iOS 2.0 and later.
         */
        @GlobalValue(symbol="kCGFontVariationAxisDefaultValue", optional=true)
        public static native CFString DefaultValue();
    }
    /*</keys>*/
}
