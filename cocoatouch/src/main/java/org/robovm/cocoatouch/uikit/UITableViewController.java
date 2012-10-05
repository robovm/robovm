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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewController_Class/Reference/Reference.html">UITableViewController Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UITableViewController /*</name>*/ 
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
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithStyle(UITableViewController __self__, Selector __cmd__, UITableViewStyle style);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewController_Class/Reference/Reference.html#//apple_ref/occ/instm/UITableViewController/initWithStyle:">- (id)initWithStyle:(UITableViewStyle)style</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITableViewController(UITableViewStyle style) {
        super((SkipInit) null);
        objc_initWithStyle(this, initWithStyle$, style);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewController/clearsSelectionOnViewWillAppear">@property(nonatomic) BOOL clearsSelectionOnViewWillAppear</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("clearsSelectionOnViewWillAppear") public native boolean isClearsSelectionOnViewWillAppear();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewController/clearsSelectionOnViewWillAppear">@property(nonatomic) BOOL clearsSelectionOnViewWillAppear</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setClearsSelectionOnViewWillAppear:") public native void setClearsSelectionOnViewWillAppear(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewController/refreshControl">@property (nonatomic,retain) UIRefreshControl *refreshControl</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("refreshControl") public native UIRefreshControl getRefreshControl();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewController/refreshControl">@property (nonatomic,retain) UIRefreshControl *refreshControl</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setRefreshControl:") public native void setRefreshControl(UIRefreshControl v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewController/tableView">@property(nonatomic, retain) UITableView *tableView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tableView") public native UITableView getTableView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITableViewController_Class/Reference/Reference.html#//apple_ref/occ/instp/UITableViewController/tableView">@property(nonatomic, retain) UITableView *tableView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTableView:") public native void setTableView(UITableView v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
