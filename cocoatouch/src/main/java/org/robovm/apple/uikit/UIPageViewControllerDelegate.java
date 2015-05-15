/*
 * Copyright (C) 2013-2015 RoboVM AB
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
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ interface /*<name>*/UIPageViewControllerDelegate/*</name>*/ 
    /*<implements>*/extends NSObjectProtocol/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "pageViewController:willTransitionToViewControllers:")
    void willTransition(UIPageViewController pageViewController, NSArray<UIViewController> pendingViewControllers);
    @Method(selector = "pageViewController:didFinishAnimating:previousViewControllers:transitionCompleted:")
    void didFinishAnimating(UIPageViewController pageViewController, boolean finished, NSArray<UIViewController> previousViewControllers, boolean completed);
    @Method(selector = "pageViewController:spineLocationForInterfaceOrientation:")
    UIPageViewControllerSpineLocation getSpineLocation(UIPageViewController pageViewController, UIInterfaceOrientation orientation);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "pageViewControllerSupportedInterfaceOrientations:")
    UIInterfaceOrientationMask getSupportedInterfaceOrientations(UIPageViewController pageViewController);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Method(selector = "pageViewControllerPreferredInterfaceOrientationForPresentation:")
    UIInterfaceOrientation getPreferredInterfaceOrientation(UIPageViewController pageViewController);
    /*</methods>*/
    /*<adapter>*/
    /*</adapter>*/
}
