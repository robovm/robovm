/*
 * Copyright (C) 2014 Trillian AB
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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UISearchBar/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*/implements UIBarPositioning/*</implements>*/ {

    /*<ptr>*/public static class UISearchBarPtr extends Ptr<UISearchBar, UISearchBarPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UISearchBar.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UISearchBar() {}
    protected UISearchBar(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "barStyle")
    public native UIBarStyle getBarStyle();
    @Property(selector = "setBarStyle:")
    public native void setBarStyle(UIBarStyle v);
    @Property(selector = "delegate")
    public native UISearchBarDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UISearchBarDelegate v);
    @Property(selector = "text")
    public native String getText();
    @Property(selector = "setText:")
    public native void setText(String v);
    @Property(selector = "prompt")
    public native String getPrompt();
    @Property(selector = "setPrompt:")
    public native void setPrompt(String v);
    @Property(selector = "placeholder")
    public native String getPlaceholder();
    @Property(selector = "setPlaceholder:")
    public native void setPlaceholder(String v);
    @Property(selector = "showsBookmarkButton")
    public native boolean isShowsBookmarkButton();
    @Property(selector = "setShowsBookmarkButton:")
    public native void setShowsBookmarkButton(boolean v);
    @Property(selector = "showsCancelButton")
    public native boolean isShowsCancelButton();
    @Property(selector = "setShowsCancelButton:")
    public native void setShowsCancelButton(boolean v);
    @Property(selector = "showsSearchResultsButton")
    public native boolean isShowsSearchResultsButton();
    @Property(selector = "setShowsSearchResultsButton:")
    public native void setShowsSearchResultsButton(boolean v);
    @Property(selector = "isSearchResultsButtonSelected")
    public native boolean isSearchResultsButtonSelected();
    @Property(selector = "setSearchResultsButtonSelected:")
    public native void setSearchResultsButtonSelected(boolean v);
    @Property(selector = "tintColor")
    public native UIColor getTintColor();
    @Property(selector = "setTintColor:")
    public native void setTintColor(UIColor v);
    @Property(selector = "barTintColor")
    public native UIColor getBarTintColor();
    @Property(selector = "setBarTintColor:")
    public native void setBarTintColor(UIColor v);
    @Property(selector = "searchBarStyle")
    public native UISearchBarStyle getSearchBarStyle();
    @Property(selector = "setSearchBarStyle:")
    public native void setSearchBarStyle(UISearchBarStyle v);
    @Property(selector = "isTranslucent")
    public native boolean isTranslucent();
    @Property(selector = "setTranslucent:")
    public native void setTranslucent(boolean v);
    @Property(selector = "autocapitalizationType")
    public native UITextAutocapitalizationType getAutocapitalizationType();
    @Property(selector = "setAutocapitalizationType:")
    public native void setAutocapitalizationType(UITextAutocapitalizationType v);
    @Property(selector = "autocorrectionType")
    public native UITextAutocorrectionType getAutocorrectionType();
    @Property(selector = "setAutocorrectionType:")
    public native void setAutocorrectionType(UITextAutocorrectionType v);
    @Property(selector = "spellCheckingType")
    public native UITextSpellCheckingType getSpellCheckingType();
    @Property(selector = "setSpellCheckingType:")
    public native void setSpellCheckingType(UITextSpellCheckingType v);
    @Property(selector = "keyboardType")
    public native UIKeyboardType getKeyboardType();
    @Property(selector = "setKeyboardType:")
    public native void setKeyboardType(UIKeyboardType v);
    @Property(selector = "scopeButtonTitles")
    public native NSArray<?> getScopeButtonTitles();
    @Property(selector = "setScopeButtonTitles:")
    public native void setScopeButtonTitles(NSArray<?> v);
    @Property(selector = "selectedScopeButtonIndex")
    public native @MachineSizedSInt long getSelectedScopeButtonIndex();
    @Property(selector = "setSelectedScopeButtonIndex:")
    public native void setSelectedScopeButtonIndex(@MachineSizedSInt long v);
    @Property(selector = "showsScopeBar")
    public native boolean isShowsScopeBar();
    @Property(selector = "setShowsScopeBar:")
    public native void setShowsScopeBar(boolean v);
    @Property(selector = "inputAccessoryView")
    public native UIView getInputAccessoryView();
    @Property(selector = "setInputAccessoryView:")
    public native void setInputAccessoryView(UIView v);
    @Property(selector = "backgroundImage")
    public native UIImage getBackgroundImage();
    @Property(selector = "setBackgroundImage:")
    public native void setBackgroundImage(UIImage v);
    @Property(selector = "scopeBarBackgroundImage")
    public native UIImage getScopeBarBackgroundImage();
    @Property(selector = "setScopeBarBackgroundImage:")
    public native void setScopeBarBackgroundImage(UIImage v);
    @Property(selector = "searchFieldBackgroundPositionAdjustment")
    public native @ByVal UIOffset getSearchFieldBackgroundPositionAdjustment();
    @Property(selector = "setSearchFieldBackgroundPositionAdjustment:")
    public native void setSearchFieldBackgroundPositionAdjustment(@ByVal UIOffset v);
    @Property(selector = "searchTextPositionAdjustment")
    public native @ByVal UIOffset getSearchTextPositionAdjustment();
    @Property(selector = "setSearchTextPositionAdjustment:")
    public native void setSearchTextPositionAdjustment(@ByVal UIOffset v);
    @Property(selector = "barPosition")
    public native UIBarPosition getBarPosition();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setShowsCancelButton:animated:")
    public native void setShowsCancelButton$animated$(boolean showsCancelButton, boolean animated);
    @Method(selector = "setBackgroundImage:forBarPosition:barMetrics:")
    public native void setBackgroundImage$forBarPosition$barMetrics$(UIImage backgroundImage, UIBarPosition barPosition, UIBarMetrics barMetrics);
    @Method(selector = "backgroundImageForBarPosition:barMetrics:")
    public native UIImage backgroundImageForBarPosition$barMetrics$(UIBarPosition barPosition, UIBarMetrics barMetrics);
    @Method(selector = "setSearchFieldBackgroundImage:forState:")
    public native void setSearchFieldBackgroundImage$forState$(UIImage backgroundImage, UIControlState state);
    @Method(selector = "searchFieldBackgroundImageForState:")
    public native UIImage getSearchFieldBackgroundImage(UIControlState state);
    @Method(selector = "setImage:forSearchBarIcon:state:")
    public native void setImageForSearchBarIcon(UIImage iconImage, UISearchBarIcon icon, UIControlState state);
    @Method(selector = "imageForSearchBarIcon:state:")
    public native UIImage getImageForSearchBarIcon(UISearchBarIcon icon, UIControlState state);
    @Method(selector = "setScopeBarButtonBackgroundImage:forState:")
    public native void setScopeBarButtonBackgroundImage$forState$(UIImage backgroundImage, UIControlState state);
    @Method(selector = "scopeBarButtonBackgroundImageForState:")
    public native UIImage getScopeBarButtonBackgroundImage(UIControlState state);
    @Method(selector = "setScopeBarButtonDividerImage:forLeftSegmentState:rightSegmentState:")
    public native void setScopeBarButtonDividerImage$forLeftSegmentState$rightSegmentState$(UIImage dividerImage, UIControlState leftState, UIControlState rightState);
    @Method(selector = "scopeBarButtonDividerImageForLeftSegmentState:rightSegmentState:")
    public native UIImage getScopeBarButtonDividerImage(UIControlState leftState, UIControlState rightState);
    @Method(selector = "setScopeBarButtonTitleTextAttributes:forState:")
    public native void setScopeBarButtonTitleTextAttributes$forState$(NSDictionary<?, ?> attributes, UIControlState state);
    @Method(selector = "scopeBarButtonTitleTextAttributesForState:")
    public native NSDictionary<?, ?> getScopeBarButtonTitleTextAttributes(UIControlState state);
    @Method(selector = "setPositionAdjustment:forSearchBarIcon:")
    public native void setPositionAdjustmentForSearchBarIcon(@ByVal UIOffset adjustment, UISearchBarIcon icon);
    @Method(selector = "positionAdjustmentForSearchBarIcon:")
    public native @ByVal UIOffset getPositionAdjustmentForSearchBarIcon(UISearchBarIcon icon);
    /*</methods>*/
}
