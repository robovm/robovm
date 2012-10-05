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

    /*<constructors>*/
    public UISearchBar() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/autocapitalizationType">@property(nonatomic) UITextAutocapitalizationType autocapitalizationType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("autocapitalizationType") public native @Type("UITextAutocapitalizationType") UITextAutocapitalizationType getAutocapitalizationType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/autocapitalizationType">@property(nonatomic) UITextAutocapitalizationType autocapitalizationType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAutocapitalizationType:") public native void setAutocapitalizationType(@Type("UITextAutocapitalizationType") UITextAutocapitalizationType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/autocorrectionType">@property(nonatomic) UITextAutocorrectionType autocorrectionType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("autocorrectionType") public native @Type("UITextAutocorrectionType") UITextAutocorrectionType getAutocorrectionType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/autocorrectionType">@property(nonatomic) UITextAutocorrectionType autocorrectionType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAutocorrectionType:") public native void setAutocorrectionType(@Type("UITextAutocorrectionType") UITextAutocorrectionType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/backgroundImage">@property(nonatomic, retain) UIImage *backgroundImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("backgroundImage") public native @Type("UIImage *") UIImage getBackgroundImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/backgroundImage">@property(nonatomic, retain) UIImage *backgroundImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setBackgroundImage:") public native void setBackgroundImage(@Type("UIImage *") UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/barStyle">@property(nonatomic) UIBarStyle barStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("barStyle") public native @Type("UIBarStyle") UIBarStyle getBarStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/barStyle">@property(nonatomic) UIBarStyle barStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBarStyle:") public native void setBarStyle(@Type("UIBarStyle") UIBarStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/delegate">@property(nonatomic, assign) id&amp;lt;UISearchBarDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native @Type("id<UISearchBarDelegate>") UISearchBarDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/delegate">@property(nonatomic, assign) id&amp;lt;UISearchBarDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UISearchBarDelegate>") UISearchBarDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/inputAccessoryView">@property (nonatomic, readwrite, retain) UIView *inputAccessoryView</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("inputAccessoryView") public native @Type("UIView *") UIView getInputAccessoryView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/inputAccessoryView">@property (nonatomic, readwrite, retain) UIView *inputAccessoryView</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setInputAccessoryView:") public native void setInputAccessoryView(@Type("UIView *") UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/keyboardType">@property(nonatomic) UIKeyboardType keyboardType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("keyboardType") public native @Type("UIKeyboardType") UIKeyboardType getKeyboardType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/keyboardType">@property(nonatomic) UIKeyboardType keyboardType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setKeyboardType:") public native void setKeyboardType(@Type("UIKeyboardType") UIKeyboardType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/placeholder">@property(nonatomic, copy) NSString *placeholder</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("placeholder") public native @Type("NSString *") String getPlaceholder();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/placeholder">@property(nonatomic, copy) NSString *placeholder</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setPlaceholder:") public native void setPlaceholder(@Type("NSString *") String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/prompt">@property(nonatomic, copy) NSString *prompt</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("prompt") public native @Type("NSString *") String getPrompt();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/prompt">@property(nonatomic, copy) NSString *prompt</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setPrompt:") public native void setPrompt(@Type("NSString *") String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/scopeBarBackgroundImage">@property(nonatomic, retain) UIImage *scopeBarBackgroundImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("scopeBarBackgroundImage") public native @Type("UIImage *") UIImage getScopeBarBackgroundImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/scopeBarBackgroundImage">@property(nonatomic, retain) UIImage *scopeBarBackgroundImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setScopeBarBackgroundImage:") public native void setScopeBarBackgroundImage(@Type("UIImage *") UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/scopeButtonTitles">@property(nonatomic, copy) NSArray *scopeButtonTitles</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("scopeButtonTitles") public native @Type("NSArray *") NSArray getScopeButtonTitles();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/scopeButtonTitles">@property(nonatomic, copy) NSArray *scopeButtonTitles</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setScopeButtonTitles:") public native void setScopeButtonTitles(@Type("NSArray *") NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/searchFieldBackgroundPositionAdjustment">@property(nonatomic) UIOffset searchFieldBackgroundPositionAdjustment</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("searchFieldBackgroundPositionAdjustment") public native @Type("UIOffset") UIOffset getSearchFieldBackgroundPositionAdjustment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/searchFieldBackgroundPositionAdjustment">@property(nonatomic) UIOffset searchFieldBackgroundPositionAdjustment</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setSearchFieldBackgroundPositionAdjustment:") public native void setSearchFieldBackgroundPositionAdjustment(@Type("UIOffset") UIOffset v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/searchResultsButtonSelected">@property(nonatomic, getter=isSearchResultsButtonSelected) BOOL searchResultsButtonSelected</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("isSearchResultsButtonSelected") public native @Type("BOOL") boolean isSearchResultsButtonSelected();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/searchResultsButtonSelected">@property(nonatomic, getter=isSearchResultsButtonSelected) BOOL searchResultsButtonSelected</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setSearchResultsButtonSelected:") public native void setSearchResultsButtonSelected(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/searchTextPositionAdjustment">@property(nonatomic) UIOffset searchTextPositionAdjustment</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("searchTextPositionAdjustment") public native @Type("UIOffset") UIOffset getSearchTextPositionAdjustment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/searchTextPositionAdjustment">@property(nonatomic) UIOffset searchTextPositionAdjustment</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setSearchTextPositionAdjustment:") public native void setSearchTextPositionAdjustment(@Type("UIOffset") UIOffset v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/selectedScopeButtonIndex">@property(nonatomic) NSInteger selectedScopeButtonIndex</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("selectedScopeButtonIndex") public native @Type("NSInteger") int getSelectedScopeButtonIndex();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/selectedScopeButtonIndex">@property(nonatomic) NSInteger selectedScopeButtonIndex</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setSelectedScopeButtonIndex:") public native void setSelectedScopeButtonIndex(@Type("NSInteger") int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsBookmarkButton">@property(nonatomic) BOOL showsBookmarkButton</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("showsBookmarkButton") public native @Type("BOOL") boolean isShowsBookmarkButton();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsBookmarkButton">@property(nonatomic) BOOL showsBookmarkButton</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShowsBookmarkButton:") public native void setShowsBookmarkButton(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsCancelButton">@property(nonatomic) BOOL showsCancelButton</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("showsCancelButton") public native @Type("BOOL") boolean isShowsCancelButton();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsCancelButton">@property(nonatomic) BOOL showsCancelButton</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShowsCancelButton:") public native void setShowsCancelButton(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsScopeBar">@property(nonatomic) BOOL showsScopeBar</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("showsScopeBar") public native @Type("BOOL") boolean isShowsScopeBar();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsScopeBar">@property(nonatomic) BOOL showsScopeBar</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setShowsScopeBar:") public native void setShowsScopeBar(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsSearchResultsButton">@property(nonatomic) BOOL showsSearchResultsButton</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("showsSearchResultsButton") public native @Type("BOOL") boolean isShowsSearchResultsButton();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/showsSearchResultsButton">@property(nonatomic) BOOL showsSearchResultsButton</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setShowsSearchResultsButton:") public native void setShowsSearchResultsButton(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/spellCheckingType">@property(nonatomic) UITextSpellCheckingType spellCheckingType</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("spellCheckingType") public native @Type("UITextSpellCheckingType") UITextSpellCheckingType getSpellCheckingType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/spellCheckingType">@property(nonatomic) UITextSpellCheckingType spellCheckingType</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setSpellCheckingType:") public native void setSpellCheckingType(@Type("UITextSpellCheckingType") UITextSpellCheckingType v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("text") public native @Type("NSString *") String getText();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setText:") public native void setText(@Type("NSString *") String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/translucent">@property(nonatomic, assign, getter=isTranslucent) BOOL translucent</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isTranslucent") public native @Type("BOOL") boolean isTranslucent();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchBar/translucent">@property(nonatomic, assign, getter=isTranslucent) BOOL translucent</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setTranslucent:") public native void setTranslucent(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/imageForSearchBarIcon:state:">- (UIImage *)imageForSearchBarIcon:(UISearchBarIcon)icon state:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("imageForSearchBarIcon:state:") public native @Type("UIImage *") UIImage getImageForSearchBarIcon(@Type("UISearchBarIcon") UISearchBarIcon icon, @Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/positionAdjustmentForSearchBarIcon:">- (UIOffset)positionAdjustmentForSearchBarIcon:(UISearchBarIcon)icon</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("positionAdjustmentForSearchBarIcon:") public native @Type("UIOffset") UIOffset getPositionAdjustmentForSearchBarIcon(@Type("UISearchBarIcon") UISearchBarIcon icon);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/scopeBarButtonBackgroundImageForState:">- (UIImage *)scopeBarButtonBackgroundImageForState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("scopeBarButtonBackgroundImageForState:") public native @Type("UIImage *") UIImage getScopeBarButtonBackgroundImage(@Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/scopeBarButtonDividerImageForLeftSegmentState:rightSegmentState:">- (UIImage *)scopeBarButtonDividerImageForLeftSegmentState:(UIControlState)leftState rightSegmentState:(UIControlState)rightState</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("scopeBarButtonDividerImageForLeftSegmentState:rightSegmentState:") public native @Type("UIImage *") UIImage getScopeBarButtonDividerImage(@Type("UIControlState") UIControlState leftState, @Type("UIControlState") UIControlState rightState);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/scopeBarButtonTitleTextAttributesForState:">- (NSDictionary *)scopeBarButtonTitleTextAttributesForState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("scopeBarButtonTitleTextAttributesForState:") public native @Type("NSDictionary *") NSDictionary getScopeBarButtonTitleTextAttributes(@Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/searchFieldBackgroundImageForState:">- (UIImage *)searchFieldBackgroundImageForState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("searchFieldBackgroundImageForState:") public native @Type("UIImage *") UIImage getSearchFieldBackgroundImage(@Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setImage:forSearchBarIcon:state:">- (void)setImage:(UIImage *)iconImage forSearchBarIcon:(UISearchBarIcon)icon state:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setImage:forSearchBarIcon:state:") public native @Type("void") void setImageForSearchBarIcon(@Type("UIImage *") UIImage iconImage, @Type("UISearchBarIcon") UISearchBarIcon icon, @Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setPositionAdjustment:forSearchBarIcon:">- (void)setPositionAdjustment:(UIOffset)adjustment forSearchBarIcon:(UISearchBarIcon)icon</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setPositionAdjustment:forSearchBarIcon:") public native @Type("void") void setPositionAdjustmentForSearchBarIcon(@Type("UIOffset") UIOffset adjustment, @Type("UISearchBarIcon") UISearchBarIcon icon);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setScopeBarButtonBackgroundImage:forState:">- (void)setScopeBarButtonBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setScopeBarButtonBackgroundImage:forState:") public native @Type("void") void setScopeBarButtonBackgroundImage(@Type("UIImage *") UIImage backgroundImage, @Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setScopeBarButtonDividerImage:forLeftSegmentState:rightSegmentState:">- (void)setScopeBarButtonDividerImage:(UIImage *)dividerImage forLeftSegmentState:(UIControlState)leftState rightSegmentState:(UIControlState)rightState</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setScopeBarButtonDividerImage:forLeftSegmentState:rightSegmentState:") public native @Type("void") void setScopeBarButtonDividerImage(@Type("UIImage *") UIImage dividerImage, @Type("UIControlState") UIControlState leftState, @Type("UIControlState") UIControlState rightState);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setScopeBarButtonTitleTextAttributes:forState:">- (void)setScopeBarButtonTitleTextAttributes:(NSDictionary *)attributes forState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setScopeBarButtonTitleTextAttributes:forState:") public native @Type("void") void setScopeBarButtonTitleTextAttributes(@Type("NSDictionary *") NSDictionary attributes, @Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setSearchFieldBackgroundImage:forState:">- (void)setSearchFieldBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setSearchFieldBackgroundImage:forState:") public native @Type("void") void setSearchFieldBackgroundImage(@Type("UIImage *") UIImage backgroundImage, @Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchBar/setShowsCancelButton:animated:">- (void)setShowsCancelButton:(BOOL)showsCancelButton animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setShowsCancelButton:animated:") public native @Type("void") void setShowsCancelButton(@Type("BOOL") boolean showsCancelButton, @Type("BOOL") boolean animated);
    /*</methods>*/

}
