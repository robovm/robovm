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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationControllerDelegate_Protocol/Reference/Reference.html">UINavigationControllerDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UINavigationControllerDelegate /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UINavigationControllerDelegate/navigationController:didShowViewController:animated:">- (void)navigationController:(UINavigationController *)navigationController  didShowViewController:(UIViewController *)viewController  animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    void didShowViewController(UINavigationController navigationController, UIViewController viewController, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UINavigationControllerDelegate/navigationController:willShowViewController:animated:">- (void)navigationController:(UINavigationController *)navigationController  willShowViewController:(UIViewController *)viewController  animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    void willShowViewController(UINavigationController navigationController, UIViewController viewController, boolean animated);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UINavigationControllerDelegate {
        @NotImplemented("navigationController:didShowViewController:animated:") public void didShowViewController(UINavigationController navigationController, UIViewController viewController, boolean animated) { throw new UnsupportedOperationException(); }
        @NotImplemented("navigationController:willShowViewController:animated:") public void willShowViewController(UINavigationController navigationController, UIViewController viewController, boolean animated) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("navigationController:didShowViewController:animated:") public static void didShowViewController(UINavigationControllerDelegate __self__, Selector __cmd__, UINavigationController navigationController, UIViewController viewController, boolean animated) { __self__.didShowViewController(navigationController, viewController, animated); }
        @Callback @BindSelector("navigationController:willShowViewController:animated:") public static void willShowViewController(UINavigationControllerDelegate __self__, Selector __cmd__, UINavigationController navigationController, UIViewController viewController, boolean animated) { __self__.willShowViewController(navigationController, viewController, animated); }
    }
    /*</callbacks>*/

}
