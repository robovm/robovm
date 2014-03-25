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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSLayoutManager/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSLayoutManagerPtr extends Ptr<NSLayoutManager, NSLayoutManagerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSLayoutManager.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSLayoutManager() {}
    protected NSLayoutManager(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "textStorage")
    public native NSTextStorage getTextStorage();
    @Property(selector = "setTextStorage:", strongRef = true)
    public native void setTextStorage(NSTextStorage v);
    @Property(selector = "textContainers")
    public native NSArray<?> getTextContainers();
    @Property(selector = "delegate")
    public native NSLayoutManagerDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(NSLayoutManagerDelegate v);
    @Property(selector = "showsInvisibleCharacters")
    public native boolean isShowsInvisibleCharacters();
    @Property(selector = "setShowsInvisibleCharacters:")
    public native void setShowsInvisibleCharacters(boolean v);
    @Property(selector = "showsControlCharacters")
    public native boolean isShowsControlCharacters();
    @Property(selector = "setShowsControlCharacters:")
    public native void setShowsControlCharacters(boolean v);
    @Property(selector = "hyphenationFactor")
    public native @MachineSizedFloat double getHyphenationFactor();
    @Property(selector = "setHyphenationFactor:")
    public native void setHyphenationFactor(@MachineSizedFloat double v);
    @Property(selector = "usesFontLeading")
    public native boolean isUsesFontLeading();
    @Property(selector = "setUsesFontLeading:")
    public native void setUsesFontLeading(boolean v);
    @Property(selector = "allowsNonContiguousLayout")
    public native boolean isAllowsNonContiguousLayout();
    @Property(selector = "setAllowsNonContiguousLayout:")
    public native void setAllowsNonContiguousLayout(boolean v);
    @Property(selector = "hasNonContiguousLayout")
    public native boolean isHasNonContiguousLayout();
    @Property(selector = "numberOfGlyphs")
    public native @MachineSizedUInt long getNumberOfGlyphs();
    @Property(selector = "extraLineFragmentRect")
    public native @ByVal CGRect getExtraLineFragmentRect();
    @Property(selector = "extraLineFragmentUsedRect")
    public native @ByVal CGRect getExtraLineFragmentUsedRect();
    @Property(selector = "extraLineFragmentTextContainer")
    public native NSTextContainer getExtraLineFragmentTextContainer();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "addTextContainer:")
    public native void addTextContainer$(NSTextContainer container);
    @Method(selector = "insertTextContainer:atIndex:")
    public native void insertTextContainer$atIndex$(NSTextContainer container, @MachineSizedUInt long index);
    @Method(selector = "removeTextContainerAtIndex:")
    public native void removeTextContainerAtIndex$(@MachineSizedUInt long index);
    @Method(selector = "textContainerChangedGeometry:")
    public native void textContainerChangedGeometry$(NSTextContainer container);
    @Method(selector = "invalidateGlyphsForCharacterRange:changeInLength:actualCharacterRange:")
    public native void invalidateGlyphsForCharacterRange$changeInLength$actualCharacterRange$(@ByVal NSRange charRange, @MachineSizedSInt long delta, NSRange actualCharRange);
    @Method(selector = "invalidateLayoutForCharacterRange:actualCharacterRange:")
    public native void invalidateLayoutForCharacterRange$actualCharacterRange$(@ByVal NSRange charRange, NSRange actualCharRange);
    @Method(selector = "invalidateDisplayForCharacterRange:")
    public native void invalidateDisplayForCharacterRange$(@ByVal NSRange charRange);
    @Method(selector = "invalidateDisplayForGlyphRange:")
    public native void invalidateDisplayForGlyphRange$(@ByVal NSRange glyphRange);
    @Method(selector = "processEditingForTextStorage:edited:range:changeInLength:invalidatedRange:")
    public native void processEditingForTextStorage$edited$range$changeInLength$invalidatedRange$(NSTextStorage textStorage, NSTextStorageEditActions editMask, @ByVal NSRange newCharRange, @MachineSizedSInt long delta, @ByVal NSRange invalidatedCharRange);
    @Method(selector = "ensureGlyphsForCharacterRange:")
    public native void ensureGlyphsForCharacterRange$(@ByVal NSRange charRange);
    @Method(selector = "ensureGlyphsForGlyphRange:")
    public native void ensureGlyphsForGlyphRange$(@ByVal NSRange glyphRange);
    @Method(selector = "ensureLayoutForCharacterRange:")
    public native void ensureLayoutForCharacterRange$(@ByVal NSRange charRange);
    @Method(selector = "ensureLayoutForGlyphRange:")
    public native void ensureLayoutForGlyphRange$(@ByVal NSRange glyphRange);
    @Method(selector = "ensureLayoutForTextContainer:")
    public native void ensureLayoutForTextContainer$(NSTextContainer container);
    @Method(selector = "ensureLayoutForBoundingRect:inTextContainer:")
    public native void ensureLayoutForBoundingRect$inTextContainer$(@ByVal CGRect bounds, NSTextContainer container);
    @Method(selector = "setGlyphs:properties:characterIndexes:font:forGlyphRange:")
    public native void setGlyphs$properties$characterIndexes$font$forGlyphRange$(ShortPtr glyphs, MachineSizedSIntPtr props, MachineSizedUIntPtr charIndexes, UIFont aFont, @ByVal NSRange glyphRange);
    @Method(selector = "glyphAtIndex:isValidIndex:")
    public native short glyphAtIndex$isValidIndex$(@MachineSizedUInt long glyphIndex, BytePtr isValidIndex);
    @Method(selector = "glyphAtIndex:")
    public native short glyphAtIndex$(@MachineSizedUInt long glyphIndex);
    @Method(selector = "isValidGlyphIndex:")
    public native boolean isValidGlyphIndex$(@MachineSizedUInt long glyphIndex);
    @Method(selector = "propertyForGlyphAtIndex:")
    public native NSGlyphProperty propertyForGlyphAtIndex$(@MachineSizedUInt long glyphIndex);
    @Method(selector = "characterIndexForGlyphAtIndex:")
    public native @MachineSizedUInt long characterIndexForGlyphAtIndex$(@MachineSizedUInt long glyphIndex);
    @Method(selector = "glyphIndexForCharacterAtIndex:")
    public native @MachineSizedUInt long glyphIndexForCharacterAtIndex$(@MachineSizedUInt long charIndex);
    @Method(selector = "getGlyphsInRange:glyphs:properties:characterIndexes:bidiLevels:")
    public native @MachineSizedUInt long getGlyphsInRange$glyphs$properties$characterIndexes$bidiLevels$(@ByVal NSRange glyphRange, ShortPtr glyphBuffer, MachineSizedSIntPtr props, MachineSizedUIntPtr charIndexBuffer, BytePtr bidiLevelBuffer);
    @Method(selector = "setTextContainer:forGlyphRange:")
    public native void setTextContainer$forGlyphRange$(NSTextContainer container, @ByVal NSRange glyphRange);
    @Method(selector = "setLineFragmentRect:forGlyphRange:usedRect:")
    public native void setLineFragmentRect$forGlyphRange$usedRect$(@ByVal CGRect fragmentRect, @ByVal NSRange glyphRange, @ByVal CGRect usedRect);
    @Method(selector = "setExtraLineFragmentRect:usedRect:textContainer:")
    public native void setExtraLineFragmentRect$usedRect$textContainer$(@ByVal CGRect fragmentRect, @ByVal CGRect usedRect, NSTextContainer container);
    @Method(selector = "setLocation:forStartOfGlyphRange:")
    public native void setLocation$forStartOfGlyphRange$(@ByVal CGPoint location, @ByVal NSRange glyphRange);
    @Method(selector = "setNotShownAttribute:forGlyphAtIndex:")
    public native void setNotShownAttribute$forGlyphAtIndex$(boolean flag, @MachineSizedUInt long glyphIndex);
    @Method(selector = "setDrawsOutsideLineFragment:forGlyphAtIndex:")
    public native void setDrawsOutsideLineFragment$forGlyphAtIndex$(boolean flag, @MachineSizedUInt long glyphIndex);
    @Method(selector = "setAttachmentSize:forGlyphRange:")
    public native void setAttachmentSize$forGlyphRange$(@ByVal CGSize attachmentSize, @ByVal NSRange glyphRange);
    @Method(selector = "getFirstUnlaidCharacterIndex:glyphIndex:")
    public native void getFirstUnlaidCharacterIndex$glyphIndex$(MachineSizedUIntPtr charIndex, MachineSizedUIntPtr glyphIndex);
    @Method(selector = "firstUnlaidCharacterIndex")
    public native @MachineSizedUInt long firstUnlaidCharacterIndex();
    @Method(selector = "firstUnlaidGlyphIndex")
    public native @MachineSizedUInt long firstUnlaidGlyphIndex();
    @Method(selector = "textContainerForGlyphAtIndex:effectiveRange:")
    public native NSTextContainer textContainerForGlyphAtIndex$effectiveRange$(@MachineSizedUInt long glyphIndex, NSRange effectiveGlyphRange);
    @Method(selector = "usedRectForTextContainer:")
    public native @ByVal CGRect usedRectForTextContainer$(NSTextContainer container);
    @Method(selector = "lineFragmentRectForGlyphAtIndex:effectiveRange:")
    public native @ByVal CGRect lineFragmentRectForGlyphAtIndex$effectiveRange$(@MachineSizedUInt long glyphIndex, NSRange effectiveGlyphRange);
    @Method(selector = "lineFragmentUsedRectForGlyphAtIndex:effectiveRange:")
    public native @ByVal CGRect lineFragmentUsedRectForGlyphAtIndex$effectiveRange$(@MachineSizedUInt long glyphIndex, NSRange effectiveGlyphRange);
    @Method(selector = "locationForGlyphAtIndex:")
    public native @ByVal CGPoint locationForGlyphAtIndex$(@MachineSizedUInt long glyphIndex);
    @Method(selector = "notShownAttributeForGlyphAtIndex:")
    public native boolean notShownAttributeForGlyphAtIndex$(@MachineSizedUInt long glyphIndex);
    @Method(selector = "drawsOutsideLineFragmentForGlyphAtIndex:")
    public native boolean drawsOutsideLineFragmentForGlyphAtIndex$(@MachineSizedUInt long glyphIndex);
    @Method(selector = "attachmentSizeForGlyphAtIndex:")
    public native @ByVal CGSize attachmentSizeForGlyphAtIndex$(@MachineSizedUInt long glyphIndex);
    @Method(selector = "truncatedGlyphRangeInLineFragmentForGlyphAtIndex:")
    public native @ByVal NSRange truncatedGlyphRangeInLineFragmentForGlyphAtIndex$(@MachineSizedUInt long glyphIndex);
    @Method(selector = "glyphRangeForCharacterRange:actualCharacterRange:")
    public native @ByVal NSRange glyphRangeForCharacterRange$actualCharacterRange$(@ByVal NSRange charRange, NSRange actualCharRange);
    @Method(selector = "characterRangeForGlyphRange:actualGlyphRange:")
    public native @ByVal NSRange characterRangeForGlyphRange$actualGlyphRange$(@ByVal NSRange glyphRange, NSRange actualGlyphRange);
    @Method(selector = "glyphRangeForTextContainer:")
    public native @ByVal NSRange glyphRangeForTextContainer$(NSTextContainer container);
    @Method(selector = "rangeOfNominallySpacedGlyphsContainingIndex:")
    public native @ByVal NSRange rangeOfNominallySpacedGlyphsContainingIndex$(@MachineSizedUInt long glyphIndex);
    @Method(selector = "boundingRectForGlyphRange:inTextContainer:")
    public native @ByVal CGRect boundingRectForGlyphRange$inTextContainer$(@ByVal NSRange glyphRange, NSTextContainer container);
    @Method(selector = "glyphRangeForBoundingRect:inTextContainer:")
    public native @ByVal NSRange glyphRangeForBoundingRect$inTextContainer$(@ByVal CGRect bounds, NSTextContainer container);
    @Method(selector = "glyphRangeForBoundingRectWithoutAdditionalLayout:inTextContainer:")
    public native @ByVal NSRange glyphRangeForBoundingRectWithoutAdditionalLayout$inTextContainer$(@ByVal CGRect bounds, NSTextContainer container);
    @Method(selector = "glyphIndexForPoint:inTextContainer:fractionOfDistanceThroughGlyph:")
    public native @MachineSizedUInt long glyphIndexForPoint$inTextContainer$fractionOfDistanceThroughGlyph$(@ByVal CGPoint point, NSTextContainer container, MachineSizedFloatPtr partialFraction);
    @Method(selector = "glyphIndexForPoint:inTextContainer:")
    public native @MachineSizedUInt long glyphIndexForPoint$inTextContainer$(@ByVal CGPoint point, NSTextContainer container);
    @Method(selector = "fractionOfDistanceThroughGlyphForPoint:inTextContainer:")
    public native @MachineSizedFloat double fractionOfDistanceThroughGlyphForPoint$inTextContainer$(@ByVal CGPoint point, NSTextContainer container);
    @Method(selector = "characterIndexForPoint:inTextContainer:fractionOfDistanceBetweenInsertionPoints:")
    public native @MachineSizedUInt long characterIndexForPoint$inTextContainer$fractionOfDistanceBetweenInsertionPoints$(@ByVal CGPoint point, NSTextContainer container, MachineSizedFloatPtr partialFraction);
    @Method(selector = "getLineFragmentInsertionPointsForCharacterAtIndex:alternatePositions:inDisplayOrder:positions:characterIndexes:")
    public native @MachineSizedUInt long getLineFragmentInsertionPointsForCharacterAtIndex$alternatePositions$inDisplayOrder$positions$characterIndexes$(@MachineSizedUInt long charIndex, boolean aFlag, boolean dFlag, MachineSizedFloatPtr positions, MachineSizedUIntPtr charIndexes);
    @Method(selector = "enumerateLineFragmentsForGlyphRange:usingBlock:")
    public native void enumerateLineFragmentsForGlyphRange$usingBlock$(@ByVal NSRange glyphRange, @Block("(@ByVal, @ByVal, , @ByVal, )") VoidBlock5<CGRect, CGRect, NSTextContainer, NSRange, BytePtr> block);
    @Method(selector = "enumerateEnclosingRectsForGlyphRange:withinSelectedGlyphRange:inTextContainer:usingBlock:")
    public native void enumerateEnclosingRectsForGlyphRange$withinSelectedGlyphRange$inTextContainer$usingBlock$(@ByVal NSRange glyphRange, @ByVal NSRange selectedRange, NSTextContainer textContainer, @Block("(@ByVal, )") VoidBlock2<CGRect, BytePtr> block);
    @Method(selector = "drawBackgroundForGlyphRange:atPoint:")
    public native void drawBackgroundForGlyphRange$atPoint$(@ByVal NSRange glyphsToShow, @ByVal CGPoint origin);
    @Method(selector = "drawGlyphsForGlyphRange:atPoint:")
    public native void drawGlyphsForGlyphRange$atPoint$(@ByVal NSRange glyphsToShow, @ByVal CGPoint origin);
    @Method(selector = "showCGGlyphs:positions:count:font:matrix:attributes:inContext:")
    public native void showCGGlyphs$positions$count$font$matrix$attributes$inContext$(ShortPtr glyphs, CGPoint positions, @MachineSizedUInt long glyphCount, UIFont font, @ByVal CGAffineTransform textMatrix, NSDictionary<?, ?> attributes, CGContext graphicsContext);
    @Method(selector = "fillBackgroundRectArray:count:forCharacterRange:color:")
    public native void fillBackgroundRectArray$count$forCharacterRange$color$(CGRect rectArray, @MachineSizedUInt long rectCount, @ByVal NSRange charRange, UIColor color);
    @Method(selector = "drawUnderlineForGlyphRange:underlineType:baselineOffset:lineFragmentRect:lineFragmentGlyphRange:containerOrigin:")
    public native void drawUnderlineForGlyphRange$underlineType$baselineOffset$lineFragmentRect$lineFragmentGlyphRange$containerOrigin$(@ByVal NSRange glyphRange, NSUnderlineStyle underlineVal, @MachineSizedFloat double baselineOffset, @ByVal CGRect lineRect, @ByVal NSRange lineGlyphRange, @ByVal CGPoint containerOrigin);
    @Method(selector = "underlineGlyphRange:underlineType:lineFragmentRect:lineFragmentGlyphRange:containerOrigin:")
    public native void underlineGlyphRange$underlineType$lineFragmentRect$lineFragmentGlyphRange$containerOrigin$(@ByVal NSRange glyphRange, NSUnderlineStyle underlineVal, @ByVal CGRect lineRect, @ByVal NSRange lineGlyphRange, @ByVal CGPoint containerOrigin);
    @Method(selector = "drawStrikethroughForGlyphRange:strikethroughType:baselineOffset:lineFragmentRect:lineFragmentGlyphRange:containerOrigin:")
    public native void drawStrikethroughForGlyphRange$strikethroughType$baselineOffset$lineFragmentRect$lineFragmentGlyphRange$containerOrigin$(@ByVal NSRange glyphRange, NSUnderlineStyle strikethroughVal, @MachineSizedFloat double baselineOffset, @ByVal CGRect lineRect, @ByVal NSRange lineGlyphRange, @ByVal CGPoint containerOrigin);
    @Method(selector = "strikethroughGlyphRange:strikethroughType:lineFragmentRect:lineFragmentGlyphRange:containerOrigin:")
    public native void strikethroughGlyphRange$strikethroughType$lineFragmentRect$lineFragmentGlyphRange$containerOrigin$(@ByVal NSRange glyphRange, NSUnderlineStyle strikethroughVal, @ByVal CGRect lineRect, @ByVal NSRange lineGlyphRange, @ByVal CGPoint containerOrigin);
    @Method(selector = "encodeWithCoder:")
    public native void encodeWithCoder$(NSCoder aCoder);
    /*</methods>*/
}
