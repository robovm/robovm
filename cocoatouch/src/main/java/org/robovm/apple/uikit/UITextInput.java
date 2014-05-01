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
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/UITextInput/*</name>*/ 
    /*<implements>*/extends UIKeyInput/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    @Property(selector = "selectedTextRange")
    UITextRange getSelectedTextRange();
    @Property(selector = "setSelectedTextRange:")
    void setSelectedTextRange(UITextRange v);
    @Property(selector = "markedTextRange")
    UITextRange getMarkedTextRange();
    @Property(selector = "markedTextStyle")
    NSDictionary<NSString, ?> getMarkedTextStyle();
    @Property(selector = "setMarkedTextStyle:")
    void setMarkedTextStyle(NSDictionary<NSString, ?> v);
    @Property(selector = "beginningOfDocument")
    UITextPosition getBeginningOfDocument();
    @Property(selector = "endOfDocument")
    UITextPosition getEndOfDocument();
    @Property(selector = "inputDelegate")
    UITextInputDelegate getInputDelegate();
    @Property(selector = "setInputDelegate:", strongRef = true)
    void setInputDelegate(UITextInputDelegate v);
    @Property(selector = "tokenizer")
    UITextInputTokenizer getTokenizer();
    @Property(selector = "textInputView")
    UIView getTextInputView();
    @Property(selector = "selectionAffinity")
    UITextStorageDirection getSelectionAffinity();
    @Property(selector = "setSelectionAffinity:")
    void setSelectionAffinity(UITextStorageDirection v);
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "textInRange:")
    String getText(UITextRange range);
    @Method(selector = "replaceRange:withText:")
    void replaceText(UITextRange range, String text);
    @Method(selector = "setMarkedText:selectedRange:")
    void setMarkedText(String markedText, @ByVal NSRange selectedRange);
    @Method(selector = "unmarkText")
    void unmarkText();
    @Method(selector = "textRangeFromPosition:toPosition:")
    UITextRange getTextRange(UITextPosition fromPosition, UITextPosition toPosition);
    @Method(selector = "positionFromPosition:offset:")
    UITextPosition getPosition(UITextPosition position, @MachineSizedSInt long offset);
    @Method(selector = "positionFromPosition:inDirection:offset:")
    UITextPosition getPosition(UITextPosition position, UITextLayoutDirection direction, @MachineSizedSInt long offset);
    @Method(selector = "comparePosition:toPosition:")
    NSComparisonResult comparePositions(UITextPosition position, UITextPosition other);
    @Method(selector = "offsetFromPosition:toPosition:")
    @MachineSizedSInt long getOffset(UITextPosition from, UITextPosition toPosition);
    @Method(selector = "positionWithinRange:farthestInDirection:")
    UITextPosition getPosition(UITextRange range, UITextLayoutDirection direction);
    @Method(selector = "characterRangeByExtendingPosition:inDirection:")
    UITextRange getCharacterRange(UITextPosition position, UITextLayoutDirection direction);
    @Method(selector = "baseWritingDirectionForPosition:inDirection:")
    UITextWritingDirection getBaseWritingDirection(UITextPosition position, UITextStorageDirection direction);
    @Method(selector = "setBaseWritingDirection:forRange:")
    void setBaseWritingDirection(UITextWritingDirection writingDirection, UITextRange range);
    @Method(selector = "firstRectForRange:")
    @ByVal CGRect getFirstRect(UITextRange range);
    @Method(selector = "caretRectForPosition:")
    @ByVal CGRect getCaretRect(UITextPosition position);
    @Method(selector = "selectionRectsForRange:")
    NSArray<UITextSelectionRect> getSelectionRects(UITextRange range);
    @Method(selector = "closestPositionToPoint:")
    UITextPosition getClosestPosition(@ByVal CGPoint point);
    @Method(selector = "closestPositionToPoint:withinRange:")
    UITextPosition getClosestPosition(@ByVal CGPoint point, UITextRange range);
    @Method(selector = "characterRangeAtPoint:")
    UITextRange getCharacterRange(@ByVal CGPoint point);
    @Method(selector = "shouldChangeTextInRange:replacementText:")
    boolean shouldChangeText(UITextRange range, String text);
    @Method(selector = "textStylingAtPosition:inDirection:")
    NSDictionary<NSString, ?> getTextStyling(UITextPosition position, UITextStorageDirection direction);
    @Method(selector = "positionWithinRange:atCharacterOffset:")
    UITextPosition getPosition(UITextRange range, @MachineSizedSInt long offset);
    @Method(selector = "characterOffsetOfPosition:withinRange:")
    @MachineSizedSInt long getCharacterOffset(UITextPosition position, UITextRange range);
    @Method(selector = "insertDictationResult:")
    void insertDictationResult(NSArray<UIDictationPhrase> dictationResult);
    @Method(selector = "dictationRecordingDidEnd")
    void dictationRecordingDidEnd();
    @Method(selector = "dictationRecognitionFailed")
    void dictationRecognitionFailed();
    @Method(selector = "insertDictationResultPlaceholder")
    NSObject getInsertDictationResultPlaceholder();
    @Method(selector = "frameForDictationResultPlaceholder:")
    @ByVal CGRect getDictationResultPlaceholderFrame(NSObject placeholder);
    @Method(selector = "removeDictationResultPlaceholder:willInsertResult:")
    void removeDictationResultPlaceholder(NSObject placeholder, boolean willInsertResult);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
