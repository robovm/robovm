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

/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UITextView /*</name>*/ 
    extends /*<extends>*/ UIScrollView /*</extends>*/ 
    /*<implements>*/ implements UITextInput, UIKeyInput, UITextInputTraits /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITextView /*</name>*/.class);
    }

    /*<constructors>*/
    public UITextView() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("allowsEditingTextAttributes") public native @Type("BOOL") boolean isAllowsEditingTextAttributes();
    @Bind("setAllowsEditingTextAttributes:") public native void setAllowsEditingTextAttributes(@Type("BOOL") boolean v);
    @Bind("attributedText") public native @Type("NSAttributedString *") NSAttributedString getAttributedText();
    @Bind("setAttributedText:") public native void setAttributedText(@Type("NSAttributedString *") NSAttributedString v);
    @Bind("autocapitalizationType") public native @Type("UITextAutocapitalizationType") UITextAutocapitalizationType getAutocapitalizationType();
    @Bind("setAutocapitalizationType:") public native void setAutocapitalizationType(@Type("UITextAutocapitalizationType") UITextAutocapitalizationType v);
    @Bind("autocorrectionType") public native @Type("UITextAutocorrectionType") UITextAutocorrectionType getAutocorrectionType();
    @Bind("setAutocorrectionType:") public native void setAutocorrectionType(@Type("UITextAutocorrectionType") UITextAutocorrectionType v);
    @Bind("beginningOfDocument") public native @Type("UITextPosition *") UITextPosition getBeginningOfDocument();
    @Bind("clearsOnInsertion") public native @Type("BOOL") boolean isClearsOnInsertion();
    @Bind("setClearsOnInsertion:") public native void setClearsOnInsertion(@Type("BOOL") boolean v);
    @Bind("dataDetectorTypes") public native @Type("UIDataDetectorTypes") EnumSet<UIDataDetectorType> getDataDetectorTypes();
    @Bind("setDataDetectorTypes:") public native void setDataDetectorTypes(@Type("UIDataDetectorTypes") EnumSet<UIDataDetectorType> v);
    @Bind("delegate") public native @Type("id<UITextViewDelegate>") UITextViewDelegate getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UITextViewDelegate>") UITextViewDelegate v);
    @Bind("isEditable") public native @Type("BOOL") boolean isEditable();
    @Bind("setEditable:") public native void setEditable(@Type("BOOL") boolean v);
    @Bind("enablesReturnKeyAutomatically") public native @Type("BOOL") boolean isEnablesReturnKeyAutomatically();
    @Bind("setEnablesReturnKeyAutomatically:") public native void setEnablesReturnKeyAutomatically(@Type("BOOL") boolean v);
    @Bind("endOfDocument") public native @Type("UITextPosition *") UITextPosition getEndOfDocument();
    @Bind("font") public native @Type("UIFont *") UIFont getFont();
    @Bind("setFont:") public native void setFont(@Type("UIFont *") UIFont v);
    @Bind("inputAccessoryView") public native @Type("UIView *") UIView getInputAccessoryView();
    @Bind("setInputAccessoryView:") public native void setInputAccessoryView(@Type("UIView *") UIView v);
    @Bind("inputDelegate") public native @Type("id<UITextInputDelegate>") UITextInputDelegate getInputDelegate();
    @Bind("setInputDelegate:") public native void setInputDelegate(@Type("id<UITextInputDelegate>") UITextInputDelegate v);
    @Bind("inputView") public native @Type("UIView *") UIView getInputView();
    @Bind("setInputView:") public native void setInputView(@Type("UIView *") UIView v);
    @Bind("keyboardAppearance") public native @Type("UIKeyboardAppearance") UIKeyboardAppearance getKeyboardAppearance();
    @Bind("setKeyboardAppearance:") public native void setKeyboardAppearance(@Type("UIKeyboardAppearance") UIKeyboardAppearance v);
    @Bind("keyboardType") public native @Type("UIKeyboardType") UIKeyboardType getKeyboardType();
    @Bind("setKeyboardType:") public native void setKeyboardType(@Type("UIKeyboardType") UIKeyboardType v);
    @Bind("markedTextRange") public native @Type("UITextRange *") UITextRange getMarkedTextRange();
    @Bind("markedTextStyle") public native @Type("NSDictionary *") NSDictionary getMarkedTextStyle();
    @Bind("setMarkedTextStyle:") public native void setMarkedTextStyle(@Type("NSDictionary *") NSDictionary v);
    @Bind("returnKeyType") public native @Type("UIReturnKeyType") UIReturnKeyType getReturnKeyType();
    @Bind("setReturnKeyType:") public native void setReturnKeyType(@Type("UIReturnKeyType") UIReturnKeyType v);
    @Bind("isSecureTextEntry") public native @Type("BOOL") boolean isSecureTextEntry();
    @Bind("setSecureTextEntry:") public native void setSecureTextEntry(@Type("BOOL") boolean v);
    @Bind("selectedRange") public native @Type("NSRange") NSRange getSelectedRange();
    @Bind("setSelectedRange:") public native void setSelectedRange(@Type("NSRange") NSRange v);
    @Bind("selectedTextRange") public native @Type("UITextRange *") UITextRange getSelectedTextRange();
    @Bind("setSelectedTextRange:") public native void setSelectedTextRange(@Type("UITextRange *") UITextRange v);
    @Bind("selectionAffinity") public native @Type("UITextStorageDirection") UITextStorageDirection getSelectionAffinity();
    @Bind("setSelectionAffinity:") public native void setSelectionAffinity(@Type("UITextStorageDirection") UITextStorageDirection v);
    @Bind("spellCheckingType") public native @Type("UITextSpellCheckingType") UITextSpellCheckingType getSpellCheckingType();
    @Bind("setSpellCheckingType:") public native void setSpellCheckingType(@Type("UITextSpellCheckingType") UITextSpellCheckingType v);
    @Bind("text") public native @Type("NSString *") String getText();
    @Bind("setText:") public native void setText(@Type("NSString *") String v);
    @Bind("textAlignment") public native @Type("NSTextAlignment") NSTextAlignment getTextAlignment();
    @Bind("setTextAlignment:") public native void setTextAlignment(@Type("NSTextAlignment") NSTextAlignment v);
    @Bind("textColor") public native @Type("UIColor *") UIColor getTextColor();
    @Bind("setTextColor:") public native void setTextColor(@Type("UIColor *") UIColor v);
    @Bind("textInputView") public native @Type("UIView *") UIView getTextInputView();
    @Bind("tokenizer") public native @Type("id<UITextInputTokenizer>") UITextInputTokenizer getTokenizer();
    @Bind("typingAttributes") public native @Type("NSDictionary *") NSDictionary getTypingAttributes();
    @Bind("setTypingAttributes:") public native void setTypingAttributes(@Type("NSDictionary *") NSDictionary v);
    /*</properties>*/
    /*<methods>*/
    @Bind("comparePosition:toPosition:") public native @Type("NSComparisonResult") NSComparisonResult comparePositions(@Type("UITextPosition *") UITextPosition position, @Type("UITextPosition *") UITextPosition other);
    @Bind("deleteBackward") public native @Type("void") void deleteBackward();
    @Bind("dictationRecognitionFailed") public native @Type("void") void dictationRecognitionFailed();
    @Bind("dictationRecordingDidEnd") public native @Type("void") void dictationRecordingDidEnd();
    @Bind("frameForDictationResultPlaceholder:") public native @Type("CGRect") CGRect frameForDictationResultPlaceholder(@Type("id") NSObject placeholder);
    @Bind("baseWritingDirectionForPosition:inDirection:") public native @Type("UITextWritingDirection") UITextWritingDirection getBaseWritingDirection(@Type("UITextPosition *") UITextPosition position, @Type("UITextStorageDirection") UITextStorageDirection direction);
    @Bind("caretRectForPosition:") public native @Type("CGRect") CGRect getCaretRect(@Type("UITextPosition *") UITextPosition position);
    @Bind("characterOffsetOfPosition:withinRange:") public native @Type("NSInteger") int getCharacterOffset(@Type("UITextPosition *") UITextPosition position, @Type("UITextRange *") UITextRange range);
    @Bind("characterRangeAtPoint:") public native @Type("UITextRange *") UITextRange getCharacterRange(@Type("CGPoint") CGPoint point);
    @Bind("characterRangeByExtendingPosition:inDirection:") public native @Type("UITextRange *") UITextRange getCharacterRange(@Type("UITextPosition *") UITextPosition position, @Type("UITextLayoutDirection") UITextLayoutDirection direction);
    @Bind("closestPositionToPoint:withinRange:") public native @Type("UITextPosition *") UITextPosition getClosestPosition(@Type("CGPoint") CGPoint point, @Type("UITextRange *") UITextRange range);
    @Bind("closestPositionToPoint:") public native @Type("UITextPosition *") UITextPosition getClosestPosition(@Type("CGPoint") CGPoint point);
    @Bind("firstRectForRange:") public native @Type("CGRect") CGRect getFirstRect(@Type("UITextRange *") UITextRange range);
    @Bind("offsetFromPosition:toPosition:") public native @Type("NSInteger") int getOffset(@Type("UITextPosition *") UITextPosition fromPosition, @Type("UITextPosition *") UITextPosition toPosition);
    @Bind("positionFromPosition:inDirection:offset:") public native @Type("UITextPosition *") UITextPosition getPosition(@Type("UITextPosition *") UITextPosition position, @Type("UITextLayoutDirection") UITextLayoutDirection direction, @Type("NSInteger") int offset);
    @Bind("positionWithinRange:farthestInDirection:") public native @Type("UITextPosition *") UITextPosition getPosition(@Type("UITextRange *") UITextRange range, @Type("UITextLayoutDirection") UITextLayoutDirection direction);
    @Bind("positionWithinRange:atCharacterOffset:") public native @Type("UITextPosition *") UITextPosition getPosition(@Type("UITextRange *") UITextRange range, @Type("NSInteger") int offset);
    @Bind("positionFromPosition:offset:") public native @Type("UITextPosition *") UITextPosition getPosition(@Type("UITextPosition *") UITextPosition position, @Type("NSInteger") int offset);
    @Bind("textInRange:") public native @Type("NSString *") String getText(@Type("UITextRange *") UITextRange range);
    @Bind("textRangeFromPosition:toPosition:") public native @Type("UITextRange *") UITextRange getTextRange(@Type("UITextPosition *") UITextPosition fromPosition, @Type("UITextPosition *") UITextPosition toPosition);
    @Bind("textStylingAtPosition:inDirection:") public native @Type("NSDictionary *") NSDictionary getTextStyling(@Type("UITextPosition *") UITextPosition position, @Type("UITextStorageDirection") UITextStorageDirection direction);
    @Bind("hasText") public native @Type("BOOL") boolean hasText();
    @Bind("insertDictationResult:") public native @Type("void") void insertDictationResult(@Type("NSArray *") NSArray dictationResult);
    @Bind("insertDictationResultPlaceholder") public native @Type("id") NSObject insertDictationResultPlaceholder();
    @Bind("insertText:") public native @Type("void") void insertText(@Type("NSString *") String text);
    @Bind("removeDictationResultPlaceholder:willInsertResult:") public native @Type("void") void removeDictationResultPlaceholder(@Type("id") NSObject placeholder, @Type("BOOL") boolean willInsertResult);
    @Bind("replaceRange:withText:") public native @Type("void") void replaceText(@Type("UITextRange *") UITextRange range, @Type("NSString *") String text);
    @Bind("scrollRangeToVisible:") public native @Type("void") void scrollRangeToVisible(@Type("NSRange") NSRange range);
    @Bind("selectionRectsForRange:") public native @Type("NSArray *") NSArray selectionRectsForRange(@Type("UITextRange *") UITextRange range);
    @Bind("setBaseWritingDirection:forRange:") public native @Type("void") void setBaseWritingDirection(@Type("UITextWritingDirection") UITextWritingDirection writingDirection, @Type("UITextRange *") UITextRange range);
    @Bind("setMarkedText:selectedRange:") public native @Type("void") void setMarkedText(@Type("NSString *") String markedText, @Type("NSRange") NSRange selectedRange);
    @Bind("shouldChangeTextInRange:replacementText:") public native @Type("BOOL") boolean shouldChangeTextInRange(@Type("UITextRange *") UITextRange range, @Type("NSString *") String text);
    @Bind("unmarkText") public native @Type("void") void unmarkText();
    /*</methods>*/

}
