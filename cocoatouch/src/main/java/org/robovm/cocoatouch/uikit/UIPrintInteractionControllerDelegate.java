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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionControllerDelegate_Protocol/Reference/Reference.html">UIPrintInteractionControllerDelegate Protocol Reference</a>
 *   @since Available in iOS 4.2 and later.
 * </div>
 */
@Protocol
public interface /*<name>*/ UIPrintInteractionControllerDelegate /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIPrintInteractionControllerDelegate/printInteractionController:choosePaper:">- (UIPrintPaper *)printInteractionController:(UIPrintInteractionController *)printInteractionController choosePaper:(NSArray *)paperList</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printInteractionController:choosePaper:") @Type("UIPrintPaper *") UIPrintPaper choosePaper(@Type("UIPrintInteractionController *") UIPrintInteractionController printInteractionController, @Type("NSArray *") NSArray paperList);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIPrintInteractionControllerDelegate/printInteractionControllerDidDismissPrinterOptions:">- (void)printInteractionControllerDidDismissPrinterOptions:(UIPrintInteractionController *)printInteractionController</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printInteractionControllerDidDismissPrinterOptions:") @Type("void") void didDismissPrinterOptions(@Type("UIPrintInteractionController *") UIPrintInteractionController printInteractionController);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIPrintInteractionControllerDelegate/printInteractionControllerDidFinishJob:">- (void)printInteractionControllerDidFinishJob:(UIPrintInteractionController *)printInteractionController</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printInteractionControllerDidFinishJob:") @Type("void") void didFinishJob(@Type("UIPrintInteractionController *") UIPrintInteractionController printInteractionController);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIPrintInteractionControllerDelegate/printInteractionControllerDidPresentPrinterOptions:">- (void)printInteractionControllerDidPresentPrinterOptions:(UIPrintInteractionController *)printInteractionController</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printInteractionControllerDidPresentPrinterOptions:") @Type("void") void didPresentPrinterOptions(@Type("UIPrintInteractionController *") UIPrintInteractionController printInteractionController);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIPrintInteractionControllerDelegate/printInteractionControllerParentViewController:">- (UIViewController *)printInteractionControllerParentViewController:(UIPrintInteractionController *)printInteractionController</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printInteractionControllerParentViewController:") @Type("UIViewController *") UIViewController getParentViewController(@Type("UIPrintInteractionController *") UIPrintInteractionController printInteractionController);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIPrintInteractionControllerDelegate/printInteractionControllerWillDismissPrinterOptions:">- (void)printInteractionControllerWillDismissPrinterOptions:(UIPrintInteractionController *)printInteractionController</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printInteractionControllerWillDismissPrinterOptions:") @Type("void") void willDismissPrinterOptions(@Type("UIPrintInteractionController *") UIPrintInteractionController printInteractionController);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIPrintInteractionControllerDelegate/printInteractionControllerWillPresentPrinterOptions:">- (void)printInteractionControllerWillPresentPrinterOptions:(UIPrintInteractionController *)printInteractionController</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printInteractionControllerWillPresentPrinterOptions:") @Type("void") void willPresentPrinterOptions(@Type("UIPrintInteractionController *") UIPrintInteractionController printInteractionController);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPrintInteractionControllerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIPrintInteractionControllerDelegate/printInteractionControllerWillStartJob:">- (void)printInteractionControllerWillStartJob:(UIPrintInteractionController *)printInteractionController</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("printInteractionControllerWillStartJob:") @Type("void") void willStartJob(@Type("UIPrintInteractionController *") UIPrintInteractionController printInteractionController);
    /*</methods>*/

}
