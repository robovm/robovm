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
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/URL">@property(nonatomic, copy) NSURL *URL</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("URL") public native NSURL getURL();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/URL">@property(nonatomic, copy) NSURL *URL</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setURL:") public native void setURL(NSURL v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/URLs">@property(nonatomic, copy) NSArray *URLs</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("URLs") public native NSArray getURLs();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/URLs">@property(nonatomic, copy) NSArray *URLs</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setURLs:") public native void setURLs(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/changeCount">@property(readonly, nonatomic) NSInteger changeCount</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("changeCount") public native int getChangeCount();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/color">@property(nonatomic, copy) UIColor *color</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("color") public native UIColor getColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/color">@property(nonatomic, copy) UIColor *color</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setColor:") public native void setColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/colors">@property(nonatomic, copy) NSArray *colors</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("colors") public native NSArray getColors();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/colors">@property(nonatomic, copy) NSArray *colors</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setColors:") public native void setColors(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/numberOfItems">@property(readonly, nonatomic) NSInteger numberOfItems</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("numberOfItems") public native int getCount();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/image">@property(nonatomic, copy) UIImage *image</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("image") public native UIImage getImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/image">@property(nonatomic, copy) UIImage *image</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setImage:") public native void setImage(UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/images">@property(nonatomic, copy) NSArray *images</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("images") public native NSArray getImages();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/images">@property(nonatomic, copy) NSArray *images</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setImages:") public native void setImages(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/items">@property(nonatomic,copy) NSArray *items</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("items") public native NSArray getItems();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/items">@property(nonatomic,copy) NSArray *items</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setItems:") public native void setItems(NSArray v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/name">@property(readonly, nonatomic) NSString *name</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("name") public native String getName();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/persistent">@property(getter=isPersistent, nonatomic) BOOL persistent</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("isPersistent") public native boolean isPersistent();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/persistent">@property(getter=isPersistent, nonatomic) BOOL persistent</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setPersistent:") public native void setPersistent(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/string">@property(nonatomic, copy) NSString *string</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("string") public native String getString();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/string">@property(nonatomic, copy) NSString *string</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setString:") public native void setString(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/strings">@property(nonatomic, copy) NSArray *strings</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("strings") public native NSArray getStrings();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIPasteboard_Class/Reference.html#//apple_ref/occ/instp/UIPasteboard/strings">@property(nonatomic, copy) NSArray *strings</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("setStrings:") public native void setStrings(NSArray v);
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

}
