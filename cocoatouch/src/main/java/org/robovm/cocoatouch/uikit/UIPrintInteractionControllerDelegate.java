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

@Protocol
public interface /*<name>*/ UIPrintInteractionControllerDelegate /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Bind("printInteractionController:choosePaper:") @Type("UIPrintPaper *") UIPrintPaper choosePaper(@Type("UIPrintInteractionController *") UIPrintInteractionController printInteractionController, @Type("NSArray *") NSArray paperList);
    @Bind("printInteractionControllerDidDismissPrinterOptions:") @Type("void") void didDismissPrinterOptions(@Type("UIPrintInteractionController *") UIPrintInteractionController printInteractionController);
    @Bind("printInteractionControllerDidFinishJob:") @Type("void") void didFinishJob(@Type("UIPrintInteractionController *") UIPrintInteractionController printInteractionController);
    @Bind("printInteractionControllerDidPresentPrinterOptions:") @Type("void") void didPresentPrinterOptions(@Type("UIPrintInteractionController *") UIPrintInteractionController printInteractionController);
    @Bind("printInteractionControllerParentViewController:") @Type("UIViewController *") UIViewController getParentViewController(@Type("UIPrintInteractionController *") UIPrintInteractionController printInteractionController);
    @Bind("printInteractionControllerWillDismissPrinterOptions:") @Type("void") void willDismissPrinterOptions(@Type("UIPrintInteractionController *") UIPrintInteractionController printInteractionController);
    @Bind("printInteractionControllerWillPresentPrinterOptions:") @Type("void") void willPresentPrinterOptions(@Type("UIPrintInteractionController *") UIPrintInteractionController printInteractionController);
    @Bind("printInteractionControllerWillStartJob:") @Type("void") void willStartJob(@Type("UIPrintInteractionController *") UIPrintInteractionController printInteractionController);
    /*</methods>*/

}
