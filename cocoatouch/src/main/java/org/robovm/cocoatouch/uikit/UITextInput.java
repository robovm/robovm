/*
 * Copyright (C) 2012 Trillian AB
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
import org.robovm.cocoatouch.coredata.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html">UITextInput Protocol Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
public interface /*<name>*/ UITextInput /*</name>*/ /*<implements>*/ extends UIKeyInput, NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/beginningOfDocument">@property(nonatomic, readonly) UITextPosition *beginningOfDocument</a>
     * @since Available in iOS 3.2 and later.
     */
    UITextPosition getBeginningOfDocument();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/endOfDocument">@property(nonatomic, readonly) UITextPosition *endOfDocument</a>
     * @since Available in iOS 3.2 and later.
     */
    UITextPosition getEndOfDocument();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/inputDelegate">@property(nonatomic, assign) id&amp;lt;UITextInputDelegate&amp;gt; inputDelegate</a>
     * @since Available in iOS 3.2 and later.
     */
    UITextInputDelegate getInputDelegate();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/inputDelegate">@property(nonatomic, assign) id&amp;lt;UITextInputDelegate&amp;gt; inputDelegate</a>
     * @since Available in iOS 3.2 and later.
     */
    void setInputDelegate(UITextInputDelegate v);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/markedTextRange">@property(nonatomic, readonly) UITextRange *markedTextRange</a>
     * @since Available in iOS 3.2 and later.
     */
    UITextRange getMarkedTextRange();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/markedTextStyle">@property(nonatomic, copy) NSDictionary *markedTextStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    NSDictionary getMarkedTextStyle();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/markedTextStyle">@property(nonatomic, copy) NSDictionary *markedTextStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    void setMarkedTextStyle(NSDictionary v);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectedTextRange">@property(readwrite, copy) UITextRange *selectedTextRange</a>
     * @since Available in iOS 3.2 and later.
     */
    UITextRange getSelectedTextRange();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectedTextRange">@property(readwrite, copy) UITextRange *selectedTextRange</a>
     * @since Available in iOS 3.2 and later.
     */
    void setSelectedTextRange(UITextRange v);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectionAffinity">@property(nonatomic) UITextStorageDirection selectionAffinity</a>
     * @since Available in iOS 3.2 and later.
     */
    UITextStorageDirection getSelectionAffinity();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectionAffinity">@property(nonatomic) UITextStorageDirection selectionAffinity</a>
     * @since Available in iOS 3.2 and later.
     */
    void setSelectionAffinity(UITextStorageDirection v);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/textInputView">@property(nonatomic, readonly) UIView *textInputView</a>
     * @since Available in iOS 3.2 and later.
     */
    UIView getTextInputView();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/tokenizer">@property(nonatomic, readonly) id&amp;lt;UITextInputTokenizer&amp;gt; tokenizer</a>
     * @since Available in iOS 3.2 and later.
     */
    UITextInputTokenizer getTokenizer();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/comparePosition:toPosition:">- (NSComparisonResult)comparePosition:(UITextPosition *)position toPosition:(UITextPosition *)other</a>
     * @since Available in iOS 3.2 and later.
     */
    NSComparisonResult comparePositions(UITextPosition position, UITextPosition other);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/dictationRecognitionFailed">- (void)dictationRecognitionFailed</a>
     * @since Available in iOS 5.1 and later.
     */
    void dictationRecognitionFailed();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/dictationRecordingDidEnd">- (void)dictationRecordingDidEnd</a>
     * @since Available in iOS 5.1 and later.
     */
    void dictationRecordingDidEnd();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/baseWritingDirectionForPosition:inDirection:">- (UITextWritingDirection)baseWritingDirectionForPosition:(UITextPosition *)position inDirection:(UITextStorageDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    UITextWritingDirection getBaseWritingDirection(UITextPosition position, UITextStorageDirection direction);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/caretRectForPosition:">- (CGRect)caretRectForPosition:(UITextPosition *)position</a>
     * @since Available in iOS 3.2 and later.
     */
    CGRect getCaretRect(UITextPosition position);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/characterOffsetOfPosition:withinRange:">- (NSInteger)characterOffsetOfPosition:(UITextPosition *)position withinRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    int getCharacterOffset(UITextPosition position, UITextRange range);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/characterRangeAtPoint:">- (UITextRange *)characterRangeAtPoint:(CGPoint)point</a>
     * @since Available in iOS 3.2 and later.
     */
    UITextRange getCharacterRange(CGPoint point);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/characterRangeByExtendingPosition:inDirection:">- (UITextRange *)characterRangeByExtendingPosition:(UITextPosition *)position inDirection:(UITextLayoutDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    UITextRange getCharacterRange(UITextPosition position, UITextLayoutDirection direction);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/closestPositionToPoint:">- (UITextPosition *)closestPositionToPoint:(CGPoint)point</a>
     * @since Available in iOS 3.2 and later.
     */
    UITextPosition getClosestPosition(CGPoint point);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/closestPositionToPoint:withinRange:">- (UITextPosition *)closestPositionToPoint:(CGPoint)point withinRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    UITextPosition getClosestPosition(CGPoint point, UITextRange range);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/frameForDictationResultPlaceholder:">- (CGRect)frameForDictationResultPlaceholder:(id)placeholder</a>
     * @since Available in iOS 6.0 and later.
     */
    CGRect getDictationResultPlaceholderFrame(NSObject placeholder);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/firstRectForRange:">- (CGRect)firstRectForRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    CGRect getFirstRect(UITextRange range);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/offsetFromPosition:toPosition:">- (NSInteger)offsetFromPosition:(UITextPosition *)fromPosition toPosition:(UITextPosition *)toPosition</a>
     * @since Available in iOS 3.2 and later.
     */
    int getOffset(UITextPosition fromPosition, UITextPosition toPosition);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionWithinRange:atCharacterOffset:">- (UITextPosition *)positionWithinRange:(UITextRange *)range atCharacterOffset:(NSInteger)offset</a>
     * @since Available in iOS 3.2 and later.
     */
    UITextPosition getPosition(UITextRange range, int offset);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionWithinRange:farthestInDirection:">- (UITextPosition *)positionWithinRange:(UITextRange *)range farthestInDirection:(UITextLayoutDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    UITextPosition getPosition(UITextRange range, UITextLayoutDirection direction);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionFromPosition:inDirection:offset:">- (UITextPosition *)positionFromPosition:(UITextPosition *)position inDirection:(UITextLayoutDirection)direction offset:(NSInteger)offset</a>
     * @since Available in iOS 3.2 and later.
     */
    UITextPosition getPosition(UITextPosition position, UITextLayoutDirection direction, int offset);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionFromPosition:offset:">- (UITextPosition *)positionFromPosition:(UITextPosition *)position offset:(NSInteger)offset</a>
     * @since Available in iOS 3.2 and later.
     */
    UITextPosition getPosition(UITextPosition position, int offset);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/selectionRectsForRange:">- (NSArray *)selectionRectsForRange:(UITextRange *)range</a>
     * @since Available in iOS 6.0 and later.
     */
    NSArray getSelectionRects(UITextRange range);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/textInRange:">- (NSString *)textInRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    String getText(UITextRange range);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/textRangeFromPosition:toPosition:">- (UITextRange *)textRangeFromPosition:(UITextPosition *)fromPosition toPosition:(UITextPosition *)toPosition</a>
     * @since Available in iOS 3.2 and later.
     */
    UITextRange getTextRange(UITextPosition fromPosition, UITextPosition toPosition);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/textStylingAtPosition:inDirection:">- (NSDictionary *)textStylingAtPosition:(UITextPosition *)position inDirection:(UITextStorageDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    NSDictionary getTextStyling(UITextPosition position, UITextStorageDirection direction);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/insertDictationResult:">- (void)insertDictationResult:(NSArray *)dictationResult</a>
     * @since Available in iOS 5.1 and later.
     */
    void insertDictationResult(NSArray dictationResult);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/insertDictationResultPlaceholder">- (id)insertDictationResultPlaceholder</a>
     * @since Available in iOS 6.0 and later.
     */
    NSObject insertDictationResultPlaceholder();
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/removeDictationResultPlaceholder:willInsertResult:">- (void)removeDictationResultPlaceholder:(id)placeholder willInsertResult:(BOOL)willInsertResult</a>
     * @since Available in iOS 6.0 and later.
     */
    void removeDictationResultPlaceholder(NSObject placeholder, boolean willInsertResult);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/replaceRange:withText:">- (void)replaceRange:(UITextRange *)range withText:(NSString *)text</a>
     * @since Available in iOS 3.2 and later.
     */
    void replaceText(UITextRange range, String text);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/setBaseWritingDirection:forRange:">- (void)setBaseWritingDirection:(UITextWritingDirection)writingDirection forRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    void setBaseWritingDirection(UITextWritingDirection writingDirection, UITextRange range);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/setMarkedText:selectedRange:">- (void)setMarkedText:(NSString *)markedText selectedRange:(NSRange)selectedRange</a>
     * @since Available in iOS 3.2 and later.
     */
    void setMarkedText(String markedText, NSRange selectedRange);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/shouldChangeTextInRange:replacementText:">- (BOOL)shouldChangeTextInRange:(UITextRange *)range replacementText:(NSString *)text</a>
     * @since Available in iOS 6.0 and later.
     */
    boolean shouldChangeText(UITextRange range, String text);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/unmarkText">- (void)unmarkText</a>
     * @since Available in iOS 3.2 and later.
     */
    void unmarkText();
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends UIKeyInput.Adapter implements UITextInput {
        @NotImplemented("beginningOfDocument") public UITextPosition getBeginningOfDocument() { throw new UnsupportedOperationException(); }
        @NotImplemented("endOfDocument") public UITextPosition getEndOfDocument() { throw new UnsupportedOperationException(); }
        @NotImplemented("inputDelegate") public UITextInputDelegate getInputDelegate() { throw new UnsupportedOperationException(); }
        @NotImplemented("setInputDelegate:") public void setInputDelegate(UITextInputDelegate inputDelegate) { throw new UnsupportedOperationException(); }
        @NotImplemented("markedTextRange") public UITextRange getMarkedTextRange() { throw new UnsupportedOperationException(); }
        @NotImplemented("markedTextStyle") public NSDictionary getMarkedTextStyle() { throw new UnsupportedOperationException(); }
        @NotImplemented("setMarkedTextStyle:") public void setMarkedTextStyle(NSDictionary markedTextStyle) { throw new UnsupportedOperationException(); }
        @NotImplemented("selectedTextRange") public UITextRange getSelectedTextRange() { throw new UnsupportedOperationException(); }
        @NotImplemented("setSelectedTextRange:") public void setSelectedTextRange(UITextRange selectedTextRange) { throw new UnsupportedOperationException(); }
        @NotImplemented("selectionAffinity") public UITextStorageDirection getSelectionAffinity() { throw new UnsupportedOperationException(); }
        @NotImplemented("setSelectionAffinity:") public void setSelectionAffinity(UITextStorageDirection selectionAffinity) { throw new UnsupportedOperationException(); }
        @NotImplemented("textInputView") public UIView getTextInputView() { throw new UnsupportedOperationException(); }
        @NotImplemented("tokenizer") public UITextInputTokenizer getTokenizer() { throw new UnsupportedOperationException(); }
        @NotImplemented("comparePosition:toPosition:") public NSComparisonResult comparePositions(UITextPosition position, UITextPosition other) { throw new UnsupportedOperationException(); }
        @NotImplemented("dictationRecognitionFailed") public void dictationRecognitionFailed() { throw new UnsupportedOperationException(); }
        @NotImplemented("dictationRecordingDidEnd") public void dictationRecordingDidEnd() { throw new UnsupportedOperationException(); }
        @NotImplemented("baseWritingDirectionForPosition:inDirection:") public UITextWritingDirection getBaseWritingDirection(UITextPosition position, UITextStorageDirection direction) { throw new UnsupportedOperationException(); }
        @NotImplemented("caretRectForPosition:") public CGRect getCaretRect(UITextPosition position) { throw new UnsupportedOperationException(); }
        @NotImplemented("characterOffsetOfPosition:withinRange:") public int getCharacterOffset(UITextPosition position, UITextRange range) { throw new UnsupportedOperationException(); }
        @NotImplemented("characterRangeAtPoint:") public UITextRange getCharacterRange(CGPoint point) { throw new UnsupportedOperationException(); }
        @NotImplemented("characterRangeByExtendingPosition:inDirection:") public UITextRange getCharacterRange(UITextPosition position, UITextLayoutDirection direction) { throw new UnsupportedOperationException(); }
        @NotImplemented("closestPositionToPoint:") public UITextPosition getClosestPosition(CGPoint point) { throw new UnsupportedOperationException(); }
        @NotImplemented("closestPositionToPoint:withinRange:") public UITextPosition getClosestPosition(CGPoint point, UITextRange range) { throw new UnsupportedOperationException(); }
        @NotImplemented("frameForDictationResultPlaceholder:") public CGRect getDictationResultPlaceholderFrame(NSObject placeholder) { throw new UnsupportedOperationException(); }
        @NotImplemented("firstRectForRange:") public CGRect getFirstRect(UITextRange range) { throw new UnsupportedOperationException(); }
        @NotImplemented("offsetFromPosition:toPosition:") public int getOffset(UITextPosition fromPosition, UITextPosition toPosition) { throw new UnsupportedOperationException(); }
        @NotImplemented("positionWithinRange:atCharacterOffset:") public UITextPosition getPosition(UITextRange range, int offset) { throw new UnsupportedOperationException(); }
        @NotImplemented("positionWithinRange:farthestInDirection:") public UITextPosition getPosition(UITextRange range, UITextLayoutDirection direction) { throw new UnsupportedOperationException(); }
        @NotImplemented("positionFromPosition:inDirection:offset:") public UITextPosition getPosition(UITextPosition position, UITextLayoutDirection direction, int offset) { throw new UnsupportedOperationException(); }
        @NotImplemented("positionFromPosition:offset:") public UITextPosition getPosition(UITextPosition position, int offset) { throw new UnsupportedOperationException(); }
        @NotImplemented("selectionRectsForRange:") public NSArray getSelectionRects(UITextRange range) { throw new UnsupportedOperationException(); }
        @NotImplemented("textInRange:") public String getText(UITextRange range) { throw new UnsupportedOperationException(); }
        @NotImplemented("textRangeFromPosition:toPosition:") public UITextRange getTextRange(UITextPosition fromPosition, UITextPosition toPosition) { throw new UnsupportedOperationException(); }
        @NotImplemented("textStylingAtPosition:inDirection:") public NSDictionary getTextStyling(UITextPosition position, UITextStorageDirection direction) { throw new UnsupportedOperationException(); }
        @NotImplemented("insertDictationResult:") public void insertDictationResult(NSArray dictationResult) { throw new UnsupportedOperationException(); }
        @NotImplemented("insertDictationResultPlaceholder") public NSObject insertDictationResultPlaceholder() { throw new UnsupportedOperationException(); }
        @NotImplemented("removeDictationResultPlaceholder:willInsertResult:") public void removeDictationResultPlaceholder(NSObject placeholder, boolean willInsertResult) { throw new UnsupportedOperationException(); }
        @NotImplemented("replaceRange:withText:") public void replaceText(UITextRange range, String text) { throw new UnsupportedOperationException(); }
        @NotImplemented("setBaseWritingDirection:forRange:") public void setBaseWritingDirection(UITextWritingDirection writingDirection, UITextRange range) { throw new UnsupportedOperationException(); }
        @NotImplemented("setMarkedText:selectedRange:") public void setMarkedText(String markedText, NSRange selectedRange) { throw new UnsupportedOperationException(); }
        @NotImplemented("shouldChangeTextInRange:replacementText:") public boolean shouldChangeText(UITextRange range, String text) { throw new UnsupportedOperationException(); }
        @NotImplemented("unmarkText") public void unmarkText() { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("beginningOfDocument") public static UITextPosition getBeginningOfDocument(UITextInput __self__, Selector __cmd__) { return __self__.getBeginningOfDocument(); }
        @Callback @BindSelector("endOfDocument") public static UITextPosition getEndOfDocument(UITextInput __self__, Selector __cmd__) { return __self__.getEndOfDocument(); }
        @Callback @BindSelector("inputDelegate") public static UITextInputDelegate getInputDelegate(UITextInput __self__, Selector __cmd__) { return __self__.getInputDelegate(); }
        @Callback @BindSelector("setInputDelegate:") public static void setInputDelegate(UITextInput __self__, Selector __cmd__, UITextInputDelegate inputDelegate) { __self__.setInputDelegate(inputDelegate); }
        @Callback @BindSelector("markedTextRange") public static UITextRange getMarkedTextRange(UITextInput __self__, Selector __cmd__) { return __self__.getMarkedTextRange(); }
        @Callback @BindSelector("markedTextStyle") public static NSDictionary getMarkedTextStyle(UITextInput __self__, Selector __cmd__) { return __self__.getMarkedTextStyle(); }
        @Callback @BindSelector("setMarkedTextStyle:") public static void setMarkedTextStyle(UITextInput __self__, Selector __cmd__, NSDictionary markedTextStyle) { __self__.setMarkedTextStyle(markedTextStyle); }
        @Callback @BindSelector("selectedTextRange") public static UITextRange getSelectedTextRange(UITextInput __self__, Selector __cmd__) { return __self__.getSelectedTextRange(); }
        @Callback @BindSelector("setSelectedTextRange:") public static void setSelectedTextRange(UITextInput __self__, Selector __cmd__, UITextRange selectedTextRange) { __self__.setSelectedTextRange(selectedTextRange); }
        @Callback @BindSelector("selectionAffinity") public static UITextStorageDirection getSelectionAffinity(UITextInput __self__, Selector __cmd__) { return __self__.getSelectionAffinity(); }
        @Callback @BindSelector("setSelectionAffinity:") public static void setSelectionAffinity(UITextInput __self__, Selector __cmd__, UITextStorageDirection selectionAffinity) { __self__.setSelectionAffinity(selectionAffinity); }
        @Callback @BindSelector("textInputView") public static UIView getTextInputView(UITextInput __self__, Selector __cmd__) { return __self__.getTextInputView(); }
        @Callback @BindSelector("tokenizer") public static UITextInputTokenizer getTokenizer(UITextInput __self__, Selector __cmd__) { return __self__.getTokenizer(); }
        @Callback @BindSelector("comparePosition:toPosition:") public static NSComparisonResult comparePositions(UITextInput __self__, Selector __cmd__, UITextPosition position, UITextPosition other) { return __self__.comparePositions(position, other); }
        @Callback @BindSelector("dictationRecognitionFailed") public static void dictationRecognitionFailed(UITextInput __self__, Selector __cmd__) { __self__.dictationRecognitionFailed(); }
        @Callback @BindSelector("dictationRecordingDidEnd") public static void dictationRecordingDidEnd(UITextInput __self__, Selector __cmd__) { __self__.dictationRecordingDidEnd(); }
        @Callback @BindSelector("baseWritingDirectionForPosition:inDirection:") public static UITextWritingDirection getBaseWritingDirection(UITextInput __self__, Selector __cmd__, UITextPosition position, UITextStorageDirection direction) { return __self__.getBaseWritingDirection(position, direction); }
        @Callback @BindSelector("caretRectForPosition:") public static @ByVal CGRect getCaretRect(UITextInput __self__, Selector __cmd__, UITextPosition position) { return __self__.getCaretRect(position); }
        @Callback @BindSelector("characterOffsetOfPosition:withinRange:") public static int getCharacterOffset(UITextInput __self__, Selector __cmd__, UITextPosition position, UITextRange range) { return __self__.getCharacterOffset(position, range); }
        @Callback @BindSelector("characterRangeAtPoint:") public static UITextRange getCharacterRange(UITextInput __self__, Selector __cmd__, @ByVal CGPoint point) { return __self__.getCharacterRange(point); }
        @Callback @BindSelector("characterRangeByExtendingPosition:inDirection:") public static UITextRange getCharacterRange(UITextInput __self__, Selector __cmd__, UITextPosition position, UITextLayoutDirection direction) { return __self__.getCharacterRange(position, direction); }
        @Callback @BindSelector("closestPositionToPoint:") public static UITextPosition getClosestPosition(UITextInput __self__, Selector __cmd__, @ByVal CGPoint point) { return __self__.getClosestPosition(point); }
        @Callback @BindSelector("closestPositionToPoint:withinRange:") public static UITextPosition getClosestPosition(UITextInput __self__, Selector __cmd__, @ByVal CGPoint point, UITextRange range) { return __self__.getClosestPosition(point, range); }
        @Callback @BindSelector("frameForDictationResultPlaceholder:") public static @ByVal CGRect getDictationResultPlaceholderFrame(UITextInput __self__, Selector __cmd__, NSObject placeholder) { return __self__.getDictationResultPlaceholderFrame(placeholder); }
        @Callback @BindSelector("firstRectForRange:") public static @ByVal CGRect getFirstRect(UITextInput __self__, Selector __cmd__, UITextRange range) { return __self__.getFirstRect(range); }
        @Callback @BindSelector("offsetFromPosition:toPosition:") public static int getOffset(UITextInput __self__, Selector __cmd__, UITextPosition fromPosition, UITextPosition toPosition) { return __self__.getOffset(fromPosition, toPosition); }
        @Callback @BindSelector("positionWithinRange:atCharacterOffset:") public static UITextPosition getPosition(UITextInput __self__, Selector __cmd__, UITextRange range, int offset) { return __self__.getPosition(range, offset); }
        @Callback @BindSelector("positionWithinRange:farthestInDirection:") public static UITextPosition getPosition(UITextInput __self__, Selector __cmd__, UITextRange range, UITextLayoutDirection direction) { return __self__.getPosition(range, direction); }
        @Callback @BindSelector("positionFromPosition:inDirection:offset:") public static UITextPosition getPosition(UITextInput __self__, Selector __cmd__, UITextPosition position, UITextLayoutDirection direction, int offset) { return __self__.getPosition(position, direction, offset); }
        @Callback @BindSelector("positionFromPosition:offset:") public static UITextPosition getPosition(UITextInput __self__, Selector __cmd__, UITextPosition position, int offset) { return __self__.getPosition(position, offset); }
        @Callback @BindSelector("selectionRectsForRange:") public static NSArray getSelectionRects(UITextInput __self__, Selector __cmd__, UITextRange range) { return __self__.getSelectionRects(range); }
        @Callback @BindSelector("textInRange:") public static String getText(UITextInput __self__, Selector __cmd__, UITextRange range) { return __self__.getText(range); }
        @Callback @BindSelector("textRangeFromPosition:toPosition:") public static UITextRange getTextRange(UITextInput __self__, Selector __cmd__, UITextPosition fromPosition, UITextPosition toPosition) { return __self__.getTextRange(fromPosition, toPosition); }
        @Callback @BindSelector("textStylingAtPosition:inDirection:") public static NSDictionary getTextStyling(UITextInput __self__, Selector __cmd__, UITextPosition position, UITextStorageDirection direction) { return __self__.getTextStyling(position, direction); }
        @Callback @BindSelector("insertDictationResult:") public static void insertDictationResult(UITextInput __self__, Selector __cmd__, NSArray dictationResult) { __self__.insertDictationResult(dictationResult); }
        @Callback @BindSelector("insertDictationResultPlaceholder") public static NSObject insertDictationResultPlaceholder(UITextInput __self__, Selector __cmd__) { return __self__.insertDictationResultPlaceholder(); }
        @Callback @BindSelector("removeDictationResultPlaceholder:willInsertResult:") public static void removeDictationResultPlaceholder(UITextInput __self__, Selector __cmd__, NSObject placeholder, boolean willInsertResult) { __self__.removeDictationResultPlaceholder(placeholder, willInsertResult); }
        @Callback @BindSelector("replaceRange:withText:") public static void replaceText(UITextInput __self__, Selector __cmd__, UITextRange range, String text) { __self__.replaceText(range, text); }
        @Callback @BindSelector("setBaseWritingDirection:forRange:") public static void setBaseWritingDirection(UITextInput __self__, Selector __cmd__, UITextWritingDirection writingDirection, UITextRange range) { __self__.setBaseWritingDirection(writingDirection, range); }
        @Callback @BindSelector("setMarkedText:selectedRange:") public static void setMarkedText(UITextInput __self__, Selector __cmd__, String markedText, @ByVal NSRange selectedRange) { __self__.setMarkedText(markedText, selectedRange); }
        @Callback @BindSelector("shouldChangeTextInRange:replacementText:") public static boolean shouldChangeText(UITextInput __self__, Selector __cmd__, UITextRange range, String text) { return __self__.shouldChangeText(range, text); }
        @Callback @BindSelector("unmarkText") public static void unmarkText(UITextInput __self__, Selector __cmd__) { __self__.unmarkText(); }
    }
    /*</callbacks>*/

}
