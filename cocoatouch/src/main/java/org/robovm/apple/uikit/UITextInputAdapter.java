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
import org.robovm.rt.annotation.*;
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
/*</javadoc>*/
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
    public UITextRange getSelectedTextRange() { return null; }
    @NotImplemented("setSelectedTextRange:")
    public void setSelectedTextRange(UITextRange v) {}
    @NotImplemented("markedTextRange")
    public UITextRange getMarkedTextRange() { return null; }
    @NotImplemented("markedTextStyle")
    public UITextInputTextStyle getMarkedTextStyle() { return null; }
    @NotImplemented("setMarkedTextStyle:")
    public void setMarkedTextStyle(UITextInputTextStyle v) {}
    @NotImplemented("beginningOfDocument")
    public UITextPosition getBeginningOfDocument() { return null; }
    @NotImplemented("endOfDocument")
    public UITextPosition getEndOfDocument() { return null; }
    @NotImplemented("inputDelegate")
    public UITextInputDelegate getInputDelegate() { return null; }
    @NotImplemented("setInputDelegate:")
    public void setInputDelegate(UITextInputDelegate v) {}
    @NotImplemented("tokenizer")
    public UITextInputTokenizer getTokenizer() { return null; }
    @NotImplemented("textInputView")
    public UIView getTextInputView() { return null; }
    @NotImplemented("selectionAffinity")
    public UITextStorageDirection getSelectionAffinity() { return null; }
    @NotImplemented("setSelectionAffinity:")
    public void setSelectionAffinity(UITextStorageDirection v) {}
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("textInRange:")
    public String getText(UITextRange range) { return null; }
    @NotImplemented("replaceRange:withText:")
    public void replaceText(UITextRange range, String text) {}
    @NotImplemented("setMarkedText:selectedRange:")
    public void setMarkedText(String markedText, @ByVal NSRange selectedRange) {}
    @NotImplemented("unmarkText")
    public void unmarkText() {}
    @NotImplemented("textRangeFromPosition:toPosition:")
    public UITextRange getTextRange(UITextPosition fromPosition, UITextPosition toPosition) { return null; }
    @NotImplemented("positionFromPosition:offset:")
    public UITextPosition getPosition(UITextPosition position, @MachineSizedSInt long offset) { return null; }
    @NotImplemented("positionFromPosition:inDirection:offset:")
    public UITextPosition getPosition(UITextPosition position, UITextLayoutDirection direction, @MachineSizedSInt long offset) { return null; }
    @NotImplemented("comparePosition:toPosition:")
    public NSComparisonResult comparePositions(UITextPosition position, UITextPosition other) { return null; }
    @NotImplemented("offsetFromPosition:toPosition:")
    public @MachineSizedSInt long getOffset(UITextPosition from, UITextPosition toPosition) { return 0; }
    @NotImplemented("positionWithinRange:farthestInDirection:")
    public UITextPosition getPosition(UITextRange range, UITextLayoutDirection direction) { return null; }
    @NotImplemented("characterRangeByExtendingPosition:inDirection:")
    public UITextRange getCharacterRange(UITextPosition position, UITextLayoutDirection direction) { return null; }
    @NotImplemented("baseWritingDirectionForPosition:inDirection:")
    public UITextWritingDirection getBaseWritingDirection(UITextPosition position, UITextStorageDirection direction) { return null; }
    @NotImplemented("setBaseWritingDirection:forRange:")
    public void setBaseWritingDirection(UITextWritingDirection writingDirection, UITextRange range) {}
    @NotImplemented("firstRectForRange:")
    public @ByVal CGRect getFirstRect(UITextRange range) { return null; }
    @NotImplemented("caretRectForPosition:")
    public @ByVal CGRect getCaretRect(UITextPosition position) { return null; }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @NotImplemented("selectionRectsForRange:")
    public NSArray<UITextSelectionRect> getSelectionRects(UITextRange range) { return null; }
    @NotImplemented("closestPositionToPoint:")
    public UITextPosition getClosestPosition(@ByVal CGPoint point) { return null; }
    @NotImplemented("closestPositionToPoint:withinRange:")
    public UITextPosition getClosestPosition(@ByVal CGPoint point, UITextRange range) { return null; }
    @NotImplemented("characterRangeAtPoint:")
    public UITextRange getCharacterRange(@ByVal CGPoint point) { return null; }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @NotImplemented("shouldChangeTextInRange:replacementText:")
    public boolean shouldChangeText(UITextRange range, String text) { return false; }
    @NotImplemented("textStylingAtPosition:inDirection:")
    public UITextInputTextStyle getTextStyling(UITextPosition position, UITextStorageDirection direction) { return null; }
    @NotImplemented("positionWithinRange:atCharacterOffset:")
    public UITextPosition getPosition(UITextRange range, @MachineSizedSInt long offset) { return null; }
    @NotImplemented("characterOffsetOfPosition:withinRange:")
    public @MachineSizedSInt long getCharacterOffset(UITextPosition position, UITextRange range) { return 0; }
    @NotImplemented("insertDictationResult:")
    public void insertDictationResult(NSArray<UIDictationPhrase> dictationResult) {}
    @NotImplemented("dictationRecordingDidEnd")
    public void dictationRecordingDidEnd() {}
    @NotImplemented("dictationRecognitionFailed")
    public void dictationRecognitionFailed() {}
    @NotImplemented("insertDictationResultPlaceholder")
    public NSObject getInsertDictationResultPlaceholder() { return null; }
    @NotImplemented("frameForDictationResultPlaceholder:")
    public @ByVal CGRect getDictationResultPlaceholderFrame(NSObject placeholder) { return null; }
    @NotImplemented("removeDictationResultPlaceholder:willInsertResult:")
    public void removeDictationResultPlaceholder(NSObject placeholder, boolean willInsertResult) {}
    /*</methods>*/
}
