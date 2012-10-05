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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html">UITextField Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UITextField /*</name>*/ 
    extends /*<extends>*/ UIControl /*</extends>*/ 
    /*<implements>*/ implements UITextInput, UIKeyInput, UITextInputTraits /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITextField /*</name>*/.class);
    }

    /*<constructors>*/
    public UITextField() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/adjustsFontSizeToFitWidth">@property(nonatomic) BOOL adjustsFontSizeToFitWidth</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("adjustsFontSizeToFitWidth") public native @Type("BOOL") boolean isAdjustsFontSizeToFitWidth();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/adjustsFontSizeToFitWidth">@property(nonatomic) BOOL adjustsFontSizeToFitWidth</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAdjustsFontSizeToFitWidth:") public native void setAdjustsFontSizeToFitWidth(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/allowsEditingTextAttributes">@property(nonatomic) BOOL allowsEditingTextAttributes</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("allowsEditingTextAttributes") public native @Type("BOOL") boolean isAllowsEditingTextAttributes();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/allowsEditingTextAttributes">@property(nonatomic) BOOL allowsEditingTextAttributes</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setAllowsEditingTextAttributes:") public native void setAllowsEditingTextAttributes(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/attributedPlaceholder">@property(nonatomic,copy) NSAttributedString *attributedPlaceholder</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("attributedPlaceholder") public native @Type("NSAttributedString *") NSAttributedString getAttributedPlaceholder();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/attributedPlaceholder">@property(nonatomic,copy) NSAttributedString *attributedPlaceholder</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setAttributedPlaceholder:") public native void setAttributedPlaceholder(@Type("NSAttributedString *") NSAttributedString v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/attributedText">@property(nonatomic,copy) NSAttributedString *attributedText</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("attributedText") public native @Type("NSAttributedString *") NSAttributedString getAttributedText();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/attributedText">@property(nonatomic,copy) NSAttributedString *attributedText</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setAttributedText:") public native void setAttributedText(@Type("NSAttributedString *") NSAttributedString v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocapitalizationType">@property(nonatomic) UITextAutocapitalizationType autocapitalizationType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("autocapitalizationType") public native @Type("UITextAutocapitalizationType") UITextAutocapitalizationType getAutocapitalizationType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocapitalizationType">@property(nonatomic) UITextAutocapitalizationType autocapitalizationType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAutocapitalizationType:") public native void setAutocapitalizationType(@Type("UITextAutocapitalizationType") UITextAutocapitalizationType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocorrectionType">@property(nonatomic) UITextAutocorrectionType autocorrectionType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("autocorrectionType") public native @Type("UITextAutocorrectionType") UITextAutocorrectionType getAutocorrectionType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocorrectionType">@property(nonatomic) UITextAutocorrectionType autocorrectionType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAutocorrectionType:") public native void setAutocorrectionType(@Type("UITextAutocorrectionType") UITextAutocorrectionType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/background">@property(nonatomic, retain) UIImage *background</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("background") public native @Type("UIImage *") UIImage getBackground();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/background">@property(nonatomic, retain) UIImage *background</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBackground:") public native void setBackground(@Type("UIImage *") UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/beginningOfDocument">@property(nonatomic, readonly) UITextPosition *beginningOfDocument</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("beginningOfDocument") public native @Type("UITextPosition *") UITextPosition getBeginningOfDocument();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/borderStyle">@property(nonatomic) UITextBorderStyle borderStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("borderStyle") public native @Type("UITextBorderStyle") UITextBorderStyle getBorderStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/borderStyle">@property(nonatomic) UITextBorderStyle borderStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBorderStyle:") public native void setBorderStyle(@Type("UITextBorderStyle") UITextBorderStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/clearButtonMode">@property(nonatomic) UITextFieldViewMode clearButtonMode</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("clearButtonMode") public native @Type("UITextFieldViewMode") UITextFieldViewMode getClearButtonMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/clearButtonMode">@property(nonatomic) UITextFieldViewMode clearButtonMode</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setClearButtonMode:") public native void setClearButtonMode(@Type("UITextFieldViewMode") UITextFieldViewMode v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/clearsOnBeginEditing">@property(nonatomic) BOOL clearsOnBeginEditing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("clearsOnBeginEditing") public native @Type("BOOL") boolean isClearsOnBeginEditing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/clearsOnBeginEditing">@property(nonatomic) BOOL clearsOnBeginEditing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setClearsOnBeginEditing:") public native void setClearsOnBeginEditing(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/clearsOnInsertion">@property(nonatomic) BOOL clearsOnInsertion</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("clearsOnInsertion") public native @Type("BOOL") boolean isClearsOnInsertion();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/clearsOnInsertion">@property(nonatomic) BOOL clearsOnInsertion</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setClearsOnInsertion:") public native void setClearsOnInsertion(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/delegate">@property(nonatomic, assign) id&amp;lt;UITextFieldDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native @Type("id<UITextFieldDelegate>") UITextFieldDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/delegate">@property(nonatomic, assign) id&amp;lt;UITextFieldDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UITextFieldDelegate>") UITextFieldDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/disabledBackground">@property(nonatomic, retain) UIImage *disabledBackground</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("disabledBackground") public native @Type("UIImage *") UIImage getDisabledBackground();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/disabledBackground">@property(nonatomic, retain) UIImage *disabledBackground</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDisabledBackground:") public native void setDisabledBackground(@Type("UIImage *") UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/editing">@property(nonatomic, readonly, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isEditing") public native @Type("BOOL") boolean isEditing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/enablesReturnKeyAutomatically">@property(nonatomic) BOOL enablesReturnKeyAutomatically</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("enablesReturnKeyAutomatically") public native @Type("BOOL") boolean isEnablesReturnKeyAutomatically();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/enablesReturnKeyAutomatically">@property(nonatomic) BOOL enablesReturnKeyAutomatically</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setEnablesReturnKeyAutomatically:") public native void setEnablesReturnKeyAutomatically(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/endOfDocument">@property(nonatomic, readonly) UITextPosition *endOfDocument</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("endOfDocument") public native @Type("UITextPosition *") UITextPosition getEndOfDocument();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/font">@property(nonatomic, retain) UIFont *font</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("font") public native @Type("UIFont *") UIFont getFont();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/font">@property(nonatomic, retain) UIFont *font</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setFont:") public native void setFont(@Type("UIFont *") UIFont v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/inputAccessoryView">@property (readwrite, retain) UIView *inputAccessoryView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("inputAccessoryView") public native @Type("UIView *") UIView getInputAccessoryView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/inputAccessoryView">@property (readwrite, retain) UIView *inputAccessoryView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setInputAccessoryView:") public native void setInputAccessoryView(@Type("UIView *") UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/inputDelegate">@property(nonatomic, assign) id&amp;lt;UITextInputDelegate&amp;gt; inputDelegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("inputDelegate") public native @Type("id<UITextInputDelegate>") UITextInputDelegate getInputDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/inputDelegate">@property(nonatomic, assign) id&amp;lt;UITextInputDelegate&amp;gt; inputDelegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setInputDelegate:") public native void setInputDelegate(@Type("id<UITextInputDelegate>") UITextInputDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/inputView">@property (readwrite, retain) UIView *inputView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("inputView") public native @Type("UIView *") UIView getInputView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/inputView">@property (readwrite, retain) UIView *inputView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setInputView:") public native void setInputView(@Type("UIView *") UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardAppearance">@property(nonatomic) UIKeyboardAppearance keyboardAppearance</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("keyboardAppearance") public native @Type("UIKeyboardAppearance") UIKeyboardAppearance getKeyboardAppearance();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardAppearance">@property(nonatomic) UIKeyboardAppearance keyboardAppearance</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setKeyboardAppearance:") public native void setKeyboardAppearance(@Type("UIKeyboardAppearance") UIKeyboardAppearance v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardType">@property(nonatomic) UIKeyboardType keyboardType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("keyboardType") public native @Type("UIKeyboardType") UIKeyboardType getKeyboardType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardType">@property(nonatomic) UIKeyboardType keyboardType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setKeyboardType:") public native void setKeyboardType(@Type("UIKeyboardType") UIKeyboardType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/leftView">@property(nonatomic, retain) UIView *leftView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("leftView") public native @Type("UIView *") UIView getLeftView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/leftView">@property(nonatomic, retain) UIView *leftView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setLeftView:") public native void setLeftView(@Type("UIView *") UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/leftViewMode">@property(nonatomic) UITextFieldViewMode leftViewMode</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("leftViewMode") public native @Type("UITextFieldViewMode") UITextFieldViewMode getLeftViewMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/leftViewMode">@property(nonatomic) UITextFieldViewMode leftViewMode</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setLeftViewMode:") public native void setLeftViewMode(@Type("UITextFieldViewMode") UITextFieldViewMode v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/markedTextRange">@property(nonatomic, readonly) UITextRange *markedTextRange</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("markedTextRange") public native @Type("UITextRange *") UITextRange getMarkedTextRange();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/markedTextStyle">@property(nonatomic, copy) NSDictionary *markedTextStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("markedTextStyle") public native @Type("NSDictionary *") NSDictionary getMarkedTextStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/markedTextStyle">@property(nonatomic, copy) NSDictionary *markedTextStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setMarkedTextStyle:") public native void setMarkedTextStyle(@Type("NSDictionary *") NSDictionary v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/minimumFontSize">@property(nonatomic) CGFloat minimumFontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("minimumFontSize") public native @Type("CGFloat") float getMinimumFontSize();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/minimumFontSize">@property(nonatomic) CGFloat minimumFontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMinimumFontSize:") public native void setMinimumFontSize(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/placeholder">@property(nonatomic, copy) NSString *placeholder</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("placeholder") public native @Type("NSString *") String getPlaceholder();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/placeholder">@property(nonatomic, copy) NSString *placeholder</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setPlaceholder:") public native void setPlaceholder(@Type("NSString *") String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/returnKeyType">@property(nonatomic) UIReturnKeyType returnKeyType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("returnKeyType") public native @Type("UIReturnKeyType") UIReturnKeyType getReturnKeyType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/returnKeyType">@property(nonatomic) UIReturnKeyType returnKeyType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setReturnKeyType:") public native void setReturnKeyType(@Type("UIReturnKeyType") UIReturnKeyType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/rightView">@property(nonatomic, retain) UIView *rightView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("rightView") public native @Type("UIView *") UIView getRightView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/rightView">@property(nonatomic, retain) UIView *rightView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setRightView:") public native void setRightView(@Type("UIView *") UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/rightViewMode">@property(nonatomic) UITextFieldViewMode rightViewMode</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("rightViewMode") public native @Type("UITextFieldViewMode") UITextFieldViewMode getRightViewMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/rightViewMode">@property(nonatomic) UITextFieldViewMode rightViewMode</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setRightViewMode:") public native void setRightViewMode(@Type("UITextFieldViewMode") UITextFieldViewMode v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/secureTextEntry">@property(nonatomic, getter=isSecureTextEntry) BOOL secureTextEntry</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isSecureTextEntry") public native @Type("BOOL") boolean isSecureTextEntry();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/secureTextEntry">@property(nonatomic, getter=isSecureTextEntry) BOOL secureTextEntry</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSecureTextEntry:") public native void setSecureTextEntry(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectedTextRange">@property(readwrite, copy) UITextRange *selectedTextRange</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("selectedTextRange") public native @Type("UITextRange *") UITextRange getSelectedTextRange();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectedTextRange">@property(readwrite, copy) UITextRange *selectedTextRange</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setSelectedTextRange:") public native void setSelectedTextRange(@Type("UITextRange *") UITextRange v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectionAffinity">@property(nonatomic) UITextStorageDirection selectionAffinity</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("selectionAffinity") public native @Type("UITextStorageDirection") UITextStorageDirection getSelectionAffinity();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectionAffinity">@property(nonatomic) UITextStorageDirection selectionAffinity</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setSelectionAffinity:") public native void setSelectionAffinity(@Type("UITextStorageDirection") UITextStorageDirection v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/spellCheckingType">@property(nonatomic) UITextSpellCheckingType spellCheckingType</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("spellCheckingType") public native @Type("UITextSpellCheckingType") UITextSpellCheckingType getSpellCheckingType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/spellCheckingType">@property(nonatomic) UITextSpellCheckingType spellCheckingType</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setSpellCheckingType:") public native void setSpellCheckingType(@Type("UITextSpellCheckingType") UITextSpellCheckingType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("text") public native @Type("NSString *") String getText();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setText:") public native void setText(@Type("NSString *") String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/textAlignment">@property(nonatomic) NSTextAlignment textAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("textAlignment") public native @Type("NSTextAlignment") NSTextAlignment getTextAlignment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/textAlignment">@property(nonatomic) NSTextAlignment textAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTextAlignment:") public native void setTextAlignment(@Type("NSTextAlignment") NSTextAlignment v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/textColor">@property(nonatomic, retain) UIColor *textColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("textColor") public native @Type("UIColor *") UIColor getTextColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/textColor">@property(nonatomic, retain) UIColor *textColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTextColor:") public native void setTextColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/textInputView">@property(nonatomic, readonly) UIView *textInputView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("textInputView") public native @Type("UIView *") UIView getTextInputView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/tokenizer">@property(nonatomic, readonly) id&amp;lt;UITextInputTokenizer&amp;gt; tokenizer</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("tokenizer") public native @Type("id<UITextInputTokenizer>") UITextInputTokenizer getTokenizer();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/typingAttributes">@property(nonatomic,copy) NSDictionary *typingAttributes;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("typingAttributes") public native @Type("NSDictionary *") NSDictionary getTypingAttributes();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/typingAttributes">@property(nonatomic,copy) NSDictionary *typingAttributes;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setTypingAttributes:") public native void setTypingAttributes(@Type("NSDictionary *") NSDictionary v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instm/UITextField/clearButtonRectForBounds:">- (CGRect)clearButtonRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("clearButtonRectForBounds:") public native @Type("CGRect") CGRect clearButtonRect(@Type("CGRect") CGRect bounds);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/comparePosition:toPosition:">- (NSComparisonResult)comparePosition:(UITextPosition *)position toPosition:(UITextPosition *)other</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("comparePosition:toPosition:") public native @Type("NSComparisonResult") NSComparisonResult comparePositions(@Type("UITextPosition *") UITextPosition position, @Type("UITextPosition *") UITextPosition other);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIKeyInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIKeyInput/deleteBackward">- (void)deleteBackward</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("deleteBackward") public native @Type("void") void deleteBackward();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/dictationRecognitionFailed">- (void)dictationRecognitionFailed</a>
     * @since Available in iOS 5.1 and later.
     */
    @Bind("dictationRecognitionFailed") public native @Type("void") void dictationRecognitionFailed();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/dictationRecordingDidEnd">- (void)dictationRecordingDidEnd</a>
     * @since Available in iOS 5.1 and later.
     */
    @Bind("dictationRecordingDidEnd") public native @Type("void") void dictationRecordingDidEnd();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instm/UITextField/drawPlaceholderInRect:">- (void)drawPlaceholderInRect:(CGRect)rect</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("drawPlaceholderInRect:") public native @Type("void") void drawPlaceholder(@Type("CGRect") CGRect rect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instm/UITextField/drawTextInRect:">- (void)drawTextInRect:(CGRect)rect</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("drawTextInRect:") public native @Type("void") void drawText(@Type("CGRect") CGRect rect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/baseWritingDirectionForPosition:inDirection:">- (UITextWritingDirection)baseWritingDirectionForPosition:(UITextPosition *)position inDirection:(UITextStorageDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("baseWritingDirectionForPosition:inDirection:") public native @Type("UITextWritingDirection") UITextWritingDirection getBaseWritingDirection(@Type("UITextPosition *") UITextPosition position, @Type("UITextStorageDirection") UITextStorageDirection direction);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instm/UITextField/borderRectForBounds:">- (CGRect)borderRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("borderRectForBounds:") public native @Type("CGRect") CGRect getBorderRect(@Type("CGRect") CGRect bounds);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/caretRectForPosition:">- (CGRect)caretRectForPosition:(UITextPosition *)position</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("caretRectForPosition:") public native @Type("CGRect") CGRect getCaretRect(@Type("UITextPosition *") UITextPosition position);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/characterOffsetOfPosition:withinRange:">- (NSInteger)characterOffsetOfPosition:(UITextPosition *)position withinRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("characterOffsetOfPosition:withinRange:") public native @Type("NSInteger") int getCharacterOffset(@Type("UITextPosition *") UITextPosition position, @Type("UITextRange *") UITextRange range);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/characterRangeAtPoint:">- (UITextRange *)characterRangeAtPoint:(CGPoint)point</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("characterRangeAtPoint:") public native @Type("UITextRange *") UITextRange getCharacterRange(@Type("CGPoint") CGPoint point);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/characterRangeByExtendingPosition:inDirection:">- (UITextRange *)characterRangeByExtendingPosition:(UITextPosition *)position inDirection:(UITextLayoutDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("characterRangeByExtendingPosition:inDirection:") public native @Type("UITextRange *") UITextRange getCharacterRange(@Type("UITextPosition *") UITextPosition position, @Type("UITextLayoutDirection") UITextLayoutDirection direction);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/closestPositionToPoint:withinRange:">- (UITextPosition *)closestPositionToPoint:(CGPoint)point withinRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("closestPositionToPoint:withinRange:") public native @Type("UITextPosition *") UITextPosition getClosestPosition(@Type("CGPoint") CGPoint point, @Type("UITextRange *") UITextRange range);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/closestPositionToPoint:">- (UITextPosition *)closestPositionToPoint:(CGPoint)point</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("closestPositionToPoint:") public native @Type("UITextPosition *") UITextPosition getClosestPosition(@Type("CGPoint") CGPoint point);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/frameForDictationResultPlaceholder:">- (CGRect)frameForDictationResultPlaceholder:(id)placeholder</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("frameForDictationResultPlaceholder:") public native @Type("CGRect") CGRect getDictationResultPlaceholderFrame(@Type("id") NSObject placeholder);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instm/UITextField/editingRectForBounds:">- (CGRect)editingRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("editingRectForBounds:") public native @Type("CGRect") CGRect getEditingRect(@Type("CGRect") CGRect bounds);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/firstRectForRange:">- (CGRect)firstRectForRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("firstRectForRange:") public native @Type("CGRect") CGRect getFirstRect(@Type("UITextRange *") UITextRange range);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instm/UITextField/leftViewRectForBounds:">- (CGRect)leftViewRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("leftViewRectForBounds:") public native @Type("CGRect") CGRect getLeftViewRect(@Type("CGRect") CGRect bounds);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/offsetFromPosition:toPosition:">- (NSInteger)offsetFromPosition:(UITextPosition *)fromPosition toPosition:(UITextPosition *)toPosition</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("offsetFromPosition:toPosition:") public native @Type("NSInteger") int getOffset(@Type("UITextPosition *") UITextPosition fromPosition, @Type("UITextPosition *") UITextPosition toPosition);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instm/UITextField/placeholderRectForBounds:">- (CGRect)placeholderRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("placeholderRectForBounds:") public native @Type("CGRect") CGRect getPlaceholderRect(@Type("CGRect") CGRect bounds);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionFromPosition:inDirection:offset:">- (UITextPosition *)positionFromPosition:(UITextPosition *)position inDirection:(UITextLayoutDirection)direction offset:(NSInteger)offset</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("positionFromPosition:inDirection:offset:") public native @Type("UITextPosition *") UITextPosition getPosition(@Type("UITextPosition *") UITextPosition position, @Type("UITextLayoutDirection") UITextLayoutDirection direction, @Type("NSInteger") int offset);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionWithinRange:atCharacterOffset:">- (UITextPosition *)positionWithinRange:(UITextRange *)range atCharacterOffset:(NSInteger)offset</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("positionWithinRange:atCharacterOffset:") public native @Type("UITextPosition *") UITextPosition getPosition(@Type("UITextRange *") UITextRange range, @Type("NSInteger") int offset);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionFromPosition:offset:">- (UITextPosition *)positionFromPosition:(UITextPosition *)position offset:(NSInteger)offset</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("positionFromPosition:offset:") public native @Type("UITextPosition *") UITextPosition getPosition(@Type("UITextPosition *") UITextPosition position, @Type("NSInteger") int offset);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionWithinRange:farthestInDirection:">- (UITextPosition *)positionWithinRange:(UITextRange *)range farthestInDirection:(UITextLayoutDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("positionWithinRange:farthestInDirection:") public native @Type("UITextPosition *") UITextPosition getPosition(@Type("UITextRange *") UITextRange range, @Type("UITextLayoutDirection") UITextLayoutDirection direction);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instm/UITextField/rightViewRectForBounds:">- (CGRect)rightViewRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("rightViewRectForBounds:") public native @Type("CGRect") CGRect getRightViewRect(@Type("CGRect") CGRect bounds);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/selectionRectsForRange:">- (NSArray *)selectionRectsForRange:(UITextRange *)range</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("selectionRectsForRange:") public native @Type("NSArray *") NSArray getSelectionRects(@Type("UITextRange *") UITextRange range);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/textInRange:">- (NSString *)textInRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("textInRange:") public native @Type("NSString *") String getText(@Type("UITextRange *") UITextRange range);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/textRangeFromPosition:toPosition:">- (UITextRange *)textRangeFromPosition:(UITextPosition *)fromPosition toPosition:(UITextPosition *)toPosition</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("textRangeFromPosition:toPosition:") public native @Type("UITextRange *") UITextRange getTextRange(@Type("UITextPosition *") UITextPosition fromPosition, @Type("UITextPosition *") UITextPosition toPosition);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instm/UITextField/textRectForBounds:">- (CGRect)textRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("textRectForBounds:") public native @Type("CGRect") CGRect getTextRect(@Type("CGRect") CGRect bounds);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/textStylingAtPosition:inDirection:">- (NSDictionary *)textStylingAtPosition:(UITextPosition *)position inDirection:(UITextStorageDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("textStylingAtPosition:inDirection:") public native @Type("NSDictionary *") NSDictionary getTextStyling(@Type("UITextPosition *") UITextPosition position, @Type("UITextStorageDirection") UITextStorageDirection direction);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIKeyInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIKeyInput/hasText">- (BOOL)hasText</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("hasText") public native @Type("BOOL") boolean hasText();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/insertDictationResult:">- (void)insertDictationResult:(NSArray *)dictationResult</a>
     * @since Available in iOS 5.1 and later.
     */
    @Bind("insertDictationResult:") public native @Type("void") void insertDictationResult(@Type("NSArray *") NSArray dictationResult);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/insertDictationResultPlaceholder">- (id)insertDictationResultPlaceholder</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("insertDictationResultPlaceholder") public native @Type("id") NSObject insertDictationResultPlaceholder();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIKeyInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIKeyInput/insertText:">- (void)insertText:(NSString *)text</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("insertText:") public native @Type("void") void insertText(@Type("NSString *") String text);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/removeDictationResultPlaceholder:willInsertResult:">- (void)removeDictationResultPlaceholder:(id)placeholder willInsertResult:(BOOL)willInsertResult</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("removeDictationResultPlaceholder:willInsertResult:") public native @Type("void") void removeDictationResultPlaceholder(@Type("id") NSObject placeholder, @Type("BOOL") boolean willInsertResult);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/replaceRange:withText:">- (void)replaceRange:(UITextRange *)range withText:(NSString *)text</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("replaceRange:withText:") public native @Type("void") void replaceText(@Type("UITextRange *") UITextRange range, @Type("NSString *") String text);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/setBaseWritingDirection:forRange:">- (void)setBaseWritingDirection:(UITextWritingDirection)writingDirection forRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setBaseWritingDirection:forRange:") public native @Type("void") void setBaseWritingDirection(@Type("UITextWritingDirection") UITextWritingDirection writingDirection, @Type("UITextRange *") UITextRange range);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/setMarkedText:selectedRange:">- (void)setMarkedText:(NSString *)markedText selectedRange:(NSRange)selectedRange</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setMarkedText:selectedRange:") public native @Type("void") void setMarkedText(@Type("NSString *") String markedText, @Type("NSRange") NSRange selectedRange);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/shouldChangeTextInRange:replacementText:">- (BOOL)shouldChangeTextInRange:(UITextRange *)range replacementText:(NSString *)text</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("shouldChangeTextInRange:replacementText:") public native @Type("BOOL") boolean shouldChangeText(@Type("UITextRange *") UITextRange range, @Type("NSString *") String text);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/unmarkText">- (void)unmarkText</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("unmarkText") public native @Type("void") void unmarkText();
    /*</methods>*/

}
