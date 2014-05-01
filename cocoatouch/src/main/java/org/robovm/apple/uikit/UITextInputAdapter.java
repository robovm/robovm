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
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UITextInputAdapter/*</name>*/ 
    extends /*<extends>*/UIKeyInputAdapter/*</extends>*/ 
    /*<implements>*/implements UITextInput/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    @NotImplemented("selectedTextRange")
    public UITextRange getSelectedTextRange() { throw new UnsupportedOperationException(); }
    @NotImplemented("setSelectedTextRange:")
    public void setSelectedTextRange(UITextRange v) { throw new UnsupportedOperationException(); }
    @NotImplemented("markedTextRange")
    public UITextRange getMarkedTextRange() { throw new UnsupportedOperationException(); }
    @NotImplemented("markedTextStyle")
    public NSDictionary<NSString, ?> getMarkedTextStyle() { throw new UnsupportedOperationException(); }
    @NotImplemented("setMarkedTextStyle:")
    public void setMarkedTextStyle(NSDictionary<NSString, ?> v) { throw new UnsupportedOperationException(); }
    @NotImplemented("beginningOfDocument")
    public UITextPosition getBeginningOfDocument() { throw new UnsupportedOperationException(); }
    @NotImplemented("endOfDocument")
    public UITextPosition getEndOfDocument() { throw new UnsupportedOperationException(); }
    @NotImplemented("inputDelegate")
    public UITextInputDelegate getInputDelegate() { throw new UnsupportedOperationException(); }
    @NotImplemented("setInputDelegate:")
    public void setInputDelegate(UITextInputDelegate v) { throw new UnsupportedOperationException(); }
    @NotImplemented("tokenizer")
    public UITextInputTokenizer getTokenizer() { throw new UnsupportedOperationException(); }
    @NotImplemented("textInputView")
    public UIView getTextInputView() { throw new UnsupportedOperationException(); }
    @NotImplemented("selectionAffinity")
    public UITextStorageDirection getSelectionAffinity() { throw new UnsupportedOperationException(); }
    @NotImplemented("setSelectionAffinity:")
    public void setSelectionAffinity(UITextStorageDirection v) { throw new UnsupportedOperationException(); }
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("textInRange:")
    public String getText(UITextRange range) { throw new UnsupportedOperationException(); }
    @NotImplemented("replaceRange:withText:")
    public void replaceText(UITextRange range, String text) { throw new UnsupportedOperationException(); }
    @NotImplemented("setMarkedText:selectedRange:")
    public void setMarkedText(String markedText, @ByVal NSRange selectedRange) { throw new UnsupportedOperationException(); }
    @NotImplemented("unmarkText")
    public void unmarkText() { throw new UnsupportedOperationException(); }
    @NotImplemented("textRangeFromPosition:toPosition:")
    public UITextRange getTextRange(UITextPosition fromPosition, UITextPosition toPosition) { throw new UnsupportedOperationException(); }
    @NotImplemented("positionFromPosition:offset:")
    public UITextPosition getPosition(UITextPosition position, @MachineSizedSInt long offset) { throw new UnsupportedOperationException(); }
    @NotImplemented("positionFromPosition:inDirection:offset:")
    public UITextPosition getPosition(UITextPosition position, UITextLayoutDirection direction, @MachineSizedSInt long offset) { throw new UnsupportedOperationException(); }
    @NotImplemented("comparePosition:toPosition:")
    public NSComparisonResult comparePositions(UITextPosition position, UITextPosition other) { throw new UnsupportedOperationException(); }
    @NotImplemented("offsetFromPosition:toPosition:")
    public @MachineSizedSInt long getOffset(UITextPosition from, UITextPosition toPosition) { throw new UnsupportedOperationException(); }
    @NotImplemented("positionWithinRange:farthestInDirection:")
    public UITextPosition getPosition(UITextRange range, UITextLayoutDirection direction) { throw new UnsupportedOperationException(); }
    @NotImplemented("characterRangeByExtendingPosition:inDirection:")
    public UITextRange getCharacterRange(UITextPosition position, UITextLayoutDirection direction) { throw new UnsupportedOperationException(); }
    @NotImplemented("baseWritingDirectionForPosition:inDirection:")
    public UITextWritingDirection getBaseWritingDirection(UITextPosition position, UITextStorageDirection direction) { throw new UnsupportedOperationException(); }
    @NotImplemented("setBaseWritingDirection:forRange:")
    public void setBaseWritingDirection(UITextWritingDirection writingDirection, UITextRange range) { throw new UnsupportedOperationException(); }
    @NotImplemented("firstRectForRange:")
    public @ByVal CGRect getFirstRect(UITextRange range) { throw new UnsupportedOperationException(); }
    @NotImplemented("caretRectForPosition:")
    public @ByVal CGRect getCaretRect(UITextPosition position) { throw new UnsupportedOperationException(); }
    @NotImplemented("selectionRectsForRange:")
    public NSArray<UITextSelectionRect> getSelectionRects(UITextRange range) { throw new UnsupportedOperationException(); }
    @NotImplemented("closestPositionToPoint:")
    public UITextPosition getClosestPosition(@ByVal CGPoint point) { throw new UnsupportedOperationException(); }
    @NotImplemented("closestPositionToPoint:withinRange:")
    public UITextPosition getClosestPosition(@ByVal CGPoint point, UITextRange range) { throw new UnsupportedOperationException(); }
    @NotImplemented("characterRangeAtPoint:")
    public UITextRange getCharacterRange(@ByVal CGPoint point) { throw new UnsupportedOperationException(); }
    @NotImplemented("shouldChangeTextInRange:replacementText:")
    public boolean shouldChangeText(UITextRange range, String text) { throw new UnsupportedOperationException(); }
    @NotImplemented("textStylingAtPosition:inDirection:")
    public NSDictionary<NSString, ?> getTextStyling(UITextPosition position, UITextStorageDirection direction) { throw new UnsupportedOperationException(); }
    @NotImplemented("positionWithinRange:atCharacterOffset:")
    public UITextPosition getPosition(UITextRange range, @MachineSizedSInt long offset) { throw new UnsupportedOperationException(); }
    @NotImplemented("characterOffsetOfPosition:withinRange:")
    public @MachineSizedSInt long getCharacterOffset(UITextPosition position, UITextRange range) { throw new UnsupportedOperationException(); }
    @NotImplemented("insertDictationResult:")
    public void insertDictationResult(NSArray<UIDictationPhrase> dictationResult) { throw new UnsupportedOperationException(); }
    @NotImplemented("dictationRecordingDidEnd")
    public void dictationRecordingDidEnd() { throw new UnsupportedOperationException(); }
    @NotImplemented("dictationRecognitionFailed")
    public void dictationRecognitionFailed() { throw new UnsupportedOperationException(); }
    @NotImplemented("insertDictationResultPlaceholder")
    public NSObject getInsertDictationResultPlaceholder() { throw new UnsupportedOperationException(); }
    @NotImplemented("frameForDictationResultPlaceholder:")
    public @ByVal CGRect getDictationResultPlaceholderFrame(NSObject placeholder) { throw new UnsupportedOperationException(); }
    @NotImplemented("removeDictationResultPlaceholder:willInsertResult:")
    public void removeDictationResultPlaceholder(NSObject placeholder, boolean willInsertResult) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
