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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html">UISearchDisplayController Class Reference</a>
 *   @since Available in iOS 3.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UISearchDisplayController /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UISearchDisplayController /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UISearchDisplayController /*</name>*/.class);

    /*<constructors>*/
    protected UISearchDisplayController(SkipInit skipInit) { super(skipInit); }
    public UISearchDisplayController() {}
    
    private static final Selector initWithSearchBar$contentsController$ = Selector.register("initWithSearchBar:contentsController:");
    @Bridge private native static @Pointer long objc_initWithSearchBar(UISearchDisplayController __self__, Selector __cmd__, UISearchBar searchBar, UIViewController viewController);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchDisplayController/initWithSearchBar:contentsController:">- (id)initWithSearchBar:(UISearchBar *)searchBar contentsController:(UIViewController *)viewController</a>
     * @since Available in iOS 3.0 and later.
     */
    public UISearchDisplayController(UISearchBar searchBar, UIViewController viewController) {
        super((SkipInit) null);
        initObject(objc_initWithSearchBar(this, initWithSearchBar$contentsController$, searchBar, viewController));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector isActive = Selector.register("isActive");
    @Bridge private native static boolean objc_isActive(UISearchDisplayController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isActiveSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/active">@property(nonatomic, getter=isActive) BOOL active</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isActive() {
        if (customClass) { return objc_isActiveSuper(getSuper(), isActive); } else { return objc_isActive(this, isActive); }
    }
    
    private static final Selector setActive$ = Selector.register("setActive:");
    @Bridge private native static void objc_setActive(UISearchDisplayController __self__, Selector __cmd__, boolean active);
    @Bridge private native static void objc_setActiveSuper(ObjCSuper __super__, Selector __cmd__, boolean active);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/active">@property(nonatomic, getter=isActive) BOOL active</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setActive(boolean active) {
        if (customClass) { objc_setActiveSuper(getSuper(), setActive$, active); } else { objc_setActive(this, setActive$, active); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UISearchDisplayDelegate objc_getDelegate(UISearchDisplayController __self__, Selector __cmd__);
    @Bridge private native static UISearchDisplayDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/delegate">@property(nonatomic, assign) id&amp;lt;UISearchDisplayDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.0 and later.
     */
    public UISearchDisplayDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UISearchDisplayController __self__, Selector __cmd__, UISearchDisplayDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UISearchDisplayDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/delegate">@property(nonatomic, assign) id&amp;lt;UISearchDisplayDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setDelegate(UISearchDisplayDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector searchBar = Selector.register("searchBar");
    @Bridge private native static UISearchBar objc_getSearchBar(UISearchDisplayController __self__, Selector __cmd__);
    @Bridge private native static UISearchBar objc_getSearchBarSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchBar">@property(nonatomic, readonly) UISearchBar *searchBar</a>
     * @since Available in iOS 3.0 and later.
     */
    public UISearchBar getSearchBar() {
        if (customClass) { return objc_getSearchBarSuper(getSuper(), searchBar); } else { return objc_getSearchBar(this, searchBar); }
    }
    
    private static final Selector searchContentsController = Selector.register("searchContentsController");
    @Bridge private native static UIViewController objc_getSearchContentsController(UISearchDisplayController __self__, Selector __cmd__);
    @Bridge private native static UIViewController objc_getSearchContentsControllerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchContentsController">@property(nonatomic, readonly) UIViewController *searchContentsController</a>
     * @since Available in iOS 3.0 and later.
     */
    public UIViewController getSearchContentsController() {
        if (customClass) { return objc_getSearchContentsControllerSuper(getSuper(), searchContentsController); } else { return objc_getSearchContentsController(this, searchContentsController); }
    }
    
    private static final Selector searchResultsDataSource = Selector.register("searchResultsDataSource");
    @Bridge private native static UITableViewDataSource objc_getSearchResultsDataSource(UISearchDisplayController __self__, Selector __cmd__);
    @Bridge private native static UITableViewDataSource objc_getSearchResultsDataSourceSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsDataSource">@property(nonatomic, assign) id&amp;lt;UITableViewDataSource&amp;gt; searchResultsDataSource</a>
     * @since Available in iOS 3.0 and later.
     */
    public UITableViewDataSource getSearchResultsDataSource() {
        if (customClass) { return objc_getSearchResultsDataSourceSuper(getSuper(), searchResultsDataSource); } else { return objc_getSearchResultsDataSource(this, searchResultsDataSource); }
    }
    
    private static final Selector setSearchResultsDataSource$ = Selector.register("setSearchResultsDataSource:");
    @Bridge private native static void objc_setSearchResultsDataSource(UISearchDisplayController __self__, Selector __cmd__, UITableViewDataSource searchResultsDataSource);
    @Bridge private native static void objc_setSearchResultsDataSourceSuper(ObjCSuper __super__, Selector __cmd__, UITableViewDataSource searchResultsDataSource);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsDataSource">@property(nonatomic, assign) id&amp;lt;UITableViewDataSource&amp;gt; searchResultsDataSource</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setSearchResultsDataSource(UITableViewDataSource searchResultsDataSource) {
        if (customClass) { objc_setSearchResultsDataSourceSuper(getSuper(), setSearchResultsDataSource$, searchResultsDataSource); } else { objc_setSearchResultsDataSource(this, setSearchResultsDataSource$, searchResultsDataSource); }
    }
    
    private static final Selector searchResultsDelegate = Selector.register("searchResultsDelegate");
    @Bridge private native static UITableViewDelegate objc_getSearchResultsDelegate(UISearchDisplayController __self__, Selector __cmd__);
    @Bridge private native static UITableViewDelegate objc_getSearchResultsDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsDelegate">@property(nonatomic, assign) id&amp;lt;UITableViewDelegate&amp;gt; searchResultsDelegate</a>
     * @since Available in iOS 3.0 and later.
     */
    public UITableViewDelegate getSearchResultsDelegate() {
        if (customClass) { return objc_getSearchResultsDelegateSuper(getSuper(), searchResultsDelegate); } else { return objc_getSearchResultsDelegate(this, searchResultsDelegate); }
    }
    
    private static final Selector setSearchResultsDelegate$ = Selector.register("setSearchResultsDelegate:");
    @Bridge private native static void objc_setSearchResultsDelegate(UISearchDisplayController __self__, Selector __cmd__, UITableViewDelegate searchResultsDelegate);
    @Bridge private native static void objc_setSearchResultsDelegateSuper(ObjCSuper __super__, Selector __cmd__, UITableViewDelegate searchResultsDelegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsDelegate">@property(nonatomic, assign) id&amp;lt;UITableViewDelegate&amp;gt; searchResultsDelegate</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setSearchResultsDelegate(UITableViewDelegate searchResultsDelegate) {
        if (customClass) { objc_setSearchResultsDelegateSuper(getSuper(), setSearchResultsDelegate$, searchResultsDelegate); } else { objc_setSearchResultsDelegate(this, setSearchResultsDelegate$, searchResultsDelegate); }
    }
    
    private static final Selector searchResultsTableView = Selector.register("searchResultsTableView");
    @Bridge private native static UITableView objc_getSearchResultsTableView(UISearchDisplayController __self__, Selector __cmd__);
    @Bridge private native static UITableView objc_getSearchResultsTableViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsTableView">@property(nonatomic, readonly) UITableView *searchResultsTableView</a>
     * @since Available in iOS 3.0 and later.
     */
    public UITableView getSearchResultsTableView() {
        if (customClass) { return objc_getSearchResultsTableViewSuper(getSuper(), searchResultsTableView); } else { return objc_getSearchResultsTableView(this, searchResultsTableView); }
    }
    
    private static final Selector searchResultsTitle = Selector.register("searchResultsTitle");
    @Bridge private native static String objc_getSearchResultsTitle(UISearchDisplayController __self__, Selector __cmd__);
    @Bridge private native static String objc_getSearchResultsTitleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsTitle">@property(nonatomic, copy) NSString *searchResultsTitle</a>
     * @since Available in iOS 5.0 and later.
     */
    public String getSearchResultsTitle() {
        if (customClass) { return objc_getSearchResultsTitleSuper(getSuper(), searchResultsTitle); } else { return objc_getSearchResultsTitle(this, searchResultsTitle); }
    }
    
    private static final Selector setSearchResultsTitle$ = Selector.register("setSearchResultsTitle:");
    @Bridge private native static void objc_setSearchResultsTitle(UISearchDisplayController __self__, Selector __cmd__, String searchResultsTitle);
    @Bridge private native static void objc_setSearchResultsTitleSuper(ObjCSuper __super__, Selector __cmd__, String searchResultsTitle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsTitle">@property(nonatomic, copy) NSString *searchResultsTitle</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setSearchResultsTitle(String searchResultsTitle) {
        if (customClass) { objc_setSearchResultsTitleSuper(getSuper(), setSearchResultsTitle$, searchResultsTitle); } else { objc_setSearchResultsTitle(this, setSearchResultsTitle$, searchResultsTitle); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector setActive$animated$ = Selector.register("setActive:animated:");
    @Bridge private native static void objc_setActive(UISearchDisplayController __self__, Selector __cmd__, boolean visible, boolean animated);
    @Bridge private native static void objc_setActiveSuper(ObjCSuper __super__, Selector __cmd__, boolean visible, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchDisplayController/setActive:animated:">- (void)setActive:(BOOL)visible animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setActive(boolean visible, boolean animated) {
        if (customClass) { objc_setActiveSuper(getSuper(), setActive$animated$, visible, animated); } else { objc_setActive(this, setActive$animated$, visible, animated); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("isActive") public static boolean isActive(UISearchDisplayController __self__, Selector __cmd__) { return __self__.isActive(); }
        @Callback @BindSelector("setActive:") public static void setActive(UISearchDisplayController __self__, Selector __cmd__, boolean active) { __self__.setActive(active); }
        @Callback @BindSelector("delegate") public static UISearchDisplayDelegate getDelegate(UISearchDisplayController __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UISearchDisplayController __self__, Selector __cmd__, UISearchDisplayDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("searchBar") public static UISearchBar getSearchBar(UISearchDisplayController __self__, Selector __cmd__) { return __self__.getSearchBar(); }
        @Callback @BindSelector("searchContentsController") public static UIViewController getSearchContentsController(UISearchDisplayController __self__, Selector __cmd__) { return __self__.getSearchContentsController(); }
        @Callback @BindSelector("searchResultsDataSource") public static UITableViewDataSource getSearchResultsDataSource(UISearchDisplayController __self__, Selector __cmd__) { return __self__.getSearchResultsDataSource(); }
        @Callback @BindSelector("setSearchResultsDataSource:") public static void setSearchResultsDataSource(UISearchDisplayController __self__, Selector __cmd__, UITableViewDataSource searchResultsDataSource) { __self__.setSearchResultsDataSource(searchResultsDataSource); }
        @Callback @BindSelector("searchResultsDelegate") public static UITableViewDelegate getSearchResultsDelegate(UISearchDisplayController __self__, Selector __cmd__) { return __self__.getSearchResultsDelegate(); }
        @Callback @BindSelector("setSearchResultsDelegate:") public static void setSearchResultsDelegate(UISearchDisplayController __self__, Selector __cmd__, UITableViewDelegate searchResultsDelegate) { __self__.setSearchResultsDelegate(searchResultsDelegate); }
        @Callback @BindSelector("searchResultsTableView") public static UITableView getSearchResultsTableView(UISearchDisplayController __self__, Selector __cmd__) { return __self__.getSearchResultsTableView(); }
        @Callback @BindSelector("searchResultsTitle") public static String getSearchResultsTitle(UISearchDisplayController __self__, Selector __cmd__) { return __self__.getSearchResultsTitle(); }
        @Callback @BindSelector("setSearchResultsTitle:") public static void setSearchResultsTitle(UISearchDisplayController __self__, Selector __cmd__, String searchResultsTitle) { __self__.setSearchResultsTitle(searchResultsTitle); }
        @Callback @BindSelector("setActive:animated:") public static void setActive(UISearchDisplayController __self__, Selector __cmd__, boolean visible, boolean animated) { __self__.setActive(visible, animated); }
    }
    /*</callbacks>*/

}
