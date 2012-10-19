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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html">UISearchBarDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UISearchBarDelegate /*</name>*/ /*<implements>*/ extends ObjCProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBarBookmarkButtonClicked:">- (void)searchBarBookmarkButtonClicked:(UISearchBar *)searchBar</a>
     * @since Available in iOS 2.0 and later.
     */
    void bookmarkButtonClicked(UISearchBar searchBar);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBarCancelButtonClicked:">- (void)searchBarCancelButtonClicked:(UISearchBar *)searchBar</a>
     * @since Available in iOS 2.0 and later.
     */
    void cancelButtonClicked(UISearchBar searchBar);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBarTextDidBeginEditing:">- (void)searchBarTextDidBeginEditing:(UISearchBar *)searchBar</a>
     * @since Available in iOS 2.0 and later.
     */
    void didBeginEditing(UISearchBar searchBar);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBar:textDidChange:">- (void)searchBar:(UISearchBar *)searchBar textDidChange:(NSString *)searchText</a>
     * @since Available in iOS 2.0 and later.
     */
    void didChange(UISearchBar searchBar, String searchText);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBarTextDidEndEditing:">- (void)searchBarTextDidEndEditing:(UISearchBar *)searchBar</a>
     * @since Available in iOS 2.0 and later.
     */
    void didEndEditing(UISearchBar searchBar);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBarResultsListButtonClicked:">- (void)searchBarResultsListButtonClicked:(UISearchBar *)searchBar</a>
     * @since Available in iOS 3.2 and later.
     */
    void resultsListButtonClicked(UISearchBar searchBar);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBarSearchButtonClicked:">- (void)searchBarSearchButtonClicked:(UISearchBar *)searchBar</a>
     * @since Available in iOS 2.0 and later.
     */
    void searchButtonClicked(UISearchBar searchBar);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBar:selectedScopeButtonIndexDidChange:">- (void)searchBar:(UISearchBar *)searchBar selectedScopeButtonIndexDidChange:(NSInteger)selectedScope</a>
     * @since Available in iOS 3.0 and later.
     */
    void selectedScopeButtonIndexDidChange(UISearchBar searchBar, int selectedScope);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBarShouldBeginEditing:">- (BOOL)searchBarShouldBeginEditing:(UISearchBar *)searchBar</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean shouldBeginEditing(UISearchBar searchBar);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBar:shouldChangeTextInRange:replacementText:">- (BOOL)searchBar:(UISearchBar *)searchBar
    shouldChangeTextInRange:(NSRange)range 
    replacementText:(NSString *)text</a>
     * @since Available in iOS 3.0 and later.
     */
    boolean shouldChange(UISearchBar searchBar, NSRange range, String text);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBarShouldEndEditing:">- (BOOL)searchBarShouldEndEditing:(UISearchBar *)searchBar</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean shouldEndEditing(UISearchBar searchBar);
    /*</methods>*/

}
