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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html">UITabBar Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UITabBar /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITabBar /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UITabBar /*</name>*/.class);

    /*<constructors>*/
    protected UITabBar(SkipInit skipInit) { super(skipInit); }
    public UITabBar() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/backgroundImage">@property(nonatomic, retain) UIImage *backgroundImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("backgroundImage") public native UIImage getBackgroundImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/backgroundImage">@property(nonatomic, retain) UIImage *backgroundImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setBackgroundImage:") public native void setBackgroundImage(UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/delegate">@property(nonatomic, assign) id&amp;lt;UITabBarDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("delegate") public native UITabBarDelegate getDelegate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/delegate">@property(nonatomic, assign) id&amp;lt;UITabBarDelegate&amp;gt; delegate</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setDelegate:") public native void setDelegate(UITabBarDelegate v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/items">@property(nonatomic, copy) NSArray *items</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("items") public native NSArray getItems();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/items">@property(nonatomic, copy) NSArray *items</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setItems:") public native void setItems(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/selectedImageTintColor">@property(nonatomic, retain) UIColor *selectedImageTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("selectedImageTintColor") public native UIColor getSelectedImageTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/selectedImageTintColor">@property(nonatomic, retain) UIColor *selectedImageTintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setSelectedImageTintColor:") public native void setSelectedImageTintColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/selectedItem">@property(nonatomic, assign) UITabBarItem *selectedItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("selectedItem") public native UITabBarItem getSelectedItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/selectedItem">@property(nonatomic, assign) UITabBarItem *selectedItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setSelectedItem:") public native void setSelectedItem(UITabBarItem v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/selectionIndicatorImage">@property(nonatomic, retain) UIImage *selectionIndicatorImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("selectionIndicatorImage") public native UIImage getSelectionIndicatorImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/selectionIndicatorImage">@property(nonatomic, retain) UIImage *selectionIndicatorImage</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setSelectionIndicatorImage:") public native void setSelectionIndicatorImage(UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/shadowImage">@property(nonatomic,retain) UIImage *shadowImage</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("shadowImage") public native UIImage getShadowImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/shadowImage">@property(nonatomic,retain) UIImage *shadowImage</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setShadowImage:") public native void setShadowImage(UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("tintColor") public native UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBar/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(UIColor v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector beginCustomizingItems$ = Selector.register("beginCustomizingItems:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_beginCustomizing(UITabBar __self__, Selector __cmd__, NSArray items);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_beginCustomizingSuper(ObjCSuper __super__, UITabBar __self__, Selector __cmd__, NSArray items);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBar/beginCustomizingItems:">- (void)beginCustomizingItems:(NSArray *)items</a>
     * @since Available in iOS 2.0 and later.
     */
    public void beginCustomizing(NSArray items) {
        if (customClass) { objc_beginCustomizingSuper(getSuper(), this, beginCustomizingItems$, items); } else { objc_beginCustomizing(this, beginCustomizingItems$, items); }
    }
    
    private static final Selector endCustomizingAnimated$ = Selector.register("endCustomizingAnimated:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_endCustomizing(UITabBar __self__, Selector __cmd__, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_endCustomizingSuper(ObjCSuper __super__, UITabBar __self__, Selector __cmd__, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBar/endCustomizingAnimated:">- (BOOL)endCustomizingAnimated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean endCustomizing(boolean animated) {
        if (customClass) { return objc_endCustomizingSuper(getSuper(), this, endCustomizingAnimated$, animated); } else { return objc_endCustomizing(this, endCustomizingAnimated$, animated); }
    }
    
    private static final Selector isCustomizing = Selector.register("isCustomizing");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isCustomizing(UITabBar __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isCustomizingSuper(ObjCSuper __super__, UITabBar __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBar/isCustomizing">- (BOOL)isCustomizing</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isCustomizing() {
        if (customClass) { return objc_isCustomizingSuper(getSuper(), this, isCustomizing); } else { return objc_isCustomizing(this, isCustomizing); }
    }
    
    private static final Selector setItems$animated$ = Selector.register("setItems:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setItems(UITabBar __self__, Selector __cmd__, NSArray items, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setItemsSuper(ObjCSuper __super__, UITabBar __self__, Selector __cmd__, NSArray items, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBar_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBar/setItems:animated:">- (void)setItems:(NSArray *)items animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setItems(NSArray items, boolean animated) {
        if (customClass) { objc_setItemsSuper(getSuper(), this, setItems$animated$, items, animated); } else { objc_setItems(this, setItems$animated$, items, animated); }
    }
    /*</methods>*/

}
