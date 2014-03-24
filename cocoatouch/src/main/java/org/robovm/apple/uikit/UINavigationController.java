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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UINavigationController/*</name>*/ 
    extends /*<extends>*/UIViewController/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UINavigationControllerPtr extends Ptr<UINavigationController, UINavigationControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UINavigationController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UINavigationController() {}
    protected UINavigationController(SkipInit skipInit) { super(skipInit); }
    public UINavigationController(ObjCClass navigationBarClass, ObjCClass toolbarClass) { super((SkipInit) null); initObject(initWithNavigationBarClass$toolbarClass$(navigationBarClass, toolbarClass)); }
    public UINavigationController(UIViewController rootViewController) { super((SkipInit) null); initObject(initWithRootViewController$(rootViewController)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "topViewController")
    public native UIViewController getTopViewController();
    @Property(selector = "visibleViewController")
    public native UIViewController getVisibleViewController();
    @Property(selector = "viewControllers")
    public native NSArray<?> getViewControllers();
    @Property(selector = "setViewControllers:")
    public native void setViewControllers(NSArray<?> v);
    @Property(selector = "isNavigationBarHidden")
    public native boolean isNavigationBarHidden();
    @Property(selector = "setNavigationBarHidden:")
    public native void setNavigationBarHidden(boolean v);
    @Property(selector = "navigationBar")
    public native UINavigationBar getNavigationBar();
    @Property(selector = "isToolbarHidden")
    public native boolean isToolbarHidden();
    @Property(selector = "setToolbarHidden:")
    public native void setToolbarHidden(boolean v);
    @Property(selector = "toolbar")
    public native UIToolbar getToolbar();
    @Property(selector = "delegate")
    public native UINavigationControllerDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UINavigationControllerDelegate v);
    @Property(selector = "interactivePopGestureRecognizer")
    public native UIGestureRecognizer getInteractivePopGestureRecognizer();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithNavigationBarClass:toolbarClass:")
    protected native @Pointer long initWithNavigationBarClass$toolbarClass$(ObjCClass navigationBarClass, ObjCClass toolbarClass);
    @Method(selector = "initWithRootViewController:")
    protected native @Pointer long initWithRootViewController$(UIViewController rootViewController);
    @Method(selector = "pushViewController:animated:")
    public native void pushViewController$animated$(UIViewController viewController, boolean animated);
    @Method(selector = "popViewControllerAnimated:")
    public native UIViewController popViewControllerAnimated$(boolean animated);
    @Method(selector = "popToViewController:animated:")
    public native NSArray<?> popToViewController$animated$(UIViewController viewController, boolean animated);
    @Method(selector = "popToRootViewControllerAnimated:")
    public native NSArray<?> popToRootViewController(boolean animated);
    @Method(selector = "setViewControllers:animated:")
    public native void setViewControllers$animated$(NSArray<?> viewControllers, boolean animated);
    @Method(selector = "setNavigationBarHidden:animated:")
    public native void setNavigationBarHidden$animated$(boolean hidden, boolean animated);
    @Method(selector = "setToolbarHidden:animated:")
    public native void setToolbarHidden$animated$(boolean hidden, boolean animated);
    /*</methods>*/
}
