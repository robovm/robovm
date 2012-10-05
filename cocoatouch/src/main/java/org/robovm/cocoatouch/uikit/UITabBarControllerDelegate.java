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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarControllerDelegate_Protocol/Reference/Reference.html">UITabBarControllerDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
@Protocol
public interface /*<name>*/ UITabBarControllerDelegate /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITabBarControllerDelegate/tabBarController:didEndCustomizingViewControllers:changed:">- (void)tabBarController:(UITabBarController *)tabBarController  didEndCustomizingViewControllers:(NSArray *)viewControllers  changed:(BOOL)changed</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tabBarController:didEndCustomizingViewControllers:changed:") @Type("void") void didEndCustomizingViewControllers(@Type("UITabBarController *") UITabBarController tabBarController, @Type("NSArray *") NSArray viewControllers, @Type("BOOL") boolean changed);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITabBarControllerDelegate/tabBarController:didSelectViewController:">- (void)tabBarController:(UITabBarController *)tabBarController  didSelectViewController:(UIViewController *)viewController</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tabBarController:didSelectViewController:") @Type("void") void didSelectViewController(@Type("UITabBarController *") UITabBarController tabBarController, @Type("UIViewController *") UIViewController viewController);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITabBarControllerDelegate/tabBarController:shouldSelectViewController:">- (BOOL)tabBarController:(UITabBarController *)tabBarController  shouldSelectViewController:(UIViewController *)viewController</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("tabBarController:shouldSelectViewController:") @Type("BOOL") boolean shouldSelectViewController(@Type("UITabBarController *") UITabBarController tabBarController, @Type("UIViewController *") UIViewController viewController);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITabBarControllerDelegate/tabBarController:willBeginCustomizingViewControllers:">- (void)tabBarController:(UITabBarController *)tabBarController  willBeginCustomizingViewControllers:(NSArray *)viewControllers</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("tabBarController:willBeginCustomizingViewControllers:") @Type("void") void willBeginCustomizingViewControllers(@Type("UITabBarController *") UITabBarController tabBarController, @Type("NSArray *") NSArray viewControllers);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITabBarControllerDelegate/tabBarController:willEndCustomizingViewControllers:changed:">- (void)tabBarController:(UITabBarController *)tabBarController  willEndCustomizingViewControllers:(NSArray *)viewControllers  changed:(BOOL)changed</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("tabBarController:willEndCustomizingViewControllers:changed:") @Type("void") void willEndCustomizingViewControllers(@Type("UITabBarController *") UITabBarController tabBarController, @Type("NSArray *") NSArray viewControllers, @Type("BOOL") boolean changed);
    /*</methods>*/

}
