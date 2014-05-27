/*
 * Copyright (C) 2014 Trillian Mobile AB
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

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
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
    
    public UISearchBar(CGRect frame) {
        super(frame);
    }
    
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
    /**
     * @since Available in iOS 3.2 and later.
     */
    public native boolean isShowsSearchResultsButton();
    @Property(selector = "setShowsSearchResultsButton:")
    /**
     * @since Available in iOS 3.2 and later.
     */
    public native void setShowsSearchResultsButton(boolean v);
    @Property(selector = "isSearchResultsButtonSelected")
    /**
     * @since Available in iOS 3.2 and later.
     */
    public native boolean isSearchResultsButtonSelected();
    @Property(selector = "setSearchResultsButtonSelected:")
    /**
     * @since Available in iOS 3.2 and later.
     */
    public native void setSearchResultsButtonSelected(boolean v);
    @Property(selector = "tintColor")
    public native UIColor getTintColor();
    @Property(selector = "setTintColor:")
    public native void setTintColor(UIColor v);
    @Property(selector = "barTintColor")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native UIColor getBarTintColor();
    @Property(selector = "setBarTintColor:")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native void setBarTintColor(UIColor v);
    @Property(selector = "searchBarStyle")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native UISearchBarStyle getSearchBarStyle();
    @Property(selector = "setSearchBarStyle:")
    /**
     * @since Available in iOS 7.0 and later.
     */
    public native void setSearchBarStyle(UISearchBarStyle v);
    @Property(selector = "isTranslucent")
    /**
     * @since Available in iOS 3.0 and later.
     */
    public native boolean isTranslucent();
    @Property(selector = "setTranslucent:")
    /**
     * @since Available in iOS 3.0 and later.
     */
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
    /**
     * @since Available in iOS 3.0 and later.
     */
    public native NSArray<NSString> getScopeButtonTitles();
    @Property(selector = "setScopeButtonTitles:")
    /**
     * @since Available in iOS 3.0 and later.
     */
    public native void setScopeButtonTitles(NSArray<NSString> v);
    @Property(selector = "selectedScopeButtonIndex")
    /**
     * @since Available in iOS 3.0 and later.
     */
    public native @MachineSizedSInt long getSelectedScopeButtonIndex();
    @Property(selector = "setSelectedScopeButtonIndex:")
    /**
     * @since Available in iOS 3.0 and later.
     */
    public native void setSelectedScopeButtonIndex(@MachineSizedSInt long v);
    @Property(selector = "showsScopeBar")
    /**
     * @since Available in iOS 3.0 and later.
     */
    public native boolean isShowsScopeBar();
    @Property(selector = "setShowsScopeBar:")
    /**
     * @since Available in iOS 3.0 and later.
     */
    public native void setShowsScopeBar(boolean v);
    @Property(selector = "inputAccessoryView")
    public native UIView getInputAccessoryView();
    @Property(selector = "setInputAccessoryView:")
    public native void setInputAccessoryView(UIView v);
    @Property(selector = "backgroundImage")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native UIImage getBackgroundImage();
    @Property(selector = "setBackgroundImage:")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native void setBackgroundImage(UIImage v);
    @Property(selector = "scopeBarBackgroundImage")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native UIImage getScopeBarBackgroundImage();
    @Property(selector = "setScopeBarBackgroundImage:")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native void setScopeBarBackgroundImage(UIImage v);
    @Property(selector = "searchFieldBackgroundPositionAdjustment")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native @ByVal UIOffset getSearchFieldBackgroundPositionAdjustment();
    @Property(selector = "setSearchFieldBackgroundPositionAdjustment:")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native void setSearchFieldBackgroundPositionAdjustment(@ByVal UIOffset v);
    @Property(selector = "searchTextPositionAdjustment")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native @ByVal UIOffset getSearchTextPositionAdjustment();
    @Property(selector = "setSearchTextPositionAdjustment:")
    /**
     * @since Available in iOS 5.0 and later.
     */
    public native void setSearchTextPositionAdjustment(@ByVal UIOffset v);
    @Property(selector = "barPosition")
    public native UIBarPosition getBarPosition();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setShowsCancelButton:animated:")
    public native void setShowsCancelButton(boolean showsCancelButton, boolean animated);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "setBackgroundImage:forBarPosition:barMetrics:")
    public native void setBackgroundImage(UIImage backgroundImage, UIBarPosition barPosition, UIBarMetrics barMetrics);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "backgroundImageForBarPosition:barMetrics:")
    public native UIImage getBackgroundImage(UIBarPosition barPosition, UIBarMetrics barMetrics);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setSearchFieldBackgroundImage:forState:")
    public native void setSearchFieldBackgroundImage(UIImage backgroundImage, UIControlState state);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "searchFieldBackgroundImageForState:")
    public native UIImage getSearchFieldBackgroundImage(UIControlState state);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setImage:forSearchBarIcon:state:")
    public native void setImageForSearchBarIcon(UIImage iconImage, UISearchBarIcon icon, UIControlState state);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "imageForSearchBarIcon:state:")
    public native UIImage getImageForSearchBarIcon(UISearchBarIcon icon, UIControlState state);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setScopeBarButtonBackgroundImage:forState:")
    public native void setScopeBarButtonBackgroundImage(UIImage backgroundImage, UIControlState state);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "scopeBarButtonBackgroundImageForState:")
    public native UIImage getScopeBarButtonBackgroundImage(UIControlState state);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setScopeBarButtonDividerImage:forLeftSegmentState:rightSegmentState:")
    public native void setScopeBarButtonDividerImage(UIImage dividerImage, UIControlState leftState, UIControlState rightState);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "scopeBarButtonDividerImageForLeftSegmentState:rightSegmentState:")
    public native UIImage getScopeBarButtonDividerImage(UIControlState leftState, UIControlState rightState);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setScopeBarButtonTitleTextAttributes:forState:")
    public native void setScopeBarButtonTitleTextAttributes(NSDictionary<NSString, ?> attributes, UIControlState state);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "scopeBarButtonTitleTextAttributesForState:")
    public native NSDictionary<NSString, ?> getScopeBarButtonTitleTextAttributes(UIControlState state);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "setPositionAdjustment:forSearchBarIcon:")
    public native void setPositionAdjustmentForSearchBarIcon(@ByVal UIOffset adjustment, UISearchBarIcon icon);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "positionAdjustmentForSearchBarIcon:")
    public native @ByVal UIOffset getPositionAdjustmentForSearchBarIcon(UISearchBarIcon icon);
    /*</methods>*/
}
