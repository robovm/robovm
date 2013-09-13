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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html">UITextField Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UITextField /*</name>*/ 
    extends /*<extends>*/ UIControl /*</extends>*/ 
    /*<implements>*/ implements UITextInput, UIKeyInput, UITextInputTraits /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITextField /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UITextField /*</name>*/.class);

    public UITextField(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UITextField(SkipInit skipInit) { super(skipInit); }
    public UITextField() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector adjustsFontSizeToFitWidth = Selector.register("adjustsFontSizeToFitWidth");
    @Bridge private native static boolean objc_isAdjustsFontSizeToFitWidth(UITextField __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isAdjustsFontSizeToFitWidthSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/adjustsFontSizeToFitWidth">@property(nonatomic) BOOL adjustsFontSizeToFitWidth</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isAdjustsFontSizeToFitWidth() {
        if (customClass) { return objc_isAdjustsFontSizeToFitWidthSuper(getSuper(), adjustsFontSizeToFitWidth); } else { return objc_isAdjustsFontSizeToFitWidth(this, adjustsFontSizeToFitWidth); }
    }
    
    private static final Selector setAdjustsFontSizeToFitWidth$ = Selector.register("setAdjustsFontSizeToFitWidth:");
    @Bridge private native static void objc_setAdjustsFontSizeToFitWidth(UITextField __self__, Selector __cmd__, boolean adjustsFontSizeToFitWidth);
    @Bridge private native static void objc_setAdjustsFontSizeToFitWidthSuper(ObjCSuper __super__, Selector __cmd__, boolean adjustsFontSizeToFitWidth);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/adjustsFontSizeToFitWidth">@property(nonatomic) BOOL adjustsFontSizeToFitWidth</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAdjustsFontSizeToFitWidth(boolean adjustsFontSizeToFitWidth) {
        if (customClass) { objc_setAdjustsFontSizeToFitWidthSuper(getSuper(), setAdjustsFontSizeToFitWidth$, adjustsFontSizeToFitWidth); } else { objc_setAdjustsFontSizeToFitWidth(this, setAdjustsFontSizeToFitWidth$, adjustsFontSizeToFitWidth); }
    }
    
    private static final Selector allowsEditingTextAttributes = Selector.register("allowsEditingTextAttributes");
    @Bridge private native static boolean objc_isAllowsEditingTextAttributes(UITextField __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isAllowsEditingTextAttributesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/allowsEditingTextAttributes">@property(nonatomic) BOOL allowsEditingTextAttributes</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean isAllowsEditingTextAttributes() {
        if (customClass) { return objc_isAllowsEditingTextAttributesSuper(getSuper(), allowsEditingTextAttributes); } else { return objc_isAllowsEditingTextAttributes(this, allowsEditingTextAttributes); }
    }
    
    private static final Selector setAllowsEditingTextAttributes$ = Selector.register("setAllowsEditingTextAttributes:");
    @Bridge private native static void objc_setAllowsEditingTextAttributes(UITextField __self__, Selector __cmd__, boolean allowsEditingTextAttributes);
    @Bridge private native static void objc_setAllowsEditingTextAttributesSuper(ObjCSuper __super__, Selector __cmd__, boolean allowsEditingTextAttributes);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/allowsEditingTextAttributes">@property(nonatomic) BOOL allowsEditingTextAttributes</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setAllowsEditingTextAttributes(boolean allowsEditingTextAttributes) {
        if (customClass) { objc_setAllowsEditingTextAttributesSuper(getSuper(), setAllowsEditingTextAttributes$, allowsEditingTextAttributes); } else { objc_setAllowsEditingTextAttributes(this, setAllowsEditingTextAttributes$, allowsEditingTextAttributes); }
    }
    
    private static final Selector attributedPlaceholder = Selector.register("attributedPlaceholder");
    @Bridge private native static NSAttributedString objc_getAttributedPlaceholder(UITextField __self__, Selector __cmd__);
    @Bridge private native static NSAttributedString objc_getAttributedPlaceholderSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/attributedPlaceholder">@property(nonatomic,copy) NSAttributedString *attributedPlaceholder</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedString getAttributedPlaceholder() {
        if (customClass) { return objc_getAttributedPlaceholderSuper(getSuper(), attributedPlaceholder); } else { return objc_getAttributedPlaceholder(this, attributedPlaceholder); }
    }
    
    private static final Selector setAttributedPlaceholder$ = Selector.register("setAttributedPlaceholder:");
    @Bridge private native static void objc_setAttributedPlaceholder(UITextField __self__, Selector __cmd__, NSAttributedString attributedPlaceholder);
    @Bridge private native static void objc_setAttributedPlaceholderSuper(ObjCSuper __super__, Selector __cmd__, NSAttributedString attributedPlaceholder);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/attributedPlaceholder">@property(nonatomic,copy) NSAttributedString *attributedPlaceholder</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setAttributedPlaceholder(NSAttributedString attributedPlaceholder) {
        if (customClass) { objc_setAttributedPlaceholderSuper(getSuper(), setAttributedPlaceholder$, attributedPlaceholder); } else { objc_setAttributedPlaceholder(this, setAttributedPlaceholder$, attributedPlaceholder); }
    }
    
    private static final Selector attributedText = Selector.register("attributedText");
    @Bridge private native static NSAttributedString objc_getAttributedText(UITextField __self__, Selector __cmd__);
    @Bridge private native static NSAttributedString objc_getAttributedTextSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/attributedText">@property(nonatomic,copy) NSAttributedString *attributedText</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedString getAttributedText() {
        if (customClass) { return objc_getAttributedTextSuper(getSuper(), attributedText); } else { return objc_getAttributedText(this, attributedText); }
    }
    
    private static final Selector setAttributedText$ = Selector.register("setAttributedText:");
    @Bridge private native static void objc_setAttributedText(UITextField __self__, Selector __cmd__, NSAttributedString attributedText);
    @Bridge private native static void objc_setAttributedTextSuper(ObjCSuper __super__, Selector __cmd__, NSAttributedString attributedText);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/attributedText">@property(nonatomic,copy) NSAttributedString *attributedText</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setAttributedText(NSAttributedString attributedText) {
        if (customClass) { objc_setAttributedTextSuper(getSuper(), setAttributedText$, attributedText); } else { objc_setAttributedText(this, setAttributedText$, attributedText); }
    }
    
    private static final Selector autocapitalizationType = Selector.register("autocapitalizationType");
    @Bridge private native static UITextAutocapitalizationType objc_getAutocapitalizationType(UITextField __self__, Selector __cmd__);
    @Bridge private native static UITextAutocapitalizationType objc_getAutocapitalizationTypeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocapitalizationType">@property(nonatomic) UITextAutocapitalizationType autocapitalizationType</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITextAutocapitalizationType getAutocapitalizationType() {
        if (customClass) { return objc_getAutocapitalizationTypeSuper(getSuper(), autocapitalizationType); } else { return objc_getAutocapitalizationType(this, autocapitalizationType); }
    }
    
    private static final Selector setAutocapitalizationType$ = Selector.register("setAutocapitalizationType:");
    @Bridge private native static void objc_setAutocapitalizationType(UITextField __self__, Selector __cmd__, UITextAutocapitalizationType autocapitalizationType);
    @Bridge private native static void objc_setAutocapitalizationTypeSuper(ObjCSuper __super__, Selector __cmd__, UITextAutocapitalizationType autocapitalizationType);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocapitalizationType">@property(nonatomic) UITextAutocapitalizationType autocapitalizationType</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAutocapitalizationType(UITextAutocapitalizationType autocapitalizationType) {
        if (customClass) { objc_setAutocapitalizationTypeSuper(getSuper(), setAutocapitalizationType$, autocapitalizationType); } else { objc_setAutocapitalizationType(this, setAutocapitalizationType$, autocapitalizationType); }
    }
    
    private static final Selector autocorrectionType = Selector.register("autocorrectionType");
    @Bridge private native static UITextAutocorrectionType objc_getAutocorrectionType(UITextField __self__, Selector __cmd__);
    @Bridge private native static UITextAutocorrectionType objc_getAutocorrectionTypeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocorrectionType">@property(nonatomic) UITextAutocorrectionType autocorrectionType</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITextAutocorrectionType getAutocorrectionType() {
        if (customClass) { return objc_getAutocorrectionTypeSuper(getSuper(), autocorrectionType); } else { return objc_getAutocorrectionType(this, autocorrectionType); }
    }
    
    private static final Selector setAutocorrectionType$ = Selector.register("setAutocorrectionType:");
    @Bridge private native static void objc_setAutocorrectionType(UITextField __self__, Selector __cmd__, UITextAutocorrectionType autocorrectionType);
    @Bridge private native static void objc_setAutocorrectionTypeSuper(ObjCSuper __super__, Selector __cmd__, UITextAutocorrectionType autocorrectionType);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocorrectionType">@property(nonatomic) UITextAutocorrectionType autocorrectionType</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAutocorrectionType(UITextAutocorrectionType autocorrectionType) {
        if (customClass) { objc_setAutocorrectionTypeSuper(getSuper(), setAutocorrectionType$, autocorrectionType); } else { objc_setAutocorrectionType(this, setAutocorrectionType$, autocorrectionType); }
    }
    
    private static final Selector background = Selector.register("background");
    @Bridge private native static UIImage objc_getBackground(UITextField __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getBackgroundSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/background">@property(nonatomic, retain) UIImage *background</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getBackground() {
        if (customClass) { return objc_getBackgroundSuper(getSuper(), background); } else { return objc_getBackground(this, background); }
    }
    
    private static final Selector setBackground$ = Selector.register("setBackground:");
    @Bridge private native static void objc_setBackground(UITextField __self__, Selector __cmd__, UIImage background);
    @Bridge private native static void objc_setBackgroundSuper(ObjCSuper __super__, Selector __cmd__, UIImage background);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/background">@property(nonatomic, retain) UIImage *background</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setBackground(UIImage background) {
        if (customClass) { objc_setBackgroundSuper(getSuper(), setBackground$, background); } else { objc_setBackground(this, setBackground$, background); }
    }
    
    private static final Selector beginningOfDocument = Selector.register("beginningOfDocument");
    @Bridge private native static UITextPosition objc_getBeginningOfDocument(UITextField __self__, Selector __cmd__);
    @Bridge private native static UITextPosition objc_getBeginningOfDocumentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/beginningOfDocument">@property(nonatomic, readonly) UITextPosition *beginningOfDocument</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextPosition getBeginningOfDocument() {
        if (customClass) { return objc_getBeginningOfDocumentSuper(getSuper(), beginningOfDocument); } else { return objc_getBeginningOfDocument(this, beginningOfDocument); }
    }
    
    private static final Selector borderStyle = Selector.register("borderStyle");
    @Bridge private native static UITextBorderStyle objc_getBorderStyle(UITextField __self__, Selector __cmd__);
    @Bridge private native static UITextBorderStyle objc_getBorderStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/borderStyle">@property(nonatomic) UITextBorderStyle borderStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITextBorderStyle getBorderStyle() {
        if (customClass) { return objc_getBorderStyleSuper(getSuper(), borderStyle); } else { return objc_getBorderStyle(this, borderStyle); }
    }
    
    private static final Selector setBorderStyle$ = Selector.register("setBorderStyle:");
    @Bridge private native static void objc_setBorderStyle(UITextField __self__, Selector __cmd__, UITextBorderStyle borderStyle);
    @Bridge private native static void objc_setBorderStyleSuper(ObjCSuper __super__, Selector __cmd__, UITextBorderStyle borderStyle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/borderStyle">@property(nonatomic) UITextBorderStyle borderStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setBorderStyle(UITextBorderStyle borderStyle) {
        if (customClass) { objc_setBorderStyleSuper(getSuper(), setBorderStyle$, borderStyle); } else { objc_setBorderStyle(this, setBorderStyle$, borderStyle); }
    }
    
    private static final Selector clearButtonMode = Selector.register("clearButtonMode");
    @Bridge private native static UITextFieldViewMode objc_getClearButtonMode(UITextField __self__, Selector __cmd__);
    @Bridge private native static UITextFieldViewMode objc_getClearButtonModeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/clearButtonMode">@property(nonatomic) UITextFieldViewMode clearButtonMode</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITextFieldViewMode getClearButtonMode() {
        if (customClass) { return objc_getClearButtonModeSuper(getSuper(), clearButtonMode); } else { return objc_getClearButtonMode(this, clearButtonMode); }
    }
    
    private static final Selector setClearButtonMode$ = Selector.register("setClearButtonMode:");
    @Bridge private native static void objc_setClearButtonMode(UITextField __self__, Selector __cmd__, UITextFieldViewMode clearButtonMode);
    @Bridge private native static void objc_setClearButtonModeSuper(ObjCSuper __super__, Selector __cmd__, UITextFieldViewMode clearButtonMode);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/clearButtonMode">@property(nonatomic) UITextFieldViewMode clearButtonMode</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setClearButtonMode(UITextFieldViewMode clearButtonMode) {
        if (customClass) { objc_setClearButtonModeSuper(getSuper(), setClearButtonMode$, clearButtonMode); } else { objc_setClearButtonMode(this, setClearButtonMode$, clearButtonMode); }
    }
    
    private static final Selector clearsOnBeginEditing = Selector.register("clearsOnBeginEditing");
    @Bridge private native static boolean objc_isClearsOnBeginEditing(UITextField __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isClearsOnBeginEditingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/clearsOnBeginEditing">@property(nonatomic) BOOL clearsOnBeginEditing</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isClearsOnBeginEditing() {
        if (customClass) { return objc_isClearsOnBeginEditingSuper(getSuper(), clearsOnBeginEditing); } else { return objc_isClearsOnBeginEditing(this, clearsOnBeginEditing); }
    }
    
    private static final Selector setClearsOnBeginEditing$ = Selector.register("setClearsOnBeginEditing:");
    @Bridge private native static void objc_setClearsOnBeginEditing(UITextField __self__, Selector __cmd__, boolean clearsOnBeginEditing);
    @Bridge private native static void objc_setClearsOnBeginEditingSuper(ObjCSuper __super__, Selector __cmd__, boolean clearsOnBeginEditing);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/clearsOnBeginEditing">@property(nonatomic) BOOL clearsOnBeginEditing</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setClearsOnBeginEditing(boolean clearsOnBeginEditing) {
        if (customClass) { objc_setClearsOnBeginEditingSuper(getSuper(), setClearsOnBeginEditing$, clearsOnBeginEditing); } else { objc_setClearsOnBeginEditing(this, setClearsOnBeginEditing$, clearsOnBeginEditing); }
    }
    
    private static final Selector clearsOnInsertion = Selector.register("clearsOnInsertion");
    @Bridge private native static boolean objc_isClearsOnInsertion(UITextField __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isClearsOnInsertionSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/clearsOnInsertion">@property(nonatomic) BOOL clearsOnInsertion</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean isClearsOnInsertion() {
        if (customClass) { return objc_isClearsOnInsertionSuper(getSuper(), clearsOnInsertion); } else { return objc_isClearsOnInsertion(this, clearsOnInsertion); }
    }
    
    private static final Selector setClearsOnInsertion$ = Selector.register("setClearsOnInsertion:");
    @Bridge private native static void objc_setClearsOnInsertion(UITextField __self__, Selector __cmd__, boolean clearsOnInsertion);
    @Bridge private native static void objc_setClearsOnInsertionSuper(ObjCSuper __super__, Selector __cmd__, boolean clearsOnInsertion);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/clearsOnInsertion">@property(nonatomic) BOOL clearsOnInsertion</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setClearsOnInsertion(boolean clearsOnInsertion) {
        if (customClass) { objc_setClearsOnInsertionSuper(getSuper(), setClearsOnInsertion$, clearsOnInsertion); } else { objc_setClearsOnInsertion(this, setClearsOnInsertion$, clearsOnInsertion); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UITextFieldDelegate objc_getDelegate(UITextField __self__, Selector __cmd__);
    @Bridge private native static UITextFieldDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/delegate">@property(nonatomic, assign) id&amp;lt;UITextFieldDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITextFieldDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UITextField __self__, Selector __cmd__, UITextFieldDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UITextFieldDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/delegate">@property(nonatomic, assign) id&amp;lt;UITextFieldDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDelegate(UITextFieldDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector disabledBackground = Selector.register("disabledBackground");
    @Bridge private native static UIImage objc_getDisabledBackground(UITextField __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getDisabledBackgroundSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/disabledBackground">@property(nonatomic, retain) UIImage *disabledBackground</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getDisabledBackground() {
        if (customClass) { return objc_getDisabledBackgroundSuper(getSuper(), disabledBackground); } else { return objc_getDisabledBackground(this, disabledBackground); }
    }
    
    private static final Selector setDisabledBackground$ = Selector.register("setDisabledBackground:");
    @Bridge private native static void objc_setDisabledBackground(UITextField __self__, Selector __cmd__, UIImage disabledBackground);
    @Bridge private native static void objc_setDisabledBackgroundSuper(ObjCSuper __super__, Selector __cmd__, UIImage disabledBackground);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/disabledBackground">@property(nonatomic, retain) UIImage *disabledBackground</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDisabledBackground(UIImage disabledBackground) {
        if (customClass) { objc_setDisabledBackgroundSuper(getSuper(), setDisabledBackground$, disabledBackground); } else { objc_setDisabledBackground(this, setDisabledBackground$, disabledBackground); }
    }
    
    private static final Selector isEditing = Selector.register("isEditing");
    @Bridge private native static boolean objc_isEditing(UITextField __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isEditingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/editing">@property(nonatomic, readonly, getter=isEditing) BOOL editing</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isEditing() {
        if (customClass) { return objc_isEditingSuper(getSuper(), isEditing); } else { return objc_isEditing(this, isEditing); }
    }
    
    private static final Selector enablesReturnKeyAutomatically = Selector.register("enablesReturnKeyAutomatically");
    @Bridge private native static boolean objc_isEnablesReturnKeyAutomatically(UITextField __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isEnablesReturnKeyAutomaticallySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/enablesReturnKeyAutomatically">@property(nonatomic) BOOL enablesReturnKeyAutomatically</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isEnablesReturnKeyAutomatically() {
        if (customClass) { return objc_isEnablesReturnKeyAutomaticallySuper(getSuper(), enablesReturnKeyAutomatically); } else { return objc_isEnablesReturnKeyAutomatically(this, enablesReturnKeyAutomatically); }
    }
    
    private static final Selector setEnablesReturnKeyAutomatically$ = Selector.register("setEnablesReturnKeyAutomatically:");
    @Bridge private native static void objc_setEnablesReturnKeyAutomatically(UITextField __self__, Selector __cmd__, boolean enablesReturnKeyAutomatically);
    @Bridge private native static void objc_setEnablesReturnKeyAutomaticallySuper(ObjCSuper __super__, Selector __cmd__, boolean enablesReturnKeyAutomatically);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/enablesReturnKeyAutomatically">@property(nonatomic) BOOL enablesReturnKeyAutomatically</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setEnablesReturnKeyAutomatically(boolean enablesReturnKeyAutomatically) {
        if (customClass) { objc_setEnablesReturnKeyAutomaticallySuper(getSuper(), setEnablesReturnKeyAutomatically$, enablesReturnKeyAutomatically); } else { objc_setEnablesReturnKeyAutomatically(this, setEnablesReturnKeyAutomatically$, enablesReturnKeyAutomatically); }
    }
    
    private static final Selector endOfDocument = Selector.register("endOfDocument");
    @Bridge private native static UITextPosition objc_getEndOfDocument(UITextField __self__, Selector __cmd__);
    @Bridge private native static UITextPosition objc_getEndOfDocumentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/endOfDocument">@property(nonatomic, readonly) UITextPosition *endOfDocument</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextPosition getEndOfDocument() {
        if (customClass) { return objc_getEndOfDocumentSuper(getSuper(), endOfDocument); } else { return objc_getEndOfDocument(this, endOfDocument); }
    }
    
    private static final Selector font = Selector.register("font");
    @Bridge private native static UIFont objc_getFont(UITextField __self__, Selector __cmd__);
    @Bridge private native static UIFont objc_getFontSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/font">@property(nonatomic, retain) UIFont *font</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIFont getFont() {
        if (customClass) { return objc_getFontSuper(getSuper(), font); } else { return objc_getFont(this, font); }
    }
    
    private static final Selector setFont$ = Selector.register("setFont:");
    @Bridge private native static void objc_setFont(UITextField __self__, Selector __cmd__, UIFont font);
    @Bridge private native static void objc_setFontSuper(ObjCSuper __super__, Selector __cmd__, UIFont font);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/font">@property(nonatomic, retain) UIFont *font</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setFont(UIFont font) {
        if (customClass) { objc_setFontSuper(getSuper(), setFont$, font); } else { objc_setFont(this, setFont$, font); }
    }
    
    private static final Selector inputAccessoryView = Selector.register("inputAccessoryView");
    @Bridge private native static UIView objc_getInputAccessoryView(UITextField __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getInputAccessoryViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/inputAccessoryView">@property (readwrite, retain) UIView *inputAccessoryView</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIView getInputAccessoryView() {
        if (customClass) { return objc_getInputAccessoryViewSuper(getSuper(), inputAccessoryView); } else { return objc_getInputAccessoryView(this, inputAccessoryView); }
    }
    
    private static final Selector setInputAccessoryView$ = Selector.register("setInputAccessoryView:");
    @Bridge private native static void objc_setInputAccessoryView(UITextField __self__, Selector __cmd__, UIView inputAccessoryView);
    @Bridge private native static void objc_setInputAccessoryViewSuper(ObjCSuper __super__, Selector __cmd__, UIView inputAccessoryView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/inputAccessoryView">@property (readwrite, retain) UIView *inputAccessoryView</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setInputAccessoryView(UIView inputAccessoryView) {
        if (customClass) { objc_setInputAccessoryViewSuper(getSuper(), setInputAccessoryView$, inputAccessoryView); } else { objc_setInputAccessoryView(this, setInputAccessoryView$, inputAccessoryView); }
    }
    
    private static final Selector inputDelegate = Selector.register("inputDelegate");
    @Bridge private native static UITextInputDelegate objc_getInputDelegate(UITextField __self__, Selector __cmd__);
    @Bridge private native static UITextInputDelegate objc_getInputDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/inputDelegate">@property(nonatomic, assign) id&amp;lt;UITextInputDelegate&amp;gt; inputDelegate</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextInputDelegate getInputDelegate() {
        if (customClass) { return objc_getInputDelegateSuper(getSuper(), inputDelegate); } else { return objc_getInputDelegate(this, inputDelegate); }
    }
    
    private static final Selector setInputDelegate$ = Selector.register("setInputDelegate:");
    @Bridge private native static void objc_setInputDelegate(UITextField __self__, Selector __cmd__, UITextInputDelegate inputDelegate);
    @Bridge private native static void objc_setInputDelegateSuper(ObjCSuper __super__, Selector __cmd__, UITextInputDelegate inputDelegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/inputDelegate">@property(nonatomic, assign) id&amp;lt;UITextInputDelegate&amp;gt; inputDelegate</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setInputDelegate(UITextInputDelegate inputDelegate) {
        if (customClass) { objc_setInputDelegateSuper(getSuper(), setInputDelegate$, inputDelegate); } else { objc_setInputDelegate(this, setInputDelegate$, inputDelegate); }
    }
    
    private static final Selector inputView = Selector.register("inputView");
    @Bridge private native static UIView objc_getInputView(UITextField __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getInputViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/inputView">@property (readwrite, retain) UIView *inputView</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIView getInputView() {
        if (customClass) { return objc_getInputViewSuper(getSuper(), inputView); } else { return objc_getInputView(this, inputView); }
    }
    
    private static final Selector setInputView$ = Selector.register("setInputView:");
    @Bridge private native static void objc_setInputView(UITextField __self__, Selector __cmd__, UIView inputView);
    @Bridge private native static void objc_setInputViewSuper(ObjCSuper __super__, Selector __cmd__, UIView inputView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/inputView">@property (readwrite, retain) UIView *inputView</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setInputView(UIView inputView) {
        if (customClass) { objc_setInputViewSuper(getSuper(), setInputView$, inputView); } else { objc_setInputView(this, setInputView$, inputView); }
    }
    
    private static final Selector keyboardAppearance = Selector.register("keyboardAppearance");
    @Bridge private native static UIKeyboardAppearance objc_getKeyboardAppearance(UITextField __self__, Selector __cmd__);
    @Bridge private native static UIKeyboardAppearance objc_getKeyboardAppearanceSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardAppearance">@property(nonatomic) UIKeyboardAppearance keyboardAppearance</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIKeyboardAppearance getKeyboardAppearance() {
        if (customClass) { return objc_getKeyboardAppearanceSuper(getSuper(), keyboardAppearance); } else { return objc_getKeyboardAppearance(this, keyboardAppearance); }
    }
    
    private static final Selector setKeyboardAppearance$ = Selector.register("setKeyboardAppearance:");
    @Bridge private native static void objc_setKeyboardAppearance(UITextField __self__, Selector __cmd__, UIKeyboardAppearance keyboardAppearance);
    @Bridge private native static void objc_setKeyboardAppearanceSuper(ObjCSuper __super__, Selector __cmd__, UIKeyboardAppearance keyboardAppearance);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardAppearance">@property(nonatomic) UIKeyboardAppearance keyboardAppearance</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setKeyboardAppearance(UIKeyboardAppearance keyboardAppearance) {
        if (customClass) { objc_setKeyboardAppearanceSuper(getSuper(), setKeyboardAppearance$, keyboardAppearance); } else { objc_setKeyboardAppearance(this, setKeyboardAppearance$, keyboardAppearance); }
    }
    
    private static final Selector keyboardType = Selector.register("keyboardType");
    @Bridge private native static UIKeyboardType objc_getKeyboardType(UITextField __self__, Selector __cmd__);
    @Bridge private native static UIKeyboardType objc_getKeyboardTypeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardType">@property(nonatomic) UIKeyboardType keyboardType</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIKeyboardType getKeyboardType() {
        if (customClass) { return objc_getKeyboardTypeSuper(getSuper(), keyboardType); } else { return objc_getKeyboardType(this, keyboardType); }
    }
    
    private static final Selector setKeyboardType$ = Selector.register("setKeyboardType:");
    @Bridge private native static void objc_setKeyboardType(UITextField __self__, Selector __cmd__, UIKeyboardType keyboardType);
    @Bridge private native static void objc_setKeyboardTypeSuper(ObjCSuper __super__, Selector __cmd__, UIKeyboardType keyboardType);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardType">@property(nonatomic) UIKeyboardType keyboardType</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setKeyboardType(UIKeyboardType keyboardType) {
        if (customClass) { objc_setKeyboardTypeSuper(getSuper(), setKeyboardType$, keyboardType); } else { objc_setKeyboardType(this, setKeyboardType$, keyboardType); }
    }
    
    private static final Selector leftView = Selector.register("leftView");
    @Bridge private native static UIView objc_getLeftView(UITextField __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getLeftViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/leftView">@property(nonatomic, retain) UIView *leftView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getLeftView() {
        if (customClass) { return objc_getLeftViewSuper(getSuper(), leftView); } else { return objc_getLeftView(this, leftView); }
    }
    
    private static final Selector setLeftView$ = Selector.register("setLeftView:");
    @Bridge private native static void objc_setLeftView(UITextField __self__, Selector __cmd__, UIView leftView);
    @Bridge private native static void objc_setLeftViewSuper(ObjCSuper __super__, Selector __cmd__, UIView leftView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/leftView">@property(nonatomic, retain) UIView *leftView</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setLeftView(UIView leftView) {
        if (customClass) { objc_setLeftViewSuper(getSuper(), setLeftView$, leftView); } else { objc_setLeftView(this, setLeftView$, leftView); }
    }
    
    private static final Selector leftViewMode = Selector.register("leftViewMode");
    @Bridge private native static UITextFieldViewMode objc_getLeftViewMode(UITextField __self__, Selector __cmd__);
    @Bridge private native static UITextFieldViewMode objc_getLeftViewModeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/leftViewMode">@property(nonatomic) UITextFieldViewMode leftViewMode</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITextFieldViewMode getLeftViewMode() {
        if (customClass) { return objc_getLeftViewModeSuper(getSuper(), leftViewMode); } else { return objc_getLeftViewMode(this, leftViewMode); }
    }
    
    private static final Selector setLeftViewMode$ = Selector.register("setLeftViewMode:");
    @Bridge private native static void objc_setLeftViewMode(UITextField __self__, Selector __cmd__, UITextFieldViewMode leftViewMode);
    @Bridge private native static void objc_setLeftViewModeSuper(ObjCSuper __super__, Selector __cmd__, UITextFieldViewMode leftViewMode);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/leftViewMode">@property(nonatomic) UITextFieldViewMode leftViewMode</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setLeftViewMode(UITextFieldViewMode leftViewMode) {
        if (customClass) { objc_setLeftViewModeSuper(getSuper(), setLeftViewMode$, leftViewMode); } else { objc_setLeftViewMode(this, setLeftViewMode$, leftViewMode); }
    }
    
    private static final Selector markedTextRange = Selector.register("markedTextRange");
    @Bridge private native static UITextRange objc_getMarkedTextRange(UITextField __self__, Selector __cmd__);
    @Bridge private native static UITextRange objc_getMarkedTextRangeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/markedTextRange">@property(nonatomic, readonly) UITextRange *markedTextRange</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextRange getMarkedTextRange() {
        if (customClass) { return objc_getMarkedTextRangeSuper(getSuper(), markedTextRange); } else { return objc_getMarkedTextRange(this, markedTextRange); }
    }
    
    private static final Selector markedTextStyle = Selector.register("markedTextStyle");
    @Bridge private native static NSDictionary objc_getMarkedTextStyle(UITextField __self__, Selector __cmd__);
    @Bridge private native static NSDictionary objc_getMarkedTextStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/markedTextStyle">@property(nonatomic, copy) NSDictionary *markedTextStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    public NSDictionary getMarkedTextStyle() {
        if (customClass) { return objc_getMarkedTextStyleSuper(getSuper(), markedTextStyle); } else { return objc_getMarkedTextStyle(this, markedTextStyle); }
    }
    
    private static final Selector setMarkedTextStyle$ = Selector.register("setMarkedTextStyle:");
    @Bridge private native static void objc_setMarkedTextStyle(UITextField __self__, Selector __cmd__, NSDictionary markedTextStyle);
    @Bridge private native static void objc_setMarkedTextStyleSuper(ObjCSuper __super__, Selector __cmd__, NSDictionary markedTextStyle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/markedTextStyle">@property(nonatomic, copy) NSDictionary *markedTextStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setMarkedTextStyle(NSDictionary markedTextStyle) {
        if (customClass) { objc_setMarkedTextStyleSuper(getSuper(), setMarkedTextStyle$, markedTextStyle); } else { objc_setMarkedTextStyle(this, setMarkedTextStyle$, markedTextStyle); }
    }
    
    private static final Selector minimumFontSize = Selector.register("minimumFontSize");
    @Bridge private native static float objc_getMinimumFontSize(UITextField __self__, Selector __cmd__);
    @Bridge private native static float objc_getMinimumFontSizeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/minimumFontSize">@property(nonatomic) CGFloat minimumFontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getMinimumFontSize() {
        if (customClass) { return objc_getMinimumFontSizeSuper(getSuper(), minimumFontSize); } else { return objc_getMinimumFontSize(this, minimumFontSize); }
    }
    
    private static final Selector setMinimumFontSize$ = Selector.register("setMinimumFontSize:");
    @Bridge private native static void objc_setMinimumFontSize(UITextField __self__, Selector __cmd__, float minimumFontSize);
    @Bridge private native static void objc_setMinimumFontSizeSuper(ObjCSuper __super__, Selector __cmd__, float minimumFontSize);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/minimumFontSize">@property(nonatomic) CGFloat minimumFontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMinimumFontSize(float minimumFontSize) {
        if (customClass) { objc_setMinimumFontSizeSuper(getSuper(), setMinimumFontSize$, minimumFontSize); } else { objc_setMinimumFontSize(this, setMinimumFontSize$, minimumFontSize); }
    }
    
    private static final Selector placeholder = Selector.register("placeholder");
    @Bridge private native static String objc_getPlaceholder(UITextField __self__, Selector __cmd__);
    @Bridge private native static String objc_getPlaceholderSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/placeholder">@property(nonatomic, copy) NSString *placeholder</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getPlaceholder() {
        if (customClass) { return objc_getPlaceholderSuper(getSuper(), placeholder); } else { return objc_getPlaceholder(this, placeholder); }
    }
    
    private static final Selector setPlaceholder$ = Selector.register("setPlaceholder:");
    @Bridge private native static void objc_setPlaceholder(UITextField __self__, Selector __cmd__, String placeholder);
    @Bridge private native static void objc_setPlaceholderSuper(ObjCSuper __super__, Selector __cmd__, String placeholder);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/placeholder">@property(nonatomic, copy) NSString *placeholder</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setPlaceholder(String placeholder) {
        if (customClass) { objc_setPlaceholderSuper(getSuper(), setPlaceholder$, placeholder); } else { objc_setPlaceholder(this, setPlaceholder$, placeholder); }
    }
    
    private static final Selector returnKeyType = Selector.register("returnKeyType");
    @Bridge private native static UIReturnKeyType objc_getReturnKeyType(UITextField __self__, Selector __cmd__);
    @Bridge private native static UIReturnKeyType objc_getReturnKeyTypeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/returnKeyType">@property(nonatomic) UIReturnKeyType returnKeyType</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIReturnKeyType getReturnKeyType() {
        if (customClass) { return objc_getReturnKeyTypeSuper(getSuper(), returnKeyType); } else { return objc_getReturnKeyType(this, returnKeyType); }
    }
    
    private static final Selector setReturnKeyType$ = Selector.register("setReturnKeyType:");
    @Bridge private native static void objc_setReturnKeyType(UITextField __self__, Selector __cmd__, UIReturnKeyType returnKeyType);
    @Bridge private native static void objc_setReturnKeyTypeSuper(ObjCSuper __super__, Selector __cmd__, UIReturnKeyType returnKeyType);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/returnKeyType">@property(nonatomic) UIReturnKeyType returnKeyType</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setReturnKeyType(UIReturnKeyType returnKeyType) {
        if (customClass) { objc_setReturnKeyTypeSuper(getSuper(), setReturnKeyType$, returnKeyType); } else { objc_setReturnKeyType(this, setReturnKeyType$, returnKeyType); }
    }
    
    private static final Selector rightView = Selector.register("rightView");
    @Bridge private native static UIView objc_getRightView(UITextField __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getRightViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/rightView">@property(nonatomic, retain) UIView *rightView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getRightView() {
        if (customClass) { return objc_getRightViewSuper(getSuper(), rightView); } else { return objc_getRightView(this, rightView); }
    }
    
    private static final Selector setRightView$ = Selector.register("setRightView:");
    @Bridge private native static void objc_setRightView(UITextField __self__, Selector __cmd__, UIView rightView);
    @Bridge private native static void objc_setRightViewSuper(ObjCSuper __super__, Selector __cmd__, UIView rightView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/rightView">@property(nonatomic, retain) UIView *rightView</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setRightView(UIView rightView) {
        if (customClass) { objc_setRightViewSuper(getSuper(), setRightView$, rightView); } else { objc_setRightView(this, setRightView$, rightView); }
    }
    
    private static final Selector rightViewMode = Selector.register("rightViewMode");
    @Bridge private native static UITextFieldViewMode objc_getRightViewMode(UITextField __self__, Selector __cmd__);
    @Bridge private native static UITextFieldViewMode objc_getRightViewModeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/rightViewMode">@property(nonatomic) UITextFieldViewMode rightViewMode</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITextFieldViewMode getRightViewMode() {
        if (customClass) { return objc_getRightViewModeSuper(getSuper(), rightViewMode); } else { return objc_getRightViewMode(this, rightViewMode); }
    }
    
    private static final Selector setRightViewMode$ = Selector.register("setRightViewMode:");
    @Bridge private native static void objc_setRightViewMode(UITextField __self__, Selector __cmd__, UITextFieldViewMode rightViewMode);
    @Bridge private native static void objc_setRightViewModeSuper(ObjCSuper __super__, Selector __cmd__, UITextFieldViewMode rightViewMode);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/rightViewMode">@property(nonatomic) UITextFieldViewMode rightViewMode</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setRightViewMode(UITextFieldViewMode rightViewMode) {
        if (customClass) { objc_setRightViewModeSuper(getSuper(), setRightViewMode$, rightViewMode); } else { objc_setRightViewMode(this, setRightViewMode$, rightViewMode); }
    }
    
    private static final Selector isSecureTextEntry = Selector.register("isSecureTextEntry");
    @Bridge private native static boolean objc_isSecureTextEntry(UITextField __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isSecureTextEntrySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/secureTextEntry">@property(nonatomic, getter=isSecureTextEntry) BOOL secureTextEntry</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isSecureTextEntry() {
        if (customClass) { return objc_isSecureTextEntrySuper(getSuper(), isSecureTextEntry); } else { return objc_isSecureTextEntry(this, isSecureTextEntry); }
    }
    
    private static final Selector setSecureTextEntry$ = Selector.register("setSecureTextEntry:");
    @Bridge private native static void objc_setSecureTextEntry(UITextField __self__, Selector __cmd__, boolean secureTextEntry);
    @Bridge private native static void objc_setSecureTextEntrySuper(ObjCSuper __super__, Selector __cmd__, boolean secureTextEntry);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/secureTextEntry">@property(nonatomic, getter=isSecureTextEntry) BOOL secureTextEntry</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSecureTextEntry(boolean secureTextEntry) {
        if (customClass) { objc_setSecureTextEntrySuper(getSuper(), setSecureTextEntry$, secureTextEntry); } else { objc_setSecureTextEntry(this, setSecureTextEntry$, secureTextEntry); }
    }
    
    private static final Selector selectedTextRange = Selector.register("selectedTextRange");
    @Bridge private native static UITextRange objc_getSelectedTextRange(UITextField __self__, Selector __cmd__);
    @Bridge private native static UITextRange objc_getSelectedTextRangeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectedTextRange">@property(readwrite, copy) UITextRange *selectedTextRange</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextRange getSelectedTextRange() {
        if (customClass) { return objc_getSelectedTextRangeSuper(getSuper(), selectedTextRange); } else { return objc_getSelectedTextRange(this, selectedTextRange); }
    }
    
    private static final Selector setSelectedTextRange$ = Selector.register("setSelectedTextRange:");
    @Bridge private native static void objc_setSelectedTextRange(UITextField __self__, Selector __cmd__, UITextRange selectedTextRange);
    @Bridge private native static void objc_setSelectedTextRangeSuper(ObjCSuper __super__, Selector __cmd__, UITextRange selectedTextRange);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectedTextRange">@property(readwrite, copy) UITextRange *selectedTextRange</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setSelectedTextRange(UITextRange selectedTextRange) {
        if (customClass) { objc_setSelectedTextRangeSuper(getSuper(), setSelectedTextRange$, selectedTextRange); } else { objc_setSelectedTextRange(this, setSelectedTextRange$, selectedTextRange); }
    }
    
    private static final Selector selectionAffinity = Selector.register("selectionAffinity");
    @Bridge private native static UITextStorageDirection objc_getSelectionAffinity(UITextField __self__, Selector __cmd__);
    @Bridge private native static UITextStorageDirection objc_getSelectionAffinitySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectionAffinity">@property(nonatomic) UITextStorageDirection selectionAffinity</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextStorageDirection getSelectionAffinity() {
        if (customClass) { return objc_getSelectionAffinitySuper(getSuper(), selectionAffinity); } else { return objc_getSelectionAffinity(this, selectionAffinity); }
    }
    
    private static final Selector setSelectionAffinity$ = Selector.register("setSelectionAffinity:");
    @Bridge private native static void objc_setSelectionAffinity(UITextField __self__, Selector __cmd__, UITextStorageDirection selectionAffinity);
    @Bridge private native static void objc_setSelectionAffinitySuper(ObjCSuper __super__, Selector __cmd__, UITextStorageDirection selectionAffinity);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectionAffinity">@property(nonatomic) UITextStorageDirection selectionAffinity</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setSelectionAffinity(UITextStorageDirection selectionAffinity) {
        if (customClass) { objc_setSelectionAffinitySuper(getSuper(), setSelectionAffinity$, selectionAffinity); } else { objc_setSelectionAffinity(this, setSelectionAffinity$, selectionAffinity); }
    }
    
    private static final Selector spellCheckingType = Selector.register("spellCheckingType");
    @Bridge private native static UITextSpellCheckingType objc_getSpellCheckingType(UITextField __self__, Selector __cmd__);
    @Bridge private native static UITextSpellCheckingType objc_getSpellCheckingTypeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/spellCheckingType">@property(nonatomic) UITextSpellCheckingType spellCheckingType</a>
     * @since Available in iOS 5.0 and later.
     */
    public UITextSpellCheckingType getSpellCheckingType() {
        if (customClass) { return objc_getSpellCheckingTypeSuper(getSuper(), spellCheckingType); } else { return objc_getSpellCheckingType(this, spellCheckingType); }
    }
    
    private static final Selector setSpellCheckingType$ = Selector.register("setSpellCheckingType:");
    @Bridge private native static void objc_setSpellCheckingType(UITextField __self__, Selector __cmd__, UITextSpellCheckingType spellCheckingType);
    @Bridge private native static void objc_setSpellCheckingTypeSuper(ObjCSuper __super__, Selector __cmd__, UITextSpellCheckingType spellCheckingType);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/spellCheckingType">@property(nonatomic) UITextSpellCheckingType spellCheckingType</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setSpellCheckingType(UITextSpellCheckingType spellCheckingType) {
        if (customClass) { objc_setSpellCheckingTypeSuper(getSuper(), setSpellCheckingType$, spellCheckingType); } else { objc_setSpellCheckingType(this, setSpellCheckingType$, spellCheckingType); }
    }
    
    private static final Selector text = Selector.register("text");
    @Bridge private native static String objc_getText(UITextField __self__, Selector __cmd__);
    @Bridge private native static String objc_getTextSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getText() {
        if (customClass) { return objc_getTextSuper(getSuper(), text); } else { return objc_getText(this, text); }
    }
    
    private static final Selector setText$ = Selector.register("setText:");
    @Bridge private native static void objc_setText(UITextField __self__, Selector __cmd__, String text);
    @Bridge private native static void objc_setTextSuper(ObjCSuper __super__, Selector __cmd__, String text);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setText(String text) {
        if (customClass) { objc_setTextSuper(getSuper(), setText$, text); } else { objc_setText(this, setText$, text); }
    }
    
    private static final Selector textAlignment = Selector.register("textAlignment");
    @Bridge private native static NSTextAlignment objc_getTextAlignment(UITextField __self__, Selector __cmd__);
    @Bridge private native static NSTextAlignment objc_getTextAlignmentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/textAlignment">@property(nonatomic) NSTextAlignment textAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSTextAlignment getTextAlignment() {
        if (customClass) { return objc_getTextAlignmentSuper(getSuper(), textAlignment); } else { return objc_getTextAlignment(this, textAlignment); }
    }
    
    private static final Selector setTextAlignment$ = Selector.register("setTextAlignment:");
    @Bridge private native static void objc_setTextAlignment(UITextField __self__, Selector __cmd__, NSTextAlignment textAlignment);
    @Bridge private native static void objc_setTextAlignmentSuper(ObjCSuper __super__, Selector __cmd__, NSTextAlignment textAlignment);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/textAlignment">@property(nonatomic) NSTextAlignment textAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTextAlignment(NSTextAlignment textAlignment) {
        if (customClass) { objc_setTextAlignmentSuper(getSuper(), setTextAlignment$, textAlignment); } else { objc_setTextAlignment(this, setTextAlignment$, textAlignment); }
    }
    
    private static final Selector textColor = Selector.register("textColor");
    @Bridge private native static UIColor objc_getTextColor(UITextField __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getTextColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/textColor">@property(nonatomic, retain) UIColor *textColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor getTextColor() {
        if (customClass) { return objc_getTextColorSuper(getSuper(), textColor); } else { return objc_getTextColor(this, textColor); }
    }
    
    private static final Selector setTextColor$ = Selector.register("setTextColor:");
    @Bridge private native static void objc_setTextColor(UITextField __self__, Selector __cmd__, UIColor textColor);
    @Bridge private native static void objc_setTextColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor textColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/textColor">@property(nonatomic, retain) UIColor *textColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTextColor(UIColor textColor) {
        if (customClass) { objc_setTextColorSuper(getSuper(), setTextColor$, textColor); } else { objc_setTextColor(this, setTextColor$, textColor); }
    }
    
    private static final Selector textInputView = Selector.register("textInputView");
    @Bridge private native static UIView objc_getTextInputView(UITextField __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getTextInputViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/textInputView">@property(nonatomic, readonly) UIView *textInputView</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIView getTextInputView() {
        if (customClass) { return objc_getTextInputViewSuper(getSuper(), textInputView); } else { return objc_getTextInputView(this, textInputView); }
    }
    
    private static final Selector tokenizer = Selector.register("tokenizer");
    @Bridge private native static UITextInputTokenizer objc_getTokenizer(UITextField __self__, Selector __cmd__);
    @Bridge private native static UITextInputTokenizer objc_getTokenizerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/tokenizer">@property(nonatomic, readonly) id&amp;lt;UITextInputTokenizer&amp;gt; tokenizer</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextInputTokenizer getTokenizer() {
        if (customClass) { return objc_getTokenizerSuper(getSuper(), tokenizer); } else { return objc_getTokenizer(this, tokenizer); }
    }
    
    private static final Selector typingAttributes = Selector.register("typingAttributes");
    @Bridge private native static NSDictionary objc_getTypingAttributes(UITextField __self__, Selector __cmd__);
    @Bridge private native static NSDictionary objc_getTypingAttributesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/typingAttributes">@property(nonatomic,copy) NSDictionary *typingAttributes;</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSDictionary getTypingAttributes() {
        if (customClass) { return objc_getTypingAttributesSuper(getSuper(), typingAttributes); } else { return objc_getTypingAttributes(this, typingAttributes); }
    }
    
    private static final Selector setTypingAttributes$ = Selector.register("setTypingAttributes:");
    @Bridge private native static void objc_setTypingAttributes(UITextField __self__, Selector __cmd__, NSDictionary typingAttributes);
    @Bridge private native static void objc_setTypingAttributesSuper(ObjCSuper __super__, Selector __cmd__, NSDictionary typingAttributes);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instp/UITextField/typingAttributes">@property(nonatomic,copy) NSDictionary *typingAttributes;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setTypingAttributes(NSDictionary typingAttributes) {
        if (customClass) { objc_setTypingAttributesSuper(getSuper(), setTypingAttributes$, typingAttributes); } else { objc_setTypingAttributes(this, setTypingAttributes$, typingAttributes); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector clearButtonRectForBounds$ = Selector.register("clearButtonRectForBounds:");
    @Bridge private native static @ByVal CGRect objc_clearButtonRect(UITextField __self__, Selector __cmd__, @ByVal CGRect bounds);
    @Bridge private native static @ByVal CGRect objc_clearButtonRectSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect bounds);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instm/UITextField/clearButtonRectForBounds:">- (CGRect)clearButtonRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect clearButtonRect(CGRect bounds) {
        if (customClass) { return objc_clearButtonRectSuper(getSuper(), clearButtonRectForBounds$, bounds); } else { return objc_clearButtonRect(this, clearButtonRectForBounds$, bounds); }
    }
    
    private static final Selector comparePosition$toPosition$ = Selector.register("comparePosition:toPosition:");
    @Bridge private native static NSComparisonResult objc_comparePositions(UITextField __self__, Selector __cmd__, UITextPosition position, UITextPosition other);
    @Bridge private native static NSComparisonResult objc_comparePositionsSuper(ObjCSuper __super__, Selector __cmd__, UITextPosition position, UITextPosition other);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/comparePosition:toPosition:">- (NSComparisonResult)comparePosition:(UITextPosition *)position toPosition:(UITextPosition *)other</a>
     * @since Available in iOS 3.2 and later.
     */
    public NSComparisonResult comparePositions(UITextPosition position, UITextPosition other) {
        if (customClass) { return objc_comparePositionsSuper(getSuper(), comparePosition$toPosition$, position, other); } else { return objc_comparePositions(this, comparePosition$toPosition$, position, other); }
    }
    
    private static final Selector deleteBackward = Selector.register("deleteBackward");
    @Bridge private native static void objc_deleteBackward(UITextField __self__, Selector __cmd__);
    @Bridge private native static void objc_deleteBackwardSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIKeyInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIKeyInput/deleteBackward">- (void)deleteBackward</a>
     * @since Available in iOS 3.2 and later.
     */
    public void deleteBackward() {
        if (customClass) { objc_deleteBackwardSuper(getSuper(), deleteBackward); } else { objc_deleteBackward(this, deleteBackward); }
    }
    
    private static final Selector dictationRecognitionFailed = Selector.register("dictationRecognitionFailed");
    @Bridge private native static void objc_dictationRecognitionFailed(UITextField __self__, Selector __cmd__);
    @Bridge private native static void objc_dictationRecognitionFailedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/dictationRecognitionFailed">- (void)dictationRecognitionFailed</a>
     * @since Available in iOS 5.1 and later.
     */
    public void dictationRecognitionFailed() {
        if (customClass) { objc_dictationRecognitionFailedSuper(getSuper(), dictationRecognitionFailed); } else { objc_dictationRecognitionFailed(this, dictationRecognitionFailed); }
    }
    
    private static final Selector dictationRecordingDidEnd = Selector.register("dictationRecordingDidEnd");
    @Bridge private native static void objc_dictationRecordingDidEnd(UITextField __self__, Selector __cmd__);
    @Bridge private native static void objc_dictationRecordingDidEndSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/dictationRecordingDidEnd">- (void)dictationRecordingDidEnd</a>
     * @since Available in iOS 5.1 and later.
     */
    public void dictationRecordingDidEnd() {
        if (customClass) { objc_dictationRecordingDidEndSuper(getSuper(), dictationRecordingDidEnd); } else { objc_dictationRecordingDidEnd(this, dictationRecordingDidEnd); }
    }
    
    private static final Selector drawPlaceholderInRect$ = Selector.register("drawPlaceholderInRect:");
    @Bridge private native static void objc_drawPlaceholder(UITextField __self__, Selector __cmd__, @ByVal CGRect rect);
    @Bridge private native static void objc_drawPlaceholderSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect rect);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instm/UITextField/drawPlaceholderInRect:">- (void)drawPlaceholderInRect:(CGRect)rect</a>
     * @since Available in iOS 2.0 and later.
     */
    public void drawPlaceholder(CGRect rect) {
        if (customClass) { objc_drawPlaceholderSuper(getSuper(), drawPlaceholderInRect$, rect); } else { objc_drawPlaceholder(this, drawPlaceholderInRect$, rect); }
    }
    
    private static final Selector drawTextInRect$ = Selector.register("drawTextInRect:");
    @Bridge private native static void objc_drawText(UITextField __self__, Selector __cmd__, @ByVal CGRect rect);
    @Bridge private native static void objc_drawTextSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect rect);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instm/UITextField/drawTextInRect:">- (void)drawTextInRect:(CGRect)rect</a>
     * @since Available in iOS 2.0 and later.
     */
    public void drawText(CGRect rect) {
        if (customClass) { objc_drawTextSuper(getSuper(), drawTextInRect$, rect); } else { objc_drawText(this, drawTextInRect$, rect); }
    }
    
    private static final Selector baseWritingDirectionForPosition$inDirection$ = Selector.register("baseWritingDirectionForPosition:inDirection:");
    @Bridge private native static UITextWritingDirection objc_getBaseWritingDirection(UITextField __self__, Selector __cmd__, UITextPosition position, UITextStorageDirection direction);
    @Bridge private native static UITextWritingDirection objc_getBaseWritingDirectionSuper(ObjCSuper __super__, Selector __cmd__, UITextPosition position, UITextStorageDirection direction);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/baseWritingDirectionForPosition:inDirection:">- (UITextWritingDirection)baseWritingDirectionForPosition:(UITextPosition *)position inDirection:(UITextStorageDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextWritingDirection getBaseWritingDirection(UITextPosition position, UITextStorageDirection direction) {
        if (customClass) { return objc_getBaseWritingDirectionSuper(getSuper(), baseWritingDirectionForPosition$inDirection$, position, direction); } else { return objc_getBaseWritingDirection(this, baseWritingDirectionForPosition$inDirection$, position, direction); }
    }
    
    private static final Selector borderRectForBounds$ = Selector.register("borderRectForBounds:");
    @Bridge private native static @ByVal CGRect objc_getBorderRect(UITextField __self__, Selector __cmd__, @ByVal CGRect bounds);
    @Bridge private native static @ByVal CGRect objc_getBorderRectSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect bounds);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instm/UITextField/borderRectForBounds:">- (CGRect)borderRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getBorderRect(CGRect bounds) {
        if (customClass) { return objc_getBorderRectSuper(getSuper(), borderRectForBounds$, bounds); } else { return objc_getBorderRect(this, borderRectForBounds$, bounds); }
    }
    
    private static final Selector caretRectForPosition$ = Selector.register("caretRectForPosition:");
    @Bridge private native static @ByVal CGRect objc_getCaretRect(UITextField __self__, Selector __cmd__, UITextPosition position);
    @Bridge private native static @ByVal CGRect objc_getCaretRectSuper(ObjCSuper __super__, Selector __cmd__, UITextPosition position);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/caretRectForPosition:">- (CGRect)caretRectForPosition:(UITextPosition *)position</a>
     * @since Available in iOS 3.2 and later.
     */
    public CGRect getCaretRect(UITextPosition position) {
        if (customClass) { return objc_getCaretRectSuper(getSuper(), caretRectForPosition$, position); } else { return objc_getCaretRect(this, caretRectForPosition$, position); }
    }
    
    private static final Selector characterOffsetOfPosition$withinRange$ = Selector.register("characterOffsetOfPosition:withinRange:");
    @Bridge private native static int objc_getCharacterOffset(UITextField __self__, Selector __cmd__, UITextPosition position, UITextRange range);
    @Bridge private native static int objc_getCharacterOffsetSuper(ObjCSuper __super__, Selector __cmd__, UITextPosition position, UITextRange range);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/characterOffsetOfPosition:withinRange:">- (NSInteger)characterOffsetOfPosition:(UITextPosition *)position withinRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    public int getCharacterOffset(UITextPosition position, UITextRange range) {
        if (customClass) { return objc_getCharacterOffsetSuper(getSuper(), characterOffsetOfPosition$withinRange$, position, range); } else { return objc_getCharacterOffset(this, characterOffsetOfPosition$withinRange$, position, range); }
    }
    
    private static final Selector characterRangeAtPoint$ = Selector.register("characterRangeAtPoint:");
    @Bridge private native static UITextRange objc_getCharacterRange(UITextField __self__, Selector __cmd__, @ByVal CGPoint point);
    @Bridge private native static UITextRange objc_getCharacterRangeSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint point);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/characterRangeAtPoint:">- (UITextRange *)characterRangeAtPoint:(CGPoint)point</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextRange getCharacterRange(CGPoint point) {
        if (customClass) { return objc_getCharacterRangeSuper(getSuper(), characterRangeAtPoint$, point); } else { return objc_getCharacterRange(this, characterRangeAtPoint$, point); }
    }
    
    private static final Selector characterRangeByExtendingPosition$inDirection$ = Selector.register("characterRangeByExtendingPosition:inDirection:");
    @Bridge private native static UITextRange objc_getCharacterRange(UITextField __self__, Selector __cmd__, UITextPosition position, UITextLayoutDirection direction);
    @Bridge private native static UITextRange objc_getCharacterRangeSuper(ObjCSuper __super__, Selector __cmd__, UITextPosition position, UITextLayoutDirection direction);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/characterRangeByExtendingPosition:inDirection:">- (UITextRange *)characterRangeByExtendingPosition:(UITextPosition *)position inDirection:(UITextLayoutDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextRange getCharacterRange(UITextPosition position, UITextLayoutDirection direction) {
        if (customClass) { return objc_getCharacterRangeSuper(getSuper(), characterRangeByExtendingPosition$inDirection$, position, direction); } else { return objc_getCharacterRange(this, characterRangeByExtendingPosition$inDirection$, position, direction); }
    }
    
    private static final Selector closestPositionToPoint$withinRange$ = Selector.register("closestPositionToPoint:withinRange:");
    @Bridge private native static UITextPosition objc_getClosestPosition(UITextField __self__, Selector __cmd__, @ByVal CGPoint point, UITextRange range);
    @Bridge private native static UITextPosition objc_getClosestPositionSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint point, UITextRange range);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/closestPositionToPoint:withinRange:">- (UITextPosition *)closestPositionToPoint:(CGPoint)point withinRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextPosition getClosestPosition(CGPoint point, UITextRange range) {
        if (customClass) { return objc_getClosestPositionSuper(getSuper(), closestPositionToPoint$withinRange$, point, range); } else { return objc_getClosestPosition(this, closestPositionToPoint$withinRange$, point, range); }
    }
    
    private static final Selector closestPositionToPoint$ = Selector.register("closestPositionToPoint:");
    @Bridge private native static UITextPosition objc_getClosestPosition(UITextField __self__, Selector __cmd__, @ByVal CGPoint point);
    @Bridge private native static UITextPosition objc_getClosestPositionSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint point);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/closestPositionToPoint:">- (UITextPosition *)closestPositionToPoint:(CGPoint)point</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextPosition getClosestPosition(CGPoint point) {
        if (customClass) { return objc_getClosestPositionSuper(getSuper(), closestPositionToPoint$, point); } else { return objc_getClosestPosition(this, closestPositionToPoint$, point); }
    }
    
    private static final Selector frameForDictationResultPlaceholder$ = Selector.register("frameForDictationResultPlaceholder:");
    @Bridge private native static @ByVal CGRect objc_getDictationResultPlaceholderFrame(UITextField __self__, Selector __cmd__, NSObject placeholder);
    @Bridge private native static @ByVal CGRect objc_getDictationResultPlaceholderFrameSuper(ObjCSuper __super__, Selector __cmd__, NSObject placeholder);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/frameForDictationResultPlaceholder:">- (CGRect)frameForDictationResultPlaceholder:(id)placeholder</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGRect getDictationResultPlaceholderFrame(NSObject placeholder) {
        if (customClass) { return objc_getDictationResultPlaceholderFrameSuper(getSuper(), frameForDictationResultPlaceholder$, placeholder); } else { return objc_getDictationResultPlaceholderFrame(this, frameForDictationResultPlaceholder$, placeholder); }
    }
    
    private static final Selector editingRectForBounds$ = Selector.register("editingRectForBounds:");
    @Bridge private native static @ByVal CGRect objc_getEditingRect(UITextField __self__, Selector __cmd__, @ByVal CGRect bounds);
    @Bridge private native static @ByVal CGRect objc_getEditingRectSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect bounds);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instm/UITextField/editingRectForBounds:">- (CGRect)editingRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getEditingRect(CGRect bounds) {
        if (customClass) { return objc_getEditingRectSuper(getSuper(), editingRectForBounds$, bounds); } else { return objc_getEditingRect(this, editingRectForBounds$, bounds); }
    }
    
    private static final Selector firstRectForRange$ = Selector.register("firstRectForRange:");
    @Bridge private native static @ByVal CGRect objc_getFirstRect(UITextField __self__, Selector __cmd__, UITextRange range);
    @Bridge private native static @ByVal CGRect objc_getFirstRectSuper(ObjCSuper __super__, Selector __cmd__, UITextRange range);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/firstRectForRange:">- (CGRect)firstRectForRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    public CGRect getFirstRect(UITextRange range) {
        if (customClass) { return objc_getFirstRectSuper(getSuper(), firstRectForRange$, range); } else { return objc_getFirstRect(this, firstRectForRange$, range); }
    }
    
    private static final Selector leftViewRectForBounds$ = Selector.register("leftViewRectForBounds:");
    @Bridge private native static @ByVal CGRect objc_getLeftViewRect(UITextField __self__, Selector __cmd__, @ByVal CGRect bounds);
    @Bridge private native static @ByVal CGRect objc_getLeftViewRectSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect bounds);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instm/UITextField/leftViewRectForBounds:">- (CGRect)leftViewRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getLeftViewRect(CGRect bounds) {
        if (customClass) { return objc_getLeftViewRectSuper(getSuper(), leftViewRectForBounds$, bounds); } else { return objc_getLeftViewRect(this, leftViewRectForBounds$, bounds); }
    }
    
    private static final Selector offsetFromPosition$toPosition$ = Selector.register("offsetFromPosition:toPosition:");
    @Bridge private native static int objc_getOffset(UITextField __self__, Selector __cmd__, UITextPosition fromPosition, UITextPosition toPosition);
    @Bridge private native static int objc_getOffsetSuper(ObjCSuper __super__, Selector __cmd__, UITextPosition fromPosition, UITextPosition toPosition);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/offsetFromPosition:toPosition:">- (NSInteger)offsetFromPosition:(UITextPosition *)fromPosition toPosition:(UITextPosition *)toPosition</a>
     * @since Available in iOS 3.2 and later.
     */
    public int getOffset(UITextPosition fromPosition, UITextPosition toPosition) {
        if (customClass) { return objc_getOffsetSuper(getSuper(), offsetFromPosition$toPosition$, fromPosition, toPosition); } else { return objc_getOffset(this, offsetFromPosition$toPosition$, fromPosition, toPosition); }
    }
    
    private static final Selector placeholderRectForBounds$ = Selector.register("placeholderRectForBounds:");
    @Bridge private native static @ByVal CGRect objc_getPlaceholderRect(UITextField __self__, Selector __cmd__, @ByVal CGRect bounds);
    @Bridge private native static @ByVal CGRect objc_getPlaceholderRectSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect bounds);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instm/UITextField/placeholderRectForBounds:">- (CGRect)placeholderRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getPlaceholderRect(CGRect bounds) {
        if (customClass) { return objc_getPlaceholderRectSuper(getSuper(), placeholderRectForBounds$, bounds); } else { return objc_getPlaceholderRect(this, placeholderRectForBounds$, bounds); }
    }
    
    private static final Selector positionFromPosition$inDirection$offset$ = Selector.register("positionFromPosition:inDirection:offset:");
    @Bridge private native static UITextPosition objc_getPosition(UITextField __self__, Selector __cmd__, UITextPosition position, UITextLayoutDirection direction, int offset);
    @Bridge private native static UITextPosition objc_getPositionSuper(ObjCSuper __super__, Selector __cmd__, UITextPosition position, UITextLayoutDirection direction, int offset);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionFromPosition:inDirection:offset:">- (UITextPosition *)positionFromPosition:(UITextPosition *)position inDirection:(UITextLayoutDirection)direction offset:(NSInteger)offset</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextPosition getPosition(UITextPosition position, UITextLayoutDirection direction, int offset) {
        if (customClass) { return objc_getPositionSuper(getSuper(), positionFromPosition$inDirection$offset$, position, direction, offset); } else { return objc_getPosition(this, positionFromPosition$inDirection$offset$, position, direction, offset); }
    }
    
    private static final Selector positionWithinRange$atCharacterOffset$ = Selector.register("positionWithinRange:atCharacterOffset:");
    @Bridge private native static UITextPosition objc_getPosition(UITextField __self__, Selector __cmd__, UITextRange range, int offset);
    @Bridge private native static UITextPosition objc_getPositionSuper(ObjCSuper __super__, Selector __cmd__, UITextRange range, int offset);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionWithinRange:atCharacterOffset:">- (UITextPosition *)positionWithinRange:(UITextRange *)range atCharacterOffset:(NSInteger)offset</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextPosition getPosition(UITextRange range, int offset) {
        if (customClass) { return objc_getPositionSuper(getSuper(), positionWithinRange$atCharacterOffset$, range, offset); } else { return objc_getPosition(this, positionWithinRange$atCharacterOffset$, range, offset); }
    }
    
    private static final Selector positionFromPosition$offset$ = Selector.register("positionFromPosition:offset:");
    @Bridge private native static UITextPosition objc_getPosition(UITextField __self__, Selector __cmd__, UITextPosition position, int offset);
    @Bridge private native static UITextPosition objc_getPositionSuper(ObjCSuper __super__, Selector __cmd__, UITextPosition position, int offset);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionFromPosition:offset:">- (UITextPosition *)positionFromPosition:(UITextPosition *)position offset:(NSInteger)offset</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextPosition getPosition(UITextPosition position, int offset) {
        if (customClass) { return objc_getPositionSuper(getSuper(), positionFromPosition$offset$, position, offset); } else { return objc_getPosition(this, positionFromPosition$offset$, position, offset); }
    }
    
    private static final Selector positionWithinRange$farthestInDirection$ = Selector.register("positionWithinRange:farthestInDirection:");
    @Bridge private native static UITextPosition objc_getPosition(UITextField __self__, Selector __cmd__, UITextRange range, UITextLayoutDirection direction);
    @Bridge private native static UITextPosition objc_getPositionSuper(ObjCSuper __super__, Selector __cmd__, UITextRange range, UITextLayoutDirection direction);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/positionWithinRange:farthestInDirection:">- (UITextPosition *)positionWithinRange:(UITextRange *)range farthestInDirection:(UITextLayoutDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextPosition getPosition(UITextRange range, UITextLayoutDirection direction) {
        if (customClass) { return objc_getPositionSuper(getSuper(), positionWithinRange$farthestInDirection$, range, direction); } else { return objc_getPosition(this, positionWithinRange$farthestInDirection$, range, direction); }
    }
    
    private static final Selector rightViewRectForBounds$ = Selector.register("rightViewRectForBounds:");
    @Bridge private native static @ByVal CGRect objc_getRightViewRect(UITextField __self__, Selector __cmd__, @ByVal CGRect bounds);
    @Bridge private native static @ByVal CGRect objc_getRightViewRectSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect bounds);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instm/UITextField/rightViewRectForBounds:">- (CGRect)rightViewRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getRightViewRect(CGRect bounds) {
        if (customClass) { return objc_getRightViewRectSuper(getSuper(), rightViewRectForBounds$, bounds); } else { return objc_getRightViewRect(this, rightViewRectForBounds$, bounds); }
    }
    
    private static final Selector selectionRectsForRange$ = Selector.register("selectionRectsForRange:");
    @Bridge private native static NSArray objc_getSelectionRects(UITextField __self__, Selector __cmd__, UITextRange range);
    @Bridge private native static NSArray objc_getSelectionRectsSuper(ObjCSuper __super__, Selector __cmd__, UITextRange range);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/selectionRectsForRange:">- (NSArray *)selectionRectsForRange:(UITextRange *)range</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSArray getSelectionRects(UITextRange range) {
        if (customClass) { return objc_getSelectionRectsSuper(getSuper(), selectionRectsForRange$, range); } else { return objc_getSelectionRects(this, selectionRectsForRange$, range); }
    }
    
    private static final Selector textInRange$ = Selector.register("textInRange:");
    @Bridge private native static String objc_getText(UITextField __self__, Selector __cmd__, UITextRange range);
    @Bridge private native static String objc_getTextSuper(ObjCSuper __super__, Selector __cmd__, UITextRange range);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/textInRange:">- (NSString *)textInRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    public String getText(UITextRange range) {
        if (customClass) { return objc_getTextSuper(getSuper(), textInRange$, range); } else { return objc_getText(this, textInRange$, range); }
    }
    
    private static final Selector textRangeFromPosition$toPosition$ = Selector.register("textRangeFromPosition:toPosition:");
    @Bridge private native static UITextRange objc_getTextRange(UITextField __self__, Selector __cmd__, UITextPosition fromPosition, UITextPosition toPosition);
    @Bridge private native static UITextRange objc_getTextRangeSuper(ObjCSuper __super__, Selector __cmd__, UITextPosition fromPosition, UITextPosition toPosition);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/textRangeFromPosition:toPosition:">- (UITextRange *)textRangeFromPosition:(UITextPosition *)fromPosition toPosition:(UITextPosition *)toPosition</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextRange getTextRange(UITextPosition fromPosition, UITextPosition toPosition) {
        if (customClass) { return objc_getTextRangeSuper(getSuper(), textRangeFromPosition$toPosition$, fromPosition, toPosition); } else { return objc_getTextRange(this, textRangeFromPosition$toPosition$, fromPosition, toPosition); }
    }
    
    private static final Selector textRectForBounds$ = Selector.register("textRectForBounds:");
    @Bridge private native static @ByVal CGRect objc_getTextRect(UITextField __self__, Selector __cmd__, @ByVal CGRect bounds);
    @Bridge private native static @ByVal CGRect objc_getTextRectSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect bounds);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextField_Class/Reference/UITextField.html#//apple_ref/occ/instm/UITextField/textRectForBounds:">- (CGRect)textRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getTextRect(CGRect bounds) {
        if (customClass) { return objc_getTextRectSuper(getSuper(), textRectForBounds$, bounds); } else { return objc_getTextRect(this, textRectForBounds$, bounds); }
    }
    
    private static final Selector textStylingAtPosition$inDirection$ = Selector.register("textStylingAtPosition:inDirection:");
    @Bridge private native static NSDictionary objc_getTextStyling(UITextField __self__, Selector __cmd__, UITextPosition position, UITextStorageDirection direction);
    @Bridge private native static NSDictionary objc_getTextStylingSuper(ObjCSuper __super__, Selector __cmd__, UITextPosition position, UITextStorageDirection direction);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/textStylingAtPosition:inDirection:">- (NSDictionary *)textStylingAtPosition:(UITextPosition *)position inDirection:(UITextStorageDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    public NSDictionary getTextStyling(UITextPosition position, UITextStorageDirection direction) {
        if (customClass) { return objc_getTextStylingSuper(getSuper(), textStylingAtPosition$inDirection$, position, direction); } else { return objc_getTextStyling(this, textStylingAtPosition$inDirection$, position, direction); }
    }
    
    private static final Selector hasText = Selector.register("hasText");
    @Bridge private native static boolean objc_hasText(UITextField __self__, Selector __cmd__);
    @Bridge private native static boolean objc_hasTextSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIKeyInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIKeyInput/hasText">- (BOOL)hasText</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean hasText() {
        if (customClass) { return objc_hasTextSuper(getSuper(), hasText); } else { return objc_hasText(this, hasText); }
    }
    
    private static final Selector insertDictationResult$ = Selector.register("insertDictationResult:");
    @Bridge private native static void objc_insertDictationResult(UITextField __self__, Selector __cmd__, NSArray dictationResult);
    @Bridge private native static void objc_insertDictationResultSuper(ObjCSuper __super__, Selector __cmd__, NSArray dictationResult);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/insertDictationResult:">- (void)insertDictationResult:(NSArray *)dictationResult</a>
     * @since Available in iOS 5.1 and later.
     */
    public void insertDictationResult(NSArray dictationResult) {
        if (customClass) { objc_insertDictationResultSuper(getSuper(), insertDictationResult$, dictationResult); } else { objc_insertDictationResult(this, insertDictationResult$, dictationResult); }
    }
    
    private static final Selector insertDictationResultPlaceholder = Selector.register("insertDictationResultPlaceholder");
    @Bridge private native static NSObject objc_insertDictationResultPlaceholder(UITextField __self__, Selector __cmd__);
    @Bridge private native static NSObject objc_insertDictationResultPlaceholderSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/insertDictationResultPlaceholder">- (id)insertDictationResultPlaceholder</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSObject insertDictationResultPlaceholder() {
        if (customClass) { return objc_insertDictationResultPlaceholderSuper(getSuper(), insertDictationResultPlaceholder); } else { return objc_insertDictationResultPlaceholder(this, insertDictationResultPlaceholder); }
    }
    
    private static final Selector insertText$ = Selector.register("insertText:");
    @Bridge private native static void objc_insertText(UITextField __self__, Selector __cmd__, String text);
    @Bridge private native static void objc_insertTextSuper(ObjCSuper __super__, Selector __cmd__, String text);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIKeyInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIKeyInput/insertText:">- (void)insertText:(NSString *)text</a>
     * @since Available in iOS 3.2 and later.
     */
    public void insertText(String text) {
        if (customClass) { objc_insertTextSuper(getSuper(), insertText$, text); } else { objc_insertText(this, insertText$, text); }
    }
    
    private static final Selector removeDictationResultPlaceholder$willInsertResult$ = Selector.register("removeDictationResultPlaceholder:willInsertResult:");
    @Bridge private native static void objc_removeDictationResultPlaceholder(UITextField __self__, Selector __cmd__, NSObject placeholder, boolean willInsertResult);
    @Bridge private native static void objc_removeDictationResultPlaceholderSuper(ObjCSuper __super__, Selector __cmd__, NSObject placeholder, boolean willInsertResult);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/removeDictationResultPlaceholder:willInsertResult:">- (void)removeDictationResultPlaceholder:(id)placeholder willInsertResult:(BOOL)willInsertResult</a>
     * @since Available in iOS 6.0 and later.
     */
    public void removeDictationResultPlaceholder(NSObject placeholder, boolean willInsertResult) {
        if (customClass) { objc_removeDictationResultPlaceholderSuper(getSuper(), removeDictationResultPlaceholder$willInsertResult$, placeholder, willInsertResult); } else { objc_removeDictationResultPlaceholder(this, removeDictationResultPlaceholder$willInsertResult$, placeholder, willInsertResult); }
    }
    
    private static final Selector replaceRange$withText$ = Selector.register("replaceRange:withText:");
    @Bridge private native static void objc_replaceText(UITextField __self__, Selector __cmd__, UITextRange range, String text);
    @Bridge private native static void objc_replaceTextSuper(ObjCSuper __super__, Selector __cmd__, UITextRange range, String text);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/replaceRange:withText:">- (void)replaceRange:(UITextRange *)range withText:(NSString *)text</a>
     * @since Available in iOS 3.2 and later.
     */
    public void replaceText(UITextRange range, String text) {
        if (customClass) { objc_replaceTextSuper(getSuper(), replaceRange$withText$, range, text); } else { objc_replaceText(this, replaceRange$withText$, range, text); }
    }
    
    private static final Selector setBaseWritingDirection$forRange$ = Selector.register("setBaseWritingDirection:forRange:");
    @Bridge private native static void objc_setBaseWritingDirection(UITextField __self__, Selector __cmd__, UITextWritingDirection writingDirection, UITextRange range);
    @Bridge private native static void objc_setBaseWritingDirectionSuper(ObjCSuper __super__, Selector __cmd__, UITextWritingDirection writingDirection, UITextRange range);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/setBaseWritingDirection:forRange:">- (void)setBaseWritingDirection:(UITextWritingDirection)writingDirection forRange:(UITextRange *)range</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setBaseWritingDirection(UITextWritingDirection writingDirection, UITextRange range) {
        if (customClass) { objc_setBaseWritingDirectionSuper(getSuper(), setBaseWritingDirection$forRange$, writingDirection, range); } else { objc_setBaseWritingDirection(this, setBaseWritingDirection$forRange$, writingDirection, range); }
    }
    
    private static final Selector setMarkedText$selectedRange$ = Selector.register("setMarkedText:selectedRange:");
    @Bridge private native static void objc_setMarkedText(UITextField __self__, Selector __cmd__, String markedText, @ByVal NSRange selectedRange);
    @Bridge private native static void objc_setMarkedTextSuper(ObjCSuper __super__, Selector __cmd__, String markedText, @ByVal NSRange selectedRange);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/setMarkedText:selectedRange:">- (void)setMarkedText:(NSString *)markedText selectedRange:(NSRange)selectedRange</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setMarkedText(String markedText, NSRange selectedRange) {
        if (customClass) { objc_setMarkedTextSuper(getSuper(), setMarkedText$selectedRange$, markedText, selectedRange); } else { objc_setMarkedText(this, setMarkedText$selectedRange$, markedText, selectedRange); }
    }
    
    private static final Selector shouldChangeTextInRange$replacementText$ = Selector.register("shouldChangeTextInRange:replacementText:");
    @Bridge private native static boolean objc_shouldChangeText(UITextField __self__, Selector __cmd__, UITextRange range, String text);
    @Bridge private native static boolean objc_shouldChangeTextSuper(ObjCSuper __super__, Selector __cmd__, UITextRange range, String text);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/shouldChangeTextInRange:replacementText:">- (BOOL)shouldChangeTextInRange:(UITextRange *)range replacementText:(NSString *)text</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean shouldChangeText(UITextRange range, String text) {
        if (customClass) { return objc_shouldChangeTextSuper(getSuper(), shouldChangeTextInRange$replacementText$, range, text); } else { return objc_shouldChangeText(this, shouldChangeTextInRange$replacementText$, range, text); }
    }
    
    private static final Selector unmarkText = Selector.register("unmarkText");
    @Bridge private native static void objc_unmarkText(UITextField __self__, Selector __cmd__);
    @Bridge private native static void objc_unmarkTextSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInput/unmarkText">- (void)unmarkText</a>
     * @since Available in iOS 3.2 and later.
     */
    public void unmarkText() {
        if (customClass) { objc_unmarkTextSuper(getSuper(), unmarkText); } else { objc_unmarkText(this, unmarkText); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("adjustsFontSizeToFitWidth") public static boolean isAdjustsFontSizeToFitWidth(UITextField __self__, Selector __cmd__) { return __self__.isAdjustsFontSizeToFitWidth(); }
        @Callback @BindSelector("setAdjustsFontSizeToFitWidth:") public static void setAdjustsFontSizeToFitWidth(UITextField __self__, Selector __cmd__, boolean adjustsFontSizeToFitWidth) { __self__.setAdjustsFontSizeToFitWidth(adjustsFontSizeToFitWidth); }
        @Callback @BindSelector("allowsEditingTextAttributes") public static boolean isAllowsEditingTextAttributes(UITextField __self__, Selector __cmd__) { return __self__.isAllowsEditingTextAttributes(); }
        @Callback @BindSelector("setAllowsEditingTextAttributes:") public static void setAllowsEditingTextAttributes(UITextField __self__, Selector __cmd__, boolean allowsEditingTextAttributes) { __self__.setAllowsEditingTextAttributes(allowsEditingTextAttributes); }
        @Callback @BindSelector("attributedPlaceholder") public static NSAttributedString getAttributedPlaceholder(UITextField __self__, Selector __cmd__) { return __self__.getAttributedPlaceholder(); }
        @Callback @BindSelector("setAttributedPlaceholder:") public static void setAttributedPlaceholder(UITextField __self__, Selector __cmd__, NSAttributedString attributedPlaceholder) { __self__.setAttributedPlaceholder(attributedPlaceholder); }
        @Callback @BindSelector("attributedText") public static NSAttributedString getAttributedText(UITextField __self__, Selector __cmd__) { return __self__.getAttributedText(); }
        @Callback @BindSelector("setAttributedText:") public static void setAttributedText(UITextField __self__, Selector __cmd__, NSAttributedString attributedText) { __self__.setAttributedText(attributedText); }
        @Callback @BindSelector("autocapitalizationType") public static UITextAutocapitalizationType getAutocapitalizationType(UITextField __self__, Selector __cmd__) { return __self__.getAutocapitalizationType(); }
        @Callback @BindSelector("setAutocapitalizationType:") public static void setAutocapitalizationType(UITextField __self__, Selector __cmd__, UITextAutocapitalizationType autocapitalizationType) { __self__.setAutocapitalizationType(autocapitalizationType); }
        @Callback @BindSelector("autocorrectionType") public static UITextAutocorrectionType getAutocorrectionType(UITextField __self__, Selector __cmd__) { return __self__.getAutocorrectionType(); }
        @Callback @BindSelector("setAutocorrectionType:") public static void setAutocorrectionType(UITextField __self__, Selector __cmd__, UITextAutocorrectionType autocorrectionType) { __self__.setAutocorrectionType(autocorrectionType); }
        @Callback @BindSelector("background") public static UIImage getBackground(UITextField __self__, Selector __cmd__) { return __self__.getBackground(); }
        @Callback @BindSelector("setBackground:") public static void setBackground(UITextField __self__, Selector __cmd__, UIImage background) { __self__.setBackground(background); }
        @Callback @BindSelector("beginningOfDocument") public static UITextPosition getBeginningOfDocument(UITextField __self__, Selector __cmd__) { return __self__.getBeginningOfDocument(); }
        @Callback @BindSelector("borderStyle") public static UITextBorderStyle getBorderStyle(UITextField __self__, Selector __cmd__) { return __self__.getBorderStyle(); }
        @Callback @BindSelector("setBorderStyle:") public static void setBorderStyle(UITextField __self__, Selector __cmd__, UITextBorderStyle borderStyle) { __self__.setBorderStyle(borderStyle); }
        @Callback @BindSelector("clearButtonMode") public static UITextFieldViewMode getClearButtonMode(UITextField __self__, Selector __cmd__) { return __self__.getClearButtonMode(); }
        @Callback @BindSelector("setClearButtonMode:") public static void setClearButtonMode(UITextField __self__, Selector __cmd__, UITextFieldViewMode clearButtonMode) { __self__.setClearButtonMode(clearButtonMode); }
        @Callback @BindSelector("clearsOnBeginEditing") public static boolean isClearsOnBeginEditing(UITextField __self__, Selector __cmd__) { return __self__.isClearsOnBeginEditing(); }
        @Callback @BindSelector("setClearsOnBeginEditing:") public static void setClearsOnBeginEditing(UITextField __self__, Selector __cmd__, boolean clearsOnBeginEditing) { __self__.setClearsOnBeginEditing(clearsOnBeginEditing); }
        @Callback @BindSelector("clearsOnInsertion") public static boolean isClearsOnInsertion(UITextField __self__, Selector __cmd__) { return __self__.isClearsOnInsertion(); }
        @Callback @BindSelector("setClearsOnInsertion:") public static void setClearsOnInsertion(UITextField __self__, Selector __cmd__, boolean clearsOnInsertion) { __self__.setClearsOnInsertion(clearsOnInsertion); }
        @Callback @BindSelector("delegate") public static UITextFieldDelegate getDelegate(UITextField __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UITextField __self__, Selector __cmd__, UITextFieldDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("disabledBackground") public static UIImage getDisabledBackground(UITextField __self__, Selector __cmd__) { return __self__.getDisabledBackground(); }
        @Callback @BindSelector("setDisabledBackground:") public static void setDisabledBackground(UITextField __self__, Selector __cmd__, UIImage disabledBackground) { __self__.setDisabledBackground(disabledBackground); }
        @Callback @BindSelector("isEditing") public static boolean isEditing(UITextField __self__, Selector __cmd__) { return __self__.isEditing(); }
        @Callback @BindSelector("enablesReturnKeyAutomatically") public static boolean isEnablesReturnKeyAutomatically(UITextField __self__, Selector __cmd__) { return __self__.isEnablesReturnKeyAutomatically(); }
        @Callback @BindSelector("setEnablesReturnKeyAutomatically:") public static void setEnablesReturnKeyAutomatically(UITextField __self__, Selector __cmd__, boolean enablesReturnKeyAutomatically) { __self__.setEnablesReturnKeyAutomatically(enablesReturnKeyAutomatically); }
        @Callback @BindSelector("endOfDocument") public static UITextPosition getEndOfDocument(UITextField __self__, Selector __cmd__) { return __self__.getEndOfDocument(); }
        @Callback @BindSelector("font") public static UIFont getFont(UITextField __self__, Selector __cmd__) { return __self__.getFont(); }
        @Callback @BindSelector("setFont:") public static void setFont(UITextField __self__, Selector __cmd__, UIFont font) { __self__.setFont(font); }
        @Callback @BindSelector("inputAccessoryView") public static UIView getInputAccessoryView(UITextField __self__, Selector __cmd__) { return __self__.getInputAccessoryView(); }
        @Callback @BindSelector("setInputAccessoryView:") public static void setInputAccessoryView(UITextField __self__, Selector __cmd__, UIView inputAccessoryView) { __self__.setInputAccessoryView(inputAccessoryView); }
        @Callback @BindSelector("inputDelegate") public static UITextInputDelegate getInputDelegate(UITextField __self__, Selector __cmd__) { return __self__.getInputDelegate(); }
        @Callback @BindSelector("setInputDelegate:") public static void setInputDelegate(UITextField __self__, Selector __cmd__, UITextInputDelegate inputDelegate) { __self__.setInputDelegate(inputDelegate); }
        @Callback @BindSelector("inputView") public static UIView getInputView(UITextField __self__, Selector __cmd__) { return __self__.getInputView(); }
        @Callback @BindSelector("setInputView:") public static void setInputView(UITextField __self__, Selector __cmd__, UIView inputView) { __self__.setInputView(inputView); }
        @Callback @BindSelector("keyboardAppearance") public static UIKeyboardAppearance getKeyboardAppearance(UITextField __self__, Selector __cmd__) { return __self__.getKeyboardAppearance(); }
        @Callback @BindSelector("setKeyboardAppearance:") public static void setKeyboardAppearance(UITextField __self__, Selector __cmd__, UIKeyboardAppearance keyboardAppearance) { __self__.setKeyboardAppearance(keyboardAppearance); }
        @Callback @BindSelector("keyboardType") public static UIKeyboardType getKeyboardType(UITextField __self__, Selector __cmd__) { return __self__.getKeyboardType(); }
        @Callback @BindSelector("setKeyboardType:") public static void setKeyboardType(UITextField __self__, Selector __cmd__, UIKeyboardType keyboardType) { __self__.setKeyboardType(keyboardType); }
        @Callback @BindSelector("leftView") public static UIView getLeftView(UITextField __self__, Selector __cmd__) { return __self__.getLeftView(); }
        @Callback @BindSelector("setLeftView:") public static void setLeftView(UITextField __self__, Selector __cmd__, UIView leftView) { __self__.setLeftView(leftView); }
        @Callback @BindSelector("leftViewMode") public static UITextFieldViewMode getLeftViewMode(UITextField __self__, Selector __cmd__) { return __self__.getLeftViewMode(); }
        @Callback @BindSelector("setLeftViewMode:") public static void setLeftViewMode(UITextField __self__, Selector __cmd__, UITextFieldViewMode leftViewMode) { __self__.setLeftViewMode(leftViewMode); }
        @Callback @BindSelector("markedTextRange") public static UITextRange getMarkedTextRange(UITextField __self__, Selector __cmd__) { return __self__.getMarkedTextRange(); }
        @Callback @BindSelector("markedTextStyle") public static NSDictionary getMarkedTextStyle(UITextField __self__, Selector __cmd__) { return __self__.getMarkedTextStyle(); }
        @Callback @BindSelector("setMarkedTextStyle:") public static void setMarkedTextStyle(UITextField __self__, Selector __cmd__, NSDictionary markedTextStyle) { __self__.setMarkedTextStyle(markedTextStyle); }
        @Callback @BindSelector("minimumFontSize") public static float getMinimumFontSize(UITextField __self__, Selector __cmd__) { return __self__.getMinimumFontSize(); }
        @Callback @BindSelector("setMinimumFontSize:") public static void setMinimumFontSize(UITextField __self__, Selector __cmd__, float minimumFontSize) { __self__.setMinimumFontSize(minimumFontSize); }
        @Callback @BindSelector("placeholder") public static String getPlaceholder(UITextField __self__, Selector __cmd__) { return __self__.getPlaceholder(); }
        @Callback @BindSelector("setPlaceholder:") public static void setPlaceholder(UITextField __self__, Selector __cmd__, String placeholder) { __self__.setPlaceholder(placeholder); }
        @Callback @BindSelector("returnKeyType") public static UIReturnKeyType getReturnKeyType(UITextField __self__, Selector __cmd__) { return __self__.getReturnKeyType(); }
        @Callback @BindSelector("setReturnKeyType:") public static void setReturnKeyType(UITextField __self__, Selector __cmd__, UIReturnKeyType returnKeyType) { __self__.setReturnKeyType(returnKeyType); }
        @Callback @BindSelector("rightView") public static UIView getRightView(UITextField __self__, Selector __cmd__) { return __self__.getRightView(); }
        @Callback @BindSelector("setRightView:") public static void setRightView(UITextField __self__, Selector __cmd__, UIView rightView) { __self__.setRightView(rightView); }
        @Callback @BindSelector("rightViewMode") public static UITextFieldViewMode getRightViewMode(UITextField __self__, Selector __cmd__) { return __self__.getRightViewMode(); }
        @Callback @BindSelector("setRightViewMode:") public static void setRightViewMode(UITextField __self__, Selector __cmd__, UITextFieldViewMode rightViewMode) { __self__.setRightViewMode(rightViewMode); }
        @Callback @BindSelector("isSecureTextEntry") public static boolean isSecureTextEntry(UITextField __self__, Selector __cmd__) { return __self__.isSecureTextEntry(); }
        @Callback @BindSelector("setSecureTextEntry:") public static void setSecureTextEntry(UITextField __self__, Selector __cmd__, boolean secureTextEntry) { __self__.setSecureTextEntry(secureTextEntry); }
        @Callback @BindSelector("selectedTextRange") public static UITextRange getSelectedTextRange(UITextField __self__, Selector __cmd__) { return __self__.getSelectedTextRange(); }
        @Callback @BindSelector("setSelectedTextRange:") public static void setSelectedTextRange(UITextField __self__, Selector __cmd__, UITextRange selectedTextRange) { __self__.setSelectedTextRange(selectedTextRange); }
        @Callback @BindSelector("selectionAffinity") public static UITextStorageDirection getSelectionAffinity(UITextField __self__, Selector __cmd__) { return __self__.getSelectionAffinity(); }
        @Callback @BindSelector("setSelectionAffinity:") public static void setSelectionAffinity(UITextField __self__, Selector __cmd__, UITextStorageDirection selectionAffinity) { __self__.setSelectionAffinity(selectionAffinity); }
        @Callback @BindSelector("spellCheckingType") public static UITextSpellCheckingType getSpellCheckingType(UITextField __self__, Selector __cmd__) { return __self__.getSpellCheckingType(); }
        @Callback @BindSelector("setSpellCheckingType:") public static void setSpellCheckingType(UITextField __self__, Selector __cmd__, UITextSpellCheckingType spellCheckingType) { __self__.setSpellCheckingType(spellCheckingType); }
        @Callback @BindSelector("text") public static String getText(UITextField __self__, Selector __cmd__) { return __self__.getText(); }
        @Callback @BindSelector("setText:") public static void setText(UITextField __self__, Selector __cmd__, String text) { __self__.setText(text); }
        @Callback @BindSelector("textAlignment") public static NSTextAlignment getTextAlignment(UITextField __self__, Selector __cmd__) { return __self__.getTextAlignment(); }
        @Callback @BindSelector("setTextAlignment:") public static void setTextAlignment(UITextField __self__, Selector __cmd__, NSTextAlignment textAlignment) { __self__.setTextAlignment(textAlignment); }
        @Callback @BindSelector("textColor") public static UIColor getTextColor(UITextField __self__, Selector __cmd__) { return __self__.getTextColor(); }
        @Callback @BindSelector("setTextColor:") public static void setTextColor(UITextField __self__, Selector __cmd__, UIColor textColor) { __self__.setTextColor(textColor); }
        @Callback @BindSelector("textInputView") public static UIView getTextInputView(UITextField __self__, Selector __cmd__) { return __self__.getTextInputView(); }
        @Callback @BindSelector("tokenizer") public static UITextInputTokenizer getTokenizer(UITextField __self__, Selector __cmd__) { return __self__.getTokenizer(); }
        @Callback @BindSelector("typingAttributes") public static NSDictionary getTypingAttributes(UITextField __self__, Selector __cmd__) { return __self__.getTypingAttributes(); }
        @Callback @BindSelector("setTypingAttributes:") public static void setTypingAttributes(UITextField __self__, Selector __cmd__, NSDictionary typingAttributes) { __self__.setTypingAttributes(typingAttributes); }
        @Callback @BindSelector("clearButtonRectForBounds:") public static @ByVal CGRect clearButtonRect(UITextField __self__, Selector __cmd__, @ByVal CGRect bounds) { return __self__.clearButtonRect(bounds); }
        @Callback @BindSelector("comparePosition:toPosition:") public static NSComparisonResult comparePositions(UITextField __self__, Selector __cmd__, UITextPosition position, UITextPosition other) { return __self__.comparePositions(position, other); }
        @Callback @BindSelector("deleteBackward") public static void deleteBackward(UITextField __self__, Selector __cmd__) { __self__.deleteBackward(); }
        @Callback @BindSelector("dictationRecognitionFailed") public static void dictationRecognitionFailed(UITextField __self__, Selector __cmd__) { __self__.dictationRecognitionFailed(); }
        @Callback @BindSelector("dictationRecordingDidEnd") public static void dictationRecordingDidEnd(UITextField __self__, Selector __cmd__) { __self__.dictationRecordingDidEnd(); }
        @Callback @BindSelector("drawPlaceholderInRect:") public static void drawPlaceholder(UITextField __self__, Selector __cmd__, @ByVal CGRect rect) { __self__.drawPlaceholder(rect); }
        @Callback @BindSelector("drawTextInRect:") public static void drawText(UITextField __self__, Selector __cmd__, @ByVal CGRect rect) { __self__.drawText(rect); }
        @Callback @BindSelector("baseWritingDirectionForPosition:inDirection:") public static UITextWritingDirection getBaseWritingDirection(UITextField __self__, Selector __cmd__, UITextPosition position, UITextStorageDirection direction) { return __self__.getBaseWritingDirection(position, direction); }
        @Callback @BindSelector("borderRectForBounds:") public static @ByVal CGRect getBorderRect(UITextField __self__, Selector __cmd__, @ByVal CGRect bounds) { return __self__.getBorderRect(bounds); }
        @Callback @BindSelector("caretRectForPosition:") public static @ByVal CGRect getCaretRect(UITextField __self__, Selector __cmd__, UITextPosition position) { return __self__.getCaretRect(position); }
        @Callback @BindSelector("characterOffsetOfPosition:withinRange:") public static int getCharacterOffset(UITextField __self__, Selector __cmd__, UITextPosition position, UITextRange range) { return __self__.getCharacterOffset(position, range); }
        @Callback @BindSelector("characterRangeAtPoint:") public static UITextRange getCharacterRange(UITextField __self__, Selector __cmd__, @ByVal CGPoint point) { return __self__.getCharacterRange(point); }
        @Callback @BindSelector("characterRangeByExtendingPosition:inDirection:") public static UITextRange getCharacterRange(UITextField __self__, Selector __cmd__, UITextPosition position, UITextLayoutDirection direction) { return __self__.getCharacterRange(position, direction); }
        @Callback @BindSelector("closestPositionToPoint:withinRange:") public static UITextPosition getClosestPosition(UITextField __self__, Selector __cmd__, @ByVal CGPoint point, UITextRange range) { return __self__.getClosestPosition(point, range); }
        @Callback @BindSelector("closestPositionToPoint:") public static UITextPosition getClosestPosition(UITextField __self__, Selector __cmd__, @ByVal CGPoint point) { return __self__.getClosestPosition(point); }
        @Callback @BindSelector("frameForDictationResultPlaceholder:") public static @ByVal CGRect getDictationResultPlaceholderFrame(UITextField __self__, Selector __cmd__, NSObject placeholder) { return __self__.getDictationResultPlaceholderFrame(placeholder); }
        @Callback @BindSelector("editingRectForBounds:") public static @ByVal CGRect getEditingRect(UITextField __self__, Selector __cmd__, @ByVal CGRect bounds) { return __self__.getEditingRect(bounds); }
        @Callback @BindSelector("firstRectForRange:") public static @ByVal CGRect getFirstRect(UITextField __self__, Selector __cmd__, UITextRange range) { return __self__.getFirstRect(range); }
        @Callback @BindSelector("leftViewRectForBounds:") public static @ByVal CGRect getLeftViewRect(UITextField __self__, Selector __cmd__, @ByVal CGRect bounds) { return __self__.getLeftViewRect(bounds); }
        @Callback @BindSelector("offsetFromPosition:toPosition:") public static int getOffset(UITextField __self__, Selector __cmd__, UITextPosition fromPosition, UITextPosition toPosition) { return __self__.getOffset(fromPosition, toPosition); }
        @Callback @BindSelector("placeholderRectForBounds:") public static @ByVal CGRect getPlaceholderRect(UITextField __self__, Selector __cmd__, @ByVal CGRect bounds) { return __self__.getPlaceholderRect(bounds); }
        @Callback @BindSelector("positionFromPosition:inDirection:offset:") public static UITextPosition getPosition(UITextField __self__, Selector __cmd__, UITextPosition position, UITextLayoutDirection direction, int offset) { return __self__.getPosition(position, direction, offset); }
        @Callback @BindSelector("positionWithinRange:atCharacterOffset:") public static UITextPosition getPosition(UITextField __self__, Selector __cmd__, UITextRange range, int offset) { return __self__.getPosition(range, offset); }
        @Callback @BindSelector("positionFromPosition:offset:") public static UITextPosition getPosition(UITextField __self__, Selector __cmd__, UITextPosition position, int offset) { return __self__.getPosition(position, offset); }
        @Callback @BindSelector("positionWithinRange:farthestInDirection:") public static UITextPosition getPosition(UITextField __self__, Selector __cmd__, UITextRange range, UITextLayoutDirection direction) { return __self__.getPosition(range, direction); }
        @Callback @BindSelector("rightViewRectForBounds:") public static @ByVal CGRect getRightViewRect(UITextField __self__, Selector __cmd__, @ByVal CGRect bounds) { return __self__.getRightViewRect(bounds); }
        @Callback @BindSelector("selectionRectsForRange:") public static NSArray getSelectionRects(UITextField __self__, Selector __cmd__, UITextRange range) { return __self__.getSelectionRects(range); }
        @Callback @BindSelector("textInRange:") public static String getText(UITextField __self__, Selector __cmd__, UITextRange range) { return __self__.getText(range); }
        @Callback @BindSelector("textRangeFromPosition:toPosition:") public static UITextRange getTextRange(UITextField __self__, Selector __cmd__, UITextPosition fromPosition, UITextPosition toPosition) { return __self__.getTextRange(fromPosition, toPosition); }
        @Callback @BindSelector("textRectForBounds:") public static @ByVal CGRect getTextRect(UITextField __self__, Selector __cmd__, @ByVal CGRect bounds) { return __self__.getTextRect(bounds); }
        @Callback @BindSelector("textStylingAtPosition:inDirection:") public static NSDictionary getTextStyling(UITextField __self__, Selector __cmd__, UITextPosition position, UITextStorageDirection direction) { return __self__.getTextStyling(position, direction); }
        @Callback @BindSelector("hasText") public static boolean hasText(UITextField __self__, Selector __cmd__) { return __self__.hasText(); }
        @Callback @BindSelector("insertDictationResult:") public static void insertDictationResult(UITextField __self__, Selector __cmd__, NSArray dictationResult) { __self__.insertDictationResult(dictationResult); }
        @Callback @BindSelector("insertDictationResultPlaceholder") public static NSObject insertDictationResultPlaceholder(UITextField __self__, Selector __cmd__) { return __self__.insertDictationResultPlaceholder(); }
        @Callback @BindSelector("insertText:") public static void insertText(UITextField __self__, Selector __cmd__, String text) { __self__.insertText(text); }
        @Callback @BindSelector("removeDictationResultPlaceholder:willInsertResult:") public static void removeDictationResultPlaceholder(UITextField __self__, Selector __cmd__, NSObject placeholder, boolean willInsertResult) { __self__.removeDictationResultPlaceholder(placeholder, willInsertResult); }
        @Callback @BindSelector("replaceRange:withText:") public static void replaceText(UITextField __self__, Selector __cmd__, UITextRange range, String text) { __self__.replaceText(range, text); }
        @Callback @BindSelector("setBaseWritingDirection:forRange:") public static void setBaseWritingDirection(UITextField __self__, Selector __cmd__, UITextWritingDirection writingDirection, UITextRange range) { __self__.setBaseWritingDirection(writingDirection, range); }
        @Callback @BindSelector("setMarkedText:selectedRange:") public static void setMarkedText(UITextField __self__, Selector __cmd__, String markedText, @ByVal NSRange selectedRange) { __self__.setMarkedText(markedText, selectedRange); }
        @Callback @BindSelector("shouldChangeTextInRange:replacementText:") public static boolean shouldChangeText(UITextField __self__, Selector __cmd__, UITextRange range, String text) { return __self__.shouldChangeText(range, text); }
        @Callback @BindSelector("unmarkText") public static void unmarkText(UITextField __self__, Selector __cmd__) { __self__.unmarkText(); }
    }
    /*</callbacks>*/

}
