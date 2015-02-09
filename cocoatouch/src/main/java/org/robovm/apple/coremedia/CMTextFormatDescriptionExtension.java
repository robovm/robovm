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
@Marshaler(/*<name>*/CMTextFormatDescriptionExtension/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMTextFormatDescriptionExtension/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CMTextFormatDescriptionExtension toObject(Class<CMTextFormatDescriptionExtension> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CMTextFormatDescriptionExtension(o);
        }
        @MarshalsPointer
        public static long toNative(CMTextFormatDescriptionExtension o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CMTextFormatDescriptionExtension> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMTextFormatDescriptionExtension> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CMTextFormatDescriptionExtension(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMTextFormatDescriptionExtension> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CMTextFormatDescriptionExtension i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    CMTextFormatDescriptionExtension(CFDictionary data) {
        super(data);
    }
    public CMTextFormatDescriptionExtension() {}
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
    public CMTextFormatDescriptionExtension set(CFString key, NativeObject value) {
        data.put(key, value);
        return this;
    }
    

    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextDisplayFlag getDisplayFlags() {
        if (has(Keys.DisplayFlags())) {
            CFNumber val = get(Keys.DisplayFlags(), CFNumber.class);
            return new CMTextDisplayFlag(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionExtension setDisplayFlags(CMTextDisplayFlag displayFlags) {
        set(Keys.DisplayFlags(), CFNumber.valueOf(displayFlags.value()));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionColor getBackgroundColor() {
        if (has(Keys.BackgroundColor())) {
            CFDictionary val = get(Keys.BackgroundColor(), CFDictionary.class);
            return new CMTextFormatDescriptionColor(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionExtension setBackgroundColor(CMTextFormatDescriptionColor backgroundColor) {
        set(Keys.BackgroundColor(), backgroundColor.getDictionary());
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionRect getDefaultTextBox() {
        if (has(Keys.DefaultTextBox())) {
            CFDictionary val = get(Keys.DefaultTextBox(), CFDictionary.class);
            return new CMTextFormatDescriptionRect(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionExtension setDefaultTextBox(CMTextFormatDescriptionRect defaultTextBox) {
        set(Keys.DefaultTextBox(), defaultTextBox.getDictionary());
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionStyle getDefaultStyle() {
        if (has(Keys.DefaultStyle())) {
            CFDictionary val = get(Keys.DefaultStyle(), CFDictionary.class);
            return new CMTextFormatDescriptionStyle(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionExtension setDefaultStyle(CMTextFormatDescriptionStyle defaultStyle) {
        set(Keys.DefaultStyle(), defaultStyle.getDictionary());
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextJustification getHorizontalJustification() {
        if (has(Keys.HorizontalJustification())) {
            CFNumber val = get(Keys.HorizontalJustification(), CFNumber.class);
            return CMTextJustification.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionExtension setHorizontalJustification(CMTextJustification horizontalJustification) {
        set(Keys.HorizontalJustification(), CFNumber.valueOf(horizontalJustification.value()));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextJustification getVerticalJustification() {
        if (has(Keys.VerticalJustification())) {
            CFNumber val = get(Keys.VerticalJustification(), CFNumber.class);
            return CMTextJustification.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionExtension setVerticalJustification(CMTextJustification verticalJustification) {
        set(Keys.VerticalJustification(), CFNumber.valueOf(verticalJustification.value()));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public Map<String, String> getFontTable() {
        if (has(Keys.FontTable())) {
            CFDictionary val = get(Keys.FontTable(), CFDictionary.class);
            return val.asStringStringMap();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionExtension setFontTable(Map<String, String> fontTable) {
        set(Keys.FontTable(), CFDictionary.fromStringStringMap(fontTable));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextJustification getTextJustification() {
        if (has(Keys.TextJustification())) {
            CFNumber val = get(Keys.TextJustification(), CFNumber.class);
            return CMTextJustification.valueOf(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionExtension setTextJustification(CMTextJustification textJustification) {
        set(Keys.TextJustification(), CFNumber.valueOf(textJustification.value()));
        return this;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getDefaultFontName() {
        if (has(Keys.DefaultFontName())) {
            CFString val = get(Keys.DefaultFontName(), CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CMTextFormatDescriptionExtension setDefaultFontName(String defaultFontName) {
        set(Keys.DefaultFontName(), new CFString(defaultFontName));
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
        @GlobalValue(symbol="kCMTextFormatDescriptionExtension_DisplayFlags", optional=true)
        public static native CFString DisplayFlags();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionExtension_BackgroundColor", optional=true)
        public static native CFString BackgroundColor();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionExtension_DefaultTextBox", optional=true)
        public static native CFString DefaultTextBox();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionExtension_DefaultStyle", optional=true)
        public static native CFString DefaultStyle();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionExtension_HorizontalJustification", optional=true)
        public static native CFString HorizontalJustification();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionExtension_VerticalJustification", optional=true)
        public static native CFString VerticalJustification();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionExtension_FontTable", optional=true)
        public static native CFString FontTable();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionExtension_TextJustification", optional=true)
        public static native CFString TextJustification();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCMTextFormatDescriptionExtension_DefaultFontName", optional=true)
        public static native CFString DefaultFontName();
    }
    /*</keys>*/
}
