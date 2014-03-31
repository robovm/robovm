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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UISearchDisplayController/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UISearchDisplayControllerPtr extends Ptr<UISearchDisplayController, UISearchDisplayControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UISearchDisplayController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UISearchDisplayController() {}
    protected UISearchDisplayController(SkipInit skipInit) { super(skipInit); }
    public UISearchDisplayController(UISearchBar searchBar, UIViewController viewController) { super((SkipInit) null); initObject(initWithSearchBar$contentsController$(searchBar, viewController)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native UISearchDisplayDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UISearchDisplayDelegate v);
    @Property(selector = "isActive")
    public native boolean isActive();
    @Property(selector = "setActive:")
    public native void setActive(boolean v);
    @Property(selector = "searchBar")
    public native UISearchBar getSearchBar();
    @Property(selector = "searchContentsController")
    public native UIViewController getSearchContentsController();
    @Property(selector = "searchResultsTableView")
    public native UITableView getSearchResultsTableView();
    @Property(selector = "searchResultsDataSource")
    public native UITableViewDataSource getSearchResultsDataSource();
    @Property(selector = "setSearchResultsDataSource:", strongRef = true)
    public native void setSearchResultsDataSource(UITableViewDataSource v);
    @Property(selector = "searchResultsDelegate")
    public native UITableViewDelegate getSearchResultsDelegate();
    @Property(selector = "setSearchResultsDelegate:", strongRef = true)
    public native void setSearchResultsDelegate(UITableViewDelegate v);
    @Property(selector = "searchResultsTitle")
    public native String getSearchResultsTitle();
    @Property(selector = "setSearchResultsTitle:")
    public native void setSearchResultsTitle(String v);
    @Property(selector = "displaysSearchBarInNavigationBar")
    public native boolean isDisplaysSearchBarInNavigationBar();
    @Property(selector = "setDisplaysSearchBarInNavigationBar:")
    public native void setDisplaysSearchBarInNavigationBar(boolean v);
    @Property(selector = "navigationItem")
    public native UINavigationItem getNavigationItem();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithSearchBar:contentsController:")
    protected native @Pointer long initWithSearchBar$contentsController$(UISearchBar searchBar, UIViewController viewController);
    @Method(selector = "setActive:animated:")
    public native void setActive(boolean visible, boolean animated);
    /*</methods>*/
}
