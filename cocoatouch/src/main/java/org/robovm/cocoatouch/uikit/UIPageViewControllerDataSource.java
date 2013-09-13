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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerDataSourceProtocolRef/UIPageViewControllerDataSource.html">UIPageViewControllerDataSource Protocol Reference</a>
 *   @since Available in iOS 5.0 and later.
 * </div>
 */
public interface /*<name>*/ UIPageViewControllerDataSource /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerDataSourceProtocolRef/UIPageViewControllerDataSource.html#//apple_ref/occ/intfm/UIPageViewControllerDataSource/presentationCountForPageViewController:">- (NSInteger)presentationCountForPageViewController:(UIPageViewController *)pageViewController</a>
     * @since Available in iOS 6.0 and later.
     */
    int getPresentationCount(UIPageViewController pageViewController);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerDataSourceProtocolRef/UIPageViewControllerDataSource.html#//apple_ref/occ/intfm/UIPageViewControllerDataSource/presentationIndexForPageViewController:">- (NSInteger)presentationIndexForPageViewController:(UIPageViewController *)pageViewController</a>
     * @since Available in iOS 6.0 and later.
     */
    int getPresentationIndex(UIPageViewController pageViewController);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerDataSourceProtocolRef/UIPageViewControllerDataSource.html#//apple_ref/occ/intfm/UIPageViewControllerDataSource/pageViewController:viewControllerAfterViewController:">- (UIViewController *)pageViewController:(UIPageViewController *)pageViewController viewControllerAfterViewController:(UIViewController *)viewController</a>
     * @since Available in iOS 5.0 and later.
     */
    UIViewController getViewControllerAfter(UIPageViewController pageViewController, UIViewController viewController);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPageViewControllerDataSourceProtocolRef/UIPageViewControllerDataSource.html#//apple_ref/occ/intfm/UIPageViewControllerDataSource/pageViewController:viewControllerBeforeViewController:">- (UIViewController *)pageViewController:(UIPageViewController *)pageViewController viewControllerBeforeViewController:(UIViewController *)viewController</a>
     * @since Available in iOS 5.0 and later.
     */
    UIViewController getViewControllerBefore(UIPageViewController pageViewController, UIViewController viewController);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UIPageViewControllerDataSource {
        @NotImplemented("presentationCountForPageViewController:") public int getPresentationCount(UIPageViewController pageViewController) { throw new UnsupportedOperationException(); }
        @NotImplemented("presentationIndexForPageViewController:") public int getPresentationIndex(UIPageViewController pageViewController) { throw new UnsupportedOperationException(); }
        @NotImplemented("pageViewController:viewControllerAfterViewController:") public UIViewController getViewControllerAfter(UIPageViewController pageViewController, UIViewController viewController) { throw new UnsupportedOperationException(); }
        @NotImplemented("pageViewController:viewControllerBeforeViewController:") public UIViewController getViewControllerBefore(UIPageViewController pageViewController, UIViewController viewController) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("presentationCountForPageViewController:") public static int getPresentationCount(UIPageViewControllerDataSource __self__, Selector __cmd__, UIPageViewController pageViewController) { return __self__.getPresentationCount(pageViewController); }
        @Callback @BindSelector("presentationIndexForPageViewController:") public static int getPresentationIndex(UIPageViewControllerDataSource __self__, Selector __cmd__, UIPageViewController pageViewController) { return __self__.getPresentationIndex(pageViewController); }
        @Callback @BindSelector("pageViewController:viewControllerAfterViewController:") public static UIViewController getViewControllerAfter(UIPageViewControllerDataSource __self__, Selector __cmd__, UIPageViewController pageViewController, UIViewController viewController) { return __self__.getViewControllerAfter(pageViewController, viewController); }
        @Callback @BindSelector("pageViewController:viewControllerBeforeViewController:") public static UIViewController getViewControllerBefore(UIPageViewControllerDataSource __self__, Selector __cmd__, UIPageViewController pageViewController, UIViewController viewController) { return __self__.getViewControllerBefore(pageViewController, viewController); }
    }
    /*</callbacks>*/

}
