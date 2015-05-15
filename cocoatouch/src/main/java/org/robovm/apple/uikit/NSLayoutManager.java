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
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
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
    public native NSArray<NSTextContainer> getTextContainers();
    @Property(selector = "delegate")
    public native NSLayoutManagerDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(NSLayoutManagerDelegate v);
    @Property(selector = "showsInvisibleCharacters")
    public native boolean showsInvisibleCharacters();
    @Property(selector = "setShowsInvisibleCharacters:")
    public native void setShowsInvisibleCharacters(boolean v);
    @Property(selector = "showsControlCharacters")
    public native boolean showsControlCharacters();
    @Property(selector = "setShowsControlCharacters:")
    public native void setShowsControlCharacters(boolean v);
    @Property(selector = "hyphenationFactor")
    public native @MachineSizedFloat double getHyphenationFactor();
    @Property(selector = "setHyphenationFactor:")
    public native void setHyphenationFactor(@MachineSizedFloat double v);
    @Property(selector = "usesFontLeading")
    public native boolean usesFontLeading();
    @Property(selector = "setUsesFontLeading:")
    public native void setUsesFontLeading(boolean v);
    @Property(selector = "allowsNonContiguousLayout")
    public native boolean allowsNonContiguousLayout();
    @Property(selector = "setAllowsNonContiguousLayout:")
    public native void setAllowsNonContiguousLayout(boolean v);
    @Property(selector = "hasNonContiguousLayout")
    public native boolean hasNonContiguousLayout();
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
    
    public void showCGGlyphs(short[] glyphs, CGPoint[] positions, UIFont font, CGAffineTransform textMatrix, NSDictionary<?, ?> attributes, CGContext graphicsContext) {
        if (glyphs == null) {
            throw new NullPointerException("glyphs");
        }
        if (positions == null) {
            throw new NullPointerException("positions");
        }
        if (glyphs.length != positions.length) {
            throw new NullPointerException("glyphs.length != positions.length");
        }
        CGPoint positionsPtr = Struct.allocate(CGPoint.class, positions.length);
        positionsPtr.update(positions);
        showCGGlyphs(VM.getArrayValuesAddress(glyphs), positionsPtr, glyphs.length, font, textMatrix, attributes, graphicsContext);
    }

    public void fillBackground(CGRect[] rectArray, NSRange charRange, UIColor color) {
        if (rectArray == null) {
            throw new NullPointerException("rectArray");
        }
        CGRect rectArrayPtr = Struct.allocate(CGRect.class, rectArray.length);
        rectArrayPtr.update(rectArray);
        fillBackground(rectArrayPtr, rectArray.length, charRange, color);
    }

    
    /*<methods>*/
    @Method(selector = "addTextContainer:")
    public native void addTextContainer(NSTextContainer container);
    @Method(selector = "insertTextContainer:atIndex:")
    public native void insertTextContainer(NSTextContainer container, @MachineSizedUInt long index);
    @Method(selector = "removeTextContainerAtIndex:")
    public native void removeTextContainer(@MachineSizedUInt long index);
    @Method(selector = "textContainerChangedGeometry:")
    public native void textContainerChangedGeometry(NSTextContainer container);
    @Method(selector = "invalidateGlyphsForCharacterRange:changeInLength:actualCharacterRange:")
    public native void invalidateGlyphs(@ByVal NSRange charRange, @MachineSizedSInt long delta, NSRange actualCharRange);
    @Method(selector = "invalidateLayoutForCharacterRange:actualCharacterRange:")
    public native void invalidateLayout(@ByVal NSRange charRange, NSRange actualCharRange);
    @Method(selector = "invalidateDisplayForCharacterRange:")
    public native void invalidateDisplayForCharacterRange(@ByVal NSRange charRange);
    @Method(selector = "invalidateDisplayForGlyphRange:")
    public native void invalidateDisplayForGlyphRange(@ByVal NSRange glyphRange);
    @Method(selector = "processEditingForTextStorage:edited:range:changeInLength:invalidatedRange:")
    public native void processEditing(NSTextStorage textStorage, NSTextStorageEditActions editMask, @ByVal NSRange newCharRange, @MachineSizedSInt long delta, @ByVal NSRange invalidatedCharRange);
    @Method(selector = "ensureGlyphsForCharacterRange:")
    public native void ensureGlyphsForCharacterRange(@ByVal NSRange charRange);
    @Method(selector = "ensureGlyphsForGlyphRange:")
    public native void ensureGlyphsForGlyphRange(@ByVal NSRange glyphRange);
    @Method(selector = "ensureLayoutForCharacterRange:")
    public native void ensureLayoutForCharacterRange(@ByVal NSRange charRange);
    @Method(selector = "ensureLayoutForGlyphRange:")
    public native void ensureLayoutForGlyphRange(@ByVal NSRange glyphRange);
    @Method(selector = "ensureLayoutForTextContainer:")
    public native void ensureLayoutForTextContainer(NSTextContainer container);
    @Method(selector = "ensureLayoutForBoundingRect:inTextContainer:")
    public native void ensureLayoutForBoundingRect(@ByVal CGRect bounds, NSTextContainer container);
    @Method(selector = "setGlyphs:properties:characterIndexes:font:forGlyphRange:")
    public native void setGlyphs(ShortPtr glyphs, MachineSizedSIntPtr props, MachineSizedUIntPtr charIndexes, UIFont aFont, @ByVal NSRange glyphRange);
    @Method(selector = "glyphAtIndex:isValidIndex:")
    public native short getGlyph(@MachineSizedUInt long glyphIndex, BooleanPtr isValidIndex);
    @Method(selector = "glyphAtIndex:")
    public native short getGlyph(@MachineSizedUInt long glyphIndex);
    @Method(selector = "isValidGlyphIndex:")
    public native boolean isValidGlyphIndex(@MachineSizedUInt long glyphIndex);
    @Method(selector = "propertyForGlyphAtIndex:")
    public native NSGlyphProperty getPropertyForGlyph(@MachineSizedUInt long glyphIndex);
    @Method(selector = "characterIndexForGlyphAtIndex:")
    public native @MachineSizedUInt long getCharacterIndexForGlyph(@MachineSizedUInt long glyphIndex);
    @Method(selector = "glyphIndexForCharacterAtIndex:")
    public native @MachineSizedUInt long getGlyphIndexForCharacter(@MachineSizedUInt long charIndex);
    @Method(selector = "getGlyphsInRange:glyphs:properties:characterIndexes:bidiLevels:")
    public native @MachineSizedUInt long getGlyphs(@ByVal NSRange glyphRange, ShortPtr glyphBuffer, MachineSizedSIntPtr props, MachineSizedUIntPtr charIndexBuffer, BytePtr bidiLevelBuffer);
    @Method(selector = "setTextContainer:forGlyphRange:")
    public native void setTextContainer(NSTextContainer container, @ByVal NSRange glyphRange);
    @Method(selector = "setLineFragmentRect:forGlyphRange:usedRect:")
    public native void setLineFragmentRect(@ByVal CGRect fragmentRect, @ByVal NSRange glyphRange, @ByVal CGRect usedRect);
    @Method(selector = "setExtraLineFragmentRect:usedRect:textContainer:")
    public native void setExtraLineFragmentRect(@ByVal CGRect fragmentRect, @ByVal CGRect usedRect, NSTextContainer container);
    @Method(selector = "setLocation:forStartOfGlyphRange:")
    public native void setLocation(@ByVal CGPoint location, @ByVal NSRange glyphRange);
    @Method(selector = "setNotShownAttribute:forGlyphAtIndex:")
    public native void setNotShownAttribute(boolean flag, @MachineSizedUInt long glyphIndex);
    @Method(selector = "setDrawsOutsideLineFragment:forGlyphAtIndex:")
    public native void setDrawsOutsideLineFragment(boolean flag, @MachineSizedUInt long glyphIndex);
    @Method(selector = "setAttachmentSize:forGlyphRange:")
    public native void setAttachmentSize(@ByVal CGSize attachmentSize, @ByVal NSRange glyphRange);
    @Method(selector = "getFirstUnlaidCharacterIndex:glyphIndex:")
    public native void getFirstUnlaidCharacterIndex(MachineSizedUIntPtr charIndex, MachineSizedUIntPtr glyphIndex);
    @Method(selector = "firstUnlaidCharacterIndex")
    public native @MachineSizedUInt long getFirstUnlaidCharacterIndex();
    @Method(selector = "firstUnlaidGlyphIndex")
    public native @MachineSizedUInt long getFirstUnlaidGlyphIndex();
    @Method(selector = "textContainerForGlyphAtIndex:effectiveRange:")
    public native NSTextContainer getTextContainer(@MachineSizedUInt long glyphIndex, NSRange effectiveGlyphRange);
    @Method(selector = "usedRectForTextContainer:")
    public native @ByVal CGRect getUsedRectForTextContainer(NSTextContainer container);
    @Method(selector = "lineFragmentRectForGlyphAtIndex:effectiveRange:")
    public native @ByVal CGRect getLineFragmentRect(@MachineSizedUInt long glyphIndex, NSRange effectiveGlyphRange);
    @Method(selector = "lineFragmentUsedRectForGlyphAtIndex:effectiveRange:")
    public native @ByVal CGRect getLineFragmentUsedRect(@MachineSizedUInt long glyphIndex, NSRange effectiveGlyphRange);
    @Method(selector = "locationForGlyphAtIndex:")
    public native @ByVal CGPoint getLocation(@MachineSizedUInt long glyphIndex);
    @Method(selector = "notShownAttributeForGlyphAtIndex:")
    public native boolean getNotShownAttribute(@MachineSizedUInt long glyphIndex);
    @Method(selector = "drawsOutsideLineFragmentForGlyphAtIndex:")
    public native boolean getDrawsOutsideLineFragment(@MachineSizedUInt long glyphIndex);
    @Method(selector = "attachmentSizeForGlyphAtIndex:")
    public native @ByVal CGSize getAttachmentSize(@MachineSizedUInt long glyphIndex);
    @Method(selector = "truncatedGlyphRangeInLineFragmentForGlyphAtIndex:")
    public native @ByVal NSRange getTruncatedGlyphRangeInLineFragment(@MachineSizedUInt long glyphIndex);
    @Method(selector = "glyphRangeForCharacterRange:actualCharacterRange:")
    public native @ByVal NSRange getGlyphRangeForCharacterRange(@ByVal NSRange charRange, NSRange actualCharRange);
    @Method(selector = "characterRangeForGlyphRange:actualGlyphRange:")
    public native @ByVal NSRange getCharacterRangeForGlyphRange(@ByVal NSRange glyphRange, NSRange actualGlyphRange);
    @Method(selector = "glyphRangeForTextContainer:")
    public native @ByVal NSRange glyphRangeForTextContainer(NSTextContainer container);
    @Method(selector = "rangeOfNominallySpacedGlyphsContainingIndex:")
    public native @ByVal NSRange getRangeOfNominallySpacedGlyphsContainingIndex(@MachineSizedUInt long glyphIndex);
    @Method(selector = "boundingRectForGlyphRange:inTextContainer:")
    public native @ByVal CGRect getBoundingRectForGlyphRange(@ByVal NSRange glyphRange, NSTextContainer container);
    @Method(selector = "glyphRangeForBoundingRect:inTextContainer:")
    public native @ByVal NSRange getGlyphRangeForBoundingRect(@ByVal CGRect bounds, NSTextContainer container);
    @Method(selector = "glyphRangeForBoundingRectWithoutAdditionalLayout:inTextContainer:")
    public native @ByVal NSRange getGlyphRangeForBoundingRectWithoutAdditionalLayout(@ByVal CGRect bounds, NSTextContainer container);
    @Method(selector = "glyphIndexForPoint:inTextContainer:fractionOfDistanceThroughGlyph:")
    public native @MachineSizedUInt long getSlyphIndexForPoint(@ByVal CGPoint point, NSTextContainer container, MachineSizedFloatPtr partialFraction);
    @Method(selector = "glyphIndexForPoint:inTextContainer:")
    public native @MachineSizedUInt long getSlyphIndexForPoint(@ByVal CGPoint point, NSTextContainer container);
    @Method(selector = "fractionOfDistanceThroughGlyphForPoint:inTextContainer:")
    public native @MachineSizedFloat double getFractionOfDistanceThroughGlyphForPoint(@ByVal CGPoint point, NSTextContainer container);
    @Method(selector = "characterIndexForPoint:inTextContainer:fractionOfDistanceBetweenInsertionPoints:")
    public native @MachineSizedUInt long getCharacterIndexForPoint(@ByVal CGPoint point, NSTextContainer container, MachineSizedFloatPtr partialFraction);
    @Method(selector = "getLineFragmentInsertionPointsForCharacterAtIndex:alternatePositions:inDisplayOrder:positions:characterIndexes:")
    public native @MachineSizedUInt long getLineFragmentInsertionPoints(@MachineSizedUInt long charIndex, boolean aFlag, boolean dFlag, MachineSizedFloatPtr positions, MachineSizedUIntPtr charIndexes);
    @Method(selector = "enumerateLineFragmentsForGlyphRange:usingBlock:")
    public native void enumerateLineFragments(@ByVal NSRange glyphRange, @Block("(@ByVal, @ByVal, , @ByVal, )") VoidBlock5<CGRect, CGRect, NSTextContainer, NSRange, BytePtr> block);
    @Method(selector = "enumerateEnclosingRectsForGlyphRange:withinSelectedGlyphRange:inTextContainer:usingBlock:")
    public native void enumerateEnclosingRects(@ByVal NSRange glyphRange, @ByVal NSRange selectedRange, NSTextContainer textContainer, @Block("(@ByVal, )") VoidBlock2<CGRect, BytePtr> block);
    @Method(selector = "drawBackgroundForGlyphRange:atPoint:")
    public native void drawBackground(@ByVal NSRange glyphsToShow, @ByVal CGPoint origin);
    @Method(selector = "drawGlyphsForGlyphRange:atPoint:")
    public native void drawGlyphs(@ByVal NSRange glyphsToShow, @ByVal CGPoint origin);
    @Method(selector = "showCGGlyphs:positions:count:font:matrix:attributes:inContext:")
    protected native void showCGGlyphs(@Pointer long glyphs, CGPoint positions, @MachineSizedUInt long glyphCount, UIFont font, @ByVal CGAffineTransform textMatrix, NSDictionary<?, ?> attributes, CGContext graphicsContext);
    @Method(selector = "fillBackgroundRectArray:count:forCharacterRange:color:")
    protected native void fillBackground(CGRect rectArray, @MachineSizedUInt long rectCount, @ByVal NSRange charRange, UIColor color);
    @Method(selector = "drawUnderlineForGlyphRange:underlineType:baselineOffset:lineFragmentRect:lineFragmentGlyphRange:containerOrigin:")
    public native void drawUnderline(@ByVal NSRange glyphRange, NSUnderlineStyle underlineVal, @MachineSizedFloat double baselineOffset, @ByVal CGRect lineRect, @ByVal NSRange lineGlyphRange, @ByVal CGPoint containerOrigin);
    @Method(selector = "underlineGlyphRange:underlineType:lineFragmentRect:lineFragmentGlyphRange:containerOrigin:")
    public native void underline(@ByVal NSRange glyphRange, NSUnderlineStyle underlineVal, @ByVal CGRect lineRect, @ByVal NSRange lineGlyphRange, @ByVal CGPoint containerOrigin);
    @Method(selector = "drawStrikethroughForGlyphRange:strikethroughType:baselineOffset:lineFragmentRect:lineFragmentGlyphRange:containerOrigin:")
    public native void drawStrikethrough(@ByVal NSRange glyphRange, NSUnderlineStyle strikethroughVal, @MachineSizedFloat double baselineOffset, @ByVal CGRect lineRect, @ByVal NSRange lineGlyphRange, @ByVal CGPoint containerOrigin);
    @Method(selector = "strikethroughGlyphRange:strikethroughType:lineFragmentRect:lineFragmentGlyphRange:containerOrigin:")
    public native void strikethrough(@ByVal NSRange glyphRange, NSUnderlineStyle strikethroughVal, @ByVal CGRect lineRect, @ByVal NSRange lineGlyphRange, @ByVal CGPoint containerOrigin);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
