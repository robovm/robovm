/*
 * Copyright (C) 2014 Trillian AB
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
    @Method(selector = "layoutManager:shouldGenerateGlyphs:properties:characterIndexes:font:forGlyphRange:")
    @MachineSizedUInt long layoutManager$shouldGenerateGlyphs$properties$characterIndexes$font$forGlyphRange$(NSLayoutManager layoutManager, ShortPtr glyphs, MachineSizedSIntPtr props, MachineSizedUIntPtr charIndexes, UIFont aFont, @ByVal NSRange glyphRange);
    @Method(selector = "layoutManager:lineSpacingAfterGlyphAtIndex:withProposedLineFragmentRect:")
    @MachineSizedFloat double layoutManager$lineSpacingAfterGlyphAtIndex$withProposedLineFragmentRect$(NSLayoutManager layoutManager, @MachineSizedUInt long glyphIndex, @ByVal CGRect rect);
    @Method(selector = "layoutManager:paragraphSpacingBeforeGlyphAtIndex:withProposedLineFragmentRect:")
    @MachineSizedFloat double layoutManager$paragraphSpacingBeforeGlyphAtIndex$withProposedLineFragmentRect$(NSLayoutManager layoutManager, @MachineSizedUInt long glyphIndex, @ByVal CGRect rect);
    @Method(selector = "layoutManager:paragraphSpacingAfterGlyphAtIndex:withProposedLineFragmentRect:")
    @MachineSizedFloat double layoutManager$paragraphSpacingAfterGlyphAtIndex$withProposedLineFragmentRect$(NSLayoutManager layoutManager, @MachineSizedUInt long glyphIndex, @ByVal CGRect rect);
    @Method(selector = "layoutManager:shouldUseAction:forControlCharacterAtIndex:")
    NSControlCharacterAction layoutManager$shouldUseAction$forControlCharacterAtIndex$(NSLayoutManager layoutManager, NSControlCharacterAction action, @MachineSizedUInt long charIndex);
    @Method(selector = "layoutManager:shouldBreakLineByWordBeforeCharacterAtIndex:")
    boolean layoutManager$shouldBreakLineByWordBeforeCharacterAtIndex$(NSLayoutManager layoutManager, @MachineSizedUInt long charIndex);
    @Method(selector = "layoutManager:shouldBreakLineByHyphenatingBeforeCharacterAtIndex:")
    boolean layoutManager$shouldBreakLineByHyphenatingBeforeCharacterAtIndex$(NSLayoutManager layoutManager, @MachineSizedUInt long charIndex);
    @Method(selector = "layoutManager:boundingBoxForControlGlyphAtIndex:forTextContainer:proposedLineFragment:glyphPosition:characterIndex:")
    @ByVal CGRect layoutManager$boundingBoxForControlGlyphAtIndex$forTextContainer$proposedLineFragment$glyphPosition$characterIndex$(NSLayoutManager layoutManager, @MachineSizedUInt long glyphIndex, NSTextContainer textContainer, @ByVal CGRect proposedRect, @ByVal CGPoint glyphPosition, @MachineSizedUInt long charIndex);
    @Method(selector = "layoutManagerDidInvalidateLayout:")
    void layoutManagerDidInvalidateLayout$(NSLayoutManager sender);
    @Method(selector = "layoutManager:didCompleteLayoutForTextContainer:atEnd:")
    void layoutManager$didCompleteLayoutForTextContainer$atEnd$(NSLayoutManager layoutManager, NSTextContainer textContainer, boolean layoutFinishedFlag);
    @Method(selector = "layoutManager:textContainer:didChangeGeometryFromSize:")
    void layoutManager$textContainer$didChangeGeometryFromSize$(NSLayoutManager layoutManager, NSTextContainer textContainer, @ByVal CGSize oldSize);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
