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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarDelegate_Protocol/Reference/Reference.html">UITabBarDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UITabBarDelegate /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITabBarDelegate/tabBar:didBeginCustomizingItems:">- (void)tabBar:(UITabBar *)tabBar didBeginCustomizingItems:(NSArray *)items</a>
     * @since Available in iOS 2.0 and later.
     */
    void didBeginCustomizingItems(UITabBar tabBar, NSArray items);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITabBarDelegate/tabBar:didEndCustomizingItems:changed:">- (void)tabBar:(UITabBar *)tabBar didEndCustomizingItems:(NSArray *)items changed:(BOOL)changed</a>
     * @since Available in iOS 2.0 and later.
     */
    void didEndCustomizingItems(UITabBar tabBar, NSArray items, boolean changed);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITabBarDelegate/tabBar:didSelectItem:">- (void)tabBar:(UITabBar *)tabBar didSelectItem:(UITabBarItem *)item</a>
     * @since Available in iOS 2.0 and later.
     */
    void didSelectItem(UITabBar tabBar, UITabBarItem item);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITabBarDelegate/tabBar:willBeginCustomizingItems:">- (void)tabBar:(UITabBar *)tabBar willBeginCustomizingItems:(NSArray *)items</a>
     * @since Available in iOS 2.0 and later.
     */
    void willBeginCustomizingItems(UITabBar tabBar, NSArray items);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITabBarDelegate/tabBar:willEndCustomizingItems:changed:">- (void)tabBar:(UITabBar *)tabBar willEndCustomizingItems:(NSArray *)items changed:(BOOL)changed</a>
     * @since Available in iOS 2.0 and later.
     */
    void willEndCustomizingItems(UITabBar tabBar, NSArray items, boolean changed);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UITabBarDelegate {
        @NotImplemented("tabBar:didBeginCustomizingItems:") public void didBeginCustomizingItems(UITabBar tabBar, NSArray items) { throw new UnsupportedOperationException(); }
        @NotImplemented("tabBar:didEndCustomizingItems:changed:") public void didEndCustomizingItems(UITabBar tabBar, NSArray items, boolean changed) { throw new UnsupportedOperationException(); }
        @NotImplemented("tabBar:didSelectItem:") public void didSelectItem(UITabBar tabBar, UITabBarItem item) { throw new UnsupportedOperationException(); }
        @NotImplemented("tabBar:willBeginCustomizingItems:") public void willBeginCustomizingItems(UITabBar tabBar, NSArray items) { throw new UnsupportedOperationException(); }
        @NotImplemented("tabBar:willEndCustomizingItems:changed:") public void willEndCustomizingItems(UITabBar tabBar, NSArray items, boolean changed) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("tabBar:didBeginCustomizingItems:") public static void didBeginCustomizingItems(UITabBarDelegate __self__, Selector __cmd__, UITabBar tabBar, NSArray items) { __self__.didBeginCustomizingItems(tabBar, items); }
        @Callback @BindSelector("tabBar:didEndCustomizingItems:changed:") public static void didEndCustomizingItems(UITabBarDelegate __self__, Selector __cmd__, UITabBar tabBar, NSArray items, boolean changed) { __self__.didEndCustomizingItems(tabBar, items, changed); }
        @Callback @BindSelector("tabBar:didSelectItem:") public static void didSelectItem(UITabBarDelegate __self__, Selector __cmd__, UITabBar tabBar, UITabBarItem item) { __self__.didSelectItem(tabBar, item); }
        @Callback @BindSelector("tabBar:willBeginCustomizingItems:") public static void willBeginCustomizingItems(UITabBarDelegate __self__, Selector __cmd__, UITabBar tabBar, NSArray items) { __self__.willBeginCustomizingItems(tabBar, items); }
        @Callback @BindSelector("tabBar:willEndCustomizingItems:changed:") public static void willEndCustomizingItems(UITabBarDelegate __self__, Selector __cmd__, UITabBar tabBar, NSArray items, boolean changed) { __self__.willEndCustomizingItems(tabBar, items, changed); }
    }
    /*</callbacks>*/

}
