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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html">UISearchDisplayController Class Reference</a>
 *   @since Available in iOS 3.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UISearchDisplayController /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UISearchDisplayController /*</name>*/.class);
    }

    /*<constructors>*/
    public UISearchDisplayController() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchDisplayController/initWithSearchBar:contentsController:">- (id)initWithSearchBar:(UISearchBar *)searchBar contentsController:(UIViewController *)viewController</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("initWithSearchBar:contentsController:") public UISearchDisplayController(@Type("UISearchBar *") UISearchBar searchBar, @Type("UIViewController *") UIViewController viewController) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/active">@property(nonatomic, getter=isActive) BOOL active</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isActive") public native @Type("BOOL") boolean isActive();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/active">@property(nonatomic, getter=isActive) BOOL active</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setActive:") public native void setActive(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/delegate">@property(nonatomic, assign) id&amp;lt;UISearchDisplayDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("delegate") public native @Type("id<UISearchDisplayDelegate>") UISearchDisplayDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/delegate">@property(nonatomic, assign) id&amp;lt;UISearchDisplayDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UISearchDisplayDelegate>") UISearchDisplayDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchBar">@property(nonatomic, readonly) UISearchBar *searchBar</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("searchBar") public native @Type("UISearchBar *") UISearchBar getSearchBar();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchContentsController">@property(nonatomic, readonly) UIViewController *searchContentsController</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("searchContentsController") public native @Type("UIViewController *") UIViewController getSearchContentsController();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsDataSource">@property(nonatomic, assign) id&amp;lt;UITableViewDataSource&amp;gt; searchResultsDataSource</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("searchResultsDataSource") public native @Type("id<UITableViewDataSource>") UITableViewDataSource getSearchResultsDataSource();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsDataSource">@property(nonatomic, assign) id&amp;lt;UITableViewDataSource&amp;gt; searchResultsDataSource</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setSearchResultsDataSource:") public native void setSearchResultsDataSource(@Type("id<UITableViewDataSource>") UITableViewDataSource v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsDelegate">@property(nonatomic, assign) id&amp;lt;UITableViewDelegate&amp;gt; searchResultsDelegate</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("searchResultsDelegate") public native @Type("id<UITableViewDelegate>") UITableViewDelegate getSearchResultsDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsDelegate">@property(nonatomic, assign) id&amp;lt;UITableViewDelegate&amp;gt; searchResultsDelegate</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setSearchResultsDelegate:") public native void setSearchResultsDelegate(@Type("id<UITableViewDelegate>") UITableViewDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsTableView">@property(nonatomic, readonly) UITableView *searchResultsTableView</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("searchResultsTableView") public native @Type("UITableView *") UITableView getSearchResultsTableView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsTitle">@property(nonatomic, copy) NSString *searchResultsTitle</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("searchResultsTitle") public native @Type("NSString *") String getSearchResultsTitle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsTitle">@property(nonatomic, copy) NSString *searchResultsTitle</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setSearchResultsTitle:") public native void setSearchResultsTitle(@Type("NSString *") String v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchDisplayController/setActive:animated:">- (void)setActive:(BOOL)visible animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setActive:animated:") public native @Type("void") void setActive(@Type("BOOL") boolean visible, @Type("BOOL") boolean animated);
    /*</methods>*/

}
