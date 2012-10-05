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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewControllerDelegate_protocol/Reference/Reference.html">UISplitViewControllerDelegate Protocol Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
@Protocol
public interface /*<name>*/ UISplitViewControllerDelegate /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISplitViewControllerDelegate/splitViewController:shouldHideViewController:inOrientation:">- (BOOL)splitViewController:(UISplitViewController *)svc shouldHideViewController:(UIViewController *)vc inOrientation:(UIInterfaceOrientation)orientation</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("splitViewController:shouldHideViewController:inOrientation:") @Type("BOOL") boolean shouldHideViewController(@Type("UISplitViewController *") UISplitViewController svc, @Type("UIViewController *") UIViewController vc, @Type("UIInterfaceOrientation") UIInterfaceOrientation orientation);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISplitViewControllerDelegate/splitViewController:willHideViewController:withBarButtonItem:forPopoverController:">- (void)splitViewController:(UISplitViewController *)svc willHideViewController:(UIViewController *)aViewController withBarButtonItem:(UIBarButtonItem *)barButtonItem forPopoverController:(UIPopoverController *)pc</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("splitViewController:willHideViewController:withBarButtonItem:forPopoverController:") @Type("void") void willHideViewController(@Type("UISplitViewController *") UISplitViewController svc, @Type("UIViewController *") UIViewController aViewController, @Type("UIBarButtonItem *") UIBarButtonItem barButtonItem, @Type("UIPopoverController *") UIPopoverController pc);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISplitViewControllerDelegate/splitViewController:popoverController:willPresentViewController:">- (void)splitViewController:(UISplitViewController *)svc popoverController:(UIPopoverController *)pc willPresentViewController:(UIViewController *)aViewController</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("splitViewController:popoverController:willPresentViewController:") @Type("void") void willPresentViewController(@Type("UISplitViewController *") UISplitViewController svc, @Type("UIPopoverController *") UIPopoverController pc, @Type("UIViewController *") UIViewController aViewController);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISplitViewControllerDelegate/splitViewController:willShowViewController:invalidatingBarButtonItem:">- (void)splitViewController:(UISplitViewController *)svc willShowViewController:(UIViewController *)aViewController invalidatingBarButtonItem:(UIBarButtonItem *)button</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("splitViewController:willShowViewController:invalidatingBarButtonItem:") @Type("void") void willShowViewController(@Type("UISplitViewController *") UISplitViewController svc, @Type("UIViewController *") UIViewController aViewController, @Type("UIBarButtonItem *") UIBarButtonItem button);
    /*</methods>*/

}
