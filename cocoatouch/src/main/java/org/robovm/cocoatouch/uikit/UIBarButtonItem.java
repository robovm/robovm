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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html">UIBarButtonItem Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIBarButtonItem /*</name>*/ 
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
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithBarButtonSystemItem(UIBarButtonItem __self__, Selector __cmd__, UIBarButtonSystemItem systemItem, NSObject target, Selector action);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/initWithBarButtonSystemItem:target:action:">- (id)initWithBarButtonSystemItem:(UIBarButtonSystemItem)systemItem target:(id)target action:(SEL)action</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarButtonItem(UIBarButtonSystemItem systemItem, NSObject target, Selector action) {
        super((SkipInit) null);
        objc_initWithBarButtonSystemItem(this, initWithBarButtonSystemItem$target$action$, systemItem, target, action);
    }
    
    private static final Selector initWithCustomView$ = Selector.register("initWithCustomView:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithCustomView(UIBarButtonItem __self__, Selector __cmd__, UIView customView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/initWithCustomView:">- (id)initWithCustomView:(UIView *)customView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarButtonItem(UIView customView) {
        super((SkipInit) null);
        objc_initWithCustomView(this, initWithCustomView$, customView);
    }
    
    private static final Selector initWithImage$landscapeImagePhone$style$target$action$ = Selector.register("initWithImage:landscapeImagePhone:style:target:action:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithImage(UIBarButtonItem __self__, Selector __cmd__, UIImage image, UIImage landscapeImagePhone, UIBarButtonItemStyle style, NSObject target, Selector action);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/initWithImage:landscapeImagePhone:style:target:action:">- (id)initWithImage:(UIImage *)image landscapeImagePhone:(UIImage *)landscapeImagePhone style:(UIBarButtonItemStyle)style target:(id)target action:(SEL)action</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIBarButtonItem(UIImage image, UIImage landscapeImagePhone, UIBarButtonItemStyle style, NSObject target, Selector action) {
        super((SkipInit) null);
        objc_initWithImage(this, initWithImage$landscapeImagePhone$style$target$action$, image, landscapeImagePhone, style, target, action);
    }
    
    private static final Selector initWithImage$style$target$action$ = Selector.register("initWithImage:style:target:action:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithImage(UIBarButtonItem __self__, Selector __cmd__, UIImage image, UIBarButtonItemStyle style, NSObject target, Selector action);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/initWithImage:style:target:action:">- (id)initWithImage:(UIImage *)image style:(UIBarButtonItemStyle)style target:(id)target action:(SEL)action</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarButtonItem(UIImage image, UIBarButtonItemStyle style, NSObject target, Selector action) {
        super((SkipInit) null);
        objc_initWithImage(this, initWithImage$style$target$action$, image, style, target, action);
    }
    
    private static final Selector initWithTitle$style$target$action$ = Selector.register("initWithTitle:style:target:action:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithTitle(UIBarButtonItem __self__, Selector __cmd__, String title, UIBarButtonItemStyle style, NSObject target, Selector action);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/initWithTitle:style:target:action:">- (id)initWithTitle:(NSString *)title style:(UIBarButtonItemStyle)style target:(id)target action:(SEL)action</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarButtonItem(String title, UIBarButtonItemStyle style, NSObject target, Selector action) {
        super((SkipInit) null);
        objc_initWithTitle(this, initWithTitle$style$target$action$, title, style, target, action);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/action">@property(nonatomic) SEL action</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("action") public native Selector getAction();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/action">@property(nonatomic) SEL action</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAction:") public native void setAction(Selector v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/customView">@property(nonatomic, retain) UIView *customView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("customView") public native UIView getCustomView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/customView">@property(nonatomic, retain) UIView *customView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setCustomView:") public native void setCustomView(UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/possibleTitles">@property(nonatomic, copy) NSSet *possibleTitles</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("possibleTitles") public native NSSet getPossibleTitles();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/possibleTitles">@property(nonatomic, copy) NSSet *possibleTitles</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setPossibleTitles:") public native void setPossibleTitles(NSSet v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/style">@property(nonatomic) UIBarButtonItemStyle style</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("style") public native UIBarButtonItemStyle getStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/style">@property(nonatomic) UIBarButtonItemStyle style</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setStyle:") public native void setStyle(UIBarButtonItemStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/target">@property(nonatomic, assign) id target</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("target") public native NSObject getTarget();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/target">@property(nonatomic, assign) id target</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTarget:") public native void setTarget(NSObject v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("tintColor") public native UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/width">@property(nonatomic) CGFloat width</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("width") public native float getWidth();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarButtonItem/width">@property(nonatomic) CGFloat width</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setWidth:") public native void setWidth(float v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector backButtonBackgroundImageForState$barMetrics$ = Selector.register("backButtonBackgroundImageForState:barMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getBackButtonBackgroundImage(UIBarButtonItem __self__, Selector __cmd__, UIControlState state, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getBackButtonBackgroundImageSuper(ObjCSuper __super__, UIBarButtonItem __self__, Selector __cmd__, UIControlState state, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/backButtonBackgroundImageForState:barMetrics:">- (UIImage *)backButtonBackgroundImageForState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getBackButtonBackgroundImage(UIControlState state, UIBarMetrics barMetrics) {
        if (customClass) { return objc_getBackButtonBackgroundImageSuper(getSuper(), this, backButtonBackgroundImageForState$barMetrics$, state, barMetrics); } else { return objc_getBackButtonBackgroundImage(this, backButtonBackgroundImageForState$barMetrics$, state, barMetrics); }
    }
    
    private static final Selector backButtonBackgroundVerticalPositionAdjustmentForBarMetrics$ = Selector.register("backButtonBackgroundVerticalPositionAdjustmentForBarMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getBackButtonBackgroundVerticalPositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getBackButtonBackgroundVerticalPositionAdjustmentSuper(ObjCSuper __super__, UIBarButtonItem __self__, Selector __cmd__, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/backButtonBackgroundVerticalPositionAdjustmentForBarMetrics:">- (CGFloat)backButtonBackgroundVerticalPositionAdjustmentForBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public float getBackButtonBackgroundVerticalPositionAdjustment(UIBarMetrics barMetrics) {
        if (customClass) { return objc_getBackButtonBackgroundVerticalPositionAdjustmentSuper(getSuper(), this, backButtonBackgroundVerticalPositionAdjustmentForBarMetrics$, barMetrics); } else { return objc_getBackButtonBackgroundVerticalPositionAdjustment(this, backButtonBackgroundVerticalPositionAdjustmentForBarMetrics$, barMetrics); }
    }
    
    private static final Selector backButtonTitlePositionAdjustmentForBarMetrics$ = Selector.register("backButtonTitlePositionAdjustmentForBarMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static UIOffset objc_getBackButtonTitlePositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIOffset objc_getBackButtonTitlePositionAdjustmentSuper(ObjCSuper __super__, UIBarButtonItem __self__, Selector __cmd__, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/backButtonTitlePositionAdjustmentForBarMetrics:">- (UIOffset)backButtonTitlePositionAdjustmentForBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIOffset getBackButtonTitlePositionAdjustment(UIBarMetrics barMetrics) {
        if (customClass) { return objc_getBackButtonTitlePositionAdjustmentSuper(getSuper(), this, backButtonTitlePositionAdjustmentForBarMetrics$, barMetrics); } else { return objc_getBackButtonTitlePositionAdjustment(this, backButtonTitlePositionAdjustmentForBarMetrics$, barMetrics); }
    }
    
    private static final Selector backgroundImageForState$style$barMetrics$ = Selector.register("backgroundImageForState:style:barMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getBackgroundImage(UIBarButtonItem __self__, Selector __cmd__, UIControlState state, UIBarButtonItemStyle style, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getBackgroundImageSuper(ObjCSuper __super__, UIBarButtonItem __self__, Selector __cmd__, UIControlState state, UIBarButtonItemStyle style, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/backgroundImageForState:style:barMetrics:">- (UIImage *)backgroundImageForState:(UIControlState)state style:(UIBarButtonItemStyle)style barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage getBackgroundImage(UIControlState state, UIBarButtonItemStyle style, UIBarMetrics barMetrics) {
        if (customClass) { return objc_getBackgroundImageSuper(getSuper(), this, backgroundImageForState$style$barMetrics$, state, style, barMetrics); } else { return objc_getBackgroundImage(this, backgroundImageForState$style$barMetrics$, state, style, barMetrics); }
    }
    
    private static final Selector backgroundImageForState$barMetrics$ = Selector.register("backgroundImageForState:barMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getBackgroundImage(UIBarButtonItem __self__, Selector __cmd__, UIControlState state, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getBackgroundImageSuper(ObjCSuper __super__, UIBarButtonItem __self__, Selector __cmd__, UIControlState state, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/backgroundImageForState:barMetrics:">- (UIImage *)backgroundImageForState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getBackgroundImage(UIControlState state, UIBarMetrics barMetrics) {
        if (customClass) { return objc_getBackgroundImageSuper(getSuper(), this, backgroundImageForState$barMetrics$, state, barMetrics); } else { return objc_getBackgroundImage(this, backgroundImageForState$barMetrics$, state, barMetrics); }
    }
    
    private static final Selector backgroundVerticalPositionAdjustmentForBarMetrics$ = Selector.register("backgroundVerticalPositionAdjustmentForBarMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getBackgroundVerticalPositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getBackgroundVerticalPositionAdjustmentSuper(ObjCSuper __super__, UIBarButtonItem __self__, Selector __cmd__, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/backgroundVerticalPositionAdjustmentForBarMetrics:">- (CGFloat)backgroundVerticalPositionAdjustmentForBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public float getBackgroundVerticalPositionAdjustment(UIBarMetrics barMetrics) {
        if (customClass) { return objc_getBackgroundVerticalPositionAdjustmentSuper(getSuper(), this, backgroundVerticalPositionAdjustmentForBarMetrics$, barMetrics); } else { return objc_getBackgroundVerticalPositionAdjustment(this, backgroundVerticalPositionAdjustmentForBarMetrics$, barMetrics); }
    }
    
    private static final Selector titlePositionAdjustmentForBarMetrics$ = Selector.register("titlePositionAdjustmentForBarMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static UIOffset objc_getTitlePositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIOffset objc_getTitlePositionAdjustmentSuper(ObjCSuper __super__, UIBarButtonItem __self__, Selector __cmd__, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/titlePositionAdjustmentForBarMetrics:">- (UIOffset)titlePositionAdjustmentForBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIOffset getTitlePositionAdjustment(UIBarMetrics barMetrics) {
        if (customClass) { return objc_getTitlePositionAdjustmentSuper(getSuper(), this, titlePositionAdjustmentForBarMetrics$, barMetrics); } else { return objc_getTitlePositionAdjustment(this, titlePositionAdjustmentForBarMetrics$, barMetrics); }
    }
    
    private static final Selector setBackButtonBackgroundImage$forState$barMetrics$ = Selector.register("setBackButtonBackgroundImage:forState:barMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setBackButtonBackgroundImage(UIBarButtonItem __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setBackButtonBackgroundImageSuper(ObjCSuper __super__, UIBarButtonItem __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setBackButtonBackgroundImage:forState:barMetrics:">- (void)setBackButtonBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setBackButtonBackgroundImage(UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics) {
        if (customClass) { objc_setBackButtonBackgroundImageSuper(getSuper(), this, setBackButtonBackgroundImage$forState$barMetrics$, backgroundImage, state, barMetrics); } else { objc_setBackButtonBackgroundImage(this, setBackButtonBackgroundImage$forState$barMetrics$, backgroundImage, state, barMetrics); }
    }
    
    private static final Selector setBackButtonBackgroundVerticalPositionAdjustment$forBarMetrics$ = Selector.register("setBackButtonBackgroundVerticalPositionAdjustment:forBarMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setBackButtonBackgroundVerticalPositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, float adjustment, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setBackButtonBackgroundVerticalPositionAdjustmentSuper(ObjCSuper __super__, UIBarButtonItem __self__, Selector __cmd__, float adjustment, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setBackButtonBackgroundVerticalPositionAdjustment:forBarMetrics:">- (void)setBackButtonBackgroundVerticalPositionAdjustment:(CGFloat)adjustment forBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setBackButtonBackgroundVerticalPositionAdjustment(float adjustment, UIBarMetrics barMetrics) {
        if (customClass) { objc_setBackButtonBackgroundVerticalPositionAdjustmentSuper(getSuper(), this, setBackButtonBackgroundVerticalPositionAdjustment$forBarMetrics$, adjustment, barMetrics); } else { objc_setBackButtonBackgroundVerticalPositionAdjustment(this, setBackButtonBackgroundVerticalPositionAdjustment$forBarMetrics$, adjustment, barMetrics); }
    }
    
    private static final Selector setBackButtonTitlePositionAdjustment$forBarMetrics$ = Selector.register("setBackButtonTitlePositionAdjustment:forBarMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setBackButtonTitlePositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, UIOffset adjustment, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setBackButtonTitlePositionAdjustmentSuper(ObjCSuper __super__, UIBarButtonItem __self__, Selector __cmd__, UIOffset adjustment, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setBackButtonTitlePositionAdjustment:forBarMetrics:">- (void)setBackButtonTitlePositionAdjustment:(UIOffset)adjustment forBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setBackButtonTitlePositionAdjustment(UIOffset adjustment, UIBarMetrics barMetrics) {
        if (customClass) { objc_setBackButtonTitlePositionAdjustmentSuper(getSuper(), this, setBackButtonTitlePositionAdjustment$forBarMetrics$, adjustment, barMetrics); } else { objc_setBackButtonTitlePositionAdjustment(this, setBackButtonTitlePositionAdjustment$forBarMetrics$, adjustment, barMetrics); }
    }
    
    private static final Selector setBackgroundImage$forState$barMetrics$ = Selector.register("setBackgroundImage:forState:barMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setBackgroundImage(UIBarButtonItem __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setBackgroundImageSuper(ObjCSuper __super__, UIBarButtonItem __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setBackgroundImage:forState:barMetrics:">- (void)setBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setBackgroundImage(UIImage backgroundImage, UIControlState state, UIBarMetrics barMetrics) {
        if (customClass) { objc_setBackgroundImageSuper(getSuper(), this, setBackgroundImage$forState$barMetrics$, backgroundImage, state, barMetrics); } else { objc_setBackgroundImage(this, setBackgroundImage$forState$barMetrics$, backgroundImage, state, barMetrics); }
    }
    
    private static final Selector setBackgroundImage$forState$style$barMetrics$ = Selector.register("setBackgroundImage:forState:style:barMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setBackgroundImage(UIBarButtonItem __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarButtonItemStyle style, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setBackgroundImageSuper(ObjCSuper __super__, UIBarButtonItem __self__, Selector __cmd__, UIImage backgroundImage, UIControlState state, UIBarButtonItemStyle style, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setBackgroundImage:forState:style:barMetrics:">- (void)setBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state style:(UIBarButtonItemStyle)style barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setBackgroundImage(UIImage backgroundImage, UIControlState state, UIBarButtonItemStyle style, UIBarMetrics barMetrics) {
        if (customClass) { objc_setBackgroundImageSuper(getSuper(), this, setBackgroundImage$forState$style$barMetrics$, backgroundImage, state, style, barMetrics); } else { objc_setBackgroundImage(this, setBackgroundImage$forState$style$barMetrics$, backgroundImage, state, style, barMetrics); }
    }
    
    private static final Selector setBackgroundVerticalPositionAdjustment$forBarMetrics$ = Selector.register("setBackgroundVerticalPositionAdjustment:forBarMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setBackgroundVerticalPositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, float adjustment, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setBackgroundVerticalPositionAdjustmentSuper(ObjCSuper __super__, UIBarButtonItem __self__, Selector __cmd__, float adjustment, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setBackgroundVerticalPositionAdjustment:forBarMetrics:">- (void)setBackgroundVerticalPositionAdjustment:(CGFloat)adjustment forBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setBackgroundVerticalPositionAdjustment(float adjustment, UIBarMetrics barMetrics) {
        if (customClass) { objc_setBackgroundVerticalPositionAdjustmentSuper(getSuper(), this, setBackgroundVerticalPositionAdjustment$forBarMetrics$, adjustment, barMetrics); } else { objc_setBackgroundVerticalPositionAdjustment(this, setBackgroundVerticalPositionAdjustment$forBarMetrics$, adjustment, barMetrics); }
    }
    
    private static final Selector setTitlePositionAdjustment$forBarMetrics$ = Selector.register("setTitlePositionAdjustment:forBarMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTitlePositionAdjustment(UIBarButtonItem __self__, Selector __cmd__, UIOffset adjustment, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTitlePositionAdjustmentSuper(ObjCSuper __super__, UIBarButtonItem __self__, Selector __cmd__, UIOffset adjustment, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarButtonItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarButtonItem/setTitlePositionAdjustment:forBarMetrics:">- (void)setTitlePositionAdjustment:(UIOffset)adjustment forBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setTitlePositionAdjustment(UIOffset adjustment, UIBarMetrics barMetrics) {
        if (customClass) { objc_setTitlePositionAdjustmentSuper(getSuper(), this, setTitlePositionAdjustment$forBarMetrics$, adjustment, barMetrics); } else { objc_setTitlePositionAdjustment(this, setTitlePositionAdjustment$forBarMetrics$, adjustment, barMetrics); }
    }
    /*</methods>*/

}
