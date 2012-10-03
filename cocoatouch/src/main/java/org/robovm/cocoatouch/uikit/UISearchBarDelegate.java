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

@Protocol
public interface /*<name>*/ UISearchBarDelegate /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Bind("searchBarBookmarkButtonClicked:") @Type("void") void bookmarkButtonClicked(@Type("UISearchBar *") UISearchBar searchBar);
    @Bind("searchBarCancelButtonClicked:") @Type("void") void cancelButtonClicked(@Type("UISearchBar *") UISearchBar searchBar);
    @Bind("searchBarTextDidBeginEditing:") @Type("void") void didBeginEditing(@Type("UISearchBar *") UISearchBar searchBar);
    @Bind("searchBar:textDidChange:") @Type("void") void didChange(@Type("UISearchBar *") UISearchBar searchBar, @Type("NSString *") String searchText);
    @Bind("searchBarTextDidEndEditing:") @Type("void") void didEndEditing(@Type("UISearchBar *") UISearchBar searchBar);
    @Bind("searchBarResultsListButtonClicked:") @Type("void") void resultsListButtonClicked(@Type("UISearchBar *") UISearchBar searchBar);
    @Bind("searchBarSearchButtonClicked:") @Type("void") void searchButtonClicked(@Type("UISearchBar *") UISearchBar searchBar);
    @Bind("searchBar:selectedScopeButtonIndexDidChange:") @Type("void") void selectedScopeButtonIndexDidChange(@Type("UISearchBar *") UISearchBar searchBar, @Type("NSInteger") int selectedScope);
    @Bind("searchBarShouldBeginEditing:") @Type("BOOL") boolean shouldBeginEditing(@Type("UISearchBar *") UISearchBar searchBar);
    @Bind("searchBar:shouldChangeTextInRange:replacementText:") @Type("BOOL") boolean shouldChange(@Type("UISearchBar *") UISearchBar searchBar, @Type("NSRange") NSRange range, @Type("NSString *") String text);
    @Bind("searchBarShouldEndEditing:") @Type("BOOL") boolean shouldEndEditing(@Type("UISearchBar *") UISearchBar searchBar);
    /*</methods>*/

}
