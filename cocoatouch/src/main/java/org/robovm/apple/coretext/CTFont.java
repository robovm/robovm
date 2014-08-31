/*
 * Copyright (C) 2014 Trillian Mobile AB
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CTFont/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CTFontPtr extends Ptr<CTFont, CTFontPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CTFont.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CTFont() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    public CTFontTraits getTraits() {
        return new CTFontTraits(getTraits0());
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public String getName(CTFontNameKey nameKey) {
        return getName(nameKey.value());
    }
    /**
     * @since Available in iOS 3.2 and later.
     */
    public String getLocalizedName(CTFontNameKey nameKey) {
        return getLocalizedName(nameKey.value(), null);
    }
    /*<methods>*/
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCreateWithName", optional=true)
    public static native CTFont create(String name, @MachineSizedFloat double size, CGAffineTransform matrix);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCreateWithFontDescriptor", optional=true)
    public static native CTFont create(CTFontDescriptor descriptor, @MachineSizedFloat double size, CGAffineTransform matrix);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCreateWithNameAndOptions", optional=true)
    public static native CTFont create(String name, @MachineSizedFloat double size, CGAffineTransform matrix, CTFontOptions options);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCreateWithFontDescriptorAndOptions", optional=true)
    public static native CTFont create(CTFontDescriptor descriptor, @MachineSizedFloat double size, CGAffineTransform matrix, CTFontOptions options);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCreateUIFontForLanguage", optional=true)
    public static native CTFont create(CTFontUIFontType uiType, @MachineSizedFloat double size, String language);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCreateCopyWithAttributes", optional=true)
    public static native CTFont createCopy(CTFont font, @MachineSizedFloat double size, CGAffineTransform matrix, CTFontDescriptor attributes);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCreateCopyWithSymbolicTraits", optional=true)
    public static native CTFont createCopy(CTFont font, @MachineSizedFloat double size, CGAffineTransform matrix, CTFontSymbolicTraits symTraitValue, CTFontSymbolicTraits symTraitMask);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCreateCopyWithFamily", optional=true)
    public static native CTFont createCopy(CTFont font, @MachineSizedFloat double size, CGAffineTransform matrix, String family);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCreateForString", optional=true)
    public native CTFont create(String string, @ByVal CFRange range);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCopyFontDescriptor", optional=true)
    public native CTFontDescriptor getFontDescriptor();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCopyAttribute", optional=true)
    public native CFType getAttribute(String attribute);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetSize", optional=true)
    public native @MachineSizedFloat double getSize();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetMatrix", optional=true)
    public native @ByVal CGAffineTransform getMatrix();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetSymbolicTraits", optional=true)
    public native CTFontSymbolicTraits getSymbolicTraits();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCopyTraits", optional=true)
    protected native CFDictionary getTraits0();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCopyPostScriptName", optional=true)
    public native String getPostScriptName();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCopyFamilyName", optional=true)
    public native String getFamilyName();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCopyFullName", optional=true)
    public native String getFullName();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCopyDisplayName", optional=true)
    public native String getDisplayName();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCopyName", optional=true)
    protected native String getName(CFString nameKey);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCopyLocalizedName", optional=true)
    protected native String getLocalizedName(CFString nameKey, CFString.CFStringPtr actualLanguage);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCopyCharacterSet", optional=true)
    public native NSCharacterSet getCharacterSet();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetStringEncoding", optional=true)
    public native int getStringEncoding();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCopySupportedLanguages", optional=true)
    public native NSArray<NSString> getSupportedLanguages();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetGlyphsForCharacters", optional=true)
    protected native boolean getGlyphs(ShortPtr characters, ShortPtr glyphs, @MachineSizedSInt long count);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetAscent", optional=true)
    public native @MachineSizedFloat double getAscent();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetDescent", optional=true)
    public native @MachineSizedFloat double getDescent();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetLeading", optional=true)
    public native @MachineSizedFloat double getLeading();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetUnitsPerEm", optional=true)
    public native int getUnitsPerEm();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetGlyphCount", optional=true)
    public native @MachineSizedSInt long getGlyphCount();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetBoundingBox", optional=true)
    public native @ByVal CGRect getBoundingBox();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetUnderlinePosition", optional=true)
    public native @MachineSizedFloat double getUnderlinePosition();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetUnderlineThickness", optional=true)
    public native @MachineSizedFloat double getUnderlineThickness();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetSlantAngle", optional=true)
    public native @MachineSizedFloat double getSlantAngle();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetCapHeight", optional=true)
    public native @MachineSizedFloat double getCapHeight();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetXHeight", optional=true)
    public native @MachineSizedFloat double getXHeight();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetGlyphWithName", optional=true)
    public native short getGlyph(String glyphName);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetBoundingRectsForGlyphs", optional=true)
    protected native @ByVal CGRect getBoundingRects(CTFontOrientation orientation, ShortPtr glyphs, CGRect boundingRects, @MachineSizedSInt long count);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CTFontGetOpticalBoundsForGlyphs", optional=true)
    protected native @ByVal CGRect getOpticalBounds(ShortPtr glyphs, CGRect boundingRects, @MachineSizedSInt long count, @MachineSizedUInt long options);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetAdvancesForGlyphs", optional=true)
    protected native double getAdvances(CTFontOrientation orientation, ShortPtr glyphs, CGSize advances, @MachineSizedSInt long count);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetVerticalTranslationsForGlyphs", optional=true)
    protected native void getVerticalTranslations(ShortPtr glyphs, CGSize translations, @MachineSizedSInt long count);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCreatePathForGlyph", optional=true)
    public native CGPath createPath(short glyph, CGAffineTransform transform);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCopyVariationAxes", optional=true)
    public native CFArray getVariationAxes();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCopyVariation", optional=true)
    public native CFDictionary getVariation();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCopyFeatures", optional=true)
    public native CFArray getFeatures();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCopyFeatureSettings", optional=true)
    public native CFArray getFeatureSettings();
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCopyGraphicsFont", optional=true)
    public native CGFont getGraphicsFont(CTFontDescriptor.CTFontDescriptorPtr attributes);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCreateWithGraphicsFont", optional=true)
    public static native CTFont create(CGFont graphicsFont, @MachineSizedFloat double size, CGAffineTransform matrix, CTFontDescriptor attributes);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCopyAvailableTables", optional=true)
    public native CFArray getAvailableTables(CTFontTableOptions options);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontCopyTable", optional=true)
    public native NSData getTable(CTFontTableTag table, CTFontTableOptions options);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Bridge(symbol="CTFontDrawGlyphs", optional=true)
    public native void drawGlyphs(ShortPtr glyphs, CGPoint positions, @MachineSizedUInt long count, CGContext context);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Bridge(symbol="CTFontGetLigatureCaretPositions", optional=true)
    public native @MachineSizedSInt long getLigatureCaretPositions(short glyph, MachineSizedFloatPtr positions, @MachineSizedSInt long maxPositions);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Bridge(symbol="CTFontCopyDefaultCascadeListForLanguages", optional=true)
    public native CFArray getDefaultCascadeListForLanguages(CFArray languagePrefList);
    /*</methods>*/
}
