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
import org.robovm.apple.coretext.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UINavigationControllerDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UINavigationControllerDelegate/*</implements>*/ {

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
    @NotImplemented("navigationController:willShowViewController:animated:")
    public void willShowViewController(UINavigationController navigationController, UIViewController viewController, boolean animated) { throw new UnsupportedOperationException(); }
    @NotImplemented("navigationController:didShowViewController:animated:")
    public void didShowViewController(UINavigationController navigationController, UIViewController viewController, boolean animated) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("navigationControllerSupportedInterfaceOrientations:")
    public UIInterfaceOrientationMask getSupportedInterfaceOrientations(UINavigationController navigationController) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("navigationControllerPreferredInterfaceOrientationForPresentation:")
    public UIInterfaceOrientation getPreferredInterfaceOrientation(UINavigationController navigationController) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("navigationController:interactionControllerForAnimationController:")
    public UIViewControllerInteractiveTransitioning getInteractionController(UINavigationController navigationController, UIViewControllerAnimatedTransitioning animationController) { throw new UnsupportedOperationException(); }
    /**
     * @since Available in iOS 7.0 and later.
     */
    @NotImplemented("navigationController:animationControllerForOperation:fromViewController:toViewController:")
    public UIViewControllerAnimatedTransitioning getAnimationController(UINavigationController navigationController, UINavigationControllerOperation operation, UIViewController fromVC, UIViewController toVC) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
