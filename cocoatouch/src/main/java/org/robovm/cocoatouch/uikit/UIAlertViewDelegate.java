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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertViewDelegate_Protocol/UIAlertViewDelegate/UIAlertViewDelegate.html">UIAlertViewDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UIAlertViewDelegate /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertViewDelegate_Protocol/UIAlertViewDelegate/UIAlertViewDelegate.html#//apple_ref/occ/intfm/UIAlertViewDelegate/alertViewCancel:">- (void)alertViewCancel:(UIAlertView *)alertView</a>
     * @since Available in iOS 2.0 and later.
     */
    void cancel(UIAlertView alertView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertViewDelegate_Protocol/UIAlertViewDelegate/UIAlertViewDelegate.html#//apple_ref/occ/intfm/UIAlertViewDelegate/alertView:clickedButtonAtIndex:">- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    void clicked(UIAlertView alertView, int buttonIndex);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertViewDelegate_Protocol/UIAlertViewDelegate/UIAlertViewDelegate.html#//apple_ref/occ/intfm/UIAlertViewDelegate/alertView:didDismissWithButtonIndex:">- (void)alertView:(UIAlertView *)alertView didDismissWithButtonIndex:(NSInteger)buttonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    void didDismiss(UIAlertView alertView, int buttonIndex);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertViewDelegate_Protocol/UIAlertViewDelegate/UIAlertViewDelegate.html#//apple_ref/occ/intfm/UIAlertViewDelegate/didPresentAlertView:">- (void)didPresentAlertView:(UIAlertView *)alertView</a>
     * @since Available in iOS 2.0 and later.
     */
    void didPresent(UIAlertView alertView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertViewDelegate_Protocol/UIAlertViewDelegate/UIAlertViewDelegate.html#//apple_ref/occ/intfm/UIAlertViewDelegate/alertViewShouldEnableFirstOtherButton:">- (BOOL)alertViewShouldEnableFirstOtherButton:(UIAlertView *)alertView</a>
     * @since Available in iOS 5.0 and later.
     */
    boolean shouldEnableFirstOtherButton(UIAlertView alertView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertViewDelegate_Protocol/UIAlertViewDelegate/UIAlertViewDelegate.html#//apple_ref/occ/intfm/UIAlertViewDelegate/alertView:willDismissWithButtonIndex:">- (void)alertView:(UIAlertView *)alertView willDismissWithButtonIndex:(NSInteger)buttonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    void willDismiss(UIAlertView alertView, int buttonIndex);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIAlertViewDelegate_Protocol/UIAlertViewDelegate/UIAlertViewDelegate.html#//apple_ref/occ/intfm/UIAlertViewDelegate/willPresentAlertView:">- (void)willPresentAlertView:(UIAlertView *)alertView</a>
     * @since Available in iOS 2.0 and later.
     */
    void willPresent(UIAlertView alertView);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UIAlertViewDelegate {
        @NotImplemented("alertViewCancel:") public void cancel(UIAlertView alertView) { throw new UnsupportedOperationException(); }
        @NotImplemented("alertView:clickedButtonAtIndex:") public void clicked(UIAlertView alertView, int buttonIndex) { throw new UnsupportedOperationException(); }
        @NotImplemented("alertView:didDismissWithButtonIndex:") public void didDismiss(UIAlertView alertView, int buttonIndex) { throw new UnsupportedOperationException(); }
        @NotImplemented("didPresentAlertView:") public void didPresent(UIAlertView alertView) { throw new UnsupportedOperationException(); }
        @NotImplemented("alertViewShouldEnableFirstOtherButton:") public boolean shouldEnableFirstOtherButton(UIAlertView alertView) { throw new UnsupportedOperationException(); }
        @NotImplemented("alertView:willDismissWithButtonIndex:") public void willDismiss(UIAlertView alertView, int buttonIndex) { throw new UnsupportedOperationException(); }
        @NotImplemented("willPresentAlertView:") public void willPresent(UIAlertView alertView) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("alertViewCancel:") public static void cancel(UIAlertViewDelegate __self__, Selector __cmd__, UIAlertView alertView) { __self__.cancel(alertView); }
        @Callback @BindSelector("alertView:clickedButtonAtIndex:") public static void clicked(UIAlertViewDelegate __self__, Selector __cmd__, UIAlertView alertView, int buttonIndex) { __self__.clicked(alertView, buttonIndex); }
        @Callback @BindSelector("alertView:didDismissWithButtonIndex:") public static void didDismiss(UIAlertViewDelegate __self__, Selector __cmd__, UIAlertView alertView, int buttonIndex) { __self__.didDismiss(alertView, buttonIndex); }
        @Callback @BindSelector("didPresentAlertView:") public static void didPresent(UIAlertViewDelegate __self__, Selector __cmd__, UIAlertView alertView) { __self__.didPresent(alertView); }
        @Callback @BindSelector("alertViewShouldEnableFirstOtherButton:") public static boolean shouldEnableFirstOtherButton(UIAlertViewDelegate __self__, Selector __cmd__, UIAlertView alertView) { return __self__.shouldEnableFirstOtherButton(alertView); }
        @Callback @BindSelector("alertView:willDismissWithButtonIndex:") public static void willDismiss(UIAlertViewDelegate __self__, Selector __cmd__, UIAlertView alertView, int buttonIndex) { __self__.willDismiss(alertView, buttonIndex); }
        @Callback @BindSelector("willPresentAlertView:") public static void willPresent(UIAlertViewDelegate __self__, Selector __cmd__, UIAlertView alertView) { __self__.willPresent(alertView); }
    }
    /*</callbacks>*/

}
