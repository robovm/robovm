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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html">UISearchBar Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UISearchBar /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UISearchBar /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UISearchBar /*</name>*/.class);

    /*<constructors>*/
    protected UISearchBar(SkipInit skipInit) { super(skipInit); }
    public UISearchBar() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/autocapitalizationType">@property(nonatomic) UITextAutocapitalizationType autocapitalizationType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("autocapitalizationType") public native UITextAutocapitalizationType getAutocapitalizationType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/autocapitalizationType">@property(nonatomic) UITextAutocapitalizationType autocapitalizationType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAutocapitalizationType:") public native void setAutocapitalizationType(UITextAutocapitalizationType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/autocorrectionType">@property(nonatomic) UITextAutocorrectionType autocorrectionType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("autocorrectionType") public native UITextAutocorrectionType getAutocorrectionType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/autocorrectionType">@property(nonatomic) UITextAutocorrectionType autocorrectionType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAutocorrectionType:") public native void setAutocorrectionType(UITextAutocorrectionType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/backgroundImage">@property(nonatomic, retain) UIImage *backgroundImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("backgroundImage") public native UIImage getBackgroundImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/backgroundImage">@property(nonatomic, retain) UIImage *backgroundImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setBackgroundImage:") public native void setBackgroundImage(UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/barStyle">@property(nonatomic) UIBarStyle barStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("barStyle") public native UIBarStyle getBarStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/barStyle">@property(nonatomic) UIBarStyle barStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBarStyle:") public native void setBarStyle(UIBarStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/delegate">@property(nonatomic, assign) id&amp;lt;UISearchBarDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native UISearchBarDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/delegate">@property(nonatomic, assign) id&amp;lt;UISearchBarDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(UISearchBarDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/inputAccessoryView">@property (nonatomic, readwrite, retain) UIView *inputAccessoryView</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("inputAccessoryView") public native UIView getInputAccessoryView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/inputAccessoryView">@property (nonatomic, readwrite, retain) UIView *inputAccessoryView</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setInputAccessoryView:") public native void setInputAccessoryView(UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/keyboardType">@property(nonatomic) UIKeyboardType keyboardType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("keyboardType") public native UIKeyboardType getKeyboardType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/keyboardType">@property(nonatomic) UIKeyboardType keyboardType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setKeyboardType:") public native void setKeyboardType(UIKeyboardType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/placeholder">@property(nonatomic, copy) NSString *placeholder</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("placeholder") public native String getPlaceholder();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/placeholder">@property(nonatomic, copy) NSString *placeholder</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setPlaceholder:") public native void setPlaceholder(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/prompt">@property(nonatomic, copy) NSString *prompt</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("prompt") public native String getPrompt();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/prompt">@property(nonatomic, copy) NSString *prompt</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setPrompt:") public native void setPrompt(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/scopeBarBackgroundImage">@property(nonatomic, retain) UIImage *scopeBarBackgroundImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("scopeBarBackgroundImage") public native UIImage getScopeBarBackgroundImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/scopeBarBackgroundImage">@property(nonatomic, retain) UIImage *scopeBarBackgroundImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setScopeBarBackgroundImage:") public native void setScopeBarBackgroundImage(UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/scopeButtonTitles">@property(nonatomic, copy) NSArray *scopeButtonTitles</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("scopeButtonTitles") public native NSArray getScopeButtonTitles();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/scopeButtonTitles">@property(nonatomic, copy) NSArray *scopeButtonTitles</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setScopeButtonTitles:") public native void setScopeButtonTitles(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/searchFieldBackgroundPositionAdjustment">@property(nonatomic) UIOffset searchFieldBackgroundPositionAdjustment</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("searchFieldBackgroundPositionAdjustment") public native UIOffset getSearchFieldBackgroundPositionAdjustment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/searchFieldBackgroundPositionAdjustment">@property(nonatomic) UIOffset searchFieldBackgroundPositionAdjustment</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setSearchFieldBackgroundPositionAdjustment:") public native void setSearchFieldBackgroundPositionAdjustment(UIOffset v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/searchResultsButtonSelected">@property(nonatomic, getter=isSearchResultsButtonSelected) BOOL searchResultsButtonSelected</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("isSearchResultsButtonSelected") public native boolean isSearchResultsButtonSelected();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/searchResultsButtonSelected">@property(nonatomic, getter=isSearchResultsButtonSelected) BOOL searchResultsButtonSelected</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setSearchResultsButtonSelected:") public native void setSearchResultsButtonSelected(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/searchTextPositionAdjustment">@property(nonatomic) UIOffset searchTextPositionAdjustment</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("searchTextPositionAdjustment") public native UIOffset getSearchTextPositionAdjustment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/searchTextPositionAdjustment">@property(nonatomic) UIOffset searchTextPositionAdjustment</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setSearchTextPositionAdjustment:") public native void setSearchTextPositionAdjustment(UIOffset v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/selectedScopeButtonIndex">@property(nonatomic) NSInteger selectedScopeButtonIndex</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("selectedScopeButtonIndex") public native int getSelectedScopeButtonIndex();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/selectedScopeButtonIndex">@property(nonatomic) NSInteger selectedScopeButtonIndex</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setSelectedScopeButtonIndex:") public native void setSelectedScopeButtonIndex(int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsBookmarkButton">@property(nonatomic) BOOL showsBookmarkButton</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("showsBookmarkButton") public native boolean isShowsBookmarkButton();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsBookmarkButton">@property(nonatomic) BOOL showsBookmarkButton</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShowsBookmarkButton:") public native void setShowsBookmarkButton(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsCancelButton">@property(nonatomic) BOOL showsCancelButton</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("showsCancelButton") public native boolean isShowsCancelButton();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsCancelButton">@property(nonatomic) BOOL showsCancelButton</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShowsCancelButton:") public native void setShowsCancelButton(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsScopeBar">@property(nonatomic) BOOL showsScopeBar</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("showsScopeBar") public native boolean isShowsScopeBar();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsScopeBar">@property(nonatomic) BOOL showsScopeBar</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setShowsScopeBar:") public native void setShowsScopeBar(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsSearchResultsButton">@property(nonatomic) BOOL showsSearchResultsButton</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("showsSearchResultsButton") public native boolean isShowsSearchResultsButton();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsSearchResultsButton">@property(nonatomic) BOOL showsSearchResultsButton</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setShowsSearchResultsButton:") public native void setShowsSearchResultsButton(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/spellCheckingType">@property(nonatomic) UITextSpellCheckingType spellCheckingType</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("spellCheckingType") public native UITextSpellCheckingType getSpellCheckingType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/spellCheckingType">@property(nonatomic) UITextSpellCheckingType spellCheckingType</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setSpellCheckingType:") public native void setSpellCheckingType(UITextSpellCheckingType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("text") public native String getText();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setText:") public native void setText(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tintColor") public native UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/translucent">@property(nonatomic, assign, getter=isTranslucent) BOOL translucent</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isTranslucent") public native boolean isTranslucent();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/translucent">@property(nonatomic, assign, getter=isTranslucent) BOOL translucent</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setTranslucent:") public native void setTranslucent(boolean v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector imageForSearchBarIcon$state$ = Selector.register("imageForSearchBarIcon:state:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getImageForSearchBarIcon(UISearchBar __self__, Selector __cmd__, UISearchBarIcon icon, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getImageForSearchBarIconSuper(ObjCSuper __super__, UISearchBar __self__, Selector __cmd__, UISearchBarIcon icon, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/imageForSearchBarIcon:state:">- (UIImage *)imageForSearchBarIcon:(UISearchBarIcon)icon state:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getImageForSearchBarIcon(UISearchBarIcon icon, UIControlState state) {
        if (customClass) { return objc_getImageForSearchBarIconSuper(getSuper(), this, imageForSearchBarIcon$state$, icon, state); } else { return objc_getImageForSearchBarIcon(this, imageForSearchBarIcon$state$, icon, state); }
    }
    
    private static final Selector positionAdjustmentForSearchBarIcon$ = Selector.register("positionAdjustmentForSearchBarIcon:");
    @Bridge(symbol = "objc_msgSend") private native static UIOffset objc_getPositionAdjustmentForSearchBarIcon(UISearchBar __self__, Selector __cmd__, UISearchBarIcon icon);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIOffset objc_getPositionAdjustmentForSearchBarIconSuper(ObjCSuper __super__, UISearchBar __self__, Selector __cmd__, UISearchBarIcon icon);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/positionAdjustmentForSearchBarIcon:">- (UIOffset)positionAdjustmentForSearchBarIcon:(UISearchBarIcon)icon</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIOffset getPositionAdjustmentForSearchBarIcon(UISearchBarIcon icon) {
        if (customClass) { return objc_getPositionAdjustmentForSearchBarIconSuper(getSuper(), this, positionAdjustmentForSearchBarIcon$, icon); } else { return objc_getPositionAdjustmentForSearchBarIcon(this, positionAdjustmentForSearchBarIcon$, icon); }
    }
    
    private static final Selector scopeBarButtonBackgroundImageForState$ = Selector.register("scopeBarButtonBackgroundImageForState:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getScopeBarButtonBackgroundImage(UISearchBar __self__, Selector __cmd__, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getScopeBarButtonBackgroundImageSuper(ObjCSuper __super__, UISearchBar __self__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/scopeBarButtonBackgroundImageForState:">- (UIImage *)scopeBarButtonBackgroundImageForState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getScopeBarButtonBackgroundImage(UIControlState state) {
        if (customClass) { return objc_getScopeBarButtonBackgroundImageSuper(getSuper(), this, scopeBarButtonBackgroundImageForState$, state); } else { return objc_getScopeBarButtonBackgroundImage(this, scopeBarButtonBackgroundImageForState$, state); }
    }
    
    private static final Selector scopeBarButtonDividerImageForLeftSegmentState$rightSegmentState$ = Selector.register("scopeBarButtonDividerImageForLeftSegmentState:rightSegmentState:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getScopeBarButtonDividerImage(UISearchBar __self__, Selector __cmd__, UIControlState leftState, UIControlState rightState);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getScopeBarButtonDividerImageSuper(ObjCSuper __super__, UISearchBar __self__, Selector __cmd__, UIControlState leftState, UIControlState rightState);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/scopeBarButtonDividerImageForLeftSegmentState:rightSegmentState:">- (UIImage *)scopeBarButtonDividerImageForLeftSegmentState:(UIControlState)leftState rightSegmentState:(UIControlState)rightState</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getScopeBarButtonDividerImage(UIControlState leftState, UIControlState rightState) {
        if (customClass) { return objc_getScopeBarButtonDividerImageSuper(getSuper(), this, scopeBarButtonDividerImageForLeftSegmentState$rightSegmentState$, leftState, rightState); } else { return objc_getScopeBarButtonDividerImage(this, scopeBarButtonDividerImageForLeftSegmentState$rightSegmentState$, leftState, rightState); }
    }
    
    private static final Selector scopeBarButtonTitleTextAttributesForState$ = Selector.register("scopeBarButtonTitleTextAttributesForState:");
    @Bridge(symbol = "objc_msgSend") private native static NSDictionary objc_getScopeBarButtonTitleTextAttributes(UISearchBar __self__, Selector __cmd__, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSDictionary objc_getScopeBarButtonTitleTextAttributesSuper(ObjCSuper __super__, UISearchBar __self__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/scopeBarButtonTitleTextAttributesForState:">- (NSDictionary *)scopeBarButtonTitleTextAttributesForState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSDictionary getScopeBarButtonTitleTextAttributes(UIControlState state) {
        if (customClass) { return objc_getScopeBarButtonTitleTextAttributesSuper(getSuper(), this, scopeBarButtonTitleTextAttributesForState$, state); } else { return objc_getScopeBarButtonTitleTextAttributes(this, scopeBarButtonTitleTextAttributesForState$, state); }
    }
    
    private static final Selector searchFieldBackgroundImageForState$ = Selector.register("searchFieldBackgroundImageForState:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getSearchFieldBackgroundImage(UISearchBar __self__, Selector __cmd__, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getSearchFieldBackgroundImageSuper(ObjCSuper __super__, UISearchBar __self__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/searchFieldBackgroundImageForState:">- (UIImage *)searchFieldBackgroundImageForState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getSearchFieldBackgroundImage(UIControlState state) {
        if (customClass) { return objc_getSearchFieldBackgroundImageSuper(getSuper(), this, searchFieldBackgroundImageForState$, state); } else { return objc_getSearchFieldBackgroundImage(this, searchFieldBackgroundImageForState$, state); }
    }
    
    private static final Selector setImage$forSearchBarIcon$state$ = Selector.register("setImage:forSearchBarIcon:state:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setImageForSearchBarIcon(UISearchBar __self__, Selector __cmd__, UIImage iconImage, UISearchBarIcon icon, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setImageForSearchBarIconSuper(ObjCSuper __super__, UISearchBar __self__, Selector __cmd__, UIImage iconImage, UISearchBarIcon icon, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setImage:forSearchBarIcon:state:">- (void)setImage:(UIImage *)iconImage forSearchBarIcon:(UISearchBarIcon)icon state:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setImageForSearchBarIcon(UIImage iconImage, UISearchBarIcon icon, UIControlState state) {
        if (customClass) { objc_setImageForSearchBarIconSuper(getSuper(), this, setImage$forSearchBarIcon$state$, iconImage, icon, state); } else { objc_setImageForSearchBarIcon(this, setImage$forSearchBarIcon$state$, iconImage, icon, state); }
    }
    
    private static final Selector setPositionAdjustment$forSearchBarIcon$ = Selector.register("setPositionAdjustment:forSearchBarIcon:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setPositionAdjustmentForSearchBarIcon(UISearchBar __self__, Selector __cmd__, UIOffset adjustment, UISearchBarIcon icon);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setPositionAdjustmentForSearchBarIconSuper(ObjCSuper __super__, UISearchBar __self__, Selector __cmd__, UIOffset adjustment, UISearchBarIcon icon);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setPositionAdjustment:forSearchBarIcon:">- (void)setPositionAdjustment:(UIOffset)adjustment forSearchBarIcon:(UISearchBarIcon)icon</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setPositionAdjustmentForSearchBarIcon(UIOffset adjustment, UISearchBarIcon icon) {
        if (customClass) { objc_setPositionAdjustmentForSearchBarIconSuper(getSuper(), this, setPositionAdjustment$forSearchBarIcon$, adjustment, icon); } else { objc_setPositionAdjustmentForSearchBarIcon(this, setPositionAdjustment$forSearchBarIcon$, adjustment, icon); }
    }
    
    private static final Selector setScopeBarButtonBackgroundImage$forState$ = Selector.register("setScopeBarButtonBackgroundImage:forState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setScopeBarButtonBackgroundImage(UISearchBar __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setScopeBarButtonBackgroundImageSuper(ObjCSuper __super__, UISearchBar __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setScopeBarButtonBackgroundImage:forState:">- (void)setScopeBarButtonBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setScopeBarButtonBackgroundImage(UIImage backgroundImage, UIControlState state) {
        if (customClass) { objc_setScopeBarButtonBackgroundImageSuper(getSuper(), this, setScopeBarButtonBackgroundImage$forState$, backgroundImage, state); } else { objc_setScopeBarButtonBackgroundImage(this, setScopeBarButtonBackgroundImage$forState$, backgroundImage, state); }
    }
    
    private static final Selector setScopeBarButtonDividerImage$forLeftSegmentState$rightSegmentState$ = Selector.register("setScopeBarButtonDividerImage:forLeftSegmentState:rightSegmentState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setScopeBarButtonDividerImage(UISearchBar __self__, Selector __cmd__, UIImage dividerImage, UIControlState leftState, UIControlState rightState);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setScopeBarButtonDividerImageSuper(ObjCSuper __super__, UISearchBar __self__, Selector __cmd__, UIImage dividerImage, UIControlState leftState, UIControlState rightState);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setScopeBarButtonDividerImage:forLeftSegmentState:rightSegmentState:">- (void)setScopeBarButtonDividerImage:(UIImage *)dividerImage forLeftSegmentState:(UIControlState)leftState rightSegmentState:(UIControlState)rightState</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setScopeBarButtonDividerImage(UIImage dividerImage, UIControlState leftState, UIControlState rightState) {
        if (customClass) { objc_setScopeBarButtonDividerImageSuper(getSuper(), this, setScopeBarButtonDividerImage$forLeftSegmentState$rightSegmentState$, dividerImage, leftState, rightState); } else { objc_setScopeBarButtonDividerImage(this, setScopeBarButtonDividerImage$forLeftSegmentState$rightSegmentState$, dividerImage, leftState, rightState); }
    }
    
    private static final Selector setScopeBarButtonTitleTextAttributes$forState$ = Selector.register("setScopeBarButtonTitleTextAttributes:forState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setScopeBarButtonTitleTextAttributes(UISearchBar __self__, Selector __cmd__, NSDictionary attributes, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setScopeBarButtonTitleTextAttributesSuper(ObjCSuper __super__, UISearchBar __self__, Selector __cmd__, NSDictionary attributes, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setScopeBarButtonTitleTextAttributes:forState:">- (void)setScopeBarButtonTitleTextAttributes:(NSDictionary *)attributes forState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setScopeBarButtonTitleTextAttributes(NSDictionary attributes, UIControlState state) {
        if (customClass) { objc_setScopeBarButtonTitleTextAttributesSuper(getSuper(), this, setScopeBarButtonTitleTextAttributes$forState$, attributes, state); } else { objc_setScopeBarButtonTitleTextAttributes(this, setScopeBarButtonTitleTextAttributes$forState$, attributes, state); }
    }
    
    private static final Selector setSearchFieldBackgroundImage$forState$ = Selector.register("setSearchFieldBackgroundImage:forState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSearchFieldBackgroundImage(UISearchBar __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSearchFieldBackgroundImageSuper(ObjCSuper __super__, UISearchBar __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setSearchFieldBackgroundImage:forState:">- (void)setSearchFieldBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setSearchFieldBackgroundImage(UIImage backgroundImage, UIControlState state) {
        if (customClass) { objc_setSearchFieldBackgroundImageSuper(getSuper(), this, setSearchFieldBackgroundImage$forState$, backgroundImage, state); } else { objc_setSearchFieldBackgroundImage(this, setSearchFieldBackgroundImage$forState$, backgroundImage, state); }
    }
    
    private static final Selector setShowsCancelButton$animated$ = Selector.register("setShowsCancelButton:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setShowsCancelButton(UISearchBar __self__, Selector __cmd__, boolean showsCancelButton, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setShowsCancelButtonSuper(ObjCSuper __super__, UISearchBar __self__, Selector __cmd__, boolean showsCancelButton, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setShowsCancelButton:animated:">- (void)setShowsCancelButton:(BOOL)showsCancelButton animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setShowsCancelButton(boolean showsCancelButton, boolean animated) {
        if (customClass) { objc_setShowsCancelButtonSuper(getSuper(), this, setShowsCancelButton$animated$, showsCancelButton, animated); } else { objc_setShowsCancelButton(this, setShowsCancelButton$animated$, showsCancelButton, animated); }
    }
    /*</methods>*/

}
