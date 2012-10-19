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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html">UISearchDisplayDelegate Protocol Reference</a>
 *   @since Available in iOS 3.0 and later.
 * </div>
 */
public interface /*<name>*/ UISearchDisplayDelegate /*</name>*/ /*<implements>*/ extends ObjCProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayControllerDidBeginSearch:">- (void)searchDisplayControllerDidBeginSearch:(UISearchDisplayController *)controller</a>
     * @since Available in iOS 3.0 and later.
     */
    void didBeginSearch(UISearchDisplayController controller);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayControllerDidEndSearch:">- (void)searchDisplayControllerDidEndSearch:(UISearchDisplayController *)controller</a>
     * @since Available in iOS 3.0 and later.
     */
    void didEndSearch(UISearchDisplayController controller);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayController:didHideSearchResultsTableView:">- (void)searchDisplayController:(UISearchDisplayController *)controller didHideSearchResultsTableView:(UITableView *)tableView</a>
     * @since Available in iOS 3.0 and later.
     */
    void didHideSearchResults(UISearchDisplayController controller, UITableView tableView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayController:didLoadSearchResultsTableView:">- (void)searchDisplayController:(UISearchDisplayController *)controller didLoadSearchResultsTableView:(UITableView *)tableView</a>
     * @since Available in iOS 3.0 and later.
     */
    void didLoadSearchResults(UISearchDisplayController controller, UITableView tableView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayController:didShowSearchResultsTableView:">- (void)searchDisplayController:(UISearchDisplayController *)controller didShowSearchResultsTableView:(UITableView *)tableView</a>
     * @since Available in iOS 3.0 and later.
     */
    void didShowSearchResults(UISearchDisplayController controller, UITableView tableView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayController:shouldReloadTableForSearchScope:">- (BOOL)searchDisplayController:(UISearchDisplayController *)controller shouldReloadTableForSearchScope:(NSInteger)searchOption</a>
     * @since Available in iOS 3.0 and later.
     */
    boolean shouldReloadForSearchScope(UISearchDisplayController controller, int searchOption);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayController:shouldReloadTableForSearchString:">- (BOOL)searchDisplayController:(UISearchDisplayController *)controller
    shouldReloadTableForSearchString:(NSString *)searchString</a>
     * @since Available in iOS 3.0 and later.
     */
    boolean shouldReloadForSearchString(UISearchDisplayController controller, String searchString);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayControllerWillBeginSearch:">- (void)searchDisplayControllerWillBeginSearch:(UISearchDisplayController *)controller</a>
     * @since Available in iOS 3.0 and later.
     */
    void willBeginSearch(UISearchDisplayController controller);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayControllerWillEndSearch:">- (void)searchDisplayControllerWillEndSearch:(UISearchDisplayController *)controller</a>
     * @since Available in iOS 3.0 and later.
     */
    void willEndSearch(UISearchDisplayController controller);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayController:willHideSearchResultsTableView:">- (void)searchDisplayController:(UISearchDisplayController *)controller willHideSearchResultsTableView:(UITableView *)tableView</a>
     * @since Available in iOS 3.0 and later.
     */
    void willHideSearchResults(UISearchDisplayController controller, UITableView tableView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayController:willShowSearchResultsTableView:">- (void)searchDisplayController:(UISearchDisplayController *)controller willShowSearchResultsTableView:(UITableView *)tableView</a>
     * @since Available in iOS 3.0 and later.
     */
    void willShowSearchResults(UISearchDisplayController controller, UITableView tableView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayController:willUnloadSearchResultsTableView:">- (void)searchDisplayController:(UISearchDisplayController *)controller willUnloadSearchResultsTableView:(UITableView *)tableView</a>
     * @since Available in iOS 3.0 and later.
     */
    void willUnloadSearchResults(UISearchDisplayController controller, UITableView tableView);
    /*</methods>*/

}
