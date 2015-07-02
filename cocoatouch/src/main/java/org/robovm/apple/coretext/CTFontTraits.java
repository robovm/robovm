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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreText")/*</annotations>*/
@Marshaler(/*<name>*/CTFontTraits/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFontTraits/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CTFontTraits toObject(Class<CTFontTraits> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CTFontTraits(o);
        }
        @MarshalsPointer
        public static long toNative(CTFontTraits o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CTFontTraits> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CTFontTraits> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CTFontTraits(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CTFontTraits> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CTFontTraits i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CTFontTraits(CFDictionary data) {
        super(data);
    }
    public CTFontTraits() {}
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
    public CTFontTraits set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontSymbolicTraits getSymbolicTrait() {
        if (has(Keys.SymbolicTrait())) {
            CFNumber val = get(Keys.SymbolicTrait(), CFNumber.class);
            return new CTFontSymbolicTraits(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontTraits setSymbolicTrait(CTFontSymbolicTraits symbolicTrait) {
        set(Keys.SymbolicTrait(), CFNumber.valueOf(symbolicTrait.value()));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public float getWeightTrait() {
        if (has(Keys.WeightTrait())) {
            CFNumber val = get(Keys.WeightTrait(), CFNumber.class);
            return val.floatValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontTraits setWeightTrait(float weightTrait) {
        set(Keys.WeightTrait(), CFNumber.valueOf(weightTrait));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public float getWidthTrait() {
        if (has(Keys.WidthTrait())) {
            CFNumber val = get(Keys.WidthTrait(), CFNumber.class);
            return val.floatValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontTraits setWidthTrait(float widthTrait) {
        set(Keys.WidthTrait(), CFNumber.valueOf(widthTrait));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public float getSlantTrait() {
        if (has(Keys.SlantTrait())) {
            CFNumber val = get(Keys.SlantTrait(), CFNumber.class);
            return val.floatValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontTraits setSlantTrait(float slantTrait) {
        set(Keys.SlantTrait(), CFNumber.valueOf(slantTrait));
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
        @GlobalValue(symbol="kCTFontSymbolicTrait", optional=true)
        public static native CFString SymbolicTrait();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontWeightTrait", optional=true)
        public static native CFString WeightTrait();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontWidthTrait", optional=true)
        public static native CFString WidthTrait();
        /**
         * @since Available in iOS 3.2 and later.
         */
        @GlobalValue(symbol="kCTFontSlantTrait", optional=true)
        public static native CFString SlantTrait();
    }
    /*</keys>*/
}
