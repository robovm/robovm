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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html">UIToolbar Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIToolbar /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIToolbar /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIToolbar /*</name>*/.class);

    public UIToolbar(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UIToolbar(SkipInit skipInit) { super(skipInit); }
    public UIToolbar() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector barStyle = Selector.register("barStyle");
    @Bridge private native static UIBarStyle objc_getBarStyle(UIToolbar __self__, Selector __cmd__);
    @Bridge private native static UIBarStyle objc_getBarStyleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/barStyle">@property(nonatomic) UIBarStyle barStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarStyle getBarStyle() {
        if (customClass) { return objc_getBarStyleSuper(getSuper(), barStyle); } else { return objc_getBarStyle(this, barStyle); }
    }
    
    private static final Selector setBarStyle$ = Selector.register("setBarStyle:");
    @Bridge private native static void objc_setBarStyle(UIToolbar __self__, Selector __cmd__, UIBarStyle barStyle);
    @Bridge private native static void objc_setBarStyleSuper(ObjCSuper __super__, Selector __cmd__, UIBarStyle barStyle);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/barStyle">@property(nonatomic) UIBarStyle barStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setBarStyle(UIBarStyle barStyle) {
        if (customClass) { objc_setBarStyleSuper(getSuper(), setBarStyle$, barStyle); } else { objc_setBarStyle(this, setBarStyle$, barStyle); }
    }
    
    private static final Selector items = Selector.register("items");
    @Bridge private native static NSArray objc_getItems(UIToolbar __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getItemsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/items">@property(nonatomic, copy) NSArray *items</a>
     * @since Available in iOS 2.0 and later.
     */
    public NSArray getItems() {
        if (customClass) { return objc_getItemsSuper(getSuper(), items); } else { return objc_getItems(this, items); }
    }
    
    private static final Selector setItems$ = Selector.register("setItems:");
    @Bridge private native static void objc_setItems(UIToolbar __self__, Selector __cmd__, NSArray items);
    @Bridge private native static void objc_setItemsSuper(ObjCSuper __super__, Selector __cmd__, NSArray items);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/items">@property(nonatomic, copy) NSArray *items</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setItems(NSArray items) {
        if (customClass) { objc_setItemsSuper(getSuper(), setItems$, items); } else { objc_setItems(this, setItems$, items); }
    }
    
    private static final Selector tintColor = Selector.register("tintColor");
    @Bridge private native static UIColor objc_getTintColor(UIToolbar __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor getTintColor() {
        if (customClass) { return objc_getTintColorSuper(getSuper(), tintColor); } else { return objc_getTintColor(this, tintColor); }
    }
    
    private static final Selector setTintColor$ = Selector.register("setTintColor:");
    @Bridge private native static void objc_setTintColor(UIToolbar __self__, Selector __cmd__, UIColor tintColor);
    @Bridge private native static void objc_setTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor tintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTintColor(UIColor tintColor) {
        if (customClass) { objc_setTintColorSuper(getSuper(), setTintColor$, tintColor); } else { objc_setTintColor(this, setTintColor$, tintColor); }
    }
    
    private static final Selector isTranslucent = Selector.register("isTranslucent");
    @Bridge private native static boolean objc_isTranslucent(UIToolbar __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isTranslucentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/translucent">@property(nonatomic, assign, getter=isTranslucent) BOOL translucent</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isTranslucent() {
        if (customClass) { return objc_isTranslucentSuper(getSuper(), isTranslucent); } else { return objc_isTranslucent(this, isTranslucent); }
    }
    
    private static final Selector setTranslucent$ = Selector.register("setTranslucent:");
    @Bridge private native static void objc_setTranslucent(UIToolbar __self__, Selector __cmd__, boolean translucent);
    @Bridge private native static void objc_setTranslucentSuper(ObjCSuper __super__, Selector __cmd__, boolean translucent);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/translucent">@property(nonatomic, assign, getter=isTranslucent) BOOL translucent</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setTranslucent(boolean translucent) {
        if (customClass) { objc_setTranslucentSuper(getSuper(), setTranslucent$, translucent); } else { objc_setTranslucent(this, setTranslucent$, translucent); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector backgroundImageForToolbarPosition$barMetrics$ = Selector.register("backgroundImageForToolbarPosition:barMetrics:");
    @Bridge private native static UIImage objc_getBackgroundImage(UIToolbar __self__, Selector __cmd__, UIToolbarPosition topOrBottom, UIBarMetrics barMetrics);
    @Bridge private native static UIImage objc_getBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIToolbarPosition topOrBottom, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instm/UIToolbar/backgroundImageForToolbarPosition:barMetrics:">- (UIImage *)backgroundImageForToolbarPosition:(UIToolbarPosition)topOrBottom barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getBackgroundImage(UIToolbarPosition topOrBottom, UIBarMetrics barMetrics) {
        if (customClass) { return objc_getBackgroundImageSuper(getSuper(), backgroundImageForToolbarPosition$barMetrics$, topOrBottom, barMetrics); } else { return objc_getBackgroundImage(this, backgroundImageForToolbarPosition$barMetrics$, topOrBottom, barMetrics); }
    }
    
    private static final Selector shadowImageForToolbarPosition$ = Selector.register("shadowImageForToolbarPosition:");
    @Bridge private native static UIImage objc_getShadowImage(UIToolbar __self__, Selector __cmd__, UIToolbarPosition topOrBottom);
    @Bridge private native static UIImage objc_getShadowImageSuper(ObjCSuper __super__, Selector __cmd__, UIToolbarPosition topOrBottom);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instm/UIToolbar/shadowImageForToolbarPosition:">- (UIImage *)shadowImageForToolbarPosition:(UIToolbarPosition)topOrBottom</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage getShadowImage(UIToolbarPosition topOrBottom) {
        if (customClass) { return objc_getShadowImageSuper(getSuper(), shadowImageForToolbarPosition$, topOrBottom); } else { return objc_getShadowImage(this, shadowImageForToolbarPosition$, topOrBottom); }
    }
    
    private static final Selector setBackgroundImage$forToolbarPosition$barMetrics$ = Selector.register("setBackgroundImage:forToolbarPosition:barMetrics:");
    @Bridge private native static void objc_setBackgroundImage(UIToolbar __self__, Selector __cmd__, UIImage backgroundImage, UIToolbarPosition topOrBottom, UIBarMetrics barMetrics);
    @Bridge private native static void objc_setBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage backgroundImage, UIToolbarPosition topOrBottom, UIBarMetrics barMetrics);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instm/UIToolbar/setBackgroundImage:forToolbarPosition:barMetrics:">- (void)setBackgroundImage:(UIImage *)backgroundImage forToolbarPosition:(UIToolbarPosition)topOrBottom barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setBackgroundImage(UIImage backgroundImage, UIToolbarPosition topOrBottom, UIBarMetrics barMetrics) {
        if (customClass) { objc_setBackgroundImageSuper(getSuper(), setBackgroundImage$forToolbarPosition$barMetrics$, backgroundImage, topOrBottom, barMetrics); } else { objc_setBackgroundImage(this, setBackgroundImage$forToolbarPosition$barMetrics$, backgroundImage, topOrBottom, barMetrics); }
    }
    
    private static final Selector setItems$animated$ = Selector.register("setItems:animated:");
    @Bridge private native static void objc_setItems(UIToolbar __self__, Selector __cmd__, NSArray items, boolean animated);
    @Bridge private native static void objc_setItemsSuper(ObjCSuper __super__, Selector __cmd__, NSArray items, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instm/UIToolbar/setItems:animated:">- (void)setItems:(NSArray *)items animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setItems(NSArray items, boolean animated) {
        if (customClass) { objc_setItemsSuper(getSuper(), setItems$animated$, items, animated); } else { objc_setItems(this, setItems$animated$, items, animated); }
    }
    
    private static final Selector setShadowImage$forToolbarPosition$ = Selector.register("setShadowImage:forToolbarPosition:");
    @Bridge private native static void objc_setShadowImage(UIToolbar __self__, Selector __cmd__, UIImage shadowImage, UIToolbarPosition topOrBottom);
    @Bridge private native static void objc_setShadowImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage shadowImage, UIToolbarPosition topOrBottom);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instm/UIToolbar/setShadowImage:forToolbarPosition:">- (void)setShadowImage:(UIImage *)shadowImage forToolbarPosition:(UIToolbarPosition)topOrBottom</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setShadowImage(UIImage shadowImage, UIToolbarPosition topOrBottom) {
        if (customClass) { objc_setShadowImageSuper(getSuper(), setShadowImage$forToolbarPosition$, shadowImage, topOrBottom); } else { objc_setShadowImage(this, setShadowImage$forToolbarPosition$, shadowImage, topOrBottom); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("barStyle") public static UIBarStyle getBarStyle(UIToolbar __self__, Selector __cmd__) { return __self__.getBarStyle(); }
        @Callback @BindSelector("setBarStyle:") public static void setBarStyle(UIToolbar __self__, Selector __cmd__, UIBarStyle barStyle) { __self__.setBarStyle(barStyle); }
        @Callback @BindSelector("items") public static NSArray getItems(UIToolbar __self__, Selector __cmd__) { return __self__.getItems(); }
        @Callback @BindSelector("setItems:") public static void setItems(UIToolbar __self__, Selector __cmd__, NSArray items) { __self__.setItems(items); }
        @Callback @BindSelector("tintColor") public static UIColor getTintColor(UIToolbar __self__, Selector __cmd__) { return __self__.getTintColor(); }
        @Callback @BindSelector("setTintColor:") public static void setTintColor(UIToolbar __self__, Selector __cmd__, UIColor tintColor) { __self__.setTintColor(tintColor); }
        @Callback @BindSelector("isTranslucent") public static boolean isTranslucent(UIToolbar __self__, Selector __cmd__) { return __self__.isTranslucent(); }
        @Callback @BindSelector("setTranslucent:") public static void setTranslucent(UIToolbar __self__, Selector __cmd__, boolean translucent) { __self__.setTranslucent(translucent); }
        @Callback @BindSelector("backgroundImageForToolbarPosition:barMetrics:") public static UIImage getBackgroundImage(UIToolbar __self__, Selector __cmd__, UIToolbarPosition topOrBottom, UIBarMetrics barMetrics) { return __self__.getBackgroundImage(topOrBottom, barMetrics); }
        @Callback @BindSelector("shadowImageForToolbarPosition:") public static UIImage getShadowImage(UIToolbar __self__, Selector __cmd__, UIToolbarPosition topOrBottom) { return __self__.getShadowImage(topOrBottom); }
        @Callback @BindSelector("setBackgroundImage:forToolbarPosition:barMetrics:") public static void setBackgroundImage(UIToolbar __self__, Selector __cmd__, UIImage backgroundImage, UIToolbarPosition topOrBottom, UIBarMetrics barMetrics) { __self__.setBackgroundImage(backgroundImage, topOrBottom, barMetrics); }
        @Callback @BindSelector("setItems:animated:") public static void setItems(UIToolbar __self__, Selector __cmd__, NSArray items, boolean animated) { __self__.setItems(items, animated); }
        @Callback @BindSelector("setShadowImage:forToolbarPosition:") public static void setShadowImage(UIToolbar __self__, Selector __cmd__, UIImage shadowImage, UIToolbarPosition topOrBottom) { __self__.setShadowImage(shadowImage, topOrBottom); }
    }
    /*</callbacks>*/

}
