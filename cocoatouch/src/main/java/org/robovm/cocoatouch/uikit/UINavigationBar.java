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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html">UINavigationBar Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UINavigationBar /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UINavigationBar /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UINavigationBar /*</name>*/.class);

    /*<constructors>*/
    protected UINavigationBar(SkipInit skipInit) { super(skipInit); }
    public UINavigationBar() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/backItem">@property(nonatomic, readonly, retain) UINavigationItem *backItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("backItem") public native UINavigationItem getBackItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/barStyle">@property(nonatomic, assign) UIBarStyle barStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("barStyle") public native UIBarStyle getBarStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/barStyle">@property(nonatomic, assign) UIBarStyle barStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBarStyle:") public native void setBarStyle(UIBarStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/delegate">@property(nonatomic, assign) id delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native NSObject getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/delegate">@property(nonatomic, assign) id delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(NSObject v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/items">@property(nonatomic, copy) NSArray *items</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("items") public native NSArray getItems();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/items">@property(nonatomic, copy) NSArray *items</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setItems:") public native void setItems(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/shadowImage">@property(nonatomic,retain) UIImage *shadowImage</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("shadowImage") public native UIImage getShadowImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/shadowImage">@property(nonatomic,retain) UIImage *shadowImage</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setShadowImage:") public native void setShadowImage(UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tintColor") public native UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/titleTextAttributes">@property(nonatomic, copy) NSDictionary *titleTextAttributes</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("titleTextAttributes") public native NSDictionary getTitleTextAttributes();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/titleTextAttributes">@property(nonatomic, copy) NSDictionary *titleTextAttributes</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setTitleTextAttributes:") public native void setTitleTextAttributes(NSDictionary v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/topItem">@property(nonatomic, readonly, retain) UINavigationItem *topItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("topItem") public native UINavigationItem getTopItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/translucent">@property(nonatomic, assign, getter=isTranslucent) BOOL translucent</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isTranslucent") public native boolean isTranslucent();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instp/UINavigationBar/translucent">@property(nonatomic, assign, getter=isTranslucent) BOOL translucent</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setTranslucent:") public native void setTranslucent(boolean v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector backgroundImageForBarMetrics$ = Selector.register("backgroundImageForBarMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getBackgroundImage(UINavigationBar __self__, Selector __cmd__, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getBackgroundImageSuper(ObjCSuper __super__, UINavigationBar __self__, Selector __cmd__, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/backgroundImageForBarMetrics:">- (UIImage *)backgroundImageForBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getBackgroundImage(UIBarMetrics barMetrics) {
        if (customClass) { return objc_getBackgroundImageSuper(getSuper(), this, backgroundImageForBarMetrics$, barMetrics); } else { return objc_getBackgroundImage(this, backgroundImageForBarMetrics$, barMetrics); }
    }
    
    private static final Selector titleVerticalPositionAdjustmentForBarMetrics$ = Selector.register("titleVerticalPositionAdjustmentForBarMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getTitleVerticalPositionAdjustment(UINavigationBar __self__, Selector __cmd__, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getTitleVerticalPositionAdjustmentSuper(ObjCSuper __super__, UINavigationBar __self__, Selector __cmd__, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/titleVerticalPositionAdjustmentForBarMetrics:">- (CGFloat)titleVerticalPositionAdjustmentForBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public float getTitleVerticalPositionAdjustment(UIBarMetrics barMetrics) {
        if (customClass) { return objc_getTitleVerticalPositionAdjustmentSuper(getSuper(), this, titleVerticalPositionAdjustmentForBarMetrics$, barMetrics); } else { return objc_getTitleVerticalPositionAdjustment(this, titleVerticalPositionAdjustmentForBarMetrics$, barMetrics); }
    }
    
    private static final Selector popNavigationItemAnimated$ = Selector.register("popNavigationItemAnimated:");
    @Bridge(symbol = "objc_msgSend") private native static UINavigationItem objc_popNavigationItem(UINavigationBar __self__, Selector __cmd__, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static UINavigationItem objc_popNavigationItemSuper(ObjCSuper __super__, UINavigationBar __self__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/popNavigationItemAnimated:">- (UINavigationItem *)popNavigationItemAnimated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public UINavigationItem popNavigationItem(boolean animated) {
        if (customClass) { return objc_popNavigationItemSuper(getSuper(), this, popNavigationItemAnimated$, animated); } else { return objc_popNavigationItem(this, popNavigationItemAnimated$, animated); }
    }
    
    private static final Selector pushNavigationItem$animated$ = Selector.register("pushNavigationItem:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_pushNavigationItem(UINavigationBar __self__, Selector __cmd__, UINavigationItem item, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_pushNavigationItemSuper(ObjCSuper __super__, UINavigationBar __self__, Selector __cmd__, UINavigationItem item, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/pushNavigationItem:animated:">- (void)pushNavigationItem:(UINavigationItem *)item animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void pushNavigationItem(UINavigationItem item, boolean animated) {
        if (customClass) { objc_pushNavigationItemSuper(getSuper(), this, pushNavigationItem$animated$, item, animated); } else { objc_pushNavigationItem(this, pushNavigationItem$animated$, item, animated); }
    }
    
    private static final Selector setBackgroundImage$forBarMetrics$ = Selector.register("setBackgroundImage:forBarMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setBackgroundImage(UINavigationBar __self__, Selector __cmd__, UIImage backgroundImage, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setBackgroundImageSuper(ObjCSuper __super__, UINavigationBar __self__, Selector __cmd__, UIImage backgroundImage, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/setBackgroundImage:forBarMetrics:">- (void)setBackgroundImage:(UIImage *)backgroundImage forBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setBackgroundImage(UIImage backgroundImage, UIBarMetrics barMetrics) {
        if (customClass) { objc_setBackgroundImageSuper(getSuper(), this, setBackgroundImage$forBarMetrics$, backgroundImage, barMetrics); } else { objc_setBackgroundImage(this, setBackgroundImage$forBarMetrics$, backgroundImage, barMetrics); }
    }
    
    private static final Selector setItems$animated$ = Selector.register("setItems:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setItems(UINavigationBar __self__, Selector __cmd__, NSArray items, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setItemsSuper(ObjCSuper __super__, UINavigationBar __self__, Selector __cmd__, NSArray items, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/setItems:animated:">- (void)setItems:(NSArray *)items animated:(BOOL)animated</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setItems(NSArray items, boolean animated) {
        if (customClass) { objc_setItemsSuper(getSuper(), this, setItems$animated$, items, animated); } else { objc_setItems(this, setItems$animated$, items, animated); }
    }
    
    private static final Selector setTitleVerticalPositionAdjustment$forBarMetrics$ = Selector.register("setTitleVerticalPositionAdjustment:forBarMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTitleVerticalPositionAdjustment(UINavigationBar __self__, Selector __cmd__, float adjustment, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTitleVerticalPositionAdjustmentSuper(ObjCSuper __super__, UINavigationBar __self__, Selector __cmd__, float adjustment, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationBar_Class/Reference/UINavigationBar.html#//apple_ref/occ/instm/UINavigationBar/setTitleVerticalPositionAdjustment:forBarMetrics:">- (void)setTitleVerticalPositionAdjustment:(CGFloat)adjustment forBarMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setTitleVerticalPositionAdjustment(float adjustment, UIBarMetrics barMetrics) {
        if (customClass) { objc_setTitleVerticalPositionAdjustmentSuper(getSuper(), this, setTitleVerticalPositionAdjustment$forBarMetrics$, adjustment, barMetrics); } else { objc_setTitleVerticalPositionAdjustment(this, setTitleVerticalPositionAdjustment$forBarMetrics$, adjustment, barMetrics); }
    }
    /*</methods>*/

}
