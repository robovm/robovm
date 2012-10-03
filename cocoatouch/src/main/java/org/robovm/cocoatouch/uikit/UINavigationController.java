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
public class /*<name>*/ UINavigationController /*</name>*/ 
    extends /*<extends>*/ UIViewController /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UINavigationController /*</name>*/.class);
    }

    /*<constructors>*/
    public UINavigationController() {}
    @Bind("initWithNavigationBarClass:toolbarClass:") public UINavigationController(@Type("Class") ObjCClass navigationBarClass, @Type("Class") ObjCClass toolbarClass) {}
    @Bind("initWithRootViewController:") public UINavigationController(@Type("UIViewController *") UIViewController rootViewController) {}
    /*</constructors>*/
    /*<properties>*/
    @Bind("delegate") public native @Type("id<UINavigationControllerDelegate>") UINavigationControllerDelegate getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UINavigationControllerDelegate>") UINavigationControllerDelegate v);
    @Bind("navigationBar") public native @Type("UINavigationBar *") UINavigationBar getNavigationBar();
    @Bind("isNavigationBarHidden") public native @Type("BOOL") boolean isNavigationBarHidden();
    @Bind("setNavigationBarHidden:") public native void setNavigationBarHidden(@Type("BOOL") boolean v);
    @Bind("toolbar") public native @Type("UIToolbar *") UIToolbar getToolbar();
    @Bind("isToolbarHidden") public native @Type("BOOL") boolean isToolbarHidden();
    @Bind("setToolbarHidden:") public native void setToolbarHidden(@Type("BOOL") boolean v);
    @Bind("topViewController") public native @Type("UIViewController *") UIViewController getTopViewController();
    @Bind("viewControllers") public native @Type("NSArray *") NSArray getViewControllers();
    @Bind("setViewControllers:") public native void setViewControllers(@Type("NSArray *") NSArray v);
    @Bind("visibleViewController") public native @Type("UIViewController *") UIViewController getVisibleViewController();
    /*</properties>*/
    /*<methods>*/
    @Bind("popToRootViewControllerAnimated:") public native @Type("NSArray *") NSArray popToRootViewController(@Type("BOOL") boolean animated);
    @Bind("popToViewController:animated:") public native @Type("NSArray *") NSArray popToViewController(@Type("UIViewController *") UIViewController viewController, @Type("BOOL") boolean animated);
    @Bind("popViewControllerAnimated:") public native @Type("UIViewController *") UIViewController popViewControllerAnimated(@Type("BOOL") boolean animated);
    @Bind("pushViewController:animated:") public native @Type("void") void pushViewController(@Type("UIViewController *") UIViewController viewController, @Type("BOOL") boolean animated);
    @Bind("setNavigationBarHidden:animated:") public native @Type("void") void setNavigationBarHidden(@Type("BOOL") boolean hidden, @Type("BOOL") boolean animated);
    @Bind("setToolbarHidden:animated:") public native @Type("void") void setToolbarHidden(@Type("BOOL") boolean hidden, @Type("BOOL") boolean animated);
    @Bind("setViewControllers:animated:") public native @Type("void") void setViewControllers(@Type("NSArray *") NSArray viewControllers, @Type("BOOL") boolean animated);
    /*</methods>*/

}
