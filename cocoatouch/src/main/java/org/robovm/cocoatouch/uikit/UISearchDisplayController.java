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

/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UISearchDisplayController /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UISearchDisplayController /*</name>*/.class);
    }

    /*<constructors>*/
    public UISearchDisplayController() {}
    @Bind("initWithSearchBar:contentsController:") public UISearchDisplayController(@Type("UISearchBar *") UISearchBar searchBar, @Type("UIViewController *") UIViewController viewController) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("isActive") public native @Type("BOOL") boolean isActive();
    @Bind("setActive:") public native void setActive(@Type("BOOL") boolean v);
    @Bind("delegate") public native @Type("id<UISearchDisplayDelegate>") UISearchDisplayDelegate getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UISearchDisplayDelegate>") UISearchDisplayDelegate v);
    @Bind("searchBar") public native @Type("UISearchBar *") UISearchBar getSearchBar();
    @Bind("searchContentsController") public native @Type("UIViewController *") UIViewController getSearchContentsController();
    @Bind("searchResultsDataSource") public native @Type("id<UITableViewDataSource>") UITableViewDataSource getSearchResultsDataSource();
    @Bind("setSearchResultsDataSource:") public native void setSearchResultsDataSource(@Type("id<UITableViewDataSource>") UITableViewDataSource v);
    @Bind("searchResultsDelegate") public native @Type("id<UITableViewDelegate>") UITableViewDelegate getSearchResultsDelegate();
    @Bind("setSearchResultsDelegate:") public native void setSearchResultsDelegate(@Type("id<UITableViewDelegate>") UITableViewDelegate v);
    @Bind("searchResultsTableView") public native @Type("UITableView *") UITableView getSearchResultsTableView();
    @Bind("searchResultsTitle") public native @Type("NSString *") String getSearchResultsTitle();
    @Bind("setSearchResultsTitle:") public native void setSearchResultsTitle(@Type("NSString *") String v);
    /*</properties>*/
    /*<methods>*/
    @Bind("setActive:animated:") public native @Type("void") void setActive(@Type("BOOL") boolean visible, @Type("BOOL") boolean animated);
    /*</methods>*/

}
