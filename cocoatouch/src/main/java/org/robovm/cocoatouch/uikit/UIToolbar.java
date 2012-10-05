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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html">UIToolbar Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIToolbar /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIToolbar /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIToolbar /*</name>*/.class);

    /*<constructors>*/
    protected UIToolbar(SkipInit skipInit) { super(skipInit); }
    public UIToolbar() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/barStyle">@property(nonatomic) UIBarStyle barStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("barStyle") public native UIBarStyle getBarStyle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/barStyle">@property(nonatomic) UIBarStyle barStyle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBarStyle:") public native void setBarStyle(UIBarStyle v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/items">@property(nonatomic, copy) NSArray *items</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("items") public native NSArray getItems();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/items">@property(nonatomic, copy) NSArray *items</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setItems:") public native void setItems(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tintColor") public native UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/translucent">@property(nonatomic, assign, getter=isTranslucent) BOOL translucent</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isTranslucent") public native boolean isTranslucent();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instp/UIToolbar/translucent">@property(nonatomic, assign, getter=isTranslucent) BOOL translucent</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setTranslucent:") public native void setTranslucent(boolean v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector backgroundImageForToolbarPosition$barMetrics$ = Selector.register("backgroundImageForToolbarPosition:barMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getBackgroundImage(UIToolbar __self__, Selector __cmd__, UIToolbarPosition topOrBottom, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getBackgroundImageSuper(ObjCSuper __super__, UIToolbar __self__, Selector __cmd__, UIToolbarPosition topOrBottom, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instm/UIToolbar/backgroundImageForToolbarPosition:barMetrics:">- (UIImage *)backgroundImageForToolbarPosition:(UIToolbarPosition)topOrBottom barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getBackgroundImage(UIToolbarPosition topOrBottom, UIBarMetrics barMetrics) {
        if (customClass) { return objc_getBackgroundImageSuper(getSuper(), this, backgroundImageForToolbarPosition$barMetrics$, topOrBottom, barMetrics); } else { return objc_getBackgroundImage(this, backgroundImageForToolbarPosition$barMetrics$, topOrBottom, barMetrics); }
    }
    
    private static final Selector shadowImageForToolbarPosition$ = Selector.register("shadowImageForToolbarPosition:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getShadowImage(UIToolbar __self__, Selector __cmd__, UIToolbarPosition topOrBottom);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getShadowImageSuper(ObjCSuper __super__, UIToolbar __self__, Selector __cmd__, UIToolbarPosition topOrBottom);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instm/UIToolbar/shadowImageForToolbarPosition:">- (UIImage *)shadowImageForToolbarPosition:(UIToolbarPosition)topOrBottom</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIImage getShadowImage(UIToolbarPosition topOrBottom) {
        if (customClass) { return objc_getShadowImageSuper(getSuper(), this, shadowImageForToolbarPosition$, topOrBottom); } else { return objc_getShadowImage(this, shadowImageForToolbarPosition$, topOrBottom); }
    }
    
    private static final Selector setBackgroundImage$forToolbarPosition$barMetrics$ = Selector.register("setBackgroundImage:forToolbarPosition:barMetrics:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setBackgroundImage(UIToolbar __self__, Selector __cmd__, UIImage backgroundImage, UIToolbarPosition topOrBottom, UIBarMetrics barMetrics);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setBackgroundImageSuper(ObjCSuper __super__, UIToolbar __self__, Selector __cmd__, UIImage backgroundImage, UIToolbarPosition topOrBottom, UIBarMetrics barMetrics);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instm/UIToolbar/setBackgroundImage:forToolbarPosition:barMetrics:">- (void)setBackgroundImage:(UIImage *)backgroundImage forToolbarPosition:(UIToolbarPosition)topOrBottom barMetrics:(UIBarMetrics)barMetrics</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setBackgroundImage(UIImage backgroundImage, UIToolbarPosition topOrBottom, UIBarMetrics barMetrics) {
        if (customClass) { objc_setBackgroundImageSuper(getSuper(), this, setBackgroundImage$forToolbarPosition$barMetrics$, backgroundImage, topOrBottom, barMetrics); } else { objc_setBackgroundImage(this, setBackgroundImage$forToolbarPosition$barMetrics$, backgroundImage, topOrBottom, barMetrics); }
    }
    
    private static final Selector setItems$animated$ = Selector.register("setItems:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setItems(UIToolbar __self__, Selector __cmd__, NSArray items, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setItemsSuper(ObjCSuper __super__, UIToolbar __self__, Selector __cmd__, NSArray items, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instm/UIToolbar/setItems:animated:">- (void)setItems:(NSArray *)items animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setItems(NSArray items, boolean animated) {
        if (customClass) { objc_setItemsSuper(getSuper(), this, setItems$animated$, items, animated); } else { objc_setItems(this, setItems$animated$, items, animated); }
    }
    
    private static final Selector setShadowImage$forToolbarPosition$ = Selector.register("setShadowImage:forToolbarPosition:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setShadowImage(UIToolbar __self__, Selector __cmd__, UIImage shadowImage, UIToolbarPosition topOrBottom);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setShadowImageSuper(ObjCSuper __super__, UIToolbar __self__, Selector __cmd__, UIImage shadowImage, UIToolbarPosition topOrBottom);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIToolbar_Class/Reference/Reference.html#//apple_ref/occ/instm/UIToolbar/setShadowImage:forToolbarPosition:">- (void)setShadowImage:(UIImage *)shadowImage forToolbarPosition:(UIToolbarPosition)topOrBottom</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setShadowImage(UIImage shadowImage, UIToolbarPosition topOrBottom) {
        if (customClass) { objc_setShadowImageSuper(getSuper(), this, setShadowImage$forToolbarPosition$, shadowImage, topOrBottom); } else { objc_setShadowImage(this, setShadowImage$forToolbarPosition$, shadowImage, topOrBottom); }
    }
    /*</methods>*/

}
