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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html">UIScrollView Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
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
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/alwaysBounceHorizontal">@property(nonatomic) BOOL alwaysBounceHorizontal</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("alwaysBounceHorizontal") public native @Type("BOOL") boolean isAlwaysBounceHorizontal();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/alwaysBounceHorizontal">@property(nonatomic) BOOL alwaysBounceHorizontal</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAlwaysBounceHorizontal:") public native void setAlwaysBounceHorizontal(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/alwaysBounceVertical">@property(nonatomic) BOOL alwaysBounceVertical</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("alwaysBounceVertical") public native @Type("BOOL") boolean isAlwaysBounceVertical();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/alwaysBounceVertical">@property(nonatomic) BOOL alwaysBounceVertical</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAlwaysBounceVertical:") public native void setAlwaysBounceVertical(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/bounces">@property(nonatomic) BOOL bounces</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("bounces") public native @Type("BOOL") boolean isBounces();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/bounces">@property(nonatomic) BOOL bounces</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBounces:") public native void setBounces(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/bouncesZoom">@property(nonatomic) BOOL bouncesZoom</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("bouncesZoom") public native @Type("BOOL") boolean isBouncesZoom();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/bouncesZoom">@property(nonatomic) BOOL bouncesZoom</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBouncesZoom:") public native void setBouncesZoom(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/canCancelContentTouches">@property(nonatomic) BOOL canCancelContentTouches</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("canCancelContentTouches") public native @Type("BOOL") boolean isCanCancelContentTouches();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/canCancelContentTouches">@property(nonatomic) BOOL canCancelContentTouches</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setCanCancelContentTouches:") public native void setCanCancelContentTouches(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/contentInset">@property(nonatomic) UIEdgeInsets contentInset</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("contentInset") public native @Type("UIEdgeInsets") UIEdgeInsets getContentInset();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/contentInset">@property(nonatomic) UIEdgeInsets contentInset</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setContentInset:") public native void setContentInset(@Type("UIEdgeInsets") UIEdgeInsets v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/contentOffset">@property(nonatomic) CGPoint contentOffset</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("contentOffset") public native @Type("CGPoint") CGPoint getContentOffset();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/contentOffset">@property(nonatomic) CGPoint contentOffset</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setContentOffset:") public native void setContentOffset(@Type("CGPoint") CGPoint v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/contentSize">@property(nonatomic) CGSize contentSize</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("contentSize") public native @Type("CGSize") CGSize getContentSize();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/contentSize">@property(nonatomic) CGSize contentSize</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setContentSize:") public native void setContentSize(@Type("CGSize") CGSize v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/decelerating">@property(nonatomic, readonly, getter=isDecelerating) BOOL decelerating</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isDecelerating") public native @Type("BOOL") boolean isDecelerating();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/decelerationRate">@property(nonatomic) float decelerationRate</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("decelerationRate") public native @Type("float") float getDecelerationRate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/decelerationRate">@property(nonatomic) float decelerationRate</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setDecelerationRate:") public native void setDecelerationRate(@Type("float") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/delaysContentTouches">@property(nonatomic) BOOL delaysContentTouches</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delaysContentTouches") public native @Type("BOOL") boolean isDelaysContentTouches();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/delaysContentTouches">@property(nonatomic) BOOL delaysContentTouches</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelaysContentTouches:") public native void setDelaysContentTouches(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/delegate">@property(nonatomic, assign) id&amp;lt;UIScrollViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native @Type("id<UIScrollViewDelegate>") UIScrollViewDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/delegate">@property(nonatomic, assign) id&amp;lt;UIScrollViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UIScrollViewDelegate>") UIScrollViewDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/directionalLockEnabled">@property(nonatomic, getter=isDirectionalLockEnabled) BOOL directionalLockEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isDirectionalLockEnabled") public native @Type("BOOL") boolean isDirectionalLockEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/directionalLockEnabled">@property(nonatomic, getter=isDirectionalLockEnabled) BOOL directionalLockEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDirectionalLockEnabled:") public native void setDirectionalLockEnabled(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/dragging">@property(nonatomic, readonly, getter=isDragging) BOOL dragging</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isDragging") public native @Type("BOOL") boolean isDragging();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/indicatorStyle">@property(nonatomic) UIScrollViewIndicatorStyle indicatorStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("indicatorStyle") public native @Type("UIScrollViewIndicatorStyle") UIScrollViewIndicatorStyle getIndicatorStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/indicatorStyle">@property(nonatomic) UIScrollViewIndicatorStyle indicatorStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setIndicatorStyle:") public native void setIndicatorStyle(@Type("UIScrollViewIndicatorStyle") UIScrollViewIndicatorStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/maximumZoomScale">@property(nonatomic) float maximumZoomScale</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("maximumZoomScale") public native @Type("float") float getMaximumZoomScale();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/maximumZoomScale">@property(nonatomic) float maximumZoomScale</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMaximumZoomScale:") public native void setMaximumZoomScale(@Type("float") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/minimumZoomScale">@property(nonatomic) float minimumZoomScale</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("minimumZoomScale") public native @Type("float") float getMinimumZoomScale();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/minimumZoomScale">@property(nonatomic) float minimumZoomScale</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMinimumZoomScale:") public native void setMinimumZoomScale(@Type("float") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/pagingEnabled">@property(nonatomic, getter=isPagingEnabled) BOOL pagingEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isPagingEnabled") public native @Type("BOOL") boolean isPagingEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/pagingEnabled">@property(nonatomic, getter=isPagingEnabled) BOOL pagingEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setPagingEnabled:") public native void setPagingEnabled(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/panGestureRecognizer">@property(nonatomic, readonly) UIPanGestureRecognizer *panGestureRecognizer</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("panGestureRecognizer") public native @Type("UIPanGestureRecognizer *") UIPanGestureRecognizer getPanGestureRecognizer();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/pinchGestureRecognizer">@property(nonatomic, readonly) UIPinchGestureRecognizer *pinchGestureRecognizer</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("pinchGestureRecognizer") public native @Type("UIPinchGestureRecognizer *") UIPinchGestureRecognizer getPinchGestureRecognizer();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/scrollEnabled">@property(nonatomic, getter=isScrollEnabled) BOOL scrollEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isScrollEnabled") public native @Type("BOOL") boolean isScrollEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/scrollEnabled">@property(nonatomic, getter=isScrollEnabled) BOOL scrollEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setScrollEnabled:") public native void setScrollEnabled(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/scrollIndicatorInsets">@property(nonatomic) UIEdgeInsets scrollIndicatorInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("scrollIndicatorInsets") public native @Type("UIEdgeInsets") UIEdgeInsets getScrollIndicatorInsets();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/scrollIndicatorInsets">@property(nonatomic) UIEdgeInsets scrollIndicatorInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setScrollIndicatorInsets:") public native void setScrollIndicatorInsets(@Type("UIEdgeInsets") UIEdgeInsets v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/scrollsToTop">@property(nonatomic) BOOL scrollsToTop</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("scrollsToTop") public native @Type("BOOL") boolean isScrollsToTop();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/scrollsToTop">@property(nonatomic) BOOL scrollsToTop</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setScrollsToTop:") public native void setScrollsToTop(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/showsHorizontalScrollIndicator">@property(nonatomic) BOOL showsHorizontalScrollIndicator</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("showsHorizontalScrollIndicator") public native @Type("BOOL") boolean isShowsHorizontalScrollIndicator();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/showsHorizontalScrollIndicator">@property(nonatomic) BOOL showsHorizontalScrollIndicator</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShowsHorizontalScrollIndicator:") public native void setShowsHorizontalScrollIndicator(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/showsVerticalScrollIndicator">@property(nonatomic) BOOL showsVerticalScrollIndicator</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("showsVerticalScrollIndicator") public native @Type("BOOL") boolean isShowsVerticalScrollIndicator();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/showsVerticalScrollIndicator">@property(nonatomic) BOOL showsVerticalScrollIndicator</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShowsVerticalScrollIndicator:") public native void setShowsVerticalScrollIndicator(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/tracking">@property(nonatomic, readonly, getter=isTracking) BOOL tracking</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isTracking") public native @Type("BOOL") boolean isTracking();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/zoomBouncing">@property(nonatomic, readonly, getter=isZoomBouncing) BOOL zoomBouncing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isZoomBouncing") public native @Type("BOOL") boolean isZoomBouncing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/zoomScale">@property(nonatomic) float zoomScale</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("zoomScale") public native @Type("float") float getZoomScale();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/zoomScale">@property(nonatomic) float zoomScale</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setZoomScale:") public native void setZoomScale(@Type("float") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/zooming">@property(nonatomic, readonly, getter=isZooming) BOOL zooming</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isZooming") public native @Type("BOOL") boolean isZooming();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/flashScrollIndicators">- (void)flashScrollIndicators</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("flashScrollIndicators") public native @Type("void") void flashScrollIndicators();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/scrollRectToVisible:animated:">- (void)scrollRectToVisible:(CGRect)rect animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("scrollRectToVisible:animated:") public native @Type("void") void scrollRectToVisible(@Type("CGRect") CGRect rect, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/setContentOffset:animated:">- (void)setContentOffset:(CGPoint)contentOffset animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setContentOffset:animated:") public native @Type("void") void setContentOffset(@Type("CGPoint") CGPoint contentOffset, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/setZoomScale:animated:">- (void)setZoomScale:(float)scale animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setZoomScale:animated:") public native @Type("void") void setZoomScale(@Type("float") float scale, @Type("BOOL") boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/touchesShouldBegin:withEvent:inContentView:">- (BOOL)touchesShouldBegin:(NSSet *)touches withEvent:(UIEvent *)event inContentView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("touchesShouldBegin:withEvent:inContentView:") public native @Type("BOOL") boolean touchesShouldBegin(@Type("NSSet *") NSSet touches, @Type("UIEvent *") UIEvent event, @Type("UIView *") UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/touchesShouldCancelInContentView:">- (BOOL)touchesShouldCancelInContentView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("touchesShouldCancelInContentView:") public native @Type("BOOL") boolean touchesShouldCancelInContentView(@Type("UIView *") UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/zoomToRect:animated:">- (void)zoomToRect:(CGRect)rect animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("zoomToRect:animated:") public native @Type("void") void zoomToRect(@Type("CGRect") CGRect rect, @Type("BOOL") boolean animated);
    /*</methods>*/

}
