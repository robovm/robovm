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
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/UISearchDisplayDelegate/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Method(selector = "searchDisplayControllerWillBeginSearch:")
    void willBeginSearch(UISearchDisplayController controller);
    @Method(selector = "searchDisplayControllerDidBeginSearch:")
    void didBeginSearch(UISearchDisplayController controller);
    @Method(selector = "searchDisplayControllerWillEndSearch:")
    void willEndSearch(UISearchDisplayController controller);
    @Method(selector = "searchDisplayControllerDidEndSearch:")
    void didEndSearch(UISearchDisplayController controller);
    @Method(selector = "searchDisplayController:didLoadSearchResultsTableView:")
    void didLoadSearchResults(UISearchDisplayController controller, UITableView tableView);
    @Method(selector = "searchDisplayController:willUnloadSearchResultsTableView:")
    void willUnloadSearchResults(UISearchDisplayController controller, UITableView tableView);
    @Method(selector = "searchDisplayController:willShowSearchResultsTableView:")
    void willShowSearchResults(UISearchDisplayController controller, UITableView tableView);
    @Method(selector = "searchDisplayController:didShowSearchResultsTableView:")
    void didShowSearchResults(UISearchDisplayController controller, UITableView tableView);
    @Method(selector = "searchDisplayController:willHideSearchResultsTableView:")
    void willHideSearchResults(UISearchDisplayController controller, UITableView tableView);
    @Method(selector = "searchDisplayController:didHideSearchResultsTableView:")
    void didHideSearchResults(UISearchDisplayController controller, UITableView tableView);
    @Method(selector = "searchDisplayController:shouldReloadTableForSearchString:")
    boolean shouldReloadForSearchString(UISearchDisplayController controller, String searchString);
    @Method(selector = "searchDisplayController:shouldReloadTableForSearchScope:")
    boolean shouldReloadForSearchScope(UISearchDisplayController controller, @MachineSizedSInt long searchOption);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
