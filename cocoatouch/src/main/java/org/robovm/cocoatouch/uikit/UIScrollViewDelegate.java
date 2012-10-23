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
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html">UIScrollViewDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UIScrollViewDelegate /*</name>*/ /*<implements>*/ extends ObjCProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidEndDecelerating:">- (void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    void didEndDecelerating(UIScrollView scrollView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidEndDragging:willDecelerate:">- (void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate</a>
     * @since Available in iOS 2.0 and later.
     */
    void didEndDragging(UIScrollView scrollView, boolean decelerate);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidEndScrollingAnimation:">- (void)scrollViewDidEndScrollingAnimation:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    void didEndScrollingAnimation(UIScrollView scrollView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidEndZooming:withView:atScale:">- (void)scrollViewDidEndZooming:(UIScrollView *)scrollView withView:(UIView *)view atScale:(float)scale</a>
     * @since Available in iOS 2.0 and later.
     */
    void didEndZooming(UIScrollView scrollView, UIView view, float scale);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidScroll:">- (void)scrollViewDidScroll:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    void didScroll(UIScrollView scrollView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidScrollToTop:">- (void)scrollViewDidScrollToTop:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    void didScrollToTop(UIScrollView scrollView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidZoom:">- (void)scrollViewDidZoom:(UIScrollView *)scrollView</a>
     * @since Available in iOS 3.2 and later.
     */
    void didZoom(UIScrollView scrollView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/viewForZoomingInScrollView:">- (UIView *)viewForZoomingInScrollView:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    UIView getViewForZooming(UIScrollView scrollView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewShouldScrollToTop:">- (BOOL)scrollViewShouldScrollToTop:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean shouldScrollToTop(UIScrollView scrollView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewWillBeginDecelerating:">- (void)scrollViewWillBeginDecelerating:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    void willBeginDecelerating(UIScrollView scrollView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewWillBeginDragging:">- (void)scrollViewWillBeginDragging:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    void willBeginDragging(UIScrollView scrollView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewWillBeginZooming:withView:">- (void)scrollViewWillBeginZooming:(UIScrollView *)scrollView withView:(UIView *)view</a>
     * @since Available in iOS 3.2 and later.
     */
    void willBeginZooming(UIScrollView scrollView, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewWillEndDragging:withVelocity:targetContentOffset:">- (void)scrollViewWillEndDragging:(UIScrollView *)scrollView withVelocity:(CGPoint)velocity targetContentOffset:(inout CGPoint *)targetContentOffset</a>
     * @since Available in iOS 5.0 and later.
     */
    void willEndDragging(UIScrollView scrollView, CGPoint velocity, CGPoint targetContentOffset);
    /*</methods>*/

}
