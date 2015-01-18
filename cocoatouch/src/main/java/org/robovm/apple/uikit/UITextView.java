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
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UITextView/*</name>*/ 
    extends /*<extends>*/UIScrollView/*</extends>*/ 
    /*<implements>*/implements UITextInput/*</implements>*/ {

    public static class Notifications {
        public static NSObject observeDidBeginEditing(UITextView object, final VoidBlock1<UITextView> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidBeginEditingNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((UITextView) a.getObject());
                }
            });
        }
        public static NSObject observeTextDidChangeEditing(UITextView object, final VoidBlock1<UITextView> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidChangeNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((UITextView) a.getObject());
                }
            });
        }
        public static NSObject observeDidEndEditing(UITextView object, final VoidBlock1<UITextView> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidEndEditingNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((UITextView) a.getObject());
                }
            });
        }
    }
    /*<ptr>*/public static class UITextViewPtr extends Ptr<UITextView, UITextViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UITextView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UITextView() {}
    protected UITextView(SkipInit skipInit) { super(skipInit); }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public UITextView(@ByVal CGRect frame, NSTextContainer textContainer) { super((SkipInit) null); initObject(init(frame, textContainer)); }
    /*</constructors>*/
    
    public UITextView(CGRect frame) {
        super(frame);
    }
    
    /*<properties>*/
    @Property(selector = "delegate")
    public native UITextViewDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UITextViewDelegate v);
    @Property(selector = "text")
    public native String getText();
    @Property(selector = "setText:")
    public native void setText(String v);
    @Property(selector = "font")
    public native UIFont getFont();
    @Property(selector = "setFont:")
    public native void setFont(UIFont v);
    @Property(selector = "textColor")
    public native UIColor getTextColor();
    @Property(selector = "setTextColor:")
    public native void setTextColor(UIColor v);
    @Property(selector = "textAlignment")
    public native NSTextAlignment getTextAlignment();
    @Property(selector = "setTextAlignment:")
    public native void setTextAlignment(NSTextAlignment v);
    @Property(selector = "selectedRange")
    public native @ByVal NSRange getSelectedRange();
    @Property(selector = "setSelectedRange:")
    public native void setSelectedRange(@ByVal NSRange v);
    @Property(selector = "isEditable")
    public native boolean isEditable();
    @Property(selector = "setEditable:")
    public native void setEditable(boolean v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "isSelectable")
    public native boolean isSelectable();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setSelectable:")
    public native void setSelectable(boolean v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "dataDetectorTypes")
    public native UIDataDetectorTypes getDataDetectorTypes();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setDataDetectorTypes:")
    public native void setDataDetectorTypes(UIDataDetectorTypes v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "allowsEditingTextAttributes")
    public native boolean allowsEditingTextAttributes();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setAllowsEditingTextAttributes:")
    public native void setAllowsEditingTextAttributes(boolean v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "attributedText")
    public native NSAttributedString getAttributedText();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setAttributedText:")
    public native void setAttributedText(NSAttributedString v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "typingAttributes")
    public native NSAttributedStringAttributes getTypingAttributes();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setTypingAttributes:")
    public native void setTypingAttributes(NSAttributedStringAttributes v);
    @Property(selector = "inputView")
    public native UIView getInputView();
    @Property(selector = "setInputView:")
    public native void setInputView(UIView v);
    @Property(selector = "inputAccessoryView")
    public native UIView getInputAccessoryView();
    @Property(selector = "setInputAccessoryView:")
    public native void setInputAccessoryView(UIView v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "clearsOnInsertion")
    public native boolean clearsOnInsertion();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setClearsOnInsertion:")
    public native void setClearsOnInsertion(boolean v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "textContainer")
    public native NSTextContainer getTextContainer();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "textContainerInset")
    public native @ByVal UIEdgeInsets getTextContainerInset();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setTextContainerInset:")
    public native void setTextContainerInset(@ByVal UIEdgeInsets v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "layoutManager")
    public native NSLayoutManager getLayoutManager();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "textStorage")
    public native NSTextStorage getTextStorage();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "linkTextAttributes")
    public native NSAttributedStringAttributes getLinkTextAttributes();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setLinkTextAttributes:")
    public native void setLinkTextAttributes(NSAttributedStringAttributes v);
    @Property(selector = "selectedTextRange")
    public native UITextRange getSelectedTextRange();
    @Property(selector = "setSelectedTextRange:")
    public native void setSelectedTextRange(UITextRange v);
    @Property(selector = "markedTextRange")
    public native UITextRange getMarkedTextRange();
    @Property(selector = "markedTextStyle")
    public native UITextInputTextStyle getMarkedTextStyle();
    @Property(selector = "setMarkedTextStyle:")
    public native void setMarkedTextStyle(UITextInputTextStyle v);
    @Property(selector = "beginningOfDocument")
    public native UITextPosition getBeginningOfDocument();
    @Property(selector = "endOfDocument")
    public native UITextPosition getEndOfDocument();
    @Property(selector = "inputDelegate")
    public native UITextInputDelegate getInputDelegate();
    @Property(selector = "setInputDelegate:", strongRef = true)
    public native void setInputDelegate(UITextInputDelegate v);
    @Property(selector = "tokenizer")
    public native UITextInputTokenizer getTokenizer();
    @Property(selector = "textInputView")
    public native UIView getTextInputView();
    @Property(selector = "selectionAffinity")
    public native UITextStorageDirection getSelectionAffinity();
    @Property(selector = "setSelectionAffinity:")
    public native void setSelectionAffinity(UITextStorageDirection v);
    @Property(selector = "autocapitalizationType")
    public native UITextAutocapitalizationType getAutocapitalizationType();
    @Property(selector = "setAutocapitalizationType:")
    public native void setAutocapitalizationType(UITextAutocapitalizationType v);
    @Property(selector = "autocorrectionType")
    public native UITextAutocorrectionType getAutocorrectionType();
    @Property(selector = "setAutocorrectionType:")
    public native void setAutocorrectionType(UITextAutocorrectionType v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "spellCheckingType")
    public native UITextSpellCheckingType getSpellCheckingType();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setSpellCheckingType:")
    public native void setSpellCheckingType(UITextSpellCheckingType v);
    @Property(selector = "keyboardType")
    public native UIKeyboardType getKeyboardType();
    @Property(selector = "setKeyboardType:")
    public native void setKeyboardType(UIKeyboardType v);
    @Property(selector = "keyboardAppearance")
    public native UIKeyboardAppearance getKeyboardAppearance();
    @Property(selector = "setKeyboardAppearance:")
    public native void setKeyboardAppearance(UIKeyboardAppearance v);
    @Property(selector = "returnKeyType")
    public native UIReturnKeyType getReturnKeyType();
    @Property(selector = "setReturnKeyType:")
    public native void setReturnKeyType(UIReturnKeyType v);
    @Property(selector = "enablesReturnKeyAutomatically")
    public native boolean enablesReturnKeyAutomatically();
    @Property(selector = "setEnablesReturnKeyAutomatically:")
    public native void setEnablesReturnKeyAutomatically(boolean v);
    @Property(selector = "isSecureTextEntry")
    public native boolean isSecureTextEntry();
    @Property(selector = "setSecureTextEntry:")
    public native void setSecureTextEntry(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="UITextViewTextDidBeginEditingNotification", optional=true)
    public static native NSString DidBeginEditingNotification();
    @GlobalValue(symbol="UITextViewTextDidChangeNotification", optional=true)
    public static native NSString DidChangeNotification();
    @GlobalValue(symbol="UITextViewTextDidEndEditingNotification", optional=true)
    public static native NSString DidEndEditingNotification();
    
    @Method(selector = "scrollRangeToVisible:")
    public native void scrollRangeToVisible(@ByVal NSRange range);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "initWithFrame:textContainer:")
    protected native @Pointer long init(@ByVal CGRect frame, NSTextContainer textContainer);
    @Method(selector = "textInRange:")
    public native String getText(UITextRange range);
    @Method(selector = "replaceRange:withText:")
    public native void replaceText(UITextRange range, String text);
    @Method(selector = "setMarkedText:selectedRange:")
    public native void setMarkedText(String markedText, @ByVal NSRange selectedRange);
    @Method(selector = "unmarkText")
    public native void unmarkText();
    @Method(selector = "textRangeFromPosition:toPosition:")
    public native UITextRange getTextRange(UITextPosition fromPosition, UITextPosition toPosition);
    @Method(selector = "positionFromPosition:offset:")
    public native UITextPosition getPosition(UITextPosition position, @MachineSizedSInt long offset);
    @Method(selector = "positionFromPosition:inDirection:offset:")
    public native UITextPosition getPosition(UITextPosition position, UITextLayoutDirection direction, @MachineSizedSInt long offset);
    @Method(selector = "comparePosition:toPosition:")
    public native NSComparisonResult comparePositions(UITextPosition position, UITextPosition other);
    @Method(selector = "offsetFromPosition:toPosition:")
    public native @MachineSizedSInt long getOffset(UITextPosition from, UITextPosition toPosition);
    @Method(selector = "positionWithinRange:farthestInDirection:")
    public native UITextPosition getPosition(UITextRange range, UITextLayoutDirection direction);
    @Method(selector = "characterRangeByExtendingPosition:inDirection:")
    public native UITextRange getCharacterRange(UITextPosition position, UITextLayoutDirection direction);
    @Method(selector = "baseWritingDirectionForPosition:inDirection:")
    public native UITextWritingDirection getBaseWritingDirection(UITextPosition position, UITextStorageDirection direction);
    @Method(selector = "setBaseWritingDirection:forRange:")
    public native void setBaseWritingDirection(UITextWritingDirection writingDirection, UITextRange range);
    @Method(selector = "firstRectForRange:")
    public native @ByVal CGRect getFirstRect(UITextRange range);
    @Method(selector = "caretRectForPosition:")
    public native @ByVal CGRect getCaretRect(UITextPosition position);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "selectionRectsForRange:")
    public native NSArray<UITextSelectionRect> getSelectionRects(UITextRange range);
    @Method(selector = "closestPositionToPoint:")
    public native UITextPosition getClosestPosition(@ByVal CGPoint point);
    @Method(selector = "closestPositionToPoint:withinRange:")
    public native UITextPosition getClosestPosition(@ByVal CGPoint point, UITextRange range);
    @Method(selector = "characterRangeAtPoint:")
    public native UITextRange getCharacterRange(@ByVal CGPoint point);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "shouldChangeTextInRange:replacementText:")
    public native boolean shouldChangeText(UITextRange range, String text);
    @Method(selector = "textStylingAtPosition:inDirection:")
    public native UITextInputTextStyle getTextStyling(UITextPosition position, UITextStorageDirection direction);
    @Method(selector = "positionWithinRange:atCharacterOffset:")
    public native UITextPosition getPosition(UITextRange range, @MachineSizedSInt long offset);
    @Method(selector = "characterOffsetOfPosition:withinRange:")
    public native @MachineSizedSInt long getCharacterOffset(UITextPosition position, UITextRange range);
    @Method(selector = "insertDictationResult:")
    public native void insertDictationResult(NSArray<UIDictationPhrase> dictationResult);
    @Method(selector = "dictationRecordingDidEnd")
    public native void dictationRecordingDidEnd();
    @Method(selector = "dictationRecognitionFailed")
    public native void dictationRecognitionFailed();
    @Method(selector = "insertDictationResultPlaceholder")
    public native NSObject getInsertDictationResultPlaceholder();
    @Method(selector = "frameForDictationResultPlaceholder:")
    public native @ByVal CGRect getDictationResultPlaceholderFrame(NSObject placeholder);
    @Method(selector = "removeDictationResultPlaceholder:willInsertResult:")
    public native void removeDictationResultPlaceholder(NSObject placeholder, boolean willInsertResult);
    @Method(selector = "hasText")
    public native boolean hasText();
    @Method(selector = "insertText:")
    public native void insertText(String text);
    @Method(selector = "deleteBackward")
    public native void deleteBackward();
    /*</methods>*/
}
