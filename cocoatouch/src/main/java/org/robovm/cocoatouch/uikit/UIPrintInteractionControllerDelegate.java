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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionControllerDelegate_Protocol/Reference/Reference.html">UIPrintInteractionControllerDelegate Protocol Reference</a>
 *   @since Available in iOS 4.2 and later.
 * </div>
 */
public interface /*<name>*/ UIPrintInteractionControllerDelegate /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIPrintInteractionControllerDelegate/printInteractionController:choosePaper:">- (UIPrintPaper *)printInteractionController:(UIPrintInteractionController *)printInteractionController choosePaper:(NSArray *)paperList</a>
     * @since Available in iOS 4.2 and later.
     */
    UIPrintPaper choosePaper(UIPrintInteractionController printInteractionController, NSArray paperList);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIPrintInteractionControllerDelegate/printInteractionControllerDidDismissPrinterOptions:">- (void)printInteractionControllerDidDismissPrinterOptions:(UIPrintInteractionController *)printInteractionController</a>
     * @since Available in iOS 4.2 and later.
     */
    void didDismissPrinterOptions(UIPrintInteractionController printInteractionController);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIPrintInteractionControllerDelegate/printInteractionControllerDidFinishJob:">- (void)printInteractionControllerDidFinishJob:(UIPrintInteractionController *)printInteractionController</a>
     * @since Available in iOS 4.2 and later.
     */
    void didFinishJob(UIPrintInteractionController printInteractionController);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIPrintInteractionControllerDelegate/printInteractionControllerDidPresentPrinterOptions:">- (void)printInteractionControllerDidPresentPrinterOptions:(UIPrintInteractionController *)printInteractionController</a>
     * @since Available in iOS 4.2 and later.
     */
    void didPresentPrinterOptions(UIPrintInteractionController printInteractionController);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIPrintInteractionControllerDelegate/printInteractionControllerParentViewController:">- (UIViewController *)printInteractionControllerParentViewController:(UIPrintInteractionController *)printInteractionController</a>
     * @since Available in iOS 4.2 and later.
     */
    UIViewController getParentViewController(UIPrintInteractionController printInteractionController);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIPrintInteractionControllerDelegate/printInteractionControllerWillDismissPrinterOptions:">- (void)printInteractionControllerWillDismissPrinterOptions:(UIPrintInteractionController *)printInteractionController</a>
     * @since Available in iOS 4.2 and later.
     */
    void willDismissPrinterOptions(UIPrintInteractionController printInteractionController);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIPrintInteractionControllerDelegate/printInteractionControllerWillPresentPrinterOptions:">- (void)printInteractionControllerWillPresentPrinterOptions:(UIPrintInteractionController *)printInteractionController</a>
     * @since Available in iOS 4.2 and later.
     */
    void willPresentPrinterOptions(UIPrintInteractionController printInteractionController);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIPrintInteractionControllerDelegate/printInteractionControllerWillStartJob:">- (void)printInteractionControllerWillStartJob:(UIPrintInteractionController *)printInteractionController</a>
     * @since Available in iOS 4.2 and later.
     */
    void willStartJob(UIPrintInteractionController printInteractionController);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UIPrintInteractionControllerDelegate {
        @NotImplemented("printInteractionController:choosePaper:") public UIPrintPaper choosePaper(UIPrintInteractionController printInteractionController, NSArray paperList) { throw new UnsupportedOperationException(); }
        @NotImplemented("printInteractionControllerDidDismissPrinterOptions:") public void didDismissPrinterOptions(UIPrintInteractionController printInteractionController) { throw new UnsupportedOperationException(); }
        @NotImplemented("printInteractionControllerDidFinishJob:") public void didFinishJob(UIPrintInteractionController printInteractionController) { throw new UnsupportedOperationException(); }
        @NotImplemented("printInteractionControllerDidPresentPrinterOptions:") public void didPresentPrinterOptions(UIPrintInteractionController printInteractionController) { throw new UnsupportedOperationException(); }
        @NotImplemented("printInteractionControllerParentViewController:") public UIViewController getParentViewController(UIPrintInteractionController printInteractionController) { throw new UnsupportedOperationException(); }
        @NotImplemented("printInteractionControllerWillDismissPrinterOptions:") public void willDismissPrinterOptions(UIPrintInteractionController printInteractionController) { throw new UnsupportedOperationException(); }
        @NotImplemented("printInteractionControllerWillPresentPrinterOptions:") public void willPresentPrinterOptions(UIPrintInteractionController printInteractionController) { throw new UnsupportedOperationException(); }
        @NotImplemented("printInteractionControllerWillStartJob:") public void willStartJob(UIPrintInteractionController printInteractionController) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("printInteractionController:choosePaper:") public static UIPrintPaper choosePaper(UIPrintInteractionControllerDelegate __self__, Selector __cmd__, UIPrintInteractionController printInteractionController, NSArray paperList) { return __self__.choosePaper(printInteractionController, paperList); }
        @Callback @BindSelector("printInteractionControllerDidDismissPrinterOptions:") public static void didDismissPrinterOptions(UIPrintInteractionControllerDelegate __self__, Selector __cmd__, UIPrintInteractionController printInteractionController) { __self__.didDismissPrinterOptions(printInteractionController); }
        @Callback @BindSelector("printInteractionControllerDidFinishJob:") public static void didFinishJob(UIPrintInteractionControllerDelegate __self__, Selector __cmd__, UIPrintInteractionController printInteractionController) { __self__.didFinishJob(printInteractionController); }
        @Callback @BindSelector("printInteractionControllerDidPresentPrinterOptions:") public static void didPresentPrinterOptions(UIPrintInteractionControllerDelegate __self__, Selector __cmd__, UIPrintInteractionController printInteractionController) { __self__.didPresentPrinterOptions(printInteractionController); }
        @Callback @BindSelector("printInteractionControllerParentViewController:") public static UIViewController getParentViewController(UIPrintInteractionControllerDelegate __self__, Selector __cmd__, UIPrintInteractionController printInteractionController) { return __self__.getParentViewController(printInteractionController); }
        @Callback @BindSelector("printInteractionControllerWillDismissPrinterOptions:") public static void willDismissPrinterOptions(UIPrintInteractionControllerDelegate __self__, Selector __cmd__, UIPrintInteractionController printInteractionController) { __self__.willDismissPrinterOptions(printInteractionController); }
        @Callback @BindSelector("printInteractionControllerWillPresentPrinterOptions:") public static void willPresentPrinterOptions(UIPrintInteractionControllerDelegate __self__, Selector __cmd__, UIPrintInteractionController printInteractionController) { __self__.willPresentPrinterOptions(printInteractionController); }
        @Callback @BindSelector("printInteractionControllerWillStartJob:") public static void willStartJob(UIPrintInteractionControllerDelegate __self__, Selector __cmd__, UIPrintInteractionController printInteractionController) { __self__.willStartJob(printInteractionController); }
    }
    /*</callbacks>*/

}
