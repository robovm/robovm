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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html">UISearchBarDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UISearchBarDelegate /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBarBookmarkButtonClicked:">- (void)searchBarBookmarkButtonClicked:(UISearchBar *)searchBar</a>
     * @since Available in iOS 2.0 and later.
     */
    void bookmarkButtonClicked(UISearchBar searchBar);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBarCancelButtonClicked:">- (void)searchBarCancelButtonClicked:(UISearchBar *)searchBar</a>
     * @since Available in iOS 2.0 and later.
     */
    void cancelButtonClicked(UISearchBar searchBar);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBarTextDidBeginEditing:">- (void)searchBarTextDidBeginEditing:(UISearchBar *)searchBar</a>
     * @since Available in iOS 2.0 and later.
     */
    void didBeginEditing(UISearchBar searchBar);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBar:textDidChange:">- (void)searchBar:(UISearchBar *)searchBar textDidChange:(NSString *)searchText</a>
     * @since Available in iOS 2.0 and later.
     */
    void didChange(UISearchBar searchBar, String searchText);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBarTextDidEndEditing:">- (void)searchBarTextDidEndEditing:(UISearchBar *)searchBar</a>
     * @since Available in iOS 2.0 and later.
     */
    void didEndEditing(UISearchBar searchBar);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBarResultsListButtonClicked:">- (void)searchBarResultsListButtonClicked:(UISearchBar *)searchBar</a>
     * @since Available in iOS 3.2 and later.
     */
    void resultsListButtonClicked(UISearchBar searchBar);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBarSearchButtonClicked:">- (void)searchBarSearchButtonClicked:(UISearchBar *)searchBar</a>
     * @since Available in iOS 2.0 and later.
     */
    void searchButtonClicked(UISearchBar searchBar);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBar:selectedScopeButtonIndexDidChange:">- (void)searchBar:(UISearchBar *)searchBar selectedScopeButtonIndexDidChange:(NSInteger)selectedScope</a>
     * @since Available in iOS 3.0 and later.
     */
    void selectedScopeButtonIndexDidChange(UISearchBar searchBar, int selectedScope);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBarShouldBeginEditing:">- (BOOL)searchBarShouldBeginEditing:(UISearchBar *)searchBar</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean shouldBeginEditing(UISearchBar searchBar);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBar:shouldChangeTextInRange:replacementText:">- (BOOL)searchBar:(UISearchBar *)searchBar
    shouldChangeTextInRange:(NSRange)range 
    replacementText:(NSString *)text</a>
     * @since Available in iOS 3.0 and later.
     */
    boolean shouldChange(UISearchBar searchBar, NSRange range, String text);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchBarDelegate/searchBarShouldEndEditing:">- (BOOL)searchBarShouldEndEditing:(UISearchBar *)searchBar</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean shouldEndEditing(UISearchBar searchBar);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UISearchBarDelegate {
        @NotImplemented("searchBarBookmarkButtonClicked:") public void bookmarkButtonClicked(UISearchBar searchBar) { throw new UnsupportedOperationException(); }
        @NotImplemented("searchBarCancelButtonClicked:") public void cancelButtonClicked(UISearchBar searchBar) { throw new UnsupportedOperationException(); }
        @NotImplemented("searchBarTextDidBeginEditing:") public void didBeginEditing(UISearchBar searchBar) { throw new UnsupportedOperationException(); }
        @NotImplemented("searchBar:textDidChange:") public void didChange(UISearchBar searchBar, String searchText) { throw new UnsupportedOperationException(); }
        @NotImplemented("searchBarTextDidEndEditing:") public void didEndEditing(UISearchBar searchBar) { throw new UnsupportedOperationException(); }
        @NotImplemented("searchBarResultsListButtonClicked:") public void resultsListButtonClicked(UISearchBar searchBar) { throw new UnsupportedOperationException(); }
        @NotImplemented("searchBarSearchButtonClicked:") public void searchButtonClicked(UISearchBar searchBar) { throw new UnsupportedOperationException(); }
        @NotImplemented("searchBar:selectedScopeButtonIndexDidChange:") public void selectedScopeButtonIndexDidChange(UISearchBar searchBar, int selectedScope) { throw new UnsupportedOperationException(); }
        @NotImplemented("searchBarShouldBeginEditing:") public boolean shouldBeginEditing(UISearchBar searchBar) { throw new UnsupportedOperationException(); }
        @NotImplemented("searchBar:shouldChangeTextInRange:replacementText:") public boolean shouldChange(UISearchBar searchBar, NSRange range, String text) { throw new UnsupportedOperationException(); }
        @NotImplemented("searchBarShouldEndEditing:") public boolean shouldEndEditing(UISearchBar searchBar) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("searchBarBookmarkButtonClicked:") public static void bookmarkButtonClicked(UISearchBarDelegate __self__, Selector __cmd__, UISearchBar searchBar) { __self__.bookmarkButtonClicked(searchBar); }
        @Callback @BindSelector("searchBarCancelButtonClicked:") public static void cancelButtonClicked(UISearchBarDelegate __self__, Selector __cmd__, UISearchBar searchBar) { __self__.cancelButtonClicked(searchBar); }
        @Callback @BindSelector("searchBarTextDidBeginEditing:") public static void didBeginEditing(UISearchBarDelegate __self__, Selector __cmd__, UISearchBar searchBar) { __self__.didBeginEditing(searchBar); }
        @Callback @BindSelector("searchBar:textDidChange:") public static void didChange(UISearchBarDelegate __self__, Selector __cmd__, UISearchBar searchBar, String searchText) { __self__.didChange(searchBar, searchText); }
        @Callback @BindSelector("searchBarTextDidEndEditing:") public static void didEndEditing(UISearchBarDelegate __self__, Selector __cmd__, UISearchBar searchBar) { __self__.didEndEditing(searchBar); }
        @Callback @BindSelector("searchBarResultsListButtonClicked:") public static void resultsListButtonClicked(UISearchBarDelegate __self__, Selector __cmd__, UISearchBar searchBar) { __self__.resultsListButtonClicked(searchBar); }
        @Callback @BindSelector("searchBarSearchButtonClicked:") public static void searchButtonClicked(UISearchBarDelegate __self__, Selector __cmd__, UISearchBar searchBar) { __self__.searchButtonClicked(searchBar); }
        @Callback @BindSelector("searchBar:selectedScopeButtonIndexDidChange:") public static void selectedScopeButtonIndexDidChange(UISearchBarDelegate __self__, Selector __cmd__, UISearchBar searchBar, int selectedScope) { __self__.selectedScopeButtonIndexDidChange(searchBar, selectedScope); }
        @Callback @BindSelector("searchBarShouldBeginEditing:") public static boolean shouldBeginEditing(UISearchBarDelegate __self__, Selector __cmd__, UISearchBar searchBar) { return __self__.shouldBeginEditing(searchBar); }
        @Callback @BindSelector("searchBar:shouldChangeTextInRange:replacementText:") public static boolean shouldChange(UISearchBarDelegate __self__, Selector __cmd__, UISearchBar searchBar, @ByVal NSRange range, String text) { return __self__.shouldChange(searchBar, range, text); }
        @Callback @BindSelector("searchBarShouldEndEditing:") public static boolean shouldEndEditing(UISearchBarDelegate __self__, Selector __cmd__, UISearchBar searchBar) { return __self__.shouldEndEditing(searchBar); }
    }
    /*</callbacks>*/

}
