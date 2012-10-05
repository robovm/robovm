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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIScrollView /*</name>*/.class);

    /*<constructors>*/
    protected UIScrollView(SkipInit skipInit) { super(skipInit); }
    public UIScrollView() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/alwaysBounceHorizontal">@property(nonatomic) BOOL alwaysBounceHorizontal</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("alwaysBounceHorizontal") public native boolean isAlwaysBounceHorizontal();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/alwaysBounceHorizontal">@property(nonatomic) BOOL alwaysBounceHorizontal</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAlwaysBounceHorizontal:") public native void setAlwaysBounceHorizontal(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/alwaysBounceVertical">@property(nonatomic) BOOL alwaysBounceVertical</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("alwaysBounceVertical") public native boolean isAlwaysBounceVertical();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/alwaysBounceVertical">@property(nonatomic) BOOL alwaysBounceVertical</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAlwaysBounceVertical:") public native void setAlwaysBounceVertical(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/bounces">@property(nonatomic) BOOL bounces</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("bounces") public native boolean isBounces();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/bounces">@property(nonatomic) BOOL bounces</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBounces:") public native void setBounces(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/bouncesZoom">@property(nonatomic) BOOL bouncesZoom</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("bouncesZoom") public native boolean isBouncesZoom();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/bouncesZoom">@property(nonatomic) BOOL bouncesZoom</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBouncesZoom:") public native void setBouncesZoom(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/canCancelContentTouches">@property(nonatomic) BOOL canCancelContentTouches</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("canCancelContentTouches") public native boolean isCanCancelContentTouches();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/canCancelContentTouches">@property(nonatomic) BOOL canCancelContentTouches</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setCanCancelContentTouches:") public native void setCanCancelContentTouches(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/contentInset">@property(nonatomic) UIEdgeInsets contentInset</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("contentInset") public native UIEdgeInsets getContentInset();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/contentInset">@property(nonatomic) UIEdgeInsets contentInset</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setContentInset:") public native void setContentInset(UIEdgeInsets v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/contentOffset">@property(nonatomic) CGPoint contentOffset</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("contentOffset") public native CGPoint getContentOffset();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/contentOffset">@property(nonatomic) CGPoint contentOffset</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setContentOffset:") public native void setContentOffset(CGPoint v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/contentSize">@property(nonatomic) CGSize contentSize</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("contentSize") public native CGSize getContentSize();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/contentSize">@property(nonatomic) CGSize contentSize</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setContentSize:") public native void setContentSize(CGSize v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/decelerating">@property(nonatomic, readonly, getter=isDecelerating) BOOL decelerating</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isDecelerating") public native boolean isDecelerating();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/decelerationRate">@property(nonatomic) float decelerationRate</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("decelerationRate") public native float getDecelerationRate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/decelerationRate">@property(nonatomic) float decelerationRate</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setDecelerationRate:") public native void setDecelerationRate(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/delaysContentTouches">@property(nonatomic) BOOL delaysContentTouches</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delaysContentTouches") public native boolean isDelaysContentTouches();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/delaysContentTouches">@property(nonatomic) BOOL delaysContentTouches</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelaysContentTouches:") public native void setDelaysContentTouches(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/delegate">@property(nonatomic, assign) id&amp;lt;UIScrollViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native UIScrollViewDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/delegate">@property(nonatomic, assign) id&amp;lt;UIScrollViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(UIScrollViewDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/directionalLockEnabled">@property(nonatomic, getter=isDirectionalLockEnabled) BOOL directionalLockEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isDirectionalLockEnabled") public native boolean isDirectionalLockEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/directionalLockEnabled">@property(nonatomic, getter=isDirectionalLockEnabled) BOOL directionalLockEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDirectionalLockEnabled:") public native void setDirectionalLockEnabled(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/dragging">@property(nonatomic, readonly, getter=isDragging) BOOL dragging</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isDragging") public native boolean isDragging();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/indicatorStyle">@property(nonatomic) UIScrollViewIndicatorStyle indicatorStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("indicatorStyle") public native UIScrollViewIndicatorStyle getIndicatorStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/indicatorStyle">@property(nonatomic) UIScrollViewIndicatorStyle indicatorStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setIndicatorStyle:") public native void setIndicatorStyle(UIScrollViewIndicatorStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/maximumZoomScale">@property(nonatomic) float maximumZoomScale</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("maximumZoomScale") public native float getMaximumZoomScale();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/maximumZoomScale">@property(nonatomic) float maximumZoomScale</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMaximumZoomScale:") public native void setMaximumZoomScale(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/minimumZoomScale">@property(nonatomic) float minimumZoomScale</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("minimumZoomScale") public native float getMinimumZoomScale();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/minimumZoomScale">@property(nonatomic) float minimumZoomScale</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setMinimumZoomScale:") public native void setMinimumZoomScale(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/pagingEnabled">@property(nonatomic, getter=isPagingEnabled) BOOL pagingEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isPagingEnabled") public native boolean isPagingEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/pagingEnabled">@property(nonatomic, getter=isPagingEnabled) BOOL pagingEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setPagingEnabled:") public native void setPagingEnabled(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/panGestureRecognizer">@property(nonatomic, readonly) UIPanGestureRecognizer *panGestureRecognizer</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("panGestureRecognizer") public native UIPanGestureRecognizer getPanGestureRecognizer();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/pinchGestureRecognizer">@property(nonatomic, readonly) UIPinchGestureRecognizer *pinchGestureRecognizer</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("pinchGestureRecognizer") public native UIPinchGestureRecognizer getPinchGestureRecognizer();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/scrollEnabled">@property(nonatomic, getter=isScrollEnabled) BOOL scrollEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isScrollEnabled") public native boolean isScrollEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/scrollEnabled">@property(nonatomic, getter=isScrollEnabled) BOOL scrollEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setScrollEnabled:") public native void setScrollEnabled(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/scrollIndicatorInsets">@property(nonatomic) UIEdgeInsets scrollIndicatorInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("scrollIndicatorInsets") public native UIEdgeInsets getScrollIndicatorInsets();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/scrollIndicatorInsets">@property(nonatomic) UIEdgeInsets scrollIndicatorInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setScrollIndicatorInsets:") public native void setScrollIndicatorInsets(UIEdgeInsets v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/scrollsToTop">@property(nonatomic) BOOL scrollsToTop</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("scrollsToTop") public native boolean isScrollsToTop();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/scrollsToTop">@property(nonatomic) BOOL scrollsToTop</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setScrollsToTop:") public native void setScrollsToTop(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/showsHorizontalScrollIndicator">@property(nonatomic) BOOL showsHorizontalScrollIndicator</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("showsHorizontalScrollIndicator") public native boolean isShowsHorizontalScrollIndicator();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/showsHorizontalScrollIndicator">@property(nonatomic) BOOL showsHorizontalScrollIndicator</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShowsHorizontalScrollIndicator:") public native void setShowsHorizontalScrollIndicator(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/showsVerticalScrollIndicator">@property(nonatomic) BOOL showsVerticalScrollIndicator</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("showsVerticalScrollIndicator") public native boolean isShowsVerticalScrollIndicator();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/showsVerticalScrollIndicator">@property(nonatomic) BOOL showsVerticalScrollIndicator</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShowsVerticalScrollIndicator:") public native void setShowsVerticalScrollIndicator(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/tracking">@property(nonatomic, readonly, getter=isTracking) BOOL tracking</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isTracking") public native boolean isTracking();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/zoomBouncing">@property(nonatomic, readonly, getter=isZoomBouncing) BOOL zoomBouncing</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isZoomBouncing") public native boolean isZoomBouncing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/zoomScale">@property(nonatomic) float zoomScale</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("zoomScale") public native float getZoomScale();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/zoomScale">@property(nonatomic) float zoomScale</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setZoomScale:") public native void setZoomScale(float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/zooming">@property(nonatomic, readonly, getter=isZooming) BOOL zooming</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isZooming") public native boolean isZooming();
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector flashScrollIndicators = Selector.register("flashScrollIndicators");
    @Bridge(symbol = "objc_msgSend") private native static void objc_flashScrollIndicators(UIScrollView __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_flashScrollIndicatorsSuper(ObjCSuper __super__, UIScrollView __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/flashScrollIndicators">- (void)flashScrollIndicators</a>
     * @since Available in iOS 2.0 and later.
     */
    public void flashScrollIndicators() {
        if (customClass) { objc_flashScrollIndicatorsSuper(getSuper(), this, flashScrollIndicators); } else { objc_flashScrollIndicators(this, flashScrollIndicators); }
    }
    
    private static final Selector scrollRectToVisible$animated$ = Selector.register("scrollRectToVisible:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_scrollRectToVisible(UIScrollView __self__, Selector __cmd__, CGRect rect, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_scrollRectToVisibleSuper(ObjCSuper __super__, UIScrollView __self__, Selector __cmd__, CGRect rect, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/scrollRectToVisible:animated:">- (void)scrollRectToVisible:(CGRect)rect animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void scrollRectToVisible(CGRect rect, boolean animated) {
        if (customClass) { objc_scrollRectToVisibleSuper(getSuper(), this, scrollRectToVisible$animated$, rect, animated); } else { objc_scrollRectToVisible(this, scrollRectToVisible$animated$, rect, animated); }
    }
    
    private static final Selector setContentOffset$animated$ = Selector.register("setContentOffset:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setContentOffset(UIScrollView __self__, Selector __cmd__, CGPoint contentOffset, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setContentOffsetSuper(ObjCSuper __super__, UIScrollView __self__, Selector __cmd__, CGPoint contentOffset, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/setContentOffset:animated:">- (void)setContentOffset:(CGPoint)contentOffset animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setContentOffset(CGPoint contentOffset, boolean animated) {
        if (customClass) { objc_setContentOffsetSuper(getSuper(), this, setContentOffset$animated$, contentOffset, animated); } else { objc_setContentOffset(this, setContentOffset$animated$, contentOffset, animated); }
    }
    
    private static final Selector setZoomScale$animated$ = Selector.register("setZoomScale:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setZoomScale(UIScrollView __self__, Selector __cmd__, float scale, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setZoomScaleSuper(ObjCSuper __super__, UIScrollView __self__, Selector __cmd__, float scale, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/setZoomScale:animated:">- (void)setZoomScale:(float)scale animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setZoomScale(float scale, boolean animated) {
        if (customClass) { objc_setZoomScaleSuper(getSuper(), this, setZoomScale$animated$, scale, animated); } else { objc_setZoomScale(this, setZoomScale$animated$, scale, animated); }
    }
    
    private static final Selector touchesShouldBegin$withEvent$inContentView$ = Selector.register("touchesShouldBegin:withEvent:inContentView:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_touchesShouldBegin(UIScrollView __self__, Selector __cmd__, NSSet touches, UIEvent event, UIView view);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_touchesShouldBeginSuper(ObjCSuper __super__, UIScrollView __self__, Selector __cmd__, NSSet touches, UIEvent event, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/touchesShouldBegin:withEvent:inContentView:">- (BOOL)touchesShouldBegin:(NSSet *)touches withEvent:(UIEvent *)event inContentView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean touchesShouldBegin(NSSet touches, UIEvent event, UIView view) {
        if (customClass) { return objc_touchesShouldBeginSuper(getSuper(), this, touchesShouldBegin$withEvent$inContentView$, touches, event, view); } else { return objc_touchesShouldBegin(this, touchesShouldBegin$withEvent$inContentView$, touches, event, view); }
    }
    
    private static final Selector touchesShouldCancelInContentView$ = Selector.register("touchesShouldCancelInContentView:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_touchesShouldCancelInContentView(UIScrollView __self__, Selector __cmd__, UIView view);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_touchesShouldCancelInContentViewSuper(ObjCSuper __super__, UIScrollView __self__, Selector __cmd__, UIView view);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/touchesShouldCancelInContentView:">- (BOOL)touchesShouldCancelInContentView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean touchesShouldCancelInContentView(UIView view) {
        if (customClass) { return objc_touchesShouldCancelInContentViewSuper(getSuper(), this, touchesShouldCancelInContentView$, view); } else { return objc_touchesShouldCancelInContentView(this, touchesShouldCancelInContentView$, view); }
    }
    
    private static final Selector zoomToRect$animated$ = Selector.register("zoomToRect:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_zoomToRect(UIScrollView __self__, Selector __cmd__, CGRect rect, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_zoomToRectSuper(ObjCSuper __super__, UIScrollView __self__, Selector __cmd__, CGRect rect, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/zoomToRect:animated:">- (void)zoomToRect:(CGRect)rect animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    public void zoomToRect(CGRect rect, boolean animated) {
        if (customClass) { objc_zoomToRectSuper(getSuper(), this, zoomToRect$animated$, rect, animated); } else { objc_zoomToRect(this, zoomToRect$animated$, rect, animated); }
    }
    /*</methods>*/

}
