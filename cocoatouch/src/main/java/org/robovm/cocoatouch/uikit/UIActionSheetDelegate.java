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
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIModalViewDelegate_Protocol/UIActionSheetDelegate/UIActionSheetDelegate.html">UIActionSheetDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UIActionSheetDelegate /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIModalViewDelegate_Protocol/UIActionSheetDelegate/UIActionSheetDelegate.html#//apple_ref/occ/intfm/UIActionSheetDelegate/actionSheetCancel:">- (void)actionSheetCancel:(UIActionSheet *)actionSheet</a>
     * @since Available in iOS 2.0 and later.
     */
    void cancel(UIActionSheet actionSheet);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIModalViewDelegate_Protocol/UIActionSheetDelegate/UIActionSheetDelegate.html#//apple_ref/occ/intfm/UIActionSheetDelegate/actionSheet:clickedButtonAtIndex:">- (void)actionSheet:(UIActionSheet *)actionSheet clickedButtonAtIndex:(NSInteger)buttonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    void clicked(UIActionSheet actionSheet, int buttonIndex);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIModalViewDelegate_Protocol/UIActionSheetDelegate/UIActionSheetDelegate.html#//apple_ref/occ/intfm/UIActionSheetDelegate/actionSheet:didDismissWithButtonIndex:">- (void)actionSheet:(UIActionSheet *)actionSheet didDismissWithButtonIndex:(NSInteger)buttonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    void didDismiss(UIActionSheet actionSheet, int buttonIndex);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIModalViewDelegate_Protocol/UIActionSheetDelegate/UIActionSheetDelegate.html#//apple_ref/occ/intfm/UIActionSheetDelegate/didPresentActionSheet:">- (void)didPresentActionSheet:(UIActionSheet *)actionSheet</a>
     * @since Available in iOS 2.0 and later.
     */
    void didPresent(UIActionSheet actionSheet);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIModalViewDelegate_Protocol/UIActionSheetDelegate/UIActionSheetDelegate.html#//apple_ref/occ/intfm/UIActionSheetDelegate/actionSheet:willDismissWithButtonIndex:">- (void)actionSheet:(UIActionSheet *)actionSheet willDismissWithButtonIndex:(NSInteger)buttonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    void willDismiss(UIActionSheet actionSheet, int buttonIndex);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIModalViewDelegate_Protocol/UIActionSheetDelegate/UIActionSheetDelegate.html#//apple_ref/occ/intfm/UIActionSheetDelegate/willPresentActionSheet:">- (void)willPresentActionSheet:(UIActionSheet *)actionSheet</a>
     * @since Available in iOS 2.0 and later.
     */
    void willPresent(UIActionSheet actionSheet);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UIActionSheetDelegate {
        @NotImplemented("actionSheetCancel:") public void cancel(UIActionSheet actionSheet) { throw new UnsupportedOperationException(); }
        @NotImplemented("actionSheet:clickedButtonAtIndex:") public void clicked(UIActionSheet actionSheet, int buttonIndex) { throw new UnsupportedOperationException(); }
        @NotImplemented("actionSheet:didDismissWithButtonIndex:") public void didDismiss(UIActionSheet actionSheet, int buttonIndex) { throw new UnsupportedOperationException(); }
        @NotImplemented("didPresentActionSheet:") public void didPresent(UIActionSheet actionSheet) { throw new UnsupportedOperationException(); }
        @NotImplemented("actionSheet:willDismissWithButtonIndex:") public void willDismiss(UIActionSheet actionSheet, int buttonIndex) { throw new UnsupportedOperationException(); }
        @NotImplemented("willPresentActionSheet:") public void willPresent(UIActionSheet actionSheet) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("actionSheetCancel:") public static void cancel(UIActionSheetDelegate __self__, Selector __cmd__, UIActionSheet actionSheet) { __self__.cancel(actionSheet); }
        @Callback @BindSelector("actionSheet:clickedButtonAtIndex:") public static void clicked(UIActionSheetDelegate __self__, Selector __cmd__, UIActionSheet actionSheet, int buttonIndex) { __self__.clicked(actionSheet, buttonIndex); }
        @Callback @BindSelector("actionSheet:didDismissWithButtonIndex:") public static void didDismiss(UIActionSheetDelegate __self__, Selector __cmd__, UIActionSheet actionSheet, int buttonIndex) { __self__.didDismiss(actionSheet, buttonIndex); }
        @Callback @BindSelector("didPresentActionSheet:") public static void didPresent(UIActionSheetDelegate __self__, Selector __cmd__, UIActionSheet actionSheet) { __self__.didPresent(actionSheet); }
        @Callback @BindSelector("actionSheet:willDismissWithButtonIndex:") public static void willDismiss(UIActionSheetDelegate __self__, Selector __cmd__, UIActionSheet actionSheet, int buttonIndex) { __self__.willDismiss(actionSheet, buttonIndex); }
        @Callback @BindSelector("willPresentActionSheet:") public static void willPresent(UIActionSheetDelegate __self__, Selector __cmd__, UIActionSheet actionSheet) { __self__.willPresent(actionSheet); }
    }
    /*</callbacks>*/

}
