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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewController_class/Reference/Reference.html">UISplitViewController Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UISplitViewController /*</name>*/ 
    extends /*<extends>*/ UIViewController /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UISplitViewController /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UISplitViewController /*</name>*/.class);

    /*<constructors>*/
    protected UISplitViewController(SkipInit skipInit) { super(skipInit); }
    public UISplitViewController() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewController_class/Reference/Reference.html#//apple_ref/occ/instp/UISplitViewController/delegate">@property(nonatomic, assign) id &amp;lt;UISplitViewControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("delegate") public native UISplitViewControllerDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewController_class/Reference/Reference.html#//apple_ref/occ/instp/UISplitViewController/delegate">@property(nonatomic, assign) id &amp;lt;UISplitViewControllerDelegate&amp;gt; delegate</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(UISplitViewControllerDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewController_class/Reference/Reference.html#//apple_ref/occ/instp/UISplitViewController/presentsWithGesture">@property (nonatomic) BOOL presentsWithGesture</a>
     * @since Available in iOS 5.1 and later.
     */
    @Bind("presentsWithGesture") public native boolean isPresentsWithGesture();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewController_class/Reference/Reference.html#//apple_ref/occ/instp/UISplitViewController/presentsWithGesture">@property (nonatomic) BOOL presentsWithGesture</a>
     * @since Available in iOS 5.1 and later.
     */
    @Bind("setPresentsWithGesture:") public native void setPresentsWithGesture(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewController_class/Reference/Reference.html#//apple_ref/occ/instp/UISplitViewController/viewControllers">@property(nonatomic, copy) NSArray *viewControllers</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("viewControllers") public native NSArray getViewControllers();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewController_class/Reference/Reference.html#//apple_ref/occ/instp/UISplitViewController/viewControllers">@property(nonatomic, copy) NSArray *viewControllers</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("setViewControllers:") public native void setViewControllers(NSArray v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
