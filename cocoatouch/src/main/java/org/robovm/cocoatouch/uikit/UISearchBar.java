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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html">UISearchBar Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UISearchBar /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UISearchBar /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UISearchBar /*</name>*/.class);

    public UISearchBar(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UISearchBar(SkipInit skipInit) { super(skipInit); }
    public UISearchBar() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector autocapitalizationType = Selector.register("autocapitalizationType");
    @Bridge private native static UITextAutocapitalizationType objc_getAutocapitalizationType(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static UITextAutocapitalizationType objc_getAutocapitalizationTypeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/autocapitalizationType">@property(nonatomic) UITextAutocapitalizationType autocapitalizationType</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITextAutocapitalizationType getAutocapitalizationType() {
        if (customClass) { return objc_getAutocapitalizationTypeSuper(getSuper(), autocapitalizationType); } else { return objc_getAutocapitalizationType(this, autocapitalizationType); }
    }
    
    private static final Selector setAutocapitalizationType$ = Selector.register("setAutocapitalizationType:");
    @Bridge private native static void objc_setAutocapitalizationType(UISearchBar __self__, Selector __cmd__, UITextAutocapitalizationType autocapitalizationType);
    @Bridge private native static void objc_setAutocapitalizationTypeSuper(ObjCSuper __super__, Selector __cmd__, UITextAutocapitalizationType autocapitalizationType);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/autocapitalizationType">@property(nonatomic) UITextAutocapitalizationType autocapitalizationType</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAutocapitalizationType(UITextAutocapitalizationType autocapitalizationType) {
        if (customClass) { objc_setAutocapitalizationTypeSuper(getSuper(), setAutocapitalizationType$, autocapitalizationType); } else { objc_setAutocapitalizationType(this, setAutocapitalizationType$, autocapitalizationType); }
    }
    
    private static final Selector autocorrectionType = Selector.register("autocorrectionType");
    @Bridge private native static UITextAutocorrectionType objc_getAutocorrectionType(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static UITextAutocorrectionType objc_getAutocorrectionTypeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/autocorrectionType">@property(nonatomic) UITextAutocorrectionType autocorrectionType</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITextAutocorrectionType getAutocorrectionType() {
        if (customClass) { return objc_getAutocorrectionTypeSuper(getSuper(), autocorrectionType); } else { return objc_getAutocorrectionType(this, autocorrectionType); }
    }
    
    private static final Selector setAutocorrectionType$ = Selector.register("setAutocorrectionType:");
    @Bridge private native static void objc_setAutocorrectionType(UISearchBar __self__, Selector __cmd__, UITextAutocorrectionType autocorrectionType);
    @Bridge private native static void objc_setAutocorrectionTypeSuper(ObjCSuper __super__, Selector __cmd__, UITextAutocorrectionType autocorrectionType);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/autocorrectionType">@property(nonatomic) UITextAutocorrectionType autocorrectionType</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAutocorrectionType(UITextAutocorrectionType autocorrectionType) {
        if (customClass) { objc_setAutocorrectionTypeSuper(getSuper(), setAutocorrectionType$, autocorrectionType); } else { objc_setAutocorrectionType(this, setAutocorrectionType$, autocorrectionType); }
    }
    
    private static final Selector backgroundImage = Selector.register("backgroundImage");
    @Bridge private native static UIImage objc_getBackgroundImage(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/backgroundImage">@property(nonatomic, retain) UIImage *backgroundImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getBackgroundImage() {
        if (customClass) { return objc_getBackgroundImageSuper(getSuper(), backgroundImage); } else { return objc_getBackgroundImage(this, backgroundImage); }
    }
    
    private static final Selector setBackgroundImage$ = Selector.register("setBackgroundImage:");
    @Bridge private native static void objc_setBackgroundImage(UISearchBar __self__, Selector __cmd__, UIImage backgroundImage);
    @Bridge private native static void objc_setBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage backgroundImage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/backgroundImage">@property(nonatomic, retain) UIImage *backgroundImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setBackgroundImage(UIImage backgroundImage) {
        if (customClass) { objc_setBackgroundImageSuper(getSuper(), setBackgroundImage$, backgroundImage); } else { objc_setBackgroundImage(this, setBackgroundImage$, backgroundImage); }
    }
    
    private static final Selector barStyle = Selector.register("barStyle");
    @Bridge private native static UIBarStyle objc_getBarStyle(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static UIBarStyle objc_getBarStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/barStyle">@property(nonatomic) UIBarStyle barStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarStyle getBarStyle() {
        if (customClass) { return objc_getBarStyleSuper(getSuper(), barStyle); } else { return objc_getBarStyle(this, barStyle); }
    }
    
    private static final Selector setBarStyle$ = Selector.register("setBarStyle:");
    @Bridge private native static void objc_setBarStyle(UISearchBar __self__, Selector __cmd__, UIBarStyle barStyle);
    @Bridge private native static void objc_setBarStyleSuper(ObjCSuper __super__, Selector __cmd__, UIBarStyle barStyle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/barStyle">@property(nonatomic) UIBarStyle barStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setBarStyle(UIBarStyle barStyle) {
        if (customClass) { objc_setBarStyleSuper(getSuper(), setBarStyle$, barStyle); } else { objc_setBarStyle(this, setBarStyle$, barStyle); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UISearchBarDelegate objc_getDelegate(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static UISearchBarDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/delegate">@property(nonatomic, assign) id&amp;lt;UISearchBarDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public UISearchBarDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UISearchBar __self__, Selector __cmd__, UISearchBarDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UISearchBarDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/delegate">@property(nonatomic, assign) id&amp;lt;UISearchBarDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDelegate(UISearchBarDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector inputAccessoryView = Selector.register("inputAccessoryView");
    @Bridge private native static UIView objc_getInputAccessoryView(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getInputAccessoryViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/inputAccessoryView">@property (nonatomic, readwrite, retain) UIView *inputAccessoryView</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIView getInputAccessoryView() {
        if (customClass) { return objc_getInputAccessoryViewSuper(getSuper(), inputAccessoryView); } else { return objc_getInputAccessoryView(this, inputAccessoryView); }
    }
    
    private static final Selector setInputAccessoryView$ = Selector.register("setInputAccessoryView:");
    @Bridge private native static void objc_setInputAccessoryView(UISearchBar __self__, Selector __cmd__, UIView inputAccessoryView);
    @Bridge private native static void objc_setInputAccessoryViewSuper(ObjCSuper __super__, Selector __cmd__, UIView inputAccessoryView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/inputAccessoryView">@property (nonatomic, readwrite, retain) UIView *inputAccessoryView</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setInputAccessoryView(UIView inputAccessoryView) {
        if (customClass) { objc_setInputAccessoryViewSuper(getSuper(), setInputAccessoryView$, inputAccessoryView); } else { objc_setInputAccessoryView(this, setInputAccessoryView$, inputAccessoryView); }
    }
    
    private static final Selector keyboardType = Selector.register("keyboardType");
    @Bridge private native static UIKeyboardType objc_getKeyboardType(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static UIKeyboardType objc_getKeyboardTypeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/keyboardType">@property(nonatomic) UIKeyboardType keyboardType</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIKeyboardType getKeyboardType() {
        if (customClass) { return objc_getKeyboardTypeSuper(getSuper(), keyboardType); } else { return objc_getKeyboardType(this, keyboardType); }
    }
    
    private static final Selector setKeyboardType$ = Selector.register("setKeyboardType:");
    @Bridge private native static void objc_setKeyboardType(UISearchBar __self__, Selector __cmd__, UIKeyboardType keyboardType);
    @Bridge private native static void objc_setKeyboardTypeSuper(ObjCSuper __super__, Selector __cmd__, UIKeyboardType keyboardType);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/keyboardType">@property(nonatomic) UIKeyboardType keyboardType</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setKeyboardType(UIKeyboardType keyboardType) {
        if (customClass) { objc_setKeyboardTypeSuper(getSuper(), setKeyboardType$, keyboardType); } else { objc_setKeyboardType(this, setKeyboardType$, keyboardType); }
    }
    
    private static final Selector placeholder = Selector.register("placeholder");
    @Bridge private native static String objc_getPlaceholder(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static String objc_getPlaceholderSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/placeholder">@property(nonatomic, copy) NSString *placeholder</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getPlaceholder() {
        if (customClass) { return objc_getPlaceholderSuper(getSuper(), placeholder); } else { return objc_getPlaceholder(this, placeholder); }
    }
    
    private static final Selector setPlaceholder$ = Selector.register("setPlaceholder:");
    @Bridge private native static void objc_setPlaceholder(UISearchBar __self__, Selector __cmd__, String placeholder);
    @Bridge private native static void objc_setPlaceholderSuper(ObjCSuper __super__, Selector __cmd__, String placeholder);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/placeholder">@property(nonatomic, copy) NSString *placeholder</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setPlaceholder(String placeholder) {
        if (customClass) { objc_setPlaceholderSuper(getSuper(), setPlaceholder$, placeholder); } else { objc_setPlaceholder(this, setPlaceholder$, placeholder); }
    }
    
    private static final Selector prompt = Selector.register("prompt");
    @Bridge private native static String objc_getPrompt(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static String objc_getPromptSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/prompt">@property(nonatomic, copy) NSString *prompt</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getPrompt() {
        if (customClass) { return objc_getPromptSuper(getSuper(), prompt); } else { return objc_getPrompt(this, prompt); }
    }
    
    private static final Selector setPrompt$ = Selector.register("setPrompt:");
    @Bridge private native static void objc_setPrompt(UISearchBar __self__, Selector __cmd__, String prompt);
    @Bridge private native static void objc_setPromptSuper(ObjCSuper __super__, Selector __cmd__, String prompt);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/prompt">@property(nonatomic, copy) NSString *prompt</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setPrompt(String prompt) {
        if (customClass) { objc_setPromptSuper(getSuper(), setPrompt$, prompt); } else { objc_setPrompt(this, setPrompt$, prompt); }
    }
    
    private static final Selector scopeBarBackgroundImage = Selector.register("scopeBarBackgroundImage");
    @Bridge private native static UIImage objc_getScopeBarBackgroundImage(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getScopeBarBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/scopeBarBackgroundImage">@property(nonatomic, retain) UIImage *scopeBarBackgroundImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getScopeBarBackgroundImage() {
        if (customClass) { return objc_getScopeBarBackgroundImageSuper(getSuper(), scopeBarBackgroundImage); } else { return objc_getScopeBarBackgroundImage(this, scopeBarBackgroundImage); }
    }
    
    private static final Selector setScopeBarBackgroundImage$ = Selector.register("setScopeBarBackgroundImage:");
    @Bridge private native static void objc_setScopeBarBackgroundImage(UISearchBar __self__, Selector __cmd__, UIImage scopeBarBackgroundImage);
    @Bridge private native static void objc_setScopeBarBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage scopeBarBackgroundImage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/scopeBarBackgroundImage">@property(nonatomic, retain) UIImage *scopeBarBackgroundImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setScopeBarBackgroundImage(UIImage scopeBarBackgroundImage) {
        if (customClass) { objc_setScopeBarBackgroundImageSuper(getSuper(), setScopeBarBackgroundImage$, scopeBarBackgroundImage); } else { objc_setScopeBarBackgroundImage(this, setScopeBarBackgroundImage$, scopeBarBackgroundImage); }
    }
    
    private static final Selector scopeButtonTitles = Selector.register("scopeButtonTitles");
    @Bridge private native static NSArray objc_getScopeButtonTitles(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getScopeButtonTitlesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/scopeButtonTitles">@property(nonatomic, copy) NSArray *scopeButtonTitles</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSArray getScopeButtonTitles() {
        if (customClass) { return objc_getScopeButtonTitlesSuper(getSuper(), scopeButtonTitles); } else { return objc_getScopeButtonTitles(this, scopeButtonTitles); }
    }
    
    private static final Selector setScopeButtonTitles$ = Selector.register("setScopeButtonTitles:");
    @Bridge private native static void objc_setScopeButtonTitles(UISearchBar __self__, Selector __cmd__, NSArray scopeButtonTitles);
    @Bridge private native static void objc_setScopeButtonTitlesSuper(ObjCSuper __super__, Selector __cmd__, NSArray scopeButtonTitles);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/scopeButtonTitles">@property(nonatomic, copy) NSArray *scopeButtonTitles</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setScopeButtonTitles(NSArray scopeButtonTitles) {
        if (customClass) { objc_setScopeButtonTitlesSuper(getSuper(), setScopeButtonTitles$, scopeButtonTitles); } else { objc_setScopeButtonTitles(this, setScopeButtonTitles$, scopeButtonTitles); }
    }
    
    private static final Selector searchFieldBackgroundPositionAdjustment = Selector.register("searchFieldBackgroundPositionAdjustment");
    @Bridge private native static @ByVal UIOffset objc_getSearchFieldBackgroundPositionAdjustment(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static @ByVal UIOffset objc_getSearchFieldBackgroundPositionAdjustmentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/searchFieldBackgroundPositionAdjustment">@property(nonatomic) UIOffset searchFieldBackgroundPositionAdjustment</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIOffset getSearchFieldBackgroundPositionAdjustment() {
        if (customClass) { return objc_getSearchFieldBackgroundPositionAdjustmentSuper(getSuper(), searchFieldBackgroundPositionAdjustment); } else { return objc_getSearchFieldBackgroundPositionAdjustment(this, searchFieldBackgroundPositionAdjustment); }
    }
    
    private static final Selector setSearchFieldBackgroundPositionAdjustment$ = Selector.register("setSearchFieldBackgroundPositionAdjustment:");
    @Bridge private native static void objc_setSearchFieldBackgroundPositionAdjustment(UISearchBar __self__, Selector __cmd__, @ByVal UIOffset searchFieldBackgroundPositionAdjustment);
    @Bridge private native static void objc_setSearchFieldBackgroundPositionAdjustmentSuper(ObjCSuper __super__, Selector __cmd__, @ByVal UIOffset searchFieldBackgroundPositionAdjustment);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/searchFieldBackgroundPositionAdjustment">@property(nonatomic) UIOffset searchFieldBackgroundPositionAdjustment</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setSearchFieldBackgroundPositionAdjustment(UIOffset searchFieldBackgroundPositionAdjustment) {
        if (customClass) { objc_setSearchFieldBackgroundPositionAdjustmentSuper(getSuper(), setSearchFieldBackgroundPositionAdjustment$, searchFieldBackgroundPositionAdjustment); } else { objc_setSearchFieldBackgroundPositionAdjustment(this, setSearchFieldBackgroundPositionAdjustment$, searchFieldBackgroundPositionAdjustment); }
    }
    
    private static final Selector isSearchResultsButtonSelected = Selector.register("isSearchResultsButtonSelected");
    @Bridge private native static boolean objc_isSearchResultsButtonSelected(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isSearchResultsButtonSelectedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/searchResultsButtonSelected">@property(nonatomic, getter=isSearchResultsButtonSelected) BOOL searchResultsButtonSelected</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean isSearchResultsButtonSelected() {
        if (customClass) { return objc_isSearchResultsButtonSelectedSuper(getSuper(), isSearchResultsButtonSelected); } else { return objc_isSearchResultsButtonSelected(this, isSearchResultsButtonSelected); }
    }
    
    private static final Selector setSearchResultsButtonSelected$ = Selector.register("setSearchResultsButtonSelected:");
    @Bridge private native static void objc_setSearchResultsButtonSelected(UISearchBar __self__, Selector __cmd__, boolean searchResultsButtonSelected);
    @Bridge private native static void objc_setSearchResultsButtonSelectedSuper(ObjCSuper __super__, Selector __cmd__, boolean searchResultsButtonSelected);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/searchResultsButtonSelected">@property(nonatomic, getter=isSearchResultsButtonSelected) BOOL searchResultsButtonSelected</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setSearchResultsButtonSelected(boolean searchResultsButtonSelected) {
        if (customClass) { objc_setSearchResultsButtonSelectedSuper(getSuper(), setSearchResultsButtonSelected$, searchResultsButtonSelected); } else { objc_setSearchResultsButtonSelected(this, setSearchResultsButtonSelected$, searchResultsButtonSelected); }
    }
    
    private static final Selector searchTextPositionAdjustment = Selector.register("searchTextPositionAdjustment");
    @Bridge private native static @ByVal UIOffset objc_getSearchTextPositionAdjustment(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static @ByVal UIOffset objc_getSearchTextPositionAdjustmentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/searchTextPositionAdjustment">@property(nonatomic) UIOffset searchTextPositionAdjustment</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIOffset getSearchTextPositionAdjustment() {
        if (customClass) { return objc_getSearchTextPositionAdjustmentSuper(getSuper(), searchTextPositionAdjustment); } else { return objc_getSearchTextPositionAdjustment(this, searchTextPositionAdjustment); }
    }
    
    private static final Selector setSearchTextPositionAdjustment$ = Selector.register("setSearchTextPositionAdjustment:");
    @Bridge private native static void objc_setSearchTextPositionAdjustment(UISearchBar __self__, Selector __cmd__, @ByVal UIOffset searchTextPositionAdjustment);
    @Bridge private native static void objc_setSearchTextPositionAdjustmentSuper(ObjCSuper __super__, Selector __cmd__, @ByVal UIOffset searchTextPositionAdjustment);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/searchTextPositionAdjustment">@property(nonatomic) UIOffset searchTextPositionAdjustment</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setSearchTextPositionAdjustment(UIOffset searchTextPositionAdjustment) {
        if (customClass) { objc_setSearchTextPositionAdjustmentSuper(getSuper(), setSearchTextPositionAdjustment$, searchTextPositionAdjustment); } else { objc_setSearchTextPositionAdjustment(this, setSearchTextPositionAdjustment$, searchTextPositionAdjustment); }
    }
    
    private static final Selector selectedScopeButtonIndex = Selector.register("selectedScopeButtonIndex");
    @Bridge private native static int objc_getSelectedScopeButtonIndex(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static int objc_getSelectedScopeButtonIndexSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/selectedScopeButtonIndex">@property(nonatomic) NSInteger selectedScopeButtonIndex</a>
     * @since Available in iOS 3.0 and later.
     */
    public int getSelectedScopeButtonIndex() {
        if (customClass) { return objc_getSelectedScopeButtonIndexSuper(getSuper(), selectedScopeButtonIndex); } else { return objc_getSelectedScopeButtonIndex(this, selectedScopeButtonIndex); }
    }
    
    private static final Selector setSelectedScopeButtonIndex$ = Selector.register("setSelectedScopeButtonIndex:");
    @Bridge private native static void objc_setSelectedScopeButtonIndex(UISearchBar __self__, Selector __cmd__, int selectedScopeButtonIndex);
    @Bridge private native static void objc_setSelectedScopeButtonIndexSuper(ObjCSuper __super__, Selector __cmd__, int selectedScopeButtonIndex);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/selectedScopeButtonIndex">@property(nonatomic) NSInteger selectedScopeButtonIndex</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setSelectedScopeButtonIndex(int selectedScopeButtonIndex) {
        if (customClass) { objc_setSelectedScopeButtonIndexSuper(getSuper(), setSelectedScopeButtonIndex$, selectedScopeButtonIndex); } else { objc_setSelectedScopeButtonIndex(this, setSelectedScopeButtonIndex$, selectedScopeButtonIndex); }
    }
    
    private static final Selector showsBookmarkButton = Selector.register("showsBookmarkButton");
    @Bridge private native static boolean objc_isShowsBookmarkButton(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isShowsBookmarkButtonSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsBookmarkButton">@property(nonatomic) BOOL showsBookmarkButton</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isShowsBookmarkButton() {
        if (customClass) { return objc_isShowsBookmarkButtonSuper(getSuper(), showsBookmarkButton); } else { return objc_isShowsBookmarkButton(this, showsBookmarkButton); }
    }
    
    private static final Selector setShowsBookmarkButton$ = Selector.register("setShowsBookmarkButton:");
    @Bridge private native static void objc_setShowsBookmarkButton(UISearchBar __self__, Selector __cmd__, boolean showsBookmarkButton);
    @Bridge private native static void objc_setShowsBookmarkButtonSuper(ObjCSuper __super__, Selector __cmd__, boolean showsBookmarkButton);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsBookmarkButton">@property(nonatomic) BOOL showsBookmarkButton</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setShowsBookmarkButton(boolean showsBookmarkButton) {
        if (customClass) { objc_setShowsBookmarkButtonSuper(getSuper(), setShowsBookmarkButton$, showsBookmarkButton); } else { objc_setShowsBookmarkButton(this, setShowsBookmarkButton$, showsBookmarkButton); }
    }
    
    private static final Selector showsCancelButton = Selector.register("showsCancelButton");
    @Bridge private native static boolean objc_isShowsCancelButton(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isShowsCancelButtonSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsCancelButton">@property(nonatomic) BOOL showsCancelButton</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isShowsCancelButton() {
        if (customClass) { return objc_isShowsCancelButtonSuper(getSuper(), showsCancelButton); } else { return objc_isShowsCancelButton(this, showsCancelButton); }
    }
    
    private static final Selector setShowsCancelButton$ = Selector.register("setShowsCancelButton:");
    @Bridge private native static void objc_setShowsCancelButton(UISearchBar __self__, Selector __cmd__, boolean showsCancelButton);
    @Bridge private native static void objc_setShowsCancelButtonSuper(ObjCSuper __super__, Selector __cmd__, boolean showsCancelButton);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsCancelButton">@property(nonatomic) BOOL showsCancelButton</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setShowsCancelButton(boolean showsCancelButton) {
        if (customClass) { objc_setShowsCancelButtonSuper(getSuper(), setShowsCancelButton$, showsCancelButton); } else { objc_setShowsCancelButton(this, setShowsCancelButton$, showsCancelButton); }
    }
    
    private static final Selector showsScopeBar = Selector.register("showsScopeBar");
    @Bridge private native static boolean objc_isShowsScopeBar(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isShowsScopeBarSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsScopeBar">@property(nonatomic) BOOL showsScopeBar</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isShowsScopeBar() {
        if (customClass) { return objc_isShowsScopeBarSuper(getSuper(), showsScopeBar); } else { return objc_isShowsScopeBar(this, showsScopeBar); }
    }
    
    private static final Selector setShowsScopeBar$ = Selector.register("setShowsScopeBar:");
    @Bridge private native static void objc_setShowsScopeBar(UISearchBar __self__, Selector __cmd__, boolean showsScopeBar);
    @Bridge private native static void objc_setShowsScopeBarSuper(ObjCSuper __super__, Selector __cmd__, boolean showsScopeBar);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsScopeBar">@property(nonatomic) BOOL showsScopeBar</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setShowsScopeBar(boolean showsScopeBar) {
        if (customClass) { objc_setShowsScopeBarSuper(getSuper(), setShowsScopeBar$, showsScopeBar); } else { objc_setShowsScopeBar(this, setShowsScopeBar$, showsScopeBar); }
    }
    
    private static final Selector showsSearchResultsButton = Selector.register("showsSearchResultsButton");
    @Bridge private native static boolean objc_isShowsSearchResultsButton(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isShowsSearchResultsButtonSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsSearchResultsButton">@property(nonatomic) BOOL showsSearchResultsButton</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean isShowsSearchResultsButton() {
        if (customClass) { return objc_isShowsSearchResultsButtonSuper(getSuper(), showsSearchResultsButton); } else { return objc_isShowsSearchResultsButton(this, showsSearchResultsButton); }
    }
    
    private static final Selector setShowsSearchResultsButton$ = Selector.register("setShowsSearchResultsButton:");
    @Bridge private native static void objc_setShowsSearchResultsButton(UISearchBar __self__, Selector __cmd__, boolean showsSearchResultsButton);
    @Bridge private native static void objc_setShowsSearchResultsButtonSuper(ObjCSuper __super__, Selector __cmd__, boolean showsSearchResultsButton);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsSearchResultsButton">@property(nonatomic) BOOL showsSearchResultsButton</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setShowsSearchResultsButton(boolean showsSearchResultsButton) {
        if (customClass) { objc_setShowsSearchResultsButtonSuper(getSuper(), setShowsSearchResultsButton$, showsSearchResultsButton); } else { objc_setShowsSearchResultsButton(this, setShowsSearchResultsButton$, showsSearchResultsButton); }
    }
    
    private static final Selector spellCheckingType = Selector.register("spellCheckingType");
    @Bridge private native static UITextSpellCheckingType objc_getSpellCheckingType(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static UITextSpellCheckingType objc_getSpellCheckingTypeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/spellCheckingType">@property(nonatomic) UITextSpellCheckingType spellCheckingType</a>
     * @since Available in iOS 5.0 and later.
     */
    public UITextSpellCheckingType getSpellCheckingType() {
        if (customClass) { return objc_getSpellCheckingTypeSuper(getSuper(), spellCheckingType); } else { return objc_getSpellCheckingType(this, spellCheckingType); }
    }
    
    private static final Selector setSpellCheckingType$ = Selector.register("setSpellCheckingType:");
    @Bridge private native static void objc_setSpellCheckingType(UISearchBar __self__, Selector __cmd__, UITextSpellCheckingType spellCheckingType);
    @Bridge private native static void objc_setSpellCheckingTypeSuper(ObjCSuper __super__, Selector __cmd__, UITextSpellCheckingType spellCheckingType);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/spellCheckingType">@property(nonatomic) UITextSpellCheckingType spellCheckingType</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setSpellCheckingType(UITextSpellCheckingType spellCheckingType) {
        if (customClass) { objc_setSpellCheckingTypeSuper(getSuper(), setSpellCheckingType$, spellCheckingType); } else { objc_setSpellCheckingType(this, setSpellCheckingType$, spellCheckingType); }
    }
    
    private static final Selector text = Selector.register("text");
    @Bridge private native static String objc_getText(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static String objc_getTextSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getText() {
        if (customClass) { return objc_getTextSuper(getSuper(), text); } else { return objc_getText(this, text); }
    }
    
    private static final Selector setText$ = Selector.register("setText:");
    @Bridge private native static void objc_setText(UISearchBar __self__, Selector __cmd__, String text);
    @Bridge private native static void objc_setTextSuper(ObjCSuper __super__, Selector __cmd__, String text);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setText(String text) {
        if (customClass) { objc_setTextSuper(getSuper(), setText$, text); } else { objc_setText(this, setText$, text); }
    }
    
    private static final Selector tintColor = Selector.register("tintColor");
    @Bridge private native static UIColor objc_getTintColor(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor getTintColor() {
        if (customClass) { return objc_getTintColorSuper(getSuper(), tintColor); } else { return objc_getTintColor(this, tintColor); }
    }
    
    private static final Selector setTintColor$ = Selector.register("setTintColor:");
    @Bridge private native static void objc_setTintColor(UISearchBar __self__, Selector __cmd__, UIColor tintColor);
    @Bridge private native static void objc_setTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor tintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTintColor(UIColor tintColor) {
        if (customClass) { objc_setTintColorSuper(getSuper(), setTintColor$, tintColor); } else { objc_setTintColor(this, setTintColor$, tintColor); }
    }
    
    private static final Selector isTranslucent = Selector.register("isTranslucent");
    @Bridge private native static boolean objc_isTranslucent(UISearchBar __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isTranslucentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/translucent">@property(nonatomic, assign, getter=isTranslucent) BOOL translucent</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isTranslucent() {
        if (customClass) { return objc_isTranslucentSuper(getSuper(), isTranslucent); } else { return objc_isTranslucent(this, isTranslucent); }
    }
    
    private static final Selector setTranslucent$ = Selector.register("setTranslucent:");
    @Bridge private native static void objc_setTranslucent(UISearchBar __self__, Selector __cmd__, boolean translucent);
    @Bridge private native static void objc_setTranslucentSuper(ObjCSuper __super__, Selector __cmd__, boolean translucent);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/translucent">@property(nonatomic, assign, getter=isTranslucent) BOOL translucent</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setTranslucent(boolean translucent) {
        if (customClass) { objc_setTranslucentSuper(getSuper(), setTranslucent$, translucent); } else { objc_setTranslucent(this, setTranslucent$, translucent); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector imageForSearchBarIcon$state$ = Selector.register("imageForSearchBarIcon:state:");
    @Bridge private native static UIImage objc_getImageForSearchBarIcon(UISearchBar __self__, Selector __cmd__, UISearchBarIcon icon, UIControlState state);
    @Bridge private native static UIImage objc_getImageForSearchBarIconSuper(ObjCSuper __super__, Selector __cmd__, UISearchBarIcon icon, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/imageForSearchBarIcon:state:">- (UIImage *)imageForSearchBarIcon:(UISearchBarIcon)icon state:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getImageForSearchBarIcon(UISearchBarIcon icon, UIControlState state) {
        if (customClass) { return objc_getImageForSearchBarIconSuper(getSuper(), imageForSearchBarIcon$state$, icon, state); } else { return objc_getImageForSearchBarIcon(this, imageForSearchBarIcon$state$, icon, state); }
    }
    
    private static final Selector positionAdjustmentForSearchBarIcon$ = Selector.register("positionAdjustmentForSearchBarIcon:");
    @Bridge private native static @ByVal UIOffset objc_getPositionAdjustmentForSearchBarIcon(UISearchBar __self__, Selector __cmd__, UISearchBarIcon icon);
    @Bridge private native static @ByVal UIOffset objc_getPositionAdjustmentForSearchBarIconSuper(ObjCSuper __super__, Selector __cmd__, UISearchBarIcon icon);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/positionAdjustmentForSearchBarIcon:">- (UIOffset)positionAdjustmentForSearchBarIcon:(UISearchBarIcon)icon</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIOffset getPositionAdjustmentForSearchBarIcon(UISearchBarIcon icon) {
        if (customClass) { return objc_getPositionAdjustmentForSearchBarIconSuper(getSuper(), positionAdjustmentForSearchBarIcon$, icon); } else { return objc_getPositionAdjustmentForSearchBarIcon(this, positionAdjustmentForSearchBarIcon$, icon); }
    }
    
    private static final Selector scopeBarButtonBackgroundImageForState$ = Selector.register("scopeBarButtonBackgroundImageForState:");
    @Bridge private native static UIImage objc_getScopeBarButtonBackgroundImage(UISearchBar __self__, Selector __cmd__, UIControlState state);
    @Bridge private native static UIImage objc_getScopeBarButtonBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/scopeBarButtonBackgroundImageForState:">- (UIImage *)scopeBarButtonBackgroundImageForState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getScopeBarButtonBackgroundImage(UIControlState state) {
        if (customClass) { return objc_getScopeBarButtonBackgroundImageSuper(getSuper(), scopeBarButtonBackgroundImageForState$, state); } else { return objc_getScopeBarButtonBackgroundImage(this, scopeBarButtonBackgroundImageForState$, state); }
    }
    
    private static final Selector scopeBarButtonDividerImageForLeftSegmentState$rightSegmentState$ = Selector.register("scopeBarButtonDividerImageForLeftSegmentState:rightSegmentState:");
    @Bridge private native static UIImage objc_getScopeBarButtonDividerImage(UISearchBar __self__, Selector __cmd__, UIControlState leftState, UIControlState rightState);
    @Bridge private native static UIImage objc_getScopeBarButtonDividerImageSuper(ObjCSuper __super__, Selector __cmd__, UIControlState leftState, UIControlState rightState);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/scopeBarButtonDividerImageForLeftSegmentState:rightSegmentState:">- (UIImage *)scopeBarButtonDividerImageForLeftSegmentState:(UIControlState)leftState rightSegmentState:(UIControlState)rightState</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getScopeBarButtonDividerImage(UIControlState leftState, UIControlState rightState) {
        if (customClass) { return objc_getScopeBarButtonDividerImageSuper(getSuper(), scopeBarButtonDividerImageForLeftSegmentState$rightSegmentState$, leftState, rightState); } else { return objc_getScopeBarButtonDividerImage(this, scopeBarButtonDividerImageForLeftSegmentState$rightSegmentState$, leftState, rightState); }
    }
    
    private static final Selector scopeBarButtonTitleTextAttributesForState$ = Selector.register("scopeBarButtonTitleTextAttributesForState:");
    @Bridge private native static NSDictionary objc_getScopeBarButtonTitleTextAttributes(UISearchBar __self__, Selector __cmd__, UIControlState state);
    @Bridge private native static NSDictionary objc_getScopeBarButtonTitleTextAttributesSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/scopeBarButtonTitleTextAttributesForState:">- (NSDictionary *)scopeBarButtonTitleTextAttributesForState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSDictionary getScopeBarButtonTitleTextAttributes(UIControlState state) {
        if (customClass) { return objc_getScopeBarButtonTitleTextAttributesSuper(getSuper(), scopeBarButtonTitleTextAttributesForState$, state); } else { return objc_getScopeBarButtonTitleTextAttributes(this, scopeBarButtonTitleTextAttributesForState$, state); }
    }
    
    private static final Selector searchFieldBackgroundImageForState$ = Selector.register("searchFieldBackgroundImageForState:");
    @Bridge private native static UIImage objc_getSearchFieldBackgroundImage(UISearchBar __self__, Selector __cmd__, UIControlState state);
    @Bridge private native static UIImage objc_getSearchFieldBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/searchFieldBackgroundImageForState:">- (UIImage *)searchFieldBackgroundImageForState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getSearchFieldBackgroundImage(UIControlState state) {
        if (customClass) { return objc_getSearchFieldBackgroundImageSuper(getSuper(), searchFieldBackgroundImageForState$, state); } else { return objc_getSearchFieldBackgroundImage(this, searchFieldBackgroundImageForState$, state); }
    }
    
    private static final Selector setImage$forSearchBarIcon$state$ = Selector.register("setImage:forSearchBarIcon:state:");
    @Bridge private native static void objc_setImageForSearchBarIcon(UISearchBar __self__, Selector __cmd__, UIImage iconImage, UISearchBarIcon icon, UIControlState state);
    @Bridge private native static void objc_setImageForSearchBarIconSuper(ObjCSuper __super__, Selector __cmd__, UIImage iconImage, UISearchBarIcon icon, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setImage:forSearchBarIcon:state:">- (void)setImage:(UIImage *)iconImage forSearchBarIcon:(UISearchBarIcon)icon state:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setImageForSearchBarIcon(UIImage iconImage, UISearchBarIcon icon, UIControlState state) {
        if (customClass) { objc_setImageForSearchBarIconSuper(getSuper(), setImage$forSearchBarIcon$state$, iconImage, icon, state); } else { objc_setImageForSearchBarIcon(this, setImage$forSearchBarIcon$state$, iconImage, icon, state); }
    }
    
    private static final Selector setPositionAdjustment$forSearchBarIcon$ = Selector.register("setPositionAdjustment:forSearchBarIcon:");
    @Bridge private native static void objc_setPositionAdjustmentForSearchBarIcon(UISearchBar __self__, Selector __cmd__, @ByVal UIOffset adjustment, UISearchBarIcon icon);
    @Bridge private native static void objc_setPositionAdjustmentForSearchBarIconSuper(ObjCSuper __super__, Selector __cmd__, @ByVal UIOffset adjustment, UISearchBarIcon icon);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setPositionAdjustment:forSearchBarIcon:">- (void)setPositionAdjustment:(UIOffset)adjustment forSearchBarIcon:(UISearchBarIcon)icon</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setPositionAdjustmentForSearchBarIcon(UIOffset adjustment, UISearchBarIcon icon) {
        if (customClass) { objc_setPositionAdjustmentForSearchBarIconSuper(getSuper(), setPositionAdjustment$forSearchBarIcon$, adjustment, icon); } else { objc_setPositionAdjustmentForSearchBarIcon(this, setPositionAdjustment$forSearchBarIcon$, adjustment, icon); }
    }
    
    private static final Selector setScopeBarButtonBackgroundImage$forState$ = Selector.register("setScopeBarButtonBackgroundImage:forState:");
    @Bridge private native static void objc_setScopeBarButtonBackgroundImage(UISearchBar __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state);
    @Bridge private native static void objc_setScopeBarButtonBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage backgroundImage, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setScopeBarButtonBackgroundImage:forState:">- (void)setScopeBarButtonBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setScopeBarButtonBackgroundImage(UIImage backgroundImage, UIControlState state) {
        if (customClass) { objc_setScopeBarButtonBackgroundImageSuper(getSuper(), setScopeBarButtonBackgroundImage$forState$, backgroundImage, state); } else { objc_setScopeBarButtonBackgroundImage(this, setScopeBarButtonBackgroundImage$forState$, backgroundImage, state); }
    }
    
    private static final Selector setScopeBarButtonDividerImage$forLeftSegmentState$rightSegmentState$ = Selector.register("setScopeBarButtonDividerImage:forLeftSegmentState:rightSegmentState:");
    @Bridge private native static void objc_setScopeBarButtonDividerImage(UISearchBar __self__, Selector __cmd__, UIImage dividerImage, UIControlState leftState, UIControlState rightState);
    @Bridge private native static void objc_setScopeBarButtonDividerImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage dividerImage, UIControlState leftState, UIControlState rightState);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setScopeBarButtonDividerImage:forLeftSegmentState:rightSegmentState:">- (void)setScopeBarButtonDividerImage:(UIImage *)dividerImage forLeftSegmentState:(UIControlState)leftState rightSegmentState:(UIControlState)rightState</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setScopeBarButtonDividerImage(UIImage dividerImage, UIControlState leftState, UIControlState rightState) {
        if (customClass) { objc_setScopeBarButtonDividerImageSuper(getSuper(), setScopeBarButtonDividerImage$forLeftSegmentState$rightSegmentState$, dividerImage, leftState, rightState); } else { objc_setScopeBarButtonDividerImage(this, setScopeBarButtonDividerImage$forLeftSegmentState$rightSegmentState$, dividerImage, leftState, rightState); }
    }
    
    private static final Selector setScopeBarButtonTitleTextAttributes$forState$ = Selector.register("setScopeBarButtonTitleTextAttributes:forState:");
    @Bridge private native static void objc_setScopeBarButtonTitleTextAttributes(UISearchBar __self__, Selector __cmd__, NSDictionary attributes, UIControlState state);
    @Bridge private native static void objc_setScopeBarButtonTitleTextAttributesSuper(ObjCSuper __super__, Selector __cmd__, NSDictionary attributes, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setScopeBarButtonTitleTextAttributes:forState:">- (void)setScopeBarButtonTitleTextAttributes:(NSDictionary *)attributes forState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setScopeBarButtonTitleTextAttributes(NSDictionary attributes, UIControlState state) {
        if (customClass) { objc_setScopeBarButtonTitleTextAttributesSuper(getSuper(), setScopeBarButtonTitleTextAttributes$forState$, attributes, state); } else { objc_setScopeBarButtonTitleTextAttributes(this, setScopeBarButtonTitleTextAttributes$forState$, attributes, state); }
    }
    
    private static final Selector setSearchFieldBackgroundImage$forState$ = Selector.register("setSearchFieldBackgroundImage:forState:");
    @Bridge private native static void objc_setSearchFieldBackgroundImage(UISearchBar __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state);
    @Bridge private native static void objc_setSearchFieldBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage backgroundImage, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setSearchFieldBackgroundImage:forState:">- (void)setSearchFieldBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setSearchFieldBackgroundImage(UIImage backgroundImage, UIControlState state) {
        if (customClass) { objc_setSearchFieldBackgroundImageSuper(getSuper(), setSearchFieldBackgroundImage$forState$, backgroundImage, state); } else { objc_setSearchFieldBackgroundImage(this, setSearchFieldBackgroundImage$forState$, backgroundImage, state); }
    }
    
    private static final Selector setShowsCancelButton$animated$ = Selector.register("setShowsCancelButton:animated:");
    @Bridge private native static void objc_setShowsCancelButton(UISearchBar __self__, Selector __cmd__, boolean showsCancelButton, boolean animated);
    @Bridge private native static void objc_setShowsCancelButtonSuper(ObjCSuper __super__, Selector __cmd__, boolean showsCancelButton, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setShowsCancelButton:animated:">- (void)setShowsCancelButton:(BOOL)showsCancelButton animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setShowsCancelButton(boolean showsCancelButton, boolean animated) {
        if (customClass) { objc_setShowsCancelButtonSuper(getSuper(), setShowsCancelButton$animated$, showsCancelButton, animated); } else { objc_setShowsCancelButton(this, setShowsCancelButton$animated$, showsCancelButton, animated); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("autocapitalizationType") public static UITextAutocapitalizationType getAutocapitalizationType(UISearchBar __self__, Selector __cmd__) { return __self__.getAutocapitalizationType(); }
        @Callback @BindSelector("setAutocapitalizationType:") public static void setAutocapitalizationType(UISearchBar __self__, Selector __cmd__, UITextAutocapitalizationType autocapitalizationType) { __self__.setAutocapitalizationType(autocapitalizationType); }
        @Callback @BindSelector("autocorrectionType") public static UITextAutocorrectionType getAutocorrectionType(UISearchBar __self__, Selector __cmd__) { return __self__.getAutocorrectionType(); }
        @Callback @BindSelector("setAutocorrectionType:") public static void setAutocorrectionType(UISearchBar __self__, Selector __cmd__, UITextAutocorrectionType autocorrectionType) { __self__.setAutocorrectionType(autocorrectionType); }
        @Callback @BindSelector("backgroundImage") public static UIImage getBackgroundImage(UISearchBar __self__, Selector __cmd__) { return __self__.getBackgroundImage(); }
        @Callback @BindSelector("setBackgroundImage:") public static void setBackgroundImage(UISearchBar __self__, Selector __cmd__, UIImage backgroundImage) { __self__.setBackgroundImage(backgroundImage); }
        @Callback @BindSelector("barStyle") public static UIBarStyle getBarStyle(UISearchBar __self__, Selector __cmd__) { return __self__.getBarStyle(); }
        @Callback @BindSelector("setBarStyle:") public static void setBarStyle(UISearchBar __self__, Selector __cmd__, UIBarStyle barStyle) { __self__.setBarStyle(barStyle); }
        @Callback @BindSelector("delegate") public static UISearchBarDelegate getDelegate(UISearchBar __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UISearchBar __self__, Selector __cmd__, UISearchBarDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("inputAccessoryView") public static UIView getInputAccessoryView(UISearchBar __self__, Selector __cmd__) { return __self__.getInputAccessoryView(); }
        @Callback @BindSelector("setInputAccessoryView:") public static void setInputAccessoryView(UISearchBar __self__, Selector __cmd__, UIView inputAccessoryView) { __self__.setInputAccessoryView(inputAccessoryView); }
        @Callback @BindSelector("keyboardType") public static UIKeyboardType getKeyboardType(UISearchBar __self__, Selector __cmd__) { return __self__.getKeyboardType(); }
        @Callback @BindSelector("setKeyboardType:") public static void setKeyboardType(UISearchBar __self__, Selector __cmd__, UIKeyboardType keyboardType) { __self__.setKeyboardType(keyboardType); }
        @Callback @BindSelector("placeholder") public static String getPlaceholder(UISearchBar __self__, Selector __cmd__) { return __self__.getPlaceholder(); }
        @Callback @BindSelector("setPlaceholder:") public static void setPlaceholder(UISearchBar __self__, Selector __cmd__, String placeholder) { __self__.setPlaceholder(placeholder); }
        @Callback @BindSelector("prompt") public static String getPrompt(UISearchBar __self__, Selector __cmd__) { return __self__.getPrompt(); }
        @Callback @BindSelector("setPrompt:") public static void setPrompt(UISearchBar __self__, Selector __cmd__, String prompt) { __self__.setPrompt(prompt); }
        @Callback @BindSelector("scopeBarBackgroundImage") public static UIImage getScopeBarBackgroundImage(UISearchBar __self__, Selector __cmd__) { return __self__.getScopeBarBackgroundImage(); }
        @Callback @BindSelector("setScopeBarBackgroundImage:") public static void setScopeBarBackgroundImage(UISearchBar __self__, Selector __cmd__, UIImage scopeBarBackgroundImage) { __self__.setScopeBarBackgroundImage(scopeBarBackgroundImage); }
        @Callback @BindSelector("scopeButtonTitles") public static NSArray getScopeButtonTitles(UISearchBar __self__, Selector __cmd__) { return __self__.getScopeButtonTitles(); }
        @Callback @BindSelector("setScopeButtonTitles:") public static void setScopeButtonTitles(UISearchBar __self__, Selector __cmd__, NSArray scopeButtonTitles) { __self__.setScopeButtonTitles(scopeButtonTitles); }
        @Callback @BindSelector("searchFieldBackgroundPositionAdjustment") public static @ByVal UIOffset getSearchFieldBackgroundPositionAdjustment(UISearchBar __self__, Selector __cmd__) { return __self__.getSearchFieldBackgroundPositionAdjustment(); }
        @Callback @BindSelector("setSearchFieldBackgroundPositionAdjustment:") public static void setSearchFieldBackgroundPositionAdjustment(UISearchBar __self__, Selector __cmd__, @ByVal UIOffset searchFieldBackgroundPositionAdjustment) { __self__.setSearchFieldBackgroundPositionAdjustment(searchFieldBackgroundPositionAdjustment); }
        @Callback @BindSelector("isSearchResultsButtonSelected") public static boolean isSearchResultsButtonSelected(UISearchBar __self__, Selector __cmd__) { return __self__.isSearchResultsButtonSelected(); }
        @Callback @BindSelector("setSearchResultsButtonSelected:") public static void setSearchResultsButtonSelected(UISearchBar __self__, Selector __cmd__, boolean searchResultsButtonSelected) { __self__.setSearchResultsButtonSelected(searchResultsButtonSelected); }
        @Callback @BindSelector("searchTextPositionAdjustment") public static @ByVal UIOffset getSearchTextPositionAdjustment(UISearchBar __self__, Selector __cmd__) { return __self__.getSearchTextPositionAdjustment(); }
        @Callback @BindSelector("setSearchTextPositionAdjustment:") public static void setSearchTextPositionAdjustment(UISearchBar __self__, Selector __cmd__, @ByVal UIOffset searchTextPositionAdjustment) { __self__.setSearchTextPositionAdjustment(searchTextPositionAdjustment); }
        @Callback @BindSelector("selectedScopeButtonIndex") public static int getSelectedScopeButtonIndex(UISearchBar __self__, Selector __cmd__) { return __self__.getSelectedScopeButtonIndex(); }
        @Callback @BindSelector("setSelectedScopeButtonIndex:") public static void setSelectedScopeButtonIndex(UISearchBar __self__, Selector __cmd__, int selectedScopeButtonIndex) { __self__.setSelectedScopeButtonIndex(selectedScopeButtonIndex); }
        @Callback @BindSelector("showsBookmarkButton") public static boolean isShowsBookmarkButton(UISearchBar __self__, Selector __cmd__) { return __self__.isShowsBookmarkButton(); }
        @Callback @BindSelector("setShowsBookmarkButton:") public static void setShowsBookmarkButton(UISearchBar __self__, Selector __cmd__, boolean showsBookmarkButton) { __self__.setShowsBookmarkButton(showsBookmarkButton); }
        @Callback @BindSelector("showsCancelButton") public static boolean isShowsCancelButton(UISearchBar __self__, Selector __cmd__) { return __self__.isShowsCancelButton(); }
        @Callback @BindSelector("setShowsCancelButton:") public static void setShowsCancelButton(UISearchBar __self__, Selector __cmd__, boolean showsCancelButton) { __self__.setShowsCancelButton(showsCancelButton); }
        @Callback @BindSelector("showsScopeBar") public static boolean isShowsScopeBar(UISearchBar __self__, Selector __cmd__) { return __self__.isShowsScopeBar(); }
        @Callback @BindSelector("setShowsScopeBar:") public static void setShowsScopeBar(UISearchBar __self__, Selector __cmd__, boolean showsScopeBar) { __self__.setShowsScopeBar(showsScopeBar); }
        @Callback @BindSelector("showsSearchResultsButton") public static boolean isShowsSearchResultsButton(UISearchBar __self__, Selector __cmd__) { return __self__.isShowsSearchResultsButton(); }
        @Callback @BindSelector("setShowsSearchResultsButton:") public static void setShowsSearchResultsButton(UISearchBar __self__, Selector __cmd__, boolean showsSearchResultsButton) { __self__.setShowsSearchResultsButton(showsSearchResultsButton); }
        @Callback @BindSelector("spellCheckingType") public static UITextSpellCheckingType getSpellCheckingType(UISearchBar __self__, Selector __cmd__) { return __self__.getSpellCheckingType(); }
        @Callback @BindSelector("setSpellCheckingType:") public static void setSpellCheckingType(UISearchBar __self__, Selector __cmd__, UITextSpellCheckingType spellCheckingType) { __self__.setSpellCheckingType(spellCheckingType); }
        @Callback @BindSelector("text") public static String getText(UISearchBar __self__, Selector __cmd__) { return __self__.getText(); }
        @Callback @BindSelector("setText:") public static void setText(UISearchBar __self__, Selector __cmd__, String text) { __self__.setText(text); }
        @Callback @BindSelector("tintColor") public static UIColor getTintColor(UISearchBar __self__, Selector __cmd__) { return __self__.getTintColor(); }
        @Callback @BindSelector("setTintColor:") public static void setTintColor(UISearchBar __self__, Selector __cmd__, UIColor tintColor) { __self__.setTintColor(tintColor); }
        @Callback @BindSelector("isTranslucent") public static boolean isTranslucent(UISearchBar __self__, Selector __cmd__) { return __self__.isTranslucent(); }
        @Callback @BindSelector("setTranslucent:") public static void setTranslucent(UISearchBar __self__, Selector __cmd__, boolean translucent) { __self__.setTranslucent(translucent); }
        @Callback @BindSelector("imageForSearchBarIcon:state:") public static UIImage getImageForSearchBarIcon(UISearchBar __self__, Selector __cmd__, UISearchBarIcon icon, UIControlState state) { return __self__.getImageForSearchBarIcon(icon, state); }
        @Callback @BindSelector("positionAdjustmentForSearchBarIcon:") public static @ByVal UIOffset getPositionAdjustmentForSearchBarIcon(UISearchBar __self__, Selector __cmd__, UISearchBarIcon icon) { return __self__.getPositionAdjustmentForSearchBarIcon(icon); }
        @Callback @BindSelector("scopeBarButtonBackgroundImageForState:") public static UIImage getScopeBarButtonBackgroundImage(UISearchBar __self__, Selector __cmd__, UIControlState state) { return __self__.getScopeBarButtonBackgroundImage(state); }
        @Callback @BindSelector("scopeBarButtonDividerImageForLeftSegmentState:rightSegmentState:") public static UIImage getScopeBarButtonDividerImage(UISearchBar __self__, Selector __cmd__, UIControlState leftState, UIControlState rightState) { return __self__.getScopeBarButtonDividerImage(leftState, rightState); }
        @Callback @BindSelector("scopeBarButtonTitleTextAttributesForState:") public static NSDictionary getScopeBarButtonTitleTextAttributes(UISearchBar __self__, Selector __cmd__, UIControlState state) { return __self__.getScopeBarButtonTitleTextAttributes(state); }
        @Callback @BindSelector("searchFieldBackgroundImageForState:") public static UIImage getSearchFieldBackgroundImage(UISearchBar __self__, Selector __cmd__, UIControlState state) { return __self__.getSearchFieldBackgroundImage(state); }
        @Callback @BindSelector("setImage:forSearchBarIcon:state:") public static void setImageForSearchBarIcon(UISearchBar __self__, Selector __cmd__, UIImage iconImage, UISearchBarIcon icon, UIControlState state) { __self__.setImageForSearchBarIcon(iconImage, icon, state); }
        @Callback @BindSelector("setPositionAdjustment:forSearchBarIcon:") public static void setPositionAdjustmentForSearchBarIcon(UISearchBar __self__, Selector __cmd__, @ByVal UIOffset adjustment, UISearchBarIcon icon) { __self__.setPositionAdjustmentForSearchBarIcon(adjustment, icon); }
        @Callback @BindSelector("setScopeBarButtonBackgroundImage:forState:") public static void setScopeBarButtonBackgroundImage(UISearchBar __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state) { __self__.setScopeBarButtonBackgroundImage(backgroundImage, state); }
        @Callback @BindSelector("setScopeBarButtonDividerImage:forLeftSegmentState:rightSegmentState:") public static void setScopeBarButtonDividerImage(UISearchBar __self__, Selector __cmd__, UIImage dividerImage, UIControlState leftState, UIControlState rightState) { __self__.setScopeBarButtonDividerImage(dividerImage, leftState, rightState); }
        @Callback @BindSelector("setScopeBarButtonTitleTextAttributes:forState:") public static void setScopeBarButtonTitleTextAttributes(UISearchBar __self__, Selector __cmd__, NSDictionary attributes, UIControlState state) { __self__.setScopeBarButtonTitleTextAttributes(attributes, state); }
        @Callback @BindSelector("setSearchFieldBackgroundImage:forState:") public static void setSearchFieldBackgroundImage(UISearchBar __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state) { __self__.setSearchFieldBackgroundImage(backgroundImage, state); }
        @Callback @BindSelector("setShowsCancelButton:animated:") public static void setShowsCancelButton(UISearchBar __self__, Selector __cmd__, boolean showsCancelButton, boolean animated) { __self__.setShowsCancelButton(showsCancelButton, animated); }
    }
    /*</callbacks>*/

}
