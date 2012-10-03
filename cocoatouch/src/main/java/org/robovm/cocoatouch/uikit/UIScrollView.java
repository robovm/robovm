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

/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIScrollView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIScrollView /*</name>*/.class);
    }

    /*<constructors>*/
    public UIScrollView() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("alwaysBounceHorizontal") public native @Type("BOOL") boolean isAlwaysBounceHorizontal();
    @Bind("setAlwaysBounceHorizontal:") public native void setAlwaysBounceHorizontal(@Type("BOOL") boolean v);
    @Bind("alwaysBounceVertical") public native @Type("BOOL") boolean isAlwaysBounceVertical();
    @Bind("setAlwaysBounceVertical:") public native void setAlwaysBounceVertical(@Type("BOOL") boolean v);
    @Bind("bounces") public native @Type("BOOL") boolean isBounces();
    @Bind("setBounces:") public native void setBounces(@Type("BOOL") boolean v);
    @Bind("bouncesZoom") public native @Type("BOOL") boolean isBouncesZoom();
    @Bind("setBouncesZoom:") public native void setBouncesZoom(@Type("BOOL") boolean v);
    @Bind("canCancelContentTouches") public native @Type("BOOL") boolean isCanCancelContentTouches();
    @Bind("setCanCancelContentTouches:") public native void setCanCancelContentTouches(@Type("BOOL") boolean v);
    @Bind("contentInset") public native @Type("UIEdgeInsets") UIEdgeInsets getContentInset();
    @Bind("setContentInset:") public native void setContentInset(@Type("UIEdgeInsets") UIEdgeInsets v);
    @Bind("contentOffset") public native @Type("CGPoint") CGPoint getContentOffset();
    @Bind("setContentOffset:") public native void setContentOffset(@Type("CGPoint") CGPoint v);
    @Bind("contentSize") public native @Type("CGSize") CGSize getContentSize();
    @Bind("setContentSize:") public native void setContentSize(@Type("CGSize") CGSize v);
    @Bind("isDecelerating") public native @Type("BOOL") boolean isDecelerating();
    @Bind("decelerationRate") public native @Type("float") float getDecelerationRate();
    @Bind("setDecelerationRate:") public native void setDecelerationRate(@Type("float") float v);
    @Bind("delaysContentTouches") public native @Type("BOOL") boolean isDelaysContentTouches();
    @Bind("setDelaysContentTouches:") public native void setDelaysContentTouches(@Type("BOOL") boolean v);
    @Bind("delegate") public native @Type("id<UIScrollViewDelegate>") UIScrollViewDelegate getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UIScrollViewDelegate>") UIScrollViewDelegate v);
    @Bind("isDirectionalLockEnabled") public native @Type("BOOL") boolean isDirectionalLockEnabled();
    @Bind("setDirectionalLockEnabled:") public native void setDirectionalLockEnabled(@Type("BOOL") boolean v);
    @Bind("isDragging") public native @Type("BOOL") boolean isDragging();
    @Bind("indicatorStyle") public native @Type("UIScrollViewIndicatorStyle") UIScrollViewIndicatorStyle getIndicatorStyle();
    @Bind("setIndicatorStyle:") public native void setIndicatorStyle(@Type("UIScrollViewIndicatorStyle") UIScrollViewIndicatorStyle v);
    @Bind("maximumZoomScale") public native @Type("float") float getMaximumZoomScale();
    @Bind("setMaximumZoomScale:") public native void setMaximumZoomScale(@Type("float") float v);
    @Bind("minimumZoomScale") public native @Type("float") float getMinimumZoomScale();
    @Bind("setMinimumZoomScale:") public native void setMinimumZoomScale(@Type("float") float v);
    @Bind("isPagingEnabled") public native @Type("BOOL") boolean isPagingEnabled();
    @Bind("setPagingEnabled:") public native void setPagingEnabled(@Type("BOOL") boolean v);
    @Bind("panGestureRecognizer") public native @Type("UIPanGestureRecognizer *") UIPanGestureRecognizer getPanGestureRecognizer();
    @Bind("pinchGestureRecognizer") public native @Type("UIPinchGestureRecognizer *") UIPinchGestureRecognizer getPinchGestureRecognizer();
    @Bind("isScrollEnabled") public native @Type("BOOL") boolean isScrollEnabled();
    @Bind("setScrollEnabled:") public native void setScrollEnabled(@Type("BOOL") boolean v);
    @Bind("scrollIndicatorInsets") public native @Type("UIEdgeInsets") UIEdgeInsets getScrollIndicatorInsets();
    @Bind("setScrollIndicatorInsets:") public native void setScrollIndicatorInsets(@Type("UIEdgeInsets") UIEdgeInsets v);
    @Bind("scrollsToTop") public native @Type("BOOL") boolean isScrollsToTop();
    @Bind("setScrollsToTop:") public native void setScrollsToTop(@Type("BOOL") boolean v);
    @Bind("showsHorizontalScrollIndicator") public native @Type("BOOL") boolean isShowsHorizontalScrollIndicator();
    @Bind("setShowsHorizontalScrollIndicator:") public native void setShowsHorizontalScrollIndicator(@Type("BOOL") boolean v);
    @Bind("showsVerticalScrollIndicator") public native @Type("BOOL") boolean isShowsVerticalScrollIndicator();
    @Bind("setShowsVerticalScrollIndicator:") public native void setShowsVerticalScrollIndicator(@Type("BOOL") boolean v);
    @Bind("isTracking") public native @Type("BOOL") boolean isTracking();
    @Bind("isZoomBouncing") public native @Type("BOOL") boolean isZoomBouncing();
    @Bind("zoomScale") public native @Type("float") float getZoomScale();
    @Bind("setZoomScale:") public native void setZoomScale(@Type("float") float v);
    @Bind("isZooming") public native @Type("BOOL") boolean isZooming();
    /*</properties>*/
    /*<methods>*/
    @Bind("flashScrollIndicators") public native @Type("void") void flashScrollIndicators();
    @Bind("scrollRectToVisible:animated:") public native @Type("void") void scrollRectToVisible(@Type("CGRect") CGRect rect, @Type("BOOL") boolean animated);
    @Bind("setContentOffset:animated:") public native @Type("void") void setContentOffset(@Type("CGPoint") CGPoint contentOffset, @Type("BOOL") boolean animated);
    @Bind("setZoomScale:animated:") public native @Type("void") void setZoomScale(@Type("float") float scale, @Type("BOOL") boolean animated);
    @Bind("touchesShouldBegin:withEvent:inContentView:") public native @Type("BOOL") boolean touchesShouldBegin(@Type("NSSet *") NSSet touches, @Type("UIEvent *") UIEvent event, @Type("UIView *") UIView view);
    @Bind("touchesShouldCancelInContentView:") public native @Type("BOOL") boolean touchesShouldCancelInContentView(@Type("UIView *") UIView view);
    @Bind("zoomToRect:animated:") public native @Type("void") void zoomToRect(@Type("CGRect") CGRect rect, @Type("BOOL") boolean animated);
    /*</methods>*/

}
