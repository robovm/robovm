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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html">UIDocumentInteractionControllerDelegate Protocol Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
public interface /*<name>*/ UIDocumentInteractionControllerDelegate /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionControllerDidDismissOpenInMenu:">- (void) documentInteractionControllerDidDismissOpenInMenu: (UIDocumentInteractionController *) controller</a>
     * @since Available in iOS 3.2 and later.
     */
    void didDismissOpenInMenu(UIDocumentInteractionController  controller);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionControllerDidDismissOptionsMenu:">- (void) documentInteractionControllerDidDismissOptionsMenu: (UIDocumentInteractionController *) controller</a>
     * @since Available in iOS 3.2 and later.
     */
    void didDismissOptionsMenu(UIDocumentInteractionController  controller);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionControllerDidEndPreview:">- (void)documentInteractionControllerDidEndPreview:(UIDocumentInteractionController *)controller</a>
     * @since Available in iOS 3.2 and later.
     */
    void didEndPreview(UIDocumentInteractionController controller);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionController:didEndSendingToApplication:">- (void) documentInteractionController: (UIDocumentInteractionController *) controller  didEndSendingToApplication: (NSString *) application</a>
     * @since Available in iOS 3.2 and later.
     */
    void didEndSendingToApplication(UIDocumentInteractionController  controller, String  application);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionControllerRectForPreview:">- (CGRect) documentInteractionControllerRectForPreview: (UIDocumentInteractionController *) controller</a>
     * @since Available in iOS 3.2 and later.
     */
    CGRect getRectForPreview(UIDocumentInteractionController  controller);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionControllerViewControllerForPreview:">- (UIViewController *) documentInteractionControllerViewControllerForPreview: (UIDocumentInteractionController *) controller</a>
     * @since Available in iOS 3.2 and later.
     */
    UIViewController getViewControllerForPreview(UIDocumentInteractionController  controller);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionControllerViewForPreview:">- (UIView *) documentInteractionControllerViewForPreview: (UIDocumentInteractionController *) controller</a>
     * @since Available in iOS 3.2 and later.
     */
    UIView getViewForPreview(UIDocumentInteractionController  controller);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionControllerWillBeginPreview:">- (void) documentInteractionControllerWillBeginPreview: (UIDocumentInteractionController *) controller</a>
     * @since Available in iOS 3.2 and later.
     */
    void willBeginPreview(UIDocumentInteractionController  controller);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionController:willBeginSendingToApplication:">- (void) documentInteractionController: (UIDocumentInteractionController *) controller  willBeginSendingToApplication: (NSString *) application</a>
     * @since Available in iOS 3.2 and later.
     */
    void willBeginSendingToApplication(UIDocumentInteractionController  controller, String  application);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionControllerWillPresentOpenInMenu:">- (void) documentInteractionControllerWillPresentOpenInMenu: (UIDocumentInteractionController *) controller</a>
     * @since Available in iOS 3.2 and later.
     */
    void willPresentOpenInMenu(UIDocumentInteractionController  controller);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIDocumentInteractionControllerDelegate_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIDocumentInteractionControllerDelegate/documentInteractionControllerWillPresentOptionsMenu:">- (void) documentInteractionControllerWillPresentOptionsMenu: (UIDocumentInteractionController *) controller</a>
     * @since Available in iOS 3.2 and later.
     */
    void willPresentOptionsMenu(UIDocumentInteractionController  controller);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UIDocumentInteractionControllerDelegate {
        @NotImplemented("documentInteractionControllerDidDismissOpenInMenu:") public void didDismissOpenInMenu(UIDocumentInteractionController  controller) { throw new UnsupportedOperationException(); }
        @NotImplemented("documentInteractionControllerDidDismissOptionsMenu:") public void didDismissOptionsMenu(UIDocumentInteractionController  controller) { throw new UnsupportedOperationException(); }
        @NotImplemented("documentInteractionControllerDidEndPreview:") public void didEndPreview(UIDocumentInteractionController controller) { throw new UnsupportedOperationException(); }
        @NotImplemented("documentInteractionController:didEndSendingToApplication:") public void didEndSendingToApplication(UIDocumentInteractionController  controller, String  application) { throw new UnsupportedOperationException(); }
        @NotImplemented("documentInteractionControllerRectForPreview:") public CGRect getRectForPreview(UIDocumentInteractionController  controller) { throw new UnsupportedOperationException(); }
        @NotImplemented("documentInteractionControllerViewControllerForPreview:") public UIViewController getViewControllerForPreview(UIDocumentInteractionController  controller) { throw new UnsupportedOperationException(); }
        @NotImplemented("documentInteractionControllerViewForPreview:") public UIView getViewForPreview(UIDocumentInteractionController  controller) { throw new UnsupportedOperationException(); }
        @NotImplemented("documentInteractionControllerWillBeginPreview:") public void willBeginPreview(UIDocumentInteractionController  controller) { throw new UnsupportedOperationException(); }
        @NotImplemented("documentInteractionController:willBeginSendingToApplication:") public void willBeginSendingToApplication(UIDocumentInteractionController  controller, String  application) { throw new UnsupportedOperationException(); }
        @NotImplemented("documentInteractionControllerWillPresentOpenInMenu:") public void willPresentOpenInMenu(UIDocumentInteractionController  controller) { throw new UnsupportedOperationException(); }
        @NotImplemented("documentInteractionControllerWillPresentOptionsMenu:") public void willPresentOptionsMenu(UIDocumentInteractionController  controller) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("documentInteractionControllerDidDismissOpenInMenu:") public static void didDismissOpenInMenu(UIDocumentInteractionControllerDelegate __self__, Selector __cmd__, UIDocumentInteractionController  controller) { __self__.didDismissOpenInMenu( controller); }
        @Callback @BindSelector("documentInteractionControllerDidDismissOptionsMenu:") public static void didDismissOptionsMenu(UIDocumentInteractionControllerDelegate __self__, Selector __cmd__, UIDocumentInteractionController  controller) { __self__.didDismissOptionsMenu( controller); }
        @Callback @BindSelector("documentInteractionControllerDidEndPreview:") public static void didEndPreview(UIDocumentInteractionControllerDelegate __self__, Selector __cmd__, UIDocumentInteractionController controller) { __self__.didEndPreview(controller); }
        @Callback @BindSelector("documentInteractionController:didEndSendingToApplication:") public static void didEndSendingToApplication(UIDocumentInteractionControllerDelegate __self__, Selector __cmd__, UIDocumentInteractionController  controller, String  application) { __self__.didEndSendingToApplication( controller,  application); }
        @Callback @BindSelector("documentInteractionControllerRectForPreview:") public static @ByVal CGRect getRectForPreview(UIDocumentInteractionControllerDelegate __self__, Selector __cmd__, UIDocumentInteractionController  controller) { return __self__.getRectForPreview( controller); }
        @Callback @BindSelector("documentInteractionControllerViewControllerForPreview:") public static UIViewController getViewControllerForPreview(UIDocumentInteractionControllerDelegate __self__, Selector __cmd__, UIDocumentInteractionController  controller) { return __self__.getViewControllerForPreview( controller); }
        @Callback @BindSelector("documentInteractionControllerViewForPreview:") public static UIView getViewForPreview(UIDocumentInteractionControllerDelegate __self__, Selector __cmd__, UIDocumentInteractionController  controller) { return __self__.getViewForPreview( controller); }
        @Callback @BindSelector("documentInteractionControllerWillBeginPreview:") public static void willBeginPreview(UIDocumentInteractionControllerDelegate __self__, Selector __cmd__, UIDocumentInteractionController  controller) { __self__.willBeginPreview( controller); }
        @Callback @BindSelector("documentInteractionController:willBeginSendingToApplication:") public static void willBeginSendingToApplication(UIDocumentInteractionControllerDelegate __self__, Selector __cmd__, UIDocumentInteractionController  controller, String  application) { __self__.willBeginSendingToApplication( controller,  application); }
        @Callback @BindSelector("documentInteractionControllerWillPresentOpenInMenu:") public static void willPresentOpenInMenu(UIDocumentInteractionControllerDelegate __self__, Selector __cmd__, UIDocumentInteractionController  controller) { __self__.willPresentOpenInMenu( controller); }
        @Callback @BindSelector("documentInteractionControllerWillPresentOptionsMenu:") public static void willPresentOptionsMenu(UIDocumentInteractionControllerDelegate __self__, Selector __cmd__, UIDocumentInteractionController  controller) { __self__.willPresentOptionsMenu( controller); }
    }
    /*</callbacks>*/

}
