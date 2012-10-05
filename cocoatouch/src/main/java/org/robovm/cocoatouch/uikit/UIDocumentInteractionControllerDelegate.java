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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html">UIDocumentInteractionControllerDelegate Protocol Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
@Protocol
public interface /*<name>*/ UIDocumentInteractionControllerDelegate /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionControllerDidDismissOpenInMenu:">- (void) documentInteractionControllerDidDismissOpenInMenu: (UIDocumentInteractionController *) controller</a>
     * @since Available in iOS 3.2 and later.
     */
    void didDismissOpenInMenu(UIDocumentInteractionController  controller);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionControllerDidDismissOptionsMenu:">- (void) documentInteractionControllerDidDismissOptionsMenu: (UIDocumentInteractionController *) controller</a>
     * @since Available in iOS 3.2 and later.
     */
    void didDismissOptionsMenu(UIDocumentInteractionController  controller);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionControllerDidEndPreview:">- (void)documentInteractionControllerDidEndPreview:(UIDocumentInteractionController *)controller</a>
     * @since Available in iOS 3.2 and later.
     */
    void didEndPreview(UIDocumentInteractionController controller);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionController:didEndSendingToApplication:">- (void) documentInteractionController: (UIDocumentInteractionController *) controller  didEndSendingToApplication: (NSString *) application</a>
     * @since Available in iOS 3.2 and later.
     */
    void didEndSendingToApplication(UIDocumentInteractionController  controller, String  application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionControllerRectForPreview:">- (CGRect) documentInteractionControllerRectForPreview: (UIDocumentInteractionController *) controller</a>
     * @since Available in iOS 3.2 and later.
     */
    CGRect getRectForPreview(UIDocumentInteractionController  controller);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionControllerViewControllerForPreview:">- (UIViewController *) documentInteractionControllerViewControllerForPreview: (UIDocumentInteractionController *) controller</a>
     * @since Available in iOS 3.2 and later.
     */
    UIViewController getViewControllerForPreview(UIDocumentInteractionController  controller);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionControllerViewForPreview:">- (UIView *) documentInteractionControllerViewForPreview: (UIDocumentInteractionController *) controller</a>
     * @since Available in iOS 3.2 and later.
     */
    UIView getViewForPreview(UIDocumentInteractionController  controller);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionControllerWillBeginPreview:">- (void) documentInteractionControllerWillBeginPreview: (UIDocumentInteractionController *) controller</a>
     * @since Available in iOS 3.2 and later.
     */
    void willBeginPreview(UIDocumentInteractionController  controller);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionController:willBeginSendingToApplication:">- (void) documentInteractionController: (UIDocumentInteractionController *) controller  willBeginSendingToApplication: (NSString *) application</a>
     * @since Available in iOS 3.2 and later.
     */
    void willBeginSendingToApplication(UIDocumentInteractionController  controller, String  application);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionControllerWillPresentOpenInMenu:">- (void) documentInteractionControllerWillPresentOpenInMenu: (UIDocumentInteractionController *) controller</a>
     * @since Available in iOS 3.2 and later.
     */
    void willPresentOpenInMenu(UIDocumentInteractionController  controller);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionControllerWillPresentOptionsMenu:">- (void) documentInteractionControllerWillPresentOptionsMenu: (UIDocumentInteractionController *) controller</a>
     * @since Available in iOS 3.2 and later.
     */
    void willPresentOptionsMenu(UIDocumentInteractionController  controller);
    /*</methods>*/

}
