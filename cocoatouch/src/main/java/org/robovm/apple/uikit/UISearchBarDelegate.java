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
import org.robovm.apple.coretext.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/UISearchBarDelegate/*</name>*/ 
    /*<implements>*/extends UIBarPositioningDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "searchBarShouldBeginEditing:")
    boolean shouldBeginEditing(UISearchBar searchBar);
    @Method(selector = "searchBarTextDidBeginEditing:")
    void didBeginEditing(UISearchBar searchBar);
    @Method(selector = "searchBarShouldEndEditing:")
    boolean shouldEndEditing(UISearchBar searchBar);
    @Method(selector = "searchBarTextDidEndEditing:")
    void didEndEditing(UISearchBar searchBar);
    @Method(selector = "searchBar:textDidChange:")
    void didChange(UISearchBar searchBar, String searchText);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "searchBar:shouldChangeTextInRange:replacementText:")
    boolean shouldChange(UISearchBar searchBar, @ByVal NSRange range, String text);
    @Method(selector = "searchBarSearchButtonClicked:")
    void searchButtonClicked(UISearchBar searchBar);
    @Method(selector = "searchBarBookmarkButtonClicked:")
    void bookmarkButtonClicked(UISearchBar searchBar);
    @Method(selector = "searchBarCancelButtonClicked:")
    void cancelButtonClicked(UISearchBar searchBar);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Method(selector = "searchBarResultsListButtonClicked:")
    void resultsListButtonClicked(UISearchBar searchBar);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "searchBar:selectedScopeButtonIndexDidChange:")
    void selectedScopeButtonIndexDidChange(UISearchBar searchBar, @MachineSizedSInt long selectedScope);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
