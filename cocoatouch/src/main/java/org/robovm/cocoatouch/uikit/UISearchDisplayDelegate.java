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
public interface /*<name>*/ UISearchDisplayDelegate /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Bind("searchDisplayControllerDidBeginSearch:") @Type("void") void didBeginSearch(@Type("UISearchDisplayController *") UISearchDisplayController controller);
    @Bind("searchDisplayControllerDidEndSearch:") @Type("void") void didEndSearch(@Type("UISearchDisplayController *") UISearchDisplayController controller);
    @Bind("searchDisplayController:didHideSearchResultsTableView:") @Type("void") void didHideSearchResults(@Type("UISearchDisplayController *") UISearchDisplayController controller, @Type("UITableView *") UITableView tableView);
    @Bind("searchDisplayController:didLoadSearchResultsTableView:") @Type("void") void didLoadSearchResults(@Type("UISearchDisplayController *") UISearchDisplayController controller, @Type("UITableView *") UITableView tableView);
    @Bind("searchDisplayController:didShowSearchResultsTableView:") @Type("void") void didShowSearchResults(@Type("UISearchDisplayController *") UISearchDisplayController controller, @Type("UITableView *") UITableView tableView);
    @Bind("searchDisplayController:shouldReloadTableForSearchScope:") @Type("BOOL") boolean shouldReloadForSearchScope(@Type("UISearchDisplayController *") UISearchDisplayController controller, @Type("NSInteger") int searchOption);
    @Bind("searchDisplayController:shouldReloadTableForSearchString:") @Type("BOOL") boolean shouldReloadForSearchString(@Type("UISearchDisplayController *") UISearchDisplayController controller, @Type("NSString *") String searchString);
    @Bind("searchDisplayControllerWillBeginSearch:") @Type("void") void willBeginSearch(@Type("UISearchDisplayController *") UISearchDisplayController controller);
    @Bind("searchDisplayControllerWillEndSearch:") @Type("void") void willEndSearch(@Type("UISearchDisplayController *") UISearchDisplayController controller);
    @Bind("searchDisplayController:willHideSearchResultsTableView:") @Type("void") void willHideSearchResults(@Type("UISearchDisplayController *") UISearchDisplayController controller, @Type("UITableView *") UITableView tableView);
    @Bind("searchDisplayController:willShowSearchResultsTableView:") @Type("void") void willShowSearchResults(@Type("UISearchDisplayController *") UISearchDisplayController controller, @Type("UITableView *") UITableView tableView);
    @Bind("searchDisplayController:willUnloadSearchResultsTableView:") @Type("void") void willUnloadSearchResults(@Type("UISearchDisplayController *") UISearchDisplayController controller, @Type("UITableView *") UITableView tableView);
    /*</methods>*/

}
