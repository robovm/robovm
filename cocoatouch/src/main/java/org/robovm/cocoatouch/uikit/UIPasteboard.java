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
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html">UIPasteboard Class Reference</a>
 *   @since Available in iOS 3.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIPasteboard /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPasteboard /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIPasteboard /*</name>*/.class);

    /*<constructors>*/
    protected UIPasteboard(SkipInit skipInit) { super(skipInit); }
    public UIPasteboard() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector URL = Selector.register("URL");
    @Bridge(symbol = "objc_msgSend") private native static NSURL objc_getURL(UIPasteboard __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSURL objc_getURLSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/URL">@property(nonatomic, copy) NSURL *URL</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSURL getURL() {
        if (customClass) { return objc_getURLSuper(getSuper(), this, URL); } else { return objc_getURL(this, URL); }
    }
    
    private static final Selector setURL$ = Selector.register("setURL:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setURL(UIPasteboard __self__, Selector __cmd__, NSURL URL);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setURLSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, NSURL URL);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/URL">@property(nonatomic, copy) NSURL *URL</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setURL(NSURL URL) {
        if (customClass) { objc_setURLSuper(getSuper(), this, setURL$, URL); } else { objc_setURL(this, setURL$, URL); }
    }
    
    private static final Selector URLs = Selector.register("URLs");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getURLs(UIPasteboard __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getURLsSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/URLs">@property(nonatomic, copy) NSArray *URLs</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSArray getURLs() {
        if (customClass) { return objc_getURLsSuper(getSuper(), this, URLs); } else { return objc_getURLs(this, URLs); }
    }
    
    private static final Selector setURLs$ = Selector.register("setURLs:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setURLs(UIPasteboard __self__, Selector __cmd__, NSArray URLs);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setURLsSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, NSArray URLs);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/URLs">@property(nonatomic, copy) NSArray *URLs</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setURLs(NSArray URLs) {
        if (customClass) { objc_setURLsSuper(getSuper(), this, setURLs$, URLs); } else { objc_setURLs(this, setURLs$, URLs); }
    }
    
    private static final Selector changeCount = Selector.register("changeCount");
    @Bridge(symbol = "objc_msgSend") private native static int objc_getChangeCount(UIPasteboard __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_getChangeCountSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/changeCount">@property(readonly, nonatomic) NSInteger changeCount</a>
     * @since Available in iOS 3.0 and later.
     */
    public int getChangeCount() {
        if (customClass) { return objc_getChangeCountSuper(getSuper(), this, changeCount); } else { return objc_getChangeCount(this, changeCount); }
    }
    
    private static final Selector color = Selector.register("color");
    @Bridge(symbol = "objc_msgSend") private native static UIColor objc_getColor(UIPasteboard __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIColor objc_getColorSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/color">@property(nonatomic, copy) UIColor *color</a>
     * @since Available in iOS 3.0 and later.
     */
    public UIColor getColor() {
        if (customClass) { return objc_getColorSuper(getSuper(), this, color); } else { return objc_getColor(this, color); }
    }
    
    private static final Selector setColor$ = Selector.register("setColor:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setColor(UIPasteboard __self__, Selector __cmd__, UIColor color);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setColorSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, UIColor color);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/color">@property(nonatomic, copy) UIColor *color</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setColor(UIColor color) {
        if (customClass) { objc_setColorSuper(getSuper(), this, setColor$, color); } else { objc_setColor(this, setColor$, color); }
    }
    
    private static final Selector colors = Selector.register("colors");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getColors(UIPasteboard __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getColorsSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/colors">@property(nonatomic, copy) NSArray *colors</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSArray getColors() {
        if (customClass) { return objc_getColorsSuper(getSuper(), this, colors); } else { return objc_getColors(this, colors); }
    }
    
    private static final Selector setColors$ = Selector.register("setColors:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setColors(UIPasteboard __self__, Selector __cmd__, NSArray colors);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setColorsSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, NSArray colors);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/colors">@property(nonatomic, copy) NSArray *colors</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setColors(NSArray colors) {
        if (customClass) { objc_setColorsSuper(getSuper(), this, setColors$, colors); } else { objc_setColors(this, setColors$, colors); }
    }
    
    private static final Selector numberOfItems = Selector.register("numberOfItems");
    @Bridge(symbol = "objc_msgSend") private native static int objc_getCount(UIPasteboard __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_getCountSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/numberOfItems">@property(readonly, nonatomic) NSInteger numberOfItems</a>
     * @since Available in iOS 3.0 and later.
     */
    public int getCount() {
        if (customClass) { return objc_getCountSuper(getSuper(), this, numberOfItems); } else { return objc_getCount(this, numberOfItems); }
    }
    
    private static final Selector image = Selector.register("image");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getImage(UIPasteboard __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getImageSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/image">@property(nonatomic, copy) UIImage *image</a>
     * @since Available in iOS 3.0 and later.
     */
    public UIImage getImage() {
        if (customClass) { return objc_getImageSuper(getSuper(), this, image); } else { return objc_getImage(this, image); }
    }
    
    private static final Selector setImage$ = Selector.register("setImage:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setImage(UIPasteboard __self__, Selector __cmd__, UIImage image);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setImageSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, UIImage image);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/image">@property(nonatomic, copy) UIImage *image</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setImage(UIImage image) {
        if (customClass) { objc_setImageSuper(getSuper(), this, setImage$, image); } else { objc_setImage(this, setImage$, image); }
    }
    
    private static final Selector images = Selector.register("images");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getImages(UIPasteboard __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getImagesSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/images">@property(nonatomic, copy) NSArray *images</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSArray getImages() {
        if (customClass) { return objc_getImagesSuper(getSuper(), this, images); } else { return objc_getImages(this, images); }
    }
    
    private static final Selector setImages$ = Selector.register("setImages:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setImages(UIPasteboard __self__, Selector __cmd__, NSArray images);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setImagesSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, NSArray images);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/images">@property(nonatomic, copy) NSArray *images</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setImages(NSArray images) {
        if (customClass) { objc_setImagesSuper(getSuper(), this, setImages$, images); } else { objc_setImages(this, setImages$, images); }
    }
    
    private static final Selector items = Selector.register("items");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getItems(UIPasteboard __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getItemsSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/items">@property(nonatomic,copy) NSArray *items</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSArray getItems() {
        if (customClass) { return objc_getItemsSuper(getSuper(), this, items); } else { return objc_getItems(this, items); }
    }
    
    private static final Selector setItems$ = Selector.register("setItems:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setItems(UIPasteboard __self__, Selector __cmd__, NSArray items);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setItemsSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, NSArray items);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/items">@property(nonatomic,copy) NSArray *items</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setItems(NSArray items) {
        if (customClass) { objc_setItemsSuper(getSuper(), this, setItems$, items); } else { objc_setItems(this, setItems$, items); }
    }
    
    private static final Selector name = Selector.register("name");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getName(UIPasteboard __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getNameSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/name">@property(readonly, nonatomic) NSString *name</a>
     * @since Available in iOS 3.0 and later.
     */
    public String getName() {
        if (customClass) { return objc_getNameSuper(getSuper(), this, name); } else { return objc_getName(this, name); }
    }
    
    private static final Selector isPersistent = Selector.register("isPersistent");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isPersistent(UIPasteboard __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isPersistentSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/persistent">@property(getter=isPersistent, nonatomic) BOOL persistent</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean isPersistent() {
        if (customClass) { return objc_isPersistentSuper(getSuper(), this, isPersistent); } else { return objc_isPersistent(this, isPersistent); }
    }
    
    private static final Selector setPersistent$ = Selector.register("setPersistent:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setPersistent(UIPasteboard __self__, Selector __cmd__, boolean persistent);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setPersistentSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, boolean persistent);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/persistent">@property(getter=isPersistent, nonatomic) BOOL persistent</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setPersistent(boolean persistent) {
        if (customClass) { objc_setPersistentSuper(getSuper(), this, setPersistent$, persistent); } else { objc_setPersistent(this, setPersistent$, persistent); }
    }
    
    private static final Selector string = Selector.register("string");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getString(UIPasteboard __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getStringSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/string">@property(nonatomic, copy) NSString *string</a>
     * @since Available in iOS 3.0 and later.
     */
    public String getString() {
        if (customClass) { return objc_getStringSuper(getSuper(), this, string); } else { return objc_getString(this, string); }
    }
    
    private static final Selector setString$ = Selector.register("setString:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setString(UIPasteboard __self__, Selector __cmd__, String string);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setStringSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, String string);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/string">@property(nonatomic, copy) NSString *string</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setString(String string) {
        if (customClass) { objc_setStringSuper(getSuper(), this, setString$, string); } else { objc_setString(this, setString$, string); }
    }
    
    private static final Selector strings = Selector.register("strings");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getStrings(UIPasteboard __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getStringsSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/strings">@property(nonatomic, copy) NSArray *strings</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSArray getStrings() {
        if (customClass) { return objc_getStringsSuper(getSuper(), this, strings); } else { return objc_getStrings(this, strings); }
    }
    
    private static final Selector setStrings$ = Selector.register("setStrings:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setStrings(UIPasteboard __self__, Selector __cmd__, NSArray strings);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setStringsSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, NSArray strings);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/strings">@property(nonatomic, copy) NSArray *strings</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setStrings(NSArray strings) {
        if (customClass) { objc_setStringsSuper(getSuper(), this, setStrings$, strings); } else { objc_setStrings(this, setStrings$, strings); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector pasteboardWithName$create$ = Selector.register("pasteboardWithName:create:");
    @Bridge(symbol = "objc_msgSend") private native static UIPasteboard objc_fromName(ObjCClass __self__, Selector __cmd__, String pasteboardName, boolean create);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/clm/UIPasteboard/pasteboardWithName:create:">+ (UIPasteboard *)pasteboardWithName:(NSString *)pasteboardName create:(BOOL)create</a>
     * @since Available in iOS 3.0 and later.
     */
    public static UIPasteboard fromName(String pasteboardName, boolean create) {
        return objc_fromName(objCClass, pasteboardWithName$create$, pasteboardName, create);
    }
    
    private static final Selector generalPasteboard = Selector.register("generalPasteboard");
    @Bridge(symbol = "objc_msgSend") private native static UIPasteboard objc_getGeneral(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/clm/UIPasteboard/generalPasteboard">+ (UIPasteboard *)generalPasteboard</a>
     * @since Available in iOS 3.0 and later.
     */
    public static UIPasteboard getGeneral() {
        return objc_getGeneral(objCClass, generalPasteboard);
    }
    
    private static final Selector pasteboardWithUniqueName = Selector.register("pasteboardWithUniqueName");
    @Bridge(symbol = "objc_msgSend") private native static UIPasteboard objc_getUnique(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/clm/UIPasteboard/pasteboardWithUniqueName">+ (UIPasteboard *)pasteboardWithUniqueName</a>
     * @since Available in iOS 3.0 and later.
     */
    public static UIPasteboard getUnique() {
        return objc_getUnique(objCClass, pasteboardWithUniqueName);
    }
    
    private static final Selector removePasteboardWithName$ = Selector.register("removePasteboardWithName:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_remove(ObjCClass __self__, Selector __cmd__, String pasteboardName);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/clm/UIPasteboard/removePasteboardWithName:">+ (void)removePasteboardWithName:(NSString *)pasteboardName</a>
     * @since Available in iOS 3.0 and later.
     */
    public static void remove(String pasteboardName) {
        objc_remove(objCClass, removePasteboardWithName$, pasteboardName);
    }
    
    private static final Selector addItems$ = Selector.register("addItems:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_addItems(UIPasteboard __self__, Selector __cmd__, NSArray items);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_addItemsSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, NSArray items);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/addItems:">- (void)addItems:(NSArray *)items</a>
     * @since Available in iOS 3.0 and later.
     */
    public void addItems(NSArray items) {
        if (customClass) { objc_addItemsSuper(getSuper(), this, addItems$, items); } else { objc_addItems(this, addItems$, items); }
    }
    
    private static final Selector containsPasteboardTypes$ = Selector.register("containsPasteboardTypes:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_contains(UIPasteboard __self__, Selector __cmd__, NSArray pasteboardTypes);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_containsSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, NSArray pasteboardTypes);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/containsPasteboardTypes:">- (BOOL)containsPasteboardTypes:(NSArray *)pasteboardTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean contains(NSArray pasteboardTypes) {
        if (customClass) { return objc_containsSuper(getSuper(), this, containsPasteboardTypes$, pasteboardTypes); } else { return objc_contains(this, containsPasteboardTypes$, pasteboardTypes); }
    }
    
    private static final Selector containsPasteboardTypes$inItemSet$ = Selector.register("containsPasteboardTypes:inItemSet:");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_contains(UIPasteboard __self__, Selector __cmd__, NSArray pasteboardTypes, NSIndexSet itemSet);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_containsSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, NSArray pasteboardTypes, NSIndexSet itemSet);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/containsPasteboardTypes:inItemSet:">- (BOOL)containsPasteboardTypes:(NSArray *)pasteboardTypes inItemSet:(NSIndexSet *)itemSet</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean contains(NSArray pasteboardTypes, NSIndexSet itemSet) {
        if (customClass) { return objc_containsSuper(getSuper(), this, containsPasteboardTypes$inItemSet$, pasteboardTypes, itemSet); } else { return objc_contains(this, containsPasteboardTypes$inItemSet$, pasteboardTypes, itemSet); }
    }
    
    private static final Selector dataForPasteboardType$ = Selector.register("dataForPasteboardType:");
    @Bridge(symbol = "objc_msgSend") private native static NSData objc_getData(UIPasteboard __self__, Selector __cmd__, String pasteboardType);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSData objc_getDataSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, String pasteboardType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/dataForPasteboardType:">- (NSData *)dataForPasteboardType:(NSString *)pasteboardType</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSData getData(String pasteboardType) {
        if (customClass) { return objc_getDataSuper(getSuper(), this, dataForPasteboardType$, pasteboardType); } else { return objc_getData(this, dataForPasteboardType$, pasteboardType); }
    }
    
    private static final Selector dataForPasteboardType$inItemSet$ = Selector.register("dataForPasteboardType:inItemSet:");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getData(UIPasteboard __self__, Selector __cmd__, String pasteboardType, NSIndexSet itemSet);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getDataSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, String pasteboardType, NSIndexSet itemSet);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/dataForPasteboardType:inItemSet:">- (NSArray *)dataForPasteboardType:(NSString *)pasteboardType inItemSet:(NSIndexSet *)itemSet</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSArray getData(String pasteboardType, NSIndexSet itemSet) {
        if (customClass) { return objc_getDataSuper(getSuper(), this, dataForPasteboardType$inItemSet$, pasteboardType, itemSet); } else { return objc_getData(this, dataForPasteboardType$inItemSet$, pasteboardType, itemSet); }
    }
    
    private static final Selector itemSetWithPasteboardTypes$ = Selector.register("itemSetWithPasteboardTypes:");
    @Bridge(symbol = "objc_msgSend") private native static NSIndexSet objc_getItemsWithTypes(UIPasteboard __self__, Selector __cmd__, NSArray pasteboardTypes);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSIndexSet objc_getItemsWithTypesSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, NSArray pasteboardTypes);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/itemSetWithPasteboardTypes:">- (NSIndexSet *)itemSetWithPasteboardTypes:(NSArray *)pasteboardTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSIndexSet getItemsWithTypes(NSArray pasteboardTypes) {
        if (customClass) { return objc_getItemsWithTypesSuper(getSuper(), this, itemSetWithPasteboardTypes$, pasteboardTypes); } else { return objc_getItemsWithTypes(this, itemSetWithPasteboardTypes$, pasteboardTypes); }
    }
    
    private static final Selector pasteboardTypes = Selector.register("pasteboardTypes");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getTypes(UIPasteboard __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getTypesSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/pasteboardTypes">- (NSArray *)pasteboardTypes</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSArray getTypes() {
        if (customClass) { return objc_getTypesSuper(getSuper(), this, pasteboardTypes); } else { return objc_getTypes(this, pasteboardTypes); }
    }
    
    private static final Selector pasteboardTypesForItemSet$ = Selector.register("pasteboardTypesForItemSet:");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getTypes(UIPasteboard __self__, Selector __cmd__, NSIndexSet itemSet);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getTypesSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, NSIndexSet itemSet);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/pasteboardTypesForItemSet:">- (NSArray *)pasteboardTypesForItemSet:(NSIndexSet *)itemSet</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSArray getTypes(NSIndexSet itemSet) {
        if (customClass) { return objc_getTypesSuper(getSuper(), this, pasteboardTypesForItemSet$, itemSet); } else { return objc_getTypes(this, pasteboardTypesForItemSet$, itemSet); }
    }
    
    private static final Selector valueForPasteboardType$ = Selector.register("valueForPasteboardType:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_getValue(UIPasteboard __self__, Selector __cmd__, String pasteboardType);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSObject objc_getValueSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, String pasteboardType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/valueForPasteboardType:">- (id)valueForPasteboardType:(NSString *)pasteboardType</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSObject getValue(String pasteboardType) {
        if (customClass) { return objc_getValueSuper(getSuper(), this, valueForPasteboardType$, pasteboardType); } else { return objc_getValue(this, valueForPasteboardType$, pasteboardType); }
    }
    
    private static final Selector valuesForPasteboardType$inItemSet$ = Selector.register("valuesForPasteboardType:inItemSet:");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getValues(UIPasteboard __self__, Selector __cmd__, String pasteboardType, NSIndexSet itemSet);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSArray objc_getValuesSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, String pasteboardType, NSIndexSet itemSet);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/valuesForPasteboardType:inItemSet:">- (NSArray *)valuesForPasteboardType:(NSString *)pasteboardType inItemSet:(NSIndexSet *)itemSet</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSArray getValues(String pasteboardType, NSIndexSet itemSet) {
        if (customClass) { return objc_getValuesSuper(getSuper(), this, valuesForPasteboardType$inItemSet$, pasteboardType, itemSet); } else { return objc_getValues(this, valuesForPasteboardType$inItemSet$, pasteboardType, itemSet); }
    }
    
    private static final Selector setData$forPasteboardType$ = Selector.register("setData:forPasteboardType:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setData(UIPasteboard __self__, Selector __cmd__, NSData data, String pasteboardType);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setDataSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, NSData data, String pasteboardType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/setData:forPasteboardType:">- (void)setData:(NSData *)data forPasteboardType:(NSString *)pasteboardType</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setData(NSData data, String pasteboardType) {
        if (customClass) { objc_setDataSuper(getSuper(), this, setData$forPasteboardType$, data, pasteboardType); } else { objc_setData(this, setData$forPasteboardType$, data, pasteboardType); }
    }
    
    private static final Selector setValue$forPasteboardType$ = Selector.register("setValue:forPasteboardType:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setValue(UIPasteboard __self__, Selector __cmd__, NSObject value, String pasteboardType);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setValueSuper(ObjCSuper __super__, UIPasteboard __self__, Selector __cmd__, NSObject value, String pasteboardType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instm/UIPasteboard/setValue:forPasteboardType:">- (void)setValue:(id)value forPasteboardType:(NSString *)pasteboardType</a>
     * @since Available in iOS 3.0 and later.
     */
    public void setValue(NSObject value, String pasteboardType) {
        if (customClass) { objc_setValueSuper(getSuper(), this, setValue$forPasteboardType$, value, pasteboardType); } else { objc_setValue(this, setValue$forPasteboardType$, value, pasteboardType); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("addItems:") public static void addItems(UIPasteboard __self__, Selector __cmd__, NSArray items) { __self__.addItems(items); }
        @Callback @BindSelector("containsPasteboardTypes:") public static boolean contains(UIPasteboard __self__, Selector __cmd__, NSArray pasteboardTypes) { return __self__.contains(pasteboardTypes); }
        @Callback @BindSelector("containsPasteboardTypes:inItemSet:") public static boolean contains(UIPasteboard __self__, Selector __cmd__, NSArray pasteboardTypes, NSIndexSet itemSet) { return __self__.contains(pasteboardTypes, itemSet); }
        @Callback @BindSelector("dataForPasteboardType:") public static NSData getData(UIPasteboard __self__, Selector __cmd__, String pasteboardType) { return __self__.getData(pasteboardType); }
        @Callback @BindSelector("dataForPasteboardType:inItemSet:") public static NSArray getData(UIPasteboard __self__, Selector __cmd__, String pasteboardType, NSIndexSet itemSet) { return __self__.getData(pasteboardType, itemSet); }
        @Callback @BindSelector("itemSetWithPasteboardTypes:") public static NSIndexSet getItemsWithTypes(UIPasteboard __self__, Selector __cmd__, NSArray pasteboardTypes) { return __self__.getItemsWithTypes(pasteboardTypes); }
        @Callback @BindSelector("pasteboardTypes") public static NSArray getTypes(UIPasteboard __self__, Selector __cmd__) { return __self__.getTypes(); }
        @Callback @BindSelector("pasteboardTypesForItemSet:") public static NSArray getTypes(UIPasteboard __self__, Selector __cmd__, NSIndexSet itemSet) { return __self__.getTypes(itemSet); }
        @Callback @BindSelector("valueForPasteboardType:") public static NSObject getValue(UIPasteboard __self__, Selector __cmd__, String pasteboardType) { return __self__.getValue(pasteboardType); }
        @Callback @BindSelector("valuesForPasteboardType:inItemSet:") public static NSArray getValues(UIPasteboard __self__, Selector __cmd__, String pasteboardType, NSIndexSet itemSet) { return __self__.getValues(pasteboardType, itemSet); }
        @Callback @BindSelector("setData:forPasteboardType:") public static void setData(UIPasteboard __self__, Selector __cmd__, NSData data, String pasteboardType) { __self__.setData(data, pasteboardType); }
        @Callback @BindSelector("setValue:forPasteboardType:") public static void setValue(UIPasteboard __self__, Selector __cmd__, NSObject value, String pasteboardType) { __self__.setValue(value, pasteboardType); }
    }
    /*</callbacks>*/

}
