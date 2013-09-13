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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewControllerDelegate_protocol/Reference/Reference.html">UISplitViewControllerDelegate Protocol Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
public interface /*<name>*/ UISplitViewControllerDelegate /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISplitViewControllerDelegate/splitViewController:shouldHideViewController:inOrientation:">- (BOOL)splitViewController:(UISplitViewController *)svc shouldHideViewController:(UIViewController *)vc inOrientation:(UIInterfaceOrientation)orientation</a>
     * @since Available in iOS 5.0 and later.
     */
    boolean shouldHideViewController(UISplitViewController svc, UIViewController vc, UIInterfaceOrientation orientation);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISplitViewControllerDelegate/splitViewController:willHideViewController:withBarButtonItem:forPopoverController:">- (void)splitViewController:(UISplitViewController *)svc willHideViewController:(UIViewController *)aViewController withBarButtonItem:(UIBarButtonItem *)barButtonItem forPopoverController:(UIPopoverController *)pc</a>
     * @since Available in iOS 3.2 and later.
     */
    void willHideViewController(UISplitViewController svc, UIViewController aViewController, UIBarButtonItem barButtonItem, UIPopoverController pc);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISplitViewControllerDelegate/splitViewController:popoverController:willPresentViewController:">- (void)splitViewController:(UISplitViewController *)svc popoverController:(UIPopoverController *)pc willPresentViewController:(UIViewController *)aViewController</a>
     * @since Available in iOS 3.2 and later.
     */
    void willPresentViewController(UISplitViewController svc, UIPopoverController pc, UIViewController aViewController);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISplitViewControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UISplitViewControllerDelegate/splitViewController:willShowViewController:invalidatingBarButtonItem:">- (void)splitViewController:(UISplitViewController *)svc willShowViewController:(UIViewController *)aViewController invalidatingBarButtonItem:(UIBarButtonItem *)button</a>
     * @since Available in iOS 3.2 and later.
     */
    void willShowViewController(UISplitViewController svc, UIViewController aViewController, UIBarButtonItem button);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UISplitViewControllerDelegate {
        @NotImplemented("splitViewController:shouldHideViewController:inOrientation:") public boolean shouldHideViewController(UISplitViewController svc, UIViewController vc, UIInterfaceOrientation orientation) { throw new UnsupportedOperationException(); }
        @NotImplemented("splitViewController:willHideViewController:withBarButtonItem:forPopoverController:") public void willHideViewController(UISplitViewController svc, UIViewController aViewController, UIBarButtonItem barButtonItem, UIPopoverController pc) { throw new UnsupportedOperationException(); }
        @NotImplemented("splitViewController:popoverController:willPresentViewController:") public void willPresentViewController(UISplitViewController svc, UIPopoverController pc, UIViewController aViewController) { throw new UnsupportedOperationException(); }
        @NotImplemented("splitViewController:willShowViewController:invalidatingBarButtonItem:") public void willShowViewController(UISplitViewController svc, UIViewController aViewController, UIBarButtonItem button) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("splitViewController:shouldHideViewController:inOrientation:") public static boolean shouldHideViewController(UISplitViewControllerDelegate __self__, Selector __cmd__, UISplitViewController svc, UIViewController vc, UIInterfaceOrientation orientation) { return __self__.shouldHideViewController(svc, vc, orientation); }
        @Callback @BindSelector("splitViewController:willHideViewController:withBarButtonItem:forPopoverController:") public static void willHideViewController(UISplitViewControllerDelegate __self__, Selector __cmd__, UISplitViewController svc, UIViewController aViewController, UIBarButtonItem barButtonItem, UIPopoverController pc) { __self__.willHideViewController(svc, aViewController, barButtonItem, pc); }
        @Callback @BindSelector("splitViewController:popoverController:willPresentViewController:") public static void willPresentViewController(UISplitViewControllerDelegate __self__, Selector __cmd__, UISplitViewController svc, UIPopoverController pc, UIViewController aViewController) { __self__.willPresentViewController(svc, pc, aViewController); }
        @Callback @BindSelector("splitViewController:willShowViewController:invalidatingBarButtonItem:") public static void willShowViewController(UISplitViewControllerDelegate __self__, Selector __cmd__, UISplitViewController svc, UIViewController aViewController, UIBarButtonItem button) { __self__.willShowViewController(svc, aViewController, button); }
    }
    /*</callbacks>*/

}
