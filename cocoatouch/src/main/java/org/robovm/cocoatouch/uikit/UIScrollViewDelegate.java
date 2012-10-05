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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html">UIScrollViewDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
@Protocol
public interface /*<name>*/ UIScrollViewDelegate /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidEndDecelerating:">- (void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("scrollViewDidEndDecelerating:") @Type("void") void didEndDecelerating(@Type("UIScrollView *") UIScrollView scrollView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidEndDragging:willDecelerate:">- (void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("scrollViewDidEndDragging:willDecelerate:") @Type("void") void didEndDragging(@Type("UIScrollView *") UIScrollView scrollView, @Type("BOOL") boolean decelerate);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidEndScrollingAnimation:">- (void)scrollViewDidEndScrollingAnimation:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("scrollViewDidEndScrollingAnimation:") @Type("void") void didEndScrollingAnimation(@Type("UIScrollView *") UIScrollView scrollView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidEndZooming:withView:atScale:">- (void)scrollViewDidEndZooming:(UIScrollView *)scrollView withView:(UIView *)view atScale:(float)scale</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("scrollViewDidEndZooming:withView:atScale:") @Type("void") void didEndZooming(@Type("UIScrollView *") UIScrollView scrollView, @Type("UIView *") UIView view, @Type("float") float scale);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidScroll:">- (void)scrollViewDidScroll:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("scrollViewDidScroll:") @Type("void") void didScroll(@Type("UIScrollView *") UIScrollView scrollView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidScrollToTop:">- (void)scrollViewDidScrollToTop:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("scrollViewDidScrollToTop:") @Type("void") void didScrollToTop(@Type("UIScrollView *") UIScrollView scrollView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidZoom:">- (void)scrollViewDidZoom:(UIScrollView *)scrollView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("scrollViewDidZoom:") @Type("void") void didZoom(@Type("UIScrollView *") UIScrollView scrollView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/viewForZoomingInScrollView:">- (UIView *)viewForZoomingInScrollView:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("viewForZoomingInScrollView:") @Type("UIView *") UIView getViewForZooming(@Type("UIScrollView *") UIScrollView scrollView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewShouldScrollToTop:">- (BOOL)scrollViewShouldScrollToTop:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("scrollViewShouldScrollToTop:") @Type("BOOL") boolean shouldScrollToTop(@Type("UIScrollView *") UIScrollView scrollView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewWillBeginDecelerating:">- (void)scrollViewWillBeginDecelerating:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("scrollViewWillBeginDecelerating:") @Type("void") void willBeginDecelerating(@Type("UIScrollView *") UIScrollView scrollView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewWillBeginDragging:">- (void)scrollViewWillBeginDragging:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("scrollViewWillBeginDragging:") @Type("void") void willBeginDragging(@Type("UIScrollView *") UIScrollView scrollView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewWillBeginZooming:withView:">- (void)scrollViewWillBeginZooming:(UIScrollView *)scrollView withView:(UIView *)view</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("scrollViewWillBeginZooming:withView:") @Type("void") void willBeginZooming(@Type("UIScrollView *") UIScrollView scrollView, @Type("UIView *") UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewWillEndDragging:withVelocity:targetContentOffset:">- (void)scrollViewWillEndDragging:(UIScrollView *)scrollView withVelocity:(CGPoint)velocity targetContentOffset:(inout CGPoint *)targetContentOffset</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("scrollViewWillEndDragging:withVelocity:targetContentOffset:") @Type("void") void willEndDragging(@Type("UIScrollView *") UIScrollView scrollView, @Type("CGPoint") CGPoint velocity, @Type("CGPoint *") CGPoint targetContentOffset);
    /*</methods>*/

}
