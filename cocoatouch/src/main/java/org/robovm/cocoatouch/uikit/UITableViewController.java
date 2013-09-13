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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewController_Class/Reference/Reference.html">UITableViewController Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UITableViewController /*</name>*/ 
    extends /*<extends>*/ UIViewController /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITableViewController /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UITableViewController /*</name>*/.class);

    /*<constructors>*/
    protected UITableViewController(SkipInit skipInit) { super(skipInit); }
    public UITableViewController() {}
    
    private static final Selector initWithStyle$ = Selector.register("initWithStyle:");
    @Bridge private native static @Pointer long objc_initWithStyle(UITableViewController __self__, Selector __cmd__, UITableViewStyle style);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewController/initWithStyle:">- (id)initWithStyle:(UITableViewStyle)style</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITableViewController(UITableViewStyle style) {
        super((SkipInit) null);
        initObject(objc_initWithStyle(this, initWithStyle$, style));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector clearsSelectionOnViewWillAppear = Selector.register("clearsSelectionOnViewWillAppear");
    @Bridge private native static boolean objc_isClearsSelectionOnViewWillAppear(UITableViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isClearsSelectionOnViewWillAppearSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewController/clearsSelectionOnViewWillAppear">@property(nonatomic) BOOL clearsSelectionOnViewWillAppear</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean isClearsSelectionOnViewWillAppear() {
        if (customClass) { return objc_isClearsSelectionOnViewWillAppearSuper(getSuper(), clearsSelectionOnViewWillAppear); } else { return objc_isClearsSelectionOnViewWillAppear(this, clearsSelectionOnViewWillAppear); }
    }
    
    private static final Selector setClearsSelectionOnViewWillAppear$ = Selector.register("setClearsSelectionOnViewWillAppear:");
    @Bridge private native static void objc_setClearsSelectionOnViewWillAppear(UITableViewController __self__, Selector __cmd__, boolean clearsSelectionOnViewWillAppear);
    @Bridge private native static void objc_setClearsSelectionOnViewWillAppearSuper(ObjCSuper __super__, Selector __cmd__, boolean clearsSelectionOnViewWillAppear);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewController/clearsSelectionOnViewWillAppear">@property(nonatomic) BOOL clearsSelectionOnViewWillAppear</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setClearsSelectionOnViewWillAppear(boolean clearsSelectionOnViewWillAppear) {
        if (customClass) { objc_setClearsSelectionOnViewWillAppearSuper(getSuper(), setClearsSelectionOnViewWillAppear$, clearsSelectionOnViewWillAppear); } else { objc_setClearsSelectionOnViewWillAppear(this, setClearsSelectionOnViewWillAppear$, clearsSelectionOnViewWillAppear); }
    }
    
    private static final Selector refreshControl = Selector.register("refreshControl");
    @Bridge private native static UIRefreshControl objc_getRefreshControl(UITableViewController __self__, Selector __cmd__);
    @Bridge private native static UIRefreshControl objc_getRefreshControlSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewController/refreshControl">@property (nonatomic,retain) UIRefreshControl *refreshControl</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIRefreshControl getRefreshControl() {
        if (customClass) { return objc_getRefreshControlSuper(getSuper(), refreshControl); } else { return objc_getRefreshControl(this, refreshControl); }
    }
    
    private static final Selector setRefreshControl$ = Selector.register("setRefreshControl:");
    @Bridge private native static void objc_setRefreshControl(UITableViewController __self__, Selector __cmd__, UIRefreshControl refreshControl);
    @Bridge private native static void objc_setRefreshControlSuper(ObjCSuper __super__, Selector __cmd__, UIRefreshControl refreshControl);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewController/refreshControl">@property (nonatomic,retain) UIRefreshControl *refreshControl</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setRefreshControl(UIRefreshControl refreshControl) {
        if (customClass) { objc_setRefreshControlSuper(getSuper(), setRefreshControl$, refreshControl); } else { objc_setRefreshControl(this, setRefreshControl$, refreshControl); }
    }
    
    private static final Selector tableView = Selector.register("tableView");
    @Bridge private native static UITableView objc_getTableView(UITableViewController __self__, Selector __cmd__);
    @Bridge private native static UITableView objc_getTableViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewController/tableView">@property(nonatomic, retain) UITableView *tableView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITableView getTableView() {
        if (customClass) { return objc_getTableViewSuper(getSuper(), tableView); } else { return objc_getTableView(this, tableView); }
    }
    
    private static final Selector setTableView$ = Selector.register("setTableView:");
    @Bridge private native static void objc_setTableView(UITableViewController __self__, Selector __cmd__, UITableView tableView);
    @Bridge private native static void objc_setTableViewSuper(ObjCSuper __super__, Selector __cmd__, UITableView tableView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewController/tableView">@property(nonatomic, retain) UITableView *tableView</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTableView(UITableView tableView) {
        if (customClass) { objc_setTableViewSuper(getSuper(), setTableView$, tableView); } else { objc_setTableView(this, setTableView$, tableView); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("clearsSelectionOnViewWillAppear") public static boolean isClearsSelectionOnViewWillAppear(UITableViewController __self__, Selector __cmd__) { return __self__.isClearsSelectionOnViewWillAppear(); }
        @Callback @BindSelector("setClearsSelectionOnViewWillAppear:") public static void setClearsSelectionOnViewWillAppear(UITableViewController __self__, Selector __cmd__, boolean clearsSelectionOnViewWillAppear) { __self__.setClearsSelectionOnViewWillAppear(clearsSelectionOnViewWillAppear); }
        @Callback @BindSelector("refreshControl") public static UIRefreshControl getRefreshControl(UITableViewController __self__, Selector __cmd__) { return __self__.getRefreshControl(); }
        @Callback @BindSelector("setRefreshControl:") public static void setRefreshControl(UITableViewController __self__, Selector __cmd__, UIRefreshControl refreshControl) { __self__.setRefreshControl(refreshControl); }
        @Callback @BindSelector("tableView") public static UITableView getTableView(UITableViewController __self__, Selector __cmd__) { return __self__.getTableView(); }
        @Callback @BindSelector("setTableView:") public static void setTableView(UITableViewController __self__, Selector __cmd__, UITableView tableView) { __self__.setTableView(tableView); }
    }
    /*</callbacks>*/

}
