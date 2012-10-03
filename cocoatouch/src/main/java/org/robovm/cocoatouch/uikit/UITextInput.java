/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

@Protocol
public interface /*<name>*/ UITextInput /*</name>*/ /*<implements>*/ extends UIKeyInput /*</implements>*/ {

    /*<properties>*/
    @Bind("beginningOfDocument") @Type("UITextPosition *") UITextPosition getBeginningOfDocument();
    @Bind("endOfDocument") @Type("UITextPosition *") UITextPosition getEndOfDocument();
    @Bind("inputDelegate") @Type("id<UITextInputDelegate>") UITextInputDelegate getInputDelegate();
    @Bind("setInputDelegate:") void setInputDelegate(@Type("id<UITextInputDelegate>") UITextInputDelegate v);
    @Bind("markedTextRange") @Type("UITextRange *") UITextRange getMarkedTextRange();
    @Bind("markedTextStyle") @Type("NSDictionary *") NSDictionary getMarkedTextStyle();
    @Bind("setMarkedTextStyle:") void setMarkedTextStyle(@Type("NSDictionary *") NSDictionary v);
    @Bind("selectedTextRange") @Type("UITextRange *") UITextRange getSelectedTextRange();
    @Bind("setSelectedTextRange:") void setSelectedTextRange(@Type("UITextRange *") UITextRange v);
    @Bind("selectionAffinity") @Type("UITextStorageDirection") UITextStorageDirection getSelectionAffinity();
    @Bind("setSelectionAffinity:") void setSelectionAffinity(@Type("UITextStorageDirection") UITextStorageDirection v);
    @Bind("textInputView") @Type("UIView *") UIView getTextInputView();
    @Bind("tokenizer") @Type("id<UITextInputTokenizer>") UITextInputTokenizer getTokenizer();
    /*</properties>*/
    /*<methods>*/
    @Bind("comparePosition:toPosition:") @Type("NSComparisonResult") NSComparisonResult comparePositions(@Type("UITextPosition *") UITextPosition position, @Type("UITextPosition *") UITextPosition other);
    @Bind("dictationRecognitionFailed") @Type("void") void dictationRecognitionFailed();
    @Bind("dictationRecordingDidEnd") @Type("void") void dictationRecordingDidEnd();
    @Bind("frameForDictationResultPlaceholder:") @Type("CGRect") CGRect frameForDictationResultPlaceholder(@Type("id") NSObject placeholder);
    @Bind("baseWritingDirectionForPosition:inDirection:") @Type("UITextWritingDirection") UITextWritingDirection getBaseWritingDirection(@Type("UITextPosition *") UITextPosition position, @Type("UITextStorageDirection") UITextStorageDirection direction);
    @Bind("caretRectForPosition:") @Type("CGRect") CGRect getCaretRect(@Type("UITextPosition *") UITextPosition position);
    @Bind("characterOffsetOfPosition:withinRange:") @Type("NSInteger") int getCharacterOffset(@Type("UITextPosition *") UITextPosition position, @Type("UITextRange *") UITextRange range);
    @Bind("characterRangeByExtendingPosition:inDirection:") @Type("UITextRange *") UITextRange getCharacterRange(@Type("UITextPosition *") UITextPosition position, @Type("UITextLayoutDirection") UITextLayoutDirection direction);
    @Bind("characterRangeAtPoint:") @Type("UITextRange *") UITextRange getCharacterRange(@Type("CGPoint") CGPoint point);
    @Bind("closestPositionToPoint:") @Type("UITextPosition *") UITextPosition getClosestPosition(@Type("CGPoint") CGPoint point);
    @Bind("closestPositionToPoint:withinRange:") @Type("UITextPosition *") UITextPosition getClosestPosition(@Type("CGPoint") CGPoint point, @Type("UITextRange *") UITextRange range);
    @Bind("firstRectForRange:") @Type("CGRect") CGRect getFirstRect(@Type("UITextRange *") UITextRange range);
    @Bind("offsetFromPosition:toPosition:") @Type("NSInteger") int getOffset(@Type("UITextPosition *") UITextPosition fromPosition, @Type("UITextPosition *") UITextPosition toPosition);
    @Bind("positionWithinRange:farthestInDirection:") @Type("UITextPosition *") UITextPosition getPosition(@Type("UITextRange *") UITextRange range, @Type("UITextLayoutDirection") UITextLayoutDirection direction);
    @Bind("positionWithinRange:atCharacterOffset:") @Type("UITextPosition *") UITextPosition getPosition(@Type("UITextRange *") UITextRange range, @Type("NSInteger") int offset);
    @Bind("positionFromPosition:offset:") @Type("UITextPosition *") UITextPosition getPosition(@Type("UITextPosition *") UITextPosition position, @Type("NSInteger") int offset);
    @Bind("positionFromPosition:inDirection:offset:") @Type("UITextPosition *") UITextPosition getPosition(@Type("UITextPosition *") UITextPosition position, @Type("UITextLayoutDirection") UITextLayoutDirection direction, @Type("NSInteger") int offset);
    @Bind("textInRange:") @Type("NSString *") String getText(@Type("UITextRange *") UITextRange range);
    @Bind("textRangeFromPosition:toPosition:") @Type("UITextRange *") UITextRange getTextRange(@Type("UITextPosition *") UITextPosition fromPosition, @Type("UITextPosition *") UITextPosition toPosition);
    @Bind("textStylingAtPosition:inDirection:") @Type("NSDictionary *") NSDictionary getTextStyling(@Type("UITextPosition *") UITextPosition position, @Type("UITextStorageDirection") UITextStorageDirection direction);
    @Bind("insertDictationResult:") @Type("void") void insertDictationResult(@Type("NSArray *") NSArray dictationResult);
    @Bind("insertDictationResultPlaceholder") @Type("id") NSObject insertDictationResultPlaceholder();
    @Bind("removeDictationResultPlaceholder:willInsertResult:") @Type("void") void removeDictationResultPlaceholder(@Type("id") NSObject placeholder, @Type("BOOL") boolean willInsertResult);
    @Bind("replaceRange:withText:") @Type("void") void replaceText(@Type("UITextRange *") UITextRange range, @Type("NSString *") String text);
    @Bind("selectionRectsForRange:") @Type("NSArray *") NSArray selectionRectsForRange(@Type("UITextRange *") UITextRange range);
    @Bind("setBaseWritingDirection:forRange:") @Type("void") void setBaseWritingDirection(@Type("UITextWritingDirection") UITextWritingDirection writingDirection, @Type("UITextRange *") UITextRange range);
    @Bind("setMarkedText:selectedRange:") @Type("void") void setMarkedText(@Type("NSString *") String markedText, @Type("NSRange") NSRange selectedRange);
    @Bind("shouldChangeTextInRange:replacementText:") @Type("BOOL") boolean shouldChangeTextInRange(@Type("UITextRange *") UITextRange range, @Type("NSString *") String text);
    @Bind("unmarkText") @Type("void") void unmarkText();
    /*</methods>*/

}
