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
@Protocol
public interface /*<name>*/ UISearchDisplayDelegate /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayControllerDidBeginSearch:">- (void)searchDisplayControllerDidBeginSearch:(UISearchDisplayController *)controller</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("searchDisplayControllerDidBeginSearch:") @Type("void") void didBeginSearch(@Type("UISearchDisplayController *") UISearchDisplayController controller);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayControllerDidEndSearch:">- (void)searchDisplayControllerDidEndSearch:(UISearchDisplayController *)controller</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("searchDisplayControllerDidEndSearch:") @Type("void") void didEndSearch(@Type("UISearchDisplayController *") UISearchDisplayController controller);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayController:didHideSearchResultsTableView:">- (void)searchDisplayController:(UISearchDisplayController *)controller didHideSearchResultsTableView:(UITableView *)tableView</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("searchDisplayController:didHideSearchResultsTableView:") @Type("void") void didHideSearchResults(@Type("UISearchDisplayController *") UISearchDisplayController controller, @Type("UITableView *") UITableView tableView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayController:didLoadSearchResultsTableView:">- (void)searchDisplayController:(UISearchDisplayController *)controller didLoadSearchResultsTableView:(UITableView *)tableView</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("searchDisplayController:didLoadSearchResultsTableView:") @Type("void") void didLoadSearchResults(@Type("UISearchDisplayController *") UISearchDisplayController controller, @Type("UITableView *") UITableView tableView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayController:didShowSearchResultsTableView:">- (void)searchDisplayController:(UISearchDisplayController *)controller didShowSearchResultsTableView:(UITableView *)tableView</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("searchDisplayController:didShowSearchResultsTableView:") @Type("void") void didShowSearchResults(@Type("UISearchDisplayController *") UISearchDisplayController controller, @Type("UITableView *") UITableView tableView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayController:shouldReloadTableForSearchScope:">- (BOOL)searchDisplayController:(UISearchDisplayController *)controller shouldReloadTableForSearchScope:(NSInteger)searchOption</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("searchDisplayController:shouldReloadTableForSearchScope:") @Type("BOOL") boolean shouldReloadForSearchScope(@Type("UISearchDisplayController *") UISearchDisplayController controller, @Type("NSInteger") int searchOption);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayController:shouldReloadTableForSearchString:">- (BOOL)searchDisplayController:(UISearchDisplayController *)controller
    shouldReloadTableForSearchString:(NSString *)searchString</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("searchDisplayController:shouldReloadTableForSearchString:") @Type("BOOL") boolean shouldReloadForSearchString(@Type("UISearchDisplayController *") UISearchDisplayController controller, @Type("NSString *") String searchString);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayControllerWillBeginSearch:">- (void)searchDisplayControllerWillBeginSearch:(UISearchDisplayController *)controller</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("searchDisplayControllerWillBeginSearch:") @Type("void") void willBeginSearch(@Type("UISearchDisplayController *") UISearchDisplayController controller);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayControllerWillEndSearch:">- (void)searchDisplayControllerWillEndSearch:(UISearchDisplayController *)controller</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("searchDisplayControllerWillEndSearch:") @Type("void") void willEndSearch(@Type("UISearchDisplayController *") UISearchDisplayController controller);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayController:willHideSearchResultsTableView:">- (void)searchDisplayController:(UISearchDisplayController *)controller willHideSearchResultsTableView:(UITableView *)tableView</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("searchDisplayController:willHideSearchResultsTableView:") @Type("void") void willHideSearchResults(@Type("UISearchDisplayController *") UISearchDisplayController controller, @Type("UITableView *") UITableView tableView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayController:willShowSearchResultsTableView:">- (void)searchDisplayController:(UISearchDisplayController *)controller willShowSearchResultsTableView:(UITableView *)tableView</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("searchDisplayController:willShowSearchResultsTableView:") @Type("void") void willShowSearchResults(@Type("UISearchDisplayController *") UISearchDisplayController controller, @Type("UITableView *") UITableView tableView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISearchDisplayDelegate/searchDisplayController:willUnloadSearchResultsTableView:">- (void)searchDisplayController:(UISearchDisplayController *)controller willUnloadSearchResultsTableView:(UITableView *)tableView</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("searchDisplayController:willUnloadSearchResultsTableView:") @Type("void") void willUnloadSearchResults(@Type("UISearchDisplayController *") UISearchDisplayController controller, @Type("UITableView *") UITableView tableView);
    /*</methods>*/

}
