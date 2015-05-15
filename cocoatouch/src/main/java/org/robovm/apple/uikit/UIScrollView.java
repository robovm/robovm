/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.apple.uikit;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIScrollView/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class UIScrollViewPtr extends Ptr<UIScrollView, UIScrollViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIScrollView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIScrollView() {}
    protected UIScrollView(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public UIScrollView(CGRect frame) {
        super(frame);
    }
    
    /*<properties>*/
    @Property(selector = "contentOffset")
    public native @ByVal CGPoint getContentOffset();
    @Property(selector = "setContentOffset:")
    public native void setContentOffset(@ByVal CGPoint v);
    @Property(selector = "contentSize")
    public native @ByVal CGSize getContentSize();
    @Property(selector = "setContentSize:")
    public native void setContentSize(@ByVal CGSize v);
    @Property(selector = "contentInset")
    public native @ByVal UIEdgeInsets getContentInset();
    @Property(selector = "setContentInset:")
    public native void setContentInset(@ByVal UIEdgeInsets v);
    @Property(selector = "delegate")
    public native UIScrollViewDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UIScrollViewDelegate v);
    @Property(selector = "isDirectionalLockEnabled")
    public native boolean isDirectionalLockEnabled();
    @Property(selector = "setDirectionalLockEnabled:")
    public native void setDirectionalLockEnabled(boolean v);
    @Property(selector = "bounces")
    public native boolean bounces();
    @Property(selector = "setBounces:")
    public native void setBounces(boolean v);
    @Property(selector = "alwaysBounceVertical")
    public native boolean alwaysBounceVertical();
    @Property(selector = "setAlwaysBounceVertical:")
    public native void setAlwaysBounceVertical(boolean v);
    @Property(selector = "alwaysBounceHorizontal")
    public native boolean alwaysBounceHorizontal();
    @Property(selector = "setAlwaysBounceHorizontal:")
    public native void setAlwaysBounceHorizontal(boolean v);
    @Property(selector = "isPagingEnabled")
    public native boolean isPagingEnabled();
    @Property(selector = "setPagingEnabled:")
    public native void setPagingEnabled(boolean v);
    @Property(selector = "isScrollEnabled")
    public native boolean isScrollEnabled();
    @Property(selector = "setScrollEnabled:")
    public native void setScrollEnabled(boolean v);
    @Property(selector = "showsHorizontalScrollIndicator")
    public native boolean showsHorizontalScrollIndicator();
    @Property(selector = "setShowsHorizontalScrollIndicator:")
    public native void setShowsHorizontalScrollIndicator(boolean v);
    @Property(selector = "showsVerticalScrollIndicator")
    public native boolean showsVerticalScrollIndicator();
    @Property(selector = "setShowsVerticalScrollIndicator:")
    public native void setShowsVerticalScrollIndicator(boolean v);
    @Property(selector = "scrollIndicatorInsets")
    public native @ByVal UIEdgeInsets getScrollIndicatorInsets();
    @Property(selector = "setScrollIndicatorInsets:")
    public native void setScrollIndicatorInsets(@ByVal UIEdgeInsets v);
    @Property(selector = "indicatorStyle")
    public native UIScrollViewIndicatorStyle getIndicatorStyle();
    @Property(selector = "setIndicatorStyle:")
    public native void setIndicatorStyle(UIScrollViewIndicatorStyle v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "decelerationRate")
    public native @MachineSizedFloat double getDecelerationRate();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setDecelerationRate:")
    public native void setDecelerationRate(@MachineSizedFloat double v);
    @Property(selector = "isTracking")
    public native boolean isTracking();
    @Property(selector = "isDragging")
    public native boolean isDragging();
    @Property(selector = "isDecelerating")
    public native boolean isDecelerating();
    @Property(selector = "delaysContentTouches")
    public native boolean delaysContentTouches();
    @Property(selector = "setDelaysContentTouches:")
    public native void setDelaysContentTouches(boolean v);
    @Property(selector = "canCancelContentTouches")
    public native boolean canCancelContentTouches();
    @Property(selector = "setCanCancelContentTouches:")
    public native void setCanCancelContentTouches(boolean v);
    @Property(selector = "minimumZoomScale")
    public native @MachineSizedFloat double getMinimumZoomScale();
    @Property(selector = "setMinimumZoomScale:")
    public native void setMinimumZoomScale(@MachineSizedFloat double v);
    @Property(selector = "maximumZoomScale")
    public native @MachineSizedFloat double getMaximumZoomScale();
    @Property(selector = "setMaximumZoomScale:")
    public native void setMaximumZoomScale(@MachineSizedFloat double v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "zoomScale")
    public native @MachineSizedFloat double getZoomScale();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setZoomScale:")
    public native void setZoomScale(@MachineSizedFloat double v);
    @Property(selector = "bouncesZoom")
    public native boolean bouncesZoom();
    @Property(selector = "setBouncesZoom:")
    public native void setBouncesZoom(boolean v);
    @Property(selector = "isZooming")
    public native boolean isZooming();
    @Property(selector = "isZoomBouncing")
    public native boolean isZoomBouncing();
    @Property(selector = "scrollsToTop")
    public native boolean scrollsToTop();
    @Property(selector = "setScrollsToTop:")
    public native void setScrollsToTop(boolean v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "panGestureRecognizer")
    public native UIPanGestureRecognizer getPanGestureRecognizer();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "pinchGestureRecognizer")
    public native UIPinchGestureRecognizer getPinchGestureRecognizer();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "keyboardDismissMode")
    public native UIScrollViewKeyboardDismissMode getKeyboardDismissMode();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setKeyboardDismissMode:")
    public native void setKeyboardDismissMode(UIScrollViewKeyboardDismissMode v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UIScrollViewDecelerationRateNormal", optional=true)
    public static native @MachineSizedFloat double getNormalDecelerationRate();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @GlobalValue(symbol="UIScrollViewDecelerationRateFast", optional=true)
    public static native @MachineSizedFloat double getFastDecelerationRate();
    
    @Method(selector = "setContentOffset:animated:")
    public native void setContentOffset(@ByVal CGPoint contentOffset, boolean animated);
    @Method(selector = "scrollRectToVisible:animated:")
    public native void scrollRectToVisible(@ByVal CGRect rect, boolean animated);
    @Method(selector = "flashScrollIndicators")
    public native void flashScrollIndicators();
    @Method(selector = "touchesShouldBegin:withEvent:inContentView:")
    public native boolean touchesShouldBegin(NSSet<UITouch> touches, UIEvent event, UIView view);
    @Method(selector = "touchesShouldCancelInContentView:")
    public native boolean touchesShouldCancelInContentView(UIView view);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "setZoomScale:animated:")
    public native void setZoomScale(@MachineSizedFloat double scale, boolean animated);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Method(selector = "zoomToRect:animated:")
    public native void zoomToRect(@ByVal CGRect rect, boolean animated);
    /*</methods>*/
}
