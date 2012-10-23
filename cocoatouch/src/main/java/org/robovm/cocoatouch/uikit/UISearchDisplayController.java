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
import org.robovm.objc.annotation.*;
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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UISearchDisplayController /*</name>*/.class);

    /*<constructors>*/
    protected UISearchDisplayController(SkipInit skipInit) { super(skipInit); }
    public UISearchDisplayController() {}
    
    private static final Selector initWithSearchBar$contentsController$ = Selector.register("initWithSearchBar:contentsController:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithSearchBar(UISearchDisplayController __self__, Selector __cmd__, UISearchBar searchBar, UIViewController viewController);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchDisplayController/initWithSearchBar:contentsController:">- (id)initWithSearchBar:(UISearchBar *)searchBar contentsController:(UIViewController *)viewController</a>
     * @since Available in iOS 3.0 and later.
     */
    public UISearchDisplayController(UISearchBar searchBar, UIViewController viewController) {
        super((SkipInit) null);
        objc_initWithSearchBar(this, initWithSearchBar$contentsController$, searchBar, viewController);
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector isActive = Selector.register("isActive");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isActive(UISearchDisplayController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isActiveSuper(ObjCSuper __super__, UISearchDisplayController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/active">@property(nonatomic, getter=isActive) BOOL active</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isActive() {
        if (customClass) { return objc_isActiveSuper(getSuper(), this, isActive); } else { return objc_isActive(this, isActive); }
    }
    
    private static final Selector setActive$ = Selector.register("setActive:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setActive(UISearchDisplayController __self__, Selector __cmd__, boolean active);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setActiveSuper(ObjCSuper __super__, UISearchDisplayController __self__, Selector __cmd__, boolean active);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/active">@property(nonatomic, getter=isActive) BOOL active</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setActive(boolean active) {
        if (customClass) { objc_setActiveSuper(getSuper(), this, setActive$, active); } else { objc_setActive(this, setActive$, active); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge(symbol = "objc_msgSend") private native static UISearchDisplayDelegate objc_getDelegate(UISearchDisplayController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UISearchDisplayDelegate objc_getDelegateSuper(ObjCSuper __super__, UISearchDisplayController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/delegate">@property(nonatomic, assign) id&amp;lt;UISearchDisplayDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.0 and later.
     */
    public UISearchDisplayDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), this, delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setDelegate(UISearchDisplayController __self__, Selector __cmd__, UISearchDisplayDelegate delegate);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setDelegateSuper(ObjCSuper __super__, UISearchDisplayController __self__, Selector __cmd__, UISearchDisplayDelegate delegate);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/delegate">@property(nonatomic, assign) id&amp;lt;UISearchDisplayDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setDelegate(UISearchDisplayDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), this, setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector searchBar = Selector.register("searchBar");
    @Bridge(symbol = "objc_msgSend") private native static UISearchBar objc_getSearchBar(UISearchDisplayController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UISearchBar objc_getSearchBarSuper(ObjCSuper __super__, UISearchDisplayController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchBar">@property(nonatomic, readonly) UISearchBar *searchBar</a>
     * @since Available in iOS 3.0 and later.
     */
    public UISearchBar getSearchBar() {
        if (customClass) { return objc_getSearchBarSuper(getSuper(), this, searchBar); } else { return objc_getSearchBar(this, searchBar); }
    }
    
    private static final Selector searchContentsController = Selector.register("searchContentsController");
    @Bridge(symbol = "objc_msgSend") private native static UIViewController objc_getSearchContentsController(UISearchDisplayController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIViewController objc_getSearchContentsControllerSuper(ObjCSuper __super__, UISearchDisplayController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchContentsController">@property(nonatomic, readonly) UIViewController *searchContentsController</a>
     * @since Available in iOS 3.0 and later.
     */
    public UIViewController getSearchContentsController() {
        if (customClass) { return objc_getSearchContentsControllerSuper(getSuper(), this, searchContentsController); } else { return objc_getSearchContentsController(this, searchContentsController); }
    }
    
    private static final Selector searchResultsDataSource = Selector.register("searchResultsDataSource");
    @Bridge(symbol = "objc_msgSend") private native static UITableViewDataSource objc_getSearchResultsDataSource(UISearchDisplayController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITableViewDataSource objc_getSearchResultsDataSourceSuper(ObjCSuper __super__, UISearchDisplayController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsDataSource">@property(nonatomic, assign) id&amp;lt;UITableViewDataSource&amp;gt; searchResultsDataSource</a>
     * @since Available in iOS 3.0 and later.
     */
    public UITableViewDataSource getSearchResultsDataSource() {
        if (customClass) { return objc_getSearchResultsDataSourceSuper(getSuper(), this, searchResultsDataSource); } else { return objc_getSearchResultsDataSource(this, searchResultsDataSource); }
    }
    
    private static final Selector setSearchResultsDataSource$ = Selector.register("setSearchResultsDataSource:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSearchResultsDataSource(UISearchDisplayController __self__, Selector __cmd__, UITableViewDataSource searchResultsDataSource);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSearchResultsDataSourceSuper(ObjCSuper __super__, UISearchDisplayController __self__, Selector __cmd__, UITableViewDataSource searchResultsDataSource);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsDataSource">@property(nonatomic, assign) id&amp;lt;UITableViewDataSource&amp;gt; searchResultsDataSource</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setSearchResultsDataSource(UITableViewDataSource searchResultsDataSource) {
        if (customClass) { objc_setSearchResultsDataSourceSuper(getSuper(), this, setSearchResultsDataSource$, searchResultsDataSource); } else { objc_setSearchResultsDataSource(this, setSearchResultsDataSource$, searchResultsDataSource); }
    }
    
    private static final Selector searchResultsDelegate = Selector.register("searchResultsDelegate");
    @Bridge(symbol = "objc_msgSend") private native static UITableViewDelegate objc_getSearchResultsDelegate(UISearchDisplayController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITableViewDelegate objc_getSearchResultsDelegateSuper(ObjCSuper __super__, UISearchDisplayController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsDelegate">@property(nonatomic, assign) id&amp;lt;UITableViewDelegate&amp;gt; searchResultsDelegate</a>
     * @since Available in iOS 3.0 and later.
     */
    public UITableViewDelegate getSearchResultsDelegate() {
        if (customClass) { return objc_getSearchResultsDelegateSuper(getSuper(), this, searchResultsDelegate); } else { return objc_getSearchResultsDelegate(this, searchResultsDelegate); }
    }
    
    private static final Selector setSearchResultsDelegate$ = Selector.register("setSearchResultsDelegate:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSearchResultsDelegate(UISearchDisplayController __self__, Selector __cmd__, UITableViewDelegate searchResultsDelegate);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSearchResultsDelegateSuper(ObjCSuper __super__, UISearchDisplayController __self__, Selector __cmd__, UITableViewDelegate searchResultsDelegate);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsDelegate">@property(nonatomic, assign) id&amp;lt;UITableViewDelegate&amp;gt; searchResultsDelegate</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setSearchResultsDelegate(UITableViewDelegate searchResultsDelegate) {
        if (customClass) { objc_setSearchResultsDelegateSuper(getSuper(), this, setSearchResultsDelegate$, searchResultsDelegate); } else { objc_setSearchResultsDelegate(this, setSearchResultsDelegate$, searchResultsDelegate); }
    }
    
    private static final Selector searchResultsTableView = Selector.register("searchResultsTableView");
    @Bridge(symbol = "objc_msgSend") private native static UITableView objc_getSearchResultsTableView(UISearchDisplayController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITableView objc_getSearchResultsTableViewSuper(ObjCSuper __super__, UISearchDisplayController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsTableView">@property(nonatomic, readonly) UITableView *searchResultsTableView</a>
     * @since Available in iOS 3.0 and later.
     */
    public UITableView getSearchResultsTableView() {
        if (customClass) { return objc_getSearchResultsTableViewSuper(getSuper(), this, searchResultsTableView); } else { return objc_getSearchResultsTableView(this, searchResultsTableView); }
    }
    
    private static final Selector searchResultsTitle = Selector.register("searchResultsTitle");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getSearchResultsTitle(UISearchDisplayController __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getSearchResultsTitleSuper(ObjCSuper __super__, UISearchDisplayController __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsTitle">@property(nonatomic, copy) NSString *searchResultsTitle</a>
     * @since Available in iOS 5.0 and later.
     */
    public String getSearchResultsTitle() {
        if (customClass) { return objc_getSearchResultsTitleSuper(getSuper(), this, searchResultsTitle); } else { return objc_getSearchResultsTitle(this, searchResultsTitle); }
    }
    
    private static final Selector setSearchResultsTitle$ = Selector.register("setSearchResultsTitle:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSearchResultsTitle(UISearchDisplayController __self__, Selector __cmd__, String searchResultsTitle);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSearchResultsTitleSuper(ObjCSuper __super__, UISearchDisplayController __self__, Selector __cmd__, String searchResultsTitle);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instp/UISearchDisplayController/searchResultsTitle">@property(nonatomic, copy) NSString *searchResultsTitle</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setSearchResultsTitle(String searchResultsTitle) {
        if (customClass) { objc_setSearchResultsTitleSuper(getSuper(), this, setSearchResultsTitle$, searchResultsTitle); } else { objc_setSearchResultsTitle(this, setSearchResultsTitle$, searchResultsTitle); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector setActive$animated$ = Selector.register("setActive:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setActive(UISearchDisplayController __self__, Selector __cmd__, boolean visible, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setActiveSuper(ObjCSuper __super__, UISearchDisplayController __self__, Selector __cmd__, boolean visible, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISearchDisplayController_Class/Reference/Reference.html#//apple_ref/occ/instm/UISearchDisplayController/setActive:animated:">- (void)setActive:(BOOL)visible animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setActive(boolean visible, boolean animated) {
        if (customClass) { objc_setActiveSuper(getSuper(), this, setActive$animated$, visible, animated); } else { objc_setActive(this, setActive$animated$, visible, animated); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("setActive:animated:") public static void setActive(UISearchDisplayController __self__, Selector __cmd__, boolean visible, boolean animated) { __self__.setActive(visible, animated); }
    }
    /*</callbacks>*/

}
