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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityItemSource_protocol/Reference/Reference.html">UIActivityItemSource Protocol Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
public interface /*<name>*/ UIActivityItemSource /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityItemSource_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIActivityItemSource/activityViewController:itemForActivityType:">- (id)activityViewController:(UIActivityViewController *)activityViewController itemForActivityType:(NSString *)activityType</a>
     * @since Available in iOS 6.0 and later.
     */
    NSObject getItem(UIActivityViewController activityViewController, String activityType);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIActivityItemSource_protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIActivityItemSource/activityViewControllerPlaceholderItem:">- (id)activityViewControllerPlaceholderItem:(UIActivityViewController *)activityViewController</a>
     * @since Available in iOS 6.0 and later.
     */
    NSObject getPlaceholderItem(UIActivityViewController activityViewController);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UIActivityItemSource {
        @NotImplemented("activityViewController:itemForActivityType:") public NSObject getItem(UIActivityViewController activityViewController, String activityType) { throw new UnsupportedOperationException(); }
        @NotImplemented("activityViewControllerPlaceholderItem:") public NSObject getPlaceholderItem(UIActivityViewController activityViewController) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("activityViewController:itemForActivityType:") public static NSObject getItem(UIActivityItemSource __self__, Selector __cmd__, UIActivityViewController activityViewController, String activityType) { return __self__.getItem(activityViewController, activityType); }
        @Callback @BindSelector("activityViewControllerPlaceholderItem:") public static NSObject getPlaceholderItem(UIActivityItemSource __self__, Selector __cmd__, UIActivityViewController activityViewController) { return __self__.getPlaceholderItem(activityViewController); }
    }
    /*</callbacks>*/

}
