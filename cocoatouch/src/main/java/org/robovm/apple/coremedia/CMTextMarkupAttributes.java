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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreMedia")/*</annotations>*/
@Marshaler(/*<name>*/CMTextMarkupAttributes/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CMTextMarkupAttributes/*</name>*/ 
    extends /*<extends>*/CFDictionaryWrapper/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CMTextMarkupAttributes toObject(Class<CMTextMarkupAttributes> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CMTextMarkupAttributes(o);
        }
        @MarshalsPointer
        public static long toNative(CMTextMarkupAttributes o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CMTextMarkupAttributes> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CMTextMarkupAttributes> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(new CMTextMarkupAttributes(o.get(i, CFDictionary.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CMTextMarkupAttributes> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CMTextMarkupAttributes i : l) {
                array.add(i.getDictionary());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constructors>*/
    public CMTextMarkupAttributes(CFDictionary data) {
        super(data);
    }
    public CMTextMarkupAttributes() {}
    /*</constructors>*/

    /*<methods>*/
    public boolean has(CMTextMarkupAttribute key) {
        return data.containsKey(key.value());
    }
    public <T extends NativeObject> T get(CMTextMarkupAttribute key, Class<T> type) {
        if (has(key)) {
            return data.get(key.value(), type);
        }
        return null;
    }
    public CMTextMarkupAttributes set(CMTextMarkupAttribute key, NativeObject value) {
        data.put(key.value(), value);
        return this;
    }
    

    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSArray<NSNumber> getForegroundColorARGB() {
        if (has(CMTextMarkupAttribute.ForegroundColorARGB)) {
            NSArray<NSNumber> val = get(CMTextMarkupAttribute.ForegroundColorARGB, NSArray.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CMTextMarkupAttributes setForegroundColorARGB(NSArray<NSNumber> foregroundColorARGB) {
        set(CMTextMarkupAttribute.ForegroundColorARGB, foregroundColorARGB);
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSArray<NSNumber> getBackgroundColorARGB() {
        if (has(CMTextMarkupAttribute.BackgroundColorARGB)) {
            NSArray<NSNumber> val = get(CMTextMarkupAttribute.BackgroundColorARGB, NSArray.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CMTextMarkupAttributes setBackgroundColorARGB(NSArray<NSNumber> backgroundColorARGB) {
        set(CMTextMarkupAttribute.BackgroundColorARGB, backgroundColorARGB);
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSArray<NSNumber> getCharacterBackgroundColorARGB() {
        if (has(CMTextMarkupAttribute.CharacterBackgroundColorARGB)) {
            NSArray<NSNumber> val = get(CMTextMarkupAttribute.CharacterBackgroundColorARGB, NSArray.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CMTextMarkupAttributes setCharacterBackgroundColorARGB(NSArray<NSNumber> characterBackgroundColorARGB) {
        set(CMTextMarkupAttribute.CharacterBackgroundColorARGB, characterBackgroundColorARGB);
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean isBold() {
        if (has(CMTextMarkupAttribute.BoldStyle)) {
            CFBoolean val = get(CMTextMarkupAttribute.BoldStyle, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CMTextMarkupAttributes setBold(boolean bold) {
        set(CMTextMarkupAttribute.BoldStyle, CFBoolean.valueOf(bold));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean isItalic() {
        if (has(CMTextMarkupAttribute.ItalicStyle)) {
            CFBoolean val = get(CMTextMarkupAttribute.ItalicStyle, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CMTextMarkupAttributes setItalic(boolean italic) {
        set(CMTextMarkupAttribute.ItalicStyle, CFBoolean.valueOf(italic));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public boolean isUnderline() {
        if (has(CMTextMarkupAttribute.UnderlineStyle)) {
            CFBoolean val = get(CMTextMarkupAttribute.UnderlineStyle, CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CMTextMarkupAttributes setUnderline(boolean underline) {
        set(CMTextMarkupAttribute.UnderlineStyle, CFBoolean.valueOf(underline));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public String getFontFamilyName() {
        if (has(CMTextMarkupAttribute.FontFamilyName)) {
            CFString val = get(CMTextMarkupAttribute.FontFamilyName, CFString.class);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CMTextMarkupAttributes setFontFamilyName(String fontFamilyName) {
        set(CMTextMarkupAttribute.FontFamilyName, new CFString(fontFamilyName));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CMTextMarkupGenericFontName getGenericFontFamilyName() {
        if (has(CMTextMarkupAttribute.GenericFontFamilyName)) {
            CFString val = get(CMTextMarkupAttribute.GenericFontFamilyName, CFString.class);
            return CMTextMarkupGenericFontName.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CMTextMarkupAttributes setGenericFontFamilyName(CMTextMarkupGenericFontName genericFontFamilyName) {
        set(CMTextMarkupAttribute.GenericFontFamilyName, genericFontFamilyName.value());
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getBaseFontSizePercentageRelativeToVideoHeight() {
        if (has(CMTextMarkupAttribute.BaseFontSizePercentageRelativeToVideoHeight)) {
            CFNumber val = get(CMTextMarkupAttribute.BaseFontSizePercentageRelativeToVideoHeight, CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CMTextMarkupAttributes setBaseFontSizePercentageRelativeToVideoHeight(double baseFontSizePercentageRelativeToVideoHeight) {
        set(CMTextMarkupAttribute.BaseFontSizePercentageRelativeToVideoHeight, CFNumber.valueOf(baseFontSizePercentageRelativeToVideoHeight));
        return this;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public double getRelativeFontSize() {
        if (has(CMTextMarkupAttribute.RelativeFontSize)) {
            CFNumber val = get(CMTextMarkupAttribute.RelativeFontSize, CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CMTextMarkupAttributes setRelativeFontSize(double relativeFontSize) {
        set(CMTextMarkupAttribute.RelativeFontSize, CFNumber.valueOf(relativeFontSize));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CMTextVerticalLayout getVerticalLayout() {
        if (has(CMTextMarkupAttribute.VerticalLayout)) {
            CFString val = get(CMTextMarkupAttribute.VerticalLayout, CFString.class);
            return CMTextVerticalLayout.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CMTextMarkupAttributes setVerticalLayout(CMTextVerticalLayout verticalLayout) {
        set(CMTextMarkupAttribute.VerticalLayout, verticalLayout.value());
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CMTextMarkupAlignmentType getAlignment() {
        if (has(CMTextMarkupAttribute.Alignment)) {
            CFString val = get(CMTextMarkupAttribute.Alignment, CFString.class);
            return CMTextMarkupAlignmentType.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CMTextMarkupAttributes setAlignment(CMTextMarkupAlignmentType alignment) {
        set(CMTextMarkupAttribute.Alignment, alignment.value());
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getTextPositionPercentageRelativeToWritingDirection() {
        if (has(CMTextMarkupAttribute.TextPositionPercentageRelativeToWritingDirection)) {
            CFNumber val = get(CMTextMarkupAttribute.TextPositionPercentageRelativeToWritingDirection, CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CMTextMarkupAttributes setTextPositionPercentageRelativeToWritingDirection(double textPositionPercentageRelativeToWritingDirection) {
        set(CMTextMarkupAttribute.TextPositionPercentageRelativeToWritingDirection, CFNumber.valueOf(textPositionPercentageRelativeToWritingDirection));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getOrthogonalLinePositionPercentageRelativeToWritingDirection() {
        if (has(CMTextMarkupAttribute.OrthogonalLinePositionPercentageRelativeToWritingDirection)) {
            CFNumber val = get(CMTextMarkupAttribute.OrthogonalLinePositionPercentageRelativeToWritingDirection, CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CMTextMarkupAttributes setOrthogonalLinePositionPercentageRelativeToWritingDirection(double orthogonalLinePositionPercentageRelativeToWritingDirection) {
        set(CMTextMarkupAttribute.OrthogonalLinePositionPercentageRelativeToWritingDirection, CFNumber.valueOf(orthogonalLinePositionPercentageRelativeToWritingDirection));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public double getWritingDirectionSizePercentage() {
        if (has(CMTextMarkupAttribute.WritingDirectionSizePercentage)) {
            CFNumber val = get(CMTextMarkupAttribute.WritingDirectionSizePercentage, CFNumber.class);
            return val.doubleValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CMTextMarkupAttributes setWritingDirectionSizePercentage(double writingDirectionSizePercentage) {
        set(CMTextMarkupAttribute.WritingDirectionSizePercentage, CFNumber.valueOf(writingDirectionSizePercentage));
        return this;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CMTextMarkupCharacterEdgeStyle getCharacterEdgeStyle() {
        if (has(CMTextMarkupAttribute.CharacterEdgeStyle)) {
            CFString val = get(CMTextMarkupAttribute.CharacterEdgeStyle, CFString.class);
            return CMTextMarkupCharacterEdgeStyle.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CMTextMarkupAttributes setCharacterEdgeStyle(CMTextMarkupCharacterEdgeStyle characterEdgeStyle) {
        set(CMTextMarkupAttribute.CharacterEdgeStyle, characterEdgeStyle.value());
        return this;
    }
    /*</methods>*/
    
    /*<keys>*/
    /*</keys>*/
}
