/*
 * Copyright (C) 2013-2015 RoboVM AB
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
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UISearchBarDelegateAdapter/*</name>*/ 
    extends /*<extends>*/UIBarPositioningDelegateAdapter/*</extends>*/ 
    /*<implements>*/implements UISearchBarDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("searchBarShouldBeginEditing:")
    public boolean shouldBeginEditing(UISearchBar searchBar) { throw new UnsupportedOperationException(); }
    @NotImplemented("searchBarTextDidBeginEditing:")
    public void didBeginEditing(UISearchBar searchBar) { throw new UnsupportedOperationException(); }
    @NotImplemented("searchBarShouldEndEditing:")
    public boolean shouldEndEditing(UISearchBar searchBar) { throw new UnsupportedOperationException(); }
    @NotImplemented("searchBarTextDidEndEditing:")
    public void didEndEditing(UISearchBar searchBar) { throw new UnsupportedOperationException(); }
    @NotImplemented("searchBar:textDidChange:")
    public void didChange(UISearchBar searchBar, String searchText) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 3.0 and later.
     */
    @NotImplemented("searchBar:shouldChangeTextInRange:replacementText:")
    public boolean shouldChange(UISearchBar searchBar, @ByVal NSRange range, String text) { throw new UnsupportedOperationException(); }
    @NotImplemented("searchBarSearchButtonClicked:")
    public void searchButtonClicked(UISearchBar searchBar) { throw new UnsupportedOperationException(); }
    @NotImplemented("searchBarBookmarkButtonClicked:")
    public void bookmarkButtonClicked(UISearchBar searchBar) { throw new UnsupportedOperationException(); }
    @NotImplemented("searchBarCancelButtonClicked:")
    public void cancelButtonClicked(UISearchBar searchBar) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 3.2 and later.
     */
    @NotImplemented("searchBarResultsListButtonClicked:")
    public void resultsListButtonClicked(UISearchBar searchBar) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 3.0 and later.
     */
    @NotImplemented("searchBar:selectedScopeButtonIndexDidChange:")
    public void selectedScopeButtonIndexDidChange(UISearchBar searchBar, @MachineSizedSInt long selectedScope) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
