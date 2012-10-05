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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerDelegateProtocolRef/UIPageViewControllerDelegate.html">UIPageViewControllerDelegate Protocol Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
@Protocol
public interface /*<name>*/ UIPageViewControllerDelegate /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerDelegateProtocolRef/UIPageViewControllerDelegate.html#//apple_ref/occ/intfm/UIPageViewControllerDelegate/pageViewController:didFinishAnimating:previousViewControllers:transitionCompleted:">- (void)pageViewController:(UIPageViewController *)pageViewController
    didFinishAnimating:(BOOL)finished
    previousViewControllers:(NSArray *)previousViewControllers
    transitionCompleted:(BOOL)completed</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("pageViewController:didFinishAnimating:previousViewControllers:transitionCompleted:") @Type("void") void didFinishAnimating(@Type("UIPageViewController *") UIPageViewController pageViewController, @Type("BOOL") boolean finished, @Type("NSArray *") NSArray previousViewControllers, @Type("BOOL") boolean completed);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerDelegateProtocolRef/UIPageViewControllerDelegate.html#//apple_ref/occ/intfm/UIPageViewControllerDelegate/pageViewController:spineLocationForInterfaceOrientation:">- (UIPageViewControllerSpineLocation)pageViewController:(UIPageViewController *)pageViewController
    spineLocationForInterfaceOrientation:(UIInterfaceOrientation)orientation</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("pageViewController:spineLocationForInterfaceOrientation:") @Type("UIPageViewControllerSpineLocation") UIPageViewControllerSpineLocation getSpineLocation(@Type("UIPageViewController *") UIPageViewController pageViewController, @Type("UIInterfaceOrientation") UIInterfaceOrientation orientation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerDelegateProtocolRef/UIPageViewControllerDelegate.html#//apple_ref/occ/intfm/UIPageViewControllerDelegate/pageViewController:willTransitionToViewControllers:">- (void)pageViewController:(UIPageViewController *)pageViewController willTransitionToViewControllers:(NSArray *)pendingViewControllers</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("pageViewController:willTransitionToViewControllers:") @Type("void") void willTransition(@Type("UIPageViewController *") UIPageViewController pageViewController, @Type("NSArray *") NSArray pendingViewControllers);
    /*</methods>*/

}
