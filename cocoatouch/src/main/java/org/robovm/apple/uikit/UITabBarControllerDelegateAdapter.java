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
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UITabBarControllerDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UITabBarControllerDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("tabBarController:shouldSelectViewController:")
    public boolean shouldSelectViewController(UITabBarController tabBarController, UIViewController viewController) { throw new UnsupportedOperationException(); }
    @NotImplemented("tabBarController:didSelectViewController:")
    public void didSelectViewController(UITabBarController tabBarController, UIViewController viewController) { throw new UnsupportedOperationException(); }
    @NotImplemented("tabBarController:willBeginCustomizingViewControllers:")
    public void willBeginCustomizingViewControllers(UITabBarController tabBarController, NSArray<?> viewControllers) { throw new UnsupportedOperationException(); }
    @NotImplemented("tabBarController:willEndCustomizingViewControllers:changed:")
    public void willEndCustomizingViewControllers(UITabBarController tabBarController, NSArray<?> viewControllers, boolean changed) { throw new UnsupportedOperationException(); }
    @NotImplemented("tabBarController:didEndCustomizingViewControllers:changed:")
    public void didEndCustomizingViewControllers(UITabBarController tabBarController, NSArray<?> viewControllers, boolean changed) { throw new UnsupportedOperationException(); }
    @NotImplemented("tabBarControllerSupportedInterfaceOrientations:")
    public @MachineSizedUInt long tabBarControllerSupportedInterfaceOrientations$(UITabBarController tabBarController) { throw new UnsupportedOperationException(); }
    @NotImplemented("tabBarControllerPreferredInterfaceOrientationForPresentation:")
    public UIInterfaceOrientation tabBarControllerPreferredInterfaceOrientationForPresentation$(UITabBarController tabBarController) { throw new UnsupportedOperationException(); }
    @NotImplemented("tabBarController:interactionControllerForAnimationController:")
    public UIViewControllerInteractiveTransitioning tabBarController$interactionControllerForAnimationController$(UITabBarController tabBarController, UIViewControllerAnimatedTransitioning animationController) { throw new UnsupportedOperationException(); }
    @NotImplemented("tabBarController:animationControllerForTransitionFromViewController:toViewController:")
    public UIViewControllerAnimatedTransitioning tabBarController$animationControllerForTransitionFromViewController$toViewController$(UITabBarController tabBarController, UIViewController fromVC, UIViewController toVC) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
