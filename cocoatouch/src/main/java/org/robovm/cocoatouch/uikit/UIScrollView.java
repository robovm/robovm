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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html">UIScrollView Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIScrollView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIScrollView /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIScrollView /*</name>*/.class);

    public UIScrollView(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UIScrollView(SkipInit skipInit) { super(skipInit); }
    public UIScrollView() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector alwaysBounceHorizontal = Selector.register("alwaysBounceHorizontal");
    @Bridge private native static boolean objc_isAlwaysBounceHorizontal(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isAlwaysBounceHorizontalSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/alwaysBounceHorizontal">@property(nonatomic) BOOL alwaysBounceHorizontal</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isAlwaysBounceHorizontal() {
        if (customClass) { return objc_isAlwaysBounceHorizontalSuper(getSuper(), alwaysBounceHorizontal); } else { return objc_isAlwaysBounceHorizontal(this, alwaysBounceHorizontal); }
    }
    
    private static final Selector setAlwaysBounceHorizontal$ = Selector.register("setAlwaysBounceHorizontal:");
    @Bridge private native static void objc_setAlwaysBounceHorizontal(UIScrollView __self__, Selector __cmd__, boolean alwaysBounceHorizontal);
    @Bridge private native static void objc_setAlwaysBounceHorizontalSuper(ObjCSuper __super__, Selector __cmd__, boolean alwaysBounceHorizontal);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/alwaysBounceHorizontal">@property(nonatomic) BOOL alwaysBounceHorizontal</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAlwaysBounceHorizontal(boolean alwaysBounceHorizontal) {
        if (customClass) { objc_setAlwaysBounceHorizontalSuper(getSuper(), setAlwaysBounceHorizontal$, alwaysBounceHorizontal); } else { objc_setAlwaysBounceHorizontal(this, setAlwaysBounceHorizontal$, alwaysBounceHorizontal); }
    }
    
    private static final Selector alwaysBounceVertical = Selector.register("alwaysBounceVertical");
    @Bridge private native static boolean objc_isAlwaysBounceVertical(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isAlwaysBounceVerticalSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/alwaysBounceVertical">@property(nonatomic) BOOL alwaysBounceVertical</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isAlwaysBounceVertical() {
        if (customClass) { return objc_isAlwaysBounceVerticalSuper(getSuper(), alwaysBounceVertical); } else { return objc_isAlwaysBounceVertical(this, alwaysBounceVertical); }
    }
    
    private static final Selector setAlwaysBounceVertical$ = Selector.register("setAlwaysBounceVertical:");
    @Bridge private native static void objc_setAlwaysBounceVertical(UIScrollView __self__, Selector __cmd__, boolean alwaysBounceVertical);
    @Bridge private native static void objc_setAlwaysBounceVerticalSuper(ObjCSuper __super__, Selector __cmd__, boolean alwaysBounceVertical);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/alwaysBounceVertical">@property(nonatomic) BOOL alwaysBounceVertical</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAlwaysBounceVertical(boolean alwaysBounceVertical) {
        if (customClass) { objc_setAlwaysBounceVerticalSuper(getSuper(), setAlwaysBounceVertical$, alwaysBounceVertical); } else { objc_setAlwaysBounceVertical(this, setAlwaysBounceVertical$, alwaysBounceVertical); }
    }
    
    private static final Selector bounces = Selector.register("bounces");
    @Bridge private native static boolean objc_isBounces(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isBouncesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/bounces">@property(nonatomic) BOOL bounces</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isBounces() {
        if (customClass) { return objc_isBouncesSuper(getSuper(), bounces); } else { return objc_isBounces(this, bounces); }
    }
    
    private static final Selector setBounces$ = Selector.register("setBounces:");
    @Bridge private native static void objc_setBounces(UIScrollView __self__, Selector __cmd__, boolean bounces);
    @Bridge private native static void objc_setBouncesSuper(ObjCSuper __super__, Selector __cmd__, boolean bounces);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/bounces">@property(nonatomic) BOOL bounces</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setBounces(boolean bounces) {
        if (customClass) { objc_setBouncesSuper(getSuper(), setBounces$, bounces); } else { objc_setBounces(this, setBounces$, bounces); }
    }
    
    private static final Selector bouncesZoom = Selector.register("bouncesZoom");
    @Bridge private native static boolean objc_isBouncesZoom(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isBouncesZoomSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/bouncesZoom">@property(nonatomic) BOOL bouncesZoom</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isBouncesZoom() {
        if (customClass) { return objc_isBouncesZoomSuper(getSuper(), bouncesZoom); } else { return objc_isBouncesZoom(this, bouncesZoom); }
    }
    
    private static final Selector setBouncesZoom$ = Selector.register("setBouncesZoom:");
    @Bridge private native static void objc_setBouncesZoom(UIScrollView __self__, Selector __cmd__, boolean bouncesZoom);
    @Bridge private native static void objc_setBouncesZoomSuper(ObjCSuper __super__, Selector __cmd__, boolean bouncesZoom);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/bouncesZoom">@property(nonatomic) BOOL bouncesZoom</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setBouncesZoom(boolean bouncesZoom) {
        if (customClass) { objc_setBouncesZoomSuper(getSuper(), setBouncesZoom$, bouncesZoom); } else { objc_setBouncesZoom(this, setBouncesZoom$, bouncesZoom); }
    }
    
    private static final Selector canCancelContentTouches = Selector.register("canCancelContentTouches");
    @Bridge private native static boolean objc_isCanCancelContentTouches(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isCanCancelContentTouchesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/canCancelContentTouches">@property(nonatomic) BOOL canCancelContentTouches</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isCanCancelContentTouches() {
        if (customClass) { return objc_isCanCancelContentTouchesSuper(getSuper(), canCancelContentTouches); } else { return objc_isCanCancelContentTouches(this, canCancelContentTouches); }
    }
    
    private static final Selector setCanCancelContentTouches$ = Selector.register("setCanCancelContentTouches:");
    @Bridge private native static void objc_setCanCancelContentTouches(UIScrollView __self__, Selector __cmd__, boolean canCancelContentTouches);
    @Bridge private native static void objc_setCanCancelContentTouchesSuper(ObjCSuper __super__, Selector __cmd__, boolean canCancelContentTouches);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/canCancelContentTouches">@property(nonatomic) BOOL canCancelContentTouches</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setCanCancelContentTouches(boolean canCancelContentTouches) {
        if (customClass) { objc_setCanCancelContentTouchesSuper(getSuper(), setCanCancelContentTouches$, canCancelContentTouches); } else { objc_setCanCancelContentTouches(this, setCanCancelContentTouches$, canCancelContentTouches); }
    }
    
    private static final Selector contentInset = Selector.register("contentInset");
    @Bridge private native static @ByVal UIEdgeInsets objc_getContentInset(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static @ByVal UIEdgeInsets objc_getContentInsetSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/contentInset">@property(nonatomic) UIEdgeInsets contentInset</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIEdgeInsets getContentInset() {
        if (customClass) { return objc_getContentInsetSuper(getSuper(), contentInset); } else { return objc_getContentInset(this, contentInset); }
    }
    
    private static final Selector setContentInset$ = Selector.register("setContentInset:");
    @Bridge private native static void objc_setContentInset(UIScrollView __self__, Selector __cmd__, @ByVal UIEdgeInsets contentInset);
    @Bridge private native static void objc_setContentInsetSuper(ObjCSuper __super__, Selector __cmd__, @ByVal UIEdgeInsets contentInset);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/contentInset">@property(nonatomic) UIEdgeInsets contentInset</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setContentInset(UIEdgeInsets contentInset) {
        if (customClass) { objc_setContentInsetSuper(getSuper(), setContentInset$, contentInset); } else { objc_setContentInset(this, setContentInset$, contentInset); }
    }
    
    private static final Selector contentOffset = Selector.register("contentOffset");
    @Bridge private native static @ByVal CGPoint objc_getContentOffset(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGPoint objc_getContentOffsetSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/contentOffset">@property(nonatomic) CGPoint contentOffset</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGPoint getContentOffset() {
        if (customClass) { return objc_getContentOffsetSuper(getSuper(), contentOffset); } else { return objc_getContentOffset(this, contentOffset); }
    }
    
    private static final Selector setContentOffset$ = Selector.register("setContentOffset:");
    @Bridge private native static void objc_setContentOffset(UIScrollView __self__, Selector __cmd__, @ByVal CGPoint contentOffset);
    @Bridge private native static void objc_setContentOffsetSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint contentOffset);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/contentOffset">@property(nonatomic) CGPoint contentOffset</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setContentOffset(CGPoint contentOffset) {
        if (customClass) { objc_setContentOffsetSuper(getSuper(), setContentOffset$, contentOffset); } else { objc_setContentOffset(this, setContentOffset$, contentOffset); }
    }
    
    private static final Selector contentSize = Selector.register("contentSize");
    @Bridge private native static @ByVal CGSize objc_getContentSize(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGSize objc_getContentSizeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/contentSize">@property(nonatomic) CGSize contentSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGSize getContentSize() {
        if (customClass) { return objc_getContentSizeSuper(getSuper(), contentSize); } else { return objc_getContentSize(this, contentSize); }
    }
    
    private static final Selector setContentSize$ = Selector.register("setContentSize:");
    @Bridge private native static void objc_setContentSize(UIScrollView __self__, Selector __cmd__, @ByVal CGSize contentSize);
    @Bridge private native static void objc_setContentSizeSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGSize contentSize);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/contentSize">@property(nonatomic) CGSize contentSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setContentSize(CGSize contentSize) {
        if (customClass) { objc_setContentSizeSuper(getSuper(), setContentSize$, contentSize); } else { objc_setContentSize(this, setContentSize$, contentSize); }
    }
    
    private static final Selector isDecelerating = Selector.register("isDecelerating");
    @Bridge private native static boolean objc_isDecelerating(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isDeceleratingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/decelerating">@property(nonatomic, readonly, getter=isDecelerating) BOOL decelerating</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isDecelerating() {
        if (customClass) { return objc_isDeceleratingSuper(getSuper(), isDecelerating); } else { return objc_isDecelerating(this, isDecelerating); }
    }
    
    private static final Selector decelerationRate = Selector.register("decelerationRate");
    @Bridge private native static float objc_getDecelerationRate(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static float objc_getDecelerationRateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/decelerationRate">@property(nonatomic) float decelerationRate</a>
     * @since Available in iOS 3.0 and later.
     */
    public float getDecelerationRate() {
        if (customClass) { return objc_getDecelerationRateSuper(getSuper(), decelerationRate); } else { return objc_getDecelerationRate(this, decelerationRate); }
    }
    
    private static final Selector setDecelerationRate$ = Selector.register("setDecelerationRate:");
    @Bridge private native static void objc_setDecelerationRate(UIScrollView __self__, Selector __cmd__, float decelerationRate);
    @Bridge private native static void objc_setDecelerationRateSuper(ObjCSuper __super__, Selector __cmd__, float decelerationRate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/decelerationRate">@property(nonatomic) float decelerationRate</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setDecelerationRate(float decelerationRate) {
        if (customClass) { objc_setDecelerationRateSuper(getSuper(), setDecelerationRate$, decelerationRate); } else { objc_setDecelerationRate(this, setDecelerationRate$, decelerationRate); }
    }
    
    private static final Selector delaysContentTouches = Selector.register("delaysContentTouches");
    @Bridge private native static boolean objc_isDelaysContentTouches(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isDelaysContentTouchesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/delaysContentTouches">@property(nonatomic) BOOL delaysContentTouches</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isDelaysContentTouches() {
        if (customClass) { return objc_isDelaysContentTouchesSuper(getSuper(), delaysContentTouches); } else { return objc_isDelaysContentTouches(this, delaysContentTouches); }
    }
    
    private static final Selector setDelaysContentTouches$ = Selector.register("setDelaysContentTouches:");
    @Bridge private native static void objc_setDelaysContentTouches(UIScrollView __self__, Selector __cmd__, boolean delaysContentTouches);
    @Bridge private native static void objc_setDelaysContentTouchesSuper(ObjCSuper __super__, Selector __cmd__, boolean delaysContentTouches);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/delaysContentTouches">@property(nonatomic) BOOL delaysContentTouches</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDelaysContentTouches(boolean delaysContentTouches) {
        if (customClass) { objc_setDelaysContentTouchesSuper(getSuper(), setDelaysContentTouches$, delaysContentTouches); } else { objc_setDelaysContentTouches(this, setDelaysContentTouches$, delaysContentTouches); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UIScrollViewDelegate objc_getDelegate(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static UIScrollViewDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/delegate">@property(nonatomic, assign) id&amp;lt;UIScrollViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIScrollViewDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UIScrollView __self__, Selector __cmd__, UIScrollViewDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UIScrollViewDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/delegate">@property(nonatomic, assign) id&amp;lt;UIScrollViewDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDelegate(UIScrollViewDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector isDirectionalLockEnabled = Selector.register("isDirectionalLockEnabled");
    @Bridge private native static boolean objc_isDirectionalLockEnabled(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isDirectionalLockEnabledSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/directionalLockEnabled">@property(nonatomic, getter=isDirectionalLockEnabled) BOOL directionalLockEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isDirectionalLockEnabled() {
        if (customClass) { return objc_isDirectionalLockEnabledSuper(getSuper(), isDirectionalLockEnabled); } else { return objc_isDirectionalLockEnabled(this, isDirectionalLockEnabled); }
    }
    
    private static final Selector setDirectionalLockEnabled$ = Selector.register("setDirectionalLockEnabled:");
    @Bridge private native static void objc_setDirectionalLockEnabled(UIScrollView __self__, Selector __cmd__, boolean directionalLockEnabled);
    @Bridge private native static void objc_setDirectionalLockEnabledSuper(ObjCSuper __super__, Selector __cmd__, boolean directionalLockEnabled);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/directionalLockEnabled">@property(nonatomic, getter=isDirectionalLockEnabled) BOOL directionalLockEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDirectionalLockEnabled(boolean directionalLockEnabled) {
        if (customClass) { objc_setDirectionalLockEnabledSuper(getSuper(), setDirectionalLockEnabled$, directionalLockEnabled); } else { objc_setDirectionalLockEnabled(this, setDirectionalLockEnabled$, directionalLockEnabled); }
    }
    
    private static final Selector isDragging = Selector.register("isDragging");
    @Bridge private native static boolean objc_isDragging(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isDraggingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/dragging">@property(nonatomic, readonly, getter=isDragging) BOOL dragging</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isDragging() {
        if (customClass) { return objc_isDraggingSuper(getSuper(), isDragging); } else { return objc_isDragging(this, isDragging); }
    }
    
    private static final Selector indicatorStyle = Selector.register("indicatorStyle");
    @Bridge private native static UIScrollViewIndicatorStyle objc_getIndicatorStyle(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static UIScrollViewIndicatorStyle objc_getIndicatorStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/indicatorStyle">@property(nonatomic) UIScrollViewIndicatorStyle indicatorStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIScrollViewIndicatorStyle getIndicatorStyle() {
        if (customClass) { return objc_getIndicatorStyleSuper(getSuper(), indicatorStyle); } else { return objc_getIndicatorStyle(this, indicatorStyle); }
    }
    
    private static final Selector setIndicatorStyle$ = Selector.register("setIndicatorStyle:");
    @Bridge private native static void objc_setIndicatorStyle(UIScrollView __self__, Selector __cmd__, UIScrollViewIndicatorStyle indicatorStyle);
    @Bridge private native static void objc_setIndicatorStyleSuper(ObjCSuper __super__, Selector __cmd__, UIScrollViewIndicatorStyle indicatorStyle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/indicatorStyle">@property(nonatomic) UIScrollViewIndicatorStyle indicatorStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setIndicatorStyle(UIScrollViewIndicatorStyle indicatorStyle) {
        if (customClass) { objc_setIndicatorStyleSuper(getSuper(), setIndicatorStyle$, indicatorStyle); } else { objc_setIndicatorStyle(this, setIndicatorStyle$, indicatorStyle); }
    }
    
    private static final Selector maximumZoomScale = Selector.register("maximumZoomScale");
    @Bridge private native static float objc_getMaximumZoomScale(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static float objc_getMaximumZoomScaleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/maximumZoomScale">@property(nonatomic) float maximumZoomScale</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getMaximumZoomScale() {
        if (customClass) { return objc_getMaximumZoomScaleSuper(getSuper(), maximumZoomScale); } else { return objc_getMaximumZoomScale(this, maximumZoomScale); }
    }
    
    private static final Selector setMaximumZoomScale$ = Selector.register("setMaximumZoomScale:");
    @Bridge private native static void objc_setMaximumZoomScale(UIScrollView __self__, Selector __cmd__, float maximumZoomScale);
    @Bridge private native static void objc_setMaximumZoomScaleSuper(ObjCSuper __super__, Selector __cmd__, float maximumZoomScale);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/maximumZoomScale">@property(nonatomic) float maximumZoomScale</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMaximumZoomScale(float maximumZoomScale) {
        if (customClass) { objc_setMaximumZoomScaleSuper(getSuper(), setMaximumZoomScale$, maximumZoomScale); } else { objc_setMaximumZoomScale(this, setMaximumZoomScale$, maximumZoomScale); }
    }
    
    private static final Selector minimumZoomScale = Selector.register("minimumZoomScale");
    @Bridge private native static float objc_getMinimumZoomScale(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static float objc_getMinimumZoomScaleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/minimumZoomScale">@property(nonatomic) float minimumZoomScale</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getMinimumZoomScale() {
        if (customClass) { return objc_getMinimumZoomScaleSuper(getSuper(), minimumZoomScale); } else { return objc_getMinimumZoomScale(this, minimumZoomScale); }
    }
    
    private static final Selector setMinimumZoomScale$ = Selector.register("setMinimumZoomScale:");
    @Bridge private native static void objc_setMinimumZoomScale(UIScrollView __self__, Selector __cmd__, float minimumZoomScale);
    @Bridge private native static void objc_setMinimumZoomScaleSuper(ObjCSuper __super__, Selector __cmd__, float minimumZoomScale);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/minimumZoomScale">@property(nonatomic) float minimumZoomScale</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMinimumZoomScale(float minimumZoomScale) {
        if (customClass) { objc_setMinimumZoomScaleSuper(getSuper(), setMinimumZoomScale$, minimumZoomScale); } else { objc_setMinimumZoomScale(this, setMinimumZoomScale$, minimumZoomScale); }
    }
    
    private static final Selector isPagingEnabled = Selector.register("isPagingEnabled");
    @Bridge private native static boolean objc_isPagingEnabled(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isPagingEnabledSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/pagingEnabled">@property(nonatomic, getter=isPagingEnabled) BOOL pagingEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isPagingEnabled() {
        if (customClass) { return objc_isPagingEnabledSuper(getSuper(), isPagingEnabled); } else { return objc_isPagingEnabled(this, isPagingEnabled); }
    }
    
    private static final Selector setPagingEnabled$ = Selector.register("setPagingEnabled:");
    @Bridge private native static void objc_setPagingEnabled(UIScrollView __self__, Selector __cmd__, boolean pagingEnabled);
    @Bridge private native static void objc_setPagingEnabledSuper(ObjCSuper __super__, Selector __cmd__, boolean pagingEnabled);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/pagingEnabled">@property(nonatomic, getter=isPagingEnabled) BOOL pagingEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setPagingEnabled(boolean pagingEnabled) {
        if (customClass) { objc_setPagingEnabledSuper(getSuper(), setPagingEnabled$, pagingEnabled); } else { objc_setPagingEnabled(this, setPagingEnabled$, pagingEnabled); }
    }
    
    private static final Selector panGestureRecognizer = Selector.register("panGestureRecognizer");
    @Bridge private native static UIPanGestureRecognizer objc_getPanGestureRecognizer(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static UIPanGestureRecognizer objc_getPanGestureRecognizerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/panGestureRecognizer">@property(nonatomic, readonly) UIPanGestureRecognizer *panGestureRecognizer</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIPanGestureRecognizer getPanGestureRecognizer() {
        if (customClass) { return objc_getPanGestureRecognizerSuper(getSuper(), panGestureRecognizer); } else { return objc_getPanGestureRecognizer(this, panGestureRecognizer); }
    }
    
    private static final Selector pinchGestureRecognizer = Selector.register("pinchGestureRecognizer");
    @Bridge private native static UIPinchGestureRecognizer objc_getPinchGestureRecognizer(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static UIPinchGestureRecognizer objc_getPinchGestureRecognizerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/pinchGestureRecognizer">@property(nonatomic, readonly) UIPinchGestureRecognizer *pinchGestureRecognizer</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIPinchGestureRecognizer getPinchGestureRecognizer() {
        if (customClass) { return objc_getPinchGestureRecognizerSuper(getSuper(), pinchGestureRecognizer); } else { return objc_getPinchGestureRecognizer(this, pinchGestureRecognizer); }
    }
    
    private static final Selector isScrollEnabled = Selector.register("isScrollEnabled");
    @Bridge private native static boolean objc_isScrollEnabled(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isScrollEnabledSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/scrollEnabled">@property(nonatomic, getter=isScrollEnabled) BOOL scrollEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isScrollEnabled() {
        if (customClass) { return objc_isScrollEnabledSuper(getSuper(), isScrollEnabled); } else { return objc_isScrollEnabled(this, isScrollEnabled); }
    }
    
    private static final Selector setScrollEnabled$ = Selector.register("setScrollEnabled:");
    @Bridge private native static void objc_setScrollEnabled(UIScrollView __self__, Selector __cmd__, boolean scrollEnabled);
    @Bridge private native static void objc_setScrollEnabledSuper(ObjCSuper __super__, Selector __cmd__, boolean scrollEnabled);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/scrollEnabled">@property(nonatomic, getter=isScrollEnabled) BOOL scrollEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setScrollEnabled(boolean scrollEnabled) {
        if (customClass) { objc_setScrollEnabledSuper(getSuper(), setScrollEnabled$, scrollEnabled); } else { objc_setScrollEnabled(this, setScrollEnabled$, scrollEnabled); }
    }
    
    private static final Selector scrollIndicatorInsets = Selector.register("scrollIndicatorInsets");
    @Bridge private native static @ByVal UIEdgeInsets objc_getScrollIndicatorInsets(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static @ByVal UIEdgeInsets objc_getScrollIndicatorInsetsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/scrollIndicatorInsets">@property(nonatomic) UIEdgeInsets scrollIndicatorInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIEdgeInsets getScrollIndicatorInsets() {
        if (customClass) { return objc_getScrollIndicatorInsetsSuper(getSuper(), scrollIndicatorInsets); } else { return objc_getScrollIndicatorInsets(this, scrollIndicatorInsets); }
    }
    
    private static final Selector setScrollIndicatorInsets$ = Selector.register("setScrollIndicatorInsets:");
    @Bridge private native static void objc_setScrollIndicatorInsets(UIScrollView __self__, Selector __cmd__, @ByVal UIEdgeInsets scrollIndicatorInsets);
    @Bridge private native static void objc_setScrollIndicatorInsetsSuper(ObjCSuper __super__, Selector __cmd__, @ByVal UIEdgeInsets scrollIndicatorInsets);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/scrollIndicatorInsets">@property(nonatomic) UIEdgeInsets scrollIndicatorInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setScrollIndicatorInsets(UIEdgeInsets scrollIndicatorInsets) {
        if (customClass) { objc_setScrollIndicatorInsetsSuper(getSuper(), setScrollIndicatorInsets$, scrollIndicatorInsets); } else { objc_setScrollIndicatorInsets(this, setScrollIndicatorInsets$, scrollIndicatorInsets); }
    }
    
    private static final Selector scrollsToTop = Selector.register("scrollsToTop");
    @Bridge private native static boolean objc_isScrollsToTop(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isScrollsToTopSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/scrollsToTop">@property(nonatomic) BOOL scrollsToTop</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isScrollsToTop() {
        if (customClass) { return objc_isScrollsToTopSuper(getSuper(), scrollsToTop); } else { return objc_isScrollsToTop(this, scrollsToTop); }
    }
    
    private static final Selector setScrollsToTop$ = Selector.register("setScrollsToTop:");
    @Bridge private native static void objc_setScrollsToTop(UIScrollView __self__, Selector __cmd__, boolean scrollsToTop);
    @Bridge private native static void objc_setScrollsToTopSuper(ObjCSuper __super__, Selector __cmd__, boolean scrollsToTop);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/scrollsToTop">@property(nonatomic) BOOL scrollsToTop</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setScrollsToTop(boolean scrollsToTop) {
        if (customClass) { objc_setScrollsToTopSuper(getSuper(), setScrollsToTop$, scrollsToTop); } else { objc_setScrollsToTop(this, setScrollsToTop$, scrollsToTop); }
    }
    
    private static final Selector showsHorizontalScrollIndicator = Selector.register("showsHorizontalScrollIndicator");
    @Bridge private native static boolean objc_isShowsHorizontalScrollIndicator(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isShowsHorizontalScrollIndicatorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/showsHorizontalScrollIndicator">@property(nonatomic) BOOL showsHorizontalScrollIndicator</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isShowsHorizontalScrollIndicator() {
        if (customClass) { return objc_isShowsHorizontalScrollIndicatorSuper(getSuper(), showsHorizontalScrollIndicator); } else { return objc_isShowsHorizontalScrollIndicator(this, showsHorizontalScrollIndicator); }
    }
    
    private static final Selector setShowsHorizontalScrollIndicator$ = Selector.register("setShowsHorizontalScrollIndicator:");
    @Bridge private native static void objc_setShowsHorizontalScrollIndicator(UIScrollView __self__, Selector __cmd__, boolean showsHorizontalScrollIndicator);
    @Bridge private native static void objc_setShowsHorizontalScrollIndicatorSuper(ObjCSuper __super__, Selector __cmd__, boolean showsHorizontalScrollIndicator);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/showsHorizontalScrollIndicator">@property(nonatomic) BOOL showsHorizontalScrollIndicator</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setShowsHorizontalScrollIndicator(boolean showsHorizontalScrollIndicator) {
        if (customClass) { objc_setShowsHorizontalScrollIndicatorSuper(getSuper(), setShowsHorizontalScrollIndicator$, showsHorizontalScrollIndicator); } else { objc_setShowsHorizontalScrollIndicator(this, setShowsHorizontalScrollIndicator$, showsHorizontalScrollIndicator); }
    }
    
    private static final Selector showsVerticalScrollIndicator = Selector.register("showsVerticalScrollIndicator");
    @Bridge private native static boolean objc_isShowsVerticalScrollIndicator(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isShowsVerticalScrollIndicatorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/showsVerticalScrollIndicator">@property(nonatomic) BOOL showsVerticalScrollIndicator</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isShowsVerticalScrollIndicator() {
        if (customClass) { return objc_isShowsVerticalScrollIndicatorSuper(getSuper(), showsVerticalScrollIndicator); } else { return objc_isShowsVerticalScrollIndicator(this, showsVerticalScrollIndicator); }
    }
    
    private static final Selector setShowsVerticalScrollIndicator$ = Selector.register("setShowsVerticalScrollIndicator:");
    @Bridge private native static void objc_setShowsVerticalScrollIndicator(UIScrollView __self__, Selector __cmd__, boolean showsVerticalScrollIndicator);
    @Bridge private native static void objc_setShowsVerticalScrollIndicatorSuper(ObjCSuper __super__, Selector __cmd__, boolean showsVerticalScrollIndicator);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/showsVerticalScrollIndicator">@property(nonatomic) BOOL showsVerticalScrollIndicator</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setShowsVerticalScrollIndicator(boolean showsVerticalScrollIndicator) {
        if (customClass) { objc_setShowsVerticalScrollIndicatorSuper(getSuper(), setShowsVerticalScrollIndicator$, showsVerticalScrollIndicator); } else { objc_setShowsVerticalScrollIndicator(this, setShowsVerticalScrollIndicator$, showsVerticalScrollIndicator); }
    }
    
    private static final Selector isTracking = Selector.register("isTracking");
    @Bridge private native static boolean objc_isTracking(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isTrackingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/tracking">@property(nonatomic, readonly, getter=isTracking) BOOL tracking</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isTracking() {
        if (customClass) { return objc_isTrackingSuper(getSuper(), isTracking); } else { return objc_isTracking(this, isTracking); }
    }
    
    private static final Selector isZoomBouncing = Selector.register("isZoomBouncing");
    @Bridge private native static boolean objc_isZoomBouncing(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isZoomBouncingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/zoomBouncing">@property(nonatomic, readonly, getter=isZoomBouncing) BOOL zoomBouncing</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isZoomBouncing() {
        if (customClass) { return objc_isZoomBouncingSuper(getSuper(), isZoomBouncing); } else { return objc_isZoomBouncing(this, isZoomBouncing); }
    }
    
    private static final Selector zoomScale = Selector.register("zoomScale");
    @Bridge private native static float objc_getZoomScale(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static float objc_getZoomScaleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/zoomScale">@property(nonatomic) float zoomScale</a>
     * @since Available in iOS 3.0 and later.
     */
    public float getZoomScale() {
        if (customClass) { return objc_getZoomScaleSuper(getSuper(), zoomScale); } else { return objc_getZoomScale(this, zoomScale); }
    }
    
    private static final Selector setZoomScale$ = Selector.register("setZoomScale:");
    @Bridge private native static void objc_setZoomScale(UIScrollView __self__, Selector __cmd__, float zoomScale);
    @Bridge private native static void objc_setZoomScaleSuper(ObjCSuper __super__, Selector __cmd__, float zoomScale);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/zoomScale">@property(nonatomic) float zoomScale</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setZoomScale(float zoomScale) {
        if (customClass) { objc_setZoomScaleSuper(getSuper(), setZoomScale$, zoomScale); } else { objc_setZoomScale(this, setZoomScale$, zoomScale); }
    }
    
    private static final Selector isZooming = Selector.register("isZooming");
    @Bridge private native static boolean objc_isZooming(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isZoomingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instp/UIScrollView/zooming">@property(nonatomic, readonly, getter=isZooming) BOOL zooming</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isZooming() {
        if (customClass) { return objc_isZoomingSuper(getSuper(), isZooming); } else { return objc_isZooming(this, isZooming); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector flashScrollIndicators = Selector.register("flashScrollIndicators");
    @Bridge private native static void objc_flashScrollIndicators(UIScrollView __self__, Selector __cmd__);
    @Bridge private native static void objc_flashScrollIndicatorsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/flashScrollIndicators">- (void)flashScrollIndicators</a>
     * @since Available in iOS 2.0 and later.
     */
    public void flashScrollIndicators() {
        if (customClass) { objc_flashScrollIndicatorsSuper(getSuper(), flashScrollIndicators); } else { objc_flashScrollIndicators(this, flashScrollIndicators); }
    }
    
    private static final Selector scrollRectToVisible$animated$ = Selector.register("scrollRectToVisible:animated:");
    @Bridge private native static void objc_scrollRectToVisible(UIScrollView __self__, Selector __cmd__, @ByVal CGRect rect, boolean animated);
    @Bridge private native static void objc_scrollRectToVisibleSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect rect, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/scrollRectToVisible:animated:">- (void)scrollRectToVisible:(CGRect)rect animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void scrollRectToVisible(CGRect rect, boolean animated) {
        if (customClass) { objc_scrollRectToVisibleSuper(getSuper(), scrollRectToVisible$animated$, rect, animated); } else { objc_scrollRectToVisible(this, scrollRectToVisible$animated$, rect, animated); }
    }
    
    private static final Selector setContentOffset$animated$ = Selector.register("setContentOffset:animated:");
    @Bridge private native static void objc_setContentOffset(UIScrollView __self__, Selector __cmd__, @ByVal CGPoint contentOffset, boolean animated);
    @Bridge private native static void objc_setContentOffsetSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint contentOffset, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/setContentOffset:animated:">- (void)setContentOffset:(CGPoint)contentOffset animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setContentOffset(CGPoint contentOffset, boolean animated) {
        if (customClass) { objc_setContentOffsetSuper(getSuper(), setContentOffset$animated$, contentOffset, animated); } else { objc_setContentOffset(this, setContentOffset$animated$, contentOffset, animated); }
    }
    
    private static final Selector setZoomScale$animated$ = Selector.register("setZoomScale:animated:");
    @Bridge private native static void objc_setZoomScale(UIScrollView __self__, Selector __cmd__, float scale, boolean animated);
    @Bridge private native static void objc_setZoomScaleSuper(ObjCSuper __super__, Selector __cmd__, float scale, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/setZoomScale:animated:">- (void)setZoomScale:(float)scale animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setZoomScale(float scale, boolean animated) {
        if (customClass) { objc_setZoomScaleSuper(getSuper(), setZoomScale$animated$, scale, animated); } else { objc_setZoomScale(this, setZoomScale$animated$, scale, animated); }
    }
    
    private static final Selector touchesShouldBegin$withEvent$inContentView$ = Selector.register("touchesShouldBegin:withEvent:inContentView:");
    @Bridge private native static boolean objc_touchesShouldBegin(UIScrollView __self__, Selector __cmd__, NSSet touches, UIEvent event, UIView view);
    @Bridge private native static boolean objc_touchesShouldBeginSuper(ObjCSuper __super__, Selector __cmd__, NSSet touches, UIEvent event, UIView view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/touchesShouldBegin:withEvent:inContentView:">- (BOOL)touchesShouldBegin:(NSSet *)touches withEvent:(UIEvent *)event inContentView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean touchesShouldBegin(NSSet touches, UIEvent event, UIView view) {
        if (customClass) { return objc_touchesShouldBeginSuper(getSuper(), touchesShouldBegin$withEvent$inContentView$, touches, event, view); } else { return objc_touchesShouldBegin(this, touchesShouldBegin$withEvent$inContentView$, touches, event, view); }
    }
    
    private static final Selector touchesShouldCancelInContentView$ = Selector.register("touchesShouldCancelInContentView:");
    @Bridge private native static boolean objc_touchesShouldCancelInContentView(UIScrollView __self__, Selector __cmd__, UIView view);
    @Bridge private native static boolean objc_touchesShouldCancelInContentViewSuper(ObjCSuper __super__, Selector __cmd__, UIView view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/touchesShouldCancelInContentView:">- (BOOL)touchesShouldCancelInContentView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean touchesShouldCancelInContentView(UIView view) {
        if (customClass) { return objc_touchesShouldCancelInContentViewSuper(getSuper(), touchesShouldCancelInContentView$, view); } else { return objc_touchesShouldCancelInContentView(this, touchesShouldCancelInContentView$, view); }
    }
    
    private static final Selector zoomToRect$animated$ = Selector.register("zoomToRect:animated:");
    @Bridge private native static void objc_zoomToRect(UIScrollView __self__, Selector __cmd__, @ByVal CGRect rect, boolean animated);
    @Bridge private native static void objc_zoomToRectSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect rect, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIScrollView_Class/Reference/UIScrollView.html#//apple_ref/occ/instm/UIScrollView/zoomToRect:animated:">- (void)zoomToRect:(CGRect)rect animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    public void zoomToRect(CGRect rect, boolean animated) {
        if (customClass) { objc_zoomToRectSuper(getSuper(), zoomToRect$animated$, rect, animated); } else { objc_zoomToRect(this, zoomToRect$animated$, rect, animated); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("alwaysBounceHorizontal") public static boolean isAlwaysBounceHorizontal(UIScrollView __self__, Selector __cmd__) { return __self__.isAlwaysBounceHorizontal(); }
        @Callback @BindSelector("setAlwaysBounceHorizontal:") public static void setAlwaysBounceHorizontal(UIScrollView __self__, Selector __cmd__, boolean alwaysBounceHorizontal) { __self__.setAlwaysBounceHorizontal(alwaysBounceHorizontal); }
        @Callback @BindSelector("alwaysBounceVertical") public static boolean isAlwaysBounceVertical(UIScrollView __self__, Selector __cmd__) { return __self__.isAlwaysBounceVertical(); }
        @Callback @BindSelector("setAlwaysBounceVertical:") public static void setAlwaysBounceVertical(UIScrollView __self__, Selector __cmd__, boolean alwaysBounceVertical) { __self__.setAlwaysBounceVertical(alwaysBounceVertical); }
        @Callback @BindSelector("bounces") public static boolean isBounces(UIScrollView __self__, Selector __cmd__) { return __self__.isBounces(); }
        @Callback @BindSelector("setBounces:") public static void setBounces(UIScrollView __self__, Selector __cmd__, boolean bounces) { __self__.setBounces(bounces); }
        @Callback @BindSelector("bouncesZoom") public static boolean isBouncesZoom(UIScrollView __self__, Selector __cmd__) { return __self__.isBouncesZoom(); }
        @Callback @BindSelector("setBouncesZoom:") public static void setBouncesZoom(UIScrollView __self__, Selector __cmd__, boolean bouncesZoom) { __self__.setBouncesZoom(bouncesZoom); }
        @Callback @BindSelector("canCancelContentTouches") public static boolean isCanCancelContentTouches(UIScrollView __self__, Selector __cmd__) { return __self__.isCanCancelContentTouches(); }
        @Callback @BindSelector("setCanCancelContentTouches:") public static void setCanCancelContentTouches(UIScrollView __self__, Selector __cmd__, boolean canCancelContentTouches) { __self__.setCanCancelContentTouches(canCancelContentTouches); }
        @Callback @BindSelector("contentInset") public static @ByVal UIEdgeInsets getContentInset(UIScrollView __self__, Selector __cmd__) { return __self__.getContentInset(); }
        @Callback @BindSelector("setContentInset:") public static void setContentInset(UIScrollView __self__, Selector __cmd__, @ByVal UIEdgeInsets contentInset) { __self__.setContentInset(contentInset); }
        @Callback @BindSelector("contentOffset") public static @ByVal CGPoint getContentOffset(UIScrollView __self__, Selector __cmd__) { return __self__.getContentOffset(); }
        @Callback @BindSelector("setContentOffset:") public static void setContentOffset(UIScrollView __self__, Selector __cmd__, @ByVal CGPoint contentOffset) { __self__.setContentOffset(contentOffset); }
        @Callback @BindSelector("contentSize") public static @ByVal CGSize getContentSize(UIScrollView __self__, Selector __cmd__) { return __self__.getContentSize(); }
        @Callback @BindSelector("setContentSize:") public static void setContentSize(UIScrollView __self__, Selector __cmd__, @ByVal CGSize contentSize) { __self__.setContentSize(contentSize); }
        @Callback @BindSelector("isDecelerating") public static boolean isDecelerating(UIScrollView __self__, Selector __cmd__) { return __self__.isDecelerating(); }
        @Callback @BindSelector("decelerationRate") public static float getDecelerationRate(UIScrollView __self__, Selector __cmd__) { return __self__.getDecelerationRate(); }
        @Callback @BindSelector("setDecelerationRate:") public static void setDecelerationRate(UIScrollView __self__, Selector __cmd__, float decelerationRate) { __self__.setDecelerationRate(decelerationRate); }
        @Callback @BindSelector("delaysContentTouches") public static boolean isDelaysContentTouches(UIScrollView __self__, Selector __cmd__) { return __self__.isDelaysContentTouches(); }
        @Callback @BindSelector("setDelaysContentTouches:") public static void setDelaysContentTouches(UIScrollView __self__, Selector __cmd__, boolean delaysContentTouches) { __self__.setDelaysContentTouches(delaysContentTouches); }
        @Callback @BindSelector("delegate") public static UIScrollViewDelegate getDelegate(UIScrollView __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UIScrollView __self__, Selector __cmd__, UIScrollViewDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("isDirectionalLockEnabled") public static boolean isDirectionalLockEnabled(UIScrollView __self__, Selector __cmd__) { return __self__.isDirectionalLockEnabled(); }
        @Callback @BindSelector("setDirectionalLockEnabled:") public static void setDirectionalLockEnabled(UIScrollView __self__, Selector __cmd__, boolean directionalLockEnabled) { __self__.setDirectionalLockEnabled(directionalLockEnabled); }
        @Callback @BindSelector("isDragging") public static boolean isDragging(UIScrollView __self__, Selector __cmd__) { return __self__.isDragging(); }
        @Callback @BindSelector("indicatorStyle") public static UIScrollViewIndicatorStyle getIndicatorStyle(UIScrollView __self__, Selector __cmd__) { return __self__.getIndicatorStyle(); }
        @Callback @BindSelector("setIndicatorStyle:") public static void setIndicatorStyle(UIScrollView __self__, Selector __cmd__, UIScrollViewIndicatorStyle indicatorStyle) { __self__.setIndicatorStyle(indicatorStyle); }
        @Callback @BindSelector("maximumZoomScale") public static float getMaximumZoomScale(UIScrollView __self__, Selector __cmd__) { return __self__.getMaximumZoomScale(); }
        @Callback @BindSelector("setMaximumZoomScale:") public static void setMaximumZoomScale(UIScrollView __self__, Selector __cmd__, float maximumZoomScale) { __self__.setMaximumZoomScale(maximumZoomScale); }
        @Callback @BindSelector("minimumZoomScale") public static float getMinimumZoomScale(UIScrollView __self__, Selector __cmd__) { return __self__.getMinimumZoomScale(); }
        @Callback @BindSelector("setMinimumZoomScale:") public static void setMinimumZoomScale(UIScrollView __self__, Selector __cmd__, float minimumZoomScale) { __self__.setMinimumZoomScale(minimumZoomScale); }
        @Callback @BindSelector("isPagingEnabled") public static boolean isPagingEnabled(UIScrollView __self__, Selector __cmd__) { return __self__.isPagingEnabled(); }
        @Callback @BindSelector("setPagingEnabled:") public static void setPagingEnabled(UIScrollView __self__, Selector __cmd__, boolean pagingEnabled) { __self__.setPagingEnabled(pagingEnabled); }
        @Callback @BindSelector("panGestureRecognizer") public static UIPanGestureRecognizer getPanGestureRecognizer(UIScrollView __self__, Selector __cmd__) { return __self__.getPanGestureRecognizer(); }
        @Callback @BindSelector("pinchGestureRecognizer") public static UIPinchGestureRecognizer getPinchGestureRecognizer(UIScrollView __self__, Selector __cmd__) { return __self__.getPinchGestureRecognizer(); }
        @Callback @BindSelector("isScrollEnabled") public static boolean isScrollEnabled(UIScrollView __self__, Selector __cmd__) { return __self__.isScrollEnabled(); }
        @Callback @BindSelector("setScrollEnabled:") public static void setScrollEnabled(UIScrollView __self__, Selector __cmd__, boolean scrollEnabled) { __self__.setScrollEnabled(scrollEnabled); }
        @Callback @BindSelector("scrollIndicatorInsets") public static @ByVal UIEdgeInsets getScrollIndicatorInsets(UIScrollView __self__, Selector __cmd__) { return __self__.getScrollIndicatorInsets(); }
        @Callback @BindSelector("setScrollIndicatorInsets:") public static void setScrollIndicatorInsets(UIScrollView __self__, Selector __cmd__, @ByVal UIEdgeInsets scrollIndicatorInsets) { __self__.setScrollIndicatorInsets(scrollIndicatorInsets); }
        @Callback @BindSelector("scrollsToTop") public static boolean isScrollsToTop(UIScrollView __self__, Selector __cmd__) { return __self__.isScrollsToTop(); }
        @Callback @BindSelector("setScrollsToTop:") public static void setScrollsToTop(UIScrollView __self__, Selector __cmd__, boolean scrollsToTop) { __self__.setScrollsToTop(scrollsToTop); }
        @Callback @BindSelector("showsHorizontalScrollIndicator") public static boolean isShowsHorizontalScrollIndicator(UIScrollView __self__, Selector __cmd__) { return __self__.isShowsHorizontalScrollIndicator(); }
        @Callback @BindSelector("setShowsHorizontalScrollIndicator:") public static void setShowsHorizontalScrollIndicator(UIScrollView __self__, Selector __cmd__, boolean showsHorizontalScrollIndicator) { __self__.setShowsHorizontalScrollIndicator(showsHorizontalScrollIndicator); }
        @Callback @BindSelector("showsVerticalScrollIndicator") public static boolean isShowsVerticalScrollIndicator(UIScrollView __self__, Selector __cmd__) { return __self__.isShowsVerticalScrollIndicator(); }
        @Callback @BindSelector("setShowsVerticalScrollIndicator:") public static void setShowsVerticalScrollIndicator(UIScrollView __self__, Selector __cmd__, boolean showsVerticalScrollIndicator) { __self__.setShowsVerticalScrollIndicator(showsVerticalScrollIndicator); }
        @Callback @BindSelector("isTracking") public static boolean isTracking(UIScrollView __self__, Selector __cmd__) { return __self__.isTracking(); }
        @Callback @BindSelector("isZoomBouncing") public static boolean isZoomBouncing(UIScrollView __self__, Selector __cmd__) { return __self__.isZoomBouncing(); }
        @Callback @BindSelector("zoomScale") public static float getZoomScale(UIScrollView __self__, Selector __cmd__) { return __self__.getZoomScale(); }
        @Callback @BindSelector("setZoomScale:") public static void setZoomScale(UIScrollView __self__, Selector __cmd__, float zoomScale) { __self__.setZoomScale(zoomScale); }
        @Callback @BindSelector("isZooming") public static boolean isZooming(UIScrollView __self__, Selector __cmd__) { return __self__.isZooming(); }
        @Callback @BindSelector("flashScrollIndicators") public static void flashScrollIndicators(UIScrollView __self__, Selector __cmd__) { __self__.flashScrollIndicators(); }
        @Callback @BindSelector("scrollRectToVisible:animated:") public static void scrollRectToVisible(UIScrollView __self__, Selector __cmd__, @ByVal CGRect rect, boolean animated) { __self__.scrollRectToVisible(rect, animated); }
        @Callback @BindSelector("setContentOffset:animated:") public static void setContentOffset(UIScrollView __self__, Selector __cmd__, @ByVal CGPoint contentOffset, boolean animated) { __self__.setContentOffset(contentOffset, animated); }
        @Callback @BindSelector("setZoomScale:animated:") public static void setZoomScale(UIScrollView __self__, Selector __cmd__, float scale, boolean animated) { __self__.setZoomScale(scale, animated); }
        @Callback @BindSelector("touchesShouldBegin:withEvent:inContentView:") public static boolean touchesShouldBegin(UIScrollView __self__, Selector __cmd__, NSSet touches, UIEvent event, UIView view) { return __self__.touchesShouldBegin(touches, event, view); }
        @Callback @BindSelector("touchesShouldCancelInContentView:") public static boolean touchesShouldCancelInContentView(UIScrollView __self__, Selector __cmd__, UIView view) { return __self__.touchesShouldCancelInContentView(view); }
        @Callback @BindSelector("zoomToRect:animated:") public static void zoomToRect(UIScrollView __self__, Selector __cmd__, @ByVal CGRect rect, boolean animated) { __self__.zoomToRect(rect, animated); }
    }
    /*</callbacks>*/

}
