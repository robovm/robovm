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
import org.robovm.cocoatouch.coredata.*;
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

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html">UITextInput Protocol Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
@Protocol
public interface /*<name>*/ UITextInput /*</name>*/ /*<implements>*/ extends UIKeyInput /*</implements>*/ {

    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/beginningOfDocument">@property(nonatomic, readonly) UITextPosition *beginningOfDocument</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("beginningOfDocument") @Type("UITextPosition *") UITextPosition getBeginningOfDocument();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/endOfDocument">@property(nonatomic, readonly) UITextPosition *endOfDocument</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("endOfDocument") @Type("UITextPosition *") UITextPosition getEndOfDocument();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/inputDelegate">@property(nonatomic, assign) id&amp;lt;UITextInputDelegate&amp;gt; inputDelegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("inputDelegate") @Type("id<UITextInputDelegate>") UITextInputDelegate getInputDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/inputDelegate">@property(nonatomic, assign) id&amp;lt;UITextInputDelegate&amp;gt; inputDelegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setInputDelegate:") void setInputDelegate(@Type("id<UITextInputDelegate>") UITextInputDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/markedTextRange">@property(nonatomic, readonly) UITextRange *markedTextRange</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("markedTextRange") @Type("UITextRange *") UITextRange getMarkedTextRange();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/markedTextStyle">@property(nonatomic, copy) NSDictionary *markedTextStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("markedTextStyle") @Type("NSDictionary *") NSDictionary getMarkedTextStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/markedTextStyle">@property(nonatomic, copy) NSDictionary *markedTextStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setMarkedTextStyle:") void setMarkedTextStyle(@Type("NSDictionary *") NSDictionary v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectedTextRange">@property(readwrite, copy) UITextRange *selectedTextRange</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("selectedTextRange") @Type("UITextRange *") UITextRange getSelectedTextRange();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectedTextRange">@property(readwrite, copy) UITextRange *selectedTextRange</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setSelectedTextRange:") void setSelectedTextRange(@Type("UITextRange *") UITextRange v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectionAffinity">@property(nonatomic) UITextStorageDirection selectionAffinity</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("selectionAffinity") @Type("UITextStorageDirection") UITextStorageDirection getSelectionAffinity();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectionAffinity">@property(nonatomic) UITextStorageDirection selectionAffinity</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setSelectionAffinity:") void setSelectionAffinity(@Type("UITextStorageDirection") UITextStorageDirection v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/textInputView">@property(nonatomic, readonly) UIView *textInputView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("textInputView") @Type("UIView *") UIView getTextInputView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/tokenizer">@property(nonatomic, readonly) id&amp;lt;UITextInputTokenizer&amp;gt; tokenizer</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("tokenizer") @Type("id<UITextInputTokenizer>") UITextInputTokenizer getTokenizer();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/comparePosition:toPosition:">- (NSComparisonResult)comparePosition:(UITextPosition *)position toPosition:(UITextPosition *)other</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("comparePosition:toPosition:") @Type("NSComparisonResult") NSComparisonResult comparePositions(@Type("UITextPosition *") UITextPosition position, @Type("UITextPosition *") UITextPosition other);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/dictationRecognitionFailed">- (void)dictationRecognitionFailed</a>
     * @since Available in iOS 5.1 and later.
     */
    @Bind("dictationRecognitionFailed") @Type("void") void dictationRecognitionFailed();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/dictationRecordingDidEnd">- (void)dictationRecordingDidEnd</a>
     * @since Available in iOS 5.1 and later.
     */
    @Bind("dictationRecordingDidEnd") @Type("void") void dictationRecordingDidEnd();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/baseWritingDirectionForPosition:inDirection:">- (UITextWritingDirection)baseWritingDirectionForPosition:(UITextPosition *)position inDirection:(UITextStorageDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("baseWritingDirectionForPosition:inDirection:") @Type("UITextWritingDirection") UITextWritingDirection getBaseWritingDirection(@Type("UITextPosition *") UITextPosition position, @Type("UITextStorageDirection") UITextStorageDirection direction);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/caretRectForPosition:">- (CGRect)caretRectForPosition:(UITextPosition *)position</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("caretRectForPosition:") @Type("CGRect") CGRect getCaretRect(@Type("UITextPosition *") UITextPosition position);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/characterOffsetOfPosition:withinRange:">- (NSInteger)characterOffsetOfPosition:(UITextPosition *)position withinRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("characterOffsetOfPosition:withinRange:") @Type("NSInteger") int getCharacterOffset(@Type("UITextPosition *") UITextPosition position, @Type("UITextRange *") UITextRange range);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/characterRangeAtPoint:">- (UITextRange *)characterRangeAtPoint:(CGPoint)point</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("characterRangeAtPoint:") @Type("UITextRange *") UITextRange getCharacterRange(@Type("CGPoint") CGPoint point);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/characterRangeByExtendingPosition:inDirection:">- (UITextRange *)characterRangeByExtendingPosition:(UITextPosition *)position inDirection:(UITextLayoutDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("characterRangeByExtendingPosition:inDirection:") @Type("UITextRange *") UITextRange getCharacterRange(@Type("UITextPosition *") UITextPosition position, @Type("UITextLayoutDirection") UITextLayoutDirection direction);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/closestPositionToPoint:">- (UITextPosition *)closestPositionToPoint:(CGPoint)point</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("closestPositionToPoint:") @Type("UITextPosition *") UITextPosition getClosestPosition(@Type("CGPoint") CGPoint point);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/closestPositionToPoint:withinRange:">- (UITextPosition *)closestPositionToPoint:(CGPoint)point withinRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("closestPositionToPoint:withinRange:") @Type("UITextPosition *") UITextPosition getClosestPosition(@Type("CGPoint") CGPoint point, @Type("UITextRange *") UITextRange range);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/frameForDictationResultPlaceholder:">- (CGRect)frameForDictationResultPlaceholder:(id)placeholder</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("frameForDictationResultPlaceholder:") @Type("CGRect") CGRect getDictationResultPlaceholderFrame(@Type("id") NSObject placeholder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/firstRectForRange:">- (CGRect)firstRectForRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("firstRectForRange:") @Type("CGRect") CGRect getFirstRect(@Type("UITextRange *") UITextRange range);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/offsetFromPosition:toPosition:">- (NSInteger)offsetFromPosition:(UITextPosition *)fromPosition toPosition:(UITextPosition *)toPosition</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("offsetFromPosition:toPosition:") @Type("NSInteger") int getOffset(@Type("UITextPosition *") UITextPosition fromPosition, @Type("UITextPosition *") UITextPosition toPosition);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionWithinRange:atCharacterOffset:">- (UITextPosition *)positionWithinRange:(UITextRange *)range atCharacterOffset:(NSInteger)offset</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("positionWithinRange:atCharacterOffset:") @Type("UITextPosition *") UITextPosition getPosition(@Type("UITextRange *") UITextRange range, @Type("NSInteger") int offset);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionWithinRange:farthestInDirection:">- (UITextPosition *)positionWithinRange:(UITextRange *)range farthestInDirection:(UITextLayoutDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("positionWithinRange:farthestInDirection:") @Type("UITextPosition *") UITextPosition getPosition(@Type("UITextRange *") UITextRange range, @Type("UITextLayoutDirection") UITextLayoutDirection direction);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionFromPosition:inDirection:offset:">- (UITextPosition *)positionFromPosition:(UITextPosition *)position inDirection:(UITextLayoutDirection)direction offset:(NSInteger)offset</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("positionFromPosition:inDirection:offset:") @Type("UITextPosition *") UITextPosition getPosition(@Type("UITextPosition *") UITextPosition position, @Type("UITextLayoutDirection") UITextLayoutDirection direction, @Type("NSInteger") int offset);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionFromPosition:offset:">- (UITextPosition *)positionFromPosition:(UITextPosition *)position offset:(NSInteger)offset</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("positionFromPosition:offset:") @Type("UITextPosition *") UITextPosition getPosition(@Type("UITextPosition *") UITextPosition position, @Type("NSInteger") int offset);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/selectionRectsForRange:">- (NSArray *)selectionRectsForRange:(UITextRange *)range</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("selectionRectsForRange:") @Type("NSArray *") NSArray getSelectionRects(@Type("UITextRange *") UITextRange range);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/textInRange:">- (NSString *)textInRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("textInRange:") @Type("NSString *") String getText(@Type("UITextRange *") UITextRange range);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/textRangeFromPosition:toPosition:">- (UITextRange *)textRangeFromPosition:(UITextPosition *)fromPosition toPosition:(UITextPosition *)toPosition</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("textRangeFromPosition:toPosition:") @Type("UITextRange *") UITextRange getTextRange(@Type("UITextPosition *") UITextPosition fromPosition, @Type("UITextPosition *") UITextPosition toPosition);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/textStylingAtPosition:inDirection:">- (NSDictionary *)textStylingAtPosition:(UITextPosition *)position inDirection:(UITextStorageDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("textStylingAtPosition:inDirection:") @Type("NSDictionary *") NSDictionary getTextStyling(@Type("UITextPosition *") UITextPosition position, @Type("UITextStorageDirection") UITextStorageDirection direction);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/insertDictationResult:">- (void)insertDictationResult:(NSArray *)dictationResult</a>
     * @since Available in iOS 5.1 and later.
     */
    @Bind("insertDictationResult:") @Type("void") void insertDictationResult(@Type("NSArray *") NSArray dictationResult);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/insertDictationResultPlaceholder">- (id)insertDictationResultPlaceholder</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("insertDictationResultPlaceholder") @Type("id") NSObject insertDictationResultPlaceholder();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/removeDictationResultPlaceholder:willInsertResult:">- (void)removeDictationResultPlaceholder:(id)placeholder willInsertResult:(BOOL)willInsertResult</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("removeDictationResultPlaceholder:willInsertResult:") @Type("void") void removeDictationResultPlaceholder(@Type("id") NSObject placeholder, @Type("BOOL") boolean willInsertResult);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/replaceRange:withText:">- (void)replaceRange:(UITextRange *)range withText:(NSString *)text</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("replaceRange:withText:") @Type("void") void replaceText(@Type("UITextRange *") UITextRange range, @Type("NSString *") String text);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/setBaseWritingDirection:forRange:">- (void)setBaseWritingDirection:(UITextWritingDirection)writingDirection forRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setBaseWritingDirection:forRange:") @Type("void") void setBaseWritingDirection(@Type("UITextWritingDirection") UITextWritingDirection writingDirection, @Type("UITextRange *") UITextRange range);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/setMarkedText:selectedRange:">- (void)setMarkedText:(NSString *)markedText selectedRange:(NSRange)selectedRange</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setMarkedText:selectedRange:") @Type("void") void setMarkedText(@Type("NSString *") String markedText, @Type("NSRange") NSRange selectedRange);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/shouldChangeTextInRange:replacementText:">- (BOOL)shouldChangeTextInRange:(UITextRange *)range replacementText:(NSString *)text</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("shouldChangeTextInRange:replacementText:") @Type("BOOL") boolean shouldChangeText(@Type("UITextRange *") UITextRange range, @Type("NSString *") String text);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/unmarkText">- (void)unmarkText</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("unmarkText") @Type("void") void unmarkText();
    /*</methods>*/

}
