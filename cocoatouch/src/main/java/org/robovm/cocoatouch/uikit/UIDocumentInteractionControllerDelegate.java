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
public interface /*<name>*/ UIDocumentInteractionControllerDelegate /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Bind("documentInteractionControllerDidDismissOpenInMenu:") @Type("void") void didDismissOpenInMenu(@Type("UIDocumentInteractionController *") UIDocumentInteractionController  controller);
    @Bind("documentInteractionControllerDidDismissOptionsMenu:") @Type("void") void didDismissOptionsMenu(@Type("UIDocumentInteractionController *") UIDocumentInteractionController  controller);
    @Bind("documentInteractionControllerDidEndPreview:") @Type("void") void didEndPreview(@Type("UIDocumentInteractionController *") UIDocumentInteractionController controller);
    @Bind("documentInteractionController:didEndSendingToApplication:") @Type("void") void didEndSendingToApplication(@Type("UIDocumentInteractionController *") UIDocumentInteractionController  controller, @Type("NSString *") String  application);
    @Bind("documentInteractionControllerRectForPreview:") @Type("CGRect") CGRect getRectForPreview(@Type("UIDocumentInteractionController *") UIDocumentInteractionController  controller);
    @Bind("documentInteractionControllerViewControllerForPreview:") @Type("UIViewController *") UIViewController getViewControllerForPreview(@Type("UIDocumentInteractionController *") UIDocumentInteractionController  controller);
    @Bind("documentInteractionControllerViewForPreview:") @Type("UIView *") UIView getViewForPreview(@Type("UIDocumentInteractionController *") UIDocumentInteractionController  controller);
    @Bind("documentInteractionControllerWillBeginPreview:") @Type("void") void willBeginPreview(@Type("UIDocumentInteractionController *") UIDocumentInteractionController  controller);
    @Bind("documentInteractionController:willBeginSendingToApplication:") @Type("void") void willBeginSendingToApplication(@Type("UIDocumentInteractionController *") UIDocumentInteractionController  controller, @Type("NSString *") String  application);
    @Bind("documentInteractionControllerWillPresentOpenInMenu:") @Type("void") void willPresentOpenInMenu(@Type("UIDocumentInteractionController *") UIDocumentInteractionController  controller);
    @Bind("documentInteractionControllerWillPresentOptionsMenu:") @Type("void") void willPresentOptionsMenu(@Type("UIDocumentInteractionController *") UIDocumentInteractionController  controller);
    /*</methods>*/

}
