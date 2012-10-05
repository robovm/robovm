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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html">UINavigationItem Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UINavigationItem /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UINavigationItem /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UINavigationItem /*</name>*/.class);

    /*<constructors>*/
    protected UINavigationItem(SkipInit skipInit) { super(skipInit); }
    public UINavigationItem() {}
    
    private static final Selector initWithTitle$ = Selector.register("initWithTitle:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithTitle(UINavigationItem __self__, Selector __cmd__, String title);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instm/UINavigationItem/initWithTitle:">- (id)initWithTitle:(NSString *)title</a>
     * @since Available in iOS 2.0 and later.
     */
    public UINavigationItem(String title) {
        super((SkipInit) null);
        objc_initWithTitle(this, initWithTitle$, title);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/backBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *backBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("backBarButtonItem") public native UIBarButtonItem getBackBarButtonItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/backBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *backBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBackBarButtonItem:") public native void setBackBarButtonItem(UIBarButtonItem v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/hidesBackButton">@property(nonatomic, assign) BOOL hidesBackButton</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("hidesBackButton") public native boolean isHidesBackButton();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/hidesBackButton">@property(nonatomic, assign) BOOL hidesBackButton</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setHidesBackButton:") public native void setHidesBackButton(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *leftBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("leftBarButtonItem") public native UIBarButtonItem getLeftBarButtonItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *leftBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setLeftBarButtonItem:") public native void setLeftBarButtonItem(UIBarButtonItem v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftBarButtonItems">@property(nonatomic, copy) NSArray *leftBarButtonItems</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("leftBarButtonItems") public native NSArray getLeftBarButtonItems();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftBarButtonItems">@property(nonatomic, copy) NSArray *leftBarButtonItems</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setLeftBarButtonItems:") public native void setLeftBarButtonItems(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftItemsSupplementBackButton">@property BOOL leftItemsSupplementBackButton</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("leftItemsSupplementBackButton") public native boolean isLeftItemsSupplementBackButton();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftItemsSupplementBackButton">@property BOOL leftItemsSupplementBackButton</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setLeftItemsSupplementBackButton:") public native void setLeftItemsSupplementBackButton(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/prompt">@property(nonatomic, copy) NSString *prompt</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("prompt") public native String getPrompt();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/prompt">@property(nonatomic, copy) NSString *prompt</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setPrompt:") public native void setPrompt(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/rightBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *rightBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("rightBarButtonItem") public native UIBarButtonItem getRightBarButtonItem();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/rightBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *rightBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setRightBarButtonItem:") public native void setRightBarButtonItem(UIBarButtonItem v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/rightBarButtonItems">@property(nonatomic, copy) NSArray *rightBarButtonItems</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("rightBarButtonItems") public native NSArray getRightBarButtonItems();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/rightBarButtonItems">@property(nonatomic, copy) NSArray *rightBarButtonItems</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setRightBarButtonItems:") public native void setRightBarButtonItems(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("title") public native String getTitle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTitle:") public native void setTitle(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/titleView">@property(nonatomic, retain) UIView *titleView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("titleView") public native UIView getTitleView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/titleView">@property(nonatomic, retain) UIView *titleView</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTitleView:") public native void setTitleView(UIView v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector setHidesBackButton$animated$ = Selector.register("setHidesBackButton:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setHidesBackButton(UINavigationItem __self__, Selector __cmd__, boolean hidesBackButton, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setHidesBackButtonSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__, boolean hidesBackButton, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instm/UINavigationItem/setHidesBackButton:animated:">- (void)setHidesBackButton:(BOOL)hidesBackButton animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setHidesBackButton(boolean hidesBackButton, boolean animated) {
        if (customClass) { objc_setHidesBackButtonSuper(getSuper(), this, setHidesBackButton$animated$, hidesBackButton, animated); } else { objc_setHidesBackButton(this, setHidesBackButton$animated$, hidesBackButton, animated); }
    }
    
    private static final Selector setLeftBarButtonItem$animated$ = Selector.register("setLeftBarButtonItem:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setLeftBarButtonItem(UINavigationItem __self__, Selector __cmd__, UIBarButtonItem item, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setLeftBarButtonItemSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__, UIBarButtonItem item, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instm/UINavigationItem/setLeftBarButtonItem:animated:">- (void)setLeftBarButtonItem:(UIBarButtonItem *)item animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setLeftBarButtonItem(UIBarButtonItem item, boolean animated) {
        if (customClass) { objc_setLeftBarButtonItemSuper(getSuper(), this, setLeftBarButtonItem$animated$, item, animated); } else { objc_setLeftBarButtonItem(this, setLeftBarButtonItem$animated$, item, animated); }
    }
    
    private static final Selector setLeftBarButtonItems$animated$ = Selector.register("setLeftBarButtonItems:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setLeftBarButtonItems(UINavigationItem __self__, Selector __cmd__, NSArray items, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setLeftBarButtonItemsSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__, NSArray items, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instm/UINavigationItem/setLeftBarButtonItems:animated:">- (void)setLeftBarButtonItems:(NSArray *)items animated:(BOOL)animated</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setLeftBarButtonItems(NSArray items, boolean animated) {
        if (customClass) { objc_setLeftBarButtonItemsSuper(getSuper(), this, setLeftBarButtonItems$animated$, items, animated); } else { objc_setLeftBarButtonItems(this, setLeftBarButtonItems$animated$, items, animated); }
    }
    
    private static final Selector setRightBarButtonItem$animated$ = Selector.register("setRightBarButtonItem:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setRightBarButtonItem(UINavigationItem __self__, Selector __cmd__, UIBarButtonItem item, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setRightBarButtonItemSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__, UIBarButtonItem item, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instm/UINavigationItem/setRightBarButtonItem:animated:">- (void)setRightBarButtonItem:(UIBarButtonItem *)item animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setRightBarButtonItem(UIBarButtonItem item, boolean animated) {
        if (customClass) { objc_setRightBarButtonItemSuper(getSuper(), this, setRightBarButtonItem$animated$, item, animated); } else { objc_setRightBarButtonItem(this, setRightBarButtonItem$animated$, item, animated); }
    }
    
    private static final Selector setRightBarButtonItems$animated$ = Selector.register("setRightBarButtonItems:animated:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setRightBarButtonItems(UINavigationItem __self__, Selector __cmd__, NSArray items, boolean animated);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setRightBarButtonItemsSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__, NSArray items, boolean animated);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instm/UINavigationItem/setRightBarButtonItems:animated:">- (void)setRightBarButtonItems:(NSArray *)items animated:(BOOL)animated</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setRightBarButtonItems(NSArray items, boolean animated) {
        if (customClass) { objc_setRightBarButtonItemsSuper(getSuper(), this, setRightBarButtonItems$animated$, items, animated); } else { objc_setRightBarButtonItems(this, setRightBarButtonItems$animated$, items, animated); }
    }
    /*</methods>*/

}
