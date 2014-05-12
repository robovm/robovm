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

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/NSLayoutManagerDelegate/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "layoutManager:shouldGenerateGlyphs:properties:characterIndexes:font:forGlyphRange:")
    @MachineSizedUInt long shouldGenerateGlyphs(NSLayoutManager layoutManager, ShortPtr glyphs, MachineSizedSIntPtr props, MachineSizedUIntPtr charIndexes, UIFont aFont, @ByVal NSRange glyphRange);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "layoutManager:lineSpacingAfterGlyphAtIndex:withProposedLineFragmentRect:")
    @MachineSizedFloat double getLineSpacingAfterGlyph(NSLayoutManager layoutManager, @MachineSizedUInt long glyphIndex, @ByVal CGRect rect);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "layoutManager:paragraphSpacingBeforeGlyphAtIndex:withProposedLineFragmentRect:")
    @MachineSizedFloat double getParagraphSpacingBeforeGlyph(NSLayoutManager layoutManager, @MachineSizedUInt long glyphIndex, @ByVal CGRect rect);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "layoutManager:paragraphSpacingAfterGlyphAtIndex:withProposedLineFragmentRect:")
    @MachineSizedFloat double getParagraphSpacingAfterGlyph(NSLayoutManager layoutManager, @MachineSizedUInt long glyphIndex, @ByVal CGRect rect);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "layoutManager:shouldUseAction:forControlCharacterAtIndex:")
    NSControlCharacterAction shouldUseAction(NSLayoutManager layoutManager, NSControlCharacterAction action, @MachineSizedUInt long charIndex);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "layoutManager:shouldBreakLineByWordBeforeCharacterAtIndex:")
    boolean shouldBreakLineByWordBeforeCharacter(NSLayoutManager layoutManager, @MachineSizedUInt long charIndex);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "layoutManager:shouldBreakLineByHyphenatingBeforeCharacterAtIndex:")
    boolean shouldBreakLineByHyphenatingBeforeCharacter(NSLayoutManager layoutManager, @MachineSizedUInt long charIndex);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "layoutManager:boundingBoxForControlGlyphAtIndex:forTextContainer:proposedLineFragment:glyphPosition:characterIndex:")
    @ByVal CGRect getBoundingBoxForControlGlyph(NSLayoutManager layoutManager, @MachineSizedUInt long glyphIndex, NSTextContainer textContainer, @ByVal CGRect proposedRect, @ByVal CGPoint glyphPosition, @MachineSizedUInt long charIndex);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "layoutManagerDidInvalidateLayout:")
    void didInvalidateLayout(NSLayoutManager sender);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "layoutManager:didCompleteLayoutForTextContainer:atEnd:")
    void didCompleteLayout(NSLayoutManager layoutManager, NSTextContainer textContainer, boolean layoutFinishedFlag);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "layoutManager:textContainer:didChangeGeometryFromSize:")
    void didChangeGeometry(NSLayoutManager layoutManager, NSTextContainer textContainer, @ByVal CGSize oldSize);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
