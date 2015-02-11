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
@Marshaler(/*<name>*/CTAttributedStringAttributes/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTAttributedStringAttributes/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CTAttributedStringAttributes toObject(Class<CTAttributedStringAttributes> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CTAttributedStringAttributes(o);
        }
        @MarshalsPointer
        public static long toNative(CTAttributedStringAttributes o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CTAttributedStringAttributes> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(cls, handle, flags);
            if (o == null) {
                return null;
            }
            List<CTAttributedStringAttributes> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CTAttributedStringAttributes(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CTAttributedStringAttributes> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CTAttributedStringAttributes i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    public CTAttributedStringAttributes(CFDictionary data) {
        super(data);
    }
    public CTAttributedStringAttributes() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(CTAttributedStringAttribute key) {
        return data.containsKey(key.value());
    }
    public <T extends NativeObject> T get(CTAttributedStringAttribute key, Class<T> type) {
        if (has(key)) {
            return data.get(key.value(), type);
        }
        return null;
    }
    public CTAttributedStringAttributes set(CTAttributedStringAttribute key, NativeObject value) {
        data.put(key.value(), value);
        return this;
    }
    

    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFont getFont() {
        if (has(CTAttributedStringAttribute.Font)) {
            CTFont val = get(CTAttributedStringAttribute.Font, CTFont.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTAttributedStringAttributes setFont(CTFont font) {
        set(CTAttributedStringAttribute.Font, font);
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public boolean usesForegroundColorFromContext() {
        if (has(CTAttributedStringAttribute.ForegroundColorFromContext)) {
            CFBoolean val = get(CTAttributedStringAttribute.ForegroundColorFromContext, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTAttributedStringAttributes setUsesForegroundColorFromContext(boolean usesForegroundColorFromContext) {
        set(CTAttributedStringAttribute.ForegroundColorFromContext, CFBoolean.valueOf(usesForegroundColorFromContext));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public float getKern() {
        if (has(CTAttributedStringAttribute.Kern)) {
            CFNumber val = get(CTAttributedStringAttribute.Kern, CFNumber.class);
            return val.floatValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTAttributedStringAttributes setKern(float kern) {
        set(CTAttributedStringAttribute.Kern, CFNumber.valueOf(kern));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public int getLigature() {
        if (has(CTAttributedStringAttribute.Ligature)) {
            CFNumber val = get(CTAttributedStringAttribute.Ligature, CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTAttributedStringAttributes setLigature(int ligature) {
        set(CTAttributedStringAttribute.Ligature, CFNumber.valueOf(ligature));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CGColor getForegroundColor() {
        if (has(CTAttributedStringAttribute.ForegroundColor)) {
            CGColor val = get(CTAttributedStringAttribute.ForegroundColor, CGColor.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTAttributedStringAttributes setForegroundColor(CGColor foregroundColor) {
        set(CTAttributedStringAttribute.ForegroundColor, foregroundColor);
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTParagraphStyle getParagraphStyle() {
        if (has(CTAttributedStringAttribute.ParagraphStyle)) {
            CTParagraphStyle val = get(CTAttributedStringAttribute.ParagraphStyle, CTParagraphStyle.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTAttributedStringAttributes setParagraphStyle(CTParagraphStyle paragraphStyle) {
        set(CTAttributedStringAttribute.ParagraphStyle, paragraphStyle);
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public double getStrokeWidth() {
        if (has(CTAttributedStringAttribute.StrokeWidth)) {
            CFNumber val = get(CTAttributedStringAttribute.StrokeWidth, CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTAttributedStringAttributes setStrokeWidth(double strokeWidth) {
        set(CTAttributedStringAttribute.StrokeWidth, CFNumber.valueOf(strokeWidth));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CGColor getStrokeColor() {
        if (has(CTAttributedStringAttribute.StrokeColor)) {
            CGColor val = get(CTAttributedStringAttribute.StrokeColor, CGColor.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTAttributedStringAttributes setStrokeColor(CGColor strokeColor) {
        set(CTAttributedStringAttribute.StrokeColor, strokeColor);
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTUnderlineStyle getUnderlineStyle() {
        if (has(CTAttributedStringAttribute.UnderlineStyle)) {
            CFNumber val = get(CTAttributedStringAttribute.UnderlineStyle, CFNumber.class);
            return new CTUnderlineStyle(val.longValue());
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTAttributedStringAttributes setUnderlineStyle(CTUnderlineStyle underlineStyle) {
        set(CTAttributedStringAttribute.UnderlineStyle, CFNumber.valueOf(underlineStyle.value()));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public int getSuperscript() {
        if (has(CTAttributedStringAttribute.Superscript)) {
            CFNumber val = get(CTAttributedStringAttribute.Superscript, CFNumber.class);
            return val.intValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTAttributedStringAttributes setSuperscript(int superscript) {
        set(CTAttributedStringAttribute.Superscript, CFNumber.valueOf(superscript));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CGColor getUnderlineColor() {
        if (has(CTAttributedStringAttribute.UnderlineColor)) {
            CGColor val = get(CTAttributedStringAttribute.UnderlineColor, CGColor.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTAttributedStringAttributes setUnderlineColor(CGColor underlineColor) {
        set(CTAttributedStringAttribute.UnderlineColor, underlineColor);
        return this;
    }
    /**
     * @since Available in iOS 4.3 and later.
     */
    public boolean usesVerticalForms() {
        if (has(CTAttributedStringAttribute.VerticalForms)) {
            CFBoolean val = get(CTAttributedStringAttribute.VerticalForms, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 4.3 and later.
     */
    public CTAttributedStringAttributes setUsesVerticalForms(boolean usesVerticalForms) {
        set(CTAttributedStringAttribute.VerticalForms, CFBoolean.valueOf(usesVerticalForms));
        return this;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTGlyphInfo getGlyphInfo() {
        if (has(CTAttributedStringAttribute.GlyphInfo)) {
            CTGlyphInfo val = get(CTAttributedStringAttribute.GlyphInfo, CTGlyphInfo.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTAttributedStringAttributes setGlyphInfo(CTGlyphInfo glyphInfo) {
        set(CTAttributedStringAttribute.GlyphInfo, glyphInfo);
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public String getLanguage() {
        if (has(CTAttributedStringAttribute.Language)) {
            CFString val = get(CTAttributedStringAttribute.Language, CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CTAttributedStringAttributes setLanguage(String language) {
        set(CTAttributedStringAttribute.Language, new CFString(language));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CTBaselineClass getBaselineClass() {
        if (has(CTAttributedStringAttribute.BaselineClass)) {
            CFString val = get(CTAttributedStringAttribute.BaselineClass, CFString.class);
            return CTBaselineClass.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CTAttributedStringAttributes setBaselineClass(CTBaselineClass baselineClass) {
        set(CTAttributedStringAttribute.BaselineClass, baselineClass.value());
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CTRubyAnnotation getRubyAnnotation() {
        if (has(CTAttributedStringAttribute.RubyAnnotation)) {
            CTRubyAnnotation val = get(CTAttributedStringAttribute.RubyAnnotation, CTRubyAnnotation.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CTAttributedStringAttributes setRubyAnnotation(CTRubyAnnotation rubyAnnotation) {
        set(CTAttributedStringAttribute.RubyAnnotation, rubyAnnotation);
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    /*</keys>*/
}
