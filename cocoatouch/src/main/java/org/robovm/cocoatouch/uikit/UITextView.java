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
import org.robovm.objc.annotation.*;
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
    
    private static final Selector allowsEditingTextAttributes = Selector.register("allowsEditingTextAttributes");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isAllowsEditingTextAttributes(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isAllowsEditingTextAttributesSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/allowsEditingTextAttributes">@property(nonatomic) BOOL allowsEditingTextAttributes</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean isAllowsEditingTextAttributes() {
        if (customClass) { return objc_isAllowsEditingTextAttributesSuper(getSuper(), this, allowsEditingTextAttributes); } else { return objc_isAllowsEditingTextAttributes(this, allowsEditingTextAttributes); }
    }
    
    private static final Selector setAllowsEditingTextAttributes$ = Selector.register("setAllowsEditingTextAttributes:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAllowsEditingTextAttributes(UITextView __self__, Selector __cmd__, boolean allowsEditingTextAttributes);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setAllowsEditingTextAttributesSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, boolean allowsEditingTextAttributes);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/allowsEditingTextAttributes">@property(nonatomic) BOOL allowsEditingTextAttributes</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setAllowsEditingTextAttributes(boolean allowsEditingTextAttributes) {
        if (customClass) { objc_setAllowsEditingTextAttributesSuper(getSuper(), this, setAllowsEditingTextAttributes$, allowsEditingTextAttributes); } else { objc_setAllowsEditingTextAttributes(this, setAllowsEditingTextAttributes$, allowsEditingTextAttributes); }
    }
    
    private static final Selector attributedText = Selector.register("attributedText");
    @Bridge(symbol = "objc_msgSend") private native static NSAttributedString objc_getAttributedText(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSAttributedString objc_getAttributedTextSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/attributedText">@property(nonatomic,copy) NSAttributedString *attributedText</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedString getAttributedText() {
        if (customClass) { return objc_getAttributedTextSuper(getSuper(), this, attributedText); } else { return objc_getAttributedText(this, attributedText); }
    }
    
    private static final Selector setAttributedText$ = Selector.register("setAttributedText:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAttributedText(UITextView __self__, Selector __cmd__, NSAttributedString attributedText);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setAttributedTextSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, NSAttributedString attributedText);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/attributedText">@property(nonatomic,copy) NSAttributedString *attributedText</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setAttributedText(NSAttributedString attributedText) {
        if (customClass) { objc_setAttributedTextSuper(getSuper(), this, setAttributedText$, attributedText); } else { objc_setAttributedText(this, setAttributedText$, attributedText); }
    }
    
    private static final Selector autocapitalizationType = Selector.register("autocapitalizationType");
    @Bridge(symbol = "objc_msgSend") private native static UITextAutocapitalizationType objc_getAutocapitalizationType(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextAutocapitalizationType objc_getAutocapitalizationTypeSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocapitalizationType">@property(nonatomic) UITextAutocapitalizationType autocapitalizationType</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITextAutocapitalizationType getAutocapitalizationType() {
        if (customClass) { return objc_getAutocapitalizationTypeSuper(getSuper(), this, autocapitalizationType); } else { return objc_getAutocapitalizationType(this, autocapitalizationType); }
    }
    
    private static final Selector setAutocapitalizationType$ = Selector.register("setAutocapitalizationType:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAutocapitalizationType(UITextView __self__, Selector __cmd__, UITextAutocapitalizationType autocapitalizationType);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setAutocapitalizationTypeSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextAutocapitalizationType autocapitalizationType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocapitalizationType">@property(nonatomic) UITextAutocapitalizationType autocapitalizationType</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAutocapitalizationType(UITextAutocapitalizationType autocapitalizationType) {
        if (customClass) { objc_setAutocapitalizationTypeSuper(getSuper(), this, setAutocapitalizationType$, autocapitalizationType); } else { objc_setAutocapitalizationType(this, setAutocapitalizationType$, autocapitalizationType); }
    }
    
    private static final Selector autocorrectionType = Selector.register("autocorrectionType");
    @Bridge(symbol = "objc_msgSend") private native static UITextAutocorrectionType objc_getAutocorrectionType(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextAutocorrectionType objc_getAutocorrectionTypeSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocorrectionType">@property(nonatomic) UITextAutocorrectionType autocorrectionType</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITextAutocorrectionType getAutocorrectionType() {
        if (customClass) { return objc_getAutocorrectionTypeSuper(getSuper(), this, autocorrectionType); } else { return objc_getAutocorrectionType(this, autocorrectionType); }
    }
    
    private static final Selector setAutocorrectionType$ = Selector.register("setAutocorrectionType:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAutocorrectionType(UITextView __self__, Selector __cmd__, UITextAutocorrectionType autocorrectionType);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setAutocorrectionTypeSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextAutocorrectionType autocorrectionType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/autocorrectionType">@property(nonatomic) UITextAutocorrectionType autocorrectionType</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAutocorrectionType(UITextAutocorrectionType autocorrectionType) {
        if (customClass) { objc_setAutocorrectionTypeSuper(getSuper(), this, setAutocorrectionType$, autocorrectionType); } else { objc_setAutocorrectionType(this, setAutocorrectionType$, autocorrectionType); }
    }
    
    private static final Selector beginningOfDocument = Selector.register("beginningOfDocument");
    @Bridge(symbol = "objc_msgSend") private native static UITextPosition objc_getBeginningOfDocument(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextPosition objc_getBeginningOfDocumentSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/beginningOfDocument">@property(nonatomic, readonly) UITextPosition *beginningOfDocument</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextPosition getBeginningOfDocument() {
        if (customClass) { return objc_getBeginningOfDocumentSuper(getSuper(), this, beginningOfDocument); } else { return objc_getBeginningOfDocument(this, beginningOfDocument); }
    }
    
    private static final Selector clearsOnInsertion = Selector.register("clearsOnInsertion");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isClearsOnInsertion(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isClearsOnInsertionSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/clearsOnInsertion">@property(nonatomic) BOOL clearsOnInsertion</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean isClearsOnInsertion() {
        if (customClass) { return objc_isClearsOnInsertionSuper(getSuper(), this, clearsOnInsertion); } else { return objc_isClearsOnInsertion(this, clearsOnInsertion); }
    }
    
    private static final Selector setClearsOnInsertion$ = Selector.register("setClearsOnInsertion:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setClearsOnInsertion(UITextView __self__, Selector __cmd__, boolean clearsOnInsertion);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setClearsOnInsertionSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, boolean clearsOnInsertion);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/clearsOnInsertion">@property(nonatomic) BOOL clearsOnInsertion</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setClearsOnInsertion(boolean clearsOnInsertion) {
        if (customClass) { objc_setClearsOnInsertionSuper(getSuper(), this, setClearsOnInsertion$, clearsOnInsertion); } else { objc_setClearsOnInsertion(this, setClearsOnInsertion$, clearsOnInsertion); }
    }
    
    private static final Selector dataDetectorTypes = Selector.register("dataDetectorTypes");
    @Bridge(symbol = "objc_msgSend") private native static UIDataDetectorTypes objc_getDataDetectorTypes(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIDataDetectorTypes objc_getDataDetectorTypesSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/dataDetectorTypes">@property(nonatomic) UIDataDetectorTypes dataDetectorTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    public UIDataDetectorTypes getDataDetectorTypes() {
        if (customClass) { return objc_getDataDetectorTypesSuper(getSuper(), this, dataDetectorTypes); } else { return objc_getDataDetectorTypes(this, dataDetectorTypes); }
    }
    
    private static final Selector setDataDetectorTypes$ = Selector.register("setDataDetectorTypes:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setDataDetectorTypes(UITextView __self__, Selector __cmd__, UIDataDetectorTypes dataDetectorTypes);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setDataDetectorTypesSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UIDataDetectorTypes dataDetectorTypes);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/dataDetectorTypes">@property(nonatomic) UIDataDetectorTypes dataDetectorTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setDataDetectorTypes(UIDataDetectorTypes dataDetectorTypes) {
        if (customClass) { objc_setDataDetectorTypesSuper(getSuper(), this, setDataDetectorTypes$, dataDetectorTypes); } else { objc_setDataDetectorTypes(this, setDataDetectorTypes$, dataDetectorTypes); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge(symbol = "objc_msgSend") private native static UITextViewDelegate objc_getDelegate(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextViewDelegate objc_getDelegateSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/delegate">@property(nonatomic, assign) id&amp;lt;UITextViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITextViewDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), this, delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setDelegate(UITextView __self__, Selector __cmd__, UITextViewDelegate delegate);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setDelegateSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextViewDelegate delegate);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/delegate">@property(nonatomic, assign) id&amp;lt;UITextViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDelegate(UITextViewDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), this, setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector isEditable = Selector.register("isEditable");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isEditable(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isEditableSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/editable">@property(nonatomic, getter=isEditable) BOOL editable</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isEditable() {
        if (customClass) { return objc_isEditableSuper(getSuper(), this, isEditable); } else { return objc_isEditable(this, isEditable); }
    }
    
    private static final Selector setEditable$ = Selector.register("setEditable:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setEditable(UITextView __self__, Selector __cmd__, boolean editable);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setEditableSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, boolean editable);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/editable">@property(nonatomic, getter=isEditable) BOOL editable</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setEditable(boolean editable) {
        if (customClass) { objc_setEditableSuper(getSuper(), this, setEditable$, editable); } else { objc_setEditable(this, setEditable$, editable); }
    }
    
    private static final Selector enablesReturnKeyAutomatically = Selector.register("enablesReturnKeyAutomatically");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isEnablesReturnKeyAutomatically(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isEnablesReturnKeyAutomaticallySuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/enablesReturnKeyAutomatically">@property(nonatomic) BOOL enablesReturnKeyAutomatically</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isEnablesReturnKeyAutomatically() {
        if (customClass) { return objc_isEnablesReturnKeyAutomaticallySuper(getSuper(), this, enablesReturnKeyAutomatically); } else { return objc_isEnablesReturnKeyAutomatically(this, enablesReturnKeyAutomatically); }
    }
    
    private static final Selector setEnablesReturnKeyAutomatically$ = Selector.register("setEnablesReturnKeyAutomatically:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setEnablesReturnKeyAutomatically(UITextView __self__, Selector __cmd__, boolean enablesReturnKeyAutomatically);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setEnablesReturnKeyAutomaticallySuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, boolean enablesReturnKeyAutomatically);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/enablesReturnKeyAutomatically">@property(nonatomic) BOOL enablesReturnKeyAutomatically</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setEnablesReturnKeyAutomatically(boolean enablesReturnKeyAutomatically) {
        if (customClass) { objc_setEnablesReturnKeyAutomaticallySuper(getSuper(), this, setEnablesReturnKeyAutomatically$, enablesReturnKeyAutomatically); } else { objc_setEnablesReturnKeyAutomatically(this, setEnablesReturnKeyAutomatically$, enablesReturnKeyAutomatically); }
    }
    
    private static final Selector endOfDocument = Selector.register("endOfDocument");
    @Bridge(symbol = "objc_msgSend") private native static UITextPosition objc_getEndOfDocument(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextPosition objc_getEndOfDocumentSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/endOfDocument">@property(nonatomic, readonly) UITextPosition *endOfDocument</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextPosition getEndOfDocument() {
        if (customClass) { return objc_getEndOfDocumentSuper(getSuper(), this, endOfDocument); } else { return objc_getEndOfDocument(this, endOfDocument); }
    }
    
    private static final Selector font = Selector.register("font");
    @Bridge(symbol = "objc_msgSend") private native static UIFont objc_getFont(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIFont objc_getFontSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/font">@property(nonatomic, retain) UIFont *font</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIFont getFont() {
        if (customClass) { return objc_getFontSuper(getSuper(), this, font); } else { return objc_getFont(this, font); }
    }
    
    private static final Selector setFont$ = Selector.register("setFont:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setFont(UITextView __self__, Selector __cmd__, UIFont font);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setFontSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UIFont font);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/font">@property(nonatomic, retain) UIFont *font</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setFont(UIFont font) {
        if (customClass) { objc_setFontSuper(getSuper(), this, setFont$, font); } else { objc_setFont(this, setFont$, font); }
    }
    
    private static final Selector inputAccessoryView = Selector.register("inputAccessoryView");
    @Bridge(symbol = "objc_msgSend") private native static UIView objc_getInputAccessoryView(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIView objc_getInputAccessoryViewSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/inputAccessoryView">@property (readwrite, retain) UIView *inputAccessoryView</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIView getInputAccessoryView() {
        if (customClass) { return objc_getInputAccessoryViewSuper(getSuper(), this, inputAccessoryView); } else { return objc_getInputAccessoryView(this, inputAccessoryView); }
    }
    
    private static final Selector setInputAccessoryView$ = Selector.register("setInputAccessoryView:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setInputAccessoryView(UITextView __self__, Selector __cmd__, UIView inputAccessoryView);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setInputAccessoryViewSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UIView inputAccessoryView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/inputAccessoryView">@property (readwrite, retain) UIView *inputAccessoryView</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setInputAccessoryView(UIView inputAccessoryView) {
        if (customClass) { objc_setInputAccessoryViewSuper(getSuper(), this, setInputAccessoryView$, inputAccessoryView); } else { objc_setInputAccessoryView(this, setInputAccessoryView$, inputAccessoryView); }
    }
    
    private static final Selector inputDelegate = Selector.register("inputDelegate");
    @Bridge(symbol = "objc_msgSend") private native static UITextInputDelegate objc_getInputDelegate(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextInputDelegate objc_getInputDelegateSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/inputDelegate">@property(nonatomic, assign) id&amp;lt;UITextInputDelegate&amp;gt; inputDelegate</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextInputDelegate getInputDelegate() {
        if (customClass) { return objc_getInputDelegateSuper(getSuper(), this, inputDelegate); } else { return objc_getInputDelegate(this, inputDelegate); }
    }
    
    private static final Selector setInputDelegate$ = Selector.register("setInputDelegate:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setInputDelegate(UITextView __self__, Selector __cmd__, UITextInputDelegate inputDelegate);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setInputDelegateSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextInputDelegate inputDelegate);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/inputDelegate">@property(nonatomic, assign) id&amp;lt;UITextInputDelegate&amp;gt; inputDelegate</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setInputDelegate(UITextInputDelegate inputDelegate) {
        if (customClass) { objc_setInputDelegateSuper(getSuper(), this, setInputDelegate$, inputDelegate); } else { objc_setInputDelegate(this, setInputDelegate$, inputDelegate); }
    }
    
    private static final Selector inputView = Selector.register("inputView");
    @Bridge(symbol = "objc_msgSend") private native static UIView objc_getInputView(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIView objc_getInputViewSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/inputView">@property (readwrite, retain) UIView *inputView</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIView getInputView() {
        if (customClass) { return objc_getInputViewSuper(getSuper(), this, inputView); } else { return objc_getInputView(this, inputView); }
    }
    
    private static final Selector setInputView$ = Selector.register("setInputView:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setInputView(UITextView __self__, Selector __cmd__, UIView inputView);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setInputViewSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UIView inputView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/inputView">@property (readwrite, retain) UIView *inputView</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setInputView(UIView inputView) {
        if (customClass) { objc_setInputViewSuper(getSuper(), this, setInputView$, inputView); } else { objc_setInputView(this, setInputView$, inputView); }
    }
    
    private static final Selector keyboardAppearance = Selector.register("keyboardAppearance");
    @Bridge(symbol = "objc_msgSend") private native static UIKeyboardAppearance objc_getKeyboardAppearance(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIKeyboardAppearance objc_getKeyboardAppearanceSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardAppearance">@property(nonatomic) UIKeyboardAppearance keyboardAppearance</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIKeyboardAppearance getKeyboardAppearance() {
        if (customClass) { return objc_getKeyboardAppearanceSuper(getSuper(), this, keyboardAppearance); } else { return objc_getKeyboardAppearance(this, keyboardAppearance); }
    }
    
    private static final Selector setKeyboardAppearance$ = Selector.register("setKeyboardAppearance:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setKeyboardAppearance(UITextView __self__, Selector __cmd__, UIKeyboardAppearance keyboardAppearance);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setKeyboardAppearanceSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UIKeyboardAppearance keyboardAppearance);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardAppearance">@property(nonatomic) UIKeyboardAppearance keyboardAppearance</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setKeyboardAppearance(UIKeyboardAppearance keyboardAppearance) {
        if (customClass) { objc_setKeyboardAppearanceSuper(getSuper(), this, setKeyboardAppearance$, keyboardAppearance); } else { objc_setKeyboardAppearance(this, setKeyboardAppearance$, keyboardAppearance); }
    }
    
    private static final Selector keyboardType = Selector.register("keyboardType");
    @Bridge(symbol = "objc_msgSend") private native static UIKeyboardType objc_getKeyboardType(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIKeyboardType objc_getKeyboardTypeSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardType">@property(nonatomic) UIKeyboardType keyboardType</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIKeyboardType getKeyboardType() {
        if (customClass) { return objc_getKeyboardTypeSuper(getSuper(), this, keyboardType); } else { return objc_getKeyboardType(this, keyboardType); }
    }
    
    private static final Selector setKeyboardType$ = Selector.register("setKeyboardType:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setKeyboardType(UITextView __self__, Selector __cmd__, UIKeyboardType keyboardType);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setKeyboardTypeSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UIKeyboardType keyboardType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/keyboardType">@property(nonatomic) UIKeyboardType keyboardType</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setKeyboardType(UIKeyboardType keyboardType) {
        if (customClass) { objc_setKeyboardTypeSuper(getSuper(), this, setKeyboardType$, keyboardType); } else { objc_setKeyboardType(this, setKeyboardType$, keyboardType); }
    }
    
    private static final Selector markedTextRange = Selector.register("markedTextRange");
    @Bridge(symbol = "objc_msgSend") private native static UITextRange objc_getMarkedTextRange(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextRange objc_getMarkedTextRangeSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/markedTextRange">@property(nonatomic, readonly) UITextRange *markedTextRange</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextRange getMarkedTextRange() {
        if (customClass) { return objc_getMarkedTextRangeSuper(getSuper(), this, markedTextRange); } else { return objc_getMarkedTextRange(this, markedTextRange); }
    }
    
    private static final Selector markedTextStyle = Selector.register("markedTextStyle");
    @Bridge(symbol = "objc_msgSend") private native static NSDictionary objc_getMarkedTextStyle(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSDictionary objc_getMarkedTextStyleSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/markedTextStyle">@property(nonatomic, copy) NSDictionary *markedTextStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    public NSDictionary getMarkedTextStyle() {
        if (customClass) { return objc_getMarkedTextStyleSuper(getSuper(), this, markedTextStyle); } else { return objc_getMarkedTextStyle(this, markedTextStyle); }
    }
    
    private static final Selector setMarkedTextStyle$ = Selector.register("setMarkedTextStyle:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setMarkedTextStyle(UITextView __self__, Selector __cmd__, NSDictionary markedTextStyle);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setMarkedTextStyleSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, NSDictionary markedTextStyle);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/markedTextStyle">@property(nonatomic, copy) NSDictionary *markedTextStyle</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setMarkedTextStyle(NSDictionary markedTextStyle) {
        if (customClass) { objc_setMarkedTextStyleSuper(getSuper(), this, setMarkedTextStyle$, markedTextStyle); } else { objc_setMarkedTextStyle(this, setMarkedTextStyle$, markedTextStyle); }
    }
    
    private static final Selector returnKeyType = Selector.register("returnKeyType");
    @Bridge(symbol = "objc_msgSend") private native static UIReturnKeyType objc_getReturnKeyType(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIReturnKeyType objc_getReturnKeyTypeSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/returnKeyType">@property(nonatomic) UIReturnKeyType returnKeyType</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIReturnKeyType getReturnKeyType() {
        if (customClass) { return objc_getReturnKeyTypeSuper(getSuper(), this, returnKeyType); } else { return objc_getReturnKeyType(this, returnKeyType); }
    }
    
    private static final Selector setReturnKeyType$ = Selector.register("setReturnKeyType:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setReturnKeyType(UITextView __self__, Selector __cmd__, UIReturnKeyType returnKeyType);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setReturnKeyTypeSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UIReturnKeyType returnKeyType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/returnKeyType">@property(nonatomic) UIReturnKeyType returnKeyType</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setReturnKeyType(UIReturnKeyType returnKeyType) {
        if (customClass) { objc_setReturnKeyTypeSuper(getSuper(), this, setReturnKeyType$, returnKeyType); } else { objc_setReturnKeyType(this, setReturnKeyType$, returnKeyType); }
    }
    
    private static final Selector isSecureTextEntry = Selector.register("isSecureTextEntry");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isSecureTextEntry(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isSecureTextEntrySuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/secureTextEntry">@property(nonatomic, getter=isSecureTextEntry) BOOL secureTextEntry</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isSecureTextEntry() {
        if (customClass) { return objc_isSecureTextEntrySuper(getSuper(), this, isSecureTextEntry); } else { return objc_isSecureTextEntry(this, isSecureTextEntry); }
    }
    
    private static final Selector setSecureTextEntry$ = Selector.register("setSecureTextEntry:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSecureTextEntry(UITextView __self__, Selector __cmd__, boolean secureTextEntry);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSecureTextEntrySuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, boolean secureTextEntry);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/secureTextEntry">@property(nonatomic, getter=isSecureTextEntry) BOOL secureTextEntry</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSecureTextEntry(boolean secureTextEntry) {
        if (customClass) { objc_setSecureTextEntrySuper(getSuper(), this, setSecureTextEntry$, secureTextEntry); } else { objc_setSecureTextEntry(this, setSecureTextEntry$, secureTextEntry); }
    }
    
    private static final Selector selectedRange = Selector.register("selectedRange");
    @Bridge(symbol = "objc_msgSend") private native static NSRange objc_getSelectedRange(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSRange objc_getSelectedRangeSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/selectedRange">@property(nonatomic) NSRange selectedRange</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSRange getSelectedRange() {
        if (customClass) { return objc_getSelectedRangeSuper(getSuper(), this, selectedRange); } else { return objc_getSelectedRange(this, selectedRange); }
    }
    
    private static final Selector setSelectedRange$ = Selector.register("setSelectedRange:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSelectedRange(UITextView __self__, Selector __cmd__, NSRange selectedRange);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSelectedRangeSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, NSRange selectedRange);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/selectedRange">@property(nonatomic) NSRange selectedRange</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSelectedRange(NSRange selectedRange) {
        if (customClass) { objc_setSelectedRangeSuper(getSuper(), this, setSelectedRange$, selectedRange); } else { objc_setSelectedRange(this, setSelectedRange$, selectedRange); }
    }
    
    private static final Selector selectedTextRange = Selector.register("selectedTextRange");
    @Bridge(symbol = "objc_msgSend") private native static UITextRange objc_getSelectedTextRange(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextRange objc_getSelectedTextRangeSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectedTextRange">@property(readwrite, copy) UITextRange *selectedTextRange</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextRange getSelectedTextRange() {
        if (customClass) { return objc_getSelectedTextRangeSuper(getSuper(), this, selectedTextRange); } else { return objc_getSelectedTextRange(this, selectedTextRange); }
    }
    
    private static final Selector setSelectedTextRange$ = Selector.register("setSelectedTextRange:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSelectedTextRange(UITextView __self__, Selector __cmd__, UITextRange selectedTextRange);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSelectedTextRangeSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextRange selectedTextRange);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectedTextRange">@property(readwrite, copy) UITextRange *selectedTextRange</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setSelectedTextRange(UITextRange selectedTextRange) {
        if (customClass) { objc_setSelectedTextRangeSuper(getSuper(), this, setSelectedTextRange$, selectedTextRange); } else { objc_setSelectedTextRange(this, setSelectedTextRange$, selectedTextRange); }
    }
    
    private static final Selector selectionAffinity = Selector.register("selectionAffinity");
    @Bridge(symbol = "objc_msgSend") private native static UITextStorageDirection objc_getSelectionAffinity(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextStorageDirection objc_getSelectionAffinitySuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectionAffinity">@property(nonatomic) UITextStorageDirection selectionAffinity</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextStorageDirection getSelectionAffinity() {
        if (customClass) { return objc_getSelectionAffinitySuper(getSuper(), this, selectionAffinity); } else { return objc_getSelectionAffinity(this, selectionAffinity); }
    }
    
    private static final Selector setSelectionAffinity$ = Selector.register("setSelectionAffinity:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSelectionAffinity(UITextView __self__, Selector __cmd__, UITextStorageDirection selectionAffinity);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSelectionAffinitySuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextStorageDirection selectionAffinity);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/selectionAffinity">@property(nonatomic) UITextStorageDirection selectionAffinity</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setSelectionAffinity(UITextStorageDirection selectionAffinity) {
        if (customClass) { objc_setSelectionAffinitySuper(getSuper(), this, setSelectionAffinity$, selectionAffinity); } else { objc_setSelectionAffinity(this, setSelectionAffinity$, selectionAffinity); }
    }
    
    private static final Selector spellCheckingType = Selector.register("spellCheckingType");
    @Bridge(symbol = "objc_msgSend") private native static UITextSpellCheckingType objc_getSpellCheckingType(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextSpellCheckingType objc_getSpellCheckingTypeSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/spellCheckingType">@property(nonatomic) UITextSpellCheckingType spellCheckingType</a>
     * @since Available in iOS 5.0 and later.
     */
    public UITextSpellCheckingType getSpellCheckingType() {
        if (customClass) { return objc_getSpellCheckingTypeSuper(getSuper(), this, spellCheckingType); } else { return objc_getSpellCheckingType(this, spellCheckingType); }
    }
    
    private static final Selector setSpellCheckingType$ = Selector.register("setSpellCheckingType:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSpellCheckingType(UITextView __self__, Selector __cmd__, UITextSpellCheckingType spellCheckingType);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSpellCheckingTypeSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UITextSpellCheckingType spellCheckingType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTraits_Protocol/Reference/UITextInputTraits.html#//apple_ref/occ/intfp/UITextInputTraits/spellCheckingType">@property(nonatomic) UITextSpellCheckingType spellCheckingType</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setSpellCheckingType(UITextSpellCheckingType spellCheckingType) {
        if (customClass) { objc_setSpellCheckingTypeSuper(getSuper(), this, setSpellCheckingType$, spellCheckingType); } else { objc_setSpellCheckingType(this, setSpellCheckingType$, spellCheckingType); }
    }
    
    private static final Selector text = Selector.register("text");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getText(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getTextSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getText() {
        if (customClass) { return objc_getTextSuper(getSuper(), this, text); } else { return objc_getText(this, text); }
    }
    
    private static final Selector setText$ = Selector.register("setText:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setText(UITextView __self__, Selector __cmd__, String text);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTextSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, String text);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setText(String text) {
        if (customClass) { objc_setTextSuper(getSuper(), this, setText$, text); } else { objc_setText(this, setText$, text); }
    }
    
    private static final Selector textAlignment = Selector.register("textAlignment");
    @Bridge(symbol = "objc_msgSend") private native static NSTextAlignment objc_getTextAlignment(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSTextAlignment objc_getTextAlignmentSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/textAlignment">@property(nonatomic) NSTextAlignment textAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSTextAlignment getTextAlignment() {
        if (customClass) { return objc_getTextAlignmentSuper(getSuper(), this, textAlignment); } else { return objc_getTextAlignment(this, textAlignment); }
    }
    
    private static final Selector setTextAlignment$ = Selector.register("setTextAlignment:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTextAlignment(UITextView __self__, Selector __cmd__, NSTextAlignment textAlignment);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTextAlignmentSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, NSTextAlignment textAlignment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/textAlignment">@property(nonatomic) NSTextAlignment textAlignment</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTextAlignment(NSTextAlignment textAlignment) {
        if (customClass) { objc_setTextAlignmentSuper(getSuper(), this, setTextAlignment$, textAlignment); } else { objc_setTextAlignment(this, setTextAlignment$, textAlignment); }
    }
    
    private static final Selector textColor = Selector.register("textColor");
    @Bridge(symbol = "objc_msgSend") private native static UIColor objc_getTextColor(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIColor objc_getTextColorSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/textColor">@property(nonatomic, retain) UIColor *textColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor getTextColor() {
        if (customClass) { return objc_getTextColorSuper(getSuper(), this, textColor); } else { return objc_getTextColor(this, textColor); }
    }
    
    private static final Selector setTextColor$ = Selector.register("setTextColor:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTextColor(UITextView __self__, Selector __cmd__, UIColor textColor);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTextColorSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, UIColor textColor);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/textColor">@property(nonatomic, retain) UIColor *textColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTextColor(UIColor textColor) {
        if (customClass) { objc_setTextColorSuper(getSuper(), this, setTextColor$, textColor); } else { objc_setTextColor(this, setTextColor$, textColor); }
    }
    
    private static final Selector textInputView = Selector.register("textInputView");
    @Bridge(symbol = "objc_msgSend") private native static UIView objc_getTextInputView(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIView objc_getTextInputViewSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/textInputView">@property(nonatomic, readonly) UIView *textInputView</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIView getTextInputView() {
        if (customClass) { return objc_getTextInputViewSuper(getSuper(), this, textInputView); } else { return objc_getTextInputView(this, textInputView); }
    }
    
    private static final Selector tokenizer = Selector.register("tokenizer");
    @Bridge(symbol = "objc_msgSend") private native static UITextInputTokenizer objc_getTokenizer(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextInputTokenizer objc_getTokenizerSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInput_Protocol/Reference/Reference.html#//apple_ref/occ/intfp/UITextInput/tokenizer">@property(nonatomic, readonly) id&amp;lt;UITextInputTokenizer&amp;gt; tokenizer</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextInputTokenizer getTokenizer() {
        if (customClass) { return objc_getTokenizerSuper(getSuper(), this, tokenizer); } else { return objc_getTokenizer(this, tokenizer); }
    }
    
    private static final Selector typingAttributes = Selector.register("typingAttributes");
    @Bridge(symbol = "objc_msgSend") private native static NSDictionary objc_getTypingAttributes(UITextView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSDictionary objc_getTypingAttributesSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/typingAttributes">@property(nonatomic,copy) NSDictionary *typingAttributes;</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSDictionary getTypingAttributes() {
        if (customClass) { return objc_getTypingAttributesSuper(getSuper(), this, typingAttributes); } else { return objc_getTypingAttributes(this, typingAttributes); }
    }
    
    private static final Selector setTypingAttributes$ = Selector.register("setTypingAttributes:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTypingAttributes(UITextView __self__, Selector __cmd__, NSDictionary typingAttributes);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTypingAttributesSuper(ObjCSuper __super__, UITextView __self__, Selector __cmd__, NSDictionary typingAttributes);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextView_Class/Reference/UITextView.html#//apple_ref/occ/instp/UITextView/typingAttributes">@property(nonatomic,copy) NSDictionary *typingAttributes;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setTypingAttributes(NSDictionary typingAttributes) {
        if (customClass) { objc_setTypingAttributesSuper(getSuper(), this, setTypingAttributes$, typingAttributes); } else { objc_setTypingAttributes(this, setTypingAttributes$, typingAttributes); }
    }
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
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("allowsEditingTextAttributes") public static boolean isAllowsEditingTextAttributes(UITextView __self__, Selector __cmd__) { return __self__.isAllowsEditingTextAttributes(); }
        @Callback @BindSelector("setAllowsEditingTextAttributes:") public static void setAllowsEditingTextAttributes(UITextView __self__, Selector __cmd__, boolean allowsEditingTextAttributes) { __self__.setAllowsEditingTextAttributes(allowsEditingTextAttributes); }
        @Callback @BindSelector("attributedText") public static NSAttributedString getAttributedText(UITextView __self__, Selector __cmd__) { return __self__.getAttributedText(); }
        @Callback @BindSelector("setAttributedText:") public static void setAttributedText(UITextView __self__, Selector __cmd__, NSAttributedString attributedText) { __self__.setAttributedText(attributedText); }
        @Callback @BindSelector("autocapitalizationType") public static UITextAutocapitalizationType getAutocapitalizationType(UITextView __self__, Selector __cmd__) { return __self__.getAutocapitalizationType(); }
        @Callback @BindSelector("setAutocapitalizationType:") public static void setAutocapitalizationType(UITextView __self__, Selector __cmd__, UITextAutocapitalizationType autocapitalizationType) { __self__.setAutocapitalizationType(autocapitalizationType); }
        @Callback @BindSelector("autocorrectionType") public static UITextAutocorrectionType getAutocorrectionType(UITextView __self__, Selector __cmd__) { return __self__.getAutocorrectionType(); }
        @Callback @BindSelector("setAutocorrectionType:") public static void setAutocorrectionType(UITextView __self__, Selector __cmd__, UITextAutocorrectionType autocorrectionType) { __self__.setAutocorrectionType(autocorrectionType); }
        @Callback @BindSelector("beginningOfDocument") public static UITextPosition getBeginningOfDocument(UITextView __self__, Selector __cmd__) { return __self__.getBeginningOfDocument(); }
        @Callback @BindSelector("clearsOnInsertion") public static boolean isClearsOnInsertion(UITextView __self__, Selector __cmd__) { return __self__.isClearsOnInsertion(); }
        @Callback @BindSelector("setClearsOnInsertion:") public static void setClearsOnInsertion(UITextView __self__, Selector __cmd__, boolean clearsOnInsertion) { __self__.setClearsOnInsertion(clearsOnInsertion); }
        @Callback @BindSelector("dataDetectorTypes") public static UIDataDetectorTypes getDataDetectorTypes(UITextView __self__, Selector __cmd__) { return __self__.getDataDetectorTypes(); }
        @Callback @BindSelector("setDataDetectorTypes:") public static void setDataDetectorTypes(UITextView __self__, Selector __cmd__, UIDataDetectorTypes dataDetectorTypes) { __self__.setDataDetectorTypes(dataDetectorTypes); }
        @Callback @BindSelector("delegate") public static UITextViewDelegate getDelegate(UITextView __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UITextView __self__, Selector __cmd__, UITextViewDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("isEditable") public static boolean isEditable(UITextView __self__, Selector __cmd__) { return __self__.isEditable(); }
        @Callback @BindSelector("setEditable:") public static void setEditable(UITextView __self__, Selector __cmd__, boolean editable) { __self__.setEditable(editable); }
        @Callback @BindSelector("enablesReturnKeyAutomatically") public static boolean isEnablesReturnKeyAutomatically(UITextView __self__, Selector __cmd__) { return __self__.isEnablesReturnKeyAutomatically(); }
        @Callback @BindSelector("setEnablesReturnKeyAutomatically:") public static void setEnablesReturnKeyAutomatically(UITextView __self__, Selector __cmd__, boolean enablesReturnKeyAutomatically) { __self__.setEnablesReturnKeyAutomatically(enablesReturnKeyAutomatically); }
        @Callback @BindSelector("endOfDocument") public static UITextPosition getEndOfDocument(UITextView __self__, Selector __cmd__) { return __self__.getEndOfDocument(); }
        @Callback @BindSelector("font") public static UIFont getFont(UITextView __self__, Selector __cmd__) { return __self__.getFont(); }
        @Callback @BindSelector("setFont:") public static void setFont(UITextView __self__, Selector __cmd__, UIFont font) { __self__.setFont(font); }
        @Callback @BindSelector("inputAccessoryView") public static UIView getInputAccessoryView(UITextView __self__, Selector __cmd__) { return __self__.getInputAccessoryView(); }
        @Callback @BindSelector("setInputAccessoryView:") public static void setInputAccessoryView(UITextView __self__, Selector __cmd__, UIView inputAccessoryView) { __self__.setInputAccessoryView(inputAccessoryView); }
        @Callback @BindSelector("inputDelegate") public static UITextInputDelegate getInputDelegate(UITextView __self__, Selector __cmd__) { return __self__.getInputDelegate(); }
        @Callback @BindSelector("setInputDelegate:") public static void setInputDelegate(UITextView __self__, Selector __cmd__, UITextInputDelegate inputDelegate) { __self__.setInputDelegate(inputDelegate); }
        @Callback @BindSelector("inputView") public static UIView getInputView(UITextView __self__, Selector __cmd__) { return __self__.getInputView(); }
        @Callback @BindSelector("setInputView:") public static void setInputView(UITextView __self__, Selector __cmd__, UIView inputView) { __self__.setInputView(inputView); }
        @Callback @BindSelector("keyboardAppearance") public static UIKeyboardAppearance getKeyboardAppearance(UITextView __self__, Selector __cmd__) { return __self__.getKeyboardAppearance(); }
        @Callback @BindSelector("setKeyboardAppearance:") public static void setKeyboardAppearance(UITextView __self__, Selector __cmd__, UIKeyboardAppearance keyboardAppearance) { __self__.setKeyboardAppearance(keyboardAppearance); }
        @Callback @BindSelector("keyboardType") public static UIKeyboardType getKeyboardType(UITextView __self__, Selector __cmd__) { return __self__.getKeyboardType(); }
        @Callback @BindSelector("setKeyboardType:") public static void setKeyboardType(UITextView __self__, Selector __cmd__, UIKeyboardType keyboardType) { __self__.setKeyboardType(keyboardType); }
        @Callback @BindSelector("markedTextRange") public static UITextRange getMarkedTextRange(UITextView __self__, Selector __cmd__) { return __self__.getMarkedTextRange(); }
        @Callback @BindSelector("markedTextStyle") public static NSDictionary getMarkedTextStyle(UITextView __self__, Selector __cmd__) { return __self__.getMarkedTextStyle(); }
        @Callback @BindSelector("setMarkedTextStyle:") public static void setMarkedTextStyle(UITextView __self__, Selector __cmd__, NSDictionary markedTextStyle) { __self__.setMarkedTextStyle(markedTextStyle); }
        @Callback @BindSelector("returnKeyType") public static UIReturnKeyType getReturnKeyType(UITextView __self__, Selector __cmd__) { return __self__.getReturnKeyType(); }
        @Callback @BindSelector("setReturnKeyType:") public static void setReturnKeyType(UITextView __self__, Selector __cmd__, UIReturnKeyType returnKeyType) { __self__.setReturnKeyType(returnKeyType); }
        @Callback @BindSelector("isSecureTextEntry") public static boolean isSecureTextEntry(UITextView __self__, Selector __cmd__) { return __self__.isSecureTextEntry(); }
        @Callback @BindSelector("setSecureTextEntry:") public static void setSecureTextEntry(UITextView __self__, Selector __cmd__, boolean secureTextEntry) { __self__.setSecureTextEntry(secureTextEntry); }
        @Callback @BindSelector("selectedRange") public static NSRange getSelectedRange(UITextView __self__, Selector __cmd__) { return __self__.getSelectedRange(); }
        @Callback @BindSelector("setSelectedRange:") public static void setSelectedRange(UITextView __self__, Selector __cmd__, NSRange selectedRange) { __self__.setSelectedRange(selectedRange); }
        @Callback @BindSelector("selectedTextRange") public static UITextRange getSelectedTextRange(UITextView __self__, Selector __cmd__) { return __self__.getSelectedTextRange(); }
        @Callback @BindSelector("setSelectedTextRange:") public static void setSelectedTextRange(UITextView __self__, Selector __cmd__, UITextRange selectedTextRange) { __self__.setSelectedTextRange(selectedTextRange); }
        @Callback @BindSelector("selectionAffinity") public static UITextStorageDirection getSelectionAffinity(UITextView __self__, Selector __cmd__) { return __self__.getSelectionAffinity(); }
        @Callback @BindSelector("setSelectionAffinity:") public static void setSelectionAffinity(UITextView __self__, Selector __cmd__, UITextStorageDirection selectionAffinity) { __self__.setSelectionAffinity(selectionAffinity); }
        @Callback @BindSelector("spellCheckingType") public static UITextSpellCheckingType getSpellCheckingType(UITextView __self__, Selector __cmd__) { return __self__.getSpellCheckingType(); }
        @Callback @BindSelector("setSpellCheckingType:") public static void setSpellCheckingType(UITextView __self__, Selector __cmd__, UITextSpellCheckingType spellCheckingType) { __self__.setSpellCheckingType(spellCheckingType); }
        @Callback @BindSelector("text") public static String getText(UITextView __self__, Selector __cmd__) { return __self__.getText(); }
        @Callback @BindSelector("setText:") public static void setText(UITextView __self__, Selector __cmd__, String text) { __self__.setText(text); }
        @Callback @BindSelector("textAlignment") public static NSTextAlignment getTextAlignment(UITextView __self__, Selector __cmd__) { return __self__.getTextAlignment(); }
        @Callback @BindSelector("setTextAlignment:") public static void setTextAlignment(UITextView __self__, Selector __cmd__, NSTextAlignment textAlignment) { __self__.setTextAlignment(textAlignment); }
        @Callback @BindSelector("textColor") public static UIColor getTextColor(UITextView __self__, Selector __cmd__) { return __self__.getTextColor(); }
        @Callback @BindSelector("setTextColor:") public static void setTextColor(UITextView __self__, Selector __cmd__, UIColor textColor) { __self__.setTextColor(textColor); }
        @Callback @BindSelector("textInputView") public static UIView getTextInputView(UITextView __self__, Selector __cmd__) { return __self__.getTextInputView(); }
        @Callback @BindSelector("tokenizer") public static UITextInputTokenizer getTokenizer(UITextView __self__, Selector __cmd__) { return __self__.getTokenizer(); }
        @Callback @BindSelector("typingAttributes") public static NSDictionary getTypingAttributes(UITextView __self__, Selector __cmd__) { return __self__.getTypingAttributes(); }
        @Callback @BindSelector("setTypingAttributes:") public static void setTypingAttributes(UITextView __self__, Selector __cmd__, NSDictionary typingAttributes) { __self__.setTypingAttributes(typingAttributes); }
        @Callback @BindSelector("comparePosition:toPosition:") public static NSComparisonResult comparePositions(UITextView __self__, Selector __cmd__, UITextPosition position, UITextPosition other) { return __self__.comparePositions(position, other); }
        @Callback @BindSelector("deleteBackward") public static void deleteBackward(UITextView __self__, Selector __cmd__) { __self__.deleteBackward(); }
        @Callback @BindSelector("dictationRecognitionFailed") public static void dictationRecognitionFailed(UITextView __self__, Selector __cmd__) { __self__.dictationRecognitionFailed(); }
        @Callback @BindSelector("dictationRecordingDidEnd") public static void dictationRecordingDidEnd(UITextView __self__, Selector __cmd__) { __self__.dictationRecordingDidEnd(); }
        @Callback @BindSelector("baseWritingDirectionForPosition:inDirection:") public static UITextWritingDirection getBaseWritingDirection(UITextView __self__, Selector __cmd__, UITextPosition position, UITextStorageDirection direction) { return __self__.getBaseWritingDirection(position, direction); }
        @Callback @BindSelector("caretRectForPosition:") public static CGRect getCaretRect(UITextView __self__, Selector __cmd__, UITextPosition position) { return __self__.getCaretRect(position); }
        @Callback @BindSelector("characterOffsetOfPosition:withinRange:") public static int getCharacterOffset(UITextView __self__, Selector __cmd__, UITextPosition position, UITextRange range) { return __self__.getCharacterOffset(position, range); }
        @Callback @BindSelector("characterRangeByExtendingPosition:inDirection:") public static UITextRange getCharacterRange(UITextView __self__, Selector __cmd__, UITextPosition position, UITextLayoutDirection direction) { return __self__.getCharacterRange(position, direction); }
        @Callback @BindSelector("characterRangeAtPoint:") public static UITextRange getCharacterRange(UITextView __self__, Selector __cmd__, CGPoint point) { return __self__.getCharacterRange(point); }
        @Callback @BindSelector("closestPositionToPoint:withinRange:") public static UITextPosition getClosestPosition(UITextView __self__, Selector __cmd__, CGPoint point, UITextRange range) { return __self__.getClosestPosition(point, range); }
        @Callback @BindSelector("closestPositionToPoint:") public static UITextPosition getClosestPosition(UITextView __self__, Selector __cmd__, CGPoint point) { return __self__.getClosestPosition(point); }
        @Callback @BindSelector("frameForDictationResultPlaceholder:") public static CGRect getDictationResultPlaceholderFrame(UITextView __self__, Selector __cmd__, NSObject placeholder) { return __self__.getDictationResultPlaceholderFrame(placeholder); }
        @Callback @BindSelector("firstRectForRange:") public static CGRect getFirstRect(UITextView __self__, Selector __cmd__, UITextRange range) { return __self__.getFirstRect(range); }
        @Callback @BindSelector("offsetFromPosition:toPosition:") public static int getOffset(UITextView __self__, Selector __cmd__, UITextPosition fromPosition, UITextPosition toPosition) { return __self__.getOffset(fromPosition, toPosition); }
        @Callback @BindSelector("positionFromPosition:inDirection:offset:") public static UITextPosition getPosition(UITextView __self__, Selector __cmd__, UITextPosition position, UITextLayoutDirection direction, int offset) { return __self__.getPosition(position, direction, offset); }
        @Callback @BindSelector("positionFromPosition:offset:") public static UITextPosition getPosition(UITextView __self__, Selector __cmd__, UITextPosition position, int offset) { return __self__.getPosition(position, offset); }
        @Callback @BindSelector("positionWithinRange:farthestInDirection:") public static UITextPosition getPosition(UITextView __self__, Selector __cmd__, UITextRange range, UITextLayoutDirection direction) { return __self__.getPosition(range, direction); }
        @Callback @BindSelector("positionWithinRange:atCharacterOffset:") public static UITextPosition getPosition(UITextView __self__, Selector __cmd__, UITextRange range, int offset) { return __self__.getPosition(range, offset); }
        @Callback @BindSelector("selectionRectsForRange:") public static NSArray getSelectionRects(UITextView __self__, Selector __cmd__, UITextRange range) { return __self__.getSelectionRects(range); }
        @Callback @BindSelector("textInRange:") public static String getText(UITextView __self__, Selector __cmd__, UITextRange range) { return __self__.getText(range); }
        @Callback @BindSelector("textRangeFromPosition:toPosition:") public static UITextRange getTextRange(UITextView __self__, Selector __cmd__, UITextPosition fromPosition, UITextPosition toPosition) { return __self__.getTextRange(fromPosition, toPosition); }
        @Callback @BindSelector("textStylingAtPosition:inDirection:") public static NSDictionary getTextStyling(UITextView __self__, Selector __cmd__, UITextPosition position, UITextStorageDirection direction) { return __self__.getTextStyling(position, direction); }
        @Callback @BindSelector("hasText") public static boolean hasText(UITextView __self__, Selector __cmd__) { return __self__.hasText(); }
        @Callback @BindSelector("insertDictationResult:") public static void insertDictationResult(UITextView __self__, Selector __cmd__, NSArray dictationResult) { __self__.insertDictationResult(dictationResult); }
        @Callback @BindSelector("insertDictationResultPlaceholder") public static NSObject insertDictationResultPlaceholder(UITextView __self__, Selector __cmd__) { return __self__.insertDictationResultPlaceholder(); }
        @Callback @BindSelector("insertText:") public static void insertText(UITextView __self__, Selector __cmd__, String text) { __self__.insertText(text); }
        @Callback @BindSelector("removeDictationResultPlaceholder:willInsertResult:") public static void removeDictationResultPlaceholder(UITextView __self__, Selector __cmd__, NSObject placeholder, boolean willInsertResult) { __self__.removeDictationResultPlaceholder(placeholder, willInsertResult); }
        @Callback @BindSelector("replaceRange:withText:") public static void replaceText(UITextView __self__, Selector __cmd__, UITextRange range, String text) { __self__.replaceText(range, text); }
        @Callback @BindSelector("scrollRangeToVisible:") public static void scrollRangeToVisible(UITextView __self__, Selector __cmd__, NSRange range) { __self__.scrollRangeToVisible(range); }
        @Callback @BindSelector("setBaseWritingDirection:forRange:") public static void setBaseWritingDirection(UITextView __self__, Selector __cmd__, UITextWritingDirection writingDirection, UITextRange range) { __self__.setBaseWritingDirection(writingDirection, range); }
        @Callback @BindSelector("setMarkedText:selectedRange:") public static void setMarkedText(UITextView __self__, Selector __cmd__, String markedText, NSRange selectedRange) { __self__.setMarkedText(markedText, selectedRange); }
        @Callback @BindSelector("shouldChangeTextInRange:replacementText:") public static boolean shouldChangeText(UITextView __self__, Selector __cmd__, UITextRange range, String text) { return __self__.shouldChangeText(range, text); }
        @Callback @BindSelector("unmarkText") public static void unmarkText(UITextView __self__, Selector __cmd__) { __self__.unmarkText(); }
    }
    /*</callbacks>*/

}
