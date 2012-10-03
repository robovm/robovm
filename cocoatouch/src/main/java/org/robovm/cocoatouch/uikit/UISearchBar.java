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
    @Bind("autocapitalizationType") public native @Type("UITextAutocapitalizationType") UITextAutocapitalizationType getAutocapitalizationType();
    @Bind("setAutocapitalizationType:") public native void setAutocapitalizationType(@Type("UITextAutocapitalizationType") UITextAutocapitalizationType v);
    @Bind("autocorrectionType") public native @Type("UITextAutocorrectionType") UITextAutocorrectionType getAutocorrectionType();
    @Bind("setAutocorrectionType:") public native void setAutocorrectionType(@Type("UITextAutocorrectionType") UITextAutocorrectionType v);
    @Bind("backgroundImage") public native @Type("UIImage *") UIImage getBackgroundImage();
    @Bind("setBackgroundImage:") public native void setBackgroundImage(@Type("UIImage *") UIImage v);
    @Bind("barStyle") public native @Type("UIBarStyle") UIBarStyle getBarStyle();
    @Bind("setBarStyle:") public native void setBarStyle(@Type("UIBarStyle") UIBarStyle v);
    @Bind("delegate") public native @Type("id<UISearchBarDelegate>") UISearchBarDelegate getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UISearchBarDelegate>") UISearchBarDelegate v);
    @Bind("inputAccessoryView") public native @Type("UIView *") UIView getInputAccessoryView();
    @Bind("setInputAccessoryView:") public native void setInputAccessoryView(@Type("UIView *") UIView v);
    @Bind("keyboardType") public native @Type("UIKeyboardType") UIKeyboardType getKeyboardType();
    @Bind("setKeyboardType:") public native void setKeyboardType(@Type("UIKeyboardType") UIKeyboardType v);
    @Bind("placeholder") public native @Type("NSString *") String getPlaceholder();
    @Bind("setPlaceholder:") public native void setPlaceholder(@Type("NSString *") String v);
    @Bind("prompt") public native @Type("NSString *") String getPrompt();
    @Bind("setPrompt:") public native void setPrompt(@Type("NSString *") String v);
    @Bind("scopeBarBackgroundImage") public native @Type("UIImage *") UIImage getScopeBarBackgroundImage();
    @Bind("setScopeBarBackgroundImage:") public native void setScopeBarBackgroundImage(@Type("UIImage *") UIImage v);
    @Bind("scopeButtonTitles") public native @Type("NSArray *") NSArray getScopeButtonTitles();
    @Bind("setScopeButtonTitles:") public native void setScopeButtonTitles(@Type("NSArray *") NSArray v);
    @Bind("searchFieldBackgroundPositionAdjustment") public native @Type("UIOffset") UIOffset getSearchFieldBackgroundPositionAdjustment();
    @Bind("setSearchFieldBackgroundPositionAdjustment:") public native void setSearchFieldBackgroundPositionAdjustment(@Type("UIOffset") UIOffset v);
    @Bind("isSearchResultsButtonSelected") public native @Type("BOOL") boolean isSearchResultsButtonSelected();
    @Bind("setSearchResultsButtonSelected:") public native void setSearchResultsButtonSelected(@Type("BOOL") boolean v);
    @Bind("searchTextPositionAdjustment") public native @Type("UIOffset") UIOffset getSearchTextPositionAdjustment();
    @Bind("setSearchTextPositionAdjustment:") public native void setSearchTextPositionAdjustment(@Type("UIOffset") UIOffset v);
    @Bind("selectedScopeButtonIndex") public native @Type("NSInteger") int getSelectedScopeButtonIndex();
    @Bind("setSelectedScopeButtonIndex:") public native void setSelectedScopeButtonIndex(@Type("NSInteger") int v);
    @Bind("showsBookmarkButton") public native @Type("BOOL") boolean isShowsBookmarkButton();
    @Bind("setShowsBookmarkButton:") public native void setShowsBookmarkButton(@Type("BOOL") boolean v);
    @Bind("showsCancelButton") public native @Type("BOOL") boolean isShowsCancelButton();
    @Bind("setShowsCancelButton:") public native void setShowsCancelButton(@Type("BOOL") boolean v);
    @Bind("showsScopeBar") public native @Type("BOOL") boolean isShowsScopeBar();
    @Bind("setShowsScopeBar:") public native void setShowsScopeBar(@Type("BOOL") boolean v);
    @Bind("showsSearchResultsButton") public native @Type("BOOL") boolean isShowsSearchResultsButton();
    @Bind("setShowsSearchResultsButton:") public native void setShowsSearchResultsButton(@Type("BOOL") boolean v);
    @Bind("spellCheckingType") public native @Type("UITextSpellCheckingType") UITextSpellCheckingType getSpellCheckingType();
    @Bind("setSpellCheckingType:") public native void setSpellCheckingType(@Type("UITextSpellCheckingType") UITextSpellCheckingType v);
    @Bind("text") public native @Type("NSString *") String getText();
    @Bind("setText:") public native void setText(@Type("NSString *") String v);
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    @Bind("isTranslucent") public native @Type("BOOL") boolean isTranslucent();
    @Bind("setTranslucent:") public native void setTranslucent(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    @Bind("imageForSearchBarIcon:state:") public native @Type("UIImage *") UIImage getImageForSearchBarIcon(@Type("UISearchBarIcon") UISearchBarIcon icon, @Type("UIControlState") UIControlState state);
    @Bind("positionAdjustmentForSearchBarIcon:") public native @Type("UIOffset") UIOffset getPositionAdjustmentForSearchBarIcon(@Type("UISearchBarIcon") UISearchBarIcon icon);
    @Bind("scopeBarButtonBackgroundImageForState:") public native @Type("UIImage *") UIImage getScopeBarButtonBackgroundImage(@Type("UIControlState") UIControlState state);
    @Bind("scopeBarButtonDividerImageForLeftSegmentState:rightSegmentState:") public native @Type("UIImage *") UIImage getScopeBarButtonDividerImage(@Type("UIControlState") UIControlState leftState, @Type("UIControlState") UIControlState rightState);
    @Bind("scopeBarButtonTitleTextAttributesForState:") public native @Type("NSDictionary *") NSDictionary getScopeBarButtonTitleTextAttributes(@Type("UIControlState") UIControlState state);
    @Bind("searchFieldBackgroundImageForState:") public native @Type("UIImage *") UIImage getSearchFieldBackgroundImage(@Type("UIControlState") UIControlState state);
    @Bind("setImage:forSearchBarIcon:state:") public native @Type("void") void setImageForSearchBarIcon(@Type("UIImage *") UIImage iconImage, @Type("UISearchBarIcon") UISearchBarIcon icon, @Type("UIControlState") UIControlState state);
    @Bind("setPositionAdjustment:forSearchBarIcon:") public native @Type("void") void setPositionAdjustmentForSearchBarIcon(@Type("UIOffset") UIOffset adjustment, @Type("UISearchBarIcon") UISearchBarIcon icon);
    @Bind("setScopeBarButtonBackgroundImage:forState:") public native @Type("void") void setScopeBarButtonBackgroundImage(@Type("UIImage *") UIImage backgroundImage, @Type("UIControlState") UIControlState state);
    @Bind("setScopeBarButtonDividerImage:forLeftSegmentState:rightSegmentState:") public native @Type("void") void setScopeBarButtonDividerImage(@Type("UIImage *") UIImage dividerImage, @Type("UIControlState") UIControlState leftState, @Type("UIControlState") UIControlState rightState);
    @Bind("setScopeBarButtonTitleTextAttributes:forState:") public native @Type("void") void setScopeBarButtonTitleTextAttributes(@Type("NSDictionary *") NSDictionary attributes, @Type("UIControlState") UIControlState state);
    @Bind("setSearchFieldBackgroundImage:forState:") public native @Type("void") void setSearchFieldBackgroundImage(@Type("UIImage *") UIImage backgroundImage, @Type("UIControlState") UIControlState state);
    @Bind("setShowsCancelButton:animated:") public native @Type("void") void setShowsCancelButton(@Type("BOOL") boolean showsCancelButton, @Type("BOOL") boolean animated);
    /*</methods>*/

}
