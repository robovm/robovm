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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html">UIView Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIView /*</name>*/ 
    extends /*<extends>*/ UIResponder /*</extends>*/ 
    /*<implements>*/ implements UIAppearance, UIAppearanceContainer /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIView /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIView /*</name>*/.class);

    /*<constructors>*/
    protected UIView(SkipInit skipInit) { super(skipInit); }
    public UIView() {}
    
    private static final Selector initWithFrame$ = Selector.register("initWithFrame:");
    @Bridge private native static @Pointer long objc_initWithFrame(UIView __self__, Selector __cmd__, @ByVal CGRect aRect);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/initWithFrame:">- (id)initWithFrame:(CGRect)aRect</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView(CGRect aRect) {
        super((SkipInit) null);
        initObject(objc_initWithFrame(this, initWithFrame$, aRect));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector alpha = Selector.register("alpha");
    @Bridge private native static float objc_getAlpha(UIView __self__, Selector __cmd__);
    @Bridge private native static float objc_getAlphaSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/alpha">@property(nonatomic) CGFloat alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getAlpha() {
        if (customClass) { return objc_getAlphaSuper(getSuper(), alpha); } else { return objc_getAlpha(this, alpha); }
    }
    
    private static final Selector setAlpha$ = Selector.register("setAlpha:");
    @Bridge private native static void objc_setAlpha(UIView __self__, Selector __cmd__, float alpha);
    @Bridge private native static void objc_setAlphaSuper(ObjCSuper __super__, Selector __cmd__, float alpha);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/alpha">@property(nonatomic) CGFloat alpha</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAlpha(float alpha) {
        if (customClass) { objc_setAlphaSuper(getSuper(), setAlpha$, alpha); } else { objc_setAlpha(this, setAlpha$, alpha); }
    }
    
    private static final Selector autoresizesSubviews = Selector.register("autoresizesSubviews");
    @Bridge private native static boolean objc_isAutoresizesSubviews(UIView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isAutoresizesSubviewsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/autoresizesSubviews">@property(nonatomic) BOOL autoresizesSubviews</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isAutoresizesSubviews() {
        if (customClass) { return objc_isAutoresizesSubviewsSuper(getSuper(), autoresizesSubviews); } else { return objc_isAutoresizesSubviews(this, autoresizesSubviews); }
    }
    
    private static final Selector setAutoresizesSubviews$ = Selector.register("setAutoresizesSubviews:");
    @Bridge private native static void objc_setAutoresizesSubviews(UIView __self__, Selector __cmd__, boolean autoresizesSubviews);
    @Bridge private native static void objc_setAutoresizesSubviewsSuper(ObjCSuper __super__, Selector __cmd__, boolean autoresizesSubviews);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/autoresizesSubviews">@property(nonatomic) BOOL autoresizesSubviews</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAutoresizesSubviews(boolean autoresizesSubviews) {
        if (customClass) { objc_setAutoresizesSubviewsSuper(getSuper(), setAutoresizesSubviews$, autoresizesSubviews); } else { objc_setAutoresizesSubviews(this, setAutoresizesSubviews$, autoresizesSubviews); }
    }
    
    private static final Selector autoresizingMask = Selector.register("autoresizingMask");
    @Bridge private native static UIViewAutoresizing objc_getAutoresizingMask(UIView __self__, Selector __cmd__);
    @Bridge private native static UIViewAutoresizing objc_getAutoresizingMaskSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/autoresizingMask">@property(nonatomic) UIViewAutoresizing autoresizingMask</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIViewAutoresizing getAutoresizingMask() {
        if (customClass) { return objc_getAutoresizingMaskSuper(getSuper(), autoresizingMask); } else { return objc_getAutoresizingMask(this, autoresizingMask); }
    }
    
    private static final Selector setAutoresizingMask$ = Selector.register("setAutoresizingMask:");
    @Bridge private native static void objc_setAutoresizingMask(UIView __self__, Selector __cmd__, UIViewAutoresizing autoresizingMask);
    @Bridge private native static void objc_setAutoresizingMaskSuper(ObjCSuper __super__, Selector __cmd__, UIViewAutoresizing autoresizingMask);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/autoresizingMask">@property(nonatomic) UIViewAutoresizing autoresizingMask</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAutoresizingMask(UIViewAutoresizing autoresizingMask) {
        if (customClass) { objc_setAutoresizingMaskSuper(getSuper(), setAutoresizingMask$, autoresizingMask); } else { objc_setAutoresizingMask(this, setAutoresizingMask$, autoresizingMask); }
    }
    
    private static final Selector backgroundColor = Selector.register("backgroundColor");
    @Bridge private native static UIColor objc_getBackgroundColor(UIView __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getBackgroundColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/backgroundColor">@property(nonatomic, copy) UIColor *backgroundColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor getBackgroundColor() {
        if (customClass) { return objc_getBackgroundColorSuper(getSuper(), backgroundColor); } else { return objc_getBackgroundColor(this, backgroundColor); }
    }
    
    private static final Selector setBackgroundColor$ = Selector.register("setBackgroundColor:");
    @Bridge private native static void objc_setBackgroundColor(UIView __self__, Selector __cmd__, UIColor backgroundColor);
    @Bridge private native static void objc_setBackgroundColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor backgroundColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/backgroundColor">@property(nonatomic, copy) UIColor *backgroundColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setBackgroundColor(UIColor backgroundColor) {
        if (customClass) { objc_setBackgroundColorSuper(getSuper(), setBackgroundColor$, backgroundColor); } else { objc_setBackgroundColor(this, setBackgroundColor$, backgroundColor); }
    }
    
    private static final Selector bounds = Selector.register("bounds");
    @Bridge private native static @ByVal CGRect objc_getBounds(UIView __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGRect objc_getBoundsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/bounds">@property(nonatomic) CGRect bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getBounds() {
        if (customClass) { return objc_getBoundsSuper(getSuper(), bounds); } else { return objc_getBounds(this, bounds); }
    }
    
    private static final Selector setBounds$ = Selector.register("setBounds:");
    @Bridge private native static void objc_setBounds(UIView __self__, Selector __cmd__, @ByVal CGRect bounds);
    @Bridge private native static void objc_setBoundsSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect bounds);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/bounds">@property(nonatomic) CGRect bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setBounds(CGRect bounds) {
        if (customClass) { objc_setBoundsSuper(getSuper(), setBounds$, bounds); } else { objc_setBounds(this, setBounds$, bounds); }
    }
    
    private static final Selector center = Selector.register("center");
    @Bridge private native static @ByVal CGPoint objc_getCenter(UIView __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGPoint objc_getCenterSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/center">@property(nonatomic) CGPoint center</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGPoint getCenter() {
        if (customClass) { return objc_getCenterSuper(getSuper(), center); } else { return objc_getCenter(this, center); }
    }
    
    private static final Selector setCenter$ = Selector.register("setCenter:");
    @Bridge private native static void objc_setCenter(UIView __self__, Selector __cmd__, @ByVal CGPoint center);
    @Bridge private native static void objc_setCenterSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint center);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/center">@property(nonatomic) CGPoint center</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setCenter(CGPoint center) {
        if (customClass) { objc_setCenterSuper(getSuper(), setCenter$, center); } else { objc_setCenter(this, setCenter$, center); }
    }
    
    private static final Selector clearsContextBeforeDrawing = Selector.register("clearsContextBeforeDrawing");
    @Bridge private native static boolean objc_isClearsContextBeforeDrawing(UIView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isClearsContextBeforeDrawingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/clearsContextBeforeDrawing">@property(nonatomic) BOOL clearsContextBeforeDrawing</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isClearsContextBeforeDrawing() {
        if (customClass) { return objc_isClearsContextBeforeDrawingSuper(getSuper(), clearsContextBeforeDrawing); } else { return objc_isClearsContextBeforeDrawing(this, clearsContextBeforeDrawing); }
    }
    
    private static final Selector setClearsContextBeforeDrawing$ = Selector.register("setClearsContextBeforeDrawing:");
    @Bridge private native static void objc_setClearsContextBeforeDrawing(UIView __self__, Selector __cmd__, boolean clearsContextBeforeDrawing);
    @Bridge private native static void objc_setClearsContextBeforeDrawingSuper(ObjCSuper __super__, Selector __cmd__, boolean clearsContextBeforeDrawing);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/clearsContextBeforeDrawing">@property(nonatomic) BOOL clearsContextBeforeDrawing</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setClearsContextBeforeDrawing(boolean clearsContextBeforeDrawing) {
        if (customClass) { objc_setClearsContextBeforeDrawingSuper(getSuper(), setClearsContextBeforeDrawing$, clearsContextBeforeDrawing); } else { objc_setClearsContextBeforeDrawing(this, setClearsContextBeforeDrawing$, clearsContextBeforeDrawing); }
    }
    
    private static final Selector clipsToBounds = Selector.register("clipsToBounds");
    @Bridge private native static boolean objc_isClipsToBounds(UIView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isClipsToBoundsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/clipsToBounds">@property(nonatomic) BOOL clipsToBounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isClipsToBounds() {
        if (customClass) { return objc_isClipsToBoundsSuper(getSuper(), clipsToBounds); } else { return objc_isClipsToBounds(this, clipsToBounds); }
    }
    
    private static final Selector setClipsToBounds$ = Selector.register("setClipsToBounds:");
    @Bridge private native static void objc_setClipsToBounds(UIView __self__, Selector __cmd__, boolean clipsToBounds);
    @Bridge private native static void objc_setClipsToBoundsSuper(ObjCSuper __super__, Selector __cmd__, boolean clipsToBounds);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/clipsToBounds">@property(nonatomic) BOOL clipsToBounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setClipsToBounds(boolean clipsToBounds) {
        if (customClass) { objc_setClipsToBoundsSuper(getSuper(), setClipsToBounds$, clipsToBounds); } else { objc_setClipsToBounds(this, setClipsToBounds$, clipsToBounds); }
    }
    
    private static final Selector contentMode = Selector.register("contentMode");
    @Bridge private native static UIViewContentMode objc_getContentMode(UIView __self__, Selector __cmd__);
    @Bridge private native static UIViewContentMode objc_getContentModeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/contentMode">@property(nonatomic) UIViewContentMode contentMode</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIViewContentMode getContentMode() {
        if (customClass) { return objc_getContentModeSuper(getSuper(), contentMode); } else { return objc_getContentMode(this, contentMode); }
    }
    
    private static final Selector setContentMode$ = Selector.register("setContentMode:");
    @Bridge private native static void objc_setContentMode(UIView __self__, Selector __cmd__, UIViewContentMode contentMode);
    @Bridge private native static void objc_setContentModeSuper(ObjCSuper __super__, Selector __cmd__, UIViewContentMode contentMode);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/contentMode">@property(nonatomic) UIViewContentMode contentMode</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setContentMode(UIViewContentMode contentMode) {
        if (customClass) { objc_setContentModeSuper(getSuper(), setContentMode$, contentMode); } else { objc_setContentMode(this, setContentMode$, contentMode); }
    }
    
    private static final Selector contentScaleFactor = Selector.register("contentScaleFactor");
    @Bridge private native static float objc_getContentScaleFactor(UIView __self__, Selector __cmd__);
    @Bridge private native static float objc_getContentScaleFactorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/contentScaleFactor">@property(nonatomic) CGFloat contentScaleFactor</a>
     * @since Available in iOS 4.0 and later.
     */
    public float getContentScaleFactor() {
        if (customClass) { return objc_getContentScaleFactorSuper(getSuper(), contentScaleFactor); } else { return objc_getContentScaleFactor(this, contentScaleFactor); }
    }
    
    private static final Selector setContentScaleFactor$ = Selector.register("setContentScaleFactor:");
    @Bridge private native static void objc_setContentScaleFactor(UIView __self__, Selector __cmd__, float contentScaleFactor);
    @Bridge private native static void objc_setContentScaleFactorSuper(ObjCSuper __super__, Selector __cmd__, float contentScaleFactor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/contentScaleFactor">@property(nonatomic) CGFloat contentScaleFactor</a>
     * @since Available in iOS 4.0 and later.
     */
    public void setContentScaleFactor(float contentScaleFactor) {
        if (customClass) { objc_setContentScaleFactorSuper(getSuper(), setContentScaleFactor$, contentScaleFactor); } else { objc_setContentScaleFactor(this, setContentScaleFactor$, contentScaleFactor); }
    }
    
    private static final Selector isExclusiveTouch = Selector.register("isExclusiveTouch");
    @Bridge private native static boolean objc_isExclusiveTouch(UIView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isExclusiveTouchSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/exclusiveTouch">@property(nonatomic, getter=isExclusiveTouch) BOOL exclusiveTouch</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isExclusiveTouch() {
        if (customClass) { return objc_isExclusiveTouchSuper(getSuper(), isExclusiveTouch); } else { return objc_isExclusiveTouch(this, isExclusiveTouch); }
    }
    
    private static final Selector setExclusiveTouch$ = Selector.register("setExclusiveTouch:");
    @Bridge private native static void objc_setExclusiveTouch(UIView __self__, Selector __cmd__, boolean exclusiveTouch);
    @Bridge private native static void objc_setExclusiveTouchSuper(ObjCSuper __super__, Selector __cmd__, boolean exclusiveTouch);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/exclusiveTouch">@property(nonatomic, getter=isExclusiveTouch) BOOL exclusiveTouch</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setExclusiveTouch(boolean exclusiveTouch) {
        if (customClass) { objc_setExclusiveTouchSuper(getSuper(), setExclusiveTouch$, exclusiveTouch); } else { objc_setExclusiveTouch(this, setExclusiveTouch$, exclusiveTouch); }
    }
    
    private static final Selector frame = Selector.register("frame");
    @Bridge private native static @ByVal CGRect objc_getFrame(UIView __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGRect objc_getFrameSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/frame">@property(nonatomic) CGRect frame</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getFrame() {
        if (customClass) { return objc_getFrameSuper(getSuper(), frame); } else { return objc_getFrame(this, frame); }
    }
    
    private static final Selector setFrame$ = Selector.register("setFrame:");
    @Bridge private native static void objc_setFrame(UIView __self__, Selector __cmd__, @ByVal CGRect frame);
    @Bridge private native static void objc_setFrameSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect frame);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/frame">@property(nonatomic) CGRect frame</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setFrame(CGRect frame) {
        if (customClass) { objc_setFrameSuper(getSuper(), setFrame$, frame); } else { objc_setFrame(this, setFrame$, frame); }
    }
    
    private static final Selector gestureRecognizers = Selector.register("gestureRecognizers");
    @Bridge private native static NSArray objc_getGestureRecognizers(UIView __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getGestureRecognizersSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/gestureRecognizers">@property(nonatomic, copy) NSArray *gestureRecognizers</a>
     * @since Available in iOS 3.2 and later.
     */
    public NSArray getGestureRecognizers() {
        if (customClass) { return objc_getGestureRecognizersSuper(getSuper(), gestureRecognizers); } else { return objc_getGestureRecognizers(this, gestureRecognizers); }
    }
    
    private static final Selector setGestureRecognizers$ = Selector.register("setGestureRecognizers:");
    @Bridge private native static void objc_setGestureRecognizers(UIView __self__, Selector __cmd__, NSArray gestureRecognizers);
    @Bridge private native static void objc_setGestureRecognizersSuper(ObjCSuper __super__, Selector __cmd__, NSArray gestureRecognizers);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/gestureRecognizers">@property(nonatomic, copy) NSArray *gestureRecognizers</a>
     * @since Available in iOS 3.2 and later.
     */
    public void setGestureRecognizers(NSArray gestureRecognizers) {
        if (customClass) { objc_setGestureRecognizersSuper(getSuper(), setGestureRecognizers$, gestureRecognizers); } else { objc_setGestureRecognizers(this, setGestureRecognizers$, gestureRecognizers); }
    }
    
    private static final Selector isHidden = Selector.register("isHidden");
    @Bridge private native static boolean objc_isHidden(UIView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isHiddenSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/hidden">@property(nonatomic, getter=isHidden) BOOL hidden</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isHidden() {
        if (customClass) { return objc_isHiddenSuper(getSuper(), isHidden); } else { return objc_isHidden(this, isHidden); }
    }
    
    private static final Selector setHidden$ = Selector.register("setHidden:");
    @Bridge private native static void objc_setHidden(UIView __self__, Selector __cmd__, boolean hidden);
    @Bridge private native static void objc_setHiddenSuper(ObjCSuper __super__, Selector __cmd__, boolean hidden);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/hidden">@property(nonatomic, getter=isHidden) BOOL hidden</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setHidden(boolean hidden) {
        if (customClass) { objc_setHiddenSuper(getSuper(), setHidden$, hidden); } else { objc_setHidden(this, setHidden$, hidden); }
    }
    
    private static final Selector layer = Selector.register("layer");
    @Bridge private native static CALayer objc_getLayer(UIView __self__, Selector __cmd__);
    @Bridge private native static CALayer objc_getLayerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/layer">@property(nonatomic, readonly, retain) CALayer *layer</a>
     * @since Available in iOS 2.0 and later.
     */
    public CALayer getLayer() {
        if (customClass) { return objc_getLayerSuper(getSuper(), layer); } else { return objc_getLayer(this, layer); }
    }
    
    private static final Selector isMultipleTouchEnabled = Selector.register("isMultipleTouchEnabled");
    @Bridge private native static boolean objc_isMultipleTouchEnabled(UIView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isMultipleTouchEnabledSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/multipleTouchEnabled">@property(nonatomic, getter=isMultipleTouchEnabled) BOOL multipleTouchEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isMultipleTouchEnabled() {
        if (customClass) { return objc_isMultipleTouchEnabledSuper(getSuper(), isMultipleTouchEnabled); } else { return objc_isMultipleTouchEnabled(this, isMultipleTouchEnabled); }
    }
    
    private static final Selector setMultipleTouchEnabled$ = Selector.register("setMultipleTouchEnabled:");
    @Bridge private native static void objc_setMultipleTouchEnabled(UIView __self__, Selector __cmd__, boolean multipleTouchEnabled);
    @Bridge private native static void objc_setMultipleTouchEnabledSuper(ObjCSuper __super__, Selector __cmd__, boolean multipleTouchEnabled);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/multipleTouchEnabled">@property(nonatomic, getter=isMultipleTouchEnabled) BOOL multipleTouchEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setMultipleTouchEnabled(boolean multipleTouchEnabled) {
        if (customClass) { objc_setMultipleTouchEnabledSuper(getSuper(), setMultipleTouchEnabled$, multipleTouchEnabled); } else { objc_setMultipleTouchEnabled(this, setMultipleTouchEnabled$, multipleTouchEnabled); }
    }
    
    private static final Selector isOpaque = Selector.register("isOpaque");
    @Bridge private native static boolean objc_isOpaque(UIView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isOpaqueSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/opaque">@property(nonatomic, getter=isOpaque) BOOL opaque</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isOpaque() {
        if (customClass) { return objc_isOpaqueSuper(getSuper(), isOpaque); } else { return objc_isOpaque(this, isOpaque); }
    }
    
    private static final Selector setOpaque$ = Selector.register("setOpaque:");
    @Bridge private native static void objc_setOpaque(UIView __self__, Selector __cmd__, boolean opaque);
    @Bridge private native static void objc_setOpaqueSuper(ObjCSuper __super__, Selector __cmd__, boolean opaque);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/opaque">@property(nonatomic, getter=isOpaque) BOOL opaque</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setOpaque(boolean opaque) {
        if (customClass) { objc_setOpaqueSuper(getSuper(), setOpaque$, opaque); } else { objc_setOpaque(this, setOpaque$, opaque); }
    }
    
    private static final Selector restorationIdentifier = Selector.register("restorationIdentifier");
    @Bridge private native static String objc_getRestorationIdentifier(UIView __self__, Selector __cmd__);
    @Bridge private native static String objc_getRestorationIdentifierSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/restorationIdentifier">@property (nonatomic, copy) NSString *restorationIdentifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public String getRestorationIdentifier() {
        if (customClass) { return objc_getRestorationIdentifierSuper(getSuper(), restorationIdentifier); } else { return objc_getRestorationIdentifier(this, restorationIdentifier); }
    }
    
    private static final Selector setRestorationIdentifier$ = Selector.register("setRestorationIdentifier:");
    @Bridge private native static void objc_setRestorationIdentifier(UIView __self__, Selector __cmd__, String restorationIdentifier);
    @Bridge private native static void objc_setRestorationIdentifierSuper(ObjCSuper __super__, Selector __cmd__, String restorationIdentifier);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/restorationIdentifier">@property (nonatomic, copy) NSString *restorationIdentifier</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setRestorationIdentifier(String restorationIdentifier) {
        if (customClass) { objc_setRestorationIdentifierSuper(getSuper(), setRestorationIdentifier$, restorationIdentifier); } else { objc_setRestorationIdentifier(this, setRestorationIdentifier$, restorationIdentifier); }
    }
    
    private static final Selector subviews = Selector.register("subviews");
    @Bridge private native static NSArray objc_getSubviews(UIView __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getSubviewsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/subviews">@property(nonatomic, readonly, copy) NSArray *subviews</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray getSubviews() {
        if (customClass) { return objc_getSubviewsSuper(getSuper(), subviews); } else { return objc_getSubviews(this, subviews); }
    }
    
    private static final Selector superview = Selector.register("superview");
    @Bridge private native static UIView objc_getSuperview(UIView __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getSuperviewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/superview">@property(nonatomic, readonly) UIView *superview</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getSuperview() {
        if (customClass) { return objc_getSuperviewSuper(getSuper(), superview); } else { return objc_getSuperview(this, superview); }
    }
    
    private static final Selector tag = Selector.register("tag");
    @Bridge private native static int objc_getTag(UIView __self__, Selector __cmd__);
    @Bridge private native static int objc_getTagSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/tag">@property(nonatomic) NSInteger tag</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getTag() {
        if (customClass) { return objc_getTagSuper(getSuper(), tag); } else { return objc_getTag(this, tag); }
    }
    
    private static final Selector setTag$ = Selector.register("setTag:");
    @Bridge private native static void objc_setTag(UIView __self__, Selector __cmd__, int tag);
    @Bridge private native static void objc_setTagSuper(ObjCSuper __super__, Selector __cmd__, int tag);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/tag">@property(nonatomic) NSInteger tag</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTag(int tag) {
        if (customClass) { objc_setTagSuper(getSuper(), setTag$, tag); } else { objc_setTag(this, setTag$, tag); }
    }
    
    private static final Selector transform = Selector.register("transform");
    @Bridge private native static @ByVal CGAffineTransform objc_getTransform(UIView __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGAffineTransform objc_getTransformSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/transform">@property(nonatomic) CGAffineTransform transform</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGAffineTransform getTransform() {
        if (customClass) { return objc_getTransformSuper(getSuper(), transform); } else { return objc_getTransform(this, transform); }
    }
    
    private static final Selector setTransform$ = Selector.register("setTransform:");
    @Bridge private native static void objc_setTransform(UIView __self__, Selector __cmd__, @ByVal CGAffineTransform transform);
    @Bridge private native static void objc_setTransformSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGAffineTransform transform);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/transform">@property(nonatomic) CGAffineTransform transform</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTransform(CGAffineTransform transform) {
        if (customClass) { objc_setTransformSuper(getSuper(), setTransform$, transform); } else { objc_setTransform(this, setTransform$, transform); }
    }
    
    private static final Selector isUserInteractionEnabled = Selector.register("isUserInteractionEnabled");
    @Bridge private native static boolean objc_isUserInteractionEnabled(UIView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isUserInteractionEnabledSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/userInteractionEnabled">@property(nonatomic, getter=isUserInteractionEnabled) BOOL userInteractionEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isUserInteractionEnabled() {
        if (customClass) { return objc_isUserInteractionEnabledSuper(getSuper(), isUserInteractionEnabled); } else { return objc_isUserInteractionEnabled(this, isUserInteractionEnabled); }
    }
    
    private static final Selector setUserInteractionEnabled$ = Selector.register("setUserInteractionEnabled:");
    @Bridge private native static void objc_setUserInteractionEnabled(UIView __self__, Selector __cmd__, boolean userInteractionEnabled);
    @Bridge private native static void objc_setUserInteractionEnabledSuper(ObjCSuper __super__, Selector __cmd__, boolean userInteractionEnabled);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/userInteractionEnabled">@property(nonatomic, getter=isUserInteractionEnabled) BOOL userInteractionEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setUserInteractionEnabled(boolean userInteractionEnabled) {
        if (customClass) { objc_setUserInteractionEnabledSuper(getSuper(), setUserInteractionEnabled$, userInteractionEnabled); } else { objc_setUserInteractionEnabled(this, setUserInteractionEnabled$, userInteractionEnabled); }
    }
    
    private static final Selector window = Selector.register("window");
    @Bridge private native static UIWindow objc_getWindow(UIView __self__, Selector __cmd__);
    @Bridge private native static UIWindow objc_getWindowSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instp/UIView/window">@property(nonatomic, readonly) UIWindow *window</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIWindow getWindow() {
        if (customClass) { return objc_getWindowSuper(getSuper(), window); } else { return objc_getWindow(this, window); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector animateWithDuration$animations$ = Selector.register("animateWithDuration:animations:");
    @Bridge private native static void objc_animate(ObjCClass __self__, Selector __cmd__, double duration, VoidBlock animations);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/animateWithDuration:animations:">+ (void)animateWithDuration:(NSTimeInterval)duration animations:(void (^)(void))animations</a>
     * @since Available in iOS 4.0 and later.
     */
    public static void animate(double duration, VoidBlock animations) {
        objc_animate(objCClass, animateWithDuration$animations$, duration, animations);
    }
    
    private static final Selector animateWithDuration$animations$completion$ = Selector.register("animateWithDuration:animations:completion:");
    @Bridge private native static void objc_animate(ObjCClass __self__, Selector __cmd__, double duration, VoidBlock animations, VoidBooleanBlock completion);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/animateWithDuration:animations:completion:">+ (void)animateWithDuration:(NSTimeInterval)duration animations:(void (^)(void))animations completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 4.0 and later.
     */
    public static void animate(double duration, VoidBlock animations, VoidBooleanBlock completion) {
        objc_animate(objCClass, animateWithDuration$animations$completion$, duration, animations, completion);
    }
    
    private static final Selector animateWithDuration$delay$options$animations$completion$ = Selector.register("animateWithDuration:delay:options:animations:completion:");
    @Bridge private native static void objc_animate(ObjCClass __self__, Selector __cmd__, double duration, double delay, UIViewAnimationOptions options, VoidBlock animations, VoidBooleanBlock completion);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/animateWithDuration:delay:options:animations:completion:">+ (void)animateWithDuration:(NSTimeInterval)duration delay:(NSTimeInterval)delay options:(UIViewAnimationOptions)options animations:(void (^)(void))animations completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 4.0 and later.
     */
    public static void animate(double duration, double delay, UIViewAnimationOptions options, VoidBlock animations, VoidBooleanBlock completion) {
        objc_animate(objCClass, animateWithDuration$delay$options$animations$completion$, duration, delay, options, animations, completion);
    }
    
    private static final Selector areAnimationsEnabled = Selector.register("areAnimationsEnabled");
    @Bridge private native static boolean objc_areAnimationsEnabled(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/areAnimationsEnabled">+ (BOOL)areAnimationsEnabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public static boolean areAnimationsEnabled() {
        return objc_areAnimationsEnabled(objCClass, areAnimationsEnabled);
    }
    
    private static final Selector beginAnimations$context$ = Selector.register("beginAnimations:context:");
    @Bridge private native static void objc_beginAnimations(ObjCClass __self__, Selector __cmd__, String animationID, VoidPtr context);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/beginAnimations:context:">+ (void)beginAnimations:(NSString *)animationID context:(void *)context</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void beginAnimations(String animationID, VoidPtr context) {
        objc_beginAnimations(objCClass, beginAnimations$context$, animationID, context);
    }
    
    private static final Selector commitAnimations = Selector.register("commitAnimations");
    @Bridge private native static void objc_commitAnimations(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/commitAnimations">+ (void)commitAnimations</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void commitAnimations() {
        objc_commitAnimations(objCClass, commitAnimations);
    }
    
    private static final Selector layerClass = Selector.register("layerClass");
    @Bridge private native static ObjCClass objc_getLayerClass(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/layerClass">+ (Class)layerClass</a>
     * @since Available in iOS 2.0 and later.
     */
    public static ObjCClass getLayerClass() {
        return objc_getLayerClass(objCClass, layerClass);
    }
    
    private static final Selector requiresConstraintBasedLayout = Selector.register("requiresConstraintBasedLayout");
    @Bridge private native static boolean objc_requiresConstraintBasedLayout(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/requiresConstraintBasedLayout">+ (BOOL)requiresConstraintBasedLayout</a>
     * @since Available in iOS 6.0 and later.
     */
    public static boolean requiresConstraintBasedLayout() {
        return objc_requiresConstraintBasedLayout(objCClass, requiresConstraintBasedLayout);
    }
    
    private static final Selector setAnimationBeginsFromCurrentState$ = Selector.register("setAnimationBeginsFromCurrentState:");
    @Bridge private native static void objc_setAnimationBeginsFromCurrentState(ObjCClass __self__, Selector __cmd__, boolean fromCurrentState);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationBeginsFromCurrentState:">+ (void)setAnimationBeginsFromCurrentState:(BOOL)fromCurrentState</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationBeginsFromCurrentState(boolean fromCurrentState) {
        objc_setAnimationBeginsFromCurrentState(objCClass, setAnimationBeginsFromCurrentState$, fromCurrentState);
    }
    
    private static final Selector setAnimationCurve$ = Selector.register("setAnimationCurve:");
    @Bridge private native static void objc_setAnimationCurve(ObjCClass __self__, Selector __cmd__, UIViewAnimationCurve curve);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationCurve:">+ (void)setAnimationCurve:(UIViewAnimationCurve)curve</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationCurve(UIViewAnimationCurve curve) {
        objc_setAnimationCurve(objCClass, setAnimationCurve$, curve);
    }
    
    private static final Selector setAnimationDelay$ = Selector.register("setAnimationDelay:");
    @Bridge private native static void objc_setAnimationDelay(ObjCClass __self__, Selector __cmd__, double delay);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationDelay:">+ (void)setAnimationDelay:(NSTimeInterval)delay</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationDelay(double delay) {
        objc_setAnimationDelay(objCClass, setAnimationDelay$, delay);
    }
    
    private static final Selector setAnimationDelegate$ = Selector.register("setAnimationDelegate:");
    @Bridge private native static void objc_setAnimationDelegate(ObjCClass __self__, Selector __cmd__, NSObject delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationDelegate:">+ (void)setAnimationDelegate:(id)delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationDelegate(NSObject delegate) {
        objc_setAnimationDelegate(objCClass, setAnimationDelegate$, delegate);
    }
    
    private static final Selector setAnimationDidStopSelector$ = Selector.register("setAnimationDidStopSelector:");
    @Bridge private native static void objc_setAnimationDidStopSelector(ObjCClass __self__, Selector __cmd__, Selector selector);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationDidStopSelector:">+ (void)setAnimationDidStopSelector:(SEL)selector</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationDidStopSelector(Selector selector) {
        objc_setAnimationDidStopSelector(objCClass, setAnimationDidStopSelector$, selector);
    }
    
    private static final Selector setAnimationRepeatAutoreverses$ = Selector.register("setAnimationRepeatAutoreverses:");
    @Bridge private native static void objc_setAnimationRepeatAutoreverses(ObjCClass __self__, Selector __cmd__, boolean repeatAutoreverses);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationRepeatAutoreverses:">+ (void)setAnimationRepeatAutoreverses:(BOOL)repeatAutoreverses</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationRepeatAutoreverses(boolean repeatAutoreverses) {
        objc_setAnimationRepeatAutoreverses(objCClass, setAnimationRepeatAutoreverses$, repeatAutoreverses);
    }
    
    private static final Selector setAnimationRepeatCount$ = Selector.register("setAnimationRepeatCount:");
    @Bridge private native static void objc_setAnimationRepeatCount(ObjCClass __self__, Selector __cmd__, float repeatCount);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationRepeatCount:">+ (void)setAnimationRepeatCount:(float)repeatCount</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationRepeatCount(float repeatCount) {
        objc_setAnimationRepeatCount(objCClass, setAnimationRepeatCount$, repeatCount);
    }
    
    private static final Selector setAnimationStartDate$ = Selector.register("setAnimationStartDate:");
    @Bridge private native static void objc_setAnimationStartDate(ObjCClass __self__, Selector __cmd__, NSDate startTime);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationStartDate:">+ (void)setAnimationStartDate:(NSDate *)startTime</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationStartDate(NSDate startTime) {
        objc_setAnimationStartDate(objCClass, setAnimationStartDate$, startTime);
    }
    
    private static final Selector setAnimationTransition$forView$cache$ = Selector.register("setAnimationTransition:forView:cache:");
    @Bridge private native static void objc_setAnimationTransition(ObjCClass __self__, Selector __cmd__, UIViewAnimationTransition transition, UIView view, boolean cache);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationTransition:forView:cache:">+ (void)setAnimationTransition:(UIViewAnimationTransition)transition forView:(UIView *)view cache:(BOOL)cache</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationTransition(UIViewAnimationTransition transition, UIView view, boolean cache) {
        objc_setAnimationTransition(objCClass, setAnimationTransition$forView$cache$, transition, view, cache);
    }
    
    private static final Selector setAnimationWillStartSelector$ = Selector.register("setAnimationWillStartSelector:");
    @Bridge private native static void objc_setAnimationWillStartSelector(ObjCClass __self__, Selector __cmd__, Selector selector);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationWillStartSelector:">+ (void)setAnimationWillStartSelector:(SEL)selector</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationWillStartSelector(Selector selector) {
        objc_setAnimationWillStartSelector(objCClass, setAnimationWillStartSelector$, selector);
    }
    
    private static final Selector setAnimationsEnabled$ = Selector.register("setAnimationsEnabled:");
    @Bridge private native static void objc_setAnimationsEnabled(ObjCClass __self__, Selector __cmd__, boolean enabled);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationsEnabled:">+ (void)setAnimationsEnabled:(BOOL)enabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setAnimationsEnabled(boolean enabled) {
        objc_setAnimationsEnabled(objCClass, setAnimationsEnabled$, enabled);
    }
    
    private static final Selector setAnimationDuration$ = Selector.register("setAnimationDuration:");
    @Bridge private native static void objc_setDurationForAnimation(ObjCClass __self__, Selector __cmd__, double duration);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/setAnimationDuration:">+ (void)setAnimationDuration:(NSTimeInterval)duration</a>
     * @since Available in iOS 2.0 and later.
     */
    public static void setDurationForAnimation(double duration) {
        objc_setDurationForAnimation(objCClass, setAnimationDuration$, duration);
    }
    
    private static final Selector transitionWithView$duration$options$animations$completion$ = Selector.register("transitionWithView:duration:options:animations:completion:");
    @Bridge private native static void objc_transition(ObjCClass __self__, Selector __cmd__, UIView view, double duration, UIViewAnimationOptions options, VoidBlock animations, VoidBooleanBlock completion);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/transitionWithView:duration:options:animations:completion:">+ (void)transitionWithView:(UIView *)view duration:(NSTimeInterval)duration options:(UIViewAnimationOptions)options animations:(void (^)(void))animations completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 4.0 and later.
     */
    public static void transition(UIView view, double duration, UIViewAnimationOptions options, VoidBlock animations, VoidBooleanBlock completion) {
        objc_transition(objCClass, transitionWithView$duration$options$animations$completion$, view, duration, options, animations, completion);
    }
    
    private static final Selector transitionFromView$toView$duration$options$completion$ = Selector.register("transitionFromView:toView:duration:options:completion:");
    @Bridge private native static void objc_transition(ObjCClass __self__, Selector __cmd__, UIView fromView, UIView toView, double duration, UIViewAnimationOptions options, VoidBooleanBlock completion);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/clm/UIView/transitionFromView:toView:duration:options:completion:">+ (void)transitionFromView:(UIView *)fromView toView:(UIView *)toView duration:(NSTimeInterval)duration options:(UIViewAnimationOptions)options completion:(void (^)(BOOL finished))completion</a>
     * @since Available in iOS 4.0 and later.
     */
    public static void transition(UIView fromView, UIView toView, double duration, UIViewAnimationOptions options, VoidBooleanBlock completion) {
        objc_transition(objCClass, transitionFromView$toView$duration$options$completion$, fromView, toView, duration, options, completion);
    }
    
    private static final Selector addConstraint$ = Selector.register("addConstraint:");
    @Bridge private native static void objc_addConstraint(UIView __self__, Selector __cmd__, NSLayoutConstraint constraint);
    @Bridge private native static void objc_addConstraintSuper(ObjCSuper __super__, Selector __cmd__, NSLayoutConstraint constraint);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/addConstraint:">- (void)addConstraint:(NSLayoutConstraint *)constraint</a>
     * @since Available in iOS 6.0 and later.
     */
    public void addConstraint(NSLayoutConstraint constraint) {
        if (customClass) { objc_addConstraintSuper(getSuper(), addConstraint$, constraint); } else { objc_addConstraint(this, addConstraint$, constraint); }
    }
    
    private static final Selector addConstraints$ = Selector.register("addConstraints:");
    @Bridge private native static void objc_addConstraints(UIView __self__, Selector __cmd__, NSArray constraints);
    @Bridge private native static void objc_addConstraintsSuper(ObjCSuper __super__, Selector __cmd__, NSArray constraints);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/addConstraints:">- (void)addConstraints:(NSArray *)constraints</a>
     * @since Available in iOS 6.0 and later.
     */
    public void addConstraints(NSArray constraints) {
        if (customClass) { objc_addConstraintsSuper(getSuper(), addConstraints$, constraints); } else { objc_addConstraints(this, addConstraints$, constraints); }
    }
    
    private static final Selector addGestureRecognizer$ = Selector.register("addGestureRecognizer:");
    @Bridge private native static void objc_addGestureRecognizer(UIView __self__, Selector __cmd__, UIGestureRecognizer gestureRecognizer);
    @Bridge private native static void objc_addGestureRecognizerSuper(ObjCSuper __super__, Selector __cmd__, UIGestureRecognizer gestureRecognizer);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/addGestureRecognizer:">- (void)addGestureRecognizer:(UIGestureRecognizer *)gestureRecognizer</a>
     * @since Available in iOS 3.2 and later.
     */
    public void addGestureRecognizer(UIGestureRecognizer gestureRecognizer) {
        if (customClass) { objc_addGestureRecognizerSuper(getSuper(), addGestureRecognizer$, gestureRecognizer); } else { objc_addGestureRecognizer(this, addGestureRecognizer$, gestureRecognizer); }
    }
    
    private static final Selector addSubview$ = Selector.register("addSubview:");
    @Bridge private native static void objc_addSubview(UIView __self__, Selector __cmd__, UIView view);
    @Bridge private native static void objc_addSubviewSuper(ObjCSuper __super__, Selector __cmd__, UIView view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/addSubview:">- (void)addSubview:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public void addSubview(UIView view) {
        if (customClass) { objc_addSubviewSuper(getSuper(), addSubview$, view); } else { objc_addSubview(this, addSubview$, view); }
    }
    
    private static final Selector bringSubviewToFront$ = Selector.register("bringSubviewToFront:");
    @Bridge private native static void objc_bringSubviewToFront(UIView __self__, Selector __cmd__, UIView view);
    @Bridge private native static void objc_bringSubviewToFrontSuper(ObjCSuper __super__, Selector __cmd__, UIView view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/bringSubviewToFront:">- (void)bringSubviewToFront:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public void bringSubviewToFront(UIView view) {
        if (customClass) { objc_bringSubviewToFrontSuper(getSuper(), bringSubviewToFront$, view); } else { objc_bringSubviewToFront(this, bringSubviewToFront$, view); }
    }
    
    private static final Selector convertPoint$fromView$ = Selector.register("convertPoint:fromView:");
    @Bridge private native static @ByVal CGPoint objc_convertPointFromView(UIView __self__, Selector __cmd__, @ByVal CGPoint point, UIView view);
    @Bridge private native static @ByVal CGPoint objc_convertPointFromViewSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint point, UIView view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/convertPoint:fromView:">- (CGPoint)convertPoint:(CGPoint)point fromView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGPoint convertPointFromView(CGPoint point, UIView view) {
        if (customClass) { return objc_convertPointFromViewSuper(getSuper(), convertPoint$fromView$, point, view); } else { return objc_convertPointFromView(this, convertPoint$fromView$, point, view); }
    }
    
    private static final Selector convertPoint$toView$ = Selector.register("convertPoint:toView:");
    @Bridge private native static @ByVal CGPoint objc_convertPointToView(UIView __self__, Selector __cmd__, @ByVal CGPoint point, UIView view);
    @Bridge private native static @ByVal CGPoint objc_convertPointToViewSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint point, UIView view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/convertPoint:toView:">- (CGPoint)convertPoint:(CGPoint)point toView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGPoint convertPointToView(CGPoint point, UIView view) {
        if (customClass) { return objc_convertPointToViewSuper(getSuper(), convertPoint$toView$, point, view); } else { return objc_convertPointToView(this, convertPoint$toView$, point, view); }
    }
    
    private static final Selector convertRect$fromView$ = Selector.register("convertRect:fromView:");
    @Bridge private native static @ByVal CGRect objc_convertRectFromView(UIView __self__, Selector __cmd__, @ByVal CGRect rect, UIView view);
    @Bridge private native static @ByVal CGRect objc_convertRectFromViewSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect rect, UIView view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/convertRect:fromView:">- (CGRect)convertRect:(CGRect)rect fromView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect convertRectFromView(CGRect rect, UIView view) {
        if (customClass) { return objc_convertRectFromViewSuper(getSuper(), convertRect$fromView$, rect, view); } else { return objc_convertRectFromView(this, convertRect$fromView$, rect, view); }
    }
    
    private static final Selector convertRect$toView$ = Selector.register("convertRect:toView:");
    @Bridge private native static @ByVal CGRect objc_convertRectToView(UIView __self__, Selector __cmd__, @ByVal CGRect rect, UIView view);
    @Bridge private native static @ByVal CGRect objc_convertRectToViewSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect rect, UIView view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/convertRect:toView:">- (CGRect)convertRect:(CGRect)rect toView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect convertRectToView(CGRect rect, UIView view) {
        if (customClass) { return objc_convertRectToViewSuper(getSuper(), convertRect$toView$, rect, view); } else { return objc_convertRectToView(this, convertRect$toView$, rect, view); }
    }
    
    private static final Selector decodeRestorableStateWithCoder$ = Selector.register("decodeRestorableStateWithCoder:");
    @Bridge private native static void objc_decodeRestorableState(UIView __self__, Selector __cmd__, NSCoder coder);
    @Bridge private native static void objc_decodeRestorableStateSuper(ObjCSuper __super__, Selector __cmd__, NSCoder coder);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/decodeRestorableStateWithCoder:">- (void) decodeRestorableStateWithCoder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    public void decodeRestorableState(NSCoder coder) {
        if (customClass) { objc_decodeRestorableStateSuper(getSuper(), decodeRestorableStateWithCoder$, coder); } else { objc_decodeRestorableState(this, decodeRestorableStateWithCoder$, coder); }
    }
    
    private static final Selector didAddSubview$ = Selector.register("didAddSubview:");
    @Bridge private native static void objc_didAddSubview(UIView __self__, Selector __cmd__, UIView subview);
    @Bridge private native static void objc_didAddSubviewSuper(ObjCSuper __super__, Selector __cmd__, UIView subview);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/didAddSubview:">- (void)didAddSubview:(UIView *)subview</a>
     * @since Available in iOS 2.0 and later.
     */
    public void didAddSubview(UIView subview) {
        if (customClass) { objc_didAddSubviewSuper(getSuper(), didAddSubview$, subview); } else { objc_didAddSubview(this, didAddSubview$, subview); }
    }
    
    private static final Selector didMoveToSuperview = Selector.register("didMoveToSuperview");
    @Bridge private native static void objc_didMoveToSuperview(UIView __self__, Selector __cmd__);
    @Bridge private native static void objc_didMoveToSuperviewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/didMoveToSuperview">- (void)didMoveToSuperview</a>
     * @since Available in iOS 2.0 and later.
     */
    public void didMoveToSuperview() {
        if (customClass) { objc_didMoveToSuperviewSuper(getSuper(), didMoveToSuperview); } else { objc_didMoveToSuperview(this, didMoveToSuperview); }
    }
    
    private static final Selector didMoveToWindow = Selector.register("didMoveToWindow");
    @Bridge private native static void objc_didMoveToWindow(UIView __self__, Selector __cmd__);
    @Bridge private native static void objc_didMoveToWindowSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/didMoveToWindow">- (void)didMoveToWindow</a>
     * @since Available in iOS 2.0 and later.
     */
    public void didMoveToWindow() {
        if (customClass) { objc_didMoveToWindowSuper(getSuper(), didMoveToWindow); } else { objc_didMoveToWindow(this, didMoveToWindow); }
    }
    
    private static final Selector drawRect$ = Selector.register("drawRect:");
    @Bridge private native static void objc_draw(UIView __self__, Selector __cmd__, @ByVal CGRect rect);
    @Bridge private native static void objc_drawSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect rect);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/drawRect:">- (void)drawRect:(CGRect)rect</a>
     * @since Available in iOS 2.0 and later.
     */
    public void draw(CGRect rect) {
        if (customClass) { objc_drawSuper(getSuper(), drawRect$, rect); } else { objc_draw(this, drawRect$, rect); }
    }
    
    private static final Selector drawRect$forViewPrintFormatter$ = Selector.register("drawRect:forViewPrintFormatter:");
    @Bridge private native static void objc_drawRect(UIView __self__, Selector __cmd__, @ByVal CGRect area, UIViewPrintFormatter formatter);
    @Bridge private native static void objc_drawRectSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect area, UIViewPrintFormatter formatter);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/drawRect:forViewPrintFormatter:">- (void)drawRect:(CGRect)area forViewPrintFormatter:(UIViewPrintFormatter *)formatter</a>
     * @since Available in iOS 4.2 and later.
     */
    public void drawRect(CGRect area, UIViewPrintFormatter formatter) {
        if (customClass) { objc_drawRectSuper(getSuper(), drawRect$forViewPrintFormatter$, area, formatter); } else { objc_drawRect(this, drawRect$forViewPrintFormatter$, area, formatter); }
    }
    
    private static final Selector encodeRestorableStateWithCoder$ = Selector.register("encodeRestorableStateWithCoder:");
    @Bridge private native static void objc_encodeRestorableState(UIView __self__, Selector __cmd__, NSCoder coder);
    @Bridge private native static void objc_encodeRestorableStateSuper(ObjCSuper __super__, Selector __cmd__, NSCoder coder);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/encodeRestorableStateWithCoder:">- (void) encodeRestorableStateWithCoder:(NSCoder *)coder</a>
     * @since Available in iOS 6.0 and later.
     */
    public void encodeRestorableState(NSCoder coder) {
        if (customClass) { objc_encodeRestorableStateSuper(getSuper(), encodeRestorableStateWithCoder$, coder); } else { objc_encodeRestorableState(this, encodeRestorableStateWithCoder$, coder); }
    }
    
    private static final Selector endEditing$ = Selector.register("endEditing:");
    @Bridge private native static boolean objc_endEditing(UIView __self__, Selector __cmd__, boolean force);
    @Bridge private native static boolean objc_endEditingSuper(ObjCSuper __super__, Selector __cmd__, boolean force);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/endEditing:">- (BOOL)endEditing:(BOOL)force</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean endEditing(boolean force) {
        if (customClass) { return objc_endEditingSuper(getSuper(), endEditing$, force); } else { return objc_endEditing(this, endEditing$, force); }
    }
    
    private static final Selector exchangeSubviewAtIndex$withSubviewAtIndex$ = Selector.register("exchangeSubviewAtIndex:withSubviewAtIndex:");
    @Bridge private native static void objc_exchangeSubview(UIView __self__, Selector __cmd__, int index1, int index2);
    @Bridge private native static void objc_exchangeSubviewSuper(ObjCSuper __super__, Selector __cmd__, int index1, int index2);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/exchangeSubviewAtIndex:withSubviewAtIndex:">- (void)exchangeSubviewAtIndex:(NSInteger)index1 withSubviewAtIndex:(NSInteger)index2</a>
     * @since Available in iOS 2.0 and later.
     */
    public void exchangeSubview(int index1, int index2) {
        if (customClass) { objc_exchangeSubviewSuper(getSuper(), exchangeSubviewAtIndex$withSubviewAtIndex$, index1, index2); } else { objc_exchangeSubview(this, exchangeSubviewAtIndex$withSubviewAtIndex$, index1, index2); }
    }
    
    private static final Selector exerciseAmbiguityInLayout = Selector.register("exerciseAmbiguityInLayout");
    @Bridge private native static void objc_exerciseAmbiguityInLayout(UIView __self__, Selector __cmd__);
    @Bridge private native static void objc_exerciseAmbiguityInLayoutSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/exerciseAmbiguityInLayout">- (void)exerciseAmbiguityInLayout</a>
     * @since Available in iOS 6.0 and later.
     */
    public void exerciseAmbiguityInLayout() {
        if (customClass) { objc_exerciseAmbiguityInLayoutSuper(getSuper(), exerciseAmbiguityInLayout); } else { objc_exerciseAmbiguityInLayout(this, exerciseAmbiguityInLayout); }
    }
    
    private static final Selector gestureRecognizerShouldBegin$ = Selector.register("gestureRecognizerShouldBegin:");
    @Bridge private native static boolean objc_gestureRecognizerShouldBegin(UIView __self__, Selector __cmd__, UIGestureRecognizer gestureRecognizer);
    @Bridge private native static boolean objc_gestureRecognizerShouldBeginSuper(ObjCSuper __super__, Selector __cmd__, UIGestureRecognizer gestureRecognizer);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/gestureRecognizerShouldBegin:">- (BOOL)gestureRecognizerShouldBegin:(UIGestureRecognizer *)gestureRecognizer</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean gestureRecognizerShouldBegin(UIGestureRecognizer gestureRecognizer) {
        if (customClass) { return objc_gestureRecognizerShouldBeginSuper(getSuper(), gestureRecognizerShouldBegin$, gestureRecognizer); } else { return objc_gestureRecognizerShouldBegin(this, gestureRecognizerShouldBegin$, gestureRecognizer); }
    }
    
    private static final Selector frameForAlignmentRect$ = Selector.register("frameForAlignmentRect:");
    @Bridge private native static @ByVal CGRect objc_getAlignmentRectFrame(UIView __self__, Selector __cmd__, @ByVal CGRect alignmentRect);
    @Bridge private native static @ByVal CGRect objc_getAlignmentRectFrameSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect alignmentRect);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/frameForAlignmentRect:">- (CGRect)frameForAlignmentRect:(CGRect)alignmentRect</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGRect getAlignmentRectFrame(CGRect alignmentRect) {
        if (customClass) { return objc_getAlignmentRectFrameSuper(getSuper(), frameForAlignmentRect$, alignmentRect); } else { return objc_getAlignmentRectFrame(this, frameForAlignmentRect$, alignmentRect); }
    }
    
    private static final Selector alignmentRectInsets = Selector.register("alignmentRectInsets");
    @Bridge private native static @ByVal UIEdgeInsets objc_getAlignmentRectInsets(UIView __self__, Selector __cmd__);
    @Bridge private native static @ByVal UIEdgeInsets objc_getAlignmentRectInsetsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/alignmentRectInsets">- (UIEdgeInsets)alignmentRectInsets</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIEdgeInsets getAlignmentRectInsets() {
        if (customClass) { return objc_getAlignmentRectInsetsSuper(getSuper(), alignmentRectInsets); } else { return objc_getAlignmentRectInsets(this, alignmentRectInsets); }
    }
    
    private static final Selector viewForBaselineLayout = Selector.register("viewForBaselineLayout");
    @Bridge private native static UIView objc_getBaselineLayoutView(UIView __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getBaselineLayoutViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/viewForBaselineLayout">- (UIView *)viewForBaselineLayout</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIView getBaselineLayoutView() {
        if (customClass) { return objc_getBaselineLayoutViewSuper(getSuper(), viewForBaselineLayout); } else { return objc_getBaselineLayoutView(this, viewForBaselineLayout); }
    }
    
    private static final Selector constraints = Selector.register("constraints");
    @Bridge private native static NSArray objc_getConstraints(UIView __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getConstraintsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/constraints">- (NSArray *)constraints</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSArray getConstraints() {
        if (customClass) { return objc_getConstraintsSuper(getSuper(), constraints); } else { return objc_getConstraints(this, constraints); }
    }
    
    private static final Selector constraintsAffectingLayoutForAxis$ = Selector.register("constraintsAffectingLayoutForAxis:");
    @Bridge private native static NSArray objc_getConstraintsAffectingLayout(UIView __self__, Selector __cmd__, UILayoutConstraintAxis axis);
    @Bridge private native static NSArray objc_getConstraintsAffectingLayoutSuper(ObjCSuper __super__, Selector __cmd__, UILayoutConstraintAxis axis);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/constraintsAffectingLayoutForAxis:">- (NSArray *)constraintsAffectingLayoutForAxis:(UILayoutConstraintAxis)axis</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSArray getConstraintsAffectingLayout(UILayoutConstraintAxis axis) {
        if (customClass) { return objc_getConstraintsAffectingLayoutSuper(getSuper(), constraintsAffectingLayoutForAxis$, axis); } else { return objc_getConstraintsAffectingLayout(this, constraintsAffectingLayoutForAxis$, axis); }
    }
    
    private static final Selector contentCompressionResistancePriorityForAxis$ = Selector.register("contentCompressionResistancePriorityForAxis:");
    @Bridge private native static UILayoutPriority objc_getContentCompressionResistancePriority(UIView __self__, Selector __cmd__, UILayoutConstraintAxis axis);
    @Bridge private native static UILayoutPriority objc_getContentCompressionResistancePrioritySuper(ObjCSuper __super__, Selector __cmd__, UILayoutConstraintAxis axis);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/contentCompressionResistancePriorityForAxis:">- (UILayoutPriority)contentCompressionResistancePriorityForAxis:(UILayoutConstraintAxis)axis</a>
     * @since Available in iOS 6.0 and later.
     */
    public UILayoutPriority getContentCompressionResistancePriority(UILayoutConstraintAxis axis) {
        if (customClass) { return objc_getContentCompressionResistancePrioritySuper(getSuper(), contentCompressionResistancePriorityForAxis$, axis); } else { return objc_getContentCompressionResistancePriority(this, contentCompressionResistancePriorityForAxis$, axis); }
    }
    
    private static final Selector contentHuggingPriorityForAxis$ = Selector.register("contentHuggingPriorityForAxis:");
    @Bridge private native static UILayoutPriority objc_getContentHuggingPriority(UIView __self__, Selector __cmd__, UILayoutConstraintAxis axis);
    @Bridge private native static UILayoutPriority objc_getContentHuggingPrioritySuper(ObjCSuper __super__, Selector __cmd__, UILayoutConstraintAxis axis);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/contentHuggingPriorityForAxis:">- (UILayoutPriority)contentHuggingPriorityForAxis:(UILayoutConstraintAxis)axis</a>
     * @since Available in iOS 6.0 and later.
     */
    public UILayoutPriority getContentHuggingPriority(UILayoutConstraintAxis axis) {
        if (customClass) { return objc_getContentHuggingPrioritySuper(getSuper(), contentHuggingPriorityForAxis$, axis); } else { return objc_getContentHuggingPriority(this, contentHuggingPriorityForAxis$, axis); }
    }
    
    private static final Selector alignmentRectForFrame$ = Selector.register("alignmentRectForFrame:");
    @Bridge private native static @ByVal CGRect objc_getFrameAlignmentRect(UIView __self__, Selector __cmd__, @ByVal CGRect frame);
    @Bridge private native static @ByVal CGRect objc_getFrameAlignmentRectSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect frame);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/alignmentRectForFrame:">- (CGRect)alignmentRectForFrame:(CGRect)frame</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGRect getFrameAlignmentRect(CGRect frame) {
        if (customClass) { return objc_getFrameAlignmentRectSuper(getSuper(), alignmentRectForFrame$, frame); } else { return objc_getFrameAlignmentRect(this, alignmentRectForFrame$, frame); }
    }
    
    private static final Selector intrinsicContentSize = Selector.register("intrinsicContentSize");
    @Bridge private native static @ByVal CGSize objc_getIntrinsicContentSize(UIView __self__, Selector __cmd__);
    @Bridge private native static @ByVal CGSize objc_getIntrinsicContentSizeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/intrinsicContentSize">- (CGSize)intrinsicContentSize</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGSize getIntrinsicContentSize() {
        if (customClass) { return objc_getIntrinsicContentSizeSuper(getSuper(), intrinsicContentSize); } else { return objc_getIntrinsicContentSize(this, intrinsicContentSize); }
    }
    
    private static final Selector sizeThatFits$ = Selector.register("sizeThatFits:");
    @Bridge private native static @ByVal CGSize objc_getSizeThatFits(UIView __self__, Selector __cmd__, @ByVal CGSize size);
    @Bridge private native static @ByVal CGSize objc_getSizeThatFitsSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGSize size);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/sizeThatFits:">- (CGSize)sizeThatFits:(CGSize)size</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGSize getSizeThatFits(CGSize size) {
        if (customClass) { return objc_getSizeThatFitsSuper(getSuper(), sizeThatFits$, size); } else { return objc_getSizeThatFits(this, sizeThatFits$, size); }
    }
    
    private static final Selector systemLayoutSizeFittingSize$ = Selector.register("systemLayoutSizeFittingSize:");
    @Bridge private native static @ByVal CGSize objc_getSystemLayoutSizeFittingSize(UIView __self__, Selector __cmd__, @ByVal CGSize targetSize);
    @Bridge private native static @ByVal CGSize objc_getSystemLayoutSizeFittingSizeSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGSize targetSize);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/systemLayoutSizeFittingSize:">- (CGSize)systemLayoutSizeFittingSize:(CGSize)targetSize</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGSize getSystemLayoutSizeFittingSize(CGSize targetSize) {
        if (customClass) { return objc_getSystemLayoutSizeFittingSizeSuper(getSuper(), systemLayoutSizeFittingSize$, targetSize); } else { return objc_getSystemLayoutSizeFittingSize(this, systemLayoutSizeFittingSize$, targetSize); }
    }
    
    private static final Selector viewPrintFormatter = Selector.register("viewPrintFormatter");
    @Bridge private native static UIViewPrintFormatter objc_getViewPrintFormatter(UIView __self__, Selector __cmd__);
    @Bridge private native static UIViewPrintFormatter objc_getViewPrintFormatterSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/viewPrintFormatter">- (UIViewPrintFormatter *)viewPrintFormatter</a>
     * @since Available in iOS 4.2 and later.
     */
    public UIViewPrintFormatter getViewPrintFormatter() {
        if (customClass) { return objc_getViewPrintFormatterSuper(getSuper(), viewPrintFormatter); } else { return objc_getViewPrintFormatter(this, viewPrintFormatter); }
    }
    
    private static final Selector viewWithTag$ = Selector.register("viewWithTag:");
    @Bridge private native static UIView objc_getViewWithTag(UIView __self__, Selector __cmd__, int tag);
    @Bridge private native static UIView objc_getViewWithTagSuper(ObjCSuper __super__, Selector __cmd__, int tag);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/viewWithTag:">- (UIView *)viewWithTag:(NSInteger)tag</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getViewWithTag(int tag) {
        if (customClass) { return objc_getViewWithTagSuper(getSuper(), viewWithTag$, tag); } else { return objc_getViewWithTag(this, viewWithTag$, tag); }
    }
    
    private static final Selector hasAmbiguousLayout = Selector.register("hasAmbiguousLayout");
    @Bridge private native static boolean objc_hasAmbiguousLayout(UIView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_hasAmbiguousLayoutSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/hasAmbiguousLayout">- (BOOL)hasAmbiguousLayout</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean hasAmbiguousLayout() {
        if (customClass) { return objc_hasAmbiguousLayoutSuper(getSuper(), hasAmbiguousLayout); } else { return objc_hasAmbiguousLayout(this, hasAmbiguousLayout); }
    }
    
    private static final Selector hitTest$withEvent$ = Selector.register("hitTest:withEvent:");
    @Bridge private native static UIView objc_hitTest(UIView __self__, Selector __cmd__, @ByVal CGPoint point, UIEvent event);
    @Bridge private native static UIView objc_hitTestSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint point, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/hitTest:withEvent:">- (UIView *)hitTest:(CGPoint)point withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView hitTest(CGPoint point, UIEvent event) {
        if (customClass) { return objc_hitTestSuper(getSuper(), hitTest$withEvent$, point, event); } else { return objc_hitTest(this, hitTest$withEvent$, point, event); }
    }
    
    private static final Selector insertSubview$atIndex$ = Selector.register("insertSubview:atIndex:");
    @Bridge private native static void objc_insertSubview(UIView __self__, Selector __cmd__, UIView view, int index);
    @Bridge private native static void objc_insertSubviewSuper(ObjCSuper __super__, Selector __cmd__, UIView view, int index);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/insertSubview:atIndex:">- (void)insertSubview:(UIView *)view atIndex:(NSInteger)index</a>
     * @since Available in iOS 2.0 and later.
     */
    public void insertSubview(UIView view, int index) {
        if (customClass) { objc_insertSubviewSuper(getSuper(), insertSubview$atIndex$, view, index); } else { objc_insertSubview(this, insertSubview$atIndex$, view, index); }
    }
    
    private static final Selector insertSubview$aboveSubview$ = Selector.register("insertSubview:aboveSubview:");
    @Bridge private native static void objc_insertSubviewAbove(UIView __self__, Selector __cmd__, UIView view, UIView siblingSubview);
    @Bridge private native static void objc_insertSubviewAboveSuper(ObjCSuper __super__, Selector __cmd__, UIView view, UIView siblingSubview);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/insertSubview:aboveSubview:">- (void)insertSubview:(UIView *)view aboveSubview:(UIView *)siblingSubview</a>
     * @since Available in iOS 2.0 and later.
     */
    public void insertSubviewAbove(UIView view, UIView siblingSubview) {
        if (customClass) { objc_insertSubviewAboveSuper(getSuper(), insertSubview$aboveSubview$, view, siblingSubview); } else { objc_insertSubviewAbove(this, insertSubview$aboveSubview$, view, siblingSubview); }
    }
    
    private static final Selector insertSubview$belowSubview$ = Selector.register("insertSubview:belowSubview:");
    @Bridge private native static void objc_insertSubviewBelow(UIView __self__, Selector __cmd__, UIView view, UIView siblingSubview);
    @Bridge private native static void objc_insertSubviewBelowSuper(ObjCSuper __super__, Selector __cmd__, UIView view, UIView siblingSubview);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/insertSubview:belowSubview:">- (void)insertSubview:(UIView *)view belowSubview:(UIView *)siblingSubview</a>
     * @since Available in iOS 2.0 and later.
     */
    public void insertSubviewBelow(UIView view, UIView siblingSubview) {
        if (customClass) { objc_insertSubviewBelowSuper(getSuper(), insertSubview$belowSubview$, view, siblingSubview); } else { objc_insertSubviewBelow(this, insertSubview$belowSubview$, view, siblingSubview); }
    }
    
    private static final Selector invalidateIntrinsicContentSize = Selector.register("invalidateIntrinsicContentSize");
    @Bridge private native static void objc_invalidateIntrinsicContentSize(UIView __self__, Selector __cmd__);
    @Bridge private native static void objc_invalidateIntrinsicContentSizeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/invalidateIntrinsicContentSize">- (void)invalidateIntrinsicContentSize</a>
     * @since Available in iOS 6.0 and later.
     */
    public void invalidateIntrinsicContentSize() {
        if (customClass) { objc_invalidateIntrinsicContentSizeSuper(getSuper(), invalidateIntrinsicContentSize); } else { objc_invalidateIntrinsicContentSize(this, invalidateIntrinsicContentSize); }
    }
    
    private static final Selector isDescendantOfView$ = Selector.register("isDescendantOfView:");
    @Bridge private native static boolean objc_isDescendantOf(UIView __self__, Selector __cmd__, UIView view);
    @Bridge private native static boolean objc_isDescendantOfSuper(ObjCSuper __super__, Selector __cmd__, UIView view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/isDescendantOfView:">- (BOOL)isDescendantOfView:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isDescendantOf(UIView view) {
        if (customClass) { return objc_isDescendantOfSuper(getSuper(), isDescendantOfView$, view); } else { return objc_isDescendantOf(this, isDescendantOfView$, view); }
    }
    
    private static final Selector translatesAutoresizingMaskIntoConstraints = Selector.register("translatesAutoresizingMaskIntoConstraints");
    @Bridge private native static boolean objc_isTranslatesAutoresizingMaskIntoConstraints(UIView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isTranslatesAutoresizingMaskIntoConstraintsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/translatesAutoresizingMaskIntoConstraints">- (BOOL)translatesAutoresizingMaskIntoConstraints</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean isTranslatesAutoresizingMaskIntoConstraints() {
        if (customClass) { return objc_isTranslatesAutoresizingMaskIntoConstraintsSuper(getSuper(), translatesAutoresizingMaskIntoConstraints); } else { return objc_isTranslatesAutoresizingMaskIntoConstraints(this, translatesAutoresizingMaskIntoConstraints); }
    }
    
    private static final Selector layoutIfNeeded = Selector.register("layoutIfNeeded");
    @Bridge private native static void objc_layoutIfNeeded(UIView __self__, Selector __cmd__);
    @Bridge private native static void objc_layoutIfNeededSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/layoutIfNeeded">- (void)layoutIfNeeded</a>
     * @since Available in iOS 2.0 and later.
     */
    public void layoutIfNeeded() {
        if (customClass) { objc_layoutIfNeededSuper(getSuper(), layoutIfNeeded); } else { objc_layoutIfNeeded(this, layoutIfNeeded); }
    }
    
    private static final Selector layoutSubviews = Selector.register("layoutSubviews");
    @Bridge private native static void objc_layoutSubviews(UIView __self__, Selector __cmd__);
    @Bridge private native static void objc_layoutSubviewsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/layoutSubviews">- (void)layoutSubviews</a>
     * @since Available in iOS 2.0 and later.
     */
    public void layoutSubviews() {
        if (customClass) { objc_layoutSubviewsSuper(getSuper(), layoutSubviews); } else { objc_layoutSubviews(this, layoutSubviews); }
    }
    
    private static final Selector needsUpdateConstraints = Selector.register("needsUpdateConstraints");
    @Bridge private native static boolean objc_needsUpdateConstraints(UIView __self__, Selector __cmd__);
    @Bridge private native static boolean objc_needsUpdateConstraintsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/needsUpdateConstraints">- (BOOL)needsUpdateConstraints</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean needsUpdateConstraints() {
        if (customClass) { return objc_needsUpdateConstraintsSuper(getSuper(), needsUpdateConstraints); } else { return objc_needsUpdateConstraints(this, needsUpdateConstraints); }
    }
    
    private static final Selector pointInside$withEvent$ = Selector.register("pointInside:withEvent:");
    @Bridge private native static boolean objc_pointInside(UIView __self__, Selector __cmd__, @ByVal CGPoint point, UIEvent event);
    @Bridge private native static boolean objc_pointInsideSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGPoint point, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/pointInside:withEvent:">- (BOOL)pointInside:(CGPoint)point withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean pointInside(CGPoint point, UIEvent event) {
        if (customClass) { return objc_pointInsideSuper(getSuper(), pointInside$withEvent$, point, event); } else { return objc_pointInside(this, pointInside$withEvent$, point, event); }
    }
    
    private static final Selector removeConstraint$ = Selector.register("removeConstraint:");
    @Bridge private native static void objc_removeConstraint(UIView __self__, Selector __cmd__, NSLayoutConstraint constraint);
    @Bridge private native static void objc_removeConstraintSuper(ObjCSuper __super__, Selector __cmd__, NSLayoutConstraint constraint);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/removeConstraint:">- (void)removeConstraint:(NSLayoutConstraint *)constraint</a>
     * @since Available in iOS 6.0 and later.
     */
    public void removeConstraint(NSLayoutConstraint constraint) {
        if (customClass) { objc_removeConstraintSuper(getSuper(), removeConstraint$, constraint); } else { objc_removeConstraint(this, removeConstraint$, constraint); }
    }
    
    private static final Selector removeConstraints$ = Selector.register("removeConstraints:");
    @Bridge private native static void objc_removeConstraints(UIView __self__, Selector __cmd__, NSArray constraints);
    @Bridge private native static void objc_removeConstraintsSuper(ObjCSuper __super__, Selector __cmd__, NSArray constraints);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/removeConstraints:">- (void)removeConstraints:(NSArray *)constraints</a>
     * @since Available in iOS 6.0 and later.
     */
    public void removeConstraints(NSArray constraints) {
        if (customClass) { objc_removeConstraintsSuper(getSuper(), removeConstraints$, constraints); } else { objc_removeConstraints(this, removeConstraints$, constraints); }
    }
    
    private static final Selector removeFromSuperview = Selector.register("removeFromSuperview");
    @Bridge private native static void objc_removeFromSuperview(UIView __self__, Selector __cmd__);
    @Bridge private native static void objc_removeFromSuperviewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/removeFromSuperview">- (void)removeFromSuperview</a>
     * @since Available in iOS 2.0 and later.
     */
    public void removeFromSuperview() {
        if (customClass) { objc_removeFromSuperviewSuper(getSuper(), removeFromSuperview); } else { objc_removeFromSuperview(this, removeFromSuperview); }
    }
    
    private static final Selector removeGestureRecognizer$ = Selector.register("removeGestureRecognizer:");
    @Bridge private native static void objc_removeGestureRecognizer(UIView __self__, Selector __cmd__, UIGestureRecognizer gestureRecognizer);
    @Bridge private native static void objc_removeGestureRecognizerSuper(ObjCSuper __super__, Selector __cmd__, UIGestureRecognizer gestureRecognizer);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/removeGestureRecognizer:">- (void)removeGestureRecognizer:(UIGestureRecognizer *)gestureRecognizer</a>
     * @since Available in iOS 3.2 and later.
     */
    public void removeGestureRecognizer(UIGestureRecognizer gestureRecognizer) {
        if (customClass) { objc_removeGestureRecognizerSuper(getSuper(), removeGestureRecognizer$, gestureRecognizer); } else { objc_removeGestureRecognizer(this, removeGestureRecognizer$, gestureRecognizer); }
    }
    
    private static final Selector sizeToFit = Selector.register("sizeToFit");
    @Bridge private native static void objc_resizeToFit(UIView __self__, Selector __cmd__);
    @Bridge private native static void objc_resizeToFitSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/sizeToFit">- (void)sizeToFit</a>
     * @since Available in iOS 2.0 and later.
     */
    public void resizeToFit() {
        if (customClass) { objc_resizeToFitSuper(getSuper(), sizeToFit); } else { objc_resizeToFit(this, sizeToFit); }
    }
    
    private static final Selector sendSubviewToBack$ = Selector.register("sendSubviewToBack:");
    @Bridge private native static void objc_sendSubviewToBack(UIView __self__, Selector __cmd__, UIView view);
    @Bridge private native static void objc_sendSubviewToBackSuper(ObjCSuper __super__, Selector __cmd__, UIView view);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/sendSubviewToBack:">- (void)sendSubviewToBack:(UIView *)view</a>
     * @since Available in iOS 2.0 and later.
     */
    public void sendSubviewToBack(UIView view) {
        if (customClass) { objc_sendSubviewToBackSuper(getSuper(), sendSubviewToBack$, view); } else { objc_sendSubviewToBack(this, sendSubviewToBack$, view); }
    }
    
    private static final Selector setContentCompressionResistancePriority$forAxis$ = Selector.register("setContentCompressionResistancePriority:forAxis:");
    @Bridge private native static void objc_setContentCompressionResistancePriority(UIView __self__, Selector __cmd__, UILayoutPriority priority, UILayoutConstraintAxis axis);
    @Bridge private native static void objc_setContentCompressionResistancePrioritySuper(ObjCSuper __super__, Selector __cmd__, UILayoutPriority priority, UILayoutConstraintAxis axis);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setContentCompressionResistancePriority:forAxis:">- (void)setContentCompressionResistancePriority:(UILayoutPriority)priority forAxis:(UILayoutConstraintAxis)axis</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setContentCompressionResistancePriority(UILayoutPriority priority, UILayoutConstraintAxis axis) {
        if (customClass) { objc_setContentCompressionResistancePrioritySuper(getSuper(), setContentCompressionResistancePriority$forAxis$, priority, axis); } else { objc_setContentCompressionResistancePriority(this, setContentCompressionResistancePriority$forAxis$, priority, axis); }
    }
    
    private static final Selector setContentHuggingPriority$forAxis$ = Selector.register("setContentHuggingPriority:forAxis:");
    @Bridge private native static void objc_setContentHuggingPriority(UIView __self__, Selector __cmd__, UILayoutPriority priority, UILayoutConstraintAxis axis);
    @Bridge private native static void objc_setContentHuggingPrioritySuper(ObjCSuper __super__, Selector __cmd__, UILayoutPriority priority, UILayoutConstraintAxis axis);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setContentHuggingPriority:forAxis:">- (void)setContentHuggingPriority:(UILayoutPriority)priority forAxis:(UILayoutConstraintAxis)axis</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setContentHuggingPriority(UILayoutPriority priority, UILayoutConstraintAxis axis) {
        if (customClass) { objc_setContentHuggingPrioritySuper(getSuper(), setContentHuggingPriority$forAxis$, priority, axis); } else { objc_setContentHuggingPriority(this, setContentHuggingPriority$forAxis$, priority, axis); }
    }
    
    private static final Selector setNeedsDisplay = Selector.register("setNeedsDisplay");
    @Bridge private native static void objc_setNeedsDisplay(UIView __self__, Selector __cmd__);
    @Bridge private native static void objc_setNeedsDisplaySuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setNeedsDisplay">- (void)setNeedsDisplay</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setNeedsDisplay() {
        if (customClass) { objc_setNeedsDisplaySuper(getSuper(), setNeedsDisplay); } else { objc_setNeedsDisplay(this, setNeedsDisplay); }
    }
    
    private static final Selector setNeedsDisplayInRect$ = Selector.register("setNeedsDisplayInRect:");
    @Bridge private native static void objc_setNeedsDisplay(UIView __self__, Selector __cmd__, @ByVal CGRect invalidRect);
    @Bridge private native static void objc_setNeedsDisplaySuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect invalidRect);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setNeedsDisplayInRect:">- (void)setNeedsDisplayInRect:(CGRect)invalidRect</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setNeedsDisplay(CGRect invalidRect) {
        if (customClass) { objc_setNeedsDisplaySuper(getSuper(), setNeedsDisplayInRect$, invalidRect); } else { objc_setNeedsDisplay(this, setNeedsDisplayInRect$, invalidRect); }
    }
    
    private static final Selector setNeedsLayout = Selector.register("setNeedsLayout");
    @Bridge private native static void objc_setNeedsLayout(UIView __self__, Selector __cmd__);
    @Bridge private native static void objc_setNeedsLayoutSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setNeedsLayout">- (void)setNeedsLayout</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setNeedsLayout() {
        if (customClass) { objc_setNeedsLayoutSuper(getSuper(), setNeedsLayout); } else { objc_setNeedsLayout(this, setNeedsLayout); }
    }
    
    private static final Selector setNeedsUpdateConstraints = Selector.register("setNeedsUpdateConstraints");
    @Bridge private native static void objc_setNeedsUpdateConstraints(UIView __self__, Selector __cmd__);
    @Bridge private native static void objc_setNeedsUpdateConstraintsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setNeedsUpdateConstraints">- (void)setNeedsUpdateConstraints</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setNeedsUpdateConstraints() {
        if (customClass) { objc_setNeedsUpdateConstraintsSuper(getSuper(), setNeedsUpdateConstraints); } else { objc_setNeedsUpdateConstraints(this, setNeedsUpdateConstraints); }
    }
    
    private static final Selector setTranslatesAutoresizingMaskIntoConstraints$ = Selector.register("setTranslatesAutoresizingMaskIntoConstraints:");
    @Bridge private native static void objc_setTranslatesAutoresizingMaskIntoConstraints(UIView __self__, Selector __cmd__, boolean flag);
    @Bridge private native static void objc_setTranslatesAutoresizingMaskIntoConstraintsSuper(ObjCSuper __super__, Selector __cmd__, boolean flag);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/setTranslatesAutoresizingMaskIntoConstraints:">- (void)setTranslatesAutoresizingMaskIntoConstraints:(BOOL)flag</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setTranslatesAutoresizingMaskIntoConstraints(boolean flag) {
        if (customClass) { objc_setTranslatesAutoresizingMaskIntoConstraintsSuper(getSuper(), setTranslatesAutoresizingMaskIntoConstraints$, flag); } else { objc_setTranslatesAutoresizingMaskIntoConstraints(this, setTranslatesAutoresizingMaskIntoConstraints$, flag); }
    }
    
    private static final Selector updateConstraints = Selector.register("updateConstraints");
    @Bridge private native static void objc_updateConstraints(UIView __self__, Selector __cmd__);
    @Bridge private native static void objc_updateConstraintsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/updateConstraints">- (void)updateConstraints</a>
     * @since Available in iOS 6.0 and later.
     */
    public void updateConstraints() {
        if (customClass) { objc_updateConstraintsSuper(getSuper(), updateConstraints); } else { objc_updateConstraints(this, updateConstraints); }
    }
    
    private static final Selector updateConstraintsIfNeeded = Selector.register("updateConstraintsIfNeeded");
    @Bridge private native static void objc_updateConstraintsIfNeeded(UIView __self__, Selector __cmd__);
    @Bridge private native static void objc_updateConstraintsIfNeededSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/updateConstraintsIfNeeded">- (void)updateConstraintsIfNeeded</a>
     * @since Available in iOS 6.0 and later.
     */
    public void updateConstraintsIfNeeded() {
        if (customClass) { objc_updateConstraintsIfNeededSuper(getSuper(), updateConstraintsIfNeeded); } else { objc_updateConstraintsIfNeeded(this, updateConstraintsIfNeeded); }
    }
    
    private static final Selector willMoveToSuperview$ = Selector.register("willMoveToSuperview:");
    @Bridge private native static void objc_willMoveToSuperview(UIView __self__, Selector __cmd__, UIView newSuperview);
    @Bridge private native static void objc_willMoveToSuperviewSuper(ObjCSuper __super__, Selector __cmd__, UIView newSuperview);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/willMoveToSuperview:">- (void)willMoveToSuperview:(UIView *)newSuperview</a>
     * @since Available in iOS 2.0 and later.
     */
    public void willMoveToSuperview(UIView newSuperview) {
        if (customClass) { objc_willMoveToSuperviewSuper(getSuper(), willMoveToSuperview$, newSuperview); } else { objc_willMoveToSuperview(this, willMoveToSuperview$, newSuperview); }
    }
    
    private static final Selector willMoveToWindow$ = Selector.register("willMoveToWindow:");
    @Bridge private native static void objc_willMoveToWindow(UIView __self__, Selector __cmd__, UIWindow newWindow);
    @Bridge private native static void objc_willMoveToWindowSuper(ObjCSuper __super__, Selector __cmd__, UIWindow newWindow);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/willMoveToWindow:">- (void)willMoveToWindow:(UIWindow *)newWindow</a>
     * @since Available in iOS 2.0 and later.
     */
    public void willMoveToWindow(UIWindow newWindow) {
        if (customClass) { objc_willMoveToWindowSuper(getSuper(), willMoveToWindow$, newWindow); } else { objc_willMoveToWindow(this, willMoveToWindow$, newWindow); }
    }
    
    private static final Selector willRemoveSubview$ = Selector.register("willRemoveSubview:");
    @Bridge private native static void objc_willRemoveSubview(UIView __self__, Selector __cmd__, UIView subview);
    @Bridge private native static void objc_willRemoveSubviewSuper(ObjCSuper __super__, Selector __cmd__, UIView subview);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIView_Class/UIView/UIView.html#//apple_ref/occ/instm/UIView/willRemoveSubview:">- (void)willRemoveSubview:(UIView *)subview</a>
     * @since Available in iOS 2.0 and later.
     */
    public void willRemoveSubview(UIView subview) {
        if (customClass) { objc_willRemoveSubviewSuper(getSuper(), willRemoveSubview$, subview); } else { objc_willRemoveSubview(this, willRemoveSubview$, subview); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("alpha") public static float getAlpha(UIView __self__, Selector __cmd__) { return __self__.getAlpha(); }
        @Callback @BindSelector("setAlpha:") public static void setAlpha(UIView __self__, Selector __cmd__, float alpha) { __self__.setAlpha(alpha); }
        @Callback @BindSelector("autoresizesSubviews") public static boolean isAutoresizesSubviews(UIView __self__, Selector __cmd__) { return __self__.isAutoresizesSubviews(); }
        @Callback @BindSelector("setAutoresizesSubviews:") public static void setAutoresizesSubviews(UIView __self__, Selector __cmd__, boolean autoresizesSubviews) { __self__.setAutoresizesSubviews(autoresizesSubviews); }
        @Callback @BindSelector("autoresizingMask") public static UIViewAutoresizing getAutoresizingMask(UIView __self__, Selector __cmd__) { return __self__.getAutoresizingMask(); }
        @Callback @BindSelector("setAutoresizingMask:") public static void setAutoresizingMask(UIView __self__, Selector __cmd__, UIViewAutoresizing autoresizingMask) { __self__.setAutoresizingMask(autoresizingMask); }
        @Callback @BindSelector("backgroundColor") public static UIColor getBackgroundColor(UIView __self__, Selector __cmd__) { return __self__.getBackgroundColor(); }
        @Callback @BindSelector("setBackgroundColor:") public static void setBackgroundColor(UIView __self__, Selector __cmd__, UIColor backgroundColor) { __self__.setBackgroundColor(backgroundColor); }
        @Callback @BindSelector("bounds") public static @ByVal CGRect getBounds(UIView __self__, Selector __cmd__) { return __self__.getBounds(); }
        @Callback @BindSelector("setBounds:") public static void setBounds(UIView __self__, Selector __cmd__, @ByVal CGRect bounds) { __self__.setBounds(bounds); }
        @Callback @BindSelector("center") public static @ByVal CGPoint getCenter(UIView __self__, Selector __cmd__) { return __self__.getCenter(); }
        @Callback @BindSelector("setCenter:") public static void setCenter(UIView __self__, Selector __cmd__, @ByVal CGPoint center) { __self__.setCenter(center); }
        @Callback @BindSelector("clearsContextBeforeDrawing") public static boolean isClearsContextBeforeDrawing(UIView __self__, Selector __cmd__) { return __self__.isClearsContextBeforeDrawing(); }
        @Callback @BindSelector("setClearsContextBeforeDrawing:") public static void setClearsContextBeforeDrawing(UIView __self__, Selector __cmd__, boolean clearsContextBeforeDrawing) { __self__.setClearsContextBeforeDrawing(clearsContextBeforeDrawing); }
        @Callback @BindSelector("clipsToBounds") public static boolean isClipsToBounds(UIView __self__, Selector __cmd__) { return __self__.isClipsToBounds(); }
        @Callback @BindSelector("setClipsToBounds:") public static void setClipsToBounds(UIView __self__, Selector __cmd__, boolean clipsToBounds) { __self__.setClipsToBounds(clipsToBounds); }
        @Callback @BindSelector("contentMode") public static UIViewContentMode getContentMode(UIView __self__, Selector __cmd__) { return __self__.getContentMode(); }
        @Callback @BindSelector("setContentMode:") public static void setContentMode(UIView __self__, Selector __cmd__, UIViewContentMode contentMode) { __self__.setContentMode(contentMode); }
        @Callback @BindSelector("contentScaleFactor") public static float getContentScaleFactor(UIView __self__, Selector __cmd__) { return __self__.getContentScaleFactor(); }
        @Callback @BindSelector("setContentScaleFactor:") public static void setContentScaleFactor(UIView __self__, Selector __cmd__, float contentScaleFactor) { __self__.setContentScaleFactor(contentScaleFactor); }
        @Callback @BindSelector("isExclusiveTouch") public static boolean isExclusiveTouch(UIView __self__, Selector __cmd__) { return __self__.isExclusiveTouch(); }
        @Callback @BindSelector("setExclusiveTouch:") public static void setExclusiveTouch(UIView __self__, Selector __cmd__, boolean exclusiveTouch) { __self__.setExclusiveTouch(exclusiveTouch); }
        @Callback @BindSelector("frame") public static @ByVal CGRect getFrame(UIView __self__, Selector __cmd__) { return __self__.getFrame(); }
        @Callback @BindSelector("setFrame:") public static void setFrame(UIView __self__, Selector __cmd__, @ByVal CGRect frame) { __self__.setFrame(frame); }
        @Callback @BindSelector("gestureRecognizers") public static NSArray getGestureRecognizers(UIView __self__, Selector __cmd__) { return __self__.getGestureRecognizers(); }
        @Callback @BindSelector("setGestureRecognizers:") public static void setGestureRecognizers(UIView __self__, Selector __cmd__, NSArray gestureRecognizers) { __self__.setGestureRecognizers(gestureRecognizers); }
        @Callback @BindSelector("isHidden") public static boolean isHidden(UIView __self__, Selector __cmd__) { return __self__.isHidden(); }
        @Callback @BindSelector("setHidden:") public static void setHidden(UIView __self__, Selector __cmd__, boolean hidden) { __self__.setHidden(hidden); }
        @Callback @BindSelector("layer") public static CALayer getLayer(UIView __self__, Selector __cmd__) { return __self__.getLayer(); }
        @Callback @BindSelector("isMultipleTouchEnabled") public static boolean isMultipleTouchEnabled(UIView __self__, Selector __cmd__) { return __self__.isMultipleTouchEnabled(); }
        @Callback @BindSelector("setMultipleTouchEnabled:") public static void setMultipleTouchEnabled(UIView __self__, Selector __cmd__, boolean multipleTouchEnabled) { __self__.setMultipleTouchEnabled(multipleTouchEnabled); }
        @Callback @BindSelector("isOpaque") public static boolean isOpaque(UIView __self__, Selector __cmd__) { return __self__.isOpaque(); }
        @Callback @BindSelector("setOpaque:") public static void setOpaque(UIView __self__, Selector __cmd__, boolean opaque) { __self__.setOpaque(opaque); }
        @Callback @BindSelector("restorationIdentifier") public static String getRestorationIdentifier(UIView __self__, Selector __cmd__) { return __self__.getRestorationIdentifier(); }
        @Callback @BindSelector("setRestorationIdentifier:") public static void setRestorationIdentifier(UIView __self__, Selector __cmd__, String restorationIdentifier) { __self__.setRestorationIdentifier(restorationIdentifier); }
        @Callback @BindSelector("subviews") public static NSArray getSubviews(UIView __self__, Selector __cmd__) { return __self__.getSubviews(); }
        @Callback @BindSelector("superview") public static UIView getSuperview(UIView __self__, Selector __cmd__) { return __self__.getSuperview(); }
        @Callback @BindSelector("tag") public static int getTag(UIView __self__, Selector __cmd__) { return __self__.getTag(); }
        @Callback @BindSelector("setTag:") public static void setTag(UIView __self__, Selector __cmd__, int tag) { __self__.setTag(tag); }
        @Callback @BindSelector("transform") public static @ByVal CGAffineTransform getTransform(UIView __self__, Selector __cmd__) { return __self__.getTransform(); }
        @Callback @BindSelector("setTransform:") public static void setTransform(UIView __self__, Selector __cmd__, @ByVal CGAffineTransform transform) { __self__.setTransform(transform); }
        @Callback @BindSelector("isUserInteractionEnabled") public static boolean isUserInteractionEnabled(UIView __self__, Selector __cmd__) { return __self__.isUserInteractionEnabled(); }
        @Callback @BindSelector("setUserInteractionEnabled:") public static void setUserInteractionEnabled(UIView __self__, Selector __cmd__, boolean userInteractionEnabled) { __self__.setUserInteractionEnabled(userInteractionEnabled); }
        @Callback @BindSelector("window") public static UIWindow getWindow(UIView __self__, Selector __cmd__) { return __self__.getWindow(); }
        @Callback @BindSelector("addConstraint:") public static void addConstraint(UIView __self__, Selector __cmd__, NSLayoutConstraint constraint) { __self__.addConstraint(constraint); }
        @Callback @BindSelector("addConstraints:") public static void addConstraints(UIView __self__, Selector __cmd__, NSArray constraints) { __self__.addConstraints(constraints); }
        @Callback @BindSelector("addGestureRecognizer:") public static void addGestureRecognizer(UIView __self__, Selector __cmd__, UIGestureRecognizer gestureRecognizer) { __self__.addGestureRecognizer(gestureRecognizer); }
        @Callback @BindSelector("addSubview:") public static void addSubview(UIView __self__, Selector __cmd__, UIView view) { __self__.addSubview(view); }
        @Callback @BindSelector("bringSubviewToFront:") public static void bringSubviewToFront(UIView __self__, Selector __cmd__, UIView view) { __self__.bringSubviewToFront(view); }
        @Callback @BindSelector("convertPoint:fromView:") public static @ByVal CGPoint convertPointFromView(UIView __self__, Selector __cmd__, @ByVal CGPoint point, UIView view) { return __self__.convertPointFromView(point, view); }
        @Callback @BindSelector("convertPoint:toView:") public static @ByVal CGPoint convertPointToView(UIView __self__, Selector __cmd__, @ByVal CGPoint point, UIView view) { return __self__.convertPointToView(point, view); }
        @Callback @BindSelector("convertRect:fromView:") public static @ByVal CGRect convertRectFromView(UIView __self__, Selector __cmd__, @ByVal CGRect rect, UIView view) { return __self__.convertRectFromView(rect, view); }
        @Callback @BindSelector("convertRect:toView:") public static @ByVal CGRect convertRectToView(UIView __self__, Selector __cmd__, @ByVal CGRect rect, UIView view) { return __self__.convertRectToView(rect, view); }
        @Callback @BindSelector("decodeRestorableStateWithCoder:") public static void decodeRestorableState(UIView __self__, Selector __cmd__, NSCoder coder) { __self__.decodeRestorableState(coder); }
        @Callback @BindSelector("didAddSubview:") public static void didAddSubview(UIView __self__, Selector __cmd__, UIView subview) { __self__.didAddSubview(subview); }
        @Callback @BindSelector("didMoveToSuperview") public static void didMoveToSuperview(UIView __self__, Selector __cmd__) { __self__.didMoveToSuperview(); }
        @Callback @BindSelector("didMoveToWindow") public static void didMoveToWindow(UIView __self__, Selector __cmd__) { __self__.didMoveToWindow(); }
        @Callback @BindSelector("drawRect:") public static void draw(UIView __self__, Selector __cmd__, @ByVal CGRect rect) { __self__.draw(rect); }
        @Callback @BindSelector("drawRect:forViewPrintFormatter:") public static void drawRect(UIView __self__, Selector __cmd__, @ByVal CGRect area, UIViewPrintFormatter formatter) { __self__.drawRect(area, formatter); }
        @Callback @BindSelector("encodeRestorableStateWithCoder:") public static void encodeRestorableState(UIView __self__, Selector __cmd__, NSCoder coder) { __self__.encodeRestorableState(coder); }
        @Callback @BindSelector("endEditing:") public static boolean endEditing(UIView __self__, Selector __cmd__, boolean force) { return __self__.endEditing(force); }
        @Callback @BindSelector("exchangeSubviewAtIndex:withSubviewAtIndex:") public static void exchangeSubview(UIView __self__, Selector __cmd__, int index1, int index2) { __self__.exchangeSubview(index1, index2); }
        @Callback @BindSelector("exerciseAmbiguityInLayout") public static void exerciseAmbiguityInLayout(UIView __self__, Selector __cmd__) { __self__.exerciseAmbiguityInLayout(); }
        @Callback @BindSelector("gestureRecognizerShouldBegin:") public static boolean gestureRecognizerShouldBegin(UIView __self__, Selector __cmd__, UIGestureRecognizer gestureRecognizer) { return __self__.gestureRecognizerShouldBegin(gestureRecognizer); }
        @Callback @BindSelector("frameForAlignmentRect:") public static @ByVal CGRect getAlignmentRectFrame(UIView __self__, Selector __cmd__, @ByVal CGRect alignmentRect) { return __self__.getAlignmentRectFrame(alignmentRect); }
        @Callback @BindSelector("alignmentRectInsets") public static @ByVal UIEdgeInsets getAlignmentRectInsets(UIView __self__, Selector __cmd__) { return __self__.getAlignmentRectInsets(); }
        @Callback @BindSelector("viewForBaselineLayout") public static UIView getBaselineLayoutView(UIView __self__, Selector __cmd__) { return __self__.getBaselineLayoutView(); }
        @Callback @BindSelector("constraints") public static NSArray getConstraints(UIView __self__, Selector __cmd__) { return __self__.getConstraints(); }
        @Callback @BindSelector("constraintsAffectingLayoutForAxis:") public static NSArray getConstraintsAffectingLayout(UIView __self__, Selector __cmd__, UILayoutConstraintAxis axis) { return __self__.getConstraintsAffectingLayout(axis); }
        @Callback @BindSelector("contentCompressionResistancePriorityForAxis:") public static UILayoutPriority getContentCompressionResistancePriority(UIView __self__, Selector __cmd__, UILayoutConstraintAxis axis) { return __self__.getContentCompressionResistancePriority(axis); }
        @Callback @BindSelector("contentHuggingPriorityForAxis:") public static UILayoutPriority getContentHuggingPriority(UIView __self__, Selector __cmd__, UILayoutConstraintAxis axis) { return __self__.getContentHuggingPriority(axis); }
        @Callback @BindSelector("alignmentRectForFrame:") public static @ByVal CGRect getFrameAlignmentRect(UIView __self__, Selector __cmd__, @ByVal CGRect frame) { return __self__.getFrameAlignmentRect(frame); }
        @Callback @BindSelector("intrinsicContentSize") public static @ByVal CGSize getIntrinsicContentSize(UIView __self__, Selector __cmd__) { return __self__.getIntrinsicContentSize(); }
        @Callback @BindSelector("sizeThatFits:") public static @ByVal CGSize getSizeThatFits(UIView __self__, Selector __cmd__, @ByVal CGSize size) { return __self__.getSizeThatFits(size); }
        @Callback @BindSelector("systemLayoutSizeFittingSize:") public static @ByVal CGSize getSystemLayoutSizeFittingSize(UIView __self__, Selector __cmd__, @ByVal CGSize targetSize) { return __self__.getSystemLayoutSizeFittingSize(targetSize); }
        @Callback @BindSelector("viewPrintFormatter") public static UIViewPrintFormatter getViewPrintFormatter(UIView __self__, Selector __cmd__) { return __self__.getViewPrintFormatter(); }
        @Callback @BindSelector("viewWithTag:") public static UIView getViewWithTag(UIView __self__, Selector __cmd__, int tag) { return __self__.getViewWithTag(tag); }
        @Callback @BindSelector("hasAmbiguousLayout") public static boolean hasAmbiguousLayout(UIView __self__, Selector __cmd__) { return __self__.hasAmbiguousLayout(); }
        @Callback @BindSelector("hitTest:withEvent:") public static UIView hitTest(UIView __self__, Selector __cmd__, @ByVal CGPoint point, UIEvent event) { return __self__.hitTest(point, event); }
        @Callback @BindSelector("insertSubview:atIndex:") public static void insertSubview(UIView __self__, Selector __cmd__, UIView view, int index) { __self__.insertSubview(view, index); }
        @Callback @BindSelector("insertSubview:aboveSubview:") public static void insertSubviewAbove(UIView __self__, Selector __cmd__, UIView view, UIView siblingSubview) { __self__.insertSubviewAbove(view, siblingSubview); }
        @Callback @BindSelector("insertSubview:belowSubview:") public static void insertSubviewBelow(UIView __self__, Selector __cmd__, UIView view, UIView siblingSubview) { __self__.insertSubviewBelow(view, siblingSubview); }
        @Callback @BindSelector("invalidateIntrinsicContentSize") public static void invalidateIntrinsicContentSize(UIView __self__, Selector __cmd__) { __self__.invalidateIntrinsicContentSize(); }
        @Callback @BindSelector("isDescendantOfView:") public static boolean isDescendantOf(UIView __self__, Selector __cmd__, UIView view) { return __self__.isDescendantOf(view); }
        @Callback @BindSelector("translatesAutoresizingMaskIntoConstraints") public static boolean isTranslatesAutoresizingMaskIntoConstraints(UIView __self__, Selector __cmd__) { return __self__.isTranslatesAutoresizingMaskIntoConstraints(); }
        @Callback @BindSelector("layoutIfNeeded") public static void layoutIfNeeded(UIView __self__, Selector __cmd__) { __self__.layoutIfNeeded(); }
        @Callback @BindSelector("layoutSubviews") public static void layoutSubviews(UIView __self__, Selector __cmd__) { __self__.layoutSubviews(); }
        @Callback @BindSelector("needsUpdateConstraints") public static boolean needsUpdateConstraints(UIView __self__, Selector __cmd__) { return __self__.needsUpdateConstraints(); }
        @Callback @BindSelector("pointInside:withEvent:") public static boolean pointInside(UIView __self__, Selector __cmd__, @ByVal CGPoint point, UIEvent event) { return __self__.pointInside(point, event); }
        @Callback @BindSelector("removeConstraint:") public static void removeConstraint(UIView __self__, Selector __cmd__, NSLayoutConstraint constraint) { __self__.removeConstraint(constraint); }
        @Callback @BindSelector("removeConstraints:") public static void removeConstraints(UIView __self__, Selector __cmd__, NSArray constraints) { __self__.removeConstraints(constraints); }
        @Callback @BindSelector("removeFromSuperview") public static void removeFromSuperview(UIView __self__, Selector __cmd__) { __self__.removeFromSuperview(); }
        @Callback @BindSelector("removeGestureRecognizer:") public static void removeGestureRecognizer(UIView __self__, Selector __cmd__, UIGestureRecognizer gestureRecognizer) { __self__.removeGestureRecognizer(gestureRecognizer); }
        @Callback @BindSelector("sizeToFit") public static void resizeToFit(UIView __self__, Selector __cmd__) { __self__.resizeToFit(); }
        @Callback @BindSelector("sendSubviewToBack:") public static void sendSubviewToBack(UIView __self__, Selector __cmd__, UIView view) { __self__.sendSubviewToBack(view); }
        @Callback @BindSelector("setContentCompressionResistancePriority:forAxis:") public static void setContentCompressionResistancePriority(UIView __self__, Selector __cmd__, UILayoutPriority priority, UILayoutConstraintAxis axis) { __self__.setContentCompressionResistancePriority(priority, axis); }
        @Callback @BindSelector("setContentHuggingPriority:forAxis:") public static void setContentHuggingPriority(UIView __self__, Selector __cmd__, UILayoutPriority priority, UILayoutConstraintAxis axis) { __self__.setContentHuggingPriority(priority, axis); }
        @Callback @BindSelector("setNeedsDisplay") public static void setNeedsDisplay(UIView __self__, Selector __cmd__) { __self__.setNeedsDisplay(); }
        @Callback @BindSelector("setNeedsDisplayInRect:") public static void setNeedsDisplay(UIView __self__, Selector __cmd__, @ByVal CGRect invalidRect) { __self__.setNeedsDisplay(invalidRect); }
        @Callback @BindSelector("setNeedsLayout") public static void setNeedsLayout(UIView __self__, Selector __cmd__) { __self__.setNeedsLayout(); }
        @Callback @BindSelector("setNeedsUpdateConstraints") public static void setNeedsUpdateConstraints(UIView __self__, Selector __cmd__) { __self__.setNeedsUpdateConstraints(); }
        @Callback @BindSelector("setTranslatesAutoresizingMaskIntoConstraints:") public static void setTranslatesAutoresizingMaskIntoConstraints(UIView __self__, Selector __cmd__, boolean flag) { __self__.setTranslatesAutoresizingMaskIntoConstraints(flag); }
        @Callback @BindSelector("updateConstraints") public static void updateConstraints(UIView __self__, Selector __cmd__) { __self__.updateConstraints(); }
        @Callback @BindSelector("updateConstraintsIfNeeded") public static void updateConstraintsIfNeeded(UIView __self__, Selector __cmd__) { __self__.updateConstraintsIfNeeded(); }
        @Callback @BindSelector("willMoveToSuperview:") public static void willMoveToSuperview(UIView __self__, Selector __cmd__, UIView newSuperview) { __self__.willMoveToSuperview(newSuperview); }
        @Callback @BindSelector("willMoveToWindow:") public static void willMoveToWindow(UIView __self__, Selector __cmd__, UIWindow newWindow) { __self__.willMoveToWindow(newWindow); }
        @Callback @BindSelector("willRemoveSubview:") public static void willRemoveSubview(UIView __self__, Selector __cmd__, UIView subview) { __self__.willRemoveSubview(subview); }
    }
    /*</callbacks>*/

}
