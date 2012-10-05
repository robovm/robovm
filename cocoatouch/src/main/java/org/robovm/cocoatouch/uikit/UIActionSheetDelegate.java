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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIModalViewDelegate_Protocol/UIActionSheetDelegate/UIActionSheetDelegate.html">UIActionSheetDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
@Protocol
public interface /*<name>*/ UIActionSheetDelegate /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIModalViewDelegate_Protocol/UIActionSheetDelegate/UIActionSheetDelegate.html#//apple_ref/occ/intfm/UIActionSheetDelegate/actionSheetCancel:">- (void)actionSheetCancel:(UIActionSheet *)actionSheet</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("actionSheetCancel:") @Type("void") void cancel(@Type("UIActionSheet *") UIActionSheet actionSheet);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIModalViewDelegate_Protocol/UIActionSheetDelegate/UIActionSheetDelegate.html#//apple_ref/occ/intfm/UIActionSheetDelegate/actionSheet:clickedButtonAtIndex:">- (void)actionSheet:(UIActionSheet *)actionSheet clickedButtonAtIndex:(NSInteger)buttonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("actionSheet:clickedButtonAtIndex:") @Type("void") void clicked(@Type("UIActionSheet *") UIActionSheet actionSheet, @Type("NSInteger") int buttonIndex);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIModalViewDelegate_Protocol/UIActionSheetDelegate/UIActionSheetDelegate.html#//apple_ref/occ/intfm/UIActionSheetDelegate/actionSheet:didDismissWithButtonIndex:">- (void)actionSheet:(UIActionSheet *)actionSheet didDismissWithButtonIndex:(NSInteger)buttonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("actionSheet:didDismissWithButtonIndex:") @Type("void") void didDismiss(@Type("UIActionSheet *") UIActionSheet actionSheet, @Type("NSInteger") int buttonIndex);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIModalViewDelegate_Protocol/UIActionSheetDelegate/UIActionSheetDelegate.html#//apple_ref/occ/intfm/UIActionSheetDelegate/didPresentActionSheet:">- (void)didPresentActionSheet:(UIActionSheet *)actionSheet</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("didPresentActionSheet:") @Type("void") void didPresent(@Type("UIActionSheet *") UIActionSheet actionSheet);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIModalViewDelegate_Protocol/UIActionSheetDelegate/UIActionSheetDelegate.html#//apple_ref/occ/intfm/UIActionSheetDelegate/actionSheet:willDismissWithButtonIndex:">- (void)actionSheet:(UIActionSheet *)actionSheet willDismissWithButtonIndex:(NSInteger)buttonIndex</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("actionSheet:willDismissWithButtonIndex:") @Type("void") void willDismiss(@Type("UIActionSheet *") UIActionSheet actionSheet, @Type("NSInteger") int buttonIndex);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIModalViewDelegate_Protocol/UIActionSheetDelegate/UIActionSheetDelegate.html#//apple_ref/occ/intfm/UIActionSheetDelegate/willPresentActionSheet:">- (void)willPresentActionSheet:(UIActionSheet *)actionSheet</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("willPresentActionSheet:") @Type("void") void willPresent(@Type("UIActionSheet *") UIActionSheet actionSheet);
    /*</methods>*/

}
