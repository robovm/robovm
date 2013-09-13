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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html">UINavigationItem Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UINavigationItem /*</name>*/ 
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
    @Bridge private native static @Pointer long objc_initWithTitle(UINavigationItem __self__, Selector __cmd__, String title);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instm/UINavigationItem/initWithTitle:">- (id)initWithTitle:(NSString *)title</a>
     * @since Available in iOS 2.0 and later.
     */
    public UINavigationItem(String title) {
        super((SkipInit) null);
        initObject(objc_initWithTitle(this, initWithTitle$, title));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector backBarButtonItem = Selector.register("backBarButtonItem");
    @Bridge private native static UIBarButtonItem objc_getBackBarButtonItem(UINavigationItem __self__, Selector __cmd__);
    @Bridge private native static UIBarButtonItem objc_getBackBarButtonItemSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/backBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *backBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarButtonItem getBackBarButtonItem() {
        if (customClass) { return objc_getBackBarButtonItemSuper(getSuper(), backBarButtonItem); } else { return objc_getBackBarButtonItem(this, backBarButtonItem); }
    }
    
    private static final Selector setBackBarButtonItem$ = Selector.register("setBackBarButtonItem:");
    @Bridge private native static void objc_setBackBarButtonItem(UINavigationItem __self__, Selector __cmd__, UIBarButtonItem backBarButtonItem);
    @Bridge private native static void objc_setBackBarButtonItemSuper(ObjCSuper __super__, Selector __cmd__, UIBarButtonItem backBarButtonItem);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/backBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *backBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setBackBarButtonItem(UIBarButtonItem backBarButtonItem) {
        if (customClass) { objc_setBackBarButtonItemSuper(getSuper(), setBackBarButtonItem$, backBarButtonItem); } else { objc_setBackBarButtonItem(this, setBackBarButtonItem$, backBarButtonItem); }
    }
    
    private static final Selector hidesBackButton = Selector.register("hidesBackButton");
    @Bridge private native static boolean objc_isHidesBackButton(UINavigationItem __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isHidesBackButtonSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/hidesBackButton">@property(nonatomic, assign) BOOL hidesBackButton</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isHidesBackButton() {
        if (customClass) { return objc_isHidesBackButtonSuper(getSuper(), hidesBackButton); } else { return objc_isHidesBackButton(this, hidesBackButton); }
    }
    
    private static final Selector setHidesBackButton$ = Selector.register("setHidesBackButton:");
    @Bridge private native static void objc_setHidesBackButton(UINavigationItem __self__, Selector __cmd__, boolean hidesBackButton);
    @Bridge private native static void objc_setHidesBackButtonSuper(ObjCSuper __super__, Selector __cmd__, boolean hidesBackButton);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/hidesBackButton">@property(nonatomic, assign) BOOL hidesBackButton</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setHidesBackButton(boolean hidesBackButton) {
        if (customClass) { objc_setHidesBackButtonSuper(getSuper(), setHidesBackButton$, hidesBackButton); } else { objc_setHidesBackButton(this, setHidesBackButton$, hidesBackButton); }
    }
    
    private static final Selector leftBarButtonItem = Selector.register("leftBarButtonItem");
    @Bridge private native static UIBarButtonItem objc_getLeftBarButtonItem(UINavigationItem __self__, Selector __cmd__);
    @Bridge private native static UIBarButtonItem objc_getLeftBarButtonItemSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *leftBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarButtonItem getLeftBarButtonItem() {
        if (customClass) { return objc_getLeftBarButtonItemSuper(getSuper(), leftBarButtonItem); } else { return objc_getLeftBarButtonItem(this, leftBarButtonItem); }
    }
    
    private static final Selector setLeftBarButtonItem$ = Selector.register("setLeftBarButtonItem:");
    @Bridge private native static void objc_setLeftBarButtonItem(UINavigationItem __self__, Selector __cmd__, UIBarButtonItem leftBarButtonItem);
    @Bridge private native static void objc_setLeftBarButtonItemSuper(ObjCSuper __super__, Selector __cmd__, UIBarButtonItem leftBarButtonItem);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *leftBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setLeftBarButtonItem(UIBarButtonItem leftBarButtonItem) {
        if (customClass) { objc_setLeftBarButtonItemSuper(getSuper(), setLeftBarButtonItem$, leftBarButtonItem); } else { objc_setLeftBarButtonItem(this, setLeftBarButtonItem$, leftBarButtonItem); }
    }
    
    private static final Selector leftBarButtonItems = Selector.register("leftBarButtonItems");
    @Bridge private native static NSArray objc_getLeftBarButtonItems(UINavigationItem __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getLeftBarButtonItemsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftBarButtonItems">@property(nonatomic, copy) NSArray *leftBarButtonItems</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSArray getLeftBarButtonItems() {
        if (customClass) { return objc_getLeftBarButtonItemsSuper(getSuper(), leftBarButtonItems); } else { return objc_getLeftBarButtonItems(this, leftBarButtonItems); }
    }
    
    private static final Selector setLeftBarButtonItems$ = Selector.register("setLeftBarButtonItems:");
    @Bridge private native static void objc_setLeftBarButtonItems(UINavigationItem __self__, Selector __cmd__, NSArray leftBarButtonItems);
    @Bridge private native static void objc_setLeftBarButtonItemsSuper(ObjCSuper __super__, Selector __cmd__, NSArray leftBarButtonItems);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftBarButtonItems">@property(nonatomic, copy) NSArray *leftBarButtonItems</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setLeftBarButtonItems(NSArray leftBarButtonItems) {
        if (customClass) { objc_setLeftBarButtonItemsSuper(getSuper(), setLeftBarButtonItems$, leftBarButtonItems); } else { objc_setLeftBarButtonItems(this, setLeftBarButtonItems$, leftBarButtonItems); }
    }
    
    private static final Selector leftItemsSupplementBackButton = Selector.register("leftItemsSupplementBackButton");
    @Bridge private native static boolean objc_isLeftItemsSupplementBackButton(UINavigationItem __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isLeftItemsSupplementBackButtonSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftItemsSupplementBackButton">@property BOOL leftItemsSupplementBackButton</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isLeftItemsSupplementBackButton() {
        if (customClass) { return objc_isLeftItemsSupplementBackButtonSuper(getSuper(), leftItemsSupplementBackButton); } else { return objc_isLeftItemsSupplementBackButton(this, leftItemsSupplementBackButton); }
    }
    
    private static final Selector setLeftItemsSupplementBackButton$ = Selector.register("setLeftItemsSupplementBackButton:");
    @Bridge private native static void objc_setLeftItemsSupplementBackButton(UINavigationItem __self__, Selector __cmd__, boolean leftItemsSupplementBackButton);
    @Bridge private native static void objc_setLeftItemsSupplementBackButtonSuper(ObjCSuper __super__, Selector __cmd__, boolean leftItemsSupplementBackButton);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftItemsSupplementBackButton">@property BOOL leftItemsSupplementBackButton</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setLeftItemsSupplementBackButton(boolean leftItemsSupplementBackButton) {
        if (customClass) { objc_setLeftItemsSupplementBackButtonSuper(getSuper(), setLeftItemsSupplementBackButton$, leftItemsSupplementBackButton); } else { objc_setLeftItemsSupplementBackButton(this, setLeftItemsSupplementBackButton$, leftItemsSupplementBackButton); }
    }
    
    private static final Selector prompt = Selector.register("prompt");
    @Bridge private native static String objc_getPrompt(UINavigationItem __self__, Selector __cmd__);
    @Bridge private native static String objc_getPromptSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/prompt">@property(nonatomic, copy) NSString *prompt</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getPrompt() {
        if (customClass) { return objc_getPromptSuper(getSuper(), prompt); } else { return objc_getPrompt(this, prompt); }
    }
    
    private static final Selector setPrompt$ = Selector.register("setPrompt:");
    @Bridge private native static void objc_setPrompt(UINavigationItem __self__, Selector __cmd__, String prompt);
    @Bridge private native static void objc_setPromptSuper(ObjCSuper __super__, Selector __cmd__, String prompt);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/prompt">@property(nonatomic, copy) NSString *prompt</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setPrompt(String prompt) {
        if (customClass) { objc_setPromptSuper(getSuper(), setPrompt$, prompt); } else { objc_setPrompt(this, setPrompt$, prompt); }
    }
    
    private static final Selector rightBarButtonItem = Selector.register("rightBarButtonItem");
    @Bridge private native static UIBarButtonItem objc_getRightBarButtonItem(UINavigationItem __self__, Selector __cmd__);
    @Bridge private native static UIBarButtonItem objc_getRightBarButtonItemSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/rightBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *rightBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarButtonItem getRightBarButtonItem() {
        if (customClass) { return objc_getRightBarButtonItemSuper(getSuper(), rightBarButtonItem); } else { return objc_getRightBarButtonItem(this, rightBarButtonItem); }
    }
    
    private static final Selector setRightBarButtonItem$ = Selector.register("setRightBarButtonItem:");
    @Bridge private native static void objc_setRightBarButtonItem(UINavigationItem __self__, Selector __cmd__, UIBarButtonItem rightBarButtonItem);
    @Bridge private native static void objc_setRightBarButtonItemSuper(ObjCSuper __super__, Selector __cmd__, UIBarButtonItem rightBarButtonItem);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/rightBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *rightBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setRightBarButtonItem(UIBarButtonItem rightBarButtonItem) {
        if (customClass) { objc_setRightBarButtonItemSuper(getSuper(), setRightBarButtonItem$, rightBarButtonItem); } else { objc_setRightBarButtonItem(this, setRightBarButtonItem$, rightBarButtonItem); }
    }
    
    private static final Selector rightBarButtonItems = Selector.register("rightBarButtonItems");
    @Bridge private native static NSArray objc_getRightBarButtonItems(UINavigationItem __self__, Selector __cmd__);
    @Bridge private native static NSArray objc_getRightBarButtonItemsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/rightBarButtonItems">@property(nonatomic, copy) NSArray *rightBarButtonItems</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSArray getRightBarButtonItems() {
        if (customClass) { return objc_getRightBarButtonItemsSuper(getSuper(), rightBarButtonItems); } else { return objc_getRightBarButtonItems(this, rightBarButtonItems); }
    }
    
    private static final Selector setRightBarButtonItems$ = Selector.register("setRightBarButtonItems:");
    @Bridge private native static void objc_setRightBarButtonItems(UINavigationItem __self__, Selector __cmd__, NSArray rightBarButtonItems);
    @Bridge private native static void objc_setRightBarButtonItemsSuper(ObjCSuper __super__, Selector __cmd__, NSArray rightBarButtonItems);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/rightBarButtonItems">@property(nonatomic, copy) NSArray *rightBarButtonItems</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setRightBarButtonItems(NSArray rightBarButtonItems) {
        if (customClass) { objc_setRightBarButtonItemsSuper(getSuper(), setRightBarButtonItems$, rightBarButtonItems); } else { objc_setRightBarButtonItems(this, setRightBarButtonItems$, rightBarButtonItems); }
    }
    
    private static final Selector title = Selector.register("title");
    @Bridge private native static String objc_getTitle(UINavigationItem __self__, Selector __cmd__);
    @Bridge private native static String objc_getTitleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getTitle() {
        if (customClass) { return objc_getTitleSuper(getSuper(), title); } else { return objc_getTitle(this, title); }
    }
    
    private static final Selector setTitle$ = Selector.register("setTitle:");
    @Bridge private native static void objc_setTitle(UINavigationItem __self__, Selector __cmd__, String title);
    @Bridge private native static void objc_setTitleSuper(ObjCSuper __super__, Selector __cmd__, String title);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTitle(String title) {
        if (customClass) { objc_setTitleSuper(getSuper(), setTitle$, title); } else { objc_setTitle(this, setTitle$, title); }
    }
    
    private static final Selector titleView = Selector.register("titleView");
    @Bridge private native static UIView objc_getTitleView(UINavigationItem __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getTitleViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/titleView">@property(nonatomic, retain) UIView *titleView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getTitleView() {
        if (customClass) { return objc_getTitleViewSuper(getSuper(), titleView); } else { return objc_getTitleView(this, titleView); }
    }
    
    private static final Selector setTitleView$ = Selector.register("setTitleView:");
    @Bridge private native static void objc_setTitleView(UINavigationItem __self__, Selector __cmd__, UIView titleView);
    @Bridge private native static void objc_setTitleViewSuper(ObjCSuper __super__, Selector __cmd__, UIView titleView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/titleView">@property(nonatomic, retain) UIView *titleView</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTitleView(UIView titleView) {
        if (customClass) { objc_setTitleViewSuper(getSuper(), setTitleView$, titleView); } else { objc_setTitleView(this, setTitleView$, titleView); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector setHidesBackButton$animated$ = Selector.register("setHidesBackButton:animated:");
    @Bridge private native static void objc_setHidesBackButton(UINavigationItem __self__, Selector __cmd__, boolean hidesBackButton, boolean animated);
    @Bridge private native static void objc_setHidesBackButtonSuper(ObjCSuper __super__, Selector __cmd__, boolean hidesBackButton, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instm/UINavigationItem/setHidesBackButton:animated:">- (void)setHidesBackButton:(BOOL)hidesBackButton animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setHidesBackButton(boolean hidesBackButton, boolean animated) {
        if (customClass) { objc_setHidesBackButtonSuper(getSuper(), setHidesBackButton$animated$, hidesBackButton, animated); } else { objc_setHidesBackButton(this, setHidesBackButton$animated$, hidesBackButton, animated); }
    }
    
    private static final Selector setLeftBarButtonItem$animated$ = Selector.register("setLeftBarButtonItem:animated:");
    @Bridge private native static void objc_setLeftBarButtonItem(UINavigationItem __self__, Selector __cmd__, UIBarButtonItem item, boolean animated);
    @Bridge private native static void objc_setLeftBarButtonItemSuper(ObjCSuper __super__, Selector __cmd__, UIBarButtonItem item, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instm/UINavigationItem/setLeftBarButtonItem:animated:">- (void)setLeftBarButtonItem:(UIBarButtonItem *)item animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setLeftBarButtonItem(UIBarButtonItem item, boolean animated) {
        if (customClass) { objc_setLeftBarButtonItemSuper(getSuper(), setLeftBarButtonItem$animated$, item, animated); } else { objc_setLeftBarButtonItem(this, setLeftBarButtonItem$animated$, item, animated); }
    }
    
    private static final Selector setLeftBarButtonItems$animated$ = Selector.register("setLeftBarButtonItems:animated:");
    @Bridge private native static void objc_setLeftBarButtonItems(UINavigationItem __self__, Selector __cmd__, NSArray items, boolean animated);
    @Bridge private native static void objc_setLeftBarButtonItemsSuper(ObjCSuper __super__, Selector __cmd__, NSArray items, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instm/UINavigationItem/setLeftBarButtonItems:animated:">- (void)setLeftBarButtonItems:(NSArray *)items animated:(BOOL)animated</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setLeftBarButtonItems(NSArray items, boolean animated) {
        if (customClass) { objc_setLeftBarButtonItemsSuper(getSuper(), setLeftBarButtonItems$animated$, items, animated); } else { objc_setLeftBarButtonItems(this, setLeftBarButtonItems$animated$, items, animated); }
    }
    
    private static final Selector setRightBarButtonItem$animated$ = Selector.register("setRightBarButtonItem:animated:");
    @Bridge private native static void objc_setRightBarButtonItem(UINavigationItem __self__, Selector __cmd__, UIBarButtonItem item, boolean animated);
    @Bridge private native static void objc_setRightBarButtonItemSuper(ObjCSuper __super__, Selector __cmd__, UIBarButtonItem item, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instm/UINavigationItem/setRightBarButtonItem:animated:">- (void)setRightBarButtonItem:(UIBarButtonItem *)item animated:(BOOL)animated</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setRightBarButtonItem(UIBarButtonItem item, boolean animated) {
        if (customClass) { objc_setRightBarButtonItemSuper(getSuper(), setRightBarButtonItem$animated$, item, animated); } else { objc_setRightBarButtonItem(this, setRightBarButtonItem$animated$, item, animated); }
    }
    
    private static final Selector setRightBarButtonItems$animated$ = Selector.register("setRightBarButtonItems:animated:");
    @Bridge private native static void objc_setRightBarButtonItems(UINavigationItem __self__, Selector __cmd__, NSArray items, boolean animated);
    @Bridge private native static void objc_setRightBarButtonItemsSuper(ObjCSuper __super__, Selector __cmd__, NSArray items, boolean animated);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instm/UINavigationItem/setRightBarButtonItems:animated:">- (void)setRightBarButtonItems:(NSArray *)items animated:(BOOL)animated</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setRightBarButtonItems(NSArray items, boolean animated) {
        if (customClass) { objc_setRightBarButtonItemsSuper(getSuper(), setRightBarButtonItems$animated$, items, animated); } else { objc_setRightBarButtonItems(this, setRightBarButtonItems$animated$, items, animated); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("backBarButtonItem") public static UIBarButtonItem getBackBarButtonItem(UINavigationItem __self__, Selector __cmd__) { return __self__.getBackBarButtonItem(); }
        @Callback @BindSelector("setBackBarButtonItem:") public static void setBackBarButtonItem(UINavigationItem __self__, Selector __cmd__, UIBarButtonItem backBarButtonItem) { __self__.setBackBarButtonItem(backBarButtonItem); }
        @Callback @BindSelector("hidesBackButton") public static boolean isHidesBackButton(UINavigationItem __self__, Selector __cmd__) { return __self__.isHidesBackButton(); }
        @Callback @BindSelector("setHidesBackButton:") public static void setHidesBackButton(UINavigationItem __self__, Selector __cmd__, boolean hidesBackButton) { __self__.setHidesBackButton(hidesBackButton); }
        @Callback @BindSelector("leftBarButtonItem") public static UIBarButtonItem getLeftBarButtonItem(UINavigationItem __self__, Selector __cmd__) { return __self__.getLeftBarButtonItem(); }
        @Callback @BindSelector("setLeftBarButtonItem:") public static void setLeftBarButtonItem(UINavigationItem __self__, Selector __cmd__, UIBarButtonItem leftBarButtonItem) { __self__.setLeftBarButtonItem(leftBarButtonItem); }
        @Callback @BindSelector("leftBarButtonItems") public static NSArray getLeftBarButtonItems(UINavigationItem __self__, Selector __cmd__) { return __self__.getLeftBarButtonItems(); }
        @Callback @BindSelector("setLeftBarButtonItems:") public static void setLeftBarButtonItems(UINavigationItem __self__, Selector __cmd__, NSArray leftBarButtonItems) { __self__.setLeftBarButtonItems(leftBarButtonItems); }
        @Callback @BindSelector("leftItemsSupplementBackButton") public static boolean isLeftItemsSupplementBackButton(UINavigationItem __self__, Selector __cmd__) { return __self__.isLeftItemsSupplementBackButton(); }
        @Callback @BindSelector("setLeftItemsSupplementBackButton:") public static void setLeftItemsSupplementBackButton(UINavigationItem __self__, Selector __cmd__, boolean leftItemsSupplementBackButton) { __self__.setLeftItemsSupplementBackButton(leftItemsSupplementBackButton); }
        @Callback @BindSelector("prompt") public static String getPrompt(UINavigationItem __self__, Selector __cmd__) { return __self__.getPrompt(); }
        @Callback @BindSelector("setPrompt:") public static void setPrompt(UINavigationItem __self__, Selector __cmd__, String prompt) { __self__.setPrompt(prompt); }
        @Callback @BindSelector("rightBarButtonItem") public static UIBarButtonItem getRightBarButtonItem(UINavigationItem __self__, Selector __cmd__) { return __self__.getRightBarButtonItem(); }
        @Callback @BindSelector("setRightBarButtonItem:") public static void setRightBarButtonItem(UINavigationItem __self__, Selector __cmd__, UIBarButtonItem rightBarButtonItem) { __self__.setRightBarButtonItem(rightBarButtonItem); }
        @Callback @BindSelector("rightBarButtonItems") public static NSArray getRightBarButtonItems(UINavigationItem __self__, Selector __cmd__) { return __self__.getRightBarButtonItems(); }
        @Callback @BindSelector("setRightBarButtonItems:") public static void setRightBarButtonItems(UINavigationItem __self__, Selector __cmd__, NSArray rightBarButtonItems) { __self__.setRightBarButtonItems(rightBarButtonItems); }
        @Callback @BindSelector("title") public static String getTitle(UINavigationItem __self__, Selector __cmd__) { return __self__.getTitle(); }
        @Callback @BindSelector("setTitle:") public static void setTitle(UINavigationItem __self__, Selector __cmd__, String title) { __self__.setTitle(title); }
        @Callback @BindSelector("titleView") public static UIView getTitleView(UINavigationItem __self__, Selector __cmd__) { return __self__.getTitleView(); }
        @Callback @BindSelector("setTitleView:") public static void setTitleView(UINavigationItem __self__, Selector __cmd__, UIView titleView) { __self__.setTitleView(titleView); }
        @Callback @BindSelector("setHidesBackButton:animated:") public static void setHidesBackButton(UINavigationItem __self__, Selector __cmd__, boolean hidesBackButton, boolean animated) { __self__.setHidesBackButton(hidesBackButton, animated); }
        @Callback @BindSelector("setLeftBarButtonItem:animated:") public static void setLeftBarButtonItem(UINavigationItem __self__, Selector __cmd__, UIBarButtonItem item, boolean animated) { __self__.setLeftBarButtonItem(item, animated); }
        @Callback @BindSelector("setLeftBarButtonItems:animated:") public static void setLeftBarButtonItems(UINavigationItem __self__, Selector __cmd__, NSArray items, boolean animated) { __self__.setLeftBarButtonItems(items, animated); }
        @Callback @BindSelector("setRightBarButtonItem:animated:") public static void setRightBarButtonItem(UINavigationItem __self__, Selector __cmd__, UIBarButtonItem item, boolean animated) { __self__.setRightBarButtonItem(item, animated); }
        @Callback @BindSelector("setRightBarButtonItems:animated:") public static void setRightBarButtonItems(UINavigationItem __self__, Selector __cmd__, NSArray items, boolean animated) { __self__.setRightBarButtonItems(items, animated); }
    }
    /*</callbacks>*/

}
