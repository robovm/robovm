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
    
    private static final Selector backBarButtonItem = Selector.register("backBarButtonItem");
    @Bridge(symbol = "objc_msgSend") private native static UIBarButtonItem objc_getBackBarButtonItem(UINavigationItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIBarButtonItem objc_getBackBarButtonItemSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/backBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *backBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarButtonItem getBackBarButtonItem() {
        if (customClass) { return objc_getBackBarButtonItemSuper(getSuper(), this, backBarButtonItem); } else { return objc_getBackBarButtonItem(this, backBarButtonItem); }
    }
    
    private static final Selector setBackBarButtonItem$ = Selector.register("setBackBarButtonItem:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setBackBarButtonItem(UINavigationItem __self__, Selector __cmd__, UIBarButtonItem backBarButtonItem);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setBackBarButtonItemSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__, UIBarButtonItem backBarButtonItem);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/backBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *backBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setBackBarButtonItem(UIBarButtonItem backBarButtonItem) {
        if (customClass) { objc_setBackBarButtonItemSuper(getSuper(), this, setBackBarButtonItem$, backBarButtonItem); } else { objc_setBackBarButtonItem(this, setBackBarButtonItem$, backBarButtonItem); }
    }
    
    private static final Selector hidesBackButton = Selector.register("hidesBackButton");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isHidesBackButton(UINavigationItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isHidesBackButtonSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/hidesBackButton">@property(nonatomic, assign) BOOL hidesBackButton</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isHidesBackButton() {
        if (customClass) { return objc_isHidesBackButtonSuper(getSuper(), this, hidesBackButton); } else { return objc_isHidesBackButton(this, hidesBackButton); }
    }
    
    private static final Selector setHidesBackButton$ = Selector.register("setHidesBackButton:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setHidesBackButton(UINavigationItem __self__, Selector __cmd__, boolean hidesBackButton);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setHidesBackButtonSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__, boolean hidesBackButton);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/hidesBackButton">@property(nonatomic, assign) BOOL hidesBackButton</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setHidesBackButton(boolean hidesBackButton) {
        if (customClass) { objc_setHidesBackButtonSuper(getSuper(), this, setHidesBackButton$, hidesBackButton); } else { objc_setHidesBackButton(this, setHidesBackButton$, hidesBackButton); }
    }
    
    private static final Selector leftBarButtonItem = Selector.register("leftBarButtonItem");
    @Bridge(symbol = "objc_msgSend") private native static UIBarButtonItem objc_getLeftBarButtonItem(UINavigationItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIBarButtonItem objc_getLeftBarButtonItemSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *leftBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarButtonItem getLeftBarButtonItem() {
        if (customClass) { return objc_getLeftBarButtonItemSuper(getSuper(), this, leftBarButtonItem); } else { return objc_getLeftBarButtonItem(this, leftBarButtonItem); }
    }
    
    private static final Selector setLeftBarButtonItem$ = Selector.register("setLeftBarButtonItem:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setLeftBarButtonItem(UINavigationItem __self__, Selector __cmd__, UIBarButtonItem leftBarButtonItem);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setLeftBarButtonItemSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__, UIBarButtonItem leftBarButtonItem);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *leftBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setLeftBarButtonItem(UIBarButtonItem leftBarButtonItem) {
        if (customClass) { objc_setLeftBarButtonItemSuper(getSuper(), this, setLeftBarButtonItem$, leftBarButtonItem); } else { objc_setLeftBarButtonItem(this, setLeftBarButtonItem$, leftBarButtonItem); }
    }
    
    private static final Selector leftBarButtonItems = Selector.register("leftBarButtonItems");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getLeftBarButtonItems(UINavigationItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getLeftBarButtonItemsSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftBarButtonItems">@property(nonatomic, copy) NSArray *leftBarButtonItems</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSArray getLeftBarButtonItems() {
        if (customClass) { return objc_getLeftBarButtonItemsSuper(getSuper(), this, leftBarButtonItems); } else { return objc_getLeftBarButtonItems(this, leftBarButtonItems); }
    }
    
    private static final Selector setLeftBarButtonItems$ = Selector.register("setLeftBarButtonItems:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setLeftBarButtonItems(UINavigationItem __self__, Selector __cmd__, NSArray leftBarButtonItems);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setLeftBarButtonItemsSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__, NSArray leftBarButtonItems);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftBarButtonItems">@property(nonatomic, copy) NSArray *leftBarButtonItems</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setLeftBarButtonItems(NSArray leftBarButtonItems) {
        if (customClass) { objc_setLeftBarButtonItemsSuper(getSuper(), this, setLeftBarButtonItems$, leftBarButtonItems); } else { objc_setLeftBarButtonItems(this, setLeftBarButtonItems$, leftBarButtonItems); }
    }
    
    private static final Selector leftItemsSupplementBackButton = Selector.register("leftItemsSupplementBackButton");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isLeftItemsSupplementBackButton(UINavigationItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isLeftItemsSupplementBackButtonSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftItemsSupplementBackButton">@property BOOL leftItemsSupplementBackButton</a>
     * @since Available in iOS 5.0 and later.
     */
    public boolean isLeftItemsSupplementBackButton() {
        if (customClass) { return objc_isLeftItemsSupplementBackButtonSuper(getSuper(), this, leftItemsSupplementBackButton); } else { return objc_isLeftItemsSupplementBackButton(this, leftItemsSupplementBackButton); }
    }
    
    private static final Selector setLeftItemsSupplementBackButton$ = Selector.register("setLeftItemsSupplementBackButton:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setLeftItemsSupplementBackButton(UINavigationItem __self__, Selector __cmd__, boolean leftItemsSupplementBackButton);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setLeftItemsSupplementBackButtonSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__, boolean leftItemsSupplementBackButton);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/leftItemsSupplementBackButton">@property BOOL leftItemsSupplementBackButton</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setLeftItemsSupplementBackButton(boolean leftItemsSupplementBackButton) {
        if (customClass) { objc_setLeftItemsSupplementBackButtonSuper(getSuper(), this, setLeftItemsSupplementBackButton$, leftItemsSupplementBackButton); } else { objc_setLeftItemsSupplementBackButton(this, setLeftItemsSupplementBackButton$, leftItemsSupplementBackButton); }
    }
    
    private static final Selector prompt = Selector.register("prompt");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getPrompt(UINavigationItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getPromptSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/prompt">@property(nonatomic, copy) NSString *prompt</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getPrompt() {
        if (customClass) { return objc_getPromptSuper(getSuper(), this, prompt); } else { return objc_getPrompt(this, prompt); }
    }
    
    private static final Selector setPrompt$ = Selector.register("setPrompt:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setPrompt(UINavigationItem __self__, Selector __cmd__, String prompt);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setPromptSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__, String prompt);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/prompt">@property(nonatomic, copy) NSString *prompt</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setPrompt(String prompt) {
        if (customClass) { objc_setPromptSuper(getSuper(), this, setPrompt$, prompt); } else { objc_setPrompt(this, setPrompt$, prompt); }
    }
    
    private static final Selector rightBarButtonItem = Selector.register("rightBarButtonItem");
    @Bridge(symbol = "objc_msgSend") private native static UIBarButtonItem objc_getRightBarButtonItem(UINavigationItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIBarButtonItem objc_getRightBarButtonItemSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/rightBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *rightBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIBarButtonItem getRightBarButtonItem() {
        if (customClass) { return objc_getRightBarButtonItemSuper(getSuper(), this, rightBarButtonItem); } else { return objc_getRightBarButtonItem(this, rightBarButtonItem); }
    }
    
    private static final Selector setRightBarButtonItem$ = Selector.register("setRightBarButtonItem:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setRightBarButtonItem(UINavigationItem __self__, Selector __cmd__, UIBarButtonItem rightBarButtonItem);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setRightBarButtonItemSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__, UIBarButtonItem rightBarButtonItem);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/rightBarButtonItem">@property(nonatomic, retain) UIBarButtonItem *rightBarButtonItem</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setRightBarButtonItem(UIBarButtonItem rightBarButtonItem) {
        if (customClass) { objc_setRightBarButtonItemSuper(getSuper(), this, setRightBarButtonItem$, rightBarButtonItem); } else { objc_setRightBarButtonItem(this, setRightBarButtonItem$, rightBarButtonItem); }
    }
    
    private static final Selector rightBarButtonItems = Selector.register("rightBarButtonItems");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getRightBarButtonItems(UINavigationItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getRightBarButtonItemsSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/rightBarButtonItems">@property(nonatomic, copy) NSArray *rightBarButtonItems</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSArray getRightBarButtonItems() {
        if (customClass) { return objc_getRightBarButtonItemsSuper(getSuper(), this, rightBarButtonItems); } else { return objc_getRightBarButtonItems(this, rightBarButtonItems); }
    }
    
    private static final Selector setRightBarButtonItems$ = Selector.register("setRightBarButtonItems:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setRightBarButtonItems(UINavigationItem __self__, Selector __cmd__, NSArray rightBarButtonItems);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setRightBarButtonItemsSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__, NSArray rightBarButtonItems);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/rightBarButtonItems">@property(nonatomic, copy) NSArray *rightBarButtonItems</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setRightBarButtonItems(NSArray rightBarButtonItems) {
        if (customClass) { objc_setRightBarButtonItemsSuper(getSuper(), this, setRightBarButtonItems$, rightBarButtonItems); } else { objc_setRightBarButtonItems(this, setRightBarButtonItems$, rightBarButtonItems); }
    }
    
    private static final Selector title = Selector.register("title");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getTitle(UINavigationItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getTitleSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getTitle() {
        if (customClass) { return objc_getTitleSuper(getSuper(), this, title); } else { return objc_getTitle(this, title); }
    }
    
    private static final Selector setTitle$ = Selector.register("setTitle:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTitle(UINavigationItem __self__, Selector __cmd__, String title);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTitleSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__, String title);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTitle(String title) {
        if (customClass) { objc_setTitleSuper(getSuper(), this, setTitle$, title); } else { objc_setTitle(this, setTitle$, title); }
    }
    
    private static final Selector titleView = Selector.register("titleView");
    @Bridge(symbol = "objc_msgSend") private native static UIView objc_getTitleView(UINavigationItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIView objc_getTitleViewSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/titleView">@property(nonatomic, retain) UIView *titleView</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIView getTitleView() {
        if (customClass) { return objc_getTitleViewSuper(getSuper(), this, titleView); } else { return objc_getTitleView(this, titleView); }
    }
    
    private static final Selector setTitleView$ = Selector.register("setTitleView:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTitleView(UINavigationItem __self__, Selector __cmd__, UIView titleView);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTitleViewSuper(ObjCSuper __super__, UINavigationItem __self__, Selector __cmd__, UIView titleView);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UINavigationItem_Class/Reference/UINavigationItem.html#//apple_ref/occ/instp/UINavigationItem/titleView">@property(nonatomic, retain) UIView *titleView</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTitleView(UIView titleView) {
        if (customClass) { objc_setTitleViewSuper(getSuper(), this, setTitleView$, titleView); } else { objc_setTitleView(this, setTitleView$, titleView); }
    }
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
