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
package org.robovm.apple.coremedia;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
@Marshaler(/*<name>*/CMTextFormatDescriptionStyle/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMTextFormatDescriptionStyle/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CMTextFormatDescriptionStyle toObject(Class<CMTextFormatDescriptionStyle> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CMTextFormatDescriptionStyle(o);
        }
        @MarshalsPointer
        public static long toNative(CMTextFormatDescriptionStyle o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CMTextFormatDescriptionStyle> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMTextFormatDescriptionStyle> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CMTextFormatDescriptionStyle(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMTextFormatDescriptionStyle> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CMTextFormatDescriptionStyle i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CMTextFormatDescriptionStyle(CFDictionary data) {
        super(data);
    }
    public CMTextFormatDescriptionStyle() {}
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
    public CMTextFormatDescriptionStyle set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 4.0 and later.
     */
    public char getStartChar() {
        if (has(Keys.StartChar())) {
            CFNumber val = get(Keys.StartChar(), CFNumber.class);
            return val.charValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionStyle setStartChar(char startChar) {
        set(Keys.StartChar(), CFNumber.valueOf(startChar));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getFont() {
        if (has(Keys.Font())) {
            CFNumber val = get(Keys.Font(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionStyle setFont(int font) {
        set(Keys.Font(), CFNumber.valueOf(font));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getFontFace() {
        if (has(Keys.FontFace())) {
            CFNumber val = get(Keys.FontFace(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionStyle setFontFace(int fontFace) {
        set(Keys.FontFace(), CFNumber.valueOf(fontFace));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionColor getForegroundColor() {
        if (has(Keys.ForegroundColor())) {
            CFDictionary val = get(Keys.ForegroundColor(), CFDictionary.class);
            return new CMTextFormatDescriptionColor(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionStyle setForegroundColor(CMTextFormatDescriptionColor foregroundColor) {
        set(Keys.ForegroundColor(), foregroundColor.getDictionary());
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getFontSize() {
        if (has(Keys.FontSize())) {
            CFNumber val = get(Keys.FontSize(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionStyle setFontSize(int fontSize) {
        set(Keys.FontSize(), CFNumber.valueOf(fontSize));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public char getEndChar() {
        if (has(Keys.EndChar())) {
            CFNumber val = get(Keys.EndChar(), CFNumber.class);
            return val.charValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionStyle setEndChar(char endChar) {
        set(Keys.EndChar(), CFNumber.valueOf(endChar));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getHeight() {
        if (has(Keys.Height())) {
            CFNumber val = get(Keys.Height(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionStyle setHeight(int height) {
        set(Keys.Height(), CFNumber.valueOf(height));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public int getAscent() {
        if (has(Keys.Ascent())) {
            CFNumber val = get(Keys.Ascent(), CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionStyle setAscent(int ascent) {
        set(Keys.Ascent(), CFNumber.valueOf(ascent));
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    @Library("CoreMedia")
    public static class Keys {
        static { Bro.bind(Keys.class); }
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionStyle_StartChar", optional=true)
        public static native CFString StartChar();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionStyle_Font", optional=true)
        public static native CFString Font();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionStyle_FontFace", optional=true)
        public static native CFString FontFace();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionStyle_ForegroundColor", optional=true)
        public static native CFString ForegroundColor();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionStyle_FontSize", optional=true)
        public static native CFString FontSize();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionStyle_EndChar", optional=true)
        public static native CFString EndChar();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionStyle_Height", optional=true)
        public static native CFString Height();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionStyle_Ascent", optional=true)
        public static native CFString Ascent();
    }
    /*</keys>*/
}
