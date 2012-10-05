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
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRefreshControl_class/Reference/Reference.html">UIRefreshControl Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIRefreshControl /*</name>*/ 
    extends /*<extends>*/ UIControl /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIRefreshControl /*</name>*/.class);
    }

    /*<constructors>*/
    public UIRefreshControl() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRefreshControl_class/Reference/Reference.html#//apple_ref/occ/instp/UIRefreshControl/attributedTitle">@property (nonatomic, retain) NSAttributedString *attributedTitle</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("attributedTitle") public native @Type("NSAttributedString *") NSAttributedString getAttributedTitle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRefreshControl_class/Reference/Reference.html#//apple_ref/occ/instp/UIRefreshControl/attributedTitle">@property (nonatomic, retain) NSAttributedString *attributedTitle</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setAttributedTitle:") public native void setAttributedTitle(@Type("NSAttributedString *") NSAttributedString v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRefreshControl_class/Reference/Reference.html#//apple_ref/occ/instp/UIRefreshControl/refreshing">@property (nonatomic, readonly, getter=isRefreshing) BOOL refreshing</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("isRefreshing") public native @Type("BOOL") boolean isRefreshing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRefreshControl_class/Reference/Reference.html#//apple_ref/occ/instp/UIRefreshControl/tintColor">@property (nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRefreshControl_class/Reference/Reference.html#//apple_ref/occ/instp/UIRefreshControl/tintColor">@property (nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRefreshControl_class/Reference/Reference.html#//apple_ref/occ/instm/UIRefreshControl/beginRefreshing">- (void)beginRefreshing</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("beginRefreshing") public native @Type("void") void beginRefreshing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIRefreshControl_class/Reference/Reference.html#//apple_ref/occ/instm/UIRefreshControl/endRefreshing">- (void)endRefreshing</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("endRefreshing") public native @Type("void") void endRefreshing();
    /*</methods>*/

}
