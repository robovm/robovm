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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html">UITextView Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UITextView /*</name>*/ 
    extends /*<extends>*/ UIScrollView /*</extends>*/ 
    /*<implements>*/ implements UITextInput, UIKeyInput, UITextInputTraits /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITextView /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UITextView /*</name>*/.class);

    /*<constructors>*/
    protected UITextView(SkipInit skipInit) { super(skipInit); }
    public UITextView() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/allowsEditingTextAttributes">@property(nonatomic) BOOL allowsEditingTextAttributes</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("allowsEditingTextAttributes") public native boolean isAllowsEditingTextAttributes();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/allowsEditingTextAttributes">@property(nonatomic) BOOL allowsEditingTextAttributes</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setAllowsEditingTextAttributes:") public native void setAllowsEditingTextAttributes(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/attributedText">@property(nonatomic,copy) NSAttributedString *attributedText</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("attributedText") public native NSAttributedString getAttributedText();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/attributedText">@property(nonatomic,copy) NSAttributedString *attributedText</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setAttributedText:") public native void setAttributedText(NSAttributedString v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocapitalizationType">@property(nonatomic) UITextAutocapitalizationType autocapitalizationType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("autocapitalizationType") public native UITextAutocapitalizationType getAutocapitalizationType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocapitalizationType">@property(nonatomic) UITextAutocapitalizationType autocapitalizationType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAutocapitalizationType:") public native void setAutocapitalizationType(UITextAutocapitalizationType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocorrectionType">@property(nonatomic) UITextAutocorrectionType autocorrectionType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("autocorrectionType") public native UITextAutocorrectionType getAutocorrectionType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocorrectionType">@property(nonatomic) UITextAutocorrectionType autocorrectionType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAutocorrectionType:") public native void setAutocorrectionType(UITextAutocorrectionType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/beginningOfDocument">@property(nonatomic, readonly) UITextPosition *beginningOfDocument</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("beginningOfDocument") public native UITextPosition getBeginningOfDocument();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/clearsOnInsertion">@property(nonatomic) BOOL clearsOnInsertion</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("clearsOnInsertion") public native boolean isClearsOnInsertion();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/clearsOnInsertion">@property(nonatomic) BOOL clearsOnInsertion</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setClearsOnInsertion:") public native void setClearsOnInsertion(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/dataDetectorTypes">@property(nonatomic) UIDataDetectorTypes dataDetectorTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("dataDetectorTypes") public native EnumSet<UIDataDetectorType> getDataDetectorTypes();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/dataDetectorTypes">@property(nonatomic) UIDataDetectorTypes dataDetectorTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setDataDetectorTypes:") public native void setDataDetectorTypes(EnumSet<UIDataDetectorType> v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/delegate">@property(nonatomic, assign) id&amp;lt;UITextViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native UITextViewDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/delegate">@property(nonatomic, assign) id&amp;lt;UITextViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(UITextViewDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/editable">@property(nonatomic, getter=isEditable) BOOL editable</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isEditable") public native boolean isEditable();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/editable">@property(nonatomic, getter=isEditable) BOOL editable</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setEditable:") public native void setEditable(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/enablesReturnKeyAutomatically">@property(nonatomic) BOOL enablesReturnKeyAutomatically</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("enablesReturnKeyAutomatically") public native boolean isEnablesReturnKeyAutomatically();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/enablesReturnKeyAutomatically">@property(nonatomic) BOOL enablesReturnKeyAutomatically</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setEnablesReturnKeyAutomatically:") public native void setEnablesReturnKeyAutomatically(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/endOfDocument">@property(nonatomic, readonly) UITextPosition *endOfDocument</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("endOfDocument") public native UITextPosition getEndOfDocument();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/font">@property(nonatomic, retain) UIFont *font</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("font") public native UIFont getFont();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/font">@property(nonatomic, retain) UIFont *font</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setFont:") public native void setFont(UIFont v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/inputAccessoryView">@property (readwrite, retain) UIView *inputAccessoryView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("inputAccessoryView") public native UIView getInputAccessoryView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/inputAccessoryView">@property (readwrite, retain) UIView *inputAccessoryView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setInputAccessoryView:") public native void setInputAccessoryView(UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/inputDelegate">@property(nonatomic, assign) id&amp;lt;UITextInputDelegate&amp;gt; inputDelegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("inputDelegate") public native UITextInputDelegate getInputDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/inputDelegate">@property(nonatomic, assign) id&amp;lt;UITextInputDelegate&amp;gt; inputDelegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setInputDelegate:") public native void setInputDelegate(UITextInputDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/inputView">@property (readwrite, retain) UIView *inputView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("inputView") public native UIView getInputView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/inputView">@property (readwrite, retain) UIView *inputView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setInputView:") public native void setInputView(UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardAppearance">@property(nonatomic) UIKeyboardAppearance keyboardAppearance</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("keyboardAppearance") public native UIKeyboardAppearance getKeyboardAppearance();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardAppearance">@property(nonatomic) UIKeyboardAppearance keyboardAppearance</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setKeyboardAppearance:") public native void setKeyboardAppearance(UIKeyboardAppearance v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardType">@property(nonatomic) UIKeyboardType keyboardType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("keyboardType") public native UIKeyboardType getKeyboardType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardType">@property(nonatomic) UIKeyboardType keyboardType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setKeyboardType:") public native void setKeyboardType(UIKeyboardType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/markedTextRange">@property(nonatomic, readonly) UITextRange *markedTextRange</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("markedTextRange") public native UITextRange getMarkedTextRange();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/markedTextStyle">@property(nonatomic, copy) NSDictionary *markedTextStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("markedTextStyle") public native NSDictionary getMarkedTextStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/markedTextStyle">@property(nonatomic, copy) NSDictionary *markedTextStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setMarkedTextStyle:") public native void setMarkedTextStyle(NSDictionary v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/returnKeyType">@property(nonatomic) UIReturnKeyType returnKeyType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("returnKeyType") public native UIReturnKeyType getReturnKeyType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/returnKeyType">@property(nonatomic) UIReturnKeyType returnKeyType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setReturnKeyType:") public native void setReturnKeyType(UIReturnKeyType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/secureTextEntry">@property(nonatomic, getter=isSecureTextEntry) BOOL secureTextEntry</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isSecureTextEntry") public native boolean isSecureTextEntry();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/secureTextEntry">@property(nonatomic, getter=isSecureTextEntry) BOOL secureTextEntry</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSecureTextEntry:") public native void setSecureTextEntry(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/selectedRange">@property(nonatomic) NSRange selectedRange</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("selectedRange") public native NSRange getSelectedRange();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/selectedRange">@property(nonatomic) NSRange selectedRange</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSelectedRange:") public native void setSelectedRange(NSRange v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectedTextRange">@property(readwrite, copy) UITextRange *selectedTextRange</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("selectedTextRange") public native UITextRange getSelectedTextRange();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectedTextRange">@property(readwrite, copy) UITextRange *selectedTextRange</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setSelectedTextRange:") public native void setSelectedTextRange(UITextRange v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectionAffinity">@property(nonatomic) UITextStorageDirection selectionAffinity</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("selectionAffinity") public native UITextStorageDirection getSelectionAffinity();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectionAffinity">@property(nonatomic) UITextStorageDirection selectionAffinity</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setSelectionAffinity:") public native void setSelectionAffinity(UITextStorageDirection v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/spellCheckingType">@property(nonatomic) UITextSpellCheckingType spellCheckingType</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("spellCheckingType") public native UITextSpellCheckingType getSpellCheckingType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/spellCheckingType">@property(nonatomic) UITextSpellCheckingType spellCheckingType</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setSpellCheckingType:") public native void setSpellCheckingType(UITextSpellCheckingType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("text") public native String getText();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setText:") public native void setText(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/textAlignment">@property(nonatomic) NSTextAlignment textAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("textAlignment") public native NSTextAlignment getTextAlignment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/textAlignment">@property(nonatomic) NSTextAlignment textAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTextAlignment:") public native void setTextAlignment(NSTextAlignment v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/textColor">@property(nonatomic, retain) UIColor *textColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("textColor") public native UIColor getTextColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/textColor">@property(nonatomic, retain) UIColor *textColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTextColor:") public native void setTextColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/textInputView">@property(nonatomic, readonly) UIView *textInputView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("textInputView") public native UIView getTextInputView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/tokenizer">@property(nonatomic, readonly) id&amp;lt;UITextInputTokenizer&amp;gt; tokenizer</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("tokenizer") public native UITextInputTokenizer getTokenizer();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/typingAttributes">@property(nonatomic,copy) NSDictionary *typingAttributes;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("typingAttributes") public native NSDictionary getTypingAttributes();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/typingAttributes">@property(nonatomic,copy) NSDictionary *typingAttributes;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setTypingAttributes:") public native void setTypingAttributes(NSDictionary v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector comparePosition$toPosition$ = Selector.register("comparePosition:toPosition:");
    @Bridge(symbol = "objc_msgSend") private native static NSComparisonResult objc_comparePositions(UITextView __self__, Selector __cmd__, UITextPosition position, UITextPosition other);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSComparisonResult objc_comparePositionsSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextPosition position, UITextPosition other);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/comparePosition:toPosition:">- (NSComparisonResult)comparePosition:(UITextPosition *)position toPosition:(UITextPosition *)other</a>
     * @since Available in iOS 3.2 and later.
     */
    public NSComparisonResult comparePositions(UITextPosition position, UITextPosition other) {
        if (customClass) { return objc_comparePositionsSuper(getSuper(), this, comparePosition$toPosition$, position, other); } else { return objc_comparePositions(this, comparePosition$toPosition$, position, other); }
    }
    
    private static final Selector deleteBackward = Selector.register("deleteBackward");
    @Bridge(symbol = "objc_msgSend") private native static void objc_deleteBackward(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_deleteBackwardSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIKeyInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIKeyInput/deleteBackward">- (void)deleteBackward</a>
     * @since Available in iOS 3.2 and later.
     */
    public void deleteBackward() {
        if (customClass) { objc_deleteBackwardSuper(getSuper(), this, deleteBackward); } else { objc_deleteBackward(this, deleteBackward); }
    }
    
    private static final Selector dictationRecognitionFailed = Selector.register("dictationRecognitionFailed");
    @Bridge(symbol = "objc_msgSend") private native static void objc_dictationRecognitionFailed(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_dictationRecognitionFailedSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/dictationRecognitionFailed">- (void)dictationRecognitionFailed</a>
     * @since Available in iOS 5.1 and later.
     */
    public void dictationRecognitionFailed() {
        if (customClass) { objc_dictationRecognitionFailedSuper(getSuper(), this, dictationRecognitionFailed); } else { objc_dictationRecognitionFailed(this, dictationRecognitionFailed); }
    }
    
    private static final Selector dictationRecordingDidEnd = Selector.register("dictationRecordingDidEnd");
    @Bridge(symbol = "objc_msgSend") private native static void objc_dictationRecordingDidEnd(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_dictationRecordingDidEndSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/dictationRecordingDidEnd">- (void)dictationRecordingDidEnd</a>
     * @since Available in iOS 5.1 and later.
     */
    public void dictationRecordingDidEnd() {
        if (customClass) { objc_dictationRecordingDidEndSuper(getSuper(), this, dictationRecordingDidEnd); } else { objc_dictationRecordingDidEnd(this, dictationRecordingDidEnd); }
    }
    
    private static final Selector baseWritingDirectionForPosition$inDirection$ = Selector.register("baseWritingDirectionForPosition:inDirection:");
    @Bridge(symbol = "objc_msgSend") private native static UITextWritingDirection objc_getBaseWritingDirection(UITextView __self__, Selector __cmd__, UITextPosition position, UITextStorageDirection direction);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextWritingDirection objc_getBaseWritingDirectionSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextPosition position, UITextStorageDirection direction);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/baseWritingDirectionForPosition:inDirection:">- (UITextWritingDirection)baseWritingDirectionForPosition:(UITextPosition *)position inDirection:(UITextStorageDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextWritingDirection getBaseWritingDirection(UITextPosition position, UITextStorageDirection direction) {
        if (customClass) { return objc_getBaseWritingDirectionSuper(getSuper(), this, baseWritingDirectionForPosition$inDirection$, position, direction); } else { return objc_getBaseWritingDirection(this, baseWritingDirectionForPosition$inDirection$, position, direction); }
    }
    
    private static final Selector caretRectForPosition$ = Selector.register("caretRectForPosition:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getCaretRect(UITextView __self__, Selector __cmd__, UITextPosition position);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getCaretRectSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextPosition position);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/caretRectForPosition:">- (CGRect)caretRectForPosition:(UITextPosition *)position</a>
     * @since Available in iOS 3.2 and later.
     */
    public CGRect getCaretRect(UITextPosition position) {
        if (customClass) { return objc_getCaretRectSuper(getSuper(), this, caretRectForPosition$, position); } else { return objc_getCaretRect(this, caretRectForPosition$, position); }
    }
    
    private static final Selector characterOffsetOfPosition$withinRange$ = Selector.register("characterOffsetOfPosition:withinRange:");
    @Bridge(symbol = "objc_msgSend") private native static int objc_getCharacterOffset(UITextView __self__, Selector __cmd__, UITextPosition position, UITextRange range);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_getCharacterOffsetSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextPosition position, UITextRange range);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/characterOffsetOfPosition:withinRange:">- (NSInteger)characterOffsetOfPosition:(UITextPosition *)position withinRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    public int getCharacterOffset(UITextPosition position, UITextRange range) {
        if (customClass) { return objc_getCharacterOffsetSuper(getSuper(), this, characterOffsetOfPosition$withinRange$, position, range); } else { return objc_getCharacterOffset(this, characterOffsetOfPosition$withinRange$, position, range); }
    }
    
    private static final Selector characterRangeByExtendingPosition$inDirection$ = Selector.register("characterRangeByExtendingPosition:inDirection:");
    @Bridge(symbol = "objc_msgSend") private native static UITextRange objc_getCharacterRange(UITextView __self__, Selector __cmd__, UITextPosition position, UITextLayoutDirection direction);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextRange objc_getCharacterRangeSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextPosition position, UITextLayoutDirection direction);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/characterRangeByExtendingPosition:inDirection:">- (UITextRange *)characterRangeByExtendingPosition:(UITextPosition *)position inDirection:(UITextLayoutDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextRange getCharacterRange(UITextPosition position, UITextLayoutDirection direction) {
        if (customClass) { return objc_getCharacterRangeSuper(getSuper(), this, characterRangeByExtendingPosition$inDirection$, position, direction); } else { return objc_getCharacterRange(this, characterRangeByExtendingPosition$inDirection$, position, direction); }
    }
    
    private static final Selector characterRangeAtPoint$ = Selector.register("characterRangeAtPoint:");
    @Bridge(symbol = "objc_msgSend") private native static UITextRange objc_getCharacterRange(UITextView __self__, Selector __cmd__, CGPoint point);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextRange objc_getCharacterRangeSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, CGPoint point);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/characterRangeAtPoint:">- (UITextRange *)characterRangeAtPoint:(CGPoint)point</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextRange getCharacterRange(CGPoint point) {
        if (customClass) { return objc_getCharacterRangeSuper(getSuper(), this, characterRangeAtPoint$, point); } else { return objc_getCharacterRange(this, characterRangeAtPoint$, point); }
    }
    
    private static final Selector closestPositionToPoint$withinRange$ = Selector.register("closestPositionToPoint:withinRange:");
    @Bridge(symbol = "objc_msgSend") private native static UITextPosition objc_getClosestPosition(UITextView __self__, Selector __cmd__, CGPoint point, UITextRange range);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextPosition objc_getClosestPositionSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, CGPoint point, UITextRange range);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/closestPositionToPoint:withinRange:">- (UITextPosition *)closestPositionToPoint:(CGPoint)point withinRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextPosition getClosestPosition(CGPoint point, UITextRange range) {
        if (customClass) { return objc_getClosestPositionSuper(getSuper(), this, closestPositionToPoint$withinRange$, point, range); } else { return objc_getClosestPosition(this, closestPositionToPoint$withinRange$, point, range); }
    }
    
    private static final Selector closestPositionToPoint$ = Selector.register("closestPositionToPoint:");
    @Bridge(symbol = "objc_msgSend") private native static UITextPosition objc_getClosestPosition(UITextView __self__, Selector __cmd__, CGPoint point);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextPosition objc_getClosestPositionSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, CGPoint point);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/closestPositionToPoint:">- (UITextPosition *)closestPositionToPoint:(CGPoint)point</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextPosition getClosestPosition(CGPoint point) {
        if (customClass) { return objc_getClosestPositionSuper(getSuper(), this, closestPositionToPoint$, point); } else { return objc_getClosestPosition(this, closestPositionToPoint$, point); }
    }
    
    private static final Selector frameForDictationResultPlaceholder$ = Selector.register("frameForDictationResultPlaceholder:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getDictationResultPlaceholderFrame(UITextView __self__, Selector __cmd__, NSObject placeholder);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getDictationResultPlaceholderFrameSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, NSObject placeholder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/frameForDictationResultPlaceholder:">- (CGRect)frameForDictationResultPlaceholder:(id)placeholder</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGRect getDictationResultPlaceholderFrame(NSObject placeholder) {
        if (customClass) { return objc_getDictationResultPlaceholderFrameSuper(getSuper(), this, frameForDictationResultPlaceholder$, placeholder); } else { return objc_getDictationResultPlaceholderFrame(this, frameForDictationResultPlaceholder$, placeholder); }
    }
    
    private static final Selector firstRectForRange$ = Selector.register("firstRectForRange:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getFirstRect(UITextView __self__, Selector __cmd__, UITextRange range);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getFirstRectSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextRange range);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/firstRectForRange:">- (CGRect)firstRectForRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    public CGRect getFirstRect(UITextRange range) {
        if (customClass) { return objc_getFirstRectSuper(getSuper(), this, firstRectForRange$, range); } else { return objc_getFirstRect(this, firstRectForRange$, range); }
    }
    
    private static final Selector offsetFromPosition$toPosition$ = Selector.register("offsetFromPosition:toPosition:");
    @Bridge(symbol = "objc_msgSend") private native static int objc_getOffset(UITextView __self__, Selector __cmd__, UITextPosition fromPosition, UITextPosition toPosition);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_getOffsetSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextPosition fromPosition, UITextPosition toPosition);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/offsetFromPosition:toPosition:">- (NSInteger)offsetFromPosition:(UITextPosition *)fromPosition toPosition:(UITextPosition *)toPosition</a>
     * @since Available in iOS 3.2 and later.
     */
    public int getOffset(UITextPosition fromPosition, UITextPosition toPosition) {
        if (customClass) { return objc_getOffsetSuper(getSuper(), this, offsetFromPosition$toPosition$, fromPosition, toPosition); } else { return objc_getOffset(this, offsetFromPosition$toPosition$, fromPosition, toPosition); }
    }
    
    private static final Selector positionFromPosition$inDirection$offset$ = Selector.register("positionFromPosition:inDirection:offset:");
    @Bridge(symbol = "objc_msgSend") private native static UITextPosition objc_getPosition(UITextView __self__, Selector __cmd__, UITextPosition position, UITextLayoutDirection direction, int offset);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextPosition objc_getPositionSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextPosition position, UITextLayoutDirection direction, int offset);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionFromPosition:inDirection:offset:">- (UITextPosition *)positionFromPosition:(UITextPosition *)position inDirection:(UITextLayoutDirection)direction offset:(NSInteger)offset</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextPosition getPosition(UITextPosition position, UITextLayoutDirection direction, int offset) {
        if (customClass) { return objc_getPositionSuper(getSuper(), this, positionFromPosition$inDirection$offset$, position, direction, offset); } else { return objc_getPosition(this, positionFromPosition$inDirection$offset$, position, direction, offset); }
    }
    
    private static final Selector positionFromPosition$offset$ = Selector.register("positionFromPosition:offset:");
    @Bridge(symbol = "objc_msgSend") private native static UITextPosition objc_getPosition(UITextView __self__, Selector __cmd__, UITextPosition position, int offset);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextPosition objc_getPositionSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextPosition position, int offset);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionFromPosition:offset:">- (UITextPosition *)positionFromPosition:(UITextPosition *)position offset:(NSInteger)offset</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextPosition getPosition(UITextPosition position, int offset) {
        if (customClass) { return objc_getPositionSuper(getSuper(), this, positionFromPosition$offset$, position, offset); } else { return objc_getPosition(this, positionFromPosition$offset$, position, offset); }
    }
    
    private static final Selector positionWithinRange$farthestInDirection$ = Selector.register("positionWithinRange:farthestInDirection:");
    @Bridge(symbol = "objc_msgSend") private native static UITextPosition objc_getPosition(UITextView __self__, Selector __cmd__, UITextRange range, UITextLayoutDirection direction);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextPosition objc_getPositionSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextRange range, UITextLayoutDirection direction);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionWithinRange:farthestInDirection:">- (UITextPosition *)positionWithinRange:(UITextRange *)range farthestInDirection:(UITextLayoutDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextPosition getPosition(UITextRange range, UITextLayoutDirection direction) {
        if (customClass) { return objc_getPositionSuper(getSuper(), this, positionWithinRange$farthestInDirection$, range, direction); } else { return objc_getPosition(this, positionWithinRange$farthestInDirection$, range, direction); }
    }
    
    private static final Selector positionWithinRange$atCharacterOffset$ = Selector.register("positionWithinRange:atCharacterOffset:");
    @Bridge(symbol = "objc_msgSend") private native static UITextPosition objc_getPosition(UITextView __self__, Selector __cmd__, UITextRange range, int offset);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextPosition objc_getPositionSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextRange range, int offset);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionWithinRange:atCharacterOffset:">- (UITextPosition *)positionWithinRange:(UITextRange *)range atCharacterOffset:(NSInteger)offset</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextPosition getPosition(UITextRange range, int offset) {
        if (customClass) { return objc_getPositionSuper(getSuper(), this, positionWithinRange$atCharacterOffset$, range, offset); } else { return objc_getPosition(this, positionWithinRange$atCharacterOffset$, range, offset); }
    }
    
    private static final Selector selectionRectsForRange$ = Selector.register("selectionRectsForRange:");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getSelectionRects(UITextView __self__, Selector __cmd__, UITextRange range);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getSelectionRectsSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextRange range);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/selectionRectsForRange:">- (NSArray *)selectionRectsForRange:(UITextRange *)range</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSArray getSelectionRects(UITextRange range) {
        if (customClass) { return objc_getSelectionRectsSuper(getSuper(), this, selectionRectsForRange$, range); } else { return objc_getSelectionRects(this, selectionRectsForRange$, range); }
    }
    
    private static final Selector textInRange$ = Selector.register("textInRange:");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getText(UITextView __self__, Selector __cmd__, UITextRange range);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getTextSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextRange range);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/textInRange:">- (NSString *)textInRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    public String getText(UITextRange range) {
        if (customClass) { return objc_getTextSuper(getSuper(), this, textInRange$, range); } else { return objc_getText(this, textInRange$, range); }
    }
    
    private static final Selector textRangeFromPosition$toPosition$ = Selector.register("textRangeFromPosition:toPosition:");
    @Bridge(symbol = "objc_msgSend") private native static UITextRange objc_getTextRange(UITextView __self__, Selector __cmd__, UITextPosition fromPosition, UITextPosition toPosition);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextRange objc_getTextRangeSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextPosition fromPosition, UITextPosition toPosition);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/textRangeFromPosition:toPosition:">- (UITextRange *)textRangeFromPosition:(UITextPosition *)fromPosition toPosition:(UITextPosition *)toPosition</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextRange getTextRange(UITextPosition fromPosition, UITextPosition toPosition) {
        if (customClass) { return objc_getTextRangeSuper(getSuper(), this, textRangeFromPosition$toPosition$, fromPosition, toPosition); } else { return objc_getTextRange(this, textRangeFromPosition$toPosition$, fromPosition, toPosition); }
    }
    
    private static final Selector textStylingAtPosition$inDirection$ = Selector.register("textStylingAtPosition:inDirection:");
    @Bridge(symbol = "objc_msgSend") private native static NSDictionary objc_getTextStyling(UITextView __self__, Selector __cmd__, UITextPosition position, UITextStorageDirection direction);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSDictionary objc_getTextStylingSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextPosition position, UITextStorageDirection direction);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/textStylingAtPosition:inDirection:">- (NSDictionary *)textStylingAtPosition:(UITextPosition *)position inDirection:(UITextStorageDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    public NSDictionary getTextStyling(UITextPosition position, UITextStorageDirection direction) {
        if (customClass) { return objc_getTextStylingSuper(getSuper(), this, textStylingAtPosition$inDirection$, position, direction); } else { return objc_getTextStyling(this, textStylingAtPosition$inDirection$, position, direction); }
    }
    
    private static final Selector hasText = Selector.register("hasText");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_hasText(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_hasTextSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instm/UITextView/hasText">- (BOOL)hasText</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean hasText() {
        if (customClass) { return objc_hasTextSuper(getSuper(), this, hasText); } else { return objc_hasText(this, hasText); }
    }
    
    private static final Selector insertDictationResult$ = Selector.register("insertDictationResult:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_insertDictationResult(UITextView __self__, Selector __cmd__, NSArray dictationResult);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_insertDictationResultSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, NSArray dictationResult);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/insertDictationResult:">- (void)insertDictationResult:(NSArray *)dictationResult</a>
     * @since Available in iOS 5.1 and later.
     */
    public void insertDictationResult(NSArray dictationResult) {
        if (customClass) { objc_insertDictationResultSuper(getSuper(), this, insertDictationResult$, dictationResult); } else { objc_insertDictationResult(this, insertDictationResult$, dictationResult); }
    }
    
    private static final Selector insertDictationResultPlaceholder = Selector.register("insertDictationResultPlaceholder");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_insertDictationResultPlaceholder(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSObject objc_insertDictationResultPlaceholderSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/insertDictationResultPlaceholder">- (id)insertDictationResultPlaceholder</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSObject insertDictationResultPlaceholder() {
        if (customClass) { return objc_insertDictationResultPlaceholderSuper(getSuper(), this, insertDictationResultPlaceholder); } else { return objc_insertDictationResultPlaceholder(this, insertDictationResultPlaceholder); }
    }
    
    private static final Selector insertText$ = Selector.register("insertText:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_insertText(UITextView __self__, Selector __cmd__, String text);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_insertTextSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, String text);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIKeyInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIKeyInput/insertText:">- (void)insertText:(NSString *)text</a>
     * @since Available in iOS 3.2 and later.
     */
    public void insertText(String text) {
        if (customClass) { objc_insertTextSuper(getSuper(), this, insertText$, text); } else { objc_insertText(this, insertText$, text); }
    }
    
    private static final Selector removeDictationResultPlaceholder$willInsertResult$ = Selector.register("removeDictationResultPlaceholder:willInsertResult:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_removeDictationResultPlaceholder(UITextView __self__, Selector __cmd__, NSObject placeholder, boolean willInsertResult);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_removeDictationResultPlaceholderSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, NSObject placeholder, boolean willInsertResult);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/removeDictationResultPlaceholder:willInsertResult:">- (void)removeDictationResultPlaceholder:(id)placeholder willInsertResult:(BOOL)willInsertResult</a>
     * @since Available in iOS 6.0 and later.
     */
    public void removeDictationResultPlaceholder(NSObject placeholder, boolean willInsertResult) {
        if (customClass) { objc_removeDictationResultPlaceholderSuper(getSuper(), this, removeDictationResultPlaceholder$willInsertResult$, placeholder, willInsertResult); } else { objc_removeDictationResultPlaceholder(this, removeDictationResultPlaceholder$willInsertResult$, placeholder, willInsertResult); }
    }
    
    private static final Selector replaceRange$withText$ = Selector.register("replaceRange:withText:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_replaceText(UITextView __self__, Selector __cmd__, UITextRange range, String text);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_replaceTextSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextRange range, String text);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/replaceRange:withText:">- (void)replaceRange:(UITextRange *)range withText:(NSString *)text</a>
     * @since Available in iOS 3.2 and later.
     */
    public void replaceText(UITextRange range, String text) {
        if (customClass) { objc_replaceTextSuper(getSuper(), this, replaceRange$withText$, range, text); } else { objc_replaceText(this, replaceRange$withText$, range, text); }
    }
    
    private static final Selector scrollRangeToVisible$ = Selector.register("scrollRangeToVisible:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_scrollRangeToVisible(UITextView __self__, Selector __cmd__, NSRange range);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_scrollRangeToVisibleSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, NSRange range);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instm/UITextView/scrollRangeToVisible:">- (void)scrollRangeToVisible:(NSRange)range</a>
     * @since Available in iOS 2.0 and later.
     */
    public void scrollRangeToVisible(NSRange range) {
        if (customClass) { objc_scrollRangeToVisibleSuper(getSuper(), this, scrollRangeToVisible$, range); } else { objc_scrollRangeToVisible(this, scrollRangeToVisible$, range); }
    }
    
    private static final Selector setBaseWritingDirection$forRange$ = Selector.register("setBaseWritingDirection:forRange:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setBaseWritingDirection(UITextView __self__, Selector __cmd__, UITextWritingDirection writingDirection, UITextRange range);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setBaseWritingDirectionSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextWritingDirection writingDirection, UITextRange range);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/setBaseWritingDirection:forRange:">- (void)setBaseWritingDirection:(UITextWritingDirection)writingDirection forRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setBaseWritingDirection(UITextWritingDirection writingDirection, UITextRange range) {
        if (customClass) { objc_setBaseWritingDirectionSuper(getSuper(), this, setBaseWritingDirection$forRange$, writingDirection, range); } else { objc_setBaseWritingDirection(this, setBaseWritingDirection$forRange$, writingDirection, range); }
    }
    
    private static final Selector setMarkedText$selectedRange$ = Selector.register("setMarkedText:selectedRange:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setMarkedText(UITextView __self__, Selector __cmd__, String markedText, NSRange selectedRange);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setMarkedTextSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, String markedText, NSRange selectedRange);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/setMarkedText:selectedRange:">- (void)setMarkedText:(NSString *)markedText selectedRange:(NSRange)selectedRange</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setMarkedText(String markedText, NSRange selectedRange) {
        if (customClass) { objc_setMarkedTextSuper(getSuper(), this, setMarkedText$selectedRange$, markedText, selectedRange); } else { objc_setMarkedText(this, setMarkedText$selectedRange$, markedText, selectedRange); }
    }
    
    private static final Selector shouldChangeTextInRange$replacementText$ = Selector.register("shouldChangeTextInRange:replacementText:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_shouldChangeText(UITextView __self__, Selector __cmd__, UITextRange range, String text);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_shouldChangeTextSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextRange range, String text);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/shouldChangeTextInRange:replacementText:">- (BOOL)shouldChangeTextInRange:(UITextRange *)range replacementText:(NSString *)text</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean shouldChangeText(UITextRange range, String text) {
        if (customClass) { return objc_shouldChangeTextSuper(getSuper(), this, shouldChangeTextInRange$replacementText$, range, text); } else { return objc_shouldChangeText(this, shouldChangeTextInRange$replacementText$, range, text); }
    }
    
    private static final Selector unmarkText = Selector.register("unmarkText");
    @Bridge(symbol = "objc_msgSend") private native static void objc_unmarkText(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_unmarkTextSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/unmarkText">- (void)unmarkText</a>
     * @since Available in iOS 3.2 and later.
     */
    public void unmarkText() {
        if (customClass) { objc_unmarkTextSuper(getSuper(), this, unmarkText); } else { objc_unmarkText(this, unmarkText); }
    }
    /*</methods>*/

}
