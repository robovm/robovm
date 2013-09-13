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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarControllerDelegate_Protocol/Reference/Reference.html">UITabBarControllerDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UITabBarControllerDelegate /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITabBarControllerDelegate/tabBarController:didEndCustomizingViewControllers:changed:">- (void)tabBarController:(UITabBarController *)tabBarController  didEndCustomizingViewControllers:(NSArray *)viewControllers  changed:(BOOL)changed</a>
     * @since Available in iOS 2.0 and later.
     */
    void didEndCustomizingViewControllers(UITabBarController tabBarController, NSArray viewControllers, boolean changed);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITabBarControllerDelegate/tabBarController:didSelectViewController:">- (void)tabBarController:(UITabBarController *)tabBarController  didSelectViewController:(UIViewController *)viewController</a>
     * @since Available in iOS 2.0 and later.
     */
    void didSelectViewController(UITabBarController tabBarController, UIViewController viewController);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITabBarControllerDelegate/tabBarController:shouldSelectViewController:">- (BOOL)tabBarController:(UITabBarController *)tabBarController  shouldSelectViewController:(UIViewController *)viewController</a>
     * @since Available in iOS 3.0 and later.
     */
    boolean shouldSelectViewController(UITabBarController tabBarController, UIViewController viewController);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITabBarControllerDelegate/tabBarController:willBeginCustomizingViewControllers:">- (void)tabBarController:(UITabBarController *)tabBarController  willBeginCustomizingViewControllers:(NSArray *)viewControllers</a>
     * @since Available in iOS 3.0 and later.
     */
    void willBeginCustomizingViewControllers(UITabBarController tabBarController, NSArray viewControllers);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITabBarControllerDelegate/tabBarController:willEndCustomizingViewControllers:changed:">- (void)tabBarController:(UITabBarController *)tabBarController  willEndCustomizingViewControllers:(NSArray *)viewControllers  changed:(BOOL)changed</a>
     * @since Available in iOS 3.0 and later.
     */
    void willEndCustomizingViewControllers(UITabBarController tabBarController, NSArray viewControllers, boolean changed);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UITabBarControllerDelegate {
        @NotImplemented("tabBarController:didEndCustomizingViewControllers:changed:") public void didEndCustomizingViewControllers(UITabBarController tabBarController, NSArray viewControllers, boolean changed) { throw new UnsupportedOperationException(); }
        @NotImplemented("tabBarController:didSelectViewController:") public void didSelectViewController(UITabBarController tabBarController, UIViewController viewController) { throw new UnsupportedOperationException(); }
        @NotImplemented("tabBarController:shouldSelectViewController:") public boolean shouldSelectViewController(UITabBarController tabBarController, UIViewController viewController) { throw new UnsupportedOperationException(); }
        @NotImplemented("tabBarController:willBeginCustomizingViewControllers:") public void willBeginCustomizingViewControllers(UITabBarController tabBarController, NSArray viewControllers) { throw new UnsupportedOperationException(); }
        @NotImplemented("tabBarController:willEndCustomizingViewControllers:changed:") public void willEndCustomizingViewControllers(UITabBarController tabBarController, NSArray viewControllers, boolean changed) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("tabBarController:didEndCustomizingViewControllers:changed:") public static void didEndCustomizingViewControllers(UITabBarControllerDelegate __self__, Selector __cmd__, UITabBarController tabBarController, NSArray viewControllers, boolean changed) { __self__.didEndCustomizingViewControllers(tabBarController, viewControllers, changed); }
        @Callback @BindSelector("tabBarController:didSelectViewController:") public static void didSelectViewController(UITabBarControllerDelegate __self__, Selector __cmd__, UITabBarController tabBarController, UIViewController viewController) { __self__.didSelectViewController(tabBarController, viewController); }
        @Callback @BindSelector("tabBarController:shouldSelectViewController:") public static boolean shouldSelectViewController(UITabBarControllerDelegate __self__, Selector __cmd__, UITabBarController tabBarController, UIViewController viewController) { return __self__.shouldSelectViewController(tabBarController, viewController); }
        @Callback @BindSelector("tabBarController:willBeginCustomizingViewControllers:") public static void willBeginCustomizingViewControllers(UITabBarControllerDelegate __self__, Selector __cmd__, UITabBarController tabBarController, NSArray viewControllers) { __self__.willBeginCustomizingViewControllers(tabBarController, viewControllers); }
        @Callback @BindSelector("tabBarController:willEndCustomizingViewControllers:changed:") public static void willEndCustomizingViewControllers(UITabBarControllerDelegate __self__, Selector __cmd__, UITabBarController tabBarController, NSArray viewControllers, boolean changed) { __self__.willEndCustomizingViewControllers(tabBarController, viewControllers, changed); }
    }
    /*</callbacks>*/

}
