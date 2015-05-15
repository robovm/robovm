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
package org.robovm.apple.coretext;

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
/*<annotations>*/@Library("CoreText")/*</annotations>*/
@Marshaler(/*<name>*/CTFontVariationAxes/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFontVariationAxes/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CTFontVariationAxes toObject(Class<CTFontVariationAxes> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CTFontVariationAxes(o);
        }
        @MarshalsPointer
        public static long toNative(CTFontVariationAxes o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CTFontVariationAxes> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CTFontVariationAxes> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CTFontVariationAxes(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CTFontVariationAxes> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CTFontVariationAxes i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CTFontVariationAxes(CFDictionary data) {
        super(data);
    }
    public CTFontVariationAxes() {}
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
    public CTFontVariationAxes set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 3.2 and later.
     */
    public long getIdentifier() {
        if (has(Keys.Identifier())) {
            CFNumber val = get(Keys.Identifier(), CFNumber.class);
            return val.longValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontVariationAxes setIdentifier(long identifier) {
        set(Keys.Identifier(), CFNumber.valueOf(identifier));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public double getMinimumValue() {
        if (has(Keys.MinimumValue())) {
            CFNumber val = get(Keys.MinimumValue(), CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontVariationAxes setMinimumValue(double minimumValue) {
        set(Keys.MinimumValue(), CFNumber.valueOf(minimumValue));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public double getMaximumValue() {
        if (has(Keys.MaximumValue())) {
            CFNumber val = get(Keys.MaximumValue(), CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontVariationAxes setMaximumValue(double maximumValue) {
        set(Keys.MaximumValue(), CFNumber.valueOf(maximumValue));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public double getDefaultValue() {
        if (has(Keys.DefaultValue())) {
            CFNumber val = get(Keys.DefaultValue(), CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontVariationAxes setDefaultValue(double defaultValue) {
        set(Keys.DefaultValue(), CFNumber.valueOf(defaultValue));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public String getLocalizedName() {
        if (has(Keys.Name())) {
            CFString val = get(Keys.Name(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontVariationAxes setLocalizedName(String localizedName) {
        set(Keys.Name(), new CFString(localizedName));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CoreText")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontVariationAxisIdentifierKey", optional=true)
        public static native CFString Identifier();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontVariationAxisMinimumValueKey", optional=true)
        public static native CFString MinimumValue();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontVariationAxisMaximumValueKey", optional=true)
        public static native CFString MaximumValue();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontVariationAxisDefaultValueKey", optional=true)
        public static native CFString DefaultValue();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontVariationAxisNameKey", optional=true)
        public static native CFString Name();
    }
    /*</keys>*/
}
