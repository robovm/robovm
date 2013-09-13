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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html">UINavigationBar Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UINavigationBar /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UINavigationBar /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UINavigationBar /*</name>*/.class);

    public UINavigationBar(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UINavigationBar(SkipInit skipInit) { super(skipInit); }
    public UINavigationBar() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector backItem = Selector.register("backItem");
    @Bridge private native static UINavigationItem objc_getBackItem(UINavigationBar __self__, Selector __cmd__);
    @Bridge private native static UINavigationItem objc_getBackItemSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/backItem">@property(nonatomic, readonly, retain) UINavigationItem *backItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public UINavigationItem getBackItem() {
        if (customClass) { return objc_getBackItemSuper(getSuper(), backItem); } else { return objc_getBackItem(this, backItem); }
    }
    
    private static final Selector barStyle = Selector.register("barStyle");
    @Bridge private native static UIBarStyle objc_getBarStyle(UINavigationBar __self__, Selector __cmd__);
    @Bridge private native static UIBarStyle objc_getBarStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/barStyle">@property(nonatomic, assign) UIBarStyle barStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarStyle getBarStyle() {
        if (customClass) { return objc_getBarStyleSuper(getSuper(), barStyle); } else { return objc_getBarStyle(this, barStyle); }
    }
    
    private static final Selector setBarStyle$ = Selector.register("setBarStyle:");
    @Bridge private native static void objc_setBarStyle(UINavigationBar __self__, Selector __cmd__, UIBarStyle barStyle);
    @Bridge private native static void objc_setBarStyleSuper(ObjCSuper __super__, Selector __cmd__, UIBarStyle barStyle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/barStyle">@property(nonatomic, assign) UIBarStyle barStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setBarStyle(UIBarStyle barStyle) {
        if (customClass) { objc_setBarStyleSuper(getSuper(), setBarStyle$, barStyle); } else { objc_setBarStyle(this, setBarStyle$, barStyle); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static NSObject objc_getDelegate(UINavigationBar __self__, Selector __cmd__);
    @Bridge private native static NSObject objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/delegate">@property(nonatomic, assign) id delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSObject getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UINavigationBar __self__, Selector __cmd__, NSObject delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, NSObject delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/delegate">@property(nonatomic, assign) id delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDelegate(NSObject delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector items = Selector.register("items");
    @Bridge private native static NSArray objc_getItems(UINavigationBar __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getItemsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/items">@property(nonatomic, copy) NSArray *items</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray getItems() {
        if (customClass) { return objc_getItemsSuper(getSuper(), items); } else { return objc_getItems(this, items); }
    }
    
    private static final Selector setItems$ = Selector.register("setItems:");
    @Bridge private native static void objc_setItems(UINavigationBar __self__, Selector __cmd__, NSArray items);
    @Bridge private native static void objc_setItemsSuper(ObjCSuper __super__, Selector __cmd__, NSArray items);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/items">@property(nonatomic, copy) NSArray *items</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setItems(NSArray items) {
        if (customClass) { objc_setItemsSuper(getSuper(), setItems$, items); } else { objc_setItems(this, setItems$, items); }
    }
    
    private static final Selector shadowImage = Selector.register("shadowImage");
    @Bridge private native static UIImage objc_getShadowImage(UINavigationBar __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getShadowImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/shadowImage">@property(nonatomic,retain) UIImage *shadowImage</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage getShadowImage() {
        if (customClass) { return objc_getShadowImageSuper(getSuper(), shadowImage); } else { return objc_getShadowImage(this, shadowImage); }
    }
    
    private static final Selector setShadowImage$ = Selector.register("setShadowImage:");
    @Bridge private native static void objc_setShadowImage(UINavigationBar __self__, Selector __cmd__, UIImage shadowImage);
    @Bridge private native static void objc_setShadowImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage shadowImage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/shadowImage">@property(nonatomic,retain) UIImage *shadowImage</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setShadowImage(UIImage shadowImage) {
        if (customClass) { objc_setShadowImageSuper(getSuper(), setShadowImage$, shadowImage); } else { objc_setShadowImage(this, setShadowImage$, shadowImage); }
    }
    
    private static final Selector tintColor = Selector.register("tintColor");
    @Bridge private native static UIColor objc_getTintColor(UINavigationBar __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor getTintColor() {
        if (customClass) { return objc_getTintColorSuper(getSuper(), tintColor); } else { return objc_getTintColor(this, tintColor); }
    }
    
    private static final Selector setTintColor$ = Selector.register("setTintColor:");
    @Bridge private native static void objc_setTintColor(UINavigationBar __self__, Selector __cmd__, UIColor tintColor);
    @Bridge private native static void objc_setTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor tintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTintColor(UIColor tintColor) {
        if (customClass) { objc_setTintColorSuper(getSuper(), setTintColor$, tintColor); } else { objc_setTintColor(this, setTintColor$, tintColor); }
    }
    
    private static final Selector titleTextAttributes = Selector.register("titleTextAttributes");
    @Bridge private native static NSDictionary objc_getTitleTextAttributes(UINavigationBar __self__, Selector __cmd__);
    @Bridge private native static NSDictionary objc_getTitleTextAttributesSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/titleTextAttributes">@property(nonatomic, copy) NSDictionary *titleTextAttributes</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSDictionary getTitleTextAttributes() {
        if (customClass) { return objc_getTitleTextAttributesSuper(getSuper(), titleTextAttributes); } else { return objc_getTitleTextAttributes(this, titleTextAttributes); }
    }
    
    private static final Selector setTitleTextAttributes$ = Selector.register("setTitleTextAttributes:");
    @Bridge private native static void objc_setTitleTextAttributes(UINavigationBar __self__, Selector __cmd__, NSDictionary titleTextAttributes);
    @Bridge private native static void objc_setTitleTextAttributesSuper(ObjCSuper __super__, Selector __cmd__, NSDictionary titleTextAttributes);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/titleTextAttributes">@property(nonatomic, copy) NSDictionary *titleTextAttributes</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setTitleTextAttributes(NSDictionary titleTextAttributes) {
        if (customClass) { objc_setTitleTextAttributesSuper(getSuper(), setTitleTextAttributes$, titleTextAttributes); } else { objc_setTitleTextAttributes(this, setTitleTextAttributes$, titleTextAttributes); }
    }
    
    private static final Selector topItem = Selector.register("topItem");
    @Bridge private native static UINavigationItem objc_getTopItem(UINavigationBar __self__, Selector __cmd__);
    @Bridge private native static UINavigationItem objc_getTopItemSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/topItem">@property(nonatomic, readonly, retain) UINavigationItem *topItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public UINavigationItem getTopItem() {
        if (customClass) { return objc_getTopItemSuper(getSuper(), topItem); } else { return objc_getTopItem(this, topItem); }
    }
    
    private static final Selector isTranslucent = Selector.register("isTranslucent");
    @Bridge private native static boolean objc_isTranslucent(UINavigationBar __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isTranslucentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/translucent">@property(nonatomic, assign, getter=isTranslucent) BOOL translucent</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isTranslucent() {
        if (customClass) { return objc_isTranslucentSuper(getSuper(), isTranslucent); } else { return objc_isTranslucent(this, isTranslucent); }
    }
    
    private static final Selector setTranslucent$ = Selector.register("setTranslucent:");
    @Bridge private native static void objc_setTranslucent(UINavigationBar __self__, Selector __cmd__, boolean translucent);
    @Bridge private native static void objc_setTranslucentSuper(ObjCSuper __super__, Selector __cmd__, boolean translucent);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/translucent">@property(nonatomic, assign, getter=isTranslucent) BOOL translucent</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setTranslucent(boolean translucent) {
        if (customClass) { objc_setTranslucentSuper(getSuper(), setTranslucent$, translucent); } else { objc_setTranslucent(this, setTranslucent$, translucent); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector backgroundImageForBarMetrics$ = Selector.register("backgroundImageForBarMetrics:");
    @Bridge private native static UIImage objc_getBackgroundImage(UINavigationBar __self__, Selector __cmd__, UIBarMetrics barMetrics);
    @Bridge private native static UIImage objc_getBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/backgroundImageForBarMetrics:">- (UIImage *)backgroundImageForBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getBackgroundImage(UIBarMetrics barMetrics) {
        if (customClass) { return objc_getBackgroundImageSuper(getSuper(), backgroundImageForBarMetrics$, barMetrics); } else { return objc_getBackgroundImage(this, backgroundImageForBarMetrics$, barMetrics); }
    }
    
    private static final Selector titleVerticalPositionAdjustmentForBarMetrics$ = Selector.register("titleVerticalPositionAdjustmentForBarMetrics:");
    @Bridge private native static float objc_getTitleVerticalPositionAdjustment(UINavigationBar __self__, Selector __cmd__, UIBarMetrics barMetrics);
    @Bridge private native static float objc_getTitleVerticalPositionAdjustmentSuper(ObjCSuper __super__, Selector __cmd__, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/titleVerticalPositionAdjustmentForBarMetrics:">- (CGFloat)titleVerticalPositionAdjustmentForBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public float getTitleVerticalPositionAdjustment(UIBarMetrics barMetrics) {
        if (customClass) { return objc_getTitleVerticalPositionAdjustmentSuper(getSuper(), titleVerticalPositionAdjustmentForBarMetrics$, barMetrics); } else { return objc_getTitleVerticalPositionAdjustment(this, titleVerticalPositionAdjustmentForBarMetrics$, barMetrics); }
    }
    
    private static final Selector popNavigationItemAnimated$ = Selector.register("popNavigationItemAnimated:");
    @Bridge private native static UINavigationItem objc_popNavigationItem(UINavigationBar __self__, Selector __cmd__, boolean animated);
    @Bridge private native static UINavigationItem objc_popNavigationItemSuper(ObjCSuper __super__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/popNavigationItemAnimated:">- (UINavigationItem *)popNavigationItemAnimated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public UINavigationItem popNavigationItem(boolean animated) {
        if (customClass) { return objc_popNavigationItemSuper(getSuper(), popNavigationItemAnimated$, animated); } else { return objc_popNavigationItem(this, popNavigationItemAnimated$, animated); }
    }
    
    private static final Selector pushNavigationItem$animated$ = Selector.register("pushNavigationItem:animated:");
    @Bridge private native static void objc_pushNavigationItem(UINavigationBar __self__, Selector __cmd__, UINavigationItem item, boolean animated);
    @Bridge private native static void objc_pushNavigationItemSuper(ObjCSuper __super__, Selector __cmd__, UINavigationItem item, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/pushNavigationItem:animated:">- (void)pushNavigationItem:(UINavigationItem *)item animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void pushNavigationItem(UINavigationItem item, boolean animated) {
        if (customClass) { objc_pushNavigationItemSuper(getSuper(), pushNavigationItem$animated$, item, animated); } else { objc_pushNavigationItem(this, pushNavigationItem$animated$, item, animated); }
    }
    
    private static final Selector setBackgroundImage$forBarMetrics$ = Selector.register("setBackgroundImage:forBarMetrics:");
    @Bridge private native static void objc_setBackgroundImage(UINavigationBar __self__, Selector __cmd__, UIImage backgroundImage, UIBarMetrics barMetrics);
    @Bridge private native static void objc_setBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage backgroundImage, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/setBackgroundImage:forBarMetrics:">- (void)setBackgroundImage:(UIImage *)backgroundImage forBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setBackgroundImage(UIImage backgroundImage, UIBarMetrics barMetrics) {
        if (customClass) { objc_setBackgroundImageSuper(getSuper(), setBackgroundImage$forBarMetrics$, backgroundImage, barMetrics); } else { objc_setBackgroundImage(this, setBackgroundImage$forBarMetrics$, backgroundImage, barMetrics); }
    }
    
    private static final Selector setItems$animated$ = Selector.register("setItems:animated:");
    @Bridge private native static void objc_setItems(UINavigationBar __self__, Selector __cmd__, NSArray items, boolean animated);
    @Bridge private native static void objc_setItemsSuper(ObjCSuper __super__, Selector __cmd__, NSArray items, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/setItems:animated:">- (void)setItems:(NSArray *)items animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setItems(NSArray items, boolean animated) {
        if (customClass) { objc_setItemsSuper(getSuper(), setItems$animated$, items, animated); } else { objc_setItems(this, setItems$animated$, items, animated); }
    }
    
    private static final Selector setTitleVerticalPositionAdjustment$forBarMetrics$ = Selector.register("setTitleVerticalPositionAdjustment:forBarMetrics:");
    @Bridge private native static void objc_setTitleVerticalPositionAdjustment(UINavigationBar __self__, Selector __cmd__, float adjustment, UIBarMetrics barMetrics);
    @Bridge private native static void objc_setTitleVerticalPositionAdjustmentSuper(ObjCSuper __super__, Selector __cmd__, float adjustment, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/setTitleVerticalPositionAdjustment:forBarMetrics:">- (void)setTitleVerticalPositionAdjustment:(CGFloat)adjustment forBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setTitleVerticalPositionAdjustment(float adjustment, UIBarMetrics barMetrics) {
        if (customClass) { objc_setTitleVerticalPositionAdjustmentSuper(getSuper(), setTitleVerticalPositionAdjustment$forBarMetrics$, adjustment, barMetrics); } else { objc_setTitleVerticalPositionAdjustment(this, setTitleVerticalPositionAdjustment$forBarMetrics$, adjustment, barMetrics); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("backItem") public static UINavigationItem getBackItem(UINavigationBar __self__, Selector __cmd__) { return __self__.getBackItem(); }
        @Callback @BindSelector("barStyle") public static UIBarStyle getBarStyle(UINavigationBar __self__, Selector __cmd__) { return __self__.getBarStyle(); }
        @Callback @BindSelector("setBarStyle:") public static void setBarStyle(UINavigationBar __self__, Selector __cmd__, UIBarStyle barStyle) { __self__.setBarStyle(barStyle); }
        @Callback @BindSelector("delegate") public static NSObject getDelegate(UINavigationBar __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UINavigationBar __self__, Selector __cmd__, NSObject delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("items") public static NSArray getItems(UINavigationBar __self__, Selector __cmd__) { return __self__.getItems(); }
        @Callback @BindSelector("setItems:") public static void setItems(UINavigationBar __self__, Selector __cmd__, NSArray items) { __self__.setItems(items); }
        @Callback @BindSelector("shadowImage") public static UIImage getShadowImage(UINavigationBar __self__, Selector __cmd__) { return __self__.getShadowImage(); }
        @Callback @BindSelector("setShadowImage:") public static void setShadowImage(UINavigationBar __self__, Selector __cmd__, UIImage shadowImage) { __self__.setShadowImage(shadowImage); }
        @Callback @BindSelector("tintColor") public static UIColor getTintColor(UINavigationBar __self__, Selector __cmd__) { return __self__.getTintColor(); }
        @Callback @BindSelector("setTintColor:") public static void setTintColor(UINavigationBar __self__, Selector __cmd__, UIColor tintColor) { __self__.setTintColor(tintColor); }
        @Callback @BindSelector("titleTextAttributes") public static NSDictionary getTitleTextAttributes(UINavigationBar __self__, Selector __cmd__) { return __self__.getTitleTextAttributes(); }
        @Callback @BindSelector("setTitleTextAttributes:") public static void setTitleTextAttributes(UINavigationBar __self__, Selector __cmd__, NSDictionary titleTextAttributes) { __self__.setTitleTextAttributes(titleTextAttributes); }
        @Callback @BindSelector("topItem") public static UINavigationItem getTopItem(UINavigationBar __self__, Selector __cmd__) { return __self__.getTopItem(); }
        @Callback @BindSelector("isTranslucent") public static boolean isTranslucent(UINavigationBar __self__, Selector __cmd__) { return __self__.isTranslucent(); }
        @Callback @BindSelector("setTranslucent:") public static void setTranslucent(UINavigationBar __self__, Selector __cmd__, boolean translucent) { __self__.setTranslucent(translucent); }
        @Callback @BindSelector("backgroundImageForBarMetrics:") public static UIImage getBackgroundImage(UINavigationBar __self__, Selector __cmd__, UIBarMetrics barMetrics) { return __self__.getBackgroundImage(barMetrics); }
        @Callback @BindSelector("titleVerticalPositionAdjustmentForBarMetrics:") public static float getTitleVerticalPositionAdjustment(UINavigationBar __self__, Selector __cmd__, UIBarMetrics barMetrics) { return __self__.getTitleVerticalPositionAdjustment(barMetrics); }
        @Callback @BindSelector("popNavigationItemAnimated:") public static UINavigationItem popNavigationItem(UINavigationBar __self__, Selector __cmd__, boolean animated) { return __self__.popNavigationItem(animated); }
        @Callback @BindSelector("pushNavigationItem:animated:") public static void pushNavigationItem(UINavigationBar __self__, Selector __cmd__, UINavigationItem item, boolean animated) { __self__.pushNavigationItem(item, animated); }
        @Callback @BindSelector("setBackgroundImage:forBarMetrics:") public static void setBackgroundImage(UINavigationBar __self__, Selector __cmd__, UIImage backgroundImage, UIBarMetrics barMetrics) { __self__.setBackgroundImage(backgroundImage, barMetrics); }
        @Callback @BindSelector("setItems:animated:") public static void setItems(UINavigationBar __self__, Selector __cmd__, NSArray items, boolean animated) { __self__.setItems(items, animated); }
        @Callback @BindSelector("setTitleVerticalPositionAdjustment:forBarMetrics:") public static void setTitleVerticalPositionAdjustment(UINavigationBar __self__, Selector __cmd__, float adjustment, UIBarMetrics barMetrics) { __self__.setTitleVerticalPositionAdjustment(adjustment, barMetrics); }
    }
    /*</callbacks>*/

}
