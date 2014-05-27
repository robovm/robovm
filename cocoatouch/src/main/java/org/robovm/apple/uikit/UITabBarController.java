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

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UITabBarController/*</name>*/ 
    extends /*<extends>*/UIViewController/*</extends>*/ 
    /*<implements>*/implements UITabBarDelegate, NSCoding/*</implements>*/ {

    /*<ptr>*/public static class UITabBarControllerPtr extends Ptr<UITabBarController, UITabBarControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UITabBarController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UITabBarController() {}
    protected UITabBarController(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "viewControllers")
    public native NSArray<UIViewController> getViewControllers();
    @Property(selector = "setViewControllers:")
    public native void setViewControllers(NSArray<UIViewController> v);
    @Property(selector = "selectedViewController")
    public native UIViewController getSelectedViewController();
    @Property(selector = "setSelectedViewController:", strongRef = true)
    public native void setSelectedViewController(UIViewController v);
    @Property(selector = "selectedIndex")
    public native @MachineSizedUInt long getSelectedIndex();
    @Property(selector = "setSelectedIndex:")
    public native void setSelectedIndex(@MachineSizedUInt long v);
    @Property(selector = "moreNavigationController")
    public native UINavigationController getMoreNavigationController();
    @Property(selector = "customizableViewControllers")
    public native NSArray<?> getCustomizableViewControllers();
    @Property(selector = "setCustomizableViewControllers:")
    public native void setCustomizableViewControllers(NSArray<?> v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "tabBar")
    public native UITabBar getTabBar();
    @Property(selector = "delegate")
    public native UITabBarControllerDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UITabBarControllerDelegate v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setViewControllers:animated:")
    public native void setViewControllers(NSArray<UIViewController> viewControllers, boolean animated);
    @Method(selector = "tabBar:didSelectItem:")
    public native void didSelectItem(UITabBar tabBar, UITabBarItem item);
    @Method(selector = "tabBar:willBeginCustomizingItems:")
    public native void willBeginCustomizingItems(UITabBar tabBar, NSArray<UITabBarItem> items);
    @Method(selector = "tabBar:didBeginCustomizingItems:")
    public native void didBeginCustomizingItems(UITabBar tabBar, NSArray<UITabBarItem> items);
    @Method(selector = "tabBar:willEndCustomizingItems:changed:")
    public native void willEndCustomizingItems(UITabBar tabBar, NSArray<UITabBarItem> items, boolean changed);
    @Method(selector = "tabBar:didEndCustomizingItems:changed:")
    public native void didEndCustomizingItems(UITabBar tabBar, NSArray<UITabBarItem> items, boolean changed);
    /*</methods>*/
}
