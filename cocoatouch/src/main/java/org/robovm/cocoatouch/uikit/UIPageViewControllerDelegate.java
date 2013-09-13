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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerDelegateProtocolRef/UIPageViewControllerDelegate.html">UIPageViewControllerDelegate Protocol Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
public interface /*<name>*/ UIPageViewControllerDelegate /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerDelegateProtocolRef/UIPageViewControllerDelegate.html#//apple_ref/occ/intfm/UIPageViewControllerDelegate/pageViewController:didFinishAnimating:previousViewControllers:transitionCompleted:">- (void)pageViewController:(UIPageViewController *)pageViewController
    didFinishAnimating:(BOOL)finished
    previousViewControllers:(NSArray *)previousViewControllers
    transitionCompleted:(BOOL)completed</a>
     * @since Available in iOS 5.0 and later.
     */
    void didFinishAnimating(UIPageViewController pageViewController, boolean finished, NSArray previousViewControllers, boolean completed);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerDelegateProtocolRef/UIPageViewControllerDelegate.html#//apple_ref/occ/intfm/UIPageViewControllerDelegate/pageViewController:spineLocationForInterfaceOrientation:">- (UIPageViewControllerSpineLocation)pageViewController:(UIPageViewController *)pageViewController
    spineLocationForInterfaceOrientation:(UIInterfaceOrientation)orientation</a>
     * @since Available in iOS 5.0 and later.
     */
    UIPageViewControllerSpineLocation getSpineLocation(UIPageViewController pageViewController, UIInterfaceOrientation orientation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerDelegateProtocolRef/UIPageViewControllerDelegate.html#//apple_ref/occ/intfm/UIPageViewControllerDelegate/pageViewController:willTransitionToViewControllers:">- (void)pageViewController:(UIPageViewController *)pageViewController willTransitionToViewControllers:(NSArray *)pendingViewControllers</a>
     * @since Available in iOS 6.0 and later.
     */
    void willTransition(UIPageViewController pageViewController, NSArray pendingViewControllers);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UIPageViewControllerDelegate {
        @NotImplemented("pageViewController:didFinishAnimating:previousViewControllers:transitionCompleted:") public void didFinishAnimating(UIPageViewController pageViewController, boolean finished, NSArray previousViewControllers, boolean completed) { throw new UnsupportedOperationException(); }
        @NotImplemented("pageViewController:spineLocationForInterfaceOrientation:") public UIPageViewControllerSpineLocation getSpineLocation(UIPageViewController pageViewController, UIInterfaceOrientation orientation) { throw new UnsupportedOperationException(); }
        @NotImplemented("pageViewController:willTransitionToViewControllers:") public void willTransition(UIPageViewController pageViewController, NSArray pendingViewControllers) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("pageViewController:didFinishAnimating:previousViewControllers:transitionCompleted:") public static void didFinishAnimating(UIPageViewControllerDelegate __self__, Selector __cmd__, UIPageViewController pageViewController, boolean finished, NSArray previousViewControllers, boolean completed) { __self__.didFinishAnimating(pageViewController, finished, previousViewControllers, completed); }
        @Callback @BindSelector("pageViewController:spineLocationForInterfaceOrientation:") public static UIPageViewControllerSpineLocation getSpineLocation(UIPageViewControllerDelegate __self__, Selector __cmd__, UIPageViewController pageViewController, UIInterfaceOrientation orientation) { return __self__.getSpineLocation(pageViewController, orientation); }
        @Callback @BindSelector("pageViewController:willTransitionToViewControllers:") public static void willTransition(UIPageViewControllerDelegate __self__, Selector __cmd__, UIPageViewController pageViewController, NSArray pendingViewControllers) { __self__.willTransition(pageViewController, pendingViewControllers); }
    }
    /*</callbacks>*/

}
