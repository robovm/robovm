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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html">UITabBar Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UITabBar /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITabBar /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UITabBar /*</name>*/.class);

    public UITabBar(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UITabBar(SkipInit skipInit) { super(skipInit); }
    public UITabBar() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector backgroundImage = Selector.register("backgroundImage");
    @Bridge private native static UIImage objc_getBackgroundImage(UITabBar __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/backgroundImage">@property(nonatomic, retain) UIImage *backgroundImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getBackgroundImage() {
        if (customClass) { return objc_getBackgroundImageSuper(getSuper(), backgroundImage); } else { return objc_getBackgroundImage(this, backgroundImage); }
    }
    
    private static final Selector setBackgroundImage$ = Selector.register("setBackgroundImage:");
    @Bridge private native static void objc_setBackgroundImage(UITabBar __self__, Selector __cmd__, UIImage backgroundImage);
    @Bridge private native static void objc_setBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage backgroundImage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/backgroundImage">@property(nonatomic, retain) UIImage *backgroundImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setBackgroundImage(UIImage backgroundImage) {
        if (customClass) { objc_setBackgroundImageSuper(getSuper(), setBackgroundImage$, backgroundImage); } else { objc_setBackgroundImage(this, setBackgroundImage$, backgroundImage); }
    }
    
    private static final Selector delegate = Selector.register("delegate");
    @Bridge private native static UITabBarDelegate objc_getDelegate(UITabBar __self__, Selector __cmd__);
    @Bridge private native static UITabBarDelegate objc_getDelegateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/delegate">@property(nonatomic, assign) id&amp;lt;UITabBarDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITabBarDelegate getDelegate() {
        if (customClass) { return objc_getDelegateSuper(getSuper(), delegate); } else { return objc_getDelegate(this, delegate); }
    }
    
    private static final Selector setDelegate$ = Selector.register("setDelegate:");
    @Bridge private native static void objc_setDelegate(UITabBar __self__, Selector __cmd__, UITabBarDelegate delegate);
    @Bridge private native static void objc_setDelegateSuper(ObjCSuper __super__, Selector __cmd__, UITabBarDelegate delegate);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/delegate">@property(nonatomic, assign) id&amp;lt;UITabBarDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setDelegate(UITabBarDelegate delegate) {
        if (customClass) { objc_setDelegateSuper(getSuper(), setDelegate$, delegate); } else { objc_setDelegate(this, setDelegate$, delegate); }
    }
    
    private static final Selector items = Selector.register("items");
    @Bridge private native static NSArray objc_getItems(UITabBar __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getItemsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/items">@property(nonatomic, copy) NSArray *items</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray getItems() {
        if (customClass) { return objc_getItemsSuper(getSuper(), items); } else { return objc_getItems(this, items); }
    }
    
    private static final Selector setItems$ = Selector.register("setItems:");
    @Bridge private native static void objc_setItems(UITabBar __self__, Selector __cmd__, NSArray items);
    @Bridge private native static void objc_setItemsSuper(ObjCSuper __super__, Selector __cmd__, NSArray items);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/items">@property(nonatomic, copy) NSArray *items</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setItems(NSArray items) {
        if (customClass) { objc_setItemsSuper(getSuper(), setItems$, items); } else { objc_setItems(this, setItems$, items); }
    }
    
    private static final Selector selectedImageTintColor = Selector.register("selectedImageTintColor");
    @Bridge private native static UIColor objc_getSelectedImageTintColor(UITabBar __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getSelectedImageTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/selectedImageTintColor">@property(nonatomic, retain) UIColor *selectedImageTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIColor getSelectedImageTintColor() {
        if (customClass) { return objc_getSelectedImageTintColorSuper(getSuper(), selectedImageTintColor); } else { return objc_getSelectedImageTintColor(this, selectedImageTintColor); }
    }
    
    private static final Selector setSelectedImageTintColor$ = Selector.register("setSelectedImageTintColor:");
    @Bridge private native static void objc_setSelectedImageTintColor(UITabBar __self__, Selector __cmd__, UIColor selectedImageTintColor);
    @Bridge private native static void objc_setSelectedImageTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor selectedImageTintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/selectedImageTintColor">@property(nonatomic, retain) UIColor *selectedImageTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setSelectedImageTintColor(UIColor selectedImageTintColor) {
        if (customClass) { objc_setSelectedImageTintColorSuper(getSuper(), setSelectedImageTintColor$, selectedImageTintColor); } else { objc_setSelectedImageTintColor(this, setSelectedImageTintColor$, selectedImageTintColor); }
    }
    
    private static final Selector selectedItem = Selector.register("selectedItem");
    @Bridge private native static UITabBarItem objc_getSelectedItem(UITabBar __self__, Selector __cmd__);
    @Bridge private native static UITabBarItem objc_getSelectedItemSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/selectedItem">@property(nonatomic, assign) UITabBarItem *selectedItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITabBarItem getSelectedItem() {
        if (customClass) { return objc_getSelectedItemSuper(getSuper(), selectedItem); } else { return objc_getSelectedItem(this, selectedItem); }
    }
    
    private static final Selector setSelectedItem$ = Selector.register("setSelectedItem:");
    @Bridge private native static void objc_setSelectedItem(UITabBar __self__, Selector __cmd__, UITabBarItem selectedItem);
    @Bridge private native static void objc_setSelectedItemSuper(ObjCSuper __super__, Selector __cmd__, UITabBarItem selectedItem);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/selectedItem">@property(nonatomic, assign) UITabBarItem *selectedItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setSelectedItem(UITabBarItem selectedItem) {
        if (customClass) { objc_setSelectedItemSuper(getSuper(), setSelectedItem$, selectedItem); } else { objc_setSelectedItem(this, setSelectedItem$, selectedItem); }
    }
    
    private static final Selector selectionIndicatorImage = Selector.register("selectionIndicatorImage");
    @Bridge private native static UIImage objc_getSelectionIndicatorImage(UITabBar __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getSelectionIndicatorImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/selectionIndicatorImage">@property(nonatomic, retain) UIImage *selectionIndicatorImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getSelectionIndicatorImage() {
        if (customClass) { return objc_getSelectionIndicatorImageSuper(getSuper(), selectionIndicatorImage); } else { return objc_getSelectionIndicatorImage(this, selectionIndicatorImage); }
    }
    
    private static final Selector setSelectionIndicatorImage$ = Selector.register("setSelectionIndicatorImage:");
    @Bridge private native static void objc_setSelectionIndicatorImage(UITabBar __self__, Selector __cmd__, UIImage selectionIndicatorImage);
    @Bridge private native static void objc_setSelectionIndicatorImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage selectionIndicatorImage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/selectionIndicatorImage">@property(nonatomic, retain) UIImage *selectionIndicatorImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setSelectionIndicatorImage(UIImage selectionIndicatorImage) {
        if (customClass) { objc_setSelectionIndicatorImageSuper(getSuper(), setSelectionIndicatorImage$, selectionIndicatorImage); } else { objc_setSelectionIndicatorImage(this, setSelectionIndicatorImage$, selectionIndicatorImage); }
    }
    
    private static final Selector shadowImage = Selector.register("shadowImage");
    @Bridge private native static UIImage objc_getShadowImage(UITabBar __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getShadowImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/shadowImage">@property(nonatomic,retain) UIImage *shadowImage</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage getShadowImage() {
        if (customClass) { return objc_getShadowImageSuper(getSuper(), shadowImage); } else { return objc_getShadowImage(this, shadowImage); }
    }
    
    private static final Selector setShadowImage$ = Selector.register("setShadowImage:");
    @Bridge private native static void objc_setShadowImage(UITabBar __self__, Selector __cmd__, UIImage shadowImage);
    @Bridge private native static void objc_setShadowImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage shadowImage);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/shadowImage">@property(nonatomic,retain) UIImage *shadowImage</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setShadowImage(UIImage shadowImage) {
        if (customClass) { objc_setShadowImageSuper(getSuper(), setShadowImage$, shadowImage); } else { objc_setShadowImage(this, setShadowImage$, shadowImage); }
    }
    
    private static final Selector tintColor = Selector.register("tintColor");
    @Bridge private native static UIColor objc_getTintColor(UITabBar __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIColor getTintColor() {
        if (customClass) { return objc_getTintColorSuper(getSuper(), tintColor); } else { return objc_getTintColor(this, tintColor); }
    }
    
    private static final Selector setTintColor$ = Selector.register("setTintColor:");
    @Bridge private native static void objc_setTintColor(UITabBar __self__, Selector __cmd__, UIColor tintColor);
    @Bridge private native static void objc_setTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor tintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setTintColor(UIColor tintColor) {
        if (customClass) { objc_setTintColorSuper(getSuper(), setTintColor$, tintColor); } else { objc_setTintColor(this, setTintColor$, tintColor); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector beginCustomizingItems$ = Selector.register("beginCustomizingItems:");
    @Bridge private native static void objc_beginCustomizing(UITabBar __self__, Selector __cmd__, NSArray items);
    @Bridge private native static void objc_beginCustomizingSuper(ObjCSuper __super__, Selector __cmd__, NSArray items);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBar/beginCustomizingItems:">- (void)beginCustomizingItems:(NSArray *)items</a>
     * @since Available in iOS 2.0 and later.
     */
    public void beginCustomizing(NSArray items) {
        if (customClass) { objc_beginCustomizingSuper(getSuper(), beginCustomizingItems$, items); } else { objc_beginCustomizing(this, beginCustomizingItems$, items); }
    }
    
    private static final Selector endCustomizingAnimated$ = Selector.register("endCustomizingAnimated:");
    @Bridge private native static boolean objc_endCustomizing(UITabBar __self__, Selector __cmd__, boolean animated);
    @Bridge private native static boolean objc_endCustomizingSuper(ObjCSuper __super__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBar/endCustomizingAnimated:">- (BOOL)endCustomizingAnimated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean endCustomizing(boolean animated) {
        if (customClass) { return objc_endCustomizingSuper(getSuper(), endCustomizingAnimated$, animated); } else { return objc_endCustomizing(this, endCustomizingAnimated$, animated); }
    }
    
    private static final Selector isCustomizing = Selector.register("isCustomizing");
    @Bridge private native static boolean objc_isCustomizing(UITabBar __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isCustomizingSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBar/isCustomizing">- (BOOL)isCustomizing</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isCustomizing() {
        if (customClass) { return objc_isCustomizingSuper(getSuper(), isCustomizing); } else { return objc_isCustomizing(this, isCustomizing); }
    }
    
    private static final Selector setItems$animated$ = Selector.register("setItems:animated:");
    @Bridge private native static void objc_setItems(UITabBar __self__, Selector __cmd__, NSArray items, boolean animated);
    @Bridge private native static void objc_setItemsSuper(ObjCSuper __super__, Selector __cmd__, NSArray items, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBar/setItems:animated:">- (void)setItems:(NSArray *)items animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setItems(NSArray items, boolean animated) {
        if (customClass) { objc_setItemsSuper(getSuper(), setItems$animated$, items, animated); } else { objc_setItems(this, setItems$animated$, items, animated); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("backgroundImage") public static UIImage getBackgroundImage(UITabBar __self__, Selector __cmd__) { return __self__.getBackgroundImage(); }
        @Callback @BindSelector("setBackgroundImage:") public static void setBackgroundImage(UITabBar __self__, Selector __cmd__, UIImage backgroundImage) { __self__.setBackgroundImage(backgroundImage); }
        @Callback @BindSelector("delegate") public static UITabBarDelegate getDelegate(UITabBar __self__, Selector __cmd__) { return __self__.getDelegate(); }
        @Callback @BindSelector("setDelegate:") public static void setDelegate(UITabBar __self__, Selector __cmd__, UITabBarDelegate delegate) { __self__.setDelegate(delegate); }
        @Callback @BindSelector("items") public static NSArray getItems(UITabBar __self__, Selector __cmd__) { return __self__.getItems(); }
        @Callback @BindSelector("setItems:") public static void setItems(UITabBar __self__, Selector __cmd__, NSArray items) { __self__.setItems(items); }
        @Callback @BindSelector("selectedImageTintColor") public static UIColor getSelectedImageTintColor(UITabBar __self__, Selector __cmd__) { return __self__.getSelectedImageTintColor(); }
        @Callback @BindSelector("setSelectedImageTintColor:") public static void setSelectedImageTintColor(UITabBar __self__, Selector __cmd__, UIColor selectedImageTintColor) { __self__.setSelectedImageTintColor(selectedImageTintColor); }
        @Callback @BindSelector("selectedItem") public static UITabBarItem getSelectedItem(UITabBar __self__, Selector __cmd__) { return __self__.getSelectedItem(); }
        @Callback @BindSelector("setSelectedItem:") public static void setSelectedItem(UITabBar __self__, Selector __cmd__, UITabBarItem selectedItem) { __self__.setSelectedItem(selectedItem); }
        @Callback @BindSelector("selectionIndicatorImage") public static UIImage getSelectionIndicatorImage(UITabBar __self__, Selector __cmd__) { return __self__.getSelectionIndicatorImage(); }
        @Callback @BindSelector("setSelectionIndicatorImage:") public static void setSelectionIndicatorImage(UITabBar __self__, Selector __cmd__, UIImage selectionIndicatorImage) { __self__.setSelectionIndicatorImage(selectionIndicatorImage); }
        @Callback @BindSelector("shadowImage") public static UIImage getShadowImage(UITabBar __self__, Selector __cmd__) { return __self__.getShadowImage(); }
        @Callback @BindSelector("setShadowImage:") public static void setShadowImage(UITabBar __self__, Selector __cmd__, UIImage shadowImage) { __self__.setShadowImage(shadowImage); }
        @Callback @BindSelector("tintColor") public static UIColor getTintColor(UITabBar __self__, Selector __cmd__) { return __self__.getTintColor(); }
        @Callback @BindSelector("setTintColor:") public static void setTintColor(UITabBar __self__, Selector __cmd__, UIColor tintColor) { __self__.setTintColor(tintColor); }
        @Callback @BindSelector("beginCustomizingItems:") public static void beginCustomizing(UITabBar __self__, Selector __cmd__, NSArray items) { __self__.beginCustomizing(items); }
        @Callback @BindSelector("endCustomizingAnimated:") public static boolean endCustomizing(UITabBar __self__, Selector __cmd__, boolean animated) { return __self__.endCustomizing(animated); }
        @Callback @BindSelector("isCustomizing") public static boolean isCustomizing(UITabBar __self__, Selector __cmd__) { return __self__.isCustomizing(); }
        @Callback @BindSelector("setItems:animated:") public static void setItems(UITabBar __self__, Selector __cmd__, NSArray items, boolean animated) { __self__.setItems(items, animated); }
    }
    /*</callbacks>*/

}
