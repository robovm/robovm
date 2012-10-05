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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBarDelegate_Protocol/Reference/Reference.html">UINavigationBarDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
@Protocol
public interface /*<name>*/ UINavigationBarDelegate /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UINavigationBarDelegate/navigationBar:didPopItem:">- (void)navigationBar:(UINavigationBar *)navigationBar didPopItem:(UINavigationItem *)item</a>
     * @since Available in iOS 2.0 and later.
     */
    void didPopItem(UINavigationBar navigationBar, UINavigationItem item);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UINavigationBarDelegate/navigationBar:didPushItem:">- (void)navigationBar:(UINavigationBar *)navigationBar didPushItem:(UINavigationItem *)item</a>
     * @since Available in iOS 2.0 and later.
     */
    void didPushItem(UINavigationBar navigationBar, UINavigationItem item);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UINavigationBarDelegate/navigationBar:shouldPopItem:">- (BOOL)navigationBar:(UINavigationBar *)navigationBar shouldPopItem:(UINavigationItem *)item</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean shouldPopItem(UINavigationBar navigationBar, UINavigationItem item);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UINavigationBarDelegate/navigationBar:shouldPushItem:">- (BOOL)navigationBar:(UINavigationBar *)navigationBar shouldPushItem:(UINavigationItem *)item</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean shouldPushItem(UINavigationBar navigationBar, UINavigationItem item);
    /*</methods>*/

}
