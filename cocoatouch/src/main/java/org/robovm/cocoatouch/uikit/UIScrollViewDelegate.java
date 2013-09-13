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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html">UIScrollViewDelegate Protocol Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
public interface /*<name>*/ UIScrollViewDelegate /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidEndDecelerating:">- (void)scrollViewDidEndDecelerating:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    void didEndDecelerating(UIScrollView scrollView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidEndDragging:willDecelerate:">- (void)scrollViewDidEndDragging:(UIScrollView *)scrollView willDecelerate:(BOOL)decelerate</a>
     * @since Available in iOS 2.0 and later.
     */
    void didEndDragging(UIScrollView scrollView, boolean decelerate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidEndScrollingAnimation:">- (void)scrollViewDidEndScrollingAnimation:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    void didEndScrollingAnimation(UIScrollView scrollView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidEndZooming:withView:atScale:">- (void)scrollViewDidEndZooming:(UIScrollView *)scrollView withView:(UIView *)view atScale:(float)scale</a>
     * @since Available in iOS 2.0 and later.
     */
    void didEndZooming(UIScrollView scrollView, UIView view, float scale);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidScroll:">- (void)scrollViewDidScroll:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    void didScroll(UIScrollView scrollView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidScrollToTop:">- (void)scrollViewDidScrollToTop:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    void didScrollToTop(UIScrollView scrollView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewDidZoom:">- (void)scrollViewDidZoom:(UIScrollView *)scrollView</a>
     * @since Available in iOS 3.2 and later.
     */
    void didZoom(UIScrollView scrollView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/viewForZoomingInScrollView:">- (UIView *)viewForZoomingInScrollView:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    UIView getViewForZooming(UIScrollView scrollView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewShouldScrollToTop:">- (BOOL)scrollViewShouldScrollToTop:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    boolean shouldScrollToTop(UIScrollView scrollView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewWillBeginDecelerating:">- (void)scrollViewWillBeginDecelerating:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    void willBeginDecelerating(UIScrollView scrollView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewWillBeginDragging:">- (void)scrollViewWillBeginDragging:(UIScrollView *)scrollView</a>
     * @since Available in iOS 2.0 and later.
     */
    void willBeginDragging(UIScrollView scrollView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewWillBeginZooming:withView:">- (void)scrollViewWillBeginZooming:(UIScrollView *)scrollView withView:(UIView *)view</a>
     * @since Available in iOS 3.2 and later.
     */
    void willBeginZooming(UIScrollView scrollView, UIView view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollViewDelegate_Protocol/Reference/UIScrollViewDelegate.html#//apple_ref/occ/intfm/UIScrollViewDelegate/scrollViewWillEndDragging:withVelocity:targetContentOffset:">- (void)scrollViewWillEndDragging:(UIScrollView *)scrollView withVelocity:(CGPoint)velocity targetContentOffset:(inout CGPoint *)targetContentOffset</a>
     * @since Available in iOS 5.0 and later.
     */
    void willEndDragging(UIScrollView scrollView, CGPoint velocity, CGPoint targetContentOffset);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UIScrollViewDelegate {
        @NotImplemented("scrollViewDidEndDecelerating:") public void didEndDecelerating(UIScrollView scrollView) { throw new UnsupportedOperationException(); }
        @NotImplemented("scrollViewDidEndDragging:willDecelerate:") public void didEndDragging(UIScrollView scrollView, boolean decelerate) { throw new UnsupportedOperationException(); }
        @NotImplemented("scrollViewDidEndScrollingAnimation:") public void didEndScrollingAnimation(UIScrollView scrollView) { throw new UnsupportedOperationException(); }
        @NotImplemented("scrollViewDidEndZooming:withView:atScale:") public void didEndZooming(UIScrollView scrollView, UIView view, float scale) { throw new UnsupportedOperationException(); }
        @NotImplemented("scrollViewDidScroll:") public void didScroll(UIScrollView scrollView) { throw new UnsupportedOperationException(); }
        @NotImplemented("scrollViewDidScrollToTop:") public void didScrollToTop(UIScrollView scrollView) { throw new UnsupportedOperationException(); }
        @NotImplemented("scrollViewDidZoom:") public void didZoom(UIScrollView scrollView) { throw new UnsupportedOperationException(); }
        @NotImplemented("viewForZoomingInScrollView:") public UIView getViewForZooming(UIScrollView scrollView) { throw new UnsupportedOperationException(); }
        @NotImplemented("scrollViewShouldScrollToTop:") public boolean shouldScrollToTop(UIScrollView scrollView) { throw new UnsupportedOperationException(); }
        @NotImplemented("scrollViewWillBeginDecelerating:") public void willBeginDecelerating(UIScrollView scrollView) { throw new UnsupportedOperationException(); }
        @NotImplemented("scrollViewWillBeginDragging:") public void willBeginDragging(UIScrollView scrollView) { throw new UnsupportedOperationException(); }
        @NotImplemented("scrollViewWillBeginZooming:withView:") public void willBeginZooming(UIScrollView scrollView, UIView view) { throw new UnsupportedOperationException(); }
        @NotImplemented("scrollViewWillEndDragging:withVelocity:targetContentOffset:") public void willEndDragging(UIScrollView scrollView, CGPoint velocity, CGPoint targetContentOffset) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("scrollViewDidEndDecelerating:") public static void didEndDecelerating(UIScrollViewDelegate __self__, Selector __cmd__, UIScrollView scrollView) { __self__.didEndDecelerating(scrollView); }
        @Callback @BindSelector("scrollViewDidEndDragging:willDecelerate:") public static void didEndDragging(UIScrollViewDelegate __self__, Selector __cmd__, UIScrollView scrollView, boolean decelerate) { __self__.didEndDragging(scrollView, decelerate); }
        @Callback @BindSelector("scrollViewDidEndScrollingAnimation:") public static void didEndScrollingAnimation(UIScrollViewDelegate __self__, Selector __cmd__, UIScrollView scrollView) { __self__.didEndScrollingAnimation(scrollView); }
        @Callback @BindSelector("scrollViewDidEndZooming:withView:atScale:") public static void didEndZooming(UIScrollViewDelegate __self__, Selector __cmd__, UIScrollView scrollView, UIView view, float scale) { __self__.didEndZooming(scrollView, view, scale); }
        @Callback @BindSelector("scrollViewDidScroll:") public static void didScroll(UIScrollViewDelegate __self__, Selector __cmd__, UIScrollView scrollView) { __self__.didScroll(scrollView); }
        @Callback @BindSelector("scrollViewDidScrollToTop:") public static void didScrollToTop(UIScrollViewDelegate __self__, Selector __cmd__, UIScrollView scrollView) { __self__.didScrollToTop(scrollView); }
        @Callback @BindSelector("scrollViewDidZoom:") public static void didZoom(UIScrollViewDelegate __self__, Selector __cmd__, UIScrollView scrollView) { __self__.didZoom(scrollView); }
        @Callback @BindSelector("viewForZoomingInScrollView:") public static UIView getViewForZooming(UIScrollViewDelegate __self__, Selector __cmd__, UIScrollView scrollView) { return __self__.getViewForZooming(scrollView); }
        @Callback @BindSelector("scrollViewShouldScrollToTop:") public static boolean shouldScrollToTop(UIScrollViewDelegate __self__, Selector __cmd__, UIScrollView scrollView) { return __self__.shouldScrollToTop(scrollView); }
        @Callback @BindSelector("scrollViewWillBeginDecelerating:") public static void willBeginDecelerating(UIScrollViewDelegate __self__, Selector __cmd__, UIScrollView scrollView) { __self__.willBeginDecelerating(scrollView); }
        @Callback @BindSelector("scrollViewWillBeginDragging:") public static void willBeginDragging(UIScrollViewDelegate __self__, Selector __cmd__, UIScrollView scrollView) { __self__.willBeginDragging(scrollView); }
        @Callback @BindSelector("scrollViewWillBeginZooming:withView:") public static void willBeginZooming(UIScrollViewDelegate __self__, Selector __cmd__, UIScrollView scrollView, UIView view) { __self__.willBeginZooming(scrollView, view); }
        @Callback @BindSelector("scrollViewWillEndDragging:withVelocity:targetContentOffset:") public static void willEndDragging(UIScrollViewDelegate __self__, Selector __cmd__, UIScrollView scrollView, @ByVal CGPoint velocity, CGPoint targetContentOffset) { __self__.willEndDragging(scrollView, velocity, targetContentOffset); }
    }
    /*</callbacks>*/

}
