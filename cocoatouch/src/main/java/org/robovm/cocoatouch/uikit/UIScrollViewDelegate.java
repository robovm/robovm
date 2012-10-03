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
public interface /*<name>*/ UIScrollViewDelegate /*</name>*/ /*<implements>*/ /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Bind("scrollViewDidEndDecelerating:") @Type("void") void didEndDecelerating(@Type("UIScrollView *") UIScrollView scrollView);
    @Bind("scrollViewDidEndDragging:willDecelerate:") @Type("void") void didEndDragging(@Type("UIScrollView *") UIScrollView scrollView, @Type("BOOL") boolean decelerate);
    @Bind("scrollViewDidEndScrollingAnimation:") @Type("void") void didEndScrollingAnimation(@Type("UIScrollView *") UIScrollView scrollView);
    @Bind("scrollViewDidEndZooming:withView:atScale:") @Type("void") void didEndZooming(@Type("UIScrollView *") UIScrollView scrollView, @Type("UIView *") UIView view, @Type("float") float scale);
    @Bind("scrollViewDidScroll:") @Type("void") void didScroll(@Type("UIScrollView *") UIScrollView scrollView);
    @Bind("scrollViewDidScrollToTop:") @Type("void") void didScrollToTop(@Type("UIScrollView *") UIScrollView scrollView);
    @Bind("scrollViewDidZoom:") @Type("void") void didZoom(@Type("UIScrollView *") UIScrollView scrollView);
    @Bind("viewForZoomingInScrollView:") @Type("UIView *") UIView getViewForZooming(@Type("UIScrollView *") UIScrollView scrollView);
    @Bind("scrollViewShouldScrollToTop:") @Type("BOOL") boolean shouldScrollToTop(@Type("UIScrollView *") UIScrollView scrollView);
    @Bind("scrollViewWillBeginDecelerating:") @Type("void") void willBeginDecelerating(@Type("UIScrollView *") UIScrollView scrollView);
    @Bind("scrollViewWillBeginDragging:") @Type("void") void willBeginDragging(@Type("UIScrollView *") UIScrollView scrollView);
    @Bind("scrollViewWillBeginZooming:withView:") @Type("void") void willBeginZooming(@Type("UIScrollView *") UIScrollView scrollView, @Type("UIView *") UIView view);
    @Bind("scrollViewWillEndDragging:withVelocity:targetContentOffset:") @Type("void") void willEndDragging(@Type("UIScrollView *") UIScrollView scrollView, @Type("CGPoint") CGPoint velocity, @Type("CGPoint *") CGPoint targetContentOffset);
    /*</methods>*/

}
