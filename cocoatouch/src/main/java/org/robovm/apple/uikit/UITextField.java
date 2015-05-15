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
import org.robovm.apple.corefoundation.CFDictionary;
import org.robovm.apple.coremedia.CMTextMarkupAttributes;

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UITextField/*</name>*/ 
    extends /*<extends>*/UIControl/*</extends>*/ 
    /*<implements>*/implements UITextInput, NSCoding/*</implements>*/ {

    public static class Notifications {
        public static NSObject observeDidBeginEditing(UITextField object, final VoidBlock1<UITextField> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidBeginEditingNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((UITextField) a.getObject());
                }
            });
        }
        public static NSObject observeDidEndEditing(UITextField object, final VoidBlock1<UITextField> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidEndEditingNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((UITextField) a.getObject());
                }
            });
        }
        public static NSObject observeTextDidChange(UITextField object, final VoidBlock1<UITextField> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidChangeNotification(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((UITextField) a.getObject());
                }
            });
        }
    }
    /*<ptr>*/public static class UITextFieldPtr extends Ptr<UITextField, UITextFieldPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UITextField.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UITextField() {}
    protected UITextField(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public UITextField(CGRect frame) {
        super(frame);
    }
    
    /**
     * @since Available in iOS 7.0 and later.
     */
    public NSAttributedStringAttributes getDefaultTextAttributes() {
        NSDictionary<NSString, NSObject> dict = getDefaultTextAttributesDictionary();
        if (dict == null) return null;
        return new NSAttributedStringAttributes(dict);
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CMTextMarkupAttributes getDefaultTextMarkupAttributes() {
        NSDictionary<NSString, NSObject> dict = getDefaultTextAttributesDictionary();
        if (dict == null) return null;
        return new CMTextMarkupAttributes(dict.as(CFDictionary.class));
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public CTAttributedStringAttributes getDefaultCoreTextAttributes() {
        NSDictionary<NSString, NSObject> dict = getDefaultTextAttributesDictionary();
        if (dict == null) return null;
        return new CTAttributedStringAttributes(dict.as(CFDictionary.class));
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void setDefaultTextAttributes(NSAttributedStringAttributes v) {
        if (v == null) {
            setDefaultTextAttributesDictionary(null);
        } else {
            setDefaultTextAttributesDictionary(v.getDictionary());
        }
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void setDefaultTextMarkupAttributes(CMTextMarkupAttributes v) {
        if (v == null) {
            setDefaultTextAttributesDictionary(null);
        } else {
            setDefaultTextAttributesDictionary(v.getDictionary().as(NSDictionary.class));
        }
    }
    /**
     * @since Available in iOS 7.0 and later.
     */
    public void setDefaultCoreTextAttributes(CTAttributedStringAttributes v) {
        if (v == null) {
            setDefaultTextAttributesDictionary(null);
        } else {
            setDefaultTextAttributesDictionary(v.getDictionary().as(NSDictionary.class));
        }
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedStringAttributes getTypingAttributes() {
        NSDictionary<NSString, NSObject> dict = getTypingAttributesDictionary();
        if (dict == null) return null;
        return new NSAttributedStringAttributes(dict);
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CMTextMarkupAttributes getTypingTextMarkupAttributes() {
        NSDictionary<NSString, NSObject> dict = getTypingAttributesDictionary();
        if (dict == null) return null;
        return new CMTextMarkupAttributes(dict.as(CFDictionary.class));
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public CTAttributedStringAttributes getTypingCoreTextAttributes() {
        NSDictionary<NSString, NSObject> dict = getTypingAttributesDictionary();
        if (dict == null) return null;
        return new CTAttributedStringAttributes(dict.as(CFDictionary.class));
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public void setTypingAttributes(NSAttributedStringAttributes v) {
        if (v == null) {
            setTypingAttributesDictionary(null);
        } else {
            setTypingAttributesDictionary(v.getDictionary());
        }
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public void setTypingTextMarkupAttributes(CMTextMarkupAttributes v) {
        if (v == null) {
            setTypingAttributesDictionary(null);
        } else {
            setTypingAttributesDictionary(v.getDictionary().as(NSDictionary.class));
        }
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public void setTypingCoreTextAttributes(CTAttributedStringAttributes v) {
        if (v == null) {
            setTypingAttributesDictionary(null);
        } else {
            setTypingAttributesDictionary(v.getDictionary().as(NSDictionary.class));
        }
    }
    /*<properties>*/
    @Property(selector = "text")
    public native String getText();
    @Property(selector = "setText:")
    public native void setText(String v);
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
    @Property(selector = "textColor")
    public native UIColor getTextColor();
    @Property(selector = "setTextColor:")
    public native void setTextColor(UIColor v);
    @Property(selector = "font")
    public native UIFont getFont();
    @Property(selector = "setFont:")
    public native void setFont(UIFont v);
    @Property(selector = "textAlignment")
    public native NSTextAlignment getTextAlignment();
    @Property(selector = "setTextAlignment:")
    public native void setTextAlignment(NSTextAlignment v);
    @Property(selector = "borderStyle")
    public native UITextBorderStyle getBorderStyle();
    @Property(selector = "setBorderStyle:")
    public native void setBorderStyle(UITextBorderStyle v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "defaultTextAttributes")
    public native NSDictionary<NSString, NSObject> getDefaultTextAttributesDictionary();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setDefaultTextAttributes:")
    public native void setDefaultTextAttributesDictionary(NSDictionary<NSString, NSObject> v);
    @Property(selector = "placeholder")
    public native String getPlaceholder();
    @Property(selector = "setPlaceholder:")
    public native void setPlaceholder(String v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "attributedPlaceholder")
    public native NSAttributedString getAttributedPlaceholder();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setAttributedPlaceholder:")
    public native void setAttributedPlaceholder(NSAttributedString v);
    @Property(selector = "clearsOnBeginEditing")
    public native boolean clearsOnBeginEditing();
    @Property(selector = "setClearsOnBeginEditing:")
    public native void setClearsOnBeginEditing(boolean v);
    @Property(selector = "adjustsFontSizeToFitWidth")
    public native boolean adjustsFontSizeToFitWidth();
    @Property(selector = "setAdjustsFontSizeToFitWidth:")
    public native void setAdjustsFontSizeToFitWidth(boolean v);
    @Property(selector = "minimumFontSize")
    public native @MachineSizedFloat double getMinimumFontSize();
    @Property(selector = "setMinimumFontSize:")
    public native void setMinimumFontSize(@MachineSizedFloat double v);
    @Property(selector = "delegate")
    public native UITextFieldDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UITextFieldDelegate v);
    @Property(selector = "background")
    public native UIImage getBackground();
    @Property(selector = "setBackground:")
    public native void setBackground(UIImage v);
    @Property(selector = "disabledBackground")
    public native UIImage getDisabledBackground();
    @Property(selector = "setDisabledBackground:")
    public native void setDisabledBackground(UIImage v);
    @Property(selector = "isEditing")
    public native boolean isEditing();
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
    @Property(selector = "typingAttributes")
    public native NSDictionary<NSString, NSObject> getTypingAttributesDictionary();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setTypingAttributes:")
    public native void setTypingAttributesDictionary(NSDictionary<NSString, NSObject> v);
    @Property(selector = "clearButtonMode")
    public native UITextFieldViewMode getClearButtonMode();
    @Property(selector = "setClearButtonMode:")
    public native void setClearButtonMode(UITextFieldViewMode v);
    @Property(selector = "leftView")
    public native UIView getLeftView();
    @Property(selector = "setLeftView:")
    public native void setLeftView(UIView v);
    @Property(selector = "leftViewMode")
    public native UITextFieldViewMode getLeftViewMode();
    @Property(selector = "setLeftViewMode:")
    public native void setLeftViewMode(UITextFieldViewMode v);
    @Property(selector = "rightView")
    public native UIView getRightView();
    @Property(selector = "setRightView:")
    public native void setRightView(UIView v);
    @Property(selector = "rightViewMode")
    public native UITextFieldViewMode getRightViewMode();
    @Property(selector = "setRightViewMode:")
    public native void setRightViewMode(UITextFieldViewMode v);
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
    @GlobalValue(symbol="UITextFieldTextDidBeginEditingNotification", optional=true)
    public static native NSString DidBeginEditingNotification();
    @GlobalValue(symbol="UITextFieldTextDidEndEditingNotification", optional=true)
    public static native NSString DidEndEditingNotification();
    @GlobalValue(symbol="UITextFieldTextDidChangeNotification", optional=true)
    public static native NSString DidChangeNotification();
    
    @Method(selector = "borderRectForBounds:")
    public native @ByVal CGRect getBorderRect(@ByVal CGRect bounds);
    @Method(selector = "textRectForBounds:")
    public native @ByVal CGRect getTextRect(@ByVal CGRect bounds);
    @Method(selector = "placeholderRectForBounds:")
    public native @ByVal CGRect getPlaceholderRect(@ByVal CGRect bounds);
    @Method(selector = "editingRectForBounds:")
    public native @ByVal CGRect getEditingRect(@ByVal CGRect bounds);
    @Method(selector = "clearButtonRectForBounds:")
    public native @ByVal CGRect getClearButtonRect(@ByVal CGRect bounds);
    @Method(selector = "leftViewRectForBounds:")
    public native @ByVal CGRect getLeftViewRect(@ByVal CGRect bounds);
    @Method(selector = "rightViewRectForBounds:")
    public native @ByVal CGRect getRightViewRect(@ByVal CGRect bounds);
    @Method(selector = "drawTextInRect:")
    public native void drawText(@ByVal CGRect rect);
    @Method(selector = "drawPlaceholderInRect:")
    public native void drawPlaceholder(@ByVal CGRect rect);
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
