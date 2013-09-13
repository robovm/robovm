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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html">UIBarButtonItem Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIBarButtonItem /*</name>*/ 
    extends /*<extends>*/ UIBarItem /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIBarButtonItem /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIBarButtonItem /*</name>*/.class);

    /*<constructors>*/
    protected UIBarButtonItem(SkipInit skipInit) { super(skipInit); }
    public UIBarButtonItem() {}
    
    private static final Selector initWithBarButtonSystemItem$target$action$ = Selector.register("initWithBarButtonSystemItem:target:action:");
    @Bridge private native static @Pointer long objc_initWithBarButtonSystemItem(UIBarButtonItem __self__, Selector __cmd__, UIBarButtonSystemItem systemItem, NSObject target, Selector action);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/initWithBarButtonSystemItem:target:action:">- (id)initWithBarButtonSystemItem:(UIBarButtonSystemItem)systemItem target:(id)target action:(SEL)action</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarButtonItem(UIBarButtonSystemItem systemItem, NSObject target, Selector action) {
        super((SkipInit) null);
        initObject(objc_initWithBarButtonSystemItem(this, initWithBarButtonSystemItem$target$action$, systemItem, target, action));
    }
    
    private static final Selector initWithCustomView$ = Selector.register("initWithCustomView:");
    @Bridge private native static @Pointer long objc_initWithCustomView(UIBarButtonItem __self__, Selector __cmd__, UIView customView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/initWithCustomView:">- (id)initWithCustomView:(UIView *)customView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarButtonItem(UIView customView) {
        super((SkipInit) null);
        initObject(objc_initWithCustomView(this, initWithCustomView$, customView));
    }
    
    private static final Selector initWithImage$landscapeImagePhone$style$target$action$ = Selector.register("initWithImage:landscapeImagePhone:style:target:action:");
    @Bridge private native static @Pointer long objc_initWithImage(UIBarButtonItem __self__, Selector __cmd__, UIImage image, UIImage landscapeImagePhone, UIBarButtonItemStyle style, NSObject target, Selector action);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/initWithImage:landscapeImagePhone:style:target:action:">- (id)initWithImage:(UIImage *)image landscapeImagePhone:(UIImage *)landscapeImagePhone style:(UIBarButtonItemStyle)style target:(id)target action:(SEL)action</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIBarButtonItem(UIImage image, UIImage landscapeImagePhone, UIBarButtonItemStyle style, NSObject target, Selector action) {
        super((SkipInit) null);
        initObject(objc_initWithImage(this, initWithImage$landscapeImagePhone$style$target$action$, image, landscapeImagePhone, style, target, action));
    }
    
    private static final Selector initWithImage$style$target$action$ = Selector.register("initWithImage:style:target:action:");
    @Bridge private native static @Pointer long objc_initWithImage(UIBarButtonItem __self__, Selector __cmd__, UIImage image, UIBarButtonItemStyle style, NSObject target, Selector action);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/initWithImage:style:target:action:">- (id)initWithImage:(UIImage *)image style:(UIBarButtonItemStyle)style target:(id)target action:(SEL)action</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarButtonItem(UIImage image, UIBarButtonItemStyle style, NSObject target, Selector action) {
        super((SkipInit) null);
        initObject(objc_initWithImage(this, initWithImage$style$target$action$, image, style, target, action));
    }
    
    private static final Selector initWithTitle$style$target$action$ = Selector.register("initWithTitle:style:target:action:");
    @Bridge private native static @Pointer long objc_initWithTitle(UIBarButtonItem __self__, Selector __cmd__, String title, UIBarButtonItemStyle style, NSObject target, Selector action);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/initWithTitle:style:target:action:">- (id)initWithTitle:(NSString *)title style:(UIBarButtonItemStyle)style target:(id)target action:(SEL)action</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarButtonItem(String title, UIBarButtonItemStyle style, NSObject target, Selector action) {
        super((SkipInit) null);
        initObject(objc_initWithTitle(this, initWithTitle$style$target$action$, title, style, target, action));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector action = Selector.register("action");
    @Bridge private native static Selector objc_getAction(UIBarButtonItem __self__, Selector __cmd__);
    @Bridge private native static Selector objc_getActionSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/action">@property(nonatomic) SEL action</a>
     * @since Available in iOS 2.0 and later.
     */
    public Selector getAction() {
        if (customClass) { return objc_getActionSuper(getSuper(), action); } else { return objc_getAction(this, action); }
    }
    
    private static final Selector setAction$ = Selector.register("setAction:");
    @Bridge private native static void objc_setAction(UIBarButtonItem __self__, Selector __cmd__, Selector action);
    @Bridge private native static void objc_setActionSuper(ObjCSuper __super__, Selector __cmd__, Selector action);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/action">@property(nonatomic) SEL action</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAction(Selector action) {
        if (customClass) { objc_setActionSuper(getSuper(), setAction$, action); } else { objc_setAction(this, setAction$, action); }
    }
    
    private static final Selector customView = Selector.register("customView");
    @Bridge private native static UIView objc_getCustomView(UIBarButtonItem __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getCustomViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/customView">@property(nonatomic, retain) UIView *customView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getCustomView() {
        if (customClass) { return objc_getCustomViewSuper(getSuper(), customView); } else { return objc_getCustomView(this, customView); }
    }
    
    private static final Selector setCustomView$ = Selector.register("setCustomView:");
    @Bridge private native static void objc_setCustomView(UIBarButtonItem __self__, Selector __cmd__, UIView customView);
    @Bridge private native static void objc_setCustomViewSuper(ObjCSuper __super__, Selector __cmd__, UIView customView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/customView">@property(nonatomic, retain) UIView *customView</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setCustomView(UIView customView) {
        if (customClass) { objc_setCustomViewSuper(getSuper(), setCustomView$, customView); } else { objc_setCustomView(this, setCustomView$, customView); }
    }
    
    private static final Selector possibleTitles = Selector.register("possibleTitles");
    @Bridge private native static NSSet objc_getPossibleTitles(UIBarButtonItem __self__, Selector __cmd__);
    @Bridge private native static NSSet objc_getPossibleTitlesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/possibleTitles">@property(nonatomic, copy) NSSet *possibleTitles</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSSet getPossibleTitles() {
        if (customClass) { return objc_getPossibleTitlesSuper(getSuper(), possibleTitles); } else { return objc_getPossibleTitles(this, possibleTitles); }
    }
    
    private static final Selector setPossibleTitles$ = Selector.register("setPossibleTitles:");
    @Bridge private native static void objc_setPossibleTitles(UIBarButtonItem __self__, Selector __cmd__, NSSet possibleTitles);
    @Bridge private native static void objc_setPossibleTitlesSuper(ObjCSuper __super__, Selector __cmd__, NSSet possibleTitles);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/possibleTitles">@property(nonatomic, copy) NSSet *possibleTitles</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setPossibleTitles(NSSet possibleTitles) {
        if (customClass) { objc_setPossibleTitlesSuper(getSuper(), setPossibleTitles$, possibleTitles); } else { objc_setPossibleTitles(this, setPossibleTitles$, possibleTitles); }
    }
    
    private static final Selector style = Selector.register("style");
    @Bridge private native static UIBarButtonItemStyle objc_getStyle(UIBarButtonItem __self__, Selector __cmd__);
    @Bridge private native static UIBarButtonItemStyle objc_getStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/style">@property(nonatomic) UIBarButtonItemStyle style</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarButtonItemStyle getStyle() {
        if (customClass) { return objc_getStyleSuper(getSuper(), style); } else { return objc_getStyle(this, style); }
    }
    
    private static final Selector setStyle$ = Selector.register("setStyle:");
    @Bridge private native static void objc_setStyle(UIBarButtonItem __self__, Selector __cmd__, UIBarButtonItemStyle style);
    @Bridge private native static void objc_setStyleSuper(ObjCSuper __super__, Selector __cmd__, UIBarButtonItemStyle style);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/style">@property(nonatomic) UIBarButtonItemStyle style</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setStyle(UIBarButtonItemStyle style) {
        if (customClass) { objc_setStyleSuper(getSuper(), setStyle$, style); } else { objc_setStyle(this, setStyle$, style); }
    }
    
    private static final Selector target = Selector.register("target");
    @Bridge private native static NSObject objc_getTarget(UIBarButtonItem __self__, Selector __cmd__);
    @Bridge private native static NSObject objc_getTargetSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/target">@property(nonatomic, assign) id target</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSObject getTarget() {
        if (customClass) { return objc_getTargetSuper(getSuper(), target); } else { return objc_getTarget(this, target); }
    }
    
    private static final Selector setTarget$ = Selector.register("setTarget:");
    @Bridge private native static void objc_setTarget(UIBarButtonItem __self__, Selector __cmd__, NSObject target);
    @Bridge private native static void objc_setTargetSuper(ObjCSuper __super__, Selector __cmd__, NSObject target);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/target">@property(nonatomic, assign) id target</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTarget(NSObject target) {
        if (customClass) { objc_setTargetSuper(getSuper(), setTarget$, target); } else { objc_setTarget(this, setTarget$, target); }
    }
    
    private static final Selector tintColor = Selector.register("tintColor");
    @Bridge private native static UIColor objc_getTintColor(UIBarButtonItem __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIColor getTintColor() {
        if (customClass) { return objc_getTintColorSuper(getSuper(), tintColor); } else { return objc_getTintColor(this, tintColor); }
    }
    
    private static final Selector setTintColor$ = Selector.register("setTintColor:");
    @Bridge private native static void objc_setTintColor(UIBarButtonItem __self__, Selector __cmd__, UIColor tintColor);
    @Bridge private native static void objc_setTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor tintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setTintColor(UIColor tintColor) {
        if (customClass) { objc_setTintColorSuper(getSuper(), setTintColor$, tintColor); } else { objc_setTintColor(this, setTintColor$, tintColor); }
    }
    
    private static final Selector width = Selector.register("width");
    @Bridge private native static float objc_getWidth(UIBarButtonItem __self__, Selector __cmd__);
    @Bridge private native static float objc_getWidthSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/width">@property(nonatomic) CGFloat width</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getWidth() {
        if (customClass) { return objc_getWidthSuper(getSuper(), width); } else { return objc_getWidth(this, width); }
    }
    
    private static final Selector setWidth$ = Selector.register("setWidth:");
    @Bridge private native static void objc_setWidth(UIBarButtonItem __self__, Selector __cmd__, float width);
    @Bridge private native static void objc_setWidthSuper(ObjCSuper __super__, Selector __cmd__, float width);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/width">@property(nonatomic) CGFloat width</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setWidth(float width) {
        if (customClass) { objc_setWidthSuper(getSuper(), setWidth$, width); } else { objc_setWidth(this, setWidth$, width); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector backButtonBackgroundImageForState$barMetrics$ = Selector.register("backButtonBackgroundImageForState:barMetrics:");
    @Bridge private native static UIImage objc_getBackButtonBackgroundImage(UIBarButtonItem __self__, Selector __cmd__, UIControlState state, UIBarMetrics barMetrics);
    @Bridge private native static UIImage objc_getBackButtonBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/backButtonBackgroundImageForState:barMetrics:">- (UIImage *)backButtonBackgroundImageForState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getBackButtonBackgroundImage(UIControlState state, UIBarMetrics barMetrics) {
        if (customClass) { return objc_getBackButtonBackgroundImageSuper(getSuper(), backButtonBackgroundImageForState$barMetrics$, state, barMetrics); } else { return objc_getBackButtonBackgroundImage(this, backButtonBackgroundImageForState$barMetrics$, state, barMetrics); }
    }
    
    private static final Selector backButtonBackgroundVerticalPositionAdjustmentForBarMetrics$ = Selector.register("backButtonBackgroundVerticalPositionAdjustmentForBarMetrics:");
    @Bridge private native static float objc_getBackButtonBackgroundVerticalPositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, UIBarMetrics barMetrics);
    @Bridge private native static float objc_getBackButtonBackgroundVerticalPositionAdjustmentSuper(ObjCSuper __super__, Selector __cmd__, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/backButtonBackgroundVerticalPositionAdjustmentForBarMetrics:">- (CGFloat)backButtonBackgroundVerticalPositionAdjustmentForBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public float getBackButtonBackgroundVerticalPositionAdjustment(UIBarMetrics barMetrics) {
        if (customClass) { return objc_getBackButtonBackgroundVerticalPositionAdjustmentSuper(getSuper(), backButtonBackgroundVerticalPositionAdjustmentForBarMetrics$, barMetrics); } else { return objc_getBackButtonBackgroundVerticalPositionAdjustment(this, backButtonBackgroundVerticalPositionAdjustmentForBarMetrics$, barMetrics); }
    }
    
    private static final Selector backButtonTitlePositionAdjustmentForBarMetrics$ = Selector.register("backButtonTitlePositionAdjustmentForBarMetrics:");
    @Bridge private native static @ByVal UIOffset objc_getBackButtonTitlePositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, UIBarMetrics barMetrics);
    @Bridge private native static @ByVal UIOffset objc_getBackButtonTitlePositionAdjustmentSuper(ObjCSuper __super__, Selector __cmd__, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/backButtonTitlePositionAdjustmentForBarMetrics:">- (UIOffset)backButtonTitlePositionAdjustmentForBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIOffset getBackButtonTitlePositionAdjustment(UIBarMetrics barMetrics) {
        if (customClass) { return objc_getBackButtonTitlePositionAdjustmentSuper(getSuper(), backButtonTitlePositionAdjustmentForBarMetrics$, barMetrics); } else { return objc_getBackButtonTitlePositionAdjustment(this, backButtonTitlePositionAdjustmentForBarMetrics$, barMetrics); }
    }
    
    private static final Selector backgroundImageForState$style$barMetrics$ = Selector.register("backgroundImageForState:style:barMetrics:");
    @Bridge private native static UIImage objc_getBackgroundImage(UIBarButtonItem __self__, Selector __cmd__, UIControlState state, UIBarButtonItemStyle style, UIBarMetrics barMetrics);
    @Bridge private native static UIImage objc_getBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state, UIBarButtonItemStyle style, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/backgroundImageForState:style:barMetrics:">- (UIImage *)backgroundImageForState:(UIControlState)state style:(UIBarButtonItemStyle)style barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage getBackgroundImage(UIControlState state, UIBarButtonItemStyle style, UIBarMetrics barMetrics) {
        if (customClass) { return objc_getBackgroundImageSuper(getSuper(), backgroundImageForState$style$barMetrics$, state, style, barMetrics); } else { return objc_getBackgroundImage(this, backgroundImageForState$style$barMetrics$, state, style, barMetrics); }
    }
    
    private static final Selector backgroundImageForState$barMetrics$ = Selector.register("backgroundImageForState:barMetrics:");
    @Bridge private native static UIImage objc_getBackgroundImage(UIBarButtonItem __self__, Selector __cmd__, UIControlState state, UIBarMetrics barMetrics);
    @Bridge private native static UIImage objc_getBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/backgroundImageForState:barMetrics:">- (UIImage *)backgroundImageForState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getBackgroundImage(UIControlState state, UIBarMetrics barMetrics) {
        if (customClass) { return objc_getBackgroundImageSuper(getSuper(), backgroundImageForState$barMetrics$, state, barMetrics); } else { return objc_getBackgroundImage(this, backgroundImageForState$barMetrics$, state, barMetrics); }
    }
    
    private static final Selector backgroundVerticalPositionAdjustmentForBarMetrics$ = Selector.register("backgroundVerticalPositionAdjustmentForBarMetrics:");
    @Bridge private native static float objc_getBackgroundVerticalPositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, UIBarMetrics barMetrics);
    @Bridge private native static float objc_getBackgroundVerticalPositionAdjustmentSuper(ObjCSuper __super__, Selector __cmd__, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/backgroundVerticalPositionAdjustmentForBarMetrics:">- (CGFloat)backgroundVerticalPositionAdjustmentForBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public float getBackgroundVerticalPositionAdjustment(UIBarMetrics barMetrics) {
        if (customClass) { return objc_getBackgroundVerticalPositionAdjustmentSuper(getSuper(), backgroundVerticalPositionAdjustmentForBarMetrics$, barMetrics); } else { return objc_getBackgroundVerticalPositionAdjustment(this, backgroundVerticalPositionAdjustmentForBarMetrics$, barMetrics); }
    }
    
    private static final Selector titlePositionAdjustmentForBarMetrics$ = Selector.register("titlePositionAdjustmentForBarMetrics:");
    @Bridge private native static @ByVal UIOffset objc_getTitlePositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, UIBarMetrics barMetrics);
    @Bridge private native static @ByVal UIOffset objc_getTitlePositionAdjustmentSuper(ObjCSuper __super__, Selector __cmd__, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/titlePositionAdjustmentForBarMetrics:">- (UIOffset)titlePositionAdjustmentForBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIOffset getTitlePositionAdjustment(UIBarMetrics barMetrics) {
        if (customClass) { return objc_getTitlePositionAdjustmentSuper(getSuper(), titlePositionAdjustmentForBarMetrics$, barMetrics); } else { return objc_getTitlePositionAdjustment(this, titlePositionAdjustmentForBarMetrics$, barMetrics); }
    }
    
    private static final Selector setBackButtonBackgroundImage$forState$barMetrics$ = Selector.register("setBackButtonBackgroundImage:forState:barMetrics:");
    @Bridge private native static void objc_setBackButtonBackgroundImage(UIBarButtonItem __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics);
    @Bridge private native static void objc_setBackButtonBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setBackButtonBackgroundImage:forState:barMetrics:">- (void)setBackButtonBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setBackButtonBackgroundImage(UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics) {
        if (customClass) { objc_setBackButtonBackgroundImageSuper(getSuper(), setBackButtonBackgroundImage$forState$barMetrics$, backgroundImage, state, barMetrics); } else { objc_setBackButtonBackgroundImage(this, setBackButtonBackgroundImage$forState$barMetrics$, backgroundImage, state, barMetrics); }
    }
    
    private static final Selector setBackButtonBackgroundVerticalPositionAdjustment$forBarMetrics$ = Selector.register("setBackButtonBackgroundVerticalPositionAdjustment:forBarMetrics:");
    @Bridge private native static void objc_setBackButtonBackgroundVerticalPositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, float adjustment, UIBarMetrics barMetrics);
    @Bridge private native static void objc_setBackButtonBackgroundVerticalPositionAdjustmentSuper(ObjCSuper __super__, Selector __cmd__, float adjustment, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setBackButtonBackgroundVerticalPositionAdjustment:forBarMetrics:">- (void)setBackButtonBackgroundVerticalPositionAdjustment:(CGFloat)adjustment forBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setBackButtonBackgroundVerticalPositionAdjustment(float adjustment, UIBarMetrics barMetrics) {
        if (customClass) { objc_setBackButtonBackgroundVerticalPositionAdjustmentSuper(getSuper(), setBackButtonBackgroundVerticalPositionAdjustment$forBarMetrics$, adjustment, barMetrics); } else { objc_setBackButtonBackgroundVerticalPositionAdjustment(this, setBackButtonBackgroundVerticalPositionAdjustment$forBarMetrics$, adjustment, barMetrics); }
    }
    
    private static final Selector setBackButtonTitlePositionAdjustment$forBarMetrics$ = Selector.register("setBackButtonTitlePositionAdjustment:forBarMetrics:");
    @Bridge private native static void objc_setBackButtonTitlePositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, @ByVal UIOffset adjustment, UIBarMetrics barMetrics);
    @Bridge private native static void objc_setBackButtonTitlePositionAdjustmentSuper(ObjCSuper __super__, Selector __cmd__, @ByVal UIOffset adjustment, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setBackButtonTitlePositionAdjustment:forBarMetrics:">- (void)setBackButtonTitlePositionAdjustment:(UIOffset)adjustment forBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setBackButtonTitlePositionAdjustment(UIOffset adjustment, UIBarMetrics barMetrics) {
        if (customClass) { objc_setBackButtonTitlePositionAdjustmentSuper(getSuper(), setBackButtonTitlePositionAdjustment$forBarMetrics$, adjustment, barMetrics); } else { objc_setBackButtonTitlePositionAdjustment(this, setBackButtonTitlePositionAdjustment$forBarMetrics$, adjustment, barMetrics); }
    }
    
    private static final Selector setBackgroundImage$forState$barMetrics$ = Selector.register("setBackgroundImage:forState:barMetrics:");
    @Bridge private native static void objc_setBackgroundImage(UIBarButtonItem __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics);
    @Bridge private native static void objc_setBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setBackgroundImage:forState:barMetrics:">- (void)setBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setBackgroundImage(UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics) {
        if (customClass) { objc_setBackgroundImageSuper(getSuper(), setBackgroundImage$forState$barMetrics$, backgroundImage, state, barMetrics); } else { objc_setBackgroundImage(this, setBackgroundImage$forState$barMetrics$, backgroundImage, state, barMetrics); }
    }
    
    private static final Selector setBackgroundImage$forState$style$barMetrics$ = Selector.register("setBackgroundImage:forState:style:barMetrics:");
    @Bridge private native static void objc_setBackgroundImage(UIBarButtonItem __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarButtonItemStyle style, UIBarMetrics barMetrics);
    @Bridge private native static void objc_setBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarButtonItemStyle style, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setBackgroundImage:forState:style:barMetrics:">- (void)setBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state style:(UIBarButtonItemStyle)style barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setBackgroundImage(UIImage backgroundImage, UIControlState state, UIBarButtonItemStyle style, UIBarMetrics barMetrics) {
        if (customClass) { objc_setBackgroundImageSuper(getSuper(), setBackgroundImage$forState$style$barMetrics$, backgroundImage, state, style, barMetrics); } else { objc_setBackgroundImage(this, setBackgroundImage$forState$style$barMetrics$, backgroundImage, state, style, barMetrics); }
    }
    
    private static final Selector setBackgroundVerticalPositionAdjustment$forBarMetrics$ = Selector.register("setBackgroundVerticalPositionAdjustment:forBarMetrics:");
    @Bridge private native static void objc_setBackgroundVerticalPositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, float adjustment, UIBarMetrics barMetrics);
    @Bridge private native static void objc_setBackgroundVerticalPositionAdjustmentSuper(ObjCSuper __super__, Selector __cmd__, float adjustment, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setBackgroundVerticalPositionAdjustment:forBarMetrics:">- (void)setBackgroundVerticalPositionAdjustment:(CGFloat)adjustment forBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setBackgroundVerticalPositionAdjustment(float adjustment, UIBarMetrics barMetrics) {
        if (customClass) { objc_setBackgroundVerticalPositionAdjustmentSuper(getSuper(), setBackgroundVerticalPositionAdjustment$forBarMetrics$, adjustment, barMetrics); } else { objc_setBackgroundVerticalPositionAdjustment(this, setBackgroundVerticalPositionAdjustment$forBarMetrics$, adjustment, barMetrics); }
    }
    
    private static final Selector setTitlePositionAdjustment$forBarMetrics$ = Selector.register("setTitlePositionAdjustment:forBarMetrics:");
    @Bridge private native static void objc_setTitlePositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, @ByVal UIOffset adjustment, UIBarMetrics barMetrics);
    @Bridge private native static void objc_setTitlePositionAdjustmentSuper(ObjCSuper __super__, Selector __cmd__, @ByVal UIOffset adjustment, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setTitlePositionAdjustment:forBarMetrics:">- (void)setTitlePositionAdjustment:(UIOffset)adjustment forBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setTitlePositionAdjustment(UIOffset adjustment, UIBarMetrics barMetrics) {
        if (customClass) { objc_setTitlePositionAdjustmentSuper(getSuper(), setTitlePositionAdjustment$forBarMetrics$, adjustment, barMetrics); } else { objc_setTitlePositionAdjustment(this, setTitlePositionAdjustment$forBarMetrics$, adjustment, barMetrics); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("action") public static Selector getAction(UIBarButtonItem __self__, Selector __cmd__) { return __self__.getAction(); }
        @Callback @BindSelector("setAction:") public static void setAction(UIBarButtonItem __self__, Selector __cmd__, Selector action) { __self__.setAction(action); }
        @Callback @BindSelector("customView") public static UIView getCustomView(UIBarButtonItem __self__, Selector __cmd__) { return __self__.getCustomView(); }
        @Callback @BindSelector("setCustomView:") public static void setCustomView(UIBarButtonItem __self__, Selector __cmd__, UIView customView) { __self__.setCustomView(customView); }
        @Callback @BindSelector("possibleTitles") public static NSSet getPossibleTitles(UIBarButtonItem __self__, Selector __cmd__) { return __self__.getPossibleTitles(); }
        @Callback @BindSelector("setPossibleTitles:") public static void setPossibleTitles(UIBarButtonItem __self__, Selector __cmd__, NSSet possibleTitles) { __self__.setPossibleTitles(possibleTitles); }
        @Callback @BindSelector("style") public static UIBarButtonItemStyle getStyle(UIBarButtonItem __self__, Selector __cmd__) { return __self__.getStyle(); }
        @Callback @BindSelector("setStyle:") public static void setStyle(UIBarButtonItem __self__, Selector __cmd__, UIBarButtonItemStyle style) { __self__.setStyle(style); }
        @Callback @BindSelector("target") public static NSObject getTarget(UIBarButtonItem __self__, Selector __cmd__) { return __self__.getTarget(); }
        @Callback @BindSelector("setTarget:") public static void setTarget(UIBarButtonItem __self__, Selector __cmd__, NSObject target) { __self__.setTarget(target); }
        @Callback @BindSelector("tintColor") public static UIColor getTintColor(UIBarButtonItem __self__, Selector __cmd__) { return __self__.getTintColor(); }
        @Callback @BindSelector("setTintColor:") public static void setTintColor(UIBarButtonItem __self__, Selector __cmd__, UIColor tintColor) { __self__.setTintColor(tintColor); }
        @Callback @BindSelector("width") public static float getWidth(UIBarButtonItem __self__, Selector __cmd__) { return __self__.getWidth(); }
        @Callback @BindSelector("setWidth:") public static void setWidth(UIBarButtonItem __self__, Selector __cmd__, float width) { __self__.setWidth(width); }
        @Callback @BindSelector("backButtonBackgroundImageForState:barMetrics:") public static UIImage getBackButtonBackgroundImage(UIBarButtonItem __self__, Selector __cmd__, UIControlState state, UIBarMetrics barMetrics) { return __self__.getBackButtonBackgroundImage(state, barMetrics); }
        @Callback @BindSelector("backButtonBackgroundVerticalPositionAdjustmentForBarMetrics:") public static float getBackButtonBackgroundVerticalPositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, UIBarMetrics barMetrics) { return __self__.getBackButtonBackgroundVerticalPositionAdjustment(barMetrics); }
        @Callback @BindSelector("backButtonTitlePositionAdjustmentForBarMetrics:") public static @ByVal UIOffset getBackButtonTitlePositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, UIBarMetrics barMetrics) { return __self__.getBackButtonTitlePositionAdjustment(barMetrics); }
        @Callback @BindSelector("backgroundImageForState:style:barMetrics:") public static UIImage getBackgroundImage(UIBarButtonItem __self__, Selector __cmd__, UIControlState state, UIBarButtonItemStyle style, UIBarMetrics barMetrics) { return __self__.getBackgroundImage(state, style, barMetrics); }
        @Callback @BindSelector("backgroundImageForState:barMetrics:") public static UIImage getBackgroundImage(UIBarButtonItem __self__, Selector __cmd__, UIControlState state, UIBarMetrics barMetrics) { return __self__.getBackgroundImage(state, barMetrics); }
        @Callback @BindSelector("backgroundVerticalPositionAdjustmentForBarMetrics:") public static float getBackgroundVerticalPositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, UIBarMetrics barMetrics) { return __self__.getBackgroundVerticalPositionAdjustment(barMetrics); }
        @Callback @BindSelector("titlePositionAdjustmentForBarMetrics:") public static @ByVal UIOffset getTitlePositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, UIBarMetrics barMetrics) { return __self__.getTitlePositionAdjustment(barMetrics); }
        @Callback @BindSelector("setBackButtonBackgroundImage:forState:barMetrics:") public static void setBackButtonBackgroundImage(UIBarButtonItem __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics) { __self__.setBackButtonBackgroundImage(backgroundImage, state, barMetrics); }
        @Callback @BindSelector("setBackButtonBackgroundVerticalPositionAdjustment:forBarMetrics:") public static void setBackButtonBackgroundVerticalPositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, float adjustment, UIBarMetrics barMetrics) { __self__.setBackButtonBackgroundVerticalPositionAdjustment(adjustment, barMetrics); }
        @Callback @BindSelector("setBackButtonTitlePositionAdjustment:forBarMetrics:") public static void setBackButtonTitlePositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, @ByVal UIOffset adjustment, UIBarMetrics barMetrics) { __self__.setBackButtonTitlePositionAdjustment(adjustment, barMetrics); }
        @Callback @BindSelector("setBackgroundImage:forState:barMetrics:") public static void setBackgroundImage(UIBarButtonItem __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics) { __self__.setBackgroundImage(backgroundImage, state, barMetrics); }
        @Callback @BindSelector("setBackgroundImage:forState:style:barMetrics:") public static void setBackgroundImage(UIBarButtonItem __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarButtonItemStyle style, UIBarMetrics barMetrics) { __self__.setBackgroundImage(backgroundImage, state, style, barMetrics); }
        @Callback @BindSelector("setBackgroundVerticalPositionAdjustment:forBarMetrics:") public static void setBackgroundVerticalPositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, float adjustment, UIBarMetrics barMetrics) { __self__.setBackgroundVerticalPositionAdjustment(adjustment, barMetrics); }
        @Callback @BindSelector("setTitlePositionAdjustment:forBarMetrics:") public static void setTitlePositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, @ByVal UIOffset adjustment, UIBarMetrics barMetrics) { __self__.setTitlePositionAdjustment(adjustment, barMetrics); }
    }
    /*</callbacks>*/

}
